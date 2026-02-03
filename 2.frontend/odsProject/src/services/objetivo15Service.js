// Mock service for ODS Objective 15 - Vida de Ecosistemas Terrestres
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo15Service = {
  // 15.1.1 Superficie forestal en proporción a la superficie total
  getIndicador_15_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 31.2, 
      targetValue: 35.0, 
      unit: 'porcentaje', 
      description: 'Superficie forestal en proporción a la superficie total' 
    }; 
  },
  
  // 15.1.2 Proporción de lugares importantes para la biodiversidad terrestre incluidos en zonas protegidas
  getIndicador_15_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de lugares importantes para la biodiversidad terrestre y del agua dulce incluidos en zonas protegidas, desglosada por tipo de ecosistema' 
    }; 
  },
  
  // 15.2.1 Avances hacia la gestión forestal sostenible
  getIndicador_15_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 58.7, 
      targetValue: 70.0, 
      unit: 'índice de gestión sostenible', 
      description: 'Avances hacia la gestión forestal sostenible' 
    }; 
  },
  
  // 15.3.1 Proporción de tierras degradadas en comparación con la superficie total
  getIndicador_15_3_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 22.5, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de tierras degradadas en comparación con la superficie total' 
    }; 
  },
  
  // 15.4.1 Lugares importantes para la biodiversidad de las montañas incluidos en zonas protegidas
  getIndicador_15_4_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 52.3, 
      targetValue: 65.0, 
      unit: 'porcentaje', 
      description: 'Lugares importantes para la biodiversidad de las montañas incluidos en zonas protegidas' 
    }; 
  },
  
  // 15.4.2 Índice de cobertura verde de las montañas y proporción de terreno montañoso degradado
  getIndicador_15_4_2: async () => { 
    await delay(800); 
    return { 
      currentValue: { greenCover: 68.5, degradedLand: 18.2 }, 
      targetValue: { greenCover: 75.0, degradedLand: 12.0 }, 
      unit: 'índice/porcentaje', 
      description: 'Índice de cobertura verde de las montañas y proporción de terreno montañoso degradado' 
    }; 
  },
  
  // 15.5.1 Índice de la Lista Roja
  getIndicador_15_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 0.28, 
      targetValue: 0.20, 
      unit: 'índice', 
      description: 'Índice de la Lista Roja' 
    }; 
  },
  
  // 15.6.1 Número de países que han adoptado marcos legislativos para distribución justa de beneficios
  getIndicador_15_6_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que han adoptado marcos legislativos, administrativos y normativos para asegurar una distribución justa y equitativa de los beneficios' 
    }; 
  },
  
  // 15.7.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes de caza furtiva
  getIndicador_15_7_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'porcentaje', 
      description: 'Proporción de especímenes de flora y fauna silvestre comercializados procedentes de la caza furtiva o el tráfico ilícito' 
    }; 
  },
  
  // 15.8.1 Proporción de países que han aprobado legislación para prevención de especies exóticas invasoras
  getIndicador_15_8_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 68, 
      targetValue: 85, 
      unit: 'países', 
      description: 'Proporción de países que han aprobado la legislación nacional pertinente y han destinado recursos suficientes para la prevención o el control de las especies exóticas invasoras' 
    }; 
  },
  
  // 15.9.1 Número de países con metas nacionales acordes con la Meta 14 del Marco Mundial de Biodiversidad
  getIndicador_15_9_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 125, 
      targetValue: 140, 
      unit: 'países', 
      description: 'Número de países que han establecido metas nacionales acordes o similares a la Meta 14 del Marco Mundial de Biodiversidad de Kunming-Montreal' 
    }; 
  },
  
  // 15.a.1 Asistencia oficial para el desarrollo destinada a conservación y uso sostenible de la biodiversidad
  getIndicador_15_a_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.5, 
      targetValue: 12.0, 
      unit: 'miles de millones USD', 
      description: 'Asistencia oficial para el desarrollo destinada concretamente a la conservación y el uso sostenible de la biodiversidad' 
    }; 
  },
  
  // 15.b.1 Asistencia oficial para el desarrollo destinada a conservación y uso sostenible de la biodiversidad
  getIndicador_15_b_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 2.5, 
      targetValue: 4.0, 
      unit: 'miles de millones USD', 
      description: 'Ingresos generados y financiación movilizada mediante instrumentos económicos pertinentes para la biodiversidad' 
    }; 
  },
  
  // 15.c.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes de caza furtiva
  getIndicador_15_c_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'porcentaje', 
      description: 'Proporción de especímenes de flora y fauna silvestre comercializados procedentes de la caza furtiva o el tráfico ilícito' 
    }; 
  }
};
