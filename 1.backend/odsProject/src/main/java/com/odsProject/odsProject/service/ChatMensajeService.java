package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoChatMensajes;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IChatMensajeRepository;
import com.odsProject.odsProject.repository.interfaces.ILoginRepository;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.service.interfaces.IChatMensajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatMensajeService implements IChatMensajeService {

    private static final int EDIT_WINDOW_MINUTES = 30;
    private static final Set<String> REVIEWER_ROLES = Set.of("admin", "evaluador");

    @Autowired
    private IChatMensajeRepository chatMensajeRepository;

    @Autowired
    private IMasterProjectRepository masterProjectRepository;

    @Autowired
    private ILoginRepository loginRepository;

    @Override
    public List<Map<String, Object>> listMessages(Integer proyectoId, Integer actorUserId, String actorRole) {
        Proyectos p = requireProyecto(proyectoId);
        assertCanAccessChat(p, actorUserId, actorRole);
        return chatMensajeRepository.findByProyectoId(proyectoId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> sendMessage(Integer proyectoId, Integer actorUserId, String actorRole, String cuerpo) {
        Proyectos p = requireProyecto(proyectoId);
        assertCanAccessChat(p, actorUserId, actorRole);
        requirePlanificacionForWrite(p);
        if (cuerpo == null || cuerpo.isBlank())
            throw new IllegalArgumentException("cuerpo es requerido");
        ProyectoChatMensajes m = chatMensajeRepository.insert(proyectoId, actorUserId, cuerpo.trim());
        return toDto(m);
    }

    @Override
    public Map<String, Object> editMessage(Integer proyectoId, Integer msgId, Integer actorUserId, String cuerpo) {
        Proyectos p = requireProyecto(proyectoId);
        requirePlanificacionForWrite(p);
        ProyectoChatMensajes m = chatMensajeRepository.findById(msgId)
                .orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado"));
        if (!proyectoId.equals(m.getProyectoId()))
            throw new IllegalArgumentException("Mensaje no pertenece al proyecto");
        if (m.getEliminado() != null && m.getEliminado() == 1)
            throw new IllegalStateException("Mensaje eliminado");
        if (!Objects.equals(actorUserId, m.getAutorId()))
            throw new SecurityException("Solo el autor del mensaje puede editarlo");
        assertWithinEditWindow(m);
        if (cuerpo == null || cuerpo.isBlank())
            throw new IllegalArgumentException("cuerpo es requerido");
        int count = (m.getEditCount() != null ? m.getEditCount() : 0) + 1;
        ProyectoChatMensajes updated = chatMensajeRepository.updateCuerpo(msgId, cuerpo.trim(), count);
        return toDto(updated);
    }

    private Proyectos requireProyecto(Integer proyectoId) {
        return masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
    }

    private void assertCanAccessChat(Proyectos p, Integer actorUserId, String actorRole) {
        if (actorUserId == null)
            throw new IllegalArgumentException("actorUserId es requerido");
        String role = normalizeRole(actorRole);
        if (REVIEWER_ROLES.contains(role))
            return;
        if ("gestor".equals(role) && actorUserId.equals(p.getUsuarioId()))
            return;
        throw new SecurityException("No autorizado para ver el chat del proyecto");
    }

    private void requirePlanificacionForWrite(Proyectos p) {
        if (!"planificacion".equalsIgnoreCase(String.valueOf(p.getEstado())))
            throw new IllegalStateException("Chat en solo lectura: el proyecto no está en planificacion");
    }

    private void assertWithinEditWindow(ProyectoChatMensajes m) {
        LocalDateTime created = m.getCreatedAt();
        if (created == null)
            throw new IllegalStateException("Mensaje sin fecha de creación");
        if (Duration.between(created, LocalDateTime.now()).toMinutes() > EDIT_WINDOW_MINUTES)
            throw new IllegalStateException("Ventana de edición expirada (30 minutos)");
    }

    private static String normalizeRole(String actorRole) {
        return actorRole != null ? actorRole.trim().toLowerCase() : "";
    }

    private Map<String, Object> toDto(ProyectoChatMensajes m) {
        Map<String, Object> dto = new LinkedHashMap<>();
        dto.put("id", m.getId());
        dto.put("proyectoId", m.getProyectoId());
        dto.put("autorId", m.getAutorId());
        dto.put("cuerpo", m.getCuerpo());
        dto.put("createdAt", m.getCreatedAt() != null ? m.getCreatedAt().toString() : null);
        dto.put("editedAt", m.getEditedAt() != null ? m.getEditedAt().toString() : null);
        dto.put("editCount", m.getEditCount() != null ? m.getEditCount() : 0);
        dto.put("eliminado", m.getEliminado() != null && m.getEliminado() == 1);
        String autorNombre = loginRepository.findUsuarioById(m.getAutorId())
                .map(u -> u.getFullName())
                .orElse(null);
        dto.put("autorNombre", autorNombre);
        return dto;
    }
}
