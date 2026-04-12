package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.AuditoriaOds14;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 14: Vida Submarina
 */
public interface IObjetivo14VidaSubmarinaRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds14              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_14_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds14(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds14();
    Optional<Proyectos> findProyectoOds14ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds14(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds14ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds14(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds14ById(Integer medicionId);
    List<AuditoriaOds14> findAllAuditoriasOds14();
    Optional<AuditoriaOds14> findAuditoriaOds14ById(Integer auditoriaId);

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
