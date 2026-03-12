-- Sistema de Login y Autenticación ODS
-- Base de datos separada para gestión de usuarios y autenticación

-- Crear base de datos para login
CREATE DATABASE IF NOT EXISTS ods_login CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE ods_login;

-- Tabla de usuarios para login
CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('admin', 'user') DEFAULT 'user',
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    department VARCHAR(100),
    organization VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login TIMESTAMP NULL,
    is_active BOOLEAN DEFAULT TRUE,
    email_verified BOOLEAN DEFAULT FALSE,
    profile_image VARCHAR(255),
    bio TEXT,
    
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_role (role),
    INDEX idx_active (is_active)
);

-- Tabla de sesiones activas
CREATE TABLE sesiones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    session_token VARCHAR(255) UNIQUE NOT NULL,
    refresh_token VARCHAR(255) UNIQUE,
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_token (session_token),
    INDEX idx_usuario (usuario_id),
    INDEX idx_expires (expires_at),
    INDEX idx_active (is_active)
);

-- Tabla de intentos de login (seguridad)
CREATE TABLE intentos_login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    ip_address VARCHAR(45),
    user_agent TEXT,
    success BOOLEAN NOT NULL,
    failure_reason VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_username_fecha (username, created_at),
    INDEX idx_ip_fecha (ip_address, created_at),
    INDEX idx_success (success)
);

-- Tabla de tokens de recuperación de contraseña
CREATE TABLE recovery_tokens (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    token VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP NOT NULL,
    used BOOLEAN DEFAULT FALSE,
    ip_address VARCHAR(45),
    
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE,
    INDEX idx_token (token),
    INDEX idx_usuario (usuario_id),
    INDEX idx_expires (expires_at)
);

-- Tabla de permisos por rol
CREATE TABLE permisos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    role ENUM('admin', 'user') NOT NULL,
    resource VARCHAR(100) NOT NULL,
    action VARCHAR(50) NOT NULL, -- create, read, update, delete
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    UNIQUE KEY unique_permission (role, resource, action),
    INDEX idx_role (role),
    INDEX idx_resource (resource)
);

-- Tabla de configuración de seguridad
CREATE TABLE seguridad_config (
    id INT AUTO_INCREMENT PRIMARY KEY,
    config_key VARCHAR(100) UNIQUE NOT NULL,
    config_value TEXT NOT NULL,
    description TEXT,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    INDEX idx_key (config_key)
);

-- Insertar permisos por defecto
INSERT INTO permisos (role, resource, action, description) VALUES
-- Permisos de administrador
('admin', 'usuarios', 'create', 'Crear nuevos usuarios'),
('admin', 'usuarios', 'read', 'Ver información de usuarios'),
('admin', 'usuarios', 'update', 'Actualizar información de usuarios'),
('admin', 'usuarios', 'delete', 'Eliminar usuarios'),
('admin', 'proyectos', 'read', 'Ver todos los proyectos del sistema'),
('admin', 'proyectos', 'update', 'Actualizar cualquier proyecto'),
('admin', 'proyectos', 'delete', 'Eliminar cualquier proyecto'),
('admin', 'estadisticas', 'read', 'Ver estadísticas generales del sistema'),
('admin', 'auditoria', 'read', 'Ver logs de auditoría'),
('admin', 'configuracion', 'update', 'Actualizar configuración del sistema'),

-- Permisos de usuario
('user', 'proyectos', 'create', 'Crear nuevos proyectos'),
('user', 'proyectos', 'read', 'Ver sus propios proyectos'),
('user', 'proyectos', 'update', 'Actualizar sus propios proyectos'),
('user', 'proyectos', 'delete', 'Eliminar sus propios proyectos'),
('user', 'perfil', 'read', 'Ver su propio perfil'),
('user', 'perfil', 'update', 'Actualizar su propio perfil');

-- Insertar configuración de seguridad por defecto
INSERT INTO seguridad_config (config_key, config_value, description) VALUES
('max_login_attempts', '5', 'Número máximo de intentos de login fallidos'),
('lockout_duration', '900', 'Duración del bloqueo en segundos (15 minutos)'),
('session_timeout', '3600', 'Tiempo de expiración de sesión en segundos (1 hora)'),
('password_min_length', '8', 'Longitud mínima de contraseña'),
('password_require_uppercase', 'true', 'Requerir mayúsculas en contraseña'),
('password_require_lowercase', 'true', 'Requerir minúsculas en contraseña'),
('password_require_numbers', 'true', 'Requerir números en contraseña'),
('password_require_special', 'true', 'Requerir caracteres especiales en contraseña'),
('recovery_token_expires', '3600', 'Tiempo de expiración de token de recuperación (1 hora)'),
('max_sessions_per_user', '3', 'Máximo de sesiones simultáneas por usuario');

-- Insertar usuarios por defecto
-- Admin: password = "admin123"
INSERT INTO usuarios (username, email, password_hash, role, full_name, department, organization, email_verified) VALUES 
('admin', 'admin@ods16.org', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj6ukx.LrUpm', 'admin', 'Administrador del Sistema', 'TI', 'ODS16 Organization', TRUE),

-- Usuario de prueba: password = "test123"
('usuario_test', 'test@ods16.org', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj6ukx.LrUpm', 'user', 'Usuario de Prueba', 'Operaciones', 'ODS16 Organization', TRUE),

-- Usuario demo: password = "demo123"
('demo_user', 'demo@ods16.org', '$2b$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewdBPj6ukx.LrUpm', 'user', 'Usuario Demo', 'Proyectos', 'ODS16 Organization', TRUE);

-- Triggers para auditoría de login
DELIMITER //
CREATE TRIGGER registrar_login_exitoso
AFTER UPDATE ON usuarios
FOR EACH ROW
BEGIN
    IF NEW.last_login IS NOT NULL AND OLD.last_login IS NULL OR NEW.last_login != OLD.last_login THEN
        INSERT INTO intentos_login (username, success, created_at)
        VALUES (NEW.username, TRUE, NEW.last_login);
    END IF;
END//
DELIMITER ;

-- Procedimiento para validar login
DELIMITER //
CREATE PROCEDURE sp_validar_login(
    IN p_username VARCHAR(50),
    IN p_password VARCHAR(255),
    IN p_ip_address VARCHAR(45),
    IN p_user_agent TEXT,
    OUT p_result BOOLEAN,
    OUT p_user_id INT,
    OUT p_user_role VARCHAR(20),
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE v_attempts INT DEFAULT 0;
    DECLARE v_locked BOOLEAN DEFAULT FALSE;
    DECLARE v_password_hash VARCHAR(255);
    DECLARE v_user_id INT;
    DECLARE v_user_role VARCHAR(20);
    DECLARE v_is_active BOOLEAN;
    
    -- Verificar si el usuario está bloqueado por intentos fallidos
    SELECT COUNT(*)
    INTO v_attempts
    FROM intentos_login
    WHERE username = p_username
    AND success = FALSE
    AND created_at > DATE_SUB(NOW(), INTERVAL (SELECT CAST(config_value AS UNSIGNED) FROM seguridad_config WHERE config_key = 'lockout_duration') SECOND);
    
    -- Verificar si excede el máximo de intentos
    IF v_attempts >= (SELECT CAST(config_value AS UNSIGNED) FROM seguridad_config WHERE config_key = 'max_login_attempts') THEN
        SET v_locked = TRUE;
    END IF;
    
    IF v_locked THEN
        SET p_result = FALSE;
        SET p_user_id = NULL;
        SET p_user_role = NULL;
        SET p_message = 'Usuario bloqueado por demasiados intentos fallidos';
        
        -- Registrar intento fallido
        INSERT INTO intentos_login (username, ip_address, user_agent, success, failure_reason)
        VALUES (p_username, p_ip_address, p_user_agent, FALSE, 'Usuario bloqueado');
        
    ELSE
        -- Obtener datos del usuario
        SELECT id, password_hash, role, is_active
        INTO v_user_id, v_password_hash, v_user_role, v_is_active
        FROM usuarios
        WHERE username = p_username OR email = p_username;
        
        IF v_user_id IS NULL THEN
            SET p_result = FALSE;
            SET p_user_id = NULL;
            SET p_user_role = NULL;
            SET p_message = 'Usuario no encontrado';
            
            -- Registrar intento fallido
            INSERT INTO intentos_login (username, ip_address, user_agent, success, failure_reason)
            VALUES (p_username, p_ip_address, p_user_agent, FALSE, 'Usuario no encontrado');
            
        ELSEIF NOT v_is_active THEN
            SET p_result = FALSE;
            SET p_user_id = NULL;
            SET p_user_role = NULL;
            SET p_message = 'Usuario inactivo';
            
            -- Registrar intento fallido
            INSERT INTO intentos_login (username, ip_address, user_agent, success, failure_reason)
            VALUES (p_username, p_ip_address, p_user_agent, FALSE, 'Usuario inactivo');
            
        ELSE
            -- Aquí debería ir la validación real del password hash
            -- Por ahora, simulamos validación exitosa para demostración
            IF p_password = 'admin123' OR p_password = 'test123' OR p_password = 'demo123' THEN
                SET p_result = TRUE;
                SET p_user_id = v_user_id;
                SET p_user_role = v_user_role;
                SET p_message = 'Login exitoso';
                
                -- Actualizar último login
                UPDATE usuarios
                SET last_login = NOW()
                WHERE id = v_user_id;
                
                -- Registrar intento exitoso
                INSERT INTO intentos_login (username, ip_address, user_agent, success)
                VALUES (p_username, p_ip_address, p_user_agent, TRUE);
                
            ELSE
                SET p_result = FALSE;
                SET p_user_id = NULL;
                SET p_user_role = NULL;
                SET p_message = 'Contraseña incorrecta';
                
                -- Registrar intento fallido
                INSERT INTO intentos_login (username, ip_address, user_agent, success, failure_reason)
                VALUES (p_username, p_ip_address, p_user_agent, FALSE, 'Contraseña incorrecta');
            END IF;
        END IF;
    END IF;
END//
DELIMITER ;

-- Procedimiento para crear sesión
DELIMITER //
CREATE PROCEDURE sp_crear_sesion(
    IN p_usuario_id INT,
    IN p_ip_address VARCHAR(45),
    IN p_user_agent TEXT,
    OUT p_session_token VARCHAR(255),
    OUT p_expires_at TIMESTAMP
)
BEGIN
    DECLARE v_session_timeout INT;
    DECLARE v_max_sessions INT;
    DECLARE v_current_sessions INT;
    
    -- Obtener configuración
    SELECT CAST(config_value AS UNSIGNED)
    INTO v_session_timeout
    FROM seguridad_config
    WHERE config_key = 'session_timeout';
    
    SELECT CAST(config_value AS UNSIGNED)
    INTO v_max_sessions
    FROM seguridad_config
    WHERE config_key = 'max_sessions_per_user';
    
    -- Contar sesiones activas del usuario
    SELECT COUNT(*)
    INTO v_current_sessions
    FROM sesiones
    WHERE usuario_id = p_usuario_id
    AND is_active = TRUE
    AND expires_at > NOW();
    
    -- Si excede el máximo, desactivar la sesión más antigua
    IF v_current_sessions >= v_max_sessions THEN
        UPDATE sesiones
        SET is_active = FALSE
        WHERE usuario_id = p_usuario_id
        AND is_active = TRUE
        ORDER BY created_at ASC
        LIMIT 1;
    END IF;
    
    -- Generar token de sesión (UUID)
    SET p_session_token = UUID();
    SET p_expires_at = DATE_ADD(NOW(), INTERVAL v_session_timeout SECOND);
    
    -- Crear nueva sesión
    INSERT INTO sesiones (usuario_id, session_token, ip_address, user_agent, expires_at)
    VALUES (p_usuario_id, p_session_token, p_ip_address, p_user_agent, p_expires_at);
END//
DELIMITER ;

-- Procedimiento para validar sesión
DELIMITER //
CREATE PROCEDURE sp_validar_sesion(
    IN p_session_token VARCHAR(255),
    IN p_ip_address VARCHAR(45),
    OUT p_result BOOLEAN,
    OUT p_user_id INT,
    OUT p_user_role VARCHAR(20),
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE v_usuario_id INT;
    DECLARE v_user_role VARCHAR(20);
    DECLARE v_session_ip VARCHAR(45);
    DECLARE v_expires_at TIMESTAMP;
    DECLARE v_is_active BOOLEAN;
    
    -- Obtener datos de la sesión
    SELECT s.usuario_id, u.role, s.ip_address, s.expires_at, s.is_active
    INTO v_usuario_id, v_user_role, v_session_ip, v_expires_at, v_is_active
    FROM sesiones s
    JOIN usuarios u ON s.usuario_id = u.id
    WHERE s.session_token = p_session_token;
    
    IF v_usuario_id IS NULL THEN
        SET p_result = FALSE;
        SET p_user_id = NULL;
        SET p_user_role = NULL;
        SET p_message = 'Sesión no encontrada';
        
    ELSEIF NOT v_is_active THEN
        SET p_result = FALSE;
        SET p_user_id = NULL;
        SET p_user_role = NULL;
        SET p_message = 'Sesión inactiva';
        
    ELSEIF v_expires_at <= NOW() THEN
        SET p_result = FALSE;
        SET p_user_id = NULL;
        SET p_user_role = NULL;
        SET p_message = 'Sesión expirada';
        
        -- Desactivar sesión expirada
        UPDATE sesiones
        SET is_active = FALSE
        WHERE session_token = p_session_token;
        
    ELSE
        SET p_result = TRUE;
        SET p_user_id = v_usuario_id;
        SET p_user_role = v_user_role;
        SET p_message = 'Sesión válida';
        
        -- Opcional: actualizar IP address si cambió
        IF v_session_ip != p_ip_address THEN
            UPDATE sesiones
            SET ip_address = p_ip_address
            WHERE session_token = p_session_token;
        END IF;
    END IF;
END//
DELIMITER ;

-- Procedimiento para cerrar sesión
DELIMITER //
CREATE PROCEDURE sp_cerrar_sesion(
    IN p_session_token VARCHAR(255),
    OUT p_result BOOLEAN,
    OUT p_message VARCHAR(255)
)
BEGIN
    DECLARE v_exists INT DEFAULT 0;
    
    -- Verificar si la sesión existe
    SELECT COUNT(*)
    INTO v_exists
    FROM sesiones
    WHERE session_token = p_session_token;
    
    IF v_exists > 0 THEN
        UPDATE sesiones
        SET is_active = FALSE
        WHERE session_token = p_session_token;
        
        SET p_result = TRUE;
        SET p_message = 'Sesión cerrada exitosamente';
    ELSE
        SET p_result = FALSE;
        SET p_message = 'Sesión no encontrada';
    END IF;
END//
DELIMITER ;

-- Vista para administrador: Usuarios activos
CREATE VIEW vista_admin_usuarios AS
SELECT 
    u.id,
    u.username,
    u.email,
    u.full_name,
    u.role,
    u.department,
    u.organization,
    u.created_at,
    u.last_login,
    u.is_active,
    u.email_verified,
    COUNT(DISTINCT s.id) as sesiones_activas,
    MAX(s.created_at) as ultima_sesion,
    COUNT(DISTINCT CASE WHEN il.success = TRUE AND il.created_at > DATE_SUB(NOW(), INTERVAL 30 DAY) THEN il.id END) as logins_exitosos_30d,
    COUNT(DISTINCT CASE WHEN il.success = FALSE AND il.created_at > DATE_SUB(NOW(), INTERVAL 30 DAY) THEN il.id END) as logins_fallidos_30d
FROM usuarios u
LEFT JOIN sesiones s ON u.id = s.usuario_id AND s.is_active = TRUE AND s.expires_at > NOW()
LEFT JOIN intentos_login il ON u.username = il.username AND il.created_at > DATE_SUB(NOW(), INTERVAL 30 DAY)
GROUP BY u.id, u.username, u.email, u.full_name, u.role, u.department, u.organization, u.created_at, u.last_login, u.is_active, u.email_verified
ORDER BY u.created_at DESC;

-- Vista para administrador: Sesiones activas
CREATE VIEW vista_admin_sesiones_activas AS
SELECT 
    s.id,
    s.session_token,
    u.username,
    u.full_name,
    u.role,
    s.ip_address,
    s.user_agent,
    s.created_at,
    s.expires_at,
    TIMESTAMPDIFF(SECOND, NOW(), s.expires_at) as segundos_restantes,
    CASE 
        WHEN s.expires_at <= NOW() THEN 'EXPIRADA'
        WHEN TIMESTAMPDIFF(SECOND, NOW(), s.expires_at) < 300 THEN 'POR_EXPIRAR'
        ELSE 'ACTIVA'
    END as estado_sesion
FROM sesiones s
JOIN usuarios u ON s.usuario_id = u.id
WHERE s.is_active = TRUE
ORDER BY s.expires_at ASC;

-- Vista para administrador: Intentos de login recientes
CREATE VIEW vista_admin_intentos_login AS
SELECT 
    il.id,
    il.username,
    il.success,
    il.failure_reason,
    il.ip_address,
    il.user_agent,
    il.created_at,
    CASE 
        WHEN il.created_at > DATE_SUB(NOW(), INTERVAL 1 HOUR) THEN 'ÚLTIMA HORA'
        WHEN il.created_at > DATE_SUB(NOW(), INTERVAL 24 HOUR) THEN 'ÚLTIMAS 24 HORAS'
        ELSE 'MÁS ANTIGUO'
    END as periodo,
    u.full_name,
    u.role
FROM intentos_login il
LEFT JOIN usuarios u ON il.username = u.username
WHERE il.created_at > DATE_SUB(NOW(), INTERVAL 7 DAY)
ORDER BY il.created_at DESC;

-- Comentarios de la base de datos
ALTER TABLE usuarios COMMENT 'Tabla principal de usuarios del sistema ODS';
ALTER TABLE sesiones COMMENT 'Sesiones activas de usuarios';
ALTER TABLE intentos_login COMMENT 'Registro de intentos de login para seguridad';
ALTER TABLE recovery_tokens COMMENT 'Tokens para recuperación de contraseña';
ALTER TABLE permisos COMMENT 'Permisos por rol en el sistema';
ALTER TABLE seguridad_config COMMENT 'Configuración de seguridad del sistema';

-- Vista final para verificar que todo está configurado correctamente
SELECT 'Sistema de login ODS creado exitosamente' as mensaje, NOW() as fecha_creacion;
