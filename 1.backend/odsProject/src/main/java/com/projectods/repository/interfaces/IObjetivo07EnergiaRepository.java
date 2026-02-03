package com.projectods.repository.interfaces;

import com.projectods.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 7: Energía Asequible y No Contaminante
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 7
 */
public interface IObjetivo07EnergiaRepository {
    
    /**
     * 7.1.1 Proporción de la población que tiene acceso a la electricidad [55]
     * 
     * @return Datos del indicador 7.1.1
     */
    IndicatorData findIndicador_7_1_1();
    
    /**
     * 7.1.2 Proporción de la población cuya fuente primaria de energía son los combustibles y tecnologías limpios [55]
     * 
     * @return Datos del indicador 7.1.2
     */
    IndicatorData findIndicador_7_1_2();
    
    /**
     * 7.2.1 Proporción de energía renovable en el consumo final total de energía [56]
     * 
     * @return Datos del indicador 7.2.1
     */
    IndicatorData findIndicador_7_2_1();
    
    /**
     * 7.3.1 Intensidad energética medida en función de la energía primaria y el PIB [56]
     * 
     * @return Datos del indicador 7.3.1
     */
    IndicatorData findIndicador_7_3_1();
    
    /**
     * 7.a.1 Corrientes financieras internacionales hacia los países en desarrollo para apoyar la investigación 
     * y el desarrollo de energías limpias y la producción de energía renovable, incluidos los sistemas híbridos [57]
     * 
     * @return Datos del indicador 7.a.1
     */
    IndicatorData findIndicador_7_a_1();
    
    /**
     * 7.b.1 Capacidad instalada de generación de energía renovable en los países en desarrollo 
     * y en los países desarrollados (en vatios per cápita) [58]
     * 
     * @return Datos del indicador 7.b.1
     */
    IndicatorData findIndicador_7_b_1();
}
