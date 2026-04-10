package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 9: Industria, Innovación e Infraestructura
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 9
 * Extiende IOdsBaseService con tipos específicos de ODS09
 */
public interface IObjetivo09InfraestructuraService extends IOdsBaseService<
    ProyectoIndicadores,     // T - ProyectoIndicadores
    Proyectos,               // P - Proyectos  
    ProyectoIndicadorParametros, // M - ProyectoIndicadorParametros
    MedicionesHistoricas,     // MH - MedicionesHistoricas
    Object                   // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 9: Industria, Innovación e Infraestructura
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<ProyectoIndicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.1.1
     * Proporción de la población rural que vive cerca de una carretera transitable
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.1.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.1.2
     * Volumen de transporte de pasajeros y carga, desglosado por medio de transporte
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.1.2
     */
    Optional<ProyectoIndicadores> getIndicador_9_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.2.1
     * Valor añadido del sector manufacturo en proporción al PIB y per cápita
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.2.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.2.2
     * Empleo del sector manufacturero en proporción al empleo total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.2.2
     */
    Optional<ProyectoIndicadores> getIndicador_9_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.3.1
     * Proporción del valor añadido total correspondiente a las pequeñas industrias
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.3.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.3.2
     * Proporción de las pequeñas industrias que han obtenido un préstamo o línea de crédito
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.3.2
     */
    Optional<ProyectoIndicadores> getIndicador_9_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.4.1
     * Emisiones de CO2 por unidad de valor añadido
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.4.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.5.1
     * Gastos en investigación y desarrollo en proporción al PIB
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.5.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.5.2
     * Número de investigadores por cada millón de habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.5.2
     */
    Optional<ProyectoIndicadores> getIndicador_9_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.a.1
     * Total de apoyo internacional oficial destinado a la infraestructura
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.a.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.b.1
     * Proporción del valor añadido por la industria de tecnología mediana y alta
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.b.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.c.1
     * Proporción de la población con cobertura de red móvil
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.c.1
     */
    Optional<ProyectoIndicadores> getIndicador_9_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS09
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<ProyectoIndicadores> findAllIndicadoresByProyectoOds09(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS09
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "9.1", "9.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
