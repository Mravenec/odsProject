package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 5: Igualdad de Género
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 5
 */
public interface IObjetivo05GeneroController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 5: Igualdad de Género
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 5.1.1
     * Existencia de marcos jurídicos para igualdad y no discriminación
     * 
     * @return ResponseEntity con los datos del indicador 5.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_1_1();
    
    /**
     * Obtiene el indicador 5.2.1
     * Proporción de mujeres que han sufrido violencia de pareja
     * 
     * @return ResponseEntity con los datos del indicador 5.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_2_1();
    
    /**
     * Obtiene el indicador 5.2.2
     * Proporción de mujeres que han sufrido violencia sexual
     * 
     * @return ResponseEntity con los datos del indicador 5.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_5_2_2();
    
    /**
     * Obtiene el indicador 5.3.1
     * Proporción de mujeres casadas antes de los 15 y 18 años
     * 
     * @return ResponseEntity con los datos del indicador 5.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_3_1();
    
    /**
     * Obtiene el indicador 5.3.2
     * Proporción de niñas que han sufrido mutilación genital femenina
     * 
     * @return ResponseEntity con los datos del indicador 5.3.2
     */
    ResponseEntity<IndicatorData> getIndicador_5_3_2();
    
    /**
     * Obtiene el indicador 5.4.1
     * Proporción de tiempo dedicado a trabajo doméstico no remunerado
     * 
     * @return ResponseEntity con los datos del indicador 5.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_4_1();
    
    /**
     * Obtiene el indicador 5.5.1
     * Proporción de escaños ocupados por mujeres en parlamentos y gobiernos
     * 
     * @return ResponseEntity con los datos del indicador 5.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_5_1();
    
    /**
     * Obtiene el indicador 5.5.2
     * Proporción de mujeres en cargos directivos
     * 
     * @return ResponseEntity con los datos del indicador 5.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_5_5_2();
    
    /**
     * Obtiene el indicador 5.6.1
     * Proporción de mujeres que toman decisiones informadas sobre salud reproductiva
     * 
     * @return ResponseEntity con los datos del indicador 5.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_6_1();
    
    /**
     * Obtiene el indicador 5.6.2
     * Países con leyes que garantizan acceso a salud sexual y reproductiva
     * 
     * @return ResponseEntity con los datos del indicador 5.6.2
     */
    ResponseEntity<IndicatorData> getIndicador_5_6_2();
    
    /**
     * Obtiene el indicador 5.a.1
     * Proporción de población agrícola con derechos seguros sobre tierras agrícolas
     * 
     * @return ResponseEntity con los datos del indicador 5.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_a_1();
    
    /**
     * Obtiene el indicador 5.a.2
     * Países con leyes que garantizan igualdad de derechos de la mujer a la tierra
     * 
     * @return ResponseEntity con los datos del indicador 5.a.2
     */
    ResponseEntity<IndicatorData> getIndicador_5_a_2();
    
    /**
     * Obtiene el indicador 5.b.1
     * Proporción de personas que poseen teléfono móvil, desglosada por sexo
     * 
     * @return ResponseEntity con los datos del indicador 5.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_b_1();
    
    /**
     * Obtiene el indicador 5.c.1
     * Países con sistemas para seguimiento de igualdad de género
     * 
     * @return ResponseEntity con los datos del indicador 5.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_5_c_1();
}
