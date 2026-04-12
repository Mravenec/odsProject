package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.AuditoriaOds08;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo08CrecimientoEconomicoService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo08CrecimientoEconomicoController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 * Expone endpoints REST para los indicadores del ODS8
 * Usa Objetivo08CrecimientoEconomicoService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/08")
public class Objetivo08CrecimientoEconomicoController implements IObjetivo08CrecimientoEconomicoController {

    @Autowired
    private Objetivo08CrecimientoEconomicoService objetivo08CrecimientoEconomicoService;

    // ── Indicadores Específicos del ODS    // ── Indicadores Específicos del ODS08 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_4_2(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_4_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_5_2(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_5_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_8_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_8_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_8_2(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_8_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_9_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_9_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_9_2(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_9_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_10_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_10_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_10_2(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_10_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getIndicador_8_b_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds08(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.findAllIndicadoresByProyectoOds08(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo08CrecimientoEconomicoService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo08CrecimientoEconomicoService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo08CrecimientoEconomicoService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo08CrecimientoEconomicoService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo08CrecimientoEconomicoService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo08CrecimientoEconomicoService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo08CrecimientoEconomicoService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo08CrecimientoEconomicoService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo08CrecimientoEconomicoService.existsMedicionHistorica(medicionId)); }
}
