// Mock service for ODS Objective 9 - Industria, Innovación e Infraestructura
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo09Service = {
  // 9.1.1 Proporción de la población rural que vive cerca de carreteras transitable
  getIndicador_9_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 78.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población rural que vive a menos de 2 km de una carretera transitable todo el año' 
    }; 
  },
  
  // 9.1.2 Volumen de transporte de pasajeros y carga
  getIndicador_9_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 1250, 
      targetValue: 1500, 
      unit: 'millones de toneladas-kilómetro', 
      description: 'Volumen de transporte de pasajeros y carga, desglosado por medio de transporte' 
    }; 
  },
  
  // 9.2.1 Valor añadido del sector manufacturo en proporción al PIB
  getIndicador_9_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 16.8, 
      targetValue: 20.0, 
      unit: 'porcentaje del PIB', 
      description: 'Valor añadido del sector manufacturo en proporción al PIB y per cápita' 
    }; 
  },
  
  // 9.2.2 Empleo del sector manufacturero en proporción al empleo total
  getIndicador_9_2_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 22.5, 
      targetValue: 25.0, 
      unit: 'porcentaje', 
      description: 'Empleo del sector manufacturero en proporción al empleo total' 
    }; 
  },
  
  // 9.3.1 Proporción del valor añadido del sector industrial correspondiente a pequeñas industrias
  getIndicador_9_3_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45.2, 
      targetValue: 55.0, 
      unit: 'porcentaje', 
      description: 'Proporción del valor añadido total del sector industrial correspondiente a las pequeñas industrias, según a) la clasificación internacional y b) las clasificaciones nacionales' 
    }; 
  },
  
  // 9.3.2 Proporción de las pequeñas industrias que han obtenido préstamo o línea de crédito
  getIndicador_9_3_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 35.7, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Proporción de las pequeñas industrias que han obtenido un préstamo o una línea de crédito' 
    }; 
  },
  
  // 9.4.1 Emisiones de CO2 por unidad de valor añadido
  getIndicador_9_4_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 0.45, 
      targetValue: 0.35, 
      unit: 'kg CO2 por USD de valor añadido', 
      description: 'Emisiones de CO2 por unidad de valor añadido' 
    }; 
  },
  
  // 9.5.1 Gastos en investigación y desarrollo en proporción al PIB
  getIndicador_9_5_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 2.3, 
      targetValue: 2.5, 
      unit: 'porcentaje del PIB', 
      description: 'Gastos en investigación y desarrollo en proporción al PIB' 
    }; 
  },
  
  // 9.5.2 Número de investigadores por cada millón de habitantes
  getIndicador_9_5_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 1250, 
      targetValue: 1500, 
      unit: 'investigadores por millón', 
      description: 'Número de investigadores (en equivalente a tiempo completo) por cada millón de habitantes' 
    }; 
  },
  
  // 9.a.1 Total de apoyo internacional oficial destinado a infraestructura
  getIndicador_9_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85.7, 
      targetValue: 120.0, 
      unit: 'miles de millones USD', 
      description: 'Total de apoyo internacional oficial (asistencia oficial para el desarrollo más otras corrientes oficiales de recursos) destinado a la infraestructura' 
    }; 
  },
  
  // 9.b.1 Proporción del valor añadido por la industria de tecnología mediana y alta
  getIndicador_9_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 25.8, 
      targetValue: 35.0, 
      unit: 'porcentaje', 
      description: 'Proporción del valor añadido por la industria de tecnología mediana y alta en el valor añadido total' 
    }; 
  },
  
  // 9.c.1 Proporción de la población con cobertura de red móvil
  getIndicador_9_c_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 95.3, 
      targetValue: 98.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población con cobertura de red móvil, desglosada por tecnología' 
    }; 
  }
};
