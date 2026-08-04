-- ============================================================
-- Catálogos SODSI — Glosario Conare / UTN
-- Sprint SODSI · ODS-164
-- Base: ods_login (referenciada por ods_master.proyectos en DB-2)
-- ============================================================

USE ods_login;

-- ────────────────────────────────────────────────────────────
-- Unidades programáticas UTN (dependencia coordinadora / participantes)
-- Seed mínimo: 7 sedes UTN + unidades placeholder por sede
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_unidades_programaticas (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    codigo      VARCHAR(20)  NULL,
    nombre      VARCHAR(200) NOT NULL,
    tipo        ENUM('sede','unidad','direccion','escuela','otro') NOT NULL DEFAULT 'unidad',
    sede_ref    VARCHAR(100) NULL COMMENT 'Nombre sede UTN de referencia',
    activo      BOOLEAN NOT NULL DEFAULT TRUE,
    orden       SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_sodsi_unidad_nombre (nombre),
    INDEX idx_sodsi_unidad_tipo (tipo),
    INDEX idx_sodsi_unidad_sede (sede_ref)
) ENGINE=InnoDB;

INSERT INTO sodsi_unidades_programaticas (id, codigo, nombre, tipo, sede_ref, orden) VALUES
  (  1, 'UTN-SED-01', 'Sede Regional Atenas',                    'sede', 'Sede Atenas',       1),
  (  2, 'UTN-SED-02', 'Sede Regional Central Alajuela',          'sede', 'Sede Central',      2),
  (  3, 'UTN-SED-03', 'Sede Regional Guanacaste',                'sede', 'Sede Guanacaste',   3),
  (  4, 'UTN-SED-04', 'Sede Regional Puntarenas',              'sede', 'Sede Puntarenas',   4),
  (  5, 'UTN-SED-05', 'Sede Regional San Carlos',              'sede', 'Sede San Carlos',   5),
  (  6, 'UTN-SED-06', 'Centro de Formación Pedagógica (CFP)',    'sede', 'CFP',               6),
  (  7, 'UTN-SED-07', 'Sede Regional Limón',                     'sede', 'Sede Limón',        7),
  ( 10, 'UTN-U-010',  'Dirección Académica — Sede Central',     'direccion', 'Sede Central', 10),
  ( 11, 'UTN-U-011',  'Escuela de Ingeniería en Sistemas',       'escuela', 'Sede Central',  11),
  ( 12, 'UTN-U-012',  'Escuela de Agronomía — Atenas',           'escuela', 'Sede Atenas',   12),
  ( 13, 'UTN-U-013',  'Unidad de Extensión — Guanacaste',        'unidad', 'Sede Guanacaste', 13),
  ( 14, 'UTN-U-014',  'Unidad de Investigación — San Carlos',    'unidad', 'Sede San Carlos', 14),
  ( 15, 'UTN-U-015',  'Dirección Administrativa — Puntarenas',   'direccion', 'Sede Puntarenas', 15),
  ( 16, 'UTN-U-016',  'Coordinación Vida Estudiantil — CFP',     'unidad', 'CFP',           16),
  ( 17, 'UTN-U-017',  'Unidad Programática — Limón (placeholder)', 'unidad', 'Sede Limón', 17),
  ( 18, 'UTN-U-018',  'Aeas',                                     'unidad', 'Sede Central', 18),
  ( 99, 'UTN-U-099',  'Unidad programática UTN (placeholder)',     'otro', NULL,            99);

-- ────────────────────────────────────────────────────────────
-- Regiones Mideplan
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_regiones_mideplan (
    id      TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(80) NOT NULL UNIQUE,
    codigo  VARCHAR(30) NULL,
    es_na   BOOLEAN NOT NULL DEFAULT FALSE,
    orden   TINYINT UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB;

INSERT INTO sodsi_regiones_mideplan (id, nombre, codigo, es_na, orden) VALUES
  (1, 'Región Central',           'central',        FALSE, 1),
  (2, 'Región Brunca',            'brunca',         FALSE, 2),
  (3, 'Región Chorotega',         'chorotega',      FALSE, 3),
  (4, 'Región Huetar Caribe',     'huetar_caribe',  FALSE, 4),
  (5, 'Región Huetar Norte',      'huetar_norte',   FALSE, 5),
  (6, 'Región Pacífico Central',  'pacifico_central', FALSE, 6),
  (7, 'No aplica',                'na',             TRUE,  7),
  (8, 'No se identifica',         'no_identifica',  TRUE,  8);

-- ────────────────────────────────────────────────────────────
-- Ejes PLANES — PNDIP 2023-2026 (5 ejes oficiales Conare/UTN)
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_ejes_planes (
    id      TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(120) NOT NULL UNIQUE,
    codigo  VARCHAR(30) NOT NULL UNIQUE,
    orden   TINYINT UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB;

INSERT INTO sodsi_ejes_planes (id, nombre, codigo, orden) VALUES
  (1, 'Seguridad Humana',                                      'pndip_seguridad',      1),
  (2, 'Infraestructura, Movilidad y Ordenamiento Territorial',   'pndip_infraestructura', 2),
  (3, 'Bienestar Social',                                      'pndip_bienestar',      3),
  (4, 'Competitividad, Innovación y Productividad',              'pndip_competitividad', 4),
  (5, 'Gestión Ambiental y Desarrollo Sostenible',             'pndip_ambiental',      5);

-- ────────────────────────────────────────────────────────────
-- Tipo de aliado externo (ámbito × tipo)
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_aliado_tipo (
    id      SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ambito  ENUM('nacional','internacional','na','no_identifica') NOT NULL,
    tipo    ENUM('academia','empresa','gobierno','sociedad','na','no_identifica') NOT NULL,
    etiqueta VARCHAR(120) NOT NULL,
    orden   SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    UNIQUE KEY uk_sodsi_aliado_ambito_tipo (ambito, tipo)
) ENGINE=InnoDB;

INSERT INTO sodsi_aliado_tipo (id, ambito, tipo, etiqueta, orden) VALUES
  ( 1, 'nacional',       'academia',  'Nacional — Academia',        1),
  ( 2, 'nacional',       'empresa',   'Nacional — Empresa',         2),
  ( 3, 'nacional',       'gobierno',  'Nacional — Gobierno',        3),
  ( 4, 'nacional',       'sociedad',  'Nacional — Sociedad',        4),
  ( 5, 'internacional',  'academia',  'Internacional — Academia',   5),
  ( 6, 'internacional',  'empresa',   'Internacional — Empresa',    6),
  ( 7, 'internacional',  'gobierno',  'Internacional — Gobierno',   7),
  ( 8, 'internacional',  'sociedad',  'Internacional — Sociedad',   8),
  ( 9, 'na',             'na',        'No aplica',                  9),
  (10, 'no_identifica',  'no_identifica', 'No se identifica',      10);

-- ────────────────────────────────────────────────────────────
-- Sector beneficiario — categoría + valores (árbol glosario PDF)
-- ────────────────────────────────────────────────────────────

CREATE TABLE sodsi_beneficiario_categoria (
    id      TINYINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    codigo  VARCHAR(40) NOT NULL UNIQUE,
    nombre  VARCHAR(80) NOT NULL,
    es_na   BOOLEAN NOT NULL DEFAULT FALSE,
    orden   TINYINT UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB;

CREATE TABLE sodsi_beneficiario_valor (
    id               SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    categoria_id     TINYINT UNSIGNED NOT NULL,
    codigo           SMALLINT UNSIGNED NOT NULL COMMENT 'Código SODSI/OPSI para export [cat]-[val]; 901+ reservado personalizados',
    nombre           VARCHAR(150) NOT NULL,
    orden            SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    activo           BOOLEAN NOT NULL DEFAULT TRUE,
    es_personalizado BOOLEAN NOT NULL DEFAULT FALSE,
    creado_por       INT NULL COMMENT 'ods_login.usuarios.id — gestor que creó el valor',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES sodsi_beneficiario_categoria(id) ON DELETE RESTRICT,
    UNIQUE KEY uk_sodsi_ben_cat_valor (categoria_id, nombre),
    UNIQUE KEY uk_sodsi_ben_valor_codigo (codigo),
    INDEX idx_sodsi_ben_valor_cat (categoria_id),
    INDEX idx_sodsi_ben_valor_activo (activo)
) ENGINE=InnoDB;

-- Regla códigos personalizados (gestor crea valor nuevo):
--   · Oficiales: seed glosario OPSI (101–512, 601, 701)
--   · Personalizados: codigo >= 901 (único global); export [cat_codigo]-[valor_codigo]

INSERT INTO sodsi_beneficiario_categoria (id, codigo, nombre, es_na, orden) VALUES
  (1, '100', 'Ocupación',                    FALSE, 1),
  (2, '200', 'Edad',                         FALSE, 2),
  (3, '300', 'Condición de vulnerabilidad',  FALSE, 3),
  (4, '400', 'Ciclo de educación',           FALSE, 4),
  (5, '500', 'Sector socioeconómico',        FALSE, 5),
  (6, '600', 'No aplica',                    TRUE,  6),
  (7, '700', 'No se identifica',             TRUE,  7);

INSERT INTO sodsi_beneficiario_valor (categoria_id, codigo, nombre, orden) VALUES
  -- Ocupación [100]
  (1, 101, 'Profesionales en servicio', 1),
  (1, 102, 'Académicos (docentes)', 2),
  (1, 103, 'Otros profesionales', 3),
  (1, 104, 'Personal técnico o auxiliar', 4),
  (1, 105, 'Personal de apoyo administrativo', 5),
  -- Edad [200]
  (2, 201, 'Niños (as)', 1),
  (2, 202, 'Adolescentes', 2),
  (2, 203, 'Adulto', 3),
  (2, 204, 'Adulto mayor', 4),
  -- Condición de vulnerabilidad [300]
  (3, 301, 'Población con discapacidad', 1),
  (3, 302, 'Población indígena', 2),
  (3, 303, 'Población migrante', 3),
  (3, 304, 'Trabajadores sexuales', 4),
  (3, 305, 'Población en zonas con riesgo ambiental', 5),
  (3, 306, 'Población con enfermedades terminales (VIH - SIDA - cáncer, otras)', 6),
  (3, 307, 'Población en condiciones de pobreza extrema (indigentes y otros)', 7),
  (3, 308, 'Población adicta', 8),
  (3, 309, 'Privados de Libertad', 9),
  -- Ciclo de educación [400] — xlsx ejemplo: [400]-[404] Estudiantes universitarios
  (4, 401, 'Estudiantes de preescolar', 1),
  (4, 402, 'Estudiantes I y II ciclo', 2),
  (4, 403, 'Estudiantes III y IV ciclo', 3),
  (4, 404, 'Estudiantes universitarios', 4),
  -- Sector socioeconómico [500]
  (5, 501, 'Sector productivo privado', 1),
  (5, 502, 'Pymes del sector industrial artesanal', 2),
  (5, 503, 'Pymes del sector agropecuario y forestal', 3),
  (5, 504, 'Pymes del sector servicios (turismo, comercio, etc)', 4),
  (5, 505, 'Sector público', 5),
  (5, 506, 'Gobiernos locales', 6),
  (5, 507, 'Empresas', 7),
  (5, 508, 'Áreas de conservación ambiental', 8),
  (5, 509, 'Otras instituciones u organizaciones', 9),
  (5, 510, 'Sector universitario', 10),
  (5, 511, 'Organismos no gubernamentales', 11),
  (5, 512, 'Núcleo familiar', 12),
  -- No aplica / No se identifica
  (6, 601, 'No aplica', 1),
  (7, 701, 'No se identifica', 1);
