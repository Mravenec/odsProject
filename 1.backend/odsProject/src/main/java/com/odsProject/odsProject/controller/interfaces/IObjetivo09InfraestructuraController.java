package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 9: Industria, Innovación e Infraestructura
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 9
 */
public interface IObjetivo09InfraestructuraController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 9: Industria, Innovación e Infraestructura
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 9.1.1
     * Proporción de la población rural que vive cerca de una carretera transitable
     * 
     * @return ResponseEntity con los datos del indicador 9.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_1_1();
    
    /**
     * Obtiene el indicador 9.1.2
     * Volumen de transporte de pasajeros y carga, desglosado por medio de transporte
     * 
     * @return ResponseEntity con los datos del indicador 9.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_9_1_2();
    
    /**
     * Obtiene el indicador 9.2.1
     * Valor añadido del sector manufacturo en proporción al PIB y per cápita
     * 
     * @return ResponseEntity con los datos del indicador 9.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_2_1();
    
    /**
     * Obtiene el indicador 9.2.2
     * Empleo del sector manufacturero en proporción al empleo total
     * 
     * @return ResponseEntity con los datos del indicador 9.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_9_2_2();
    
    /**
     * Obtiene el indicador 9.3.1
     * Proporción del valor añadido total correspondiente a las pequeñas industrias
     * 
     * @return ResponseEntity con los datos del indicador 9.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_3_1();
    
    /**
     * Obtiene el indicador 9.3.2
     * Proporción de las pequeñas industrias que han obtenido un préstamo o línea de crédito
     * 
     * @return ResponseEntity con los datos del indicador 9.3.2
     */
    ResponseEntity<IndicatorData> getIndicador_9_3_2();
    
    /**
     * Obtiene el indicador 9.4.1
     * Emisiones de CO2 por unidad de valor añadido
     * 
     * @return ResponseEntity con los datos del indicador 9.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_4_1();
    
    /**
     * Obtiene el indicador 9.5.1
     * Gastos en investigación y desarrollo en proporción al PIB
     * 
     * @return ResponseEntity con los datos del indicador 9.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_5_1();
    
    /**
     * Obtiene el indicador 9.5.2
     * Número de investigadores por cada millón de habitantes
     * 
     * @return ResponseEntity con los datos del indicador 9.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_9_5_2();
    
    /**
     * Obtiene el indicador 9.a.1
     * Total de apoyo internacional oficial destinado a la infraestructura
     * 
     * @return ResponseEntity con los datos del indicador 9.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_a_1();
    
    /**
     * Obtiene el indicador 9.b.1
     * Proporción del valor añadido por la industria de tecnología mediana y alta
     * 
     * @return ResponseEntity con los datos del indicador 9.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_b_1();
    
    /**
     * Obtiene el indicador 9.c.1
     * Proporción de la población con cobertura de red móvil
     * 
     * @return ResponseEntity con los datos del indicador 9.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_9_c_1();
}
