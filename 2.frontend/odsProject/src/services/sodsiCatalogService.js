import api from './api';

export const sodsiCatalogService = {
  async getCatalogos() {
    try {
      const response = await api.get('/sodsi/catalogos');
      return { success: true, data: response.data || {} };
    } catch (error) {
      const msg = error.userMessage || error.message || 'No se pudieron cargar los catálogos SODSI';
      return { success: false, error: msg };
    }
  },

  async listBeneficiarioValores({ activo = 'true', adminAll = false } = {}) {
    try {
      const q = adminAll ? 'activo=all' : `activo=${activo}`;
      const response = await api.get(`/sodsi/beneficiarios/valores?${q}`);
      return { success: true, data: response.data || [] };
    } catch (error) {
      const msg = error.userMessage || error.message || 'No se pudieron cargar los beneficiarios';
      return { success: false, error: msg };
    }
  },

  async createBeneficiarioValor({ categoriaId, nombre }) {
    try {
      const response = await api.post('/sodsi/beneficiarios/valores', { categoriaId, nombre });
      return { success: true, data: response.data };
    } catch (error) {
      const msg = error.response?.data?.error || error.userMessage || error.message || 'No se pudo crear el beneficiario';
      return { success: false, error: msg };
    }
  },

  async setBeneficiarioActivo(valorId, activo) {
    try {
      const response = await api.patch(`/sodsi/beneficiarios/valores/${valorId}/activo`, { activo });
      return { success: true, data: response.data };
    } catch (error) {
      const msg = error.response?.data?.error
        || error.userMessage
        || error.message
        || 'No se pudo actualizar el beneficiario';
      return { success: false, error: msg };
    }
  },
};

export default sodsiCatalogService;
