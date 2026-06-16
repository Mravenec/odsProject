-- ============================================================
-- Catálogos SODSI — Perfil usuario (área, dependencia, rol)
-- Sprint SODSI matriz · ODS-174
-- Base: ods_login
-- ============================================================

USE ods_login;

-- ────────────────────────────────────────────────────────────
-- Área organizacional (Fuente de información en Excel SODSI)
-- Administrable por admin del sistema
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_area (
    id          SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    codigo      VARCHAR(20)  NULL COMMENT 'Código institucional opcional',
    nombre      VARCHAR(150) NOT NULL,
    activo      BOOLEAN NOT NULL DEFAULT TRUE,
    orden       SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_sodsi_area_nombre (nombre)
) ENGINE=InnoDB;

INSERT INTO sodsi_area (id, codigo, nombre, orden) VALUES
  (1, 'GEST_ADM',  'Gestión Administrativa',           1),
  (2, 'DOCENCIA',  'Docencia',                         2),
  (3, 'VIDA_EST',  'Vida Estudiantil',                 3),
  (4, 'INVEST',    'Investigación',                    4),
  (5, 'EXTENSION', 'Extensión y Acción Social',        5),
  (6, '5038',      'Área Gestión Administrativa (Sedes)', 6);

-- ────────────────────────────────────────────────────────────
-- Dependencia organizacional (columna Dependencia en Excel SODSI)
-- Distinta de área / fuente de información
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_dependencia (
    id          SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    codigo      VARCHAR(20)  NULL COMMENT 'Código SODSI/OPSI opcional',
    nombre      VARCHAR(150) NOT NULL,
    activo      BOOLEAN NOT NULL DEFAULT TRUE,
    orden       SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_sodsi_dependencia_nombre (nombre)
) ENGINE=InnoDB;

INSERT INTO sodsi_dependencia (id, codigo, nombre, orden) VALUES
  (1, '5032', 'Gestión Ambiental y Salud Ocupacional', 1),
  (2, '5033', 'Dirección Académica',                 2),
  (3, '5034', 'Escuela de Ingeniería en Sistemas',   3),
  (4, '5035', 'Unidad de Extensión',                 4),
  (5, '5036', 'Unidad de Investigación',             5),
  (6, '5037', 'Coordinación Vida Estudiantil',     6);

-- ────────────────────────────────────────────────────────────
-- Rol de dependencia (columna Rol de dependencia en Excel SODSI)
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_rol_dependencia (
    id          TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    codigo      VARCHAR(10)  NULL COMMENT 'Código SODSI/OPSI opcional',
    nombre      VARCHAR(80) NOT NULL,
    activo      BOOLEAN NOT NULL DEFAULT TRUE,
    orden       TINYINT UNSIGNED NOT NULL DEFAULT 0,
    UNIQUE KEY uk_sodsi_rol_dependencia_nombre (nombre)
) ENGINE=InnoDB;

INSERT INTO sodsi_rol_dependencia (id, codigo, nombre, orden) VALUES
  (1, '1', 'Coordinador',    1),
  (2, '2', 'Profesor',       2),
  (3, '3', 'Investigador',   3),
  (4, '4', 'Extensionista',  4);

-- ────────────────────────────────────────────────────────────
-- Provincias CR → Región Mideplan (derivación automática en export)
-- Mapeo a nivel provincia (cantones frontera usan provincia del proyecto)
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_provincias (
    id                  TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre              VARCHAR(80) NOT NULL UNIQUE,
    region_mideplan_id  TINYINT UNSIGNED NOT NULL,
    codigo              VARCHAR(10) NULL COMMENT 'Código territorial opcional',
    orden               TINYINT UNSIGNED NOT NULL DEFAULT 0,
    FOREIGN KEY (region_mideplan_id) REFERENCES sodsi_regiones_mideplan(id),
    INDEX idx_sodsi_provincia_region (region_mideplan_id)
) ENGINE=InnoDB;

INSERT INTO sodsi_provincias (id, nombre, region_mideplan_id, codigo, orden) VALUES
  (1, 'San José',    1, '1', 1),
  (2, 'Alajuela',    1, '2', 2),
  (3, 'Cartago',     1, '3', 3),
  (4, 'Heredia',     1, '4', 4),
  (5, 'Guanacaste',  3, '5', 5),
  (6, 'Puntarenas',  6, '6', 6),
  (7, 'Limón',       4, '7', 7);

-- ────────────────────────────────────────────────────────────
-- FK usuarios → catálogos SODSI (columnas en login_system.sql)
-- ────────────────────────────────────────────────────────────

ALTER TABLE usuarios
    ADD CONSTRAINT fk_usuarios_sodsi_area
        FOREIGN KEY (area_id) REFERENCES sodsi_area(id) ON DELETE SET NULL,
    ADD CONSTRAINT fk_usuarios_sodsi_dependencia
        FOREIGN KEY (dependencia_id) REFERENCES sodsi_dependencia(id) ON DELETE SET NULL,
    ADD CONSTRAINT fk_usuarios_sodsi_rol_dependencia
        FOREIGN KEY (rol_dependencia_id) REFERENCES sodsi_rol_dependencia(id) ON DELETE SET NULL;
