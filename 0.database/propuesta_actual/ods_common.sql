-- ============================================================
-- ODS COMMON: Tablas, Triggers, Vistas y Procedimientos comunes
-- Uso: hacer SOURCE de este archivo DENTRO de cada base ods_XX,
--      DESPUÉS de haber ejecutado el archivo ods_XX_database.sql.
--
-- Requiere que la variable @ODS_NUM esté definida antes de llamar,
-- por ejemplo:  SET @ODS_NUM = 1;
-- El archivo ods_XX_database.sql lo define automáticamente.
-- ============================================================

-- ────────────────────────────────────────────────────────────
-- TABLAS
-- ────────────────────────────────────────────────────────────

CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    nombre_proyecto VARCHAR(200) NOT NULL,
    objetivo_id INT NOT NULL,
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

CREATE TABLE metas_proyecto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id INT NOT NULL,
    meta_codigo VARCHAR(10) NOT NULL,
    meta_descripcion TEXT NOT NULL,
    valor_meta DECIMAL(15,4) NOT NULL,
    unidad_medida VARCHAR(50) NOT NULL,
    fecha_limite DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    INDEX idx_proyecto_meta (proyecto_id, meta_codigo)
);

CREATE TABLE indicadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id INT NOT NULL,
    indicador_codigo VARCHAR(10) NOT NULL,
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

-- La tabla de auditoría se llama auditoria_odsXX; se crea en cada archivo individual.

-- ────────────────────────────────────────────────────────────
-- TRIGGERS  (usan @ODS_NUM para nombrar la tabla de auditoría)
-- MySQL no permite nombres de tabla dinámicos en triggers, así
-- que los triggers se crean en cada archivo ODS con el nombre
-- correcto. Ver sección TRIGGERS en ods_XX_database.sql.
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- ÍNDICES ADICIONALES
-- ────────────────────────────────────────────────────────────

CREATE INDEX idx_indicadores_proyecto_valor ON indicadores(proyecto_id, valor_actual);
CREATE INDEX idx_mediciones_indicador_fecha  ON mediciones_historicas(indicador_id, fecha_medicion DESC);

-- ────────────────────────────────────────────────────────────
-- VISTA: Resumen General de Proyectos
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_resumen_general AS
SELECT
    p.id                 AS proyecto_id,
    p.nombre_proyecto,
    u.username           AS usuario_creador,
    u.full_name          AS nombre_usuario,
    p.fecha_inicio,
    p.fecha_fin,
    p.estado,
    COUNT(DISTINCT i.id) AS total_indicadores,
    COUNT(DISTINCT CASE WHEN i.valor_actual >= i.valor_meta THEN i.id END) AS indicadores_logrados,
    ROUND(
        CASE
            WHEN COUNT(DISTINCT i.id) > 0
            THEN (COUNT(DISTINCT CASE WHEN i.valor_actual >= i.valor_meta THEN i.id END) * 100.0)
                 / COUNT(DISTINCT i.id)
            ELSE 0
        END, 2
    ) AS progreso_porcentaje,
    MIN(i.valor_actual)  AS valor_minimo_actual,
    MAX(i.valor_actual)  AS valor_maximo_actual,
    AVG(i.valor_actual)  AS valor_promedio_actual,
    p.created_at         AS fecha_creacion
FROM proyectos p
LEFT JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN indicadores i        ON p.id = i.proyecto_id
GROUP BY p.id, p.nombre_proyecto, u.username, u.full_name,
         p.fecha_inicio, p.fecha_fin, p.estado, p.created_at
ORDER BY p.created_at DESC;

-- ────────────────────────────────────────────────────────────
-- VISTA: Detalle de Indicadores por Proyecto
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_detalle_indicadores AS
SELECT
    p.id AS proyecto_id,
    p.nombre_proyecto,
    u.username AS usuario_creador,
    i.indicador_codigo,
    i.indicador_descripcion,
    i.valor_actual,
    i.valor_meta,
    i.unidad_medida,
    CASE
        WHEN i.valor_actual >= i.valor_meta             THEN 'LOGRADO'
        WHEN i.valor_actual >= (i.valor_meta * 0.8)     THEN 'CERCA META'
        WHEN i.valor_actual >= (i.valor_meta * 0.5)     THEN 'PROGRESO'
        ELSE 'BAJO'
    END AS estado_indicador,
    ROUND((i.valor_actual / i.valor_meta) * 100, 2) AS porcentaje_logro,
    i.fecha_medicion,
    i.fuente_datos,
    i.updated_at AS ultima_actualizacion
FROM proyectos p
LEFT JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN indicadores i        ON p.id = i.proyecto_id
ORDER BY p.id, i.indicador_codigo;

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
