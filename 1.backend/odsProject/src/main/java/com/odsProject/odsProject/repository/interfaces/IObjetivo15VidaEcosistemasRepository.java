package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.AuditoriaOds15;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 15: Vida de Ecosistemas Terrestres
 */
public interface IObjetivo15VidaEcosistemasRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds15              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_15_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds15(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds15();
    Optional<Proyectos> findProyectoOds15ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds15(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds15ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds15(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds15ById(Integer medicionId);
    List<AuditoriaOds15> findAllAuditoriasOds15();
    Optional<AuditoriaOds15> findAuditoriaOds15ById(Integer auditoriaId);

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
