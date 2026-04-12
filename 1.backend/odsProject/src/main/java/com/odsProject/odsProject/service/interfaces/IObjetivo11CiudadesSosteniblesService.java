package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 */
public interface IObjetivo11CiudadesSosteniblesService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds11              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_5_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_b_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_11_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds11();
    Optional<Proyectos> getProjectOds11ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds11(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds11ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds11(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds11ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds11Statistics();
}
