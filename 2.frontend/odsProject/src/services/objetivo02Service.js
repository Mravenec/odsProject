import api from './api';
// Mock service for ODS Objective 2 - Hambre Cero
// Based on official SDG indicators from Global Indicator Framework
export const objetivo02Service = {
  // 2.1.1 Prevalencia de la subalimentación
  getIndicador_2_1_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.1.1:', error);
      throw error;
    }
  },
  
  // 2.1.2 Prevalencia de la inseguridad alimentaria moderada o grave
  getIndicador_2_1_2: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.1.2:', error);
      throw error;
    }
  },
  
  // 2.2.1 Prevalencia del retraso del crecimiento
  getIndicador_2_2_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.2.1:', error);
      throw error;
    }
  },
  
  // 2.2.2 Prevalencia de la malnutrición
  getIndicador_2_2_2: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.2.2:', error);
      throw error;
    }
  },
  
  // 2.2.3 Prevalencia de la anemia en mujeres
  getIndicador_2_2_3: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.2.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.2.3:', error);
      throw error;
    }
  },
  
  // 2.2.4 Prevalencia del umbral mínimo de diversidad alimentaria
  getIndicador_2_2_4: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.2.4?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.2.4:', error);
      throw error;
    }
  },
  
  // 2.3.1 Volumen de producción por unidad de trabajo
  getIndicador_2_3_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.3.1:', error);
      throw error;
    }
  },
  
  // 2.3.2 Media de ingresos de los productores de alimentos en pequeña escala
  getIndicador_2_3_2: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.3.2:', error);
      throw error;
    }
  },
  
  // 2.4.1 Proporción de la superficie agrícola en que se practica agricultura sostenible
  getIndicador_2_4_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.4.1:', error);
      throw error;
    }
  },
  
  // 2.5.1 Recursos genéticos para alimentos y agricultura
  getIndicador_2_5_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.5.1:', error);
      throw error;
    }
  },
  
  // 2.5.2 Proporción de razas y variedades locales en riesgo de extinción
  getIndicador_2_5_2: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.5.2:', error);
      throw error;
    }
  },
  
  // 2.a.1 Índice de orientación agrícola para el gasto público
  getIndicador_2_a_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.a.1:', error);
      throw error;
    }
  },
  
  // 2.a.2 Total de corrientes oficiales destinado al sector agrícola
  getIndicador_2_a_2: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.a.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.a.2:', error);
      throw error;
    }
  },
  
  // 2.b.1 Subsidios a la exportación de productos agropecuarios
  getIndicador_2_b_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.b.1:', error);
      throw error;
    }
  },
  
  // 2.c.1 Indicador de anomalías en los precios de los alimentos
  getIndicador_2_c_1: async () => {
    try {
      const response = await api.get(`/ods/02/indicadores/2.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 2.c.1:', error);
      throw error;
    }
  }
};
