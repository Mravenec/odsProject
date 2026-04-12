package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.AuditoriaOds03;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 3: Salud y Bienestar
 */
public interface IObjetivo03SaludBienestarService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds03              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_5(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_8_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_9_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_9_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_b_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_b_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_c_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_d_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_3_d_2(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds03();
    Optional<Proyectos> getProjectOds03ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds03(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds03ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds03(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds03ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds03Statistics();
}
