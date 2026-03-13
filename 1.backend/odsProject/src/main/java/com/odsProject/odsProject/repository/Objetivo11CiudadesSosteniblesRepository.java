package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;
import com.odsProject.odsProject.database.jooq.ods11.routines.SpAdminReporteProyecto;
import com.odsProject.odsProject.database.jooq.ods11.routines.SpAdminDashboard;
import com.odsProject.odsProject.repository.interfaces.IObjetivo11CiudadesSosteniblesRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods11.tables.Indicadores.INDICADORES;
import static com.odsProject.odsProject.database.jooq.ods11.tables.Proyectos.PROYECTOS;
import static com.odsProject.odsProject.database.jooq.ods11.tables.MetasProyecto.METAS_PROYECTO;
import static com.odsProject.odsProject.database.jooq.ods11.tables.MedicionesHistoricas.MEDICIONES_HISTORICAS;
import static com.odsProject.odsProject.database.jooq.ods11.tables.AuditoriaOds11.AUDITORIA_ODS11;

/**
 * Implementación del Repositorio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Implementa los métodos para acceder a los indicadores del ODS11 usando jOOQ
 * Usa datasource ods11 y sus propios stored procedures
 */
@Repository
public class Objetivo11CiudadesSosteniblesRepository implements IObjetivo11CiudadesSosteniblesRepository {

    @Autowired
    @Qualifier("dslOds11")
    private DSLContext dsl;

    // ── Métodos específicos de indicadores del ODS11 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_1_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.1.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_2_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.2.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_3_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.3.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_3_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.3.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_4_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.4.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_5_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.5.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_5_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.5.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_5_3(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.5.3"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_6_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.6.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_6_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.6.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_7_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.7.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_7_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.7.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_a_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.a.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_b_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.b.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_b_2(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.b.2"))
                .fetchOptionalInto(Indicadores.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicador_11_c_1(Integer proyectoId) {
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.eq("11.c.1"))
                .fetchOptionalInto(Indicadores.class);
    }

    // ── Métodos Agregados (usando stored procedures del ODS11) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId) {
        // Usar el stored procedure sp_admin_reporte_proyecto del ODS11
        SpAdminReporteProyecto sp = new SpAdminReporteProyecto();
        sp.setProyectoIdParam(proyectoId);
        sp.execute(dsl.configuration());
        
        // Filtrar por metaPrefix
        return dsl.selectFrom(INDICADORES)
                .where(INDICADORES.PROYECTO_ID.eq(proyectoId))
                .and(INDICADORES.INDICADOR_CODIGO.like("11.%"))
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
    public List<AuditoriaOds11> findAuditoriaReciente(Integer dias) {
        return dsl.selectFrom(AUDITORIA_ODS11)
                .where("{0} >= DATE_SUB(NOW(), INTERVAL ? DAY)", AUDITORIA_ODS11.FECHA_CAMBIO, dias)
                .fetchInto(AuditoriaOds11.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds11> findAuditoriaByRegistro(String tablaAfectada, Integer registroId) {
        return dsl.selectFrom(AUDITORIA_ODS11)
                .where(AUDITORIA_ODS11.TABLA_AFECTADA.eq(tablaAfectada))
                .and(AUDITORIA_ODS11.REGISTRO_ID.eq(registroId))
                .fetchInto(AuditoriaOds11.class);
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
            "message", "Dashboard procedure executed for ODS11"
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
            "message", "Reporte procedure executed for ODS11"
        );
    }

    // ── Métodos específicos del ODS11 ──
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectosOds11() {
        return dsl.selectFrom(PROYECTOS)
                .fetchInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoOds11ById(Integer proyectoId) {
        return dsl.selectFrom(PROYECTOS)
                .where(PROYECTOS.ID.eq(proyectoId))
                .fetchOptionalInto(Proyectos.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MetasProyecto> findAllMetasProyectoOds11(Integer proyectoId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.PROYECTO_ID.eq(proyectoId))
                .fetchInto(MetasProyecto.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoOds11ById(Integer metaId) {
        return dsl.selectFrom(METAS_PROYECTO)
                .where(METAS_PROYECTO.ID.eq(metaId))
                .fetchOptionalInto(MetasProyecto.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricasOds11(Integer indicadorId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.INDICADOR_ID.eq(indicadorId))
                .fetchInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaOds11ById(Integer medicionId) {
        return dsl.selectFrom(MEDICIONES_HISTORICAS)
                .where(MEDICIONES_HISTORICAS.ID.eq(medicionId))
                .fetchOptionalInto(MedicionesHistoricas.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<AuditoriaOds11> findAllAuditoriasOds11() {
        return dsl.selectFrom(AUDITORIA_ODS11)
                .fetchInto(AuditoriaOds11.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<AuditoriaOds11> findAuditoriaOds11ById(Integer auditoriaId) {
        return dsl.selectFrom(AUDITORIA_ODS11)
                .where(AUDITORIA_ODS11.ID.eq(auditoriaId))
                .fetchOptionalInto(AuditoriaOds11.class);
    }
}
