package com.odsProject.odsProject.service.interfaces;

import java.util.List;
import java.util.Map;

public interface IChatMensajeService {

    List<Map<String, Object>> listMessages(Integer proyectoId, Integer actorUserId, String actorRole);

    Map<String, Object> sendMessage(Integer proyectoId, Integer actorUserId, String actorRole, String cuerpo);

    Map<String, Object> editMessage(Integer proyectoId, Integer msgId, Integer actorUserId, String cuerpo);

    /** Bandeja global para admin/evaluador (proyectos en planificación con chat). */
    List<Map<String, Object>> listInbox(Integer actorUserId, String actorRole);
}
