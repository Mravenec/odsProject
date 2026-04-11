package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.AuditoriaOds17;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 17
 */
@RequestMapping("/api/ods/17")
public interface IObjetivo17AlianzasController extends IOdsBaseController<
    VistaAdminDetalleIndicadores, // T - Lectura (Enriquecida)
    ProyectoIndicadores,          // E - Escritura (Tabla)
    Proyectos,                    // P - Proyectos
    ProyectoIndicadorParametros,  // M - Metas
    MedicionesHistoricas,         // MH - Mediciones
    AuditoriaOds17                // A - Auditoria
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 17: Alianzas para Lograr los Objetivos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.1.1
     * Total de ingresos del gobierno en proporción al PIB, desglosado por fuente
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.1.1
     */    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.1.2
     * Proporción del presupuesto nacional financiado por impuestos internos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.1.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.2.1
     * Asistencia oficial para el desarrollo neta en proporción al INB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.2.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.3.1
     * Recursos financieros adicionales movilizados para países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.3.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.3.2
     * Volumen de remesas en proporción al PIB total
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.3.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.4.1
     * Servicio de la deuda en proporción a las exportaciones
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.4.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.5.1
     * Número de países que adoptan sistemas de promoción de inversiones
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.5.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.6.1
     * Número de abonados a servicios de banda ancha fija por cada 100 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.6.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.7.1
     * Total de fondos destinados a promover desarrollo, transferencia y difusión de tecnologías
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.7.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.8.1
     * Proporción de personas que utilizan Internet
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.8.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.9.1
     * Valor en dólares de la AOD comprometida para países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.9.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.10.1
     * Promedio arancelario mundial ponderado
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.10.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_10_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.11.1
     * Participación de países en desarrollo en las exportaciones mundiales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.11.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_11_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.12.1
     * Promedio ponderado de los aranceles que enfrentan países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.12.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_12_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.13.1
     * Tablero macroeconómico
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.13.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_13_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.14.1
     * Número de países con mecanismos para mejorar coherencia de políticas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.14.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_14_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.15.1
     * Grado de utilización de marcos de resultados y herramientas de planificación
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.15.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_15_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.16.1
     * Número de países que informan de progresos en marcos de múltiples interesados
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.16.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_16_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.17.1
     * Suma en dólares prometida a alianzas público-privadas centradas en infraestructura
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.17.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_17_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.18.1
     * Indicadores de la capacidad estadística
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.18.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_18_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.18.2
     * Número de países cuya legislación cumple Principios Fundamentales de Estadísticas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.18.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_18_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.18.3
     * Número de países con plan estadístico nacional plenamente financiado
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.18.3
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_18_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.19.1
     * Valor en dólares de recursos para fortalecer capacidad estadística
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.19.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_19_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 17.19.2
     * Proporción de países que han realizado censo y registrado nacimientos y defunciones
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 17.19.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_17_19_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds17(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "17.1", "17.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
