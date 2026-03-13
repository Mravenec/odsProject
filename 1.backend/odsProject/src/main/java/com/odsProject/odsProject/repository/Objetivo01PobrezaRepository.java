package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;
import com.odsProject.odsProject.database.jooq.ods01.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.database.jooq.ods01.routines.SpAdminDashboard;
import com.odsProject.odsProject.repository.interfaces.IObjetivo01PobrezaRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods01.tables.Indicadores.INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods01.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods01.tables.MetasProyecto.METAS_PROYECTO;
import static com.odsProject.odsProject.database.jooq.ods01.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods01.tables.AuditoriaOds01.AUDITORIA_ODS01;

/**
 * Implementación del Repositorio para el Objetivo 1: Fin de la Pobreza
 * Implementa los métodos para acceder a los indicadores del ODS1 usando jOOQ
 * Usa datasource ods01 y sus propios stored procedures
 */
@Repository
public class Objetivo01PobrezaRepository implements IObjetivo01PobrezaRepository {

    @Autowired
    @Qualifier("ods01DslContext")
    private DSLContext dsl;

    // ── Indicadores Específicos del ODS01 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_1_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.1.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_2_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.2.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_2_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.2.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_3_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.3.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_4_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.4.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_4_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.4.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_5_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.5.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_5_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.5.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_5_3(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.5.3"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_5_4(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.5.4"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_a_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.a.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_a_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.a.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_1_b_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("1.b.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    // ── Métodos Agregados (usando stored procedures del ODS01) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId) {
        // Usar el stored procedure sp_admin_reporte_proyecto del ODS01
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
    public List<AuditoriaOds01> findAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(AUDITORIA_ODS01)
                .where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS01.FECHA_CAMBIO, dias)
                .fetchInto(AuditoriaOds01.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds01> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) {
        return dsl.selectFrom(AUDITORIA_ODS01)
                .where(AUDITORIA_ODS01.TABLA_AFECTADA.eq(tablaAfectada))
                .and(AUDITORIA_ODS01.REGISTRO_ID.eq(registroId))
                .fetchInto(AuditoriaOds01.class);
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
            "message", "Dashboard procedure executed for ODS01"
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
            "message", "Reporte procedure executed for ODS01"
        );
    }

    // ── Métodos específicos del ODS01 ──
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectosOds01() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoOds01ById(Integer proyectoId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(proyectoId))
                .fetchOptionalInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MetasProyecto> findAllMetasProyectoOds01(Integer proyectoId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.PROYECTO_ID.eq(proyectoId))
                .fetchInto(MetasProyecto.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoOds01ById(Integer metaId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.ID.eq(metaId))
                .fetchOptionalInto(MetasProyecto.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricasOds01(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaOds01ById(Integer medicionId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
                .fetchOptionalInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds01> findAllAuditoriasOds01() {
        return dsl.selectFrom(AUDITORIA_ODS01)
                .fetchInto(AuditoriaOds01.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AuditoriaOds01> findAuditoriaOds01ById(Integer auditoriaId) {
        return dsl.selectFrom(AUDITORIA_ODS01)
                .where(AUDITORIA_ODS01.ID.eq(auditoriaId))
                .fetchOptionalInto(AuditoriaOds01.class);
    }
}
