package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.AuditoriaOds15;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo15VidaEcosistemasService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo15VidaEcosistemasController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 15: Vida de Ecosistemas Terrestres
 * Expone endpoints REST para los indicadores del ODS15
 * Usa Objetivo15VidaEcosistemasService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/15")
public class Objetivo15VidaEcosistemasController implements IObjetivo15VidaEcosistemasController {

    @Autowired
    private Objetivo15VidaEcosistemasService objetivo15VidaEcosistemasService;

    // ── Indicadores Específicos del ODS15 ──

    // ── Indicadores Específicos del ODS15 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_1_2(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_1_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_4_2(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_4_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_8_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_8_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_9_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_9_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_b_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_15_c_1(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getIndicador_15_c_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds15(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.findAllIndicadoresByProyectoOds15(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo15VidaEcosistemasService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo15VidaEcosistemasService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo15VidaEcosistemasService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo15VidaEcosistemasService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo15VidaEcosistemasService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo15VidaEcosistemasService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo15VidaEcosistemasService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo15VidaEcosistemasService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo15VidaEcosistemasService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo15VidaEcosistemasService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo15VidaEcosistemasService.existsMedicionHistorica(medicionId)); }
}
