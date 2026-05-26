package com.odsProject.odsProject.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odsProject.odsProject.service.interfaces.IRoleAuthorizationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Capa HTTP delgada: delega reglas de rol a {@link IRoleAuthorizationService}.
 */
@Component
public class RoleAuthorizationFilter extends OncePerRequestFilter {

    private static final Set<String> WRITE_METHODS = Set.of("POST", "PUT", "PATCH", "DELETE");

    private final IRoleAuthorizationService roleAuthorizationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RoleAuthorizationFilter(IRoleAuthorizationService roleAuthorizationService) {
        this.roleAuthorizationService = roleAuthorizationService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String method = request.getMethod();

        if (HttpMethod.OPTIONS.matches(method)
                || HttpMethod.GET.matches(method)
                || isLoginPath(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String role = roleAuthorizationService.extractRoleFromAuthorizationHeader(
                request.getHeader("Authorization"));

        if (isWriteMethod(method) && roleAuthorizationService.isWriteForbiddenForRole(role)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            objectMapper.writeValue(
                    response.getOutputStream(),
                    Map.of("error", roleAuthorizationService.consultorReadonlyErrorCode()));
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isLoginPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri != null && uri.startsWith("/api/login");
    }

    private static boolean isWriteMethod(String httpMethod) {
        return httpMethod != null && WRITE_METHODS.contains(httpMethod.toUpperCase());
    }
}
