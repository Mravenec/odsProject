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
    eje_planes_id           TINYINT UNSIGNED NULL,
    aliado_externo          VARCHAR(500) NULL COMMENT 'Aliado externo texto libre SODSI',
    location_province       VARCHAR(80)   NULL,
    location_canton         VARCHAR(80)   NULL,
    location_district       VARCHAR(80)   NULL,
    -- Sprint 15: máquina de estados extendida con 'en_revision' como pivote del flujo
    -- Gestor → Auditor. El nuevo valor se intercala entre 'activo' y 'completado'
    -- para representar la posición lógica en el ciclo de vida.
    estado ENUM('planificacion','activo','en_revision','completado','cancelado') DEFAULT 'planificacion',
    -- Sprint 15: stamping de auditoría — quién y cuándo cerró la auditoría.
    -- NULL mientras el proyecto no ha sido cerrado. Permite trazabilidad
    -- institucional (firma del auditor responsable).
    auditado_por        INT          NULL,
    auditado_en         TIMESTAMP    NULL,
    observaciones_cierre VARCHAR(1000) NULL,   -- firma al cerrar evaluación (→ completado)
                                               --   o motivo si en_revision→activo (rechazo)
                                               --   se limpia al reenviar a evaluación (activo→en_revision)
                                               --   NO usar en planificación→activo (nota va en solicitud)
    fecha_envio_revision TIMESTAMP    NULL,    -- momento en que el gestor envió a auditar
                                               --   se usa para calcular tiempo de respuesta
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id)          REFERENCES ods_login.usuarios(id) ON DELETE CASCADE,
    FOREIGN KEY (sede_id)             REFERENCES ods_login.sedes(id)    ON DELETE SET NULL,
    FOREIGN KEY (auditado_por)        REFERENCES ods_login.usuarios(id) ON DELETE SET NULL,
    FOREIGN KEY (eje_planes_id)       REFERENCES ods_login.sodsi_ejes_planes(id) ON DELETE SET NULL,
    INDEX idx_usuario     (usuario_id),
    INDEX idx_sede        (sede_id),
    INDEX idx_estado      (estado),
    INDEX idx_auditado_en (auditado_en),
    INDEX idx_auditor     (auditado_por),
    INDEX idx_eje_planes  (eje_planes_id)
) ENGINE=InnoDB;

-- ────────────────────────────────────────────────────────────
-- SODSI — Sectores beneficiarios (multi-select)
-- ────────────────────────────────────────────────────────────

CREATE TABLE proyecto_beneficiarios (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id INT NOT NULL,
    valor_id    SMALLINT UNSIGNED NOT NULL,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (valor_id)    REFERENCES ods_login.sodsi_beneficiario_valor(id),
    UNIQUE KEY uk_proyecto_beneficiario (proyecto_id, valor_id),
    INDEX idx_ben_proyecto (proyecto_id)
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
    u.email AS gestor_email,
    u.telefono_contacto AS gestor_telefono,
    us.nombre AS sede_usuario,
    sa.codigo AS area_codigo,
    sa.nombre AS area_nombre,
    sd.codigo AS dependencia_codigo,
    sd.nombre AS dependencia_nombre,
    srd.codigo AS rol_dependencia_codigo,
    srd.nombre AS rol_dependencia_nombre,
    s.nombre AS sede,
    p.estado,
    p.fecha_inicio,
    p.fecha_fin,
    p.auditado_por,
    auditor.full_name AS auditor_nombre,
    p.auditado_en,
    p.observaciones_cierre,
    p.fecha_envio_revision,
    p.aliado_externo,
    ep.nombre AS eje_planes,
    ep.codigo AS eje_planes_codigo,
    p.location_province,
    p.location_canton,
    p.location_district,
    prov.region_mideplan_id,
    rm.nombre AS region_mideplan,
    rm.codigo AS region_mideplan_codigo,
    GROUP_CONCAT(DISTINCT po.ods_id ORDER BY po.ods_id) AS ods_vinculados,
    MAX(CASE WHEN po.es_primario = TRUE THEN po.ods_id END) AS ods_primario
FROM proyectos p
JOIN ods_login.usuarios u ON p.usuario_id = u.id
LEFT JOIN ods_login.sedes s ON p.sede_id = s.id
LEFT JOIN ods_login.sedes us ON u.sede_id = us.id
LEFT JOIN ods_login.sodsi_area sa ON u.area_id = sa.id
LEFT JOIN ods_login.sodsi_dependencia sd ON u.dependencia_id = sd.id
LEFT JOIN ods_login.sodsi_rol_dependencia srd ON u.rol_dependencia_id = srd.id
LEFT JOIN ods_login.usuarios auditor ON p.auditado_por = auditor.id
LEFT JOIN ods_login.sodsi_ejes_planes ep ON p.eje_planes_id = ep.id
LEFT JOIN ods_login.sodsi_provincias prov ON prov.nombre = p.location_province
LEFT JOIN ods_login.sodsi_regiones_mideplan rm ON prov.region_mideplan_id = rm.id
LEFT JOIN proyecto_ods po ON po.proyecto_id = p.id
GROUP BY p.id, p.nombre_proyecto, u.full_name, u.email, u.telefono_contacto,
         us.nombre, sa.codigo, sa.nombre, sd.codigo, sd.nombre,
         srd.codigo, srd.nombre, s.nombre, p.estado,
         p.fecha_inicio, p.fecha_fin, p.auditado_por, auditor.full_name,
         p.auditado_en, p.observaciones_cierre, p.fecha_envio_revision,
         p.aliado_externo, ep.nombre, ep.codigo,
         p.location_province, p.location_canton, p.location_district,
         prov.region_mideplan_id, rm.nombre, rm.codigo;

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

-- ────────────────────────────────────────────────────────────
-- TABLA: proyecto_chat_mensajes (Sprint Chat Planificación)
-- Hilo gestor ⇄ admin/evaluador mientras estado = planificacion
-- ────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS proyecto_chat_mensajes (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id   INT NOT NULL,
    autor_id      INT NOT NULL,
    cuerpo        TEXT NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    edited_at     TIMESTAMP NULL,
    edit_count    INT DEFAULT 0,
    eliminado     BOOLEAN DEFAULT FALSE,
    eliminado_at  TIMESTAMP NULL,
    FOREIGN KEY (proyecto_id) REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (autor_id)    REFERENCES ods_login.usuarios(id),
    INDEX idx_chat_proyecto (proyecto_id, created_at)
) ENGINE=InnoDB;

-- ────────────────────────────────────────────────────────────
-- TABLA: proyecto_transicion_solicitud (Sprint Chat Planificación)
-- Gestor solicita salida; admin/evaluador aprueba o rechaza
-- ────────────────────────────────────────────────────────────
CREATE TABLE IF NOT EXISTS proyecto_transicion_solicitud (
    id               INT AUTO_INCREMENT PRIMARY KEY,
    proyecto_id      INT NOT NULL,
    solicitado_por   INT NOT NULL,
    estado_destino   ENUM('activo','cancelado') NOT NULL,
    motivo           VARCHAR(1000) NULL,
    estado_solicitud ENUM('pendiente','aprobada','rechazada') DEFAULT 'pendiente',
    resuelto_por     INT NULL,
    resuelto_en      TIMESTAMP NULL,
    nota_resolucion  VARCHAR(1000) NULL,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (proyecto_id)    REFERENCES proyectos(id) ON DELETE CASCADE,
    FOREIGN KEY (solicitado_por) REFERENCES ods_login.usuarios(id),
    FOREIGN KEY (resuelto_por)   REFERENCES ods_login.usuarios(id),
    INDEX idx_sol_proyecto (proyecto_id),
    INDEX idx_sol_estado (estado_solicitud)
) ENGINE=InnoDB;

SELECT 'Base de datos ods_master creada exitosamente' AS mensaje;
