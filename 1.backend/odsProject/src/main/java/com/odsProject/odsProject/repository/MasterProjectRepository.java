package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        return findById(proyecto.getId()).orElse(null);
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
        
        // Conteo de usuarios (Métrica global centralizada)
        Integer totalUsuarios = dsl.selectCount().from(USUARIOS).fetchOne(0, Integer.class);

        dashboard.put("totalProyectos", total != null ? total : 0);
        dashboard.put("proyectosActivos", activos != null ? activos : 0);
        dashboard.put("proyectosCompletados", completados != null ? completados : 0);
        dashboard.put("proyectosPlanificacion", planificacion != null ? planificacion : 0);
        dashboard.put("totalUsuarios", totalUsuarios != null ? totalUsuarios : 0);
        
        // Proyectos por estado (para gráficos)
        java.util.Map<String, Integer> estados = new java.util.HashMap<>();
        estados.put("Activo", activos != null ? activos : 0);
        estados.put("Completado", completados != null ? completados : 0);
        estados.put("Planificación", planificacion != null ? planificacion : 0);
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
    public List<VistaResumenProyectosOds> findByUsuarioWithOds(Integer usuarioId) {
        if (usuarioId == null) return java.util.Collections.emptyList();
        // La vista no expone usuario_id; hacemos join contra proyectos para filtrar.
        return dsl.select(
                    VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID,
                    VISTA_RESUMEN_PROYECTOS_ODS.NOMBRE_PROYECTO,
                    VISTA_RESUMEN_PROYECTOS_ODS.GESTOR,
                    VISTA_RESUMEN_PROYECTOS_ODS.SEDE,
                    VISTA_RESUMEN_PROYECTOS_ODS.ESTADO,
                    VISTA_RESUMEN_PROYECTOS_ODS.FECHA_INICIO,
                    VISTA_RESUMEN_PROYECTOS_ODS.FECHA_FIN,
                    VISTA_RESUMEN_PROYECTOS_ODS.ODS_VINCULADOS,
                    VISTA_RESUMEN_PROYECTOS_ODS.ODS_PRIMARIO)
                  .from(VISTA_RESUMEN_PROYECTOS_ODS)
                  .join(PROYECTOS).on(PROYECTOS.ID.eq(VISTA_RESUMEN_PROYECTOS_ODS.PROYECTO_ID))
                  .where(PROYECTOS.USUARIO_ID.eq(usuarioId))
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
}
