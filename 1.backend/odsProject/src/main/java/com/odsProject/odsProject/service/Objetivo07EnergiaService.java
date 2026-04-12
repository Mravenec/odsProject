package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.AuditoriaOds07;
import com.odsProject.odsProject.repository.Objetivo07EnergiaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo07EnergiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 7: Energía Asequible y No Contaminante
 */
@Service
public class Objetivo07EnergiaService implements IObjetivo07EnergiaService {

    @Autowired
    private Objetivo07EnergiaRepository objetivo07EnergiaRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo07EnergiaRepository.findAllIndicadoresByProyectoOds07(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_1_1(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_1_2(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_2_1(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_3_1(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_a_1(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_b_1(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_7_c_1(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicador_7_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId) { return objetivo07EnergiaRepository.findAllIndicadoresByProyectoOds07(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo07EnergiaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds07() { return objetivo07EnergiaRepository.findAllProyectosOds07(); }
    @Override public Optional<Proyectos> getProjectOds07ById(Integer proyectoId) { return objetivo07EnergiaRepository.findProyectoOds07ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds07(Integer proyectoId) { return objetivo07EnergiaRepository.findAllMetasProyectoOds07(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds07ById(Integer metaId) { return objetivo07EnergiaRepository.findMetaProyectoOds07ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds07(Integer indicadorId) { return objetivo07EnergiaRepository.findAllMedicionesHistoricasOds07(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds07ById(Integer medicionId) { return objetivo07EnergiaRepository.findMedicionHistoricaOds07ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo07EnergiaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds07Statistics() {
        List<Proyectos> proyectos = objetivo07EnergiaRepository.findAllProyectosOds07();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo07EnergiaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo07EnergiaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo07EnergiaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo07EnergiaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo07EnergiaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo07EnergiaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo07EnergiaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo07EnergiaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo07EnergiaRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo07EnergiaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo07EnergiaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo07EnergiaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo07EnergiaRepository.findMetaProyectoOds07ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo07EnergiaRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo07EnergiaRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo07EnergiaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo07EnergiaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo07EnergiaRepository.findMedicionHistoricaOds07ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo07EnergiaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo07EnergiaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo07EnergiaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds07Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo07EnergiaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo07EnergiaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo07EnergiaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo07EnergiaRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo07EnergiaRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo07EnergiaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo07EnergiaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo07EnergiaRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS07 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
