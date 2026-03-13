package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 4: Educación de Calidad
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 4
 * Extiende IOdsBaseController con tipos específicos de ODS04
 */
@RequestMapping("/api/ods/04")
public interface IObjetivo04EducacionController extends IOdsBaseController<
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
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<Indicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.1.1
     * Proporción de niños con nivel mínimo de competencia en lectura y matemáticas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.1.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.1.2
     * Tasa de finalización educativa (primaria, secundaria inferior y superior)
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.1.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.2.1
     * Desarrollo adecuado en salud, aprendizaje y bienestar psicosocial
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.2.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.2.2
     * Tasa de participación en aprendizaje organizado pre-escolar
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.2.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.3.1
     * Tasa de participación en educación y formación de adultos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.3.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.4.1
     * Proporción de jóvenes y adultos con competencias en TIC
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.4.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.5.1
     * Índices de paridad para indicadores de educación desglosados
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.5.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.6.1
     * Tasa de alfabetización de adultos/jóvenes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.6.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.7.1
     * Grado de incorporación de educación para ciudadanía mundial y desarrollo sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.7.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.a.1
     * Proporción de escuelas que ofrecen servicios básicos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.a.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.b.1
     * Volumen de AOD destinada a becas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.b.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 4.c.1
     * Proporción de docentes con calificaciones mínimas requeridas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 4.c.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_4_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds04(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "4.1", "4.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<Indicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
