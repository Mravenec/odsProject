// Mock service for ODS Objective 11 - Ciudades y Comunidades Sostenibles
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo11Service = {
  // 11.1.1 Proporción de la población urbana que vive en barrios marginales
  getIndicador_11_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 25.3, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población urbana que vive en barrios marginales, asentamientos informales o viviendas inadecuadas' 
    }; 
  },
  
  // 11.2.1 Proporción de la población con fácil acceso al transporte público
  getIndicador_11_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 68.5, 
      targetValue: 75.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que tiene fácil acceso al transporte público, desglosada por sexo, edad y personas con discapacidad' 
    }; 
  },
  
  // 11.3.1 Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población
  getIndicador_11_3_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 1.8, 
      targetValue: 1.5, 
      unit: 'índice', 
      description: 'Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población' 
    }; 
  },
  
  // 11.3.2 Proporción de ciudades con participación directa de la sociedad civil
  getIndicador_11_3_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de ciudades que cuentan con una estructura de participación directa de la sociedad civil en la planificación y la gestión urbanas' 
    }; 
  },
  
  // 11.4.1 Total de gastos per cápita destinados a la preservación del patrimonio cultural y natural
  getIndicador_11_4_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 85.7, 
      targetValue: 120.0, 
      unit: 'USD per cápita', 
      description: 'Total de gastos per cápita destinados a la preservación, protección y conservación de todo el patrimonio cultural y natural' 
    }; 
  },
  
  // 11.5.1 Número de personas muertas, desaparecidas y afectadas directamente por desastres
  getIndicador_11_5_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.2, 
      targetValue: 10.0, 
      unit: 'por cada 100.000 personas', 
      description: 'Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres por cada 100.000 personas' 
    }; 
  },
  
  // 11.5.2 Pérdidas económicas directas atribuidas a los desastres
  getIndicador_11_5_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 2.8, 
      targetValue: 2.0, 
      unit: 'porcentaje del PIB mundial', 
      description: 'Pérdidas económicas directas atribuidas a los desastres en relación con el producto interno bruto (PIB) mundial' 
    }; 
  },
  
  // 11.5.3 Daños en la infraestructura crítica e interrupciones de servicios básicos
  getIndicador_11_5_3: async () => { 
    await delay(700); 
    return { 
      currentValue: 125, 
      targetValue: 80, 
      unit: 'incidentes', 
      description: 'Daños en la infraestructura crítica y número de interrupciones de los servicios básicos, atribuidos a desastres' 
    }; 
  },
  
  // 11.6.1 Proporción de residuos sólidos municipales recogidos y administrados
  getIndicador_11_6_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 72.3, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de residuos sólidos municipales recogidos y administrados en instalaciones controladas con respecto al total de residuos municipales generados' 
    }; 
  },
  
  // 11.6.2 Niveles medios anuales de partículas finas en las ciudades
  getIndicador_11_6_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 35.8, 
      targetValue: 25.0, 
      unit: 'μg/m³', 
      description: 'Niveles medios anuales de partículas finas en suspensión (por ejemplo, PM2.5 y PM10) en las ciudades (ponderados según la población)' 
    }; 
  },
  
  // 11.7.1 Proporción media de la superficie edificada dedicada a espacios abiertos públicos
  getIndicador_11_7_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 12.0, 
      unit: 'porcentaje', 
      description: 'Proporción media de la superficie edificada de las ciudades que se dedica a espacios abiertos para uso público de todos' 
    }; 
  },
  
  // 11.7.2 Proporción de personas que han sido víctimas de acoso en espacios públicos
  getIndicador_11_7_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 18.5, 
      targetValue: 12.0, 
      unit: 'porcentaje', 
      description: 'Proporción de personas que han sido víctimas de acoso no sexual o sexual en los últimos 12 meses, desglosada por sexo, edad, grado de discapacidad y lugar del hecho' 
    }; 
  },
  
  // 11.a.1 Número de países con políticas urbanas nacionales o planes de desarrollo regionales
  getIndicador_11_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que cuentan con políticas urbanas nacionales o planes de desarrollo regionales que responden a la dinámica de la población' 
    }; 
  },
  
  // 11.b.1 Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
  getIndicador_11_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 92, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo de desastres en consonancia con el Marco de Sendái' 
    }; 
  },
  
  // 11.b.2 Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
  getIndicador_11_b_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción del riesgo de desastres' 
    }; 
  },
  
  // 11.c.1 Total de asistencia oficial para el desarrollo destinada a infraestructuras urbanas
  getIndicador_11_c_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 45.8, 
      targetValue: 65.0, 
      unit: 'miles de millones USD', 
      description: 'Total de asistencia oficial para el desarrollo y otros flujos oficiales destinados a infraestructuras urbanas o proyectos de infraestructuras urbanas' 
    }; 
  }
};
