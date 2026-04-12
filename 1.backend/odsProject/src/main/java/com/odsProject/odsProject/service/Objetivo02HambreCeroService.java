package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.AuditoriaOds02;
import com.odsProject.odsProject.repository.Objetivo02HambreCeroRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo02HambreCeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 2: Hambre Cero
 */
@Service
public class Objetivo02HambreCeroService implements IObjetivo02HambreCeroService {

    @Autowired
    private Objetivo02HambreCeroRepository objetivo02HambreCeroRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo02HambreCeroRepository.findAllIndicadoresByProyectoOds02(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_1_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_1_2(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_2(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_3(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_2_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_2_4(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_2_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_3_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_3_2(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_4_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_5_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_5_2(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_a_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_a_2(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_a_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_b_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_2_c_1(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicador_2_c_1(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds02(Integer proyectoId) { return objetivo02HambreCeroRepository.findAllIndicadoresByProyectoOds02(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo02HambreCeroRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds02() { return objetivo02HambreCeroRepository.findAllProyectosOds02(); }
    @Override public Optional<Proyectos> getProjectOds02ById(Integer proyectoId) { return objetivo02HambreCeroRepository.findProyectoOds02ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds02(Integer proyectoId) { return objetivo02HambreCeroRepository.findAllMetasProyectoOds02(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds02ById(Integer metaId) { return objetivo02HambreCeroRepository.findMetaProyectoOds02ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds02(Integer indicadorId) { return objetivo02HambreCeroRepository.findAllMedicionesHistoricasOds02(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds02ById(Integer medicionId) { return objetivo02HambreCeroRepository.findMedicionHistoricaOds02ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo02HambreCeroRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds02Statistics() {
        List<Proyectos> proyectos = objetivo02HambreCeroRepository.findAllProyectosOds02();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo02HambreCeroRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo02HambreCeroRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo02HambreCeroRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo02HambreCeroRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo02HambreCeroRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo02HambreCeroRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo02HambreCeroRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo02HambreCeroRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo02HambreCeroRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo02HambreCeroRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo02HambreCeroRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo02HambreCeroRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo02HambreCeroRepository.findMetaProyectoOds02ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo02HambreCeroRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo02HambreCeroRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo02HambreCeroRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo02HambreCeroRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo02HambreCeroRepository.findMedicionHistoricaOds02ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo02HambreCeroRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo02HambreCeroRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo02HambreCeroRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds02Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo02HambreCeroRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo02HambreCeroRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo02HambreCeroRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo02HambreCeroRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo02HambreCeroRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo02HambreCeroRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo02HambreCeroRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo02HambreCeroRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS02 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
