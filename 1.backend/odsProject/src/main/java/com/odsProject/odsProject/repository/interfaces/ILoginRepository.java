package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Roles;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sesiones;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.AuditoriaLogin;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.PermisosOds;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminAuditoriaLoginReciente;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminUsuariosActivos;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sedes;
import com.odsProject.odsProject.database.jooq.ods_login.routines.SpLogin;
import com.odsProject.odsProject.database.jooq.ods_login.routines.SpLogout;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Repository para el Sistema de Login
 * Define los contratos de acceso a datos para autenticación, usuarios y sesión
 * Implementa operaciones CRUD y consultas específicas del sistema de login
 */
public interface ILoginRepository {

    // ── Usuarios ──
    
    /**
     * Busca un usuario por su ID
     * 
     * @param id ID del usuario
     * @return Optional con el usuario encontrado
     */
    Optional<Usuarios> findUsuarioById(Integer id);
    
    /**
     * Busca un usuario por su nombre de usuario
     * 
     * @param username Nombre de usuario único
     * @return Optional con el usuario encontrado
     */
    Optional<Usuarios> findUsuarioByUsername(String username);
    
    /**
     * Busca un usuario por su email
     * 
     * @param email Email del usuario
     * @return Optional con el usuario encontrado
     */
    Optional<Usuarios> findUsuarioByEmail(String email);
    
    /**
     * Obtiene todos los usuarios activos del sistema
     * 
     * @return Lista de usuarios activos
     */
    List<Usuarios> findUsuariosActivos();
    
    /**
     * Guarda un nuevo usuario en el sistema
     * 
     * @param usuario Usuario a guardar
     * @return Usuario guardado con ID asignado
     */
    Usuarios saveUsuario(Usuarios usuario);
    
    /**
     * Actualiza la fecha de último login de un usuario
     * 
     * @param usuarioId ID del usuario
     */
    void updateUltimoLogin(Integer usuarioId);
    
    /**
     * Incrementa el contador de intentos fallidos de login
     * 
     * @param usuarioId ID del usuario
     */
    void incrementarIntentosFallidos(Integer usuarioId);
    
    /**
     * Bloquea un usuario hasta una fecha específica
     * 
     * @param usuarioId ID del usuario
     * @param hasta Fecha hasta la cual estará bloqueado
     */
    void bloquearUsuario(Integer usuarioId, LocalDateTime hasta);
    
    /**
     * Desbloquea un usuario
     * 
     * @param usuarioId ID del usuario
     */
    void desbloquearUsuario(Integer usuarioId);
    
    /**
     * Actualiza el token de recuperación de contraseña
     * 
     * @param usuarioId ID del usuario
     * @param token Token de recuperación
     * @param expira Fecha de expiración del token
     */
    void actualizarTokenRecuperacion(Integer usuarioId, String token, LocalDateTime expira);
    
    // ── Roles ──
    
    /**
     * Obtiene todos los roles del sistema
     * 
     * @return Lista de roles disponibles
     */
    List<Roles> findAllRoles();
    
    /**
     * Busca un rol por su ID
     * 
     * @param id ID del rol
     * @return Optional con el rol encontrado
     */
    Optional<Roles> findRolById(Integer id);
    
    /**
     * Busca un rol por su nombre
     * 
     * @param nombre Nombre del rol
     * @return Optional con el rol encontrado
     */
    Optional<Roles> findRolByNombre(String nombre);
    
    // ── Sedes ──
    
    /**
     * Obtiene todas las sedes del sistema
     * 
     * @return Lista de sedes disponibles
     */
    List<Sedes> findAllSedes();
    
    /**
     * Busca una sede por su ID
     * 
     * @param id ID de la sede
     * @return Optional con la sede encontrada
     */
    Optional<Sedes> findSedeById(Integer id);
    
    // ── Sesiones ──
    
    /**
     * Busca una sesión activa por su token hash
     * 
     * @param tokenHash Hash del token de sesión
     * @return Optional con la sesión encontrada
     */
    Optional<Sesiones> findSesionByToken(String tokenHash);
    
    /**
     * Guarda una nueva sesión de usuario
     * 
     * @param sesion Sesión a guardar
     * @return Sesión guardada con ID asignado
     */
    Sesiones saveSesion(Sesiones sesion);
    
    /**
     * Revoca una sesión específica
     * 
     * @param tokenHash Hash del token a revocar
     */
    void revocarSesion(String tokenHash);
    
    /**
     * Revoca todas las sesiones de un usuario
     * 
     * @param usuarioId ID del usuario
     */
    void revocarSesionesByUsuario(Integer usuarioId);
    
    /**
     * Limpia sesiones expiradas
     * 
     * @return Número de sesiones eliminadas
     */
    Integer limpiarSesionesExpiradas();
    
    /**
     * Obtiene las sesiones activas de un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Lista de sesiones activas
     */
    List<Sesiones> findSesionesByUsuario(Integer usuarioId);
    
    // ── Permisos ODS ──
    
    /**
     * Obtiene los permisos ODS de un usuario
     * 
     * @param usuarioId ID del usuario
     * @return Lista de permisos ODS del usuario
     */
    List<PermisosOds> findPermisosByUsuario(Integer usuarioId);
    
    /**
     * Verifica si un usuario tiene acceso a un ODS específico
     * 
     * @param usuarioId ID del usuario
     * @param odsId ID del ODS
     * @return true si tiene permiso, false otherwise
     */
    Boolean hasPermisoOds(Integer usuarioId, Integer odsId);
    
    // ── Auditoría Login ──
    
    /**
     * Registra un intento de login en la auditoría
     * 
     * @param auditoriaLogin Registro de auditoría
     * @return Registro guardado
     */
    AuditoriaLogin saveAuditoriaLogin(AuditoriaLogin auditoriaLogin);
    
    /**
     * Obtiene el historial de login reciente de un usuario
     * 
     * @param usuarioId ID del usuario
     * @param dias Número de días hacia atrás
     * @return Lista de registros de auditoría
     */
    List<AuditoriaLogin> findAuditoriaByUsuario(Integer usuarioId, Integer dias);
    
    /**
     * Obtiene intentos de login fallidos recientes
     * 
     * @param horas Número de horas hacia atrás
     * @return Lista de intentos fallidos
     */
    List<AuditoriaLogin> findIntentosFallidosRecientes(Integer horas);
    
    // ─── Vistas Administrativas ───
    
    /**
     * Obtiene la vista de auditoría de login reciente
     * 
     * @param dias Número de días a consultar
     * @return Lista de auditorías recientes
     */
    List<VistaAdminAuditoriaLoginReciente> findVistaAuditoriaReciente(Integer dias);
    
    /**
     * Obtiene el resumen general administrativo
     * 
     * @return Lista con datos resumidos del sistema
     */
    List<VistaAdminResumenGeneral> findVistaResumenGeneral();
    
    /**
     * Obtiene usuarios activos para vista administrativa
     * 
     * @return Lista de usuarios activos con detalles
     */
    List<VistaAdminUsuariosActivos> findVistaUsuariosActivos();
    
    /**
     * Obtiene detalles de indicadores para vista administrativa
     * 
     * @param proyectoId ID del proyecto (opcional)
     * @return Lista de detalles de indicadores
     */
    List<VistaAdminDetalleIndicadores> findVistaDetalleIndicadores(Integer proyectoId);
    
    // ─── Stored Procedures ───
    
    /**
     * Ejecuta el stored procedure de login
     * 
     * @param email Email del usuario
     * @param passwordHash Hash del password
     * @param ip Dirección IP del cliente
     * @param userAgent User agent del navegador
     * @return Resultado de la ejecución del SP
     */
    SpLogin executeSpLogin(String email, String passwordHash, String ip, String userAgent);
    
    /**
     * Ejecuta el stored procedure de logout
     * 
     * @param tokenHash Hash del token de sesión
     * @return Resultado de la ejecución del SP
     */
    SpLogout executeSpLogout(String tokenHash);
    
    /**
     * Ejecuta el stored procedure de administración de usuarios
     * 
     * @param accion Acción a ejecutar (CREATE, UPDATE, DELETE, ACTIVATE, DEACTIVATE)
     * @param usuarioId ID del usuario (para UPDATE, DELETE)
     * @param username Nombre de usuario (para CREATE, UPDATE)
     * @param email Email del usuario (para CREATE, UPDATE)
     * @param rolId ID del rol (para CREATE, UPDATE)
     * @return Map con el resultado de la operación
     */
    Map<String, Object> executeSpAdminUsuarios(String accion, Integer usuarioId, String username, String email, Integer rolId);
    
    /**
     * Obtiene estadísticas del sistema de login
     * 
     * @return Map con estadísticas generales
     */
    Map<String, Object> getEstadisticasSistema();
    
    /**
     * Verifica si un email ya está registrado
     * 
     * @param email Email a verificar
     * @return true si existe, false otherwise
     */
    Boolean existsEmail(String email);
    
    /**
     * Verifica si un username ya está registrado
     * 
     * @param username Username a verificar
     * @return true si existe, false otherwise
     */
    Boolean existsUsername(String username);
}
