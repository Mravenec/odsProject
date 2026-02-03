// Mock service for ODS Objective 8 - Trabajo Decente y Crecimiento Económico
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo08Service = {
  // 8.1.1 Tasa de crecimiento anual del PIB real per cápita
  getIndicador_8_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 3.2, 
      targetValue: 4.0, 
      unit: 'porcentaje anual', 
      description: 'Tasa de crecimiento anual del PIB real per cápita' 
    }; 
  },
  
  // 8.2.1 Tasa de crecimiento anual del PIB real por persona empleada
  getIndicador_8_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 2.8, 
      targetValue: 3.5, 
      unit: 'porcentaje anual', 
      description: 'Tasa de crecimiento anual del PIB real por persona empleada' 
    }; 
  },
  
  // 8.3.1 Proporción de empleo informal con respecto al empleo total
  getIndicador_8_3_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 28.3, 
      targetValue: 20.0, 
      unit: 'porcentaje', 
      description: 'Proporción de empleo informal con respecto al empleo total, desglosada por sector y sexo' 
    }; 
  },
  
  // 8.4.1 Huella material en términos absolutos, per cápita y por PIB
  getIndicador_8_4_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 15.8, 
      targetValue: 12.0, 
      unit: 'toneladas per cápita', 
      description: 'Huella material en términos absolutos, huella material per cápita y huella material por PIB' 
    }; 
  },
  
  // 8.4.2 Consumo material interno en términos absolutos, per cápita y por PIB
  getIndicador_8_4_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 18.5, 
      targetValue: 15.0, 
      unit: 'toneladas per cápita', 
      description: 'Consumo material interno en términos absolutos, consumo material interno per cápita y consumo material interno por PIB' 
    }; 
  },
  
  // 8.5.1 Ingreso medio por hora de las personas empleadas
  getIndicador_8_5_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.2, 
      targetValue: 18.0, 
      unit: 'USD por hora', 
      description: 'Ingreso medio por hora de las personas empleadas, desglosado por sexo, edad, ocupación y personas con discapacidad' 
    }; 
  },
  
  // 8.5.2 Tasa de desempleo
  getIndicador_8_5_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 6.8, 
      targetValue: 5.0, 
      unit: 'porcentaje', 
      description: 'Tasa de desempleo, desglosada por sexo, edad y personas con discapacidad' 
    }; 
  },
  
  // 8.6.1 Proporción de jóvenes que no cursan estudios, no están empleados ni reciben capacitación
  getIndicador_8_6_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'porcentaje', 
      description: 'Proporción de jóvenes (entre 15 y 24 años) que no cursan estudios, no están empleados ni reciben capacitación' 
    }; 
  },
  
  // 8.7.1 Proporción de niños que realizan trabajo infantil
  getIndicador_8_7_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.2, 
      targetValue: 5.0, 
      unit: 'porcentaje', 
      description: 'Proporción y número de niños de entre 5 y 17 años que realizan trabajo infantil, desglosados por sexo y edad' 
    }; 
  },
  
  // 8.8.1 Lesiones ocupacionales mortales y no mortales
  getIndicador_8_8_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 3.5, 
      targetValue: 2.0, 
      unit: 'por cada 100,000 trabajadores', 
      description: 'Lesiones ocupacionales mortales y no mortales por cada 100.000 trabajadores, desglosadas por sexo y estatus migratorio' 
    }; 
  },
  
  // 8.8.2 Nivel de cumplimiento nacional de los derechos laborales
  getIndicador_8_8_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'índice de cumplimiento', 
      description: 'Nivel de cumplimiento nacional de los derechos laborales (libertad de asociación y negociación colectiva) con arreglo a las fuentes textuales de la Organización Internacional del Trabajo (OIT)' 
    }; 
  },
  
  // 8.9.1 PIB generado directamente por el turismo
  getIndicador_8_9_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 9.2, 
      targetValue: 10.0, 
      unit: 'porcentaje del PIB', 
      description: 'PIB generado directamente por el turismo en proporción al PIB total y a la tasa de crecimiento' 
    }; 
  },
  
  // 8.9.2 Personas empleadas en el sector del turismo
  getIndicador_8_9_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 8.5, 
      targetValue: 10.0, 
      unit: 'porcentaje del empleo total', 
      description: 'Personas empleadas en el sector del turismo' 
    }; 
  },
  
  // 8.10.1 Número de sucursales de bancos comerciales y cajeros automáticos
  getIndicador_8_10_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 12.5, 
      targetValue: 15.0, 
      unit: 'por cada 100,000 adultos', 
      description: 'Número de sucursales de bancos comerciales por cada 100.000 adultos y número de cajeros automáticos por cada 100.000 adultos' 
    }; 
  },
  
  // 8.10.2 Proporción de adultos que tienen cuenta en banco
  getIndicador_8_10_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 78.9, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de adultos (a partir de 15 años de edad) que tienen una cuenta en un banco u otra institución financiera o un proveedor de servicios de dinero móvil' 
    }; 
  },
  
  // 8.a.1 Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio
  getIndicador_8_a_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'miles de millones USD', 
      description: 'Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio' 
    }; 
  },
  
  // 8.b.1 Existencia de una estrategia nacional para el empleo de los jóvenes
  getIndicador_8_b_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 72, 
      targetValue: 85, 
      unit: 'países', 
      description: 'Existencia de una estrategia nacional organizada y en marcha para el empleo de los jóvenes, como estrategia independiente o como parte de una estrategia nacional de empleo' 
    }; 
  }
};
