package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 3: Salud y Bienestar
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 3
 * Extiende IOdsBaseService con tipos específicos de ODS03
 */
public interface IObjetivo03SaludBienestarService extends IOdsBaseService<
    Indicadores,     // T - Indicadores
    Proyectos,       // P - Proyectos  
    MetasProyecto,   // M - MetasProyecto
    MedicionesHistoricas, // MH - MedicionesHistoricas
    Object           // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 3: Salud y Bienestar
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.1.1
     * Tasa de mortalidad materna
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.1.1
     */
    Optional<Indicadores> getIndicador_3_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.1.2
     * Proporción de partos atendidos por personal especializado
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.1.2
     */
    Optional<Indicadores> getIndicador_3_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.2.1
     * Tasa de mortalidad de niños menores de 5 años
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.2.1
     */
    Optional<Indicadores> getIndicador_3_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.2.2
     * Tasa de mortalidad neonatal
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.2.2
     */
    Optional<Indicadores> getIndicador_3_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.1
     * Nuevas infecciones por VIH por cada 1.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.1
     */
    Optional<Indicadores> getIndicador_3_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.2
     * Incidencia de tuberculosis por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.2
     */
    Optional<Indicadores> getIndicador_3_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.3
     * Incidencia de malaria por cada 1.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.3
     */
    Optional<Indicadores> getIndicador_3_3_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.4
     * Incidencia de hepatitis B por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.4
     */
    Optional<Indicadores> getIndicador_3_3_4(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.5
     * Personas que requieren intervenciones contra enfermedades tropicales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.5
     */
    Optional<Indicadores> getIndicador_3_3_5(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.4.1
     * Tasa de mortalidad por enfermedades no transmisibles
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.4.1
     */
    Optional<Indicadores> getIndicador_3_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.4.2
     * Tasa de mortalidad por suicidio
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.4.2
     */
    Optional<Indicadores> getIndicador_3_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.5.1
     * Cobertura de tratamientos para trastornos por abuso de sustancias
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.5.1
     */
    Optional<Indicadores> getIndicador_3_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.5.2
     * Consumo de alcohol per cápita
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.5.2
     */
    Optional<Indicadores> getIndicador_3_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.6.1
     * Tasa de mortalidad por accidentes de tráfico
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.6.1
     */
    Optional<Indicadores> getIndicador_3_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.6.2
     * Muertes por lesiones en carretera
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.6.2
     */
    Optional<Indicadores> getIndicador_3_6_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.7.1
     * Mujeres que cubren necesidades de planificación familiar
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.7.1
     */
    Optional<Indicadores> getIndicador_3_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.7.2
     * Tasa de fecundidad de adolescentes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.7.2
     */
    Optional<Indicadores> getIndicador_3_7_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.8.1
     * Cobertura de servicios de salud esenciales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.8.1
     */
    Optional<Indicadores> getIndicador_3_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.8.2
     * Proporción con grandes gastos sanitarios por hogar
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.8.2
     */
    Optional<Indicadores> getIndicador_3_8_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.9.1
     * Tasa de mortalidad por contaminación del aire
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.9.1
     */
    Optional<Indicadores> getIndicador_3_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.9.2
     * Tasa de mortalidad por agua insalubre y saneamiento deficiente
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.9.2
     */
    Optional<Indicadores> getIndicador_3_9_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.9.3
     * Tasa de mortalidad por intoxicaciones involuntarias
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.9.3
     */
    Optional<Indicadores> getIndicador_3_9_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.a.1
     * Prevalencia del consumo de tabaco
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.a.1
     */
    Optional<Indicadores> getIndicador_3_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.b.1
     * Proporción de población inmunizada con todas las vacunas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.b.1
     */
    Optional<Indicadores> getIndicador_3_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.b.2
     * AOD destinada a investigación médica y atención sanitaria básica
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.b.2
     */
    Optional<Indicadores> getIndicador_3_b_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.b.3
     * Índice de acceso a productos sanitarios
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.b.3
     */
    Optional<Indicadores> getIndicador_3_b_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.c.1
     * Densidad y distribución del personal sanitario
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.c.1
     */
    Optional<Indicadores> getIndicador_3_c_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.d.1
     * Capacidad del Reglamento Sanitario Internacional
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.d.1
     */
    Optional<Indicadores> getIndicador_3_d_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.d.2
     * Porcentaje de infecciones resistentes a antimicrobianos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.d.2
     */
    Optional<Indicadores> getIndicador_3_d_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "3.1", "3.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──
    
    /**
     * Obtiene todos los proyectos del ODS03
     * 
     * @return Lista de todos los proyectos del ODS03
     */
    List<Proyectos> getAllProjectsOds03();
    
    /**
     * Obtiene un proyecto del ODS03 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> getProjectOds03ById(Integer proyectoId);
    
    /**
     * Obtiene todas las metas de proyecto del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto
     */
    List<MetasProyecto> getAllMetasProyectoOds03(Integer proyectoId);
    
    /**
     * Obtiene una meta de proyecto del ODS03 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> getMetaProyectoOds03ById(Integer metaId);
    
    /**
     * Obtiene todas las mediciones históricas del ODS03
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas
     */
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds03(Integer indicadorId);
    
    /**
     * Obtiene una medición histórica del ODS03 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> getMedicionHistoricaOds03ById(Integer medicionId);
    
    /**
     * Valida los datos de un indicador
     * 
     * @param indicador Datos del indicador a validar
     * @return true si los datos son válidos, false otherwise
     */
    Boolean validateIndicatorData(Indicadores indicador);
    
    /**
     * Calcula el progreso de un proyecto
     * 
     * @param proyectoId ID del proyecto
     * @return Porcentaje de progreso (0.0 - 100.0)
     */
    Double calculateProjectProgress(Integer proyectoId);
    
    /**
     * Obtiene estadísticas del ODS03
     * 
     * @return Map con estadísticas generales
     */
    Map<String, Object> getOds03Statistics();
    
    /**
     * Verifica si un proyecto existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    Boolean projectExists(Integer proyectoId);
    
    /**
     * Verifica si un indicador existe
     * 
     * @param indicadorId ID del indicador
     * @return true si existe, false otherwise
     */
    Boolean indicatorExists(Integer indicadorId);
    
    /**
     * Verifica si una meta de proyecto existe
     * 
     * @param metaId ID de la meta
     * @return true si existe, false otherwise
     */
    Boolean existsMetaProyecto(Integer metaId);
    
    /**
     * Verifica si una medición histórica existe
     * 
     * @param medicionId ID de la medición
     * @return true si existe, false otherwise
     */
    Boolean existsMedicionHistorica(Integer medicionId);
}
