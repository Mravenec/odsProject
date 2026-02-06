package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 15: Vida de Ecosistemas Terrestres
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 15
 */
public interface IObjetivo15VidaEcosistemasService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 15: Vida de Ecosistemas Terrestres
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 15.1.1
     * Superficie forestal en proporción a la superficie total
     * 
     * @return Datos del indicador 15.1.1
     */
    IndicatorData getIndicador_15_1_1();
    
    /**
     * Obtiene el indicador 15.1.2
     * Proporción de lugares importantes para biodiversidad terrestre incluidos en zonas protegidas
     * 
     * @return Datos del indicador 15.1.2
     */
    IndicatorData getIndicador_15_1_2();
    
    /**
     * Obtiene el indicador 15.2.1
     * Avances hacia la gestión forestal sostenible
     * 
     * @return Datos del indicador 15.2.1
     */
    IndicatorData getIndicador_15_2_1();
    
    /**
     * Obtiene el indicador 15.3.1
     * Proporción de tierras degradadas en comparación con la superficie total
     * 
     * @return Datos del indicador 15.3.1
     */
    IndicatorData getIndicador_15_3_1();
    
    /**
     * Obtiene el indicador 15.4.1
     * Lugares importantes para biodiversidad de montañas incluidos en zonas protegidas
     * 
     * @return Datos del indicador 15.4.1
     */
    IndicatorData getIndicador_15_4_1();
    
    /**
     * Obtiene el indicador 15.4.2
     * Índice de cobertura verde de montañas y proporción de terreno montañoso degradado
     * 
     * @return Datos del indicador 15.4.2
     */
    IndicatorData getIndicador_15_4_2();
    
    /**
     * Obtiene el indicador 15.5.1
     * Índice de la Lista Roja
     * 
     * @return Datos del indicador 15.5.1
     */
    IndicatorData getIndicador_15_5_1();
    
    /**
     * Obtiene el indicador 15.6.1
     * Número de países con marcos legislativos para distribución justa de beneficios
     * 
     * @return Datos del indicador 15.6.1
     */
    IndicatorData getIndicador_15_6_1();
    
    /**
     * Obtiene el indicador 15.7.1
     * Proporción de especímenes de flora y fauna silvestre comercializados ilícitamente
     * 
     * @return Datos del indicador 15.7.1
     */
    IndicatorData getIndicador_15_7_1();
    
    /**
     * Obtiene el indicador 15.8.1
     * Proporción de países con legislación para especies exóticas invasoras
     * 
     * @return Datos del indicador 15.8.1
     */
    IndicatorData getIndicador_15_8_1();
    
    /**
     * Obtiene el indicador 15.9.1
     * Número de países con metas nacionales acordes con Marco Mundial de Biodiversidad
     * 
     * @return Datos del indicador 15.9.1
     */
    IndicatorData getIndicador_15_9_1();
    
    /**
     * Obtiene el indicador 15.a.1
     * AOD destinada a conservación y uso sostenible de biodiversidad
     * 
     * @return Datos del indicador 15.a.1
     */
    IndicatorData getIndicador_15_a_1();
    
    /**
     * Obtiene el indicador 15.b.1
     * Ingresos generados y financiación movilizada para biodiversidad
     * 
     * @return Datos del indicador 15.b.1
     */
    IndicatorData getIndicador_15_b_1();
    
    /**
     * Obtiene el indicador 15.c.1
     * Proporción de especímenes de flora y fauna silvestre comercializados ilícitamente
     * 
     * @return Datos del indicador 15.c.1
     */
    IndicatorData getIndicador_15_c_1();
}
