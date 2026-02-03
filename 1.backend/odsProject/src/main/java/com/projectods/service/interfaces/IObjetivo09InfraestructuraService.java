package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 9: Industria, Innovación e Infraestructura
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 9
 */
public interface IObjetivo09InfraestructuraService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 9: Industria, Innovación e Infraestructura
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 9.1.1
     * Proporción de la población rural que vive cerca de una carretera transitable
     * 
     * @return Datos del indicador 9.1.1
     */
    IndicatorData getIndicador_9_1_1();
    
    /**
     * Obtiene el indicador 9.1.2
     * Volumen de transporte de pasajeros y carga, desglosado por medio de transporte
     * 
     * @return Datos del indicador 9.1.2
     */
    IndicatorData getIndicador_9_1_2();
    
    /**
     * Obtiene el indicador 9.2.1
     * Valor añadido del sector manufacturo en proporción al PIB y per cápita
     * 
     * @return Datos del indicador 9.2.1
     */
    IndicatorData getIndicador_9_2_1();
    
    /**
     * Obtiene el indicador 9.2.2
     * Empleo del sector manufacturero en proporción al empleo total
     * 
     * @return Datos del indicador 9.2.2
     */
    IndicatorData getIndicador_9_2_2();
    
    /**
     * Obtiene el indicador 9.3.1
     * Proporción del valor añadido total correspondiente a las pequeñas industrias
     * 
     * @return Datos del indicador 9.3.1
     */
    IndicatorData getIndicador_9_3_1();
    
    /**
     * Obtiene el indicador 9.3.2
     * Proporción de las pequeñas industrias que han obtenido un préstamo o línea de crédito
     * 
     * @return Datos del indicador 9.3.2
     */
    IndicatorData getIndicador_9_3_2();
    
    /**
     * Obtiene el indicador 9.4.1
     * Emisiones de CO2 por unidad de valor añadido
     * 
     * @return Datos del indicador 9.4.1
     */
    IndicatorData getIndicador_9_4_1();
    
    /**
     * Obtiene el indicador 9.5.1
     * Gastos en investigación y desarrollo en proporción al PIB
     * 
     * @return Datos del indicador 9.5.1
     */
    IndicatorData getIndicador_9_5_1();
    
    /**
     * Obtiene el indicador 9.5.2
     * Número de investigadores por cada millón de habitantes
     * 
     * @return Datos del indicador 9.5.2
     */
    IndicatorData getIndicador_9_5_2();
    
    /**
     * Obtiene el indicador 9.a.1
     * Total de apoyo internacional oficial destinado a la infraestructura
     * 
     * @return Datos del indicador 9.a.1
     */
    IndicatorData getIndicador_9_a_1();
    
    /**
     * Obtiene el indicador 9.b.1
     * Proporción del valor añadido por la industria de tecnología mediana y alta
     * 
     * @return Datos del indicador 9.b.1
     */
    IndicatorData getIndicador_9_b_1();
    
    /**
     * Obtiene el indicador 9.c.1
     * Proporción de la población con cobertura de red móvil
     * 
     * @return Datos del indicador 9.c.1
     */
    IndicatorData getIndicador_9_c_1();
}
