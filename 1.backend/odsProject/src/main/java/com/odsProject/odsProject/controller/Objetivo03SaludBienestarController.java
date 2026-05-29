package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo03SaludBienestarService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo03SaludBienestarController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 3: Salud y Bienestar
 * Expone endpoints REST para los indicadores del ODS3
 * Usa Objetivo03SaludBienestarService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/03")
public class Objetivo03SaludBienestarController implements IObjetivo03SaludBienestarController {


    @Autowired
    private Objetivo03SaludBienestarService objetivo03SaludBienestarService;

    // ── Indicadores Específicos del ODS0    // ── Indicadores Específicos del ODS03 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_1_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_1_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_2_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_2_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_3_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_3_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_3_3(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_3_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_3_4(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_3_4(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_3_5(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_3_5(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_4_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_4_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_5_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_5_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_7_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_7_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_8_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_8_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_8_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_8_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_9_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_9_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_9_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_9_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_9_3(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_9_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_b_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_b_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_b_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_b_3(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_b_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_c_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_c_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_d_1(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_d_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_3_d_2(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getIndicador_3_d_2(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds03(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.findAllIndicadoresByProyectoOds03(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo03SaludBienestarService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo03SaludBienestarService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo03SaludBienestarService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo03SaludBienestarService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo03SaludBienestarService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo03SaludBienestarService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo03SaludBienestarService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo03SaludBienestarService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo03SaludBienestarService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo03SaludBienestarService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo03SaludBienestarService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo03SaludBienestarService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo03SaludBienestarService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo03SaludBienestarService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo03SaludBienestarService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo03SaludBienestarService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo03SaludBienestarService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo03SaludBienestarService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo03SaludBienestarService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getDashboard() { return ResponseEntity.ok(objetivo03SaludBienestarService.getDashboardData()); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo03SaludBienestarService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo03SaludBienestarService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo03SaludBienestarService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo03SaludBienestarService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo03SaludBienestarService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo03SaludBienestarService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo03SaludBienestarService.existsMedicionHistorica(medicionId)); }

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 2/5: Endpoints de medición auditada
    // ─────────────────────────────────────────────────────────────────────

    @Override
    public ResponseEntity<java.util.Map<String, Object>> createMedicionAuditada(java.util.Map<String, Object> payload) {
        try {
            return ResponseEntity.ok(objetivo03SaludBienestarService.saveMedicionAuditada(payload));
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
            return ResponseEntity.ok(objetivo03SaludBienestarService.getMedicionAuditoria(medicionId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                .body(java.util.Map.of("error", e.getMessage()));
        }
    }

}