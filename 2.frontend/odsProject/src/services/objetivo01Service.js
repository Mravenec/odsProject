// Mock service for ODS Objective 1 - Fin de la Pobreza
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo01Service = {
  // 1.1.1 Proporción de la población que vive por debajo del umbral internacional de pobreza
  getIndicador_1_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.5, 
      targetValue: 5.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que vive por debajo del umbral internacional de pobreza, desglosada por sexo, edad, situación laboral y ubicación geográfica' 
    }; 
  },
  
  // 1.2.1 Proporción de la población que vive por debajo del umbral nacional de pobreza
  getIndicador_1_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 22.3, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que vive por debajo del umbral nacional de pobreza, desglosada por sexo y edad' 
    }; 
  },
  
  // 1.2.2 Proporción de personas que viven en la pobreza multidimensional
  getIndicador_1_2_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.7, 
      targetValue: 10.0, 
      unit: 'porcentaje', 
      description: 'Proporción de hombres, mujeres y niños de todas las edades que viven en la pobreza, en todas sus dimensiones' 
    }; 
  },
  
  // 1.3.1 Proporción de la población cubierta por sistemas de protección social
  getIndicador_1_3_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población cubierta por sistemas o niveles mínimos de protección social, desglosada por sexo' 
    }; 
  },
  
  // 1.4.1 Proporción de la población que vive en hogares con acceso a servicios básicos
  getIndicador_1_4_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 78.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que vive en hogares con acceso a los servicios básicos' 
    }; 
  },
  
  // 1.4.2 Proporción del total de la población adulta con derechos seguros de tenencia de la tierra
  getIndicador_1_4_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 62.3, 
      targetValue: 75.0, 
      unit: 'porcentaje', 
      description: 'Proporción del total de la población adulta con derechos seguros de tenencia de la tierra, desglosada por sexo y tipo de tenencia' 
    }; 
  },
  
  // 1.5.1 Número de personas muertas, desaparecidas y afectadas directamente por desastres
  getIndicador_1_5_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'por cada 100,000 habitantes', 
      description: 'Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres por cada 100.000 habitantes' 
    }; 
  },
  
  // 1.5.2 Pérdidas económicas directas atribuidas a los desastres
  getIndicador_1_5_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 2.3, 
      targetValue: 1.5, 
      unit: 'porcentaje del PIB mundial', 
      description: 'Pérdidas económicas directas atribuidas a los desastres en relación con el producto interno bruto (PIB) mundial' 
    }; 
  },
  
  // 1.5.3 Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
  getIndicador_1_5_3: async () => { 
    await delay(800); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo de desastres en consonancia con el Marco de Sendái' 
    }; 
  },
  
  // 1.5.4 Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
  getIndicador_1_5_4: async () => { 
    await delay(700); 
    return { 
      currentValue: 68.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción del riesgo de desastres' 
    }; 
  },
  
  // 1.a.1 Total de subvenciones de asistencia oficial para el desarrollo destinadas a la reducción de la pobreza
  getIndicador_1_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 0.25, 
      targetValue: 0.35, 
      unit: 'porcentaje de la RNB', 
      description: 'Total de subvenciones de asistencia oficial para el desarrollo destinadas a la reducción de la pobreza en porcentaje de la renta nacional bruta' 
    }; 
  },
  
  // 1.a.2 Proporción del gasto público total dedicado a servicios esenciales
  getIndicador_1_a_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 12.5, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción del gasto público total que se dedica a servicios esenciales (educación, salud y protección social)' 
    }; 
  },
  
  // 1.b.1 Gasto público social en favor de los pobres
  getIndicador_1_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 12.0, 
      unit: 'porcentaje del PIB', 
      description: 'Gasto público social en favor de los pobres' 
    }; 
  }
};
