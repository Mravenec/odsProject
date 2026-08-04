package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoTransicionSolicitud;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ITransicionPlanificacionRepository {

    Optional<ProyectoTransicionSolicitud> findPendienteByProyectoId(Integer proyectoId);

    /** Última solicitud del proyecto (cualquier estado), por created_at DESC. */
    Optional<ProyectoTransicionSolicitud> findLatestByProyectoId(Integer proyectoId);

    /**
     * Resoluciones recientes (aprobada/rechazada) de proyectos del gestor dueño.
     * Cada fila: mapa con campos de solicitud + nombreProyecto + estadoProyecto.
     */
    List<Map<String, Object>> findRecientesResueltasByGestor(Integer gestorUserId, int limit);

    ProyectoTransicionSolicitud insert(
            Integer proyectoId,
            Integer solicitadoPor,
            String estadoDestino,
            String motivo);

    ProyectoTransicionSolicitud resolver(
            Integer id,
            String estadoSolicitud,
            Integer resueltoPor,
            String notaResolucion);

    /**
     * Inserta resolución ya cerrada (p. ej. fuerza mayor activo→cancelado)
     * para que el gestor la vea en solicitudes/recientes.
     */
    ProyectoTransicionSolicitud insertResuelta(
            Integer proyectoId,
            Integer solicitadoPor,
            Integer resueltoPor,
            String estadoDestino,
            String motivo,
            String notaResolucion);
}
