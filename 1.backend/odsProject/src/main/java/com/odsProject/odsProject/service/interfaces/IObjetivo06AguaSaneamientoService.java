package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.AuditoriaOds06;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 6: Agua Limpia y Saneamiento
 */
public interface IObjetivo06AguaSaneamientoService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds06              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_6_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds06(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds06();
    Optional<Proyectos> getProjectOds06ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds06(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds06ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds06(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds06ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds06Statistics();
}
