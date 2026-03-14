package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo14VidaSubmarinaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo14VidaSubmarinaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 14: Vida Submarina
 * Expone endpoints REST para los indicadores del ODS14
 * Usa Objetivo14VidaSubmarinaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/14")
public class Objetivo14VidaSubmarinaController implements IObjetivo14VidaSubmarinaController {

    @Autowired
    private Objetivo14VidaSubmarinaService objetivo14VidaSubmarinaService;

    // ── Indicadores Específicos del ODS14 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo14VidaSubmarinaService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.6.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_6_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.7.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_7_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/14.c.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_14_c_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.getIndicador_14_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds14(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo14VidaSubmarinaService.findAllIndicadoresByProyectoOds14(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo14VidaSubmarinaService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS14
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS14
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds14() {
        List<Proyectos> result = objetivo14VidaSubmarinaService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS14 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds14ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo14VidaSubmarinaService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS14
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds14(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo14VidaSubmarinaService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una meta de proyecto del ODS14 por su ID
     * 
     * @param metaId ID de la meta
     * @return ResponseEntity con la meta encontrada
     */
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyectoOds14ById(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo14VidaSubmarinaService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las mediciones históricas del ODS14
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS14
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds14(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo14VidaSubmarinaService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una medición histórica del ODS14 por su ID
     * 
     * @param medicionId ID de la medición
     * @return ResponseEntity con la medición encontrada
     */
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistoricaOds14ById(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo14VidaSubmarinaService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Calcula el progreso de un proyecto del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo14VidaSubmarinaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS14
     * 
     * @return ResponseEntity con estadísticas del ODS14
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds14Statistics() {
        java.util.Map<String, Object> result = objetivo14VidaSubmarinaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS14 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo14VidaSubmarinaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS14 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo14VidaSubmarinaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo14VidaSubmarinaService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo14VidaSubmarinaService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo14VidaSubmarinaService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo14VidaSubmarinaService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo14VidaSubmarinaService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo14VidaSubmarinaService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo14VidaSubmarinaService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo14VidaSubmarinaService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo14VidaSubmarinaService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo14VidaSubmarinaService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo14VidaSubmarinaService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo14VidaSubmarinaService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo14VidaSubmarinaService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo14VidaSubmarinaService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo14VidaSubmarinaService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo14VidaSubmarinaService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo14VidaSubmarinaService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo14VidaSubmarinaService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo14VidaSubmarinaService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo14VidaSubmarinaService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo14VidaSubmarinaService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo14VidaSubmarinaService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo14VidaSubmarinaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo14VidaSubmarinaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo14VidaSubmarinaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo14VidaSubmarinaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo14VidaSubmarinaService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo14VidaSubmarinaService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
