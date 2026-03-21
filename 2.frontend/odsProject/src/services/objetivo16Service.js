import api from './api';
// Mock service for ODS Objective 16 - Paz, Justicia e Instituciones Sólidas
// Based on official SDG indicators from Global Indicator Framework
export const objetivo16Service = {
  // 16.1.1 Número de víctimas de homicidios intencionales por cada 100.000 habitantes
  getIndicador_16_1_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.1.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.1.1:', error);
      throw error;
    }
  },
  
  // 16.1.2 Muertes relacionadas con conflictos por cada 100.000 habitantes
  getIndicador_16_1_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.1.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.1.2:', error);
      throw error;
    }
  },
  
  // 16.1.3 Proporción de la población que ha sufrido violencia física, psicológica o sexual
  getIndicador_16_1_3: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.1.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.1.3:', error);
      throw error;
    }
  },
  
  // 16.1.4 Proporción de la población que se siente segura al caminar sola después de que oscurece
  getIndicador_16_1_4: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.1.4?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.1.4:', error);
      throw error;
    }
  },
  
  // 16.2.1 Proporción de niños que han sufrido castigo físico o agresión psicológica
  getIndicador_16_2_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.2.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.2.1:', error);
      throw error;
    }
  },
  
  // 16.2.2 Número de víctimas de la trata de personas por cada 100.000 habitantes
  getIndicador_16_2_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.2.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.2.2:', error);
      throw error;
    }
  },
  
  // 16.2.3 Proporción de jóvenes que sufrieron violencia sexual antes de cumplir los 18 años
  getIndicador_16_2_3: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.2.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.2.3:', error);
      throw error;
    }
  },
  
  // 16.3.1 Proporción de víctimas que han notificado su victimización a las autoridades
  getIndicador_16_3_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.3.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.3.1:', error);
      throw error;
    }
  },
  
  // 16.3.2 Proporción de detenidos que no han sido condenados en la población reclusa total
  getIndicador_16_3_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.3.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.3.2:', error);
      throw error;
    }
  },
  
  // 16.3.3 Proporción de la población que ha accedido a mecanismos de solución de controversias
  getIndicador_16_3_3: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.3.3?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.3.3:', error);
      throw error;
    }
  },
  
  // 16.4.1 Valor total de las corrientes financieras ilícitas entrantes y salientes
  getIndicador_16_4_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.4.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.4.1:', error);
      throw error;
    }
  },
  
  // 16.4.2 Proporción de armas incautadas cuyo origen ilícito ha sido determinado
  getIndicador_16_4_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.4.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.4.2:', error);
      throw error;
    }
  },
  
  // 16.5.1 Proporción de personas que han pagado un soborno a un funcionario público
  getIndicador_16_5_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.5.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.5.1:', error);
      throw error;
    }
  },
  
  // 16.5.2 Proporción de negocios que han pagado un soborno a un funcionario público
  getIndicador_16_5_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.5.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.5.2:', error);
      throw error;
    }
  },
  
  // 16.6.1 Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente
  getIndicador_16_6_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.6.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.6.1:', error);
      throw error;
    }
  },
  
  // 16.6.2 Proporción de la población que se siente satisfecha con sus servicios públicos
  getIndicador_16_6_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.6.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.6.2:', error);
      throw error;
    }
  },
  
  // 16.7.1 Proporciones de plazas en instituciones nacionales y locales
  getIndicador_16_7_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.7.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.7.1:', error);
      throw error;
    }
  },
  
  // 16.7.2 Proporción de la población que considera que la adopción de decisiones es inclusiva
  getIndicador_16_7_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.7.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.7.2:', error);
      throw error;
    }
  },
  
  // 16.8.1 Proporción de miembros y derechos de voto de los países en desarrollo
  getIndicador_16_8_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.8.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.8.1:', error);
      throw error;
    }
  },
  
  // 16.9.1 Proporción de niños menores de 5 años cuyo nacimiento se ha registrado
  getIndicador_16_9_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.9.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.9.1:', error);
      throw error;
    }
  },
  
  // 16.10.1 Número de casos verificados de asesinato, secuestro, desaparición forzada
  getIndicador_16_10_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.10.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.10.1:', error);
      throw error;
    }
  },
  
  // 16.10.2 Número de países que adoptan garantías para el acceso público a la información
  getIndicador_16_10_2: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.10.2?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.10.2:', error);
      throw error;
    }
  },
  
  // 16.a.1 Existencia de instituciones nacionales independientes de derechos humanos
  getIndicador_16_a_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.a.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.a.1:', error);
      throw error;
    }
  },
  
  // 16.b.1 Proporción de la población que declara haberse sentido discriminada o acosada
  getIndicador_16_b_1: async () => {
    try {
      const response = await api.get(`/ods/16/indicadores/16.b.1?proyectoId=1`);
      const data = response.data || {};
      return {
        currentValue: data.valorActual !== undefined ? data.valorActual : (data.valor_actual !== undefined ? data.valor_actual : 0),
        targetValue: data.valorMeta !== undefined ? data.valorMeta : (data.valor_meta !== undefined ? data.valor_meta : 0),
        unit: data.unidadMedida || data.unidad_medida || 'unidad',
        description: data.descripcion || ''
      };
    } catch (error) {
      console.error('Error fetching 16.b.1:', error);
      throw error;
    }
  }
};
