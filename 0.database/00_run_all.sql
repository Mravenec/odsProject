-- ============================================================
-- 00_run_all.sql — SCRIPT MAESTRO DE INSTALACIÓN
-- Sistema ODS Agenda 2030 — UTN Costa Rica
--
-- EJECUTAR EN ESTE ORDEN EXACTO contra MariaDB como root:
--   mysql -u root -p < 00_run_all.sql
--
-- Requiere: MariaDB 10.6+ con usuario root con todos los permisos
-- Tiempo estimado: ~30 segundos
-- ============================================================

SET FOREIGN_KEY_CHECKS = 0;
SET SQL_MODE = '';

-- ─────────────────────────────────────────────────────────────────
-- PASO 1: ods_login (base maestra de autenticación y catálogos)
--         DEBE ir primero — todos los demás dependen de ella
-- ─────────────────────────────────────────────────────────────────
SOURCE propuesta_actual/1. login_system.sql;

-- ─────────────────────────────────────────────────────────────────
-- PASO 2: ods_master (proyectos centralizados)
--         Depende de: ods_login.usuarios, ods_login.sedes
-- ─────────────────────────────────────────────────────────────────
SOURCE propuesta_actual/2. ods_master_database.sql;

-- ─────────────────────────────────────────────────────────────────
-- PASO 3: ods01 … ods17 (uno por cada ODS)
--         Cada uno depende de: ods_login.indicador_master, ods_master.proyectos
-- ─────────────────────────────────────────────────────────────────
SOURCE propuesta_actual/4. ods01_database.sql;
SOURCE propuesta_actual/5. ods02_database.sql;
SOURCE propuesta_actual/6. ods03_database.sql;
SOURCE propuesta_actual/7. ods04_database.sql;
SOURCE propuesta_actual/8. ods05_database.sql;
SOURCE propuesta_actual/9. ods06_database.sql;
SOURCE propuesta_actual/10. ods07_database.sql;
SOURCE propuesta_actual/11. ods08_database.sql;
SOURCE propuesta_actual/12. ods09_database.sql;
SOURCE propuesta_actual/13. ods10_database.sql;
SOURCE propuesta_actual/14. ods11_database.sql;
SOURCE propuesta_actual/15. ods12_database.sql;
SOURCE propuesta_actual/16. ods13_database.sql;
SOURCE propuesta_actual/17. ods14_database.sql;
SOURCE propuesta_actual/18. ods15_database.sql;
SOURCE propuesta_actual/19. ods16_database.sql;
SOURCE propuesta_actual/20. ods17_database.sql;

-- ─────────────────────────────────────────────────────────────────
-- PASO 4: Datos de prueba / mocks
--         ÚLTIMO — requiere que todas las BDs existan
-- ─────────────────────────────────────────────────────────────────
SOURCE propuesta_actual/21. ods_mocks.sql;

SET FOREIGN_KEY_CHECKS = 1;

-- ─────────────────────────────────────────────────────────────────
-- VERIFICACIÓN FINAL
-- ─────────────────────────────────────────────────────────────────
SELECT 
    SCHEMA_NAME AS base_de_datos,
    (SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA_NAME) AS tablas
FROM INFORMATION_SCHEMA.SCHEMATA
WHERE SCHEMA_NAME IN (
    'ods_login','ods_master',
    'ods01','ods02','ods03','ods04','ods05','ods06','ods07','ods08','ods09',
    'ods10','ods11','ods12','ods13','ods14','ods15','ods16','ods17'
)
ORDER BY SCHEMA_NAME;

SELECT '✅ Sistema ODS instalado exitosamente' AS mensaje, NOW() AS fecha;
