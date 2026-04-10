package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.AuditoriaOds03;
import com.odsProject.odsProject.database.jooq.ods03.routines.SpAdminReporteProyecto;
// SpAdminDashboard not present in ODS03 routines
import com.odsProject.odsProject.repository.interfaces.IObjetivo03SaludBienestarRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods03.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods03.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods03.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods03.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods03.tables.AuditoriaOds03.AUDITORIA_ODS03;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;

/**
 * Implementación del Repositorio para el Objetivo 3: Salud y Bienestar
 * Implementa los métodos para acceder a los indicadores del ODS3 usando jOOQ
 * Usa datasource ods03 y sus propios stored procedures
 */
@Repository
public class Objetivo03SaludBienestarRepository implements IObjetivo03SaludBienestarRepository {

    @Autowired
    @Qualifier("dslOds03")
    private DSLContext dsl;

    // ── Métodos específicos de indicadores del ODS03 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_1_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.1.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_1_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.1.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_2_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.2.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_2_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.2.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_3_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.3.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_3_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.3.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_3_3(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.3.3"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_3_4(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.3.4"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_3_5(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.3.5"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_4_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.4.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_4_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.4.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_5_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.5.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_5_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.5.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_6_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.6.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_6_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.6.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_7_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.7.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_7_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.7.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_8_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.8.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_8_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.8.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_9_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.9.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_9_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.9.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_9_3(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.9.3"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_a_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.a.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_b_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.b.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_b_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.b.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_b_3(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.b.3"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_c_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.c.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_d_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.d.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_3_d_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("3.d.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    // ── Métodos Agregados (usando stored procedures del ODS03) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId) {
        // Usar el stored procedure sp_admin_reporte_proyecto del ODS03
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // Filtrar por ODS 03 usando JOIN con INDICADOR_MASTER
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.like("3.%"))
                .fetchInto(ProyectoIndicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        // Usar el stored procedure sp_admin_reporte_proyecto y filtrar por meta
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // Filtrar por metaPrefix usando JOIN con INDICADOR_MASTER
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.like(metaPrefix + ".%"))
                .fetchInto(ProyectoIndicadores.class);
    }

    // ── Implementación de IOdsBaseRepository ──

    // Proyectos
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer id) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(id))
                .fetchOptionalInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findProyectosByUsuario(Integer usuarioId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.USUARIO_ID.eq(usuarioId))
                .fetchInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findProyectosByEstado(String estado) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ESTADO.cast(String.class).eq(estado))
                .fetchInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return dsl.insertInto(PROYECTOS)
                .set(dsl.newRecord(PROYECTOS, proyecto))
                .returning()
                .fetchOneInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return dsl.update(PROYECTOS)
                .set(dsl.newRecord(PROYECTOS, proyecto))
                .where(PROYECTOS.ID.eq(proyecto.getId()))
                .returning()
                .fetchOneInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteProyecto(Integer id) {
        dsl.deleteFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(id))
                .execute();
    }

    // Indicadores
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByProyecto(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_INDICADORES)
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .fetchInto(ProyectoIndicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicadorByCodigo(Integer proyectoId, String codigo) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq(codigo))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByCodigoPrefix(String prefix) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(INDICADOR_MASTER.CODIGO.like(prefix + "%"))
                .fetchInto(ProyectoIndicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        return dsl.insertInto(PROYECTO_INDICADORES)
                .set(dsl.newRecord(PROYECTO_INDICADORES, indicador))
                .returning()
                .fetchOneInto(ProyectoIndicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return dsl.update(PROYECTO_INDICADORES)
                .set(dsl.newRecord(PROYECTO_INDICADORES, indicador))
                .where(PROYECTO_INDICADORES.ID.eq(indicador.getId()))
                .returning()
                .fetchOneInto(ProyectoIndicadores.class);
    }

    // Metas
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findMetasByProyecto(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
                .where(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID.in(
                    dsl.select(PROYECTO_INDICADORES.ID)
                       .from(PROYECTO_INDICADORES)
                       .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                ))
                .fetchInto(ProyectoIndicadorParametros.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return dsl.insertInto(PROYECTO_INDICADOR_PARAMETROS)
                .set(dsl.newRecord(PROYECTO_INDICADOR_PARAMETROS, meta))
                .returning()
                .fetchOneInto(ProyectoIndicadorParametros.class);
    }

    // Mediciones
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findMedicionesByIndicador(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.PROYECTO_INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicion(MedicionesHistoricas medicion) {
        return dsl.insertInto(MEDICIONES_HISTORICAS)
                .set(dsl.newRecord(MEDICIONES_HISTORICAS, medicion))
                .returning()
                .fetchOneInto(MedicionesHistoricas.class);
    }

    // Auditoría
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds03> findAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(AUDITORIA_ODS03)
                .where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS03.FECHA_CAMBIO, dias)
                .fetchInto(AuditoriaOds03.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds03> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) {
        return dsl.selectFrom(AUDITORIA_ODS03)
                .where(AUDITORIA_ODS03.TABLA_AFECTADA.eq(tablaAfectada))
                .and(AUDITORIA_ODS03.REGISTRO_ID.eq(registroId))
                .fetchInto(AuditoriaOds03.class);
    }

    // Stored Procedures
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> spAdminDashboard() {
        // SpAdminDashboard procedure not available for ODS03
        return Map.of(
            "status", "not_implemented",
            "message", "Dashboard procedure not available for ODS03"
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> spAdminReporteProyecto(Integer proyectoId) {
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        return Map.of(
            "status", "executed",
            "proyectoId", proyectoId,
            "message", "Reporte procedure executed for ODS03"
        );
    }

    // ── Métodos específicos del ODS03 ──
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectosOds03() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoOds03ById(Integer proyectoId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(proyectoId))
                .fetchOptionalInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyectoOds03(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
                .where(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID.in(
                    dsl.select(PROYECTO_INDICADORES.ID)
                       .from(PROYECTO_INDICADORES)
                       .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                ))
                .fetchInto(ProyectoIndicadorParametros.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoOds03ById(Integer metaId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
                .where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId))
                .fetchOptionalInto(ProyectoIndicadorParametros.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricasOds03(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.PROYECTO_INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaOds03ById(Integer medicionId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
                .fetchOptionalInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds03> findAllAuditoriasOds03() {
        return dsl.selectFrom(AUDITORIA_ODS03)
                .fetchInto(AuditoriaOds03.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AuditoriaOds03> findAuditoriaOds03ById(Integer auditoriaId) {
        return dsl.selectFrom(AUDITORIA_ODS03)
                .where(AUDITORIA_ODS03.ID.eq(auditoriaId))
                .fetchOptionalInto(AuditoriaOds03.class);
    }
}
