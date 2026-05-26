package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Contrato REST de exportación Excel (consultor / planificación).
 */
@RequestMapping("/api/export")
public interface IExportController {

    @GetMapping("/proyecto/{id}")
    ResponseEntity<byte[]> exportProyecto(@PathVariable Integer id);

    @GetMapping("/planificacion/consolidado")
    ResponseEntity<byte[]> exportConsolidado();

    @GetMapping("/projects/excel")
    ResponseEntity<byte[]> exportProjectsExcel(
            @RequestParam(required = false) Integer sedeId,
            @RequestParam(required = false) Integer userId);
}
