import api from './api';

/**
 * Servicio para el ODS 08 - Trabajo Decente y Crecimiento Económico
 * Implementa la carga dinámica de indicadores desde la base de datos.
 */
export const objetivo08Service = {
  // Obtener todos los indicadores de un proyecto (VistaAdminDetalleIndicadores)
  getIndicators: async (proyectoId) => {
    try {
      if (proyectoId === undefined) throw new Error('proyectoId is required');
      const response = await api.get(`/ods/08/indicadores`, { params: { proyectoId } });
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
      console.error('Error fetching ODS 08 indicators:', error);
      return {};
    }
  },

  // Estadísticas del ODS 08
  getStatistics: async () => {
    try {
      const response = await api.get(`/ods/08/estadisticas`);
      return response.data || {};
    } catch (error) {
      console.error('Error fetching ODS 08 statistics:', error);
      return {};
    }
  },

  // Dashboard específico del ODS 08 (V3)
  getDashboard: async () => {
    try {
      const response = await api.get(`/ods/08/dashboard`);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error fetching ODS 08 dashboard:', error);
      return { success: false, error: error.message };
    }
  },

  /**
   * Mantiene compatibilidad con llamadas individuales si existieran.
   */
  getIndicadorGeneral: async (code, proyectoId = 1) => {
    try {
      const response = await api.get(`/ods/08/indicadores/${code}`, { params: { proyectoId } });
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
        metaNombre:       indicatorData.metaNombre || null,
        formulaCustom: indicatorData.formulaCustom || null
      };
      const response = await api.post(`/ods/08/indicadores`, backendData);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error saving ODS 08 indicator:', error);
      throw new Error(error.response?.data?.message || 'Error al vincular indicador');
    }
  },

  // Guardar parámetros/metas del proyecto
  saveParameter: async (parameterData) => {
    try {
      const backendData = {
        proyectoIndicadorId: parameterData.proyectoIndicadorId,
        nombreParametro: parameterData.nombreParametro,
                nombreVariable:  parameterData.nombreVariable || parameterData.nombreParametro,
        tipoDato: parameterData.tipoDato || 'Decimal'
      };
      const response = await api.post(`/ods/08/metas`, backendData);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error saving ODS 08 parameter:', error);
      throw new Error(error.response?.data?.message || 'Error al guardar parámetro');
    }
  }
,
  getMetasProyecto: async (proyectoId) => {
    try {
      const res = await api.get('/ods/08/metas', { params: { proyectoId } });
      return { success: true, data: res.data || [] };
    } catch (e) { return { success: true, data: [] }; }
  },


  getMediciones: async (proyectoIndicadorId) => {
    try {
      const res = await api.get('/ods/08/mediciones', { params: { indicadorId: proyectoIndicadorId } });
      return { success: true, data: res.data || [] };
    } catch (e) { return { success: false, data: [] }; }
  },

  createMedicion: async ({ proyectoIndicadorId, valorCalculado, fechaMedicion, responsable }) => {
    try {
      const res = await api.post('/ods/08/mediciones', {
        proyectoIndicadorId,
        valorCalculado,
        fechaMedicion: fechaMedicion || new Date().toISOString().split('T')[0],
        responsable: responsable || 'Sistema'
      });
      return { success: true, data: res.data };
    } catch (e) { throw new Error(e.response?.data?.message || 'Error al registrar medición'); }
  },


};