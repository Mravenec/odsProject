-- Base de Datos ODS12: Producción y Consumo Responsables
-- Sistema completo con triggers automáticos y vistas para administrador
-- La lógica común (tablas compartidas, vistas genéricas) está en ods_common.sql

CREATE DATABASE IF NOT EXISTS ods12 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods12;

SET @ODS_NUM = 12;

-- ────────────────────────────────────────────────────────────
-- CONFIGURACIÓN DE METADATOS CENTRALIZADOS
-- ────────────────────────────────────────────────────────────

INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion)
VALUES (@ODS_NUM, 'Producción y Consumo Responsables', '#BF8B2E', 'Garantizar modalidades de consumo y producción sostenibles');

INSERT IGNORE INTO ods_login.indicador_master (ods_id, codigo, nombre, formula_default, unidad_medida_default)
VALUES 
(@ODS_NUM, '12.1.1', 'Número de países con planes de acción nacionales de consumo y producción sostenibles o que incorporan el consumo y la producción sostenibles como prioridad o elemento en las políticas nacionales', 'count', 'Paises'),
(@ODS_NUM, '12.2.1', 'Huella material, huella material per cápita y huella material por unidad de PIB', 'valor', 'Toneladas/USD'),
(@ODS_NUM, '12.2.2', 'Consumo material doméstico, consumo material doméstico per cápita y consumo material doméstico por unidad de PIB', 'valor', 'Toneladas/USD'),
(@ODS_NUM, '12.3.1', 'a) Índice de pérdida de alimentos; y b) índice de desperdicio de alimentos', 'valor', 'Indice'),
(@ODS_NUM, '12.4.1', 'Número de partes en los acuerdos ambientales internacionales sobre desechos peligrosos y otros productos químicos que cumplen con sus compromisos y obligaciones de transmitir información, según lo exija cada acuerdo pertinente', 'count', 'Partes'),
(@ODS_NUM, '12.4.2', 'a) Cantidad de desechos peligrosos generados per cápita; y b) proporción de desechos peligrosos tratados, por tipo de tratamiento', 'valor', 'kg/Capita'),
(@ODS_NUM, '12.5.1', 'Tasa nacional de reciclado, toneladas de material reciclado', 'valor', 'Porcentaje'),
(@ODS_NUM, '12.6.1', 'Número de empresas que publican informes sobre sostenibilidad', 'count', 'Empresas'),
(@ODS_NUM, '12.7.1', 'Número de países que aplican políticas y planes de acción sobre adquisiciones públicas sostenibles', 'count', 'Paises'),
(@ODS_NUM, '12.8.1', 'Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible (incluida la educación sobre el cambio climático) se incorporan en v) las políticas educativas nacionales', 'valor', 'Grado'),
(@ODS_NUM, '12.a.1', 'Capacidad instalada de generación de energía renovable en los países en desarrollo (en vatios por habitante)', 'valor', 'Vatios/hab'),
(@ODS_NUM, '12.b.1', 'Número de estrategias o planes de acción de turismo sostenible y de instrumentos de seguimiento conexos con modalidades de aplicación financiadas y sometidas a seguimiento y evaluación', 'count', 'Unidades'),
(@ODS_NUM, '12.c.1', 'Cuantía de los subsidios a los combustibles fósiles por unidad de PIB (producción y consumo)', 'valor', 'Porcentaje');

-- ────────────────────────────────────────────────────────────
-- TABLA DE AUDITORÍA (nombre único por ODS)
-- ────────────────────────────────────────────────────────────

CREATE TABLE auditoria_ods12 (
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
CREATE TABLE IF NOT EXISTS proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    sede_id    INT NULL,
    nombre_proyecto VARCHAR(200) NOT NULL,
    objetivo_id TINYINT UNSIGNED NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    meta_general VARCHAR(500),
    estado ENUM('planificacion', 'activo', 'completado', 'cancelado') DEFAULT 'planificacion',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES ods_login.usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (sede_id)    REFERENCES ods_login.sedes(id) ON DELETE SET NULL,
    FOREIGN KEY (objetivo_id) REFERENCES ods_login.ods_catalog(id),
    INDEX idx_usuario (usuario_id),
    INDEX idx_sede    (sede_id),
    INDEX idx_objetivo (objetivo_id),
    INDEX idx_estado (estado)
);

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
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (indicador_master_id) REFERENCES ods_login.indicador_master(id),
    INDEX idx_proyecto_master (proyecto_id, indicador_master_id)
);

CREATE TABLE IF NOT EXISTS proyecto_indicador_parametros (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_indicador_id INT NOT NULL,
    nombre_parametro    VARCHAR(50) NOT NULL,
    tipo_dato           ENUM('Integer', 'Decimal') NOT NULL DEFAULT 'Decimal',
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
FROM proyectos p
LEFT JOIN ods_login.usuarios u    ON p.usuario_id = u.id
LEFT JOIN ods_login.sedes s       ON p.sede_id = s.id
LEFT JOIN ods_login.ods_catalog cat ON p.objetivo_id = cat.id
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
FROM proyectos p
INNER JOIN proyecto_indicadores pi ON p.id = pi.proyecto_id
INNER JOIN ods_login.indicador_master m ON pi.indicador_master_id = m.id;

-- ────────────────────────────────────────────────────────────
-- TRIGGERS ESPECÍFICOS
-- ────────────────────────────────────────────────────────────

DELIMITER //
CREATE TRIGGER auditoria_proyectos_insert
AFTER INSERT ON proyectos
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_ods12 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
    VALUES ('proyectos', NEW.id, 'INSERT', NEW.usuario_id, 
            JSON_OBJECT('nombre', NEW.nombre_proyecto, 'estado', NEW.estado));
END//

CREATE TRIGGER auditoria_indicadores_insert
AFTER INSERT ON proyecto_indicadores
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_ods12 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
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
    
    INSERT INTO auditoria_ods12 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
    VALUES ('mediciones_historicas', NEW.id, 'INSERT', NULL,
            JSON_OBJECT('indicador_id', NEW.proyecto_indicador_id, 'valor', NEW.valor_calculado));
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- VISTAS Y PROCEDIMIENTOS
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_auditoria_reciente AS
SELECT 
    a.id, a.tabla_afectada, a.registro_id, a.accion,
    u.username AS usuario, a.fecha_cambio, a.ip_address
FROM auditoria_ods12 a
LEFT JOIN ods_login.usuarios u ON a.usuario_id = u.id
ORDER BY a.fecha_cambio DESC;

DELIMITER //
CREATE PROCEDURE sp_admin_reporte_proyecto(IN proyecto_id_param INT)
BEGIN
    SELECT * FROM proyectos WHERE id = proyecto_id_param;
    SELECT pi.*, m.codigo, m.nombre 
    FROM proyecto_indicadores pi
    JOIN ods_login.indicador_master m ON pi.indicador_master_id = m.id
    WHERE pi.proyecto_id = proyecto_id_param;
    SELECT * FROM auditoria_ods12 
    WHERE (tabla_afectada = 'proyectos' AND registro_id = proyecto_id_param)
       OR (tabla_afectada = 'proyecto_indicadores' AND registro_id IN (SELECT id FROM proyecto_indicadores WHERE proyecto_id = proyecto_id_param));
END//
DELIMITER ;

SELECT 'Base de datos ODS12 configurada exitosamente' AS mensaje, NOW() AS fecha_creacion;
