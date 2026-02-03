package com.projectods.repository.interfaces;

import com.projectods.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 2: Hambre Cero
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 2
 */
public interface IObjetivo02HambreCeroRepository {
    
    /**
     * 2.1.1 Prevalencia de la subalimentación [10]
     * 
     * @return Datos del indicador 2.1.1
     */
    IndicatorData findIndicador_2_1_1();
    
    /**
     * 2.1.2 Prevalencia de la inseguridad alimentaria moderada o grave entre la población, 
     * según la escala de experiencia de inseguridad alimentaria [10]
     * 
     * @return Datos del indicador 2.1.2
     */
    IndicatorData findIndicador_2_1_2();
    
    /**
     * 2.2.1 Prevalencia del retraso del crecimiento (estatura para la edad, desviación típica < -2 
     * de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años [11]
     * 
     * @return Datos del indicador 2.2.1
     */
    IndicatorData findIndicador_2_2_1();
    
    /**
     * 2.2.2 Prevalencia de la malnutrición (peso para la estatura, desviación típica > +2 o < -2 
     * de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años, 
     * desglosada por tipo (emaciación y sobrepeso) [11, 12]
     * 
     * @return Datos del indicador 2.2.2
     */
    IndicatorData findIndicador_2_2_2();
    
    /**
     * 2.2.3 Prevalencia de la anemia en las mujeres de entre 15 y 49 años, 
     * desglosada por embarazo (porcentaje) [12]
     * 
     * @return Datos del indicador 2.2.3
     */
    IndicatorData findIndicador_2_2_3();
    
    /**
     * 2.2.4 Prevalencia del umbral mínimo de diversidad alimentaria, por grupo de población 
     * (niños de 6 a 23,9 meses y mujeres no embarazadas de 15 a 49 años) [12]
     * 
     * @return Datos del indicador 2.2.4
     */
    IndicatorData findIndicador_2_2_4();
    
    /**
     * 2.3.1 Volumen de producción por unidad de trabajo desglosado por tamaño y tipo de explotación 
     * (agropecuaria/ganadera/forestal) [13]
     * 
     * @return Datos del indicador 2.3.1
     */
    IndicatorData findIndicador_2_3_1();
    
    /**
     * 2.3.2 Media de ingresos de los productores de alimentos en pequeña escala, 
     * desglosada por sexo y condición indígena [13]
     * 
     * @return Datos del indicador 2.3.2
     */
    IndicatorData findIndicador_2_3_2();
    
    /**
     * 2.4.1 Proporción de la superficie agrícola en que se practica una agricultura productiva y sostenible [14]
     * 
     * @return Datos del indicador 2.4.1
     */
    IndicatorData findIndicador_2_4_1();
    
    /**
     * 2.5.1 Número de: a) recursos genéticos vegetales y b) animales para la alimentación y la agricultura 
     * preservados en instalaciones de conservación a medio y largo plazo [15, 16]
     * 
     * @return Datos del indicador 2.5.1
     */
    IndicatorData findIndicador_2_5_1();
    
    /**
     * 2.5.2 Proporción de razas y variedades locales y transfronterizas consideradas en riesgo de extinción [16]
     * 
     * @return Datos del indicador 2.5.2
     */
    IndicatorData findIndicador_2_5_2();
    
    /**
     * 2.a.1 Índice de orientación agrícola para el gasto público [17]
     * 
     * @return Datos del indicador 2.a.1
     */
    IndicatorData findIndicador_2_a_1();
    
    /**
     * 2.a.2 Total de corrientes oficiales de recursos (asistencia oficial para el desarrollo más otras corrientes oficiales) 
     * destinado al sector agrícola [17]
     * 
     * @return Datos del indicador 2.a.2
     */
    IndicatorData findIndicador_2_a_2();
    
    /**
     * 2.b.1 Subsidios a la exportación de productos agropecuarios [18]
     * 
     * @return Datos del indicador 2.b.1
     */
    IndicatorData findIndicador_2_b_1();
    
    /**
     * 2.c.1 Indicador de anomalías en los precios de los alimentos [18]
     * 
     * @return Datos del indicador 2.c.1
     */
    IndicatorData findIndicador_2_c_1();
}
