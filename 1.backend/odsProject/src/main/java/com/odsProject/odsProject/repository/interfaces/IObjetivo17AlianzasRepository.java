package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.AuditoriaOds17;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 17: Alianzas para Lograr los Objetivos
 */
public interface IObjetivo17AlianzasRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds17              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_10_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_11_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_12_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_13_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_14_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_15_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_16_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_17_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_18_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_18_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_18_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_19_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_17_19_2(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds17();
    Optional<Proyectos> findProyectoOds17ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds17(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds17ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds17(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds17ById(Integer medicionId);
    List<AuditoriaOds17> findAllAuditoriasOds17();
    Optional<AuditoriaOds17> findAuditoriaOds17ById(Integer auditoriaId);

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
