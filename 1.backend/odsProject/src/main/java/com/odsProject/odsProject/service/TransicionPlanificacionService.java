package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoTransicionSolicitud;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.repository.interfaces.ITransicionPlanificacionRepository;
import com.odsProject.odsProject.service.interfaces.IChatMensajeService;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.service.interfaces.ITransicionPlanificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class TransicionPlanificacionService implements ITransicionPlanificacionService {

    private static final Set<String> REVIEWER_ROLES = Set.of("admin", "evaluador");

    @Autowired
    private ITransicionPlanificacionRepository transicionRepository;

    @Autowired
    private IMasterProjectRepository masterProjectRepository;

    @Autowired
    private IMasterProjectService masterProjectService;

    @Autowired
    private IChatMensajeService chatMensajeService;

    @Override
    public Map<String, Object> crearSolicitud(
            Integer proyectoId, Integer actorUserId, String estadoDestino, String motivo) {
        Proyectos p = requireProyecto(proyectoId);
        requirePlanificacion(p);
        if (!actorUserId.equals(p.getUsuarioId()))
            throw new SecurityException("Solo el gestor dueño puede solicitar la transición");
        if (transicionRepository.findPendienteByProyectoId(proyectoId).isPresent())
            throw new IllegalStateException("Ya existe una solicitud pendiente para este proyecto");
        String dest = estadoDestino != null ? estadoDestino.toLowerCase() : "";
        if (!"activo".equals(dest) && !"cancelado".equals(dest))
            throw new IllegalArgumentException("estadoDestino debe ser activo o cancelado");
        ProyectoTransicionSolicitud sol = transicionRepository.insert(
                proyectoId, actorUserId, dest, motivo);
        postChatPing(p, actorUserId, dest, motivo);
        return Map.of("success", true, "solicitud", toDto(sol));
    }

    /** Avisa a admin/evaluador vía bandeja de chat (StaffGlobalChatWidget). */
    private void postChatPing(Proyectos p, Integer actorUserId, String dest, String motivo) {
        String nombre = p.getNombreProyecto() != null ? p.getNombreProyecto() : ("#" + p.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("Favor revisar y aprobar la transición a ")
                .append(dest)
                .append(" del proyecto «")
                .append(nombre)
                .append("».");
        if (motivo != null && !motivo.isBlank()) {
            sb.append(" Motivo: ").append(motivo.trim());
        }
        try {
            chatMensajeService.sendMessage(p.getId(), actorUserId, "gestor", sb.toString());
        } catch (RuntimeException e) {
            // La solicitud ya quedó creada; no revertir por fallo de chat
            org.slf4j.LoggerFactory.getLogger(TransicionPlanificacionService.class)
                    .warn("No se pudo publicar ping de chat para proyecto {}: {}", p.getId(), e.getMessage());
        }
    }

    @Override
    public Map<String, Object> obtenerPendiente(Integer proyectoId, Integer actorUserId, String actorRole) {
        Proyectos p = requireProyecto(proyectoId);
        assertCanViewSolicitud(p, actorUserId, actorRole);
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("success", true);
        resp.put("solicitud", transicionRepository.findPendienteByProyectoId(proyectoId)
                .map(this::toDto)
                .orElse(null));
        resp.put("ultimaSolicitud", transicionRepository.findLatestByProyectoId(proyectoId)
                .map(this::toDto)
                .orElse(null));
        return resp;
    }

    @Override
    public Map<String, Object> aprobar(
            Integer proyectoId, Integer actorUserId, String actorRole, String nota) {
        requireProyecto(proyectoId);
        requireReviewer(actorRole);
        ProyectoTransicionSolicitud sol = transicionRepository.findPendienteByProyectoId(proyectoId)
                .orElseThrow(() -> new IllegalStateException("No hay solicitud pendiente"));
        String dest = sol.getEstadoDestino() != null
                ? sol.getEstadoDestino().getLiteral() : "activo";
        ProyectoTransicionSolicitud updated = transicionRepository.resolver(
                sol.getId(), "aprobada", actorUserId, nota);
        Map<String, Object> transition = masterProjectService.transitionStatePlanificacionAprobada(
                proyectoId, dest, actorUserId, actorRole, nota);
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("success", true);
        resp.put("solicitud", toDto(updated));
        resp.putAll(transition);
        return resp;
    }

    @Override
    public Map<String, Object> rechazar(
            Integer proyectoId, Integer actorUserId, String actorRole, String nota) {
        requireProyecto(proyectoId);
        requireReviewer(actorRole);
        if (nota == null || nota.trim().length() < 10)
            throw new IllegalArgumentException("nota de rechazo debe tener al menos 10 caracteres");
        ProyectoTransicionSolicitud sol = transicionRepository.findPendienteByProyectoId(proyectoId)
                .orElseThrow(() -> new IllegalStateException("No hay solicitud pendiente"));
        ProyectoTransicionSolicitud updated = transicionRepository.resolver(
                sol.getId(), "rechazada", actorUserId, nota.trim());
        return Map.of("success", true, "solicitud", toDto(updated));
    }

    @Override
    public Map<String, Object> fuerzaMayor(
            Integer proyectoId, Integer actorUserId, String actorRole, String motivo) {
        Proyectos p = requireProyecto(proyectoId);
        requireReviewer(actorRole);
        if (!"activo".equalsIgnoreCase(String.valueOf(p.getEstado())))
            throw new IllegalStateException("Fuerza mayor solo aplica desde estado activo");
        if (motivo == null || motivo.isBlank())
            throw new IllegalArgumentException("motivo es obligatorio");
        return masterProjectService.transitionState(proyectoId, "cancelado", actorUserId, actorRole, motivo);
    }

    @Override
    public Map<String, Object> listarRecientes(Integer actorUserId, String actorRole) {
        if (actorUserId == null)
            throw new IllegalArgumentException("actorUserId es requerido");
        String role = normalizeRole(actorRole);
        if (!"gestor".equals(role))
            throw new SecurityException("Solo el gestor puede consultar su bandeja de resoluciones");
        List<Map<String, Object>> items =
                transicionRepository.findRecientesResueltasByGestor(actorUserId, 20);
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("success", true);
        resp.put("items", items);
        return resp;
    }

    private Proyectos requireProyecto(Integer proyectoId) {
        return masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
    }

    private static void requirePlanificacion(Proyectos p) {
        if (!"planificacion".equalsIgnoreCase(String.valueOf(p.getEstado())))
            throw new IllegalStateException("Solo disponible en planificacion");
    }

    private static void requireReviewer(String actorRole) {
        if (!REVIEWER_ROLES.contains(normalizeRole(actorRole)))
            throw new SecurityException("Solo admin o evaluador pueden resolver la solicitud");
    }

    private void assertCanViewSolicitud(Proyectos p, Integer actorUserId, String actorRole) {
        String role = normalizeRole(actorRole);
        if (REVIEWER_ROLES.contains(role))
            return;
        if ("gestor".equals(role) && actorUserId.equals(p.getUsuarioId()))
            return;
        throw new SecurityException("No autorizado");
    }

    private static String normalizeRole(String actorRole) {
        return actorRole != null ? actorRole.trim().toLowerCase() : "";
    }

    private Map<String, Object> toDto(ProyectoTransicionSolicitud s) {
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("id", s.getId());
        m.put("proyectoId", s.getProyectoId());
        m.put("solicitadoPor", s.getSolicitadoPor());
        m.put("estadoDestino", s.getEstadoDestino() != null ? s.getEstadoDestino().getLiteral() : null);
        m.put("motivo", s.getMotivo());
        m.put("estadoSolicitud", s.getEstadoSolicitud() != null ? s.getEstadoSolicitud().getLiteral() : null);
        m.put("resueltoPor", s.getResueltoPor());
        m.put("resueltoEn", s.getResueltoEn() != null ? s.getResueltoEn().toString() : null);
        m.put("notaResolucion", s.getNotaResolucion());
        m.put("createdAt", s.getCreatedAt() != null ? s.getCreatedAt().toString() : null);
        return m;
    }
}
