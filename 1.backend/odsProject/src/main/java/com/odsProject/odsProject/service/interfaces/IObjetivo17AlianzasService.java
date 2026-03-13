package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 17
 * Extiende IOdsBaseService con tipos específicos de ODS17
 */
public interface IObjetivo17AlianzasService extends IOdsBaseService<
    Indicadores,     // T - Indicadores
    Proyectos,       // P - Proyectos  
    MetasProyecto,   // M - MetasProyecto
    MedicionesHistoricas, // MH - MedicionesHistoricas
    Object           // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 17: Alianzas para Lograr los Objetivos
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.1.1
     * Total de ingresos del gobierno en proporción al PIB, desglosado por fuente
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.1.1
     */
    Optional<Indicadores> getIndicador_17_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.1.2
     * Proporción del presupuesto nacional financiado por impuestos internos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.1.2
     */
    Optional<Indicadores> getIndicador_17_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.2.1
     * Asistencia oficial para el desarrollo neta en proporción al INB
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.2.1
     */
    Optional<Indicadores> getIndicador_17_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.3.1
     * Recursos financieros adicionales movilizados para países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.3.1
     */
    Optional<Indicadores> getIndicador_17_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.3.2
     * Volumen de remesas en proporción al PIB total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.3.2
     */
    Optional<Indicadores> getIndicador_17_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.4.1
     * Servicio de la deuda en proporción a las exportaciones
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.4.1
     */
    Optional<Indicadores> getIndicador_17_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.5.1
     * Número de países que adoptan sistemas de promoción de inversiones
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.5.1
     */
    Optional<Indicadores> getIndicador_17_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.6.1
     * Número de abonados a servicios de banda ancha fija por cada 100 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.6.1
     */
    Optional<Indicadores> getIndicador_17_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.7.1
     * Total de fondos destinados a promover desarrollo, transferencia y difusión de tecnologías
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.7.1
     */
    Optional<Indicadores> getIndicador_17_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.8.1
     * Proporción de personas que utilizan Internet
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.8.1
     */
    Optional<Indicadores> getIndicador_17_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.9.1
     * Valor en dólares de la AOD comprometida para países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.9.1
     */
    Optional<Indicadores> getIndicador_17_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.10.1
     * Promedio arancelario mundial ponderado
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.10.1
     */
    Optional<Indicadores> getIndicador_17_10_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.11.1
     * Participación de países en desarrollo en las exportaciones mundiales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.11.1
     */
    Optional<Indicadores> getIndicador_17_11_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.12.1
     * Promedio ponderado de los aranceles que enfrentan países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.12.1
     */
    Optional<Indicadores> getIndicador_17_12_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.13.1
     * Tablero macroeconómico
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.13.1
     */
    Optional<Indicadores> getIndicador_17_13_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.14.1
     * Número de países con mecanismos para mejorar coherencia de políticas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.14.1
     */
    Optional<Indicadores> getIndicador_17_14_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.15.1
     * Grado de utilización de marcos de resultados y herramientas de planificación
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.15.1
     */
    Optional<Indicadores> getIndicador_17_15_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.16.1
     * Número de países que informan de progresos en marcos de múltiples interesados
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.16.1
     */
    Optional<Indicadores> getIndicador_17_16_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.17.1
     * Suma en dólares prometida a las alianzas público-privadas centradas en infraestructura
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.17.1
     */
    Optional<Indicadores> getIndicador_17_17_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.18.1
     * Indicadores de la capacidad estadística
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.18.1
     */
    Optional<Indicadores> getIndicador_17_18_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.18.2
     * Número de países cuya legislación cumple Principios Fundamentales de Estadísticas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.18.2
     */
    Optional<Indicadores> getIndicador_17_18_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.18.3
     * Número de países con plan estadístico nacional plenamente financiado
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.18.3
     */
    Optional<Indicadores> getIndicador_17_18_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.19.1
     * Valor en dólares de recursos para fortalecer capacidad estadística
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.19.1
     */
    Optional<Indicadores> getIndicador_17_19_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.19.2
     * Proporción de países que han realizado censo y registrado nacimientos y defunciones
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.19.2
     */
    Optional<Indicadores> getIndicador_17_19_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "17.1", "17.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
