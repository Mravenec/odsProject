package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz para el Servicio Maestro de Proyectos
 * Maneja la lógica de negocio central de gestión de proyectos multi-ods
 */
public interface IMasterProjectService {

    // CRUD Básico
    List<Proyectos> getAllProyectos();
    Optional<Proyectos> getProyectoById(Integer id);
    List<Proyectos> getProyectosByUsuario(Integer usuarioId);
    Proyectos createProyecto(Proyectos proyecto);
    Proyectos updateProyecto(Proyectos proyecto);
    void deleteProyecto(Integer id);

    // Lógica de Evaluación
    /**
     * Calcula el progreso de un proyecto sumando los resultados de todos los ODS vinculados
     * @param proyectoId ID del proyecto
     * @return Mapa con el resumen de progreso (total indicators, achieved, percentage)
     */
    Map<String, Object> calculateProjectSummary(Integer proyectoId);

    /**
     * Obtiene el resumen consolidado de todo el ecosistema ODS
     * Agrega datos de proyectos, sedes e indicadores globales
     * @return Mapa con el Dashboard Maestro
     */
    Map<String, Object> getGlobalDashboardData();

    /**
     * Evalúa un indicador específico dentro de un proyecto ODS
     * Actualiza el valor_actual basado en los parámetros cargados
     * 
     * @param proyectoId ID del proyecto
     * @param odsId ID del ODS (1-17)
     * @param indicadorId ID del indicador en proyecto_indicadores
     * @return El nuevo valor calculado
     */
    Double evaluateProjectIndicator(Integer proyectoId, Integer odsId, Integer indicadorId);

    // ── Sprint 3: Orquestador transaccional de creación completa ─────────

    /**
     * Crea de forma atómica un proyecto con sus ODS vinculados, indicadores y
     * parámetros. Si una etapa falla, ejecuta compensaciones (borra lo creado
     * hasta ahí) y devuelve un resumen con los errores.
     *
     * Payload esperado:
     * {
     *   "proyecto":      { ... campos de Proyectos ... },
     *   "odsIds":        [1, 3, 17],
     *   "primaryOdsId":  17,
     *   "indicadores":   [
     *     { "odsId": 17, "indicadorMasterId": 142, "metaValor": 80, "metaUnidad": "%",
     *       "metaNombre": "...", "formulaCustom": "(a+b)/100",
     *       "parametros": [ {"nombreParametro":"a","tipoDato":"Integer"}, ... ] }
     *   ]
     * }
     *
     * Devuelve:
     * {
     *   "proyectoId": 42,
     *   "odsVinculados": [1, 3, 17],
     *   "indicadoresCreados": [{ odsId, codigo, proyectoIndicadorId, parametros }],
     *   "errores": []
     * }
     */
    Map<String, Object> createFullProject(Map<String, Object> payload);

    /**
     * Devuelve la lista de ODS vinculados a un proyecto.
     */
    List<Map<String, Object>> getOdsByProyecto(Integer proyectoId);

    // ── Sprint 8.3: Listados enriquecidos con ODS ────────────────────────

    /**
     * Lista todos los proyectos con su ODS primario y los ODS vinculados.
     * Reemplaza al getAllProyectos cuando el frontend necesita mostrar el ODS
     * en el listado (la vista de ProjectListPage).
     */
    List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
        getAllProyectosWithOds();

    /**
     * Lista los proyectos de un usuario con su ODS primario y ODS vinculados.
     */
    List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
        getProyectosWithOdsByUsuario(Integer usuarioId);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 15 — State Machine de transiciones del proyecto
    // ═════════════════════════════════════════════════════════════════════

    /**
     * Transición genérica con validación. Devuelve el proyecto actualizado.
     * Lanza:
     *   - IllegalStateException si la transición no es permitida por la máquina de estados
     *   - SecurityException si el rol del actor no autoriza esta transición
     *   - IllegalArgumentException si los datos requeridos no llegaron
     */
    Map<String, Object> transitionState(Integer proyectoId,
                                        String nuevoEstado,
                                        Integer actorUserId,
                                        String actorRole,
                                        String observaciones);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 16 — Cierre del gestor
    // ═════════════════════════════════════════════════════════════════════

    /**
     * "Enviar a auditoría". Solo el dueño del proyecto puede llamarlo.
     * Precondiciones validadas:
     *   - El proyecto está en estado 'activo' (planificacion también se acepta para flexibilidad)
     *   - El proyecto tiene ≥ 1 indicador configurado
     *   - El proyecto tiene ≥ 1 documento de evidencia subido
     * Efecto:
     *   - estado → 'en_revision'
     *   - fecha_envio_revision = NOW()
     */
    Map<String, Object> enviarARevision(Integer proyectoId, Integer gestorUserId);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 17 — Cierre del auditor
    // ═════════════════════════════════════════════════════════════════════

    /**
     * "Cerrar auditoría" (aprobar). Solo admin/auditor.
     * Precondiciones:
     *   - role ∈ {admin, auditor}
     *   - proyecto.estado == 'en_revision'
     *   - allIndicadoresTienenMedicion == true
     * Efecto:
     *   - estado → 'completado'
     *   - auditado_por = actorUserId, auditado_en = NOW()
     *   - observaciones_cierre = texto opcional de la firma
     */
    Map<String, Object> cerrarAuditoria(Integer proyectoId,
                                        Integer auditorUserId,
                                        String auditorRole,
                                        String observaciones);

    /**
     * "Rechazar auditoría". Devuelve a 'activo' con motivo obligatorio.
     */
    Map<String, Object> rechazarAuditoria(Integer proyectoId,
                                          Integer auditorUserId,
                                          String auditorRole,
                                          String motivoRechazo);

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 19 — Métricas para AuditQueuePage
    // ═════════════════════════════════════════════════════════════════════

    /**
     * Devuelve { pendientes, enCurso, auditadosMes, tiempoPromedioHoras }.
     */
    Map<String, Object> getAuditQueueMetrics();
}
