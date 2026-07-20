package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.service.interfaces.IPlanificacionEdicionService;
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

    @Autowired
    private IPlanificacionEdicionService planificacionEdicionService;

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
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer id, @RequestBody Map<String, Object> body) {
        try {
            Integer actorUserId = toIntOrNull(body.get("actorUserId"));
            String actorRole = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "";
            Proyectos proyecto = mapToProyectos(body);
            if (actorUserId != null && !actorRole.isBlank()) {
                return ResponseEntity.ok(
                        masterProjectService.updateProyecto(id, proyecto, actorUserId, actorRole));
            }
            proyecto.setId(id);
            return ResponseEntity.ok(masterProjectService.updateProyecto(proyecto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN).build();
        }
    }

    private Proyectos mapToProyectos(Map<String, Object> body) {
        Proyectos p = new Proyectos();
        if (body.get("usuarioId") != null) p.setUsuarioId(toIntOrNull(body.get("usuarioId")));
        if (body.get("sedeId") != null) p.setSedeId(toIntOrNull(body.get("sedeId")));
        if (body.get("nombreProyecto") != null) p.setNombreProyecto(String.valueOf(body.get("nombreProyecto")));
        if (body.get("descripcion") != null) p.setDescripcion(String.valueOf(body.get("descripcion")));
        if (body.get("metaGeneral") != null) p.setMetaGeneral(String.valueOf(body.get("metaGeneral")));
        if (body.get("aliadoExterno") != null) p.setAliadoExterno(String.valueOf(body.get("aliadoExterno")));
        if (body.get("locationProvince") != null) p.setLocationProvince(String.valueOf(body.get("locationProvince")));
        if (body.get("locationCanton") != null) p.setLocationCanton(String.valueOf(body.get("locationCanton")));
        if (body.get("locationDistrict") != null) p.setLocationDistrict(String.valueOf(body.get("locationDistrict")));
        return p;
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
    @PutMapping("/{id}/full")
    public ResponseEntity<Map<String, Object>> updateFullProject(@PathVariable Integer id,
                                                                 @RequestBody Map<String, Object> payload) {
        try {
            Integer actorUserId = toIntOrNull(payload.get("actorUserId"));
            String actorRole = payload.get("actorRole") != null ? String.valueOf(payload.get("actorRole")) : "";
            return ResponseEntity.ok(planificacionEdicionService.updateFullProject(
                    id, payload, actorUserId, actorRole));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @Override
    @GetMapping("/{id}/planificacion/editable")
    public ResponseEntity<Map<String, Object>> getPlanificacionEditable(@PathVariable Integer id,
                                                                      @RequestParam Integer actorUserId,
                                                                      @RequestParam String actorRole) {
        try {
            return ResponseEntity.ok(planificacionEdicionService.buildEditableSnapshot(
                    id, actorUserId, actorRole));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.FORBIDDEN)
                    .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @Override
    @GetMapping("/{id}/ods")
    public ResponseEntity<List<Map<String, Object>>> getOdsByProyecto(@PathVariable Integer id) {
        return ResponseEntity.ok(masterProjectService.getOdsByProyecto(id));
    }

    @Override
    @GetMapping("/{id}/sodsi")
    public ResponseEntity<Map<String, Object>> getSodsiFicha(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(masterProjectService.getSodsiFichaByProyectoId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
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

    @Override
    @GetMapping("/{id}/with-ods")
    public ResponseEntity<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
            getProyectoWithOdsById(@PathVariable Integer id) {
        return masterProjectService.getProyectoWithOdsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
    @PostMapping({"/{id}/enviar-evaluacion", "/{id}/enviar-revision"})
    public ResponseEntity<Map<String, Object>> enviarEvaluacion(@PathVariable Integer id,
                                                                @RequestBody(required = false) Map<String, Object> body) {
        try {
            Integer actor = body != null ? toIntOrNull(body.get("actorUserId")) : null;
            Map<String, Object> r = masterProjectService.enviarEvaluacion(id, actor);
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
    @PostMapping({"/{id}/aprobar-evaluacion", "/{id}/cerrar-auditoria"})
    public ResponseEntity<Map<String, Object>> aprobarEvaluacion(@PathVariable Integer id,
                                                                  @RequestBody Map<String, Object> body) {
        try {
            Integer actor = toIntOrNull(body.get("actorUserId"));
            String role   = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "";
            String obs    = body.get("observaciones") != null ? String.valueOf(body.get("observaciones")) : null;
            Map<String, Object> r = masterProjectService.aprobarEvaluacion(id, actor, role, obs);
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
    @PostMapping({"/{id}/rechazar-evaluacion", "/{id}/rechazar-auditoria"})
    public ResponseEntity<Map<String, Object>> rechazarEvaluacion(@PathVariable Integer id,
                                                                  @RequestBody Map<String, Object> body) {
        try {
            Integer actor = toIntOrNull(body.get("actorUserId"));
            String role   = body.get("actorRole") != null ? String.valueOf(body.get("actorRole")) : "";
            String motivo = body.get("motivoRechazo") != null ? String.valueOf(body.get("motivoRechazo")) : "";
            Map<String, Object> r = masterProjectService.rechazarEvaluacion(id, actor, role, motivo);
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
    @GetMapping({"/evaluacion/metrics", "/audit/metrics"})
    public ResponseEntity<Map<String, Object>> getEvaluationMetrics() {
        return ResponseEntity.ok(masterProjectService.getEvaluationQueueMetrics());
    }

    /** Conversión segura para los campos de body que el frontend manda como Integer o String. */
    private static Integer toIntOrNull(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        try { return Integer.parseInt(String.valueOf(v).trim()); }
        catch (NumberFormatException e) { return null; }
    }
}
