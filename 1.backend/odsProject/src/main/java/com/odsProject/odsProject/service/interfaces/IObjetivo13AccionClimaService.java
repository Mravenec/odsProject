package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.AuditoriaOds13;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 13: Acción por el Clima
 */
public interface IObjetivo13AccionClimaService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds13              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_1_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_2_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_13_b_2(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds13(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds13();
    Optional<Proyectos> getProjectOds13ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds13(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds13ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds13(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds13ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds13Statistics();
}
