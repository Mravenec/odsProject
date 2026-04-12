package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.AuditoriaOds05;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 5: Igualdad de Género
 */
public interface IObjetivo05GeneroService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds05              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_a_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_5_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds05(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds05();
    Optional<Proyectos> getProjectOds05ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds05(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds05ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds05(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds05ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds05Statistics();
}
