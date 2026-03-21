import api from './api';
// Mock service for ODS Objective 6 - Agua Limpia y Saneamiento
// Based on official SDG indicators from Global Indicator Framework
export const objetivo06Service = {
  // 6.1.1 Proporción de la población que utiliza servicios de suministro de agua potable
  getIndicador_6_1_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.1.1:', error);
      throw error;
    }
  },
  
  // 6.2.1 Proporción de la población que utiliza saneamiento y lavado de manos
  getIndicador_6_2_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.2.1:', error);
      throw error;
    }
  },
  
  // 6.3.1 Proporción de aguas residuales tratadas adecuadamente
  getIndicador_6_3_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.3.1:', error);
      throw error;
    }
  },
  
  // 6.3.2 Proporción de masas de agua de buena calidad
  getIndicador_6_3_2: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.3.2:', error);
      throw error;
    }
  },
  
  // 6.4.1 Cambio en el uso eficiente de los recursos hídricos
  getIndicador_6_4_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.4.1:', error);
      throw error;
    }
  },
  
  // 6.4.2 Nivel de estrés hídrico
  getIndicador_6_4_2: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.4.2:', error);
      throw error;
    }
  },
  
  // 6.5.1 Grado de gestión integrada de los recursos hídricos
  getIndicador_6_5_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.5.1:', error);
      throw error;
    }
  },
  
  // 6.5.2 Proporción de cuencas transfronterizas con cooperación
  getIndicador_6_5_2: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.5.2:', error);
      throw error;
    }
  },
  
  // 6.6.1 Cambio en la extensión de los ecosistemas relacionados con el agua
  getIndicador_6_6_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.6.1:', error);
      throw error;
    }
  },
  
  // 6.a.1 Volumen de asistencia oficial para el desarrollo destinada al agua y saneamiento
  getIndicador_6_a_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.a.1:', error);
      throw error;
    }
  },
  
  // 6.b.1 Proporción de dependencias locales con políticas de participación comunitaria
  getIndicador_6_b_1: async () => {
    try {
      const response = await api.get(`/ods/06/indicadores/6.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 6.b.1:', error);
      throw error;
    }
  }
};
