package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.AuditoriaOds02;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 2: Hambre Cero
 */
public interface IObjetivo02HambreCeroService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds02              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_a_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_2_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds02(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds02();
    Optional<Proyectos> getProjectOds02ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds02(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds02ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds02(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds02ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    java.util.Map<String, Object> getOds02Statistics();
}
