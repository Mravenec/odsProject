package com.odsProject.odsProject.service;

import com.odsProject.odsProject.service.interfaces.IRoleAuthorizationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@Service
public class RoleAuthorizationService implements IRoleAuthorizationService {

    private static final String CONSULTOR_ROLE = "consultor";
    private static final String ERROR_CODE = "ROL_CONSULTOR_READONLY";
    private static final Set<String> BULK_EXPORT_ROLES = Set.of("admin", "evaluador", "consultor");

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Override
    public String extractRoleFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(signingKey())
                    .build()
                    .parseSignedClaims(authorizationHeader.substring(7))
                    .getPayload();
            Object rol = claims.get("rol");
            return rol != null ? rol.toString() : null;
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public boolean isWriteForbiddenForRole(String role) {
        return role != null && CONSULTOR_ROLE.equalsIgnoreCase(role);
    }

    @Override
    public String consultorReadonlyErrorCode() {
        return ERROR_CODE;
    }

    @Override
    public boolean canExportBulkProjects(String role) {
        return role != null && BULK_EXPORT_ROLES.contains(role.toLowerCase());
    }

    private SecretKey signingKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
}
