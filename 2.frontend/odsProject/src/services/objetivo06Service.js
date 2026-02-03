// Mock service for ODS Objective 6 - Agua Limpia y Saneamiento
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo06Service = {
  // 6.1.1 Proporción de la población que utiliza servicios de suministro de agua potable
  getIndicador_6_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 92.5, 
      targetValue: 95.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que utiliza servicios de suministro de agua potable gestionados sin riesgos' 
    }; 
  },
  
  // 6.2.1 Proporción de la población que utiliza saneamiento y lavado de manos
  getIndicador_6_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85.3, 
      targetValue: 90.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que utiliza: a) servicios de saneamiento gestionados sin riesgos y b) instalaciones para el lavado de manos con agua y jabón' 
    }; 
  },
  
  // 6.3.1 Proporción de aguas residuales tratadas adecuadamente
  getIndicador_6_3_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 65.2, 
      targetValue: 75.0, 
      unit: 'porcentaje', 
      description: 'Proporción de los flujos de aguas residuales domésticas e industriales tratados de manera adecuada' 
    }; 
  },
  
  // 6.3.2 Proporción de masas de agua de buena calidad
  getIndicador_6_3_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 72.8, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de masas de agua de buena calidad' 
    }; 
  },
  
  // 6.4.1 Cambio en el uso eficiente de los recursos hídricos
  getIndicador_6_4_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 15.8, 
      targetValue: 12.0, 
      unit: 'm³ por dólar de PIB', 
      description: 'Cambio en el uso eficiente de los recursos hídricos con el paso del tiempo' 
    }; 
  },
  
  // 6.4.2 Nivel de estrés hídrico
  getIndicador_6_4_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 42.3, 
      targetValue: 35.0, 
      unit: 'porcentaje', 
      description: 'Nivel de estrés hídrico: extracción de agua dulce en proporción a los recursos de agua dulce disponibles' 
    }; 
  },
  
  // 6.5.1 Grado de gestión integrada de los recursos hídricos
  getIndicador_6_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 58.7, 
      targetValue: 70.0, 
      unit: 'índice de gestión', 
      description: 'Grado de gestión integrada de los recursos hídricos' 
    }; 
  },
  
  // 6.5.2 Proporción de cuencas transfronterizas con cooperación
  getIndicador_6_5_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 65.8, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la superficie de cuencas transfronterizas sujetas a arreglos operacionales para la cooperación en materia de aguas' 
    }; 
  },
  
  // 6.6.1 Cambio en la extensión de los ecosistemas relacionados con el agua
  getIndicador_6_6_1: async () => { 
    await delay(800); 
    return { 
      currentValue: -2.5, 
      targetValue: 0.0, 
      unit: 'porcentaje anual', 
      description: 'Cambio en la extensión de los ecosistemas relacionados con el agua con el paso del tiempo' 
    }; 
  },
  
  // 6.a.1 Volumen de asistencia oficial para el desarrollo destinada al agua y saneamiento
  getIndicador_6_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 8.5, 
      targetValue: 12.0, 
      unit: 'miles de millones USD', 
      description: 'Volumen de la asistencia oficial para el desarrollo destinada al agua y el saneamiento que forma parte de un plan de gastos coordinados por el gobierno' 
    }; 
  },
  
  // 6.b.1 Proporción de dependencias locales con políticas de participación comunitaria
  getIndicador_6_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de dependencias administrativas locales que han establecido políticas y procedimientos operacionales para la participación de las comunidades locales en la gestión del agua y el saneamiento' 
    }; 
  }
};
