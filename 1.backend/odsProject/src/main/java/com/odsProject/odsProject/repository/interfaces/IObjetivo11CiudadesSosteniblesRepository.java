package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods11.tables.pojos.AuditoriaOds11;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 11: Ciudades y Comunidades Sostenibles
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 11
 * Usa jOOQ con datasource ods11
 */
public interface IObjetivo11CiudadesSosteniblesRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds11> {
    
    /**
     * 11.1.1 Proporción de la población urbana que vive en barrios marginales, 
     * asentamientos informales o viviendas inadecuadas [83]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.1.1
     */
    Optional<Indicadores> findIndicador_11_1_1(Integer proyectoId);
    
    /**
     * 11.2.1 Proporción de la población que tiene fácil acceso al transporte público, 
     * desglosada por sexo, edad y personas con discapacidad [83]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.2.1
     */
    Optional<Indicadores> findIndicador_11_2_1(Integer proyectoId);
    
    /**
     * 11.3.1 Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población [84]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.3.1
     */
    Optional<Indicadores> findIndicador_11_3_1(Integer proyectoId);
    
    /**
     * 11.3.2 Proporción de ciudades que cuentan con una estructura de participación directa 
     * de la sociedad civil en la planificación y gestión urbana [84]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.3.2
     */
    Optional<Indicadores> findIndicador_11_3_2(Integer proyectoId);
    
    /**
     * 11.4.1 Total de gastos per cápita destinados a la preservación, protección y conservación 
     * de todo el patrimonio cultural y natural, desglosado por tipo de patrimonio (cultural, natural, mixto y otros) [85]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.4.1
     */
    Optional<Indicadores> findIndicador_11_4_1(Integer proyectoId);
    
    /**
     * 11.5.1 Número de personas muertas, desaparecidas y afectadas directamente atribuido 
     * a desastres por cada 100.000 personas [86]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.5.1
     */
    Optional<Indicadores> findIndicador_11_5_1(Integer proyectoId);
    
    /**
     * 11.5.2 Pérdidas económicas directas atribuidas a los desastres en relación con 
     * el producto interno bruto mundial (PIB) [86]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.5.2
     */
    Optional<Indicadores> findIndicador_11_5_2(Integer proyectoId);
    
    /**
     * 11.5.3 a) Daños en la infraestructura crítica y b) número de interrupciones de los servicios básicos, 
     * atribuidos a desastres [86]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.5.3
     */
    Optional<Indicadores> findIndicador_11_5_3(Integer proyectoId);
    
    /**
     * 11.6.1 Proporción de residuos sólidos municipales recogidos y administrados en instalaciones controladas 
     * con respecto al total de residuos municipales generados, desglosada por ciudad [88]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.6.1
     */
    Optional<Indicadores> findIndicador_11_6_1(Integer proyectoId);
    
    /**
     * 11.6.2 Niveles medios anuales de partículas finas en suspensión (por ejemplo, PM2.5 y PM10) 
     * en las ciudades (ponderados según la población) [88, 89]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.6.2
     */
    Optional<Indicadores> findIndicador_11_6_2(Integer proyectoId);
    
    /**
     * 11.7.1 Proporción media de la superficie edificada de las ciudades que se dedica a espacios abiertos 
     * para uso público de todos, desglosada por sexo, edad y personas con discapacidad [89]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.7.1
     */
    Optional<Indicadores> findIndicador_11_7_1(Integer proyectoId);
    
    /**
     * 11.7.2 Proporción de personas que han sido víctimas de acoso no sexual o sexual en los últimos 12 meses, 
     * desglosada por sexo, edad, grado de discapacidad y lugar del hecho [89, 90]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.7.2
     */
    Optional<Indicadores> findIndicador_11_7_2(Integer proyectoId);
    
    /**
     * 11.a.1 Número de países que cuentan con políticas urbanas nacionales o planes de desarrollo regionales 
     * que a) responden a la dinámica de la población, b) garantizan un desarrollo territorial equilibrado 
     * y c) aumentan el margen fiscal local [90, 91]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.a.1
     */
    Optional<Indicadores> findIndicador_11_a_1(Integer proyectoId);
    
    /**
     * 11.b.1 Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo de desastres 
     * en consonancia con el Marco de Sendái para la Reducción del Riesgo de Desastres 2015-2030 [92]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.b.1
     */
    Optional<Indicadores> findIndicador_11_b_1(Integer proyectoId);
    
    /**
     * 11.b.2 Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción del riesgo 
     * de desastres en consonancia con las estrategias nacionales de reducción del riesgo de desastres [92]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.b.2
     */
    Optional<Indicadores> findIndicador_11_b_2(Integer proyectoId);
    
    /**
     * 11.c.1 Total de asistencia oficial para el desarrollo y otros flujos oficiales destinados 
     * a infraestructuras urbanas o proyectos de infraestructuras urbanas, por sector [93]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 11.c.1
     */
    Optional<Indicadores> findIndicador_11_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds11(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS11
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "11.1", "11.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
}
