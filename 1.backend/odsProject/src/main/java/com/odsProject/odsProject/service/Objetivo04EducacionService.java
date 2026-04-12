package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.AuditoriaOds04;
import com.odsProject.odsProject.repository.Objetivo04EducacionRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo04EducacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 4: Educación de Calidad
 */
@Service
public class Objetivo04EducacionService implements IObjetivo04EducacionService {

    @Autowired
    private Objetivo04EducacionRepository objetivo04EducacionRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo04EducacionRepository.findAllIndicadoresByProyectoOds04(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_1_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_1_2(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_2_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_2_2(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_3_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_4_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_5_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_6_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_7_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_a_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_b_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_4_c_1(Integer proyectoId) { return objetivo04EducacionRepository.findIndicador_4_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds04(Integer proyectoId) { return objetivo04EducacionRepository.findAllIndicadoresByProyectoOds04(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo04EducacionRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds04() { return objetivo04EducacionRepository.findAllProyectosOds04(); }
    @Override public Optional<Proyectos> getProjectOds04ById(Integer proyectoId) { return objetivo04EducacionRepository.findProyectoOds04ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds04(Integer proyectoId) { return objetivo04EducacionRepository.findAllMetasProyectoOds04(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds04ById(Integer metaId) { return objetivo04EducacionRepository.findMetaProyectoOds04ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds04(Integer indicadorId) { return objetivo04EducacionRepository.findAllMedicionesHistoricasOds04(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds04ById(Integer medicionId) { return objetivo04EducacionRepository.findMedicionHistoricaOds04ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo04EducacionRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds04Statistics() {
        List<Proyectos> proyectos = objetivo04EducacionRepository.findAllProyectosOds04();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo04EducacionRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo04EducacionRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo04EducacionRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo04EducacionRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo04EducacionRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo04EducacionRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo04EducacionRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo04EducacionRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo04EducacionRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo04EducacionRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo04EducacionRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo04EducacionRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo04EducacionRepository.findMetaProyectoOds04ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo04EducacionRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo04EducacionRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo04EducacionRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo04EducacionRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo04EducacionRepository.findMedicionHistoricaOds04ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo04EducacionRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo04EducacionRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo04EducacionRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds04Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo04EducacionRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo04EducacionRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo04EducacionRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo04EducacionRepository.existsMedicionHistorica(medicionId); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo04EducacionRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo04EducacionRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            if (p.getNombreVariable() != null) {
                paramsMap.put(p.getNombreVariable(), p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo04EducacionRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS04 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }
}
