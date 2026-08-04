package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ITransicionPlanificacionController {

    ResponseEntity<Map<String, Object>> crearSolicitud(
            @PathVariable Integer id, @RequestBody Map<String, Object> body);

    ResponseEntity<Map<String, Object>> obtenerPendiente(
            @PathVariable Integer id,
            Integer actorUserId,
            String actorRole);

    ResponseEntity<Map<String, Object>> aprobar(
            @PathVariable Integer id, @RequestBody Map<String, Object> body);

    ResponseEntity<Map<String, Object>> rechazar(
            @PathVariable Integer id, @RequestBody Map<String, Object> body);

    ResponseEntity<Map<String, Object>> fuerzaMayor(
            @PathVariable Integer id, @RequestBody Map<String, Object> body);

    ResponseEntity<Map<String, Object>> listarRecientes(Integer actorUserId, String actorRole);
}
