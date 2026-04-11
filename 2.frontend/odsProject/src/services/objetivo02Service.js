import api from './api';
// Mock service for ODS Objective 2 - Hambre Cero
// Based on official SDG indicators from Global Indicator Framework
export const objetivo02Service = {
  // Obtener todos los indicadores de un proyecto (Base-Indicadores)
  getIndicators: async (proyectoId) => {
    try {
      if (!proyectoId) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/02/base-indicadores`, { params: { proyectoId } });
      const indicators = response.data || [];
      
      return indicators.reduce((acc, ind) => {
        const code = ind.indicadorCodigo;
        if (!code) return acc;

        acc[code] = {
          id: ind.id,
          masterId: ind.indicadorMasterId,
          code: code,
          name: ind.indicadorNombre,
          currentValue: ind.valorActual !== undefined && ind.valorActual !== null ? ind.valorActual : null,
          targetValue: ind.metaValor || 0,
          unit: ind.metaUnidad || 'unidad',
          formula: ind.formulaCustom || '',
          updatedAt: ind.ultimaActualizacion,
          hasData: ind.valorActual !== null
        };
        return acc;
      }, {});
    } catch (error) {
      console.error('Error fetching ODS 02 indicators:', error);
      return {};
    }
  },

  // Estadísticas del ODS 02
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/02/base-estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 02 statistics:', error);
      return {};
    }
  },

  // Métodos individuales adaptados
  getIndicador_2_1_1: async (proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.1.1`, { params: { proyectoId } });
      const data = response.data || {};
      return {
        currentValue: data.valorActual ?? 0,
        targetValue: data.metaValor ?? 0,
        unit: data.metaUnidad || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.1.1:', error);
      return { currentValue: 0, targetValue: 0, unit: 'unidad' };
    }
  },
  
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/02/indicadores/${code}`, { params: { proyectoId } });
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
