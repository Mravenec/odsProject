package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectosEstado;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoChatMensajes;
import com.odsProject.odsProject.repository.interfaces.IChatMensajeRepository;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.odsProject.odsProject.database.jooq.ods_master.tables.ProyectoChatMensajes.PROYECTO_CHAT_MENSAJES;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.Proyectos.PROYECTOS;

@Repository
public class ChatMensajeRepository implements IChatMensajeRepository {

    @Autowired
    @Qualifier("dslOdsMaster")
    private DSLContext dsl;

    @Override
    public List<ProyectoChatMensajes> findByProyectoId(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_CHAT_MENSAJES)
                .where(PROYECTO_CHAT_MENSAJES.PROYECTO_ID.eq(proyectoId))
                .and(PROYECTO_CHAT_MENSAJES.ELIMINADO.eq((byte) 0))
                .orderBy(PROYECTO_CHAT_MENSAJES.CREATED_AT.asc())
                .fetchInto(ProyectoChatMensajes.class);
    }

    @Override
    public Optional<ProyectoChatMensajes> findById(Integer id) {
        return dsl.selectFrom(PROYECTO_CHAT_MENSAJES)
                .where(PROYECTO_CHAT_MENSAJES.ID.eq(id))
                .fetchOptionalInto(ProyectoChatMensajes.class);
    }

    @Override
    public ProyectoChatMensajes insert(Integer proyectoId, Integer autorId, String cuerpo) {
        return dsl.insertInto(PROYECTO_CHAT_MENSAJES)
                .set(PROYECTO_CHAT_MENSAJES.PROYECTO_ID, proyectoId)
                .set(PROYECTO_CHAT_MENSAJES.AUTOR_ID, autorId)
                .set(PROYECTO_CHAT_MENSAJES.CUERPO, cuerpo)
                .returning()
                .fetchOneInto(ProyectoChatMensajes.class);
    }

    @Override
    public ProyectoChatMensajes updateCuerpo(Integer id, String cuerpo, int editCount) {
        dsl.update(PROYECTO_CHAT_MENSAJES)
                .set(PROYECTO_CHAT_MENSAJES.CUERPO, cuerpo)
                .set(PROYECTO_CHAT_MENSAJES.EDITED_AT, LocalDateTime.now())
                .set(PROYECTO_CHAT_MENSAJES.EDIT_COUNT, editCount)
                .where(PROYECTO_CHAT_MENSAJES.ID.eq(id))
                .execute();
        return findById(id).orElseThrow();
    }

    @Override
    public List<Map<String, Object>> findInboxThreadsPlanificacion() {
        var latestPerProject = dsl
                .select(
                        PROYECTO_CHAT_MENSAJES.PROYECTO_ID,
                        DSL.max(PROYECTO_CHAT_MENSAJES.ID).as("max_id"),
                        DSL.count().as("msg_count"))
                .from(PROYECTO_CHAT_MENSAJES)
                .join(PROYECTOS).on(PROYECTOS.ID.eq(PROYECTO_CHAT_MENSAJES.PROYECTO_ID))
                .where(PROYECTOS.ESTADO.eq(ProyectosEstado.planificacion))
                .and(PROYECTO_CHAT_MENSAJES.ELIMINADO.eq((byte) 0))
                .groupBy(PROYECTO_CHAT_MENSAJES.PROYECTO_ID)
                .asTable("latest");

        return dsl
                .select(
                        PROYECTOS.ID,
                        PROYECTOS.NOMBRE_PROYECTO,
                        PROYECTOS.USUARIO_ID,
                        PROYECTOS.ESTADO,
                        PROYECTO_CHAT_MENSAJES.ID,
                        PROYECTO_CHAT_MENSAJES.AUTOR_ID,
                        PROYECTO_CHAT_MENSAJES.CUERPO,
                        PROYECTO_CHAT_MENSAJES.CREATED_AT,
                        latestPerProject.field("msg_count", Integer.class))
                .from(PROYECTO_CHAT_MENSAJES)
                .join(latestPerProject)
                .on(PROYECTO_CHAT_MENSAJES.ID.eq(latestPerProject.field("max_id", Integer.class)))
                .join(PROYECTOS).on(PROYECTOS.ID.eq(PROYECTO_CHAT_MENSAJES.PROYECTO_ID))
                .orderBy(PROYECTO_CHAT_MENSAJES.CREATED_AT.desc())
                .fetch()
                .stream()
                .map(this::mapInboxRow)
                .collect(Collectors.toList());
    }

    private Map<String, Object> mapInboxRow(Record r) {
        Map<String, Object> row = new LinkedHashMap<>();
        row.put("proyectoId", r.get(PROYECTOS.ID));
        row.put("nombreProyecto", r.get(PROYECTOS.NOMBRE_PROYECTO));
        row.put("gestorUserId", r.get(PROYECTOS.USUARIO_ID));
        row.put("estado", r.get(PROYECTOS.ESTADO) != null ? r.get(PROYECTOS.ESTADO).getLiteral() : null);
        row.put("lastMessageId", r.get(PROYECTO_CHAT_MENSAJES.ID));
        row.put("lastAutorId", r.get(PROYECTO_CHAT_MENSAJES.AUTOR_ID));
        row.put("lastCuerpo", r.get(PROYECTO_CHAT_MENSAJES.CUERPO));
        LocalDateTime at = r.get(PROYECTO_CHAT_MENSAJES.CREATED_AT);
        row.put("lastAt", at != null ? at.toString() : null);
        row.put("messageCount", r.get("msg_count", Integer.class));
        return row;
    }
}
