package com.odsProject.odsProject.service.interfaces;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio Base para todos los ODS
 * Define los contratos de negocio comunes para todos los Objetivos de Desarrollo Sostenible
 * @param <T> Tipo de entidad de LECTURA (Enriquecida, ej: VistaAdminDetalleIndicadores)
 * @param <E> Tipo de entidad de ESCRITURA (Tabla física, ej: ProyectoIndicadores)
 * @param <P> Tipo de entidad Proyectos
 * @param <M> Tipo de entidad MetasProyecto
 * @param <MH> Tipo de entidad MedicionesHistoricas
 * @param <A> Tipo de entidad Auditoria
 */
public interface IOdsBaseService<T, E, P, M, MH, A> {
    
    // ── Métodos CRUD Base ──
    
    /**
     * Obtiene todos los proyectos de un ODS específico
     * 
     * @return Lista de todos los proyectos del ODS
     */
    List<P> findAllProyectos();
    
    /**
     * Obtiene un proyecto por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<P> findProyectoById(Integer proyectoId);
    
    /**
     * Crea un nuevo proyecto
     * 
     * @param proyecto Datos del nuevo proyecto
     * @return Proyecto creado con ID asignado
     */
    P saveProyecto(P proyecto);
    
    /**
     * Actualiza un proyecto existente
     * 
     * @param proyecto Datos actualizados del proyecto
     * @return Proyecto actualizado
     */
    P updateProyecto(P proyecto);
    
    /**
     * Elimina un proyecto
     * 
     * @param proyectoId ID del proyecto a eliminar
     * @return true si se eliminó correctamente, false otherwise
     */
    Boolean deleteProyecto(Integer proyectoId);
    
    /**
     * Obtiene todos los indicadores de un proyecto (Enriquecidos)
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<T> findAllIndicadoresByProyecto(Integer proyectoId);
    
    /**
     * Obtiene un indicador por su ID (Enriquecido)
     * 
     * @param indicadorId ID del indicador
     * @return Optional con el indicador encontrado
     */
    Optional<T> findIndicadorById(Integer indicadorId);
    
    /**
     * Crea un nuevo indicador
     * 
     * @param indicador Datos del nuevo indicador (Entidad de tabla)
     * @return Indicador creado con ID asignado
     */
    E saveIndicador(E indicador);
    
    /**
     * Actualiza un indicador existente
     * 
     * @param indicador Datos actualizados del indicador (Entidad de tabla)
     * @return Indicador actualizado
     */
    E updateIndicador(E indicador);
    
    /**
     * Elimina un indicador
     * 
     * @param indicadorId ID del indicador a eliminar
     * @return true si se eliminó correctamente, false otherwise
     */
    Boolean deleteIndicador(Integer indicadorId);
    
    /**
     * Obtiene todas las metas de un proyecto
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto
     */
    List<M> findAllMetasProyecto(Integer proyectoId);
    
    /**
     * Obtiene una meta por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<M> findMetaProyectoById(Integer metaId);
    
    /**
     * Crea una nueva meta de proyecto
     * 
     * @param meta Datos de la nueva meta
     * @return Meta creada con ID asignado
     */
    M saveMetaProyecto(M meta);
    
    /**
     * Actualiza una meta existente
     * 
     * @param meta Datos actualizados de la meta
     * @return Meta actualizada
     */
    M updateMetaProyecto(M meta);
    
    /**
     * Elimina una meta
     * 
     * @param metaId ID de la meta a eliminar
     * @return true si se eliminó correctamente, false otherwise
     */
    Boolean deleteMetaProyecto(Integer metaId);
    
    /**
     * Obtiene todas las mediciones históricas de un indicador
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas
     */
    List<MH> findAllMedicionesHistoricas(Integer indicadorId);
    
    /**
     * Obtiene una medición histórica por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MH> findMedicionHistoricaById(Integer medicionId);
    
    /**
     * Crea una nueva medición histórica
     * 
     * @param medicion Datos de la nueva medición
     * @return Medición creada con ID asignado
     */
    MH saveMedicionHistorica(MH medicion);
    
    /**
     * Actualiza una medición histórica existente
     * 
     * @param medicion Datos actualizados de la medición
     * @return Medición actualizada
     */
    MH updateMedicionHistorica(MH medicion);
    
    /**
     * Elimina una medición histórica
     * 
     * @param medicionId ID de la medición a eliminar
     * @return true si se eliminó correctamente, false otherwise
     */
    Boolean deleteMedicionHistorica(Integer medicionId);
    
    // ── Métodos de Utilidad y Validación ──
    
    /**
     * Valida los datos de un indicador
     * 
     * @param indicador Datos del indicador a validar (Enriquecido)
     * @return true si los datos son válidos, false otherwise
     */
    Boolean validateIndicatorData(T indicador);
    
    /**
     * Valida los datos de un proyecto
     * 
     * @param proyecto Datos del proyecto a validar
     * @return true si los datos son válidos, false otherwise
     */
    Boolean validateProjectData(P proyecto);
    
    /**
     * Calcula el progreso de un proyecto
     * 
     * @param proyectoId ID del proyecto
     * @return Porcentaje de progreso (0-100)
     */
    Double calculateProjectProgress(Integer proyectoId);
    
    /**
     * Obtiene estadísticas generales del ODS
     * 
     * @return Map con estadísticas generales
     */
    Map<String, Object> getOdsStatistics();
    
    /**
     * Verifica si un proyecto existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    Boolean existsProyecto(Integer proyectoId);
    
    /**
     * Verifica si un indicador existe
     * 
     * @param indicadorId ID del indicador
     * @return true si existe, false otherwise
     */
    Boolean existsIndicador(Integer indicadorId);
    
    /**
     * Verifica si una meta existe
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
    
    /**
     * Encuentra indicadores por meta específica
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "1.1", "1.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<T> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
