package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.AuditoriaOds12;
import com.odsProject.odsProject.database.jooq.ods12.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.repository.interfaces.IObjetivo12ConsumoProduccionRepository;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods12.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods12.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods12.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods12.tables.AuditoriaOds12.AUDITORIA_ODS12;
import static com.odsProject.odsProject.database.jooq.ods12.tables.VistaAdminResumenGeneral.VISTA_ADMIN_RESUMEN_GENERAL;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;

/**
 * Implementación del Repositorio para el Objetivo 12: Producción y Consumo Responsables
 */
@Repository
public class Objetivo12ConsumoProduccionRepository implements IObjetivo12ConsumoProduccionRepository {

    @Autowired
    @Qualifier("dslOds12")
    private DSLContext dsl;

    private VistaAdminDetalleIndicadores mapToEnriched(Integer proyectoId, String codigo) {
        return dsl.select(
                PROYECTO_INDICADORES.PROYECTO_ID,
                PROYECTOS.NOMBRE_PROYECTO,
                INDICADOR_MASTER.ID.as("indicador_master_id"),
                INDICADOR_MASTER.CODIGO.as("indicador_codigo"),
                INDICADOR_MASTER.NOMBRE.as("indicador_nombre"),
                PROYECTO_INDICADORES.FORMULA_CUSTOM,
                PROYECTO_INDICADORES.VALOR_ACTUAL,
                PROYECTO_INDICADORES.META_VALOR,
                PROYECTO_INDICADORES.META_UNIDAD,
                DSL.case_()
                    .when(PROYECTO_INDICADORES.META_VALOR.isNull()
                          .or(PROYECTO_INDICADORES.META_VALOR.eq(java.math.BigDecimal.ZERO))
                          .or(PROYECTO_INDICADORES.VALOR_ACTUAL.isNull()), "SIN DATOS")
                    .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR), "LOGRADO")
                    .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR.mul(0.8)), "CERCA META")
                    .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR.mul(0.5)), "PROGRESO")
                    .otherwise("BAJO").as("estado_indicador"),
                DSL.round(
                    DSL.case_()
                        .when(PROYECTO_INDICADORES.META_VALOR.isNull().or(PROYECTO_INDICADORES.META_VALOR.eq(java.math.BigDecimal.ZERO)), 0.0)
                        .otherwise(DSL.field("({0} / {1}) * 100", Double.class, 
                            DSL.coalesce(PROYECTO_INDICADORES.VALOR_ACTUAL, 0.0), 
                            PROYECTO_INDICADORES.META_VALOR)), 2).as("porcentaje_logro"),
                PROYECTO_INDICADORES.UPDATED_AT.as("ultima_actualizacion")
        )
        .from(INDICADOR_MASTER)
        .leftJoin(PROYECTO_INDICADORES).on(INDICADOR_MASTER.ID.eq(PROYECTO_INDICADORES.INDICADOR_MASTER_ID)
                .and(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId)))
        .leftJoin(PROYECTOS).on(PROYECTO_INDICADORES.PROYECTO_ID.eq(PROYECTOS.ID))
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(12)))
        .and(INDICADOR_MASTER.CODIGO.eq(codigo))
        .fetchOneInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_1_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.1.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_2_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.2.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_2_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.2.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_3_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.3.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_4_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.4.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_4_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.4.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_5_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.5.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_6_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.6.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_7_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.7.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_8_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.8.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_a_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.a.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_b_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.b.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_12_c_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "12.c.1"); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds12(Integer proyectoId) {
        return dsl.select(
            PROYECTO_INDICADORES.PROYECTO_ID, 
            PROYECTOS.NOMBRE_PROYECTO, 
            INDICADOR_MASTER.ID.as("indicador_master_id"),
            INDICADOR_MASTER.CODIGO.as("indicador_codigo"), 
            INDICADOR_MASTER.NOMBRE.as("indicador_nombre"), 
            PROYECTO_INDICADORES.FORMULA_CUSTOM, 
            PROYECTO_INDICADORES.VALOR_ACTUAL, 
            PROYECTO_INDICADORES.META_VALOR, 
            PROYECTO_INDICADORES.META_UNIDAD,
            DSL.case_()
                .when(PROYECTO_INDICADORES.META_VALOR.isNull()
                      .or(PROYECTO_INDICADORES.META_VALOR.eq(java.math.BigDecimal.ZERO))
                      .or(PROYECTO_INDICADORES.VALOR_ACTUAL.isNull()), "SIN DATOS")
                .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR), "LOGRADO")
                .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR.mul(0.8)), "CERCA META")
                .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR.mul(0.5)), "PROGRESO")
                .otherwise("BAJO").as("estado_indicador"),
            DSL.round(
                DSL.case_()
                    .when(PROYECTO_INDICADORES.META_VALOR.isNull().or(PROYECTO_INDICADORES.META_VALOR.eq(java.math.BigDecimal.ZERO)), 0.0)
                    .otherwise(DSL.field("({0} / {1}) * 100", Double.class, 
                        DSL.coalesce(PROYECTO_INDICADORES.VALOR_ACTUAL, 0.0), 
                        PROYECTO_INDICADORES.META_VALOR)), 2).as("porcentaje_logro"),
            PROYECTO_INDICADORES.UPDATED_AT.as("ultima_actualizacion")
        )
        .from(INDICADOR_MASTER)
        .leftJoin(PROYECTO_INDICADORES).on(INDICADOR_MASTER.ID.eq(PROYECTO_INDICADORES.INDICADOR_MASTER_ID).and(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId)))
        .leftJoin(PROYECTOS).on(PROYECTO_INDICADORES.PROYECTO_ID.eq(PROYECTOS.ID))
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(12)))
        .orderBy(INDICADOR_MASTER.CODIGO.asc())
        .fetchInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return dsl.select(
            PROYECTO_INDICADORES.PROYECTO_ID, 
            PROYECTOS.NOMBRE_PROYECTO, 
            INDICADOR_MASTER.ID.as("indicador_master_id"),
            INDICADOR_MASTER.CODIGO.as("indicador_codigo"), 
            INDICADOR_MASTER.NOMBRE.as("indicador_nombre"), 
            PROYECTO_INDICADORES.FORMULA_CUSTOM, 
            PROYECTO_INDICADORES.VALOR_ACTUAL, 
            PROYECTO_INDICADORES.META_VALOR, 
            PROYECTO_INDICADORES.META_UNIDAD,
            DSL.case_()
                .when(PROYECTO_INDICADORES.META_VALOR.isNull()
                      .or(PROYECTO_INDICADORES.META_VALOR.eq(java.math.BigDecimal.ZERO))
                      .or(PROYECTO_INDICADORES.VALOR_ACTUAL.isNull()), "SIN DATOS")
                .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR), "LOGRADO")
                .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR.mul(0.8)), "CERCA META")
                .when(PROYECTO_INDICADORES.VALOR_ACTUAL.ge(PROYECTO_INDICADORES.META_VALOR.mul(0.5)), "PROGRESO")
                .otherwise("BAJO").as("estado_indicador"),
            DSL.round(
                DSL.case_()
                    .when(PROYECTO_INDICADORES.META_VALOR.isNull().or(PROYECTO_INDICADORES.META_VALOR.eq(java.math.BigDecimal.ZERO)), 0.0)
                    .otherwise(DSL.field("({0} / {1}) * 100", Double.class, 
                        DSL.coalesce(PROYECTO_INDICADORES.VALOR_ACTUAL, 0.0), 
                        PROYECTO_INDICADORES.META_VALOR)), 2).as("porcentaje_logro"),
            PROYECTO_INDICADORES.UPDATED_AT.as("ultima_actualizacion")
        )
        .from(INDICADOR_MASTER)
        .leftJoin(PROYECTO_INDICADORES).on(INDICADOR_MASTER.ID.eq(PROYECTO_INDICADORES.INDICADOR_MASTER_ID).and(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId)))
        .leftJoin(PROYECTOS).on(PROYECTO_INDICADORES.PROYECTO_ID.eq(PROYECTOS.ID))
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(12)))
        .and(INDICADOR_MASTER.CODIGO.startsWith(metaPrefix + "."))
        .orderBy(INDICADOR_MASTER.CODIGO.asc())
        .fetchInto(VistaAdminDetalleIndicadores.class);
    }

    // IOdsBaseRepository
    @Override public List<Proyectos> findAllProyectos() { return dsl.selectFrom(PROYECTOS).fetchInto(Proyectos.class); }
    @Override public Optional<Proyectos> findProyectoById(Integer id) { return dsl.selectFrom(PROYECTOS).where(PROYECTOS.ID.eq(id)).fetchOptionalInto(Proyectos.class); }
    @Override public List<Proyectos> findProyectosByUsuario(Integer usuarioId) { return dsl.selectFrom(PROYECTOS).where(PROYECTOS.USUARIO_ID.eq(usuarioId)).fetchInto(Proyectos.class); }
    @Override public List<Proyectos> findProyectosByEstado(String estado) { return dsl.selectFrom(PROYECTOS).where(PROYECTOS.ESTADO.cast(String.class).eq(estado)).fetchInto(Proyectos.class); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return dsl.insertInto(PROYECTOS).set(dsl.newRecord(PROYECTOS, proyecto)).returning().fetchOneInto(Proyectos.class); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return dsl.update(PROYECTOS).set(dsl.newRecord(PROYECTOS, proyecto)).where(PROYECTOS.ID.eq(proyecto.getId())).returning().fetchOneInto(Proyectos.class); }
    @Override public void deleteProyecto(Integer id) { dsl.deleteFrom(PROYECTOS).where(PROYECTOS.ID.eq(id)).execute(); }

    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByProyecto(Integer proyectoId) { return findAllIndicadoresByProyectoOds12(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorByCodigo(Integer proyectoId, String codigo) { return Optional.ofNullable(mapToEnriched(proyectoId, codigo)); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByCodigoPrefix(String prefix) {
        return dsl.select(INDICADOR_MASTER.CODIGO.as("indicador_codigo"), INDICADOR_MASTER.NOMBRE.as("indicador_nombre")).from(INDICADOR_MASTER).where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(12))).and(INDICADOR_MASTER.CODIGO.startsWith(prefix)).fetchInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return dsl.insertInto(PROYECTO_INDICADORES).set(dsl.newRecord(PROYECTO_INDICADORES, indicador)).returning().fetchOneInto(ProyectoIndicadores.class); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        dsl.update(PROYECTO_INDICADORES).set(dsl.newRecord(PROYECTO_INDICADORES, indicador)).where(PROYECTO_INDICADORES.ID.eq(indicador.getId())).execute();
        return dsl.selectFrom(PROYECTO_INDICADORES).where(PROYECTO_INDICADORES.ID.eq(indicador.getId())).fetchOneInto(ProyectoIndicadores.class);
    }

    @Override public void deleteIndicador(Integer id) {
        dsl.deleteFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID.eq(id)).execute();
        dsl.deleteFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.PROYECTO_INDICADOR_ID.eq(id)).execute();
        dsl.deleteFrom(PROYECTO_INDICADORES).where(PROYECTO_INDICADORES.ID.eq(id)).execute();
    }

    @Override public List<ProyectoIndicadorParametros> findMetasByProyecto(Integer proyectoId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID.in(dsl.select(PROYECTO_INDICADORES.ID).from(PROYECTO_INDICADORES).where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId)))).fetchInto(ProyectoIndicadorParametros.class);
    }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { return dsl.insertInto(PROYECTO_INDICADOR_PARAMETROS).set(dsl.newRecord(PROYECTO_INDICADOR_PARAMETROS, meta)).returning().fetchOneInto(ProyectoIndicadorParametros.class); }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) {
        dsl.update(PROYECTO_INDICADOR_PARAMETROS).set(dsl.newRecord(PROYECTO_INDICADOR_PARAMETROS, meta)).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(meta.getId())).execute();
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(meta.getId())).fetchOneInto(ProyectoIndicadorParametros.class);
    }
    @Override public void deleteMetaProyecto(Integer id) { dsl.deleteFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(id)).execute(); }

    @Override public List<MedicionesHistoricas> findMedicionesByIndicador(Integer indicadorId) { return dsl.selectFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.PROYECTO_INDICADOR_ID.eq(indicadorId)).fetchInto(MedicionesHistoricas.class); }
    @Override public MedicionesHistoricas saveMedicion(MedicionesHistoricas medicion) { return dsl.insertInto(MEDICIONES_HISTORICAS).set(dsl.newRecord(MEDICIONES_HISTORICAS, medicion)).returning().fetchOneInto(MedicionesHistoricas.class); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) {
        dsl.update(MEDICIONES_HISTORICAS).set(dsl.newRecord(MEDICIONES_HISTORICAS, medicion)).where(MEDICIONES_HISTORICAS.ID.eq(medicion.getId())).execute();
        return dsl.selectFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(medicion.getId())).fetchOneInto(MedicionesHistoricas.class);
    }
    @Override public void deleteMedicionHistorica(Integer id) { dsl.deleteFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(id)).execute(); }

    @Override public List<AuditoriaOds12> findAuditoriaReciente(Integer dias) { return dsl.selectFrom(AUDITORIA_ODS12).where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS12.FECHA_CAMBIO, dias).fetchInto(AuditoriaOds12.class); }
    @Override public List<AuditoriaOds12> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) { return dsl.selectFrom(AUDITORIA_ODS12).where(AUDITORIA_ODS12.TABLA_AFECTADA.eq(tablaAfectada)).and(AUDITORIA_ODS12.REGISTRO_ID.eq(registroId)).fetchInto(AuditoriaOds12.class); }

    @Override public Map<String, Object> spAdminDashboard() {
        List<VistaAdminResumenGeneral> resumen = dsl.selectFrom(VISTA_ADMIN_RESUMEN_GENERAL).fetchInto(VistaAdminResumenGeneral.class);
        return Map.of("status", "executed", "message", "Dashboard data retrieved from view for ODS12", "data", resumen);
    }
    @Override public Map<String, Object> spAdminReporteProyecto(Integer proyectoId) {
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        return Map.of("status", "executed", "proyectoId", proyectoId);
    }

    @Override public List<Proyectos> findAllProyectosOds12() { return findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoOds12ById(Integer proyectoId) { return findProyectoById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> findAllMetasProyectoOds12(Integer proyectoId) { return findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoOds12ById(Integer metaId) { return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId)).fetchOptionalInto(ProyectoIndicadorParametros.class); }
    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricasOds12(Integer indicadorId) { return findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaOds12ById(Integer medicionId) { return dsl.selectFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(medicionId)).fetchOptionalInto(MedicionesHistoricas.class); }
    @Override public List<AuditoriaOds12> findAllAuditoriasOds12() { return dsl.selectFrom(AUDITORIA_ODS12).fetchInto(AuditoriaOds12.class); }
    @Override public Optional<AuditoriaOds12> findAuditoriaOds12ById(Integer auditoriaId) { return dsl.selectFrom(AUDITORIA_ODS12).where(AUDITORIA_ODS12.ID.eq(auditoriaId)).fetchOptionalInto(AuditoriaOds12.class); }

    @Override public Boolean existsIndicador(Integer indicadorId) { return dsl.fetchExists(dsl.selectOne().from(PROYECTO_INDICADORES).where(PROYECTO_INDICADORES.ID.eq(indicadorId))); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return dsl.fetchExists(dsl.selectOne().from(PROYECTOS).where(PROYECTOS.ID.eq(proyectoId))); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return dsl.fetchExists(dsl.selectOne().from(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId))); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return dsl.fetchExists(dsl.selectOne().from(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(medicionId))); }

    @Override
    public Optional<ProyectoIndicadores> findIndicadorByIdEntity(Integer id) {
        return dsl.selectFrom(PROYECTO_INDICADORES).where(PROYECTO_INDICADORES.ID.eq(id)).fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public List<ProyectoIndicadorParametros> findMetasByProyectoIndicador(Integer proyectoIndicadorId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID.eq(proyectoIndicadorId)).fetchInto(ProyectoIndicadorParametros.class);
    }
}
