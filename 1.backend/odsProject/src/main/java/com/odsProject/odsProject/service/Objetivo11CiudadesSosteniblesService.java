package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;
import com.odsProject.odsProject.repository.Objetivo11CiudadesSosteniblesRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo11CiudadesSosteniblesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 */
@Service
public class Objetivo11CiudadesSosteniblesService implements IObjetivo11CiudadesSosteniblesService {

    @Autowired
    private Objetivo11CiudadesSosteniblesRepository objetivo11CiudadesSosteniblesRepository;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findAllIndicadoresByProyectoOds11(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_1_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_2_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_3_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_3_2(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_4_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_5_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_5_2(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_5_3(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_5_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_6_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_6_2(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_6_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_7_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_7_2(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_7_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_a_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_b_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_b_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_b_2(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_b_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_11_c_1(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicador_11_c_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findAllIndicadoresByProyectoOds11(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo11CiudadesSosteniblesRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds11() { return objetivo11CiudadesSosteniblesRepository.findAllProyectosOds11(); }
    @Override public Optional<Proyectos> getProjectOds11ById(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findProyectoOds11ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds11(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findAllMetasProyectoOds11(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds11ById(Integer metaId) { return objetivo11CiudadesSosteniblesRepository.findMetaProyectoOds11ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds11(Integer indicadorId) { return objetivo11CiudadesSosteniblesRepository.findAllMedicionesHistoricasOds11(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds11ById(Integer medicionId) { return objetivo11CiudadesSosteniblesRepository.findMedicionHistoricaOds11ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds11Statistics() {
        List<Proyectos> proyectos = objetivo11CiudadesSosteniblesRepository.findAllProyectosOds11();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo11CiudadesSosteniblesRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo11CiudadesSosteniblesRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo11CiudadesSosteniblesRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo11CiudadesSosteniblesRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo11CiudadesSosteniblesRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) { return objetivo11CiudadesSosteniblesRepository.saveIndicador(indicador); }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo11CiudadesSosteniblesRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo11CiudadesSosteniblesRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo11CiudadesSosteniblesRepository.findMetaProyectoOds11ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo11CiudadesSosteniblesRepository.saveMetaProyecto(meta); }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { return objetivo11CiudadesSosteniblesRepository.updateMetaProyecto(meta); }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo11CiudadesSosteniblesRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo11CiudadesSosteniblesRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo11CiudadesSosteniblesRepository.findMedicionHistoricaOds11ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo11CiudadesSosteniblesRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo11CiudadesSosteniblesRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo11CiudadesSosteniblesRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds11Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo11CiudadesSosteniblesRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo11CiudadesSosteniblesRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo11CiudadesSosteniblesRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo11CiudadesSosteniblesRepository.existsMedicionHistorica(medicionId); }
}
