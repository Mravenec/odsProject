package com.projectods.repository.interfaces;

import com.projectods.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 14: Vida Submarina
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 14
 */
public interface IObjetivo14VidaSubmarinaRepository {
    
    /**
     * 14.1.1 a) Índice de eutrofización costera; y b) densidad de detritos plásticos [108, 109]
     * 
     * @return Datos del indicador 14.1.1
     */
    IndicatorData findIndicador_14_1_1();
    
    /**
     * 14.2.1 Número de países que aplican enfoques basados en los ecosistemas para gestionar las zonas marinas [109]
     * 
     * @return Datos del indicador 14.2.1
     */
    IndicatorData findIndicador_14_2_1();
    
    /**
     * 14.3.1 Acidez media del mar (pH) medida en un conjunto convenido de estaciones de muestreo representativas [110]
     * 
     * @return Datos del indicador 14.3.1
     */
    IndicatorData findIndicador_14_3_1();
    
    /**
     * 14.4.1 Proporción de poblaciones de peces cuyos niveles son biológicamente sostenibles [111]
     * 
     * @return Datos del indicador 14.4.1
     */
    IndicatorData findIndicador_14_4_1();
    
    /**
     * 14.5.1 Cobertura de las zonas protegidas en relación con las zonas marinas [111]
     * 
     * @return Datos del indicador 14.5.1
     */
    IndicatorData findIndicador_14_5_1();
    
    /**
     * 14.6.1 Grado de aplicación de instrumentos internacionales cuyo objetivo es combatir 
     * la pesca ilegal, no declarada y no reglamentada [112]
     * 
     * @return Datos del indicador 14.6.1
     */
    IndicatorData findIndicador_14_6_1();
    
    /**
     * 14.7.1 Proporción del PIB correspondiente a la pesca sostenible en los pequeños Estados insulares en desarrollo, 
     * en los países menos adelantados y en todos los países [113]
     * 
     * @return Datos del indicador 14.7.1
     */
    IndicatorData findIndicador_14_7_1();
    
    /**
     * 14.a.1 Proporción del presupuesto total de investigación asignada a la investigación 
     * en el campo de la tecnología marina [114]
     * 
     * @return Datos del indicador 14.a.1
     */
    IndicatorData findIndicador_14_a_1();
    
    /**
     * 14.b.1 Grado de aplicación de un marco jurídico, reglamentario, normativo o institucional 
     * que reconozca y proteja los derechos de acceso para la pesca en pequeña escala [114, 115]
     * 
     * @return Datos del indicador 14.b.1
     */
    IndicatorData findIndicador_14_b_1();
    
    /**
     * 14.c.1 Número de países que, mediante marcos jurídicos, normativos e institucionales, 
     * avanzan en la ratificación, la aceptación y la implementación de los instrumentos relacionados 
     * con los océanos que aplican el derecho internacional reflejado en la Convención de las Naciones Unidas 
     * sobre el Derecho del Mar para la conservación y el uso sostenible de los océanos y sus recursos [115, 116]
     * 
     * @return Datos del indicador 14.c.1
     */
    IndicatorData findIndicador_14_c_1();
}
