package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 14: Vida Submarina
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 14
 */
@RequestMapping("/api/ods/14")
public interface IObjetivo14VidaSubmarinaController extends IOdsBaseController<
    ProyectoIndicadores,
    Proyectos,
    ProyectoIndicadorParametros,
    MedicionesHistoricas,
    Object
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 14: Vida Submarina
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.1.1
     * Índice de eutrofización costera y densidad de detritos plásticos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.1.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.2.1
     * Número de países que aplican enfoques basados en ecosistemas para gestionar zonas marinas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.2.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.3.1
     * Acidez media del mar medida en estaciones de muestreo representativas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.3.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.4.1
     * Proporción de poblaciones de peces con niveles biológicamente sostenibles
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.4.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.5.1
     * Cobertura de las zonas protegidas en relación con las zonas marinas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.5.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.6.1
     * Grado de aplicación de instrumentos contra la pesca ilegal
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.6.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.7.1
     * Proporción del PIB correspondiente a la pesca sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.7.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.a.1
     * Proporción del presupuesto total de investigación asignada a tecnología marina
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.a.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.b.1
     * Grado de aplicación de marcos jurídicos para pesca en pequeña escala
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.b.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 14.c.1
     * Número de países que avanzan en implementación de instrumentos relacionados con océanos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 14.c.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_14_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds14(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "14.1", "14.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
