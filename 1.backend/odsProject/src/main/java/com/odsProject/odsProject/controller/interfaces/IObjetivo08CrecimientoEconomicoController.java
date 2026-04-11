package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.AuditoriaOds08;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 8
 * Extiende IOdsBaseController con tipos específicos de ODS08
 */
@RequestMapping("/api/ods/08")
public interface IObjetivo08CrecimientoEconomicoController extends IOdsBaseController<
    VistaAdminDetalleIndicadores, // T - Lectura (Enriquecida)
    ProyectoIndicadores,          // E - Escritura (Tabla)
    Proyectos,                    // P - Proyectos
    ProyectoIndicadorParametros,  // M - Metas
    MedicionesHistoricas,         // MH - Mediciones
    AuditoriaOds08                // A - Auditoria
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 8: Trabajo Decente y Crecimiento Económico
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.1.1
     * Tasa de crecimiento anual del PIB real per cápita
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.1.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.2.1
     * Tasa de crecimiento anual del PIB real por persona empleada
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.2.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.3.1
     * Proporción de empleo informal con respecto al empleo total
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.3.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.4.1
     * Huella material en términos absolutos, per cápita y por PIB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.4.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.4.2
     * Consumo material interno en términos absolutos, per cápita y por PIB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.4.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.5.1
     * Ingreso medio por hora de las personas empleadas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.5.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.5.2
     * Tasa de desempleo, desglosada por sexo, edad y personas con discapacidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.5.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.6.1
     * Proporción de jóvenes que no estudian ni trabajan
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.6.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.7.1
     * Proporción de niños que realizan trabajo infantil
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.7.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.8.1
     * Lesiones ocupacionales mortales y no mortales por cada 100.000 trabajadores
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.8.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.8.2
     * Nivel de cumplimiento nacional de los derechos laborales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.8.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_8_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.9.1
     * PIB generado directamente por el turismo en proporción al PIB total
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.9.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.9.2
     * Personas empleadas en el sector del turismo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.9.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_9_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.10.1
     * Número de sucursales de bancos y cajeros automáticos por cada 100.000 adultos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.10.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_10_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.10.2
     * Proporción de adultos que tienen una cuenta en banco
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.10.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_10_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.a.1
     * Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.a.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.b.1
     * Existencia de una estrategia nacional para el empleo de los jóvenes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 8.b.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_8_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS08
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds08(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS08
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "8.1", "8.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
