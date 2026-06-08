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
  async downloadProjectFullReport(projectId) {
    return this.downloadProjectExport(projectId);
  },

  async downloadProjectExport(projectId) {
    try {
      const r = await api.get(_exportPath(`/proyecto/${projectId}`), { responseType: 'blob' });
      const disposition = r.headers['content-disposition'] || '';
      const match = disposition.match(/filename="?([^";\n]+)"?/);
      const filename = match?.[1] || `proyecto-${projectId}-resumen.xlsx`;
      _triggerDownload(r.data, filename, r.headers['content-type']);
      return { success: true };
    } catch (error) {
      const status = error.response?.status;
      if (status === 409) {
        return { success: false, error: 'Proyecto aún no finalizado. Disponible cuando esté evaluado.' };
      }
      if (status === 403) {
        return { success: false, error: 'Sin permisos para exportar este proyecto.' };
      }
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

  async downloadProjectsExcel({ sedeId, anio }) {
    try {
      if (sedeId == null || sedeId === '' || anio == null || anio === '') {
        return { success: false, error: 'Seleccione sede y año' };
      }
      const q = new URLSearchParams({
        sedeId: String(sedeId),
        anio: String(anio),
      });
      const url = _exportPath(`/projects/excel?${q.toString()}`);
      const r = await api.get(url, { responseType: 'blob' });
      const disposition = r.headers['content-disposition'] || '';
      const match = disposition.match(/filename="?([^";\n]+)"?/);
      const filename = match?.[1] || `proyectos-sede-${sedeId}-${anio}.xlsx`;
      _triggerDownload(r.data, filename, r.headers['content-type']);
      return { success: true };
    } catch (error) {
      const status = error.response?.status;
      if (status === 403) {
        return { success: false, error: 'Sin permisos para exportar el consolidado.' };
      }
      if (status === 400) {
        return { success: false, error: 'Parámetros de exportación inválidos.' };
      }
      const msg = error.userMessage || error.message || 'No se pudo descargar el Excel de proyectos';
      return { success: false, error: msg };
    }
  },
};

export default exportService;
