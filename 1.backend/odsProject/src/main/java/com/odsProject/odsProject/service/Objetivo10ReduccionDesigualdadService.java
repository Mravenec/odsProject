package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo10ReduccionDesigualdadRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo10ReduccionDesigualdadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 10: Reducción de las Desigualdades
 * Implementa la lógica de negocio para los indicadores del ODS10
 * Usa Objetivo10ReduccionDesigualdadRepository para el acceso a datos
 */
@Service
public class Objetivo10ReduccionDesigualdadService implements IObjetivo10ReduccionDesigualdadService {

    @Autowired
    private Objetivo10ReduccionDesigualdadRepository objetivo10ReduccionDesigualdadRepository;

    // ── Indicadores Específicos del ODS10 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> getAllIndicators(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_1_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_2_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_3_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_4_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_4_2(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_5_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_6_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_7_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_7_2(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_7_3(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_7_4(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_4(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_a_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_b_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_10_c_1(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicador_10_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findAllIndicadoresByProyectoOds10(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo10ReduccionDesigualdadRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──
public List<Proyectos> getAllProjectsOds10() {
        return objetivo10ReduccionDesigualdadRepository.findAllProyectosOds10();
    }
public Optional<Proyectos> getProjectOds10ById(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findProyectoOds10ById(proyectoId);
    }
public List<MetasProyecto> getAllMetasProyectoOds10(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findAllMetasProyectoOds10(proyectoId);
    }
public Optional<MetasProyecto> getMetaProyectoOds10ById(Integer metaId) {
        return objetivo10ReduccionDesigualdadRepository.findMetaProyectoOds10ById(metaId);
    }
public List<MedicionesHistoricas> getAllMedicionesHistoricasOds10(Integer indicadorId) {
        return objetivo10ReduccionDesigualdadRepository.findAllMedicionesHistoricasOds10(indicadorId);
    }
public Optional<MedicionesHistoricas> getMedicionHistoricaOds10ById(Integer medicionId) {
        return objetivo10ReduccionDesigualdadRepository.findMedicionHistoricaOds10ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateIndicatorData(Indicadores indicador) {
        if (indicador == null) return false;
        if (indicador.getProyectoId() == null) return false;
        if (indicador.getIndicadorCodigo() == null || indicador.getIndicadorCodigo().trim().isEmpty()) return false;
        return true;
    }
public Map<String, Object> getOds10Statistics() {
        List<Proyectos> proyectos = objetivo10ReduccionDesigualdadRepository.findAllProyectosOds10();
        List<Indicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }
public Boolean projectExists(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findProyectoOds10ById(proyectoId).isPresent();
    }
public Boolean indicatorExists(Integer indicadorId) {
        return true;
    }

    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo10ReduccionDesigualdadRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo10ReduccionDesigualdadRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo10ReduccionDesigualdadRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo10ReduccionDesigualdadRepository.deleteProyecto(proyectoId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyecto(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> findIndicadorById(Integer indicadorId) {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores saveIndicador(Indicadores indicador) {
        return objetivo10ReduccionDesigualdadRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
        return objetivo10ReduccionDesigualdadRepository.updateIndicador(indicador);
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
    public List<MetasProyecto> findAllMetasProyecto(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoById(Integer metaId) {
        return objetivo10ReduccionDesigualdadRepository.findMetaProyectoOds10ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return objetivo10ReduccionDesigualdadRepository.saveMetaProyecto(meta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto updateMetaProyecto(MetasProyecto meta) {
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
        return objetivo10ReduccionDesigualdadRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo10ReduccionDesigualdadRepository.findMedicionHistoricaOds10ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo10ReduccionDesigualdadRepository.saveMedicion(medicion);
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
        List<Indicadores> indicadores = objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds10Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo10ReduccionDesigualdadRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo10ReduccionDesigualdadRepository.findMetaProyectoOds10ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo10ReduccionDesigualdadRepository.findMedicionHistoricaOds10ById(medicionId).isPresent();
    }
}
