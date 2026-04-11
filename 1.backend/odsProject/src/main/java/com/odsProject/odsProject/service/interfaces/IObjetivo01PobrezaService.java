package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 1: Fin de la Pobreza
 */
public interface IObjetivo01PobrezaService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds01              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_a_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_1_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds01();
    Optional<Proyectos> getProjectOds01ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds01(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds01ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds01(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds01ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    java.util.Map<String, Object> getOds01Statistics();
}
