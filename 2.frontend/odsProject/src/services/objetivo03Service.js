import api from './api';
// Mock service for ODS Objective 3 - Salud y Bienestar
// Based on official SDG indicators from Global Indicator Framework
export const objetivo03Service = {
  // 3.1.1 Tasa de mortalidad materna
  getIndicador_3_1_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.1.1:', error);
      throw error;
    }
  },
  
  // 3.1.2 Proporción de partos atendidos por personal sanitario especializado
  getIndicador_3_1_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.1.2:', error);
      throw error;
    }
  },
  
  // 3.2.1 Tasa de mortalidad de niños menores de 5 años
  getIndicador_3_2_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.2.1:', error);
      throw error;
    }
  },
  
  // 3.2.2 Tasa de mortalidad neonatal
  getIndicador_3_2_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.2.2:', error);
      throw error;
    }
  },
  
  // 3.3.1 Número de nuevas infecciones por el VIH
  getIndicador_3_3_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.3.1:', error);
      throw error;
    }
  },
  
  // 3.3.2 Incidencia de la tuberculosis
  getIndicador_3_3_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.3.2:', error);
      throw error;
    }
  },
  
  // 3.3.3 Incidencia de la malaria
  getIndicador_3_3_3: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.3.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.3.3:', error);
      throw error;
    }
  },
  
  // 3.3.4 Incidencia de la hepatitis B
  getIndicador_3_3_4: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.3.4?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.3.4:', error);
      throw error;
    }
  },
  
  // 3.3.5 Número de personas que requieren intervenciones contra enfermedades tropicales desatendidas
  getIndicador_3_3_5: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.3.5?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.3.5:', error);
      throw error;
    }
  },
  
  // 3.4.1 Tasa de mortalidad atribuida a enfermedades no transmisibles
  getIndicador_3_4_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.4.1:', error);
      throw error;
    }
  },
  
  // 3.4.2 Tasa de mortalidad por suicidio
  getIndicador_3_4_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.4.2:', error);
      throw error;
    }
  },
  
  // 3.5.1 Cobertura de los tratamientos de trastornos por abuso de sustancias
  getIndicador_3_5_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.5.1:', error);
      throw error;
    }
  },
  
  // 3.5.2 Consumo de alcohol per cápita
  getIndicador_3_5_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.5.2:', error);
      throw error;
    }
  },
  
  // 3.6.1 Tasa de mortalidad por lesiones debidas a accidentes de tráfico
  getIndicador_3_6_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.6.1:', error);
      throw error;
    }
  },
  
  // 3.7.1 Proporción de mujeres que cubren sus necesidades de planificación familiar
  getIndicador_3_7_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.7.1:', error);
      throw error;
    }
  },
  
  // 3.7.2 Tasa de fecundidad de las adolescentes
  getIndicador_3_7_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.7.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.7.2:', error);
      throw error;
    }
  },
  
  // 3.8.1 Cobertura de los servicios de salud esenciales
  getIndicador_3_8_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.8.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.8.1:', error);
      throw error;
    }
  },
  
  // 3.8.2 Proporción de la población con grandes gastos sanitarios
  getIndicador_3_8_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.8.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.8.2:', error);
      throw error;
    }
  },
  
  // 3.9.1 Tasa de mortalidad atribuida a la contaminación del aire
  getIndicador_3_9_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.9.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.9.1:', error);
      throw error;
    }
  },
  
  // 3.9.2 Tasa de mortalidad atribuida al agua insalubre
  getIndicador_3_9_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.9.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.9.2:', error);
      throw error;
    }
  },
  
  // 3.9.3 Tasa de mortalidad atribuida a intoxicaciones involuntarias
  getIndicador_3_9_3: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.9.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.9.3:', error);
      throw error;
    }
  },
  
  // 3.a.1 Prevalencia del consumo actual de tabaco
  getIndicador_3_a_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.a.1:', error);
      throw error;
    }
  },
  
  // 3.b.1 Proporción de la población inmunizada con todas las vacunas
  getIndicador_3_b_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.b.1:', error);
      throw error;
    }
  },
  
  // 3.b.2 Total neto de asistencia oficial para el desarrollo destinado a salud
  getIndicador_3_b_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.b.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.b.2:', error);
      throw error;
    }
  },
  
  // 3.b.3 Índice de acceso a los productos sanitarios
  getIndicador_3_b_3: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.b.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.b.3:', error);
      throw error;
    }
  },
  
  // 3.c.1 Densidad y distribución del personal sanitario
  getIndicador_3_c_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.c.1:', error);
      throw error;
    }
  },
  
  // 3.d.1 Capacidad prevista en el Reglamento Sanitario Internacional
  getIndicador_3_d_1: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.d.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.d.1:', error);
      throw error;
    }
  },
  
  // 3.d.2 Porcentaje de infecciones del torrente sanguíneo resistentes a antimicrobianos
  getIndicador_3_d_2: async () => {
    try {
      const response = await api.get(`/ods/03/indicadores/3.d.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 3.d.2:', error);
      throw error;
    }
  }
};
