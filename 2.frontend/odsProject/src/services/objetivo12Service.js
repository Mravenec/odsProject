// Mock service for ODS Objective 12 - Producción y Consumo Responsables
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo12Service = {
  // 12.1.1 Número de países que elaboran instrumentos de política para consumo y producción sostenibles
  getIndicador_12_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 125, 
      targetValue: 150, 
      unit: 'países', 
      description: 'Número de países que elaboran, adoptan o aplican instrumentos de política destinados a apoyar la transición hacia modalidades de consumo y producción sostenibles' 
    }; 
  },
  
  // 12.2.1 Huella material en términos absolutos, per cápita y por PIB
  getIndicador_12_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 15.8, 
      targetValue: 12.0, 
      unit: 'toneladas per cápita', 
      description: 'Huella material en términos absolutos, huella material per cápita y huella material por PIB' 
    }; 
  },
  
  // 12.2.2 Consumo material interno en términos absolutos, per cápita y por PIB
  getIndicador_12_2_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 18.5, 
      targetValue: 15.0, 
      unit: 'toneladas per cápita', 
      description: 'Consumo material interno en términos absolutos, consumo material interno per cápita y consumo material interno por PIB' 
    }; 
  },
  
  // 12.3.1 Índice de pérdidas de alimentos y desperdicio de alimentos
  getIndicador_12_3_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 25.3, 
      targetValue: 15.0, 
      unit: 'índice', 
      description: 'Índice de pérdidas de alimentos y índice de desperdicio de alimentos' 
    }; 
  },
  
  // 12.4.1 Número de partes en acuerdos ambientales multilaterales sobre desechos peligrosos
  getIndicador_12_4_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 185, 
      targetValue: 195, 
      unit: 'partes', 
      description: 'Número de partes en los acuerdos ambientales multilaterales internacionales sobre desechos peligrosos y otros productos químicos que cumplen sus compromisos' 
    }; 
  },
  
  // 12.4.2 Desechos peligrosos generados per cápita y proporción de desechos peligrosos tratados
  getIndicador_12_4_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 8.5, 
      targetValue: 5.0, 
      unit: 'kg per cápita', 
      description: 'Desechos peligrosos generados per cápita y proporción de desechos peligrosos tratados, desglosados por tipo de tratamiento' 
    }; 
  },
  
  // 12.5.1 Tasa nacional de reciclado
  getIndicador_12_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 35.7, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Tasa nacional de reciclado, en toneladas de material reciclado' 
    }; 
  },
  
  // 12.6.1 Número de empresas que publican informes sobre sostenibilidad
  getIndicador_12_6_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Número de empresas que publican informes sobre sostenibilidad' 
    }; 
  },
  
  // 12.7.1 Número de países que aplican políticas y planes de acción sostenibles en adquisiciones públicas
  getIndicador_12_7_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que aplican políticas y planes de acción sostenibles en materia de adquisiciones públicas' 
    }; 
  },
  
  // 12.8.1 Grado en que se incorpora educación para ciudadanía mundial y desarrollo sostenible
  getIndicador_12_8_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 55.3, 
      targetValue: 75.0, 
      unit: 'índice de incorporación', 
      description: 'Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible se incorporan en políticas nacionales de educación' 
    }; 
  },
  
  // 12.a.1 Capacidad instalada de generación de energía renovable
  getIndicador_12_a_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 850, 
      targetValue: 1200, 
      unit: 'vatios per cápita', 
      description: 'Capacidad instalada de generación de energía renovable en los países en desarrollo y en los países desarrollados (en vatios per cápita)' 
    }; 
  },
  
  // 12.b.1 Aplicación de instrumentos normalizados de contabilidad para turismo sostenible
  getIndicador_12_b_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'índice de aplicación', 
      description: 'Aplicación de instrumentos normalizados de contabilidad para hacer un seguimiento de los aspectos económicos y ambientales de la sostenibilidad del turismo' 
    }; 
  },
  
  // 12.c.1 Cuantía de los subsidios a los combustibles fósiles por unidad del PIB
  getIndicador_12_c_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 2.5, 
      targetValue: 1.5, 
      unit: 'porcentaje del PIB', 
      description: 'Cuantía de los subsidios a los combustibles fósiles (producción y consumo) por unidad del PIB' 
    }; 
  }
};
