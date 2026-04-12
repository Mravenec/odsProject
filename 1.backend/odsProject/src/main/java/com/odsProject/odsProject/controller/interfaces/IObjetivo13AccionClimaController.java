package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.AuditoriaOds13;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 13: Acción por el Clima
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 13
 * Extiende IOdsBaseController con tipos específicos de ODS13
 */
@RequestMapping("/api/ods/13")
public interface IObjetivo13AccionClimaController extends IOdsBaseController<
    VistaAdminDetalleIndicadores, // T - Lectura (Enriquecida)
    ProyectoIndicadores,          // E - Escritura (Tabla)
    Proyectos,                    // P - Proyectos
    ProyectoIndicadorParametros,  // M - Metas
    MedicionesHistoricas,         // MH - Mediciones
    AuditoriaOds13                // A - Auditoria
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 13: Acción por el Clima
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.1.1
     * Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.1.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.1.2
     * Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.1.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.1.3
     * Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.1.3
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_1_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.2.1
     * Número de países con contribuciones determinadas a nivel nacional
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.2.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.2.2
     * Emisiones totales de gases de efecto invernadero por año
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.2.2
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.3.1
     * Grado en que se incorpora educación para el cambio climático
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.3.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.a.1
     * Cantidades proporcionadas y movilizadas en relación con el objetivo de 100.000 millones
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.a.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 13.b.1
     * Número de países menos adelantados y pequeños Estados insulares con planes climáticos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 13.b.1
     */
    ResponseEntity<Optional<VistaAdminDetalleIndicadores>> getIndicador_13_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS13
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findAllIndicadoresByProyectoOds13(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS13
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "13.1", "13.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<VistaAdminDetalleIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
