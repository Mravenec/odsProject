package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 */
public interface IObjetivo11CiudadesSosteniblesRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds11              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_5_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_b_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_11_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds11();
    Optional<Proyectos> findProyectoOds11ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds11(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds11ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds11(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds11ById(Integer medicionId);
    List<AuditoriaOds11> findAllAuditoriasOds11();
    Optional<AuditoriaOds11> findAuditoriaOds11ById(Integer auditoriaId);

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
