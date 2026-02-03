package com.projectods.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 8
 */
public interface IObjetivo08CrecimientoEconomicoController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 8: Trabajo Decente y Crecimiento Económico
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 8.1.1
     * Tasa de crecimiento anual del PIB real per cápita
     * 
     * @return ResponseEntity con los datos del indicador 8.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_1_1();
    
    /**
     * Obtiene el indicador 8.2.1
     * Tasa de crecimiento anual del PIB real por persona empleada
     * 
     * @return ResponseEntity con los datos del indicador 8.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_2_1();
    
    /**
     * Obtiene el indicador 8.3.1
     * Proporción de empleo informal con respecto al empleo total
     * 
     * @return ResponseEntity con los datos del indicador 8.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_3_1();
    
    /**
     * Obtiene el indicador 8.4.1
     * Huella material en términos absolutos, per cápita y por PIB
     * 
     * @return ResponseEntity con los datos del indicador 8.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_4_1();
    
    /**
     * Obtiene el indicador 8.4.2
     * Consumo material interno en términos absolutos, per cápita y por PIB
     * 
     * @return ResponseEntity con los datos del indicador 8.4.2
     */
    ResponseEntity<IndicatorData> getIndicador_8_4_2();
    
    /**
     * Obtiene el indicador 8.5.1
     * Ingreso medio por hora de las personas empleadas
     * 
     * @return ResponseEntity con los datos del indicador 8.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_5_1();
    
    /**
     * Obtiene el indicador 8.5.2
     * Tasa de desempleo, desglosada por sexo, edad y personas con discapacidad
     * 
     * @return ResponseEntity con los datos del indicador 8.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_8_5_2();
    
    /**
     * Obtiene el indicador 8.6.1
     * Proporción de jóvenes que no estudian ni trabajan
     * 
     * @return ResponseEntity con los datos del indicador 8.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_6_1();
    
    /**
     * Obtiene el indicador 8.7.1
     * Proporción de niños que realizan trabajo infantil
     * 
     * @return ResponseEntity con los datos del indicador 8.7.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_7_1();
    
    /**
     * Obtiene el indicador 8.8.1
     * Lesiones ocupacionales mortales y no mortales por cada 100.000 trabajadores
     * 
     * @return ResponseEntity con los datos del indicador 8.8.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_8_1();
    
    /**
     * Obtiene el indicador 8.8.2
     * Nivel de cumplimiento nacional de los derechos laborales
     * 
     * @return ResponseEntity con los datos del indicador 8.8.2
     */
    ResponseEntity<IndicatorData> getIndicador_8_8_2();
    
    /**
     * Obtiene el indicador 8.9.1
     * PIB generado directamente por el turismo en proporción al PIB total
     * 
     * @return ResponseEntity con los datos del indicador 8.9.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_9_1();
    
    /**
     * Obtiene el indicador 8.9.2
     * Personas empleadas en el sector del turismo
     * 
     * @return ResponseEntity con los datos del indicador 8.9.2
     */
    ResponseEntity<IndicatorData> getIndicador_8_9_2();
    
    /**
     * Obtiene el indicador 8.10.1
     * Número de sucursales de bancos y cajeros automáticos por cada 100.000 adultos
     * 
     * @return ResponseEntity con los datos del indicador 8.10.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_10_1();
    
    /**
     * Obtiene el indicador 8.10.2
     * Proporción de adultos que tienen una cuenta en banco
     * 
     * @return ResponseEntity con los datos del indicador 8.10.2
     */
    ResponseEntity<IndicatorData> getIndicador_8_10_2();
    
    /**
     * Obtiene el indicador 8.a.1
     * Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio
     * 
     * @return ResponseEntity con los datos del indicador 8.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_a_1();
    
    /**
     * Obtiene el indicador 8.b.1
     * Existencia de una estrategia nacional para el empleo de los jóvenes
     * 
     * @return ResponseEntity con los datos del indicador 8.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_8_b_1();
}
