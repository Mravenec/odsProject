package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods05.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 5: Igualdad de Género
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 5
 * Usa jOOQ con datasource ods05
 */
public interface IObjetivo05GeneroRepository extends IOdsBaseRepository<Indicadores> {
    
    /**
     * 5.1.1 Determinar si existen o no marcos jurídicos para promover, hacer cumplir y supervisar 
     * la igualdad y la no discriminación por razón de sexo [41]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.1.1
     */
    Optional<Indicadores> findIndicador_5_1_1(Integer proyectoId);
    
    /**
     * 5.2.1 Proporción de mujeres y niñas a partir de 15 años de edad que han sufrido violencia física, 
     * sexual o psicológica a manos de su actual o anterior pareja en los últimos 12 meses, 
     * desglosada por forma de violencia y edad [41, 42]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.2.1
     */
    Optional<Indicadores> findIndicador_5_2_1(Integer proyectoId);
    
    /**
     * 5.2.2 Proporción de mujeres y niñas a partir de 15 años de edad que han sufrido violencia sexual 
     * a manos de personas que no eran su pareja en los últimos 12 meses, desglosada por edad y lugar del hecho [42]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.2.2
     */
    Optional<Indicadores> findIndicador_5_2_2(Integer proyectoId);
    
    /**
     * 5.3.1 Proporción de mujeres de entre 20 y 24 años que estaban casadas o mantenían una unión estable 
     * antes de cumplir los 15 años y antes de cumplir los 18 años [42, 43]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.3.1
     */
    Optional<Indicadores> findIndicador_5_3_1(Integer proyectoId);
    
    /**
     * 5.3.2 Proporción de niñas y mujeres de entre 15 y 49 años que han sufrido mutilación genital femenina, 
     * desglosada por edad [43]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.3.2
     */
    Optional<Indicadores> findIndicador_5_3_2(Integer proyectoId);
    
    /**
     * 5.4.1 Proporción de tiempo dedicado al trabajo doméstico y asistencial no remunerado, 
     * desglosada por sexo, edad y ubicación [44]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.4.1
     */
    Optional<Indicadores> findIndicador_5_4_1(Integer proyectoId);
    
    /**
     * 5.5.1 Proporción de escaños ocupados por mujeres en a) los parlamentos nacionales y b) los gobiernos locales [44]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.5.1
     */
    Optional<Indicadores> findIndicador_5_5_1(Integer proyectoId);
    
    /**
     * 5.5.2 Proporción de mujeres en cargos directivos [44]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.5.2
     */
    Optional<Indicadores> findIndicador_5_5_2(Integer proyectoId);
    
    /**
     * 5.6.1 Proporción de mujeres de entre 15 y 49 años que toman sus propias decisiones informadas 
     * en relación con la salud sexual, el uso de anticonceptivos y su salud reproductiva [45]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.6.1
     */
    Optional<Indicadores> findIndicador_5_6_1(Integer proyectoId);
    
    /**
     * 5.6.2 Número de países con leyes y reglamentos que garantizan a los hombres y las mujeres 
     * igual acceso a los servicios de salud sexual y reproductiva [45]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.6.2
     */
    Optional<Indicadores> findIndicador_5_6_2(Integer proyectoId);
    
    /**
     * 5.a.1 a) Proporción del total de la población agrícola con derechos de propiedad o derechos seguros 
     * sobre tierras agrícolas, desglosada por sexo; y b) proporción de mujeres entre los propietarios 
     * o los titulares de derechos sobre tierras agrícolas, desglosada por tipo de tenencia [46]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.a.1
     */
    Optional<Indicadores> findIndicador_5_a_1(Integer proyectoId);
    
    /**
     * 5.a.2 Proporción de países cuyo ordenamiento jurídico (incluido el derecho consuetudinario) 
     * garantiza la igualdad de derechos de la mujer a la propiedad o el control de las tierras [47]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.a.2
     */
    Optional<Indicadores> findIndicador_5_a_2(Integer proyectoId);
    
    /**
     * 5.b.1 Proporción de personas que poseen un teléfono móvil, desglosada por sexo [48]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.b.1
     */
    Optional<Indicadores> findIndicador_5_b_1(Integer proyectoId);
    
    /**
     * 5.c.1 Proporción de países con sistemas para el seguimiento de la igualdad de género 
     * y el empoderamiento de las mujeres y la asignación de fondos públicos para ese fin [48]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 5.c.1
     */
    Optional<Indicadores> findIndicador_5_c_1(Integer proyectoId);
    
    // ── Consultas agregadas propias ODS05 ──
    List<Indicadores> findAllIndicadoresByProyectoOds05(Integer proyectoId);
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
