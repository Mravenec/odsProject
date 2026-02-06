package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.model.IndicatorData;

/**
 * Interfaz del Repositorio para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 17
 */
public interface IObjetivo17AlianzasRepository {
    
    /**
     * 17.1.1 Total de ingresos del gobierno en proporción al PIB, desglosado por fuente [138]
     * 
     * @return Datos del indicador 17.1.1
     */
    IndicatorData findIndicador_17_1_1();
    
    /**
     * 17.1.2 Proporción del presupuesto nacional financiado por impuestos internos [139]
     * 
     * @return Datos del indicador 17.1.2
     */
    IndicatorData findIndicador_17_1_2();
    
    /**
     * 17.2.1 Asistencia oficial para el desarrollo neta, total y para los países menos adelantados 
     * en proporción al ingreso nacional bruto (INB) de los donantes del Comité de Asistencia para el Desarrollo de la OCDE [140]
     * 
     * @return Datos del indicador 17.2.1
     */
    IndicatorData findIndicador_17_2_1();
    
    /**
     * 17.3.1 Recursos financieros adicionales movilizados para los países en desarrollo 
     * procedentes de múltiples fuentes [141]
     * 
     * @return Datos del indicador 17.3.1
     */
    IndicatorData findIndicador_17_3_1();
    
    /**
     * 17.3.2 Volumen de remesas (en dólares de los Estados Unidos) en proporción al PIB total [141]
     * 
     * @return Datos del indicador 17.3.2
     */
    IndicatorData findIndicador_17_3_2();
    
    /**
     * 17.4.1 Servicio de la deuda en proporción a las exportaciones de bienes, servicios e ingresos primarios [142]
     * 
     * @return Datos del indicador 17.4.1
     */
    IndicatorData findIndicador_17_4_1();
    
    /**
     * 17.5.1 Número de países que adoptan y aplican sistemas de promoción de las inversiones 
     * en favor de los países en desarrollo, entre ellos los países menos adelantados [142]
     * 
     * @return Datos del indicador 17.5.1
     */
    IndicatorData findIndicador_17_5_1();
    
    /**
     * 17.6.1 Número de abonados a servicios de banda ancha fija por cada 100 habitantes, 
     * desglosado por velocidad [143]
     * 
     * @return Datos del indicador 17.6.1
     */
    IndicatorData findIndicador_17_6_1();
    
    /**
     * 17.7.1 Total de los fondos destinados a los países en desarrollo y los países desarrollados 
     * con el fin de promover el desarrollo, la transferencia y la difusión de tecnologías ecológicamente racionales [144, 145]
     * 
     * @return Datos del indicador 17.7.1
     */
    IndicatorData findIndicador_17_7_1();
    
    /**
     * 17.8.1 Proporción de personas que utilizan Internet [145]
     * 
     * @return Datos del indicador 17.8.1
     */
    IndicatorData findIndicador_17_8_1();
    
    /**
     * 17.9.1 Valor en dólares de la asistencia oficial para el desarrollo comprometida 
     * para los países en desarrollo [146]
     * 
     * @return Datos del indicador 17.9.1
     */
    IndicatorData findIndicador_17_9_1();
    
    /**
     * 17.10.1 Promedio arancelario mundial ponderado [147]
     * 
     * @return Datos del indicador 17.10.1
     */
    IndicatorData findIndicador_17_10_1();
    
    /**
     * 17.11.1 Participación de los países en desarrollo y los países menos adelantados 
     * en las exportaciones mundiales [147]
     * 
     * @return Datos del indicador 17.11.1
     */
    IndicatorData findIndicador_17_11_1();
    
    /**
     * 17.12.1 Promedio ponderado de los aranceles que enfrentan los países en desarrollo, 
     * los países menos adelantados y los pequeños Estados insulares en desarrollo [148]
     * 
     * @return Datos del indicador 17.12.1
     */
    IndicatorData findIndicador_17_12_1();
    
    /**
     * 17.13.1 Tablero macroeconómico [149]
     * 
     * @return Datos del indicador 17.13.1
     */
    IndicatorData findIndicador_17_13_1();
    
    /**
     * 17.14.1 Número de países que cuentan con mecanismos para mejorar la coherencia 
     * de las políticas de desarrollo sostenible [149]
     * 
     * @return Datos del indicador 17.14.1
     */
    IndicatorData findIndicador_17_14_1();
    
    /**
     * 17.15.1 Grado de utilización de los marcos de resultados y las herramientas de planificación 
     * de los propios países por los proveedores de cooperación para el desarrollo [150]
     * 
     * @return Datos del indicador 17.15.1
     */
    IndicatorData findIndicador_17_15_1();
    
    /**
     * 17.16.1 Número de países que informan de sus progresos en los marcos de múltiples interesados 
     * para el seguimiento de la eficacia de las actividades de desarrollo que apoyan el logro 
     * de los Objetivos de Desarrollo Sostenible [151]
     * 
     * @return Datos del indicador 17.16.1
     */
    IndicatorData findIndicador_17_16_1();
    
    /**
     * 17.17.1 Suma en dólares de los Estados Unidos prometida a las alianzas público-privadas 
     * centradas en la infraestructura [152]
     * 
     * @return Datos del indicador 17.17.1
     */
    IndicatorData findIndicador_17_17_1();
    
    /**
     * 17.18.1 Indicadores de la capacidad estadística [153]
     * 
     * @return Datos del indicador 17.18.1
     */
    IndicatorData findIndicador_17_18_1();
    
    /**
     * 17.18.2 Número de países cuya legislación nacional sobre estadísticas cumple 
     * los Principios Fundamentales de las Estadísticas Oficiales [153]
     * 
     * @return Datos del indicador 17.18.2
     */
    IndicatorData findIndicador_17_18_2();
    
    /**
     * 17.18.3 Número de países que cuentan con un plan estadístico nacional plenamente financiado 
     * y en proceso de aplicación, desglosado por fuente de financiación [153, 154]
     * 
     * @return Datos del indicador 17.18.3
     */
    IndicatorData findIndicador_17_18_3();
    
    /**
     * 17.19.1 Valor en dólares de todos los recursos proporcionados para fortalecer 
     * la capacidad estadística de los países en desarrollo [154]
     * 
     * @return Datos del indicador 17.19.1
     */
    IndicatorData findIndicador_17_19_1();
    
    /**
     * 17.19.2 Proporción de países que a) han realizado al menos un censo de población 
     * y vivienda en los últimos diez años; y b) han registrado el 100% de los nacimientos y el 80% de las defunciones [155]
     * 
     * @return Datos del indicador 17.19.2
     */
    IndicatorData findIndicador_17_19_2();
}
