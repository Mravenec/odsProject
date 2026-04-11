package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.AuditoriaOds13;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 13: Acción por el Clima
 */
public interface IObjetivo13AccionClimaRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds13              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_1_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_2_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_13_b_2(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds13(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds13();
    Optional<Proyectos> findProyectoOds13ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds13(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds13ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds13(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds13ById(Integer medicionId);
    List<AuditoriaOds13> findAllAuditoriasOds13();
    Optional<AuditoriaOds13> findAuditoriaOds13ById(Integer auditoriaId);

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
