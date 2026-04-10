package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo02HambreCeroRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo02HambreCeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 2: Hambre Cero
 * Implementa la lógica de negocio para los indicadores del ODS2
 * Usa Objetivo02HambreCeroRepository para el acceso a datos
 */
@Service
public class Objetivo02HambreCeroService implements IObjetivo02HambreCeroService {

    @Autowired
    private Objetivo02HambreCeroRepository objetivo02HambreCeroRepository;

    // ── Indicadores Específicos del ODS02 ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_1_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_1_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_1_2(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_1_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_2_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_2_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_2_2(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_2_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_2_3(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_2_3(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_2_4(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_2_4(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_3_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_3_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_3_2(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_3_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_4_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_4_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_5_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_5_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_5_2(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_5_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_a_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_a_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_a_2(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_a_2(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_b_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_b_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> getIndicador_2_c_1(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findIndicador_2_c_1(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds02(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findAllIndicadoresByProyectoOds02(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo02HambreCeroRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> getAllProjectsOds02() {
        return objetivo02HambreCeroRepository.findAllProyectosOds02();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> getProjectOds02ById(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findProyectoOds02ById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds02(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findAllMetasProyectoOds02(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds02ById(Integer metaId) {
        return objetivo02HambreCeroRepository.findMetaProyectoOds02ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds02(Integer indicadorId) {
        return objetivo02HambreCeroRepository.findAllMedicionesHistoricasOds02(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds02ById(Integer medicionId) {
        return objetivo02HambreCeroRepository.findMedicionHistoricaOds02ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateIndicatorData(ProyectoIndicadores indicador) {
        // Validación básica: verificar que los campos obligatorios no sean null
        if (indicador == null) return false;
        if (indicador.getProyectoId() == null) return false;
        // En la nueva arquitectura No usamos getIndicadorCodigo directamente del pojo si no existe
        // Pero si JOOQ lo generó por una vista, lo mantendremos.
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        // Lógica simplificada: contar indicadores con datos vs total
        List<ProyectoIndicadores> indicadores = objetivo02HambreCeroRepository.findIndicadoresByProyecto(proyectoId);
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
    public Map<String, Object> getOds02Statistics() {
        List<Proyectos> proyectos = objetivo02HambreCeroRepository.findAllProyectosOds02();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo02HambreCeroRepository.findIndicadoresByProyecto(p.getId()).stream())
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
        return objetivo02HambreCeroRepository.findProyectoOds02ById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean indicatorExists(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver true
        return true; // Placeholder
    }

    // ── IOdsBaseService implementations ──

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo02HambreCeroRepository.findAllProyectos();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findProyectoById(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo02HambreCeroRepository.saveProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo02HambreCeroRepository.updateProyecto(proyecto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo02HambreCeroRepository.deleteProyecto(proyectoId);
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
        return objetivo02HambreCeroRepository.findIndicadoresByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadores> findIndicadorById(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver empty
        return Optional.empty(); // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        return objetivo02HambreCeroRepository.saveIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo02HambreCeroRepository.updateIndicador(indicador);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteIndicador(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver false
        return false; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findMetasByProyecto(proyectoId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo02HambreCeroRepository.findMetaProyectoOds02ById(metaId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo02HambreCeroRepository.saveMetaProyecto(meta);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) {
        // Necesitaría método en repositorio, por ahora devolver la misma meta
        return meta; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteMetaProyecto(Integer metaId) {
        // Necesitaría método en repositorio, por ahora devolver false
        return false; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) {
        return objetivo02HambreCeroRepository.findMedicionesByIndicador(indicadorId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo02HambreCeroRepository.findMedicionHistoricaOds02ById(medicionId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo02HambreCeroRepository.saveMedicion(medicion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) {
        // Necesitaría método en repositorio, por ahora devolver la misma medición
        return medicion; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean deleteMedicionHistorica(Integer medicionId) {
        // Necesitaría método en repositorio, por ahora devolver false
        return false; // Placeholder
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
        return getOds02Statistics();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo02HambreCeroRepository.findProyectoById(proyectoId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsIndicador(Integer indicadorId) {
        // Necesitaría método en repositorio, por ahora devolver true
        return true; // Placeholder
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMetaProyecto(Integer metaId) {
        return objetivo02HambreCeroRepository.findMetaProyectoOds02ById(metaId).isPresent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo02HambreCeroRepository.findMedicionHistoricaOds02ById(medicionId).isPresent();
    }
}
