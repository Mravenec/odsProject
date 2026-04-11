-- ============================================================
-- [NOTIFICACIÓN] ESTE ARCHIVO ES AHORA SOLO UNA REFERENCIA.
-- Las estructuras comunes han sido incrustadas directamente
-- en cada archivo odsXX_database.sql para compatibilidad Standalone
-- con HeidiSQL (evitando el uso de SOURCE).
-- ============================================================

-- ────────────────────────────────────────────────────────────
-- TABLAS
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- COMPROBACIÓN DE CONTEXTO (Informativo)
-- ────────────────────────────────────────────────────────────
SELECT CASE 
    WHEN DATABASE() = 'ods_login' THEN 'ADVERTENCIA: Estás intentando ejecutar tablas de proyecto en ods_login. Esto no es recomendado.'
    ELSE CONCAT('Ejecutando componentes comunes en: ', DATABASE())
END AS contexto_ejecucion;

CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    sede_id    INT NULL,                                 -- Sede a la que pertenece el proyecto
    nombre_proyecto VARCHAR(200) NOT NULL,
    objetivo_id TINYINT UNSIGNED NOT NULL,              -- Referencia al ODS (1-17) en ods_login.ods_catalog
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

-- Tabla que vincula un proyecto con indicadores específicos y sus fórmulas
CREATE TABLE proyecto_indicadores (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id         INT NOT NULL,
    indicador_master_id INT NOT NULL,                    -- Referencia al catálogo central
    formula_custom      TEXT,                             -- Si es NULL, usa formula_default de indicador_master
    valor_actual        DECIMAL(15,4) DEFAULT 0,          -- Último resultado calculado
    meta_valor          DECIMAL(15,4) NOT NULL,
    meta_unidad         VARCHAR(50) NOT NULL,
    fecha_proxima_medicion DATE,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (indicador_master_id) REFERENCES ods_login.indicador_master(id),
    INDEX idx_proyecto_master (proyecto_id, indicador_master_id)
);

-- Parámetros específicos definidos para el indicador en este proyecto
CREATE TABLE proyecto_indicador_parametros (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_indicador_id INT NOT NULL,
    nombre_parametro    VARCHAR(50) NOT NULL,            -- ej: 'estudiantes_becados'
    tipo_dato           ENUM('Integer', 'Decimal') NOT NULL DEFAULT 'Decimal',
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_indicador_id) REFERENCES proyecto_indicadores(id) ON DELETE CASCADE,
    UNIQUE KEY uk_proyecto_param (proyecto_indicador_id, nombre_parametro)
);

-- Registro de mediciones periódicas
CREATE TABLE mediciones_historicas (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_indicador_id INT NOT NULL,
    valor_calculado     DECIMAL(15,4) NOT NULL,           -- Resultado de aplicar la fórmula
    fecha_medicion      DATE NOT NULL,
    responsable         VARCHAR(100),
    metodo_medicion     VARCHAR(100),
    observaciones       TEXT,
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_indicador_id) REFERENCES proyecto_indicadores(id) ON DELETE CASCADE,
    INDEX idx_proyecto_indicador_fecha (proyecto_indicador_id, fecha_medicion)
);

-- Valores de los parámetros ingresados para una medición específica
CREATE TABLE medicion_parametro_valores (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    medicion_id         INT NOT NULL,
    parametro_id        INT NOT NULL,
    valor_ingresado     DECIMAL(15,4) NOT NULL,
    FOREIGN KEY (medicion_id) REFERENCES mediciones_historicas(id) ON DELETE CASCADE,
    FOREIGN KEY (parametro_id) REFERENCES proyecto_indicador_parametros(id) ON DELETE CASCADE
);

-- ────────────────────────────────────────────────────────────
-- VISTAS ACTUALIZADAS
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_resumen_general AS
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

CREATE VIEW vista_admin_detalle_indicadores AS
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
-- VISTA: Auditoría de Cambios Recientes
-- (referencia a auditoria_odsXX → creada en cada archivo ODS)
-- ────────────────────────────────────────────────────────────
-- Esta vista se crea en cada archivo ODS porque necesita el
-- nombre concreto de la tabla de auditoría (auditoria_ods01, etc.)

-- ────────────────────────────────────────────────────────────
-- PROCEDIMIENTO: sp_admin_reporte_proyecto
-- (también necesita el nombre de la tabla de auditoría, por lo
--  que se crea en cada archivo ODS)
-- ────────────────────────────────────────────────────────────
