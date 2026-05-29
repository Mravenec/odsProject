package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoTransicionSolicitud;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.repository.interfaces.ITransicionPlanificacionRepository;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.service.interfaces.ITransicionPlanificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
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
        return Map.of("success", true, "solicitud", toDto(sol));
    }

    @Override
    public Map<String, Object> obtenerPendiente(Integer proyectoId, Integer actorUserId, String actorRole) {
        Proyectos p = requireProyecto(proyectoId);
        assertCanViewSolicitud(p, actorUserId, actorRole);
        return transicionRepository.findPendienteByProyectoId(proyectoId)
                .map(s -> {
                    Map<String, Object> resp = new LinkedHashMap<>();
                    resp.put("success", true);
                    resp.put("solicitud", toDto(s));
                    return resp;
                })
                .orElseGet(() -> {
                    Map<String, Object> resp = new LinkedHashMap<>();
                    resp.put("success", true);
                    resp.put("solicitud", null);
                    return resp;
                });
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
