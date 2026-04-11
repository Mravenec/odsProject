package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods06.tables.pojos.AuditoriaOds06;
import com.odsProject.odsProject.repository.Objetivo06AguaSaneamientoRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo06AguaSaneamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 6: Agua Limpia y Saneamiento
 */
@Service
public class Objetivo06AguaSaneamientoService implements IObjetivo06AguaSaneamientoService {

    @Autowired
    private Objetivo06AguaSaneamientoRepository objetivo06AguaSaneamientoRepository;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findAllIndicadoresByProyectoOds06(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_1_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_2_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_3_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_3_2(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_4_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_4_2(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_5_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_5_2(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_6_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_a_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_6_b_1(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicador_6_b_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds06(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findAllIndicadoresByProyectoOds06(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo06AguaSaneamientoRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds06() { return objetivo06AguaSaneamientoRepository.findAllProyectosOds06(); }
    @Override public Optional<Proyectos> getProjectOds06ById(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findProyectoOds06ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds06(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findAllMetasProyectoOds06(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds06ById(Integer metaId) { return objetivo06AguaSaneamientoRepository.findMetaProyectoOds06ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds06(Integer indicadorId) { return objetivo06AguaSaneamientoRepository.findAllMedicionesHistoricasOds06(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds06ById(Integer medicionId) { return objetivo06AguaSaneamientoRepository.findMedicionHistoricaOds06ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds06Statistics() {
        List<Proyectos> proyectos = objetivo06AguaSaneamientoRepository.findAllProyectosOds06();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo06AguaSaneamientoRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo06AguaSaneamientoRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo06AguaSaneamientoRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo06AguaSaneamientoRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo06AguaSaneamientoRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo06AguaSaneamientoRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo06AguaSaneamientoRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo06AguaSaneamientoRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo06AguaSaneamientoRepository.findMetaProyectoOds06ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo06AguaSaneamientoRepository.saveMetaProyecto(meta); }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo06AguaSaneamientoRepository.updateMetaProyecto(meta); }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo06AguaSaneamientoRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo06AguaSaneamientoRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo06AguaSaneamientoRepository.findMedicionHistoricaOds06ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo06AguaSaneamientoRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo06AguaSaneamientoRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo06AguaSaneamientoRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds06Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo06AguaSaneamientoRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo06AguaSaneamientoRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo06AguaSaneamientoRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo06AguaSaneamientoRepository.existsMedicionHistorica(medicionId); }
}
