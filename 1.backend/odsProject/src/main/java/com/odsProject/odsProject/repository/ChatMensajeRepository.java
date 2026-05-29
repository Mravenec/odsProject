package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoChatMensajes;
import com.odsProject.odsProject.repository.interfaces.IChatMensajeRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods_master.tables.ProyectoChatMensajes.PROYECTO_CHAT_MENSAJES;

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
}
