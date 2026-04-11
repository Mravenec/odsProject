import api from './api';

/**
 * Servicio para el ODS 05 - Igualdad de Género
 * Implementa la carga dinámica de indicadores desde la base de datos.
 */
export const objetivo05Service = {
  /**
   * Obtener todos los indicadores enriquecidos para un proyecto en el ODS 05
   * @param {number} proyectoId ID del proyecto
   * @returns {Object} Mapa de indicadores indexados por código
   */
  getIndicators: async (proyectoId) => {
    try {
      if (!proyectoId) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/05/base-indicadores`, { params: { proyectoId } });
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
      console.error('Error fetching ODS 05 indicators:', error);
      return {};
    }
  },

  /**
   * Obtiene estadísticas generales del ODS 05
   */
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/05/base-estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 05 statistics:', error);
      return {};
    }
  },

  /**
   * Mantiene compatibilidad con llamadas individuales si existieran.
   */
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/05/indicadores/${code}`, { params: { proyectoId } });
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
