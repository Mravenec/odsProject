package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Indicadores;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MetasProyecto;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods17.tables.pojos.AuditoriaOds17;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz del Repositorio para el Objetivo 17: Alianzas para Lograr los Objetivos
 * Implementa los métodos para acceder a los indicadores del Objetivo de Desarrollo Sostenible 17
 * Usa jOOQ con datasource ods17
 */
public interface IObjetivo17AlianzasRepository extends IOdsBaseRepository<Indicadores, Proyectos, MetasProyecto, MedicionesHistoricas, AuditoriaOds17> {
    
    /**
     * 17.1.1 Total de ingresos del gobierno en proporción al PIB, desglosado por fuente [138]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.1.1
     */
    Optional<Indicadores> findIndicador_17_1_1(Integer proyectoId);
    
    /**
     * 17.1.2 Proporción del presupuesto nacional financiado por impuestos internos [139]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.1.2
     */
    Optional<Indicadores> findIndicador_17_1_2(Integer proyectoId);
    
    /**
     * 17.2.1 Asistencia oficial para el desarrollo neta, total y para los países menos adelantados 
     * como proporción del ingreso nacional bruto de los donantes [140]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.2.1
     */
    Optional<Indicadores> findIndicador_17_2_1(Integer proyectoId);
    
    /**
     * 17.3.1 Recursos financieros adicionales movilizados para los países en desarrollo 
     * debido a la asistencia oficial para el desarrollo [140]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.3.1
     */
    Optional<Indicadores> findIndicador_17_3_1(Integer proyectoId);
    
    /**
     * 17.3.2 Volumen de remesas (en dólares de los Estados Unidos) en proporción al PIB total [141]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.3.2
     */
    Optional<Indicadores> findIndicador_17_3_2(Integer proyectoId);
    
    /**
     * 17.4.1 Servicio de la deuda en proporción a las exportaciones de bienes, servicios e ingresos primarios [142]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.4.1
     */
    Optional<Indicadores> findIndicador_17_4_1(Integer proyectoId);
    
    /**
     * 17.5.1 Número de países que adoptan y aplican sistemas de promoción de las inversiones 
     * para los países menos adelantados [143]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.5.1
     */
    Optional<Indicadores> findIndicador_17_5_1(Integer proyectoId);
    
    /**
     * 17.6.1 Número de abonados a servicios de banda ancha fija por cada 100 habitantes, 
     * desglosado por región [144]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.6.1
     */
    Optional<Indicadores> findIndicador_17_6_1(Integer proyectoId);
    
    /**
     * 17.7.1 Total de los fondos destinados a los países en desarrollo y los países desarrollados 
     * con el fin de promover el desarrollo, la transferencia y la difusión de tecnologías ecológicamente racionales [144, 145]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.7.1
     */
    Optional<Indicadores> findIndicador_17_7_1(Integer proyectoId);
    
    /**
     * 17.8.1 Proporción de personas que utilizan Internet [145]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.8.1
     */
    Optional<Indicadores> findIndicador_17_8_1(Integer proyectoId);
    
    /**
     * 17.9.1 Valor en dólares de la asistencia oficial para el desarrollo comprometida 
     * para los países en desarrollo [146]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.9.1
     */
    Optional<Indicadores> findIndicador_17_9_1(Integer proyectoId);
    
    /**
     * 17.10.1 Promedio arancelario mundial ponderado [147]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.10.1
     */
    Optional<Indicadores> findIndicador_17_10_1(Integer proyectoId);
    
    /**
     * 17.11.1 Participación de los países en desarrollo y los países menos adelantados 
     * en las exportaciones mundiales [147]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.11.1
     */
    Optional<Indicadores> findIndicador_17_11_1(Integer proyectoId);
    
    /**
     * 17.12.1 Promedio ponderado de los aranceles que enfrentan los países en desarrollo, 
     * los países menos adelantados y los pequeños Estados insulares en desarrollo [148]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.12.1
     */
    Optional<Indicadores> findIndicador_17_12_1(Integer proyectoId);
    
    /**
     * 17.13.1 Tablero macroeconómico [149]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.13.1
     */
    Optional<Indicadores> findIndicador_17_13_1(Integer proyectoId);
    
    /**
     * 17.14.1 Número de países que cuentan con mecanismos para mejorar la coherencia 
     * de las políticas de desarrollo sostenible [149]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.14.1
     */
    Optional<Indicadores> findIndicador_17_14_1(Integer proyectoId);
    
    /**
     * 17.15.1 Grado de utilización de los marcos de resultados y las herramientas de planificación 
     * de los propios países por los proveedores de cooperación para el desarrollo [150]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.15.1
     */
    Optional<Indicadores> findIndicador_17_15_1(Integer proyectoId);
    
    /**
     * 17.16.1 Número de países que informan de sus progresos en los marcos de múltiples interesados 
     * para el seguimiento de la eficacia de las actividades de desarrollo que apoyan el logro 
     * de los Objetivos de Desarrollo Sostenible [151]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.16.1
     */
    Optional<Indicadores> findIndicador_17_16_1(Integer proyectoId);
    
    /**
     * 17.17.1 Suma en dólares de los Estados Unidos prometida a las alianzas público-privadas 
     * centradas en la infraestructura [152]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.17.1
     */
    Optional<Indicadores> findIndicador_17_17_1(Integer proyectoId);
    
    /**
     * 17.18.1 Indicadores de la capacidad estadística [153]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.18.1
     */
    Optional<Indicadores> findIndicador_17_18_1(Integer proyectoId);
    
    /**
     * 17.18.2 Número de países cuya legislación nacional sobre estadísticas cumple 
     * los Principios Fundamentales de las Estadísticas Oficiales [153]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.18.2
     */
    Optional<Indicadores> findIndicador_17_18_2(Integer proyectoId);
    
    /**
     * 17.18.3 Número de países que cuentan con un plan estadístico nacional plenamente financiado 
     * y en proceso de aplicación, desglosado por fuente de financiación [153, 154]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.18.3
     */
    Optional<Indicadores> findIndicador_17_18_3(Integer proyectoId);
    
    /**
     * 17.19.1 Valor en dólares de todos los recursos proporcionados para fortalecer 
     * la capacidad estadística de los países en desarrollo [154]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.19.1
     */
    Optional<Indicadores> findIndicador_17_19_1(Integer proyectoId);
    
    /**
     * 17.19.2 Proporción de países que a) han realizado al menos un censo de población 
     * y vivienda en los últimos diez años; y b) han registrado el 100% de los nacimientos y el 80% de las defunciones [155]
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 17.19.2
     */
    Optional<Indicadores> findIndicador_17_19_2(Integer proyectoId);
    
    /**
     * Encuentra todos los indicadores asociados a un proyecto específico del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de todos los indicadores del proyecto
     */
    List<Indicadores> findAllIndicadoresByProyectoOds17(Integer proyectoId);
    
    /**
     * Encuentra indicadores filtrando por meta específica del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @param metaPrefix Prefijo de la meta (ej: "17.1", "17.2")
     * @return Lista de indicadores que pertenecen a la meta especificada
     */
    List<Indicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix);
    
    // ── Métodos específicos del ODS17 ──
    
    /**
     * Encuentra todos los proyectos del ODS17
     * 
     * @return Lista de todos los proyectos del ODS17
     */
    List<Proyectos> findAllProyectosOds17();
    
    /**
     * Encuentra un proyecto del ODS17 por su ID
     * 
     * @param proyectoId ID del proyecto
     * @return Optional con el proyecto encontrado
     */
    Optional<Proyectos> findProyectoOds17ById(Integer proyectoId);
    
    /**
     * Encuentra todas las metas de proyecto del ODS17
     * 
     * @param proyectoId ID del proyecto
     * @return Lista de metas del proyecto
     */
    List<MetasProyecto> findAllMetasProyectoOds17(Integer proyectoId);
    
    /**
     * Encuentra una meta de proyecto del ODS17 por su ID
     * 
     * @param metaId ID de la meta
     * @return Optional con la meta encontrada
     */
    Optional<MetasProyecto> findMetaProyectoOds17ById(Integer metaId);
    
    /**
     * Encuentra todas las mediciones históricas del ODS17
     * 
     * @param indicadorId ID del indicador
     * @return Lista de mediciones históricas
     */
    List<MedicionesHistoricas> findAllMedicionesHistoricasOds17(Integer indicadorId);
    
    /**
     * Encuentra una medición histórica del ODS17 por su ID
     * 
     * @param medicionId ID de la medición
     * @return Optional con la medición encontrada
     */
    Optional<MedicionesHistoricas> findMedicionHistoricaOds17ById(Integer medicionId);
    
    /**
     * Encuentra todas las auditorías del ODS17
     * 
     * @return Lista de todas las auditorías
     */
    List<AuditoriaOds17> findAllAuditoriasOds17();
    
    /**
     * Encuentra una auditoría del ODS17 por su ID
     * 
     * @param auditoriaId ID de la auditoría
     * @return Optional con la auditoría encontrada
     */
    Optional<AuditoriaOds17> findAuditoriaOds17ById(Integer auditoriaId);
}
