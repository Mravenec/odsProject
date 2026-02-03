package com.projectods.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 11
 */
public interface IObjetivo11CiudadesSosteniblesController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 11: Ciudades y Comunidades Sostenibles
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 11.1.1
     * Proporción de la población urbana que vive en barrios marginales
     * 
     * @return ResponseEntity con los datos del indicador 11.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_1_1();
    
    /**
     * Obtiene el indicador 11.2.1
     * Proporción de la población con fácil acceso al transporte público
     * 
     * @return ResponseEntity con los datos del indicador 11.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_2_1();
    
    /**
     * Obtiene el indicador 11.3.1
     * Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población
     * 
     * @return ResponseEntity con los datos del indicador 11.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_3_1();
    
    /**
     * Obtiene el indicador 11.3.2
     * Proporción de ciudades con participación directa de la sociedad civil
     * 
     * @return ResponseEntity con los datos del indicador 11.3.2
     */
    ResponseEntity<IndicatorData> getIndicador_11_3_2();
    
    /**
     * Obtiene el indicador 11.4.1
     * Total de gastos per cápita destinados a la preservación del patrimonio cultural
     * 
     * @return ResponseEntity con los datos del indicador 11.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_4_1();
    
    /**
     * Obtiene el indicador 11.5.1
     * Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres
     * 
     * @return ResponseEntity con los datos del indicador 11.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_5_1();
    
    /**
     * Obtiene el indicador 11.5.2
     * Pérdidas económicas directas atribuidas a los desastres en relación con el PIB mundial
     * 
     * @return ResponseEntity con los datos del indicador 11.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_11_5_2();
    
    /**
     * Obtiene el indicador 11.5.3
     * Daños en la infraestructura crítica e interrupciones de servicios básicos
     * 
     * @return ResponseEntity con los datos del indicador 11.5.3
     */
    ResponseEntity<IndicatorData> getIndicador_11_5_3();
    
    /**
     * Obtiene el indicador 11.6.1
     * Proporción de residuos sólidos municipales recogidos y administrados
     * 
     * @return ResponseEntity con los datos del indicador 11.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_6_1();
    
    /**
     * Obtiene el indicador 11.6.2
     * Niveles medios anuales de partículas finas en las ciudades
     * 
     * @return ResponseEntity con los datos del indicador 11.6.2
     */
    ResponseEntity<IndicatorData> getIndicador_11_6_2();
    
    /**
     * Obtiene el indicador 11.7.1
     * Proporción media de la superficie edificada dedicada a espacios abiertos públicos
     * 
     * @return ResponseEntity con los datos del indicador 11.7.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_7_1();
    
    /**
     * Obtiene el indicador 11.7.2
     * Proporción de personas que han sido víctimas de acoso en espacios públicos
     * 
     * @return ResponseEntity con los datos del indicador 11.7.2
     */
    ResponseEntity<IndicatorData> getIndicador_11_7_2();
    
    /**
     * Obtiene el indicador 11.a.1
     * Número de países con políticas urbanas nacionales
     * 
     * @return ResponseEntity con los datos del indicador 11.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_a_1();
    
    /**
     * Obtiene el indicador 11.b.1
     * Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
     * 
     * @return ResponseEntity con los datos del indicador 11.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_b_1();
    
    /**
     * Obtiene el indicador 11.b.2
     * Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
     * 
     * @return ResponseEntity con los datos del indicador 11.b.2
     */
    ResponseEntity<IndicatorData> getIndicador_11_b_2();
    
    /**
     * Obtiene el indicador 11.c.1
     * Total de asistencia oficial para el desarrollo destinada a infraestructuras urbanas
     * 
     * @return ResponseEntity con los datos del indicador 11.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_11_c_1();
}
