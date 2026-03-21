import api from './api';
// Mock service for ODS Objective 8 - Trabajo Decente y Crecimiento Económico
// Based on official SDG indicators from Global Indicator Framework
export const objetivo08Service = {
  // 8.1.1 Tasa de crecimiento anual del PIB real per cápita
  getIndicador_8_1_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.1.1:', error);
      throw error;
    }
  },
  
  // 8.2.1 Tasa de crecimiento anual del PIB real por persona empleada
  getIndicador_8_2_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.2.1:', error);
      throw error;
    }
  },
  
  // 8.3.1 Proporción de empleo informal con respecto al empleo total
  getIndicador_8_3_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.3.1:', error);
      throw error;
    }
  },
  
  // 8.4.1 Huella material en términos absolutos, per cápita y por PIB
  getIndicador_8_4_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.4.1:', error);
      throw error;
    }
  },
  
  // 8.4.2 Consumo material interno en términos absolutos, per cápita y por PIB
  getIndicador_8_4_2: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.4.2:', error);
      throw error;
    }
  },
  
  // 8.5.1 Ingreso medio por hora de las personas empleadas
  getIndicador_8_5_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.5.1:', error);
      throw error;
    }
  },
  
  // 8.5.2 Tasa de desempleo
  getIndicador_8_5_2: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.5.2:', error);
      throw error;
    }
  },
  
  // 8.6.1 Proporción de jóvenes que no cursan estudios, no están empleados ni reciben capacitación
  getIndicador_8_6_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.6.1:', error);
      throw error;
    }
  },
  
  // 8.7.1 Proporción de niños que realizan trabajo infantil
  getIndicador_8_7_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.7.1:', error);
      throw error;
    }
  },
  
  // 8.8.1 Lesiones ocupacionales mortales y no mortales
  getIndicador_8_8_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.8.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.8.1:', error);
      throw error;
    }
  },
  
  // 8.8.2 Nivel de cumplimiento nacional de los derechos laborales
  getIndicador_8_8_2: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.8.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.8.2:', error);
      throw error;
    }
  },
  
  // 8.9.1 PIB generado directamente por el turismo
  getIndicador_8_9_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.9.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.9.1:', error);
      throw error;
    }
  },
  
  // 8.9.2 Personas empleadas en el sector del turismo
  getIndicador_8_9_2: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.9.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.9.2:', error);
      throw error;
    }
  },
  
  // 8.10.1 Número de sucursales de bancos comerciales y cajeros automáticos
  getIndicador_8_10_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.10.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.10.1:', error);
      throw error;
    }
  },
  
  // 8.10.2 Proporción de adultos que tienen cuenta en banco
  getIndicador_8_10_2: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.10.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.10.2:', error);
      throw error;
    }
  },
  
  // 8.a.1 Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio
  getIndicador_8_a_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.a.1:', error);
      throw error;
    }
  },
  
  // 8.b.1 Existencia de una estrategia nacional para el empleo de los jóvenes
  getIndicador_8_b_1: async () => {
    try {
      const response = await api.get(`/ods/08/indicadores/8.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 8.b.1:', error);
      throw error;
    }
  }
};
