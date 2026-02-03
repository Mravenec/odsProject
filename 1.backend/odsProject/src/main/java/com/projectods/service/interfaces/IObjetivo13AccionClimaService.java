package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 13: Acción por el Clima
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 13
 */
public interface IObjetivo13AccionClimaService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 13: Acción por el Clima
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 13.1.1
     * Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres
     * 
     * @return Datos del indicador 13.1.1
     */
    IndicatorData getIndicador_13_1_1();
    
    /**
     * Obtiene el indicador 13.1.2
     * Número de países que adoptan estrategias nacionales de reducción del riesgo de desastres
     * 
     * @return Datos del indicador 13.1.2
     */
    IndicatorData getIndicador_13_1_2();
    
    /**
     * Obtiene el indicador 13.1.3
     * Proporción de gobiernos locales que adoptan estrategias locales de reducción del riesgo
     * 
     * @return Datos del indicador 13.1.3
     */
    IndicatorData getIndicador_13_1_3();
    
    /**
     * Obtiene el indicador 13.2.1
     * Número de países con contribuciones determinadas a nivel nacional
     * 
     * @return Datos del indicador 13.2.1
     */
    IndicatorData getIndicador_13_2_1();
    
    /**
     * Obtiene el indicador 13.2.2
     * Emisiones totales de gases de efecto invernadero por año
     * 
     * @return Datos del indicador 13.2.2
     */
    IndicatorData getIndicador_13_2_2();
    
    /**
     * Obtiene el indicador 13.3.1
     * Grado en que se incorpora educación para el cambio climático
     * 
     * @return Datos del indicador 13.3.1
     */
    IndicatorData getIndicador_13_3_1();
    
    /**
     * Obtiene el indicador 13.a.1
     * Cantidades proporcionadas y movilizadas en relación con el objetivo de 100.000 millones
     * 
     * @return Datos del indicador 13.a.1
     */
    IndicatorData getIndicador_13_a_1();
    
    /**
     * Obtiene el indicador 13.b.1
     * Número de países menos adelantados y pequeños Estados insulares con planes climáticos
     * 
     * @return Datos del indicador 13.b.1
     */
    IndicatorData getIndicador_13_b_1();
}
