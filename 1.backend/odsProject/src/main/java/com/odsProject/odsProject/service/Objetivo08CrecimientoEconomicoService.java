package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.AuditoriaOds08;
import com.odsProject.odsProject.repository.Objetivo08CrecimientoEconomicoRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo08CrecimientoEconomicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 */
@Service
public class Objetivo08CrecimientoEconomicoService implements IObjetivo08CrecimientoEconomicoService {

    @Autowired
    private Objetivo08CrecimientoEconomicoRepository objetivo08CrecimientoEconomicoRepository;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findAllIndicadoresByProyectoOds08(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_1_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_2_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_3_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_4_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_4_2(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_5_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_5_2(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_6_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_7_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_8_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_8_2(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_8_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_9_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_9_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_9_2(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_9_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_10_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_10_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_10_2(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_10_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_a_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_8_b_1(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicador_8_b_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds08(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findAllIndicadoresByProyectoOds08(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo08CrecimientoEconomicoRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds08() { return objetivo08CrecimientoEconomicoRepository.findAllProyectosOds08(); }
    @Override public Optional<Proyectos> getProjectOds08ById(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findProyectoOds08ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds08(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findAllMetasProyectoOds08(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds08ById(Integer metaId) { return objetivo08CrecimientoEconomicoRepository.findMetaProyectoOds08ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds08(Integer indicadorId) { return objetivo08CrecimientoEconomicoRepository.findAllMedicionesHistoricasOds08(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds08ById(Integer medicionId) { return objetivo08CrecimientoEconomicoRepository.findMedicionHistoricaOds08ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds08Statistics() {
        List<Proyectos> proyectos = objetivo08CrecimientoEconomicoRepository.findAllProyectosOds08();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo08CrecimientoEconomicoRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo08CrecimientoEconomicoRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo08CrecimientoEconomicoRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo08CrecimientoEconomicoRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo08CrecimientoEconomicoRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo08CrecimientoEconomicoRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo08CrecimientoEconomicoRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo08CrecimientoEconomicoRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo08CrecimientoEconomicoRepository.findMetaProyectoOds08ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo08CrecimientoEconomicoRepository.saveMetaProyecto(meta); }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo08CrecimientoEconomicoRepository.updateMetaProyecto(meta); }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo08CrecimientoEconomicoRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo08CrecimientoEconomicoRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo08CrecimientoEconomicoRepository.findMedicionHistoricaOds08ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo08CrecimientoEconomicoRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo08CrecimientoEconomicoRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo08CrecimientoEconomicoRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds08Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo08CrecimientoEconomicoRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo08CrecimientoEconomicoRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo08CrecimientoEconomicoRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo08CrecimientoEconomicoRepository.existsMedicionHistorica(medicionId); }
}
