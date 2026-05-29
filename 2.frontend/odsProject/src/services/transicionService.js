import api from './api';

/**
 * Transición de planificación — TransicionPlanificacionController
 */
export const transicionService = {
  async getPendiente(projectId, actorUserId, actorRole) {
    const r = await api.get(`/projects/${projectId}/planificacion/solicitud/pendiente`, {
      params: { actorUserId, actorRole },
    });
    return { success: true, data: r.data };
  },

  async crearSolicitud(projectId, actorUserId, estadoDestino, motivo) {
    const r = await api.post(`/projects/${projectId}/planificacion/solicitud`, {
      actorUserId,
      estadoDestino,
      motivo,
    });
    return { success: true, data: r.data };
  },

  async aprobar(projectId, actorUserId, actorRole, nota) {
    const r = await api.post(`/projects/${projectId}/planificacion/solicitud/aprobar`, {
      actorUserId,
      actorRole,
      nota: nota || '',
    });
    return { success: true, data: r.data };
  },

  async rechazar(projectId, actorUserId, actorRole, nota) {
    const r = await api.post(`/projects/${projectId}/planificacion/solicitud/rechazar`, {
      actorUserId,
      actorRole,
      nota,
    });
    return { success: true, data: r.data };
  },

  async fuerzaMayor(projectId, actorUserId, actorRole, motivo) {
    const r = await api.post(`/projects/${projectId}/planificacion/fuerza-mayor`, {
      actorUserId,
      actorRole,
      motivo,
    });
    return { success: true, data: r.data };
  },
};
