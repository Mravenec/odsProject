import api from './api';
// Mock service for ODS Objective 17 - Alianzas para Lograr los Objetivos
// Based on official SDG indicators from Global Indicator Framework
export const objetivo17Service = {
  // 17.1.1 Total de ingresos del gobierno en proporción al PIB
  getIndicador_17_1_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.1.1:', error);
      throw error;
    }
  },
  
  // 17.1.2 Proporción del presupuesto nacional financiado por impuestos internos
  getIndicador_17_1_2: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.1.2:', error);
      throw error;
    }
  },
  
  // 17.2.1 Asistencia oficial para el desarrollo neta
  getIndicador_17_2_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.2.1:', error);
      throw error;
    }
  },
  
  // 17.3.1 Recursos financieros adicionales movilizados para países en desarrollo
  getIndicador_17_3_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.3.1:', error);
      throw error;
    }
  },
  
  // 17.3.2 Volumen de remesas en proporción al PIB total
  getIndicador_17_3_2: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.3.2:', error);
      throw error;
    }
  },
  
  // 17.4.1 Servicio de la deuda en proporción a las exportaciones
  getIndicador_17_4_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.4.1:', error);
      throw error;
    }
  },
  
  // 17.5.1 Número de países que adoptan sistemas de promoción de inversiones
  getIndicador_17_5_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.5.1:', error);
      throw error;
    }
  },
  
  // 17.6.1 Número de abonados a servicios de banda ancha fija por cada 100 habitantes
  getIndicador_17_6_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.6.1:', error);
      throw error;
    }
  },
  
  // 17.7.1 Total de fondos destinados a promover desarrollo y transferencia de tecnologías
  getIndicador_17_7_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.7.1:', error);
      throw error;
    }
  },
  
  // 17.8.1 Proporción de personas que utilizan Internet
  getIndicador_17_8_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.8.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.8.1:', error);
      throw error;
    }
  },
  
  // 17.9.1 Valor en dólares de la asistencia oficial para el desarrollo comprometida
  getIndicador_17_9_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.9.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.9.1:', error);
      throw error;
    }
  },
  
  // 17.10.1 Promedio arancelario mundial ponderado
  getIndicador_17_10_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.10.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.10.1:', error);
      throw error;
    }
  },
  
  // 17.11.1 Participación de los países en desarrollo en las exportaciones mundiales
  getIndicador_17_11_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.11.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.11.1:', error);
      throw error;
    }
  },
  
  // 17.12.1 Promedio ponderado de los aranceles que enfrentan los países en desarrollo
  getIndicador_17_12_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.12.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.12.1:', error);
      throw error;
    }
  },
  
  // 17.13.1 Tablero macroeconómico
  getIndicador_17_13_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.13.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.13.1:', error);
      throw error;
    }
  },
  
  // 17.14.1 Número de países con mecanismos para mejorar coherencia de políticas
  getIndicador_17_14_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.14.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.14.1:', error);
      throw error;
    }
  },
  
  // 17.15.1 Grado de utilización de marcos de resultados y herramientas de planificación
  getIndicador_17_15_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.15.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.15.1:', error);
      throw error;
    }
  },
  
  // 17.16.1 Número de países que informan de sus progresos en marcos de múltiples interesados
  getIndicador_17_16_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.16.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.16.1:', error);
      throw error;
    }
  },
  
  // 17.17.1 Suma en dólares prometida a alianzas público-privadas centradas en infraestructura
  getIndicador_17_17_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.17.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.17.1:', error);
      throw error;
    }
  },
  
  // 17.18.1 Indicadores de la capacidad estadística
  getIndicador_17_18_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.18.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.18.1:', error);
      throw error;
    }
  },
  
  // 17.18.2 Número de países cuya legislación nacional cumple Principios Fundamentales
  getIndicador_17_18_2: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.18.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.18.2:', error);
      throw error;
    }
  },
  
  // 17.18.3 Número de países con plan estadístico nacional plenamente financiado
  getIndicador_17_18_3: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.18.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.18.3:', error);
      throw error;
    }
  },
  
  // 17.19.1 Valor en dólares de recursos para fortalecer capacidad estadística
  getIndicador_17_19_1: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.19.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.19.1:', error);
      throw error;
    }
  },
  
  // 17.19.2 Proporción de países que realizan censo y registran nacimientos y defunciones
  getIndicador_17_19_2: async () => {
    try {
      const response = await api.get(`/ods/17/indicadores/17.19.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 17.19.2:', error);
      throw error;
    }
  }
};
