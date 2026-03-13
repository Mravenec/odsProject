package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.Indicadores;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 1: Fin de la Pobreza
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 1
 * Usa jOOQ con datasource ods01
 */
public interface IObjetivo01PobrezaRepository extends IOdsBaseRepository<Indicadores> {
    
    /**
     * 1.1.1 Proporción de la población que vive por debajo del umbral internacional de pobreza, 
     * desglosada por sexo, edad, situación laboral y ubicación geográfica (urbana o rural) [1, 2]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.1.1
     */
    Optional<Indicadores> findIndicador_1_1_1(Integer proyectoId);
    
    /**
     * 1.2.1 Proporción de la población que vive por debajo del umbral nacional de pobreza, 
     * desglosada por sexo y edad [2]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.2.1
     */
    Optional<Indicadores> findIndicador_1_2_1(Integer proyectoId);
    
    /**
     * 1.2.2 Proporción de hombres, mujeres y niños de todas las edades que viven en la pobreza, 
     * en todas sus dimensiones, con arreglo a las definiciones nacionales [2]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.2.2
     */
    Optional<Indicadores> findIndicador_1_2_2(Integer proyectoId);
    
    /**
     * 1.3.1 Proporción de la población cubierta por sistemas o niveles mínimos de protección social, 
     * desglosada por sexo, distinguiendo entre los niños, los desempleados, los ancianos, 
     * las personas con discapacidad, las mujeres embarazadas, los recién nacidos, 
     * las víctimas de accidentes de trabajo, los pobres y los vulnerables [3]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.3.1
     */
    Optional<Indicadores> findIndicador_1_3_1(Integer proyectoId);
    
    /**
     * 1.4.1 Proporción de la población que vive en hogares con acceso a los servicios básicos [4]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.4.1
     */
    Optional<Indicadores> findIndicador_1_4_1(Integer proyectoId);
    
    /**
     * 1.4.2 Proporción del total de la población adulta con derechos seguros de tenencia de la tierra: 
     * a) que posee documentación reconocida legalmente al respecto y b) considera seguros sus derechos, 
     * desglosada por sexo y tipo de tenencia [4, 5]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.4.2
     */
    Optional<Indicadores> findIndicador_1_4_2(Integer proyectoId);
    
    /**
     * 1.5.1 Número de personas muertas, desaparecidas y afectadas directamente atribuido a desastres 
     * por cada 100.000 habitantes [5, 6]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.5.1
     */
    Optional<Indicadores> findIndicador_1_5_1(Integer proyectoId);
    
    /**
     * 1.5.2 Pérdidas económicas directas atribuidas a los desastres en relación con 
     * el producto interno bruto (PIB) mundial [6]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.5.2
     */
    Optional<Indicadores> findIndicador_1_5_2(Integer proyectoId);
    
    /**
     * 1.5.3 Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo 
     * de desastres en consonancia con el Marco de Sendái para la Reducción del Riesgo de Desastres 2015-2030 [6]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.5.3
     */
    Optional<Indicadores> findIndicador_1_5_3(Integer proyectoId);
    
    /**
     * 1.5.4 Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción 
     * del riesgo de desastres en consonancia con las estrategias nacionales de reducción del riesgo de desastres [7]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.5.4
     */
    Optional<Indicadores> findIndicador_1_5_4(Integer proyectoId);
    
    /**
     * 1.a.1 Total de subvenciones de asistencia oficial para el desarrollo destinadas a la reducción 
     * de la pobreza en porcentaje de la renta nacional bruta del país beneficiario [8]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.a.1
     */
    Optional<Indicadores> findIndicador_1_a_1(Integer proyectoId);
    
    /**
     * 1.a.2 Proporción del gasto público total que se dedica a servicios esenciales 
     * (educación, salud y protección social) [8]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.a.2
     */
    Optional<Indicadores> findIndicador_1_a_2(Integer proyectoId);
    
    /**
     * 1.b.1 Gasto público social en favor de los pobres [9]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 1.b.1
     */
    Optional<Indicadores> findIndicador_1_b_1(Integer proyectoId);
    
    // ── Consultas agregadas propias ODS01 ──
    List<Indicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId);
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
