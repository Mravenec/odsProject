package com.odsProject.odsProject.controller.interfaces;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminDetalleIndicadores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Interfaz de Controller para el Sistema de Login
 * Define los endpoints REST para autenticación, gestión de usuarios y sesión
 * Implementa la API RESTful del sistema de login
 */
public interface ILoginController {

    // ── Autenticación ──
    
    /**
     * Endpoint para login de usuarios
     * 
     * @param loginRequest Map con email, password, ip y userAgent
     * @return ResponseEntity con token de sesión y datos del usuario
     */
    @PostMapping("/auth/login")
    ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest);
    
    /**
     * Endpoint para logout de usuarios
     * 
     * @param token Token de sesión a revocar
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/auth/logout")
    ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String token);
    
    /**
     * Endpoint para verificar validez de token
     * 
     * @param token Token de sesión
     * @return ResponseEntity con datos del usuario si el token es válido
     */
    @GetMapping("/auth/validate")
    ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String token);
    
    /**
     * Endpoint para refrescar token de sesión
     * 
     * @param token Token actual
     * @return ResponseEntity con nuevo token
     */
    @PostMapping("/auth/refresh")
    ResponseEntity<Map<String, String>> refreshToken(@RequestHeader("Authorization") String token);
    
    // ── Gestión de Usuarios ──
    
    /**
     * Endpoint para registrar nuevo usuario
     * 
     * @param userRequest Map con datos del usuario y password
     * @return ResponseEntity con usuario registrado
     */
    @PostMapping("/users/register")
    ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> userRequest);
    
    /**
     * Endpoint para obtener usuario por ID
     * 
     * @param id ID del usuario
     * @return ResponseEntity con datos del usuario
     */
    @GetMapping("/users/{id}")
    ResponseEntity<Map<String, Object>> getUsuarioById(@PathVariable Integer id);
    
    /**
     * Endpoint para obtener usuario por username
     * 
     * @param username Nombre de usuario
     * @return ResponseEntity con datos del usuario
     */
    @GetMapping("/users/username/{username}")
    ResponseEntity<Map<String, Object>> getUsuarioByUsername(@PathVariable String username);
    
    /**
     * Endpoint para actualizar datos de usuario
     * 
     * @param id ID del usuario
     * @param userRequest Map con datos actualizados
     * @return ResponseEntity con usuario actualizado
     */
    @PutMapping("/users/{id}")
    ResponseEntity<Map<String, Object>> updateUsuario(@PathVariable Integer id, @RequestBody Map<String, Object> userRequest);
    
    /**
     * Endpoint para cambiar contraseña
     * 
     * @param passwordRequest Map con password actual y nueva
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/change-password")
    ResponseEntity<Boolean> changePassword(@RequestBody Map<String, String> passwordRequest);
    
    /**
     * Endpoint para iniciar recuperación de contraseña
     * 
     * @param emailRequest Map con email del usuario
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/recovery")
    ResponseEntity<Boolean> initiatePasswordRecovery(@RequestBody Map<String, String> emailRequest);
    
    /**
     * Endpoint para restablecer contraseña
     * 
     * @param resetRequest Map con token y nueva contraseña
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/reset-password")
    ResponseEntity<Boolean> resetPassword(@RequestBody Map<String, String> resetRequest);
    
    /**
     * Endpoint para verificar token de recuperación
     * 
     * @param token Token de recuperación
     * @return ResponseEntity con datos del usuario si el token es válido
     */
    @GetMapping("/users/verify-recovery/{token}")
    ResponseEntity<Map<String, Object>> validateRecoveryToken(@PathVariable String token);
    
    // ── Gestión de Roles ──
    
    /**
     * Endpoint para obtener todos los roles
     * 
     * @return ResponseEntity con lista de roles
     */
    @GetMapping("/roles")
    ResponseEntity<List<Map<String, Object>>> getAllRoles();
    
    /**
     * Endpoint para obtener rol por ID
     * 
     * @param id ID del rol
     * @return ResponseEntity con datos del rol
     */
    @GetMapping("/roles/{id}")
    ResponseEntity<Map<String, Object>> getRolById(@PathVariable Integer id);
    
    /**
     * Endpoint para asignar rol a usuario
     * 
     * @param assignmentRequest Map con usuarioId y rolId
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/assign-role")
    ResponseEntity<Boolean> assignRolToUsuario(@RequestBody Map<String, Integer> assignmentRequest);
    
    // ── Sedes ──
    
    /**
     * Endpoint para obtener todas las sedes
     * 
     * @return ResponseEntity con lista de sedes
     */
    @GetMapping("/sedes")
    ResponseEntity<List<Map<String, Object>>> getAllSedes();
    
    /**
     * Endpoint para obtener sede por ID
     * 
     * @param id ID de la sede
     * @return ResponseEntity con datos de la sede
     */
    @GetMapping("/sedes/{id}")
    ResponseEntity<Map<String, Object>> getSedeById(@PathVariable Integer id);
    
    // ── Gestión de Sesiones ──
    
    /**
     * Endpoint para obtener sesiones activas de usuario
     * 
     * @param usuarioId ID del usuario
     * @return ResponseEntity con lista de sesiones activas
     */
    @GetMapping("/users/{id}/sessions")
    ResponseEntity<List<Map<String, Object>>> getActiveSessions(@PathVariable("id") Integer usuarioId);
    
    /**
     * Endpoint para revocar otras sesiones del usuario
     * 
     * @param revokeRequest Map con usuarioId y currentToken
     * @return ResponseEntity con número de sesiones revocadas
     */
    @PostMapping("/users/revoke-other-sessions")
    ResponseEntity<Integer> revokeOtherSessions(@RequestBody Map<String, String> revokeRequest);
    
    /**
     * Endpoint para revocar todas las sesiones de usuario
     * 
     * @param usuarioId ID del usuario
     * @return ResponseEntity con número de sesiones revocadas
     */
    @PostMapping("/users/{id}/revoke-all-sessions")
    ResponseEntity<Integer> revokeAllSessions(@PathVariable("id") Integer usuarioId);
    
    // ── Permisos ODS ──
    
    /**
     * Endpoint para obtener permisos ODS de usuario
     * 
     * @param usuarioId ID del usuario
     * @return ResponseEntity con lista de permisos ODS
     */
    @GetMapping("/users/{id}/permits")
    ResponseEntity<List<Map<String, Object>>> getPermisosByUsuario(@PathVariable("id") Integer usuarioId);
    
    /**
     * Endpoint para verificar permiso ODS de usuario
     * 
     * @param usuarioId ID del usuario
     * @param odsId ID del ODS
     * @return ResponseEntity con resultado de la verificación
     */
    @GetMapping("/users/{id}/permits/{odsId}")
    ResponseEntity<Boolean> hasPermisoOds(@PathVariable("id") Integer usuarioId, @PathVariable Integer odsId);
    
    /**
     * Endpoint para otorgar permiso ODS a usuario
     * 
     * @param permissionRequest Map con usuarioId y odsId
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/grant-permit")
    ResponseEntity<Boolean> grantPermisoOds(@RequestBody Map<String, Integer> permissionRequest);
    
    /**
     * Endpoint para revocar permiso ODS de usuario
     * 
     * @param permissionRequest Map con usuarioId y odsId
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/revoke-permit")
    ResponseEntity<Boolean> revokePermisoOds(@RequestBody Map<String, Integer> permissionRequest);
    
    // ── Auditoría y Seguridad ──
    
    /**
     * Endpoint para obtener historial de login de usuario
     * 
     * @param usuarioId ID del usuario
     * @param dias Número de días hacia atrás
     * @return ResponseEntity con lista de registros de auditoría
     */
    @GetMapping("/users/{id}/login-history")
    ResponseEntity<List<Map<String, Object>>> getLoginHistory(@PathVariable("id") Integer usuarioId, @RequestParam(defaultValue = "30") Integer dias);
    
    /**
     * Endpoint para obtener intentos fallidos recientes
     * 
     * @param horas Número de horas hacia atrás
     * @return ResponseEntity con lista de intentos fallidos
     */
    @GetMapping("/auth/failed-attempts")
    ResponseEntity<List<Map<String, Object>>> getFailedLoginAttempts(@RequestParam(defaultValue = "24") Integer horas);
    
    /**
     * Endpoint para bloquear usuario por seguridad
     * 
     * @param blockRequest Map con usuarioId, motivo y horas
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/block")
    ResponseEntity<Boolean> blockUsuario(@RequestBody Map<String, Object> blockRequest);
    
    /**
     * Endpoint para desbloquear usuario
     * 
     * @param usuarioId ID del usuario
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/users/{id}/unblock")
    ResponseEntity<Boolean> unblockUsuario(@PathVariable("id") Integer usuarioId);
    
    // ── Administración ──
    
    /**
     * Endpoint para obtener vista de auditoría reciente
     * 
     * @param dias Número de días a consultar
     * @return ResponseEntity con lista de auditorías recientes
     */
    @GetMapping("/admin/audit-recent")
    ResponseEntity<List<Map<String, Object>>> getVistaAuditoriaReciente(@RequestParam(defaultValue = "7") Integer dias);
    
    /**
     * Endpoint para obtener resumen general administrativo
     * 
     * @return ResponseEntity con datos resumidos del sistema
     */
    @GetMapping("/admin/summary")
    ResponseEntity<List<Map<String, Object>>> getVistaResumenGeneral();
    
    /**
     * Endpoint para obtener usuarios activos
     * 
     * @return ResponseEntity con lista de usuarios activos
     */
    @GetMapping("/admin/active-users")
    ResponseEntity<List<Map<String, Object>>> getVistaUsuariosActivos();
    
    /**
     * Endpoint para obtener detalles de indicadores
     * 
     * @param proyectoId ID del proyecto (opcional)
     * @return ResponseEntity con lista de detalles de indicadores
     */
    @GetMapping("/admin/indicators-detail")
    ResponseEntity<List<VistaAdminDetalleIndicadores>> getVistaDetalleIndicadores(@RequestParam(required = false) Integer proyectoId);
    
    /**
     * Endpoint para obtener estadísticas del sistema
     * 
     * @return ResponseEntity con estadísticas generales
     */
    @GetMapping("/admin/statistics")
    ResponseEntity<Map<String, Object>> getEstadisticasSistema();
    
    /**
     * Endpoint para operaciones administrativas sobre usuarios
     * 
     * @param adminRequest Map con acción y datos del usuario
     * @return ResponseEntity con resultado de la operación
     */
    @PostMapping("/admin/users")
    ResponseEntity<Map<String, Object>> adminUsuario(@RequestBody Map<String, Object> adminRequest);
    
    // ── Utilidades ──
    
    /**
     * Endpoint para verificar si email existe
     * 
     * @param email Email a verificar
     * @return ResponseEntity con resultado de la verificación
     */
    @GetMapping("/users/check-email/{email}")
    ResponseEntity<Boolean> emailExists(@PathVariable String email);
    
    /**
     * Endpoint para verificar si username existe
     * 
     * @param username Username a verificar
     * @return ResponseEntity con resultado de la verificación
     */
    @GetMapping("/users/check-username/{username}")
    ResponseEntity<Boolean> usernameExists(@PathVariable String username);
    
    /**
     * Endpoint para validar formato de contraseña
     * 
     * @param passwordRequest Map con contraseña a validar
     * @return ResponseEntity con resultado de la validación
     */
    @PostMapping("/users/validate-password")
    ResponseEntity<Boolean> validatePasswordFormat(@RequestBody Map<String, String> passwordRequest);
    
    /**
     * Endpoint para limpieza de datos expirados
     * 
     * @return ResponseEntity con resultados de la limpieza
     */
    @PostMapping("/admin/cleanup")
    ResponseEntity<Map<String, Object>> cleanupExpiredData();
    
    /**
     * Endpoint para health check del servicio de login
     * 
     * @return ResponseEntity con estado del servicio
     */
    @GetMapping("/health")
    ResponseEntity<Map<String, String>> healthCheck();
}
