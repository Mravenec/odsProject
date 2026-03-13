package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 7: Energía Asequible y No Contaminante
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 7
 */
public interface IObjetivo07EnergiaController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 7: Energía Asequible y No Contaminante
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<Indicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 7.1.1
     * Proporción de la población que tiene acceso a la electricidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 7.1.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_7_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 7.1.2
     * Proporción de la población con combustibles y tecnologías limpios
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 7.1.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_7_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 7.2.1
     * Proporción de energía renovable en el consumo final total
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 7.2.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_7_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 7.3.1
     * Intensidad energética medida en función de la energía primaria y el PIB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 7.3.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_7_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 7.a.1
     * Corrientes financieras internacionales para energías limpias
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 7.a.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_7_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 7.b.1
     * Capacidad instalada de generación de energía renovable
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 7.b.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_7_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds07(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "7.1", "7.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<Indicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
