package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.AuditoriaOds09;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 9: Industria, Innovación e Infraestructura
 */
public interface IObjetivo09InfraestructuraRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds09              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_9_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds09(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds09();
    Optional<Proyectos> findProyectoOds09ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds09(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds09ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds09(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds09ById(Integer medicionId);
    List<AuditoriaOds09> findAllAuditoriasOds09();
    Optional<AuditoriaOds09> findAuditoriaOds09ById(Integer auditoriaId);

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
