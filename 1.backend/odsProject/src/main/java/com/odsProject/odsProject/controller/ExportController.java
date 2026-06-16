package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.controller.interfaces.IExportController;
import com.odsProject.odsProject.service.interfaces.IExportService;
import com.odsProject.odsProject.service.interfaces.IRoleAuthorizationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExportController implements IExportController {

    private final IExportService exportService;
    private final IRoleAuthorizationService roleAuthorizationService;

    public ExportController(IExportService exportService,
                            IRoleAuthorizationService roleAuthorizationService) {
        this.exportService = exportService;
        this.roleAuthorizationService = roleAuthorizationService;
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
    public ResponseEntity<byte[]> exportProjectsExcel(Integer sedeId, Integer anio, String authorization) {
        String role = roleAuthorizationService.extractRoleFromAuthorizationHeader(authorization);
        if (!roleAuthorizationService.canExportBulkProjects(role)) {
            return ResponseEntity.status(403).build();
        }

        try {
            byte[] data = exportService.exportProyectosEvaluadosPorSedeYAnio(sedeId, anio);
            String filename = "ods_sodsi_" + sedeId + "_" + anio + ".xlsx";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + filename + "\"")
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(data);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

