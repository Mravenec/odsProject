package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Usuarios;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Roles;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sesiones;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.AuditoriaLogin;
import com.odsProject.odsProject.database.jooq.ods_login.enums.AuditoriaLoginEvento;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.PermisosOds;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.VistaAdminUsuariosActivos;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.Sedes;
import com.odsProject.odsProject.repository.LoginRepository;
import com.odsProject.odsProject.repository.interfaces.ISodsiCatalogRepository;
import com.odsProject.odsProject.service.interfaces.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;


/**
 * Implementación del Servicio para el Sistema de Login
 * Implementa la lógica de negocio para autenticación, gestión de usuarios y sesión
 * Usa LoginRepository para el acceso a datos y agrega validaciones de negocio
 */
@Service
public class LoginService implements ILoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginService.class);
    /** Coincide con auditoria_login.user_agent VARCHAR(300). */
    private static final int USER_AGENT_MAX = 300;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private ISodsiCatalogRepository sodsiCatalogRepository;
    
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    private String generateToken(Usuarios usuario, String rolName) {
        return Jwts.builder()
            .subject(String.valueOf(usuario.getId()))
            .claim("rol", rolName)
            .claim("email", usuario.getEmail())
            .claim("fullName", usuario.getFullName())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 86400000L))
            .signWith(getSigningKey(), Jwts.SIG.HS256)
            .compact();
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ── Autenticación ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Map<String, Object>> authenticate(String email, String password, String ip, String userAgent) {
        try {
            // Validar entrada
            if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                return Optional.empty();
            }

            // Buscar usuario por email
            Optional<Usuarios> usuarioOpt = loginRepository.findUsuarioByEmail(email);
            
            if (usuarioOpt.isEmpty()) {
                // Usuario no encontrado - registrar intento fallido
                registerLoginAudit(null, email, AuditoriaLoginEvento.LOGIN_FALLIDO, ip, userAgent,
                        "Usuario no encontrado");
                return Optional.empty();
            }
            
            Usuarios usuario = usuarioOpt.get();
            
            // Verificar contraseña usando BCrypt - con manejo especial para mocks
            String storedHash = usuario.getPasswordHash();
            boolean passwordValid = false;
            
            if (storedHash.startsWith("$2b$12$MOCK_HASH_")) {
                // Para mocks de desarrollo - verificar contra password123
                passwordValid = "password123".equals(password);
            } else {
                // Validación normal con BCrypt
                passwordValid = passwordEncoder.matches(password, storedHash);
            }
            
            if (!passwordValid) {
                // Contraseña incorrecta - registrar intento fallido
                registerLoginAudit(usuario.getId(), email, AuditoriaLoginEvento.LOGIN_FALLIDO, ip, userAgent,
                        "Contraseña incorrecta");
                return Optional.empty();
            }
            
            // Verificar si el usuario está activo
            if (usuario.getIsActive() == null || usuario.getIsActive() != (byte) 1) {
                // Usuario inactivo - registrar intento fallido
                registerLoginAudit(usuario.getId(), email, AuditoriaLoginEvento.LOGIN_FALLIDO, ip, userAgent,
                        "Usuario inactivo");
                return Optional.empty();
            }

            registerLoginAudit(usuario.getId(), email, AuditoriaLoginEvento.LOGIN_OK, ip, userAgent, null);
            loginRepository.updateUltimoLogin(usuario.getId());

            // Obtener rol
            Optional<Roles> rolOpt = loginRepository.findRolById(usuario.getRolId());
            String rolName = rolOpt.isPresent() ? rolOpt.get().getNombre() : "USER";

            // Obtener sede detallada
            Optional<Sedes> sedeOpt = loginRepository.findSedeById(usuario.getSedeId());
            String sedeName = sedeOpt.isPresent() ? sedeOpt.get().getNombre() : "Sede Central";

            // Crear mapa de respuesta
            Map<String, Object> result = new HashMap<>();
            result.put("usuario", usuario);
            result.put("token", generateToken(usuario, rolName));
            result.put("rol", rolName); // Rol real desde DB
            result.put("sedeId", usuario.getSedeId());
            result.put("sedeNombre", sedeName);
            result.put("profile", buildUsuarioAuthProfile(usuario));
            result.put("permisos", List.of("READ", "WRITE"));
            result.put("loginStatus", "success");
            
            // Crear y guardar sesión
            String token = (String) result.get("token");
            Sesiones nuevaSesion = new Sesiones(
                null, // id - se genera automáticamente
                usuario.getId(),
                token,
                ip,
                userAgent,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(24), // expira en 24 horas
                (byte) 0 // no revocada
            );
            loginRepository.saveSesion(nuevaSesion);
            
            return Optional.of(result);
            
        } catch (Exception e) {
            // Registrar error de auditoría
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean logout(String token) {
        try {
            if (token == null || token.trim().isEmpty()) {
                return false;
            }
            String actualToken = token.startsWith("Bearer ") ? token.substring(7).trim() : token.trim();

            Integer userId = null;
            String email = null;
            try {
                Claims claims = Jwts.parser().verifyWith(getSigningKey()).build()
                        .parseSignedClaims(actualToken).getPayload();
                userId = Integer.parseInt(claims.getSubject());
                Optional<Usuarios> u = loginRepository.findUsuarioById(userId);
                if (u.isPresent()) {
                    email = u.get().getEmail();
                }
            } catch (Exception e) {
                log.warn("Logout: JWT inválido: {}", e.getMessage());
                return false;
            }

            // Revocar sesión en BD (sin sp_logout: ese SP también inserta LOGOUT y duplicaría)
            try {
                loginRepository.revocarSesion(actualToken);
            } catch (Exception e) {
                log.warn("revocarSesion: {}", e.getMessage());
            }

            // Bitácora: siempre LOGOUT desde JWT
            registerLoginAudit(userId, email, AuditoriaLoginEvento.LOGOUT, "127.0.0.1", null,
                    "Sesión cerrada");

            return true;

        } catch (Exception e) {
            log.warn("Logout falló: {}", e.getMessage());
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> validateToken(String token) {
        try {
            if (token == null || token.trim().isEmpty()) return Optional.empty();
            String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
            Claims claims = Jwts.parser().verifyWith(getSigningKey()).build()
                .parseSignedClaims(actualToken).getPayload();
            Integer userId = Integer.parseInt(claims.getSubject());
            return loginRepository.findUsuarioById(userId);
        } catch (Exception e) {
            try {
                String actualToken = token.startsWith("Bearer ") ? token.substring(7) : token;
                Optional<Sesiones> sesionOpt = loginRepository.findSesionByToken(actualToken);
                if (sesionOpt.isPresent()) return loginRepository.findUsuarioById(sesionOpt.get().getUsuarioId());
            } catch (Exception ex) {}
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<String> refreshToken(String tokenActual) {
        try {
            // Validar token actual
            Optional<Usuarios> usuarioOpt = validateToken(tokenActual);
            
            if (usuarioOpt.isPresent()) {
                // Generar nuevo token
                String nuevoToken = UUID.randomUUID().toString();
                
                // Crear nueva sesión con constructor completo
                Sesiones nuevaSesion = new Sesiones(
                    null, // id - se genera automáticamente
                    usuarioOpt.get().getId(),
                    nuevoToken,
                    "127.0.0.1", // IP por defecto
                    "Service-Refresh", // User agent
                    LocalDateTime.now(),
                    LocalDateTime.now().plusHours(24),
                    (byte) 0 // no revocada
                );
                
                loginRepository.saveSesion(nuevaSesion);
                
                return Optional.of(nuevoToken);
            }
            
            return Optional.empty();
            
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // ── Gestión de Usuarios ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios registerUser(Usuarios usuario, String password) {
        if (loginRepository.existsEmail(usuario.getEmail(), null)) {
            throw new IllegalArgumentException("Email ya registrado");
        }

        if (loginRepository.existsUsername(usuario.getUsername(), null)) {
            throw new IllegalArgumentException("Username ya registrado");
        }

        if (password != null && !password.isBlank()) {
            usuario.setPasswordHash(passwordEncoder.encode(password));
        }

        if (usuario.getIsActive() == null) {
            usuario.setIsActive((byte) 1);
        }

        return loginRepository.saveUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> getUsuarioById(Integer id) {
        return loginRepository.findUsuarioById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> getUsuarioByUsername(String username) {
        return loginRepository.findUsuarioByUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> getUsuarioByEmail(String email) {
        return loginRepository.findUsuarioByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios updateUsuario(Usuarios usuario) {
        if (loginRepository.findUsuarioById(usuario.getId()).isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        return loginRepository.updateUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getAllUsuariosAdmin() {
        return loginRepository.findAllUsuariosAdmin();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios createUser(Usuarios usuario, String password) {
        validateRolAndSede(usuario.getRolId(), usuario.getSedeId());

        if (loginRepository.existsEmail(usuario.getEmail(), null)) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (loginRepository.existsUsername(usuario.getUsername(), null)) {
            throw new IllegalArgumentException("Username ya registrado");
        }
        if (password == null || password.isBlank() || !validatePasswordFormat(password)) {
            throw new IllegalArgumentException("Password inválido");
        }

        usuario.setPasswordHash(passwordEncoder.encode(password));
        if (usuario.getIsActive() == null) {
            usuario.setIsActive((byte) 1);
        }
        if (usuario.getEmailVerificado() == null) {
            usuario.setEmailVerificado((byte) 0);
        }

        return loginRepository.saveUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios updateUser(Usuarios usuario, String password) {
        Usuarios existente = loginRepository.findUsuarioById(usuario.getId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        validateRolAndSede(usuario.getRolId(), usuario.getSedeId());

        if (loginRepository.existsEmail(usuario.getEmail(), usuario.getId())) {
            throw new IllegalArgumentException("Email ya registrado");
        }
        if (loginRepository.existsUsername(usuario.getUsername(), usuario.getId())) {
            throw new IllegalArgumentException("Username ya registrado");
        }

        if (password != null && !password.isBlank()) {
            if (!validatePasswordFormat(password)) {
                throw new IllegalArgumentException("Password inválido");
            }
            usuario.setPasswordHash(passwordEncoder.encode(password));
        } else {
            usuario.setPasswordHash(null);
        }

        if (usuario.getIsActive() == null) {
            usuario.setIsActive(existente.getIsActive());
        }
        if (usuario.getEmailVerificado() == null) {
            usuario.setEmailVerificado(existente.getEmailVerificado());
        }
        if (usuario.getAreaId() == null) {
            usuario.setAreaId(existente.getAreaId());
        }
        if (usuario.getDependenciaId() == null) {
            usuario.setDependenciaId(existente.getDependenciaId());
        }
        if (usuario.getRolDependenciaId() == null) {
            usuario.setRolDependenciaId(existente.getRolDependenciaId());
        }
        if (usuario.getTelefonoContacto() == null) {
            usuario.setTelefonoContacto(existente.getTelefonoContacto());
        }

        return loginRepository.updateUsuario(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuarios deactivateUser(Integer id) {
        Usuarios user = loginRepository.findUsuarioById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Optional<Roles> rol = loginRepository.findRolById(user.getRolId());
        if (rol.isPresent() && "admin".equals(rol.get().getNombre())
                && loginRepository.countActiveAdmins() <= 1) {
            throw new IllegalArgumentException("No se puede desactivar al único admin activo");
        }

        loginRepository.deactivateUsuario(id);
        return loginRepository.findUsuarioById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }

    private void validateRolAndSede(Integer rolId, Integer sedeId) {
        if (rolId == null || loginRepository.findRolById(rolId).isEmpty()) {
            throw new IllegalArgumentException("rolId inválido");
        }
        if (sedeId != null && loginRepository.findSedeById(sedeId).isEmpty()) {
            throw new IllegalArgumentException("sedeId inválido");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean changePassword(Integer usuarioId, String passwordActual, String passwordNueva) {
        try {
            Optional<Usuarios> usuarioOpt = loginRepository.findUsuarioById(usuarioId);
            if (usuarioOpt.isEmpty()) {
                return false;
            }
            
            // Lógica de cambio de contraseña (simplificada)
            // En un caso real, verificarías el hash del password actual
            // Como los POJOs son inmutables, necesitamos crear uno nuevo
            // Simplificado - en un caso real necesitaríamos un método en el repositorio para actualizar solo el password
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean initiatePasswordRecovery(String email) {
        try {
            Optional<Usuarios> usuarioOpt = loginRepository.findUsuarioByEmail(email);
            if (usuarioOpt.isEmpty()) {
                return false;
            }
            
            // Generar token de recuperación
            String token = UUID.randomUUID().toString();
            LocalDateTime expira = LocalDateTime.now().plusHours(24);
            
            // Actualizar token en usuario
            loginRepository.actualizarTokenRecuperacion(usuarioOpt.get().getId(), token, expira);
            
            // Aquí se enviaría el email (simplificado)
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean resetPassword(String token, String passwordNueva) {
        try {
            // Buscar usuario por token (simplificado - necesitaría método en repositorio)
            // Por ahora, retornamos false como placeholder
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Usuarios> validateRecoveryToken(String token) {
        try {
            // Buscar usuario por token (simplificado - necesitaría método en repositorio)
            // Por ahora, retornamos empty como placeholder
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // ── Gestión de Roles ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Roles> getAllRoles() {
        return loginRepository.findAllRoles();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Roles> getRolById(Integer id) {
        return loginRepository.findRolById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean assignRolToUsuario(Integer usuarioId, Integer rolId) {
        try {
            // This would need to be implemented in the repository
            // For now, return true as placeholder
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ── Sedes ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sedes> getAllSedes() {
        return loginRepository.findAllSedes();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Sedes> getSedeById(Integer id) {
        return loginRepository.findSedeById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Map<String, Object>> getVistaAuditoriaReciente(Integer dias) {
        return loginRepository.findVistaAuditoriaReciente(dias);
    }

    /** Persiste evento en auditoria_login (bitácora admin). No lanza: el login no debe fallar por auditoría. */
    private void registerLoginAudit(Integer usuarioId, String email, AuditoriaLoginEvento evento,
                                    String ip, String userAgent, String detalle) {
        try {
            AuditoriaLogin row = new AuditoriaLogin();
            row.setUsuarioId(usuarioId);
            row.setEmailIntento(email);
            row.setEvento(evento);
            row.setIpAddress(ip != null && ip.length() > 45 ? ip.substring(0, 45) : ip);
            row.setUserAgent(userAgent != null && userAgent.length() > USER_AGENT_MAX
                    ? userAgent.substring(0, USER_AGENT_MAX) : userAgent);
            row.setDetalle(detalle);
            row.setFechaEvento(LocalDateTime.now());
            loginRepository.saveAuditoriaLogin(row);
        } catch (Exception e) {
            log.warn("Bitácora: no se pudo registrar {} para email={}: {}",
                    evento, email, e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VistaAdminUsuariosActivos> getVistaUsuariosActivos() {
        return loginRepository.findVistaUsuariosActivos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VistaAdminResumenGeneral> getVistaResumenGeneral() {
        return loginRepository.findVistaResumenGeneral();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer revokeOtherSessions(Integer usuarioId, String currentToken) {
        try {
            // Revocar todas las sesiones excepto la actual
            List<Sesiones> sesiones = loginRepository.findSesionesByUsuario(usuarioId);
            int revocadas = 0;
            
            for (Sesiones sesion : sesiones) {
                if (!sesion.getTokenHash().equals(currentToken)) {
                    loginRepository.revocarSesion(sesion.getTokenHash());
                    revocadas++;
                }
            }
            
            return revocadas;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer revokeAllSessions(Integer usuarioId) {
        try {
            loginRepository.revocarSesionesByUsuario(usuarioId);
            // Contar cuántas sesiones se revocaron (simplificado)
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sesiones> getActiveSessions(Integer usuarioId) {
        return loginRepository.findSesionesByUsuario(usuarioId);
    }

    // ── Permisos ODS ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PermisosOds> getPermisosByUsuario(Integer usuarioId) {
        return loginRepository.findPermisosByUsuario(usuarioId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasPermisoOds(Integer usuarioId, Integer odsId) {
        return loginRepository.hasPermisoOds(usuarioId, odsId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean grantPermisoOds(Integer usuarioId, Integer odsId) {
        try {
            // Lógica para otorgar permiso (simplificado - necesitaría método en repositorio)
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean revokePermisoOds(Integer usuarioId, Integer odsId) {
        try {
            // Lógica para revocar permiso (simplificado - necesitaría método en repositorio)
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ── Auditoría y Seguridad ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaLogin> getLoginHistory(Integer usuarioId, Integer dias) {
        return loginRepository.findAuditoriaByUsuario(usuarioId, dias);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaLogin> getFailedLoginAttempts(Integer horas) {
        return loginRepository.findIntentosFallidosRecientes(horas);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean blockUsuario(Integer usuarioId, String motivo, Integer horas) {
        try {
            LocalDateTime hasta = LocalDateTime.now().plusHours(horas);
            loginRepository.bloquearUsuario(usuarioId, hasta);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean unblockUsuario(Integer usuarioId) {
        try {
            loginRepository.desbloquearUsuario(usuarioId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ── Administración ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getEstadisticasSistema() {
        return loginRepository.getEstadisticasSistema();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> adminUsuario(String accion, Integer usuarioId, String username, String email, Integer rolId) {
        return loginRepository.executeSpAdminUsuarios(accion, usuarioId, username, email, rolId);
    }

    // ── Utilidades ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean emailExists(String email) {
        return loginRepository.existsEmail(email, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean usernameExists(String username) {
        return loginRepository.existsUsername(username, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validatePasswordFormat(String password) {
        // Validación básica de formato de contraseña
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasLetter = password.chars().anyMatch(Character::isLetter);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        
        return hasLetter && hasDigit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateSecureToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> cleanupExpiredData() {
        try {
            Integer sesionesLimpiadas = loginRepository.limpiarSesionesExpiradas();
            
            return Map.of(
                "sesionesEliminadas", sesionesLimpiadas,
                "fechaLimpieza", LocalDateTime.now(),
                "status", "success"
            );
        } catch (Exception e) {
            return Map.of(
                "status", "error",
                "error", e.getMessage(),
                "fechaLimpieza", LocalDateTime.now()
            );
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<VistaAdminDetalleIndicadores> getVistaDetalleIndicadores(Integer proyectoId) {
        return loginRepository.findVistaDetalleIndicadores(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> buildUsuarioAuthProfile(Usuarios usuario) {
        Map<String, Object> profile = new LinkedHashMap<>();
        if (usuario == null) {
            return profile;
        }
        profile.put("userId", usuario.getId());
        profile.put("fullName", usuario.getFullName());
        profile.put("email", usuario.getEmail());
        profile.put("telefonoContacto", usuario.getTelefonoContacto());
        profile.put("sedeId", usuario.getSedeId());
        loginRepository.findSedeById(usuario.getSedeId())
                .ifPresent(s -> profile.put("sedeNombre", s.getNombre()));
        profile.put("areaId", usuario.getAreaId() != null ? usuario.getAreaId().intValue() : null);
        sodsiCatalogRepository.findAreaById(usuario.getAreaId()).ifPresent(a -> {
            profile.put("areaCodigo", a.getCodigo());
            profile.put("areaNombre", a.getNombre());
        });
        profile.put("dependenciaId", usuario.getDependenciaId() != null ? usuario.getDependenciaId().intValue() : null);
        sodsiCatalogRepository.findDependenciaById(usuario.getDependenciaId()).ifPresent(d -> {
            profile.put("dependenciaCodigo", d.getCodigo());
            profile.put("dependenciaNombre", d.getNombre());
        });
        profile.put("rolDependenciaId", usuario.getRolDependenciaId() != null ? usuario.getRolDependenciaId().intValue() : null);
        sodsiCatalogRepository.findRolDependenciaById(usuario.getRolDependenciaId()).ifPresent(r -> {
            profile.put("rolDependenciaCodigo", r.getCodigo());
            profile.put("rolDependenciaNombre", r.getNombre());
        });
        profile.put("unidadProgramaticaId", usuario.getUnidadProgramaticaId());
        sodsiCatalogRepository.findUnidadProgramaticaById(usuario.getUnidadProgramaticaId()).ifPresent(u -> {
            profile.put("unidadProgramaticaCodigo", u.getCodigo());
            profile.put("unidadProgramaticaNombre", u.getNombre());
        });
        profile.put("contacto", formatContactoExport(
                usuario.getFullName(), usuario.getEmail(), usuario.getTelefonoContacto()));
        return profile;
    }

    private static String formatContactoExport(String nombre, String email, String telefono) {
        String n = nombre != null ? nombre : "";
        String e = email != null ? email : "";
        String t = telefono != null ? telefono : "";
        if (n.isBlank() && e.isBlank() && t.isBlank()) return "";
        return n + " - " + e + " - " + t;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> healthCheck() {
        try {
            // Simple health check - verify database connectivity
            loginRepository.findAllRoles(); // Simple query to test DB connection
            
            return Map.of(
                "status", "UP",
                "service", "LoginService",
                "timestamp", LocalDateTime.now().toString(),
                "database", "CONNECTED"
            );
        } catch (Exception e) {
            return Map.of(
                "status", "DOWN",
                "service", "LoginService",
                "timestamp", LocalDateTime.now().toString(),
                "database", "ERROR: " + e.getMessage()
            );
        }
    }
}
