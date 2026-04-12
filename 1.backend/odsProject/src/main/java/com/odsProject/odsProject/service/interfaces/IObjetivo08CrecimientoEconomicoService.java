package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.AuditoriaOds08;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 */
public interface IObjetivo08CrecimientoEconomicoService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds08              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_8_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_9_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_10_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_10_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_8_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds08(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds08();
    Optional<Proyectos> getProjectOds08ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds08(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds08ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds08(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds08ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds08Statistics();
}
