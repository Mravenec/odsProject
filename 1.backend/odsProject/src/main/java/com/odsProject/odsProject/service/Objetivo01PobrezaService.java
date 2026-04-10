package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo01PobrezaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo01PobrezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 1: Fin de la Pobreza
 * Implementa la lógica de negocio para los indicadores del ODS1
 * Usa Objetivo01PobrezaRepository para el acceso a datos
 */
@Service
public class Objetivo01PobrezaService implements IObjetivo01PobrezaService {

    @Autowired
    private Objetivo01PobrezaRepository objetivo01PobrezaRepository;

    // ── Indicadores Específicos del ODS01 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_1_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_2_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_2_2(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_3_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_4_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_4_2(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_5_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_5_2(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_5_3(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_5_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_5_4(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_5_4(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_a_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_a_2(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_a_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_1_b_1(Integer proyectoId) {
        return objetivo01PobrezaRepository.findIndicador_1_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId) {
        return objetivo01PobrezaRepository.findAllIndicadoresByProyectoOds01(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo01PobrezaRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> getAllProjectsOds01() {
        return objetivo01PobrezaRepository.findAllProyectosOds01();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> getProjectOds01ById(Integer proyectoId) {
        return objetivo01PobrezaRepository.findProyectoOds01ById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds01(Integer proyectoId) {
        return objetivo01PobrezaRepository.findAllMetasProyectoOds01(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds01ById(Integer metaId) {
        return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds01(Integer indicadorId) {
        return objetivo01PobrezaRepository.findAllMedicionesHistoricasOds01(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds01ById(Integer medicionId) {
        return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateIndicatorData(ProyectoIndicadores indicador) {
        // Validación básica: verificar que los campos obligatorios no sean null
        if (indicador == null) return false;
        if (indicador.getProyectoId() == null) return false;
        if (indicador.getIndicadorMasterId() == null) return false;
        // Agregar más validaciones según sea necesario
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        // Lógica simplificada: contar indicadores con datos vs total
        List<ProyectoIndicadores> indicadores = objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId);
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
    public Map<String, Object> getOds01Statistics() {
        List<Proyectos> proyectos = objetivo01PobrezaRepository.findAllProyectosOds01();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo01PobrezaRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();

        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean projectExists(Integer proyectoId) {
        return objetivo01PobrezaRepository.findProyectoOds01ById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo01PobrezaRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo01PobrezaRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo01PobrezaRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo01PobrezaRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo01PobrezaRepository.deleteProyecto(proyectoId);
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
        return objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicadorById(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver empty
        return Optional.empty(); // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        return objetivo01PobrezaRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo01PobrezaRepository.updateIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteIndicador(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver false
        return false; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) {
        return objetivo01PobrezaRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo01PobrezaRepository.saveMetaProyecto(meta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) {
        // Necesitaría método en repositorio, por ahora devolver la misma meta
        return meta; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteMetaProyecto(Integer metaId) {
        // Necesitaría método en repositorio, por ahora devolver false
        return false; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) {
        return objetivo01PobrezaRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo01PobrezaRepository.saveMedicion(medicion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) {
        // Necesitaría método en repositorio, por ahora devolver la misma medición
        return medicion; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteMedicionHistorica(Integer medicionId) {
        // Necesitaría método en repositorio, por ahora devolver false
        return false; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateProjectData(Proyectos proyecto) {
        // Validación básica: verificar que los campos obligatorios no sean null
        if (proyecto == null) return false;
        if (proyecto.getNombreProyecto() == null || proyecto.getNombreProyecto().trim().isEmpty()) return false;
        if (proyecto.getUsuarioId() == null) return false;
        // Agregar más validaciones según sea necesario
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getOdsStatistics() {
        return getOds01Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo01PobrezaRepository.findProyectoById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsIndicador(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver true
        return true; // Placeholder
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean indicatorExists(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver true
        return true; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMetaProyecto(Integer metaId) {
        return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId).isPresent();
    }
}
