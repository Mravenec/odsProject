package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo03SaludBienestarRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo03SaludBienestarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 3: Salud y Bienestar
 * Implementa la lógica de negocio para los indicadores del ODS3
 * Usa Objetivo03SaludBienestarRepository para el acceso a datos
 */
@Service
public class Objetivo03SaludBienestarService implements IObjetivo03SaludBienestarService {

    @Autowired
    private Objetivo03SaludBienestarRepository objetivo03SaludBienestarRepository;

    // ── Indicadores Específicos del ODS03 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> getAllIndicators(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_1_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_1_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_2_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_2_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_3_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_3_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_3_3(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_3_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_3_4(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_3_4(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_3_5(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_3_5(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_4_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_4_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_4_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_5_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_5_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_6_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_6_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_6_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_6_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_7_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_7_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_7_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_7_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_8_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_8_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_8_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_8_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_9_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_9_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_9_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_9_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_9_3(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_9_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_a_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_b_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_b_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_b_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_b_3(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_b_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_c_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_d_1(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_d_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Indicadores> getIndicador_3_d_2(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findIndicador_3_d_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findAllIndicadoresByProyectoOds03(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo03SaludBienestarRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> getAllProjectsOds03() {
        return objetivo03SaludBienestarRepository.findAllProyectosOds03();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> getProjectOds03ById(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findProyectoOds03ById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MetasProyecto> getAllMetasProyectoOds03(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findAllMetasProyectoOds03(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> getMetaProyectoOds03ById(Integer metaId) {
        return objetivo03SaludBienestarRepository.findMetaProyectoOds03ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds03(Integer indicadorId) {
        return objetivo03SaludBienestarRepository.findAllMedicionesHistoricasOds03(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds03ById(Integer medicionId) {
        return objetivo03SaludBienestarRepository.findMedicionHistoricaOds03ById(medicionId);
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
        List<Indicadores> indicadores = objetivo03SaludBienestarRepository.findIndicadoresByProyecto(proyectoId);
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
    public Map<String, Object> getOds03Statistics() {
        List<Proyectos> proyectos = objetivo03SaludBienestarRepository.findAllProyectosOds03();
        List<Indicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo03SaludBienestarRepository.findIndicadoresByProyecto(p.getId()).stream())
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
    public Boolean projectExists(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findProyectoOds03ById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean indicatorExists(Integer indicadorId) {
        return true;
    }

    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo03SaludBienestarRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo03SaludBienestarRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo03SaludBienestarRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo03SaludBienestarRepository.deleteProyecto(proyectoId);
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
        return objetivo03SaludBienestarRepository.findIndicadoresByProyecto(proyectoId);
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
        return objetivo03SaludBienestarRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicadores updateIndicador(Indicadores indicador) {
        return objetivo03SaludBienestarRepository.updateIndicador(indicador);
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
        return objetivo03SaludBienestarRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MetasProyecto> findMetaProyectoById(Integer metaId) {
        return objetivo03SaludBienestarRepository.findMetaProyectoOds03ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MetasProyecto saveMetaProyecto(MetasProyecto meta) {
        return objetivo03SaludBienestarRepository.saveMetaProyecto(meta);
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
        return objetivo03SaludBienestarRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo03SaludBienestarRepository.findMedicionHistoricaOds03ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo03SaludBienestarRepository.saveMedicion(medicion);
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
        // Validación básica: verificar que los campos obligatorios no sean null
        if (proyecto == null) return false;
        if (proyecto.getNombreProyecto() == null || proyecto.getNombreProyecto().trim().isEmpty()) return false;
        if (proyecto.getUsuarioId() == null) return false;
        // Agregar más validaciones según sea necesario
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getOdsStatistics() {
        return getOds03Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo03SaludBienestarRepository.findProyectoById(proyectoId).isPresent();
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
        return objetivo03SaludBienestarRepository.findMetaProyectoOds03ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo03SaludBienestarRepository.findMedicionHistoricaOds03ById(medicionId).isPresent();
    }
}
