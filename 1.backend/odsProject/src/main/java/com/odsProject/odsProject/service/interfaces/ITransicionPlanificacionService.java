package com.odsProject.odsProject.service.interfaces;

import java.util.Map;

public interface ITransicionPlanificacionService {

    Map<String, Object> crearSolicitud(Integer proyectoId, Integer actorUserId, String estadoDestino, String motivo);

    Map<String, Object> obtenerPendiente(Integer proyectoId, Integer actorUserId, String actorRole);

    Map<String, Object> aprobar(Integer proyectoId, Integer actorUserId, String actorRole, String nota);

    Map<String, Object> rechazar(Integer proyectoId, Integer actorUserId, String actorRole, String nota);

    Map<String, Object> fuerzaMayor(Integer proyectoId, Integer actorUserId, String actorRole, String motivo);

    /** Resoluciones recientes (aprobada/rechazada) para el gestor dueño. */
    Map<String, Object> listarRecientes(Integer actorUserId, String actorRole);
}
