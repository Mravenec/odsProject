package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods15.tables.pojos.AuditoriaOds15;
import com.odsProject.odsProject.repository.Objetivo15VidaEcosistemasRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo15VidaEcosistemasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 15: Vida de Ecosistemas Terrestres
 */
@Service
public class Objetivo15VidaEcosistemasService implements IObjetivo15VidaEcosistemasService {

    @Autowired
    private Objetivo15VidaEcosistemasRepository objetivo15VidaEcosistemasRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findAllIndicadoresByProyectoOds15(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_1_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_1_2(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_2_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_3_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_4_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_4_2(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_5_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_6_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_7_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_8_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_9_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_9_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_a_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_b_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_15_c_1(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicador_15_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds15(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findAllIndicadoresByProyectoOds15(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo15VidaEcosistemasRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds15() { return objetivo15VidaEcosistemasRepository.findAllProyectosOds15(); }
    @Override public Optional<Proyectos> getProjectOds15ById(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findProyectoOds15ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds15(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findAllMetasProyectoOds15(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds15ById(Integer metaId) { return objetivo15VidaEcosistemasRepository.findMetaProyectoOds15ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds15(Integer indicadorId) { return objetivo15VidaEcosistemasRepository.findAllMedicionesHistoricasOds15(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds15ById(Integer medicionId) { return objetivo15VidaEcosistemasRepository.findMedicionHistoricaOds15ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds15Statistics() {
        List<Proyectos> proyectos = objetivo15VidaEcosistemasRepository.findAllProyectosOds15();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo15VidaEcosistemasRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo15VidaEcosistemasRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo15VidaEcosistemasRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo15VidaEcosistemasRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo15VidaEcosistemasRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo15VidaEcosistemasRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo15VidaEcosistemasRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo15VidaEcosistemasRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo15VidaEcosistemasRepository.findMetaProyectoOds15ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo15VidaEcosistemasRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo15VidaEcosistemasRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo15VidaEcosistemasRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo15VidaEcosistemasRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo15VidaEcosistemasRepository.findMedicionHistoricaOds15ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo15VidaEcosistemasRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo15VidaEcosistemasRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo15VidaEcosistemasRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds15Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo15VidaEcosistemasRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo15VidaEcosistemasRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo15VidaEcosistemasRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo15VidaEcosistemasRepository.existsMedicionHistorica(medicionId); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo15VidaEcosistemasRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo15VidaEcosistemasRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo15VidaEcosistemasRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS15 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
