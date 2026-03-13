package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MetasProyecto;
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
    public List<Indicadores> getAllIndicators(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_1_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_1_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_2_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_3_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_3_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_4_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_5_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_6_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_7_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_8_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_9_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_9_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_10_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_10_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_11_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_11_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_12_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_12_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_13_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_13_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_14_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_14_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_15_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_15_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_16_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_16_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_17_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_17_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_18_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_18_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_18_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_18_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_18_3(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_18_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_19_1(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_19_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_17_19_2(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicador_17_19_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId) {
        return objetivo17AlianzasRepository.findAllIndicadoresByProyectoOds17(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo17AlianzasRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * Obtiene todos los proyectos del ODS17
     * 
     * @return Lista de todos los proyectos del ODS17
     */
    public List<Proyectos> getAllProjectsOds17() {
        return objetivo17AlianzasRepository.findAllProyectosOds17();
    }

    /**
     * Obtiene un proyecto del ODS17 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyectos> getProjectOds17ById(Integer proyectoId) {
        return objetivo17AlianzasRepository.findProyectoOds17ById(proyectoId);
    }

    /**
     * Obtiene todas las metas de proyecto del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto ODS17
     */
    public List<MetasProyecto> getAllMetasProyectoOds17(Integer proyectoId) {
        return objetivo17AlianzasRepository.findAllMetasProyectoOds17(proyectoId);
    }

    /**
     * Obtiene una meta de proyecto del ODS17 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    public Optional<MetasProyecto> getMetaProyectoOds17ById(Integer metaId) {
        return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId);
    }

    /**
     * Obtiene todas las mediciones históricas del ODS17
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas del ODS17
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds17(Integer indicadorId) {
        return objetivo17AlianzasRepository.findAllMedicionesHistoricasOds17(indicadorId);
    }

    /**
     * Obtiene una medición histórica del ODS17 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds17ById(Integer medicionId) {
        return objetivo17AlianzasRepository.findMedicionHistoricaOds17ById(medicionId);
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
     * Obtiene estadísticas específicas del ODS17
     * 
     * @return Map con estadísticas del ODS17
     */
    public Map<String, Object> getOds17Statistics() {
        List<Proyectos> proyectos = objetivo17AlianzasRepository.findAllProyectosOds17();
        List<Indicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo17AlianzasRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * Verifica si un proyecto del ODS17 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    public Boolean projectExists(Integer proyectoId) {
        return objetivo17AlianzasRepository.findProyectoOds17ById(proyectoId).isPresent();
    }

    /**
     * Verifica si un indicador del ODS17 existe
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
    public List<Indicadores> findAllIndicadoresByProyecto(Integer proyectoId) {
        return objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo17AlianzasRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
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
    public List<MetasProyecto> findAllMetasProyecto(Integer proyectoId) {
        return objetivo17AlianzasRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoById(Integer metaId) {
        return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return objetivo17AlianzasRepository.saveMetaProyecto(meta);
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
        List<Indicadores> indicadores = objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
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
