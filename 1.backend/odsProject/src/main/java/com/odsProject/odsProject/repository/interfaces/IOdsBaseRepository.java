package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz base para repositorios ODS usando jOOQ
 * Define los métodos comunes CRUD y consultas específicas ODS
 * @param <T> Tipo de entidad (Indicadores, Proyectos, etc.)
 */
public interface IOdsBaseRepository<T> {
    
    // ── Proyectos ──
    
    /**
     * Encuentra todos los proyectos en el sistema
     * 
     * @return Lista de todos los proyectos
     */
    List<Proyectos> findAllProyectos();
    
    /**
     * Busca un proyecto por su ID
     * 
     * @param id ID del proyecto a buscar
     * @return Optional con el proyecto encontrado o vacío si no existe
     */
    Optional<Proyectos> findProyectoById(Integer id);
    
    /**
     * Encuentra todos los proyectos asociados a un usuario específico
     * 
     * @param usuarioId ID del usuario
     * @return Lista de proyectos del usuario
     */
    List<Proyectos> findProyectosByUsuario(Integer usuarioId);
    
    /**
     * Encuentra todos los proyectos con un estado específico
     * 
     * @param estado Estado del proyecto (ej: "ACTIVO", "INACTIVO", "COMPLETADO")
     * @return Lista de proyectos con el estado especificado
     */
    List<Proyectos> findProyectosByEstado(String estado);
    
    /**
     * Guarda un nuevo proyecto en el sistema
     * 
     * @param proyecto Proyecto a guardar
     * @return Proyecto guardado con ID asignado
     */
    Proyectos saveProyecto(Proyectos proyecto);
    
    /**
     * Actualiza un proyecto existente
     * 
     * @param proyecto Proyecto con datos actualizados
     * @return Proyecto actualizado
     */
    Proyectos updateProyecto(Proyectos proyecto);
    
    /**
     * Elimina un proyecto por su ID
     * 
     * @param id ID del proyecto a eliminar
     */
    void deleteProyecto(Integer id);
    
    // ── Indicadores ──
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de indicadores del proyecto
     */
    List<Indicadores> findIndicadoresByProyecto(Integer proyectoId);
    
    /**
     * Busca un indicador específico por proyecto y código
     * 
     * @param proyectoId ID del proyecto
     * @param codigo Código del indicador (ej: "1.1.1", "2.1.1")
     * @return Optional con el indicador encontrado o vacío si no existe
     */
    Optional<Indicadores> findIndicadorByCodigo(Integer proyectoId, String codigo);
    
    /**
     * Encuentra todos los indicadores cuyo código comienza con un prefijo específico
     * 
     * @param prefix Prefijo del código (ej: "1.", "2.", "3.")
     * @return Lista de indicadores con el prefijo especificado
     */
    List<Indicadores> findIndicadoresByCodigoPrefix(String prefix);
    
    /**
     * Guarda un nuevo indicador en el sistema
     * 
     * @param indicador Indicador a guardar
     * @return Indicador guardado con ID asignado
     */
    Indicadores saveIndicador(Indicadores indicador);
    
    /**
     * Actualiza un indicador existente
     * 
     * @param indicador Indicador con datos actualizados
     * @return Indicador actualizado
     */
    Indicadores updateIndicador(Indicadores indicador);
    
    // ── Metas ──
    
    /**
     * Encuentra todas las metas asociadas a un proyecto específico
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> findMetasByProyecto(Integer proyectoId);
    
    /**
     * Guarda una nueva meta de proyecto
     * 
     * @param meta Meta a guardar
     * @return Meta guardada con ID asignado
     */
    MetasProyecto saveMetaProyecto(MetasProyecto meta);
    
    // ── Mediciones ──
    
    /**
     * Encuentra todas las mediciones históricas asociadas a un indicador específico
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas del indicador
     */
    List<MedicionesHistoricas> findMedicionesByIndicador(Integer indicadorId);
    
    /**
     * Guarda una nueva medición histórica
     * 
     * @param medicion Medición a guardar
     * @return Medición guardada con ID asignado
     */
    MedicionesHistoricas saveMedicion(MedicionesHistoricas medicion);
    
    // ── Auditoría ──
    
    /**
     * Encuentra registros de auditoría recientes de los últimos N días
     * 
     * @param dias Número de días hacia atrás para buscar
     * @return Lista de registros de auditoría recientes
     */
    List<AuditoriaOds01> findAuditoriaReciente(Integer dias);
    
    /**
     * Encuentra registros de auditoría para una tabla y registro específicos
     * 
     * @param tablaAfectada Nombre de la tabla afectada
     * @param registroId ID del registro afectado
     * @return Lista de registros de auditoría para el registro especificado
     */
    List<AuditoriaOds01> findAuditoriaByRegistro(String tablaAfectada, Integer registroId);
    
    // ── Stored Procedures ──
    
    /**
     * Ejecuta el stored procedure sp_admin_dashboard para obtener datos del dashboard
     * 
     * @return Map con los datos del dashboard administrativo
     */
    Map<String, Object> spAdminDashboard();
    
    /**
     * Ejecuta el stored procedure sp_admin_reporte_proyecto para obtener reporte específico
     * 
     * @param proyectoId ID del proyecto para el reporte
     * @return Map con los datos del reporte del proyecto
     */
    Map<String, Object> spAdminReporteProyecto(Integer proyectoId);
}
