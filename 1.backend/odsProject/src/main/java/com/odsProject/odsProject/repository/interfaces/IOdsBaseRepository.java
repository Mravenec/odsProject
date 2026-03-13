package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Interfaz base para repositorios ODS usando jOOQ
 * Define los métodos comunes CRUD y consultas específicas ODS
 * @param <T> Tipo de entidad (Indicadores, Proyectos, etc.)
 */
public interface IOdsBaseRepository<T> {
    
    // ── Proyectos ──
    List<Proyectos> findAllProyectos();
    Optional<Proyectos> findProyectoById(Integer id);
    List<Proyectos> findProyectosByUsuario(Integer usuarioId);
    List<Proyectos> findProyectosByEstado(String estado);
    Proyectos saveProyecto(Proyectos proyecto);
    Proyectos updateProyecto(Proyectos proyecto);
    void deleteProyecto(Integer id);
    
    // ── Indicadores ──
    List<Indicadores> findIndicadoresByProyecto(Integer proyectoId);
    Optional<Indicadores> findIndicadorByCodigo(Integer proyectoId, String codigo);
    List<Indicadores> findIndicadoresByCodigoPrefix(String prefix);
    Indicadores saveIndicador(Indicadores indicador);
    Indicadores updateIndicador(Indicadores indicador);
    
    // ── Metas ──
    List<MetasProyecto> findMetasByProyecto(Integer proyectoId);
    MetasProyecto saveMetaProyecto(MetasProyecto meta);
    
    // ── Mediciones ──
    List<MedicionesHistoricas> findMedicionesByIndicador(Integer indicadorId);
    MedicionesHistoricas saveMedicion(MedicionesHistoricas medicion);
    
    // ── Auditoría ──
    List<AuditoriaOds01> findAuditoriaReciente(Integer dias);
    List<AuditoriaOds01> findAuditoriaByRegistro(String tablaAfectada, Integer registroId);
    
    // ── Stored Procedures ──
    Map<String, Object> spAdminDashboard();
    Map<String, Object> spAdminReporteProyecto(Integer proyectoId);
}
