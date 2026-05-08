package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.AuditoriaOds07;
import com.odsProject.odsProject.database.jooq.ods07.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.repository.interfaces.IObjetivo07EnergiaRepository;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods07.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods07.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods07.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods07.tables.AuditoriaOds07.AUDITORIA_ODS07;
import static com.odsProject.odsProject.database.jooq.ods07.tables.VistaAdminResumenGeneral.VISTA_ADMIN_RESUMEN_GENERAL;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;

/**
 * Implementación del Repositorio para el Objetivo 7: Energía Asequible y No Contaminante
 */
@Slf4j
@Repository
public class Objetivo07EnergiaRepository implements IObjetivo07EnergiaRepository {

    @Autowired
    @Qualifier("dslOds07")
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
                PROYECTO_INDICADORES.META_NOMBRE,
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(7)))
        .and(INDICADOR_MASTER.CODIGO.eq(codigo))
        .fetchOneInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_1_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.1.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_1_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.1.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_2_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.2.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_3_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.3.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_a_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.a.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_b_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.b.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_7_c_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "7.c.1"); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId) {
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
            PROYECTO_INDICADORES.META_NOMBRE,
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(7)))
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
            PROYECTO_INDICADORES.META_NOMBRE,
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(7)))
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

    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByProyecto(Integer proyectoId) { return findAllIndicadoresByProyectoOds07(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorByCodigo(Integer proyectoId, String codigo) { return Optional.ofNullable(mapToEnriched(proyectoId, codigo)); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByCodigoPrefix(String prefix) {
        return dsl.select(INDICADOR_MASTER.CODIGO.as("indicador_codigo"), INDICADOR_MASTER.NOMBRE.as("indicador_nombre")).from(INDICADOR_MASTER).where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(7))).and(INDICADOR_MASTER.CODIGO.startsWith(prefix)).fetchInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        Integer newId;
        try {
            // Intento primario: con meta_nombre
            newId = dsl.insertInto(PROYECTO_INDICADORES)
                .set(PROYECTO_INDICADORES.PROYECTO_ID,         indicador.getProyectoId())
                .set(PROYECTO_INDICADORES.INDICADOR_MASTER_ID, indicador.getIndicadorMasterId())
                .set(PROYECTO_INDICADORES.META_VALOR,          indicador.getMetaValor())
                .set(PROYECTO_INDICADORES.META_UNIDAD,         indicador.getMetaUnidad() != null ? indicador.getMetaUnidad() : "unidad")
                .set(PROYECTO_INDICADORES.FORMULA_CUSTOM,      indicador.getFormulaCustom())
                .set(org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("meta_nombre"), String.class), indicador.getMetaNombre())
                .returningResult(PROYECTO_INDICADORES.ID)
                .fetchOneInto(Integer.class);
        } catch (Exception e) {
            // Fallback: sin meta_nombre (esquema antiguo)
            log.warn("[saveIndicador] Fallback sin meta_nombre: {}", e.getMessage());
            newId = dsl.insertInto(PROYECTO_INDICADORES)
                .set(PROYECTO_INDICADORES.PROYECTO_ID,         indicador.getProyectoId())
                .set(PROYECTO_INDICADORES.INDICADOR_MASTER_ID, indicador.getIndicadorMasterId())
                .set(PROYECTO_INDICADORES.META_VALOR,          indicador.getMetaValor())
                .set(PROYECTO_INDICADORES.META_UNIDAD,         indicador.getMetaUnidad() != null ? indicador.getMetaUnidad() : "unidad")
                .set(PROYECTO_INDICADORES.FORMULA_CUSTOM,      indicador.getFormulaCustom())
                .returningResult(PROYECTO_INDICADORES.ID)
                .fetchOneInto(Integer.class);
        }
        return dsl.selectFrom(PROYECTO_INDICADORES)
            .where(PROYECTO_INDICADORES.ID.eq(newId))
            .fetchOneInto(ProyectoIndicadores.class);
    }
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
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        // tipo_dato es ENUM en DB — usar DSL.field(String) para evitar ClassCastException
        String tipoDatoLiteral = (meta.getTipoDato() != null)
            ? meta.getTipoDato().getLiteral()
            : "Decimal";
        String nombreVar = (meta.getNombreVariable() != null && !meta.getNombreVariable().isEmpty())
            ? meta.getNombreVariable()
            : meta.getNombreParametro();

        Integer newId = dsl.insertInto(PROYECTO_INDICADOR_PARAMETROS)
            .set(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID, meta.getProyectoIndicadorId())
            .set(PROYECTO_INDICADOR_PARAMETROS.NOMBRE_PARAMETRO,      meta.getNombreParametro())
            .set(org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("nombre_variable"), String.class), nombreVar)
            .set(org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("tipo_dato"),       String.class), tipoDatoLiteral)
            .set(PROYECTO_INDICADOR_PARAMETROS.VALOR_ACTUAL,
                 meta.getValorActual() != null ? meta.getValorActual() : java.math.BigDecimal.ZERO)
            .returningResult(PROYECTO_INDICADOR_PARAMETROS.ID)
            .fetchOneInto(Integer.class);

        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
            .where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(newId))
            .fetchOneInto(ProyectoIndicadorParametros.class);
    }
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

    @Override public List<AuditoriaOds07> findAuditoriaReciente(Integer dias) { return dsl.selectFrom(AUDITORIA_ODS07).where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS07.FECHA_CAMBIO, dias).fetchInto(AuditoriaOds07.class); }
    @Override public List<AuditoriaOds07> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) { return dsl.selectFrom(AUDITORIA_ODS07).where(AUDITORIA_ODS07.TABLA_AFECTADA.eq(tablaAfectada)).and(AUDITORIA_ODS07.REGISTRO_ID.eq(registroId)).fetchInto(AuditoriaOds07.class); }

    @Override public Map<String, Object> spAdminDashboard() {
        List<VistaAdminResumenGeneral> resumen = dsl.selectFrom(VISTA_ADMIN_RESUMEN_GENERAL).fetchInto(VistaAdminResumenGeneral.class);
        return Map.of("status", "executed", "message", "Dashboard data retrieved from view for ODS07", "data", resumen);
    }
    @Override public Map<String, Object> spAdminReporteProyecto(Integer proyectoId) {
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        return Map.of("status", "executed", "proyectoId", proyectoId);
    }

    @Override public List<Proyectos> findAllProyectosOds07() { return findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoOds07ById(Integer proyectoId) { return findProyectoById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> findAllMetasProyectoOds07(Integer proyectoId) { return findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoOds07ById(Integer metaId) { return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId)).fetchOptionalInto(ProyectoIndicadorParametros.class); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaOds07ById(Integer medicionId) { return dsl.selectFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(medicionId)).fetchOptionalInto(MedicionesHistoricas.class); }
    @Override public List<AuditoriaOds07> findAllAuditoriasOds07() { return dsl.selectFrom(AUDITORIA_ODS07).fetchInto(AuditoriaOds07.class); }
    @Override public Optional<AuditoriaOds07> findAuditoriaOds07ById(Integer auditoriaId) { return dsl.selectFrom(AUDITORIA_ODS07).where(AUDITORIA_ODS07.ID.eq(auditoriaId)).fetchOptionalInto(AuditoriaOds07.class); }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricasOds07(Integer indicadorId) { return findMedicionesByIndicador(indicadorId); }

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
