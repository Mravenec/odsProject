package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo06AguaSaneamientoRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo06AguaSaneamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 6: Agua Limpia y Saneamiento
 * Implementa la lógica de negocio para los indicadores del ODS6
 * Usa Objetivo06AguaSaneamientoRepository para el acceso a datos
 */
@Service
public class Objetivo06AguaSaneamientoService implements IObjetivo06AguaSaneamientoService {

    @Autowired
    private Objetivo06AguaSaneamientoRepository objetivo06AguaSaneamientoRepository;

    // ── Indicadores Específicos del ODS06 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_1_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_2_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_3_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_3_2(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_4_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_4_2(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_5_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_5_2(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_6_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_a_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_6_b_1(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findIndicador_6_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds06(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findAllIndicadoresByProyectoOds06(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo06AguaSaneamientoRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * {@inheritDoc}
     */
    public List<Proyectos> getAllProjectsOds06() {
        return objetivo06AguaSaneamientoRepository.findAllProyectosOds06();
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Proyectos> getProjectOds06ById(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findProyectoOds06ById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds06(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findAllMetasProyectoOds06(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds06ById(Integer metaId) {
        return objetivo06AguaSaneamientoRepository.findMetaProyectoOds06ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds06(Integer indicadorId) {
        return objetivo06AguaSaneamientoRepository.findAllMedicionesHistoricasOds06(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds06ById(Integer medicionId) {
        return objetivo06AguaSaneamientoRepository.findMedicionHistoricaOds06ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    public Boolean validateIndicatorData(ProyectoIndicadores indicador) {
        if (indicador == null) return false;
        if (indicador.getProyectoId() == null) return false;
        if (indicador.getIndicadorMasterId() == null) return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        List<ProyectoIndicadores> indicadores = objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(proyectoId);
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
        List<Proyectos> proyectos = objetivo06AguaSaneamientoRepository.findAllProyectosOds06();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(p.getId()).stream())
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
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findProyectoOds06ById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsIndicador(Integer indicadorId) {
        return true;
    }

    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo06AguaSaneamientoRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo06AguaSaneamientoRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo06AguaSaneamientoRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo06AguaSaneamientoRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo06AguaSaneamientoRepository.deleteProyecto(proyectoId);
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
        return objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo06AguaSaneamientoRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo06AguaSaneamientoRepository.updateIndicador(indicador);
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
        return objetivo06AguaSaneamientoRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo06AguaSaneamientoRepository.findMetaProyectoOds06ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo06AguaSaneamientoRepository.saveMetaProyecto(meta);
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
        return objetivo06AguaSaneamientoRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo06AguaSaneamientoRepository.findMedicionHistoricaOds06ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo06AguaSaneamientoRepository.saveMedicion(medicion);
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
    public Boolean existsMetaProyecto(Integer metaId) {
        return objetivo06AguaSaneamientoRepository.findMetaProyectoOds06ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo06AguaSaneamientoRepository.findMedicionHistoricaOds06ById(medicionId).isPresent();
    }
}
