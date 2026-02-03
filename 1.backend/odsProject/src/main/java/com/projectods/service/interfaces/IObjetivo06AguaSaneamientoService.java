package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 6: Agua Limpia y Saneamiento
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 6
 */
public interface IObjetivo06AguaSaneamientoService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 6: Agua Limpia y Saneamiento
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 6.1.1
     * Proporción de la población que utiliza servicios de agua potable gestionados sin riesgos
     * 
     * @return Datos del indicador 6.1.1
     */
    IndicatorData getIndicador_6_1_1();
    
    /**
     * Obtiene el indicador 6.2.1
     * Proporción de la población que utiliza servicios de saneamiento gestionados sin riesgos
     * 
     * @return Datos del indicador 6.2.1
     */
    IndicatorData getIndicador_6_2_1();
    
    /**
     * Obtiene el indicador 6.3.1
     * Proporción de aguas residuales tratadas de manera adecuada
     * 
     * @return Datos del indicador 6.3.1
     */
    IndicatorData getIndicador_6_3_1();
    
    /**
     * Obtiene el indicador 6.3.2
     * Proporción de masas de agua de buena calidad
     * 
     * @return Datos del indicador 6.3.2
     */
    IndicatorData getIndicador_6_3_2();
    
    /**
     * Obtiene el indicador 6.4.1
     * Cambio en el uso eficiente de los recursos hídricos
     * 
     * @return Datos del indicador 6.4.1
     */
    IndicatorData getIndicador_6_4_1();
    
    /**
     * Obtiene el indicador 6.4.2
     * Nivel de estrés hídrico
     * 
     * @return Datos del indicador 6.4.2
     */
    IndicatorData getIndicador_6_4_2();
    
    /**
     * Obtiene el indicador 6.5.1
     * Grado de gestión integrada de los recursos hídricos
     * 
     * @return Datos del indicador 6.5.1
     */
    IndicatorData getIndicador_6_5_1();
    
    /**
     * Obtiene el indicador 6.5.2
     * Proporción de cuencas transfronterizas con arreglos operacionales
     * 
     * @return Datos del indicador 6.5.2
     */
    IndicatorData getIndicador_6_5_2();
    
    /**
     * Obtiene el indicador 6.6.1
     * Cambio en la extensión de ecosistemas relacionados con el agua
     * 
     * @return Datos del indicador 6.6.1
     */
    IndicatorData getIndicador_6_6_1();
    
    /**
     * Obtiene el indicador 6.a.1
     * Volumen de AOD destinada al agua y saneamiento
     * 
     * @return Datos del indicador 6.a.1
     */
    IndicatorData getIndicador_6_a_1();
    
    /**
     * Obtiene el indicador 6.b.1
     * Proporción de dependencias con políticas de participación comunitaria
     * 
     * @return Datos del indicador 6.b.1
     */
    IndicatorData getIndicador_6_b_1();
}
