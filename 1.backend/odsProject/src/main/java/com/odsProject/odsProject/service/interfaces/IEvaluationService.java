package com.odsProject.odsProject.service.interfaces;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Interfaz para el Servicio de Evaluación y Cálculo
 * Provee lógica para evaluar fórmulas matemáticas dinámicas
 */
public interface IEvaluationService {

    /**
     * Evalúa una fórmula matemática usando un mapa de parámetros
     * 
     * @param formula La expresión matemática (ej: "(p1 + p2) / p3")
     * @param parametros Mapa con los valores de las variables (ej: "p1" -> 10.0)
     * @return El resultado del cálculo
     * @throws IllegalArgumentException si la fórmula es inválida o faltan parámetros
     */
    BigDecimal evaluateFormula(String formula, Map<String, BigDecimal> parametros);

    /**
     * Valida si una fórmula es sintácticamente correcta
     * 
     * @param formula La expresión a validar
     * @return true si es válida, false de lo contrario
     */
    boolean validateFormula(String formula);
}
