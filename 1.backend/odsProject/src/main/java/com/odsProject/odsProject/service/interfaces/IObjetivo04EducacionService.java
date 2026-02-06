package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 4: Educación de Calidad
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 4
 */
public interface IObjetivo04EducacionService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 4: Educación de Calidad
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 4.1.1
     * Proporción de niños con nivel mínimo de competencia en lectura y matemáticas
     * 
     * @return Datos del indicador 4.1.1
     */
    IndicatorData getIndicador_4_1_1();
    
    /**
     * Obtiene el indicador 4.1.2
     * Tasa de finalización educativa (primaria, secundaria inferior y superior)
     * 
     * @return Datos del indicador 4.1.2
     */
    IndicatorData getIndicador_4_1_2();
    
    /**
     * Obtiene el indicador 4.2.1
     * Desarrollo adecuado en salud, aprendizaje y bienestar psicosocial
     * 
     * @return Datos del indicador 4.2.1
     */
    IndicatorData getIndicador_4_2_1();
    
    /**
     * Obtiene el indicador 4.2.2
     * Tasa de participación en aprendizaje organizado pre-escolar
     * 
     * @return Datos del indicador 4.2.2
     */
    IndicatorData getIndicador_4_2_2();
    
    /**
     * Obtiene el indicador 4.3.1
     * Tasa de participación en educación y formación de adultos
     * 
     * @return Datos del indicador 4.3.1
     */
    IndicatorData getIndicador_4_3_1();
    
    /**
     * Obtiene el indicador 4.4.1
     * Proporción de jóvenes y adultos con competencias en TIC
     * 
     * @return Datos del indicador 4.4.1
     */
    IndicatorData getIndicador_4_4_1();
    
    /**
     * Obtiene el indicador 4.5.1
     * Índices de paridad para indicadores de educación desglosados
     * 
     * @return Datos del indicador 4.5.1
     */
    IndicatorData getIndicador_4_5_1();
    
    /**
     * Obtiene el indicador 4.6.1
     * Tasa de alfabetización de adultos/jóvenes
     * 
     * @return Datos del indicador 4.6.1
     */
    IndicatorData getIndicador_4_6_1();
    
    /**
     * Obtiene el indicador 4.7.1
     * Grado de incorporación de educación para ciudadanía mundial y desarrollo sostenible
     * 
     * @return Datos del indicador 4.7.1
     */
    IndicatorData getIndicador_4_7_1();
    
    /**
     * Obtiene el indicador 4.a.1
     * Proporción de escuelas que ofrecen servicios básicos
     * 
     * @return Datos del indicador 4.a.1
     */
    IndicatorData getIndicador_4_a_1();
    
    /**
     * Obtiene el indicador 4.b.1
     * Volumen de AOD destinada a becas
     * 
     * @return Datos del indicador 4.b.1
     */
    IndicatorData getIndicador_4_b_1();
    
    /**
     * Obtiene el indicador 4.c.1
     * Proporción de docentes con calificaciones mínimas requeridas
     * 
     * @return Datos del indicador 4.c.1
     */
    IndicatorData getIndicador_4_c_1();
}
