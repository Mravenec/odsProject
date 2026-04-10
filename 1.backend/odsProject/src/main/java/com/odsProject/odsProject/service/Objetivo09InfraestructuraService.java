package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo09InfraestructuraRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo09InfraestructuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 9: Industria, Innovación e Infraestructura
 * Implementa la lógica de negocio para los indicadores del ODS9
 * Usa Objetivo09InfraestructuraRepository para el acceso a datos
 */
@Service
public class Objetivo09InfraestructuraService implements IObjetivo09InfraestructuraService {

    @Autowired
    private Objetivo09InfraestructuraRepository objetivo09InfraestructuraRepository;

    // ── Indicadores Específicos del ODS09 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_1_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_1_2(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_2_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_2_2(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_3_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_3_2(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_4_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_5_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_5_2(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_a_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_b_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_9_c_1(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findIndicador_9_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds09(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findAllIndicadoresByProyectoOds09(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo09InfraestructuraRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }
    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo09InfraestructuraRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo09InfraestructuraRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo09InfraestructuraRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo09InfraestructuraRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo09InfraestructuraRepository.deleteProyecto(proyectoId);
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
        return objetivo09InfraestructuraRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo09InfraestructuraRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo09InfraestructuraRepository.updateIndicador(indicador);
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
        return objetivo09InfraestructuraRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo09InfraestructuraRepository.findMetaProyectoOds09ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo09InfraestructuraRepository.saveMetaProyecto(meta);
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
        return objetivo09InfraestructuraRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo09InfraestructuraRepository.findMedicionHistoricaOds09ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo09InfraestructuraRepository.saveMedicion(medicion);
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
        return getOds09Statistics();
    }

    /**
     * Obtiene estadísticas generales del ODS09
     * 
     * @return Map con estadísticas generales del ODS09
     */
    private Map<String, Object> getOds09Statistics() {
        List<Proyectos> proyectos = objetivo09InfraestructuraRepository.findAllProyectos();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo09InfraestructuraRepository.findIndicadoresByProyecto(p.getId()).stream())
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
        return objetivo09InfraestructuraRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo09InfraestructuraRepository.findMetaProyectoOds09ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo09InfraestructuraRepository.findMedicionHistoricaOds09ById(medicionId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        List<ProyectoIndicadores> indicadores = findAllIndicadoresByProyecto(proyectoId);
        long totalIndicadores = indicadores.size();
        long indicadoresConDatos = indicadores.stream().filter(i -> i.getValorActual() != null).count();
        if (totalIndicadores == 0) return 0.0;
        return (double) indicadoresConDatos / totalIndicadores * 100;
    }
}
