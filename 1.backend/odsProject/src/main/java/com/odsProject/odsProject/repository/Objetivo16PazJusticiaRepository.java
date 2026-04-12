package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;
import com.odsProject.odsProject.database.jooq.ods16.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.repository.interfaces.IObjetivo16PazJusticiaRepository;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods16.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods16.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods16.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods16.tables.AuditoriaOds16.AUDITORIA_ODS16;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;

/**
 * Implementación del Repositorio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 */
@Repository
public class Objetivo16PazJusticiaRepository implements IObjetivo16PazJusticiaRepository {

    @Autowired
    @Qualifier("dslOds16")
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(16)))
        .and(INDICADOR_MASTER.CODIGO.eq(codigo))
        .fetchOneInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.1.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.1.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_3(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.1.3"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_4(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.1.4"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_2_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.2.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_2_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.2.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_2_3(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.2.3"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_3_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.3.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_3_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.3.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_3_3(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.3.3"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_4_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.4.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_4_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.4.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_5_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.5.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_5_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.5.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_6_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.6.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_6_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.6.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_7_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.7.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_7_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.7.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_8_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.8.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_9_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.9.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_10_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.10.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_10_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.10.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_a_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.a.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_16_b_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "16.b.1"); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId) {
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(16)))
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(16)))
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

    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByProyecto(Integer proyectoId) { return findAllIndicadoresByProyectoOds16(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorByCodigo(Integer proyectoId, String codigo) { return Optional.ofNullable(mapToEnriched(proyectoId, codigo)); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByCodigoPrefix(String prefix) {
        return dsl.select(INDICADOR_MASTER.CODIGO.as("indicador_codigo"), INDICADOR_MASTER.NOMBRE.as("indicador_nombre")).from(INDICADOR_MASTER).where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(16))).and(INDICADOR_MASTER.CODIGO.startsWith(prefix)).fetchInto(VistaAdminDetalleIndicadores.class);
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

    @Override public List<AuditoriaOds16> findAuditoriaReciente(Integer dias) { return dsl.selectFrom(AUDITORIA_ODS16).where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS16.FECHA_CAMBIO, dias).fetchInto(AuditoriaOds16.class); }
    @Override public List<AuditoriaOds16> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) { return dsl.selectFrom(AUDITORIA_ODS16).where(AUDITORIA_ODS16.TABLA_AFECTADA.eq(tablaAfectada)).and(AUDITORIA_ODS16.REGISTRO_ID.eq(registroId)).fetchInto(AuditoriaOds16.class); }

    @Override public Map<String, Object> spAdminDashboard() { return Map.of("status", "not_implemented"); }
    @Override public Map<String, Object> spAdminReporteProyecto(Integer proyectoId) {
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        return Map.of("status", "executed", "proyectoId", proyectoId);
    }

    @Override public List<Proyectos> findAllProyectosOds16() { return findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoOds16ById(Integer proyectoId) { return findProyectoById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> findAllMetasProyectoOds16(Integer proyectoId) { return findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoOds16ById(Integer metaId) { return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId)).fetchOptionalInto(ProyectoIndicadorParametros.class); }
    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricasOds16(Integer indicadorId) { return findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaOds16ById(Integer medicionId) { return dsl.selectFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(medicionId)).fetchOptionalInto(MedicionesHistoricas.class); }
    @Override public List<AuditoriaOds16> findAllAuditoriasOds16() { return dsl.selectFrom(AUDITORIA_ODS16).fetchInto(AuditoriaOds16.class); }
    @Override public Optional<AuditoriaOds16> findAuditoriaOds16ById(Integer auditoriaId) { return dsl.selectFrom(AUDITORIA_ODS16).where(AUDITORIA_ODS16.ID.eq(auditoriaId)).fetchOptionalInto(AuditoriaOds16.class); }

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
