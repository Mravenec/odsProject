import api from './api';

export const documentService = {
  async upload(proyectoId, file, usuarioId, descripcion) {
    try {
      const form = new FormData();
      form.append('file', file);
      form.append('usuarioId', String(usuarioId));
      if (descripcion) form.append('descripcion', descripcion);
      const r = await api.post(`/projects/${proyectoId}/documents`, form,
        { headers: { 'Content-Type': 'multipart/form-data' } });
      return { success: true, data: r.data };
    } catch (e) {
      const msg = e.response?.data?.message || e.userMessage || e.message;
      return { success: false, error: msg };
    }
  },
  async listByProject(proyectoId) {
    try {
      const r = await api.get(`/projects/${proyectoId}/documents`);
      return { success: true, data: r.data || [] };
    } catch (e) { return { success: false, error: e.message, data: [] }; }
  },
  async download(documentoId, nombreSugerido = 'documento') {
    try {
      const r = await api.get(`/documents/${documentoId}/download`, { responseType: 'blob' });
      const blob = new Blob([r.data], { type: r.headers['content-type'] || 'application/octet-stream' });
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url; a.download = nombreSugerido;
      document.body.appendChild(a); a.click(); document.body.removeChild(a);
      window.URL.revokeObjectURL(url);
      return { success: true };
    } catch (e) { return { success: false, error: e.message }; }
  },
  async remove(documentoId, usuarioId, isAdmin = false) {
    try {
      const r = await api.delete(`/documents/${documentoId}`, { params: { usuarioId, admin: isAdmin } });
      return { success: true, data: r.data };
    } catch (e) { return { success: false, error: e.message }; }
  }
};
export default documentService;
