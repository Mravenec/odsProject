package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.controller.interfaces.IMasterProjectController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller Maestro de Proyectos
 * Expone endpoints REST para la gestión centralizada de proyectos en ods_master
 */
@RestController
@RequestMapping("/api/projects")
public class MasterProjectController implements IMasterProjectController {

    @Autowired
    private IMasterProjectService masterProjectService;

    @Override
    @GetMapping
    public ResponseEntity<List<Proyectos>> getAllProyectos() {
        return ResponseEntity.ok(masterProjectService.getAllProyectos());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Proyectos> getProyectoById(@PathVariable Integer id) {
        return masterProjectService.getProyectoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Proyectos>> getProyectosByUsuario(@PathVariable Integer userId) {
        return ResponseEntity.ok(masterProjectService.getProyectosByUsuario(userId));
    }

    @Override
    @PostMapping
    public ResponseEntity<Proyectos> createProyecto(@RequestBody Proyectos proyecto) {
        return ResponseEntity.ok(masterProjectService.createProyecto(proyecto));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Proyectos> updateProyecto(@PathVariable Integer id, @RequestBody Proyectos proyecto) {
        proyecto.setId(id);
        return ResponseEntity.ok(masterProjectService.updateProyecto(proyecto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Integer id) {
        masterProjectService.deleteProyecto(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}/summary")
    public ResponseEntity<Map<String, Object>> getProjectSummary(@PathVariable Integer id) {
        return ResponseEntity.ok(masterProjectService.calculateProjectSummary(id));
    }
}
