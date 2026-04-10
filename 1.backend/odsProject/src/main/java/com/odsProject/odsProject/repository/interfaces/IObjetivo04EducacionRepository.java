package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods04.tables.pojos.AuditoriaOds04;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 4: Educación de Calidad
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 4
 * Usa jOOQ con datasource ods04
 */
public interface IObjetivo04EducacionRepository extends IOdsBaseRepository<ProyectoIndicadores, Proyectos, ProyectoIndicadorParametros, MedicionesHistoricas, AuditoriaOds04> {
    
    /**
     * 4.1.1 Proporción de niños y adolescentes que, a) en los grados 2 o 3, 
     * b) al final de la educación primaria y c) al final de la educación secundaria inferior, 
     * han alcanzado al menos un nivel mínimo de competencia en i) lectura y ii) matemáticas, 
     * desglosada por sexo [31]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.1.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_1_1(Integer proyectoId);
    
    /**
     * 4.1.2 Tasa de finalización (educación primaria, educación secundaria inferior y educación secundaria superior) [32]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.1.2
     */
    Optional<ProyectoIndicadores> findIndicador_4_1_2(Integer proyectoId);
    
    /**
     * 4.2.1 Proporción de niños de 24 a 59 meses cuyo desarrollo es adecuado en cuanto a la salud, 
     * el aprendizaje y el bienestar psicosocial, desglosada por sexo [33]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.2.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_2_1(Integer proyectoId);
    
    /**
     * 4.2.2 Tasa de participación en el aprendizaje organizado (un año antes de la edad oficial 
     * de ingreso a la educación primaria), desglosada por sexo [34]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.2.2
     */
    Optional<ProyectoIndicadores> findIndicador_4_2_2(Integer proyectoId);
    
    /**
     * 4.3.1 Tasa de participación de jóvenes y adultos en la educación y formación académica 
     * en los últimos 12 meses, desglosada por sexo [35]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.3.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_3_1(Integer proyectoId);
    
    /**
     * 4.4.1 Proporción de jóvenes y adultos con competencias en tecnología de la información 
     * y las comunicaciones (TIC), desglosada por tipo de competencia [35]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.4.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_4_1(Integer proyectoId);
    
    /**
     * 4.5.1 Índices de paridad (entre mujeres y hombres, zonas rurales y urbanas, 
     * quintiles de riqueza y otros grupos, como personas con discapacidad, pueblos indígenas 
     * y personas afectadas por conflictos) en todos los indicadores de educación [36]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.5.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_5_1(Integer proyectoId);
    
    /**
     * 4.6.1 Tasa de alfabetización de adultos/jóvenes [36]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.6.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_6_1(Integer proyectoId);
    
    /**
     * 4.7.1 Grado en que i) la educación para la ciudadanía mundial y ii) la educación para el desarrollo 
     * sostenible, incluida la igualdad de género y la no discriminación, están incorporadas en: a) las políticas 
     * educativas nacionales, b) los currículos, c) la formación del docente y d) la evaluación de los estudiantes [37]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.7.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_7_1(Integer proyectoId);
    
    /**
     * 4.a.1 Proporción de escuelas que ofrecen servicios básicos, desglosada por tipo de servicio [38]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.a.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_a_1(Integer proyectoId);
    
    /**
     * 4.b.1 Volumen de la asistencia oficial para el desarrollo destinada a becas [39]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.b.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_b_1(Integer proyectoId);
    
    /**
     * 4.c.1 Proporción de docentes con las calificaciones mínimas requeridas, 
     * desglosada por tipo de institution educativa [39]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 4.c.1
     */
    Optional<ProyectoIndicadores> findIndicador_4_c_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<ProyectoIndicadores> findAllIndicadoresByProyectoOds04(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "4.1", "4.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS04 ──
    
    /**
     * Encuentra todos los proyectos del ODS04
     * 
     * @return Lista de todos los proyectos del ODS04
     */
    List<Proyectos> findAllProyectosOds04();
    
    /**
     * Encuentra un proyecto del ODS04 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds04ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS04
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds04(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS04 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds04ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS04
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds04(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS04 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds04ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS04
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds04> findAllAuditoriasOds04();
    
    /**
     * Encuentra una auditoría del ODS04 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds04> findAuditoriaOds04ById(Integer auditoriaId);
}
