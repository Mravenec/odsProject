package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo02HambreCeroService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo02HambreCeroController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 2: Hambre Cero
 * Expone endpoints REST para los indicadores del ODS2
 * Usa Objetivo02HambreCeroService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/02")
public class Objetivo02HambreCeroController implements IObjetivo02HambreCeroController {

    @Autowired
    private Objetivo02HambreCeroService objetivo02HambreCeroService;

    // ── Indicadores Específicos del ODS02 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo02HambreCeroService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.1.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_1_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.2.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_2_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.2.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_2_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_2_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.2.4")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_2_4(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_2_4(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.3.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_3_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_3_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.5.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_5_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.a.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_a_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_a_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/2.c.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_2_c_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.getIndicador_2_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds02() {
        List<Proyectos> result = objetivo02HambreCeroService.getAllProjectsOds02();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds02(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo02HambreCeroService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds02ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo02HambreCeroService.getProjectOds02ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds02(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo02HambreCeroService.getAllMetasProyectoOds02(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds02(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo02HambreCeroService.getAllMedicionesHistoricasOds02(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo02HambreCeroService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo02HambreCeroService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo02HambreCeroService.getOds02Statistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo02HambreCeroService.projectExists(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo02HambreCeroService.indicatorExists(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo02HambreCeroService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo02HambreCeroService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/base-proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo02HambreCeroService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/base-validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo02HambreCeroService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/base-proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo02HambreCeroService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/base-proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo02HambreCeroService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo02HambreCeroService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicador/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo02HambreCeroService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/base-indicador")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo02HambreCeroService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/base-validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo02HambreCeroService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/base-indicador/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo02HambreCeroService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/base-indicador/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo02HambreCeroService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo02HambreCeroService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-meta/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo02HambreCeroService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/base-meta")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo02HambreCeroService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/base-meta/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo02HambreCeroService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/base-meta/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo02HambreCeroService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo02HambreCeroService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-medicion/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo02HambreCeroService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/base-medicion")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo02HambreCeroService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/base-medicion/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo02HambreCeroService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/base-medicion/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo02HambreCeroService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOdsStatistics() {
        java.util.Map<String, Object> result = objetivo02HambreCeroService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo02HambreCeroService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo02HambreCeroService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo02HambreCeroService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }
}
