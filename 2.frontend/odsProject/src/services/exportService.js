import api from './api';

const _triggerDownload = (blob, filename, contentType) => {
  const file = new Blob([blob], { type: contentType || 'application/octet-stream' });
  const url = window.URL.createObjectURL(file);
  const a = document.createElement('a');
  a.href = url;
  a.download = filename;
  document.body.appendChild(a);
  a.click();
  document.body.removeChild(a);
  window.URL.revokeObjectURL(url);
};

/** Axios baseURL already includes `/api`; paths match ExportController under `/api/export`. */
const _exportPath = (suffix) => `/export${suffix}`;

export const exportService = {
  async downloadProjectExport(projectId) {
    try {
      const r = await api.get(_exportPath(`/proyecto/${projectId}`), { responseType: 'blob' });
      const disposition = r.headers['content-disposition'] || '';
      const match = disposition.match(/filename="?([^";\n]+)"?/);
      const filename = match?.[1] || `proyecto-${projectId}-export.xlsx`;
      _triggerDownload(r.data, filename, r.headers['content-type']);
      return { success: true };
    } catch (error) {
      const msg = error.userMessage || error.message || 'No se pudo exportar el proyecto';
      return { success: false, error: msg };
    }
  },

  async downloadConsolidatedExport() {
    try {
      const r = await api.get(_exportPath('/planificacion/consolidado'), { responseType: 'blob' });
      const disposition = r.headers['content-disposition'] || '';
      const match = disposition.match(/filename="?([^";\n]+)"?/);
      const filename = match?.[1] || 'planificacion-consolidado.xlsx';
      _triggerDownload(r.data, filename, r.headers['content-type']);
      return { success: true };
    } catch (error) {
      const msg = error.userMessage || error.message || 'No se pudo exportar el consolidado';
      return { success: false, error: msg };
    }
  },

  async downloadProjectsExcel(filters = {}) {
    try {
      const q = new URLSearchParams();
      if (filters.sedeId != null && filters.sedeId !== '') q.set('sedeId', String(filters.sedeId));
      if (filters.userId != null && filters.userId !== '') q.set('userId', String(filters.userId));
      const qs = q.toString();
      const url = _exportPath(`/projects/excel${qs ? `?${qs}` : ''}`);
      const r = await api.get(url, { responseType: 'blob' });
      const disposition = r.headers['content-disposition'] || '';
      const match = disposition.match(/filename="?([^";\n]+)"?/);
      const filename = match?.[1] || 'proyectos-planificacion.xlsx';
      _triggerDownload(r.data, filename, r.headers['content-type']);
      return { success: true };
    } catch (error) {
      const msg = error.userMessage || error.message || 'No se pudo descargar el Excel de proyectos';
      return { success: false, error: msg };
    }
  },
};

export default exportService;
