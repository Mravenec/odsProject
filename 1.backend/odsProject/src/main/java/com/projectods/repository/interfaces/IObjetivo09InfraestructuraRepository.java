package com.projectods.repository.interfaces;

import com.projectods.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 9: Industria, Innovación e Infraestructura
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 9
 */
public interface IObjetivo09InfraestructuraRepository {
    
    /**
     * 9.1.1 Proporción de la población rural que vive a menos de 2 km de una carretera transitable todo el año [68]
     * 
     * @return Datos del indicador 9.1.1
     */
    IndicatorData findIndicador_9_1_1();
    
    /**
     * 9.1.2 Volumen de transporte de pasajeros y carga, desglosado por medio de transporte [68]
     * 
     * @return Datos del indicador 9.1.2
     */
    IndicatorData findIndicador_9_1_2();
    
    /**
     * 9.2.1 Valor añadido del sector manufacturo en proporción al PIB y per cápita [69]
     * 
     * @return Datos del indicador 9.2.1
     */
    IndicatorData findIndicador_9_2_1();
    
    /**
     * 9.2.2 Empleo del sector manufacturero en proporción al empleo total [69]
     * 
     * @return Datos del indicador 9.2.2
     */
    IndicatorData findIndicador_9_2_2();
    
    /**
     * 9.3.1 Proporción del valor añadido total del sector industrial correspondiente a las pequeñas industrias, 
     * según a) la clasificación internacional y b) las clasificaciones nacionales [70]
     * 
     * @return Datos del indicador 9.3.1
     */
    IndicatorData findIndicador_9_3_1();
    
    /**
     * 9.3.2 Proporción de las pequeñas industrias que han obtenido un préstamo o una línea de crédito [70]
     * 
     * @return Datos del indicador 9.3.2
     */
    IndicatorData findIndicador_9_3_2();
    
    /**
     * 9.4.1 Emisiones de CO2 por unidad de valor añadido [71]
     * 
     * @return Datos del indicador 9.4.1
     */
    IndicatorData findIndicador_9_4_1();
    
    /**
     * 9.5.1 Gastos en investigación y desarrollo en proporción al PIB [72]
     * 
     * @return Datos del indicador 9.5.1
     */
    IndicatorData findIndicador_9_5_1();
    
    /**
     * 9.5.2 Número de investigadores (en equivalente a tiempo completo) por cada millón de habitantes [72]
     * 
     * @return Datos del indicador 9.5.2
     */
    IndicatorData findIndicador_9_5_2();
    
    /**
     * 9.a.1 Total de apoyo internacional oficial (asistencia oficial para el desarrollo más otras corrientes 
     * oficiales de recursos) destinado a la infraestructura [73]
     * 
     * @return Datos del indicador 9.a.1
     */
    IndicatorData findIndicador_9_a_1();
    
    /**
     * 9.b.1 Proporción del valor añadido por la industria de tecnología mediana y alta en el valor añadido total [74]
     * 
     * @return Datos del indicador 9.b.1
     */
    IndicatorData findIndicador_9_b_1();
    
    /**
     * 9.c.1 Proporción de la población con cobertura de red móvil, desglosada por tecnología [74, 75]
     * 
     * @return Datos del indicador 9.c.1
     */
    IndicatorData findIndicador_9_c_1();
}
