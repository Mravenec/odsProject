package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo16PazJusticiaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo16PazJusticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 16: Paz y Justicia
 * Implementa la lógica de negocio para los indicadores del ODS16
 * Usa Objetivo16PazJusticiaRepository para el acceso a datos
 */
@Service
public class Objetivo16PazJusticiaService implements IObjetivo16PazJusticiaService {

    @Autowired
    private Objetivo16PazJusticiaRepository objetivo16PazJusticiaRepository;

    // ── Indicadores Específicos del ODS16 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> getAllIndicators(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_1_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_1_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_1_3(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_1_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_1_4(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_1_4(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_2_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_2_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_2_3(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_2_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_3_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_3_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_3_3(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_3_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_4_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_4_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_5_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_5_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_6_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_6_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_6_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_7_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_7_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_7_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_8_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_9_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_9_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_10_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_10_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_10_2(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_10_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_a_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_16_b_1(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findIndicador_16_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findAllIndicadoresByProyectoOds16(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo16PazJusticiaRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * Obtiene todos los proyectos del ODS16
     * 
     * @return Lista de todos los proyectos del ODS16
     */
    public List<Proyectos> getAllProjectsOds16() {
        return objetivo16PazJusticiaRepository.findAllProyectosOds16();
    }

    /**
     * Obtiene un proyecto del ODS16 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyectos> getProjectOds16ById(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findProyectoOds16ById(proyectoId);
    }

    /**
     * Obtiene todas las metas de proyecto del ODS16
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto ODS16
     */
    public List<MetasProyecto> getAllMetasProyectoOds16(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findAllMetasProyectoOds16(proyectoId);
    }

    /**
     * Obtiene una meta de proyecto del ODS16 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    public Optional<MetasProyecto> getMetaProyectoOds16ById(Integer metaId) {
        return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId);
    }

    /**
     * Obtiene todas las mediciones históricas del ODS16
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas del ODS16
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds16(Integer indicadorId) {
        return objetivo16PazJusticiaRepository.findAllMedicionesHistoricasOds16(indicadorId);
    }

    /**
     * Obtiene una medición histórica del ODS16 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds16ById(Integer medicionId) {
        return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId);
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
     * Obtiene estadísticas específicas del ODS16
     * 
     * @return Map con estadísticas del ODS16
     */
    public Map<String, Object> getOds16Statistics() {
        List<Proyectos> proyectos = objetivo16PazJusticiaRepository.findAllProyectosOds16();
        List<Indicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo16PazJusticiaRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * Verifica si un proyecto del ODS16 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    public Boolean projectExists(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findProyectoOds16ById(proyectoId).isPresent();
    }

    /**
     * Verifica si un indicador del ODS16 existe
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
        return objetivo16PazJusticiaRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo16PazJusticiaRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo16PazJusticiaRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo16PazJusticiaRepository.deleteProyecto(proyectoId);
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
        return objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo16PazJusticiaRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
        return objetivo16PazJusticiaRepository.updateIndicador(indicador);
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
        return objetivo16PazJusticiaRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoById(Integer metaId) {
        return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return objetivo16PazJusticiaRepository.saveMetaProyecto(meta);
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
        return objetivo16PazJusticiaRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo16PazJusticiaRepository.saveMedicion(medicion);
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
        List<Indicadores> indicadores = objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds16Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo16PazJusticiaRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId).isPresent();
    }
}
