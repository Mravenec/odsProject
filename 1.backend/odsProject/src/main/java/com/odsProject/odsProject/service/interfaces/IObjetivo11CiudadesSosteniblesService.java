package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 11
 * Extiende IOdsBaseService con tipos específicos de ODS11
 */
public interface IObjetivo11CiudadesSosteniblesService extends IOdsBaseService<
    ProyectoIndicadores,     // T - ProyectoIndicadores
    Proyectos,               // P - Proyectos  
    ProyectoIndicadorParametros, // M - ProyectoIndicadorParametros
    MedicionesHistoricas,     // MH - MedicionesHistoricas
    Object                   // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 11: Ciudades y Comunidades Sostenibles
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<ProyectoIndicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.1.1
     * Proporción de la población urbana que vive en barrios marginales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.1.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.2.1
     * Proporción de la población con fácil acceso al transporte público
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.2.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.3.1
     * Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.3.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.3.2
     * Proporción de ciudades con participación directa de la sociedad civil
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.3.2
     */
    Optional<ProyectoIndicadores> getIndicador_11_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.4.1
     * Total de gastos per cápita destinados a la preservación del patrimonio cultural
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.4.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.5.1
     * Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.5.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.5.2
     * Pérdidas económicas directas atribuidas a los desastres en relación con el PIB mundial
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.5.2
     */
    Optional<ProyectoIndicadores> getIndicador_11_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.5.3
     * Daños en la infraestructura crítica e interrupciones de servicios básicos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.5.3
     */
    Optional<ProyectoIndicadores> getIndicador_11_5_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.6.1
     * Proporción de residuos sólidos municipales recogidos y administrados
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.6.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.6.2
     * Niveles medios anuales de partículas finas en las ciudades
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.6.2
     */
    Optional<ProyectoIndicadores> getIndicador_11_6_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.7.1
     * Proporción media de la superficie edificada dedicada a espacios abiertos públicos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.7.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.7.2
     * Proporción de personas que han sido víctimas de acoso en espacios públicos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.7.2
     */
    Optional<ProyectoIndicadores> getIndicador_11_7_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.a.1
     * Número de países con políticas urbanas nacionales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.a.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.b.1
     * Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.b.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.b.2
     * Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.b.2
     */
    Optional<ProyectoIndicadores> getIndicador_11_b_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.c.1
     * Total de asistencia oficial para el desarrollo destinada a infraestructuras urbanas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.c.1
     */
    Optional<ProyectoIndicadores> getIndicador_11_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<ProyectoIndicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "11.1", "11.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
