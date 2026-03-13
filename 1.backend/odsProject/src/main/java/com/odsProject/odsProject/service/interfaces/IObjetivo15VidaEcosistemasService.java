package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 15: Vida de Ecosistemas Terrestres
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 15
 */
public interface IObjetivo15VidaEcosistemasService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 15: Vida de Ecosistemas Terrestres
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.1.1
     * Superficie forestal en proporción a la superficie total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.1.1
     */
    Optional<Indicadores> getIndicador_15_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.1.2
     * Proporción de lugares importantes para biodiversidad terrestre incluidos en zonas protegidas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.1.2
     */
    Optional<Indicadores> getIndicador_15_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.2.1
     * Avances hacia la gestión forestal sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.2.1
     */
    Optional<Indicadores> getIndicador_15_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.3.1
     * Proporción de tierras degradadas en comparación con la superficie total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.3.1
     */
    Optional<Indicadores> getIndicador_15_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.4.1
     * Lugares importantes para biodiversidad de montañas incluidos en zonas protegidas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.4.1
     */
    Optional<Indicadores> getIndicador_15_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.4.2
     * Índice de cobertura verde de montañas y proporción de terreno montañoso degradado
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.4.2
     */
    Optional<Indicadores> getIndicador_15_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.5.1
     * Índice de la Lista Roja
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.5.1
     */
    Optional<Indicadores> getIndicador_15_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.6.1
     * Número de países con marcos legislativos para distribución justa de beneficios
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.6.1
     */
    Optional<Indicadores> getIndicador_15_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.7.1
     * Proporción de especímenes de flora y fauna silvestre comercializados ilícitamente
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.7.1
     */
    Optional<Indicadores> getIndicador_15_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.8.1
     * Proporción de países con legislación para especies exóticas invasoras
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.8.1
     */
    Optional<Indicadores> getIndicador_15_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.9.1
     * Número de países con metas nacionales acordes con Marco Mundial de Biodiversidad
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.9.1
     */
    Optional<Indicadores> getIndicador_15_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.a.1
     * AOD destinada a conservación y uso sostenible de biodiversidad
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.a.1
     */
    Optional<Indicadores> getIndicador_15_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.b.1
     * Ingresos generados y financiación movilizada para biodiversidad
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.b.1
     */
    Optional<Indicadores> getIndicador_15_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 15.c.1
     * Proporción de especímenes de flora y fauna silvestre comercializados ilícitamente
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.c.1
     */
    Optional<Indicadores> getIndicador_15_c_1(Integer proyectoId);
}
