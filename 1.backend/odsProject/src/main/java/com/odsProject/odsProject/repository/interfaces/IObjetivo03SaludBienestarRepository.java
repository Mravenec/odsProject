package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods03.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 3: Salud y Bienestar
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 3
 * Usa jOOQ con datasource ods03
 */
public interface IObjetivo03SaludBienestarRepository extends IOdsBaseRepository<Indicadores> {
    
    /**
     * 3.1.1 Tasa de mortalidad materna [19]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.1.1
     */
    Optional<Indicadores> findIndicador_3_1_1(Integer proyectoId);
    
    /**
     * 3.1.2 Proporción de partos atendidos por personal sanitario especializado [19]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.1.2
     */
    Optional<Indicadores> findIndicador_3_1_2(Integer proyectoId);
    
    /**
     * 3.2.1 Tasa de mortalidad de niños menores de 5 años [19]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.2.1
     */
    Optional<Indicadores> findIndicador_3_2_1(Integer proyectoId);
    
    /**
     * 3.2.2 Tasa de mortalidad neonatal [20]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.2.2
     */
    Optional<Indicadores> findIndicador_3_2_2(Integer proyectoId);
    
    /**
     * 3.3.1 Número de nuevas infecciones por el VIH por cada 1.000 habitantes no infectados, 
     * desglosado por sexo, edad y poblaciones clave [21]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.1
     */
    Optional<Indicadores> findIndicador_3_3_1(Integer proyectoId);
    
    /**
     * 3.3.2 Incidencia de la tuberculosis por cada 100.000 habitantes [21]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.2
     */
    Optional<Indicadores> findIndicador_3_3_2(Integer proyectoId);
    
    /**
     * 3.3.3 Incidencia de la malaria por cada 1.000 habitantes [21]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.3
     */
    Optional<Indicadores> findIndicador_3_3_3(Integer proyectoId);
    
    /**
     * 3.3.4 Incidencia de la hepatitis B por cada 100.000 habitantes [21]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.4
     */
    Optional<Indicadores> findIndicador_3_3_4(Integer proyectoId);
    
    /**
     * 3.3.5 Número de personas que requieren intervenciones contra enfermedades tropicales desatendidas [21]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.3.5
     */
    Optional<Indicadores> findIndicador_3_3_5(Integer proyectoId);
    
    /**
     * 3.4.1 Tasa de mortalidad atribuida a las enfermedades cardiovasculares, el cáncer, 
     * las enfermedades respiratorias crónicas, la diabetes y las enfermedades mentales [22]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.4.1
     */
    Optional<Indicadores> findIndicador_3_4_1(Integer proyectoId);
    
    /**
     * 3.4.2 Tasa de mortalidad por suicidio [22]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.4.2
     */
    Optional<Indicadores> findIndicador_3_4_2(Integer proyectoId);
    
    /**
     * 3.5.1 Cobertura de los tratamientos (farmacológicos y psicosociales y servicios de rehabilitación 
     * para los trastornos por consumo de sustancias) [23]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.5.1
     */
    Optional<Indicadores> findIndicador_3_5_1(Integer proyectoId);
    
    /**
     * 3.5.2 Consumo de alcohol per cápita (a partir de los 15 años de edad) durante un año civil 
     * en litros de alcohol puro [23]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.5.2
     */
    Optional<Indicadores> findIndicador_3_5_2(Integer proyectoId);
    
    /**
     * 3.6.1 Tasa de mortalidad por lesiones debidas a accidentes de tráfico [23]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.6.1
     */
    Optional<Indicadores> findIndicador_3_6_1(Integer proyectoId);
    
    /**
     * 3.7.1 Proporción de mujeres en edad de procrear (entre 15 y 49 años) que cubren sus necesidades 
     * de planificación familiar con métodos modernos [24]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.7.1
     */
    Optional<Indicadores> findIndicador_3_7_1(Integer proyectoId);
    
    /**
     * 3.7.2 Tasa de fecundidad de las adolescentes (entre 10 y 14 años y entre 15 y 19 años) 
     * por cada 1.000 mujeres de ese grupo de edad [24]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.7.2
     */
    Optional<Indicadores> findIndicador_3_7_2(Integer proyectoId);
    
    /**
     * 3.8.1 Cobertura de los servicios de salud esenciales [25]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.8.1
     */
    Optional<Indicadores> findIndicador_3_8_1(Integer proyectoId);
    
    /**
     * 3.8.2 Proporción de la población con grandes gastos sanitarios por hogar como porcentaje 
     * del total de gastos o ingresos de los hogares [25]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.8.2
     */
    Optional<Indicadores> findIndicador_3_8_2(Integer proyectoId);
    
    /**
     * 3.9.1 Tasa de mortalidad atribuida a la contaminación de los hogares y del aire ambiente [25, 26]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.9.1
     */
    Optional<Indicadores> findIndicador_3_9_1(Integer proyectoId);
    
    /**
     * 3.9.2 Tasa de mortalidad atribuida al agua insalubre, el saneamiento deficiente 
     * y la falta de higiene (exposición a servicios insalubres de agua, saneamiento e higiene para todos (WASH)) [26]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.9.2
     */
    Optional<Indicadores> findIndicador_3_9_2(Integer proyectoId);
    
    /**
     * 3.9.3 Tasa de mortalidad atribuida a intoxicaciones involuntarias [26]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.9.3
     */
    Optional<Indicadores> findIndicador_3_9_3(Integer proyectoId);
    
    /**
     * 3.a.1 Prevalencia del consumo actual de tabaco a partir de los 15 años de edad (edades ajustadas) [27]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.a.1
     */
    Optional<Indicadores> findIndicador_3_a_1(Integer proyectoId);
    
    /**
     * 3.b.1 Proporción de la población inmunizada con todas las vacunas incluidas en cada programa nacional [28]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.b.1
     */
    Optional<Indicadores> findIndicador_3_b_1(Integer proyectoId);
    
    /**
     * 3.b.2 Total neto de asistencia oficial para el desarrollo destinado a los sectores 
     * de la investigación médica y la atención sanitaria básica [29]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.b.2
     */
    Optional<Indicadores> findIndicador_3_b_2(Integer proyectoId);
    
    /**
     * 3.b.3 Índice de acceso a los productos sanitarios [29]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.b.3
     */
    Optional<Indicadores> findIndicador_3_b_3(Integer proyectoId);
    
    /**
     * 3.c.1 Densidad y distribución del personal sanitario [29]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.c.1
     */
    Optional<Indicadores> findIndicador_3_c_1(Integer proyectoId);
    
    /**
     * 3.d.1 Capacidad prevista en el Reglamento Sanitario Internacional (RSI) 
     * y preparación para emergencias de salud [30]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.d.1
     */
    Optional<Indicadores> findIndicador_3_d_1(Integer proyectoId);
    
    /**
     * 3.d.2 Porcentaje de infecciones del torrente sanguíneo debidas a determinados organismos 
     * resistentes a los antimicrobianos seleccionados [30]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 3.d.2
     */
    Optional<Indicadores> findIndicador_3_d_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds03(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS03
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "3.1", "3.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
