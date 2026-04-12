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

    @Autowired
    private List<com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?>> odsServices;

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
        Map<String, Object> summary = new HashMap<>();
        summary.put("proyectoId", proyectoId);
        
        Optional<Proyectos> proyectoOpt = getProyectoById(proyectoId);
        if (proyectoOpt.isEmpty()) {
            summary.put("status", "error");
            summary.put("message", "Proyecto no encontrado");
            return summary;
        }

        int totalIndicators = 0;
        double totalProgress = 0.0;
        int odsCount = 0;

        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> service : odsServices) {
            try {
                // Verificamos si este ODS tiene indicadores para este proyecto
                List<?> indicators = service.findAllIndicadoresByProyecto(proyectoId);
                if (!indicators.isEmpty()) {
                    totalIndicators += indicators.size();
                    totalProgress += service.calculateProjectProgress(proyectoId);
                    odsCount++;
                }
            } catch (Exception e) {
                // Silently skip if an ODS schema isn't fully ready or doesn't have the project
            }
        }

        double averageProgress = odsCount > 0 ? totalProgress / odsCount : 0.0;

        summary.put("totalIndicators", totalIndicators);
        summary.put("odsLinkedCount", odsCount);
        summary.put("averageProgress", averageProgress);
        summary.put("status", averageProgress >= 100 ? "completado" : "activo");
        
        return summary;
    }

    @Override
    public Map<String, Object> getGlobalDashboardData() {
        Map<String, Object> dashboard = masterProjectRepository.spAdminGlobalDashboard();
        
        int totalIndicatorsAllOds = 0;
        int completedIndicatorsAllOds = 0;
        double summedProgressAllOds = 0.0;
        int odsWithIndicatorsCount = 0;

        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> service : odsServices) {
            try {
                // Obtenemos los datos del dashboard de cada ODS
                Map<String, Object> odsDashboard = service.getDashboardData();
                if (odsDashboard != null) {
                    int total = (int) odsDashboard.getOrDefault("total_indicadores", 0);
                    int completed = (int) odsDashboard.getOrDefault("indicadores_terminados", 0);
                    double progress = ((Number) odsDashboard.getOrDefault("progreso_promedio", 0.0)).doubleValue();

                    totalIndicatorsAllOds += total;
                    completedIndicatorsAllOds += completed;
                    summedProgressAllOds += progress;
                    
                    if (total > 0) {
                        odsWithIndicatorsCount++;
                    }
                }
            } catch (Exception e) {
                // Silently skip if an ODS schema isn't fully ready
            }
        }

        dashboard.put("indicadoresTotalesEcosistema", totalIndicatorsAllOds);
        dashboard.put("indicadoresCompletadosEcosistema", completedIndicatorsAllOds);
        dashboard.put("progresoPromedio", odsWithIndicatorsCount > 0 ? summedProgressAllOds / odsWithIndicatorsCount : 0.0);
        dashboard.put("odsActivosConDatos", odsWithIndicatorsCount);
        
        return dashboard;
    }

    @Override
    public Double evaluateProjectIndicator(Integer proyectoId, Integer odsId, Integer indicadorId) {
        // Buscamos el servicio correspondiente al ODS solicitado
        // El nombre del servicio suele ser ObjetivoXX...Service
        String servicePrefix = String.format("Objetivo%02d", odsId);
        
        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> service : odsServices) {
            if (service.getClass().getSimpleName().startsWith(servicePrefix)) {
                // Aquí podríamos disparar lógica específica o simplemente retornar el valor actual
                // Por ahora, el Master actúa como pasarela si se requiere orquestación global
                return service.calculateProjectProgress(proyectoId);
            }
        }
        return 0.0;
    }
}
