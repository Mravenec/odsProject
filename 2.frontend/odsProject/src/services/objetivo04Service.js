import api from './api';
// Mock service for ODS Objective 4 - Educación de Calidad
// Based on official SDG indicators from Global Indicator Framework
export const objetivo04Service = {
  // 4.1.1 Proporción de niños y adolescentes con nivel mínimo de competencia
  getIndicador_4_1_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.1.1:', error);
      throw error;
    }
  },
  
  // 4.1.2 Tasa de finalización de educación
  getIndicador_4_1_2: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.1.2:', error);
      throw error;
    }
  },
  
  // 4.2.1 Proporción de niños con desarrollo adecuado
  getIndicador_4_2_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.2.1:', error);
      throw error;
    }
  },
  
  // 4.2.2 Tasa de participación en el aprendizaje organizado
  getIndicador_4_2_2: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.2.2:', error);
      throw error;
    }
  },
  
  // 4.3.1 Tasa de participación de jóvenes y adultos en educación
  getIndicador_4_3_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.3.1:', error);
      throw error;
    }
  },
  
  // 4.4.1 Proporción de jóvenes y adultos con competencias en TIC
  getIndicador_4_4_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.4.1:', error);
      throw error;
    }
  },
  
  // 4.5.1 Índices de paridad para indicadores de educación
  getIndicador_4_5_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.5.1:', error);
      throw error;
    }
  },
  
  // 4.6.1 Tasa de alfabetización de adultos/jóvenes
  getIndicador_4_6_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.6.1:', error);
      throw error;
    }
  },
  
  // 4.7.1 Grado de incorporación de educación para ciudadanía mundial y desarrollo sostenible
  getIndicador_4_7_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.7.1:', error);
      throw error;
    }
  },
  
  // 4.a.1 Proporción de escuelas que ofrecen servicios básicos
  getIndicador_4_a_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.a.1:', error);
      throw error;
    }
  },
  
  // 4.b.1 Volumen de la asistencia oficial para el desarrollo destinada a becas
  getIndicador_4_b_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.b.1:', error);
      throw error;
    }
  },
  
  // 4.c.1 Proporción de docentes con calificaciones mínimas requeridas
  getIndicador_4_c_1: async () => {
    try {
      const response = await api.get(`/ods/04/indicadores/4.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 4.c.1:', error);
      throw error;
    }
  }
};
