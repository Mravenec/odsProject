// Mock service for ODS Objective 10 - Reducción de las Desigualdades
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo10Service = {
  // 10.1.1 Tasas de crecimiento per cápita de los gastos del 40% más pobre
  getIndicador_10_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 3.5, 
      targetValue: 4.5, 
      unit: 'porcentaje anual', 
      description: 'Tasas de crecimiento per cápita de los gastos o ingresos de los hogares del 40% más pobre de la población y la población total' 
    }; 
  },
  
  // 10.2.1 Proporción de personas que viven por debajo del 50% de la mediana de los ingresos
  getIndicador_10_2_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'porcentaje', 
      description: 'Proporción de personas que viven por debajo del 50% de la mediana de los ingresos, desglosada por sexo, edad y personas con discapacidad' 
    }; 
  },
  
  // 10.3.1 Proporción de la población que se siente discriminada o acosada
  getIndicador_10_3_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 15.8, 
      targetValue: 10.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que declara haberse sentido personalmente discriminada o acosada en los últimos 12 meses por motivos de discriminación prohibidos por el derecho internacional de los derechos humanos' 
    }; 
  },
  
  // 10.4.1 Proporción del PIB generado por el trabajo
  getIndicador_10_4_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 52.3, 
      targetValue: 55.0, 
      unit: 'porcentaje', 
      description: 'Proporción del PIB generado por el trabajo' 
    }; 
  },
  
  // 10.4.2 Impacto redistributivo de la política fiscal en el índice de Gini
  getIndicador_10_4_2: async () => { 
    await delay(700); 
    return { 
      currentValue: -0.05, 
      targetValue: -0.08, 
      unit: 'puntos de índice Gini', 
      description: 'Impacto redistributivo de la política fiscal en el índice de Gini' 
    }; 
  },
  
  // 10.5.1 Indicadores de solidez financiera
  getIndicador_10_5_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.5, 
      targetValue: 75.0, 
      unit: 'índice de solidez', 
      description: 'Indicadores de solidez financiera' 
    }; 
  },
  
  // 10.6.1 Proporción de miembros y derechos de voto de los países en desarrollo
  getIndicador_10_6_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 65.8, 
      targetValue: 75.0, 
      unit: 'porcentaje', 
      description: 'Proporción de miembros y derechos de voto de los países en desarrollo en organizaciones internacionales' 
    }; 
  },
  
  // 10.7.1 Costo de la contratación sufragado por el empleado
  getIndicador_10_7_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 8.5, 
      targetValue: 5.0, 
      unit: 'porcentaje de los ingresos', 
      description: 'Costo de la contratación sufragado por el empleado en proporción a los ingresos mensuales percibidos en el país de destino' 
    }; 
  },
  
  // 10.7.2 Proporción de países con políticas migratorias bien gestionadas
  getIndicador_10_7_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 45.2, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de países que han aplicado políticas migratorias bien gestionadas que facilitan la migración y la movilidad ordenadas, seguras, regulares y responsables' 
    }; 
  },
  
  // 10.7.3 Número de personas que murieron o desaparecieron en proceso de migración
  getIndicador_10_7_3: async () => { 
    await delay(750); 
    return { 
      currentValue: 4500, 
      targetValue: 3000, 
      unit: 'personas', 
      description: 'Número de personas que murieron o desaparecieron en el proceso de migración hacia un destino internacional' 
    }; 
  },
  
  // 10.7.4 Proporción de la población integrada por refugiados
  getIndicador_10_7_4: async () => { 
    await delay(700); 
    return { 
      currentValue: 2.8, 
      targetValue: 3.5, 
      unit: 'porcentaje', 
      description: 'Proporción de la población integrada por refugiados, desglosada por país de origen' 
    }; 
  },
  
  // 10.a.1 Proporción de líneas arancelarias aplicadas a importaciones de países menos adelantados
  getIndicador_10_a_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 85.2, 
      targetValue: 95.0, 
      unit: 'porcentaje', 
      description: 'Proporción de líneas arancelarias que se aplican a las importaciones de los países menos adelantados y los países en desarrollo con arancel cero' 
    }; 
  },
  
  // 10.b.1 Corrientes totales de recursos para el desarrollo
  getIndicador_10_b_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 185.5, 
      targetValue: 250.0, 
      unit: 'miles de millones USD', 
      description: 'Corrientes totales de recursos para el desarrollo (por ejemplo, asistencia oficial para el desarrollo, inversión extranjera directa y otras corrientes)' 
    }; 
  },
  
  // 10.c.1 Costo de las remesas en proporción a las sumas remitidas
  getIndicador_10_c_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 6.5, 
      targetValue: 3.0, 
      unit: 'porcentaje', 
      description: 'Costo de las remesas en proporción a las sumas remitidas' 
    }; 
  }
};
