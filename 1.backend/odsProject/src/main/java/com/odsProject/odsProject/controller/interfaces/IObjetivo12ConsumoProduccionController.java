package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 12: Producción y Consumo Responsables
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 12
 */
public interface IObjetivo12ConsumoProduccionController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 12: Producción y Consumo Responsables
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 12.1.1
     * Número de países con políticas de consumo y producción sostenibles
     * 
     * @return ResponseEntity con los datos del indicador 12.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_1_1();
    
    /**
     * Obtiene el indicador 12.2.1
     * Huella material en términos absolutos, per cápita y por PIB
     * 
     * @return ResponseEntity con los datos del indicador 12.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_2_1();
    
    /**
     * Obtiene el indicador 12.2.2
     * Consumo material interno en términos absolutos, per cápita y por PIB
     * 
     * @return ResponseEntity con los datos del indicador 12.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_12_2_2();
    
    /**
     * Obtiene el indicador 12.3.1
     * Índice de pérdidas de alimentos y desperdicio de alimentos
     * 
     * @return ResponseEntity con los datos del indicador 12.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_3_1();
    
    /**
     * Obtiene el indicador 12.4.1
     * Número de partes en acuerdos sobre desechos peligrosos que cumplen sus compromisos
     * 
     * @return ResponseEntity con los datos del indicador 12.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_4_1();
    
    /**
     * Obtiene el indicador 12.4.2
     * Desechos peligrosos generados per cápita y proporción tratada
     * 
     * @return ResponseEntity con los datos del indicador 12.4.2
     */
    ResponseEntity<IndicatorData> getIndicador_12_4_2();
    
    /**
     * Obtiene el indicador 12.5.1
     * Tasa nacional de reciclado, en toneladas de material reciclado
     * 
     * @return ResponseEntity con los datos del indicador 12.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_5_1();
    
    /**
     * Obtiene el indicador 12.6.1
     * Número de empresas que publican informes sobre sostenibilidad
     * 
     * @return ResponseEntity con los datos del indicador 12.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_6_1();
    
    /**
     * Obtiene el indicador 12.7.1
     * Número de países con políticas de adquisiciones públicas sostenibles
     * 
     * @return ResponseEntity con los datos del indicador 12.7.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_7_1();
    
    /**
     * Obtiene el indicador 12.8.1
     * Grado en que se incorpora educación para el desarrollo sostenible
     * 
     * @return ResponseEntity con los datos del indicador 12.8.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_8_1();
    
    /**
     * Obtiene el indicador 12.a.1
     * Capacidad instalada de generación de energía renovable
     * 
     * @return ResponseEntity con los datos del indicador 12.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_a_1();
    
    /**
     * Obtiene el indicador 12.b.1
     * Aplicación de instrumentos de contabilidad para turismo sostenible
     * 
     * @return ResponseEntity con los datos del indicador 12.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_b_1();
    
    /**
     * Obtiene el indicador 12.c.1
     * Cuantía de los subsidios a los combustibles fósiles por unidad del PIB
     * 
     * @return ResponseEntity con los datos del indicador 12.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_12_c_1();
}
