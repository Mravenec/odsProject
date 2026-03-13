package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods08.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 8: Trabajo Decente y Crecimiento Económico
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 8
 */
public interface IObjetivo08CrecimientoEconomicoService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 8: Trabajo Decente y Crecimiento Económico
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.1.1
     * Tasa de crecimiento anual del PIB real per cápita
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.1.1
     */
    Optional<Indicadores> getIndicador_8_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.2.1
     * Tasa de crecimiento anual del PIB real por persona empleada
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.2.1
     */
    Optional<Indicadores> getIndicador_8_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.3.1
     * Proporción de empleo informal con respecto al empleo total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.3.1
     */
    Optional<Indicadores> getIndicador_8_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.4.1
     * Huella material en términos absolutos, per cápita y por PIB
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.4.1
     */
    Optional<Indicadores> getIndicador_8_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.4.2
     * Consumo material interno en términos absolutos, per cápita y por PIB
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.4.2
     */
    Optional<Indicadores> getIndicador_8_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.5.1
     * Ingreso medio por hora de las personas empleadas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.5.1
     */
    Optional<Indicadores> getIndicador_8_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.5.2
     * Tasa de desempleo, desglosada por sexo, edad y personas con discapacidad
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.5.2
     */
    Optional<Indicadores> getIndicador_8_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.6.1
     * Proporción de jóvenes que no estudian ni trabajan
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.6.1
     */
    Optional<Indicadores> getIndicador_8_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.7.1
     * Proporción de niños que realizan trabajo infantil
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.7.1
     */
    Optional<Indicadores> getIndicador_8_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.8.1
     * Lesiones ocupacionales mortales y no mortales por cada 100.000 trabajadores
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.8.1
     */
    Optional<Indicadores> getIndicador_8_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.8.2
     * Nivel de cumplimiento nacional de los derechos laborales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.8.2
     */
    Optional<Indicadores> getIndicador_8_8_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.9.1
     * PIB generado directamente por el turismo en proporción al PIB total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.9.1
     */
    Optional<Indicadores> getIndicador_8_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.9.2
     * Personas empleadas en el sector del turismo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.9.2
     */
    Optional<Indicadores> getIndicador_8_9_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.10.1
     * Número de sucursales de bancos y cajeros automáticos por cada 100.000 adultos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.10.1
     */
    Optional<Indicadores> getIndicador_8_10_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.10.2
     * Proporción de adultos que tienen una cuenta en banco
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.10.2
     */
    Optional<Indicadores> getIndicador_8_10_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.a.1
     * Compromisos y desembolsos en relación con la iniciativa Ayuda para el Comercio
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.a.1
     */
    Optional<Indicadores> getIndicador_8_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 8.b.1
     * Existencia de una estrategia nacional para el empleo de los jóvenes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 8.b.1
     */
    Optional<Indicadores> getIndicador_8_b_1(Integer proyectoId);
}
