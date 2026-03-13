package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 5: Igualdad de Género
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 5
 */
public interface IObjetivo05GeneroController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 5: Igualdad de Género
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<Indicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.1.1
     * Existencia de marcos jurídicos para igualdad y no discriminación
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.1.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.2.1
     * Proporción de mujeres que han sufrido violencia de pareja
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.2.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.2.2
     * Proporción de mujeres que han sufrido violencia sexual
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.2.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.3.1
     * Proporción de mujeres casadas antes de los 15 y 18 años
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.3.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.3.2
     * Proporción de niñas que han sufrido mutilación genital femenina
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.3.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.4.1
     * Proporción de tiempo dedicado a trabajo doméstico no remunerado
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.4.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.5.1
     * Proporción de escaños ocupados por mujeres en parlamentos y gobiernos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.5.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.5.2
     * Proporción de mujeres en cargos directivos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.5.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.6.1
     * Proporción de mujeres que toman decisiones informadas sobre salud reproductiva
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.6.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.6.2
     * Países con leyes que garantizan acceso a salud sexual y reproductiva
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.6.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_6_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.a.1
     * Proporción de población agrícola con derechos seguros sobre tierras agrícolas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.a.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.a.2
     * Países con leyes que garantizan igualdad de derechos de la mujer a la tierra
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.a.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_a_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.b.1
     * Proporción de personas que poseen teléfono móvil, desglosada por sexo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.b.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 5.c.1
     * Países con sistemas para seguimiento de igualdad de género
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 5.c.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_5_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS05
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds05(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS05
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "5.1", "5.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<Indicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
