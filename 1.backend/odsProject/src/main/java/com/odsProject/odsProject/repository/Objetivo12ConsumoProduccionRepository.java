package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.AuditoriaOds12;
import com.odsProject.odsProject.database.jooq.ods12.routines.SpAdminReporteProyecto;
// SpAdminDashboard not present in ODS12 routines
import com.odsProject.odsProject.repository.interfaces.IObjetivo12ConsumoProduccionRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods12.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods12.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods12.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods12.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods12.tables.AuditoriaOds12.AUDITORIA_ODS12;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;

/**
 * Implementación del Repositorio para el Objetivo 12: Producción y Consumo Responsables
 * Implementa los métodos para acceder a los indicadores del ODS12 usando jOOQ
 * Usa datasource ods12 y sus propios stored procedures
 */
@Repository
public class Objetivo12ConsumoProduccionRepository implements IObjetivo12ConsumoProduccionRepository {

    @Autowired
    @Qualifier("dslOds12")
    private DSLContext dsl;

    // ── Métodos específicos de indicadores del ODS12 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_1_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.1.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_2_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.2.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_2_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.2.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_3_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.3.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_4_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.4.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_4_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.4.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_5_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.5.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_6_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.6.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_7_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.7.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_8_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.8.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_a_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.a.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_b_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.b.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_12_c_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("12.c.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    // ── Métodos Agregados (usando stored procedures del ODS12) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds12(Integer proyectoId) {
        // Usar el stored procedure sp_admin_reporte_proyecto del ODS12
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // Filtrar por ODS 12 usando JOIN con INDICADOR_MASTER
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.like("12.%"))
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
    public List<AuditoriaOds12> findAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(AUDITORIA_ODS12)
                .where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS12.FECHA_CAMBIO, dias)
                .fetchInto(AuditoriaOds12.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds12> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) {
        return dsl.selectFrom(AUDITORIA_ODS12)
                .where(AUDITORIA_ODS12.TABLA_AFECTADA.eq(tablaAfectada))
                .and(AUDITORIA_ODS12.REGISTRO_ID.eq(registroId))
                .fetchInto(AuditoriaOds12.class);
    }

    // Stored Procedures
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> spAdminDashboard() {
        // SpAdminDashboard procedure not available for ODS12
        return Map.of(
            "status", "not_implemented",
            "message", "Dashboard procedure not available for ODS12"
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
            "message", "Reporte procedure executed for ODS12"
        );
    }

    // ── Métodos específicos del ODS12 ──
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectosOds12() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoOds12ById(Integer proyectoId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(proyectoId))
                .fetchOptionalInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyectoOds12(Integer proyectoId) {
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
    public Optional<ProyectoIndicadorParametros> findMetaProyectoOds12ById(Integer metaId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
                .where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId))
                .fetchOptionalInto(ProyectoIndicadorParametros.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricasOds12(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.PROYECTO_INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaOds12ById(Integer medicionId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
                .fetchOptionalInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds12> findAllAuditoriasOds12() {
        return dsl.selectFrom(AUDITORIA_ODS12)
                .fetchInto(AuditoriaOds12.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AuditoriaOds12> findAuditoriaOds12ById(Integer auditoriaId) {
        return dsl.selectFrom(AUDITORIA_ODS12)
                .where(AUDITORIA_ODS12.ID.eq(auditoriaId))
                .fetchOptionalInto(AuditoriaOds12.class);
    }
}
