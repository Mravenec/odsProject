package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.AuditoriaOds06;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 6: Agua Limpia y Saneamiento
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 6
 * Usa jOOQ con datasource ods06
 */
public interface IObjetivo06AguaSaneamientoRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds06> {
    
    /**
     * 6.1.1 Proporción de la población que utiliza servicios de suministro de agua potable gestionados sin riesgos [49]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.1.1
     */
    Optional<Indicadores> findIndicador_6_1_1(Integer proyectoId);
    
    /**
     * 6.2.1 Proporción de la población que utiliza: a) servicios de saneamiento gestionados sin riesgos 
     * y b) instalaciones de lavado de manos con agua y jabón [49]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.2.1
     */
    Optional<Indicadores> findIndicador_6_2_1(Integer proyectoId);
    
    /**
     * 6.3.1 Proporción de los flujos de aguas residuales domésticas e industriales tratados de manera adecuada [50]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.3.1
     */
    Optional<Indicadores> findIndicador_6_3_1(Integer proyectoId);
    
    /**
     * 6.3.2 Proporción de masas de agua de buena calidad [51]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.3.2
     */
    Optional<Indicadores> findIndicador_6_3_2(Integer proyectoId);
    
    /**
     * 6.4.1 Cambio en el uso eficiente de los recursos hídricos con el paso del tiempo [51]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.4.1
     */
    Optional<Indicadores> findIndicador_6_4_1(Integer proyectoId);
    
    /**
     * 6.4.2 Nivel de estrés hídrico: extracción de agua dulce en proporción a los recursos de agua dulce disponibles [51]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.4.2
     */
    Optional<Indicadores> findIndicador_6_4_2(Integer proyectoId);
    
    /**
     * 6.5.1 Grado de gestión integrada de los recursos hídricos [52]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.5.1
     */
    Optional<Indicadores> findIndicador_6_5_1(Integer proyectoId);
    
    /**
     * 6.5.2 Proporción de la superficie de cuencas transfronterizas sujetas a arreglos operacionales 
     * para la gestión cooperativa del agua [52]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.5.2
     */
    Optional<Indicadores> findIndicador_6_5_2(Integer proyectoId);
    
    /**
     * 6.6.1 Cambio en la extensión de los ecosistemas relacionados con el agua con el paso del tiempo [53]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.6.1
     */
    Optional<Indicadores> findIndicador_6_6_1(Integer proyectoId);
    
    /**
     * 6.a.1 Volumen de la asistencia oficial para el desarrollo destinada al agua y el saneamiento 
     * que forma parte de un plan de gastos coordinados por el gobierno [53]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.a.1
     */
    Optional<Indicadores> findIndicador_6_a_1(Integer proyectoId);
    
    /**
     * 6.b.1 Proporción de dependencias administrativas locales que han establecido políticas 
     * y procedimientos operacionales para la participación de las comunidades locales 
     * en la gestión del agua y el saneamiento [54, 55]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 6.b.1
     */
    Optional<Indicadores> findIndicador_6_b_1(Integer proyectoId);
    
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
    
    // ── Métodos específicos del ODS06 ──
    
    /**
     * Encuentra todos los proyectos del ODS06
     * 
     * @return Lista de todos los proyectos del ODS06
     */
    List<Proyectos> findAllProyectosOds06();
    
    /**
     * Encuentra un proyecto del ODS06 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds06ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS06
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> findAllMetasProyectoOds06(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS06 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> findMetaProyectoOds06ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS06
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds06(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS06 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds06ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS06
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds06> findAllAuditoriasOds06();
    
    /**
     * Encuentra una auditoría del ODS06 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds06> findAuditoriaOds06ById(Integer auditoriaId);
}
