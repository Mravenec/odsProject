package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.AuditoriaOds12;
import com.odsProject.odsProject.repository.Objetivo12ConsumoProduccionRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo12ConsumoProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 12: Producción y Consumo Responsables
 */
@Service
public class Objetivo12ConsumoProduccionService implements IObjetivo12ConsumoProduccionService {

    @Autowired
    private Objetivo12ConsumoProduccionRepository objetivo12ConsumoProduccionRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findAllIndicadoresByProyectoOds12(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_1_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_2_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_2_2(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_3_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_4_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_4_2(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_5_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_6_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_7_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_8_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_a_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_b_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_12_c_1(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicador_12_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds12(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findAllIndicadoresByProyectoOds12(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo12ConsumoProduccionRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds12() { return objetivo12ConsumoProduccionRepository.findAllProyectosOds12(); }
    @Override public Optional<Proyectos> getProjectOds12ById(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findProyectoOds12ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds12(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findAllMetasProyectoOds12(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds12ById(Integer metaId) { return objetivo12ConsumoProduccionRepository.findMetaProyectoOds12ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds12(Integer indicadorId) { return objetivo12ConsumoProduccionRepository.findAllMedicionesHistoricasOds12(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds12ById(Integer medicionId) { return objetivo12ConsumoProduccionRepository.findMedicionHistoricaOds12ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds12Statistics() {
        List<Proyectos> proyectos = objetivo12ConsumoProduccionRepository.findAllProyectosOds12();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo12ConsumoProduccionRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo12ConsumoProduccionRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo12ConsumoProduccionRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo12ConsumoProduccionRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo12ConsumoProduccionRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo12ConsumoProduccionRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo12ConsumoProduccionRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo12ConsumoProduccionRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo12ConsumoProduccionRepository.findMetaProyectoOds12ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo12ConsumoProduccionRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo12ConsumoProduccionRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo12ConsumoProduccionRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo12ConsumoProduccionRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo12ConsumoProduccionRepository.findMedicionHistoricaOds12ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo12ConsumoProduccionRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo12ConsumoProduccionRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo12ConsumoProduccionRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds12Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo12ConsumoProduccionRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo12ConsumoProduccionRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo12ConsumoProduccionRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo12ConsumoProduccionRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo12ConsumoProduccionRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo12ConsumoProduccionRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo12ConsumoProduccionRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
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
            objetivo12ConsumoProduccionRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS12 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
