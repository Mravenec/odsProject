package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.AuditoriaOds05;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 5: Igualdad de Género
 */
public interface IObjetivo05GeneroRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds05              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_3_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_5_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_6_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_a_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_5_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds05(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds05();
    Optional<Proyectos> findProyectoOds05ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds05(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds05ById(Integer metaId);
    List<MedicionesHistoricas> findAllMetasMedicionesHistoricasOds05(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds05ById(Integer medicionId);
    List<AuditoriaOds05> findAllAuditoriasOds05();
    Optional<AuditoriaOds05> findAuditoriaOds05ById(Integer auditoriaId);

    void deleteIndicador(Integer indicadorId);
    ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta);
    void deleteMetaProyecto(Integer metaId);
    MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion);
    void deleteMedicionHistorica(Integer medicionId);
    
    Boolean existsIndicador(Integer indicadorId);
    Boolean existsProyecto(Integer proyectoId);
    Boolean existsMetaProyecto(Integer metaId);
    Boolean existsMedicionHistorica(Integer medicionId);

    // Compatibilidad
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds05(Integer indicadorId);
}
