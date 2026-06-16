package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Roles;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sesiones;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.AuditoriaLogin;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.PermisosOds;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminAuditoriaLoginReciente;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminUsuariosActivos;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sedes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Sistema de Login
 * Define los contratos de negocio para autenticación, gestión de usuarios y sesión
 * Implementa la lógica de negocio y reglas del sistema de login
 */
public interface ILoginService {

    // ── Autenticación ──
    
    /**
     * Autentica un usuario con email y contraseña
     * 
     * @param email Email del usuario
     * @param password Contraseña en texto plano
     * @param ip Dirección IP del cliente
     * @param userAgent User agent del navegador
     * @return Optional con el usuario autenticado y token de sesión
     */
    Optional<Map<String, Object>> authenticate(String email, String password, String ip, String userAgent);
    
    /**
     * Perfil SODSI enriquecido del usuario (sede, área, dependencia, rol, contacto).
     */
    Map<String, Object> buildUsuarioAuthProfile(Usuarios usuario);
    
    /**
     * Cierra la sesión de un usuario
     * 
     * @param token Token de sesión a revocar
     * @return true si se cerró correctamente, false otherwise
     */
    Boolean logout(String token);
    
    /**
     * Verifica si un token de sesión es válido
     * 
     * @param token Token de sesión
     * @return Optional con el usuario si el token es válido
     */
    Optional<Usuarios> validateToken(String token);
    
    /**
     * Refresca un token de sesión existente
     * 
     * @param tokenActual Token actual
     * @return Nuevo token de sesión
     */
    Optional<String> refreshToken(String tokenActual);
    
    // ── Gestión de Usuarios ──
    
    /**
     * Registra un nuevo usuario en el sistema
     * 
     * @param usuario Datos del nuevo usuario
     * @param password Contraseña en texto plano
     * @return Usuario registrado con ID asignado
     */
    Usuarios registerUser(Usuarios usuario, String password);
    
    /**
     * Obtiene un usuario por su ID
     * 
     * @param id ID del usuario
     * @return Optional con el usuario encontrado
     */
    Optional<Usuarios> getUsuarioById(Integer id);
    
    /**
     * Obtiene un usuario por su nombre de usuario
     * 
     * @param username Nombre de usuario
     * @return Optional con el usuario encontrado
     */
    Optional<Usuarios> getUsuarioByUsername(String username);
    
    /**
     * Obtiene un usuario por su email
     * 
     * @param email Email del usuario
     * @return Optional con el usuario encontrado
     */
    Optional<Usuarios> getUsuarioByEmail(String email);
    
    /**
     * Actualiza los datos de un usuario
     * 
     * @param usuario Datos actualizados del usuario
     * @return Usuario actualizado
     */
    Usuarios updateUsuario(Usuarios usuario);

    /**
     * Lista usuarios para panel admin (join roles + sedes, sin password)
     *
     * @return Lista de registros sanitizados
     */
    List<Map<String, Object>> getAllUsuariosAdmin();

    /**
     * Crea un usuario admin con contraseña hasheada
     *
     * @param usuario Datos del usuario
     * @param password Contraseña en texto plano
     * @return Usuario creado
     */
    Usuarios createUser(Usuarios usuario, String password);

    /**
     * Actualiza un usuario admin; password opcional
     *
     * @param usuario Datos actualizados
     * @param password Nueva contraseña (null/blank = no cambiar)
     * @return Usuario actualizado
     */
    Usuarios updateUser(Usuarios usuario, String password);

    /**
     * Desactiva un usuario (is_active = 0)
     *
     * @param id ID del usuario
     * @return Usuario desactivado
     */
    Usuarios deactivateUser(Integer id);
    
    /**
     * Cambia la contraseña de un usuario
     * 
     * @param usuarioId ID del usuario
     * @param passwordActual Contraseña actual
     * @param passwordNueva Nueva contraseña
     * @return true si se cambió correctamente, false otherwise
     */
    Boolean changePassword(Integer usuarioId, String passwordActual, String passwordNueva);
    
    /**
     * Inicia el proceso de recuperación de contraseña
     * 
     * @param email Email del usuario
     * @return true si se envió el email de recuperación, false otherwise
     */
    Boolean initiatePasswordRecovery(String email);
    
    /**
     * Restablece la contraseña con token de recuperación
     * 
     * @param token Token de recuperación
     * @param passwordNueva Nueva contraseña
     * @return true si se restableció correctamente, false otherwise
     */
    Boolean resetPassword(String token, String passwordNueva);
    
    /**
     * Verifica si un token de recuperación es válido
     * 
     * @param token Token de recuperación
     * @return Optional con el usuario si el token es válido
     */
    Optional<Usuarios> validateRecoveryToken(String token);
    
    // ── Gestión de Roles ──
    
    /**
     * Obtiene todos los roles del sistema
     * 
     * @return Lista de roles disponibles
     */
    List<Roles> getAllRoles();
    
    /**
     * Obtiene un rol por su ID
     * 
     * @param id ID del rol
     * @return Optional con el rol encontrado
     */
    Optional<Roles> getRolById(Integer id);
    
    /**
     * Asigna un rol a un usuario
     * 
     * @param usuarioId ID del usuario
     * @param rolId ID del rol
     * @return true si se asignó correctamente, false otherwise
     */
    Boolean assignRolToUsuario(Integer usuarioId, Integer rolId);
    
    // ── Sedes ──
    
    /**
     * Obtiene todas las sedes del sistema
     * 
     * @return Lista de sedes disponibles
     */
    List<Sedes> getAllSedes();
    
    /**
     * Obtiene una sede por su ID
     * 
     * @param id ID de la sede
     * @return Optional con la sede encontrada
     */
    Optional<Sedes> getSedeById(Integer id);
    
    // ── Gestión de Sesiones ──
    
    /**
     * Obtiene las sesiones activas de un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Lista de sesiones activas
     */
    List<Sesiones> getActiveSessions(Integer usuarioId);
    
    /**
     * Revoca todas las sesiones de un usuario excepto la actual
     * 
     * @param usuarioId ID del usuario
     * @param currentToken Token de la sesión actual
     * @return Número de sesiones revocadas
     */
    Integer revokeOtherSessions(Integer usuarioId, String currentToken);
    
    /**
     * Revoca todas las sesiones de un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Número de sesiones revocadas
     */
    Integer revokeAllSessions(Integer usuarioId);
    
    // ── Permisos ODS ──
    
    /**
     * Obtiene los permisos ODS de un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Lista de permisos ODS del usuario
     */
    List<PermisosOds> getPermisosByUsuario(Integer usuarioId);
    
    /**
     * Verifica si un usuario tiene acceso a un ODS específico
     * 
     * @param usuarioId ID del usuario
     * @param odsId ID del ODS
     * @return true si tiene permiso, false otherwise
     */
    Boolean hasPermisoOds(Integer usuarioId, Integer odsId);
    
    /**
     * Otorga permiso de ODS a un usuario
     * 
     * @param usuarioId ID del usuario
     * @param odsId ID del ODS
     * @return true si se otorgó correctamente, false otherwise
     */
    Boolean grantPermisoOds(Integer usuarioId, Integer odsId);
    
    /**
     * Revoca permiso de ODS a un usuario
     * 
     * @param usuarioId ID del usuario
     * @param odsId ID del ODS
     * @return true si se revocó correctamente, false otherwise
     */
    Boolean revokePermisoOds(Integer usuarioId, Integer odsId);
    
    // ── Auditoría y Seguridad ──
    
    /**
     * Obtiene el historial de login de un usuario
     * 
     * @param usuarioId ID del usuario
     * @param dias Número de días hacia atrás
     * @return Lista de registros de auditoría
     */
    List<AuditoriaLogin> getLoginHistory(Integer usuarioId, Integer dias);
    
    /**
     * Obtiene intentos de login fallidos recientes
     * 
     * @param horas Número de horas hacia atrás
     * @return Lista de intentos fallidos
     */
    List<AuditoriaLogin> getFailedLoginAttempts(Integer horas);
    
    /**
     * Bloquea un usuario por seguridad
     * 
     * @param usuarioId ID del usuario
     * @param motivo Motivo del bloqueo
     * @param horas Número de horas de bloqueo
     * @return true si se bloqueó correctamente, false otherwise
     */
    Boolean blockUsuario(Integer usuarioId, String motivo, Integer horas);
    
    /**
     * Desbloquea un usuario
     * 
     * @param usuarioId ID del usuario
     * @return true si se desbloqueó correctamente, false otherwise
     */
    Boolean unblockUsuario(Integer usuarioId);
    
    // ── Administración ──
    
    /**
     * Obtiene la vista de auditoría de login reciente
     * 
     * @param dias Número de días a consultar
     * @return Lista de auditorías recientes
     */
    List<VistaAdminAuditoriaLoginReciente> getVistaAuditoriaReciente(Integer dias);
    
    /**
     * Obtiene el resumen general administrativo
     * 
     * @return Lista con datos resumidos del sistema
     */
    List<VistaAdminResumenGeneral> getVistaResumenGeneral();
    
    /**
     * Obtiene usuarios activos para vista administrativa
     * 
     * @return Lista de usuarios activos con detalles
     */
    List<VistaAdminUsuariosActivos> getVistaUsuariosActivos();
    
    /**
     * Obtiene detalles de indicadores para vista administrativa
     * 
     * @param proyectoId ID del proyecto (opcional)
     * @return Lista de detalles de indicadores
     */
    List<VistaAdminDetalleIndicadores> getVistaDetalleIndicadores(Integer proyectoId);
    
    /**
     * Obtiene estadísticas del sistema de login
     * 
     * @return Map con estadísticas generales
     */
    Map<String, Object> getEstadisticasSistema();
    
    /**
     * Ejecuta operaciones administrativas sobre usuarios
     * 
     * @param accion Acción a ejecutar (CREATE, UPDATE, DELETE, ACTIVATE, DEACTIVATE)
     * @param usuarioId ID del usuario (para UPDATE, DELETE)
     * @param username Nombre de usuario (para CREATE, UPDATE)
     * @param email Email del usuario (para CREATE, UPDATE)
     * @param rolId ID del rol (para CREATE, UPDATE)
     * @return Map con el resultado de la operación
     */
    Map<String, Object> adminUsuario(String accion, Integer usuarioId, String username, String email, Integer rolId);
    
    // ── Utilidades ──
    
    /**
     * Verifica si un email ya está registrado
     * 
     * @param email Email a verificar
     * @return true si existe, false otherwise
     */
    Boolean emailExists(String email);
    
    /**
     * Verifica si un username ya está registrado
     * 
     * @param username Username a verificar
     * @return true si existe, false otherwise
     */
    Boolean usernameExists(String username);
    
    /**
     * Valida el formato de una contraseña según políticas de seguridad
     * 
     * @param password Contraseña a validar
     * @return true si es válida, false otherwise
     */
    Boolean validatePasswordFormat(String password);
    
    /**
     * Genera un token seguro para recuperación de contraseña
     * 
     * @return Token generado
     */
    String generateSecureToken();
    
    /**
     * Limpia sesiones expiradas y datos temporales
     * 
     * @return Map con resultados de la limpieza
     */
    Map<String, Object> cleanupExpiredData();
    
    /**
     * Verifica el estado de salud del servicio
     * 
     * @return Map con información del estado del servicio
     */
    Map<String, String> healthCheck();
}
