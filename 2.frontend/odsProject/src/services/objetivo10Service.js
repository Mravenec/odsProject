import api from './api';
// Mock service for ODS Objective 10 - Reducción de las Desigualdades
// Based on official SDG indicators from Global Indicator Framework
export const objetivo10Service = {
  // 10.1.1 Tasas de crecimiento per cápita de los gastos del 40% más pobre
  getIndicador_10_1_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.1.1:', error);
      throw error;
    }
  },
  
  // 10.2.1 Proporción de personas que viven por debajo del 50% de la mediana de los ingresos
  getIndicador_10_2_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.2.1:', error);
      throw error;
    }
  },
  
  // 10.3.1 Proporción de la población que se siente discriminada o acosada
  getIndicador_10_3_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.3.1:', error);
      throw error;
    }
  },
  
  // 10.4.1 Proporción del PIB generado por el trabajo
  getIndicador_10_4_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.4.1:', error);
      throw error;
    }
  },
  
  // 10.4.2 Impacto redistributivo de la política fiscal en el índice de Gini
  getIndicador_10_4_2: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.4.2:', error);
      throw error;
    }
  },
  
  // 10.5.1 Indicadores de solidez financiera
  getIndicador_10_5_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.5.1:', error);
      throw error;
    }
  },
  
  // 10.6.1 Proporción de miembros y derechos de voto de los países en desarrollo
  getIndicador_10_6_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.6.1:', error);
      throw error;
    }
  },
  
  // 10.7.1 Costo de la contratación sufragado por el empleado
  getIndicador_10_7_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.7.1:', error);
      throw error;
    }
  },
  
  // 10.7.2 Proporción de países con políticas migratorias bien gestionadas
  getIndicador_10_7_2: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.7.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.7.2:', error);
      throw error;
    }
  },
  
  // 10.7.3 Número de personas que murieron o desaparecieron en proceso de migración
  getIndicador_10_7_3: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.7.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.7.3:', error);
      throw error;
    }
  },
  
  // 10.7.4 Proporción de la población integrada por refugiados
  getIndicador_10_7_4: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.7.4?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.7.4:', error);
      throw error;
    }
  },
  
  // 10.a.1 Proporción de líneas arancelarias aplicadas a importaciones de países menos adelantados
  getIndicador_10_a_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.a.1:', error);
      throw error;
    }
  },
  
  // 10.b.1 Corrientes totales de recursos para el desarrollo
  getIndicador_10_b_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.b.1:', error);
      throw error;
    }
  },
  
  // 10.c.1 Costo de las remesas en proporción a las sumas remitidas
  getIndicador_10_c_1: async () => {
    try {
      const response = await api.get(`/ods/10/indicadores/10.c.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 10.c.1:', error);
      throw error;
    }
  }
};
