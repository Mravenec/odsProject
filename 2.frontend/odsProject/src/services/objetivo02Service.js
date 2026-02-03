// Mock service for ODS Objective 2 - Hambre Cero
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo02Service = {
  // 2.1.1 Prevalencia de la subalimentación
  getIndicador_2_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.2, 
      targetValue: 5.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia de la subalimentación' 
    }; 
  },
  
  // 2.1.2 Prevalencia de la inseguridad alimentaria moderada o grave
  getIndicador_2_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 25.8, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia de la inseguridad alimentaria moderada o grave entre la población, según la escala de experiencia de inseguridad alimentaria' 
    }; 
  },
  
  // 2.2.1 Prevalencia del retraso del crecimiento
  getIndicador_2_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 22.5, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia del retraso del crecimiento (estatura para la edad, desviación típica < -2 de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años' 
    }; 
  },
  
  // 2.2.2 Prevalencia de la malnutrición
  getIndicador_2_2_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 7.3, 
      targetValue: 5.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia de la malnutrición (peso para la estatura, desviación típica > +2 o < -2 de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años, desglosada por tipo (emaciación y sobrepeso)' 
    }; 
  },
  
  // 2.2.3 Prevalencia de la anemia en mujeres
  getIndicador_2_2_3: async () => { 
    await delay(700); 
    return { 
      currentValue: 29.8, 
      targetValue: 20.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia de la anemia en las mujeres de entre 15 y 49 años, desglosada por embarazo (porcentaje)' 
    }; 
  },
  
  // 2.2.4 Prevalencia del umbral mínimo de diversidad alimentaria
  getIndicador_2_2_4: async () => { 
    await delay(800); 
    return { 
      currentValue: 65.2, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia del umbral mínimo de diversidad alimentaria, por grupo de población (niños de 6 a 23,9 meses y mujeres no embarazadas de 15 a 49 años)' 
    }; 
  },
  
  // 2.3.1 Volumen de producción por unidad de trabajo
  getIndicador_2_3_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 1850, 
      targetValue: 2200, 
      unit: 'USD por trabajador', 
      description: 'Volumen de producción por unidad de trabajo desglosado por tamaño y tipo de explotación (agropecuaria/ganadera/forestal)' 
    }; 
  },
  
  // 2.3.2 Media de ingresos de los productores de alimentos en pequeña escala
  getIndicador_2_3_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 3200, 
      targetValue: 4500, 
      unit: 'USD anuales', 
      description: 'Media de ingresos de los productores de alimentos en pequeña escala, desglosada por sexo y condición indígena' 
    }; 
  },
  
  // 2.4.1 Proporción de la superficie agrícola en que se practica agricultura sostenible
  getIndicador_2_4_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 42.5, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la superficie agrícola en que se practica una agricultura productiva y sostenible' 
    }; 
  },
  
  // 2.5.1 Recursos genéticos para alimentos y agricultura
  getIndicador_2_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 5850, 
      targetValue: 6500, 
      unit: 'accesiones', 
      description: 'Número de: a) recursos genéticos vegetales y b) animales para la alimentación y la agricultura preservados en instalaciones de conservación a medio y largo plazo' 
    }; 
  },
  
  // 2.5.2 Proporción de razas y variedades locales en riesgo de extinción
  getIndicador_2_5_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 22.5, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de razas y variedades locales y transfronterizas consideradas en riesgo de extinción' 
    }; 
  },
  
  // 2.a.1 Índice de orientación agrícola para el gasto público
  getIndicador_2_a_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 0.85, 
      targetValue: 1.0, 
      unit: 'índice', 
      description: 'Índice de orientación agrícola para el gasto público' 
    }; 
  },
  
  // 2.a.2 Total de corrientes oficiales destinado al sector agrícola
  getIndicador_2_a_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 12.5, 
      targetValue: 15.0, 
      unit: 'miles de millones USD', 
      description: 'Total de corrientes oficiales de recursos (asistencia oficial para el desarrollo más otras corrientes oficiales) destinado al sector agrícola' 
    }; 
  },
  
  // 2.b.1 Subsidios a la exportación de productos agropecuarios
  getIndicador_2_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 5.0, 
      unit: 'miles de millones USD', 
      description: 'Subsidios a la exportación de productos agropecuarios' 
    }; 
  },
  
  // 2.c.1 Indicador de anomalías en los precios de los alimentos
  getIndicador_2_c_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.2, 
      targetValue: 10.0, 
      unit: 'índice', 
      description: 'Indicador de anomalías en los precios de los alimentos' 
    }; 
  }
};
