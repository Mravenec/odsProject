package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 8
 */
public interface IObjetivo08CrecimientoEconomicoRepository {
    
    /**
     * 8.1.1 Tasa de crecimiento anual del PIB real per cápita [59]
     * 
     * @return Datos del indicador 8.1.1
     */
    IndicatorData findIndicador_8_1_1();
    
    /**
     * 8.2.1 Tasa de crecimiento anual del PIB real por persona empleada [59]
     * 
     * @return Datos del indicador 8.2.1
     */
    IndicatorData findIndicador_8_2_1();
    
    /**
     * 8.3.1 Proporción de empleo informal con respecto al empleo total, desglosada por sector y sexo [60]
     * 
     * @return Datos del indicador 8.3.1
     */
    IndicatorData findIndicador_8_3_1();
    
    /**
     * 8.4.1 Huella material en términos absolutos, huella material per cápita y huella material por PIB [60]
     * 
     * @return Datos del indicador 8.4.1
     */
    IndicatorData findIndicador_8_4_1();
    
    /**
     * 8.4.2 Consumo material interno en términos absolutos, consumo material interno per cápita 
     * y consumo material interno por PIB [61]
     * 
     * @return Datos del indicador 8.4.2
     */
    IndicatorData findIndicador_8_4_2();
    
    /**
     * 8.5.1 Ingreso medio por hora de las personas empleadas, desglosado por sexo, edad, 
     * ocupación y personas con discapacidad [61]
     * 
     * @return Datos del indicador 8.5.1
     */
    IndicatorData findIndicador_8_5_1();
    
    /**
     * 8.5.2 Tasa de desempleo, desglosada por sexo, edad y personas con discapacidad [61, 62]
     * 
     * @return Datos del indicador 8.5.2
     */
    IndicatorData findIndicador_8_5_2();
    
    /**
     * 8.6.1 Proporción de jóvenes (entre 15 y 24 años) que no cursan estudios, 
     * no están empleados ni reciben capacitación [62]
     * 
     * @return Datos del indicador 8.6.1
     */
    IndicatorData findIndicador_8_6_1();
    
    /**
     * 8.7.1 Proporción y número de niños de entre 5 y 17 años que realizan trabajo infantil, 
     * desglosados por sexo y edad [63]
     * 
     * @return Datos del indicador 8.7.1
     */
    IndicatorData findIndicador_8_7_1();
    
    /**
     * 8.8.1 Lesiones ocupacionales mortales y no mortales por cada 100.000 trabajadores, 
     * desglosadas por sexo y estatus migratorio [63, 64]
     * 
     * @return Datos del indicador 8.8.1
     */
    IndicatorData findIndicador_8_8_1();
    
    /**
     * 8.8.2 Nivel de cumplimiento nacional de los derechos laborales (libertad de asociación 
     * y negociación colectiva) con arreglo a las fuentes textuales de la Organización Internacional 
     * del Trabajo (OIT) y la legislación interna, desglosado por sexo y estatus migratorio [64]
     * 
     * @return Datos del indicador 8.8.2
     */
    IndicatorData findIndicador_8_8_2();
    
    /**
     * 8.9.1 PIB generado directamente por el turismo en proporción al PIB total y a la tasa de crecimiento [65]
     * 
     * @return Datos del indicador 8.9.1
     */
    IndicatorData findIndicador_8_9_1();
    
    /**
     * 8.9.2 Personas empleadas en el sector del turismo [65]
     * 
     * @return Datos del indicador 8.9.2
     */
    IndicatorData findIndicador_8_9_2();
    
    /**
     * 8.10.1 a) Número de sucursales de bancos comerciales por cada 100.000 adultos 
     * y b) número de cajeros automáticos por cada 100.000 adultos [65]
     * 
     * @return Datos del indicador 8.10.1
     */
    IndicatorData findIndicador_8_10_1();
    
    /**
     * 8.10.2 Proporción de adultos (a partir de 15 años de edad) que tienen una cuenta en un banco 
     * u otra institución financiera o un proveedor de servicios de dinero móvil [66]
     * 
     * @return Datos del indicador 8.10.2
     */
    IndicatorData findIndicador_8_10_2();
    
    /**
     * 8.a.1 Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio [67]
     * 
     * @return Datos del indicador 8.a.1
     */
    IndicatorData findIndicador_8_a_1();
    
    /**
     * 8.b.1 Existencia de una estrategia nacional organizada y en marcha para el empleo de los jóvenes, 
     * como estrategia independiente o como parte de una estrategia nacional de empleo [67]
     * 
     * @return Datos del indicador 8.b.1
     */
    IndicatorData findIndicador_8_b_1();
}
