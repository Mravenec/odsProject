package com.projectods.service.interfaces;

import com.projectods.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Servicio para el Objetivo 3: Salud y Bienestar
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 3
 */
public interface IObjetivo03SaludBienestarService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 3: Salud y Bienestar
     * 
     * @return Lista con todos los indicadores del objetivo
     */
    List<IndicatorData> getAllIndicators();
    
    /**
     * Obtiene el indicador 3.1.1
     * Tasa de mortalidad materna
     * 
     * @return Datos del indicador 3.1.1
     */
    IndicatorData getIndicador_3_1_1();
    
    /**
     * Obtiene el indicador 3.1.2
     * Proporción de partos atendidos por personal especializado
     * 
     * @return Datos del indicador 3.1.2
     */
    IndicatorData getIndicador_3_1_2();
    
    /**
     * Obtiene el indicador 3.2.1
     * Tasa de mortalidad de niños menores de 5 años
     * 
     * @return Datos del indicador 3.2.1
     */
    IndicatorData getIndicador_3_2_1();
    
    /**
     * Obtiene el indicador 3.2.2
     * Tasa de mortalidad neonatal
     * 
     * @return Datos del indicador 3.2.2
     */
    IndicatorData getIndicador_3_2_2();
    
    /**
     * Obtiene el indicador 3.3.1
     * Nuevas infecciones por VIH por cada 1.000 habitantes
     * 
     * @return Datos del indicador 3.3.1
     */
    IndicatorData getIndicador_3_3_1();
    
    /**
     * Obtiene el indicador 3.3.2
     * Incidencia de tuberculosis por cada 100.000 habitantes
     * 
     * @return Datos del indicador 3.3.2
     */
    IndicatorData getIndicador_3_3_2();
    
    /**
     * Obtiene el indicador 3.3.3
     * Incidencia de malaria por cada 1.000 habitantes
     * 
     * @return Datos del indicador 3.3.3
     */
    IndicatorData getIndicador_3_3_3();
    
    /**
     * Obtiene el indicador 3.3.4
     * Incidencia de hepatitis B por cada 100.000 habitantes
     * 
     * @return Datos del indicador 3.3.4
     */
    IndicatorData getIndicador_3_3_4();
    
    /**
     * Obtiene el indicador 3.3.5
     * Personas que requieren intervenciones contra enfermedades tropicales
     * 
     * @return Datos del indicador 3.3.5
     */
    IndicatorData getIndicador_3_3_5();
    
    /**
     * Obtiene el indicador 3.4.1
     * Tasa de mortalidad por enfermedades no transmisibles
     * 
     * @return Datos del indicador 3.4.1
     */
    IndicatorData getIndicador_3_4_1();
    
    /**
     * Obtiene el indicador 3.4.2
     * Tasa de mortalidad por suicidio
     * 
     * @return Datos del indicador 3.4.2
     */
    IndicatorData getIndicador_3_4_2();
    
    /**
     * Obtiene el indicador 3.5.1
     * Cobertura de tratamientos para trastornos por abuso de sustancias
     * 
     * @return Datos del indicador 3.5.1
     */
    IndicatorData getIndicador_3_5_1();
    
    /**
     * Obtiene el indicador 3.5.2
     * Consumo de alcohol per cápita
     * 
     * @return Datos del indicador 3.5.2
     */
    IndicatorData getIndicador_3_5_2();
    
    /**
     * Obtiene el indicador 3.6.1
     * Tasa de mortalidad por accidentes de tráfico
     * 
     * @return Datos del indicador 3.6.1
     */
    IndicatorData getIndicador_3_6_1();
    
    /**
     * Obtiene el indicador 3.7.1
     * Mujeres que cubren necesidades de planificación familiar
     * 
     * @return Datos del indicador 3.7.1
     */
    IndicatorData getIndicador_3_7_1();
    
    /**
     * Obtiene el indicador 3.7.2
     * Tasa de fecundidad de adolescentes
     * 
     * @return Datos del indicador 3.7.2
     */
    IndicatorData getIndicador_3_7_2();
    
    /**
     * Obtiene el indicador 3.8.1
     * Cobertura de servicios de salud esenciales
     * 
     * @return Datos del indicador 3.8.1
     */
    IndicatorData getIndicador_3_8_1();
    
    /**
     * Obtiene el indicador 3.8.2
     * Proporción con grandes gastos sanitarios por hogar
     * 
     * @return Datos del indicador 3.8.2
     */
    IndicatorData getIndicador_3_8_2();
    
    /**
     * Obtiene el indicador 3.9.1
     * Tasa de mortalidad por contaminación del aire
     * 
     * @return Datos del indicador 3.9.1
     */
    IndicatorData getIndicador_3_9_1();
    
    /**
     * Obtiene el indicador 3.9.2
     * Tasa de mortalidad por agua insalubre y saneamiento deficiente
     * 
     * @return Datos del indicador 3.9.2
     */
    IndicatorData getIndicador_3_9_2();
    
    /**
     * Obtiene el indicador 3.9.3
     * Tasa de mortalidad por intoxicaciones involuntarias
     * 
     * @return Datos del indicador 3.9.3
     */
    IndicatorData getIndicador_3_9_3();
    
    /**
     * Obtiene el indicador 3.a.1
     * Prevalencia del consumo de tabaco
     * 
     * @return Datos del indicador 3.a.1
     */
    IndicatorData getIndicador_3_a_1();
    
    /**
     * Obtiene el indicador 3.b.1
     * Proporción de población inmunizada con todas las vacunas
     * 
     * @return Datos del indicador 3.b.1
     */
    IndicatorData getIndicador_3_b_1();
    
    /**
     * Obtiene el indicador 3.b.2
     * AOD destinada a investigación médica y atención sanitaria básica
     * 
     * @return Datos del indicador 3.b.2
     */
    IndicatorData getIndicador_3_b_2();
    
    /**
     * Obtiene el indicador 3.b.3
     * Índice de acceso a productos sanitarios
     * 
     * @return Datos del indicador 3.b.3
     */
    IndicatorData getIndicador_3_b_3();
    
    /**
     * Obtiene el indicador 3.c.1
     * Densidad y distribución del personal sanitario
     * 
     * @return Datos del indicador 3.c.1
     */
    IndicatorData getIndicador_3_c_1();
    
    /**
     * Obtiene el indicador 3.d.1
     * Capacidad del Reglamento Sanitario Internacional
     * 
     * @return Datos del indicador 3.d.1
     */
    IndicatorData getIndicador_3_d_1();
    
    /**
     * Obtiene el indicador 3.d.2
     * Porcentaje de infecciones resistentes a antimicrobianos
     * 
     * @return Datos del indicador 3.d.2
     */
    IndicatorData getIndicador_3_d_2();
}
