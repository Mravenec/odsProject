package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.AuditoriaOds12;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 12: Producción y Consumo Responsables
 */
public interface IObjetivo12ConsumoProduccionService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds12              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_12_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds12(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds12();
    Optional<Proyectos> getProjectOds12ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds12(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds12ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds12(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds12ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds12Statistics();
}
