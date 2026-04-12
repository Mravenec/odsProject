package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.AuditoriaOds09;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 9: Industria, Innovación e Infraestructura
 */
public interface IObjetivo09InfraestructuraService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds09              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_9_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds09(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds09();
    Optional<Proyectos> getProjectOds09ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds09(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds09ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds09(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds09ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds09Statistics();
}
