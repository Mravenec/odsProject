package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.AuditoriaOds05;
import com.odsProject.odsProject.repository.Objetivo05GeneroRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo05GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 5: Igualdad de Género
 */
@Service
public class Objetivo05GeneroService implements IObjetivo05GeneroService {

    @Autowired
    private Objetivo05GeneroRepository objetivo05GeneroRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo05GeneroRepository.findAllIndicadoresByProyectoOds05(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_1_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_2_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_2_2(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_3_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_3_2(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_4_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_5_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_5_2(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_6_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_6_2(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_6_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_a_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_a_2(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_a_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_b_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_5_c_1(Integer proyectoId) { return objetivo05GeneroRepository.findIndicador_5_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds05(Integer proyectoId) { return objetivo05GeneroRepository.findAllIndicadoresByProyectoOds05(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo05GeneroRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds05() { return objetivo05GeneroRepository.findAllProyectosOds05(); }
    @Override public Optional<Proyectos> getProjectOds05ById(Integer proyectoId) { return objetivo05GeneroRepository.findProyectoOds05ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds05(Integer proyectoId) { return objetivo05GeneroRepository.findAllMetasProyectoOds05(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds05ById(Integer metaId) { return objetivo05GeneroRepository.findMetaProyectoOds05ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds05(Integer indicadorId) { return objetivo05GeneroRepository.findAllMedicionesHistoricasOds05(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds05ById(Integer medicionId) { return objetivo05GeneroRepository.findMedicionHistoricaOds05ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo05GeneroRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds05Statistics() {
        List<Proyectos> proyectos = objetivo05GeneroRepository.findAllProyectosOds05();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo05GeneroRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo05GeneroRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo05GeneroRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo05GeneroRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo05GeneroRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo05GeneroRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo05GeneroRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo05GeneroRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo05GeneroRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo05GeneroRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo05GeneroRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo05GeneroRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo05GeneroRepository.findMetaProyectoOds05ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo05GeneroRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo05GeneroRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo05GeneroRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo05GeneroRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo05GeneroRepository.findMedicionHistoricaOds05ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo05GeneroRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo05GeneroRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo05GeneroRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds05Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo05GeneroRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo05GeneroRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo05GeneroRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo05GeneroRepository.existsMedicionHistorica(medicionId); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo05GeneroRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo05GeneroRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo05GeneroRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS05 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
