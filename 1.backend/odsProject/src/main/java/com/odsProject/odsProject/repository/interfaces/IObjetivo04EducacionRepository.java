package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 4: Educación de Calidad
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 4
 */
public interface IObjetivo04EducacionRepository {
    
    /**
     * 4.1.1 Proporción de niños y adolescentes que, a) en los grados 2 o 3, 
     * b) al final de la educación primaria y c) al final de la educación secundaria inferior, 
     * han alcanzado al menos un nivel mínimo de competencia en i) lectura y ii) matemáticas, 
     * desglosada por sexo [31]
     * 
     * @return Datos del indicador 4.1.1
     */
    IndicatorData findIndicador_4_1_1();
    
    /**
     * 4.1.2 Tasa de finalización (educación primaria, educación secundaria inferior y educación secundaria superior) [32]
     * 
     * @return Datos del indicador 4.1.2
     */
    IndicatorData findIndicador_4_1_2();
    
    /**
     * 4.2.1 Proporción de niños de 24 a 59 meses cuyo desarrollo es adecuado en cuanto a la salud, 
     * el aprendizaje y el bienestar psicosocial, desglosada por sexo [32]
     * 
     * @return Datos del indicador 4.2.1
     */
    IndicatorData findIndicador_4_2_1();
    
    /**
     * 4.2.2 Tasa de participación en el aprendizaje organizado (un año antes de la edad oficial 
     * de ingreso en la educación primaria), desglosada por sexo [33]
     * 
     * @return Datos del indicador 4.2.2
     */
    IndicatorData findIndicador_4_2_2();
    
    /**
     * 4.3.1 Tasa de participación de jóvenes y adultos en la educación y formación académica 
     * y no académica en los últimos 12 meses, desglosada por sexo [34]
     * 
     * @return Datos del indicador 4.3.1
     */
    IndicatorData findIndicador_4_3_1();
    
    /**
     * 4.4.1 Proporción de jóvenes y adultos con competencias en tecnología de la información 
     * y las comunicaciones (TIC), desglosada por tipo de competencia [34]
     * 
     * @return Datos del indicador 4.4.1
     */
    IndicatorData findIndicador_4_4_1();
    
    /**
     * 4.5.1 Índices de paridad (entre mujeres y hombres, zonas rurales y urbanas, 
     * quintiles de riqueza superior e inferior y grupos como los discapacitados, los pueblos indígenas 
     * y los afectados por los conflictos, a medida que se disponga de datos) para todos los indicadores 
     * de educación de esta lista que puedan desglosarse [35, 36]
     * 
     * @return Datos del indicador 4.5.1
     */
    IndicatorData findIndicador_4_5_1();
    
    /**
     * 4.6.1 Tasa de alfabetización de adultos/jóvenes [36]
     * 
     * @return Datos del indicador 4.6.1
     */
    IndicatorData findIndicador_4_6_1();
    
    /**
     * 4.7.1 Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo 
     * sostenible se incorporan en a) las políticas nacionales de educación, b) los planes de estudio, 
     * c) la formación de docentes y d) la evaluación de los estudiantes [37]
     * 
     * @return Datos del indicador 4.7.1
     */
    IndicatorData findIndicador_4_7_1();
    
    /**
     * 4.a.1 Proporción de escuelas que ofrecen servicios básicos, desglosada por tipo de servicio [38]
     * 
     * @return Datos del indicador 4.a.1
     */
    IndicatorData findIndicador_4_a_1();
    
    /**
     * 4.b.1 Volumen de la asistencia oficial para el desarrollo destinada a becas [39]
     * 
     * @return Datos del indicador 4.b.1
     */
    IndicatorData findIndicador_4_b_1();
    
    /**
     * 4.c.1 Proporción de docentes con las calificaciones mínimas requeridas, 
     * desglosada por nivel educativo [40]
     * 
     * @return Datos del indicador 4.c.1
     */
    IndicatorData findIndicador_4_c_1();
}
