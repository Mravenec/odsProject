package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.AuditoriaOds02;
import com.odsProject.odsProject.database.jooq.ods02.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.database.jooq.ods02.routines.SpAdminDashboard;
import com.odsProject.odsProject.repository.interfaces.IObjetivo02HambreCeroRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods02.tables.Indicadores.INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods02.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods02.tables.MetasProyecto.METAS_PROYECTO;
import static com.odsProject.odsProject.database.jooq.ods02.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods02.tables.AuditoriaOds02.AUDITORIA_ODS02;

/**
 * Implementación del Repositorio para el Objetivo 2: Hambre Cero
 * Implementa los métodos para acceder a los indicadores del ODS2 usando jOOQ
 * Usa datasource ods02 y sus propios stored procedures
 */
@Repository
public class Objetivo02HambreCeroRepository implements IObjetivo02HambreCeroRepository {

    @Autowired
    @Qualifier("ods02DslContext")
    private DSLContext dsl;

    // ── Indicadores Específicos del ODS02 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_1_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.1.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_1_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.1.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_2_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.2.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_2_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.2.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_2_3(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.2.3"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_2_4(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.2.4"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_3_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.3.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_3_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.3.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_4_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.4.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_5_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.5.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_5_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.5.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_a_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.a.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_a_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.a.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_b_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.b.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_2_c_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("2.c.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    // ── Métodos Agregados (usando stored procedures del ODS02) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds02(Integer proyectoId) {
        // Usar el stored procedure sp_admin_reporte_proyecto del ODS02
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // También hacer query directa como fallback
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .fetchInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        // Usar el stored procedure sp_admin_reporte_proyecto y filtrar por meta
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // Filtrar por metaPrefix
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.like(metaPrefix + ".%"))
                .fetchInto(Indicadores.class);
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
    public List<Indicadores> findIndicadoresByProyecto(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .fetchInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores saveIndicador(Indicadores indicador) {
        return dsl.insertInto(INDICADORES)
                .set(dsl.newRecord(INDICADORES, indicador))
                .returning()
                .fetchOneInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
        return dsl.update(INDICADORES)
                .set(dsl.newRecord(INDICADORES, indicador))
                .where(INDICADORES.ID.eq(indicador.getId()))
                .returning()
                .fetchOneInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicadorByCodigo(Integer proyectoId, String codigo) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq(codigo))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByCodigoPrefix(String prefix) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.INDICADOR_CODIGO.like(prefix + "%"))
                .fetchInto(Indicadores.class);
    }

    // Metas
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MetasProyecto> findMetasByProyecto(Integer proyectoId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.PROYECTO_ID.eq(proyectoId))
                .fetchInto(MetasProyecto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return dsl.insertInto(METAS_PROYECTO)
                .set(dsl.newRecord(METAS_PROYECTO, meta))
                .returning()
                .fetchOneInto(MetasProyecto.class);
    }

    // Mediciones
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findMedicionesByIndicador(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.INDICADOR_ID.eq(indicadorId))
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
    public List<AuditoriaOds02> findAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(AUDITORIA_ODS02)
                .where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS02.FECHA_CAMBIO, dias)
                .fetchInto(AuditoriaOds02.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds02> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) {
        return dsl.selectFrom(AUDITORIA_ODS02)
                .where(AUDITORIA_ODS02.TABLA_AFECTADA.eq(tablaAfectada))
                .and(AUDITORIA_ODS02.REGISTRO_ID.eq(registroId))
                .fetchInto(AuditoriaOds02.class);
    }

    // Stored Procedures
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> spAdminDashboard() {
        SpAdminDashboard sp = new SpAdminDashboard();
        sp.execute(dsl.configuration());
        
        return Map.of(
            "status", "executed",
            "message", "Dashboard procedure executed for ODS02"
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
            "message", "Reporte procedure executed for ODS02"
        );
    }

    // ── Métodos específicos del ODS02 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectosOds02() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoOds02ById(Integer proyectoId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(proyectoId))
                .fetchOptionalInto(Proyectos.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MetasProyecto> findAllMetasProyectoOds02(Integer proyectoId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.PROYECTO_ID.eq(proyectoId))
                .fetchInto(MetasProyecto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoOds02ById(Integer metaId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.ID.eq(metaId))
                .fetchOptionalInto(MetasProyecto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricasOds02(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaOds02ById(Integer medicionId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
                .fetchOptionalInto(MedicionesHistoricas.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds02> findAllAuditoriasOds02() {
        return dsl.selectFrom(AUDITORIA_ODS02)
                .fetchInto(AuditoriaOds02.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AuditoriaOds02> findAuditoriaOds02ById(Integer auditoriaId) {
        return dsl.selectFrom(AUDITORIA_ODS02)
                .where(AUDITORIA_ODS02.ID.eq(auditoriaId))
                .fetchOptionalInto(AuditoriaOds02.class);
    }
}
