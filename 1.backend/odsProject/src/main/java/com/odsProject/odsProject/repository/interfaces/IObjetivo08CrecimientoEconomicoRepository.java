package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.AuditoriaOds08;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 */
public interface IObjetivo08CrecimientoEconomicoRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds08              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_8_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_9_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_10_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_10_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_8_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds08(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds08();
    Optional<Proyectos> findProyectoOds08ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds08(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds08ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds08(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds08ById(Integer medicionId);
    List<AuditoriaOds08> findAllAuditoriasOds08();
    Optional<AuditoriaOds08> findAuditoriaOds08ById(Integer auditoriaId);

    void deleteIndicador(Integer indicadorId);
    ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta);
    void deleteMetaProyecto(Integer metaId);
    MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion);
    void deleteMedicionHistorica(Integer medicionId);
    
    Boolean existsIndicador(Integer indicadorId);
    Boolean existsProyecto(Integer proyectoId);
    Boolean existsMetaProyecto(Integer metaId);
    Boolean existsMedicionHistorica(Integer medicionId);
}
