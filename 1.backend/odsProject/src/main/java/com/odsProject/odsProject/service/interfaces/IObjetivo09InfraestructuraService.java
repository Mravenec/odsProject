package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 9: Industria, Innovación e Infraestructura
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 9
 */
public interface IObjetivo09InfraestructuraService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 9: Industria, Innovación e Infraestructura
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.1.1
     * Proporción de la población rural que vive cerca de una carretera transitable
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.1.1
     */
    Optional<Indicadores> getIndicador_9_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.1.2
     * Volumen de transporte de pasajeros y carga, desglosado por medio de transporte
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.1.2
     */
    Optional<Indicadores> getIndicador_9_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.2.1
     * Valor añadido del sector manufacturo en proporción al PIB y per cápita
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.2.1
     */
    Optional<Indicadores> getIndicador_9_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.2.2
     * Empleo del sector manufacturero en proporción al empleo total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.2.2
     */
    Optional<Indicadores> getIndicador_9_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.3.1
     * Proporción del valor añadido total correspondiente a las pequeñas industrias
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.3.1
     */
    Optional<Indicadores> getIndicador_9_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.3.2
     * Proporción de las pequeñas industrias que han obtenido un préstamo o línea de crédito
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.3.2
     */
    Optional<Indicadores> getIndicador_9_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.4.1
     * Emisiones de CO2 por unidad de valor añadido
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.4.1
     */
    Optional<Indicadores> getIndicador_9_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.5.1
     * Gastos en investigación y desarrollo en proporción al PIB
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.5.1
     */
    Optional<Indicadores> getIndicador_9_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.5.2
     * Número de investigadores por cada millón de habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.5.2
     */
    Optional<Indicadores> getIndicador_9_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.a.1
     * Total de apoyo internacional oficial destinado a la infraestructura
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.a.1
     */
    Optional<Indicadores> getIndicador_9_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.b.1
     * Proporción del valor añadido por la industria de tecnología mediana y alta
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.b.1
     */
    Optional<Indicadores> getIndicador_9_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 9.c.1
     * Proporción de la población con cobertura de red móvil
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.c.1
     */
    Optional<Indicadores> getIndicador_9_c_1(Integer proyectoId);
}
