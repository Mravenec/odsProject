package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 13: Acción por el Clima
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 13
 */
public interface IObjetivo13AccionClimaController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 13: Acción por el Clima
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 13.1.1
     * Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres
     * 
     * @return ResponseEntity con los datos del indicador 13.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_13_1_1();
    
    /**
     * Obtiene el indicador 13.1.2
     * Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
     * 
     * @return ResponseEntity con los datos del indicador 13.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_13_1_2();
    
    /**
     * Obtiene el indicador 13.1.3
     * Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
     * 
     * @return ResponseEntity con los datos del indicador 13.1.3
     */
    ResponseEntity<IndicatorData> getIndicador_13_1_3();
    
    /**
     * Obtiene el indicador 13.2.1
     * Número de países con contribuciones determinadas a nivel nacional
     * 
     * @return ResponseEntity con los datos del indicador 13.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_13_2_1();
    
    /**
     * Obtiene el indicador 13.2.2
     * Emisiones totales de gases de efecto invernadero por año
     * 
     * @return ResponseEntity con los datos del indicador 13.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_13_2_2();
    
    /**
     * Obtiene el indicador 13.3.1
     * Grado en que se incorpora educación para el cambio climático
     * 
     * @return ResponseEntity con los datos del indicador 13.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_13_3_1();
    
    /**
     * Obtiene el indicador 13.a.1
     * Cantidades proporcionadas y movilizadas en relación con el objetivo de 100.000 millones
     * 
     * @return ResponseEntity con los datos del indicador 13.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_13_a_1();
    
    /**
     * Obtiene el indicador 13.b.1
     * Número de países menos adelantados y pequeños Estados insulares con planes climáticos
     * 
     * @return ResponseEntity con los datos del indicador 13.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_13_b_1();
}
