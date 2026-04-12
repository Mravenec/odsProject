package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.AuditoriaOds12;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 12: Producción y Consumo Responsables
 */
public interface IObjetivo12ConsumoProduccionRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds12              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_4_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_8_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_12_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds12(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds12();
    Optional<Proyectos> findProyectoOds12ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds12(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds12ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds12(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds12ById(Integer medicionId);
    List<AuditoriaOds12> findAllAuditoriasOds12();
    Optional<AuditoriaOds12> findAuditoriaOds12ById(Integer auditoriaId);

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
