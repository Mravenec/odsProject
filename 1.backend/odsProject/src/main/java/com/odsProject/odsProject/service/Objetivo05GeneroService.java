package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo05GeneroRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo05GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 5: Igualdad de Género
 * Implementa la lógica de negocio para los indicadores del ODS5
 * Usa Objetivo05GeneroRepository para el acceso a datos
 */
@Service
public class Objetivo05GeneroService implements IObjetivo05GeneroService {

    @Autowired
    private Objetivo05GeneroRepository objetivo05GeneroRepository;

    // ── Indicadores Específicos del ODS05 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_1_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_2_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_2_2(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_3_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_3_2(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_4_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_5_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_5_2(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_6_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_6_2(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_6_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_a_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_a_2(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_a_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_b_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_5_c_1(Integer proyectoId) {
        return objetivo05GeneroRepository.findIndicador_5_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds05(Integer proyectoId) {
        return objetivo05GeneroRepository.findAllIndicadoresByProyectoOds05(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo05GeneroRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo05GeneroRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo05GeneroRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo05GeneroRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo05GeneroRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo05GeneroRepository.deleteProyecto(proyectoId);
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
        return objetivo05GeneroRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo05GeneroRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo05GeneroRepository.updateIndicador(indicador);
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
        return objetivo05GeneroRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo05GeneroRepository.findMetaProyectoOds05ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo05GeneroRepository.saveMetaProyecto(meta);
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
        return objetivo05GeneroRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo05GeneroRepository.findMedicionHistoricaOds05ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo05GeneroRepository.saveMedicion(medicion);
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
        List<ProyectoIndicadores> indicadores = objetivo05GeneroRepository.findIndicadoresByProyecto(proyectoId);
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
        List<Proyectos> proyectos = objetivo05GeneroRepository.findAllProyectos();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo05GeneroRepository.findIndicadoresByProyecto(p.getId()).stream())
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
        return objetivo05GeneroRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo05GeneroRepository.findMetaProyectoOds05ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo05GeneroRepository.findMedicionHistoricaOds05ById(medicionId).isPresent();
    }

}
