package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 1: Fin de la Pobreza
 */
public interface IObjetivo01PobrezaRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds01              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_5_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_5_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_a_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_1_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds01();
    Optional<Proyectos> findProyectoOds01ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds01(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds01ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds01(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds01ById(Integer medicionId);
    List<AuditoriaOds01> findAllAuditoriasOds01();
    Optional<AuditoriaOds01> findAuditoriaOds01ById(Integer auditoriaId);

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
