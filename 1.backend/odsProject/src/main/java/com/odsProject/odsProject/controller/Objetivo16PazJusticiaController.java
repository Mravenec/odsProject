package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo16PazJusticiaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo16PazJusticiaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 * Expone endpoints REST para los indicadores del ODS16
 * Usa Objetivo16PazJusticiaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/16")
public class Objetivo16PazJusticiaController implements IObjetivo16PazJusticiaController {

    @Autowired
    private Objetivo16PazJusticiaService objetivo16PazJusticiaService;

    // ── Indicadores Específicos del ODS16 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo16PazJusticiaService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.1.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_1_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.1.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_1_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_1_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.1.4")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_1_4(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_1_4(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.2.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_2_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.2.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_2_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_2_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.3.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_3_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_3_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.3.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_3_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_3_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.4.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_4_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_4_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.5.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_5_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.6.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_6_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.6.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_6_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_6_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.7.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_7_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.7.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_7_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_7_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.8.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_8_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_8_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.9.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_9_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_9_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.10.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_10_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_10_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.10.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_10_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_10_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/16.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_16_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.getIndicador_16_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds16(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo16PazJusticiaService.findAllIndicadoresByProyectoOds16(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo16PazJusticiaService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS16
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS16
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds16() {
        List<Proyectos> result = objetivo16PazJusticiaService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS16 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds16ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo16PazJusticiaService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS16
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS16
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds16(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo16PazJusticiaService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS16
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS16
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds16(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo16PazJusticiaService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS16
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo16PazJusticiaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS16
     * 
     * @return ResponseEntity con estadísticas del ODS16
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds16Statistics() {
        java.util.Map<String, Object> result = objetivo16PazJusticiaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS16 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo16PazJusticiaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS16 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo16PazJusticiaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo16PazJusticiaService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo16PazJusticiaService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo16PazJusticiaService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo16PazJusticiaService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo16PazJusticiaService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo16PazJusticiaService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo16PazJusticiaService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo16PazJusticiaService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo16PazJusticiaService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo16PazJusticiaService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo16PazJusticiaService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo16PazJusticiaService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo16PazJusticiaService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo16PazJusticiaService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo16PazJusticiaService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo16PazJusticiaService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo16PazJusticiaService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo16PazJusticiaService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo16PazJusticiaService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo16PazJusticiaService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo16PazJusticiaService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo16PazJusticiaService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo16PazJusticiaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo16PazJusticiaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo16PazJusticiaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo16PazJusticiaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo16PazJusticiaService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo16PazJusticiaService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
