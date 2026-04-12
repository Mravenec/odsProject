package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;
import com.odsProject.odsProject.repository.Objetivo01PobrezaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo01PobrezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 1: Fin de la Pobreza
 */
@Service
public class Objetivo01PobrezaService implements IObjetivo01PobrezaService {

    @Autowired
    private Objetivo01PobrezaRepository objetivo01PobrezaRepository;

    @Autowired
    private com.odsProject.odsProject.service.interfaces.IEvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo01PobrezaRepository.findAllIndicadoresByProyectoOds01(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_1_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_2_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_2_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_3_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_4_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_4_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_3(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_4(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_a_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_a_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_a_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_b_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_b_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId) { return objetivo01PobrezaRepository.findAllIndicadoresByProyectoOds01(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo01PobrezaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds01() { return objetivo01PobrezaRepository.findAllProyectosOds01(); }
    @Override public Optional<Proyectos> getProjectOds01ById(Integer proyectoId) { return objetivo01PobrezaRepository.findProyectoOds01ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds01(Integer proyectoId) { return objetivo01PobrezaRepository.findAllMetasProyectoOds01(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds01ById(Integer metaId) { return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds01(Integer indicadorId) { return objetivo01PobrezaRepository.findAllMedicionesHistoricasOds01(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds01ById(Integer medicionId) { return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds01Statistics() {
        List<Proyectos> proyectos = objetivo01PobrezaRepository.findAllProyectosOds01();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo01PobrezaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo01PobrezaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo01PobrezaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo01PobrezaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo01PobrezaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo01PobrezaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo01PobrezaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo01PobrezaRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo01PobrezaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo01PobrezaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo01PobrezaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo01PobrezaRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo01PobrezaRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo01PobrezaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo01PobrezaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo01PobrezaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo01PobrezaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo01PobrezaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds01Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo01PobrezaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo01PobrezaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo01PobrezaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo01PobrezaRepository.existsMedicionHistorica(medicionId); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo01PobrezaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        // Obtener todos los parámetros de este indicador
        List<ProyectoIndicadorParametros> parametros = objetivo01PobrezaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo01PobrezaRepository.updateIndicador(indicador);
        } catch (Exception e) {
            // Log error or handle gracefully
            System.err.println("Error recalculando indicador " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
