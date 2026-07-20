package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.service.LoginService;
import com.odsProject.odsProject.controller.interfaces.ILoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.odsProject.odsProject.database.jooq.ods_login.enums.AuditoriaLoginEvento;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sesiones;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.AuditoriaLogin;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.PermisosOds;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminAuditoriaLoginReciente;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminUsuariosActivos;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sedes;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Roles;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios;
import org.jooq.types.UByte;
import org.jooq.types.UShort;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Controlador REST para el Sistema de Login
 * Expone endpoints REST para autenticación, gestión de usuarios y sesión
 * Usa LoginService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/login")
public class LoginController implements ILoginController {

    @Autowired
    private LoginService loginService;

    // ── Autenticación ──

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        String ip = loginRequest.get("ip");
        String userAgent = loginRequest.get("userAgent");

        var result = loginService.authenticate(email, password, ip, userAgent);
        if (result.isPresent()) {
            Map<String, Object> authData = result.get();
            com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios usuario = 
                (com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios) authData.get("usuario");

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("success", true);
            response.put("message", "Login successful for " + email);
            response.put("token", authData.get("token"));
            response.put("userId", usuario.getId());
            response.put("role", authData.get("rol"));
            response.put("email", email);
            response.put("fullName", usuario.getFullName());
            response.put("sedeId", authData.get("sedeId"));
            response.put("sedeNombre", authData.get("sedeNombre"));
            Object profile = authData.get("profile");
            if (profile instanceof Map<?, ?> profileMap) {
                response.put("profile", profileMap);
                response.put("telefonoContacto", profileMap.get("telefonoContacto"));
                response.put("areaId", profileMap.get("areaId"));
                response.put("areaNombre", profileMap.get("areaNombre"));
                response.put("dependenciaId", profileMap.get("dependenciaId"));
                response.put("dependenciaNombre", profileMap.get("dependenciaNombre"));
                response.put("rolDependenciaId", profileMap.get("rolDependenciaId"));
                response.put("rolDependenciaNombre", profileMap.get("rolDependenciaNombre"));
                response.put("contacto", profileMap.get("contacto"));
            }
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> errorResponse = Map.of(
                "success", false,
                "message", "Login failed"
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/auth/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("Authorization") String token) {
        Boolean result = loginService.logout(token);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/auth/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader("Authorization") String token) {
        var result = loginService.validateToken(token);
        return result.map(user -> {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            loginService.getRolById(user.getRolId()).ifPresent(r -> response.put("role", r.getNombre()));
            response.put("profile", loginService.buildUsuarioAuthProfile(user));
            return ResponseEntity.ok(response);
        }).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/auth/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestHeader("Authorization") String token) {
        var result = loginService.refreshToken(token);
        return result.map(newToken -> ResponseEntity.ok(Map.of("token", newToken))).orElse(ResponseEntity.badRequest().build());
    }

    // ── Gestión de Usuarios ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users")
    public ResponseEntity<List<Map<String, Object>>> listUsuariosAdmin() {
        return ResponseEntity.ok(loginService.getAllUsuariosAdmin());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody Map<String, Object> userRequest) {
        Usuarios usuario = mapToUsuario(userRequest, null);
        String password = stringField(userRequest, "password");
        Usuarios created = loginService.createUser(usuario, password);
        return ResponseEntity.status(201).body(sanitizeUsuario(created));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PatchMapping("/users/{id}/deactivate")
    public ResponseEntity<Map<String, Object>> deactivateUser(@PathVariable Integer id) {
        Usuarios deactivated = loginService.deactivateUser(id);
        return ResponseEntity.ok(sanitizeUsuario(deactivated));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, Object> userRequest) {
        // Placeholder - service doesn't have matching method
        return ResponseEntity.badRequest().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> getUsuarioById(@PathVariable Integer id) {
        var result = loginService.getUsuarioById(id);
        return result.map(user -> {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/username/{username}")
    public ResponseEntity<Map<String, Object>> getUsuarioByUsername(@PathVariable String username) {
        var result = loginService.getUsuarioByUsername(username);
        return result.map(user -> {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> updateUsuario(@PathVariable Integer id, @RequestBody Map<String, Object> userRequest) {
        Usuarios usuario = mapToUsuario(userRequest, id);
        String password = stringField(userRequest, "password");
        Usuarios updated = loginService.updateUser(usuario, password);
        return ResponseEntity.ok(sanitizeUsuario(updated));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/change-password")
    public ResponseEntity<Boolean> changePassword(@RequestBody Map<String, String> passwordRequest) {
        // Placeholder - service method signature differs
        return ResponseEntity.ok(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/recovery")
    public ResponseEntity<Boolean> initiatePasswordRecovery(@RequestBody Map<String, String> emailRequest) {
        String email = emailRequest.get("email");
        Boolean result = loginService.initiatePasswordRecovery(email);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/reset-password")
    public ResponseEntity<Boolean> resetPassword(@RequestBody Map<String, String> resetRequest) {
        // Placeholder - service method signature differs
        return ResponseEntity.ok(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/verify-recovery/{token}")
    public ResponseEntity<Map<String, Object>> validateRecoveryToken(@PathVariable String token) {
        var result = loginService.validateRecoveryToken(token);
        return result.map(user -> {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.badRequest().build());
    }

    // ── Gestión de Roles ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> getAllRoles() {
        return ResponseEntity.ok(loginService.getAllRoles());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/roles/{id}")
    public ResponseEntity<Map<String, Object>> getRolById(@PathVariable Integer id) {
        var result = loginService.getRolById(id);
        return result.map(role -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", role.getId());
            response.put("name", role.getNombre());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/assign-role")
    public ResponseEntity<Boolean> assignRolToUsuario(@RequestBody Map<String, Integer> assignmentRequest) {
        // Placeholder - service method signature differs
        return ResponseEntity.ok(false);
    }

    // ── Sedes ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/sedes")
    public ResponseEntity<List<Map<String, Object>>> getAllSedes() {
        List<Sedes> sedes = loginService.getAllSedes();
        return ResponseEntity.ok(sedes.stream().map(sede -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sede.getId());
            map.put("nombre", sede.getNombre());
            map.put("descripcion", sede.getDescripcion());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/sedes/{id}")
    public ResponseEntity<Map<String, Object>> getSedeById(@PathVariable Integer id) {
        var result = loginService.getSedeById(id);
        return result.map(sede -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", sede.getId());
            response.put("nombre", sede.getNombre());
            response.put("descripcion", sede.getDescripcion());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }

    // ── Gestión de Sesiones ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/{id}/sessions")
    public ResponseEntity<List<Map<String, Object>>> getActiveSessions(@PathVariable("id") Integer usuarioId) {
        List<Sesiones> result = loginService.getActiveSessions(usuarioId);
        return ResponseEntity.ok(result.stream().map(session -> {
            Map<String, Object> map = new HashMap<>();
            map.put("token", session.getTokenHash());
            map.put("fechaCreacion", session.getCreatedAt());
            map.put("fechaExpiracion", session.getExpiraEn());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/revoke-other-sessions")
    public ResponseEntity<Integer> revokeOtherSessions(@RequestBody Map<String, String> revokeRequest) {
        Integer usuarioId = Integer.valueOf(revokeRequest.get("usuarioId"));
        String currentToken = revokeRequest.get("currentToken");
        Integer result = loginService.revokeOtherSessions(usuarioId, currentToken);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/{id}/revoke-all-sessions")
    public ResponseEntity<Integer> revokeAllSessions(@PathVariable("id") Integer usuarioId) {
        Integer result = loginService.revokeAllSessions(usuarioId);
        return ResponseEntity.ok(result);
    }

    // ── Permisos ODS ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/{id}/permits")
    public ResponseEntity<List<Map<String, Object>>> getPermisosByUsuario(@PathVariable("id") Integer usuarioId) {
        List<PermisosOds> result = loginService.getPermisosByUsuario(usuarioId);
        return ResponseEntity.ok(result.stream().map(permiso -> {
            Map<String, Object> map = new HashMap<>();
            map.put("odsId", permiso.getOdsNum().intValue());
            map.put("fechaAsignacion", permiso.getCreatedAt());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/{id}/permits/{odsId}")
    public ResponseEntity<Boolean> hasPermisoOds(@PathVariable("id") Integer usuarioId, @PathVariable Integer odsId) {
        Boolean result = loginService.hasPermisoOds(usuarioId, odsId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/grant-permit")
    public ResponseEntity<Boolean> grantPermisoOds(@RequestBody Map<String, Integer> permissionRequest) {
        Integer usuarioId = permissionRequest.get("usuarioId");
        Integer odsId = permissionRequest.get("odsId");
        Boolean result = loginService.grantPermisoOds(usuarioId, odsId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/revoke-permit")
    public ResponseEntity<Boolean> revokePermisoOds(@RequestBody Map<String, Integer> permissionRequest) {
        Integer usuarioId = permissionRequest.get("usuarioId");
        Integer odsId = permissionRequest.get("odsId");
        Boolean result = loginService.revokePermisoOds(usuarioId, odsId);
        return ResponseEntity.ok(result);
    }

    // ── Auditoría y Seguridad ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/{id}/login-history")
    public ResponseEntity<List<Map<String, Object>>> getLoginHistory(@PathVariable("id") Integer usuarioId, @RequestParam(defaultValue = "30") Integer dias) {
        List<AuditoriaLogin> result = loginService.getLoginHistory(usuarioId, dias);
        return ResponseEntity.ok(result.stream().map(audit -> {
            Map<String, Object> map = new HashMap<>();
            map.put("fecha", audit.getFechaEvento());
            map.put("ip", audit.getIpAddress());
            map.put("exitoso", audit.getEvento() == AuditoriaLoginEvento.LOGIN_OK);
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/auth/failed-attempts")
    public ResponseEntity<List<Map<String, Object>>> getFailedLoginAttempts(@RequestParam(defaultValue = "24") Integer horas) {
        List<AuditoriaLogin> result = loginService.getFailedLoginAttempts(horas);
        return ResponseEntity.ok(result.stream().map(audit -> {
            Map<String, Object> map = new HashMap<>();
            map.put("fecha", audit.getFechaEvento());
            map.put("ip", audit.getIpAddress());
            map.put("email", audit.getEmailIntento());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/block")
    public ResponseEntity<Boolean> blockUsuario(@RequestBody Map<String, Object> blockRequest) {
        Integer usuarioId = (Integer) blockRequest.get("usuarioId");
        String motivo = (String) blockRequest.get("motivo");
        Integer horas = (Integer) blockRequest.get("horas");
        Boolean result = loginService.blockUsuario(usuarioId, motivo, horas);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/{id}/unblock")
    public ResponseEntity<Boolean> unblockUsuario(@PathVariable("id") Integer usuarioId) {
        Boolean result = loginService.unblockUsuario(usuarioId);
        return ResponseEntity.ok(result);
    }

    // ── Administración ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/admin/audit-recent")
    public ResponseEntity<List<Map<String, Object>>> getVistaAuditoriaReciente(@RequestParam(defaultValue = "7") Integer dias) {
        List<VistaAdminAuditoriaLoginReciente> result = loginService.getVistaAuditoriaReciente(dias);
        return ResponseEntity.ok(result.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("fechaEvento", item.getFechaEvento());
            map.put("evento", item.getEvento() != null ? item.getEvento().getLiteral() : null);
            map.put("username", item.getUsername());
            map.put("fullName", item.getFullName());
            map.put("emailIntento", item.getEmailIntento());
            map.put("ipAddress", item.getIpAddress());
            map.put("userAgent", item.getUserAgent());
            map.put("detalle", item.getDetalle());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/admin/summary")
    public ResponseEntity<List<Map<String, Object>>> getVistaResumenGeneral() {
        List<VistaAdminResumenGeneral> result = loginService.getVistaResumenGeneral();
        return ResponseEntity.ok(result.stream().map(item -> {
            Map<String, Object> map = new HashMap<>();
            map.put("proyectoId", item.getProyectoId());
            map.put("nombreProyecto", item.getNombreProyecto());
            map.put("usuarioCreador", item.getUsuarioCreador());
            map.put("fechaInicio", item.getFechaInicio());
            map.put("fechaFin", item.getFechaFin());
            map.put("estado", item.getEstado());
            map.put("totalIndicadores", item.getTotalIndicadores());
            map.put("indicadoresLogrados", item.getIndicadoresLogrados());
            map.put("progresoPorcentaje", item.getProgresoPorcentaje());
            map.put("fechaCreacion", item.getFechaCreacion());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/admin/active-users")
    public ResponseEntity<List<Map<String, Object>>> getVistaUsuariosActivos() {
        List<VistaAdminUsuariosActivos> result = loginService.getVistaUsuariosActivos();
        return ResponseEntity.ok(result.stream().map(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("username", user.getUsername());
            map.put("fullName", user.getFullName());
            map.put("email", user.getEmail());
            map.put("rol", user.getRol());
            map.put("sede", user.getSede());
            map.put("ultimoLogin", user.getUltimoLogin());
            map.put("createdAt", user.getCreatedAt());
            map.put("odsPermitidos", user.getOdsPermitidos());
            return map;
        }).collect(java.util.stream.Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/admin/indicators-detail")
    public ResponseEntity<List<VistaAdminDetalleIndicadores>> getVistaDetalleIndicadores(@RequestParam(required = false) Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> result = loginService.getVistaDetalleIndicadores(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/admin/statistics")
    public ResponseEntity<Map<String, Object>> getEstadisticasSistema() {
        Map<String, Object> result = loginService.getEstadisticasSistema();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/admin/users")
    public ResponseEntity<Map<String, Object>> adminUsuario(@RequestBody Map<String, Object> adminRequest) {
        String accion = (String) adminRequest.get("accion");
        Integer usuarioId = (Integer) adminRequest.get("usuarioId");
        String username = (String) adminRequest.get("username");
        String email = (String) adminRequest.get("email");
        Integer rolId = (Integer) adminRequest.get("rolId");
        Map<String, Object> result = loginService.adminUsuario(accion, usuarioId, username, email, rolId);
        return ResponseEntity.ok(result);
    }

    // ── Utilidades ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/check-email/{email}")
    public ResponseEntity<Boolean> emailExists(@PathVariable String email) {
        Boolean result = loginService.emailExists(email);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/users/check-username/{username}")
    public ResponseEntity<Boolean> usernameExists(@PathVariable String username) {
        Boolean result = loginService.usernameExists(username);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/users/validate-password")
    public ResponseEntity<Boolean> validatePasswordFormat(@RequestBody Map<String, String> passwordRequest) {
        String password = passwordRequest.get("password");
        if (password == null) {
            return ResponseEntity.badRequest().body(false);
        }
        Boolean result = loginService.validatePasswordFormat(password);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/admin/cleanup")
    public ResponseEntity<Map<String, Object>> cleanupExpiredData() {
        Map<String, Object> result = loginService.cleanupExpiredData();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> result = loginService.healthCheck();
        return ResponseEntity.ok(result);
    }

    private static Usuarios mapToUsuario(Map<String, Object> body, Integer id) {
        Usuarios u = new Usuarios();
        if (id != null) {
            u.setId(id);
        }
        u.setUsername(stringField(body, "username"));
        u.setEmail(stringField(body, "email"));
        u.setFullName(stringField(body, "fullName"));
        u.setRolId(intField(body, "rolId"));
        u.setSedeId(intField(body, "sedeId"));
        Integer areaId = intField(body, "areaId");
        if (body.containsKey("areaId")) {
            u.setAreaId(areaId != null ? UShort.valueOf(areaId.shortValue()) : null);
        }
        Integer dependenciaId = intField(body, "dependenciaId");
        if (body.containsKey("dependenciaId")) {
            u.setDependenciaId(dependenciaId != null ? UShort.valueOf(dependenciaId.shortValue()) : null);
        }
        Integer rolDependenciaId = intField(body, "rolDependenciaId");
        if (body.containsKey("rolDependenciaId")) {
            u.setRolDependenciaId(rolDependenciaId != null ? UByte.valueOf(rolDependenciaId.byteValue()) : null);
        }
        if (body.containsKey("telefonoContacto")) {
            u.setTelefonoContacto(stringField(body, "telefonoContacto"));
        }
        return u;
    }

    private static String stringField(Map<String, Object> body, String key) {
        Object value = body.get(key);
        return value == null ? null : String.valueOf(value);
    }

    private static Integer intField(Map<String, Object> body, String key) {
        Object value = body.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof Number number) {
            return number.intValue();
        }
        return Integer.valueOf(String.valueOf(value));
    }

    /** Sanitiza Usuarios POJO para JSON — nunca expone passwordHash. */
    private static Map<String, Object> sanitizeUsuario(Usuarios u) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", u.getId());
        m.put("username", u.getUsername());
        m.put("email", u.getEmail());
        m.put("fullName", u.getFullName());
        m.put("rolId", u.getRolId());
        m.put("sedeId", u.getSedeId());
        m.put("areaId", u.getAreaId() != null ? u.getAreaId().intValue() : null);
        m.put("dependenciaId", u.getDependenciaId() != null ? u.getDependenciaId().intValue() : null);
        m.put("rolDependenciaId", u.getRolDependenciaId() != null ? u.getRolDependenciaId().intValue() : null);
        m.put("telefonoContacto", u.getTelefonoContacto());
        m.put("isActive", u.getIsActive());
        m.put("emailVerificado", u.getEmailVerificado());
        m.put("ultimoLogin", u.getUltimoLogin());
        m.put("createdAt", u.getCreatedAt());
        m.put("updatedAt", u.getUpdatedAt());
        return m;
    }
}

