package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo11CiudadesSosteniblesRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo11CiudadesSosteniblesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Implementa la lógica de negocio para los indicadores del ODS11
 * Usa Objetivo11CiudadesSosteniblesRepository para el acceso a datos
 */
@Service
public class Objetivo11CiudadesSosteniblesService implements IObjetivo11CiudadesSosteniblesService {

    @Autowired
    private Objetivo11CiudadesSosteniblesRepository objetivo11CiudadesSosteniblesRepository;

    // ── Indicadores Específicos del ODS11 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_1_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_3_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_5_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_5_2(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_6_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_6_2(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_6_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_7_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_7_2(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_7_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_b_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_b_2(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_b_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_c_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_2_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_3_2(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_4_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_5_3(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_5_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_11_a_1(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findIndicador_11_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findAllIndicadoresByProyectoOds11(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo11CiudadesSosteniblesRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──
public List<Proyectos> getAllProjectsOds11() {
        return objetivo11CiudadesSosteniblesRepository.findAllProyectosOds11();
    }
public Optional<Proyectos> getProjectOds11ById(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findProyectoOds11ById(proyectoId);
    }
public List<ProyectoIndicadorParametros> getAllMetasProyectoOds11(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findAllMetasProyectoOds11(proyectoId);
    }
public Optional<ProyectoIndicadorParametros> getMetaProyectoOds11ById(Integer metaId) {
        return objetivo11CiudadesSosteniblesRepository.findMetaProyectoOds11ById(metaId);
    }
public List<MedicionesHistoricas> getAllMedicionesHistoricasOds11(Integer indicadorId) {
        return objetivo11CiudadesSosteniblesRepository.findAllMedicionesHistoricasOds11(indicadorId);
    }
public Optional<MedicionesHistoricas> getMedicionHistoricaOds11ById(Integer medicionId) {
        return objetivo11CiudadesSosteniblesRepository.findMedicionHistoricaOds11ById(medicionId);
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
public Map<String, Object> getOds11Statistics() {
        List<Proyectos> proyectos = objetivo11CiudadesSosteniblesRepository.findAllProyectosOds11();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }
public Boolean projectExists(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findProyectoOds11ById(proyectoId).isPresent();
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
        return objetivo11CiudadesSosteniblesRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo11CiudadesSosteniblesRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo11CiudadesSosteniblesRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo11CiudadesSosteniblesRepository.deleteProyecto(proyectoId);
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
        return objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo11CiudadesSosteniblesRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo11CiudadesSosteniblesRepository.updateIndicador(indicador);
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
        return objetivo11CiudadesSosteniblesRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo11CiudadesSosteniblesRepository.findMetaProyectoOds11ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo11CiudadesSosteniblesRepository.saveMetaProyecto(meta);
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
        return objetivo11CiudadesSosteniblesRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo11CiudadesSosteniblesRepository.findMedicionHistoricaOds11ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo11CiudadesSosteniblesRepository.saveMedicion(medicion);
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
        List<ProyectoIndicadores> indicadores = objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds11Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo11CiudadesSosteniblesRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo11CiudadesSosteniblesRepository.findMetaProyectoOds11ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo11CiudadesSosteniblesRepository.findMedicionHistoricaOds11ById(medicionId).isPresent();
    }
}
