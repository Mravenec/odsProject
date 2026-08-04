package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectoTransicionSolicitudEstadoDestino;
import com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectoTransicionSolicitudEstadoSolicitud;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoTransicionSolicitud;
import com.odsProject.odsProject.repository.interfaces.ITransicionPlanificacionRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.odsProject.odsProject.database.jooq.ods_master.tables.ProyectoTransicionSolicitud.PROYECTO_TRANSICION_SOLICITUD;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.Proyectos.PROYECTOS;

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
    public Optional<ProyectoTransicionSolicitud> findLatestByProyectoId(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_TRANSICION_SOLICITUD)
                .where(PROYECTO_TRANSICION_SOLICITUD.PROYECTO_ID.eq(proyectoId))
                .orderBy(PROYECTO_TRANSICION_SOLICITUD.CREATED_AT.desc())
                .limit(1)
                .fetchOptionalInto(ProyectoTransicionSolicitud.class);
    }

    @Override
    public List<Map<String, Object>> findRecientesResueltasByGestor(Integer gestorUserId, int limit) {
        int lim = Math.max(1, Math.min(limit, 50));
        return dsl.select(
                        PROYECTO_TRANSICION_SOLICITUD.ID,
                        PROYECTO_TRANSICION_SOLICITUD.PROYECTO_ID,
                        PROYECTO_TRANSICION_SOLICITUD.ESTADO_DESTINO,
                        PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD,
                        PROYECTO_TRANSICION_SOLICITUD.MOTIVO,
                        PROYECTO_TRANSICION_SOLICITUD.NOTA_RESOLUCION,
                        PROYECTO_TRANSICION_SOLICITUD.RESUELTO_EN,
                        PROYECTO_TRANSICION_SOLICITUD.CREATED_AT,
                        PROYECTOS.NOMBRE_PROYECTO,
                        PROYECTOS.ESTADO)
                .from(PROYECTO_TRANSICION_SOLICITUD)
                .join(PROYECTOS).on(PROYECTOS.ID.eq(PROYECTO_TRANSICION_SOLICITUD.PROYECTO_ID))
                .where(PROYECTOS.USUARIO_ID.eq(gestorUserId))
                .and(PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD.in(
                        ProyectoTransicionSolicitudEstadoSolicitud.aprobada,
                        ProyectoTransicionSolicitudEstadoSolicitud.rechazada))
                .orderBy(PROYECTO_TRANSICION_SOLICITUD.RESUELTO_EN.desc().nullsLast(),
                        PROYECTO_TRANSICION_SOLICITUD.CREATED_AT.desc())
                .limit(lim)
                .fetch()
                .stream()
                .map(this::toRecienteRow)
                .collect(Collectors.toList());
    }

    private Map<String, Object> toRecienteRow(Record r) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", r.get(PROYECTO_TRANSICION_SOLICITUD.ID));
        m.put("proyectoId", r.get(PROYECTO_TRANSICION_SOLICITUD.PROYECTO_ID));
        m.put("nombreProyecto", r.get(PROYECTOS.NOMBRE_PROYECTO));
        m.put("estadoProyecto", r.get(PROYECTOS.ESTADO) != null
                ? r.get(PROYECTOS.ESTADO).getLiteral() : null);
        m.put("estadoDestino", r.get(PROYECTO_TRANSICION_SOLICITUD.ESTADO_DESTINO) != null
                ? r.get(PROYECTO_TRANSICION_SOLICITUD.ESTADO_DESTINO).getLiteral() : null);
        m.put("estadoSolicitud", r.get(PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD) != null
                ? r.get(PROYECTO_TRANSICION_SOLICITUD.ESTADO_SOLICITUD).getLiteral() : null);
        m.put("motivo", r.get(PROYECTO_TRANSICION_SOLICITUD.MOTIVO));
        m.put("notaResolucion", r.get(PROYECTO_TRANSICION_SOLICITUD.NOTA_RESOLUCION));
        m.put("resueltoEn", r.get(PROYECTO_TRANSICION_SOLICITUD.RESUELTO_EN) != null
                ? r.get(PROYECTO_TRANSICION_SOLICITUD.RESUELTO_EN).toString() : null);
        m.put("createdAt", r.get(PROYECTO_TRANSICION_SOLICITUD.CREATED_AT) != null
                ? r.get(PROYECTO_TRANSICION_SOLICITUD.CREATED_AT).toString() : null);
        return m;
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
