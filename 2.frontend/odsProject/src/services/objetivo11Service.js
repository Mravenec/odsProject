import api from './api';
// Mock service for ODS Objective 11 - Ciudades y Comunidades Sostenibles
// Based on official SDG indicators from Global Indicator Framework
export const objetivo11Service = {
  // 11.1.1 Proporción de la población urbana que vive en barrios marginales
  getIndicador_11_1_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.1.1:', error);
      throw error;
    }
  },
  
  // 11.2.1 Proporción de la población con fácil acceso al transporte público
  getIndicador_11_2_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.2.1:', error);
      throw error;
    }
  },
  
  // 11.3.1 Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población
  getIndicador_11_3_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.3.1:', error);
      throw error;
    }
  },
  
  // 11.3.2 Proporción de ciudades con participación directa de la sociedad civil
  getIndicador_11_3_2: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.3.2:', error);
      throw error;
    }
  },
  
  // 11.4.1 Total de gastos per cápita destinados a la preservación del patrimonio cultural y natural
  getIndicador_11_4_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.4.1:', error);
      throw error;
    }
  },
  
  // 11.5.1 Número de personas muertas, desaparecidas y afectadas directamente por desastres
  getIndicador_11_5_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.5.1:', error);
      throw error;
    }
  },
  
  // 11.5.2 Pérdidas económicas directas atribuidas a los desastres
  getIndicador_11_5_2: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.5.2:', error);
      throw error;
    }
  },
  
  // 11.5.3 Daños en la infraestructura crítica e interrupciones de servicios básicos
  getIndicador_11_5_3: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.5.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.5.3:', error);
      throw error;
    }
  },
  
  // 11.6.1 Proporción de residuos sólidos municipales recogidos y administrados
  getIndicador_11_6_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.6.1:', error);
      throw error;
    }
  },
  
  // 11.6.2 Niveles medios anuales de partículas finas en las ciudades
  getIndicador_11_6_2: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.6.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.6.2:', error);
      throw error;
    }
  },
  
  // 11.7.1 Proporción media de la superficie edificada dedicada a espacios abiertos públicos
  getIndicador_11_7_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.7.1:', error);
      throw error;
    }
  },
  
  // 11.7.2 Proporción de personas que han sido víctimas de acoso en espacios públicos
  getIndicador_11_7_2: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.7.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.7.2:', error);
      throw error;
    }
  },
  
  // 11.a.1 Número de países con políticas urbanas nacionales o planes de desarrollo regionales
  getIndicador_11_a_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.a.1:', error);
      throw error;
    }
  },
  
  // 11.b.1 Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
  getIndicador_11_b_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.b.1:', error);
      throw error;
    }
  },
  
  // 11.b.2 Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
  getIndicador_11_b_2: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.b.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.b.2:', error);
      throw error;
    }
  },
  
  // 11.c.1 Total de asistencia oficial para el desarrollo destinada a infraestructuras urbanas
  getIndicador_11_c_1: async () => {
    try {
      const response = await api.get(`/ods/11/indicadores/11.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 11.c.1:', error);
      throw error;
    }
  }
};
