package com.odsProject.odsProject.service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * FormulaUtils — Utilidades transversales para análisis sintáctico de fórmulas matemáticas.
 *
 * Sirve como soporte al EvaluationService y a los servicios por-ODS para:
 *   1. Extraer variables (identificadores) presentes en una fórmula
 *   2. Filtrar palabras reservadas / funciones soportadas por exp4j
 *   3. Comparar variables usadas vs variables declaradas (auditoría de coherencia)
 */
public final class FormulaUtils {

    private FormulaUtils() {}

    /**
     * Patrón de identificadores válidos de variable.
     * Coincide con las reglas de exp4j: [a-zA-Z_][a-zA-Z0-9_]*
     */
    private static final Pattern IDENTIFIER = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");

    /**
     * Palabras reservadas que NO son variables, sino funciones o constantes
     * que exp4j reconoce nativamente. Si aparecen en la fórmula no se cuentan
     * como variables del usuario.
     */
    private static final Set<String> RESERVED = Set.of(
        // Funciones built-in de exp4j
        "abs", "acos", "asin", "atan", "cbrt", "ceil", "cos", "cosh",
        "exp", "floor", "log", "log10", "log2", "sin", "sinh", "sqrt",
        "tan", "tanh", "signum",
        // Constantes
        "pi", "e",
        // Marcadores semánticos del catálogo de indicadores que NO son variables
        "valor", "count"
    );

    /**
     * Extrae el conjunto ordenado (LinkedHashSet) de variables presentes en una fórmula.
     * Preserva el orden de aparición — útil para mostrar al usuario en el orden esperado.
     *
     * @param formula la expresión, p.ej. "(p1 + p2) / total"
     * @return conjunto de variables, p.ej. ["p1", "p2", "total"]
     */
    public static Set<String> extractVariables(String formula) {
        Set<String> variables = new LinkedHashSet<>();
        if (formula == null || formula.isBlank()) return variables;

        Matcher m = IDENTIFIER.matcher(formula);
        while (m.find()) {
            String token = m.group();
            // Filtrar reservadas (case-insensitive)
            if (!RESERVED.contains(token.toLowerCase())) {
                variables.add(token);
            }
        }
        return variables;
    }

    /**
     * Calcula las variables faltantes: están en la fórmula pero NO fueron declaradas
     * por el usuario. Si la lista no está vacía, la fórmula no se puede evaluar.
     */
    public static Set<String> missingVariables(String formula, Set<String> declared) {
        Set<String> used = extractVariables(formula);
        Set<String> missing = new LinkedHashSet<>(used);
        if (declared != null) {
            missing.removeAll(declared);
        }
        return missing;
    }

    /**
     * Calcula las variables sobrantes: fueron declaradas por el usuario pero NO se
     * usan en la fórmula. Útil como warning (no bloquea, solo informa).
     */
    public static Set<String> unusedVariables(String formula, Set<String> declared) {
        Set<String> used = extractVariables(formula);
        Set<String> unused = new LinkedHashSet<>();
        if (declared != null) {
            for (String d : declared) {
                if (!used.contains(d)) unused.add(d);
            }
        }
        return unused;
    }
}
