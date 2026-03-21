import api from './api';
// Mock service for ODS Objective 1 - Fin de la Pobreza
// Based on official SDG indicators from Global Indicator Framework
export const objetivo01Service = {
  // 1.1.1 Proporción de la población que vive por debajo del umbral internacional de pobreza
  getIndicador_1_1_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.1.1:', error);
      throw error;
    }
  },
  
  // 1.2.1 Proporción de la población que vive por debajo del umbral nacional de pobreza
  getIndicador_1_2_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.2.1:', error);
      throw error;
    }
  },
  
  // 1.2.2 Proporción de personas que viven en la pobreza multidimensional
  getIndicador_1_2_2: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.2.2:', error);
      throw error;
    }
  },
  
  // 1.3.1 Proporción de la población cubierta por sistemas de protección social
  getIndicador_1_3_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.3.1:', error);
      throw error;
    }
  },
  
  // 1.4.1 Proporción de la población que vive en hogares con acceso a servicios básicos
  getIndicador_1_4_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.4.1:', error);
      throw error;
    }
  },
  
  // 1.4.2 Proporción del total de la población adulta con derechos seguros de tenencia de la tierra
  getIndicador_1_4_2: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.4.2:', error);
      throw error;
    }
  },
  
  // 1.5.1 Número de personas muertas, desaparecidas y afectadas directamente por desastres
  getIndicador_1_5_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.5.1:', error);
      throw error;
    }
  },
  
  // 1.5.2 Pérdidas económicas directas atribuidas a los desastres
  getIndicador_1_5_2: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.5.2:', error);
      throw error;
    }
  },
  
  // 1.5.3 Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
  getIndicador_1_5_3: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.5.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.5.3:', error);
      throw error;
    }
  },
  
  // 1.5.4 Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
  getIndicador_1_5_4: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.5.4?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.5.4:', error);
      throw error;
    }
  },
  
  // 1.a.1 Total de subvenciones de asistencia oficial para el desarrollo destinadas a la reducción de la pobreza
  getIndicador_1_a_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.a.1:', error);
      throw error;
    }
  },
  
  // 1.a.2 Proporción del gasto público total dedicado a servicios esenciales
  getIndicador_1_a_2: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.a.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.a.2:', error);
      throw error;
    }
  },
  
  // 1.b.1 Gasto público social en favor de los pobres
  getIndicador_1_b_1: async () => {
    try {
      const response = await api.get(`/ods/01/indicadores/1.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 1.b.1:', error);
      throw error;
    }
  }
};
