package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo17AlianzasRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo17AlianzasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 17: Alianzas para los Objetivos
 * Implementa la lógica de negocio para los indicadores del ODS17
 * Usa Objetivo17AlianzasRepository para el acceso a datos
 */
@Service
public class Objetivo17AlianzasService implements IObjetivo17AlianzasService {

    @Autowired
    private Objetivo17AlianzasRepository objetivo17AlianzasRepository;

    // ── Indicadores Específicos del ODS17 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_1_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_1_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_2_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_3_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_3_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_4_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_5_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_6_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_7_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_8_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_9_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_9_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_10_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_10_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_11_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_11_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_12_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_12_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_13_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_13_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_14_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_14_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_15_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_15_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_16_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_16_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_17_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_17_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_18_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_18_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_18_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_18_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_18_3(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_18_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_19_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_19_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_17_19_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_19_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId) {
        return objetivo17AlianzasRepository.findAllIndicadoresByProyectoOds17(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo17AlianzasRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * {@inheritDoc}
     */
    public List<Proyectos> getAllProjectsOds17() {
        return objetivo17AlianzasRepository.findAllProyectosOds17();
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Proyectos> getProjectOds17ById(Integer proyectoId) {
        return objetivo17AlianzasRepository.findProyectoOds17ById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds17(Integer proyectoId) {
        return objetivo17AlianzasRepository.findAllMetasProyectoOds17(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds17ById(Integer metaId) {
        return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds17(Integer indicadorId) {
        return objetivo17AlianzasRepository.findAllMedicionesHistoricasOds17(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds17ById(Integer medicionId) {
        return objetivo17AlianzasRepository.findMedicionHistoricaOds17ById(medicionId);
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
    public Map<String, Object> getOds17Statistics() {
        List<Proyectos> proyectos = objetivo17AlianzasRepository.findAllProyectosOds17();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo17AlianzasRepository.findIndicadoresByProyecto(p.getId()).stream())
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
    public Boolean projectExists(Integer proyectoId) {
        return objetivo17AlianzasRepository.findProyectoOds17ById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
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
        return objetivo17AlianzasRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo17AlianzasRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo17AlianzasRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo17AlianzasRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo17AlianzasRepository.deleteProyecto(proyectoId);
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
        return objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo17AlianzasRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo17AlianzasRepository.updateIndicador(indicador);
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
        return objetivo17AlianzasRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo17AlianzasRepository.saveMetaProyecto(meta);
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
        return objetivo17AlianzasRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo17AlianzasRepository.findMedicionHistoricaOds17ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo17AlianzasRepository.saveMedicion(medicion);
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
        List<ProyectoIndicadores> indicadores = objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds17Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo17AlianzasRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo17AlianzasRepository.findMedicionHistoricaOds17ById(medicionId).isPresent();
    }
}
