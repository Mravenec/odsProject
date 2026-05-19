package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.controller.interfaces.IMasterProjectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller Maestro de Proyectos
 * Expone endpoints REST para la gestión centralizada de proyectos en ods_master
 */
@RestController
@RequestMapping("/api/projects")
public class MasterProjectController implements IMasterProjectController {

    @Autowired
    private IMasterProjectService masterProjectService;

    @Override
    @GetMapping
    public ResponseEntity<List<Proyectos>> getAllProyectos() {
        return ResponseEntity.ok(masterProjectService.getAllProyectos());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Proyectos> getProyectoById(@PathVariable Integer id) {
        return masterProjectService.getProyectoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Proyectos>> getProyectosByUsuario(@PathVariable Integer userId) {
        return ResponseEntity.ok(masterProjectService.getProyectosByUsuario(userId));
    }

    @Override
    @PostMapping
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        return ResponseEntity.ok(masterProjectService.createProyecto(proyecto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer id, @RequestBody Proyectos proyecto) {
        proyecto.setId(id);
        return ResponseEntity.ok(masterProjectService.updateProyecto(proyecto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer id) {
        masterProjectService.deleteProyecto(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}/summary")
    public ResponseEntity<Map<String, Object>> getProjectSummary(@PathVariable Integer id) {
        return ResponseEntity.ok(masterProjectService.calculateProjectSummary(id));
    }

    @Override
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getGlobalDashboard() {
        return ResponseEntity.ok(masterProjectService.getGlobalDashboardData());
    }

    // ── Sprint 3 ─────────────────────────────────────────────────────────

    @Override
    @PostMapping("/full")
    public ResponseEntity<Map<String, Object>> createFullProject(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(masterProjectService.createFullProject(payload));
    }

    @Override
    @GetMapping("/{id}/ods")
    public ResponseEntity<List<Map<String, Object>>> getOdsByProyecto(@PathVariable Integer id) {
        return ResponseEntity.ok(masterProjectService.getOdsByProyecto(id));
    }

    // ── Sprint 8.3 ───────────────────────────────────────────────────────

    @Override
    @GetMapping("/with-ods")
    public ResponseEntity<List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>>
            getAllProyectosWithOds() {
        return ResponseEntity.ok(masterProjectService.getAllProyectosWithOds());
    }

    @Override
    @GetMapping("/user/{userId}/with-ods")
    public ResponseEntity<List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>>
            getProyectosWithOdsByUsuario(@PathVariable Integer userId) {
        return ResponseEntity.ok(masterProjectService.getProyectosWithOdsByUsuario(userId));
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 15 — Transición genérica de estado
    //  Sprint 16/17 — Endpoints semánticos para el flujo de auditoría
    //  Sprint 19 — Métricas para AuditQueuePage
    //
    //  Convención de errores (manejada por GlobalExceptionHandler + acá):
    //    400 → IllegalArgumentException  (datos faltantes / formato)
    //    403 → SecurityException         (rol no autorizado / no es dueño)
    //    409 → IllegalStateException     (transición inválida / precondición)
    // ═════════════════════════════════════════════════════════════════════

    @Override
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Map<String, Object>> changeProjectState(@PathVariable Integer id,
                                                                  @RequestBody Map<String, Object> body) {
        try {
            String estado = String.valueOf(body.getOrDefault("estado", ""));
            String observ = body.get("observaciones") != null ? String.valueOf(body.get("observaciones")) : null;
            Integer actor = toIntOrNull(body.get("actorUserId"));
            String role   = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "admin";
            Map<String, Object> r = masterProjectService.transitionState(id, estado, actor, role, observ);
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
                .body(Map.of("success", false, "error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.CONFLICT)
                .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @Override
    @PostMapping("/{id}/enviar-revision")
    public ResponseEntity<Map<String, Object>> enviarRevision(@PathVariable Integer id,
                                                              @RequestBody(required = false) Map<String, Object> body) {
        try {
            Integer actor = body != null ? toIntOrNull(body.get("actorUserId")) : null;
            Map<String, Object> r = masterProjectService.enviarARevision(id, actor);
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
                .body(Map.of("success", false, "error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.CONFLICT)
                .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @Override
    @PostMapping("/{id}/cerrar-auditoria")
    public ResponseEntity<Map<String, Object>> cerrarAuditoria(@PathVariable Integer id,
                                                               @RequestBody Map<String, Object> body) {
        try {
            Integer actor = toIntOrNull(body.get("actorUserId"));
            String role   = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "";
            String obs    = body.get("observaciones") != null ? String.valueOf(body.get("observaciones")) : null;
            Map<String, Object> r = masterProjectService.cerrarAuditoria(id, actor, role, obs);
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
                .body(Map.of("success", false, "error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.CONFLICT)
                .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @Override
    @PostMapping("/{id}/rechazar-auditoria")
    public ResponseEntity<Map<String, Object>> rechazarAuditoria(@PathVariable Integer id,
                                                                 @RequestBody Map<String, Object> body) {
        try {
            Integer actor = toIntOrNull(body.get("actorUserId"));
            String role   = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "";
            String motivo = body.get("motivoRechazo") != null ? String.valueOf(body.get("motivoRechazo")) : "";
            Map<String, Object> r = masterProjectService.rechazarAuditoria(id, actor, role, motivo);
            return ResponseEntity.ok(r);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
                .body(Map.of("success", false, "error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.CONFLICT)
                .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @Override
    @GetMapping("/audit/metrics")
    public ResponseEntity<Map<String, Object>> getAuditMetrics() {
        return ResponseEntity.ok(masterProjectService.getAuditQueueMetrics());
    }

    /** Conversión segura para los campos de body que el frontend manda como Integer o String. */
    private static Integer toIntOrNull(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        try { return Integer.parseInt(String.valueOf(v).trim()); }
        catch (NumberFormatException e) { return null; }
    }
}
