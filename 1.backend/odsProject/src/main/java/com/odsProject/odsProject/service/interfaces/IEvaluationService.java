package com.odsProject.odsProject.service.interfaces;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

/**
 * Interfaz para el Servicio de Evaluación y Cálculo
 * Provee lógica para evaluar fórmulas matemáticas dinámicas y validarlas
 * antes de que sean guardadas o aplicadas a una medición.
 */
public interface IEvaluationService {

    /**
     * Evalúa una fórmula matemática usando un mapa de parámetros.
     *
     * @param formula     La expresión matemática (ej: "(p1 + p2) / p3")
     * @param parametros  Mapa con los valores de las variables (ej: "p1" -&gt; 10.0)
     * @return El resultado del cálculo, escalado a 4 decimales
     * @throws IllegalArgumentException si la fórmula es inválida o faltan parámetros
     */
    BigDecimal evaluateFormula(String formula, Map<String, BigDecimal> parametros);

    /**
     * Valida si una fórmula es sintácticamente correcta.
     * Esta versión NO declara variables — solo verifica que la cadena sea
     * un literal/expresión sin referencias a identificadores libres.
     * Para fórmulas con variables, usar {@link #validateFormula(String, Set)}.
     */
    boolean validateFormula(String formula);

    /**
     * Valida si una fórmula es sintácticamente correcta DECLARANDO previamente
     * el conjunto de variables permitidas. Esta es la forma correcta de validar
     * fórmulas como "(p1+p2)/p3" antes de guardarlas.
     *
     * @param formula  La expresión a validar
     * @param variables El conjunto de variables esperadas en la fórmula
     * @return true si la fórmula compila contra esas variables
     */
    boolean validateFormula(String formula, Set<String> variables);

    /**
     * Determina si una meta fue alcanzada dado un valor calculado y la meta esperada.
     * Devuelve true si {@code valor &gt;= meta}.
     */
    boolean metaAlcanzada(BigDecimal valor, BigDecimal meta);
}
