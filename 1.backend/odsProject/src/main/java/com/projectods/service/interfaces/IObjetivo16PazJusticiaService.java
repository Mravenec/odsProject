package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 16
 */
public interface IObjetivo16PazJusticiaService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 16: Paz, Justicia e Instituciones Sólidas
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 16.1.1
     * Número de víctimas de homicidios intencionales por cada 100.000 habitantes
     * 
     * @return Datos del indicador 16.1.1
     */
    IndicatorData getIndicador_16_1_1();
    
    /**
     * Obtiene el indicador 16.1.2
     * Muertes relacionadas con conflictos por cada 100.000 habitantes
     * 
     * @return Datos del indicador 16.1.2
     */
    IndicatorData getIndicador_16_1_2();
    
    /**
     * Obtiene el indicador 16.1.3
     * Proporción de la población que ha sufrido violencia física, psicológica o sexual
     * 
     * @return Datos del indicador 16.1.3
     */
    IndicatorData getIndicador_16_1_3();
    
    /**
     * Obtiene el indicador 16.1.4
     * Proporción de la población que se siente segura caminando sola después de oscurecer
     * 
     * @return Datos del indicador 16.1.4
     */
    IndicatorData getIndicador_16_1_4();
    
    /**
     * Obtiene el indicador 16.2.1
     * Proporción de niños que han sufrido castigo físico o agresión psicológica
     * 
     * @return Datos del indicador 16.2.1
     */
    IndicatorData getIndicador_16_2_1();
    
    /**
     * Obtiene el indicador 16.2.2
     * Número de víctimas de la trata de personas por cada 100.000 habitantes
     * 
     * @return Datos del indicador 16.2.2
     */
    IndicatorData getIndicador_16_2_2();
    
    /**
     * Obtiene el indicador 16.2.3
     * Proporción de jóvenes que sufrieron violencia sexual antes de cumplir los 18 años
     * 
     * @return Datos del indicador 16.2.3
     */
    IndicatorData getIndicador_16_2_3();
    
    /**
     * Obtiene el indicador 16.3.1
     * Proporción de víctimas que han notificado su victimización a las autoridades
     * 
     * @return Datos del indicador 16.3.1
     */
    IndicatorData getIndicador_16_3_1();
    
    /**
     * Obtiene el indicador 16.3.2
     * Proporción de detenidos que no han sido condenados en la población reclusa total
     * 
     * @return Datos del indicador 16.3.2
     */
    IndicatorData getIndicador_16_3_2();
    
    /**
     * Obtiene el indicador 16.3.3
     * Proporción de la población que ha accedido a mecanismos de solución de controversias
     * 
     * @return Datos del indicador 16.3.3
     */
    IndicatorData getIndicador_16_3_3();
    
    /**
     * Obtiene el indicador 16.4.1
     * Valor total de las corrientes financieras ilícitas entrantes y salientes
     * 
     * @return Datos del indicador 16.4.1
     */
    IndicatorData getIndicador_16_4_1();
    
    /**
     * Obtiene el indicador 16.4.2
     * Proporción de armas incautadas con origen ilícito determinado
     * 
     * @return Datos del indicador 16.4.2
     */
    IndicatorData getIndicador_16_4_2();
    
    /**
     * Obtiene el indicador 16.5.1
     * Proporción de personas que han pagado un soborno a un funcionario público
     * 
     * @return Datos del indicador 16.5.1
     */
    IndicatorData getIndicador_16_5_1();
    
    /**
     * Obtiene el indicador 16.5.2
     * Proporción de negocios que han pagado un soborno a un funcionario público
     * 
     * @return Datos del indicador 16.5.2
     */
    IndicatorData getIndicador_16_5_2();
    
    /**
     * Obtiene el indicador 16.6.1
     * Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente
     * 
     * @return Datos del indicador 16.6.1
     */
    IndicatorData getIndicador_16_6_1();
    
    /**
     * Obtiene el indicador 16.6.2
     * Proporción de la población satisfecha con servicios públicos
     * 
     * @return Datos del indicador 16.6.2
     */
    IndicatorData getIndicador_16_6_2();
    
    /**
     * Obtiene el indicador 16.7.1
     * Proporciones de plazas en instituciones nacionales y locales
     * 
     * @return Datos del indicador 16.7.1
     */
    IndicatorData getIndicador_16_7_1();
    
    /**
     * Obtiene el indicador 16.7.2
     * Proporción de la población que considera que la adopción de decisiones es inclusiva
     * 
     * @return Datos del indicador 16.7.2
     */
    IndicatorData getIndicador_16_7_2();
    
    /**
     * Obtiene el indicador 16.8.1
     * Proporción de miembros y derechos de voto de países en desarrollo
     * 
     * @return Datos del indicador 16.8.1
     */
    IndicatorData getIndicador_16_8_1();
    
    /**
     * Obtiene el indicador 16.9.1
     * Proporción de niños cuyo nacimiento se ha registrado ante autoridad civil
     * 
     * @return Datos del indicador 16.9.1
     */
    IndicatorData getIndicador_16_9_1();
    
    /**
     * Obtiene el indicador 16.10.1
     * Número de casos verificados de asesinato, secuestro, desaparición de periodistas
     * 
     * @return Datos del indicador 16.10.1
     */
    IndicatorData getIndicador_16_10_1();
    
    /**
     * Obtiene el indicador 16.10.2
     * Número de países con garantías para acceso público a la información
     * 
     * @return Datos del indicador 16.10.2
     */
    IndicatorData getIndicador_16_10_2();
    
    /**
     * Obtiene el indicador 16.a.1
     * Existencia de instituciones nacionales independientes de derechos humanos
     * 
     * @return Datos del indicador 16.a.1
     */
    IndicatorData getIndicador_16_a_1();
    
    /**
     * Obtiene el indicador 16.b.1
     * Proporción de la población que se siente discriminada o acosada
     * 
     * @return Datos del indicador 16.b.1
     */
    IndicatorData getIndicador_16_b_1();
}
