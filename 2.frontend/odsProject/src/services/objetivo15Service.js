import api from './api';
// Mock service for ODS Objective 15 - Vida de Ecosistemas Terrestres
// Based on official SDG indicators from Global Indicator Framework
export const objetivo15Service = {
  // 15.1.1 Superficie forestal en proporción a la superficie total
  getIndicador_15_1_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.1.1:', error);
      throw error;
    }
  },
  
  // 15.1.2 Proporción de lugares importantes para la biodiversidad terrestre incluidos en zonas protegidas
  getIndicador_15_1_2: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.1.2:', error);
      throw error;
    }
  },
  
  // 15.2.1 Avances hacia la gestión forestal sostenible
  getIndicador_15_2_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.2.1:', error);
      throw error;
    }
  },
  
  // 15.3.1 Proporción de tierras degradadas en comparación con la superficie total
  getIndicador_15_3_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.3.1:', error);
      throw error;
    }
  },
  
  // 15.4.1 Lugares importantes para la biodiversidad de las montañas incluidos en zonas protegidas
  getIndicador_15_4_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.4.1:', error);
      throw error;
    }
  },
  
  // 15.4.2 Índice de cobertura verde de las montañas y proporción de terreno montañoso degradado
  getIndicador_15_4_2: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.4.2:', error);
      throw error;
    }
  },
  
  // 15.5.1 Índice de la Lista Roja
  getIndicador_15_5_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.5.1:', error);
      throw error;
    }
  },
  
  // 15.6.1 Número de países que han adoptado marcos legislativos para distribución justa de beneficios
  getIndicador_15_6_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.6.1:', error);
      throw error;
    }
  },
  
  // 15.7.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes de caza furtiva
  getIndicador_15_7_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.7.1:', error);
      throw error;
    }
  },
  
  // 15.8.1 Proporción de países que han aprobado legislación para prevención de especies exóticas invasoras
  getIndicador_15_8_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.8.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.8.1:', error);
      throw error;
    }
  },
  
  // 15.9.1 Número de países con metas nacionales acordes con la Meta 14 del Marco Mundial de Biodiversidad
  getIndicador_15_9_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.9.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.9.1:', error);
      throw error;
    }
  },
  
  // 15.a.1 Asistencia oficial para el desarrollo destinada a conservación y uso sostenible de la biodiversidad
  getIndicador_15_a_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.a.1:', error);
      throw error;
    }
  },
  
  // 15.b.1 Asistencia oficial para el desarrollo destinada a conservación y uso sostenible de la biodiversidad
  getIndicador_15_b_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.b.1:', error);
      throw error;
    }
  },
  
  // 15.c.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes de caza furtiva
  getIndicador_15_c_1: async () => {
    try {
      const response = await api.get(`/ods/15/indicadores/15.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 15.c.1:', error);
      throw error;
    }
  }
};
