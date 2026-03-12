-- Script Principal para Crear Base de Datos Completa ODS16
-- Ejecutar en orden: 1. Login System, 2. Todos los ODS Projects (1-17)

-- ============================================
-- PASO 1: Crear Sistema de Login y Autenticación
-- ============================================
SOURCE login_system.sql;

-- ============================================
-- PASO 2: Crear Base de Datos ODS01 (Fin de la Pobreza)
-- ============================================
SOURCE ods01_database.sql;

-- ============================================
-- PASO 3: Crear Base de Datos ODS02 (Hambre Cero)
-- ============================================
SOURCE ods02_database.sql;

-- ============================================
-- PASO 4: Crear Base de Datos ODS03 (Salud y Bienestar)
-- ============================================
SOURCE ods03_database.sql;

-- ============================================
-- PASO 5: Crear Base de Datos ODS04 (Educación de Calidad)
-- ============================================
SOURCE ods04_database.sql;

-- ============================================
-- PASO 6: Crear Base de Datos ODS05 (Igualdad de Género)
-- ============================================
SOURCE ods05_database.sql;

-- ============================================
-- PASO 7: Crear Base de Datos ODS06 (Agua Limpia y Saneamiento)
-- ============================================
SOURCE ods06_database.sql;

-- ============================================
-- PASO 8: Crear Base de Datos ODS07 (Energía Asequible y No Contaminante)
-- ============================================
SOURCE ods07_database.sql;

-- ============================================
-- PASO 9: Crear Base de Datos ODS08 (Trabajo Decente y Crecimiento Económico)
-- ============================================
SOURCE ods08_database.sql;

-- ============================================
-- PASO 10: Crear Base de Datos ODS09 (Industria, Innovación e Infraestructura)
-- ============================================
SOURCE ods09_database.sql;

-- ============================================
-- PASO 11: Crear Base de Datos ODS10 (Reducción de las Desigualdades)
-- ============================================
SOURCE ods10_database.sql;

-- ============================================
-- PASO 12: Crear Base de Datos ODS11 (Ciudades y Comunidades Sostenibles)
-- ============================================
SOURCE ods11_database.sql;

-- ============================================
-- PASO 13: Crear Base de Datos ODS12 (Producción y Consumo Responsables)
-- ============================================
SOURCE ods12_database.sql;

-- ============================================
-- PASO 14: Crear Base de Datos ODS13 (Acción por el Clima)
-- ============================================
SOURCE ods13_database.sql;

-- ============================================
-- PASO 15: Crear Base de Datos ODS14 (Vida Submarina)
-- ============================================
SOURCE ods14_database.sql;

-- ============================================
-- PASO 16: Crear Base de Datos ODS15 (Vida de Ecosistemas Terrestres)
-- ============================================
SOURCE ods15_database.sql;

-- ============================================
-- PASO 17: Crear Base de Datos ODS16 (Paz, Justicia e Instituciones Sólidas)
-- ============================================
SOURCE ods16_database.sql;

-- ============================================
-- PASO 18: Crear Base de Datos ODS17 (Alianzas para Lograr los Objetivos)
-- ============================================
SOURCE ods17_database.sql;

-- ============================================
-- VERIFICACIÓN FINAL
-- ============================================

-- Verificar que todas las bases de datos existen
SELECT 'Verificando bases de datos ODS creadas...' as mensaje;
SHOW DATABASES LIKE 'ods%';

-- Verificar tablas en ods_login
SELECT 'Tablas en ods_login:' as mensaje;
USE ods_login;
SHOW TABLES;

-- Verificar tablas en cada base de datos ODS
SELECT 'Tablas en ods01:' as mensaje;
USE ods01;
SHOW TABLES;

SELECT 'Tablas en ods02:' as mensaje;
USE ods02;
SHOW TABLES;

SELECT 'Tablas en ods03:' as mensaje;
USE ods03;
SHOW TABLES;

SELECT 'Tablas en ods04:' as mensaje;
USE ods04;
SHOW TABLES;

SELECT 'Tablas en ods05:' as mensaje;
USE ods05;
SHOW TABLES;

SELECT 'Tablas en ods06:' as mensaje;
USE ods06;
SHOW TABLES;

SELECT 'Tablas en ods07:' as mensaje;
USE ods07;
SHOW TABLES;

SELECT 'Tablas en ods08:' as mensaje;
USE ods08;
SHOW TABLES;

SELECT 'Tablas en ods09:' as mensaje;
USE ods09;
SHOW TABLES;

SELECT 'Tablas en ods10:' as mensaje;
USE ods10;
SHOW TABLES;

SELECT 'Tablas en ods11:' as mensaje;
USE ods11;
SHOW TABLES;

SELECT 'Tablas en ods12:' as mensaje;
USE ods12;
SHOW TABLES;

SELECT 'Tablas en ods13:' as mensaje;
USE ods13;
SHOW TABLES;

SELECT 'Tablas en ods14:' as mensaje;
USE ods14;
SHOW TABLES;

SELECT 'Tablas en ods15:' as mensaje;
USE ods15;
SHOW TABLES;

SELECT 'Tablas en ods16:' as mensaje;
USE ods16;
SHOW TABLES;

SELECT 'Tablas en ods17:' as mensaje;
USE ods17;
SHOW TABLES;

SELECT '=========================================' as separador;
SELECT 'SISTEMA COMPLETO ODS CREADO EXITOSAMENTE' as mensaje;
SELECT 'Total de bases de datos ODS: 17 + 1 (login)' as resumen;
SELECT '=========================================' as separador;

-- Verificar tablas en ods16
SELECT 'Tablas en ods16:' as mensaje;
USE ods16;
SHOW TABLES;

-- Verificar usuarios creados
SELECT 'Usuarios en ods_login:' as mensaje;
USE ods_login;
SELECT id, username, email, role, full_name, is_active FROM usuarios;

-- Verificar vistas administrativas
SELECT 'Vistas administrativas en ods16:' as mensaje;
USE ods16;
SHOW FULL TABLES WHERE Table_type LIKE 'VIEW';

-- Verificar procedimientos almacenados
SELECT 'Procedimientos en ods16:' as mensaje;
USE ods16;
SHOW PROCEDURE STATUS WHERE Db = 'ods16';

SELECT 'Base de datos completa creada exitosamente!' as mensaje_final, NOW() as fecha_creacion;
