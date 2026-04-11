package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.AuditoriaOds07;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 7: Energía Asequible y No Contaminante
 */
public interface IObjetivo07EnergiaRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds07              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_7_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds07();
    Optional<Proyectos> findProyectoOds07ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds07(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds07ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds07(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds07ById(Integer medicionId);
    List<AuditoriaOds07> findAllAuditoriasOds07();
    Optional<AuditoriaOds07> findAuditoriaOds07ById(Integer auditoriaId);

    void deleteIndicador(Integer indicadorId);
    ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta);
    void deleteMetaProyecto(Integer metaId);
    MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion);
    void deleteMedicionHistorica(Integer medicionId);
    
    Boolean existsIndicador(Integer indicadorId);
    Boolean existsProyecto(Integer proyectoId);
    Boolean existsMetaProyecto(Integer metaId);
    Boolean existsMedicionHistorica(Integer medicionId);
}
