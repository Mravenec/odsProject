-- Base de Datos ODS17: Alianzas para Lograr los Objetivos
-- Sistema completo con triggers automáticos y vistas para administrador
-- La lógica común (tablas compartidas, vistas genéricas) está en ods_common.sql

CREATE DATABASE IF NOT EXISTS ods17 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods17;

SET @ODS_NUM = 17;

-- ────────────────────────────────────────────────────────────
-- CONFIGURACIÓN DE METADATOS CENTRALIZADOS
-- ────────────────────────────────────────────────────────────

-- 1. Asegurar que el ODS existe en el catálogo
INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion)
VALUES (@ODS_NUM, 'Alianzas para Lograr los Objetivos', '#19486A', 'Fortalecer los medios de ejecución y revitalizar la Alianza Mundial para el Desarrollo Sostenible');

-- 2. Sembrar indicadores maestros para este ODS
INSERT IGNORE INTO ods_login.indicador_master (ods_id, codigo, nombre, formula_default, unidad_medida_default)
VALUES 
(@ODS_NUM, '17.1.1', 'Total de ingresos del gobierno en proporción al PIB, desglosado por fuente', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.1.2', 'Proporción del presupuesto nacional financiado por impuestos internos', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.2.1', 'Asistencia oficial para el desarrollo neta, total y para los países menos adelantados como proporción del ingreso nacional bruto de los donantes', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.3.1', 'Recursos financieros adicionales movilizados para los países en desarrollo debido a la asistencia oficial para el desarrollo', 'valor', 'Monto'),
(@ODS_NUM, '17.3.2', 'Volumen de remesas (en dólares de los Estados Unidos) en proporción al PIB total', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.4.1', 'Servicio de la deuda en proporción a las exportaciones de bienes, servicios e ingresos primarios', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.5.1', 'Número de países que adoptan y aplican sistemas de promoción de las inversiones para los países menos adelantados', 'count', 'Paises'),
(@ODS_NUM, '17.6.1', 'Número de abonados a servicios de banda ancha fija por cada 100 habitantes, desglosado por región', 'valor', 'Abonados/100hab'),
(@ODS_NUM, '17.7.1', 'Total de los fondos destinados a los países en desarrollo y los países desarrollados con el fin de promover el desarrollo, la transferencia y la difusión de tecnologías ecológicamente racionales', 'valor', 'Monto'),
(@ODS_NUM, '17.8.1', 'Proporción de personas que utilizan Internet', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.9.1', 'Valor en dólares de la asistencia oficial para el desarrollo comprometida para los países en desarrollo', 'valor', 'Monto'),
(@ODS_NUM, '17.10.1', 'Promedio arancelario mundial ponderado', 'valor', 'Porcentaje'),
(@ODS_NUM, '17.11.1', 'Participación de los países en desarrollo y los países menos adelantados en las exportaciones mundiales', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '17.12.1', 'Promedio ponderado de los aranceles que enfrentan los países en desarrollo, los países menos adelantados y los pequeños Estados insulares en desarrollo', 'valor', 'Porcentaje'),
(@ODS_NUM, '17.13.1', 'Tablero macroeconómico', 'valor', 'Indice'),
(@ODS_NUM, '17.14.1', 'Número de países que cuentan con mecanismos para mejorar la coherencia de las políticas de desarrollo sostenible', 'count', 'Paises'),
(@ODS_NUM, '17.15.1', 'Grado de utilización de los marcos de resultados y las herramientas de planificación de los propios países por los proveedores de cooperación para el desarrollo', 'valor', 'Porcentaje'),
(@ODS_NUM, '17.16.1', 'Número de países que informan de sus progresos en los marcos de múltiples interesados para el seguimiento de la eficacia de las actividades de desarrollo', 'count', 'Paises'),
(@ODS_NUM, '17.17.1', 'Suma en dólares de los Estados Unidos prometida a las alianzas público-privadas centradas en la infraestructura', 'valor', 'Monto'),
(@ODS_NUM, '17.18.1', 'Indicadores de la capacidad estadística', 'valor', 'Indice'),
(@ODS_NUM, '17.18.2', 'Número de países cuya legislación nacional sobre estadísticas cumple los Principios Fundamentales de las Estadísticas Oficiales', 'count', 'Paises'),
(@ODS_NUM, '17.18.3', 'Número de países que cuentan con un plan estadístico nacional plenamente financiado y en proceso de aplicación', 'count', 'Paises'),
(@ODS_NUM, '17.19.1', 'Valor en dólares de todos los recursos proporcionados para fortalecer la capacidad estadística de los países en desarrollo', 'valor', 'Monto'),
(@ODS_NUM, '17.19.2', 'Proporción de países que a) han realizado al menos un censo en los últimos diez años; y b) han registrado el 100% de los nacimientos', '(p1 / p2) * 100', 'Porcentaje');

-- ────────────────────────────────────────────────────────────
-- TABLA DE AUDITORÍA (nombre único por ODS)
-- ────────────────────────────────────────────────────────────

CREATE TABLE auditoria_ods17 (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tabla_afectada VARCHAR(50) NOT NULL,
    registro_id INT NOT NULL,
    accion VARCHAR(20) NOT NULL,
    usuario_id INT,
    valores_anteriores JSON,
    valores_nuevos JSON,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),
    FOREIGN KEY (usuario_id) REFERENCES ods_login.usuarios(id) ON DELETE SET NULL,
    INDEX idx_tabla_registro (tabla_afectada, registro_id),
    INDEX idx_fecha (fecha_cambio)
);

-- ────────────────────────────────────────────────────────────
-- ESTRUCTURA COMÚN (STANDALONE - COMPATIBLE CON HEIDISQL)
-- ────────────────────────────────────────────────────────────
-- [ ELIMINADA: La tabla proyectos ahora vive en ods_master.proyectos ]

CREATE TABLE IF NOT EXISTS proyecto_indicadores (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id         INT NOT NULL,
    indicador_master_id INT NOT NULL,
    formula_custom      TEXT,
    valor_actual        DECIMAL(15,4) DEFAULT 0,
    meta_valor          DECIMAL(15,4) NOT NULL,
    meta_unidad         VARCHAR(50) NOT NULL,
    fecha_proxima_medicion DATE,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id) REFERENCES ods_master.proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (indicador_master_id) REFERENCES ods_login.indicador_master(id),
    INDEX idx_proyecto_master (proyecto_id, indicador_master_id)
);

CREATE TABLE IF NOT EXISTS proyecto_indicador_parametros (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_indicador_id INT NOT NULL,
    nombre_parametro    VARCHAR(50) NOT NULL,
    nombre_variable     VARCHAR(20),
    tipo_dato           ENUM('Integer', 'Decimal') NOT NULL DEFAULT 'Decimal',
    valor_actual        DECIMAL(15,4) DEFAULT 0,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_indicador_id) REFERENCES proyecto_indicadores(id) ON DELETE CASCADE,
    UNIQUE KEY uk_proyecto_param (proyecto_indicador_id, nombre_parametro)
);

CREATE TABLE IF NOT EXISTS mediciones_historicas (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_indicador_id INT NOT NULL,
    valor_calculado     DECIMAL(15,4) NOT NULL,
    fecha_medicion      DATE NOT NULL,
    responsable         VARCHAR(100),
    metodo_medicion     VARCHAR(100),
    observaciones       TEXT,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_indicador_id) REFERENCES proyecto_indicadores(id) ON DELETE CASCADE,
    INDEX idx_proyecto_indicador_fecha (proyecto_indicador_id, fecha_medicion)
);

CREATE TABLE IF NOT EXISTS medicion_parametro_valores (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    medicion_id         INT NOT NULL,
    parametro_id        INT NOT NULL,
    valor_ingresado     DECIMAL(15,4) NOT NULL,
    FOREIGN KEY (medicion_id) REFERENCES mediciones_historicas(id) ON DELETE CASCADE,
    FOREIGN KEY (parametro_id) REFERENCES proyecto_indicador_parametros(id) ON DELETE CASCADE
);

CREATE OR REPLACE VIEW vista_admin_resumen_general AS
SELECT
    p.id                 AS proyecto_id,
    p.nombre_proyecto,
    u.username           AS usuario_creador,
    s.nombre             AS sede_nombre,
    cat.nombre           AS ods_nombre,
    p.fecha_inicio,
    p.fecha_fin,
    p.estado,
    COUNT(DISTINCT pi.id) AS total_indicadores,
    COUNT(DISTINCT CASE WHEN pi.valor_actual >= pi.meta_valor THEN pi.id END) AS indicadores_logrados,
    ROUND(
        CASE
            WHEN COUNT(DISTINCT pi.id) > 0
            THEN (COUNT(DISTINCT CASE WHEN pi.valor_actual >= pi.meta_valor THEN pi.id END) * 100.0)
                 / COUNT(DISTINCT pi.id)
            ELSE 0
        END, 2
    ) AS progreso_porcentaje,
    p.created_at         AS fecha_creacion
FROM ods_master.proyectos p
LEFT JOIN ods_login.usuarios u    ON p.usuario_id = u.id
LEFT JOIN ods_login.sedes s       ON p.sede_id = s.id
LEFT JOIN ods_login.ods_catalog cat ON 17 = cat.id
LEFT JOIN proyecto_indicadores pi ON p.id = pi.proyecto_id
GROUP BY p.id, p.nombre_proyecto, u.username, s.nombre, cat.nombre,
         p.fecha_inicio, p.fecha_fin, p.estado, p.created_at;

CREATE OR REPLACE VIEW vista_admin_detalle_indicadores AS
SELECT
    p.id AS proyecto_id,
    p.nombre_proyecto,
    m.id AS indicador_master_id,
    m.codigo AS indicador_codigo,
    m.nombre AS indicador_nombre,
    pi.formula_custom,
    pi.valor_actual,
    pi.meta_valor,
    pi.meta_unidad,
    CASE
        WHEN pi.meta_valor IS NULL OR pi.meta_valor = 0 OR pi.valor_actual IS NULL THEN 'SIN DATOS'
        WHEN pi.valor_actual >= pi.meta_valor             THEN 'LOGRADO'
        WHEN pi.valor_actual >= (pi.meta_valor * 0.8)     THEN 'CERCA META'
        WHEN pi.valor_actual >= (pi.meta_valor * 0.5)     THEN 'PROGRESO'
        ELSE 'BAJO'
    END AS estado_indicador,
    ROUND(
        CASE 
            WHEN pi.meta_valor IS NULL OR pi.meta_valor = 0 THEN 0 
            ELSE (COALESCE(pi.valor_actual, 0) / pi.meta_valor) * 100 
        END, 2
    ) AS porcentaje_logro,
    pi.updated_at AS ultima_actualizacion
FROM ods_master.proyectos p
INNER JOIN proyecto_indicadores pi ON p.id = pi.proyecto_id
INNER JOIN ods_login.indicador_master m ON pi.indicador_master_id = m.id;

-- ────────────────────────────────────────────────────────────
-- TRIGGERS ESPECÍFICOS (usan la tabla auditoria_ods17)
-- ────────────────────────────────────────────────────────────

-- [ ELIMINADA: La auditoría de inserción de proyectos ahora ocurre en ods_master ]

DELIMITER //

CREATE TRIGGER auditoria_indicadores_insert
AFTER INSERT ON proyecto_indicadores
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_ods17 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
    VALUES ('proyecto_indicadores', NEW.id, 'INSERT', NULL,
            JSON_OBJECT(
                'proyecto_id', NEW.proyecto_id,
                'indicador_master_id', NEW.indicador_master_id,
                'valor_meta', NEW.meta_valor
            ));
END//

CREATE TRIGGER registrar_medicion_y_actualizar_valor
AFTER INSERT ON mediciones_historicas
FOR EACH ROW
BEGIN
    UPDATE proyecto_indicadores 
    SET valor_actual = NEW.valor_calculado,
        updated_at = CURRENT_TIMESTAMP
    WHERE id = NEW.proyecto_indicador_id;
    
    INSERT INTO auditoria_ods17 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
    VALUES ('mediciones_historicas', NEW.id, 'INSERT', NULL,
            JSON_OBJECT('indicador_id', NEW.proyecto_indicador_id, 'valor', NEW.valor_calculado));
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- VISTAS Y PROCEDIMIENTOS QUE REQUIEREN NOMBRE DE AUDITORÍA ÚNICA
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_auditoria_reciente AS
SELECT 
    a.id, a.tabla_afectada, a.registro_id, a.accion,
    u.username AS usuario, a.fecha_cambio, a.ip_address
FROM auditoria_ods17 a
LEFT JOIN ods_login.usuarios u ON a.usuario_id = u.id
ORDER BY a.fecha_cambio DESC;

DELIMITER //
CREATE PROCEDURE sp_admin_reporte_proyecto(IN proyecto_id_param INT)
BEGIN
    SELECT * FROM ods_master.proyectos WHERE id = proyecto_id_param;
    SELECT pi.*, m.codigo, m.nombre 
    FROM proyecto_indicadores pi
    JOIN ods_login.indicador_master m ON pi.indicador_master_id = m.id
    WHERE pi.proyecto_id = proyecto_id_param;
    SELECT * FROM auditoria_ods17 
    WHERE (tabla_afectada = 'proyecto_indicadores' AND registro_id IN (SELECT id FROM proyecto_indicadores WHERE proyecto_id = proyecto_id_param));
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- COMENTARIOS Y FINALIZACIÓN
-- ────────────────────────────────────────────────────────────
ALTER TABLE auditoria_ods17 COMMENT 'Auditoría interna de cambios en la base de datos ODS17';
CREATE INDEX idx_auditoria_fecha_tabla ON auditoria_ods17(fecha_cambio, tabla_afectada);

SELECT 'Base de datos ODS17 estandarizada exitosamente' AS mensaje;
