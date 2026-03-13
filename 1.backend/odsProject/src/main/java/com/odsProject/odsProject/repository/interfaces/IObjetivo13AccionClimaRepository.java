package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods13.tables.pojos.AuditoriaOds13;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 13: Acción por el Clima
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 13
 * Usa jOOQ con datasource ods13
 */
public interface IObjetivo13AccionClimaRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds13> {
    
    /**
     * 13.1.1 Número de personas muertas, desaparecidas y afectadas directamente atribuido 
     * a desastres por cada 100.000 personas [102]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.1.1
     */
    Optional<Indicadores> findIndicador_13_1_1(Integer proyectoId);
    
    /**
     * 13.1.2 Número de países que adoptan y aplican estrategias nacionales de reducción del riesgo de desastres 
     * en consonancia con el Marco de Sendái para la Reducción del Riesgo de Desastres 2015-2030 [103]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.1.2
     */
    Optional<Indicadores> findIndicador_13_1_2(Integer proyectoId);
    
    /**
     * 13.1.3 Proporción de gobiernos locales que adoptan y aplican estrategias locales de reducción del riesgo 
     * de desastres en consonancia con las estrategias nacionales de reducción del riesgo de desastres [103]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.1.3
     */
    Optional<Indicadores> findIndicador_13_1_3(Integer proyectoId);
    
    /**
     * 13.2.1 Número de países con contribuciones determinadas a nivel nacional, estrategias a largo plazo, 
     * planes nacionales de adaptación y comunicaciones sobre la adaptación, notificadas a la secretaría 
     * de la Convención Marco de las Naciones Unidas sobre el Cambio Climático [104]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.2.1
     */
    Optional<Indicadores> findIndicador_13_2_1(Integer proyectoId);
    
    /**
     * 13.2.2 Emisiones totales de gases de efecto invernadero por año [104]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.2.2
     */
    Optional<Indicadores> findIndicador_13_2_2(Integer proyectoId);
    
    /**
     * 13.2.3 Indicador adicional para meta 13.2
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.2.3
     */
    Optional<Indicadores> findIndicador_13_2_3(Integer proyectoId);
    
    /**
     * 13.3.1 Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo sostenible 
     * se incorporan en a) las políticas nacionales de educación, b) los planes de estudio, c) la formación de docentes 
     * y d) la evaluación de los estudiantes [104]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.3.1
     */
    Optional<Indicadores> findIndicador_13_3_1(Integer proyectoId);
    
    /**
     * 13.3.2 Indicador adicional para meta 13.3
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.3.2
     */
    Optional<Indicadores> findIndicador_13_3_2(Integer proyectoId);
    
    /**
     * 13.a.1 Cantidades proporcionadas y movilizadas en dólares de los Estados Unidos al año 
     * en relación con el objetivo actual de 100.000 millones de dólares para el período 2020-2025 [104]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.a.1
     */
    Optional<Indicadores> findIndicador_13_a_1(Integer proyectoId);
    
    /**
     * 13.b.1 Número de países menos adelantados y pequeños Estados insulares en desarrollo 
     * que reciben apoyo para la planificación y ejecución de la adaptación y la mitigación del cambio climático [104]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.b.1
     */
    Optional<Indicadores> findIndicador_13_b_1(Integer proyectoId);
    
    /**
     * 13.b.2 Indicador adicional para meta 13.b
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 13.b.2
     */
    Optional<Indicadores> findIndicador_13_b_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS13
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds13(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS13
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "13.1", "13.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS13 ──
    
    /**
     * Encuentra todos los proyectos del ODS13
     * 
     * @return Lista de todos los proyectos del ODS13
     */
    List<Proyectos> findAllProyectosOds13();
    
    /**
     * Encuentra un proyecto del ODS13 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds13ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS13
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> findAllMetasProyectoOds13(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS13 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> findMetaProyectoOds13ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS13
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds13(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS13 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds13ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS13
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds13> findAllAuditoriasOds13();
    
    /**
     * Encuentra una auditoría del ODS13 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds13> findAuditoriaOds13ById(Integer auditoriaId);
}
