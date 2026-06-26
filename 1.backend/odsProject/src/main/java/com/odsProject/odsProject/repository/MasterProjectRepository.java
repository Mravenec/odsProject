package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoBeneficiarios;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.odsProject.odsProject.database.jooq.ods_master.Tables.PROYECTO_BENEFICIARIOS;
import static com.odsProject.odsProject.database.jooq.ods_master.Tables.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods_master.Tables.VISTA_RESUMEN_PROYECTOS_ODS;
import static com.odsProject.odsProject.database.jooq.ods_login.Tables.USUARIOS;
import org.jooq.impl.DSL;

/**
 * Implementación del Repositorio Maestro de Proyectos
 * Centraliza la gestión de proyectos en la base de datos 'ods_master'
 */
@Repository
public class MasterProjectRepository implements IMasterProjectRepository {

    @Autowired
    @Qualifier("dslOdsMaster")
    private DSLContext dsl;

    @Override
    public List<Proyectos> findAll() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }

    @Override
    public Optional<Proyectos> findById(Integer id) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(id))
                .fetchOptionalInto(Proyectos.class);
    }

    @Override
    public List<Proyectos> findByUsuario(Integer usuarioId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.USUARIO_ID.eq(usuarioId))
                .fetchInto(Proyectos.class);
    }

    @Override
    public List<Proyectos> findBySede(Integer sedeId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.SEDE_ID.eq(sedeId))
                .fetchInto(Proyectos.class);
    }

    @Override
    public List<Proyectos> findByEstado(String estado) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ESTADO.cast(String.class).equalIgnoreCase(estado))
                .fetchInto(Proyectos.class);
    }

    @Override
    public Proyectos save(Proyectos proyecto) {
        return dsl.insertInto(PROYECTOS)
                .set(dsl.newRecord(PROYECTOS, proyecto))
                .returning()
                .fetchOneInto(Proyectos.class);
    }

    @Override
    public Proyectos update(Proyectos proyecto) {
        dsl.update(PROYECTOS)
                .set(dsl.newRecord(PROYECTOS, proyecto))
                .where(PROYECTOS.ID.eq(proyecto.getId()))
                .execute();
        return findById(proyecto.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "Proyecto no encontrado tras actualizar: " + proyecto.getId()));
    }

    @Override
    public void delete(Integer id) {
        dsl.deleteFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(id))
                .execute();
    }

    @Override
    public java.util.Map<String, Object> spAdminGlobalDashboard() {
        java.util.Map<String, Object> dashboard = new java.util.HashMap<>();
        
        // Métricas básicas de proyectos (insensible a mayúsculas para robustez)
        Integer total = dsl.selectCount().from(PROYECTOS).fetchOne(0, Integer.class);
        Integer activos = dsl.selectCount().from(PROYECTOS).where(DSL.lower(PROYECTOS.ESTADO.cast(String.class)).eq("activo")).fetchOne(0, Integer.class);
        Integer completados = dsl.selectCount().from(PROYECTOS).where(DSL.lower(PROYECTOS.ESTADO.cast(String.class)).eq("completado")).fetchOne(0, Integer.class);
        Integer planificacion = dsl.selectCount().from(PROYECTOS).where(DSL.lower(PROYECTOS.ESTADO.cast(String.class)).eq("planificacion")).fetchOne(0, Integer.class);
        Integer enRevision = dsl.selectCount().from(PROYECTOS).where(DSL.lower(PROYECTOS.ESTADO.cast(String.class)).eq("en_revision")).fetchOne(0, Integer.class);
        
        // Conteo de usuarios (Métrica global centralizada)
        Integer totalUsuarios = dsl.selectCount().from(USUARIOS).fetchOne(0, Integer.class);

        dashboard.put("totalProyectos", total != null ? total : 0);
        dashboard.put("proyectosActivos", activos != null ? activos : 0);
        dashboard.put("proyectosCompletados", completados != null ? completados : 0);
        dashboard.put("proyectosPlanificacion", planificacion != null ? planificacion : 0);
        dashboard.put("proyectosEnRevision", enRevision != null ? enRevision : 0);
        dashboard.put("totalUsuarios", totalUsuarios != null ? totalUsuarios : 0);
        
        // Proyectos por estado (para gráficos)
        java.util.Map<String, Integer> estados = new java.util.HashMap<>();
        estados.put("Activo", activos != null ? activos : 0);
        estados.put("Completado", completados != null ? completados : 0);
        estados.put("Planificación", planificacion != null ? planificacion : 0);
        estados.put("En revisión", enRevision != null ? enRevision : 0);
        dashboard.put("distribucion_estados", estados);

        return dashboard;
    }

    @Override
    public boolean exists(Integer id) {
        return dsl.fetchExists(dsl.selectOne().from(PROYECTOS).where(PROYECTOS.ID.eq(id)));
    }

    // ── Sprint 8.3: listados enriquecidos con ODS via la view de ods_master ──

    @Override
    public List<VistaResumenProyectosOds> findAllWithOds() {
        return dsl.selectFrom(VISTA_RESUMEN_PROYECTOS_ODS)
                  .orderBy(VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID.desc())
                  .fetchInto(VistaResumenProyectosOds.class);
    }

    @Override
    public Optional<VistaResumenProyectosOds> findResumenWithOdsByProyectoId(Integer proyectoId) {
        if (proyectoId == null) return Optional.empty();
        return dsl.selectFrom(VISTA_RESUMEN_PROYECTOS_ODS)
                .where(VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID.eq(proyectoId))
                .fetchOptionalInto(VistaResumenProyectosOds.class);
    }

    @Override
    public List<VistaResumenProyectosOds> findCompletedWithOds() {
        return dsl.selectFrom(VISTA_RESUMEN_PROYECTOS_ODS)
                .where(VISTA_RESUMEN_PROYECTOS_ODS.ESTADO.cast(String.class).equalIgnoreCase("completado"))
                .orderBy(VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID.desc())
                .fetchInto(VistaResumenProyectosOds.class);
    }

    @Override
    public List<VistaResumenProyectosOds> findByUsuarioWithOds(Integer usuarioId) {
        if (usuarioId == null) return java.util.Collections.emptyList();
        // Vista completa (mismo shape que findAllWithOds), filtrada por dueño en proyectos.
        return dsl.selectFrom(VISTA_RESUMEN_PROYECTOS_ODS)
                .where(VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID.in(
                        dsl.select(PROYECTOS.ID)
                                .from(PROYECTOS)
                                .where(PROYECTOS.USUARIO_ID.eq(usuarioId))
                ))
                .orderBy(VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID.desc())
                .fetchInto(VistaResumenProyectosOds.class);
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 2/3: Relación explícita Proyecto ↔ ODS (tabla proyecto_ods)
    //
    //  Usamos DSL.table()/DSL.field() con SQL raw para no depender de POJOs
    //  JOOQ que aún no se han regenerado. Cuando el usuario corra
    //  `mvn spring-boot:run` con la BD actualizada, JOOQ generará una
    //  ProyectoOds POJO oficial, pero este código sigue funcionando.
    // ─────────────────────────────────────────────────────────────────────

    private static final org.jooq.Table<?> PROYECTO_ODS =
        org.jooq.impl.DSL.table(org.jooq.impl.DSL.name("ods_master", "proyecto_ods"));
    private static final org.jooq.Field<Integer> PO_ID =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master", "proyecto_ods", "id"), Integer.class);
    private static final org.jooq.Field<Integer> PO_PROYECTO_ID =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master", "proyecto_ods", "proyecto_id"), Integer.class);
    private static final org.jooq.Field<Integer> PO_ODS_ID =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master", "proyecto_ods", "ods_id"), Integer.class);
    private static final org.jooq.Field<Boolean> PO_ES_PRIMARIO =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master", "proyecto_ods", "es_primario"), Boolean.class);
    private static final org.jooq.Field<java.time.LocalDateTime> PO_FECHA =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master", "proyecto_ods", "fecha_vinculacion"), java.time.LocalDateTime.class);

    @Override
    public Integer linkOds(Integer proyectoId, Integer odsId, boolean esPrimario) {
        if (proyectoId == null || odsId == null) {
            throw new IllegalArgumentException("proyectoId y odsId son requeridos");
        }

        // ─── Sprint 7 — Hotfix trigger mutante ──────────────────────────────
        // Antes había un BEFORE INSERT trigger en proyecto_ods que despromovía
        // a los otros ODS primarios del mismo proyecto. MariaDB lo prohíbe
        // (error 1442: "Can't update table in stored function/trigger because
        // it is already used by statement which invoked this trigger").
        // La regla vive ahora en código: si vamos a marcar este vínculo como
        // primario, primero hacemos UPDATE de los otros del mismo proyecto.
        // ────────────────────────────────────────────────────────────────────
        if (esPrimario) {
            dsl.update(PROYECTO_ODS)
               .set(PO_ES_PRIMARIO, false)
               .where(PO_PROYECTO_ID.eq(proyectoId))
               .and(PO_ES_PRIMARIO.eq(true))
               .execute();
        }

        // UPSERT del vínculo (la UNIQUE uk_proyecto_ods evita duplicados)
        dsl.insertInto(PROYECTO_ODS)
           .set(PO_PROYECTO_ID, proyectoId)
           .set(PO_ODS_ID,      odsId)
           .set(PO_ES_PRIMARIO, esPrimario)
           .onDuplicateKeyUpdate()
           .set(PO_ES_PRIMARIO, esPrimario)
           .execute();
        // Refetch del ID
        return dsl.select(PO_ID)
                  .from(PROYECTO_ODS)
                  .where(PO_PROYECTO_ID.eq(proyectoId)).and(PO_ODS_ID.eq(odsId))
                  .fetchOneInto(Integer.class);
    }

    @Override
    public List<java.util.Map<String, Object>> findOdsByProyecto(Integer proyectoId) {
        if (proyectoId == null) return java.util.Collections.emptyList();
        return dsl.select(PO_ID, PO_PROYECTO_ID, PO_ODS_ID, PO_ES_PRIMARIO, PO_FECHA)
                  .from(PROYECTO_ODS)
                  .where(PO_PROYECTO_ID.eq(proyectoId))
                  .orderBy(PO_ODS_ID.asc())
                  .fetchMaps();
    }

    @Override
    public void unlinkOds(Integer proyectoId, Integer odsId) {
        if (proyectoId == null || odsId == null) return;
        dsl.deleteFrom(PROYECTO_ODS)
           .where(PO_PROYECTO_ID.eq(proyectoId)).and(PO_ODS_ID.eq(odsId))
           .execute();
    }

    @Override
    public java.util.Optional<Integer> findProyectoIndicadorId(Integer odsId, Integer proyectoId,
                                                                Integer indicadorMasterId) {
        if (odsId == null || proyectoId == null || indicadorMasterId == null) {
            return java.util.Optional.empty();
        }
        String schema = String.format("ods%02d", odsId);
        var idField = org.jooq.impl.DSL.field(
                org.jooq.impl.DSL.name(schema, "proyecto_indicadores", "id"), Integer.class);
        var proyectoField = org.jooq.impl.DSL.field(
                org.jooq.impl.DSL.name(schema, "proyecto_indicadores", "proyecto_id"), Integer.class);
        var masterField = org.jooq.impl.DSL.field(
                org.jooq.impl.DSL.name(schema, "proyecto_indicadores", "indicador_master_id"), Integer.class);
        var table = org.jooq.impl.DSL.table(org.jooq.impl.DSL.name(schema, "proyecto_indicadores"));
        Integer id = dsl.select(idField)
                .from(table)
                .where(proyectoField.eq(proyectoId).and(masterField.eq(indicadorMasterId)))
                .fetchOneInto(Integer.class);
        return java.util.Optional.ofNullable(id);
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 15 — Transiciones de estado + stamping de auditoría
    //
    //  Usamos DSL.field() en lugar de PROYECTOS.AUDITADO_POR etc. porque
    //  algunas IDEs no refrescan los POJOs entre regeneraciones de JOOQ.
    //  El método sigue siendo type-safe: pasamos los tipos al DSL.field().
    // ═════════════════════════════════════════════════════════════════════

    private static final org.jooq.Field<String>  FLD_ESTADO =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master","proyectos","estado"), String.class);
    private static final org.jooq.Field<Integer> FLD_AUDITADO_POR =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master","proyectos","auditado_por"), Integer.class);
    private static final org.jooq.Field<java.time.LocalDateTime> FLD_AUDITADO_EN =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master","proyectos","auditado_en"), java.time.LocalDateTime.class);
    private static final org.jooq.Field<String>  FLD_OBSERVACIONES =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master","proyectos","observaciones_cierre"), String.class);
    private static final org.jooq.Field<java.time.LocalDateTime> FLD_ENVIO_REVISION =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master","proyectos","fecha_envio_revision"), java.time.LocalDateTime.class);

    @Override
    public int updateEstado(Integer proyectoId,
                            String nuevoEstado,
                            Integer auditadoPor,
                            String observaciones,
                            boolean stampAuditadoEn,
                            boolean stampEnvioRevision,
                            boolean clearObservacionesCierre) {
        if (proyectoId == null || nuevoEstado == null)
            throw new IllegalArgumentException("proyectoId y nuevoEstado son requeridos");

        var update = dsl.update(PROYECTOS).set(FLD_ESTADO, nuevoEstado);

        // Stamping del cierre (Sprint 17 — aprobación o rechazo)
        if (stampAuditadoEn) {
            update = update
                .set(FLD_AUDITADO_POR, auditadoPor)
                .set(FLD_AUDITADO_EN, java.time.LocalDateTime.now());
        }
        // Stamping del envío a revisión (Sprint 16 — gestor pulsa enviar)
        if (stampEnvioRevision) {
            update = update.set(FLD_ENVIO_REVISION, java.time.LocalDateTime.now());
        }
        if (clearObservacionesCierre) {
            update = update.set(FLD_OBSERVACIONES, (String) null);
        } else if (observaciones != null) {
            update = update.set(FLD_OBSERVACIONES, observaciones);
        }
        return update.where(PROYECTOS.ID.eq(proyectoId)).execute();
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 16/17 — Conteos y validaciones de transición
    // ═════════════════════════════════════════════════════════════════════

    private static final org.jooq.Table<?> PROYECTO_DOCUMENTOS =
        org.jooq.impl.DSL.table(org.jooq.impl.DSL.name("ods_master","proyecto_documentos"));
    private static final org.jooq.Field<Integer> PD_PROYECTO_ID =
        org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("ods_master","proyecto_documentos","proyecto_id"), Integer.class);

    @Override
    public int countDocumentosByProyecto(Integer proyectoId) {
        if (proyectoId == null) return 0;
        Integer n = dsl.selectCount()
                       .from(PROYECTO_DOCUMENTOS)
                       .where(PD_PROYECTO_ID.eq(proyectoId))
                       .fetchOne(0, Integer.class);
        return n != null ? n : 0;
    }

    /**
     * Iteramos los 17 schemas porque proyecto_indicadores está replicado.
     * Para no romper si un schema todavía no existe, usamos try/catch silencioso.
     */
    @Override
    public int countIndicadoresByProyecto(Integer proyectoId) {
        if (proyectoId == null) return 0;
        int total = 0;
        for (int i = 1; i <= 17; i++) {
            String schema = String.format("ods%02d", i);
            try {
                var t = org.jooq.impl.DSL.table(org.jooq.impl.DSL.name(schema, "proyecto_indicadores"));
                var fProj = org.jooq.impl.DSL.field(
                    org.jooq.impl.DSL.name(schema, "proyecto_indicadores", "proyecto_id"), Integer.class);
                Integer n = dsl.selectCount().from(t).where(fProj.eq(proyectoId))
                               .fetchOne(0, Integer.class);
                if (n != null) total += n;
            } catch (Exception ignore) { /* schema no listo aún */ }
        }
        return total;
    }

    @Override
    public List<VistaAdminDetalleIndicadores> findDetalleIndicadoresProyecto(Integer proyectoId) {
        if (proyectoId == null) return List.of();
        Set<Integer> odsIds = new LinkedHashSet<>();
        for (Map<String, Object> link : findOdsByProyecto(proyectoId)) {
            Object raw = link.get("ods_id");
            if (raw == null) raw = link.get("odsId");
            if (raw instanceof Number n) odsIds.add(n.intValue());
        }
        if (odsIds.isEmpty()) {
            for (int i = 1; i <= 17; i++) odsIds.add(i);
        }
        List<VistaAdminDetalleIndicadores> out = new ArrayList<>();
        for (Integer odsId : odsIds) {
            out.addAll(fetchVistaDetalleIndicadores(proyectoId, odsId));
        }
        return out;
    }

    private List<VistaAdminDetalleIndicadores> fetchVistaDetalleIndicadores(Integer proyectoId, int odsId) {
        String schema = String.format("ods%02d", odsId);
        try {
            var view = DSL.table(DSL.name(schema, "vista_admin_detalle_indicadores"));
            var proyectoField = DSL.field(
                    DSL.name(schema, "vista_admin_detalle_indicadores", "proyecto_id"), Integer.class);
            return dsl.selectFrom(view)
                    .where(proyectoField.eq(proyectoId))
                    .fetchInto(VistaAdminDetalleIndicadores.class);
        } catch (Exception ignore) {
            return List.of();
        }
    }

    /**
     * Sprint 17 — Para aprobar una auditoría, TODOS los indicadores del proyecto
     * deben tener al menos una medición. Iteramos los 17 schemas; si alguno tiene
     * un indicador sin mediciones, devolvemos false inmediatamente.
     */
    @Override
    public boolean allIndicadoresTienenMedicion(Integer proyectoId) {
        if (proyectoId == null) return false;
        boolean encontroAlgunIndicador = false;
        for (int i = 1; i <= 17; i++) {
            String schema = String.format("ods%02d", i);
            try {
                var pi = org.jooq.impl.DSL.table(org.jooq.impl.DSL.name(schema, "proyecto_indicadores"));
                var mh = org.jooq.impl.DSL.table(org.jooq.impl.DSL.name(schema, "mediciones_historicas"));
                var piId   = org.jooq.impl.DSL.field(org.jooq.impl.DSL.name(schema,"proyecto_indicadores","id"), Integer.class);
                var piProj = org.jooq.impl.DSL.field(org.jooq.impl.DSL.name(schema,"proyecto_indicadores","proyecto_id"), Integer.class);
                var mhPi   = org.jooq.impl.DSL.field(org.jooq.impl.DSL.name(schema,"mediciones_historicas","proyecto_indicador_id"), Integer.class);

                // Indicadores SIN ninguna medición:
                //   SELECT 1 FROM proyecto_indicadores pi
                //     WHERE pi.proyecto_id = ?
                //       AND NOT EXISTS (SELECT 1 FROM mediciones_historicas mh WHERE mh.proyecto_indicador_id = pi.id)
                //     LIMIT 1
                Integer indicadoresEnEsteSchema = dsl.selectCount().from(pi).where(piProj.eq(proyectoId))
                                                     .fetchOne(0, Integer.class);
                if (indicadoresEnEsteSchema != null && indicadoresEnEsteSchema > 0) {
                    encontroAlgunIndicador = true;
                    Integer sinMedicion = dsl.selectCount().from(pi)
                        .where(piProj.eq(proyectoId))
                        .andNotExists(
                            dsl.selectOne().from(mh).where(mhPi.eq(piId))
                        )
                        .fetchOne(0, Integer.class);
                    if (sinMedicion != null && sinMedicion > 0) return false;
                }
            } catch (Exception ignore) { /* schema no listo aún */ }
        }
        // Solo aprobamos si encontramos al menos un indicador y ninguno quedó sin medición.
        return encontroAlgunIndicador;
    }

    // ═════════════════════════════════════════════════════════════════════
    //  Sprint 19 — Métricas para AuditQueuePage
    // ═════════════════════════════════════════════════════════════════════

    @Override
    public java.util.Map<String, Object> auditQueueMetrics() {
        java.util.Map<String, Object> m = new java.util.LinkedHashMap<>();

        Integer pendientes = dsl.selectCount().from(PROYECTOS)
            .where(FLD_ESTADO.eq("en_revision"))
            .fetchOne(0, Integer.class);

        // "En curso" = activos que ya tienen al menos un documento
        Integer enCurso = dsl.selectCount().from(PROYECTOS)
            .where(FLD_ESTADO.eq("activo"))
            .andExists(
                dsl.selectOne().from(PROYECTO_DOCUMENTOS).where(PD_PROYECTO_ID.eq(PROYECTOS.ID))
            )
            .fetchOne(0, Integer.class);

        // Auditados este mes
        Integer auditadosMes = dsl.selectCount().from(PROYECTOS)
            .where(FLD_ESTADO.eq("completado"))
            .and(org.jooq.impl.DSL.year(FLD_AUDITADO_EN).eq(org.jooq.impl.DSL.year(org.jooq.impl.DSL.currentLocalDateTime())))
            .and(org.jooq.impl.DSL.month(FLD_AUDITADO_EN).eq(org.jooq.impl.DSL.month(org.jooq.impl.DSL.currentLocalDateTime())))
            .fetchOne(0, Integer.class);

        // Tiempo promedio de auditoría (horas) — últimos 30 días
        // AVG(TIMESTAMPDIFF(HOUR, fecha_envio_revision, auditado_en))
        Double promedio = null;
        try {
            promedio = dsl.fetchValue(
                "SELECT AVG(TIMESTAMPDIFF(HOUR, fecha_envio_revision, auditado_en)) " +
                "  FROM ods_master.proyectos " +
                " WHERE estado = 'completado' " +
                "   AND auditado_en >= DATE_SUB(NOW(), INTERVAL 30 DAY) " +
                "   AND fecha_envio_revision IS NOT NULL"
            ) instanceof Number n ? n.doubleValue() : null;
        } catch (Exception ignore) { /* sin datos aún */ }

        m.put("pendientes",    pendientes != null ? pendientes : 0);
        m.put("enCurso",       enCurso    != null ? enCurso    : 0);
        m.put("auditadosMes",  auditadosMes != null ? auditadosMes : 0);
        m.put("tiempoPromedioHoras", promedio);
        return m;
    }

    @Override
    public List<Proyectos> findByIds(Collection<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.in(ids))
                .fetchInto(Proyectos.class);
    }

    @Override
    public List<ProyectoBeneficiarios> findBeneficiariosByProyecto(Integer proyectoId) {
        if (proyectoId == null) {
            return Collections.emptyList();
        }
        return dsl.selectFrom(PROYECTO_BENEFICIARIOS)
                .where(PROYECTO_BENEFICIARIOS.PROYECTO_ID.eq(proyectoId))
                .fetchInto(ProyectoBeneficiarios.class);
    }

    @Override
    public void replaceBeneficiarios(Integer proyectoId, List<ProyectoBeneficiarios> beneficiarios) {
        if (proyectoId == null) {
            throw new IllegalArgumentException("proyectoId es requerido");
        }
        dsl.deleteFrom(PROYECTO_BENEFICIARIOS).where(PROYECTO_BENEFICIARIOS.PROYECTO_ID.eq(proyectoId)).execute();
        if (beneficiarios == null) {
            return;
        }
        for (ProyectoBeneficiarios b : beneficiarios) {
            if (b == null || b.getValorId() == null) continue;
            dsl.insertInto(PROYECTO_BENEFICIARIOS)
                    .set(PROYECTO_BENEFICIARIOS.PROYECTO_ID, proyectoId)
                    .set(PROYECTO_BENEFICIARIOS.VALOR_ID, b.getValorId())
                    .onDuplicateKeyIgnore()
                    .execute();
        }
    }
}
