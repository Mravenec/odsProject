// Mock service for ODS Objective 16 - Paz, Justicia e Instituciones Sólidas
// Based on official SDG indicators from Global Indicator Framework
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const objetivo16Service = {
  // 16.1.1 Número de víctimas de homicidios intencionales por cada 100.000 habitantes
  getIndicador_16_1_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 5.2, 
      targetValue: 3.0, 
      unit: 'por cada 100.000 habitantes', 
      description: 'Número de víctimas de homicidios intencionales por cada 100.000 habitantes, desglosado por sexo y edad' 
    }; 
  },
  
  // 16.1.2 Muertes relacionadas con conflictos por cada 100.000 habitantes
  getIndicador_16_1_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 12.5, 
      targetValue: 8.0, 
      unit: 'por cada 100.000 habitantes', 
      description: 'Muertes relacionadas con conflictos por cada 100.000 habitantes, desglosadas por sexo, edad y causa' 
    }; 
  },
  
  // 16.1.3 Proporción de la población que ha sufrido violencia física, psicológica o sexual
  getIndicador_16_1_3: async () => { 
    await delay(800); 
    return { 
      currentValue: 22.8, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que ha sufrido a) violencia física, b) violencia psicológica o c) violencia sexual en los últimos 12 meses' 
    }; 
  },
  
  // 16.1.4 Proporción de la población que se siente segura al caminar sola después de que oscurece
  getIndicador_16_1_4: async () => { 
    await delay(750); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que se siente segura al caminar sola en su zona de residencia después de que oscurece' 
    }; 
  },
  
  // 16.2.1 Proporción de niños que han sufrido castigo físico o agresión psicológica
  getIndicador_16_2_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 35.7, 
      targetValue: 20.0, 
      unit: 'porcentaje', 
      description: 'Proporción de niños de entre 1 y 17 años que han sufrido algún castigo físico o agresión psicológica a manos de sus cuidadores en el último mes' 
    }; 
  },
  
  // 16.2.2 Número de víctimas de la trata de personas por cada 100.000 habitantes
  getIndicador_16_2_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 2.5, 
      targetValue: 1.5, 
      unit: 'por cada 100.000 habitantes', 
      description: 'Número de víctimas de la trata de personas por cada 100.000 habitantes, desglosado por sexo, edad y tipo de explotación' 
    }; 
  },
  
  // 16.2.3 Proporción de jóvenes que sufrieron violencia sexual antes de cumplir los 18 años
  getIndicador_16_2_3: async () => { 
    await delay(750); 
    return { 
      currentValue: 18.5, 
      targetValue: 12.0, 
      unit: 'porcentaje', 
      description: 'Proporción de mujeres y hombres jóvenes de entre 18 y 29 años que sufrieron violencia sexual antes de cumplir los 18 años' 
    }; 
  },
  
  // 16.3.1 Proporción de víctimas que han notificado su victimización a las autoridades
  getIndicador_16_3_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 45.8, 
      targetValue: 60.0, 
      unit: 'porcentaje', 
      description: 'Proporción de víctimas de violencia física, psicológica o sexual que han notificado su victimización a las autoridades competentes' 
    }; 
  },
  
  // 16.3.2 Proporción de detenidos que no han sido condenados en la población reclusa total
  getIndicador_16_3_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 35.2, 
      targetValue: 25.0, 
      unit: 'porcentaje', 
      description: 'Proporción de detenidos que no han sido condenados en el conjunto de la población reclusa total' 
    }; 
  },
  
  // 16.3.3 Proporción de la población que ha accedido a mecanismos de solución de controversias
  getIndicador_16_3_3: async () => { 
    await delay(750); 
    return { 
      currentValue: 28.5, 
      targetValue: 40.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que se ha visto implicada en alguna controversia y ha accedido a algún mecanismo oficial u oficioso de solución de controversias' 
    }; 
  },
  
  // 16.4.1 Valor total de las corrientes financieras ilícitas entrantes y salientes
  getIndicador_16_4_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 125.5, 
      targetValue: 80.0, 
      unit: 'miles de millones USD', 
      description: 'Valor total de las corrientes financieras ilícitas entrantes y salientes (en dólares corrientes de los Estados Unidos)' 
    }; 
  },
  
  // 16.4.2 Proporción de armas incautadas cuyo origen ilícito ha sido determinado
  getIndicador_16_4_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 68.5, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de armas incautadas, encontradas o entregadas cuyo origen o contexto ilícitos han sido determinados por una autoridad competente' 
    }; 
  },
  
  // 16.5.1 Proporción de personas que han pagado un soborno a un funcionario público
  getIndicador_16_5_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 15.8, 
      targetValue: 10.0, 
      unit: 'porcentaje', 
      description: 'Proporción de personas que han tenido al menos un contacto con un funcionario público y que han pagado un soborno a un funcionario público durante los últimos 12 meses' 
    }; 
  },
  
  // 16.5.2 Proporción de negocios que han pagado un soborno a un funcionario público
  getIndicador_16_5_2: async () => { 
    await delay(700); 
    return { 
      currentValue: 22.5, 
      targetValue: 15.0, 
      unit: 'porcentaje', 
      description: 'Proporción de negocios que han tenido al menos un contacto con un funcionario público y que han pagado un soborno a un funcionario público' 
    }; 
  },
  
  // 16.6.1 Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente
  getIndicador_16_6_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 95.2, 
      targetValue: 98.0, 
      unit: 'porcentaje', 
      description: 'Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente, desglosados por sector' 
    }; 
  },
  
  // 16.6.2 Proporción de la población que se siente satisfecha con sus servicios públicos
  getIndicador_16_6_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 72.3, 
      targetValue: 80.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que se siente satisfecha con su última experiencia de los servicios públicos' 
    }; 
  },
  
  // 16.7.1 Proporciones de plazas en instituciones nacionales y locales
  getIndicador_16_7_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 42.3, 
      targetValue: 50.0, 
      unit: 'porcentaje', 
      description: 'Proporciones de plazas en las instituciones nacionales y locales, entre ellas: a) las asambleas legislativas, b) la administración pública y c) el poder judicial' 
    }; 
  },
  
  // 16.7.2 Proporción de la población que considera que la adopción de decisiones es inclusiva
  getIndicador_16_7_2: async () => { 
    await delay(800); 
    return { 
      currentValue: 58.7, 
      targetValue: 70.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que considera que la adopción de decisiones es inclusiva y responde a sus necesidades' 
    }; 
  },
  
  // 16.8.1 Proporción de miembros y derechos de voto de los países en desarrollo
  getIndicador_16_8_1: async () => { 
    await delay(750); 
    return { 
      currentValue: 65.8, 
      targetValue: 75.0, 
      unit: 'porcentaje', 
      description: 'Proporción de miembros y derechos de voto de los países en desarrollo en organizaciones internacionales' 
    }; 
  },
  
  // 16.9.1 Proporción de niños menores de 5 años cuyo nacimiento se ha registrado
  getIndicador_16_9_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 85.2, 
      targetValue: 95.0, 
      unit: 'porcentaje', 
      description: 'Proporción de niños menores de 5 años cuyo nacimiento se ha registrado ante una autoridad civil, desglosada por edad' 
    }; 
  },
  
  // 16.10.1 Número de casos verificados de asesinato, secuestro, desaparición forzada
  getIndicador_16_10_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 125, 
      targetValue: 80, 
      unit: 'casos', 
      description: 'Número de casos verificados de asesinato, secuestro, desaparición forzada, detención arbitraria y tortura de periodistas y defensores de los derechos humanos' 
    }; 
  },
  
  // 16.10.2 Número de países que adoptan garantías para el acceso público a la información
  getIndicador_16_10_2: async () => { 
    await delay(750); 
    return { 
      currentValue: 135, 
      targetValue: 150, 
      unit: 'países', 
      description: 'Número de países que adoptan y aplican garantías constitucionales, legales o normativas para el acceso público a la información' 
    }; 
  },
  
  // 16.a.1 Existencia de instituciones nacionales independientes de derechos humanos
  getIndicador_16_a_1: async () => { 
    await delay(700); 
    return { 
      currentValue: 85, 
      targetValue: 100, 
      unit: 'países', 
      description: 'Existencia de instituciones nacionales independientes de derechos humanos, en cumplimiento de los Principios de París' 
    }; 
  },
  
  // 16.b.1 Proporción de la población que declara haberse sentido discriminada o acosada
  getIndicador_16_b_1: async () => { 
    await delay(800); 
    return { 
      currentValue: 18.5, 
      targetValue: 12.0, 
      unit: 'porcentaje', 
      description: 'Proporción de la población que declara haberse sentido personalmente discriminada o acosada en los últimos 12 meses por motivos de discriminación prohibidos' 
    }; 
  }
};
