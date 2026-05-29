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
}
