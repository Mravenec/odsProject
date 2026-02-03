// Mock service for ODS Objective 4 - Educación de Calidad
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo04Service = {
  // 4.1.1 Proporción de niños y adolescentes con nivel mínimo de competencia
  getIndicador_4_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de niños y adolescentes que, a) en los grados 2 o 3, b) al final de la educación primaria y c) al final de la educación secundaria inferior, han alcanzado al menos un nivel mínimo de competencia en lectura y matemáticas, desglosada por sexo' 
    }; 
  },
  
  // 4.1.2 Tasa de finalización de educación
  getIndicador_4_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 75.8, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Tasa de finalización (educación primaria, educación secundaria inferior y educación secundaria superior)' 
    }; 
  },
  
  // 4.2.1 Proporción de niños con desarrollo adecuado
  getIndicador_4_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 72.3, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de niños de 24 a 59 meses cuyo desarrollo es adecuado en cuanto a la salud, el aprendizaje y el bienestar psicosocial, desglosada por sexo' 
    }; 
  },
  
  // 4.2.2 Tasa de participación en el aprendizaje organizado
  getIndicador_4_2_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 85.7, 
      targetValue: 95.0, 
      unit: 'porcentaje', 
      description: 'Tasa de participación en el aprendizaje organizado (un año antes de la edad oficial de ingreso en la educación primaria), desglosada por sexo' 
    }; 
  },
  
  // 4.3.1 Tasa de participación de jóvenes y adultos en educación
  getIndicador_4_3_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45.2, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Tasa de participación de jóvenes y adultos en la educación y formación académica y no académica en los últimos 12 meses, desglosada por sexo' 
    }; 
  },
  
  // 4.4.1 Proporción de jóvenes y adultos con competencias en TIC
  getIndicador_4_4_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.9, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de jóvenes y adultos con competencias en tecnología de la información y las comunicaciones (TIC), desglosada por tipo de competencia' 
    }; 
  },
  
  // 4.5.1 Índices de paridad para indicadores de educación
  getIndicador_4_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 0.95, 
      targetValue: 1.0, 
      unit: 'índice de paridad', 
      description: 'Índices de paridad (entre mujeres y hombres, zonas rurales y urbanas, quintiles de riqueza superior e inferior y grupos como los discapacitados, los pueblos indígenas y los afectados por los conflictos) para todos los indicadores de educación de esta lista que puedan desglosarse' 
    }; 
  },
  
  // 4.6.1 Tasa de alfabetización de adultos/jóvenes
  getIndicador_4_6_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 96.8, 
      targetValue: 99.0, 
      unit: 'porcentaje', 
      description: 'Tasa de alfabetización de adultos/jóvenes' 
    }; 
  },
  
  // 4.7.1 Grado de incorporación de educación para ciudadanía mundial y desarrollo sostenible
  getIndicador_4_7_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 55.3, 
      targetValue: 75.0, 
      unit: 'índice de incorporación', 
      description: 'Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible se incorporan en a) las políticas nacionales de educación, b) los planes de estudio, c) la formación de docentes y d) la evaluación de los estudiantes' 
    }; 
  },
  
  // 4.a.1 Proporción de escuelas que ofrecen servicios básicos
  getIndicador_4_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 78.5, 
      targetValue: 90.0, 
      unit: 'porcentaje', 
      description: 'Proporción de escuelas que ofrecen servicios básicos, desglosada por tipo de servicio' 
    }; 
  },
  
  // 4.b.1 Volumen de la asistencia oficial para el desarrollo destinada a becas
  getIndicador_4_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 2.5, 
      targetValue: 4.0, 
      unit: 'miles de millones USD', 
      description: 'Volumen de la asistencia oficial para el desarrollo destinada a becas' 
    }; 
  },
  
  // 4.c.1 Proporción de docentes con calificaciones mínimas requeridas
  getIndicador_4_c_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 82.5, 
      targetValue: 90.0, 
      unit: 'porcentaje', 
      description: 'Proporción de docentes con las calificaciones mínimas requeridas, desglosada por nivel educativo' 
    }; 
  }
};
