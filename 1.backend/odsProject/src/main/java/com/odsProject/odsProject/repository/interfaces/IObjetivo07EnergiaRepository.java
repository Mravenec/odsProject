package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods07.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 7: Energía Asequible y No Contaminante
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 7
 * Usa jOOQ con datasource ods07
 */
public interface IObjetivo07EnergiaRepository extends IOdsBaseRepository<Indicadores> {
    
    /**
     * 7.1.1 Proporción de la población que tiene acceso a la electricidad [55]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.1.1
     */
    Optional<Indicadores> findIndicador_7_1_1(Integer proyectoId);
    
    /**
     * 7.1.2 Proporción de la población cuya fuente primaria de energía son los combustibles y tecnologías limpios [55]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.1.2
     */
    Optional<Indicadores> findIndicador_7_1_2(Integer proyectoId);
    
    /**
     * 7.2.1 Proporción de energía renovable en el consumo final total de energía [56]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.2.1
     */
    Optional<Indicadores> findIndicador_7_2_1(Integer proyectoId);
    
    /**
     * 7.3.1 Intensidad energética medida en función de la energía primaria y el PIB [56]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.3.1
     */
    Optional<Indicadores> findIndicador_7_3_1(Integer proyectoId);
    
    /**
     * 7.a.1 Corrientes financieras internacionales hacia los países en desarrollo para apoyar la investigación 
     * y el desarrollo en el sector de la energía limpia [57]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.a.1
     */
    Optional<Indicadores> findIndicador_7_a_1(Integer proyectoId);
    
    /**
     * 7.b.1 Capacidad instalada de generación de energía renovable en los países en desarrollo 
     * en vatios por habitante [57]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 7.b.1
     */
    Optional<Indicadores> findIndicador_7_b_1(Integer proyectoId);
    
    // ── Consultas agregadas propias ODS07 ──
    List<Indicadores> findAllIndicadoresByProyectoOds07(Integer proyectoId);
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
