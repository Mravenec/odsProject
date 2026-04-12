package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 1: Fin de la Pobreza
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 1
 * Extiende IOdsBaseController con tipos específicos de ODS01
 */
@RequestMapping("/api/ods/01")
public interface IObjetivo01PobrezaController extends IOdsBaseController<
    VistaAdminDetalleIndicadores, // T - Lectura (Enriquecida)
    ProyectoIndicadores,          // E - Escritura (Tabla)
    Proyectos,                    // P - Proyectos
    ProyectoIndicadorParametros,  // M - Metas
    MedicionesHistoricas,         // MH - Mediciones
    AuditoriaOds01                // A - Auditoria
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 1: Fin de la Pobreza
     * enriquecidos con metadatos de la base de datos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores enriquecidos
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId);
    
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_1_1(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_2_1(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_2_2(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_3_1(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_4_1(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_4_2(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_1(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_2(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_3(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_5_4(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_a_1(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_a_2(Integer proyectoId);
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_1_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS01
     * con metadatos enriquecidos (nombre, código)
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto enriquecidos
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds01(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS01
     * con metadatos enriquecidos (nombre, código)
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "1.1", "1.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
