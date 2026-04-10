package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods12.tables.pojos.MedicionesHistoricas;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 12: Producción y Consumo Responsables
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 12
 */
@RequestMapping("/api/ods/12")
public interface IObjetivo12ConsumoProduccionController extends IOdsBaseController<
    ProyectoIndicadores,
    Proyectos,
    ProyectoIndicadorParametros,
    MedicionesHistoricas,
    Object
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 12: Producción y Consumo Responsables
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<ProyectoIndicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.1.1
     * Número de países con políticas de consumo y producción sostenibles
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.1.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.2.1
     * Huella material en términos absolutos, per cápita y por PIB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.2.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.2.2
     * Consumo material interno en términos absolutos, per cápita y por PIB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.2.2
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.3.1
     * Índice de pérdidas de alimentos y desperdicio de alimentos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.3.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.4.1
     * Número de partes en acuerdos sobre desechos peligrosos que cumplen sus compromisos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.4.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.4.2
     * Desechos peligrosos generados per cápita y proporción tratada
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.4.2
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.5.1
     * Tasa nacional de reciclado, en toneladas de material reciclado
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.5.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.6.1
     * Número de empresas que publican informes sobre sostenibilidad
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.6.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.7.1
     * Número de países con políticas de adquisiciones públicas sostenibles
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.7.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.8.1
     * Grado en que se incorpora educación para el desarrollo sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.8.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.a.1
     * Capacidad instalada de generación de energía renovable
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.a.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.b.1
     * Aplicación de instrumentos de contabilidad para turismo sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.b.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 12.c.1
     * Cuantía de los subsidios a los combustibles fósiles por unidad del PIB
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 12.c.1
     */
    ResponseEntity<Optional<ProyectoIndicadores>> getIndicador_12_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS12
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<ProyectoIndicadores>> findAllIndicadoresByProyectoOds12(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS12
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "12.1", "12.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<ProyectoIndicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
