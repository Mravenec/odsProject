// Mock service for ODS Objective 3 - Salud y Bienestar
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo03Service = {
  // 3.1.1 Tasa de mortalidad materna
  getIndicador_3_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 85.3, 
      targetValue: 70.0, 
      unit: 'muertes por 100,000 nacidos vivos', 
      description: 'Tasa de mortalidad materna' 
    }; 
  },
  
  // 3.1.2 Proporción de partos atendidos por personal sanitario especializado
  getIndicador_3_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 95.2, 
      targetValue: 98.0, 
      unit: 'porcentaje', 
      description: 'Proporción de partos atendidos por personal sanitario especializado' 
    }; 
  },
  
  // 3.2.1 Tasa de mortalidad de niños menores de 5 años
  getIndicador_3_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 18.3, 
      targetValue: 12.0, 
      unit: 'muertes por 1,000 nacidos vivos', 
      description: 'Tasa de mortalidad de niños menores de 5 años' 
    }; 
  },
  
  // 3.2.2 Tasa de mortalidad neonatal
  getIndicador_3_2_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'muertes por 1,000 nacidos vivos', 
      description: 'Tasa de mortalidad neonatal' 
    }; 
  },
  
  // 3.3.1 Número de nuevas infecciones por el VIH
  getIndicador_3_3_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 0.8, 
      targetValue: 0.5, 
      unit: 'por cada 1.000 habitantes', 
      description: 'Número de nuevas infecciones por el VIH por cada 1.000 habitantes no infectados, desglosado por sexo, edad y poblaciones clave' 
    }; 
  },
  
  // 3.3.2 Incidencia de la tuberculosis
  getIndicador_3_3_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 42.3, 
      targetValue: 35.0, 
      unit: 'por cada 100.000 habitantes', 
      description: 'Incidencia de la tuberculosis por cada 100.000 habitantes' 
    }; 
  },
  
  // 3.3.3 Incidencia de la malaria
  getIndicador_3_3_3: async () => { 
    await delay(750); 
    return { 
      currentValue: 8.7, 
      targetValue: 5.0, 
      unit: 'por cada 1.000 habitantes', 
      description: 'Incidencia de la malaria por cada 1.000 habitantes' 
    }; 
  },
  
  // 3.3.4 Incidencia de la hepatitis B
  getIndicador_3_3_4: async () => { 
    await delay(700); 
    return { 
      currentValue: 3.2, 
      targetValue: 2.0, 
      unit: 'por cada 100.000 habitantes', 
      description: 'Incidencia de la hepatitis B por cada 100.000 habitantes' 
    }; 
  },
  
  // 3.3.5 Número de personas que requieren intervenciones contra enfermedades tropicales desatendidas
  getIndicador_3_3_5: async () => { 
    await delay(800); 
    return { 
      currentValue: 1850000, 
      targetValue: 1000000, 
      unit: 'personas', 
      description: 'Número de personas que requieren intervenciones contra enfermedades tropicales desatendidas' 
    }; 
  },
  
  // 3.4.1 Tasa de mortalidad atribuida a enfermedades no transmisibles
  getIndicador_3_4_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 152.7, 
      targetValue: 120.0, 
      unit: 'muertes por 100.000 habitantes', 
      description: 'Tasa de mortalidad atribuida a las enfermedades cardiovasculares, el cáncer, la diabetes o las enfermedades respiratorias crónicas' 
    }; 
  },
  
  // 3.4.2 Tasa de mortalidad por suicidio
  getIndicador_3_4_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 9.8, 
      targetValue: 7.0, 
      unit: 'muertes por 100.000 habitantes', 
      description: 'Tasa de mortalidad por suicidio' 
    }; 
  },
  
  // 3.5.1 Cobertura de los tratamientos de trastornos por abuso de sustancias
  getIndicador_3_5_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 45.3, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Cobertura de los tratamientos (farmacológicos y psicosociales y servicios de rehabilitación y postratamiento) de trastornos por abuso de sustancias adictivas' 
    }; 
  },
  
  // 3.5.2 Consumo de alcohol per cápita
  getIndicador_3_5_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 6.8, 
      targetValue: 5.0, 
      unit: 'litros de alcohol puro', 
      description: 'Consumo de alcohol per cápita (a partir de los 15 años de edad) durante un año civil en litros de alcohol puro' 
    }; 
  },
  
  // 3.6.1 Tasa de mortalidad por lesiones debidas a accidentes de tráfico
  getIndicador_3_6_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 12.3, 
      targetValue: 8.0, 
      unit: 'muertes por 100.000 habitantes', 
      description: 'Tasa de mortalidad por lesiones debidas a accidentes de tráfico' 
    }; 
  },
  
  // 3.7.1 Proporción de mujeres que cubren sus necesidades de planificación familiar
  getIndicador_3_7_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 78.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres en edad de procrear (entre 15 y 49 años) que cubren sus necesidades de planificación familiar con métodos modernos' 
    }; 
  },
  
  // 3.7.2 Tasa de fecundidad de las adolescentes
  getIndicador_3_7_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 25.4, 
      targetValue: 15.0, 
      unit: 'nacimientos por 1.000 mujeres', 
      description: 'Tasa de fecundidad de las adolescentes (entre 10 y 14 años y entre 15 y 19 años) por cada 1.000 mujeres de ese grupo de edad' 
    }; 
  },
  
  // 3.8.1 Cobertura de los servicios de salud esenciales
  getIndicador_3_8_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 72.3, 
      targetValue: 80.0, 
      unit: 'índice de cobertura', 
      description: 'Cobertura de los servicios de salud esenciales' 
    }; 
  },
  
  // 3.8.2 Proporción de la población con grandes gastos sanitarios
  getIndicador_3_8_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población con grandes gastos sanitarios por hogar como porcentaje del total de gastos o ingresos de los hogares' 
    }; 
  },
  
  // 3.9.1 Tasa de mortalidad atribuida a la contaminación del aire
  getIndicador_3_9_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 45.7, 
      targetValue: 35.0, 
      unit: 'muertes por 100.000 habitantes', 
      description: 'Tasa de mortalidad atribuida a la contaminación de los hogares y del aire ambiente' 
    }; 
  },
  
  // 3.9.2 Tasa de mortalidad atribuida al agua insalubre
  getIndicador_3_9_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.9, 
      targetValue: 5.0, 
      unit: 'muertes por 100.000 habitantes', 
      description: 'Tasa de mortalidad atribuida al agua insalubre, el saneamiento deficiente y la falta de higiene' 
    }; 
  },
  
  // 3.9.3 Tasa de mortalidad atribuida a intoxicaciones involuntarias
  getIndicador_3_9_3: async () => { 
    await delay(800); 
    return { 
      currentValue: 3.2, 
      targetValue: 2.0, 
      unit: 'muertes por 100.000 habitantes', 
      description: 'Tasa de mortalidad atribuida a intoxicaciones involuntarias' 
    }; 
  },
  
  // 3.a.1 Prevalencia del consumo actual de tabaco
  getIndicador_3_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 18.5, 
      targetValue: 12.0, 
      unit: 'porcentaje', 
      description: 'Prevalencia del consumo actual de tabaco a partir de los 15 años de edad (edades ajustadas)' 
    }; 
  },
  
  // 3.b.1 Proporción de la población inmunizada con todas las vacunas
  getIndicador_3_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 85.2, 
      targetValue: 95.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población inmunizada con todas las vacunas incluidas en cada programa nacional' 
    }; 
  },
  
  // 3.b.2 Total neto de asistencia oficial para el desarrollo destinado a salud
  getIndicador_3_b_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.5, 
      targetValue: 12.0, 
      unit: 'miles de millones USD', 
      description: 'Total neto de asistencia oficial para el desarrollo destinado a los sectores de la investigación médica y la atención sanitaria básica' 
    }; 
  },
  
  // 3.b.3 Índice de acceso a los productos sanitarios
  getIndicador_3_b_3: async () => { 
    await delay(750); 
    return { 
      currentValue: 68.4, 
      targetValue: 80.0, 
      unit: 'índice', 
      description: 'Índice de acceso a los productos sanitarios' 
    }; 
  },
  
  // 3.c.1 Densidad y distribución del personal sanitario
  getIndicador_3_c_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45.8, 
      targetValue: 55.0, 
      unit: 'profesionales por 10,000 habitantes', 
      description: 'Densidad y distribución del personal sanitario' 
    }; 
  },
  
  // 3.d.1 Capacidad prevista en el Reglamento Sanitario Internacional
  getIndicador_3_d_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.4, 
      targetValue: 85.0, 
      unit: 'índice de capacidad', 
      description: 'Capacidad prevista en el Reglamento Sanitario Internacional (RSI) y preparación para emergencias de salud' 
    }; 
  },
  
  // 3.d.2 Porcentaje de infecciones del torrente sanguíneo resistentes a antimicrobianos
  getIndicador_3_d_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 15.2, 
      targetValue: 10.0, 
      unit: 'porcentaje', 
      description: 'Porcentaje de infecciones del torrente sanguíneo debidas a determinados organismos resistentes a los antimicrobianos seleccionados' 
    }; 
  }
};
