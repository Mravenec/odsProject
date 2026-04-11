package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.AuditoriaOds15;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 15: Vida de Ecosistemas Terrestres
 */
public interface IObjetivo15VidaEcosistemasService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds15              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_15_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds15(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds15();
    Optional<Proyectos> getProjectOds15ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds15(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds15ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds15(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds15ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds15Statistics();
}
