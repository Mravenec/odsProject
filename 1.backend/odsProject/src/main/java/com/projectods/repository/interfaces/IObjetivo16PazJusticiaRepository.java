package com.projectods.repository.interfaces;

import com.projectods.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 16
 */
public interface IObjetivo16PazJusticiaRepository {
    
    /**
     * 16.1.1 Número de víctimas de homicidios intencionales por cada 100.000 habitantes, 
     * desglosado por sexo y edad [127]
     * 
     * @return Datos del indicador 16.1.1
     */
    IndicatorData findIndicador_16_1_1();
    
    /**
     * 16.1.2 Muertes relacionadas con conflictos por cada 100.000 habitantes, 
     * desglosadas por sexo, edad y causa [127]
     * 
     * @return Datos del indicador 16.1.2
     */
    IndicatorData findIndicador_16_1_2();
    
    /**
     * 16.1.3 Proporción de la población que ha sufrido a) violencia física, b) violencia psicológica 
     * o c) violencia sexual en los últimos 12 meses [127]
     * 
     * @return Datos del indicador 16.1.3
     */
    IndicatorData findIndicador_16_1_3();
    
    /**
     * 16.1.4 Proporción de la población que se siente segura al caminar sola en su zona 
     * de residencia después de que oscurece [127]
     * 
     * @return Datos del indicador 16.1.4
     */
    IndicatorData findIndicador_16_1_4();
    
    /**
     * 16.2.1 Proporción de niños de entre 1 y 17 años que han sufrido algún castigo físico 
     * o agresión psicológica a manos de sus cuidadores en el último mes [128]
     * 
     * @return Datos del indicador 16.2.1
     */
    IndicatorData findIndicador_16_2_1();
    
    /**
     * 16.2.2 Número de víctimas de la trata de personas por cada 100.000 habitantes, 
     * desglosado por sexo, edad y tipo de explotación [128]
     * 
     * @return Datos del indicador 16.2.2
     */
    IndicatorData findIndicador_16_2_2();
    
    /**
     * 16.2.3 Proporción de mujeres y hombres jóvenes de entre 18 y 29 años que sufrieron 
     * violencia sexual antes de cumplir los 18 años [128]
     * 
     * @return Datos del indicador 16.2.3
     */
    IndicatorData findIndicador_16_2_3();
    
    /**
     * 16.3.1 Proporción de víctimas de a) violencia física, b) violencia psicológica o c) violencia sexual 
     * en los últimos 12 meses que han notificado su victimización a las autoridades competentes 
     * u otros mecanismos de resolución de conflictos reconocidos oficialmente [129]
     * 
     * @return Datos del indicador 16.3.1
     */
    IndicatorData findIndicador_16_3_1();
    
    /**
     * 16.3.2 Proporción de detenidos que no han sido condenados en el conjunto de la población reclusa total [129]
     * 
     * @return Datos del indicador 16.3.2
     */
    IndicatorData findIndicador_16_3_2();
    
    /**
     * 16.3.3 Proporción de la población que se ha visto implicada en alguna controversia en los dos últimos años 
     * y ha accedido a algún mecanismo oficial u oficioso de solución de controversias, desglosada por tipo de mecanismo [129, 130]
     * 
     * @return Datos del indicador 16.3.3
     */
    IndicatorData findIndicador_16_3_3();
    
    /**
     * 16.4.1 Valor total de las corrientes financieras ilícitas entrantes y salientes 
     * (en dólares corrientes de los Estados Unidos) [130]
     * 
     * @return Datos del indicador 16.4.1
     */
    IndicatorData findIndicador_16_4_1();
    
    /**
     * 16.4.2 Proporción de armas incautadas, encontradas o entregadas cuyo origen o contexto ilícitos 
     * han sido determinados o establecidos por una autoridad competente, de conformidad con los instrumentos internacionales [130, 131]
     * 
     * @return Datos del indicador 16.4.2
     */
    IndicatorData findIndicador_16_4_2();
    
    /**
     * 16.5.1 Proporción de personas que han tenido al menos un contacto con un funcionario público 
     * y que han pagado un soborno a un funcionario público, o a las que un funcionario público les ha pedido un soborno, 
     * durante los últimos 12 meses [131, 132]
     * 
     * @return Datos del indicador 16.5.1
     */
    IndicatorData findIndicador_16_5_1();
    
    /**
     * 16.5.2 Proporción de negocios que han tenido al menos un contacto con un funcionario público 
     * y que han pagado un soborno a un funcionario público, o a los que un funcionario público les ha pedido un soborno, 
     * durante los últimos 12 meses [132]
     * 
     * @return Datos del indicador 16.5.2
     */
    IndicatorData findIndicador_16_5_2();
    
    /**
     * 16.6.1 Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente, 
     * desglosados por sector (o por códigos presupuestarios o elementos similares) [132, 133]
     * 
     * @return Datos del indicador 16.6.1
     */
    IndicatorData findIndicador_16_6_1();
    
    /**
     * 16.6.2 Proporción de la población que se siente satisfecha con su última experiencia de los servicios públicos [133]
     * 
     * @return Datos del indicador 16.6.2
     */
    IndicatorData findIndicador_16_6_2();
    
    /**
     * 16.7.1 Proporciones de plazas en las instituciones nacionales y locales, entre ellas: a) las asambleas legislativas, 
     * b) la administración pública y c) el poder judicial, en comparación con la distribución nacional, 
     * desglosadas por sexo, edad, personas con discapacidad y grupos de población [133, 134]
     * 
     * @return Datos del indicador 16.7.1
     */
    IndicatorData findIndicador_16_7_1();
    
    /**
     * 16.7.2 Proporción de la población que considera que la adopción de decisiones es inclusiva 
     * y responde a sus necesidades, desglosada por sexo, edad, discapacidad y grupo de población [134]
     * 
     * @return Datos del indicador 16.7.2
     */
    IndicatorData findIndicador_16_7_2();
    
    /**
     * 16.8.1 Proporción de miembros y derechos de voto de los países en desarrollo en organizaciones internacionales [134]
     * 
     * @return Datos del indicador 16.8.1
     */
    IndicatorData findIndicador_16_8_1();
    
    /**
     * 16.9.1 Proporción de niños menores de 5 años cuyo nacimiento se ha registrado ante una autoridad civil, 
     * desglosada por edad [135]
     * 
     * @return Datos del indicador 16.9.1
     */
    IndicatorData findIndicador_16_9_1();
    
    /**
     * 16.10.1 Número de casos verificados de asesinato, secuestro, desaparición forzada, detención arbitraria 
     * y tortura de periodistas, miembros asociados de los medios de comunicación, sindicalistas 
     * y defensores de los derechos humanos, en los últimos 12 meses [135]
     * 
     * @return Datos del indicador 16.10.1
     */
    IndicatorData findIndicador_16_10_1();
    
    /**
     * 16.10.2 Número de países que adoptan y aplican garantías constitucionales, legales o normativas 
     * para el acceso público a la información [136]
     * 
     * @return Datos del indicador 16.10.2
     */
    IndicatorData findIndicador_16_10_2();
    
    /**
     * 16.a.1 Existencia de instituciones nacionales independientes de derechos humanos, 
     * en cumplimiento de los Principios de París [136]
     * 
     * @return Datos del indicador 16.a.1
     */
    IndicatorData findIndicador_16_a_1();
    
    /**
     * 16.b.1 Proporción de la población que declara haberse sentido personalmente discriminada 
     * o acosada en los últimos 12 meses por motivos de discriminación prohibidos por el derecho internacional de los derechos humanos [137]
     * 
     * @return Datos del indicador 16.b.1
     */
    IndicatorData findIndicador_16_b_1();
}
