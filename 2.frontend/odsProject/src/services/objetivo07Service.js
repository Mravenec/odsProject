import api from './api';
// Mock service for ODS Objective 7 - Energía Asequible y No Contaminante
// Based on official SDG indicators from Global Indicator Framework
export const objetivo07Service = {
  // 7.1.1 Proporción de la población que tiene acceso a la electricidad
  getIndicador_7_1_1: async () => {
    try {
      const response = await api.get(`/ods/07/indicadores/7.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 7.1.1:', error);
      throw error;
    }
  },
  
  // 7.1.2 Proporción de la población con combustibles y tecnologías limpios
  getIndicador_7_1_2: async () => {
    try {
      const response = await api.get(`/ods/07/indicadores/7.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 7.1.2:', error);
      throw error;
    }
  },
  
  // 7.2.1 Proporción de energía renovable en el consumo final total de energía
  getIndicador_7_2_1: async () => {
    try {
      const response = await api.get(`/ods/07/indicadores/7.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 7.2.1:', error);
      throw error;
    }
  },
  
  // 7.3.1 Intensidad energética medida en función de la energía primaria y el PIB
  getIndicador_7_3_1: async () => {
    try {
      const response = await api.get(`/ods/07/indicadores/7.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 7.3.1:', error);
      throw error;
    }
  },
  
  // 7.a.1 Corrientes financieras hacia países en desarrollo para energías limpias
  getIndicador_7_a_1: async () => {
    try {
      const response = await api.get(`/ods/07/indicadores/7.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 7.a.1:', error);
      throw error;
    }
  },
  
  // 7.b.1 Capacidad instalada de generación de energía renovable
  getIndicador_7_b_1: async () => {
    try {
      const response = await api.get(`/ods/07/indicadores/7.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 7.b.1:', error);
      throw error;
    }
  }
};
