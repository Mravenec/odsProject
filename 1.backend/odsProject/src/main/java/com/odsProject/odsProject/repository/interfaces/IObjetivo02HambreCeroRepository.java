package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.AuditoriaOds02;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 2: Hambre Cero
 */
public interface IObjetivo02HambreCeroRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds02              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_2_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_2_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_a_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_2_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds02(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds02();
    Optional<Proyectos> findProyectoOds02ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds02(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds02ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds02(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds02ById(Integer medicionId);
    List<AuditoriaOds02> findAllAuditoriasOds02();
    Optional<AuditoriaOds02> findAuditoriaOds02ById(Integer auditoriaId);

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
