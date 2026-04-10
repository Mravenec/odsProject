package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.AuditoriaOds07;
import com.odsProject.odsProject.database.jooq.ods07.routines.SpAdminReporteProyecto;
// SpAdminDashboard not present in ODS07 routines
import com.odsProject.odsProject.repository.interfaces.IObjetivo07EnergiaRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods07.tables.ProyectoIndicadores.PROYECTO_INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods07.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods07.tables.ProyectoIndicadorParametros.PROYECTO_INDICADOR_PARAMETROS;
import static com.odsProject.odsProject.database.jooq.ods07.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods07.tables.AuditoriaOds07.AUDITORIA_ODS07;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;

/**
 * Implementación del Repositorio para el Objetivo 7: Energía Asequible y No Contaminante
 * Implementa los métodos para acceder a los indicadores del ODS7 usando jOOQ
 * Usa datasource ods07 y sus propios stored procedures
 */
@Repository
public class Objetivo07EnergiaRepository implements IObjetivo07EnergiaRepository {

    @Autowired
    @Qualifier("dslOds07")
    private DSLContext dsl;

    // ── Métodos específicos de indicadores del ODS07 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_1_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.1.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_1_2(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.1.2"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_2_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.2.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_3_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.3.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_a_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.a.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_b_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.b.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicador_7_c_1(Integer proyectoId) {
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.eq("7.c.1"))
                .fetchOptionalInto(ProyectoIndicadores.class);
    }

    // ── Métodos Agregados (usando stored procedures del ODS07) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId) {
        // Usar el stored procedure sp_admin_reporte_proyecto del ODS07
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // Filtrar por ODS 07 usando JOIN con INDICADOR_MASTER
        return dsl.select(PROYECTO_INDICADORES.fields())
                .from(PROYECTO_INDICADORES)
                .join(INDICADOR_MASTER).on(PROYECTO_INDICADORES.INDICADOR_MASTER_ID.eq(INDICADOR_MASTER.ID))
                .where(PROYECTO_INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADOR_MASTER.CODIGO.like("7.%"))
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
    public List<AuditoriaOds07> findAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(AUDITORIA_ODS07)
                .where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS07.FECHA_CAMBIO, dias)
                .fetchInto(AuditoriaOds07.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds07> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) {
        return dsl.selectFrom(AUDITORIA_ODS07)
                .where(AUDITORIA_ODS07.TABLA_AFECTADA.eq(tablaAfectada))
                .and(AUDITORIA_ODS07.REGISTRO_ID.eq(registroId))
                .fetchInto(AuditoriaOds07.class);
    }

    // Stored Procedures
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> spAdminDashboard() {
        // SpAdminDashboard procedure not available for ODS07
        return Map.of(
            "status", "not_implemented",
            "message", "Dashboard procedure not available for ODS07"
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
            "message", "Reporte procedure executed for ODS07"
        );
    }

    // ── Métodos específicos del ODS07 ──
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectosOds07() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoOds07ById(Integer proyectoId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(proyectoId))
                .fetchOptionalInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyectoOds07(Integer proyectoId) {
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
    public Optional<ProyectoIndicadorParametros> findMetaProyectoOds07ById(Integer metaId) {
        return dsl.selectFrom(PROYECTO_INDICADOR_PARAMETROS)
                .where(PROYECTO_INDICADOR_PARAMETROS.ID.eq(metaId))
                .fetchOptionalInto(ProyectoIndicadorParametros.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricasOds07(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.PROYECTO_INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaOds07ById(Integer medicionId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
                .fetchOptionalInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds07> findAllAuditoriasOds07() {
        return dsl.selectFrom(AUDITORIA_ODS07)
                .fetchInto(AuditoriaOds07.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AuditoriaOds07> findAuditoriaOds07ById(Integer auditoriaId) {
        return dsl.selectFrom(AUDITORIA_ODS07)
                .where(AUDITORIA_ODS07.ID.eq(auditoriaId))
                .fetchOptionalInto(AuditoriaOds07.class);
    }
}
