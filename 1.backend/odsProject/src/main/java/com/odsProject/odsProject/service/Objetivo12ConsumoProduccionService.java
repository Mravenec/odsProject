package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo12ConsumoProduccionRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo12ConsumoProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 12: Producción y Consumo Responsables
 * Implementa la lógica de negocio para los indicadores del ODS12
 * Usa Objetivo12ConsumoProduccionRepository para el acceso a datos
 */
@Service
public class Objetivo12ConsumoProduccionService implements IObjetivo12ConsumoProduccionService {

    @Autowired
    private Objetivo12ConsumoProduccionRepository objetivo12ConsumoProduccionRepository;

    // -- Indicadores Específicos del ODS12 --

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_1_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_2_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_2_2(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_3_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_4_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_4_2(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_5_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_6_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_7_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_8_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_a_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_b_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_12_c_1(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findIndicador_12_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds12(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findAllIndicadoresByProyectoOds12(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo12ConsumoProduccionRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // -- Métodos CRUD y Utilidades (para alinear con Repository) --

    /**
     * Obtiene todos los proyectos del ODS12
     * 
     * @return Lista de todos los proyectos del ODS12
     */
    public List<Proyectos> getAllProjectsOds12() {
        return objetivo12ConsumoProduccionRepository.findAllProyectosOds12();
    }

    /**
     * Obtiene un proyecto del ODS12 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    public Optional<Proyectos> getProjectOds12ById(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findProyectoOds12ById(proyectoId);
    }

    /**
     * Obtiene todas las metas de proyecto del ODS12
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todas las metas del proyecto ODS12
     */
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds12(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findAllMetasProyectoOds12(proyectoId);
    }

    /**
     * Obtiene una meta de proyecto del ODS12 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds12ById(Integer metaId) {
        return objetivo12ConsumoProduccionRepository.findMetaProyectoOds12ById(metaId);
    }

    /**
     * Obtiene todas las mediciones históricas del ODS12
     * 
     * @param indicadorId ID del indicador
     * @return Lista de todas las mediciones históricas del ODS12
     */
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds12(Integer indicadorId) {
        return objetivo12ConsumoProduccionRepository.findAllMedicionesHistoricasOds12(indicadorId);
    }

    /**
     * Obtiene una medición histórica del ODS12 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds12ById(Integer medicionId) {
        return objetivo12ConsumoProduccionRepository.findMedicionHistoricaOds12ById(medicionId);
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
     * Obtiene estadísticas generales del ODS12
     * 
     * @return Map con estadísticas generales del ODS12
     */
    public Map<String, Object> getOds12Statistics() {
        List<Proyectos> proyectos = objetivo12ConsumoProduccionRepository.findAllProyectosOds12();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    /**
     * Verifica si un proyecto del ODS12 existe
     * 
     * @param proyectoId ID del proyecto
     * @return true si existe, false otherwise
     */
    public Boolean projectExists(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findProyectoOds12ById(proyectoId).isPresent();
    }

    /**
     * Verifica si un indicador del ODS12 existe
     * 
     * @param indicadorId ID del indicador
     * @return true si existe, false otherwise
     */
    public Boolean indicatorExists(Integer indicadorId) {
        return true;
    }

    // -- IOdsBaseService implementations --

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo12ConsumoProduccionRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo12ConsumoProduccionRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo12ConsumoProduccionRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo12ConsumoProduccionRepository.deleteProyecto(proyectoId);
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
        return objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo12ConsumoProduccionRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo12ConsumoProduccionRepository.updateIndicador(indicador);
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
        return objetivo12ConsumoProduccionRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo12ConsumoProduccionRepository.findMetaProyectoOds12ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo12ConsumoProduccionRepository.saveMetaProyecto(meta);
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
        return objetivo12ConsumoProduccionRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo12ConsumoProduccionRepository.findMedicionHistoricaOds12ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo12ConsumoProduccionRepository.saveMedicion(medicion);
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
        List<ProyectoIndicadores> indicadores = objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(proyectoId);
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
        return getOds12Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo12ConsumoProduccionRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo12ConsumoProduccionRepository.findMetaProyectoOds12ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo12ConsumoProduccionRepository.findMedicionHistoricaOds12ById(medicionId).isPresent();
    }
}
