package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.service.Objetivo01PobrezaService;
import com.odsProject.odsProject.controller.interfaces.IObjetivo01PobrezaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para el Objetivo 1: Fin de la Pobreza
 * Expone endpoints REST para los indicadores del ODS1
 * Usa Objetivo01PobrezaService para la lógica de negocio
 */
@RestController
@RequestMapping("/api/ods/01")
public class Objetivo01PobrezaController implements IObjetivo01PobrezaController {

    @Autowired
    private Objetivo01PobrezaService objetivo01PobrezaService;

    // ── Indicadores Específicos del ODS01 ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo01PobrezaService.getAllIndicators(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.1.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_1_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_1_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/proyecto")
    public ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds01(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo01PobrezaService.findAllIndicadoresByProyectoOds01(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/meta")
    public ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(@RequestParam Integer proyectoId, @RequestParam String metaPrefix) {
        List<ProyectoIndicadores> result = objetivo01PobrezaService.findIndicadoresByMeta(proyectoId, metaPrefix);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.2.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_2_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_2_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.2.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_2_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_2_2(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.3.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_3_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_3_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.4.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_4_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_4_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.4.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_4_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_4_2(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.5.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_5_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.5.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_5_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_2(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.5.3")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_5_3(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_3(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.5.4")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_5_4(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_5_4(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.a.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_a_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_a_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.a.2")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_a_2(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_a_2(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/1.b.1")
    public ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_1_b_1(@RequestParam Integer proyectoId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.getIndicador_1_b_1(proyectoId);
        if (result.isPresent()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtiene todos los proyectos del ODS01
     * 
     * @return ResponseEntity con la lista de todos los proyectos del ODS01
     */
    @GetMapping("/proyectos")
    public ResponseEntity<List<Proyectos>> getAllProjectsOds01() {
        List<Proyectos> result = objetivo01PobrezaService.getAllProjectsOds01();
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene un proyecto del ODS01 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el proyecto encontrado
     */
    @GetMapping("/proyectos/{proyectoId}")
    public ResponseEntity<Proyectos> getProjectOds01ById(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo01PobrezaService.getProjectOds01ById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Obtiene todas las metas de proyecto del ODS01
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todas las metas del proyecto ODS01
     */
    @GetMapping("/proyectos/{proyectoId}/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getAllMetasProyectoOds01(@PathVariable Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo01PobrezaService.getAllMetasProyectoOds01(proyectoId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Obtiene todas las mediciones históricas del ODS01
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con la lista de todas las mediciones históricas del ODS01
     */
    @GetMapping("/indicadores/historicas/{indicadorId}")
    public ResponseEntity<List<MedicionesHistoricas>> getAllMedicionesHistoricasOds01(@PathVariable Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo01PobrezaService.getAllMedicionesHistoricasOds01(indicadorId);
        return ResponseEntity.ok(result);
    }

    
    /**
     * Calcula el progreso de un proyecto del ODS01
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con el porcentaje de progreso
     */
    @GetMapping("/progreso/{proyectoId}")
    public ResponseEntity<Double> calculateProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo01PobrezaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Obtiene estadísticas específicas del ODS01
     * 
     * @return ResponseEntity con estadísticas del ODS01
     */
    @GetMapping("/estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getOds01Statistics() {
        java.util.Map<String, Object> result = objetivo01PobrezaService.getOds01Statistics();
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un proyecto del ODS01 existe
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/proyectos/{proyectoId}/existe")
    public ResponseEntity<Boolean> projectExists(@PathVariable Integer proyectoId) {
        Boolean result = objetivo01PobrezaService.projectExists(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * Verifica si un indicador del ODS01 existe
     * 
     * @param indicadorId ID del indicador
     * @return ResponseEntity con true si existe, false otherwise
     */
    @GetMapping("/indicadores/{indicadorId}/existe")
    public ResponseEntity<Boolean> indicatorExists(@PathVariable Integer indicadorId) {
        Boolean result = objetivo01PobrezaService.indicatorExists(indicadorId);
        return ResponseEntity.ok(result);
    }

    // ── IOdsBaseController implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/all-proyectos")
    public ResponseEntity<List<Proyectos>> getProyectos() {
        List<Proyectos> result = objetivo01PobrezaService.findAllProyectos();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> getProyecto(@PathVariable Integer proyectoId) {
        Optional<Proyectos> result = objetivo01PobrezaService.findProyectoById(proyectoId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/proyecto")
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        Proyectos result = objetivo01PobrezaService.saveProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/proyecto")
    public ResponseEntity<Boolean> validateProyecto(@RequestBody Proyectos proyecto) {
        Boolean result = objetivo01PobrezaService.validateProjectData(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer proyectoId, @RequestBody Proyectos proyecto) {
        Proyectos result = objetivo01PobrezaService.updateProyecto(proyecto);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/proyecto/{proyectoId}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer proyectoId) {
        objetivo01PobrezaService.deleteProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-indicadores")
    public ResponseEntity<List<ProyectoIndicadores>> getIndicadores(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadores> result = objetivo01PobrezaService.findAllIndicadoresByProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> getIndicador(@PathVariable Integer indicadorId) {
        Optional<ProyectoIndicadores> result = objetivo01PobrezaService.findIndicadorById(indicadorId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/indicadores")
    public ResponseEntity<ProyectoIndicadores> createIndicador(@RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo01PobrezaService.saveIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/validar/indicador")
    public ResponseEntity<Boolean> validateIndicador(@RequestBody ProyectoIndicadores indicador) {
        Boolean result = objetivo01PobrezaService.validateIndicatorData(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/indicadores/{indicadorId}")
    public ResponseEntity<ProyectoIndicadores> updateIndicador(@PathVariable Integer indicadorId, @RequestBody ProyectoIndicadores indicador) {
        ProyectoIndicadores result = objetivo01PobrezaService.updateIndicador(indicador);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/indicadores/{indicadorId}")
    public ResponseEntity<Void> deleteIndicador(@PathVariable Integer indicadorId) {
        objetivo01PobrezaService.deleteIndicador(indicadorId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas")
    public ResponseEntity<List<ProyectoIndicadorParametros>> getMetasProyecto(@RequestParam Integer proyectoId) {
        List<ProyectoIndicadorParametros> result = objetivo01PobrezaService.findAllMetasProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> getMetaProyecto(@PathVariable Integer metaId) {
        Optional<ProyectoIndicadorParametros> result = objetivo01PobrezaService.findMetaProyectoById(metaId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/metas")
    public ResponseEntity<ProyectoIndicadorParametros> createMetaProyecto(@RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo01PobrezaService.saveMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/metas/{metaId}")
    public ResponseEntity<ProyectoIndicadorParametros> updateMetaProyecto(@PathVariable Integer metaId, @RequestBody ProyectoIndicadorParametros meta) {
        ProyectoIndicadorParametros result = objetivo01PobrezaService.updateMetaProyecto(meta);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/metas/{metaId}")
    public ResponseEntity<Void> deleteMetaProyecto(@PathVariable Integer metaId) {
        objetivo01PobrezaService.deleteMetaProyecto(metaId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones")
    public ResponseEntity<List<MedicionesHistoricas>> getMedicionesHistoricas(@RequestParam Integer indicadorId) {
        List<MedicionesHistoricas> result = objetivo01PobrezaService.findAllMedicionesHistoricas(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> getMedicionHistorica(@PathVariable Integer medicionId) {
        Optional<MedicionesHistoricas> result = objetivo01PobrezaService.findMedicionHistoricaById(medicionId);
        return result.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PostMapping("/mediciones")
    public ResponseEntity<MedicionesHistoricas> createMedicionHistorica(@RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo01PobrezaService.saveMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @PutMapping("/mediciones/{medicionId}")
    public ResponseEntity<MedicionesHistoricas> updateMedicionHistorica(@PathVariable Integer medicionId, @RequestBody MedicionesHistoricas medicion) {
        MedicionesHistoricas result = objetivo01PobrezaService.updateMedicionHistorica(medicion);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @DeleteMapping("/mediciones/{medicionId}")
    public ResponseEntity<Void> deleteMedicionHistorica(@PathVariable Integer medicionId) {
        objetivo01PobrezaService.deleteMedicionHistorica(medicionId);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-estadisticas")
    public ResponseEntity<java.util.Map<String, Object>> getEstadisticas() {
        java.util.Map<String, Object> result = objetivo01PobrezaService.getOdsStatistics();
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/base-progreso/{proyectoId}")
    public ResponseEntity<Double> getProjectProgress(@PathVariable Integer proyectoId) {
        Double result = objetivo01PobrezaService.calculateProjectProgress(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/proyecto/{proyectoId}/existe")
    public ResponseEntity<Boolean> existsProyecto(@PathVariable Integer proyectoId) {
        Boolean result = objetivo01PobrezaService.existsProyecto(proyectoId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/indicador/{indicadorId}/existe")
    public ResponseEntity<Boolean> existsIndicador(@PathVariable Integer indicadorId) {
        Boolean result = objetivo01PobrezaService.existsIndicador(indicadorId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/meta/{metaId}/existe")
    public ResponseEntity<Boolean> existsMetaProyecto(@PathVariable Integer metaId) {
        Boolean result = objetivo01PobrezaService.existsMetaProyecto(metaId);
        return ResponseEntity.ok(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping("/medicion/{medicionId}/existe")
    public ResponseEntity<Boolean> existsMedicionHistorica(@PathVariable Integer medicionId) {
        Boolean result = objetivo01PobrezaService.existsMedicionHistorica(medicionId);
        return ResponseEntity.ok(result);
    }
}
