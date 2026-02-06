package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 12: Producción y Consumo Responsables
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 12
 */
public interface IObjetivo12ConsumoProduccionRepository {
    
    /**
     * 12.1.1 Número de países que elaboran, adoptan o aplican instrumentos de política destinados 
     * a apoyar la transición hacia modalidades de consumo y producción sostenibles [93, 94]
     * 
     * @return Datos del indicador 12.1.1
     */
    IndicatorData findIndicador_12_1_1();
    
    /**
     * 12.2.1 Huella material en términos absolutos, huella material per cápita y huella material por PIB [95]
     * 
     * @return Datos del indicador 12.2.1
     */
    IndicatorData findIndicador_12_2_1();
    
    /**
     * 12.2.2 Consumo material interno en términos absolutos, consumo material interno per cápita 
     * y consumo material interno por PIB [95]
     * 
     * @return Datos del indicador 12.2.2
     */
    IndicatorData findIndicador_12_2_2();
    
    /**
     * 12.3.1 a) Índice de pérdidas de alimentos y b) índice de desperdicio de alimentos [96]
     * 
     * @return Datos del indicador 12.3.1
     */
    IndicatorData findIndicador_12_3_1();
    
    /**
     * 12.4.1 Número de partes en los acuerdos ambientales multilaterales internacionales sobre 
     * desechos peligrosos y otros productos químicos que cumplen sus compromisos y obligaciones 
     * de transmitir información como se exige en cada uno de esos acuerdos [96, 97]
     * 
     * @return Datos del indicador 12.4.1
     */
    IndicatorData findIndicador_12_4_1();
    
    /**
     * 12.4.2 a) Desechos peligrosos generados per cápita y b) proporción de desechos peligrosos tratados, 
     * desglosados por tipo de tratamiento [97]
     * 
     * @return Datos del indicador 12.4.2
     */
    IndicatorData findIndicador_12_4_2();
    
    /**
     * 12.5.1 Tasa nacional de reciclado, en toneladas de material reciclado [97]
     * 
     * @return Datos del indicador 12.5.1
     */
    IndicatorData findIndicador_12_5_1();
    
    /**
     * 12.6.1 Número de empresas que publican informes sobre sostenibilidad [98]
     * 
     * @return Datos del indicador 12.6.1
     */
    IndicatorData findIndicador_12_6_1();
    
    /**
     * 12.7.1 Número de países que aplican políticas y planes de acción sostenibles en materia de adquisiciones públicas [98]
     * 
     * @return Datos del indicador 12.7.1
     */
    IndicatorData findIndicador_12_7_1();
    
    /**
     * 12.8.1 Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible 
     * se incorporan en a) las políticas nacionales de educación, b) los planes de estudio, c) la formación de docentes 
     * y d) la evaluación de los estudiantes [99]
     * 
     * @return Datos del indicador 12.8.1
     */
    IndicatorData findIndicador_12_8_1();
    
    /**
     * 12.a.1 Capacidad instalada de generación de energía renovable en los países en desarrollo 
     * y en los países desarrollados (en vatios per cápita) [100]
     * 
     * @return Datos del indicador 12.a.1
     */
    IndicatorData findIndicador_12_a_1();
    
    /**
     * 12.b.1 Aplicación de instrumentos normalizados de contabilidad para hacer un seguimiento 
     * de los aspectos económicos y ambientales de la sostenibilidad del turismo [100, 101]
     * 
     * @return Datos del indicador 12.b.1
     */
    IndicatorData findIndicador_12_b_1();
    
    /**
     * 12.c.1 Cuantía de los subsidios a los combustibles fósiles (producción y consumo) por unidad del PIB [101, 102]
     * 
     * @return Datos del indicador 12.c.1
     */
    IndicatorData findIndicador_12_c_1();
}
