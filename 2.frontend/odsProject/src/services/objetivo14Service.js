import api from './api';
// Mock service for ODS Objective 14 - Vida Submarina
// Based on official SDG indicators from Global Indicator Framework
export const objetivo14Service = {
  // 14.1.1 Índice de eutrofización costera y densidad de detritos plásticos
  getIndicador_14_1_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.1.1:', error);
      throw error;
    }
  },
  
  // 14.2.1 Número de países que aplican enfoques basados en los ecosistemas
  getIndicador_14_2_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.2.1:', error);
      throw error;
    }
  },
  
  // 14.3.1 Acidez media del mar (pH) medida en estaciones de muestreo
  getIndicador_14_3_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.3.1:', error);
      throw error;
    }
  },
  
  // 14.4.1 Proporción de poblaciones de peces con niveles biológicamente sostenibles
  getIndicador_14_4_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.4.1:', error);
      throw error;
    }
  },
  
  // 14.5.1 Cobertura de las zonas protegidas en relación con las zonas marinas
  getIndicador_14_5_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.5.1:', error);
      throw error;
    }
  },
  
  // 14.6.1 Grado de aplicación de instrumentos internacionales contra pesca ilegal
  getIndicador_14_6_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.6.1:', error);
      throw error;
    }
  },
  
  // 14.7.1 Proporción del PIB correspondiente a la pesca sostenible
  getIndicador_14_7_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.7.1:', error);
      throw error;
    }
  },
  
  // 14.a.1 Proporción del presupuesto total de investigación asignada a tecnología marina
  getIndicador_14_a_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.a.1:', error);
      throw error;
    }
  },
  
  // 14.b.1 Grado de aplicación de un marco jurídico que reconozca derechos de acceso para pesca
  getIndicador_14_b_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.b.1:', error);
      throw error;
    }
  },
  
  // 14.c.1 Número de países que avanzan en la ratificación de instrumentos relacionados con los océanos
  getIndicador_14_c_1: async () => {
    try {
      const response = await api.get(`/ods/14/indicadores/14.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 14.c.1:', error);
      throw error;
    }
  }
};
