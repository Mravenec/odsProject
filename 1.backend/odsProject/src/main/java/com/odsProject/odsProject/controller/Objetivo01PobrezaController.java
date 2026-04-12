package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;
import com.odsProject.odsProject.service.Objetivo01PobrezaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo01PobrezaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 1: Fin de la Pobreza
 * Expone endpoints REST para los indicadores del ODS1
 * Usa Objetivo01PobrezaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/01")
public class Objetivo01PobrezaController implements IObjetivo01PobrezaController {

    @Autowired
    private Objetivo01PobrezaService objetivo01PobrezaService;

    // ── Indicadores Específicos del ODS01 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.1.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_1_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.2.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_2_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.2.2")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_2_2(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds01(@RequestParam Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.findAllIndicadoresByProyectoOds01(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.3.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_3_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.4.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_4_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.4.2")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_4_2(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_4_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.5.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.5.2")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_2(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.5.3")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_3(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.5.4")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_4(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_4(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.a.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_a_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.a.2")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_a_2(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_a_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/indicadores/1.b.1")
    public ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_b_1(@RequestParam Integer proyectoId) {
        Optional<VistaAdminDetalleIndicadores> result = objetivo01PobrezaService.getIndicador_1_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene todos los proyectos del ODS01
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS01
     */
    public ResponseEntity<List<Proyectos>> getAllProjectsOds01() {
        List<Proyectos> result = objetivo01PobrezaService.getAllProjectsOds01();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS01 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    public ResponseEntity<Proyectos> getProjectOds01ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo01PobrezaService.getProjectOds01ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS01
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS01
     */
    public ResponseEntity<List<ProyectoIndicadorParametros>> getAllMetasProyectoOds01(@PathVariable Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo01PobrezaService.getAllMetasProyectoOds01(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS01
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS01
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds01(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo01PobrezaService.getAllMedicionesHistoricasOds01(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS01
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo01PobrezaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS01
     * 
     * @return ResponseEntity con estadísticas del ODS01
     */
    public ResponseEntity<java.util.Map<String, Object>> getOds01Statistics() {
        java.util.Map<String, Object> result = objetivo01PobrezaService.getOds01Statistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS01 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */

    // ── IOdsBaseController implementations ──
    @Override public ResponseEntity<List<Proyectos>> getProyectos() { return ResponseEntity.ok(objetivo01PobrezaService.findAllProyectos()); }
    @Override public ResponseEntity<Proyectos> getProyecto(Integer proyectoId) { return objetivo01PobrezaService.findProyectoById(proyectoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<Proyectos> createProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo01PobrezaService.saveProyecto(proyecto)); }
    @Override public ResponseEntity<Proyectos> updateProyecto(Integer proyectoId, Proyectos proyecto) { return ResponseEntity.ok(objetivo01PobrezaService.updateProyecto(proyecto)); }
    @Override public ResponseEntity<Void> deleteProyecto(Integer proyectoId) { objetivo01PobrezaService.deleteProyecto(proyectoId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<VistaAdminDetalleIndicadores>> getIndicadores(Integer proyectoId) { return ResponseEntity.ok(objetivo01PobrezaService.getAllIndicators(proyectoId)); }
    @Override public ResponseEntity<VistaAdminDetalleIndicadores> getIndicador(Integer indicadorId) { return objetivo01PobrezaService.findIndicadorById(indicadorId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadores> createIndicador(ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo01PobrezaService.saveIndicador(indicador)); }
    @Override public ResponseEntity<ProyectoIndicadores> updateIndicador(Integer indicadorId, ProyectoIndicadores indicador) { return ResponseEntity.ok(objetivo01PobrezaService.updateIndicador(indicador)); }
    @Override public ResponseEntity<Void> deleteIndicador(Integer indicadorId) { objetivo01PobrezaService.deleteIndicador(indicadorId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo01PobrezaService.findAllMetasProyecto(proyectoId)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(Integer metaId) { return objetivo01PobrezaService.findMetaProyectoById(metaId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo01PobrezaService.saveMetaProyecto(meta)); }
    @Override public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(Integer metaId, ProyectoIndicadorParametros meta) { return ResponseEntity.ok(objetivo01PobrezaService.updateMetaProyecto(meta)); }
    @Override public ResponseEntity<Void> deleteMetaProyecto(Integer metaId) { objetivo01PobrezaService.deleteMetaProyecto(metaId); return ResponseEntity.noContent().build(); }

    @Override public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(Integer indicadorId) { return ResponseEntity.ok(objetivo01PobrezaService.findAllMedicionesHistoricas(indicadorId)); }
    @Override public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(Integer medicionId) { return objetivo01PobrezaService.findMedicionHistoricaById(medicionId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); }
    @Override public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo01PobrezaService.saveMedicionHistorica(medicion)); }
    @Override public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(Integer medicionId, MedicionesHistoricas medicion) { return ResponseEntity.ok(objetivo01PobrezaService.updateMedicionHistorica(medicion)); }
    @Override public ResponseEntity<Void> deleteMedicionHistorica(Integer medicionId) { objetivo01PobrezaService.deleteMedicionHistorica(medicionId); return ResponseEntity.noContent().build(); }

    @Override public Map<String, Object> getDashboardData() { return objetivo01PobrezaService.getDashboardData(); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getDashboard() { return ResponseEntity.ok(objetivo01PobrezaService.getDashboardData()); }
    @Override public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() { return ResponseEntity.ok(objetivo01PobrezaService.getOdsStatistics()); }
    @Override public ResponseEntity<Double> getProjectProgress(Integer proyectoId) { return ResponseEntity.ok(objetivo01PobrezaService.calculateProjectProgress(proyectoId)); }
    @Override public ResponseEntity<Boolean> validateIndicador(VistaAdminDetalleIndicadores indicador) { return ResponseEntity.ok(objetivo01PobrezaService.validateIndicatorData(indicador)); }
    @Override public ResponseEntity<Boolean> validateProyecto(Proyectos proyecto) { return ResponseEntity.ok(objetivo01PobrezaService.validateProjectData(proyecto)); }

    @Override public ResponseEntity<Boolean> existsProyecto(Integer proyectoId) { return ResponseEntity.ok(objetivo01PobrezaService.existsProyecto(proyectoId)); }
    @Override public ResponseEntity<Boolean> existsIndicador(Integer indicadorId) { return ResponseEntity.ok(objetivo01PobrezaService.existsIndicador(indicadorId)); }
    @Override public ResponseEntity<Boolean> existsMetaProyecto(Integer metaId) { return ResponseEntity.ok(objetivo01PobrezaService.existsMetaProyecto(metaId)); }
    @Override public ResponseEntity<Boolean> existsMedicionHistorica(Integer medicionId) { return ResponseEntity.ok(objetivo01PobrezaService.existsMedicionHistorica(medicionId)); }
}
