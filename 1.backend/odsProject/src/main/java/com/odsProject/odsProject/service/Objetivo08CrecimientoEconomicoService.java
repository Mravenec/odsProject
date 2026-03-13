package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo08CrecimientoEconomicoRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo08CrecimientoEconomicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 * Implementa la lógica de negocio para los indicadores del ODS8
 * Usa Objetivo08CrecimientoEconomicoRepository para el acceso a datos
 */
@Service
public class Objetivo08CrecimientoEconomicoService implements IObjetivo08CrecimientoEconomicoService {

    @Autowired
    private Objetivo08CrecimientoEconomicoRepository objetivo08CrecimientoEconomicoRepository;

    // ── Indicadores Específicos del ODS08 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> getAllIndicators(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_1_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_2_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_3_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_4_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_4_2(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_5_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_5_2(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_6_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_7_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_8_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_8_2(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_8_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_9_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_9_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_9_2(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_9_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_10_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_10_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_10_2(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_10_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_a_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_8_b_1(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findIndicador_8_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds08(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findAllIndicadoresByProyectoOds08(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo08CrecimientoEconomicoRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * {@inheritDoc}
     */
    public List<Proyectos> getAllProjectsOds08() {
        return objetivo08CrecimientoEconomicoRepository.findAllProyectosOds08();
    }

    /**
     * {@inheritDoc}
     */
    public Optional<Proyectos> getProjectOds08ById(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findProyectoOds08ById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    public List<MetasProyecto> getAllMetasProyectoOds08(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findAllMetasProyectoOds08(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<MetasProyecto> getMetaProyectoOds08ById(Integer metaId) {
        return objetivo08CrecimientoEconomicoRepository.findMetaProyectoOds08ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds08(Integer indicadorId) {
        return objetivo08CrecimientoEconomicoRepository.findAllMedicionesHistoricasOds08(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds08ById(Integer medicionId) {
        return objetivo08CrecimientoEconomicoRepository.findMedicionHistoricaOds08ById(medicionId);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        List<Indicadores> indicadores = objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream()
            .filter(ind -> ind.getValorActual() != null)
            .count();
        return (double) withData / indicadores.size() * 100.0;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, Object> getOds08Statistics() {
        List<Proyectos> proyectos = objetivo08CrecimientoEconomicoRepository.findAllProyectosOds08();
        List<Indicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(p.getId()).stream())
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
        return objetivo08CrecimientoEconomicoRepository.findProyectoOds08ById(proyectoId).isPresent();
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
        return objetivo08CrecimientoEconomicoRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo08CrecimientoEconomicoRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo08CrecimientoEconomicoRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo08CrecimientoEconomicoRepository.deleteProyecto(proyectoId);
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
        return objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo08CrecimientoEconomicoRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
        return objetivo08CrecimientoEconomicoRepository.updateIndicador(indicador);
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
        return objetivo08CrecimientoEconomicoRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoById(Integer metaId) {
        return objetivo08CrecimientoEconomicoRepository.findMetaProyectoOds08ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return objetivo08CrecimientoEconomicoRepository.saveMetaProyecto(meta);
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
        return objetivo08CrecimientoEconomicoRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo08CrecimientoEconomicoRepository.findMedicionHistoricaOds08ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo08CrecimientoEconomicoRepository.saveMedicion(medicion);
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
    public Map<String, Object> getOdsStatistics() {
        return getOds08Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo08CrecimientoEconomicoRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo08CrecimientoEconomicoRepository.findMetaProyectoOds08ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo08CrecimientoEconomicoRepository.findMedicionHistoricaOds08ById(medicionId).isPresent();
    }
}
