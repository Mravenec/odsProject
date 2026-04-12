package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz para el Repositorio Maestro de Proyectos
 * Gestiona la tabla central 'proyectos' en la base de datos 'ods_master'
 */
public interface IMasterProjectRepository {

    /**
     * Obtiene todos los proyectos registrados en el sistema
     * @return Lista de todos los proyectos
     */
    List<Proyectos> findAll();

    /**
     * Obtiene un proyecto por su ID
     * @param id ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findById(Integer id);

    /**
     * Obtiene los proyectos de un usuario gestor específico
     * @param usuarioId ID del usuario
     * @return Lista de proyectos del usuario
     */
    List<Proyectos> findByUsuario(Integer usuarioId);

    /**
     * Obtiene los proyectos de una sede específica
     * @param sedeId ID de la sede
     * @return Lista de proyectos de la sede
     */
    List<Proyectos> findBySede(Integer sedeId);

    /**
     * Obtiene proyectos por su estado (planificacion, activo, etc.)
     * @param estado Estado del proyecto
     * @return Lista de proyectos con dicho estado
     */
    List<Proyectos> findByEstado(String estado);

    /**
     * Guarda o actualiza un proyecto en la base de datos maestra
     * @param proyecto Objeto proyecto a guardar
     * @return Proyecto guardado con su ID generado
     */
    Proyectos save(Proyectos proyecto);

    /**
     * Actualiza un proyecto existente
     * @param proyecto Objeto proyecto con datos actualizados
     * @return Proyecto actualizado
     */
    Proyectos update(Proyectos proyecto);

    /**
     * Elimina un proyecto por su ID
     * @param id ID del proyecto a eliminar
     */
    void delete(Integer id);

    /**
     * Obtiene estadísticas globales de todos los proyectos en el sistema
     * @return Mapa con métricas administrativas (conteo por estado, totales, etc.)
     */
    java.util.Map<String, Object> spAdminGlobalDashboard();

    /**
     * Verifica si un proyecto existe por ID
     * @param id ID del proyecto
     * @return true si existe, false si no
     */
    boolean exists(Integer id);
}
