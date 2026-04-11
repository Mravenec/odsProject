package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 */
public interface IObjetivo16PazJusticiaRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds16              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_1_4(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_2_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_3_3(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_7_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_9_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_10_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_10_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_16_b_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds16();
    Optional<Proyectos> findProyectoOds16ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds16(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds16ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds16(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds16ById(Integer medicionId);
    List<AuditoriaOds16> findAllAuditoriasOds16();
    Optional<AuditoriaOds16> findAuditoriaOds16ById(Integer auditoriaId);

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
