-- ============================================================
-- BASE DE DATOS: ods_login
-- Sistema central de autenticación y gestión de usuarios
-- Referenciada por todas las bases ods_01 … ods_17
--
-- Orden de ejecución recomendado:
--   1. login_system.sql       ← este archivo
--   2. ods_common.sql         ← tablas/vistas comunes (se SOURCE desde cada ODS)
--   3. ods_XX_database.sql    ← una por cada ODS
-- ============================================================

CREATE DATABASE IF NOT EXISTS ods_login CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods_login;

-- ────────────────────────────────────────────────────────────
-- TABLA: roles
-- Catálogo de roles del sistema
-- ────────────────────────────────────────────────────────────

CREATE TABLE roles (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(50) NOT NULL UNIQUE,
    descripcion VARCHAR(200),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Roles base del sistema
INSERT INTO roles (nombre, descripcion) VALUES
    ('admin',       'Administrador con acceso total al sistema'),
    ('gestor',      'Gestor de proyectos ODS: crea y edita sus propios proyectos'),
    ('consultor',   'Acceso de solo lectura a reportes y dashboards'),
    ('auditor',     'Acceso a registros de auditoría de todos los ODS');

-- ────────────────────────────────────────────────────────────
-- TABLA: usuarios
-- Tabla central referenciada por TODOS los ods_XX
-- ────────────────────────────────────────────────────────────

CREATE TABLE usuarios (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    username            VARCHAR(50)  NOT NULL UNIQUE,
    email               VARCHAR(150) NOT NULL UNIQUE,
    password_hash       VARCHAR(255) NOT NULL,          -- bcrypt / argon2
    full_name           VARCHAR(150) NOT NULL,
    rol_id              INT          NOT NULL DEFAULT 2, -- 'gestor' por defecto
    is_active           BOOLEAN      NOT NULL DEFAULT TRUE,
    email_verificado    BOOLEAN      NOT NULL DEFAULT FALSE,
    ultimo_login        TIMESTAMP    NULL,
    intentos_fallidos   TINYINT UNSIGNED NOT NULL DEFAULT 0,
    bloqueado_hasta     TIMESTAMP    NULL,              -- bloqueo temporal por intentos
    token_recuperacion  VARCHAR(100) NULL,
    token_expira        TIMESTAMP    NULL,
    created_at          TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    updated_at          TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (rol_id) REFERENCES roles(id) ON UPDATE CASCADE,
    INDEX idx_email      (email),
    INDEX idx_username   (username),
    INDEX idx_rol        (rol_id),
    INDEX idx_activo_rol (is_active, rol_id)
);

-- ────────────────────────────────────────────────────────────
-- TABLA: sesiones
-- Control de sesiones activas (JWT / token de sesión)
-- ────────────────────────────────────────────────────────────

CREATE TABLE sesiones (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id    INT          NOT NULL,
    token_hash    VARCHAR(255) NOT NULL UNIQUE,          -- hash del token de sesión
    ip_address    VARCHAR(45),
    user_agent    VARCHAR(300),
    created_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    expira_en     TIMESTAMP    NOT NULL,
    revocada      BOOLEAN      NOT NULL DEFAULT FALSE,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_token    (token_hash),
    INDEX idx_usuario  (usuario_id),
    INDEX idx_expira   (expira_en)
);

-- ────────────────────────────────────────────────────────────
-- TABLA: permisos_ods
-- Controla qué ODS puede gestionar cada usuario
-- (un gestor puede tener acceso a uno o varios ODS)
-- ────────────────────────────────────────────────────────────

CREATE TABLE permisos_ods (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id  INT     NOT NULL,
    ods_num     TINYINT UNSIGNED NOT NULL,               -- 1-17
    puede_crear  BOOLEAN NOT NULL DEFAULT TRUE,
    puede_editar BOOLEAN NOT NULL DEFAULT TRUE,
    puede_ver    BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uk_usuario_ods (usuario_id, ods_num),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_ods_num (ods_num),
    CONSTRAINT chk_ods_num CHECK (ods_num BETWEEN 1 AND 17)
);

-- ────────────────────────────────────────────────────────────
-- TABLA: auditoria_login
-- Registro de eventos de autenticación
-- ────────────────────────────────────────────────────────────

CREATE TABLE auditoria_login (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id  INT         NULL,                        -- NULL si usuario no existe
    email_intento VARCHAR(150),                          -- email usado en el intento
    evento      ENUM(
                    'LOGIN_OK',
                    'LOGIN_FALLIDO',
                    'LOGOUT',
                    'TOKEN_RECUPERACION',
                    'CAMBIO_PASSWORD',
                    'CUENTA_BLOQUEADA',
                    'SESION_EXPIRADA',
                    'REGISTRO'
                ) NOT NULL,
    ip_address  VARCHAR(45),
    user_agent  VARCHAR(300),
    detalle     VARCHAR(300),
    fecha_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE SET NULL,
    INDEX idx_usuario_evento (usuario_id, evento),
    INDEX idx_fecha          (fecha_evento),
    INDEX idx_ip             (ip_address)
);

-- ────────────────────────────────────────────────────────────
-- TRIGGERS: auditoría automática de cambios en usuarios
-- ────────────────────────────────────────────────────────────

DELIMITER //
CREATE TRIGGER trg_usuarios_update
AFTER UPDATE ON usuarios
FOR EACH ROW
BEGIN
    -- Registrar cambios relevantes de seguridad
    IF OLD.password_hash <> NEW.password_hash THEN
        INSERT INTO auditoria_login (usuario_id, email_intento, evento, detalle)
        VALUES (NEW.id, NEW.email, 'CAMBIO_PASSWORD', 'Contraseña actualizada');
    END IF;

    IF OLD.bloqueado_hasta IS NULL AND NEW.bloqueado_hasta IS NOT NULL THEN
        INSERT INTO auditoria_login (usuario_id, email_intento, evento, detalle)
        VALUES (NEW.id, NEW.email, 'CUENTA_BLOQUEADA',
                CONCAT('Bloqueada hasta: ', NEW.bloqueado_hasta));
    END IF;
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- PROCEDIMIENTO: sp_login
-- Valida credenciales y registra el intento
-- Devuelve: usuario completo + rol si éxito, NULL si fallo
-- ────────────────────────────────────────────────────────────

DELIMITER //
CREATE PROCEDURE sp_login(
    IN  p_email       VARCHAR(150),
    IN  p_password_hash VARCHAR(255),    -- hash ya calculado por la app
    IN  p_ip          VARCHAR(45),
    IN  p_user_agent  VARCHAR(300)
)
BEGIN
    DECLARE v_id               INT;
    DECLARE v_is_active        BOOLEAN;
    DECLARE v_bloqueado_hasta  TIMESTAMP;
    DECLARE v_intentos         TINYINT UNSIGNED;
    DECLARE v_stored_hash      VARCHAR(255);

    -- Buscar usuario
    SELECT id, is_active, bloqueado_hasta, intentos_fallidos, password_hash
    INTO   v_id, v_is_active, v_bloqueado_hasta, v_intentos, v_stored_hash
    FROM   usuarios
    WHERE  email = p_email
    LIMIT 1;

    -- Usuario no encontrado
    IF v_id IS NULL THEN
        INSERT INTO auditoria_login (usuario_id, email_intento, evento, ip_address, user_agent, detalle)
        VALUES (NULL, p_email, 'LOGIN_FALLIDO', p_ip, p_user_agent, 'Usuario no existe');
        SELECT NULL AS usuario_id, 'CREDENCIALES_INVALIDAS' AS resultado;

    -- Cuenta inactiva
    ELSEIF v_is_active = FALSE THEN
        INSERT INTO auditoria_login (usuario_id, email_intento, evento, ip_address, user_agent, detalle)
        VALUES (v_id, p_email, 'LOGIN_FALLIDO', p_ip, p_user_agent, 'Cuenta inactiva');
        SELECT NULL AS usuario_id, 'CUENTA_INACTIVA' AS resultado;

    -- Cuenta bloqueada temporalmente
    ELSEIF v_bloqueado_hasta IS NOT NULL AND v_bloqueado_hasta > NOW() THEN
        INSERT INTO auditoria_login (usuario_id, email_intento, evento, ip_address, user_agent, detalle)
        VALUES (v_id, p_email, 'LOGIN_FALLIDO', p_ip, p_user_agent,
                CONCAT('Bloqueada hasta ', v_bloqueado_hasta));
        SELECT NULL AS usuario_id, 'CUENTA_BLOQUEADA' AS resultado;

    -- Contraseña correcta
    ELSEIF v_stored_hash = p_password_hash THEN
        -- Resetear intentos fallidos y actualizar último login
        UPDATE usuarios
        SET    ultimo_login       = NOW(),
               intentos_fallidos  = 0,
               bloqueado_hasta    = NULL
        WHERE  id = v_id;

        INSERT INTO auditoria_login (usuario_id, email_intento, evento, ip_address, user_agent)
        VALUES (v_id, p_email, 'LOGIN_OK', p_ip, p_user_agent);

        -- Devolver datos completos del usuario
        SELECT u.id         AS usuario_id,
               'OK'         AS resultado,
               u.username,
               u.full_name,
               u.email,
               r.nombre     AS rol,
               u.ultimo_login
        FROM   usuarios u
        JOIN   roles r ON u.rol_id = r.id
        WHERE  u.id = v_id;

    -- Contraseña incorrecta
    ELSE
        -- Incrementar intentos; bloquear tras 5 fallos
        UPDATE usuarios
        SET    intentos_fallidos = intentos_fallidos + 1,
               bloqueado_hasta   = CASE
                                       WHEN intentos_fallidos + 1 >= 5
                                       THEN DATE_ADD(NOW(), INTERVAL 15 MINUTE)
                                       ELSE NULL
                                   END
        WHERE  id = v_id;

        INSERT INTO auditoria_login (usuario_id, email_intento, evento, ip_address, user_agent,
                                     detalle)
        VALUES (v_id, p_email, 'LOGIN_FALLIDO', p_ip, p_user_agent,
                CONCAT('Intento ', v_intentos + 1, ' de 5'));

        SELECT NULL AS usuario_id, 'CREDENCIALES_INVALIDAS' AS resultado;
    END IF;
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- PROCEDIMIENTO: sp_logout
-- Revoca la sesión activa del usuario
-- ────────────────────────────────────────────────────────────

DELIMITER //
CREATE PROCEDURE sp_logout(
    IN p_token_hash  VARCHAR(255),
    IN p_ip          VARCHAR(45)
)
BEGIN
    DECLARE v_usuario_id INT;

    SELECT usuario_id INTO v_usuario_id
    FROM   sesiones
    WHERE  token_hash = p_token_hash AND revocada = FALSE
    LIMIT 1;

    IF v_usuario_id IS NOT NULL THEN
        UPDATE sesiones SET revocada = TRUE WHERE token_hash = p_token_hash;

        INSERT INTO auditoria_login (usuario_id, evento, ip_address, detalle)
        SELECT v_usuario_id, 'LOGOUT', p_ip, 'Sesión cerrada'
        FROM   usuarios WHERE id = v_usuario_id;
    END IF;
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- PROCEDIMIENTO: sp_admin_usuarios
-- Dashboard de gestión de usuarios para el administrador
-- ────────────────────────────────────────────────────────────

DELIMITER //
CREATE PROCEDURE sp_admin_usuarios()
BEGIN
    -- Resumen general
    SELECT 'TOTAL_USUARIOS'   AS metrica, COUNT(*)                        AS valor FROM usuarios
    UNION ALL
    SELECT 'USUARIOS_ACTIVOS',            COUNT(*) FROM usuarios WHERE is_active = TRUE
    UNION ALL
    SELECT 'USUARIOS_BLOQUEADOS',         COUNT(*) FROM usuarios WHERE bloqueado_hasta > NOW()
    UNION ALL
    SELECT 'LOGINS_HOY',                  COUNT(*) FROM auditoria_login
        WHERE evento = 'LOGIN_OK' AND DATE(fecha_evento) = CURDATE()
    UNION ALL
    SELECT 'FALLOS_HOY',                  COUNT(*) FROM auditoria_login
        WHERE evento = 'LOGIN_FALLIDO' AND DATE(fecha_evento) = CURDATE();

    -- Listado completo de usuarios con su rol
    SELECT u.id, u.username, u.full_name, u.email,
           r.nombre     AS rol,
           u.is_active,
           u.ultimo_login,
           u.intentos_fallidos,
           u.bloqueado_hasta,
           u.created_at
    FROM   usuarios u
    JOIN   roles r ON u.rol_id = r.id
    ORDER  BY u.created_at DESC;

    -- Últimos 20 eventos de login
    SELECT a.fecha_evento, a.evento, u.username, a.email_intento,
           a.ip_address, a.detalle
    FROM   auditoria_login a
    LEFT   JOIN usuarios u ON a.usuario_id = u.id
    ORDER  BY a.fecha_evento DESC
    LIMIT  20;
END//
DELIMITER ;

-- ────────────────────────────────────────────────────────────
-- VISTA: vista_admin_usuarios_activos
-- Usuarios activos con su rol y ODS permitidos
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_usuarios_activos AS
SELECT
    u.id,
    u.username,
    u.full_name,
    u.email,
    r.nombre        AS rol,
    u.ultimo_login,
    u.created_at,
    GROUP_CONCAT(p.ods_num ORDER BY p.ods_num SEPARATOR ', ')
                    AS ods_permitidos
FROM  usuarios u
JOIN  roles r    ON u.rol_id = r.id
LEFT  JOIN permisos_ods p ON u.id = p.usuario_id
WHERE u.is_active = TRUE
GROUP BY u.id, u.username, u.full_name, u.email, r.nombre,
         u.ultimo_login, u.created_at
ORDER BY u.full_name;

-- ────────────────────────────────────────────────────────────
-- VISTA: vista_admin_auditoria_login_reciente
-- Eventos de autenticación de los últimos 30 días
-- ────────────────────────────────────────────────────────────

CREATE VIEW vista_admin_auditoria_login_reciente AS
SELECT
    a.id,
    a.fecha_evento,
    a.evento,
    u.username,
    u.full_name,
    a.email_intento,
    a.ip_address,
    a.user_agent,
    a.detalle
FROM  auditoria_login a
LEFT  JOIN usuarios u ON a.usuario_id = u.id
WHERE a.fecha_evento >= DATE_SUB(NOW(), INTERVAL 30 DAY)
ORDER BY a.fecha_evento DESC;

-- ────────────────────────────────────────────────────────────
-- ÍNDICES ADICIONALES
-- ────────────────────────────────────────────────────────────

CREATE INDEX idx_auditoria_login_fecha_evento ON auditoria_login(fecha_evento, evento);
CREATE INDEX idx_sesiones_expira_revocada      ON sesiones(expira_en, revocada);

-- ────────────────────────────────────────────────────────────
-- DATOS DE EJEMPLO: usuario administrador inicial
-- IMPORTANTE: cambiar la contraseña en producción
-- password_hash corresponde a bcrypt de 'Admin1234!'
-- LOGIN: admin@ods.local / Admin1234!
-- ────────────────────────────────────────────────────────────

INSERT INTO usuarios (username, email, password_hash, full_name, rol_id, is_active, email_verificado)
VALUES ('admin', 'admin@ods.local',
        '$2b$12$Mz3n8g34Ig8QllOrTDPKP.CiqYrhzBYy4l3JsJLmp1paGYZkPlBSy',
        'Administrador del Sistema', 1, TRUE, TRUE);

-- ────────────────────────────────────────────────────────────
-- COMENTARIOS DE TABLAS
-- ────────────────────────────────────────────────────────────

ALTER TABLE roles          COMMENT 'Catálogo de roles del sistema ODS';
ALTER TABLE usuarios       COMMENT 'Usuarios centrales; referenciados por todas las bases ods_XX';
ALTER TABLE sesiones       COMMENT 'Control de sesiones activas por token';
ALTER TABLE permisos_ods   COMMENT 'Qué ODS puede gestionar cada usuario';
ALTER TABLE auditoria_login COMMENT 'Registro de todos los eventos de autenticación';

SELECT 'Base de datos ods_login creada exitosamente' AS mensaje, NOW() AS fecha_creacion;
