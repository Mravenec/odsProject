import api from './api';

/**
 * Servicio para el ODS 03 - Salud y Bienestar
 * Implementa la carga dinámica de indicadores desde la base de datos.
 */
export const objetivo03Service = {
  /**
   * Obtener todos los indicadores enriquecidos para un proyecto en el ODS 03
   * @param {number} proyectoId ID del proyecto
   * @returns {Object} Mapa de indicadores indexados por código
   */
  getIndicators: async (proyectoId) => {
    try {
      if (!proyectoId) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/03/base-indicadores`, { params: { proyectoId } });
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
      console.error('Error fetching ODS 03 indicators:', error);
      return {};
    }
  },

  /**
   * Obtiene estadísticas generales del ODS 03
   */
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/03/base-estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 03 statistics:', error);
      return {};
    }
  },

  /**
   * Mantiene compatibilidad con llamadas individuales si existieran, 
   * aunque el ProjectCreationPage ahora usa getIndicators.
   */
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/03/indicadores/${code}`, { params: { proyectoId } });
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
