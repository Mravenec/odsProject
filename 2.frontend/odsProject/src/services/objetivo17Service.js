// Mock service for ODS Objective 17 - Alianzas para Lograr los Objetivos
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo17Service = {
  // 17.1.1 Total de ingresos del gobierno en proporción al PIB
  getIndicador_17_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 28.5, 
      targetValue: 30.0, 
      unit: 'porcentaje del PIB', 
      description: 'Total de ingresos del gobierno en proporción al PIB, desglosado por fuente' 
    }; 
  },
  
  // 17.1.2 Proporción del presupuesto nacional financiado por impuestos internos
  getIndicador_17_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 65.8, 
      targetValue: 70.0, 
      unit: 'porcentaje', 
      description: 'Proporción del presupuesto nacional financiado por impuestos internos' 
    }; 
  },
  
  // 17.2.1 Asistencia oficial para el desarrollo neta
  getIndicador_17_2_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 0.35, 
      targetValue: 0.7, 
      unit: 'porcentaje del INB', 
      description: 'Asistencia oficial para el desarrollo neta, total y para los países menos adelantados en proporción al ingreso nacional bruto (INB)' 
    }; 
  },
  
  // 17.3.1 Recursos financieros adicionales movilizados para países en desarrollo
  getIndicador_17_3_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 285.5, 
      targetValue: 350.0, 
      unit: 'miles de millones USD', 
      description: 'Recursos financieros adicionales movilizados para los países en desarrollo procedentes de múltiples fuentes' 
    }; 
  },
  
  // 17.3.2 Volumen de remesas en proporción al PIB total
  getIndicador_17_3_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 5.2, 
      targetValue: 6.0, 
      unit: 'porcentaje del PIB', 
      description: 'Volumen de remesas (en dólares de los Estados Unidos) en proporción al PIB total' 
    }; 
  },
  
  // 17.4.1 Servicio de la deuda en proporción a las exportaciones
  getIndicador_17_4_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.8, 
      targetValue: 12.0, 
      unit: 'porcentaje', 
      description: 'Servicio de la deuda en proporción a las exportaciones de bienes, servicios e ingresos primarios' 
    }; 
  },
  
  // 17.5.1 Número de países que adoptan sistemas de promoción de inversiones
  getIndicador_17_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 125, 
      targetValue: 140, 
      unit: 'países', 
      description: 'Número de países que adoptan y aplican sistemas de promoción de las inversiones en favor de los países en desarrollo' 
    }; 
  },
  
  // 17.6.1 Número de abonados a servicios de banda ancha fija por cada 100 habitantes
  getIndicador_17_6_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 18.5, 
      targetValue: 25.0, 
      unit: 'por cada 100 habitantes', 
      description: 'Número de abonados a servicios de banda ancha fija por cada 100 habitantes, desglosado por velocidad' 
    }; 
  },
  
  // 17.7.1 Total de fondos destinados a promover desarrollo y transferencia de tecnologías
  getIndicador_17_7_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 45.8, 
      targetValue: 65.0, 
      unit: 'miles de millones USD', 
      description: 'Total de los fondos destinados a los países en desarrollo y los países desarrollados con el fin de promover el desarrollo, la transferencia y la difusión de tecnologías ecológicamente racionales' 
    }; 
  },
  
  // 17.8.1 Proporción de personas que utilizan Internet
  getIndicador_17_8_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 68.9, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de personas que utilizan Internet' 
    }; 
  },
  
  // 17.9.1 Valor en dólares de la asistencia oficial para el desarrollo comprometida
  getIndicador_17_9_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 185.5, 
      targetValue: 220.0, 
      unit: 'miles de millones USD', 
      description: 'Valor en dólares de la asistencia oficial para el desarrollo comprometida para los países en desarrollo' 
    }; 
  },
  
  // 17.10.1 Promedio arancelario mundial ponderado
  getIndicador_17_10_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 2.8, 
      targetValue: 2.5, 
      unit: 'porcentaje', 
      description: 'Promedio arancelario mundial ponderado' 
    }; 
  },
  
  // 17.11.1 Participación de los países en desarrollo en las exportaciones mundiales
  getIndicador_17_11_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 45.2, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Participación de los países en desarrollo y los países menos adelantados en las exportaciones mundiales' 
    }; 
  },
  
  // 17.12.1 Promedio ponderado de los aranceles que enfrentan los países en desarrollo
  getIndicador_17_12_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 6.5, 
      unit: 'porcentaje', 
      description: 'Promedio ponderado de los aranceles que enfrentan los países en desarrollo, los países menos adelantados y los pequeños Estados insulares en desarrollo' 
    }; 
  },
  
  // 17.13.1 Tablero macroeconómico
  getIndicador_17_13_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 72.3, 
      targetValue: 80.0, 
      unit: 'índice', 
      description: 'Tablero macroeconómico' 
    }; 
  },
  
  // 17.14.1 Número de países con mecanismos para mejorar coherencia de políticas
  getIndicador_17_14_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que cuentan con mecanismos para mejorar la coherencia de las políticas de desarrollo sostenible' 
    }; 
  },
  
  // 17.15.1 Grado de utilización de marcos de resultados y herramientas de planificación
  getIndicador_17_15_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'índice de utilización', 
      description: 'Grado de utilización de los marcos de resultados y las herramientas de planificación de los propios países por los proveedores de cooperación para el desarrollo' 
    }; 
  },
  
  // 17.16.1 Número de países que informan de sus progresos en marcos de múltiples interesados
  getIndicador_17_16_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 125, 
      targetValue: 140, 
      unit: 'países', 
      description: 'Número de países que informan de sus progresos en los marcos de múltiples interesados para el seguimiento de la eficacia de las actividades de desarrollo' 
    }; 
  },
  
  // 17.17.1 Suma en dólares prometida a alianzas público-privadas centradas en infraestructura
  getIndicador_17_17_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 85.5, 
      targetValue: 120.0, 
      unit: 'miles de millones USD', 
      description: 'Suma en dólares de los Estados Unidos prometida a las alianzas público-privadas centradas en la infraestructura' 
    }; 
  },
  
  // 17.18.1 Indicadores de la capacidad estadística
  getIndicador_17_18_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 65.8, 
      targetValue: 80.0, 
      unit: 'índice de capacidad', 
      description: 'Indicadores de la capacidad estadística' 
    }; 
  },
  
  // 17.18.2 Número de países cuya legislación nacional cumple Principios Fundamentales
  getIndicador_17_18_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 125, 
      targetValue: 140, 
      unit: 'países', 
      description: 'Número de países cuya legislación nacional sobre estadísticas cumple los Principios Fundamentales de las Estadísticas Oficiales' 
    }; 
  },
  
  // 17.18.3 Número de países con plan estadístico nacional plenamente financiado
  getIndicador_17_18_3: async () => { 
    await delay(750); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Número de países que cuentan con un plan estadístico nacional plenamente financiado y en proceso de aplicación' 
    }; 
  },
  
  // 17.19.1 Valor en dólares de recursos para fortalecer capacidad estadística
  getIndicador_17_19_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 12.0, 
      unit: 'miles de millones USD', 
      description: 'Valor en dólares de todos los recursos proporcionados para fortalecer la capacidad estadística de los países en desarrollo' 
    }; 
  },
  
  // 17.19.2 Proporción de países que realizan censo y registran nacimientos y defunciones
  getIndicador_17_19_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 92.5, 
      targetValue: 100.0, 
      unit: 'porcentaje', 
      description: 'Proporción de países que a) han realizado al menos un censo de población y vivienda y b) han registrado el 100% de los nacimientos y el 80% de las defunciones' 
    }; 
  }
};
