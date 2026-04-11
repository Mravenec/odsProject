import api from './api';
// Mock service for ODS Objective 9 - Industria, Innovación e Infraestructura
// Based on official SDG indicators from Global Indicator Framework
export const objetivo09Service = {
  // Obtener todos los indicadores de un proyecto (Base-Indicadores)
  getIndicators: async (proyectoId) => {
    try {
      if (!proyectoId) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/09/base-indicadores`, { params: { proyectoId } });
      const indicators = response.data || [];
      
      return indicators.reduce((acc, ind) => {
        const id = ind.indicadorMasterId || ind.id;
        acc[id] = {
          id: ind.id,
          masterId: ind.indicadorMasterId,
          code: ind.codigo || `9.${id}`,
          currentValue: ind.valorActual !== undefined ? ind.valorActual : 0,
          targetValue: ind.metaValor || 0,
          unit: ind.metaUnidad || 'unidad',
          updatedAt: ind.updatedAt
        };
        return acc;
      }, {});
    } catch (error) {
      console.error('Error fetching ODS 09 indicators:', error);
      return {};
    }
  },

  // Estadísticas del ODS 09
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/09/base-estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 09 statistics:', error);
      return {};
    }
  },

  // Métodos individuales adaptados
  getIndicador_9_1_1: async (proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.1.1`, { params: { proyectoId } });
      const data = response.data || {};
      return {
        currentValue: data.valorActual ?? 0,
        targetValue: data.metaValor ?? 0,
        unit: data.metaUnidad || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.1.1:', error);
      return { currentValue: 0, targetValue: 0, unit: 'unidad' };
    }
  },
  
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/09/indicadores/${code}`, { params: { proyectoId } });
      const data = response.data || {};
      return {
        currentValue: data.valorActual ?? 0,
        targetValue: data.metaValor ?? 0,
        unit: data.metaUnidad || 'unidad'
      };
    } catch (error) {
      console.error(`Error fetching indicator ${code}:`, error);
      return { currentValue: 0, targetValue: 0, unit: 'unidad' };
    }
  }
};
