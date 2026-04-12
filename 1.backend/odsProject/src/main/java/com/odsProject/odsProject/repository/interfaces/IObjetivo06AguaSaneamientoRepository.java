package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.AuditoriaOds06;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 6: Agua Limpia y Saneamiento
 */
public interface IObjetivo06AguaSaneamientoRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds06              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_6_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds06(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds06();
    Optional<Proyectos> findProyectoOds06ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds06(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds06ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds06(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds06ById(Integer medicionId);
    List<AuditoriaOds06> findAllAuditoriasOds06();
    Optional<AuditoriaOds06> findAuditoriaOds06ById(Integer auditoriaId);

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
