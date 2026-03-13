package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods02.tables.pojos.AuditoriaOds02;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 2: Hambre Cero
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 2
 * Usa jOOQ con datasource ods02
 */
public interface IObjetivo02HambreCeroRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds02> {
    
    // ── Indicadores Específicos del ODS02 ──
    
    /**
     * 2.1.1 Prevalencia de la subalimentación [10]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.1.1
     */
    Optional<Indicadores> findIndicador_2_1_1(Integer proyectoId);
    
    /**
     * 2.1.2 Prevalencia de la inseguridad alimentaria moderada o grave entre la población, 
     * según la escala de experiencia de inseguridad alimentaria [10]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.1.2
     */
    Optional<Indicadores> findIndicador_2_1_2(Integer proyectoId);
    
    /**
     * 2.2.1 Prevalencia del retraso del crecimiento (estatura para la edad, desviación típica < -2 
     * entre los niños menores de 5 años) [11]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.1
     */
    Optional<Indicadores> findIndicador_2_2_1(Integer proyectoId);
    
    /**
     * 2.2.2 Prevalencia de la malnutrición (peso para la estatura, desviación típica > +2 o < -2 
     * de la mediana de los patrones de crecimiento infantil de la OMS) entre los niños menores de 5 años, 
     * desglosada por tipo (emaciación y sobrepeso) [11, 12]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.2
     */
    Optional<Indicadores> findIndicador_2_2_2(Integer proyectoId);
    
    /**
     * 2.2.3 Prevalencia de la anemia en las mujeres de entre 15 y 49 años, 
     * según el embarazo (porcentaje) [12]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.3
     */
    Optional<Indicadores> findIndicador_2_2_3(Integer proyectoId);
    
    /**
     * 2.2.4 Prevalencia del umbral mínimo de diversidad alimentaria, por grupo de población 
     * (porcentaje) [12]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.2.4
     */
    Optional<Indicadores> findIndicador_2_2_4(Integer proyectoId);
    
    /**
     * 2.3.1 Volumen de producción por unidad de trabajo desglosado por tamaño y tipo de explotación 
     * agrícola y ganadera [13]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.3.1
     */
    Optional<Indicadores> findIndicador_2_3_1(Integer proyectoId);
    
    /**
     * 2.3.2 Media de ingresos de los productores de alimentos en pequeña escala, 
     * desglosada por tamaño y tipo de explotación [13]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.3.2
     */
    Optional<Indicadores> findIndicador_2_3_2(Integer proyectoId);
    
    /**
     * 2.4.1 Proporción de la superficie agrícola en que se practica una agricultura productiva y sostenible [14]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.4.1
     */
    Optional<Indicadores> findIndicador_2_4_1(Integer proyectoId);
    
    /**
     * 2.5.1 Número de: a) recursos genéticos vegetales y b) animales para la alimentación y la agricultura 
     * conservados en instalaciones de conservación a mediano y largo plazo [15]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.5.1
     */
    Optional<Indicadores> findIndicador_2_5_1(Integer proyectoId);
    
    /**
     * 2.5.2 Proporción de razas y variedades locales y transfronterizas consideradas en riesgo de extinción [16]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.5.2
     */
    Optional<Indicadores> findIndicador_2_5_2(Integer proyectoId);
    
    /**
     * 2.a.1 Índice de orientación agrícola para el gasto público [17]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.a.1
     */
    Optional<Indicadores> findIndicador_2_a_1(Integer proyectoId);
    
    /**
     * 2.a.2 Total de corrientes oficiales de recursos (asistencia oficial para el desarrollo más otras corrientes oficiales) 
     * destinadas al sector agrícola [17]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.a.2
     */
    Optional<Indicadores> findIndicador_2_a_2(Integer proyectoId);
    
    /**
     * 2.b.1 Subsidios a la exportación de productos agropecuarios [18]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.b.1
     */
    Optional<Indicadores> findIndicador_2_b_1(Integer proyectoId);
    
    /**
     * 2.c.1 Indicador de anomalías en los precios de los alimentos [18]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 2.c.1
     */
    Optional<Indicadores> findIndicador_2_c_1(Integer proyectoId);
    
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
    
    // ── Métodos específicos del ODS02 ──
    
    List<Proyectos> findAllProyectosOds02();
    Optional<Proyectos> findProyectoOds02ById(Integer proyectoId);
    List<MetasProyecto> findAllMetasProyectoOds02(Integer proyectoId);
    Optional<MetasProyecto> findMetaProyectoOds02ById(Integer metaId);
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds02(Integer indicadorId);
    Optional<MedicionesHistoricas> findMedicionHistoricaOds02ById(Integer medicionId);
    List<AuditoriaOds02> findAllAuditoriasOds02();
    Optional<AuditoriaOds02> findAuditoriaOds02ById(Integer auditoriaId);
}
