import api from './api';

/**
 * Servicio para el ODS 10 - Reducción de las Desigualdades
 * Implementa la carga dinámica de indicadores desde la base de datos.
 */
export const objetivo10Service = {
  // Obtener todos los indicadores de un proyecto (VistaAdminDetalleIndicadores)
  getIndicators: async (proyectoId) => {
    try {
      if (proyectoId === undefined) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/10/indicadores`, { params: { proyectoId } });
      const indicators = response.data || [];
      
      return indicators.reduce((acc, ind) => {
        const code = ind.indicadorCodigo;
        if (!code) return acc;

        acc[code] = {
          code: code,
          masterId: ind.indicadorMasterId,
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
      console.error('Error fetching ODS 10 indicators:', error);
      return {};
    }
  },

  // Estadísticas del ODS 10
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/10/estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 10 statistics:', error);
      return {};
    }
  },

  // Dashboard específico del ODS 10 (V3)
  getDashboard: async () => {
    try {
      const response = await api.get(`/ods/10/dashboard`);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error fetching ODS 10 dashboard:', error);
      return { success: false, error: error.message };
    }
  },

  /**
   * Mantiene compatibilidad con llamadas individuales si existieran.
   */
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/10/indicadores/${code}`, { params: { proyectoId } });
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
  },

  // Guardar vinculación de indicador a proyecto
  saveIndicator: async (indicatorData) => {
    try {
      const backendData = {
        proyectoId: indicatorData.proyectoId,
        indicadorMasterId: indicatorData.indicadorMasterId,
        metaValor: indicatorData.metaValor,
        metaUnidad: indicatorData.metaUnidad || 'unidad',
        formulaCustom: indicatorData.formulaCustom || null
      };
      const response = await api.post(`/ods/10/indicadores`, backendData);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error saving ODS 10 indicator:', error);
      throw new Error(error.response?.data?.message || 'Error al vincular indicador');
    }
  },

  // Guardar parámetros/metas del proyecto
  saveParameter: async (parameterData) => {
    try {
      const backendData = {
        proyectoIndicadorId: parameterData.proyectoIndicadorId,
        nombreParametro: parameterData.nombreParametro,
        tipoDato: parameterData.tipoDato || 'Decimal'
      };
      const response = await api.post(`/ods/10/metas`, backendData);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error saving ODS 10 parameter:', error);
      throw new Error(error.response?.data?.message || 'Error al guardar parámetro');
    }
  }
};
