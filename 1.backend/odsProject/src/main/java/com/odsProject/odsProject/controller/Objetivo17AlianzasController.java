package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.AuditoriaOds17;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo17AlianzasService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo17AlianzasController;
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
 * Controlador REST para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Expone endpoints REST para los indicadores del ODS17
 * Usa Objetivo17AlianzasService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/17")
public class Objetivo17AlianzasController implements IObjetivo17AlianzasController {

    private static final Logger log = LoggerFactory.getLogger(Objetivo17AlianzasController.class);

    @Autowired
    private Objetivo17AlianzasService objetivo17AlianzasService;

    // ── Indicadores Específicos del ODS17 ──

    // ── Indicadores Específicos del ODS17 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_1_2(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_1_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_3_2(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_3_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_8_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_8_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_9_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_9_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_10_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_10_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_11_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_11_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_12_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_12_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_13_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_13_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_14_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_14_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_15_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_15_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_16_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_16_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_17_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_17_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_18_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_18_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_18_2(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_18_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_18_3(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_18_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_19_1(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_19_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_19_2(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getIndicador_17_19_2(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds17(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.findAllIndicadoresByProyectoOds17(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo17AlianzasService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo17AlianzasService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo17AlianzasService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo17AlianzasService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo17AlianzasService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo17AlianzasService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo17AlianzasService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override
    public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) {
        try {
            return ResponseEntity.ok(objetivo17AlianzasService.saveIndicador(indicador));
        } catch (Exception e) {
            log.error("createIndicador error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo17AlianzasService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo17AlianzasService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo17AlianzasService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override
    public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) {
        try {
            return ResponseEntity.ok(objetivo17AlianzasService.saveMetaProyecto(meta));
        } catch (Exception e) {
            log.error("createMetaProyecto error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo17AlianzasService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo17AlianzasService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo17AlianzasService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo17AlianzasService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo17AlianzasService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo17AlianzasService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo17AlianzasService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getDashboard() { return ResponseEntity.ok(objetivo17AlianzasService.getDashboardData()); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo17AlianzasService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo17AlianzasService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo17AlianzasService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo17AlianzasService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo17AlianzasService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo17AlianzasService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo17AlianzasService.existsMedicionHistorica(medicionId)); }
}
