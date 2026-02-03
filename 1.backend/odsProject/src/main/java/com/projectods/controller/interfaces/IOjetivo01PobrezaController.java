package com.projectods.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 1: Fin de la Pobreza
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 1
 */
public interface IOjetivo01PobrezaController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 1: Fin de la Pobreza
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 1.1.1
     * Proporción de la población que vive por debajo del umbral internacional de pobreza
     * 
     * @return ResponseEntity con los datos del indicador 1.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_1_1();
    
    /**
     * Obtiene el indicador 1.2.1
     * Proporción de la población que vive por debajo del umbral nacional de pobreza
     * 
     * @return ResponseEntity con los datos del indicador 1.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_2_1();
    
    /**
     * Obtiene el indicador 1.2.2
     * Proporción de personas que viven en la pobreza multidimensional
     * 
     * @return ResponseEntity con los datos del indicador 1.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_1_2_2();
    
    /**
     * Obtiene el indicador 1.3.1
     * Proporción de la población cubierta por sistemas de protección social
     * 
     * @return ResponseEntity con los datos del indicador 1.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_3_1();
    
    /**
     * Obtiene el indicador 1.4.1
     * Proporción de la población con acceso a servicios básicos
     * 
     * @return ResponseEntity con los datos del indicador 1.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_4_1();
    
    /**
     * Obtiene el indicador 1.4.2
     * Proporción de población con derechos seguros de tenencia de tierra
     * 
     * @return ResponseEntity con los datos del indicador 1.4.2
     */
    ResponseEntity<IndicatorData> getIndicador_1_4_2();
    
    /**
     * Obtiene el indicador 1.5.1
     * Personas afectadas por desastres por cada 100.000 habitantes
     * 
     * @return ResponseEntity con los datos del indicador 1.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_5_1();
    
    /**
     * Obtiene el indicador 1.5.2
     * Pérdidas económicas por desastres en relación con el PIB mundial
     * 
     * @return ResponseEntity con los datos del indicador 1.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_1_5_2();
    
    /**
     * Obtiene el indicador 1.5.3
     * Países con estrategias nacionales de reducción del riesgo de desastres
     * 
     * @return ResponseEntity con los datos del indicador 1.5.3
     */
    ResponseEntity<IndicatorData> getIndicador_1_5_3();
    
    /**
     * Obtiene el indicador 1.5.4
     * Gobiernos locales con estrategias locales de reducción del riesgo de desastres
     * 
     * @return ResponseEntity con los datos del indicador 1.5.4
     */
    ResponseEntity<IndicatorData> getIndicador_1_5_4();
    
    /**
     * Obtiene el indicador 1.a.1
     * AOD destinada a reducción de la pobreza en porcentaje de la RNB
     * 
     * @return ResponseEntity con los datos del indicador 1.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_a_1();
    
    /**
     * Obtiene el indicador 1.a.2
     * Proporción del gasto público en servicios esenciales
     * 
     * @return ResponseEntity con los datos del indicador 1.a.2
     */
    ResponseEntity<IndicatorData> getIndicador_1_a_2();
    
    /**
     * Obtiene el indicador 1.b.1
     * Gasto público social en favor de los pobres
     * 
     * @return ResponseEntity con los datos del indicador 1.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_1_b_1();
}
