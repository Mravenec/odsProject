package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 7: Energía Asequible y No Contaminante
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 7
 */
public interface IObjetivo07EnergiaService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 7: Energía Asequible y No Contaminante
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 7.1.1
     * Proporción de la población que tiene acceso a la electricidad
     * 
     * @return Datos del indicador 7.1.1
     */
    IndicatorData getIndicador_7_1_1();
    
    /**
     * Obtiene el indicador 7.1.2
     * Proporción de la población con combustibles y tecnologías limpios
     * 
     * @return Datos del indicador 7.1.2
     */
    IndicatorData getIndicador_7_1_2();
    
    /**
     * Obtiene el indicador 7.2.1
     * Proporción de energía renovable en el consumo final total
     * 
     * @return Datos del indicador 7.2.1
     */
    IndicatorData getIndicador_7_2_1();
    
    /**
     * Obtiene el indicador 7.3.1
     * Intensidad energética medida en función de la energía primaria y el PIB
     * 
     * @return Datos del indicador 7.3.1
     */
    IndicatorData getIndicador_7_3_1();
    
    /**
     * Obtiene el indicador 7.a.1
     * Corrientes financieras internacionales para energías limpias
     * 
     * @return Datos del indicador 7.a.1
     */
    IndicatorData getIndicador_7_a_1();
    
    /**
     * Obtiene el indicador 7.b.1
     * Capacidad instalada de generación de energía renovable
     * 
     * @return Datos del indicador 7.b.1
     */
    IndicatorData getIndicador_7_b_1();
}
