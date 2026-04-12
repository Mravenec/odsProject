package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.AuditoriaOds04;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo04EducacionService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo04EducacionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 4: Educación de Calidad
 * Expone endpoints REST para los indicadores del ODS4
 * Usa Objetivo04EducacionService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/04")
public class Objetivo04EducacionController implements IObjetivo04EducacionController {

    @Autowired
    private Objetivo04EducacionService objetivo04EducacionService;

    // ── Indicadores Específicos del ODS    // ── Indicadores Específicos del ODS04 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_1_2(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_1_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_2_2(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_2_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_b_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_4_c_1(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getIndicador_4_c_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds04(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.findAllIndicadoresByProyectoOds04(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo04EducacionService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo04EducacionService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo04EducacionService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo04EducacionService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo04EducacionService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo04EducacionService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo04EducacionService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo04EducacionService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo04EducacionService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo04EducacionService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo04EducacionService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo04EducacionService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo04EducacionService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo04EducacionService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo04EducacionService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo04EducacionService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo04EducacionService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo04EducacionService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo04EducacionService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo04EducacionService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo04EducacionService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo04EducacionService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo04EducacionService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo04EducacionService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo04EducacionService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo04EducacionService.existsMedicionHistorica(medicionId)); }
}
