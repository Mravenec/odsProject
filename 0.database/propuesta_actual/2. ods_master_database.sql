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

CREATE VIEW vista_resumen_proyectos_ods AS
SELECT 
    p.id AS proyecto_id,
    p.nombre_proyecto,
    u.full_name AS gestor,
    s.nombre AS sede,
    p.estado,
    p.fecha_inicio,
    p.fecha_fin
FROM proyectos p
JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN ods_login.sedes s ON p.sede_id = s.id;

SELECT 'Base de datos ods_master creada exitosamente' AS mensaje;
