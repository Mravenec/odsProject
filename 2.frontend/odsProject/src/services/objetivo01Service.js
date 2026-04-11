import api from './api';
// Mock service for ODS Objective 1 - Fin de la Pobreza
// Based on official SDG indicators from Global Indicator Framework
export const objetivo01Service = {
  // Obtener todos los indicadores de un proyecto (VistaAdminDetalleIndicadores)
  getIndicators: async (proyectoId) => {
    try {
      if (proyectoId === undefined) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/01/indicadores`, { params: { proyectoId } });
      const indicators = response.data || [];
      
      // Mapeo a objeto indexado por código de indicador para consistencia en la UI
      return indicators.reduce((acc, ind) => {
        const code = ind.indicadorCodigo;
        if (!code) return acc;

        acc[code] = {
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
      console.error('Error fetching ODS 01 indicators:', error);
      return {};
    }
  },

  // Estadísticas del ODS 01
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/01/estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 01 statistics:', error);
      return {};
    }
  },

  // Mantener métodos individuales para compatibilidad, pero parametrizados
  getIndicador_1_1_1: async (proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.1.1`, { params: { proyectoId } });
      const data = response.data || {};
      return {
        currentValue: data.valorActual ?? 0,
        targetValue: data.metaValor ?? 0,
        unit: data.metaUnidad || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.1.1:', error);
      return { currentValue: 0, targetValue: 0, unit: 'unidad' };
    }
  },
  
  // Refactorizar el resto de indicadores individuales para que sean consistentes
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/01/indicadores/${code}`, { params: { proyectoId } });
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
