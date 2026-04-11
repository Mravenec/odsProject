-- ============================================================
-- MOCKS COMPLETOS DEL SISTEMA ODS (V3: Robusto & Standalone)
-- Compatible con MariaDB 10.6+ y HeidiSQL
-- Ejecutar DESPUÉS de: login_system.sql y cada odsXX_database.sql
-- ============================================================

-- ────────────────────────────────────────────────────────────
-- 1. CONFIGURACIÓN GLOBAL (ods_login)
-- ────────────────────────────────────────────────────────────
USE ods_login;

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
  (6, 'CFP', 'Centro de Formación Pedagógica y Tecnología Educativa');

INSERT INTO usuarios (id, username, email, password_hash, full_name, rol_id, sede_id, is_active, email_verificado) VALUES
  (2, 'gestor_pobreza', 'ana.garcia@ods.cr', '$2b$12$kk4GLfeuXtXbsdS0Dj01mepTBBAWTV814xwLBkR4bBlyRiSyxvqMq', 'Ana García López', 2, 2, TRUE, TRUE),
  (3, 'gestor_hambre', 'carlos.rodriguez@ods.cr', '$2b$12$kk4GLfeuXtXbsdS0Dj01mepTBBAWTV814xwLBkR4bBlyRiSyxvqMq', 'Carlos Rodríguez Mora', 2, 5, TRUE, TRUE),
  (4, 'consultor_general', 'maria.jimenez@ods.cr', '$2b$12$kk4GLfeuXtXbsdS0Dj01mepTBBAWTV814xwLBkR4bBlyRiSyxvqMq', 'María Jiménez Solano', 3, 2, TRUE, TRUE);

INSERT INTO permisos_ods (usuario_id, ods_num, puede_crear, puede_editar, puede_ver) VALUES
  (2, 1, TRUE, TRUE, TRUE), (3, 2, TRUE, TRUE, TRUE);

-- ────────────────────────────────────────────────────────────
-- 2. ODS 01: FIN DE LA POBREZA (ESCENARIO COMPLETO)
-- ────────────────────────────────────────────────────────────
USE ods01;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas;
TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos;
TRUNCATE TABLE auditoria_ods01;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO proyectos (id, usuario_id, sede_id, nombre_proyecto, objetivo_id, descripcion, fecha_inicio, fecha_fin, meta_general, estado) VALUES
  (1, 2, 2, 'Reducción Pobreza Extrema - Chorotega', 1, 'Iniciativa para reducir la brecha de pobreza', '2023-01-01', '2024-12-31', 'Reducir un 5%', 'activo');

SET @ind111 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '1.1.1' AND ods_id = 1);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, meta_valor, meta_unidad) VALUES
  (1, 1, @ind111, 2.5, 'Porcentaje'),
  (2, 1, (SELECT id FROM ods_login.indicador_master WHERE codigo = '1.2.1' AND ods_id = 1), 0, 'Porcentaje'); -- Caso Borde: Meta 0 (SIN DATOS)

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, tipo_dato) VALUES
  (1, 1, 'p1', 'Decimal'),
  (2, 1, 'p2', 'Decimal');

INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES
  (1, 1, 3.1, '2023-06-15', 'Ana García'),
  (2, 1, 2.8, '2023-12-15', 'Ana García'),
  (3, 1, 2.5, '2024-03-30', 'Ana García');

INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES
  (1, 1, 15500), (1, 2, 500000),
  (2, 1, 14000), (2, 2, 500000),
  (3, 1, 12500), (3, 2, 500000);

-- ────────────────────────────────────────────────────────────
-- 3. ODS 02: HAMBRE CERO
-- ────────────────────────────────────────────────────────────
USE ods02;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas;
TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos;
TRUNCATE TABLE auditoria_ods02;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO proyectos (id, usuario_id, sede_id, nombre_proyecto, objetivo_id, fecha_inicio, fecha_fin, estado) VALUES
  (1, 3, 5, 'Seguridad Alimentaria San Carlos', 2, '2023-02-01', '2024-12-01', 'activo');

SET @ind211 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '2.1.1' AND ods_id = 2);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, meta_valor, meta_unidad) VALUES
  (1, 1, @ind211, 1.5, 'Porcentaje'),
  (2, 1, (SELECT id FROM ods_login.indicador_master WHERE codigo = '2.1.2' AND ods_id = 2), 0, 'Porcentaje'); -- Caso Borde: Meta 0 (SIN DATOS)

INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, tipo_dato) VALUES
  (1, 1, 'subalimentados', 'Decimal'),
  (2, 1, 'poblacion_total', 'Decimal');

INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES
  (1, 1, 1.8, '2023-08-10', 'Carlos Rodríguez');
INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES
  (1, 1, 5400), (1, 2, 300000);

-- ────────────────────────────────────────────────────────────
-- 4. OTROS ODS PRIORITARIOS (03, 04, 05, 13)
-- ────────────────────────────────────────────────────────────

-- ODS 03
USE ods03; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods03; SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO proyectos (id, usuario_id, sede_id, nombre_proyecto, objetivo_id, fecha_inicio, fecha_fin, estado) VALUES (1, 2, 2, 'Mortalidad Materna Regional', 3, '2023-01-01', '2024-12-31', 'activo');
SET @ind311 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '3.1.1' AND ods_id = 3);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, meta_valor, meta_unidad) VALUES (1, 1, @ind311, 50, 'Tasa/100k');

-- ODS 04
USE ods04; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods04; SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO proyectos (id, usuario_id, sede_id, nombre_proyecto, objetivo_id, fecha_inicio, fecha_fin, estado) VALUES (1, 3, 2, 'Bilingüismo Nacional', 4, '2023-01-01', '2025-12-31', 'activo');
SET @ind411 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '4.1.1' AND ods_id = 4);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, meta_valor, meta_unidad) VALUES (1, 1, @ind411, 95, 'Porcentaje');

-- ODS 13 (Formulas Custom)
USE ods13; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods13; SET FOREIGN_KEY_CHECKS = 1;
INSERT INTO proyectos (id, usuario_id, sede_id, nombre_proyecto, objetivo_id, fecha_inicio, fecha_fin, estado) VALUES (1, 2, 4, 'Reforestación Manglares Puntarenas', 13, '2023-10-01', '2025-10-01', 'activo');
SET @ind1311 = (SELECT id FROM ods_login.indicador_master WHERE codigo = '13.1.1' AND ods_id = 13);
INSERT INTO proyecto_indicadores (id, proyecto_id, indicador_master_id, formula_custom, meta_valor, meta_unidad) VALUES (1, 1, @ind1311, '(area_reforestada / area_objetivo) * 100', 100, 'Porcentaje');
INSERT INTO proyecto_indicador_parametros (id, proyecto_indicador_id, nombre_parametro, tipo_dato) VALUES (1, 1, 'area_reforestada', 'Decimal'), (2, 1, 'area_objetivo', 'Decimal');
INSERT INTO mediciones_historicas (id, proyecto_indicador_id, valor_calculado, fecha_medicion, responsable) VALUES (1, 1, 25.0, '2024-01-20', 'Carlos Clima');
INSERT INTO medicion_parametro_valores (medicion_id, parametro_id, valor_ingresado) VALUES (1, 1, 12.5), (1, 2, 50.0);

-- ────────────────────────────────────────────────────────────
-- 5. LIMPIEZA DE LOS DEMÁS ODS (Para evitar datos huérfanos)
-- ────────────────────────────────────────────────────────────
-- Este bloque asegura que aunque no insertemos datos pesados, las tablas estén vacías y listas.

USE ods05; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods05; SET FOREIGN_KEY_CHECKS = 1;
USE ods06; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods06; SET FOREIGN_KEY_CHECKS = 1;
USE ods07; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods07; SET FOREIGN_KEY_CHECKS = 1;
USE ods08; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods08; SET FOREIGN_KEY_CHECKS = 1;
USE ods09; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods09; SET FOREIGN_KEY_CHECKS = 1;
USE ods10; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods10; SET FOREIGN_KEY_CHECKS = 1;
USE ods11; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods11; SET FOREIGN_KEY_CHECKS = 1;
USE ods12; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods12; SET FOREIGN_KEY_CHECKS = 1;
USE ods14; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods14; SET FOREIGN_KEY_CHECKS = 1;
USE ods15; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods15; SET FOREIGN_KEY_CHECKS = 1;
USE ods16; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods16; SET FOREIGN_KEY_CHECKS = 1;
USE ods17; SET FOREIGN_KEY_CHECKS = 0; TRUNCATE TABLE medicion_parametro_valores; TRUNCATE TABLE mediciones_historicas; TRUNCATE TABLE proyecto_indicador_parametros; TRUNCATE TABLE proyecto_indicadores; TRUNCATE TABLE proyectos; TRUNCATE TABLE auditoria_ods17; SET FOREIGN_KEY_CHECKS = 1;

-- ────────────────────────────────────────────────────────────
-- 6. FINALIZACIÓN
-- ────────────────────────────────────────────────────────────
USE ods_login;
SELECT 'Sistema de Mocks V3 (Standalone & Robusto) completado' AS resultado, NOW() AS fecha;