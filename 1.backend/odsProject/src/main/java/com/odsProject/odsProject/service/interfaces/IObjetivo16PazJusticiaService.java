package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 */
public interface IObjetivo16PazJusticiaService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds16              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_10_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_10_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_16_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds16();
    Optional<Proyectos> getProjectOds16ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds16(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds16ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds16(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds16ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds16Statistics();
}
