package com.projectods.repository.interfaces;

import com.projectods.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 15: Vida de Ecosistemas Terrestres
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 15
 */
public interface IObjetivo15VidaEcosistemasRepository {
    
    /**
     * 15.1.1 Superficie forestal en proporción a la superficie total [117]
     * 
     * @return Datos del indicador 15.1.1
     */
    IndicatorData findIndicador_15_1_1();
    
    /**
     * 15.1.2 Proporción de lugares importantes para la biodiversidad terrestre y del agua dulce 
     * incluidos en zonas protegidas, desglosada por tipo de ecosistema [117, 118]
     * 
     * @return Datos del indicador 15.1.2
     */
    IndicatorData findIndicador_15_1_2();
    
    /**
     * 15.2.1 Avances hacia la gestión forestal sostenible [118]
     * 
     * @return Datos del indicador 15.2.1
     */
    IndicatorData findIndicador_15_2_1();
    
    /**
     * 15.3.1 Proporción de tierras degradadas en comparación con la superficie total [119]
     * 
     * @return Datos del indicador 15.3.1
     */
    IndicatorData findIndicador_15_3_1();
    
    /**
     * 15.4.1 Lugares importantes para la biodiversidad de las montañas incluidos en zonas protegidas [120]
     * 
     * @return Datos del indicador 15.4.1
     */
    IndicatorData findIndicador_15_4_1();
    
    /**
     * 15.4.2 a) Índice de cobertura verde de las montañas y b) proporción de terreno montañoso degradado [120]
     * 
     * @return Datos del indicador 15.4.2
     */
    IndicatorData findIndicador_15_4_2();
    
    /**
     * 15.5.1 Índice de la Lista Roja [121]
     * 
     * @return Datos del indicador 15.5.1
     */
    IndicatorData findIndicador_15_5_1();
    
    /**
     * 15.6.1 Número de países que han adoptado marcos legislativos, administrativos y normativos 
     * para asegurar una distribución justa y equitativa de los beneficios [121]
     * 
     * @return Datos del indicador 15.6.1
     */
    IndicatorData findIndicador_15_6_1();
    
    /**
     * 15.7.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes 
     * de la caza furtiva o el tráfico ilícito [122]
     * 
     * @return Datos del indicador 15.7.1
     */
    IndicatorData findIndicador_15_7_1();
    
    /**
     * 15.8.1 Proporción de países que han aprobado la legislación nacional pertinente 
     * y han destinado recursos suficientes para la prevención o el control de las especies exóticas invasoras [122, 123]
     * 
     * @return Datos del indicador 15.8.1
     */
    IndicatorData findIndicador_15_8_1();
    
    /**
     * 15.9.1 a) Número de países que han establecido metas nacionales acordes o similares a la Meta 14 
     * del Marco Mundial de Biodiversidad de Kunming-Montreal en sus estrategias y planes de acción nacionales 
     * en materia de diversidad biológica y los progresos notificados en la consecución de dichas metas; 
     * y b) integración de la diversidad biológica en los sistemas nacionales de contabilidad y presentación de informes, 
     * definida como aplicación del Sistema de Contabilidad Ambiental y Económica [123, 124]
     * 
     * @return Datos del indicador 15.9.1
     */
    IndicatorData findIndicador_15_9_1();
    
    /**
     * 15.a.1 a) Asistencia oficial para el desarrollo destinada concretamente a la conservación 
     * y el uso sostenible de la biodiversidad y b) ingresos generados y financiación movilizada 
     * mediante instrumentos económicos pertinentes para la biodivers [124, 125]
     * 
     * @return Datos del indicador 15.a.1
     */
    IndicatorData findIndicador_15_a_1();
    
    /**
     * 15.b.1 a) Asistencia oficial para el desarrollo destinada concretamente a la conservación 
     * y el uso sostenible de la biodiversidad y b) ingresos generados y financiación movilizada 
     * mediante instrumentos económicos pertinentes para la biodivers [125, 126]
     * 
     * @return Datos del indicador 15.b.1
     */
    IndicatorData findIndicador_15_b_1();
    
    /**
     * 15.c.1 Proporción de especímenes de flora y fauna silvestre comercializados procedentes 
     * de la caza furtiva o el tráfico ilícito [126]
     * 
     * @return Datos del indicador 15.c.1
     */
    IndicatorData findIndicador_15_c_1();
}
