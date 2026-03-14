package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo07EnergiaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo07EnergiaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 7: Energía Asequible y No Contaminante
 * Expone endpoints REST para los indicadores del ODS7
 * Usa Objetivo07EnergiaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/07")
public class Objetivo07EnergiaController implements IObjetivo07EnergiaController {

    @Autowired
    private Objetivo07EnergiaService objetivo07EnergiaService;

    // ── Indicadores Específicos del ODS07 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo07EnergiaService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/7.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_7_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo07EnergiaService.getIndicador_7_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/7.1.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_7_1_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo07EnergiaService.getIndicador_7_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/7.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_7_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo07EnergiaService.getIndicador_7_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/7.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_7_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo07EnergiaService.getIndicador_7_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/7.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_7_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo07EnergiaService.getIndicador_7_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/7.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_7_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo07EnergiaService.getIndicador_7_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds07(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo07EnergiaService.findAllIndicadoresByProyectoOds07(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo07EnergiaService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS07
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS07
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds07() {
        List<Proyectos> result = objetivo07EnergiaService.getAllProjectsOds07();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS07 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds07ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo07EnergiaService.getProjectOds07ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS07
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds07(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo07EnergiaService.getAllMetasProyectoOds07(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una meta de proyecto del ODS07 por su ID
     * 
     * @param metaId ID de la meta
     * @return ResponseEntity con la meta encontrada
     */
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyectoOds07ById(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo07EnergiaService.getMetaProyectoOds07ById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las mediciones históricas del ODS07
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS07
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds07(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo07EnergiaService.getAllMedicionesHistoricasOds07(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una medición histórica del ODS07 por su ID
     * 
     * @param medicionId ID de la medición
     * @return ResponseEntity con la medición encontrada
     */
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistoricaOds07ById(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo07EnergiaService.getMedicionHistoricaOds07ById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Calcula el progreso de un proyecto del ODS07
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo07EnergiaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS07
     * 
     * @return ResponseEntity con estadísticas del ODS07
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds07Statistics() {
        java.util.Map<String, Object> result = objetivo07EnergiaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS07 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo07EnergiaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS07 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo07EnergiaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo07EnergiaService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo07EnergiaService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo07EnergiaService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo07EnergiaService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo07EnergiaService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo07EnergiaService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo07EnergiaService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo07EnergiaService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo07EnergiaService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo07EnergiaService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo07EnergiaService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo07EnergiaService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo07EnergiaService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo07EnergiaService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo07EnergiaService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo07EnergiaService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo07EnergiaService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo07EnergiaService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo07EnergiaService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo07EnergiaService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo07EnergiaService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo07EnergiaService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo07EnergiaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo07EnergiaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo07EnergiaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo07EnergiaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo07EnergiaService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo07EnergiaService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
