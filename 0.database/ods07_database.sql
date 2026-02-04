-- Base de Datos ODS07: Energía Asequible y No Contaminante
-- Sistema completo con triggers automáticos y vistas para administrador

-- Crear base de datos ODS07 (sin sistema de login)
CREATE DATABASE IF NOT EXISTS ods07 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods07;

-- NOTA: El sistema de login está separado en login_system.sql
-- Esta base de datos se enfoca exclusivamente en la gestión de proyectos ODS07

-- Tabla de proyectos creados por usuarios
CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    nombre_proyecto VARCHAR(200) NOT NULL,
    objetivo_id INT NOT NULL DEFAULT 7, -- Solo ODS07
    descripcion TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    meta_general VARCHAR(500),
    estado ENUM('planificacion', 'activo', 'completado', 'cancelado') DEFAULT 'planificacion',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES ods_login.usuarios(id) ON DELETE CASCADE,
    INDEX idx_usuario (usuario_id),
    INDEX idx_objetivo (objetivo_id),
    INDEX idx_estado (estado)
);

-- Tabla de metas específicas del proyecto
CREATE TABLE metas_proyecto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id INT NOT NULL,
    meta_codigo VARCHAR(10) NOT NULL, -- 7.1, 7.2, etc.
    meta_descripcion TEXT NOT NULL,
    valor_meta DECIMAL(15,4) NOT NULL,
    unidad_medida VARCHAR(50) NOT NULL,
    fecha_limite DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    INDEX idx_proyecto_meta (proyecto_id, meta_codigo)
);

-- Tabla de indicadores con datos reales
CREATE TABLE indicadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id INT NOT NULL,
    indicador_codigo VARCHAR(10) NOT NULL, -- 7.1.1, 7.1.2, etc.
    indicador_descripcion TEXT NOT NULL,
    valor_actual DECIMAL(15,4) DEFAULT 0,
    valor_meta DECIMAL(15,4) NOT NULL,
    unidad_medida VARCHAR(50) NOT NULL,
    fecha_medicion DATE NOT NULL,
    fuente_datos VARCHAR(100),
    observaciones TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    INDEX idx_proyecto_indicador (proyecto_id, indicador_codigo),
    INDEX idx_codigo (indicador_codigo)
);

-- Tabla de mediciones históricas
CREATE TABLE mediciones_historicas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    indicador_id INT NOT NULL,
    valor_medido DECIMAL(15,4) NOT NULL,
    fecha_medicion DATE NOT NULL,
    responsable VARCHAR(100),
    metodo_medicion VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (indicador_id) REFERENCES indicadores(id) ON DELETE CASCADE,
    INDEX idx_indicador_fecha (indicador_id, fecha_medicion)
);

-- Tabla de auditoría de cambios
CREATE TABLE auditoria (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tabla_afectada VARCHAR(50) NOT NULL,
    registro_id INT NOT NULL,
    accion VARCHAR(20) NOT NULL, -- INSERT, UPDATE, DELETE
    usuario_id INT,
    valores_anteriores JSON,
    valores_nuevos JSON,
    fecha_cambio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ip_address VARCHAR(45),
    FOREIGN KEY (usuario_id) REFERENCES ods_login.usuarios(id) ON DELETE SET NULL,
    INDEX idx_tabla_registro (tabla_afectada, registro_id),
    INDEX idx_fecha (fecha_cambio)
);

-- NOTA: Los usuarios se gestionan en la base de datos ods_login (login_system.sql)
-- Esta tabla solo mantiene referencia al usuario_id para integridad referencial

-- Trigger para auditoría automática
DELIMITER //
CREATE TRIGGER auditoria_indicadores_insert
AFTER INSERT ON indicadores
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (tabla_afectada, registro_id, accion, usuario_id, valores_nuevos)
    VALUES ('indicadores', NEW.id, 'INSERT', NULL, 
            JSON_OBJECT(
                'proyecto_id', NEW.proyecto_id,
                'indicador_codigo', NEW.indicador_codigo,
                'valor_actual', NEW.valor_actual,
                'valor_meta', NEW.valor_meta,
                'unidad_medida', NEW.unidad_medida
            ));
END//
DELIMITER ;

DELIMITER //
CREATE TRIGGER auditoria_indicadores_update
AFTER UPDATE ON indicadores
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (tabla_afectada, registro_id, accion, usuario_id, valores_anteriores, valores_nuevos)
    VALUES ('indicadores', NEW.id, 'UPDATE', NULL,
            JSON_OBJECT(
                'valor_actual', OLD.valor_actual,
                'valor_meta', OLD.valor_meta
            ),
            JSON_OBJECT(
                'valor_actual', NEW.valor_actual,
                'valor_meta', NEW.valor_meta
            ));
END//
DELIMITER ;

-- Trigger para calcular progreso automáticamente
DELIMITER //
CREATE TRIGGER calcular_proyecto_progreso
AFTER UPDATE ON indicadores
FOR EACH ROW
BEGIN
    DECLARE total_indicadores INT DEFAULT 0;
    DECLARE indicadores_completados INT DEFAULT 0;
    DECLARE progreso DECIMAL(5,2) DEFAULT 0;
    
    -- Contar indicadores del proyecto
    SELECT COUNT(*) INTO total_indicadores 
    FROM indicadores 
    WHERE proyecto_id = NEW.proyecto_id;
    
    -- Contar indicadores que alcanzaron o superaron la meta
    SELECT COUNT(*) INTO indicadores_completados 
    FROM indicadores 
    WHERE proyecto_id = NEW.proyecto_id 
    AND valor_actual >= valor_meta;
    
    -- Calcular progreso
    IF total_indicadores > 0 THEN
        SET progreso = (indicadores_completados * 100.0) / total_indicadores;
    END IF;
    
    -- Actualizar estado del proyecto basado en progreso
    UPDATE proyectos 
    SET estado = CASE 
        WHEN progreso >= 100 THEN 'completado'
        WHEN progreso > 0 THEN 'activo'
        ELSE 'planificacion'
    END
    WHERE id = NEW.proyecto_id;
END//
DELIMITER ;

-- Trigger para registrar mediciones históricas
DELIMITER //
CREATE TRIGGER registrar_medicion_historica
AFTER UPDATE ON indicadores
FOR EACH ROW
BEGIN
    -- Solo registrar si el valor cambió significativamente
    IF OLD.valor_actual != NEW.valor_actual THEN
        INSERT INTO mediciones_historicas (indicador_id, valor_medido, fecha_medicion, responsable)
        VALUES (NEW.id, NEW.valor_actual, NEW.fecha_medicion, 'Sistema Automático');
    END IF;
END//
DELIMITER ;

-- VISTA PARA ADMINISTRADOR: Resumen General de Proyectos
CREATE VIEW vista_admin_resumen_general AS
SELECT 
    p.id as proyecto_id,
    p.nombre_proyecto,
    u.username as usuario_creador,
    u.full_name as nombre_usuario,
    p.fecha_inicio,
    p.fecha_fin,
    p.estado,
    COUNT(DISTINCT i.id) as total_indicadores,
    COUNT(DISTINCT CASE WHEN i.valor_actual >= i.valor_meta THEN i.id END) as indicadores_logrados,
    ROUND(
        CASE 
            WHEN COUNT(DISTINCT i.id) > 0 
            THEN (COUNT(DISTINCT CASE WHEN i.valor_actual >= i.valor_meta THEN i.id END) * 100.0) / COUNT(DISTINCT i.id)
            ELSE 0 
        END, 2
    ) as progreso_porcentaje,
    MIN(i.valor_actual) as valor_minimo_actual,
    MAX(i.valor_actual) as valor_maximo_actual,
    AVG(i.valor_actual) as valor_promedio_actual,
    p.created_at as fecha_creacion
FROM proyectos p
LEFT JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7
GROUP BY p.id, p.nombre_proyecto, u.username, u.full_name, p.fecha_inicio, p.fecha_fin, p.estado, p.created_at
ORDER BY p.created_at DESC;

-- VISTA PARA ADMINISTRADOR: Detalle de Indicadores por Proyecto
CREATE VIEW vista_admin_detalle_indicadores AS
SELECT 
    p.id as proyecto_id,
    p.nombre_proyecto,
    u.username as usuario_creador,
    i.indicador_codigo,
    i.indicador_descripcion,
    i.valor_actual,
    i.valor_meta,
    i.unidad_medida,
    CASE 
        WHEN i.valor_actual >= i.valor_meta THEN 'LOGRADO'
        WHEN i.valor_actual >= (i.valor_meta * 0.8) THEN 'CERCA META'
        WHEN i.valor_actual >= (i.valor_meta * 0.5) THEN 'PROGRESO'
        ELSE 'BAJO'
    END as estado_indicador,
    ROUND((i.valor_actual / i.valor_meta) * 100, 2) as porcentaje_logro,
    i.fecha_medicion,
    i.fuente_datos,
    i.updated_at as ultima_actualizacion
FROM proyectos p
LEFT JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7
ORDER BY p.id, i.indicador_codigo;

-- VISTA PARA ADMINISTRADOR: Estadísticas Generales ODS07
CREATE VIEW vista_admin_estadisticas_generales AS
SELECT 
    '7.1' as meta_codigo,
    'Garantizar acceso universal energía asequible fiable moderna' as meta_descripcion,
    COUNT(DISTINCT p.id) as total_proyectos,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.1.%' THEN p.id END) as proyectos_con_indicadores,
    AVG(CASE WHEN i.indicador_codigo LIKE '7.1.%' THEN (i.valor_actual / i.valor_meta) * 100 END) as promedio_logro_meta_7_1,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.1.%' AND i.valor_actual >= i.valor_meta THEN i.id END) as indicadores_7_1_logrados
FROM proyectos p
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7

UNION ALL

SELECT 
    '7.2' as meta_codigo,
    'Aumentar considerablemente proporción energía renovable' as meta_descripcion,
    COUNT(DISTINCT p.id) as total_proyectos,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.2.%' THEN p.id END) as proyectos_con_indicadores,
    AVG(CASE WHEN i.indicador_codigo LIKE '7.2.%' THEN (i.valor_actual / i.valor_meta) * 100 END) as promedio_logro_meta_7_2,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.2.%' AND i.valor_actual >= i.valor_meta THEN i.id END) as indicadores_7_2_logrados
FROM proyectos p
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7

UNION ALL

SELECT 
    '7.3' as meta_codigo,
    'Duplicar tasa mundial mejora eficiencia energética' as meta_descripcion,
    COUNT(DISTINCT p.id) as total_proyectos,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.3.%' THEN p.id END) as proyectos_con_indicadores,
    AVG(CASE WHEN i.indicador_codigo LIKE '7.3.%' THEN (i.valor_actual / i.valor_meta) * 100 END) as promedio_logro_meta_7_3,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.3.%' AND i.valor_actual >= i.valor_meta THEN i.id END) as indicadores_7_3_logrados
FROM proyectos p
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7

UNION ALL

SELECT 
    '7.a' as meta_codigo,
    'Aumentar cooperación internacional energía limpia' as meta_descripcion,
    COUNT(DISTINCT p.id) as total_proyectos,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.a.%' THEN p.id END) as proyectos_con_indicadores,
    AVG(CASE WHEN i.indicador_codigo LIKE '7.a.%' THEN (i.valor_actual / i.valor_meta) * 100 END) as promedio_logro_meta_7_a,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.a.%' AND i.valor_actual >= i.valor_meta THEN i.id END) as indicadores_7_a_logrados
FROM proyectos p
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7

UNION ALL

SELECT 
    '7.b' as meta_codigo,
    'Ampliar infraestructura tecnología energía sostenible' as meta_descripcion,
    COUNT(DISTINCT p.id) as total_proyectos,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.b.%' THEN p.id END) as proyectos_con_indicadores,
    AVG(CASE WHEN i.indicador_codigo LIKE '7.b.%' THEN (i.valor_actual / i.valor_meta) * 100 END) as promedio_logro_meta_7_b,
    COUNT(DISTINCT CASE WHEN i.indicador_codigo LIKE '7.b.%' AND i.valor_actual >= i.valor_meta THEN i.id END) as indicadores_7_b_logrados
FROM proyectos p
LEFT JOIN indicadores i ON p.id = i.proyecto_id
WHERE p.objetivo_id = 7;

-- VISTA PARA ADMINISTRADOR: Auditoría de Cambios Recientes
CREATE VIEW vista_admin_auditoria_reciente AS
SELECT 
    a.id,
    a.tabla_afectada,
    a.registro_id,
    a.accion,
    u.username as usuario,
    u.full_name as nombre_usuario,
    a.valores_anteriores,
    a.valores_nuevos,
    a.fecha_cambio,
    a.ip_address,
    CASE 
        WHEN a.tabla_afectada = 'indicadores' THEN 
            (SELECT CONCAT(p.nombre_proyecto, ' - ', i.indicador_codigo) 
             FROM indicadores i 
             JOIN proyectos p ON i.proyecto_id = p.id 
             WHERE i.id = a.registro_id)
        ELSE CONCAT('Registro ', a.registro_id)
    END as descripcion_cambio
FROM auditoria a
LEFT JOIN ods_login.usuarios u ON a.usuario_id = u.id
WHERE a.fecha_cambio >= DATE_SUB(NOW(), INTERVAL 30 DAY)
ORDER BY a.fecha_cambio DESC;

-- Procedimiento almacenado para obtener dashboard de administrador
DELIMITER //
CREATE PROCEDURE sp_admin_dashboard()
BEGIN
    -- Estadísticas generales
    SELECT 
        'TOTAL_PROYECTOS' as metrica,
        COUNT(*) as valor
    FROM proyectos 
    WHERE objetivo_id = 7
    
    UNION ALL
    
    SELECT 
        'PROYECTOS_ACTIVOS' as metrica,
        COUNT(*) as valor
    FROM proyectos 
    WHERE objetivo_id = 7 AND estado = 'activo'
    
    UNION ALL
    
    SELECT 
        'PROYECTOS_COMPLETADOS' as metrica,
        COUNT(*) as valor
    FROM proyectos 
    WHERE objetivo_id = 7 AND estado = 'completado'
    
    UNION ALL
    
    SELECT 
        'TOTAL_USUARIOS' as metrica,
        COUNT(*) as valor
    FROM ods_login.usuarios
    WHERE is_active = TRUE;
    
    -- Proyectos recientes
    SELECT 
        p.id,
        p.nombre_proyecto,
        u.username,
        p.estado,
        p.created_at
    FROM proyectos p
    JOIN ods_login.usuarios u ON p.usuario_id = u.id
    WHERE p.objetivo_id = 7
    ORDER BY p.created_at DESC
    LIMIT 5;
    
    -- Indicadores críticos (bajo rendimiento)
    SELECT 
        i.indicador_codigo,
        p.nombre_proyecto,
        ROUND((i.valor_actual / i.valor_meta) * 100, 2) as porcentaje_logro,
        i.valor_actual,
        i.valor_meta
    FROM indicadores i
    JOIN proyectos p ON i.proyecto_id = p.id
    WHERE p.objetivo_id = 7 
    AND (i.valor_actual / i.valor_meta) < 0.5
    ORDER BY (i.valor_actual / i.valor_meta) ASC
    LIMIT 10;
END//
DELIMITER ;

-- Procedimiento para obtener reporte detallado de un proyecto
DELIMITER //
CREATE PROCEDURE sp_admin_reporte_proyecto(IN proyecto_id_param INT)
BEGIN
    -- Información general del proyecto
    SELECT 
        p.id,
        p.nombre_proyecto,
        u.username as usuario_creador,
        u.full_name,
        p.descripcion,
        p.fecha_inicio,
        p.fecha_fin,
        p.estado,
        p.created_at
    FROM proyectos p
    JOIN ods_login.usuarios u ON p.usuario_id = u.id
    WHERE p.id = proyecto_id_param AND p.objetivo_id = 7;
    
    -- Indicadores del proyecto
    SELECT 
        i.indicador_codigo,
        i.indicador_descripcion,
        i.valor_actual,
        i.valor_meta,
        i.unidad_medida,
        CASE 
            WHEN i.valor_actual >= i.valor_meta THEN 'LOGRADO'
            WHEN i.valor_actual >= (i.valor_meta * 0.8) THEN 'CERCA META'
            WHEN i.valor_actual >= (i.valor_meta * 0.5) THEN 'PROGRESO'
            ELSE 'BAJO'
        END as estado_indicador,
        ROUND((i.valor_actual / i.valor_meta) * 100, 2) as porcentaje_logro,
        i.fecha_medicion,
        i.fuente_datos,
        i.observaciones,
        i.updated_at
    FROM indicadores i
    WHERE i.proyecto_id = proyecto_id_param
    ORDER BY i.indicador_codigo;
    
    -- Historial de mediciones
    SELECT 
        i.indicador_codigo,
        mh.valor_medido,
        mh.fecha_medicion,
        mh.responsable,
        mh.metodo_medicion,
        mh.created_at
    FROM mediciones_historicas mh
    JOIN indicadores i ON mh.indicador_id = i.id
    WHERE i.proyecto_id = proyecto_id_param
    ORDER BY mh.fecha_medicion DESC;
    
    -- Auditoría del proyecto
    SELECT 
        a.tabla_afectada,
        a.accion,
        u.username as usuario,
        a.valores_anteriores,
        a.valores_nuevos,
        a.fecha_cambio
    FROM auditoria a
    LEFT JOIN ods_login.usuarios u ON a.usuario_id = u.id
    WHERE a.tabla_afectada IN ('proyectos', 'indicadores', 'metas_proyecto')
    AND (a.registro_id = proyecto_id_param OR a.registro_id IN (
        SELECT id FROM indicadores WHERE proyecto_id = proyecto_id_param
        UNION
        SELECT id FROM metas_proyecto WHERE proyecto_id = proyecto_id_param
    ))
    ORDER BY a.fecha_cambio DESC;
END//
DELIMITER ;

-- Índices adicionales para optimización
CREATE INDEX idx_indicadores_proyecto_valor ON indicadores(proyecto_id, valor_actual);
CREATE INDEX idx_auditoria_fecha_tabla ON auditoria(fecha_cambio, tabla_afectada);
CREATE INDEX idx_mediciones_indicador_fecha ON mediciones_historicas(indicador_id, fecha_medicion DESC);

-- Comentarios de la base de datos
ALTER TABLE proyectos COMMENT 'Proyectos ODS07 creados por usuarios';
ALTER TABLE metas_proyecto COMMENT 'Metas específicas establecidas por cada proyecto ODS07';
ALTER TABLE indicadores COMMENT 'Indicadores medidos por cada proyecto ODS07';
ALTER TABLE mediciones_historicas COMMENT 'Historial de mediciones de indicadores ODS07';
ALTER TABLE auditoria COMMENT 'Auditoría de cambios en el sistema ODS07';

-- Vista final para verificar que todo está configurado correctamente
SELECT 'Base de datos ODS07 creada exitosamente' as mensaje, NOW() as fecha_creacion;
