package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.AuditoriaOds07;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 7: Energía Asequible y No Contaminante
 */
public interface IObjetivo07EnergiaService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds07              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_7_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds07();
    Optional<Proyectos> getProjectOds07ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds07(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds07ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds07(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds07ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds07Statistics();
}
