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

  // Dashboard específico del ODS 01 (V3)
  getDashboard: async () => {
    try {
      const response = await api.get(`/ods/01/dashboard`);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error fetching ODS 01 dashboard:', error);
      return { success: false, error: error.message };
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
      const response = await api.post(`/ods/01/indicadores`, backendData);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error saving ODS 01 indicator:', error);
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
      const response = await api.post(`/ods/01/metas`, backendData);
      return { success: true, data: response.data };
    } catch (error) {
      console.error('Error saving ODS 01 parameter:', error);
      throw new Error(error.response?.data?.message || 'Error al guardar parámetro');
    }
  }
,
  getMetasProyecto: async (proyectoId) => {
    try {
      const res = await api.get('/ods/01/metas', { params: { proyectoId } });
      return { success: true, data: res.data || [] };
    } catch (e) { return { success: true, data: [] }; }
  },


  getMediciones: async (proyectoIndicadorId) => {
    try {
      const res = await api.get('/ods/01/mediciones', { params: { indicadorId: proyectoIndicadorId } });
      return { success: true, data: res.data || [] };
    } catch (e) { return { success: false, data: [] }; }
  },

  createMedicion: async ({ proyectoIndicadorId, valorCalculado, fechaMedicion, responsable }) => {
    try {
      const res = await api.post('/ods/01/mediciones', {
        proyectoIndicadorId,
        valorCalculado,
        fechaMedicion: fechaMedicion || new Date().toISOString().split('T')[0],
        responsable: responsable || 'Sistema'
      });
      return { success: true, data: res.data };
    } catch (e) { throw new Error(e.response?.data?.message || 'Error al registrar medición'); }
  },

  // ─────────────────────────────────────────────────────────────────────
  //  Sprint 2/5: Medición auditada y traza
  //  El cliente envía valoresParametros; el backend recalcula y persiste atómico.
  // ─────────────────────────────────────────────────────────────────────

  createMedicionAuditada: async ({ proyectoIndicadorId, fechaMedicion, responsable, metodoMedicion, observaciones, valoresParametros }) => {
    try {
      const res = await api.post('/ods/01/mediciones/auditada', {
        proyectoIndicadorId,
        fechaMedicion: fechaMedicion || new Date().toISOString().split('T')[0],
        responsable: responsable || 'Sistema',
        metodoMedicion: metodoMedicion || 'manual',
        observaciones: observaciones || null,
        valoresParametros: valoresParametros || {}
      });
      return { success: true, data: res.data };
    } catch (e) {
      throw new Error(e.response?.data?.error || e.message || 'Error al guardar medición auditada');
    }
  },

  getMedicionAuditoria: async (medicionId) => {
    try {
      const res = await api.get(`/ods/01/mediciones/${medicionId}/auditoria`);
      return { success: true, data: res.data };
    } catch (e) {
      return { success: false, error: e.response?.data?.error || e.message };
    }
  }

};