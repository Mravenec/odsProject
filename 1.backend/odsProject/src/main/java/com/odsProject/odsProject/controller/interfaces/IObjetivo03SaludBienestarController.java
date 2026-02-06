package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import com.odsProject.odsProject.model.IndicatorData;
import java.util.List;

/**
 * Interfaz de Controlador para el Objetivo 3: Salud y Bienestar
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 3
 */
public interface IObjetivo03SaludBienestarController {
    
    /**
     * Obtiene todos los indicadores del Objetivo 3: Salud y Bienestar
     * 
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<IndicatorData>> getAllIndicators();
    
    /**
     * Obtiene el indicador 3.1.1
     * Tasa de mortalidad materna
     * 
     * @return ResponseEntity con los datos del indicador 3.1.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_1_1();
    
    /**
     * Obtiene el indicador 3.1.2
     * Proporción de partos atendidos por personal especializado
     * 
     * @return ResponseEntity con los datos del indicador 3.1.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_1_2();
    
    /**
     * Obtiene el indicador 3.2.1
     * Tasa de mortalidad de niños menores de 5 años
     * 
     * @return ResponseEntity con los datos del indicador 3.2.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_2_1();
    
    /**
     * Obtiene el indicador 3.2.2
     * Tasa de mortalidad neonatal
     * 
     * @return ResponseEntity con los datos del indicador 3.2.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_2_2();
    
    /**
     * Obtiene el indicador 3.3.1
     * Nuevas infecciones por VIH por cada 1.000 habitantes
     * 
     * @return ResponseEntity con los datos del indicador 3.3.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_3_1();
    
    /**
     * Obtiene el indicador 3.3.2
     * Incidencia de tuberculosis por cada 100.000 habitantes
     * 
     * @return ResponseEntity con los datos del indicador 3.3.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_3_2();
    
    /**
     * Obtiene el indicador 3.3.3
     * Incidencia de malaria por cada 1.000 habitantes
     * 
     * @return ResponseEntity con los datos del indicador 3.3.3
     */
    ResponseEntity<IndicatorData> getIndicador_3_3_3();
    
    /**
     * Obtiene el indicador 3.3.4
     * Incidencia de hepatitis B por cada 100.000 habitantes
     * 
     * @return ResponseEntity con los datos del indicador 3.3.4
     */
    ResponseEntity<IndicatorData> getIndicador_3_3_4();
    
    /**
     * Obtiene el indicador 3.3.5
     * Personas que requieren intervenciones contra enfermedades tropicales
     * 
     * @return ResponseEntity con los datos del indicador 3.3.5
     */
    ResponseEntity<IndicatorData> getIndicador_3_3_5();
    
    /**
     * Obtiene el indicador 3.4.1
     * Tasa de mortalidad por enfermedades no transmisibles
     * 
     * @return ResponseEntity con los datos del indicador 3.4.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_4_1();
    
    /**
     * Obtiene el indicador 3.4.2
     * Tasa de mortalidad por suicidio
     * 
     * @return ResponseEntity con los datos del indicador 3.4.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_4_2();
    
    /**
     * Obtiene el indicador 3.5.1
     * Cobertura de tratamientos para trastornos por abuso de sustancias
     * 
     * @return ResponseEntity con los datos del indicador 3.5.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_5_1();
    
    /**
     * Obtiene el indicador 3.5.2
     * Consumo de alcohol per cápita
     * 
     * @return ResponseEntity con los datos del indicador 3.5.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_5_2();
    
    /**
     * Obtiene el indicador 3.6.1
     * Tasa de mortalidad por accidentes de tráfico
     * 
     * @return ResponseEntity con los datos del indicador 3.6.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_6_1();
    
    /**
     * Obtiene el indicador 3.7.1
     * Mujeres que cubren necesidades de planificación familiar
     * 
     * @return ResponseEntity con los datos del indicador 3.7.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_7_1();
    
    /**
     * Obtiene el indicador 3.7.2
     * Tasa de fecundidad de adolescentes
     * 
     * @return ResponseEntity con los datos del indicador 3.7.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_7_2();
    
    /**
     * Obtiene el indicador 3.8.1
     * Cobertura de servicios de salud esenciales
     * 
     * @return ResponseEntity con los datos del indicador 3.8.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_8_1();
    
    /**
     * Obtiene el indicador 3.8.2
     * Proporción con grandes gastos sanitarios por hogar
     * 
     * @return ResponseEntity con los datos del indicador 3.8.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_8_2();
    
    /**
     * Obtiene el indicador 3.9.1
     * Tasa de mortalidad por contaminación del aire
     * 
     * @return ResponseEntity con los datos del indicador 3.9.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_9_1();
    
    /**
     * Obtiene el indicador 3.9.2
     * Tasa de mortalidad por agua insalubre y saneamiento deficiente
     * 
     * @return ResponseEntity con los datos del indicador 3.9.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_9_2();
    
    /**
     * Obtiene el indicador 3.9.3
     * Tasa de mortalidad por intoxicaciones involuntarias
     * 
     * @return ResponseEntity con los datos del indicador 3.9.3
     */
    ResponseEntity<IndicatorData> getIndicador_3_9_3();
    
    /**
     * Obtiene el indicador 3.a.1
     * Prevalencia del consumo de tabaco
     * 
     * @return ResponseEntity con los datos del indicador 3.a.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_a_1();
    
    /**
     * Obtiene el indicador 3.b.1
     * Proporción de población inmunizada con todas las vacunas
     * 
     * @return ResponseEntity con los datos del indicador 3.b.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_b_1();
    
    /**
     * Obtiene el indicador 3.b.2
     * AOD destinada a investigación médica y atención sanitaria básica
     * 
     * @return ResponseEntity con los datos del indicador 3.b.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_b_2();
    
    /**
     * Obtiene el indicador 3.b.3
     * Índice de acceso a productos sanitarios
     * 
     * @return ResponseEntity con los datos del indicador 3.b.3
     */
    ResponseEntity<IndicatorData> getIndicador_3_b_3();
    
    /**
     * Obtiene el indicador 3.c.1
     * Densidad y distribución del personal sanitario
     * 
     * @return ResponseEntity con los datos del indicador 3.c.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_c_1();
    
    /**
     * Obtiene el indicador 3.d.1
     * Capacidad del Reglamento Sanitario Internacional
     * 
     * @return ResponseEntity con los datos del indicador 3.d.1
     */
    ResponseEntity<IndicatorData> getIndicador_3_d_1();
    
    /**
     * Obtiene el indicador 3.d.2
     * Porcentaje de infecciones resistentes a antimicrobianos
     * 
     * @return ResponseEntity con los datos del indicador 3.d.2
     */
    ResponseEntity<IndicatorData> getIndicador_3_d_2();
}
