package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 17
 */
public interface IObjetivo17AlianzasService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 17: Alianzas para Lograr los Objetivos
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 17.1.1
     * Total de ingresos del gobierno en proporción al PIB, desglosado por fuente
     * 
     * @return Datos del indicador 17.1.1
     */
    IndicatorData getIndicador_17_1_1();
    
    /**
     * Obtiene el indicador 17.1.2
     * Proporción del presupuesto nacional financiado por impuestos internos
     * 
     * @return Datos del indicador 17.1.2
     */
    IndicatorData getIndicador_17_1_2();
    
    /**
     * Obtiene el indicador 17.2.1
     * Asistencia oficial para el desarrollo neta en proporción al INB
     * 
     * @return Datos del indicador 17.2.1
     */
    IndicatorData getIndicador_17_2_1();
    
    /**
     * Obtiene el indicador 17.3.1
     * Recursos financieros adicionales movilizados para países en desarrollo
     * 
     * @return Datos del indicador 17.3.1
     */
    IndicatorData getIndicador_17_3_1();
    
    /**
     * Obtiene el indicador 17.3.2
     * Volumen de remesas en proporción al PIB total
     * 
     * @return Datos del indicador 17.3.2
     */
    IndicatorData getIndicador_17_3_2();
    
    /**
     * Obtiene el indicador 17.4.1
     * Servicio de la deuda en proporción a las exportaciones
     * 
     * @return Datos del indicador 17.4.1
     */
    IndicatorData getIndicador_17_4_1();
    
    /**
     * Obtiene el indicador 17.5.1
     * Número de países que adoptan sistemas de promoción de inversiones
     * 
     * @return Datos del indicador 17.5.1
     */
    IndicatorData getIndicador_17_5_1();
    
    /**
     * Obtiene el indicador 17.6.1
     * Número de abonados a servicios de banda ancha fija por cada 100 habitantes
     * 
     * @return Datos del indicador 17.6.1
     */
    IndicatorData getIndicador_17_6_1();
    
    /**
     * Obtiene el indicador 17.7.1
     * Total de fondos destinados a promover desarrollo, transferencia y difusión de tecnologías
     * 
     * @return Datos del indicador 17.7.1
     */
    IndicatorData getIndicador_17_7_1();
    
    /**
     * Obtiene el indicador 17.8.1
     * Proporción de personas que utilizan Internet
     * 
     * @return Datos del indicador 17.8.1
     */
    IndicatorData getIndicador_17_8_1();
    
    /**
     * Obtiene el indicador 17.9.1
     * Valor en dólares de la AOD comprometida para países en desarrollo
     * 
     * @return Datos del indicador 17.9.1
     */
    IndicatorData getIndicador_17_9_1();
    
    /**
     * Obtiene el indicador 17.10.1
     * Promedio arancelario mundial ponderado
     * 
     * @return Datos del indicador 17.10.1
     */
    IndicatorData getIndicador_17_10_1();
    
    /**
     * Obtiene el indicador 17.11.1
     * Participación de países en desarrollo en las exportaciones mundiales
     * 
     * @return Datos del indicador 17.11.1
     */
    IndicatorData getIndicador_17_11_1();
    
    /**
     * Obtiene el indicador 17.12.1
     * Promedio ponderado de los aranceles que enfrentan países en desarrollo
     * 
     * @return Datos del indicador 17.12.1
     */
    IndicatorData getIndicador_17_12_1();
    
    /**
     * Obtiene el indicador 17.13.1
     * Tablero macroeconómico
     * 
     * @return Datos del indicador 17.13.1
     */
    IndicatorData getIndicador_17_13_1();
    
    /**
     * Obtiene el indicador 17.14.1
     * Número de países con mecanismos para mejorar coherencia de políticas
     * 
     * @return Datos del indicador 17.14.1
     */
    IndicatorData getIndicador_17_14_1();
    
    /**
     * Obtiene el indicador 17.15.1
     * Grado de utilización de marcos de resultados y herramientas de planificación
     * 
     * @return Datos del indicador 17.15.1
     */
    IndicatorData getIndicador_17_15_1();
    
    /**
     * Obtiene el indicador 17.16.1
     * Número de países que informan de progresos en marcos de múltiples interesados
     * 
     * @return Datos del indicador 17.16.1
     */
    IndicatorData getIndicador_17_16_1();
    
    /**
     * Obtiene el indicador 17.17.1
     * Suma en dólares prometida a alianzas público-privadas centradas en infraestructura
     * 
     * @return Datos del indicador 17.17.1
     */
    IndicatorData getIndicador_17_17_1();
    
    /**
     * Obtiene el indicador 17.18.1
     * Indicadores de la capacidad estadística
     * 
     * @return Datos del indicador 17.18.1
     */
    IndicatorData getIndicador_17_18_1();
    
    /**
     * Obtiene el indicador 17.18.2
     * Número de países cuya legislación cumple Principios Fundamentales de Estadísticas
     * 
     * @return Datos del indicador 17.18.2
     */
    IndicatorData getIndicador_17_18_2();
    
    /**
     * Obtiene el indicador 17.18.3
     * Número de países con plan estadístico nacional plenamente financiado
     * 
     * @return Datos del indicador 17.18.3
     */
    IndicatorData getIndicador_17_18_3();
    
    /**
     * Obtiene el indicador 17.19.1
     * Valor en dólares de recursos para fortalecer capacidad estadística
     * 
     * @return Datos del indicador 17.19.1
     */
    IndicatorData getIndicador_17_19_1();
    
    /**
     * Obtiene el indicador 17.19.2
     * Proporción de países que han realizado censo y registrado nacimientos y defunciones
     * 
     * @return Datos del indicador 17.19.2
     */
    IndicatorData getIndicador_17_19_2();
}
