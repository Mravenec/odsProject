package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoTransicionSolicitud;

import java.util.Optional;

public interface ITransicionPlanificacionRepository {

    Optional<ProyectoTransicionSolicitud> findPendienteByProyectoId(Integer proyectoId);

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
}
