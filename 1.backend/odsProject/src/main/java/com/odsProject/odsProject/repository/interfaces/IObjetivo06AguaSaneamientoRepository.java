package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 6: Agua Limpia y Saneamiento
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 6
 */
public interface IObjetivo06AguaSaneamientoRepository {
    
    /**
     * 6.1.1 Proporción de la población que utiliza servicios de suministro de agua potable gestionados sin riesgos [49]
     * 
     * @return Datos del indicador 6.1.1
     */
    IndicatorData findIndicador_6_1_1();
    
    /**
     * 6.2.1 Proporción de la población que utiliza: a) servicios de saneamiento gestionados sin riesgos 
     * y b) instalaciones para el lavado de manos con agua y jabón [49, 50]
     * 
     * @return Datos del indicador 6.2.1
     */
    IndicatorData findIndicador_6_2_1();
    
    /**
     * 6.3.1 Proporción de los flujos de aguas residuales domésticas e industriales tratados de manera adecuada [50]
     * 
     * @return Datos del indicador 6.3.1
     */
    IndicatorData findIndicador_6_3_1();
    
    /**
     * 6.3.2 Proporción de masas de agua de buena calidad [51]
     * 
     * @return Datos del indicador 6.3.2
     */
    IndicatorData findIndicador_6_3_2();
    
    /**
     * 6.4.1 Cambio en el uso eficiente de los recursos hídricos con el paso del tiempo [51]
     * 
     * @return Datos del indicador 6.4.1
     */
    IndicatorData findIndicador_6_4_1();
    
    /**
     * 6.4.2 Nivel de estrés hídrico: extracción de agua dulce en proporción a los recursos de agua dulce disponibles [51]
     * 
     * @return Datos del indicador 6.4.2
     */
    IndicatorData findIndicador_6_4_2();
    
    /**
     * 6.5.1 Grado de gestión integrada de los recursos hídricos [52]
     * 
     * @return Datos del indicador 6.5.1
     */
    IndicatorData findIndicador_6_5_1();
    
    /**
     * 6.5.2 Proporción de la superficie de cuencas transfronterizas sujetas a arreglos operacionales 
     * para la cooperación en materia de aguas [52]
     * 
     * @return Datos del indicador 6.5.2
     */
    IndicatorData findIndicador_6_5_2();
    
    /**
     * 6.6.1 Cambio en la extensión de los ecosistemas relacionados con el agua con el paso del tiempo [53]
     * 
     * @return Datos del indicador 6.6.1
     */
    IndicatorData findIndicador_6_6_1();
    
    /**
     * 6.a.1 Volumen de la asistencia oficial para el desarrollo destinada al agua y el saneamiento 
     * que forma parte de un plan de gastos coordinados por el gobierno [53]
     * 
     * @return Datos del indicador 6.a.1
     */
    IndicatorData findIndicador_6_a_1();
    
    /**
     * 6.b.1 Proporción de dependencias administrativas locales que han establecido políticas 
     * y procedimientos operacionales para la participación de las comunidades locales 
     * en la gestión del agua y el saneamiento [54, 55]
     * 
     * @return Datos del indicador 6.b.1
     */
    IndicatorData findIndicador_6_b_1();
}
