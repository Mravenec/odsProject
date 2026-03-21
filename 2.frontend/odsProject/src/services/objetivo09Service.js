import api from './api';
// Mock service for ODS Objective 9 - Industria, Innovación e Infraestructura
// Based on official SDG indicators from Global Indicator Framework
export const objetivo09Service = {
  // 9.1.1 Proporción de la población rural que vive cerca de carreteras transitable
  getIndicador_9_1_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.1.1:', error);
      throw error;
    }
  },
  
  // 9.1.2 Volumen de transporte de pasajeros y carga
  getIndicador_9_1_2: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.1.2:', error);
      throw error;
    }
  },
  
  // 9.2.1 Valor añadido del sector manufacturo en proporción al PIB
  getIndicador_9_2_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.2.1:', error);
      throw error;
    }
  },
  
  // 9.2.2 Empleo del sector manufacturero en proporción al empleo total
  getIndicador_9_2_2: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.2.2:', error);
      throw error;
    }
  },
  
  // 9.3.1 Proporción del valor añadido del sector industrial correspondiente a pequeñas industrias
  getIndicador_9_3_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.3.1:', error);
      throw error;
    }
  },
  
  // 9.3.2 Proporción de las pequeñas industrias que han obtenido préstamo o línea de crédito
  getIndicador_9_3_2: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.3.2:', error);
      throw error;
    }
  },
  
  // 9.4.1 Emisiones de CO2 por unidad de valor añadido
  getIndicador_9_4_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.4.1:', error);
      throw error;
    }
  },
  
  // 9.5.1 Gastos en investigación y desarrollo en proporción al PIB
  getIndicador_9_5_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.5.1:', error);
      throw error;
    }
  },
  
  // 9.5.2 Número de investigadores por cada millón de habitantes
  getIndicador_9_5_2: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.5.2:', error);
      throw error;
    }
  },
  
  // 9.a.1 Total de apoyo internacional oficial destinado a infraestructura
  getIndicador_9_a_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.a.1:', error);
      throw error;
    }
  },
  
  // 9.b.1 Proporción del valor añadido por la industria de tecnología mediana y alta
  getIndicador_9_b_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.b.1:', error);
      throw error;
    }
  },
  
  // 9.c.1 Proporción de la población con cobertura de red móvil
  getIndicador_9_c_1: async () => {
    try {
      const response = await api.get(`/ods/09/indicadores/9.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 9.c.1:', error);
      throw error;
    }
  }
};
