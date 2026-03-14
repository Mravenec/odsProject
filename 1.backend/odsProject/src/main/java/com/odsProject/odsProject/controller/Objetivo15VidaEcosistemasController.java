package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MetasProyecto;
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

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo15VidaEcosistemasService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.1.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_1_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.4.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_4_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_4_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.6.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_6_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.7.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_7_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.8.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_8_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_8_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.9.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_9_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_9_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/15.c.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_15_c_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.getIndicador_15_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds15(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo15VidaEcosistemasService.findAllIndicadoresByProyectoOds15(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo15VidaEcosistemasService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS15
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS15
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds15() {
        List<Proyectos> result = objetivo15VidaEcosistemasService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS15 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds15ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo15VidaEcosistemasService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS15
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds15(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo15VidaEcosistemasService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una meta de proyecto del ODS15 por su ID
     * 
     * @param metaId ID de la meta
     * @return ResponseEntity con la meta encontrada
     */
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyectoOds15ById(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo15VidaEcosistemasService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las mediciones históricas del ODS15
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS15
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds15(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo15VidaEcosistemasService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una medición histórica del ODS15 por su ID
     * 
     * @param medicionId ID de la medición
     * @return ResponseEntity con la medición encontrada
     */
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistoricaOds15ById(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo15VidaEcosistemasService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Calcula el progreso de un proyecto del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo15VidaEcosistemasService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS15
     * 
     * @return ResponseEntity con estadísticas del ODS15
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds15Statistics() {
        java.util.Map<String, Object> result = objetivo15VidaEcosistemasService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS15 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo15VidaEcosistemasService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS15 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo15VidaEcosistemasService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo15VidaEcosistemasService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo15VidaEcosistemasService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo15VidaEcosistemasService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo15VidaEcosistemasService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo15VidaEcosistemasService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo15VidaEcosistemasService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo15VidaEcosistemasService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo15VidaEcosistemasService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo15VidaEcosistemasService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo15VidaEcosistemasService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo15VidaEcosistemasService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo15VidaEcosistemasService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo15VidaEcosistemasService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo15VidaEcosistemasService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo15VidaEcosistemasService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo15VidaEcosistemasService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo15VidaEcosistemasService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo15VidaEcosistemasService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo15VidaEcosistemasService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo15VidaEcosistemasService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo15VidaEcosistemasService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo15VidaEcosistemasService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo15VidaEcosistemasService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo15VidaEcosistemasService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo15VidaEcosistemasService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo15VidaEcosistemasService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo15VidaEcosistemasService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo15VidaEcosistemasService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
