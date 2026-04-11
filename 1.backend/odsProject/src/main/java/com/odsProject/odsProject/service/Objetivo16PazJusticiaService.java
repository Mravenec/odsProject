package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;
import com.odsProject.odsProject.repository.Objetivo16PazJusticiaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo16PazJusticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 */
@Service
public class Objetivo16PazJusticiaService implements IObjetivo16PazJusticiaService {

    @Autowired
    private Objetivo16PazJusticiaRepository objetivo16PazJusticiaRepository;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo16PazJusticiaRepository.findAllIndicadoresByProyectoOds16(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_3(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_4(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_3(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_2_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_3(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_3_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_4_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_4_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_5_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_5_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_6_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_6_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_6_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_7_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_7_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_7_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_8_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_9_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_9_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_10_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_10_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_10_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_10_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_a_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_b_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_b_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId) { return objetivo16PazJusticiaRepository.findAllIndicadoresByProyectoOds16(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo16PazJusticiaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds16() { return objetivo16PazJusticiaRepository.findAllProyectosOds16(); }
    @Override public Optional<Proyectos> getProjectOds16ById(Integer proyectoId) { return objetivo16PazJusticiaRepository.findProyectoOds16ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds16(Integer proyectoId) { return objetivo16PazJusticiaRepository.findAllMetasProyectoOds16(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds16ById(Integer metaId) { return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds16(Integer indicadorId) { return objetivo16PazJusticiaRepository.findAllMedicionesHistoricasOds16(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds16ById(Integer medicionId) { return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds16Statistics() {
        List<Proyectos> proyectos = objetivo16PazJusticiaRepository.findAllProyectosOds16();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo16PazJusticiaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo16PazJusticiaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo16PazJusticiaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo16PazJusticiaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo16PazJusticiaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo16PazJusticiaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo16PazJusticiaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo16PazJusticiaRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo16PazJusticiaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo16PazJusticiaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo16PazJusticiaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo16PazJusticiaRepository.saveMetaProyecto(meta); }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo16PazJusticiaRepository.updateMetaProyecto(meta); }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo16PazJusticiaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo16PazJusticiaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo16PazJusticiaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo16PazJusticiaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo16PazJusticiaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds16Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo16PazJusticiaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo16PazJusticiaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo16PazJusticiaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo16PazJusticiaRepository.existsMedicionHistorica(medicionId); }
}
