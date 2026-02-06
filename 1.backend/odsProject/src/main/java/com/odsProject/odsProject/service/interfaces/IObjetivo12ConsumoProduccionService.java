package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 12: Producción y Consumo Responsables
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 12
 */
public interface IObjetivo12ConsumoProduccionService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 12: Producción y Consumo Responsables
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 12.1.1
     * Número de países con políticas de consumo y producción sostenibles
     * 
     * @return Datos del indicador 12.1.1
     */
    IndicatorData getIndicador_12_1_1();
    
    /**
     * Obtiene el indicador 12.2.1
     * Huella material en términos absolutos, per cápita y por PIB
     * 
     * @return Datos del indicador 12.2.1
     */
    IndicatorData getIndicador_12_2_1();
    
    /**
     * Obtiene el indicador 12.2.2
     * Consumo material interno en términos absolutos, per cápita y por PIB
     * 
     * @return Datos del indicador 12.2.2
     */
    IndicatorData getIndicador_12_2_2();
    
    /**
     * Obtiene el indicador 12.3.1
     * Índice de pérdidas de alimentos y desperdicio de alimentos
     * 
     * @return Datos del indicador 12.3.1
     */
    IndicatorData getIndicador_12_3_1();
    
    /**
     * Obtiene el indicador 12.4.1
     * Número de partes en acuerdos sobre desechos peligrosos que cumplen sus compromisos
     * 
     * @return Datos del indicador 12.4.1
     */
    IndicatorData getIndicador_12_4_1();
    
    /**
     * Obtiene el indicador 12.4.2
     * Desechos peligrosos generados per cápita y proporción tratada
     * 
     * @return Datos del indicador 12.4.2
     */
    IndicatorData getIndicador_12_4_2();
    
    /**
     * Obtiene el indicador 12.5.1
     * Tasa nacional de reciclado, en toneladas de material reciclado
     * 
     * @return Datos del indicador 12.5.1
     */
    IndicatorData getIndicador_12_5_1();
    
    /**
     * Obtiene el indicador 12.6.1
     * Número de empresas que publican informes sobre sostenibilidad
     * 
     * @return Datos del indicador 12.6.1
     */
    IndicatorData getIndicador_12_6_1();
    
    /**
     * Obtiene el indicador 12.7.1
     * Número de países con políticas de adquisiciones públicas sostenibles
     * 
     * @return Datos del indicador 12.7.1
     */
    IndicatorData getIndicador_12_7_1();
    
    /**
     * Obtiene el indicador 12.8.1
     * Grado en que se incorpora educación para el desarrollo sostenible
     * 
     * @return Datos del indicador 12.8.1
     */
    IndicatorData getIndicador_12_8_1();
    
    /**
     * Obtiene el indicador 12.a.1
     * Capacidad instalada de generación de energía renovable
     * 
     * @return Datos del indicador 12.a.1
     */
    IndicatorData getIndicador_12_a_1();
    
    /**
     * Obtiene el indicador 12.b.1
     * Aplicación de instrumentos de contabilidad para turismo sostenible
     * 
     * @return Datos del indicador 12.b.1
     */
    IndicatorData getIndicador_12_b_1();
    
    /**
     * Obtiene el indicador 12.c.1
     * Cuantía de los subsidios a los combustibles fósiles por unidad del PIB
     * 
     * @return Datos del indicador 12.c.1
     */
    IndicatorData getIndicador_12_c_1();
}
