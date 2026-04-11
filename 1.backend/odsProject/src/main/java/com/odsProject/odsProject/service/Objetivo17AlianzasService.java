package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.AuditoriaOds17;
import com.odsProject.odsProject.repository.Objetivo17AlianzasRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo17AlianzasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 17: Alianzas para los Objetivos
 */
@Service
public class Objetivo17AlianzasService implements IObjetivo17AlianzasService {

    @Autowired
    private Objetivo17AlianzasRepository objetivo17AlianzasRepository;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo17AlianzasRepository.findAllIndicadoresByProyectoOds17(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_1_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_1_2(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_2_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_3_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_3_2(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_4_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_5_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_6_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_7_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_8_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_9_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_9_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_10_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_10_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_11_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_11_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_12_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_12_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_13_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_13_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_14_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_14_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_15_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_15_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_16_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_16_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_17_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_17_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_18_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_18_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_18_2(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_18_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_18_3(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_18_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_19_1(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_19_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_17_19_2(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicador_17_19_2(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId) { return objetivo17AlianzasRepository.findAllIndicadoresByProyectoOds17(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo17AlianzasRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds17() { return objetivo17AlianzasRepository.findAllProyectosOds17(); }
    @Override public Optional<Proyectos> getProjectOds17ById(Integer proyectoId) { return objetivo17AlianzasRepository.findProyectoOds17ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds17(Integer proyectoId) { return objetivo17AlianzasRepository.findAllMetasProyectoOds17(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds17ById(Integer metaId) { return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds17(Integer indicadorId) { return objetivo17AlianzasRepository.findAllMedicionesHistoricasOds17(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds17ById(Integer medicionId) { return objetivo17AlianzasRepository.findMedicionHistoricaOds17ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds17Statistics() {
        List<Proyectos> proyectos = objetivo17AlianzasRepository.findAllProyectosOds17();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo17AlianzasRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo17AlianzasRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo17AlianzasRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo17AlianzasRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo17AlianzasRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo17AlianzasRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo17AlianzasRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo17AlianzasRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo17AlianzasRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo17AlianzasRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo17AlianzasRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo17AlianzasRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo17AlianzasRepository.findMetaProyectoOds17ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo17AlianzasRepository.saveMetaProyecto(meta); }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo17AlianzasRepository.updateMetaProyecto(meta); }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo17AlianzasRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo17AlianzasRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo17AlianzasRepository.findMedicionHistoricaOds17ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo17AlianzasRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo17AlianzasRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo17AlianzasRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds17Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo17AlianzasRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo17AlianzasRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo17AlianzasRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo17AlianzasRepository.existsMedicionHistorica(medicionId); }
}
