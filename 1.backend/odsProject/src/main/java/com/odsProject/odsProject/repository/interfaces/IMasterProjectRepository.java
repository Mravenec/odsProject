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

    // ── Sprint 8.3: Listados enriquecidos con info de ODS ────────────────
    //
    // Devuelven el view POJO ya generado por JOOQ (VistaResumenProyectosOds),
    // que incluye odsPrimario y odsVinculados (CSV). Sin esto la UI muestra
    // "Objetivo Desconocido" en el listado.

    /**
     * Lista todos los proyectos con su ODS primario y la lista CSV de ODS vinculados.
     */
    java.util.List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
        findAllWithOds();

    /**
     * Resumen enriquecido (vista) de un proyecto por ID.
     */
    java.util.Optional<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
        findResumenWithOdsByProyectoId(Integer proyectoId);

    /**
     * Proyectos en estado completado con datos ODS (exportación / planificación).
     */
    java.util.List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
        findCompletedWithOds();

    /**
     * Lista los proyectos de un usuario con su ODS primario y ODS vinculados.
     */
    java.util.List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
        findByUsuarioWithOds(Integer usuarioId);

    // ── Sprint 2/3: Relación explícita Proyecto ↔ ODS ────────────────────

    /**
     * Vincula un proyecto a un ODS. Idempotente: si ya está vinculado, actualiza
     * el flag es_primario. Si es_primario=true, despromueve los demás del mismo proyecto.
     *
     * @return el ID generado o existente del registro en proyecto_ods
     */
    Integer linkOds(Integer proyectoId, Integer odsId, boolean esPrimario);

    /**
     * Devuelve la lista de ODS vinculados a un proyecto.
     */
    java.util.List<java.util.Map<String, Object>> findOdsByProyecto(Integer proyectoId);

    /**
     * Desvincula un ODS de un proyecto. ON DELETE CASCADE en BD ya borra los
     * indicadores asociados; este método solo limpia la fila de proyecto_ods.
     */
    void unlinkOds(Integer proyectoId, Integer odsId);

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 15 — Transiciones de estado + stamping de auditoría
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Cambia el estado de un proyecto y, si aplica, registra quién y cuándo lo cerró.
     *
     * @param proyectoId           Proyecto a actualizar.
     * @param nuevoEstado          'planificacion' | 'activo' | 'en_revision' | 'completado' | 'cancelado'
     * @param auditadoPor          ID del auditor (NULL para transiciones que no son cierre).
     * @param observaciones        Texto libre o motivo de rechazo (NULL si no aplica).
     * @param stampAuditadoEn      Si true, setea auditado_en = NOW(). Si false, lo deja como esté.
     * @param stampEnvioRevision   Si true, setea fecha_envio_revision = NOW().
     * @return Número de filas afectadas (1 = OK, 0 = proyecto no existe).
     */
    int updateEstado(Integer proyectoId,
                     String nuevoEstado,
                     Integer auditadoPor,
                     String observaciones,
                     boolean stampAuditadoEn,
                     boolean stampEnvioRevision);

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 16/17 — Validaciones de transición (defensa en BD)
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Cuenta documentos de evidencia subidos para un proyecto.
     * Sprint 16 lo usa para validar que el gestor adjuntó al menos un archivo
     * antes de enviar a revisión.
     */
    int countDocumentosByProyecto(Integer proyectoId);

    /**
     * Cuenta indicadores configurados para un proyecto sumando los 17 schemas
     * (proyecto_indicadores existe replicado en ods01..ods17).
     * Sprint 16 lo usa como precondición; Sprint 17 lo usa para validar que
     * todos los indicadores tienen al menos una medición antes de aprobar.
     */
    int countIndicadoresByProyecto(Integer proyectoId);

    /**
     * Verifica que TODO indicador del proyecto tiene al menos una medición
     * histórica registrada. Es la precondición central para "Cerrar auditoría"
     * (Sprint 17): no se puede aprobar un proyecto si faltan datos.
     *
     * @return true si todos los indicadores tienen ≥1 medición, false en caso contrario.
     */
    boolean allIndicadoresTienenMedicion(Integer proyectoId);

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 19 — Métricas para la cola de auditoría
    // ─────────────────────────────────────────────────────────────────────

    /**
     * Devuelve métricas agregadas para el panel superior del AuditQueuePage:
     *   - pendientes    : proyectos en estado 'en_revision'
     *   - enCurso       : proyectos 'activo' con al menos un documento subido
     *   - auditadosMes  : proyectos 'completado' cuyo auditado_en cae en el mes actual
     *   - tiempoPromedioHoras : promedio de horas entre fecha_envio_revision y auditado_en
     *                           en los últimos 30 días (NULL si aún no hay datos)
     */
    java.util.Map<String, Object> auditQueueMetrics();
}
