package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.AuditoriaOds08;
import com.odsProject.odsProject.database.jooq.ods08.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminResumenGeneral;
import com.odsProject.odsProject.repository.interfaces.IObjetivo08CrecimientoEconomicoRepository;
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

import static com.odsProject.odsProject.database.jooq.ods08.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods_master.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods08.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods08.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods08.tables.AuditoriaOds08.AUDITORIA_ODS08;
import static com.odsProject.odsProject.database.jooq.ods08.tables.VistaAdminResumenGeneral.VISTA_ADMIN_RESUMEN_GENERAL;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;
import static com.odsProject.odsProject.database.jooq.ods08.tables.MedicionParametroValores.MEDICION_PARAMETRO_VALORES;

/**
 * Implementación del Repositorio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 */
@Slf4j
@Repository
public class Objetivo08CrecimientoEconomicoRepository implements IObjetivo08CrecimientoEconomicoRepository {

    @Autowired
    @Qualifier("dslOds08")
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(8)))
        .and(INDICADOR_MASTER.CODIGO.eq(codigo))
        .fetchOneInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_1_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.1.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_2_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.2.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_3_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.3.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_4_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.4.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_4_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.4.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_5_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.5.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_5_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.5.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_6_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.6.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_7_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.7.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_8_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.8.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_8_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.8.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_9_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.9.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_9_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.9.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_10_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.10.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_10_2(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.10.2"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_a_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.a.1"); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicador_8_b_1(Integer proyectoId) { return findIndicadorByCodigo(proyectoId, "8.b.1"); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds08(Integer proyectoId) {
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(8)))
        .and(proyectoId != null && proyectoId > 0 ? PROYECTO_INDICADORES.ID.isNotNull() : DSL.noCondition())
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
        .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(8)))
        .and(proyectoId != null && proyectoId > 0 ? PROYECTO_INDICADORES.ID.isNotNull() : DSL.noCondition())
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

    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByProyecto(Integer proyectoId) { return findAllIndicadoresByProyectoOds08(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorByCodigo(Integer proyectoId, String codigo) { return Optional.ofNullable(mapToEnriched(proyectoId, codigo)); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByCodigoPrefix(String prefix) {
        return dsl.select(INDICADOR_MASTER.CODIGO.as("indicador_codigo"), INDICADOR_MASTER.NOMBRE.as("indicador_nombre")).from(INDICADOR_MASTER).where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(8))).and(INDICADOR_MASTER.CODIGO.startsWith(prefix)).fetchInto(VistaAdminDetalleIndicadores.class);
    }

    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        // ─── Sprint 4: UPSERT idempotente ────────────────────────────────
        // Con la UNIQUE KEY uk_proyecto_indicador (proyecto_id, indicador_master_id)
        // podemos re-llamar este método tantas veces como queramos: si el par ya
        // existe, se actualiza la meta y la fórmula; si no, se inserta.
        // ──────────────────────────────────────────────────────────────────
        if (indicador.getProyectoId() == null || indicador.getIndicadorMasterId() == null) {
            throw new IllegalArgumentException("proyectoId e indicadorMasterId son requeridos");
        }
        String unidad = indicador.getMetaUnidad() != null ? indicador.getMetaUnidad() : "unidad";
        java.math.BigDecimal valor = indicador.getMetaValor() != null
            ? indicador.getMetaValor() : java.math.BigDecimal.ZERO;
        org.jooq.Field<String> METANOMBRE_F =
            org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("meta_nombre"), String.class);

        dsl.insertInto(PROYECTO_INDICADORES)
            .set(PROYECTO_INDICADORES.PROYECTO_ID,         indicador.getProyectoId())
            .set(PROYECTO_INDICADORES.INDICADOR_MASTER_ID, indicador.getIndicadorMasterId())
            .set(PROYECTO_INDICADORES.META_VALOR,          valor)
            .set(PROYECTO_INDICADORES.META_UNIDAD,         unidad)
            .set(PROYECTO_INDICADORES.FORMULA_CUSTOM,      indicador.getFormulaCustom())
            .set(METANOMBRE_F,                             indicador.getMetaNombre())
            .onDuplicateKeyUpdate()
            .set(PROYECTO_INDICADORES.META_VALOR,          valor)
            .set(PROYECTO_INDICADORES.META_UNIDAD,         unidad)
            .set(PROYECTO_INDICADORES.FORMULA_CUSTOM,      indicador.getFormulaCustom())
            .set(METANOMBRE_F,                             indicador.getMetaNombre())
            .execute();

        return dsl.selectFrom(PROYECTO_INDICADORES)
            .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(indicador.getProyectoId()))
            .and(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(indicador.getIndicadorMasterId()))
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
        // ─── FIX integración Sprint 3 ↔ frontend.saveParameter ──────────────
        //
        // El esquema tiene UNIQUE KEY uk_proyecto_param (proyecto_indicador_id,
        // nombre_parametro). Cuando saveIndicador dispara seedParametrosFromFormula
        // (Sprint 3), los parámetros ya existen con tipo_dato=Decimal por defecto.
        // Si el frontend luego llama POST /metas para asignar el tipo elegido por
        // el usuario, un INSERT plano choca con duplicate key → 500.
        //
        // UPSERT vía INSERT ... ON DUPLICATE KEY UPDATE: si la fila existe se
        // actualizan nombre_variable, tipo_dato y valor_actual con lo que mandó
        // el cliente (refinamiento del auto-seed). Si no existe, se inserta normal.
        // ────────────────────────────────────────────────────────────────────
        String tipoDatoLiteral = (meta.getTipoDato() != null)
            ? meta.getTipoDato().getLiteral()
            : "Decimal";
        String nombreVar = (meta.getNombreVariable() != null && !meta.getNombreVariable().isEmpty())
            ? meta.getNombreVariable()
            : meta.getNombreParametro();
        java.math.BigDecimal valorActual = meta.getValorActual() != null
            ? meta.getValorActual()
            : java.math.BigDecimal.ZERO;

        org.jooq.Field<String> NOMBRE_VARIABLE_F =
            org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("nombre_variable"), String.class);
        org.jooq.Field<String> TIPO_DATO_F =
            org.jooq.impl.DSL.field(org.jooq.impl.DSL.name("tipo_dato"), String.class);

        dsl.insertInto(PROYECTO_INDICADOR_PARAMETROS)
            .set(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID, meta.getProyectoIndicadorId())
            .set(PROYECTO_INDICADOR_PARAMETROS.NOMBRE_PARAMETRO,      meta.getNombreParametro())
            .set(NOMBRE_VARIABLE_F, nombreVar)
            .set(TIPO_DATO_F,       tipoDatoLiteral)
            .set(PROYECTO_INDICADOR_PARAMETROS.VALOR_ACTUAL, valorActual)
            .onDuplicateKeyUpdate()
            .set(NOMBRE_VARIABLE_F, nombreVar)
            .set(TIPO_DATO_F,       tipoDatoLiteral)
            .set(PROYECTO_INDICADOR_PARAMETROS.VALOR_ACTUAL, valorActual)
            .execute();

        // Refetch por la llave única (ON DUPLICATE KEY UPDATE no soporta RETURNING)
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
            .where(PROYECTO_INDICADOR_PARAMETROS.PROYECTO_INDICADOR_ID.eq(meta.getProyectoIndicadorId()))
            .and(PROYECTO_INDICADOR_PARAMETROS.NOMBRE_PARAMETRO.eq(meta.getNombreParametro()))
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

    @Override public List<AuditoriaOds08> findAuditoriaReciente(Integer dias) { return dsl.selectFrom(AUDITORIA_ODS08).where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS08.FECHA_CAMBIO, dias).fetchInto(AuditoriaOds08.class); }
    @Override public List<AuditoriaOds08> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) { return dsl.selectFrom(AUDITORIA_ODS08).where(AUDITORIA_ODS08.TABLA_AFECTADA.eq(tablaAfectada)).and(AUDITORIA_ODS08.REGISTRO_ID.eq(registroId)).fetchInto(AuditoriaOds08.class); }

    @Override public Map<String, Object> spAdminDashboard() {
        List<VistaAdminResumenGeneral> resumen = dsl.selectFrom(VISTA_ADMIN_RESUMEN_GENERAL).fetchInto(VistaAdminResumenGeneral.class);
        return Map.of("status", "executed", "message", "Dashboard data retrieved from view for ODS08", "data", resumen);
    }
    @Override public Map<String, Object> spAdminReporteProyecto(Integer proyectoId) {
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        return Map.of("status", "executed", "proyectoId", proyectoId);
    }

    @Override public List<Proyectos> findAllProyectosOds08() { return findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoOds08ById(Integer proyectoId) { return findProyectoById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> findAllMetasProyectoOds08(Integer proyectoId) { return findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoOds08ById(Integer metaId) { return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS).where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId)).fetchOptionalInto(ProyectoIndicadorParametros.class); }
    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricasOds08(Integer indicadorId) { return findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaOds08ById(Integer medicionId) { return dsl.selectFrom(MEDICIONES_HISTORICAS).where(MEDICIONES_HISTORICAS.ID.eq(medicionId)).fetchOptionalInto(MedicionesHistoricas.class); }
    @Override public List<AuditoriaOds08> findAllAuditoriasOds08() { return dsl.selectFrom(AUDITORIA_ODS08).fetchInto(AuditoriaOds08.class); }
    @Override public Optional<AuditoriaOds08> findAuditoriaOds08ById(Integer auditoriaId) { return dsl.selectFrom(AUDITORIA_ODS08).where(AUDITORIA_ODS08.ID.eq(auditoriaId)).fetchOptionalInto(AuditoriaOds08.class); }

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

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 2: auditoría granular de mediciones (medicion_parametro_valores)
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public MedicionesHistoricas findMedicionByIdEntity(Integer medicionId) {
        if (medicionId == null) return null;
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
            .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
            .fetchOneInto(MedicionesHistoricas.class);
    }

    @Override
    public Integer insertMedicionParametroValor(Integer medicionId, Integer parametroId, java.math.BigDecimal valor) {
        if (medicionId == null || parametroId == null) return null;
        java.math.BigDecimal v = valor != null ? valor : java.math.BigDecimal.ZERO;
        return dsl.insertInto(MEDICION_PARAMETRO_VALORES)
            .set(MEDICION_PARAMETRO_VALORES.MEDICION_ID, medicionId)
            .set(MEDICION_PARAMETRO_VALORES.PARAMETRO_ID, parametroId)
            .set(MEDICION_PARAMETRO_VALORES.VALOR_INGRESADO, v)
            .returningResult(MEDICION_PARAMETRO_VALORES.ID)
            .fetchOneInto(Integer.class);
    }

    @Override
    public java.util.List<java.util.Map<String, Object>> findMedicionParametroValoresByMedicion(Integer medicionId) {
        if (medicionId == null) return java.util.Collections.emptyList();
        return dsl.select(
                MEDICION_PARAMETRO_VALORES.ID,
                MEDICION_PARAMETRO_VALORES.MEDICION_ID,
                MEDICION_PARAMETRO_VALORES.PARAMETRO_ID,
                MEDICION_PARAMETRO_VALORES.VALOR_INGRESADO,
                PROYECTO_INDICADOR_PARAMETROS.NOMBRE_PARAMETRO,
                PROYECTO_INDICADOR_PARAMETROS.NOMBRE_VARIABLE
            )
            .from(MEDICION_PARAMETRO_VALORES)
            .leftJoin(PROYECTO_INDICADOR_PARAMETROS)
            .on(PROYECTO_INDICADOR_PARAMETROS.ID.eq(MEDICION_PARAMETRO_VALORES.PARAMETRO_ID))
            .where(MEDICION_PARAMETRO_VALORES.MEDICION_ID.eq(medicionId))
            .fetchMaps();
    }

}
