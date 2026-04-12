package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz para el Servicio Maestro de Proyectos
 * Maneja la lógica de negocio central de gestión de proyectos multi-ods
 */
public interface IMasterProjectService {

    // CRUD Básico
    List<Proyectos> getAllProyectos();
    Optional<Proyectos> getProyectoById(Integer id);
    List<Proyectos> getProyectosByUsuario(Integer usuarioId);
    Proyectos createProyecto(Proyectos proyecto);
    Proyectos updateProyecto(Proyectos proyecto);
    void deleteProyecto(Integer id);

    // Lógica de Evaluación
    /**
     * Calcula el progreso de un proyecto sumando los resultados de todos los ODS vinculados
     * @param proyectoId ID del proyecto
     * @return Mapa con el resumen de progreso (total indicators, achieved, percentage)
     */
    Map<String, Object> calculateProjectSummary(Integer proyectoId);

    /**
     * Obtiene el resumen consolidado de todo el ecosistema ODS
     * Agrega datos de proyectos, sedes e indicadores globales
     * @return Mapa con el Dashboard Maestro
     */
    Map<String, Object> getGlobalDashboardData();

    /**
     * Evalúa un indicador específico dentro de un proyecto ODS
     * Actualiza el valor_actual basado en los parámetros cargados
     * 
     * @param proyectoId ID del proyecto
     * @param odsId ID del ODS (1-17)
     * @param indicadorId ID del indicador en proyecto_indicadores
     * @return El nuevo valor calculado
     */
    Double evaluateProjectIndicator(Integer proyectoId, Integer odsId, Integer indicadorId);
}
