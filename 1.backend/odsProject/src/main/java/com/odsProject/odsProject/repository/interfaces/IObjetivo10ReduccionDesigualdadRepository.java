package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.AuditoriaOds10;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 10: Reducción de las Desigualdades
 */
public interface IObjetivo10ReduccionDesigualdadRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds10              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_7_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_7_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_10_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds10();
    Optional<Proyectos> findProyectoOds10ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds10(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds10ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds10(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds10ById(Integer medicionId);
    List<AuditoriaOds10> findAllAuditoriasOds10();
    Optional<AuditoriaOds10> findAuditoriaOds10ById(Integer auditoriaId);

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
