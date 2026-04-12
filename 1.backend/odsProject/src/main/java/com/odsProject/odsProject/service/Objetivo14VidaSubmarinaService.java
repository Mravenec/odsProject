package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods14.tables.pojos.AuditoriaOds14;
import com.odsProject.odsProject.repository.Objetivo14VidaSubmarinaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo14VidaSubmarinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 14: Vida Submarina
 */
@Service
public class Objetivo14VidaSubmarinaService implements IObjetivo14VidaSubmarinaService {

    @Autowired
    private Objetivo14VidaSubmarinaRepository objetivo14VidaSubmarinaRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findAllIndicadoresByProyectoOds14(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_1_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_2_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_3_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_4_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_5_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_6_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_7_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_a_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_b_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_14_c_1(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicador_14_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds14(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findAllIndicadoresByProyectoOds14(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo14VidaSubmarinaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds14() { return objetivo14VidaSubmarinaRepository.findAllProyectosOds14(); }
    @Override public Optional<Proyectos> getProjectOds14ById(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findProyectoOds14ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds14(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findAllMetasProyectoOds14(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds14ById(Integer metaId) { return objetivo14VidaSubmarinaRepository.findMetaProyectoOds14ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds14(Integer indicadorId) { return objetivo14VidaSubmarinaRepository.findAllMedicionesHistoricasOds14(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds14ById(Integer medicionId) { return objetivo14VidaSubmarinaRepository.findMedicionHistoricaOds14ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds14Statistics() {
        List<Proyectos> proyectos = objetivo14VidaSubmarinaRepository.findAllProyectosOds14();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo14VidaSubmarinaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo14VidaSubmarinaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo14VidaSubmarinaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo14VidaSubmarinaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo14VidaSubmarinaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo14VidaSubmarinaRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo14VidaSubmarinaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo14VidaSubmarinaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo14VidaSubmarinaRepository.findMetaProyectoOds14ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo14VidaSubmarinaRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo14VidaSubmarinaRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo14VidaSubmarinaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo14VidaSubmarinaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo14VidaSubmarinaRepository.findMedicionHistoricaOds14ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo14VidaSubmarinaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo14VidaSubmarinaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo14VidaSubmarinaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds14Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo14VidaSubmarinaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo14VidaSubmarinaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo14VidaSubmarinaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo14VidaSubmarinaRepository.existsMedicionHistorica(medicionId); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo14VidaSubmarinaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo14VidaSubmarinaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo14VidaSubmarinaRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS14 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
