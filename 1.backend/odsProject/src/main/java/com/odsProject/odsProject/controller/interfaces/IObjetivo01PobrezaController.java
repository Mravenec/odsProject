package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 1: Fin de la Pobreza
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 1
 */
public interface IObjetivo01PobrezaController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 1: Fin de la Pobreza
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<Indicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.1.1
     * Proporción de la población que vive por debajo del umbral internacional de pobreza
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.1.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.2.1
     * Proporción de la población que vive por debajo del umbral nacional de pobreza
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.2.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.2.2
     * Proporción de personas que viven en la pobreza multidimensional
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.2.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.3.1
     * Proporción de la población cubierta por sistemas de protección social
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.3.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.4.1
     * Proporción de la población con acceso a servicios básicos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.4.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.4.2
     * Proporción de población con derechos seguros de tenencia de tierra
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.4.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.5.1
     * Personas afectadas por desastres por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.5.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.5.2
     * Pérdidas económicas por desastres en relación con el PIB mundial
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.5.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.5.3
     * Países con estrategias nacionales de reducción del riesgo de desastres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.5.3
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_5_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.5.4
     * Gobiernos locales con estrategias locales de reducción del riesgo de desastres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.5.4
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_5_4(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.a.1
     * AOD destinada a reducción de la pobreza en porcentaje de la RNB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.a.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.a.2
     * Proporción del gasto público en servicios esenciales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.a.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_a_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 1.b.1
     * Gasto público social en favor de los pobres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 1.b.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_1_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS01
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds01(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS01
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "1.1", "1.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<Indicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
