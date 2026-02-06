package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 2: Hambre Cero
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 2
 */
public interface IObjetivo02HambreCeroController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 2: Hambre Cero
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 2.1.1
     * Prevalencia de la subalimentación
     * 
     * @return ResponseEntity con los datos del indicador 2.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_1_1();
    
    /**
     * Obtiene el indicador 2.1.2
     * Prevalencia de la inseguridad alimentaria moderada o grave
     * 
     * @return ResponseEntity con los datos del indicador 2.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_2_1_2();
    
    /**
     * Obtiene el indicador 2.2.1
     * Prevalencia del retraso del crecimiento en niños menores de 5 años
     * 
     * @return ResponseEntity con los datos del indicador 2.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_2_1();
    
    /**
     * Obtiene el indicador 2.2.2
     * Prevalencia de la malnutrición en niños menores de 5 años
     * 
     * @return ResponseEntity con los datos del indicador 2.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_2_2_2();
    
    /**
     * Obtiene el indicador 2.2.3
     * Prevalencia de anemia en mujeres de 15-49 años
     * 
     * @return ResponseEntity con los datos del indicador 2.2.3
     */
    ResponseEntity<IndicatorData> getIndicador_2_2_3();
    
    /**
     * Obtiene el indicador 2.2.4
     * Prevalencia del umbral mínimo de diversidad alimentaria
     * 
     * @return ResponseEntity con los datos del indicador 2.2.4
     */
    ResponseEntity<IndicatorData> getIndicador_2_2_4();
    
    /**
     * Obtiene el indicador 2.3.1
     * Volumen de producción por unidad de trabajo agropecuaria
     * 
     * @return ResponseEntity con los datos del indicador 2.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_3_1();
    
    /**
     * Obtiene el indicador 2.3.2
     * Media de ingresos de productores de alimentos en pequeña escala
     * 
     * @return ResponseEntity con los datos del indicador 2.3.2
     */
    ResponseEntity<IndicatorData> getIndicador_2_3_2();
    
    /**
     * Obtiene el indicador 2.4.1
     * Proporción de superficie agrícola con agricultura sostenible
     * 
     * @return ResponseEntity con los datos del indicador 2.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_4_1();
    
    /**
     * Obtiene el indicador 2.5.1
     * Recursos genéticos preservados para alimentación y agricultura
     * 
     * @return ResponseEntity con los datos del indicador 2.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_5_1();
    
    /**
     * Obtiene el indicador 2.5.2
     * Razas y variedades locales en riesgo de extinción
     * 
     * @return ResponseEntity con los datos del indicador 2.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_2_5_2();
    
    /**
     * Obtiene el indicador 2.a.1
     * Índice de orientación agrícola para el gasto público
     * 
     * @return ResponseEntity con los datos del indicador 2.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_a_1();
    
    /**
     * Obtiene el indicador 2.a.2
     * Corrientes oficiales destinadas al sector agrícola
     * 
     * @return ResponseEntity con los datos del indicador 2.a.2
     */
    ResponseEntity<IndicatorData> getIndicador_2_a_2();
    
    /**
     * Obtiene el indicador 2.b.1
     * Subsidios a la exportación de productos agropecuarios
     * 
     * @return ResponseEntity con los datos del indicador 2.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_b_1();
    
    /**
     * Obtiene el indicador 2.c.1
     * Indicador de anomalías en los precios de los alimentos
     * 
     * @return ResponseEntity con los datos del indicador 2.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_2_c_1();
}
