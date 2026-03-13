package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 2: Hambre Cero
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 2
 */
public interface IObjetivo02HambreCeroService {
    
    /**
     * Obtiene todos los indicadores del Objetivo 2: Hambre Cero
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<Indicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.1.1
     * Prevalencia de la subalimentación
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.1.1
     */
    Optional<Indicadores> getIndicador_2_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.1.2
     * Prevalencia de la inseguridad alimentaria moderada o grave
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.1.2
     */
    Optional<Indicadores> getIndicador_2_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.2.1
     * Prevalencia del retraso del crecimiento en niños menores de 5 años
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.1
     */
    Optional<Indicadores> getIndicador_2_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.2.2
     * Prevalencia de la malnutrición en niños menores de 5 años
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.2
     */
    Optional<Indicadores> getIndicador_2_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.2.3
     * Prevalencia de anemia en mujeres de 15-49 años
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.3
     */
    Optional<Indicadores> getIndicador_2_2_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.2.4
     * Prevalencia del umbral mínimo de diversidad alimentaria
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.4
     */
    Optional<Indicadores> getIndicador_2_2_4(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.3.1
     * Volumen de producción por unidad de trabajo agropecuaria
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.3.1
     */
    Optional<Indicadores> getIndicador_2_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.3.2
     * Media de ingresos de productores de alimentos en pequeña escala
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.3.2
     */
    Optional<Indicadores> getIndicador_2_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.4.1
     * Proporción de superficie agrícola con agricultura sostenible
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.4.1
     */
    Optional<Indicadores> getIndicador_2_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.5.1
     * Recursos genéticos preservados para alimentación y agricultura
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.5.1
     */
    Optional<Indicadores> getIndicador_2_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.5.2
     * Razas y variedades locales en riesgo de extinción
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.5.2
     */
    Optional<Indicadores> getIndicador_2_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.a.1
     * Índice de orientación agrícola para el gasto público
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.a.1
     */
    Optional<Indicadores> getIndicador_2_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.a.2
     * Corrientes oficiales destinadas al sector agrícola
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.a.2
     */
    Optional<Indicadores> getIndicador_2_a_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.b.1
     * Subsidios a la exportación de productos agropecuarios
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.b.1
     */
    Optional<Indicadores> getIndicador_2_b_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 2.c.1
     * Indicador de anomalías en los precios de los alimentos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.c.1
     */
    Optional<Indicadores> getIndicador_2_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS02
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds02(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS02
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "2.1", "2.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
