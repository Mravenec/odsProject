package com.odsProject.odsProject.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.MedicionesHistoricas;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Controlador para el Objetivo 3: Salud y Bienestar
 * Define los endpoints REST para los indicadores del Objetivo de Desarrollo Sostenible 3
 * Extiende IOdsBaseController con tipos específicos de ODS03
 */
@RequestMapping("/api/ods/03")
public interface IObjetivo03SaludBienestarController extends IOdsBaseController<
    Indicadores,     // T - Indicadores
    Proyectos,       // P - Proyectos
    MetasProyecto,   // M - MetasProyecto
    MedicionesHistoricas, // MH - MedicionesHistoricas
    Object           // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 3: Salud y Bienestar
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores
     */
    ResponseEntity<List<Indicadores>> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.1.1
     * Tasa de mortalidad materna
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.1.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.1.2
     * Proporción de partos atendidos por personal especializado
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.1.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.2.1
     * Tasa de mortalidad de niños menores de 5 años
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.2.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.2.2
     * Tasa de mortalidad neonatal
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.2.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.1
     * Nuevas infecciones por VIH por cada 1.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.3.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.2
     * Incidencia de tuberculosis por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.3.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.3
     * Incidencia de malaria por cada 1.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.3.3
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_3_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.4
     * Incidencia de hepatitis B por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.3.4
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_3_4(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.3.5
     * Personas que requieren intervenciones contra enfermedades tropicales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.3.5
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_3_5(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.4.1
     * Tasa de mortalidad por enfermedades no transmisibles
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.4.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.4.2
     * Tasa de mortalidad por suicidio
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.4.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.5.1
     * Cobertura de tratamientos para trastornos por abuso de sustancias
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.5.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.5.2
     * Consumo de alcohol per cápita
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.5.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.6.1
     * Tasa de mortalidad por accidentes de tráfico
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.6.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.7.1
     * Mujeres que cubren necesidades de planificación familiar
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.7.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.7.2
     * Tasa de fecundidad de adolescentes
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.7.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_7_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.8.1
     * Cobertura de servicios de salud esenciales
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.8.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.8.2
     * Proporción con grandes gastos sanitarios por hogar
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.8.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_8_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.9.1
     * Tasa de mortalidad por contaminación del aire
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.9.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.9.2
     * Tasa de mortalidad por agua insalubre y saneamiento deficiente
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.9.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_9_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.9.3
     * Tasa de mortalidad por intoxicaciones involuntarias
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.9.3
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_9_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.a.1
     * Prevalencia del consumo de tabaco
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.a.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.b.1
     * Proporción de población inmunizada con todas las vacunas
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.b.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.b.2
     * AOD destinada a investigación médica y atención sanitaria básica
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.b.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_b_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.b.3
     * Índice de acceso a productos sanitarios
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.b.3
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_b_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.c.1
     * Densidad y distribución del personal sanitario
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.c.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_c_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.d.1
     * Capacidad del Reglamento Sanitario Internacional
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.d.1
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_d_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 3.d.2
     * Porcentaje de infecciones resistentes a antimicrobianos
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con los datos del indicador 3.d.2
     */
    ResponseEntity<Optional<Indicadores>> getIndicador_3_d_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @return ResponseEntity con la lista de todos los indicadores del proyecto
     */
    ResponseEntity<List<Indicadores>> findAllIndicadoresByProyectoOds03(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "3.1", "3.2")
     * @return ResponseEntity con la lista de indicadores que pertenecen a la meta especificada
     */
    ResponseEntity<List<Indicadores>> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
