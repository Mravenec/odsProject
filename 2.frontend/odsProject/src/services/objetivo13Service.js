// Mock service for ODS Objective 13 - Acción por el Clima
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo13Service = {
  // 13.1.1 Número de personas muertas, desaparecidas y afectadas directamente por desastres
  getIndicador_13_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.2, 
      targetValue: 10.0, 
      unit: 'por cada 100.000 personas', 
      description: 'Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres por cada 100.000 personas' 
    }; 
  },
  
  // 13.1.2 Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
  getIndicador_13_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 92, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo de desastres en consonancia con el Marco de Sendái' 
    }; 
  },
  
  // 13.1.3 Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
  getIndicador_13_1_3: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción del riesgo de desastres' 
    }; 
  },
  
  // 13.2.1 Número de países con contribuciones determinadas a nivel nacional
  getIndicador_13_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 185, 
      targetValue: 195, 
      unit: 'países', 
      description: 'Número de países con contribuciones determinadas a nivel nacional, estrategias a largo plazo, planes nacionales de adaptación y comunicaciones sobre la adaptación' 
    }; 
  },
  
  // 13.2.2 Emisiones totales de gases de efecto invernadero por año
  getIndicador_13_2_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 52.5, 
      targetValue: 45.0, 
      unit: 'GtCO2e', 
      description: 'Emisiones totales de gases de efecto invernadero por año' 
    }; 
  },
  
  // 13.3.1 Grado en que se incorpora educación para ciudadanía mundial y desarrollo sostenible
  getIndicador_13_3_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 58.4, 
      targetValue: 75.0, 
      unit: 'índice de incorporación', 
      description: 'Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible se incorporan en políticas nacionales de educación' 
    }; 
  },
  
  // 13.a.1 Cantidades proporcionadas y movilizadas en relación con el objetivo actual
  getIndicador_13_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85.7, 
      targetValue: 100.0, 
      unit: 'miles de millones USD', 
      description: 'Cantidades proporcionadas y movilizadas en dólares de los Estados Unidos al año en relación con el objetivo actual mantenido de movilización colectiva de 100.000 millones de dólares' 
    }; 
  },
  
  // 13.b.1 Número de países menos adelantados y pequeños Estados insulares con contribuciones
  getIndicador_13_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45, 
      targetValue: 52, 
      unit: 'países', 
      description: 'Número de países menos adelantados y pequeños Estados insulares en desarrollo con contribuciones determinadas a nivel nacional, estrategias a largo plazo' 
    }; 
  }
};
