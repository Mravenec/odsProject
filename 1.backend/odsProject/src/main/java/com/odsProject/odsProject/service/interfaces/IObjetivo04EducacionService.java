package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 4: Educación de Calidad
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 4
 * Extiende IOdsBaseService con tipos específicos de ODS04
 */
public interface IObjetivo04EducacionService extends IOdsBaseService<
    Indicadores,     // T - Indicadores
    Proyectos,       // P - Proyectos
    MetasProyecto,   // M - MetasProyecto
    MedicionesHistoricas, // MH - MedicionesHistoricas
    Object           // A - Auditoria (placeholder)
> {
    
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
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds04(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "4.1", "4.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──
    
    /**
     * Obtiene todos los proyectos del ODS 4
     * 
     * @return Lista de todos los proyectos del objetivo 4
     */
    List<Proyectos> getAllProjectsOds04();
    
    /**
     * Obtiene un proyecto del ODS 4 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> getProjectOds04ById(Integer proyectoId);
    
    /**
     * Obtiene las metas de un proyecto del ODS 4
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> getAllMetasProyectoOds04(Integer proyectoId);
    
    /**
     * Obtiene una meta de proyecto del ODS 4 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> getMetaProyectoOds04ById(Integer metaId);
    
    /**
     * Obtiene las mediciones históricas de un indicador del ODS 4
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds04(Integer indicadorId);
    
    /**
     * Obtiene una medición histórica del ODS 4 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> getMedicionHistoricaOds04ById(Integer medicionId);
    
    /**
     * Valida los datos de un indicador del ODS 4
     * 
     * @param indicador Datos del indicador a validar
     * @return true si los datos son válidos, false otherwise
     */
    Boolean validateIndicatorData(Indicadores indicador);
    
    /**
     * Calcula el progreso de un proyecto del ODS 4
     * 
     * @param proyectoId ID del proyecto
     * @return Porcentaje de progreso (0-100)
     */
    Double calculateProjectProgress(Integer proyectoId);
    
    /**
     * Obtiene estadísticas generales del ODS 4
     * 
     * @return Map con estadísticas del objetivo
     */
    java.util.Map<String, Object> getOds04Statistics();
    
    /**
     * Verifica si un proyecto del ODS 4 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    Boolean projectExists(Integer proyectoId);
    
    /**
     * Verifica si un indicador del ODS 4 existe
     * 
     * @param indicadorId ID del indicador
     * @return true si existe, false otherwise
     */
    Boolean indicatorExists(Integer indicadorId);
}
