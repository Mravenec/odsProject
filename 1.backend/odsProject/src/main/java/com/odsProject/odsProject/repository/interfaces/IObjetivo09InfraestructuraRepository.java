package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods09.tables.pojos.AuditoriaOds09;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 9: Industria, Innovación e Infraestructura
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 9
 * Usa jOOQ con datasource ods09
 */
public interface IObjetivo09InfraestructuraRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds09> {
    
    /**
     * 9.1.1 Proporción de la población rural que vive a menos de 2 km de una carretera transitable todo el año [68]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.1.1
     */
    Optional<Indicadores> findIndicador_9_1_1(Integer proyectoId);
    
    /**
     * 9.1.2 Volumen de transporte de pasajeros y carga, desglosado por medio de transporte [68]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.1.2
     */
    Optional<Indicadores> findIndicador_9_1_2(Integer proyectoId);
    
    /**
     * 9.2.1 Valor añadido del sector manufacturo en proporción al PIB y per cápita [69]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.2.1
     */
    Optional<Indicadores> findIndicador_9_2_1(Integer proyectoId);
    
    /**
     * 9.2.2 Empleo del sector manufacturero en proporción al empleo total [69]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.2.2
     */
    Optional<Indicadores> findIndicador_9_2_2(Integer proyectoId);
    
    /**
     * 9.3.1 Proporción del valor añadido total del sector industrial correspondiente a las pequeñas industrias, 
     * desglosada por sexo [70]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.3.1
     */
    Optional<Indicadores> findIndicador_9_3_1(Integer proyectoId);
    
    /**
     * 9.3.2 Proporción de las pequeñas industrias que han obtenido un préstamo o una línea de crédito [70]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.3.2
     */
    Optional<Indicadores> findIndicador_9_3_2(Integer proyectoId);
    
    /**
     * 9.4.1 Emisiones de CO2 por unidad de valor añadido [71]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.4.1
     */
    Optional<Indicadores> findIndicador_9_4_1(Integer proyectoId);
    
    /**
     * 9.5.1 Gastos en investigación y desarrollo en proporción al PIB [72]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.5.1
     */
    Optional<Indicadores> findIndicador_9_5_1(Integer proyectoId);
    
    /**
     * 9.5.2 Número de investigadores (en equivalente a tiempo completo) por cada millón de habitantes [72]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.5.2
     */
    Optional<Indicadores> findIndicador_9_5_2(Integer proyectoId);
    
    /**
     * 9.a.1 Total de apoyo internacional oficial (asistencia oficial para el desarrollo más otras corrientes 
     * oficiales de recursos) destinado a la infraestructura [73]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.a.1
     */
    Optional<Indicadores> findIndicador_9_a_1(Integer proyectoId);
    
    /**
     * 9.b.1 Proporción del valor añadido por la industria de tecnología mediana y alta en el valor añadido total [74]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.b.1
     */
    Optional<Indicadores> findIndicador_9_b_1(Integer proyectoId);
    
    /**
     * 9.c.1 Proporción de la población con cobertura de red móvil, desglosada por tecnología [74, 75]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 9.c.1
     */
    Optional<Indicadores> findIndicador_9_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS09
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds09(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS09
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "9.1", "9.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
