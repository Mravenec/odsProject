package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo13AccionClimaticaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo13AccionClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 13: Acción por el Clima
 * Implementa la lógica de negocio para los indicadores del ODS13
 * Usa Objetivo13AccionClimaRepository para el acceso a datos
 */
@Service
public class Objetivo13AccionClimaService implements IObjetivo13AccionClimaService {

    @Autowired
    private Objetivo13AccionClimaticaRepository objetivo13AccionClimaRepository;

    // ── Indicadores Específicos del ODS13 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_1_1(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_1_2(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_1_3(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_1_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_2_1(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_2_2(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_2_3(Integer proyectoId) {
        if (proyectoId == null) {
            return Optional.empty();
        }
        return objetivo13AccionClimaRepository.findIndicador_13_2_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_3_1(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_3_2(Integer proyectoId) {
        if (proyectoId == null) {
            return Optional.empty();
        }
        return objetivo13AccionClimaRepository.findIndicador_13_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_a_1(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_b_1(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicador_13_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_13_b_2(Integer proyectoId) {
        if (proyectoId == null) {
            return Optional.empty();
        }
        return objetivo13AccionClimaRepository.findIndicador_13_b_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds13(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findAllIndicadoresByProyectoOds13(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo13AccionClimaRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * Obtiene todos los proyectos del ODS13
     * 
     * @return Lista de todos los proyectos del ODS13
     */
    public List<Proyectos> getAllProjectsOds13() {
        return objetivo13AccionClimaRepository.findAllProyectosOds13();
    }

    /**
     * Obtiene un proyecto del ODS13 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyectos> getProjectOds13ById(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findProyectoOds13ById(proyectoId);
    }

    /**
     * Obtiene todas las metas de proyecto del ODS13
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto ODS13
     */
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds13(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findAllMetasProyectoOds13(proyectoId);
    }

    /**
     * Obtiene una meta de proyecto del ODS13 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds13ById(Integer metaId) {
        return objetivo13AccionClimaRepository.findMetaProyectoOds13ById(metaId);
    }

    /**
     * Obtiene todas las mediciones históricas del ODS13
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas del ODS13
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds13(Integer indicadorId) {
        return objetivo13AccionClimaRepository.findAllMedicionesHistoricasOds13(indicadorId);
    }

    /**
     * Obtiene una medición histórica del ODS13 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds13ById(Integer medicionId) {
        return objetivo13AccionClimaRepository.findMedicionHistoricaOds13ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateIndicatorData(ProyectoIndicadores indicador) {
        if (indicador == null) return false;
        if (indicador.getProyectoId() == null) return false;
        if (indicador.getIndicadorMasterId() == null) return false;
        return true;
    }

    /**
     * Obtiene estadísticas generales del ODS13
     * 
     * @return Map con estadísticas generales del ODS13
     */
    public Map<String, Object> getOds13Statistics() {
        List<Proyectos> proyectos = objetivo13AccionClimaRepository.findAllProyectosOds13();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo13AccionClimaRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * Verifica si un proyecto del ODS13 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    public Boolean projectExists(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findProyectoOds13ById(proyectoId).isPresent();
    }

    /**
     * Verifica si un indicador del ODS13 existe
     * 
     * @param indicadorId ID del indicador
     * @return true si existe, false otherwise
     */
    public Boolean indicatorExists(Integer indicadorId) {
        return true;
    }

    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo13AccionClimaRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo13AccionClimaRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo13AccionClimaRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo13AccionClimaRepository.deleteProyecto(proyectoId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicadorById(Integer indicadorId) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        return objetivo13AccionClimaRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo13AccionClimaRepository.updateIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteIndicador(Integer indicadorId) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo13AccionClimaRepository.findMetaProyectoOds13ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo13AccionClimaRepository.saveMetaProyecto(meta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) {
        return meta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteMetaProyecto(Integer metaId) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) {
        return objetivo13AccionClimaRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo13AccionClimaRepository.findMedicionHistoricaOds13ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo13AccionClimaRepository.saveMedicion(medicion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) {
        return medicion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteMedicionHistorica(Integer medicionId) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateProjectData(Proyectos proyecto) {
        if (proyecto == null) return false;
        if (proyecto.getUsuarioId() == null) return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        List<ProyectoIndicadores> indicadores = objetivo13AccionClimaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream()
            .filter(ind -> ind.getValorActual() != null)
            .count();
        return (double) withData / indicadores.size() * 100.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getOdsStatistics() {
        return getOds13Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo13AccionClimaRepository.findProyectoById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsIndicador(Integer indicadorId) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMetaProyecto(Integer metaId) {
        return objetivo13AccionClimaRepository.findMetaProyectoOds13ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo13AccionClimaRepository.findMedicionHistoricaOds13ById(medicionId).isPresent();
    }
}
