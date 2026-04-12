package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.AuditoriaOds10;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 10: Reducción de las Desigualdades
 */
public interface IObjetivo10ReduccionDesigualdadService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds10              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_10_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds10();
    Optional<Proyectos> getProjectOds10ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds10(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds10ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds10(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds10ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds10Statistics();
}
