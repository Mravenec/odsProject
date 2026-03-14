package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo09InfraestructuraService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo09InfraestructuraController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 9: Industria, Innovación e Infraestructura
 * Expone endpoints REST para los indicadores del ODS9
 * Usa Objetivo09InfraestructuraService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/09")
public class Objetivo09InfraestructuraController implements IObjetivo09InfraestructuraController {

    @Autowired
    private Objetivo09InfraestructuraService objetivo09InfraestructuraService;

    // ── Indicadores Específicos del ODS09 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo09InfraestructuraService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.1.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_1_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.2.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_2_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.3.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_3_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_3_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.5.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_5_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/9.c.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_9_c_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.getIndicador_9_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds09(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo09InfraestructuraService.findAllIndicadoresByProyectoOds09(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo09InfraestructuraService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS09
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS09
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds09() {
        List<Proyectos> result = objetivo09InfraestructuraService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS09 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds09ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo09InfraestructuraService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS09
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS09
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds09(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo09InfraestructuraService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una meta de proyecto del ODS09 por su ID
     * 
     * @param metaId ID de la meta
     * @return ResponseEntity con la meta encontrada
     */
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyectoOds09ById(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo09InfraestructuraService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las mediciones históricas del ODS09
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS09
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds09(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo09InfraestructuraService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una medición histórica del ODS09 por su ID
     * 
     * @param medicionId ID de la medición
     * @return ResponseEntity con la medición encontrada
     */
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistoricaOds09ById(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo09InfraestructuraService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Calcula el progreso de un proyecto del ODS09
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo09InfraestructuraService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS09
     * 
     * @return ResponseEntity con estadísticas del ODS09
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds09Statistics() {
        java.util.Map<String, Object> result = objetivo09InfraestructuraService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS09 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo09InfraestructuraService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS09 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo09InfraestructuraService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo09InfraestructuraService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo09InfraestructuraService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo09InfraestructuraService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo09InfraestructuraService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo09InfraestructuraService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo09InfraestructuraService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo09InfraestructuraService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo09InfraestructuraService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo09InfraestructuraService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo09InfraestructuraService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo09InfraestructuraService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo09InfraestructuraService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo09InfraestructuraService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo09InfraestructuraService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo09InfraestructuraService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo09InfraestructuraService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo09InfraestructuraService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo09InfraestructuraService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo09InfraestructuraService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo09InfraestructuraService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo09InfraestructuraService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo09InfraestructuraService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo09InfraestructuraService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo09InfraestructuraService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo09InfraestructuraService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo09InfraestructuraService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo09InfraestructuraService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo09InfraestructuraService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
