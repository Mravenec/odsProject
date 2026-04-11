package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.AuditoriaOds03;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 3: Salud y Bienestar
 */
public interface IObjetivo03SaludBienestarRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds03              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_3_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_3_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_3_5(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_8_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_9_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_9_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_b_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_b_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_c_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_d_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_3_d_2(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds03();
    Optional<Proyectos> findProyectoOds03ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds03(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds03ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds03(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds03ById(Integer medicionId);
    List<AuditoriaOds03> findAllAuditoriasOds03();
    Optional<AuditoriaOds03> findAuditoriaOds03ById(Integer auditoriaId);

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
