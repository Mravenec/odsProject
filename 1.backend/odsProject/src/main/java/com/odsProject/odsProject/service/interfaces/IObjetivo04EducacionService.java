package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.AuditoriaOds04;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 4: Educación de Calidad
 */
public interface IObjetivo04EducacionService extends IOdsBaseService<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds04              // A
> {
    
    List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId);
    
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> getIndicador_4_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds04(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> getAllProjectsOds04();
    Optional<Proyectos> getProjectOds04ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> getAllMetasProyectoOds04(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> getMetaProyectoOds04ById(Integer metaId);
    List<MedicionesHistoricas> getAllMedicionesHistoricasOds04(Integer indicadorId);
    Optional<MedicionesHistoricas> getMedicionHistoricaOds04ById(Integer medicionId);
    
    Double calculateProjectProgress(Integer proyectoId);
    Map<String, Object> getOds04Statistics();
}
