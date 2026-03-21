import api from './api';
// Mock service for ODS Objective 13 - Acción por el Clima
// Based on official SDG indicators from Global Indicator Framework
export const objetivo13Service = {
  // 13.1.1 Número de personas muertas, desaparecidas y afectadas directamente por desastres
  getIndicador_13_1_1: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.1.1:', error);
      throw error;
    }
  },
  
  // 13.1.2 Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
  getIndicador_13_1_2: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.1.2:', error);
      throw error;
    }
  },
  
  // 13.1.3 Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
  getIndicador_13_1_3: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.1.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.1.3:', error);
      throw error;
    }
  },
  
  // 13.2.1 Número de países con contribuciones determinadas a nivel nacional
  getIndicador_13_2_1: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.2.1:', error);
      throw error;
    }
  },
  
  // 13.2.2 Emisiones totales de gases de efecto invernadero por año
  getIndicador_13_2_2: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.2.2:', error);
      throw error;
    }
  },
  
  // 13.3.1 Grado en que se incorpora educación para ciudadanía mundial y desarrollo sostenible
  getIndicador_13_3_1: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.3.1:', error);
      throw error;
    }
  },
  
  // 13.a.1 Cantidades proporcionadas y movilizadas en relación con el objetivo actual
  getIndicador_13_a_1: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.a.1:', error);
      throw error;
    }
  },
  
  // 13.b.1 Número de países menos adelantados y pequeños Estados insulares con contribuciones
  getIndicador_13_b_1: async () => {
    try {
      const response = await api.get(`/ods/13/indicadores/13.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 13.b.1:', error);
      throw error;
    }
  }
};
