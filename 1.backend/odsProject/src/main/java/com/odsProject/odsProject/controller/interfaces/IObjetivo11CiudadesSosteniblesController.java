package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 11
 */
@RequestMapping("/api/ods/11")
public interface IObjetivo11CiudadesSosteniblesController extends IOdsBaseController<
    VistaAdminDetalleIndicadores, // T - Lectura (Enriquecida)
    ProyectoIndicadores,          // E - Escritura (Tabla)
    Proyectos,                    // P - Proyectos
    ProyectoIndicadorParametros,  // M - Metas
    MedicionesHistoricas,         // MH - Mediciones
    AuditoriaOds11                // A - Auditoria
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 11: Ciudades y Comunidades Sostenibles
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.1.1
     * Proporción de la población urbana que vive en barrios marginales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.1.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.2.1
     * Proporción de la población con fácil acceso al transporte público
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.2.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.3.1
     * Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.3.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.3.2
     * Proporción de ciudades con participación directa de la sociedad civil
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.3.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.4.1
     * Total de gastos per cápita destinados a la preservación del patrimonio cultural
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.4.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.5.1
     * Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.5.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.5.2
     * Pérdidas económicas directas atribuidas a los desastres en relación con el PIB mundial
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.5.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.5.3
     * Daños en la infraestructura crítica e interrupciones de servicios básicos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.5.3
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_5_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.6.1
     * Proporción de residuos sólidos municipales recogidos y administrados
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.6.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.6.2
     * Niveles medios anuales de partículas finas en las ciudades
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.6.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_6_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.7.1
     * Proporción media de la superficie edificada dedicada a espacios abiertos públicos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.7.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.7.2
     * Proporción de personas que han sido víctimas de acoso en espacios públicos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.7.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_7_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.a.1
     * Número de países con políticas urbanas nacionales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.a.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.b.1
     * Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.b.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.b.2
     * Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.b.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_b_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 11.c.1
     * Total de asistencia oficial para el desarrollo destinada a infraestructuras urbanas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 11.c.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_11_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds11(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "11.1", "11.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
