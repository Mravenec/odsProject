// Mock service for ODS Objective 14 - Vida Submarina
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo14Service = {
  // 14.1.1 Índice de eutrofización costera y densidad de detritos plásticos
  getIndicador_14_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: { eutrophication: 65.3, plasticDebris: 45.8 }, 
      targetValue: { eutrophication: 50.0, plasticDebris: 30.0 }, 
      unit: 'índice', 
      description: 'Índice de eutrofización costera; y densidad de detritos plásticos' 
    }; 
  },
  
  // 14.2.1 Número de países que aplican enfoques basados en los ecosistemas
  getIndicador_14_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que aplican enfoques basados en los ecosistemas para gestionar las zonas marinas' 
    }; 
  },
  
  // 14.3.1 Acidez media del mar (pH) medida en estaciones de muestreo
  getIndicador_14_3_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.2, 
      targetValue: 8.1, 
      unit: 'pH', 
      description: 'Acidez media del mar (pH) medida en un conjunto convenido de estaciones de muestreo representativas' 
    }; 
  },
  
  // 14.4.1 Proporción de poblaciones de peces con niveles biológicamente sostenibles
  getIndicador_14_4_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de poblaciones de peces cuyos niveles son biológicamente sostenibles' 
    }; 
  },
  
  // 14.5.1 Cobertura de las zonas protegidas en relación con las zonas marinas
  getIndicador_14_5_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 15.8, 
      targetValue: 30.0, 
      unit: 'porcentaje', 
      description: 'Cobertura de las zonas protegidas en relación con las zonas marinas' 
    }; 
  },
  
  // 14.6.1 Grado de aplicación de instrumentos internacionales contra pesca ilegal
  getIndicador_14_6_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 72.3, 
      targetValue: 85.0, 
      unit: 'índice de aplicación', 
      description: 'Grado de aplicación de instrumentos internacionales cuyo objetivo es combatir la pesca ilegal, no declarada y no reglamentada' 
    }; 
  },
  
  // 14.7.1 Proporción del PIB correspondiente a la pesca sostenible
  getIndicador_14_7_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 5.8, 
      targetValue: 8.0, 
      unit: 'porcentaje del PIB', 
      description: 'Proporción del PIB correspondiente a la pesca sostenible en los pequeños Estados insulares en desarrollo, en los países menos adelantados y en todos los países' 
    }; 
  },
  
  // 14.a.1 Proporción del presupuesto total de investigación asignada a tecnología marina
  getIndicador_14_a_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 2.5, 
      targetValue: 4.0, 
      unit: 'porcentaje', 
      description: 'Proporción del presupuesto total de investigación asignada a la investigación en el campo de la tecnología marina' 
    }; 
  },
  
  // 14.b.1 Grado de aplicación de un marco jurídico que reconozca derechos de acceso para pesca
  getIndicador_14_b_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 65.8, 
      targetValue: 80.0, 
      unit: 'índice de aplicación', 
      description: 'Grado de aplicación de un marco jurídico, reglamentario, normativo o institucional que reconozca y proteja los derechos de acceso para la pesca en pequeña escala' 
    }; 
  },
  
  // 14.c.1 Número de países que avanzan en la ratificación de instrumentos relacionados con los océanos
  getIndicador_14_c_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 125, 
      targetValue: 140, 
      unit: 'países', 
      description: 'Número de países que, mediante marcos jurídicos, normativos e institucionales, avanzan en la ratificación, la aceptación y la implementación de los instrumentos relacionados con los océanos' 
    }; 
  }
};
