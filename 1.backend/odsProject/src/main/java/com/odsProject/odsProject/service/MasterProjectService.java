package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.service.interfaces.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio Maestro de Proyectos
 * Maneja la lógica de negocio central de gestión de proyectos multi-ods
 */
@Service
public class MasterProjectService implements IMasterProjectService {

    @Autowired
    private IMasterProjectRepository masterProjectRepository;

    @Autowired
    private IEvaluationService evaluationService;

    // TODO: Inyectar repositorios ODS para el cálculo general (Opcional, según diseño final)

    @Override
    public List<Proyectos> getAllProyectos() {
        return masterProjectRepository.findAll();
    }

    @Override
    public Optional<Proyectos> getProyectoById(Integer id) {
        return masterProjectRepository.findById(id);
    }

    @Override
    public List<Proyectos> getProyectosByUsuario(Integer usuarioId) {
        return masterProjectRepository.findByUsuario(usuarioId);
    }

    @Override
    public Proyectos createProyecto(Proyectos proyecto) {
        return masterProjectRepository.save(proyecto);
    }

    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        if (!masterProjectRepository.exists(proyecto.getId())) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        return masterProjectRepository.update(proyecto);
    }

    @Override
    public void deleteProyecto(Integer id) {
        masterProjectRepository.delete(id);
    }

    @Override
    public Map<String, Object> calculateProjectSummary(Integer proyectoId) {
        // Lógica de resumen global (se implementará mediante agregación de todos los ODS)
        Map<String, Object> summary = new HashMap<>();
        summary.put("proyectoId", proyectoId);
        summary.put("status", "pending_aggregation"); 
        return summary;
    }

    @Override
    public Double evaluateProjectIndicator(Integer proyectoId, Integer odsId, Integer indicadorId) {
        // Esta lógica requiere llamar al ODS específico
        // Será implementada por cada OdsService, y el Master puede orquestar si es necesario
        return 0.0;
    }
}
