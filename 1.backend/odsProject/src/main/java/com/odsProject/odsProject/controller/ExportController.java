package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Autowired private ExportService exportService;

    @GetMapping("/proyecto/{id}")
    public ResponseEntity<byte[]> exportProyecto(@PathVariable Integer id) {
        try {
            byte[] data = exportService.exportProyecto(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"proyecto-" + id + ".xlsx\"")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @GetMapping("/planificacion/consolidado")
    public ResponseEntity<byte[]> exportConsolidado() {
        byte[] data = exportService.exportPlanificacionConsolidado();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"planificacion-consolidado.xlsx\"")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }

    /** Board spec alias: same workbook as consolidado; optional filters reserved for future use. */
    @SuppressWarnings("unused")
    @GetMapping("/projects/excel")
    public ResponseEntity<byte[]> exportProjectsExcel(
            @RequestParam(required = false) Integer sedeId,
            @RequestParam(required = false) Integer userId) {
        byte[] data = exportService.exportPlanificacionConsolidado();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"proyectos-planificacion.xlsx\"")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }
}
