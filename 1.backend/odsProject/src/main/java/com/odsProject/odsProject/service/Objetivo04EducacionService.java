package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.repository.Objetivo04EducacionRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo04EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 4: Educación de Calidad
 * Implementa la lógica de negocio para los indicadores del ODS4
 * Usa Objetivo04EducacionRepository para el acceso a datos
 */
@Service
public class Objetivo04EducacionService implements IObjetivo04EducacionService {

    @Autowired
    private Objetivo04EducacionRepository objetivo04EducacionRepository;

    // ── Indicadores Específicos del ODS04 ──

    @Override
    public List<ProyectoIndicadores> getAllIndicators(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicadoresByProyecto(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_1_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_1_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_1_2(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_1_2(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_2_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_2_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_2_2(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_2_2(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_3_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_3_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_4_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_4_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_5_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_5_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_6_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_6_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_7_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_7_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_a_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_a_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_b_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_b_1(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> getIndicador_4_c_1(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicador_4_c_1(proyectoId);
    }

    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyectoOds04(Integer proyectoId) {
        return objetivo04EducacionRepository.findAllIndicadoresByProyectoOds04(proyectoId);
    }

    @Override
    public List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) {
        return objetivo04EducacionRepository.findIndicadoresByMeta(proyectoId, metaPrefix);
    }

    // ── Métodos CRUD y Utilidades (para alinear con Repository) ──

    @Override
    public List<Proyectos> getAllProjectsOds04() {
        return objetivo04EducacionRepository.findAllProyectosOds04();
    }

    @Override
    public Optional<Proyectos> getProjectOds04ById(Integer proyectoId) {
        return objetivo04EducacionRepository.findProyectoOds04ById(proyectoId);
    }

    @Override
    public List<ProyectoIndicadorParametros> getAllMetasProyectoOds04(Integer proyectoId) {
        return objetivo04EducacionRepository.findAllMetasProyectoOds04(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadorParametros> getMetaProyectoOds04ById(Integer metaId) {
        return objetivo04EducacionRepository.findMetaProyectoOds04ById(metaId);
    }

    @Override
    public List<MedicionesHistoricas> getAllMedicionesHistoricasOds04(Integer indicadorId) {
        return objetivo04EducacionRepository.findAllMedicionesHistoricasOds04(indicadorId);
    }

    @Override
    public Optional<MedicionesHistoricas> getMedicionHistoricaOds04ById(Integer medicionId) {
        return objetivo04EducacionRepository.findMedicionHistoricaOds04ById(medicionId);
    }

    @Override
    public Boolean validateIndicatorData(ProyectoIndicadores indicador) {
        if (indicador == null) return false;
        if (indicador.getProyectoId() == null) return false;
        if (indicador.getIndicadorMasterId() == null) return false;
        return true;
    }

    @Override
    public Double calculateProjectProgress(Integer proyectoId) {
        List<ProyectoIndicadores> indicadores = objetivo04EducacionRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream()
            .filter(ind -> ind.getValorActual() != null)
            .count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override
    public Map<String, Object> getOds04Statistics() {
        List<Proyectos> proyectos = objetivo04EducacionRepository.findAllProyectosOds04();
        List<ProyectoIndicadores> indicadores = proyectos.stream()
            .flatMap(p -> objetivo04EducacionRepository.findIndicadoresByProyecto(p.getId()).stream())
            .toList();
        return Map.of(
            "totalProyectos", proyectos.size(),
            "totalIndicadores", indicadores.size(),
            "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count()
        );
    }

    @Override
    public Boolean projectExists(Integer proyectoId) {
        return objetivo04EducacionRepository.findProyectoOds04ById(proyectoId).isPresent();
    }

    @Override
    public Boolean indicatorExists(Integer indicadorId) {
        return true;
    }

    // ── IOdsBaseService implementations ──

    @Override
    public List<Proyectos> findAllProyectos() {
        return objetivo04EducacionRepository.findAllProyectos();
    }

    @Override
    public Optional<Proyectos> findProyectoById(Integer proyectoId) {
        return objetivo04EducacionRepository.findProyectoById(proyectoId);
    }

    @Override
    public Proyectos saveProyecto(Proyectos proyecto) {
        return objetivo04EducacionRepository.saveProyecto(proyecto);
    }

    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        return objetivo04EducacionRepository.updateProyecto(proyecto);
    }

    @Override
    public Boolean deleteProyecto(Integer proyectoId) {
        try {
            objetivo04EducacionRepository.deleteProyecto(proyectoId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<ProyectoIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) {
        return objetivo04EducacionRepository.findIndicadoresByProyecto(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadores> findIndicadorById(Integer indicadorId) {
        return Optional.empty();
    }

    @Override
    public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        return objetivo04EducacionRepository.saveIndicador(indicador);
    }

    @Override
    public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) {
        return objetivo04EducacionRepository.updateIndicador(indicador);
    }

    @Override
    public Boolean deleteIndicador(Integer indicadorId) {
        return false;
    }

    @Override
    public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) {
        return objetivo04EducacionRepository.findMetasByProyecto(proyectoId);
    }

    @Override
    public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) {
        return objetivo04EducacionRepository.findMetaProyectoOds04ById(metaId);
    }

    @Override
    public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) {
        return objetivo04EducacionRepository.saveMetaProyecto(meta);
    }

    @Override
    public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) {
        return meta;
    }

    @Override
    public Boolean deleteMetaProyecto(Integer metaId) {
        return false;
    }

    @Override
    public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) {
        return objetivo04EducacionRepository.findMedicionesByIndicador(indicadorId);
    }

    @Override
    public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) {
        return objetivo04EducacionRepository.findMedicionHistoricaOds04ById(medicionId);
    }

    @Override
    public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) {
        return objetivo04EducacionRepository.saveMedicion(medicion);
    }

    @Override
    public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) {
        return medicion;
    }

    @Override
    public Boolean deleteMedicionHistorica(Integer medicionId) {
        return false;
    }

    @Override
    public Boolean validateProjectData(Proyectos proyecto) {
        if (proyecto == null) return false;
        if (proyecto.getUsuarioId() == null) return false;
        return true;
    }

    @Override
    public Map<String, Object> getOdsStatistics() {
        return getOds04Statistics();
    }

    @Override
    public Boolean existsProyecto(Integer proyectoId) {
        return objetivo04EducacionRepository.findProyectoById(proyectoId).isPresent();
    }

    @Override
    public Boolean existsIndicador(Integer indicadorId) {
        return true;
    }

    @Override
    public Boolean existsMetaProyecto(Integer metaId) {
        return objetivo04EducacionRepository.findMetaProyectoOds04ById(metaId).isPresent();
    }

    @Override
    public Boolean existsMedicionHistorica(Integer medicionId) {
        return objetivo04EducacionRepository.findMedicionHistoricaOds04ById(medicionId).isPresent();
    }
}
