package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.AuditoriaOds14;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 14: Vida Submarina
 */
public interface IObjetivo14VidaSubmarinaService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds14              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_14_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds14(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds14();
    Optional<Proyectos> getProjectOds14ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds14(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds14ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds14(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds14ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds14Statistics();
}
