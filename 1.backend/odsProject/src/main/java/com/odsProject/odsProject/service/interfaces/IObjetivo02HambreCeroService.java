package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 2: Hambre Cero
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 2
 */
public interface IObjetivo02HambreCeroService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 2: Hambre Cero
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 2.1.1
     * Prevalencia de la subalimentación
     * 
     * @return Datos del indicador 2.1.1
     */
    IndicatorData getIndicador_2_1_1();
    
    /**
     * Obtiene el indicador 2.1.2
     * Prevalencia de la inseguridad alimentaria moderada o grave
     * 
     * @return Datos del indicador 2.1.2
     */
    IndicatorData getIndicador_2_1_2();
    
    /**
     * Obtiene el indicador 2.2.1
     * Prevalencia del retraso del crecimiento en niños menores de 5 años
     * 
     * @return Datos del indicador 2.2.1
     */
    IndicatorData getIndicador_2_2_1();
    
    /**
     * Obtiene el indicador 2.2.2
     * Prevalencia de la malnutrición en niños menores de 5 años
     * 
     * @return Datos del indicador 2.2.2
     */
    IndicatorData getIndicador_2_2_2();
    
    /**
     * Obtiene el indicador 2.2.3
     * Prevalencia de anemia en mujeres de 15-49 años
     * 
     * @return Datos del indicador 2.2.3
     */
    IndicatorData getIndicador_2_2_3();
    
    /**
     * Obtiene el indicador 2.2.4
     * Prevalencia del umbral mínimo de diversidad alimentaria
     * 
     * @return Datos del indicador 2.2.4
     */
    IndicatorData getIndicador_2_2_4();
    
    /**
     * Obtiene el indicador 2.3.1
     * Volumen de producción por unidad de trabajo agropecuaria
     * 
     * @return Datos del indicador 2.3.1
     */
    IndicatorData getIndicador_2_3_1();
    
    /**
     * Obtiene el indicador 2.3.2
     * Media de ingresos de productores de alimentos en pequeña escala
     * 
     * @return Datos del indicador 2.3.2
     */
    IndicatorData getIndicador_2_3_2();
    
    /**
     * Obtiene el indicador 2.4.1
     * Proporción de superficie agrícola con agricultura sostenible
     * 
     * @return Datos del indicador 2.4.1
     */
    IndicatorData getIndicador_2_4_1();
    
    /**
     * Obtiene el indicador 2.5.1
     * Recursos genéticos preservados para alimentación y agricultura
     * 
     * @return Datos del indicador 2.5.1
     */
    IndicatorData getIndicador_2_5_1();
    
    /**
     * Obtiene el indicador 2.5.2
     * Razas y variedades locales en riesgo de extinción
     * 
     * @return Datos del indicador 2.5.2
     */
    IndicatorData getIndicador_2_5_2();
    
    /**
     * Obtiene el indicador 2.a.1
     * Índice de orientación agrícola para el gasto público
     * 
     * @return Datos del indicador 2.a.1
     */
    IndicatorData getIndicador_2_a_1();
    
    /**
     * Obtiene el indicador 2.a.2
     * Corrientes oficiales destinadas al sector agrícola
     * 
     * @return Datos del indicador 2.a.2
     */
    IndicatorData getIndicador_2_a_2();
    
    /**
     * Obtiene el indicador 2.b.1
     * Subsidios a la exportación de productos agropecuarios
     * 
     * @return Datos del indicador 2.b.1
     */
    IndicatorData getIndicador_2_b_1();
    
    /**
     * Obtiene el indicador 2.c.1
     * Indicador de anomalías en los precios de los alimentos
     * 
     * @return Datos del indicador 2.c.1
     */
    IndicatorData getIndicador_2_c_1();
}
