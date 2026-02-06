package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 14: Vida Submarina
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 14
 */
public interface IObjetivo14VidaSubmarinaService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 14: Vida Submarina
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 14.1.1
     * Índice de eutrofización costera y densidad de detritos plásticos
     * 
     * @return Datos del indicador 14.1.1
     */
    IndicatorData getIndicador_14_1_1();
    
    /**
     * Obtiene el indicador 14.2.1
     * Número de países que aplican enfoques basados en ecosistemas para gestionar zonas marinas
     * 
     * @return Datos del indicador 14.2.1
     */
    IndicatorData getIndicador_14_2_1();
    
    /**
     * Obtiene el indicador 14.3.1
     * Acidez media del mar medida en estaciones de muestreo representativas
     * 
     * @return Datos del indicador 14.3.1
     */
    IndicatorData getIndicador_14_3_1();
    
    /**
     * Obtiene el indicador 14.4.1
     * Proporción de poblaciones de peces con niveles biológicamente sostenibles
     * 
     * @return Datos del indicador 14.4.1
     */
    IndicatorData getIndicador_14_4_1();
    
    /**
     * Obtiene el indicador 14.5.1
     * Cobertura de las zonas protegidas en relación con las zonas marinas
     * 
     * @return Datos del indicador 14.5.1
     */
    IndicatorData getIndicador_14_5_1();
    
    /**
     * Obtiene el indicador 14.6.1
     * Grado de aplicación de instrumentos contra la pesca ilegal
     * 
     * @return Datos del indicador 14.6.1
     */
    IndicatorData getIndicador_14_6_1();
    
    /**
     * Obtiene el indicador 14.7.1
     * Proporción del PIB correspondiente a la pesca sostenible
     * 
     * @return Datos del indicador 14.7.1
     */
    IndicatorData getIndicador_14_7_1();
    
    /**
     * Obtiene el indicador 14.a.1
     * Proporción del presupuesto total de investigación asignada a tecnología marina
     * 
     * @return Datos del indicador 14.a.1
     */
    IndicatorData getIndicador_14_a_1();
    
    /**
     * Obtiene el indicador 14.b.1
     * Grado de aplicación de marcos jurídicos para pesca en pequeña escala
     * 
     * @return Datos del indicador 14.b.1
     */
    IndicatorData getIndicador_14_b_1();
    
    /**
     * Obtiene el indicador 14.c.1
     * Número de países que avanzan en implementación de instrumentos relacionados con océanos
     * 
     * @return Datos del indicador 14.c.1
     */
    IndicatorData getIndicador_14_c_1();
}
