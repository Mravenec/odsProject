package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo04EducacionService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo04EducacionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 4: Educación de Calidad
 * Expone endpoints REST para los indicadores del ODS4
 * Usa Objetivo04EducacionService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/04")
public class Objetivo04EducacionController implements IObjetivo04EducacionController {

    @Autowired
    private Objetivo04EducacionService objetivo04EducacionService;

    // ── Indicadores Específicos del ODS04 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo04EducacionService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.1.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_1_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_1_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.1.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_1_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_1_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.2.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_2_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_2_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.2.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_2_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_2_2(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.3.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_3_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_3_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.4.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_4_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_4_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.5.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_5_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_5_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.6.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_6_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_6_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.7.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_7_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_7_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.a.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_a_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_a_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.b.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_b_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_b_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/4.c.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_4_c_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.getIndicador_4_c_1(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds04(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo04EducacionService.findAllIndicadoresByProyectoOds04(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<ProyectoIndicadores> result = objetivo04EducacionService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    // ── Proyectos ──

    /**
     * Obtiene todos los proyectos del ODS04
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS04
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds04() {
        List<Proyectos> result = objetivo04EducacionService.getAllProjectsOds04();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS04 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds04ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo04EducacionService.getProjectOds04ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS04
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getAllMetasProyectoOds04(@PathVariable Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo04EducacionService.getAllMetasProyectoOds04(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS04
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS04
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds04(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo04EducacionService.getAllMedicionesHistoricasOds04(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo04EducacionService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS04
     * 
     * @return ResponseEntity con estadísticas del ODS04
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds04Statistics() {
        java.util.Map<String, Object> result = objetivo04EducacionService.getOds04Statistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS04 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo04EducacionService.projectExists(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS04 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo04EducacionService.indicatorExists(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo04EducacionService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo04EducacionService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo04EducacionService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo04EducacionService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo04EducacionService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo04EducacionService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo04EducacionService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<ProyectoIndicadores> result = objetivo04EducacionService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<ProyectoIndicadores> createIndicador(@RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo04EducacionService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody ProyectoIndicadores indicador) {
        Boolean result = objetivo04EducacionService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo04EducacionService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo04EducacionService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo04EducacionService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<ProyectoIndicadorParametros> result = objetivo04EducacionService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(@RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo04EducacionService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo04EducacionService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo04EducacionService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo04EducacionService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo04EducacionService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo04EducacionService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo04EducacionService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo04EducacionService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo04EducacionService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo04EducacionService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo04EducacionService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo04EducacionService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo04EducacionService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo04EducacionService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
