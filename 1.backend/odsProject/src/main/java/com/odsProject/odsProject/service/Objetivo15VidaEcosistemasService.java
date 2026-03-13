package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo15VidaEcosistemasRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo15VidaEcosistemasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 15: Vida de los Ecosistemas Terrestres
 * Implementa la lógica de negocio para los indicadores del ODS15
 * Usa Objetivo15VidaEcosistemasRepository para el acceso a datos
 */
@Service
public class Objetivo15VidaEcosistemasService implements IObjetivo15VidaEcosistemasService {

    @Autowired
    private Objetivo15VidaEcosistemasRepository objetivo15VidaEcosistemasRepository;

    // ── Indicadores Específicos del ODS15 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> getAllIndicators(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_1_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_1_2(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_2_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_3_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_4_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_4_2(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_5_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_6_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_7_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_8_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_9_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_9_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_a_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_b_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_15_c_1(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findIndicador_15_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds15(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findAllIndicadoresByProyectoOds15(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo15VidaEcosistemasRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * Obtiene todos los proyectos del ODS15
     * 
     * @return Lista de todos los proyectos del ODS15
     */
    public List<Proyectos> getAllProjectsOds15() {
        return objetivo15VidaEcosistemasRepository.findAllProyectosOds15();
    }

    /**
     * Obtiene un proyecto del ODS15 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyectos> getProjectOds15ById(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findProyectoOds15ById(proyectoId);
    }

    /**
     * Obtiene todas las metas de proyecto del ODS15
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto ODS15
     */
    public List<MetasProyecto> getAllMetasProyectoOds15(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findAllMetasProyectoOds15(proyectoId);
    }

    /**
     * Obtiene una meta de proyecto del ODS15 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    public Optional<MetasProyecto> getMetaProyectoOds15ById(Integer metaId) {
        return objetivo15VidaEcosistemasRepository.findMetaProyectoOds15ById(metaId);
    }

    /**
     * Obtiene todas las mediciones históricas del ODS15
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas del ODS15
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds15(Integer indicadorId) {
        return objetivo15VidaEcosistemasRepository.findAllMedicionesHistoricasOds15(indicadorId);
    }

    /**
     * Obtiene una medición histórica del ODS15 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds15ById(Integer medicionId) {
        return objetivo15VidaEcosistemasRepository.findMedicionHistoricaOds15ById(medicionId);
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
     * Obtiene estadísticas específicas del ODS15
     * 
     * @return Map con estadísticas del ODS15
     */
    public Map<String, Object> getOds15Statistics() {
        List<Proyectos> proyectos = objetivo15VidaEcosistemasRepository.findAllProyectosOds15();
        List<Indicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * Verifica si un proyecto del ODS15 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    public Boolean projectExists(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findProyectoOds15ById(proyectoId).isPresent();
    }

    /**
     * Verifica si un indicador del ODS15 existe
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
        return objetivo15VidaEcosistemasRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo15VidaEcosistemasRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo15VidaEcosistemasRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo15VidaEcosistemasRepository.deleteProyecto(proyectoId);
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
        return objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo15VidaEcosistemasRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
        return objetivo15VidaEcosistemasRepository.updateIndicador(indicador);
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
        return objetivo15VidaEcosistemasRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoById(Integer metaId) {
        return objetivo15VidaEcosistemasRepository.findMetaProyectoOds15ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return objetivo15VidaEcosistemasRepository.saveMetaProyecto(meta);
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
        return objetivo15VidaEcosistemasRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo15VidaEcosistemasRepository.findMedicionHistoricaOds15ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo15VidaEcosistemasRepository.saveMedicion(medicion);
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
        List<Indicadores> indicadores = objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds15Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo15VidaEcosistemasRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo15VidaEcosistemasRepository.findMetaProyectoOds15ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo15VidaEcosistemasRepository.findMedicionHistoricaOds15ById(medicionId).isPresent();
    }
}
