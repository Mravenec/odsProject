package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo11CiudadesSosteniblesService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo11CiudadesSosteniblesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Expone endpoints REST para los indicadores del ODS11
 * Usa Objetivo11CiudadesSosteniblesService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/11")
public class Objetivo11CiudadesSosteniblesController implements IObjetivo11CiudadesSosteniblesController {

    @Autowired
    private Objetivo11CiudadesSosteniblesService objetivo11CiudadesSosteniblesService;

    // ── Indicadores Específicos del ODS11 ──

    // ── Indicadores Específicos del ODS11 ──
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_1_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_1_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_2_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_2_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_3_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_3_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_3_2(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_3_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_4_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_4_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_5_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_5_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_5_2(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_5_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_5_3(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_5_3(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_6_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_6_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_6_2(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_6_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_7_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_7_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_7_2(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_7_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_a_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_a_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_b_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_b_1(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_b_2(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_b_2(proyectoId)); }
    @Override public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_c_1(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getIndicador_11_c_1(proyectoId)); }


    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds11(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.findAllIndicadoresByProyectoOds11(proyectoId)); }
    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.findIndicadoresByMeta(proyectoId, metaPrefix)); }

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo11CiudadesSosteniblesService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo11CiudadesSosteniblesService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo11CiudadesSosteniblesService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo11CiudadesSosteniblesService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo11CiudadesSosteniblesService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo11CiudadesSosteniblesService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo11CiudadesSosteniblesService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo11CiudadesSosteniblesService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo11CiudadesSosteniblesService.existsMedicionHistorica(medicionId)); }
}
