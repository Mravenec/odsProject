package com.odsProject.odsProject.service.interfaces;

import java.util.Map;

/**
 * Edición atómica de proyectos en estado planificacion (cabecera + ODS + indicadores).
 * Orquesta todos los ODS vía servicios master — sin mutar controllers por ODS.
 */
public interface IPlanificacionEdicionService {

    void assertCanEditPlanificacion(Integer proyectoId, Integer actorUserId, String actorRole);

    Map<String, Object> buildEditableSnapshot(Integer proyectoId, Integer actorUserId, String actorRole);

    Map<String, Object> updateFullProject(Integer proyectoId, Map<String, Object> payload,
                                          Integer actorUserId, String actorRole);

    /** Persiste escalares Proyectos + relaciones N:M desde mapa fichaSodsi (POJOs JOOQ). */
    void saveFichaSodsi(Integer proyectoId, Map<String, Object> ficha);
}
