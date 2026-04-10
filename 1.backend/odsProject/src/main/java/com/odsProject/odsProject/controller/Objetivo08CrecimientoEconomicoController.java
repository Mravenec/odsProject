package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.Proyectos;
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

    // ── Indicadores Específicos del ODS08 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.1.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_1_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.2.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_2_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.3.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_3_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.4.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_4_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.4.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_4_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_4_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.5.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_5_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.5.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_5_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.6.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_6_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.7.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_7_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.8.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_8_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_8_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.8.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_8_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_8_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.9.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_9_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_9_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.9.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_9_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_9_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.10.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_10_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_10_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.10.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_10_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_10_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.a.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_a_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/8.b.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_8_b_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.getIndicador_8_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds08(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.findAllIndicadoresByProyectoOds08(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS08
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS08
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds08() {
        List<Proyectos> result = objetivo08CrecimientoEconomicoService.getAllProjectsOds08();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS08 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds08ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo08CrecimientoEconomicoService.getProjectOds08ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS08
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS08
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getAllMetasProyectoOds08(@PathVariable Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo08CrecimientoEconomicoService.getAllMetasProyectoOds08(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS08
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS08
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds08(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo08CrecimientoEconomicoService.getAllMedicionesHistoricasOds08(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS08
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo08CrecimientoEconomicoService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS08
     * 
     * @return ResponseEntity con estadísticas del ODS08
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds08Statistics() {
        java.util.Map<String, Object> result = objetivo08CrecimientoEconomicoService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS08 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo08CrecimientoEconomicoService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS08 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo08CrecimientoEconomicoService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo08CrecimientoEconomicoService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo08CrecimientoEconomicoService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo08CrecimientoEconomicoService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo08CrecimientoEconomicoService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo08CrecimientoEconomicoService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo08CrecimientoEconomicoService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<ProyectoIndicadores> result = objetivo08CrecimientoEconomicoService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<ProyectoIndicadores> createIndicador(@RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo08CrecimientoEconomicoService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody ProyectoIndicadores indicador) {
        Boolean result = objetivo08CrecimientoEconomicoService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo08CrecimientoEconomicoService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo08CrecimientoEconomicoService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo08CrecimientoEconomicoService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<ProyectoIndicadorParametros> result = objetivo08CrecimientoEconomicoService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(@RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo08CrecimientoEconomicoService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo08CrecimientoEconomicoService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo08CrecimientoEconomicoService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo08CrecimientoEconomicoService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo08CrecimientoEconomicoService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo08CrecimientoEconomicoService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo08CrecimientoEconomicoService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo08CrecimientoEconomicoService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo08CrecimientoEconomicoService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo08CrecimientoEconomicoService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo08CrecimientoEconomicoService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo08CrecimientoEconomicoService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo08CrecimientoEconomicoService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo08CrecimientoEconomicoService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
