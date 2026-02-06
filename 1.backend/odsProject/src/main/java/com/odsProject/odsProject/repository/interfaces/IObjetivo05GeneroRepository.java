package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 5: Igualdad de Género
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 5
 */
public interface IObjetivo05GeneroRepository {
    
    /**
     * 5.1.1 Determinar si existen o no marcos jurídicos para promover, hacer cumplir y supervisar 
     * la igualdad y la no discriminación por razón de sexo [41]
     * 
     * @return Datos del indicador 5.1.1
     */
    IndicatorData findIndicador_5_1_1();
    
    /**
     * 5.2.1 Proporción de mujeres y niñas a partir de 15 años de edad que han sufrido violencia física, 
     * sexual o psicológica a manos de su actual o anterior pareja en los últimos 12 meses, 
     * desglosada por forma de violencia y edad [41, 42]
     * 
     * @return Datos del indicador 5.2.1
     */
    IndicatorData findIndicador_5_2_1();
    
    /**
     * 5.2.2 Proporción de mujeres y niñas a partir de 15 años de edad que han sufrido violencia sexual 
     * a manos de personas que no eran su pareja en los últimos 12 meses, desglosada por edad y lugar del hecho [42]
     * 
     * @return Datos del indicador 5.2.2
     */
    IndicatorData findIndicador_5_2_2();
    
    /**
     * 5.3.1 Proporción de mujeres de entre 20 y 24 años que estaban casadas o mantenían una unión estable 
     * antes de cumplir los 15 años y antes de cumplir los 18 años [42, 43]
     * 
     * @return Datos del indicador 5.3.1
     */
    IndicatorData findIndicador_5_3_1();
    
    /**
     * 5.3.2 Proporción de niñas y mujeres de entre 15 y 49 años que han sufrido mutilación genital femenina, 
     * desglosada por edad [43]
     * 
     * @return Datos del indicador 5.3.2
     */
    IndicatorData findIndicador_5_3_2();
    
    /**
     * 5.4.1 Proporción de tiempo dedicado al trabajo doméstico y asistencial no remunerado, 
     * desglosada por sexo, edad y ubicación [43, 44]
     * 
     * @return Datos del indicador 5.4.1
     */
    IndicatorData findIndicador_5_4_1();
    
    /**
     * 5.5.1 Proporción de escaños ocupados por mujeres en a) los parlamentos nacionales y b) los gobiernos locales [44]
     * 
     * @return Datos del indicador 5.5.1
     */
    IndicatorData findIndicador_5_5_1();
    
    /**
     * 5.5.2 Proporción de mujeres en cargos directivos [44]
     * 
     * @return Datos del indicador 5.5.2
     */
    IndicatorData findIndicador_5_5_2();
    
    /**
     * 5.6.1 Proporción de mujeres de entre 15 y 49 años que toman sus propias decisiones informadas 
     * sobre las relaciones sexuales, el uso de anticonceptivos y la atención de la salud reproductiva [45]
     * 
     * @return Datos del indicador 5.6.1
     */
    IndicatorData findIndicador_5_6_1();
    
    /**
     * 5.6.2 Número de países con leyes y reglamentos que garantizan a los hombres y las mujeres 
     * a partir de los 15 años de edad un acceso pleno e igualitario a los servicios de salud sexual 
     * y reproductiva y a la información y educación al respecto [45, 46]
     * 
     * @return Datos del indicador 5.6.2
     */
    IndicatorData findIndicador_5_6_2();
    
    /**
     * 5.a.1 a) Proporción del total de la población agrícola con derechos de propiedad o derechos seguros 
     * sobre tierras agrícolas, desglosada por sexo; y b) proporción de mujeres entre los propietarios 
     * o los titulares de derechos sobre tierras agrícolas, desglosada por tipo de tenencia [46]
     * 
     * @return Datos del indicador 5.a.1
     */
    IndicatorData findIndicador_5_a_1();
    
    /**
     * 5.a.2 Proporción de países cuyo ordenamiento jurídico (incluido el derecho consuetudinario) 
     * garantiza la igualdad de derechos de la mujer a la propiedad o el control de las tierras [47]
     * 
     * @return Datos del indicador 5.a.2
     */
    IndicatorData findIndicador_5_a_2();
    
    /**
     * 5.b.1 Proporción de personas que poseen un teléfono móvil, desglosada por sexo [48]
     * 
     * @return Datos del indicador 5.b.1
     */
    IndicatorData findIndicador_5_b_1();
    
    /**
     * 5.c.1 Proporción de países con sistemas para el seguimiento de la igualdad de género 
     * y el empoderamiento de las mujeres y la asignación de fondos públicos para ese fin [48]
     * 
     * @return Datos del indicador 5.c.1
     */
    IndicatorData findIndicador_5_c_1();
}
