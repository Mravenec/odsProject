package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.controller.interfaces.IExportController;
import com.odsProject.odsProject.service.interfaces.IExportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController implements IExportController {

    private final IExportService exportService;

    public ExportController(IExportService exportService) {
        this.exportService = exportService;
    }

    @Override
    public ResponseEntity<byte[]> exportProyecto(Integer id) {
        try {
            byte[] data = exportService.exportProyecto(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"proyecto-" + id + "-resumen.xlsx\"")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @Override
    public ResponseEntity<byte[]> exportConsolidado() {
        byte[] data = exportService.exportPlanificacionConsolidado();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"planificacion-consolidado.xlsx\"")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }

    @Override
    public ResponseEntity<byte[]> exportProjectsExcel(Integer sedeId, Integer userId) {
        byte[] data = exportService.exportPlanificacionConsolidado(sedeId, userId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"proyectos-planificacion.xlsx\"")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(data);
    }
}
