import api from './api';

/**
 * Servicio para el ODS 11 - Ciudades y Comunidades Sostenibles
 * Implementa la carga dinámica de indicadores desde la base de datos.
 */
export const objetivo11Service = {
  // Obtener todos los indicadores de un proyecto (VistaAdminDetalleIndicadores)
  getIndicators: async (proyectoId) => {
    try {
      if (proyectoId === undefined) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/11/indicadores`, { params: { proyectoId } });
      const indicators = response.data || [];
      
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
      console.error('Error fetching ODS 11 indicators:', error);
      return {};
    }
  },

  // Estadísticas del ODS 11
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/11/estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 11 statistics:', error);
      return {};
    }
  },

  /**
   * Mantiene compatibilidad con llamadas individuales si existieran.
   */
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/11/indicadores/${code}`, { params: { proyectoId } });
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
