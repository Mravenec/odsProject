package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 10: Reducción de las Desigualdades
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 10
 */
public interface IObjetivo10ReduccionDesigualdadService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 10: Reducción de las Desigualdades
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 10.1.1
     * Tasas de crecimiento per cápita de los gastos del 40% más pobre
     * 
     * @return Datos del indicador 10.1.1
     */
    IndicatorData getIndicador_10_1_1();
    
    /**
     * Obtiene el indicador 10.2.1
     * Proporción de personas que viven por debajo del 50% de la mediana de los ingresos
     * 
     * @return Datos del indicador 10.2.1
     */
    IndicatorData getIndicador_10_2_1();
    
    /**
     * Obtiene el indicador 10.3.1
     * Proporción de la población que se siente discriminada o acosada
     * 
     * @return Datos del indicador 10.3.1
     */
    IndicatorData getIndicador_10_3_1();
    
    /**
     * Obtiene el indicador 10.4.1
     * Proporción del PIB generada por el trabajo
     * 
     * @return Datos del indicador 10.4.1
     */
    IndicatorData getIndicador_10_4_1();
    
    /**
     * Obtiene el indicador 10.4.2
     * Impacto redistributivo de la política fiscal en el índice de Gini
     * 
     * @return Datos del indicador 10.4.2
     */
    IndicatorData getIndicador_10_4_2();
    
    /**
     * Obtiene el indicador 10.5.1
     * Indicadores de solidez financiera
     * 
     * @return Datos del indicador 10.5.1
     */
    IndicatorData getIndicador_10_5_1();
    
    /**
     * Obtiene el indicador 10.6.1
     * Proporción de miembros y derechos de voto de países en desarrollo
     * 
     * @return Datos del indicador 10.6.1
     */
    IndicatorData getIndicador_10_6_1();
    
    /**
     * Obtiene el indicador 10.7.1
     * Costo de la contratación sufragado por el empleado
     * 
     * @return Datos del indicador 10.7.1
     */
    IndicatorData getIndicador_10_7_1();
    
    /**
     * Obtiene el indicador 10.7.2
     * Proporción de países con políticas migratorias bien gestionadas
     * 
     * @return Datos del indicador 10.7.2
     */
    IndicatorData getIndicador_10_7_2();
    
    /**
     * Obtiene el indicador 10.7.3
     * Número de personas que murieron o desaparecieron en proceso de migración
     * 
     * @return Datos del indicador 10.7.3
     */
    IndicatorData getIndicador_10_7_3();
    
    /**
     * Obtiene el indicador 10.7.4
     * Proporción de la población integrada por refugiados
     * 
     * @return Datos del indicador 10.7.4
     */
    IndicatorData getIndicador_10_7_4();
    
    /**
     * Obtiene el indicador 10.a.1
     * Proporción de líneas arancelarias con arancel cero para países menos adelantados
     * 
     * @return Datos del indicador 10.a.1
     */
    IndicatorData getIndicador_10_a_1();
    
    /**
     * Obtiene el indicador 10.b.1
     * Corrientes totales de recursos para el desarrollo
     * 
     * @return Datos del indicador 10.b.1
     */
    IndicatorData getIndicador_10_b_1();
    
    /**
     * Obtiene el indicador 10.c.1
     * Costo de las remesas en proporción a las sumas remitidas
     * 
     * @return Datos del indicador 10.c.1
     */
    IndicatorData getIndicador_10_c_1();
}
