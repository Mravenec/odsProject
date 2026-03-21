import api from './api';
// Mock service for ODS Objective 12 - Producción y Consumo Responsables
// Based on official SDG indicators from Global Indicator Framework
export const objetivo12Service = {
  // 12.1.1 Número de países que elaboran instrumentos de política para consumo y producción sostenibles
  getIndicador_12_1_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.1.1:', error);
      throw error;
    }
  },
  
  // 12.2.1 Huella material en términos absolutos, per cápita y por PIB
  getIndicador_12_2_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.2.1:', error);
      throw error;
    }
  },
  
  // 12.2.2 Consumo material interno en términos absolutos, per cápita y por PIB
  getIndicador_12_2_2: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.2.2:', error);
      throw error;
    }
  },
  
  // 12.3.1 Índice de pérdidas de alimentos y desperdicio de alimentos
  getIndicador_12_3_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.3.1:', error);
      throw error;
    }
  },
  
  // 12.4.1 Número de partes en acuerdos ambientales multilaterales sobre desechos peligrosos
  getIndicador_12_4_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.4.1:', error);
      throw error;
    }
  },
  
  // 12.4.2 Desechos peligrosos generados per cápita y proporción de desechos peligrosos tratados
  getIndicador_12_4_2: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.4.2:', error);
      throw error;
    }
  },
  
  // 12.5.1 Tasa nacional de reciclado
  getIndicador_12_5_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.5.1:', error);
      throw error;
    }
  },
  
  // 12.6.1 Número de empresas que publican informes sobre sostenibilidad
  getIndicador_12_6_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.6.1:', error);
      throw error;
    }
  },
  
  // 12.7.1 Número de países que aplican políticas y planes de acción sostenibles en adquisiciones públicas
  getIndicador_12_7_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.7.1:', error);
      throw error;
    }
  },
  
  // 12.8.1 Grado en que se incorpora educación para ciudadanía mundial y desarrollo sostenible
  getIndicador_12_8_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.8.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.8.1:', error);
      throw error;
    }
  },
  
  // 12.a.1 Capacidad instalada de generación de energía renovable
  getIndicador_12_a_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.a.1:', error);
      throw error;
    }
  },
  
  // 12.b.1 Aplicación de instrumentos normalizados de contabilidad para turismo sostenible
  getIndicador_12_b_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.b.1:', error);
      throw error;
    }
  },
  
  // 12.c.1 Cuantía de los subsidios a los combustibles fósiles por unidad del PIB
  getIndicador_12_c_1: async () => {
    try {
      const response = await api.get(`/ods/12/indicadores/12.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 12.c.1:', error);
      throw error;
    }
  }
};
