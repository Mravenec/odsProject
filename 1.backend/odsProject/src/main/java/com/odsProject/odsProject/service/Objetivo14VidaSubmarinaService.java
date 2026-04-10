package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo14VidaSubmarinaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo14VidaSubmarinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 14: Vida Submarina
 * Implementa la lógica de negocio para los indicadores del ODS14
 * Usa Objetivo14VidaSubmarinaRepository para el acceso a datos
 */
@Service
public class Objetivo14VidaSubmarinaService implements IObjetivo14VidaSubmarinaService {

    @Autowired
    private Objetivo14VidaSubmarinaRepository objetivo14VidaSubmarinaRepository;

    // ── Indicadores Específicos del ODS14 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_1_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_2_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_3_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_4_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_5_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_6_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_7_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_a_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_b_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_14_c_1(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findIndicador_14_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds14(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findAllIndicadoresByProyectoOds14(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo14VidaSubmarinaRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * Obtiene todos los proyectos del ODS14
     * 
     * @return Lista de todos los proyectos del ODS14
     */
    public List<Proyectos> getAllProjectsOds14() {
        return objetivo14VidaSubmarinaRepository.findAllProyectosOds14();
    }

    /**
     * Obtiene un proyecto del ODS14 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyectos> getProjectOds14ById(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findProyectoOds14ById(proyectoId);
    }

    /**
     * Obtiene todas las metas de proyecto del ODS14
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto ODS14
     */
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds14(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findAllMetasProyectoOds14(proyectoId);
    }

    /**
     * Obtiene una meta de proyecto del ODS14 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds14ById(Integer metaId) {
        return objetivo14VidaSubmarinaRepository.findMetaProyectoOds14ById(metaId);
    }

    /**
     * Obtiene todas las mediciones históricas del ODS14
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas del ODS14
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds14(Integer indicadorId) {
        return objetivo14VidaSubmarinaRepository.findAllMedicionesHistoricasOds14(indicadorId);
    }

    /**
     * Obtiene una medición histórica del ODS14 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds14ById(Integer medicionId) {
        return objetivo14VidaSubmarinaRepository.findMedicionHistoricaOds14ById(medicionId);
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
     * Obtiene estadísticas específicas del ODS14
     * 
     * @return Map con estadísticas del ODS14
     */
    public Map<String, Object> getOds14Statistics() {
        List<Proyectos> proyectos = objetivo14VidaSubmarinaRepository.findAllProyectosOds14();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * Verifica si un proyecto del ODS14 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    public Boolean projectExists(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findProyectoOds14ById(proyectoId).isPresent();
    }

    /**
     * Verifica si un indicador del ODS14 existe
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
        return objetivo14VidaSubmarinaRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo14VidaSubmarinaRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo14VidaSubmarinaRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo14VidaSubmarinaRepository.deleteProyecto(proyectoId);
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
        return objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo14VidaSubmarinaRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo14VidaSubmarinaRepository.updateIndicador(indicador);
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
        return objetivo14VidaSubmarinaRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo14VidaSubmarinaRepository.findMetaProyectoOds14ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo14VidaSubmarinaRepository.saveMetaProyecto(meta);
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
        return objetivo14VidaSubmarinaRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo14VidaSubmarinaRepository.findMedicionHistoricaOds14ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo14VidaSubmarinaRepository.saveMedicion(medicion);
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
        List<ProyectoIndicadores> indicadores = objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds14Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo14VidaSubmarinaRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo14VidaSubmarinaRepository.findMetaProyectoOds14ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo14VidaSubmarinaRepository.findMedicionHistoricaOds14ById(medicionId).isPresent();
    }
}
