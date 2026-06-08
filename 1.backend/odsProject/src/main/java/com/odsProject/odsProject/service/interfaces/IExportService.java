package com.odsProject.odsProject.service.interfaces;

/**
 * Exportación Excel de proyectos (consultor / planificación).
 */
public interface IExportService {

    /**
     * Excel de resumen de un proyecto completado (3 hojas).
     * @throws IllegalArgumentException proyecto inexistente
     * @throws IllegalStateException proyecto no completado (409 en controller)
     */
    byte[] exportProyecto(Integer proyectoId);

    /** Consolidado de proyectos completados agrupado por sede. */
    byte[] exportPlanificacionConsolidado();

    /**
     * Consolidado con filtros opcionales (consultor / planificación).
     * @param sedeId filtra por sede del proyecto; null = todas
     * @param userId filtra por gestor (usuario_id); null = todos
     */
    byte[] exportPlanificacionConsolidado(Integer sedeId, Integer userId);

    /**
     * Excel de proyectos evaluados (completados) en una sede y año de cierre.
     * El año se calcula con {@code YEAR(auditado_en)}.
     *
     * @throws IllegalArgumentException sedeId o anio nulos / inválidos
     */
    byte[] exportProyectosEvaluadosPorSedeYAnio(Integer sedeId, Integer anio);
}
