package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 10: Reducción de las Desigualdades
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 10
 * Usa jOOQ con datasource ods10
 */
public interface IObjetivo10ReduccionDesigualdadRepository extends IOdsBaseRepository<Indicadores> {
    
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
    
    // ── Consultas agregadas propias ODS10 ──
    List<Indicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId);
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
