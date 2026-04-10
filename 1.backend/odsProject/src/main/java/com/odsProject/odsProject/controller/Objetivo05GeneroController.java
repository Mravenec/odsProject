package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo05GeneroService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo05GeneroController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 5: Igualdad de Género
 * Expone endpoints REST para los indicadores del ODS5
 * Usa Objetivo05GeneroService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/05")
public class Objetivo05GeneroController implements IObjetivo05GeneroController {

    @Autowired
    private Objetivo05GeneroService objetivo05GeneroService;

    // ── Indicadores Específicos del ODS05 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo05GeneroService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.1.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_1_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.2.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_2_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.2.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_2_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.3.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_3_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.3.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_3_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_3_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.4.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_4_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.5.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_5_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.5.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_5_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.6.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_6_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.6.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_6_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_6_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.a.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_a_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.a.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_a_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_a_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.b.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_b_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/5.c.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_5_c_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.getIndicador_5_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds05(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo05GeneroService.findAllIndicadoresByProyectoOds05(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<ProyectoIndicadores> result = objetivo05GeneroService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS05
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS05
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds05() {
        List<Proyectos> result = objetivo05GeneroService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS05 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds05ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo05GeneroService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS05
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS05
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getAllMetasProyectoOds05(@PathVariable Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo05GeneroService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS05
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS05
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds05(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo05GeneroService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS05
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo05GeneroService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS05
     * 
     * @return ResponseEntity con estadísticas del ODS05
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds05Statistics() {
        java.util.Map<String, Object> result = objetivo05GeneroService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS05 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo05GeneroService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS05 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo05GeneroService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo05GeneroService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo05GeneroService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo05GeneroService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo05GeneroService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo05GeneroService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo05GeneroService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo05GeneroService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<ProyectoIndicadores> result = objetivo05GeneroService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<ProyectoIndicadores> createIndicador(@RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo05GeneroService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody ProyectoIndicadores indicador) {
        Boolean result = objetivo05GeneroService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo05GeneroService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo05GeneroService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo05GeneroService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<ProyectoIndicadorParametros> result = objetivo05GeneroService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(@RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo05GeneroService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo05GeneroService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo05GeneroService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo05GeneroService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo05GeneroService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo05GeneroService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo05GeneroService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo05GeneroService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo05GeneroService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo05GeneroService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo05GeneroService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo05GeneroService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo05GeneroService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo05GeneroService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
