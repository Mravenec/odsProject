package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.AuditoriaOds13;
import com.odsProject.odsProject.repository.Objetivo13AccionClimaticaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo13AccionClimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 13: Acción por el Clima
 */
@Service
public class Objetivo13AccionClimaService implements IObjetivo13AccionClimaService {

    @Autowired
    private Objetivo13AccionClimaticaRepository objetivo13AccionClimaRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo13AccionClimaRepository.findAllIndicadoresByProyectoOds13(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_1_1(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_1_2(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_1_3(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_1_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_2_1(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_2_2(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_2_3(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_2_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_3_1(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_3_2(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_a_1(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_b_1(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_13_b_2(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicador_13_b_2(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds13(Integer proyectoId) { return objetivo13AccionClimaRepository.findAllIndicadoresByProyectoOds13(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo13AccionClimaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds13() { return objetivo13AccionClimaRepository.findAllProyectosOds13(); }
    @Override public Optional<Proyectos> getProjectOds13ById(Integer proyectoId) { return objetivo13AccionClimaRepository.findProyectoOds13ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds13(Integer proyectoId) { return objetivo13AccionClimaRepository.findAllMetasProyectoOds13(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds13ById(Integer metaId) { return objetivo13AccionClimaRepository.findMetaProyectoOds13ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds13(Integer indicadorId) { return objetivo13AccionClimaRepository.findAllMedicionesHistoricasOds13(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds13ById(Integer medicionId) { return objetivo13AccionClimaRepository.findMedicionHistoricaOds13ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo13AccionClimaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds13Statistics() {
        List<Proyectos> proyectos = objetivo13AccionClimaRepository.findAllProyectosOds13();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo13AccionClimaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo13AccionClimaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo13AccionClimaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo13AccionClimaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo13AccionClimaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo13AccionClimaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo13AccionClimaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo13AccionClimaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo13AccionClimaRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo13AccionClimaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo13AccionClimaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo13AccionClimaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo13AccionClimaRepository.findMetaProyectoOds13ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo13AccionClimaRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo13AccionClimaRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo13AccionClimaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo13AccionClimaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo13AccionClimaRepository.findMedicionHistoricaOds13ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo13AccionClimaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo13AccionClimaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo13AccionClimaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds13Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo13AccionClimaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo13AccionClimaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo13AccionClimaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo13AccionClimaRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo13AccionClimaRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo13AccionClimaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo13AccionClimaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo13AccionClimaRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS13 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
