package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 5: Igualdad de Género
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 5
 */
public interface IObjetivo05GeneroService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 5: Igualdad de Género
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 5.1.1
     * Existencia de marcos jurídicos para igualdad y no discriminación
     * 
     * @return Datos del indicador 5.1.1
     */
    IndicatorData getIndicador_5_1_1();
    
    /**
     * Obtiene el indicador 5.2.1
     * Proporción de mujeres que han sufrido violencia de pareja
     * 
     * @return Datos del indicador 5.2.1
     */
    IndicatorData getIndicador_5_2_1();
    
    /**
     * Obtiene el indicador 5.2.2
     * Proporción de mujeres que han sufrido violencia sexual
     * 
     * @return Datos del indicador 5.2.2
     */
    IndicatorData getIndicador_5_2_2();
    
    /**
     * Obtiene el indicador 5.3.1
     * Proporción de mujeres casadas antes de los 15 y 18 años
     * 
     * @return Datos del indicador 5.3.1
     */
    IndicatorData getIndicador_5_3_1();
    
    /**
     * Obtiene el indicador 5.3.2
     * Proporción de niñas que han sufrido mutilación genital femenina
     * 
     * @return Datos del indicador 5.3.2
     */
    IndicatorData getIndicador_5_3_2();
    
    /**
     * Obtiene el indicador 5.4.1
     * Proporción de tiempo dedicado a trabajo doméstico no remunerado
     * 
     * @return Datos del indicador 5.4.1
     */
    IndicatorData getIndicador_5_4_1();
    
    /**
     * Obtiene el indicador 5.5.1
     * Proporción de escaños ocupados por mujeres en parlamentos y gobiernos
     * 
     * @return Datos del indicador 5.5.1
     */
    IndicatorData getIndicador_5_5_1();
    
    /**
     * Obtiene el indicador 5.5.2
     * Proporción de mujeres en cargos directivos
     * 
     * @return Datos del indicador 5.5.2
     */
    IndicatorData getIndicador_5_5_2();
    
    /**
     * Obtiene el indicador 5.6.1
     * Proporción de mujeres que toman decisiones informadas sobre salud reproductiva
     * 
     * @return Datos del indicador 5.6.1
     */
    IndicatorData getIndicador_5_6_1();
    
    /**
     * Obtiene el indicador 5.6.2
     * Países con leyes que garantizan acceso a salud sexual y reproductiva
     * 
     * @return Datos del indicador 5.6.2
     */
    IndicatorData getIndicador_5_6_2();
    
    /**
     * Obtiene el indicador 5.a.1
     * Proporción de población agrícola con derechos seguros sobre tierras
     * 
     * @return Datos del indicador 5.a.1
     */
    IndicatorData getIndicador_5_a_1();
    
    /**
     * Obtiene el indicador 5.a.2
     * Países con leyes que garantizan igualdad de derechos de la mujer a la tierra
     * 
     * @return Datos del indicador 5.a.2
     */
    IndicatorData getIndicador_5_a_2();
    
    /**
     * Obtiene el indicador 5.b.1
     * Proporción de personas que poseen teléfono móvil, desglosada por sexo
     * 
     * @return Datos del indicador 5.b.1
     */
    IndicatorData getIndicador_5_b_1();
    
    /**
     * Obtiene el indicador 5.c.1
     * Países con sistemas para seguimiento de igualdad de género
     * 
     * @return Datos del indicador 5.c.1
     */
    IndicatorData getIndicador_5_c_1();
}
