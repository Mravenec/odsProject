package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectoTransicionSolicitudEstadoDestino;
import com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectoTransicionSolicitudEstadoSolicitud;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoTransicionSolicitud;
import com.odsProject.odsProject.repository.interfaces.ITransicionPlanificacionRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods_master.tables.ProyectoTransicionSolicitud.PROYECTO_TRANSICION_SOLICITUD;

@Repository
public class TransicionPlanificacionRepository implements ITransicionPlanificacionRepository {

    @Autowired
    @Qualifier("dslOdsMaster")
    private DSLContext dsl;

    @Override
    public Optional<ProyectoTransicionSolicitud> findPendienteByProyectoId(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_TRANSICION_SOLICITUD)
                .where(PROYECTO_TRANSICION_SOLICITUD.PROYECTO_ID.eq(proyectoId))
                .and(PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD.eq(
                        ProyectoTransicionSolicitudEstadoSolicitud.pendiente))
                .fetchOptionalInto(ProyectoTransicionSolicitud.class);
    }

    @Override
    public ProyectoTransicionSolicitud insert(
            Integer proyectoId,
            Integer solicitadoPor,
            String estadoDestino,
            String motivo) {
        ProyectoTransicionSolicitudEstadoDestino dest = ProyectoTransicionSolicitudEstadoDestino
                .lookupLiteral(estadoDestino.toLowerCase());
        if (dest == null)
            throw new IllegalArgumentException("estadoDestino inválido: " + estadoDestino);
        return dsl.insertInto(PROYECTO_TRANSICION_SOLICITUD)
                .set(PROYECTO_TRANSICION_SOLICITUD.PROYECTO_ID, proyectoId)
                .set(PROYECTO_TRANSICION_SOLICITUD.SOLICITADO_POR, solicitadoPor)
                .set(PROYECTO_TRANSICION_SOLICITUD.ESTADO_DESTINO, dest)
                .set(PROYECTO_TRANSICION_SOLICITUD.MOTIVO, motivo)
                .set(PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD,
                        ProyectoTransicionSolicitudEstadoSolicitud.pendiente)
                .returning()
                .fetchOneInto(ProyectoTransicionSolicitud.class);
    }

    @Override
    public ProyectoTransicionSolicitud resolver(
            Integer id,
            String estadoSolicitud,
            Integer resueltoPor,
            String notaResolucion) {
        ProyectoTransicionSolicitudEstadoSolicitud est = ProyectoTransicionSolicitudEstadoSolicitud
                .lookupLiteral(estadoSolicitud.toLowerCase());
        if (est == null)
            throw new IllegalArgumentException("estadoSolicitud inválido");
        dsl.update(PROYECTO_TRANSICION_SOLICITUD)
                .set(PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD, est)
                .set(PROYECTO_TRANSICION_SOLICITUD.RESUELTO_POR, resueltoPor)
                .set(PROYECTO_TRANSICION_SOLICITUD.RESUELTO_EN, LocalDateTime.now())
                .set(PROYECTO_TRANSICION_SOLICITUD.NOTA_RESOLUCION, notaResolucion)
                .where(PROYECTO_TRANSICION_SOLICITUD.ID.eq(id))
                .execute();
        return dsl.selectFrom(PROYECTO_TRANSICION_SOLICITUD)
                .where(PROYECTO_TRANSICION_SOLICITUD.ID.eq(id))
                .fetchOneInto(ProyectoTransicionSolicitud.class);
    }
}
