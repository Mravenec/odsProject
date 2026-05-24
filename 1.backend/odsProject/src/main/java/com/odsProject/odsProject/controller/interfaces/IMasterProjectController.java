package com.odsProject.odsProject.controller.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Interfaz para el Controller Maestro de Proyectos
 * Expone endpoints REST para la gestión centralizada de proyectos
 */
@RequestMapping("/api/projects")
public interface IMasterProjectController {

    @GetMapping
    ResponseEntity<List<Proyectos>> getAllProyectos();

    @GetMapping("/{id}")
    ResponseEntity<Proyectos> getProyectoById(@PathVariable Integer id);

    @GetMapping("/user/{userId}")
    ResponseEntity<List<Proyectos>> getProyectosByUsuario(@PathVariable Integer userId);

    @PostMapping
    ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto);

    @PutMapping("/{id}")
    ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer id, @RequestBody Proyectos proyecto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProyecto(@PathVariable Integer id);

    @GetMapping("/{id}/summary")
    ResponseEntity<Map<String, Object>> getProjectSummary(@PathVariable Integer id);

    @GetMapping("/dashboard")
    ResponseEntity<Map<String, Object>> getGlobalDashboard();

    // ── Sprint 3: Endpoints para orquestación completa ───────────────────

    /**
     * POST /api/projects/full
     * Crea un proyecto con ODS vinculados, indicadores y parámetros en una sola
     * llamada. Devuelve un resumen con errores granulares si algo falló a mitad.
     */
    @PostMapping("/full")
    ResponseEntity<Map<String, Object>> createFullProject(@RequestBody Map<String, Object> payload);

    /**
     * GET /api/projects/{id}/ods
     * Devuelve los ODS vinculados a un proyecto (con bandera es_primario).
     */
    @GetMapping("/{id}/ods")
    ResponseEntity<List<Map<String, Object>>> getOdsByProyecto(@PathVariable Integer id);

    // ── Sprint 8.3: Listados enriquecidos con ODS ────────────────────────

    /**
     * GET /api/projects/with-ods
     * Igual que GET /api/projects pero incluye odsPrimario y odsVinculados.
     * Es lo que la pantalla de listado debería consumir.
     */
    @GetMapping("/with-ods")
    ResponseEntity<List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>>
        getAllProyectosWithOds();

    /**
     * GET /api/projects/user/{userId}/with-ods
     * Proyectos de un usuario con info de ODS para el listado.
     */
    @GetMapping("/user/{userId}/with-ods")
    ResponseEntity<List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>>
        getProyectosWithOdsByUsuario(@PathVariable Integer userId);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 15 — Transición genérica de estado (admin-only fallback)
    // ═════════════════════════════════════════════════════════════════════

    /**
     * PATCH /api/projects/{id}/estado
     * Endpoint genérico para casos administrativos. El cuerpo es
     * { "estado": "...", "observaciones": "...", "actorUserId": N, "actorRole": "..." }
     * En 99 % de los flujos se prefieren los endpoints semánticos:
     *   POST /enviar-revision, /cerrar-auditoria, /rechazar-auditoria.
     */
    @PatchMapping("/{id}/estado")
    ResponseEntity<Map<String, Object>> changeProjectState(@PathVariable Integer id,
                                                           @RequestBody Map<String, Object> body);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 16 — Gestor envía proyecto a auditoría
    // ═════════════════════════════════════════════════════════════════════

    /**
     * POST /api/projects/{id}/enviar-revision
     * Body: { "actorUserId": N }  ← el gestor dueño del proyecto.
     * Valida:
     *   - proyecto.estado IN (activo)
     *   - actorUserId == proyecto.usuario_id (solo el dueño puede enviar)
     *   - countIndicadores > 0
     *   - countDocumentos  > 0
     * Efecto: estado → 'en_revision', fecha_envio_revision = NOW().
     */
    @PostMapping({"/{id}/enviar-evaluacion", "/{id}/enviar-revision"})
    ResponseEntity<Map<String, Object>> enviarEvaluacion(@PathVariable Integer id,
                                                         @RequestBody(required = false) Map<String, Object> body);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 17 — Auditor cierra (aprueba o rechaza)
    // ═════════════════════════════════════════════════════════════════════

    /**
     * POST /api/projects/{id}/cerrar-auditoria
     * Body: { "actorUserId": N, "actorRole": "admin|auditor", "observaciones": "..." }
     * Valida:
     *   - role ∈ {admin, auditor}
     *   - proyecto.estado == 'en_revision'
     *   - allIndicadoresTienenMedicion() == true
     * Efecto: estado → 'completado', auditado_por = actor, auditado_en = NOW().
     */
    @PostMapping({"/{id}/aprobar-evaluacion", "/{id}/cerrar-auditoria"})
    ResponseEntity<Map<String, Object>> aprobarEvaluacion(@PathVariable Integer id,
                                                          @RequestBody Map<String, Object> body);

    /**
     * POST /api/projects/{id}/rechazar-auditoria
     * Body: { "actorUserId": N, "actorRole": "...", "motivoRechazo": "≥10 chars" }
     * Devuelve el proyecto a 'activo' con observaciones_cierre = motivoRechazo
     * para que el gestor lo lea como banner.
     */
    @PostMapping({"/{id}/rechazar-evaluacion", "/{id}/rechazar-auditoria"})
    ResponseEntity<Map<String, Object>> rechazarEvaluacion(@PathVariable Integer id,
                                                             @RequestBody Map<String, Object> body);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 19 — Métricas para el panel del AuditQueuePage
    // ═════════════════════════════════════════════════════════════════════

    /**
     * GET /api/projects/audit/metrics
     * Devuelve { pendientes, enCurso, auditadosMes, tiempoPromedioHoras }.
     */
    @GetMapping({"/evaluacion/metrics", "/audit/metrics"})
    ResponseEntity<Map<String, Object>> getEvaluationMetrics();
}
