package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 1: Fin de la Pobreza
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 1
 */
public interface IOjetivo01PobrezaService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 1: Fin de la Pobreza
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 1.1.1
     * Proporción de la población que vive por debajo del umbral internacional de pobreza
     * 
     * @return Datos del indicador 1.1.1
     */
    IndicatorData getIndicador_1_1_1();
    
    /**
     * Obtiene el indicador 1.2.1
     * Proporción de la población que vive por debajo del umbral nacional de pobreza
     * 
     * @return Datos del indicador 1.2.1
     */
    IndicatorData getIndicador_1_2_1();
    
    /**
     * Obtiene el indicador 1.2.2
     * Proporción de personas que viven en la pobreza multidimensional
     * 
     * @return Datos del indicador 1.2.2
     */
    IndicatorData getIndicador_1_2_2();
    
    /**
     * Obtiene el indicador 1.3.1
     * Proporción de la población cubierta por sistemas de protección social
     * 
     * @return Datos del indicador 1.3.1
     */
    IndicatorData getIndicador_1_3_1();
    
    /**
     * Obtiene el indicador 1.4.1
     * Proporción de la población con acceso a servicios básicos
     * 
     * @return Datos del indicador 1.4.1
     */
    IndicatorData getIndicador_1_4_1();
    
    /**
     * Obtiene el indicador 1.4.2
     * Proporción de población con derechos seguros de tenencia de tierra
     * 
     * @return Datos del indicador 1.4.2
     */
    IndicatorData getIndicador_1_4_2();
    
    /**
     * Obtiene el indicador 1.5.1
     * Personas afectadas por desastres por cada 100.000 habitantes
     * 
     * @return Datos del indicador 1.5.1
     */
    IndicatorData getIndicador_1_5_1();
    
    /**
     * Obtiene el indicador 1.5.2
     * Pérdidas económicas por desastres en relación con el PIB mundial
     * 
     * @return Datos del indicador 1.5.2
     */
    IndicatorData getIndicador_1_5_2();
    
    /**
     * Obtiene el indicador 1.5.3
     * Países con estrategias nacionales de reducción del riesgo de desastres
     * 
     * @return Datos del indicador 1.5.3
     */
    IndicatorData getIndicador_1_5_3();
    
    /**
     * Obtiene el indicador 1.5.4
     * Gobiernos locales con estrategias locales de reducción del riesgo de desastres
     * 
     * @return Datos del indicador 1.5.4
     */
    IndicatorData getIndicador_1_5_4();
    
    /**
     * Obtiene el indicador 1.a.1
     * AOD destinada a reducción de la pobreza en porcentaje de la RNB
     * 
     * @return Datos del indicador 1.a.1
     */
    IndicatorData getIndicador_1_a_1();
    
    /**
     * Obtiene el indicador 1.a.2
     * Proporción del gasto público en servicios esenciales
     * 
     * @return Datos del indicador 1.a.2
     */
    IndicatorData getIndicador_1_a_2();
    
    /**
     * Obtiene el indicador 1.b.1
     * Gasto público social en favor de los pobres
     * 
     * @return Datos del indicador 1.b.1
     */
    IndicatorData getIndicador_1_b_1();
}
