-- Base de Datos ODS16: Paz, Justicia e Instituciones Sólidas
-- Sistema completo con triggers automáticos y vistas para administrador
-- La lógica común (tablas compartidas, vistas genéricas) está en ods_common.sql

CREATE DATABASE IF NOT EXISTS ods16 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods16;

SET @ODS_NUM = 16;

-- ────────────────────────────────────────────────────────────
-- CONFIGURACIÓN DE METADATOS CENTRALIZADOS
-- ────────────────────────────────────────────────────────────

INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion)
VALUES (@ODS_NUM, 'Paz, Justicia e Instituciones Sólidas', '#00689D', 'Promover sociedades pacíficas e inclusivas para el desarrollo sostenible, facilitar el acceso a la justicia para todos y construir a todos los niveles instituciones eficaces e inclusivas que rindan cuentas');

INSERT IGNORE INTO ods_login.indicador_master (ods_id, codigo, nombre, formula_default, unidad_medida_default)
VALUES 
(@ODS_NUM, '16.1.1', 'Número de víctimas de homicidio doloso por cada 100.000 habitantes, desglosado por sexo y edad', 'valor', 'Personas/100k'),
(@ODS_NUM, '16.1.2', 'Muertes relacionadas con conflictos por cada 100.000 habitantes, desglosadas por sexo, edad y causa', 'valor', 'Personas/100k'),
(@ODS_NUM, '16.1.3', 'Proporción de la población que ha sufrido a) violencia física, b) violencia psicológica y c) violencia sexual en los últimos 12 meses', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.1.4', 'Proporción de la población que se siente segura caminando sola por la zona donde vive', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.2.1', 'Proporción de niños de entre 1 y 17 años que han sufrido castigos físicos o agresiones psicológicas por parte de sus cuidadores en el último mes', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.2.2', 'Número de víctimas de la trata de personas por cada 100.000 habitantes, desglosado por sexo, edad y forma de explotación', 'valor', 'Personas/100k'),
(@ODS_NUM, '16.2.3', 'Proporción de mujeres y hombres jóvenes de entre 18 y 29 años que han sufrido violencia sexual antes de cumplir los 18 años', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.3.1', 'Proporción de víctimas de violencia en los últimos 12 meses que han denunciado su victimización a las autoridades competentes u otros mecanismos de resolución de conflictos oficialmente reconocidos', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.3.2', 'Proporción de detenidos que no han sido condenados por un tribunal respecto del total de la población reclusa', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.3.3', 'Proporción de la población que ha experimentado un conflicto en los últimos dos años y que ha accedido a un mecanismo formal o informal de resolución de conflictos, por tipo de mecanismo', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.4.1', 'Valor total de las corrientes financieras ilícitas entrantes y salientes (en dólares de los Estados Unidos constantes)', 'valor', 'Monto'),
(@ODS_NUM, '16.4.2', 'Proporción de armas incautadas, encontradas o entregadas cuyo origen o contexto ilícitos han sido rastreados o establecidos por una autoridad competente de conformidad con los instrumentos internacionales', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.5.1', 'Proporción de personas que han tenido al menos un contacto con un funcionario público y que han pagado un soborno a un funcionario público, o a quienes estos les han solicitado un soborno, durante los últimos 12 meses', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.5.2', 'Proporción de empresas que han tenido al menos un contacto con un funcionario público y que han pagado un soborno a un funcionario público, o a quienes estos les han solicitado un soborno, durante los últimos 12 meses', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.6.1', 'Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente, desglosados por sectores (o por códigos presupuestarios)', 'valor', 'Porcentaje'),
(@ODS_NUM, '16.6.2', 'Proporción de la población satisfecha con su última experiencia de servicios públicos', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.7.1', 'Proporciones de cargos (por sexo, edad, personas con discapacidad y grupos de población) en las instituciones públicas (parlamentos nacionales y locales, administración pública y poder judicial) en comparación con la distribución nacional', 'valor', 'Indice'),
(@ODS_NUM, '16.7.2', 'Proporción de la población que considera que la toma de decisiones es inclusiva y receptiva, desglosada por sexo, edad, situación de discapacidad y grupo de población', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.8.1', 'Proporción de miembros y derechos de voto de los países en desarrollo en organizaciones internacionales', 'valor', 'Porcentaje'),
(@ODS_NUM, '16.9.1', 'Proporción de niños menores de 5 años cuyo nacimiento se ha registrado ante una autoridad civil, desglosada por edad', '(p1 / p2) * 100', 'Porcentaje'),
(@ODS_NUM, '16.10.1', 'Número de casos verificados de asesinato, secuestro, desaparición forzada, detención arbitraria y tortura de periodistas, personal de medios de comunicación asociados, sindicalistas y defensores de los derechos humanos en los últimos 12 meses', 'count', 'Casos'),
(@ODS_NUM, '16.10.2', 'Número de países que adoptan y aplican garantías constitucionales, legales o de política para el acceso público a la información', 'count', 'Paises'),
(@ODS_NUM, '16.a.1', 'Existencia de instituciones nacionales de derechos humanos independientes de plena conformidad con los Principios de París', 'valor', 'Binario (1/0)'),
(@ODS_NUM, '16.b.1', 'Proporción de la población que informa haberse sentido personalmente discriminada o acosada en los últimos 12 meses por motivos de discriminación prohibidos por el derecho internacional de los derechos humanos', '(p1 / p2) * 100', 'Porcentaje');

-- ────────────────────────────────────────────────────────────
-- TABLA DE AUDITORÍA (nombre único por ODS)
-- ────────────────────────────────────────────────────────────

CREATE TABLE auditoria_ods16 (
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
LEFT JOIN ods_login.ods_catalog cat ON 16 = cat.id
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
-- TRIGGERS ESPECÍFICOS
-- ────────────────────────────────────────────────────────────

-- [ ELIMINADA: La auditoría de inserción de proyectos ahora ocurre en ods_master ]

DELIMITER //

CREATE TRIGGER auditoria_indicadores_insert
AFTER INSERT ON proyecto_indicadores
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_ods16 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
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
    
    INSERT INTO auditoria_ods16 (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
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
FROM auditoria_ods16 a
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
    SELECT * FROM auditoria_ods16 
    WHERE (tabla_afectada = 'proyecto_indicadores' AND registro_id IN (SELECT id FROM proyecto_indicadores WHERE proyecto_id = proyecto_id_param));
END//
DELIMITER ;

SELECT 'Base de datos ODS16 configurada exitosamente' AS mensaje, NOW() AS fecha_creacion;
