package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.AuditoriaOds17;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 17: Alianzas para Lograr los Objetivos
 */
public interface IObjetivo17AlianzasService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds17              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_10_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_11_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_12_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_13_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_14_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_15_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_16_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_17_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_18_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_18_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_18_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_19_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_17_19_2(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds17();
    Optional<Proyectos> getProjectOds17ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds17(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds17ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds17(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds17ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds17Statistics();
}
