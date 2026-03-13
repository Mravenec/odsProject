package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.AuditoriaOds10;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 10: Reducción de las Desigualdades
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 10
 * Usa jOOQ con datasource ods10
 */
public interface IObjetivo10ReduccionDesigualdadRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds10> {
    
    /**
     * 10.1.1 Tasas de crecimiento per cápita de los gastos o ingresos de los hogares del 40% más pobre 
     * de la población y la población total [75]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.1.1
     */
    Optional<Indicadores> findIndicador_10_1_1(Integer proyectoId);
    
    /**
     * 10.2.1 Proporción de personas que viven por debajo del 50% de la mediana de los ingresos, 
     * desglosada por sexo, edad y personas con discapacidad [76]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.2.1
     */
    Optional<Indicadores> findIndicador_10_2_1(Integer proyectoId);
    
    /**
     * 10.3.1 Proporción de la población que declara haberse sentido personalmente discriminada 
     * o acosada en los últimos 12 meses por motivos relacionados con la discriminación [76]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.3.1
     */
    Optional<Indicadores> findIndicador_10_3_1(Integer proyectoId);
    
    /**
     * 10.4.1 Proporción del PIB generada por el trabajo [77]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.4.1
     */
    Optional<Indicadores> findIndicador_10_4_1(Integer proyectoId);
    
    /**
     * 10.4.2 Impacto redistributivo de la política fiscal en el índice de Gini [77]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.4.2
     */
    Optional<Indicadores> findIndicador_10_4_2(Integer proyectoId);
    
    /**
     * 10.5.1 Indicadores de solidez financiera [78]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.5.1
     */
    Optional<Indicadores> findIndicador_10_5_1(Integer proyectoId);
    
    /**
     * 10.6.1 Proporción de miembros y derechos de voto de los países en desarrollo 
     * en las organizaciones internacionales [78]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.6.1
     */
    Optional<Indicadores> findIndicador_10_6_1(Integer proyectoId);
    
    /**
     * 10.7.1 Costo de la contratación sufragado por el empleado en proporción a los ingresos 
     * mensuales percibidos en el país de destino [79]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.1
     */
    Optional<Indicadores> findIndicador_10_7_1(Integer proyectoId);
    
    /**
     * 10.7.2 Proporción de países que han aplicado políticas migratorias bien gestionadas 
     * que facilitan la migración y la movilidad ordenadas, seguras, regulares y responsables de las personas [79, 80]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.2
     */
    Optional<Indicadores> findIndicador_10_7_2(Integer proyectoId);
    
    /**
     * 10.7.3 Número de personas que murieron o desaparecieron en el proceso de migración 
     * hacia un destino internacional [80]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.3
     */
    Optional<Indicadores> findIndicador_10_7_3(Integer proyectoId);
    
    /**
     * 10.7.4 Proporción de la población integrada por refugiados, desglosada por país de origen [80]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.4
     */
    Optional<Indicadores> findIndicador_10_7_4(Integer proyectoId);
    
    /**
     * 10.a.1 Proporción de líneas arancelarias que se aplican a las importaciones de los países 
     * menos adelantados y los países en desarrollo con arancel cero [80, 81]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.a.1
     */
    Optional<Indicadores> findIndicador_10_a_1(Integer proyectoId);
    
    /**
     * 10.b.1 Corrientes totales de recursos para el desarrollo (por ejemplo, asistencia oficial 
     * para el desarrollo, inversión extranjera directa y otras corrientes) [81, 82]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.b.1
     */
    Optional<Indicadores> findIndicador_10_b_1(Integer proyectoId);
    
    /**
     * 10.c.1 Costo de las remesas en proporción a las sumas remitidas [82]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.c.1
     */
    Optional<Indicadores> findIndicador_10_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS10
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS10
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "10.1", "10.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS10 ──
    
    /**
     * Encuentra todos los proyectos del ODS10
     * 
     * @return Lista de todos los proyectos del ODS10
     */
    List<Proyectos> findAllProyectosOds10();
    
    /**
     * Encuentra un proyecto del ODS10 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds10ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS10
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> findAllMetasProyectoOds10(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS10 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> findMetaProyectoOds10ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS10
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds10(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS10 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds10ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS10
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds10> findAllAuditoriasOds10();
    
    /**
     * Encuentra una auditoría del ODS10 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds10> findAuditoriaOds10ById(Integer auditoriaId);
}
