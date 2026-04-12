package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.AuditoriaOds03;
import com.odsProject.odsProject.repository.Objetivo03SaludBienestarRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo03SaludBienestarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 3: Salud y Bienestar
 */
@Service
public class Objetivo03SaludBienestarService implements IObjetivo03SaludBienestarService {

    @Autowired
    private Objetivo03SaludBienestarRepository objetivo03SaludBienestarRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo03SaludBienestarRepository.findAllIndicadoresByProyectoOds03(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_1_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_1_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_2_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_2_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_3(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_3_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_4(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_3_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_3_5(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_3_5(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_4_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_4_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_5_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_5_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_6_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_6_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_6_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_7_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_7_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_7_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_8_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_8_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_8_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_9_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_9_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_9_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_9_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_9_3(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_9_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_a_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_b_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_b_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_b_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_b_3(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_b_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_c_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_c_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_d_1(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_d_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_3_d_2(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicador_3_d_2(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId) { return objetivo03SaludBienestarRepository.findAllIndicadoresByProyectoOds03(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo03SaludBienestarRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds03() { return objetivo03SaludBienestarRepository.findAllProyectosOds03(); }
    @Override public Optional<Proyectos> getProjectOds03ById(Integer proyectoId) { return objetivo03SaludBienestarRepository.findProyectoOds03ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds03(Integer proyectoId) { return objetivo03SaludBienestarRepository.findAllMetasProyectoOds03(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds03ById(Integer metaId) { return objetivo03SaludBienestarRepository.findMetaProyectoOds03ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds03(Integer indicadorId) { return objetivo03SaludBienestarRepository.findAllMedicionesHistoricasOds03(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds03ById(Integer medicionId) { return objetivo03SaludBienestarRepository.findMedicionHistoricaOds03ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo03SaludBienestarRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds03Statistics() {
        List<Proyectos> proyectos = objetivo03SaludBienestarRepository.findAllProyectosOds03();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo03SaludBienestarRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo03SaludBienestarRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo03SaludBienestarRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo03SaludBienestarRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo03SaludBienestarRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo03SaludBienestarRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo03SaludBienestarRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo03SaludBienestarRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo03SaludBienestarRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo03SaludBienestarRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo03SaludBienestarRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo03SaludBienestarRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo03SaludBienestarRepository.findMetaProyectoOds03ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo03SaludBienestarRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo03SaludBienestarRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo03SaludBienestarRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo03SaludBienestarRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo03SaludBienestarRepository.findMedicionHistoricaOds03ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo03SaludBienestarRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo03SaludBienestarRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo03SaludBienestarRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds03Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo03SaludBienestarRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo03SaludBienestarRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo03SaludBienestarRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo03SaludBienestarRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo03SaludBienestarRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo03SaludBienestarRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo03SaludBienestarRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo03SaludBienestarRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS03 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
