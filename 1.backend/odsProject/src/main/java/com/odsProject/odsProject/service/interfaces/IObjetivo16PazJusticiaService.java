package com.odsProject.odsProject.service.interfaces;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de Servicio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 * Define los contratos de negocio para los indicadores del Objetivo de Desarrollo Sostenible 16
 * Extiende IOdsBaseService con tipos específicos de ODS16
 */
public interface IObjetivo16PazJusticiaService extends IOdsBaseService<
    ProyectoIndicadores,     // T - ProyectoIndicadores
    Proyectos,               // P - Proyectos  
    ProyectoIndicadorParametros, // M - ProyectoIndicadorParametros
    MedicionesHistoricas,     // MH - MedicionesHistoricas
    Object                   // A - Auditoria (placeholder)
> {
    
    /**
     * Obtiene todos los indicadores del Objetivo 16: Paz, Justicia e Instituciones Sólidas
     * 
     * @param proyectoId ID del proyecto
     * @return Lista con todos los indicadores del objetivo
     */
    List<ProyectoIndicadores> getAllIndicators(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.1.1
     * Número de víctimas de homicidios intencionales por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_1_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.1.2
     * Muertes relacionadas con conflictos por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_1_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.1.3
     * Proporción de la población que ha sufrido violencia física, psicológica o sexual
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.3
     */
    Optional<ProyectoIndicadores> getIndicador_16_1_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.1.4
     * Proporción de la población que se siente segura caminando sola después de oscurecer
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.1.4
     */
    Optional<ProyectoIndicadores> getIndicador_16_1_4(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.2.1
     * Proporción de niños que han sufrido castigo físico o agresión psicológica
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.2.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_2_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.2.2
     * Número de víctimas de la trata de personas por cada 100.000 habitantes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.2.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_2_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.2.3
     * Proporción de jóvenes que sufrieron violencia sexual antes de cumplir los 18 años
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.2.3
     */
    Optional<ProyectoIndicadores> getIndicador_16_2_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.3.1
     * Proporción de víctimas que han notificado su victimización a las autoridades
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.3.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_3_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.3.2
     * Proporción de detenidos que no han sido condenados en la población reclusa total
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.3.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_3_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.3.3
     * Proporción de la población que ha accedido a mecanismos de solución de controversias
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.3.3
     */
    Optional<ProyectoIndicadores> getIndicador_16_3_3(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.4.1
     * Valor total de las corrientes financieras ilícitas entrantes y salientes
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.4.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_4_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.4.2
     * Proporción de armas incautadas con origen ilícito determinado
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.4.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_4_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.5.1
     * Proporción de personas que han pagado un soborno a un funcionario público
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.5.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_5_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.5.2
     * Proporción de negocios que han pagado un soborno a un funcionario público
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.5.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_5_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.6.1
     * Gastos primarios del gobierno en proporción al presupuesto aprobado originalmente
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.6.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_6_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.6.2
     * Proporción de la población satisfecha con servicios públicos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.6.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_6_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.7.1
     * Proporciones de plazas en instituciones nacionales y locales
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.7.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_7_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.7.2
     * Proporción de la población que considera que la adopción de decisiones es inclusiva
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.7.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_7_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.8.1
     * Proporción de miembros y derechos de voto de países en desarrollo
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.8.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_8_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.9.1
     * Proporción de niños cuyo nacimiento se ha registrado ante autoridad civil
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.9.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_9_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.10.1
     * Número de casos verificados de asesinato, secuestro, desaparición de periodistas
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.10.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_10_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.10.2
     * Número de países con garantías para acceso público a la información
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.10.2
     */
    Optional<ProyectoIndicadores> getIndicador_16_10_2(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.a.1
     * Existencia de instituciones nacionales independientes de derechos humanos
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.a.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_a_1(Integer proyectoId);
    
    /**
     * Obtiene el indicador 16.b.1
     * Proporción de la población que se siente discriminada o acosada
     * 
     * @param proyectoId ID del proyecto
     * @return Datos del indicador 16.b.1
     */
    Optional<ProyectoIndicadores> getIndicador_16_b_1(Integer proyectoId);
    
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
}
