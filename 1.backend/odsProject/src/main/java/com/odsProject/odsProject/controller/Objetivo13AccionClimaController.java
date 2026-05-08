package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.AuditoriaOds13;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo13AccionClimaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo13AccionClimaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 13: Acción por el Clima
 * Expone endpoints REST para los indicadores del ODS13
 * Usa Objetivo13AccionClimaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/13")
public class Objetivo13AccionClimaController implements IObjetivo13AccionClimaController {

    private static final Logger log = LoggerFactory.getLogger(Objetivo13AccionClimaController.class);

    @Autowired
    private Objetivo13AccionClimaService objetivo13AccionClimaService;

    // ── Indicadores Específicos del ODS13 ──

    // ── Indicadores Específicos del ODS13 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_1_2(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_1_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_1_3(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_1_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_2_2(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_2_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getIndicador_13_b_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds13(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.findAllIndicadoresByProyectoOds13(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo13AccionClimaService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo13AccionClimaService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo13AccionClimaService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo13AccionClimaService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo13AccionClimaService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo13AccionClimaService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo13AccionClimaService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override
    public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) {
        try {
            return ResponseEntity.ok(objetivo13AccionClimaService.saveIndicador(indicador));
        } catch (Exception e) {
            log.error("createIndicador error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo13AccionClimaService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo13AccionClimaService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo13AccionClimaService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override
    public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) {
        try {
            return ResponseEntity.ok(objetivo13AccionClimaService.saveMetaProyecto(meta));
        } catch (Exception e) {
            log.error("createMetaProyecto error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo13AccionClimaService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo13AccionClimaService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo13AccionClimaService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo13AccionClimaService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo13AccionClimaService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo13AccionClimaService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo13AccionClimaService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getDashboard() { return ResponseEntity.ok(objetivo13AccionClimaService.getDashboardData()); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo13AccionClimaService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo13AccionClimaService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo13AccionClimaService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo13AccionClimaService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo13AccionClimaService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo13AccionClimaService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo13AccionClimaService.existsMedicionHistorica(medicionId)); }
}
