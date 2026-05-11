package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.AuditoriaOds12;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo12ConsumoProduccionService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo12ConsumoProduccionController;
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
 * Controlador REST para el Objetivo 12: Producción y Consumo Responsables
 * Expone endpoints REST para los indicadores del ODS12
 * Usa Objetivo12ConsumoProduccionService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/12")
public class Objetivo12ConsumoProduccionController implements IObjetivo12ConsumoProduccionController {

    private static final Logger log = LoggerFactory.getLogger(Objetivo12ConsumoProduccionController.class);

    @Autowired
    private Objetivo12ConsumoProduccionService objetivo12ConsumoProduccionService;

    // ── Indicadores Específicos del ODS12 ──

    // ── Indicadores Específicos del ODS12 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_2_2(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_2_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_4_2(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_4_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_8_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_8_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_b_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_12_c_1(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getIndicador_12_c_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds12(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.findAllIndicadoresByProyectoOds12(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo12ConsumoProduccionService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo12ConsumoProduccionService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo12ConsumoProduccionService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo12ConsumoProduccionService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo12ConsumoProduccionService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo12ConsumoProduccionService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo12ConsumoProduccionService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo12ConsumoProduccionService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo12ConsumoProduccionService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getDashboard() { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getDashboardData()); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo12ConsumoProduccionService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo12ConsumoProduccionService.existsMedicionHistorica(medicionId)); }

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 2/5: Endpoints de medición auditada
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<java.util.Map<String, Object>> createMedicionAuditada(java.util.Map<String, Object> payload) {
        try {
            return ResponseEntity.ok(objetivo12ConsumoProduccionService.saveMedicionAuditada(payload));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
                .body(java.util.Map.of("error", e.getMessage() != null ? e.getMessage() : "error interno"));
        }
    }

    @Override
    public ResponseEntity<java.util.Map<String, Object>> getMedicionAuditoria(Integer medicionId) {
        try {
            return ResponseEntity.ok(objetivo12ConsumoProduccionService.getMedicionAuditoria(medicionId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                .body(java.util.Map.of("error", e.getMessage()));
        }
    }

}
