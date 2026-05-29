package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.controller.interfaces.ITransicionPlanificacionController;
import com.odsProject.odsProject.service.interfaces.ITransicionPlanificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class TransicionPlanificacionController implements ITransicionPlanificacionController {

    @Autowired
    private ITransicionPlanificacionService transicionPlanificacionService;

    @Override
    @PostMapping("/{id}/planificacion/solicitud")
    public ResponseEntity<Map<String, Object>> crearSolicitud(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body) {
        return handle(() -> transicionPlanificacionService.crearSolicitud(
                id,
                toInt(body.get("actorUserId")),
                str(body.get("estadoDestino")),
                str(body.get("motivo"))));
    }

    @Override
    @GetMapping("/{id}/planificacion/solicitud/pendiente")
    public ResponseEntity<Map<String, Object>> obtenerPendiente(
            @PathVariable Integer id,
            @RequestParam Integer actorUserId,
            @RequestParam String actorRole) {
        return handle(() -> transicionPlanificacionService.obtenerPendiente(id, actorUserId, actorRole));
    }

    @Override
    @PostMapping("/{id}/planificacion/solicitud/aprobar")
    public ResponseEntity<Map<String, Object>> aprobar(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body) {
        return handle(() -> transicionPlanificacionService.aprobar(
                id,
                toInt(body.get("actorUserId")),
                str(body.get("actorRole")),
                str(body.get("nota"))));
    }

    @Override
    @PostMapping("/{id}/planificacion/solicitud/rechazar")
    public ResponseEntity<Map<String, Object>> rechazar(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body) {
        return handle(() -> transicionPlanificacionService.rechazar(
                id,
                toInt(body.get("actorUserId")),
                str(body.get("actorRole")),
                str(body.get("nota"))));
    }

    @Override
    @PostMapping("/{id}/planificacion/fuerza-mayor")
    public ResponseEntity<Map<String, Object>> fuerzaMayor(
            @PathVariable Integer id,
            @RequestBody Map<String, Object> body) {
        return handle(() -> transicionPlanificacionService.fuerzaMayor(
                id,
                toInt(body.get("actorUserId")),
                str(body.get("actorRole")),
                str(body.get("motivo"))));
    }

    private ResponseEntity<Map<String, Object>> handle(SupplierWithException supplier) {
        try {
            return ResponseEntity.ok(supplier.get());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "error", e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(403).body(Map.of("success", false, "error", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    @FunctionalInterface
    private interface SupplierWithException {
        Map<String, Object> get();
    }

    private static Integer toInt(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        return Integer.valueOf(String.valueOf(v));
    }

    private static String str(Object v) {
        return v != null ? String.valueOf(v) : "";
    }
}
