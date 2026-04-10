package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 16
 * Usa jOOQ con datasource ods16
 */
public interface IObjetivo16PazJusticiaRepository extends IOdsBaseRepository<ProyectoIndicadores, Proyectos, ProyectoIndicadorParametros, MedicionesHistoricas, AuditoriaOds16> {
    
    /**
     * 16.1.1 Número de víctimas de homicidios intencionales por cada 100.000 habitantes, 
     * desglosado por sexo y edad [127]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_1_1(Integer proyectoId);
    
    /**
     * 16.1.2 Muertes relacionadas con conflictos por cada 100.000 habitantes, 
     * desglosado por sexo, edad y causa [127]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_1_2(Integer proyectoId);
    
    /**
     * 16.1.3 Proporción de la población que ha sufrido a) violencia física, b) violencia psicológica 
     * o c) violencia sexual en los últimos 12 meses [127]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.3
     */
    Optional<ProyectoIndicadores> findIndicador_16_1_3(Integer proyectoId);
    
    /**
     * 16.1.4 Proporción de la población que se siente segura al caminar sola en su zona 
     * de residencia por la noche [127]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.4
     */
    Optional<ProyectoIndicadores> findIndicador_16_1_4(Integer proyectoId);
    
    /**
     * 16.2.1 Proporción de niños de entre 1 y 17 años que han sufrido algún castigo físico 
     * por parte de sus cuidadores en el último mes [128]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.2.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_2_1(Integer proyectoId);
    
    /**
     * 16.2.2 Número de víctimas de la trata de personas por cada 100.000 habitantes, 
     * desglosado por sexo, edad y forma de explotación [128]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.2.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_2_2(Integer proyectoId);
    
    /**
     * 16.2.3 Proporción de mujeres y hombres jóvenes de entre 18 y 29 años que sufrieron 
     * violencia sexual antes de los 18 años [128]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.2.3
     */
    Optional<ProyectoIndicadores> findIndicador_16_2_3(Integer proyectoId);
    
    /**
     * 16.3.1 Proporción de víctimas de a) violencia física, b) violencia psicológica o c) violencia sexual 
     * en los últimos 12 meses que notificaron el hecho a la policía o a otra autoridad [129]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.3.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_3_1(Integer proyectoId);
    
    /**
     * 16.3.2 Proporción de detenidos que no han sido condenados en el conjunto de la población reclusa total [129]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.3.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_3_2(Integer proyectoId);
    
    /**
     * 16.3.3 Proporción de la población que se ha visto implicada en alguna controversia en los dos últimos años 
     * y ha accedido a algún mecanismo oficial u oficioso de solución de controversias, desglosada por tipo de mecanismo [129, 130]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.3.3
     */
    Optional<ProyectoIndicadores> findIndicador_16_3_3(Integer proyectoId);
    
    /**
     * 16.4.1 Valor total de las corrientes financieras ilícitas entrantes y salientes 
     * (en dólares corrientes de los Estados Unidos) [130]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.4.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_4_1(Integer proyectoId);
    
    /**
     * 16.4.2 Proporción de armas incautadas, encontradas o entregadas cuyo origen o contexto ilícitos 
     * han sido determinados o establecidos por una autoridad competente, de conformidad con los instrumentos internacionales [130, 131]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.4.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_4_2(Integer proyectoId);
    
    /**
     * 16.5.1 Proporción de personas que han tenido al menos un contacto con un funcionario público 
     * y que han pagado un soborno a un funcionario público, o a las que un funcionario público les ha pedido un soborno, 
     * durante los últimos 12 meses [131, 132]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.5.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_5_1(Integer proyectoId);
    
    /**
     * 16.5.2 Proporción de negocios que han tenido al menos un contacto con un funcionario público 
     * y que han pagado un soborno a un funcionario público, o a los que un funcionario público les ha pedido un soborno, 
     * durante los últimos 12 meses [132]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.5.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_5_2(Integer proyectoId);
    
    /**
     * 16.6.1 Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente, 
     * desglosado por sector (o por códigos presupuestarios o elementos similares) [132, 133]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.6.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_6_1(Integer proyectoId);
    
    /**
     * 16.6.2 Proporción de la población que se siente satisfecha con su última experiencia de los servicios públicos [133]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.6.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_6_2(Integer proyectoId);
    
    /**
     * 16.7.1 Proporciones de plazas en las instituciones nacionales y locales, entre ellas: a) las asambleas legislativas, 
     * b) la administración pública y c) el poder judicial, en comparación con la distribución nacional, 
     * desglosadas por sexo, edad, personas con discapacidad y grupos de población [133, 134]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.7.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_7_1(Integer proyectoId);
    
    /**
     * 16.7.2 Proporción de la población que considera que la adopción de decisiones es inclusiva 
     * y responde a sus necesidades, desglosada por sexo, edad, discapacidad y grupo de población [134]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.7.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_7_2(Integer proyectoId);
    
    /**
     * 16.8.1 Proporción de miembros y derechos de voto de los países en desarrollo en organizaciones internacionales [134]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.8.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_8_1(Integer proyectoId);
    
    /**
     * 16.9.1 Proporción de niños menores de 5 años cuyo nacimiento se ha registrado ante una autoridad civil, 
     * desglosada por edad [135]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.9.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_9_1(Integer proyectoId);
    
    /**
     * 16.10.1 Número de casos verificados de asesinato, secuestro, desaparición forzada, detención arbitraria 
     * y tortura de periodistas, miembros asociados de los medios de comunicación, sindicalistas 
     * y defensores de los derechos humanos, en los últimos 12 meses [135]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.10.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_10_1(Integer proyectoId);
    
    /**
     * 16.10.2 Número de países que adoptan y aplican garantías constitucionales, legales o normativas 
     * para el acceso público a la información [136]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.10.2
     */
    Optional<ProyectoIndicadores> findIndicador_16_10_2(Integer proyectoId);
    
    /**
     * 16.a.1 Existencia de instituciones nacionales independientes de derechos humanos, 
     * en cumplimiento de los Principios de París [136]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.a.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_a_1(Integer proyectoId);
    
    /**
     * 16.b.1 Proporción de la población que declara haberse sentido personalmente discriminada 
     * o acosada en los últimos 12 meses por motivos de discriminación prohibidos por el derecho internacional de los derechos humanos [137]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.b.1
     */
    Optional<ProyectoIndicadores> findIndicador_16_b_1(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS16
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<ProyectoIndicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS16
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "16.1", "16.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<ProyectoIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS16 ──
    
    /**
     * Encuentra todos los proyectos del ODS16
     * 
     * @return Lista de todos los proyectos del ODS16
     */
    List<Proyectos> findAllProyectosOds16();
    
    /**
     * Encuentra un proyecto del ODS16 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds16ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS16
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<ProyectoIndicadorParametros> findAllMetasProyectoOds16(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS16 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<ProyectoIndicadorParametros> findMetaProyectoOds16ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS16
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds16(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS16 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds16ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS16
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds16> findAllAuditoriasOds16();
    
    /**
     * Encuentra una auditoría del ODS16 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds16> findAuditoriaOds16ById(Integer auditoriaId);
}
