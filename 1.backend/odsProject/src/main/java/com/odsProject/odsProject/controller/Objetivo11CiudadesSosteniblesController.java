package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo11CiudadesSosteniblesService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo11CiudadesSosteniblesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Expone endpoints REST para los indicadores del ODS11
 * Usa Objetivo11CiudadesSosteniblesService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/11")
public class Objetivo11CiudadesSosteniblesController implements IObjetivo11CiudadesSosteniblesController {

    @Autowired
    private Objetivo11CiudadesSosteniblesService objetivo11CiudadesSosteniblesService;

    // ── Indicadores Específicos del ODS11 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<Indicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo11CiudadesSosteniblesService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.1.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_1_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.2.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_2_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.3.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_3_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.3.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_3_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_3_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.4.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_4_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.5.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_5_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.5.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_5_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_5_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.5.3")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_5_3(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_5_3(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.6.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_6_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.6.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_6_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_6_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.7.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_7_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.7.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_7_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_7_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.a.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_a_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.b.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_b_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.b.2")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_b_2(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_b_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/11.c.1")
    public ResponseEntity<Optional<Indicadores>> getIndicador_11_c_1(@RequestParam Integer proyectoId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.getIndicador_11_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds11(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo11CiudadesSosteniblesService.findAllIndicadoresByProyectoOds11(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<Indicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<Indicadores> result = objetivo11CiudadesSosteniblesService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS11
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS11
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds11() {
        List<Proyectos> result = objetivo11CiudadesSosteniblesService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS11 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds11ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo11CiudadesSosteniblesService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS11
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<MetasProyecto>> getAllMetasProyectoOds11(@PathVariable Integer proyectoId) {
        List<MetasProyecto> result = objetivo11CiudadesSosteniblesService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una meta de proyecto del ODS11 por su ID
     * 
     * @param metaId ID de la meta
     * @return ResponseEntity con la meta encontrada
     */
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyectoOds11ById(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo11CiudadesSosteniblesService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las mediciones históricas del ODS11
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS11
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds11(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo11CiudadesSosteniblesService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene una medición histórica del ODS11 por su ID
     * 
     * @param medicionId ID de la medición
     * @return ResponseEntity con la medición encontrada
     */
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistoricaOds11ById(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo11CiudadesSosteniblesService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Calcula el progreso de un proyecto del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo11CiudadesSosteniblesService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS11
     * 
     * @return ResponseEntity con estadísticas del ODS11
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds11Statistics() {
        java.util.Map<String, Object> result = objetivo11CiudadesSosteniblesService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS11 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo11CiudadesSosteniblesService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS11 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo11CiudadesSosteniblesService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo11CiudadesSosteniblesService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo11CiudadesSosteniblesService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo11CiudadesSosteniblesService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo11CiudadesSosteniblesService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo11CiudadesSosteniblesService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo11CiudadesSosteniblesService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<Indicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<Indicadores> result = objetivo11CiudadesSosteniblesService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<Indicadores> result = objetivo11CiudadesSosteniblesService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<Indicadores> createIndicador(@RequestBody Indicadores indicador) {
        Indicadores result = objetivo11CiudadesSosteniblesService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody Indicadores indicador) {
        Boolean result = objetivo11CiudadesSosteniblesService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Indicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody Indicadores indicador) {
        Indicadores result = objetivo11CiudadesSosteniblesService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo11CiudadesSosteniblesService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<MetasProyecto>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<MetasProyecto> result = objetivo11CiudadesSosteniblesService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<MetasProyecto> result = objetivo11CiudadesSosteniblesService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<MetasProyecto> createMetaProyecto(@RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo11CiudadesSosteniblesService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<MetasProyecto> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody MetasProyecto meta) {
        MetasProyecto result = objetivo11CiudadesSosteniblesService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo11CiudadesSosteniblesService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo11CiudadesSosteniblesService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo11CiudadesSosteniblesService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo11CiudadesSosteniblesService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo11CiudadesSosteniblesService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo11CiudadesSosteniblesService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo11CiudadesSosteniblesService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo11CiudadesSosteniblesService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo11CiudadesSosteniblesService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo11CiudadesSosteniblesService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo11CiudadesSosteniblesService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo11CiudadesSosteniblesService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
