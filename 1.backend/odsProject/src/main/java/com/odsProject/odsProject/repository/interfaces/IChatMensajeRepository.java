package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoChatMensajes;

import java.util.List;
import java.util.Optional;

public interface IChatMensajeRepository {

    List<ProyectoChatMensajes> findByProyectoId(Integer proyectoId);

    Optional<ProyectoChatMensajes> findById(Integer id);

    ProyectoChatMensajes insert(Integer proyectoId, Integer autorId, String cuerpo);

    ProyectoChatMensajes updateCuerpo(Integer id, String cuerpo, int editCount);
}
