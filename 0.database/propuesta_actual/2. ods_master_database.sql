-- ============================================================
-- BASE DE DATOS: ods_master
-- Sistema central de GESTIÓN DE PROYECTOS Multi-ODS
-- Referenciada por todas las bases ods_01 … ods_17
-- ============================================================

CREATE DATABASE IF NOT EXISTS ods_master CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods_master;

-- ────────────────────────────────────────────────────────────
-- TABLA: proyectos (Maestra)
-- ────────────────────────────────────────────────────────────

CREATE TABLE proyectos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    sede_id    INT NULL,                                 -- Sede a la que pertenece el proyecto
    nombre_proyecto VARCHAR(200) NOT NULL,
    descripcion TEXT,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    meta_general VARCHAR(500),
    responsable_nombre      VARCHAR(150)  NULL,
    location_province       VARCHAR(80)   NULL,
    location_canton         VARCHAR(80)   NULL,
    location_district       VARCHAR(80)   NULL,
    estado ENUM('planificacion', 'activo', 'completado', 'cancelado') DEFAULT 'planificacion',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES ods_login.usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (sede_id)    REFERENCES ods_login.sedes(id) ON DELETE SET NULL,
    INDEX idx_usuario (usuario_id),
    INDEX idx_sede    (sede_id),
    INDEX idx_estado (estado)
) ENGINE=InnoDB;

-- ────────────────────────────────────────────────────────────
-- VISTA: vista_resumen_proyectos_ods
-- Proporciona una visión unificada de los proyectos con sus ODS vinculados
-- ────────────────────────────────────────────────────────────

-- ────────────────────────────────────────────────────────────
-- TABLA: proyecto_ods (Sprint 2 — relación explícita Proyecto ↔ ODS)
-- ────────────────────────────────────────────────────────────

CREATE TABLE IF NOT EXISTS proyecto_ods (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id         INT NOT NULL,
    ods_id              TINYINT UNSIGNED NOT NULL,         -- 1 a 17 (FK lógica a ods_login.ods_catalog.id)
    es_primario         BOOLEAN NOT NULL DEFAULT FALSE,
    fecha_vinculacion   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (ods_id)      REFERENCES ods_login.ods_catalog(id),
    UNIQUE KEY uk_proyecto_ods (proyecto_id, ods_id),
    INDEX idx_ods (ods_id)
) ENGINE=InnoDB;

-- Sprint 7 — La unicidad del ODS primario por proyecto se enforce en código
-- Java (MasterProjectRepository.linkOds). MariaDB no permite que un trigger
-- modifique la misma tabla que lo activa (error 1442), así que la regla vive
-- en la capa de servicio. Aquí solo dejamos el DROP IF EXISTS por idempotencia.
DROP TRIGGER IF EXISTS trg_proyecto_ods_unico_primario;

-- ────────────────────────────────────────────────────────────
-- VISTA: vista_resumen_proyectos_ods
-- Proporciona una visión unificada de los proyectos con sus ODS vinculados
-- ────────────────────────────────────────────────────────────

CREATE OR REPLACE VIEW vista_resumen_proyectos_ods AS
SELECT 
    p.id AS proyecto_id,
    p.nombre_proyecto,
    u.full_name AS gestor,
    s.nombre AS sede,
    p.estado,
    p.fecha_inicio,
    p.fecha_fin,
    GROUP_CONCAT(DISTINCT po.ods_id ORDER BY po.ods_id) AS ods_vinculados,
    MAX(CASE WHEN po.es_primario = TRUE THEN po.ods_id END) AS ods_primario
FROM proyectos p
JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN ods_login.sedes s ON p.sede_id = s.id
LEFT JOIN proyecto_ods po ON po.proyecto_id = p.id
GROUP BY p.id, p.nombre_proyecto, u.full_name, s.nombre, p.estado, p.fecha_inicio, p.fecha_fin;

-- ────────────────────────────────────────────────────────────
-- TABLA: proyecto_documentos (Sprint 11 — evidencia de cierre)
-- El gestor sube documento(s) al cerrar; auditor los descarga para auditar.
-- ────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS proyecto_documentos (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id     INT NOT NULL,
    nombre_archivo  VARCHAR(255) NOT NULL,
    tipo_mime       VARCHAR(120) NOT NULL,
    tamanio_bytes   INT NOT NULL,
    contenido       LONGBLOB NOT NULL,
    subido_por      INT NOT NULL,
    subido_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descripcion     VARCHAR(500),
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (subido_por)  REFERENCES ods_login.usuarios(id),
    INDEX idx_proyecto (proyecto_id),
    INDEX idx_subido_at (subido_at)
) ENGINE=InnoDB;

SELECT 'Base de datos ods_master creada exitosamente' AS mensaje;
