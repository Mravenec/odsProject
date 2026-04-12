package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo16PazJusticiaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo16PazJusticiaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 * Expone endpoints REST para los indicadores del ODS16
 * Usa Objetivo16PazJusticiaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/16")
public class Objetivo16PazJusticiaController implements IObjetivo16PazJusticiaController {

    @Autowired
    private Objetivo16PazJusticiaService objetivo16PazJusticiaService;

    // ── Indicadores Específicos del ODS16 ──

    // ── Indicadores Específicos del ODS16 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_1_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_1_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_1_3(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_1_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_1_4(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_1_4(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_2_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_2_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_2_3(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_2_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_3_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_3_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_3_3(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_3_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_4_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_4_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_5_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_5_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_6_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_6_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_7_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_7_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_8_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_8_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_9_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_9_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_10_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_10_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_10_2(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_10_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_16_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getIndicador_16_b_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds16(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.findAllIndicadoresByProyectoOds16(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo16PazJusticiaService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo16PazJusticiaService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo16PazJusticiaService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo16PazJusticiaService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo16PazJusticiaService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo16PazJusticiaService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo16PazJusticiaService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo16PazJusticiaService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo16PazJusticiaService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo16PazJusticiaService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.getAllMetasProyectoOds16(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo16PazJusticiaService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo16PazJusticiaService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo16PazJusticiaService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo16PazJusticiaService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo16PazJusticiaService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo16PazJusticiaService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo16PazJusticiaService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo16PazJusticiaService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo16PazJusticiaService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getDashboard() { return ResponseEntity.ok(objetivo16PazJusticiaService.getDashboardData()); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo16PazJusticiaService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo16PazJusticiaService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo16PazJusticiaService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo16PazJusticiaService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo16PazJusticiaService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo16PazJusticiaService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo16PazJusticiaService.existsMedicionHistorica(medicionId)); }
}
