package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.AuditoriaOds04;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 4: Educación de Calidad
 */
public interface IObjetivo04EducacionRepository extends IOdsBaseRepository<
    VistaAdminDetalleIndicadores, // T (Lectura)
    ProyectoIndicadores,         // E (Escritura)
    Proyectos,                   // P
    ProyectoIndicadorParametros, // M
    MedicionesHistoricas,        // MH
    AuditoriaOds04              // A
> {
    
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_1_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_1_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_2_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_2_2(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_3_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_4_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_5_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_6_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_7_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_a_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_b_1(Integer proyectoId);
    Optional<VistaAdminDetalleIndicadores> findIndicador_4_c_1(Integer proyectoId);
    
    List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds04(Integer proyectoId);
    List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    List<Proyectos> findAllProyectosOds04();
    Optional<Proyectos> findProyectoOds04ById(Integer proyectoId);
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds04(Integer proyectoId);
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds04ById(Integer metaId);
    List<MedicionesHistoricas> findAllMetasEducacionOds04(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds04ById(Integer medicionId);
    List<AuditoriaOds04> findAllAuditoriasOds04();
    Optional<AuditoriaOds04> findAuditoriaOds04ById(Integer auditoriaId);

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
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds04(Integer indicadorId);
}
