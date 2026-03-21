import api from './api';
// Mock service for ODS Objective 5 - Igualdad de Género
// Based on official SDG indicators from Global Indicator Framework
export const objetivo05Service = {
  // 5.1.1 Existencia de marcos jurídicos para promover igualdad y no discriminación
  getIndicador_5_1_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.1.1:', error);
      throw error;
    }
  },
  
  // 5.2.1 Proporción de mujeres que han sufrido violencia de pareja
  getIndicador_5_2_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.2.1:', error);
      throw error;
    }
  },
  
  // 5.2.2 Proporción de mujeres que han sufrido violencia sexual
  getIndicador_5_2_2: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.2.2:', error);
      throw error;
    }
  },
  
  // 5.3.1 Proporción de mujeres casadas antes de los 15 y 18 años
  getIndicador_5_3_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.3.1:', error);
      throw error;
    }
  },
  
  // 5.3.2 Proporción de niñas y mujeres que han sufrido mutilación genital femenina
  getIndicador_5_3_2: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.3.2:', error);
      throw error;
    }
  },
  
  // 5.4.1 Proporción de tiempo dedicado al trabajo doméstico no remunerado
  getIndicador_5_4_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.4.1:', error);
      throw error;
    }
  },
  
  // 5.5.1 Proporción de escaños ocupados por mujeres en parlamentos y gobiernos locales
  getIndicador_5_5_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.5.1:', error);
      throw error;
    }
  },
  
  // 5.5.2 Proporción de mujeres en cargos directivos
  getIndicador_5_5_2: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.5.2:', error);
      throw error;
    }
  },
  
  // 5.6.1 Proporción de mujeres que toman decisiones informadas sobre salud reproductiva
  getIndicador_5_6_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.6.1:', error);
      throw error;
    }
  },
  
  // 5.6.2 Número de países con leyes que garantizan acceso a salud sexual y reproductiva
  getIndicador_5_6_2: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.6.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.6.2:', error);
      throw error;
    }
  },
  
  // 5.a.1 Proporción de población agrícola con derechos de propiedad sobre tierras
  getIndicador_5_a_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.a.1:', error);
      throw error;
    }
  },
  
  // 5.a.2 Proporción de países con leyes que garantizan igualdad de derechos de la mujer a la tierra
  getIndicador_5_a_2: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.a.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.a.2:', error);
      throw error;
    }
  },
  
  // 5.b.1 Proporción de personas que poseen teléfono móvil, desglosada por sexo
  getIndicador_5_b_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.b.1:', error);
      throw error;
    }
  },
  
  // 5.c.1 Proporción de países con sistemas para seguimiento de igualdad de género
  getIndicador_5_c_1: async () => {
    try {
      const response = await api.get(`/ods/05/indicadores/5.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 5.c.1:', error);
      throw error;
    }
  }
};
