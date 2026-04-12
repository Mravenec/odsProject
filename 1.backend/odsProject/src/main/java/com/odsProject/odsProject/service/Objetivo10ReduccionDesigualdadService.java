package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods10.tables.pojos.AuditoriaOds10;
import com.odsProject.odsProject.repository.Objetivo10ReduccionDesigualdadRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo10ReduccionDesigualdadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 10: Reducción de las Desigualdades
 */
@Service
public class Objetivo10ReduccionDesigualdadService implements IObjetivo10ReduccionDesigualdadService {

    @Autowired
    private Objetivo10ReduccionDesigualdadRepository objetivo10ReduccionDesigualdadRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findAllIndicadoresByProyectoOds10(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_1_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_2_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_3_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_4_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_4_2(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_5_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_6_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_2(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_3(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_7_4(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_7_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_a_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_b_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_10_c_1(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicador_10_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds10(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findAllIndicadoresByProyectoOds10(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo10ReduccionDesigualdadRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds10() { return objetivo10ReduccionDesigualdadRepository.findAllProyectosOds10(); }
    @Override public Optional<Proyectos> getProjectOds10ById(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findProyectoOds10ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds10(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findAllMetasProyectoOds10(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds10ById(Integer metaId) { return objetivo10ReduccionDesigualdadRepository.findMetaProyectoOds10ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds10(Integer indicadorId) { return objetivo10ReduccionDesigualdadRepository.findAllMedicionesHistoricasOds10(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds10ById(Integer medicionId) { return objetivo10ReduccionDesigualdadRepository.findMedicionHistoricaOds10ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds10Statistics() {
        List<Proyectos> proyectos = objetivo10ReduccionDesigualdadRepository.findAllProyectosOds10();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo10ReduccionDesigualdadRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo10ReduccionDesigualdadRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo10ReduccionDesigualdadRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo10ReduccionDesigualdadRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo10ReduccionDesigualdadRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo10ReduccionDesigualdadRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo10ReduccionDesigualdadRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo10ReduccionDesigualdadRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo10ReduccionDesigualdadRepository.findMetaProyectoOds10ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo10ReduccionDesigualdadRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo10ReduccionDesigualdadRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo10ReduccionDesigualdadRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo10ReduccionDesigualdadRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo10ReduccionDesigualdadRepository.findMedicionHistoricaOds10ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo10ReduccionDesigualdadRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo10ReduccionDesigualdadRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo10ReduccionDesigualdadRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds10Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo10ReduccionDesigualdadRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo10ReduccionDesigualdadRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo10ReduccionDesigualdadRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo10ReduccionDesigualdadRepository.existsMedicionHistorica(medicionId); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo10ReduccionDesigualdadRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo10ReduccionDesigualdadRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo10ReduccionDesigualdadRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS10 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
