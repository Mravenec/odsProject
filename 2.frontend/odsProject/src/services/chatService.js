import api from './api';

/**
 * Chat de planificación — alineado con ChatMensajeController
 */
export const chatService = {
  async listMessages(projectId, actorUserId, actorRole) {
    const r = await api.get(`/projects/${projectId}/chat/messages`, {
      params: { actorUserId, actorRole },
    });
    return { success: true, data: r.data };
  },

  async sendMessage(projectId, actorUserId, actorRole, cuerpo) {
    const r = await api.post(`/projects/${projectId}/chat/messages`, {
      actorUserId, actorRole, cuerpo,
    });
    return { success: true, data: r.data };
  },

  async editMessage(projectId, msgId, actorUserId, cuerpo) {
    const r = await api.put(`/projects/${projectId}/chat/messages/${msgId}`, {
      actorUserId, cuerpo,
    });
    return { success: true, data: r.data };
  },
};
