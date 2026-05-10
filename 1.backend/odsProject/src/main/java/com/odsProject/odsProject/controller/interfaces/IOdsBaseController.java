package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Interfaz Controller Base para todos los ODS
 * Define los contratos REST comunes para todos los Objetivos de Desarrollo Sostenible
 * @param <T> Tipo de entidad de LECTURA (Enriquecida, ej: VistaAdminDetalleIndicadores)
 * @param <E> Tipo de entidad de ESCRITURA (Tabla física, ej: ProyectoIndicadores)
 * @param <P> Tipo de entidad Proyectos
 * @param <M> Tipo de entidad ProyectoIndicadorParametros
 * @param <MH> Tipo de entidad MedicionesHistoricas
 * @param <A> Tipo de entidad Auditoria
 */
public interface IOdsBaseController<T, E, P, M, MH, A> {

    // ── Endpoints de Indicadores ──

    /**
     * GET /api/ods/{odsId}/indicadores?proyectoId={id}
     * Obtiene todos los indicadores de un proyecto (Enriquecidos)
     */
    @GetMapping("/indicadores")
    ResponseEntity<List<T>> getIndicadores(
        @RequestParam Integer proyectoId
    );

    /**
     * GET /api/ods/{odsId}/indicadores/{indicadorId}
     * Obtiene un indicador específico (Enriquecido)
     */
    @GetMapping("/indicadores/{indicadorId}")
    ResponseEntity<T> getIndicador(
        @PathVariable Integer indicadorId
    );

    /**
     * POST /api/ods/{odsId}/indicadores
     * Crea un nuevo indicador
     */
    @PostMapping("/indicadores")
    ResponseEntity<E> createIndicador(
        @RequestBody E indicador
    );

    /**
     * PUT /api/ods/{odsId}/indicadores/{indicadorId}
     * Actualiza un indicador existente
     */
    @PutMapping("/indicadores/{indicadorId}")
    ResponseEntity<E> updateIndicador(
        @PathVariable Integer indicadorId,
        @RequestBody E indicador
    );

    /**
     * DELETE /api/ods/{odsId}/indicadores/{indicadorId}
     * Elimina un indicador
     */
    @DeleteMapping("/indicadores/{indicadorId}")
    ResponseEntity<Void> deleteIndicador(
        @PathVariable Integer indicadorId
    );

    // ── Endpoints de Proyectos ──

    /**
     * GET /api/ods/{odsId}/proyectos
     * Obtiene todos los proyectos del ODS
     */
    @GetMapping("/proyectos")
    ResponseEntity<List<P>> getProyectos();

    /**
     * GET /api/ods/{odsId}/proyectos/{proyectoId}
     * Obtiene un proyecto específico
     */
    @GetMapping("/proyectos/{proyectoId}")
    ResponseEntity<P> getProyecto(
        @PathVariable Integer proyectoId
    );

    /**
     * POST /api/ods/{odsId}/proyectos
     * Crea un nuevo proyecto
     */
    @PostMapping("/proyectos")
    ResponseEntity<P> createProyecto(
        @RequestBody P proyecto
    );

    /**
     * PUT /api/ods/{odsId}/proyectos/{proyectoId}
     * Actualiza un proyecto existente
     */
    @PutMapping("/proyectos/{proyectoId}")
    ResponseEntity<P> updateProyecto(
        @PathVariable Integer proyectoId,
        @RequestBody P proyecto
    );

    /**
     * DELETE /api/ods/{odsId}/proyectos/{proyectoId}
     * Elimina un proyecto
     */
    @DeleteMapping("/proyectos/{proyectoId}")
    ResponseEntity<Void> deleteProyecto(
        @PathVariable Integer proyectoId
    );

    // ── Endpoints de Metas ──

    /**
     * GET /api/ods/{odsId}/metas?proyectoId={id}
     * Obtiene todas las metas de un proyecto
     */
    @GetMapping("/metas")
    ResponseEntity<List<M>> getMetasProyecto(
        @RequestParam Integer proyectoId
    );

    /**
     * GET /api/ods/{odsId}/metas/{metaId}
     * Obtiene una meta específica
     */
    @GetMapping("/metas/{metaId}")
    ResponseEntity<M> getMetaProyecto(
        @PathVariable Integer metaId
    );

    /**
     * POST /api/ods/{odsId}/metas
     * Crea una nueva meta
     */
    @PostMapping("/metas")
    ResponseEntity<M> createMetaProyecto(
        @RequestBody M meta
    );

    /**
     * PUT /api/ods/{odsId}/metas/{metaId}
     * Actualiza una meta existente
     */
    @PutMapping("/metas/{metaId}")
    ResponseEntity<M> updateMetaProyecto(
        @PathVariable Integer metaId,
        @RequestBody M meta
    );

    /**
     * DELETE /api/ods/{odsId}/metas/{metaId}
     * Elimina una meta
     */
    @DeleteMapping("/metas/{metaId}")
    ResponseEntity<Void> deleteMetaProyecto(
        @PathVariable Integer metaId
    );

    // ── Endpoints de Mediciones ──

    /**
     * GET /api/ods/{odsId}/mediciones?indicadorId={id}
     * Obtiene todas las mediciones de un indicador
     */
    @GetMapping("/mediciones")
    ResponseEntity<List<MH>> getMedicionesHistoricas(
        @RequestParam Integer indicadorId
    );

    /**
     * GET /api/ods/{odsId}/mediciones/{medicionId}
     * Obtiene una medición específica
     */
    @GetMapping("/mediciones/{medicionId}")
    ResponseEntity<MH> getMedicionHistorica(
        @PathVariable Integer medicionId
    );

    /**
     * POST /api/ods/{odsId}/mediciones
     * Crea una nueva medición histórica
     */
    @PostMapping("/mediciones")
    ResponseEntity<MH> createMedicionHistorica(
        @RequestBody MH medicion
    );

    /**
     * PUT /api/ods/{odsId}/mediciones/{medicionId}
     * Actualiza una medición histórica
     */
    @PutMapping("/mediciones/{medicionId}")
    ResponseEntity<MH> updateMedicionHistorica(
        @PathVariable Integer medicionId,
        @RequestBody MH medicion
    );

    /**
     * DELETE /api/ods/{odsId}/mediciones/{medicionId}
     * Elimina una medición histórica
     */
    @DeleteMapping("/mediciones/{medicionId}")
    ResponseEntity<Void> deleteMedicionHistorica(
        @PathVariable Integer medicionId
    );

    // ── Endpoints de Utilidades ──

    /**
     * GET /api/ods/{odsId}/dashboard
     * Obtiene datos del dashboard administrativo para este ODS
     */
    @GetMapping("/dashboard")
    ResponseEntity<java.util.Map<String, Object>> getDashboard();

    /**
     * GET /api/ods/{odsId}/estadisticas
     * Obtiene estadísticas generales del ODS
     */
    @GetMapping("/estadisticas")
    ResponseEntity<java.util.Map<String, Object>> getEstadisticas();

    /**
     * POST /api/ods/{odsId}/validar/indicador
     * Valida los datos de un indicador
     */
    @PostMapping("/validar/indicador")
    ResponseEntity<Boolean> validateIndicador(
        @RequestBody T indicador
    );

    /**
     * POST /api/ods/{odsId}/validar/proyecto
     * Valida los datos de un proyecto
     */
    @PostMapping("/validar/proyecto")
    ResponseEntity<Boolean> validateProyecto(
        @RequestBody P proyecto
    );

    /**
     * GET /api/ods/{odsId}/progreso/{proyectoId}
     * Calcula el progreso de un proyecto
     */
    @GetMapping("/progreso/{proyectoId}")
    ResponseEntity<Double> getProjectProgress(
        @PathVariable Integer proyectoId
    );

    // ── Endpoints de Verificación de Existencia ──

    /**
     * GET /api/ods/{odsId}/proyecto/{proyectoId}/existe
     * Verifica si un proyecto existe
     */
    @GetMapping("/proyecto/{proyectoId}/existe")
    ResponseEntity<Boolean> existsProyecto(
        @PathVariable Integer proyectoId
    );

    /**
     * GET /api/ods/{odsId}/indicador/{indicadorId}/existe
     * Verifica si un indicador existe
     */
    @GetMapping("/indicador/{indicadorId}/existe")
    ResponseEntity<Boolean> existsIndicador(
        @PathVariable Integer indicadorId
    );

    /**
     * GET /api/ods/{odsId}/meta/{metaId}/existe
     * Verifica si una meta existe
     */
    @GetMapping("/meta/{metaId}/existe")
    ResponseEntity<Boolean> existsMetaProyecto(
        @PathVariable Integer metaId
    );

    /**
     * GET /api/ods/{odsId}/medicion/{medicionId}/existe
     * Verifica si una medición histórica existe
     */
    @GetMapping("/medicion/{medicionId}/existe")
    ResponseEntity<Boolean> existsMedicionHistorica(
        @PathVariable Integer medicionId
    );

    // ── Sprint 2 / 5: Medición auditada y traza ────────────────────────────

    /**
     * POST /api/ods/{odsId}/mediciones/auditada
     *
     * Body (Map plano, sin DTO):
     *   {
     *     "proyectoIndicadorId": 123,
     *     "fechaMedicion": "2026-05-10",
     *     "responsable": "Juan Pérez",
     *     "metodoMedicion": "encuesta",
     *     "observaciones": "...",
     *     "valoresParametros": { "<parametroId>": <valor>, ... }
     *   }
     *
     * El backend recalcula valor_calculado a partir de la fórmula del indicador,
     * persiste medición + valores de parámetros en una sola transacción y devuelve
     * el resultado con meta_alcanzada.
     */
    @PostMapping("/mediciones/auditada")
    ResponseEntity<java.util.Map<String, Object>> createMedicionAuditada(
        @RequestBody java.util.Map<String, Object> payload
    );

    /**
     * GET /api/ods/{odsId}/mediciones/{medicionId}/auditoria
     * Devuelve la traza completa de una medición:
     *   fórmula vigente, valores por parámetro, valor calculado y meta_alcanzada.
     */
    @GetMapping("/mediciones/{medicionId}/auditoria")
    ResponseEntity<java.util.Map<String, Object>> getMedicionAuditoria(
        @PathVariable Integer medicionId
    );
}
