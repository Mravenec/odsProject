package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 15: Vida de Ecosistemas Terrestres
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 15
 */
@RequestMapping("/api/ods/15")
public interface IObjetivo15VidaEcosistemasController extends IOdsBaseController<
    ProyectoIndicadores,
    Proyectos,
    ProyectoIndicadorParametros,
    MedicionesHistoricas,
    Object
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 15: Vida de Ecosistemas Terrestres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.1.1
     * Superficie forestal en proporción a la superficie total
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.1.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.1.2
     * Proporción de lugares importantes para biodiversidad terrestre incluidos en zonas protegidas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.1.2
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.2.1
     * Avances hacia la gestión forestal sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.2.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.3.1
     * Proporción de tierras degradadas en comparación con la superficie total
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.3.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.4.1
     * Lugares importantes para biodiversidad de montañas incluidos en zonas protegidas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.4.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.4.2
     * Índice de cobertura verde de montañas y proporción de terreno montañoso degradado
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.4.2
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.5.1
     * Índice de la Lista Roja
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.5.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.6.1
     * Número de países con marcos legislativos para distribución justa de beneficios
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.6.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.7.1
     * Proporción de especímenes de flora y fauna silvestre comercializados ilícitamente
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.7.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.8.1
     * Proporción de países con legislación para especies exóticas invasoras
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.8.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.9.1
     * Número de países con metas nacionales acordes con Marco Mundial de Biodiversidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.9.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.a.1
     * AOD destinada a conservación y uso sostenible de biodiversidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.a.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.b.1
     * Ingresos generados y financiación movilizada para biodiversidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.b.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.c.1
     * Proporción de especímenes de flora y fauna silvestre comercializados ilícitamente
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 15.c.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_15_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds15(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "15.1", "15.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
