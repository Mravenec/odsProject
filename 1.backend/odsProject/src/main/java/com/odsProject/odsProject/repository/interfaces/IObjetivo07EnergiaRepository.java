package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.AuditoriaOds07;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 7: Energía Asequible y No Contaminante
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 7
 * Usa jOOQ con datasource ods07
 */
public interface IObjetivo07EnergiaRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds07> {
    
    /**
     * 7.1.1 Proporción de la población que tiene acceso a la electricidad [55]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.1.1
     */
    Optional<Indicadores> findIndicador_7_1_1(Integer proyectoId);
    
    /**
     * 7.1.2 Proporción de la población cuya fuente primaria de energía son los combustibles y tecnologías limpios [55]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.1.2
     */
    Optional<Indicadores> findIndicador_7_1_2(Integer proyectoId);
    
    /**
     * 7.2.1 Proporción de energía renovable en el consumo final total de energía [56]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.2.1
     */
    Optional<Indicadores> findIndicador_7_2_1(Integer proyectoId);
    
    /**
     * 7.3.1 Intensidad energética medida en función de la energía primaria y el PIB [56]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.3.1
     */
    Optional<Indicadores> findIndicador_7_3_1(Integer proyectoId);
    
    /**
     * 7.a.1 Corrientes financieras internacionales hacia los países en desarrollo para apoyar la investigación 
     * y el desarrollo en el sector de la energía limpia [57]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.a.1
     */
    Optional<Indicadores> findIndicador_7_a_1(Integer proyectoId);
    
    /**
     * 7.b.1 Capacidad instalada de generación de energía renovable en los países en desarrollo 
     * en vatios por habitante [57]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.b.1
     */
    Optional<Indicadores> findIndicador_7_b_1(Integer proyectoId);
    
    /**
     * 7.c.1 Proporción de la población que tiene acceso a tecnologías de energía limpia y renovable [57]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.c.1
     */
    Optional<Indicadores> findIndicador_7_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "7.1", "7.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS07 ──
    
    /**
     * Encuentra todos los proyectos del ODS07
     * 
     * @return Lista de todos los proyectos del ODS07
     */
    List<Proyectos> findAllProyectosOds07();
    
    /**
     * Encuentra un proyecto del ODS07 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds07ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> findAllMetasProyectoOds07(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS07 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> findMetaProyectoOds07ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS07
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds07(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS07 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds07ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS07
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds07> findAllAuditoriasOds07();
    
    /**
     * Encuentra una auditoría del ODS07 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds07> findAuditoriaOds07ById(Integer auditoriaId);
}
