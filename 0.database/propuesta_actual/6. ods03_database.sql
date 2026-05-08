-- Base de Datos ODS03: Salud y Bienestar
-- Sistema completo con triggers automáticos y vistas para administrador
-- La lógica común (tablas compartidas, vistas genéricas) está en ods_common.sql

CREATE DATABASE IF NOT EXISTS ods03 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods03;

SET @ODS_NUM = 3;

-- ────────────────────────────────────────────────────────────
-- CONFIGURACIÓN DE METADATOS CENTRALIZADOS
-- ────────────────────────────────────────────────────────────

INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion)
VALUES (@ODS_NUM, 'Salud y Bienestar', '#4C9F38', 'Garantizar una vida sana y promover el bienestar para todos en todas las edades');

INSERT IGNORE INTO ods_login.indicador_master (ods_id, codigo, nombre, formula_default, unidad_medida_default)
VALUES 
(@ODS_NUM, '3.1.1', 'Tasa de mortalidad materna', 'valor', 'Tasa/100k nacidos'),
(@ODS_NUM, '3.1.2', 'Proporción de partos con asistencia de personal sanitario especializado', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '3.2.1', 'Tasa de mortalidad de niños menores de 5 años', 'valor', 'Tasa/1k nacidos'),
(@ODS_NUM, '3.2.2', 'Tasa de mortalidad neonatal', 'valor', 'Tasa/1k nacidos'),
(@ODS_NUM, '3.3.1', 'Número de nuevas infecciones por el VIH por cada 1.000 personas no infectadas, desglosado por sexo, edad y poblaciones clave', 'valor', 'Tasa/1k'),
(@ODS_NUM, '3.3.2', 'Incidencia de la tuberculosis por cada 100.000 habitantes', 'valor', 'Incidencia'),
(@ODS_NUM, '3.3.3', 'Incidencia de la malaria por cada 1.000 habitantes', 'valor', 'Incidencia'),
(@ODS_NUM, '3.3.4', 'Incidencia de la hepatitis B por cada 100.000 habitantes', 'valor', 'Incidencia'),
(@ODS_NUM, '3.3.5', 'Número de personas que requieren intervenciones contra enfermedades tropicales desatendidas', 'valor', 'Personas'),
(@ODS_NUM, '3.4.1', 'Tasa de mortalidad atribuida a enfermedades cardiovasculares, cáncer, diabetes o enfermedades respiratorias crónicas', 'valor', 'Tasa'),
(@ODS_NUM, '3.4.2', 'Tasa de mortalidad por suicidio', 'valor', 'Tasa/100k'),
(@ODS_NUM, '3.5.1', 'Cobertura de las intervenciones de tratamiento (farmacológicas, psicosociales y servicios de rehabilitación y de postratamiento) de trastornos por abuso de sustancias', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '3.5.2', 'Consumo de alcohol per cápita (población de 15 años o más) en un año civil en litros de alcohol puro', 'valor', 'Litros'),
(@ODS_NUM, '3.6.1', 'Tasa de mortalidad por lesiones debidas a accidentes de tráfico', 'valor', 'Tasa/100k'),
(@ODS_NUM, '3.7.1', 'Proporción de mujeres en edad de procrear (de 15 a 49 años) que cubren sus necesidades de planificación familiar con métodos modernos', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '3.7.2', 'Tasa de fecundidad de las adolescentes (de 10 a 14 años y de 15 a 19 años) por cada 1.000 mujeres de ese grupo de edad', 'valor', 'Tasa/1k'),
(@ODS_NUM, '3.8.1', 'Cobertura de los servicios de salud esenciales', 'valor', 'Indice'),
(@ODS_NUM, '3.8.2', 'Proporción de la población con grandes gastos sanitarios por unidad de gasto de los hogares o de ingresos', '(p1 / p2) * 100', 'Porcentaje');

-- ────────────────────────────────────────────────────────────
-- TABLA DE AUDITORÍA (nombre único por ODS)
-- ────────────────────────────────────────────────────────────

CREATE TABLE auditoria_ods03 (
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
    meta_nombre             VARCHAR(300) NULL,
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
LEFT JOIN ods_login.ods_catalog cat ON 3 = cat.id
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
    pi.meta_nombre,
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
-- TRIGGERS ESPECÍFICOS (usan la tabla auditoria_ods03)
-- ────────────────────────────────────────────────────────────

-- [ ELIMINADA: La auditoría de inserción de proyectos ahora ocurre en ods_master ]

DELIMITER //

CREATE TRIGGER auditoria_indicadores_insert
AFTER INSERT ON proyecto_indicadores
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_ods03 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
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
    
    INSERT INTO auditoria_ods03 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
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
FROM auditoria_ods03 a
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
    SELECT * FROM auditoria_ods03 
    WHERE (tabla_afectada = 'proyecto_indicadores' AND registro_id IN (SELECT id FROM proyecto_indicadores WHERE proyecto_id = proyecto_id_param));
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- COMENTARIOS Y FINALIZACIÓN
-- ────────────────────────────────────────────────────────────

ALTER TABLE auditoria_ods03 COMMENT 'Auditoría interna de cambios en la base de datos ODS03';
CREATE INDEX idx_auditoria_fecha_tabla ON auditoria_ods03(fecha_cambio, tabla_afectada);

SELECT 'Base de datos ODS03 configurada exitosamente' AS mensaje, NOW() AS fecha_creacion;
