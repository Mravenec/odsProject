// Mock service for ODS Objective 5 - Igualdad de Género
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo05Service = {
  // 5.1.1 Existencia de marcos jurídicos para promover igualdad y no discriminación
  getIndicador_5_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Determinar si existen o no marcos jurídicos para promover, hacer cumplir y supervisar la igualdad y la no discriminación por razón de sexo' 
    }; 
  },
  
  // 5.2.1 Proporción de mujeres que han sufrido violencia de pareja
  getIndicador_5_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 28.7, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres y niñas a partir de 15 años de edad que han sufrido violencia física, sexual o psicológica a manos de su actual o anterior pareja en los últimos 12 meses, desglosada por forma de violencia y edad' 
    }; 
  },
  
  // 5.2.2 Proporción de mujeres que han sufrido violencia sexual
  getIndicador_5_2_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres y niñas a partir de 15 años de edad que han sufrido violencia sexual a manos de personas que no eran su pareja en los últimos 12 meses, desglosada por edad y lugar del hecho' 
    }; 
  },
  
  // 5.3.1 Proporción de mujeres casadas antes de los 15 y 18 años
  getIndicador_5_3_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 15.2, 
      targetValue: 10.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres de entre 20 y 24 años que estaban casadas o mantenían una unión estable antes de cumplir los 15 años y antes de cumplir los 18 años' 
    }; 
  },
  
  // 5.3.2 Proporción de niñas y mujeres que han sufrido mutilación genital femenina
  getIndicador_5_3_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 5.0, 
      unit: 'porcentaje', 
      description: 'Proporción de niñas y mujeres de entre 15 y 49 años que han sufrido mutilación genital femenina, desglosada por edad' 
    }; 
  },
  
  // 5.4.1 Proporción de tiempo dedicado al trabajo doméstico no remunerado
  getIndicador_5_4_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 75.3, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Proporción de tiempo dedicado al trabajo doméstico y asistencial no remunerado, desglosada por sexo, edad y ubicación' 
    }; 
  },
  
  // 5.5.1 Proporción de escaños ocupados por mujeres en parlamentos y gobiernos locales
  getIndicador_5_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 42.3, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Proporción de escaños ocupados por mujeres en a) los parlamentos nacionales y b) los gobiernos locales' 
    }; 
  },
  
  // 5.5.2 Proporción de mujeres en cargos directivos
  getIndicador_5_5_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 38.7, 
      targetValue: 45.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres en cargos directivos' 
    }; 
  },
  
  // 5.6.1 Proporción de mujeres que toman decisiones informadas sobre salud reproductiva
  getIndicador_5_6_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 78.5, 
      targetValue: 85.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres de entre 15 y 49 años que toman sus propias decisiones informadas sobre las relaciones sexuales, el uso de anticonceptivos y la atención de la salud reproductiva' 
    }; 
  },
  
  // 5.6.2 Número de países con leyes que garantizan acceso a salud sexual y reproductiva
  getIndicador_5_6_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 125, 
      targetValue: 150, 
      unit: 'países', 
      description: 'Número de países con leyes y reglamentos que garantizan a los hombres y las mujeres a partir de los 15 años de edad un acceso pleno e igualitario a los servicios de salud sexual y reproductiva' 
    }; 
  },
  
  // 5.a.1 Proporción de población agrícola con derechos de propiedad sobre tierras
  getIndicador_5_a_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 35.7, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Proporción del total de la población agrícola con derechos de propiedad o derechos seguros sobre tierras agrícolas, desglosada por sexo; y proporción de mujeres entre los propietarios o los titulares de derechos sobre tierras agrícolas' 
    }; 
  },
  
  // 5.a.2 Proporción de países con leyes que garantizan igualdad de derechos de la mujer a la tierra
  getIndicador_5_a_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 68, 
      targetValue: 85, 
      unit: 'países', 
      description: 'Proporción de países cuyo ordenamiento jurídico (incluido el derecho consuetudinario) garantiza la igualdad de derechos de la mujer a la propiedad o el control de las tierras' 
    }; 
  },
  
  // 5.b.1 Proporción de personas que poseen teléfono móvil, desglosada por sexo
  getIndicador_5_b_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 95.2, 
      targetValue: 98.0, 
      unit: 'porcentaje', 
      description: 'Proporción de personas que poseen un teléfono móvil, desglosada por sexo' 
    }; 
  },
  
  // 5.c.1 Proporción de países con sistemas para seguimiento de igualdad de género
  getIndicador_5_c_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 72, 
      targetValue: 85, 
      unit: 'países', 
      description: 'Proporción de países con sistemas para el seguimiento de la igualdad de género y el empoderamiento de las mujeres y la asignación de fondos públicos para ese fin' 
    }; 
  }
};
