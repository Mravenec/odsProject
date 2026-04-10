package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.AuditoriaOds14;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 14: Vida Submarina
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 14
 * Usa jOOQ con datasource ods14
 */
public interface IObjetivo14VidaSubmarinaRepository extends IOdsBaseRepository<ProyectoIndicadores, Proyectos, ProyectoIndicadorParametros, MedicionesHistoricas, AuditoriaOds14> {
    
    /**
     * 14.1.1 a) Índice de eutrofización costera; y b) densidad de detritos plásticos [108, 109]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.1.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_1_1(Integer proyectoId);
    
    /**
     * 14.2.1 Número de países que aplican enfoques basados en los ecosistemas para gestionar las zonas marinas [109]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.2.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_2_1(Integer proyectoId);
    
    /**
     * 14.3.1 Acidez media del mar (pH) medida en un conjunto convenido de estaciones de muestreo representativas [110]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.3.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_3_1(Integer proyectoId);
    
    /**
     * 14.4.1 Proporción de poblaciones de peces cuyos niveles son biológicamente sostenibles [111]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.4.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_4_1(Integer proyectoId);
    
    /**
     * 14.5.1 Cobertura de las zonas protegidas en relación con las zonas marinas [111]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.5.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_5_1(Integer proyectoId);
    
    /**
     * 14.6.1 Grado de aplicación de instrumentos internacionales cuyo objetivo es combatir 
     * la pesca ilegal, no declarada y no reglamentada [112]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.6.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_6_1(Integer proyectoId);
    
    /**
     * 14.7.1 Proporción del PIB correspondiente a la pesca sostenible en los pequeños Estados insulares en desarrollo, 
     * los países menos adelantados y todos los países [112]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.7.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_7_1(Integer proyectoId);
    
    /**
     * 14.a.1 Proporción del presupuesto total de investigación asignada a la investigación 
     * y el desarrollo marítimos [113]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.a.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_a_1(Integer proyectoId);
    
    /**
     * 14.b.1 Grado de aplicación de un marco jurídico, reglamentario, normativo o institucional 
     * que reconozca y proteja los derechos de acceso para la pesca en pequeña escala [114, 115]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.b.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_b_1(Integer proyectoId);
    
    /**
     * 14.c.1 Número de países que, mediante marcos jurídicos, normativos e institucionales, 
     * avanzan en la ratificación, la aceptación y la implementación de los instrumentos relacionados 
     * con los océanos que aplican el derecho internacional reflejado en la Convención de las Naciones Unidas 
     * sobre el Derecho del Mar para la conservación y el uso sostenible de los océanos y sus recursos [115, 116]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 14.c.1
     */
    Optional<ProyectoIndicadores> findIndicador_14_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<ProyectoIndicadores> findAllIndicadoresByProyectoOds14(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "14.1", "14.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS14 ──
    
    /**
     * Encuentra todos los proyectos del ODS14
     * 
     * @return Lista de todos los proyectos del ODS14
     */
    List<Proyectos> findAllProyectosOds14();
    
    /**
     * Encuentra un proyecto del ODS14 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds14ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds14(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS14 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds14ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS14
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds14(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS14 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds14ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS14
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds14> findAllAuditoriasOds14();
    
    /**
     * Encuentra una auditoría del ODS14 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds14> findAuditoriaOds14ById(Integer auditoriaId);
}
