package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 6: Agua Limpia y Saneamiento
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 6
 */
public interface IObjetivo06AguaSaneamientoController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 6: Agua Limpia y Saneamiento
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<Indicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.1.1
     * Proporción de la población que utiliza servicios de agua potable gestionados sin riesgos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.1.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.2.1
     * Proporción de la población que utiliza servicios de saneamiento gestionados sin riesgos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.2.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.3.1
     * Proporción de aguas residuales tratadas de manera adecuada
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.3.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.3.2
     * Proporción de masas de agua de buena calidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.3.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.4.1
     * Cambio en el uso eficiente de los recursos hídricos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.4.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.4.2
     * Nivel de estrés hídrico
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.4.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.5.1
     * Grado de gestión integrada de los recursos hídricos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.5.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.5.2
     * Proporción de cuencas transfronterizas con arreglos operacionales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.5.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.6.1
     * Cambio en la extensión de ecosistemas relacionados con el agua
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.6.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.a.1
     * Volumen de AOD destinada al agua y saneamiento
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.a.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.b.1
     * Proporción de dependencias con políticas de participación comunitaria
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 6.b.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_6_b_1(Integer proyectoId);
}
