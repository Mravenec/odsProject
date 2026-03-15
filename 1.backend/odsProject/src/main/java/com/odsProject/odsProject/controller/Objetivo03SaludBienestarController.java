package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo03SaludBienestarService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo03SaludBienestarController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 3: Salud y Bienestar
 * Expone endpoints REST para los indicadores del ODS3
 * Usa Objetivo03SaludBienestarService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/03")
public class Objetivo03SaludBienestarController implements IObjetivo03SaludBienestarController {

    @Autowired
    private Objetivo03SaludBienestarService objetivo03SaludBienestarService;

    // ── Indicadores Específicos del ODS03 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo03SaludBienestarService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.1.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_1_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.2.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_2_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.3.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_3_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_3_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.3.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_3_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_3_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.3.4")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_3_4(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_3_4(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.3.5")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_3_5(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_3_5(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.4.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_4_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_4_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.5.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_5_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.6.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_6_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.7.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_7_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.7.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_7_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_7_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.8.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_8_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_8_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.8.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_8_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_8_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.9.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_9_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_9_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.9.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_9_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_9_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.9.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_9_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_9_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.b.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_b_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_b_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.b.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_b_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_b_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.c.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_c_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.d.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_d_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_d_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/3.d.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_3_d_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.getIndicador_3_d_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds03(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo03SaludBienestarService.findAllIndicadoresByProyectoOds03(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo03SaludBienestarService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS03
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS03
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds03() {
        List<Proyectos> result = objetivo03SaludBienestarService.getAllProjectsOds03();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS03 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds03ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo03SaludBienestarService.getProjectOds03ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS03
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds03(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo03SaludBienestarService.getAllMetasProyectoOds03(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS03
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS03
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds03(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo03SaludBienestarService.getAllMedicionesHistoricasOds03(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo03SaludBienestarService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS03
     * 
     * @return ResponseEntity con estadísticas del ODS03
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds03Statistics() {
        java.util.Map<String, Object> result = objetivo03SaludBienestarService.getOds03Statistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS03 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo03SaludBienestarService.projectExists(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS03 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo03SaludBienestarService.indicatorExists(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo03SaludBienestarService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo03SaludBienestarService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo03SaludBienestarService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo03SaludBienestarService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo03SaludBienestarService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo03SaludBienestarService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo03SaludBienestarService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo03SaludBienestarService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo03SaludBienestarService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo03SaludBienestarService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo03SaludBienestarService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo03SaludBienestarService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo03SaludBienestarService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo03SaludBienestarService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo03SaludBienestarService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo03SaludBienestarService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo03SaludBienestarService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo03SaludBienestarService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo03SaludBienestarService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo03SaludBienestarService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo03SaludBienestarService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo03SaludBienestarService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo03SaludBienestarService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo03SaludBienestarService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo03SaludBienestarService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo03SaludBienestarService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo03SaludBienestarService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo03SaludBienestarService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
