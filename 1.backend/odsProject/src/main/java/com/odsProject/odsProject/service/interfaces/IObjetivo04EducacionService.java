package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 4: Educación de Calidad
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 4
 */
public interface IObjetivo04EducacionService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 4: Educación de Calidad
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.1.1
     * Proporción de niños con nivel mínimo de competencia en lectura y matemáticas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.1.1
     */
    Optional<Indicadores> getIndicador_4_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.1.2
     * Tasa de finalización educativa (primaria, secundaria inferior y superior)
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.1.2
     */
    Optional<Indicadores> getIndicador_4_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.2.1
     * Desarrollo adecuado en salud, aprendizaje y bienestar psicosocial
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.2.1
     */
    Optional<Indicadores> getIndicador_4_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.2.2
     * Tasa de participación en aprendizaje organizado pre-escolar
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.2.2
     */
    Optional<Indicadores> getIndicador_4_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.3.1
     * Tasa de participación en educación y formación de adultos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.3.1
     */
    Optional<Indicadores> getIndicador_4_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.4.1
     * Proporción de jóvenes y adultos con competencias en TIC
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.4.1
     */
    Optional<Indicadores> getIndicador_4_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.5.1
     * Índices de paridad para indicadores de educación desglosados
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.5.1
     */
    Optional<Indicadores> getIndicador_4_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.6.1
     * Tasa de alfabetización de adultos/jóvenes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.6.1
     */
    Optional<Indicadores> getIndicador_4_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.7.1
     * Grado de incorporación de educación para ciudadanía mundial y desarrollo sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.7.1
     */
    Optional<Indicadores> getIndicador_4_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.a.1
     * Proporción de escuelas que ofrecen servicios básicos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.a.1
     */
    Optional<Indicadores> getIndicador_4_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.b.1
     * Volumen de AOD destinada a becas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.b.1
     */
    Optional<Indicadores> getIndicador_4_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.c.1
     * Proporción de docentes con calificaciones mínimas requeridas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.c.1
     */
    Optional<Indicadores> getIndicador_4_c_1(Integer proyectoId);
}
