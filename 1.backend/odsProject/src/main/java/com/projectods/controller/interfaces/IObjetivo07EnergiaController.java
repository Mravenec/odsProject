package com.projectods.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 7: Energía Asequible y No Contaminante
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 7
 */
public interface IObjetivo07EnergiaController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 7: Energía Asequible y No Contaminante
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 7.1.1
     * Proporción de la población que tiene acceso a la electricidad
     * 
     * @return ResponseEntity con los datos del indicador 7.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_7_1_1();
    
    /**
     * Obtiene el indicador 7.1.2
     * Proporción de la población con combustibles y tecnologías limpios
     * 
     * @return ResponseEntity con los datos del indicador 7.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_7_1_2();
    
    /**
     * Obtiene el indicador 7.2.1
     * Proporción de energía renovable en el consumo final total
     * 
     * @return ResponseEntity con los datos del indicador 7.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_7_2_1();
    
    /**
     * Obtiene el indicador 7.3.1
     * Intensidad energética medida en función de la energía primaria y el PIB
     * 
     * @return ResponseEntity con los datos del indicador 7.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_7_3_1();
    
    /**
     * Obtiene el indicador 7.a.1
     * Corrientes financieras internacionales para energías limpias
     * 
     * @return ResponseEntity con los datos del indicador 7.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_7_a_1();
    
    /**
     * Obtiene el indicador 7.b.1
     * Capacidad instalada de generación de energía renovable
     * 
     * @return ResponseEntity con los datos del indicador 7.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_7_b_1();
}
