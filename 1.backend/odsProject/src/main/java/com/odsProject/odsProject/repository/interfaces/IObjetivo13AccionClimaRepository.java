package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 13: Acción por el Clima
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 13
 */
public interface IObjetivo13AccionClimaRepository {
    
    /**
     * 13.1.1 Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres 
     * por cada 100.000 personas [102]
     * 
     * @return Datos del indicador 13.1.1
     */
    IndicatorData findIndicador_13_1_1();
    
    /**
     * 13.1.2 Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo de desastres 
     * en consonancia con el Marco de Sendái para la Reducción del Riesgo de Desastres 2015-2030 [103]
     * 
     * @return Datos del indicador 13.1.2
     */
    IndicatorData findIndicador_13_1_2();
    
    /**
     * 13.1.3 Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción del riesgo 
     * de desastres en consonancia con las estrategias nacionales de reducción del riesgo de desastres [103]
     * 
     * @return Datos del indicador 13.1.3
     */
    IndicatorData findIndicador_13_1_3();
    
    /**
     * 13.2.1 Número de países con contribuciones determinadas a nivel nacional, estrategias a largo plazo, 
     * planes nacionales de adaptación y comunicaciones sobre la adaptación, notificadas a la secretaría 
     * de la Convención Marco de las Naciones Unidas sobre el Cambio Climático [104]
     * 
     * @return Datos del indicador 13.2.1
     */
    IndicatorData findIndicador_13_2_1();
    
    /**
     * 13.2.2 Emisiones totales de gases de efecto invernadero por año [104]
     * 
     * @return Datos del indicador 13.2.2
     */
    IndicatorData findIndicador_13_2_2();
    
    /**
     * 13.3.1 Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible 
     * se incorporan en a) las políticas nacionales de educación, b) los planes de estudio, c) la formación de docentes 
     * y d) la evaluación de los estudiantes [105]
     * 
     * @return Datos del indicador 13.3.1
     */
    IndicatorData findIndicador_13_3_1();
    
    /**
     * 13.a.1 Cantidades proporcionadas y movilizadas en dólares de los Estados Unidos al año 
     * en relación con el objetivo actual mantenido de movilización colectiva de 100.000 millones de dólares 
     * de aquí a 2025 [106]
     * 
     * @return Datos del indicador 13.a.1
     */
    IndicatorData findIndicador_13_a_1();
    
    /**
     * 13.b.1 Número de países menos adelantados y pequeños Estados insulares en desarrollo 
     * con contribuciones determinadas a nivel nacional, estrategias a largo plazo, planes nacionales de adaptación 
     * y comunicaciones sobre la adaptación, notificadas a la secretaría de la Convención Marco de las Naciones Unidas 
     * sobre el Cambio Climático [107, 108]
     * 
     * @return Datos del indicador 13.b.1
     */
    IndicatorData findIndicador_13_b_1();
}
