package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.AuditoriaOds15;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 15: Vida de Ecosistemas Terrestres
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 15
 * Usa jOOQ con datasource ods15
 */
public interface IObjetivo15VidaEcosistemasRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds15> {
    
    /**
     * 15.1.1 Superficie forestal en proporción a la superficie total [117]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.1.1
     */
    Optional<Indicadores> findIndicador_15_1_1(Integer proyectoId);
    
    /**
     * 15.1.2 Proporción de lugares importantes para la biodiversidad terrestre y del agua dulce 
     * incluidos en zonas protegidas, desglosada por tipo de ecosistema [117, 118]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.1.2
     */
    Optional<Indicadores> findIndicador_15_1_2(Integer proyectoId);
    
    /**
     * 15.2.1 Avances hacia la gestión forestal sostenible [118]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.2.1
     */
    Optional<Indicadores> findIndicador_15_2_1(Integer proyectoId);
    
    /**
     * 15.3.1 Proporción de tierras degradadas en comparación con la superficie total [119]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.3.1
     */
    Optional<Indicadores> findIndicador_15_3_1(Integer proyectoId);
    
    /**
     * 15.4.1 Lugares importantes para la biodiversidad de las montañas incluidos en zonas protegidas [120]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.4.1
     */
    Optional<Indicadores> findIndicador_15_4_1(Integer proyectoId);
    
    /**
     * 15.4.2 a) Índice de cobertura verde de las montañas y b) proporción de terreno montañoso degradado [120]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.4.2
     */
    Optional<Indicadores> findIndicador_15_4_2(Integer proyectoId);
    
    /**
     * 15.5.1 Índice de la Lista Roja [121]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.5.1
     */
    Optional<Indicadores> findIndicador_15_5_1(Integer proyectoId);
    
    /**
     * 15.6.1 Número de países que han adoptado marcos legislativos, administrativos y normativos 
     * para garantizar la participación equitativa en los beneficios de los recursos genéticos [121]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.6.1
     */
    Optional<Indicadores> findIndicador_15_6_1(Integer proyectoId);
    
    /**
     * 15.7.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes 
     * de la caza furtiva o el tráfico ilícito [122]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.7.1
     */
    Optional<Indicadores> findIndicador_15_7_1(Integer proyectoId);
    
    /**
     * 15.8.1 Proporción de países que han aprobado la legislación nacional pertinente 
     * y han destinado recursos suficientes para la prevención o el control de las especies exóticas invasoras [122, 123]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.8.1
     */
    Optional<Indicadores> findIndicador_15_8_1(Integer proyectoId);
    
    /**
     * 15.9.1 a) Número de países que han establecido metas nacionales acordes o similares a la Meta 14 
     * del Marco Mundial de Biodiversidad de Kunming-Montreal en sus estrategias y planes de acción nacionales 
     * en materia de diversidad biológica y los progresos notificados en la consecución de dichas metas; 
     * y b) integración de la diversidad biológica en los sistemas nacionales de contabilidad y presentación de informes, 
     * definida como aplicación del Sistema de Contabilidad Ambiental y Económica [123, 124]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.9.1
     */
    Optional<Indicadores> findIndicador_15_9_1(Integer proyectoId);
    
    /**
     * 15.a.1 a) Asistencia oficial para el desarrollo destinada concretamente a la conservación 
     * y el uso sostenible de la biodiversidad y b) ingresos generados y financiación movilizada 
     * mediante instrumentos económicos pertinentes para la biodivers [124, 125]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.a.1
     */
    Optional<Indicadores> findIndicador_15_a_1(Integer proyectoId);
    
    /**
     * 15.b.1 a) Asistencia oficial para el desarrollo destinada concretamente a la conservación 
     * y el uso sostenible de la biodiversidad y b) ingresos generados y financiación movilizada 
     * mediante instrumentos económicos pertinentes para la biodivers [125, 126]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.b.1
     */
    Optional<Indicadores> findIndicador_15_b_1(Integer proyectoId);
    
    /**
     * 15.c.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes 
     * de la caza furtiva o el tráfico ilícito [126]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 15.c.1
     */
    Optional<Indicadores> findIndicador_15_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds15(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "15.1", "15.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
