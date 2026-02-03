// Mock service for ODS Objective 7 - Energía Asequible y No Contaminante
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo07Service = {
  // 7.1.1 Proporción de la población que tiene acceso a la electricidad
  getIndicador_7_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 98.5, 
      targetValue: 100.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que tiene acceso a la electricidad' 
    }; 
  },
  
  // 7.1.2 Proporción de la población con combustibles y tecnologías limpios
  getIndicador_7_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 68.3, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población cuya fuente primaria de energía son los combustibles y tecnologías limpios' 
    }; 
  },
  
  // 7.2.1 Proporción de energía renovable en el consumo final total de energía
  getIndicador_7_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 35.7, 
      targetValue: 45.0, 
      unit: 'porcentaje', 
      description: 'Proporción de energía renovable en el consumo final total de energía' 
    }; 
  },
  
  // 7.3.1 Intensidad energética medida en función de la energía primaria y el PIB
  getIndicador_7_3_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 5.2, 
      targetValue: 4.5, 
      unit: 'MJ por dólar de PIB', 
      description: 'Intensidad energética medida en función de la energía primaria y el PIB' 
    }; 
  },
  
  // 7.a.1 Corrientes financieras hacia países en desarrollo para energías limpias
  getIndicador_7_a_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 125.7, 
      targetValue: 150.0, 
      unit: 'miles de millones USD', 
      description: 'Corrientes financieras internacionales hacia los países en desarrollo para apoyar la investigación y el desarrollo de energías limpias y la producción de energía renovable' 
    }; 
  },
  
  // 7.b.1 Capacidad instalada de generación de energía renovable
  getIndicador_7_b_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 850, 
      targetValue: 1200, 
      unit: 'vatios per cápita', 
      description: 'Capacidad instalada de generación de energía renovable en los países en desarrollo y en los países desarrollados (en vatios per cápita)' 
    }; 
  }
};
