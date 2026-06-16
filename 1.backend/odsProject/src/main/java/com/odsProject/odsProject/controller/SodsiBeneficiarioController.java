package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.controller.interfaces.ISodsiBeneficiarioController;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioValor;
import com.odsProject.odsProject.service.interfaces.ILoginService;
import com.odsProject.odsProject.service.interfaces.IRoleAuthorizationService;
import com.odsProject.odsProject.service.interfaces.ISodsiBeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class SodsiBeneficiarioController implements ISodsiBeneficiarioController {

    @Autowired
    private ISodsiBeneficiarioService sodsiBeneficiarioService;

    @Autowired
    private ILoginService loginService;

    @Autowired
    private IRoleAuthorizationService roleAuthorizationService;

    @Override
    public ResponseEntity<List<Map<String, Object>>> listValores(String activoFilter, String authorization) {
        String role = roleAuthorizationService.extractRoleFromAuthorizationHeader(authorization);
        boolean isAdmin = isAdminRole(role);
        boolean activosOnly = activoFilter == null
                || "true".equalsIgnoreCase(activoFilter)
                || "1".equals(activoFilter);
        boolean adminView = isAdmin && ("all".equalsIgnoreCase(activoFilter) || "false".equalsIgnoreCase(activoFilter));

        List<SodsiBeneficiarioValor> valores = sodsiBeneficiarioService.listValores(activosOnly, adminView);
        List<Map<String, Object>> out = valores.stream()
                .map(sodsiBeneficiarioService::toMap)
                .collect(Collectors.toList());
        return ResponseEntity.ok(out);
    }

    @Override
    public ResponseEntity<Map<String, Object>> crearValor(Map<String, Object> body, String authorization) {
        var userOpt = loginService.validateToken(authorization);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        try {
            Integer categoriaId = intField(body, "categoriaId");
            String nombre = body.get("nombre") != null ? String.valueOf(body.get("nombre")) : null;
            SodsiBeneficiarioValor created = sodsiBeneficiarioService.crearValorPersonalizado(
                    categoriaId, nombre, userOpt.get().getId());
            return ResponseEntity.status(201).body(sodsiBeneficiarioService.toMap(created));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("error", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> setActivo(Integer id, Map<String, Object> body, String authorization) {
        String role = roleAuthorizationService.extractRoleFromAuthorizationHeader(authorization);
        if (!isAdminRole(role)) {
            return ResponseEntity.status(403).body(Map.of("error", "Solo administrador"));
        }
        if (loginService.validateToken(authorization).isEmpty()) {
            return ResponseEntity.status(401).build();
        }
        boolean activo = true;
        if (body != null && body.containsKey("activo")) {
            Object raw = body.get("activo");
            activo = raw instanceof Boolean b ? b : Boolean.parseBoolean(String.valueOf(raw));
        }
        try {
            SodsiBeneficiarioValor updated = sodsiBeneficiarioService.setActivo(id, activo);
            return ResponseEntity.ok(sodsiBeneficiarioService.toMap(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private static boolean isAdminRole(String role) {
        return role != null && "admin".equalsIgnoreCase(role);
    }

    private static Integer intField(Map<String, Object> body, String key) {
        if (body == null || !body.containsKey(key) || body.get(key) == null) return null;
        Object v = body.get(key);
        if (v instanceof Number n) return n.intValue();
        return Integer.parseInt(String.valueOf(v));
    }
}
