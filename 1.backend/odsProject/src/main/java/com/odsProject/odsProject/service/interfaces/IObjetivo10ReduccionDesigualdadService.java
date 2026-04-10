package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 10: Reducción de las Desigualdades
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 10
 * Extiende IOdsBaseService con tipos específicos de ODS10
 */
public interface IObjetivo10ReduccionDesigualdadService extends IOdsBaseService<
    ProyectoIndicadores,     // T - ProyectoIndicadores
    Proyectos,               // P - Proyectos  
    ProyectoIndicadorParametros, // M - ProyectoIndicadorParametros
    MedicionesHistoricas,     // MH - MedicionesHistoricas
    Object                   // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 10: Reducción de las Desigualdades
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<ProyectoIndicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.1.1
     * Tasas de crecimiento per cápita de los gastos del 40% más pobre
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.1.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.2.1
     * Proporción de personas que viven por debajo del 50% de la mediana de los ingresos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.2.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.3.1
     * Proporción de la población que se siente discriminada o acosada
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.3.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.4.1
     * Proporción del PIB generada por el trabajo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.4.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.4.2
     * Impacto redistributivo de la política fiscal en el índice de Gini
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.4.2
     */
    Optional<ProyectoIndicadores> getIndicador_10_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.5.1
     * Indicadores de solidez financiera
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.5.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.6.1
     * Proporción de miembros y derechos de voto de países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.6.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.7.1
     * Costo de la contratación sufragado por el empleado
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.7.2
     * Proporción de países con políticas migratorias bien gestionadas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.2
     */
    Optional<ProyectoIndicadores> getIndicador_10_7_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.7.3
     * Número de personas que murieron o desaparecieron en proceso de migración
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.3
     */
    Optional<ProyectoIndicadores> getIndicador_10_7_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.7.4
     * Proporción de la población integrada por refugiados
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.7.4
     */
    Optional<ProyectoIndicadores> getIndicador_10_7_4(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.a.1
     * Proporción de líneas arancelarias con arancel cero para países menos adelantados
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.a.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.b.1
     * Corrientes totales de recursos para el desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.b.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 10.c.1
     * Costo de las remesas en proporción a las sumas remitidas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 10.c.1
     */
    Optional<ProyectoIndicadores> getIndicador_10_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS10
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<ProyectoIndicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS10
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "10.1", "10.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
