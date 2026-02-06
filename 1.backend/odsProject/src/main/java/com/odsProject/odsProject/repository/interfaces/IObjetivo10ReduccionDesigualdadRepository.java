package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 10: Reducción de las Desigualdades
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 10
 */
public interface IObjetivo10ReduccionDesigualdadRepository {
    
    /**
     * 10.1.1 Tasas de crecimiento per cápita de los gastos o ingresos de los hogares del 40% más pobre 
     * de la población y la población total [75]
     * 
     * @return Datos del indicador 10.1.1
     */
    IndicatorData findIndicador_10_1_1();
    
    /**
     * 10.2.1 Proporción de personas que viven por debajo del 50% de la mediana de los ingresos, 
     * desglosada por sexo, edad y personas con discapacidad [76]
     * 
     * @return Datos del indicador 10.2.1
     */
    IndicatorData findIndicador_10_2_1();
    
    /**
     * 10.3.1 Proporción de la población que declara haberse sentido personalmente discriminada 
     * o acosada en los últimos 12 meses por motivos de discriminación prohibidos 
     * por el derecho internacional de los derechos humanos [76, 77]
     * 
     * @return Datos del indicador 10.3.1
     */
    IndicatorData findIndicador_10_3_1();
    
    /**
     * 10.4.1 Proporción del PIB generada por el trabajo [77]
     * 
     * @return Datos del indicador 10.4.1
     */
    IndicatorData findIndicador_10_4_1();
    
    /**
     * 10.4.2 Impacto redistributivo de la política fiscal en el índice de Gini [77]
     * 
     * @return Datos del indicador 10.4.2
     */
    IndicatorData findIndicador_10_4_2();
    
    /**
     * 10.5.1 Indicadores de solidez financiera [78]
     * 
     * @return Datos del indicador 10.5.1
     */
    IndicatorData findIndicador_10_5_1();
    
    /**
     * 10.6.1 Proporción de miembros y derechos de voto de los países en desarrollo 
     * en organizaciones internacionales [78]
     * 
     * @return Datos del indicador 10.6.1
     */
    IndicatorData findIndicador_10_6_1();
    
    /**
     * 10.7.1 Costo de la contratación sufragado por el empleado en proporción a los ingresos 
     * mensuales percibidos en el país de destino [79]
     * 
     * @return Datos del indicador 10.7.1
     */
    IndicatorData findIndicador_10_7_1();
    
    /**
     * 10.7.2 Proporción de países que han aplicado políticas migratorias bien gestionadas 
     * que facilitan la migración y la movilidad ordenadas, seguras, regulares y responsables de las personas [79, 80]
     * 
     * @return Datos del indicador 10.7.2
     */
    IndicatorData findIndicador_10_7_2();
    
    /**
     * 10.7.3 Número de personas que murieron o desaparecieron en el proceso de migración 
     * hacia un destino internacional [80]
     * 
     * @return Datos del indicador 10.7.3
     */
    IndicatorData findIndicador_10_7_3();
    
    /**
     * 10.7.4 Proporción de la población integrada por refugiados, desglosada por país de origen [80]
     * 
     * @return Datos del indicador 10.7.4
     */
    IndicatorData findIndicador_10_7_4();
    
    /**
     * 10.a.1 Proporción de líneas arancelarias que se aplican a las importaciones de los países 
     * menos adelantados y los países en desarrollo con arancel cero [80, 81]
     * 
     * @return Datos del indicador 10.a.1
     */
    IndicatorData findIndicador_10_a_1();
    
    /**
     * 10.b.1 Corrientes totales de recursos para el desarrollo (por ejemplo, asistencia oficial 
     * para el desarrollo, inversión extranjera directa y otras corrientes) [81, 82]
     * 
     * @return Datos del indicador 10.b.1
     */
    IndicatorData findIndicador_10_b_1();
    
    /**
     * 10.c.1 Costo de las remesas en proporción a las sumas remitidas [82]
     * 
     * @return Datos del indicador 10.c.1
     */
    IndicatorData findIndicador_10_c_1();
}
