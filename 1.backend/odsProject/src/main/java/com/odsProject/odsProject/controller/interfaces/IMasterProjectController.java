package com.odsProject.odsProject.controller.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Interfaz para el Controller Maestro de Proyectos
 * Expone endpoints REST para la gestión centralizada de proyectos
 */
@RequestMapping("/api/projects")
public interface IMasterProjectController {

    @GetMapping
    ResponseEntity<List<Proyectos>> getAllProyectos();

    @GetMapping("/{id}")
    ResponseEntity<Proyectos> getProyectoById(@PathVariable Integer id);

    @GetMapping("/user/{userId}")
    ResponseEntity<List<Proyectos>> getProyectosByUsuario(@PathVariable Integer userId);

    @PostMapping
    ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto);

    @PutMapping("/{id}")
    ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer id, @RequestBody Proyectos proyecto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProyecto(@PathVariable Integer id);

    @GetMapping("/{id}/summary")
    ResponseEntity<Map<String, Object>> getProjectSummary(@PathVariable Integer id);

    @GetMapping("/dashboard")
    ResponseEntity<Map<String, Object>> getGlobalDashboard();
}
