-- ============================================================
-- MOCKS COMPLETOS DEL SISTEMA ODS (V3: Robusto & Standalone)
-- Compatible con MariaDB 10.6+ y HeidiSQL
-- Ejecutar DESPUÉS de: login_system.sql y cada odsXX_database.sql
-- ============================================================

-- ────────────────────────────────────────────────────────────
-- 1. CONFIGURACIÓN GLOBAL (ods_login)
-- ────────────────────────────────────────────────────────────
USE ods_login;

-- Admin inicial (creado en login_system.sql): mantener hash bcrypt al recargar mocks
UPDATE usuarios SET password_hash = '$2b$12$4NK2SGLqwC.FGlCUpp6Is.QfrDn0WNyuYEKdy2VDf/aZ/zkLF315i'
WHERE email = 'admin@utn.ac.cr';

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE sesiones;
TRUNCATE TABLE permisos_ods;
TRUNCATE TABLE auditoria_login;
DELETE FROM usuarios WHERE id > 1;
TRUNCATE TABLE sedes;
ALTER TABLE sedes AUTO_INCREMENT = 1;
ALTER TABLE usuarios AUTO_INCREMENT = 2;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO sedes (id, nombre, descripcion) VALUES
  (1, 'Sede Atenas', 'Sede enfocada en Ciencias Agropecuarias'),
  (2, 'Sede Central', 'Sede administrativa y académica central - Alajuela'),
  (3, 'Sede Guanacaste', 'Sede en la región Chorotega'),
  (4, 'Sede Puntarenas', 'Sede en la región Pacífico Central'),
  (5, 'Sede San Carlos', 'Sede en la región Huetar Norte'),
  (6, 'CFP', 'Centro de Formación Pedagógica y Tecnología Educativa'),
  (7, 'Sede Limón', 'Sede Regional Limón — UTN (7ª sede SODSI)');

-- Credenciales de desarrollo (contraseña en texto → hash bcrypt cost 12 en password_hash):
--   password123    → jartavia / khernandez / mlopez (@utn.ac.cr)
--   Admin1234!     → admin@utn.ac.cr (login_system.sql + UPDATE arriba)

INSERT INTO usuarios (id, username, email, password_hash, full_name, rol_id, sede_id,
  area_id, dependencia_id, rol_dependencia_id, unidad_programatica_id, telefono_contacto, is_active, email_verificado) VALUES
  (2, 'jartavia', 'jartavia@utn.ac.cr', '$2b$12$k47TtiACGyZylvK057dRW.X705iWMTY/yymuTA9A40aF/uKLj4Vhq', 'Joaquín Artavia', 2, 2, 7, 7, 1, 18, '24355000', TRUE, TRUE),
  (3, 'khernandez', 'khernandez@utn.ac.cr', '$2b$12$k47TtiACGyZylvK057dRW.X705iWMTY/yymuTA9A40aF/uKLj4Vhq', 'Karina Hernández', 4, 2, 7, 8, 1, 18, '24355000', TRUE, TRUE),
  (4, 'mlopez', 'mlopez@utn.ac.cr', '$2b$12$k47TtiACGyZylvK057dRW.X705iWMTY/yymuTA9A40aF/uKLj4Vhq', 'Marco López', 3, 2, 7, 9, 5, 18, '24355000', TRUE, TRUE);

INSERT INTO permisos_ods (usuario_id, ods_num, puede_crear, puede_editar, puede_ver) VALUES
  (2, 1, TRUE, TRUE, TRUE),
  (2, 2, TRUE, TRUE, TRUE);

-- ────────────────────────────────────────────────────────────
-- 1.1 CONFIGURACIÓN DE PROYECTOS CENTRALIZADOS (ods_master)
-- ────────────────────────────────────────────────────────────
USE ods_master;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE proyecto_beneficiarios;
TRUNCATE TABLE proyecto_documentos;
TRUNCATE TABLE proyecto_ods;
TRUNCATE TABLE proyectos;
ALTER TABLE proyectos AUTO_INCREMENT = 1;
ALTER TABLE proyecto_ods AUTO_INCREMENT = 1;
ALTER TABLE proyecto_documentos AUTO_INCREMENT = 1;
ALTER TABLE proyecto_beneficiarios AUTO_INCREMENT = 1;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO proyectos (id, usuario_id, sede_id, nombre_proyecto, descripcion, fecha_inicio, fecha_fin, meta_general, estado) VALUES
  (1, 2, 2, 'Reducción Pobreza Extrema - Chorotega', 'Iniciativa para reducir la brecha de pobreza', '2023-01-01', '2024-12-31', 'Reducir un 5%', 'activo'),
  (2, 2, 5, 'Seguridad Alimentaria San Carlos', 'Fondo de ayuda alimentaria', '2023-02-01', '2024-12-01', NULL, 'activo'),
  (3, 2, 2, 'Mortalidad Materna Regional', 'Salud pública materna', '2023-01-01', '2024-12-31', NULL, 'activo'),
  (4, 2, 2, 'Bilingüismo Nacional', 'Programa de educación bilingüe', '2023-01-01', '2025-12-31', NULL, 'activo'),
  (5, 2, 4, 'Reforestación Manglares Puntarenas', 'Conservación de ecosistemas marinos', '2023-10-01', '2025-10-01', NULL, 'activo');

-- Vinculación explícita Proyecto ↔ ODS
INSERT INTO proyecto_ods (proyecto_id, ods_id, es_primario) VALUES
  (1, 1, 1),
  (2, 2, 1),
  (3, 3, 1),
  (4, 4, 1),
  (5, 14, 1),
  (5, 13, 0),
  (5, 1, 0);

-- Documentos de evidencia para Project 1 (Requerido para enviar a revisión)
INSERT INTO proyecto_documentos (proyecto_id, nombre_archivo, tipo_mime, tamanio_bytes, contenido, subido_por) VALUES
  (1, 'evidencia_chorotega.pdf', 'application/pdf', 1024, 'FAKE_PDF_CONTENT', 2);

-- ────────────────────────────────────────────────────────────
-- 2. ODS 01: FIN DE LA POBREZA (ESCENARIO COMPLETO)
-- ────────────────────────────────────────────────────────────
USE ods01;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas;
TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores;
TRUNCATE TABLE auditoria_ods01;
SET FOREIGN_KEY_CHECKS = 1;

SET @ind111 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '1.1.1' AND ods_id = 1);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES
  (1, 1, @ind111, '(p1/p2)*100', 2.5, 'Porcentaje'),
  (2, 1, (SELECT id FROM ods_login.indicador_master WHERE codigo = '1.2.1' AND ods_id = 1), '(p1/p2)*100', 10.0, 'Porcentaje'),
  (3, 5, @ind111, '(p1/p2)*100', 5.0, 'Porcentaje');

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES
  (1, 1, 'Población Pobre', 'p1', 'Decimal', 12500),
  (2, 1, 'Población Total', 'p2', 'Decimal', 500000),
  (3, 3, 'Población Pobre', 'p1', 'Decimal', 500),
  (4, 3, 'Población Total', 'p2', 'Decimal', 10000),
  (5, 2, 'Población Pobreza Relativa', 'p1', 'Decimal', 10000),
  (6, 2, 'Población Total', 'p2', 'Decimal', 500000);

INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES
  (1, 1, 3.1, '2023-06-15', 'Joaquín Artavia'),
  (2, 1, 2.8, '2023-12-15', 'Joaquín Artavia'),
  (3, 1, 2.5, '2024-03-30', 'Joaquín Artavia'),
  (4, 2, 2.0, '2024-04-01', 'Joaquín Artavia');

INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES
  (1, 1, 15500), (1, 2, 500000),
  (2, 1, 14000), (2, 2, 500000),
  (3, 1, 12500), (3, 2, 500000),
  (4, 5, 10000), (4, 6, 500000);

-- ────────────────────────────────────────────────────────────
-- 3. ODS 02: HAMBRE CERO
-- ────────────────────────────────────────────────────────────
USE ods02;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas;
TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores;
TRUNCATE TABLE auditoria_ods02;
SET FOREIGN_KEY_CHECKS = 1;

SET @ind211 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '2.1.1' AND ods_id = 2);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES
  (1, 2, @ind211, '(p1/p2)*100', 1.5, 'Porcentaje'),
  (2, 2, (SELECT id FROM ods_login.indicador_master WHERE codigo = '2.1.2' AND ods_id = 2), '(p1/p2)*100', 5.0, 'Porcentaje');

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES
  (1, 1, 'Personas Subalimentadas', 'p1', 'Decimal', 5400),
  (2, 1, 'Población Total', 'p2', 'Decimal', 300000),
  (3, 2, 'Inseguridad Alimentaria', 'p1', 'Decimal', 1500),
  (4, 2, 'Población Total', 'p2', 'Decimal', 300000);

INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES
  (1, 1, 1.8, '2023-08-10', 'Joaquín Artavia'),
  (2, 2, 0.5, '2023-09-10', 'Joaquín Artavia');

INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES
  (1, 1, 5400), (1, 2, 300000),
  (2, 3, 1500), (2, 4, 300000);

-- ────────────────────────────────────────────────────────────
-- 4. OTROS ODS PRIORITARIOS (03, 04, 05, 13)
-- ────────────────────────────────────────────────────────────

-- ODS 03
USE ods03; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods03; SET FOREIGN_KEY_CHECKS = 1;
SET @ind311 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '3.1.1' AND ods_id = 3);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES (1, 3, @ind311, '(p1/p2)*100000', 50, 'Tasa/100k');
INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES (1, 1, 'Muertes', 'p1', 'Decimal', 10), (2, 1, 'Nacimientos', 'p2', 'Decimal', 20000);
INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES (1, 1, 50.0, '2024-01-01', 'Admin');
INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES (1, 1, 10), (1, 2, 20000);

-- ODS 04
USE ods04; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods04; SET FOREIGN_KEY_CHECKS = 1;
SET @ind411 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '4.1.1' AND ods_id = 4);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES (1, 4, @ind411, '(p1/p2)*100', 95, 'Porcentaje');
INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES (1, 1, 'Estudiantes', 'p1', 'Decimal', 950), (2, 1, 'Total Niños', 'p2', 'Decimal', 1000);
INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES (1, 1, 95.0, '2024-01-01', 'Admin');
INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES (1, 1, 950), (1, 2, 1000);

-- ODS 13 (Formulas Custom)
USE ods13; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods13; SET FOREIGN_KEY_CHECKS = 1;
SET @ind1311 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '13.1.1' AND ods_id = 13);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES (1, 5, @ind1311, '(area_reforestada / area_objetivo) * 100', 100, 'Porcentaje');
INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES (1, 1, 'Área Reforestada', 'area_reforestada', 'Decimal', 12.5), (2, 1, 'Área Objetivo', 'area_objetivo', 'Decimal', 50.0);
INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES (1, 1, 25.0, '2024-01-20', 'Carlos Clima');
INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES (1, 1, 12.5), (1, 2, 50.0);

-- ────────────────────────────────────────────────────────────
-- 5. LIMPIEZA DE LOS DEMÁS ODS (Para evitar datos huérfanos)
-- ────────────────────────────────────────────────────────────
-- Este bloque asegura que aunque no insertemos datos pesados, las tablas estén vacías y listas.

USE ods05; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods05; SET FOREIGN_KEY_CHECKS = 1;
USE ods06; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods06; SET FOREIGN_KEY_CHECKS = 1;
USE ods07; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods07; SET FOREIGN_KEY_CHECKS = 1;
USE ods08; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods08; SET FOREIGN_KEY_CHECKS = 1;
USE ods09; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods09; SET FOREIGN_KEY_CHECKS = 1;
USE ods10; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods10; SET FOREIGN_KEY_CHECKS = 1;
USE ods11; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods11; SET FOREIGN_KEY_CHECKS = 1;
USE ods12; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods12; SET FOREIGN_KEY_CHECKS = 1;
-- ODS 14: VIDA SUBMARINA (Escenario de conservación de manglares)
USE ods14;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas;
TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores;
TRUNCATE TABLE auditoria_ods14;
SET FOREIGN_KEY_CHECKS = 1;

SET @ind1451 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '14.5.1' AND ods_id = 14);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES
  (1, 5, @ind1451, '(p1 / p2) * 100', 30.0, 'Porcentaje');

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES
  (1, 1, 'Área marina protegida (km²)', 'p1', 'Decimal', 15.0),
  (2, 1, 'Área marina total jurisdicción (km²)', 'p2', 'Decimal', 50.0);

INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES
  (1, 1, 20.0, '2024-02-15', 'Joaquín Artavia'),
  (2, 1, 30.0, '2024-05-15', 'Joaquín Artavia');

INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES
  (1, 1, 10.0), (1, 2, 50.0),
  (2, 1, 15.0), (2, 2, 50.0);
USE ods15; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods15; SET FOREIGN_KEY_CHECKS = 1;
USE ods16; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods16; SET FOREIGN_KEY_CHECKS = 1;
USE ods17; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE auditoria_ods17; SET FOREIGN_KEY_CHECKS = 1;

-- ────────────────────────────────────────────────────────────
-- 5.0b Beneficiario personalizado inactivo (QA export histórico soft-delete)
-- ────────────────────────────────────────────────────────────
USE ods_login;

INSERT INTO sodsi_beneficiario_valor (categoria_id, codigo, nombre, orden, activo, es_personalizado, creado_por) VALUES
  (5, 901, 'Emprendedores de la zona (QA inactivo)', 99, FALSE, TRUE, 2)
ON DUPLICATE KEY UPDATE
  activo = FALSE,
  es_personalizado = TRUE,
  creado_por = 2;

-- ────────────────────────────────────────────────────────────
-- 5.0 SPRINT EDICIÓN PLANIFICACIÓN — proyecto id=7 en planificacion (QA)
-- ────────────────────────────────────────────────────────────
USE ods_master;

INSERT INTO proyectos (
  id, usuario_id, sede_id, nombre_proyecto, descripcion,
  fecha_inicio, fecha_fin, meta_general, eje_planes_id, aliado_externo,
  location_province, location_canton, location_district, estado
) VALUES (
  7, 2, 2, 'Proyecto QA Planificación',
  'Mock para edición en planificación y wizard ficha SODSI simplificado',
  '2024-01-01', '2025-12-31', 'Revisar metas antes de activo',
  1, 'Municipalidad de Alajuela',
  'Alajuela', 'Alajuela', 'Alajuela', 'planificacion'
);

INSERT INTO proyecto_beneficiarios (proyecto_id, valor_id)
SELECT 7, id FROM ods_login.sodsi_beneficiario_valor
WHERE codigo = 203 LIMIT 1;

INSERT INTO proyecto_ods (proyecto_id, ods_id, es_primario) VALUES
  (7, 1, 1);

USE ods01;

INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad, meta_nombre) VALUES
  (5, 7, @ind111, '(p1/p2)*100', 5.0, 'Porcentaje', 'Reducir pobreza');

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES
  (9, 5, 'Población Pobre', 'p1', 'Decimal', 0),
  (10, 5, 'Población Total', 'p2', 'Decimal', 0);

-- ────────────────────────────────────────────────────────────
-- 5.1 SPRINT CONSULTOR — proyecto completado auditado (export Excel QA)
-- Consultor: mlopez (id 4); auditor: khernandez (id 3)
-- ────────────────────────────────────────────────────────────
USE ods_master;

INSERT INTO proyectos (
  id, usuario_id, sede_id, nombre_proyecto, descripcion,
  fecha_inicio, fecha_fin, meta_general, eje_planes_id, aliado_externo,
  location_province, location_canton, location_district,
  estado, auditado_por, auditado_en, observaciones_cierre, fecha_envio_revision
) VALUES (
  6, 2, 2, 'Proyecto QA Consultor SODSI',
  'Proyecto mock completado para QA export matriz SODSI (sede 2, año 2024)',
  '2024-01-01', '2024-12-31', 'Validar exportación matriz SODSI consultor',
  3, 'Universidad Nacional — Facultad Educación; Cámara de Comercio Alajuela',
  'Alajuela', 'Alajuela', 'Alajuela',
  'activo', NULL, NULL, NULL, NULL
);

INSERT INTO proyecto_beneficiarios (proyecto_id, valor_id)
SELECT 6, id FROM ods_login.sodsi_beneficiario_valor
WHERE codigo IN (404, 510);

INSERT INTO proyecto_beneficiarios (proyecto_id, valor_id)
SELECT 6, id FROM ods_login.sodsi_beneficiario_valor
WHERE codigo = 901 LIMIT 1;

INSERT INTO proyecto_ods (proyecto_id, ods_id, es_primario) VALUES
  (6, 1, 1);

INSERT INTO proyecto_documentos (proyecto_id, nombre_archivo, tipo_mime, tamanio_bytes, contenido, subido_por, descripcion) VALUES
  (6, 'informe_cierre_consultor.pdf', 'application/pdf', 2048, 'FAKE_PDF_QA_CONSULTOR', 2, 'Informe final de cierre'),
  (6, 'datos_indicadores_consultor.xlsx', 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', 4096, 'FAKE_XLSX_QA_CONSULTOR', 2, 'Resumen de indicadores');

USE ods01;

SET @ind111_qa = (SELECT id FROM ods_login.indicador_master WHERE codigo = '1.1.1' AND ods_id = 1);

INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES
  (4, 6, @ind111_qa, '(p1/p2)*100', 3.0, 'Porcentaje');

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, nombre_variable, tipo_dato, valor_actual) VALUES
  (7, 4, 'Población Pobre', 'p1', 'Decimal', 3000),
  (8, 4, 'Población Total', 'p2', 'Decimal', 100000);

INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES
  (5, 4, 3.0, '2024-10-01', 'Joaquín Artavia');

INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES
  (5, 7, 3000), (5, 8, 100000);

-- Cerrar auditoría DESPUÉS de indicadores/mediciones (Sprint 18: inmutabilidad post-cierre)
USE ods_master;

UPDATE proyectos SET
  estado = 'completado',
  auditado_por = 3,
  auditado_en = '2024-12-15 10:00:00',
  observaciones_cierre = 'Proyecto auditado y cerrado correctamente. Indicadores y evidencias verificados.',
  fecha_envio_revision = '2024-11-01 08:00:00'
WHERE id = 6;

-- ────────────────────────────────────────────────────────────
-- 6. FINALIZACIÓN
-- ────────────────────────────────────────────────────────────
USE ods_login;
SELECT 'Sistema de Mocks V3 (Standalone & Robusto) completado' AS resultado, NOW() AS fecha;