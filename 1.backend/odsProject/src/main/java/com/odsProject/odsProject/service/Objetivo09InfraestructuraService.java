package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.AuditoriaOds09;
import com.odsProject.odsProject.repository.Objetivo09InfraestructuraRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo09InfraestructuraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 9: Industria, Innovación e Infraestructura
 */
@Service
public class Objetivo09InfraestructuraService implements IObjetivo09InfraestructuraService {

    @Autowired
    private Objetivo09InfraestructuraRepository objetivo09InfraestructuraRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo09InfraestructuraRepository.findAllIndicadoresByProyectoOds09(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_1_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_1_2(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_2_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_2_2(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_3_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_3_2(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_4_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_5_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_5_2(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_a_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_b_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_9_c_1(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicador_9_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds09(Integer proyectoId) { return objetivo09InfraestructuraRepository.findAllIndicadoresByProyectoOds09(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo09InfraestructuraRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds09() { return objetivo09InfraestructuraRepository.findAllProyectosOds09(); }
    @Override public Optional<Proyectos> getProjectOds09ById(Integer proyectoId) { return objetivo09InfraestructuraRepository.findProyectoOds09ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds09(Integer proyectoId) { return objetivo09InfraestructuraRepository.findAllMetasProyectoOds09(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds09ById(Integer metaId) { return objetivo09InfraestructuraRepository.findMetaProyectoOds09ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds09(Integer indicadorId) { return objetivo09InfraestructuraRepository.findAllMedicionesHistoricasOds09(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds09ById(Integer medicionId) { return objetivo09InfraestructuraRepository.findMedicionHistoricaOds09ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo09InfraestructuraRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds09Statistics() {
        List<Proyectos> proyectos = objetivo09InfraestructuraRepository.findAllProyectosOds09();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo09InfraestructuraRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo09InfraestructuraRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo09InfraestructuraRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo09InfraestructuraRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo09InfraestructuraRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo09InfraestructuraRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo09InfraestructuraRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo09InfraestructuraRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo09InfraestructuraRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo09InfraestructuraRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo09InfraestructuraRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo09InfraestructuraRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo09InfraestructuraRepository.findMetaProyectoOds09ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo09InfraestructuraRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo09InfraestructuraRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo09InfraestructuraRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo09InfraestructuraRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo09InfraestructuraRepository.findMedicionHistoricaOds09ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo09InfraestructuraRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo09InfraestructuraRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo09InfraestructuraRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds09Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo09InfraestructuraRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo09InfraestructuraRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo09InfraestructuraRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo09InfraestructuraRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo09InfraestructuraRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo09InfraestructuraRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo09InfraestructuraRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            String varName = p.getNombreVariable() != null ? p.getNombreVariable() : p.getNombreParametro();
            if (varName != null) {
                paramsMap.put(varName, p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo09InfraestructuraRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS09 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
