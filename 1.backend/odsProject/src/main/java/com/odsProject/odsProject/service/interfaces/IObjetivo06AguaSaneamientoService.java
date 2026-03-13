package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 6: Agua Limpia y Saneamiento
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 6
 * Extiende IOdsBaseService con tipos específicos de ODS06
 */
public interface IObjetivo06AguaSaneamientoService extends IOdsBaseService<
    Indicadores,     // T - Indicadores
    Proyectos,       // P - Proyectos
    MetasProyecto,   // M - MetasProyecto
    MedicionesHistoricas, // MH - MedicionesHistoricas
    Object           // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 6: Agua Limpia y Saneamiento
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.1.1
     * Proporción de la población que utiliza servicios de agua potable gestionados sin riesgos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.1.1
     */
    Optional<Indicadores> getIndicador_6_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.2.1
     * Proporción de la población que utiliza servicios de saneamiento gestionados sin riesgos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.2.1
     */
    Optional<Indicadores> getIndicador_6_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.3.1
     * Proporción de aguas residuales tratadas de manera adecuada
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.3.1
     */
    Optional<Indicadores> getIndicador_6_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.3.2
     * Proporción de masas de agua de buena calidad
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.3.2
     */
    Optional<Indicadores> getIndicador_6_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.4.1
     * Cambio en el uso eficiente de los recursos hídricos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.4.1
     */
    Optional<Indicadores> getIndicador_6_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.4.2
     * Nivel de estrés hídrico
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.4.2
     */
    Optional<Indicadores> getIndicador_6_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.5.1
     * Grado de gestión integrada de los recursos hídricos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.5.1
     */
    Optional<Indicadores> getIndicador_6_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.5.2
     * Proporción de cuencas transfronterizas con arreglos operacionales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.5.2
     */
    Optional<Indicadores> getIndicador_6_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.6.1
     * Cambio en la extensión de ecosistemas relacionados con el agua
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.6.1
     */
    Optional<Indicadores> getIndicador_6_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.a.1
     * Volumen de AOD destinada al agua y saneamiento
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.a.1
     */
    Optional<Indicadores> getIndicador_6_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 6.b.1
     * Proporción de dependencias con políticas de participación comunitaria
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.b.1
     */
    Optional<Indicadores> getIndicador_6_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS06
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds06(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS06
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "6.1", "6.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
