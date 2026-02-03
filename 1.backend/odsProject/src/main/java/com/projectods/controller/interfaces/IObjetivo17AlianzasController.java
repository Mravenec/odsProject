package com.projectods.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 17
 */
public interface IObjetivo17AlianzasController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 17: Alianzas para Lograr los Objetivos
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 17.1.1
     * Total de ingresos del gobierno en proporción al PIB, desglosado por fuente
     * 
     * @return ResponseEntity con los datos del indicador 17.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_1_1();
    
    /**
     * Obtiene el indicador 17.1.2
     * Proporción del presupuesto nacional financiado por impuestos internos
     * 
     * @return ResponseEntity con los datos del indicador 17.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_17_1_2();
    
    /**
     * Obtiene el indicador 17.2.1
     * Asistencia oficial para el desarrollo neta en proporción al INB
     * 
     * @return ResponseEntity con los datos del indicador 17.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_2_1();
    
    /**
     * Obtiene el indicador 17.3.1
     * Recursos financieros adicionales movilizados para países en desarrollo
     * 
     * @return ResponseEntity con los datos del indicador 17.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_3_1();
    
    /**
     * Obtiene el indicador 17.3.2
     * Volumen de remesas en proporción al PIB total
     * 
     * @return ResponseEntity con los datos del indicador 17.3.2
     */
    ResponseEntity<IndicatorData> getIndicador_17_3_2();
    
    /**
     * Obtiene el indicador 17.4.1
     * Servicio de la deuda en proporción a las exportaciones
     * 
     * @return ResponseEntity con los datos del indicador 17.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_4_1();
    
    /**
     * Obtiene el indicador 17.5.1
     * Número de países que adoptan sistemas de promoción de inversiones
     * 
     * @return ResponseEntity con los datos del indicador 17.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_5_1();
    
    /**
     * Obtiene el indicador 17.6.1
     * Número de abonados a servicios de banda ancha fija por cada 100 habitantes
     * 
     * @return ResponseEntity con los datos del indicador 17.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_6_1();
    
    /**
     * Obtiene el indicador 17.7.1
     * Total de fondos destinados a promover desarrollo, transferencia y difusión de tecnologías
     * 
     * @return ResponseEntity con los datos del indicador 17.7.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_7_1();
    
    /**
     * Obtiene el indicador 17.8.1
     * Proporción de personas que utilizan Internet
     * 
     * @return ResponseEntity con los datos del indicador 17.8.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_8_1();
    
    /**
     * Obtiene el indicador 17.9.1
     * Valor en dólares de la AOD comprometida para países en desarrollo
     * 
     * @return ResponseEntity con los datos del indicador 17.9.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_9_1();
    
    /**
     * Obtiene el indicador 17.10.1
     * Promedio arancelario mundial ponderado
     * 
     * @return ResponseEntity con los datos del indicador 17.10.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_10_1();
    
    /**
     * Obtiene el indicador 17.11.1
     * Participación de países en desarrollo en las exportaciones mundiales
     * 
     * @return ResponseEntity con los datos del indicador 17.11.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_11_1();
    
    /**
     * Obtiene el indicador 17.12.1
     * Promedio ponderado de los aranceles que enfrentan países en desarrollo
     * 
     * @return ResponseEntity con los datos del indicador 17.12.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_12_1();
    
    /**
     * Obtiene el indicador 17.13.1
     * Tablero macroeconómico
     * 
     * @return ResponseEntity con los datos del indicador 17.13.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_13_1();
    
    /**
     * Obtiene el indicador 17.14.1
     * Número de países con mecanismos para mejorar coherencia de políticas
     * 
     * @return ResponseEntity con los datos del indicador 17.14.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_14_1();
    
    /**
     * Obtiene el indicador 17.15.1
     * Grado de utilización de marcos de resultados y herramientas de planificación
     * 
     * @return ResponseEntity con los datos del indicador 17.15.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_15_1();
    
    /**
     * Obtiene el indicador 17.16.1
     * Número de países que informan de progresos en marcos de múltiples interesados
     * 
     * @return ResponseEntity con los datos del indicador 17.16.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_16_1();
    
    /**
     * Obtiene el indicador 17.17.1
     * Suma en dólares prometida a alianzas público-privadas centradas en infraestructura
     * 
     * @return ResponseEntity con los datos del indicador 17.17.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_17_1();
    
    /**
     * Obtiene el indicador 17.18.1
     * Indicadores de la capacidad estadística
     * 
     * @return ResponseEntity con los datos del indicador 17.18.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_18_1();
    
    /**
     * Obtiene el indicador 17.18.2
     * Número de países cuya legislación cumple Principios Fundamentales de Estadísticas
     * 
     * @return ResponseEntity con los datos del indicador 17.18.2
     */
    ResponseEntity<IndicatorData> getIndicador_17_18_2();
    
    /**
     * Obtiene el indicador 17.18.3
     * Número de países con plan estadístico nacional plenamente financiado
     * 
     * @return ResponseEntity con los datos del indicador 17.18.3
     */
    ResponseEntity<IndicatorData> getIndicador_17_18_3();
    
    /**
     * Obtiene el indicador 17.19.1
     * Valor en dólares de recursos para fortalecer capacidad estadística
     * 
     * @return ResponseEntity con los datos del indicador 17.19.1
     */
    ResponseEntity<IndicatorData> getIndicador_17_19_1();
    
    /**
     * Obtiene el indicador 17.19.2
     * Proporción de países que han realizado censo y registrado nacimientos y defunciones
     * 
     * @return ResponseEntity con los datos del indicador 17.19.2
     */
    ResponseEntity<IndicatorData> getIndicador_17_19_2();
}
