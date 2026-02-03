package com.projectods.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 4: Educación de Calidad
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 4
 */
public interface IObjetivo04EducacionController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 4: Educación de Calidad
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 4.1.1
     * Proporción de niños con nivel mínimo de competencia en lectura y matemáticas
     * 
     * @return ResponseEntity con los datos del indicador 4.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_1_1();
    
    /**
     * Obtiene el indicador 4.1.2
     * Tasa de finalización educativa (primaria, secundaria inferior y superior)
     * 
     * @return ResponseEntity con los datos del indicador 4.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_4_1_2();
    
    /**
     * Obtiene el indicador 4.2.1
     * Desarrollo adecuado en salud, aprendizaje y bienestar psicosocial
     * 
     * @return ResponseEntity con los datos del indicador 4.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_2_1();
    
    /**
     * Obtiene el indicador 4.2.2
     * Tasa de participación en aprendizaje organizado pre-escolar
     * 
     * @return ResponseEntity con los datos del indicador 4.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_4_2_2();
    
    /**
     * Obtiene el indicador 4.3.1
     * Tasa de participación en educación y formación de adultos
     * 
     * @return ResponseEntity con los datos del indicador 4.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_3_1();
    
    /**
     * Obtiene el indicador 4.4.1
     * Proporción de jóvenes y adultos con competencias en TIC
     * 
     * @return ResponseEntity con los datos del indicador 4.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_4_1();
    
    /**
     * Obtiene el indicador 4.5.1
     * Índices de paridad para indicadores de educación desglosados
     * 
     * @return ResponseEntity con los datos del indicador 4.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_5_1();
    
    /**
     * Obtiene el indicador 4.6.1
     * Tasa de alfabetización de adultos/jóvenes
     * 
     * @return ResponseEntity con los datos del indicador 4.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_6_1();
    
    /**
     * Obtiene el indicador 4.7.1
     * Grado de incorporación de educación para ciudadanía mundial y desarrollo sostenible
     * 
     * @return ResponseEntity con los datos del indicador 4.7.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_7_1();
    
    /**
     * Obtiene el indicador 4.a.1
     * Proporción de escuelas que ofrecen servicios básicos
     * 
     * @return ResponseEntity con los datos del indicador 4.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_a_1();
    
    /**
     * Obtiene el indicador 4.b.1
     * Volumen de AOD destinada a becas
     * 
     * @return ResponseEntity con los datos del indicador 4.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_b_1();
    
    /**
     * Obtiene el indicador 4.c.1
     * Proporción de docentes con calificaciones mínimas requeridas
     * 
     * @return ResponseEntity con los datos del indicador 4.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_4_c_1();
}
