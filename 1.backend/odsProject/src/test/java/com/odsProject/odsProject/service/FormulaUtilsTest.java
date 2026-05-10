package com.odsProject.odsProject.service;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests del extractor de variables (Sprint 5).
 */
class FormulaUtilsTest {

    @Test
    void extraeVariablesSimples() {
        Set<String> vars = FormulaUtils.extractVariables("(p1 + p2) / p3");
        assertEquals(Set.of("p1", "p2", "p3"), vars);
    }

    @Test
    void preservaOrdenDeAparicion() {
        Set<String> vars = FormulaUtils.extractVariables("(c + a) * b");
        // LinkedHashSet preserva orden de aparición
        assertArrayEquals(new String[]{"c", "a", "b"}, vars.toArray());
    }

    @Test
    void filtraFuncionesReservadas() {
        // sqrt, sin, cos, etc. NO son variables
        Set<String> vars = FormulaUtils.extractVariables("sqrt(p1) + sin(p2) + cos(0)");
        assertEquals(Set.of("p1", "p2"), vars);
    }

    @Test
    void filtraConstantesYMarcadores() {
        // pi, e, valor, count NO son variables
        Set<String> vars = FormulaUtils.extractVariables("pi * p1 + e + valor + count");
        assertEquals(Set.of("p1"), vars);
    }

    @Test
    void identificadoresConGuionBajoYNumeros() {
        Set<String> vars = FormulaUtils.extractVariables("(total_becados / total_estudiantes_2024) * 100");
        assertEquals(Set.of("total_becados", "total_estudiantes_2024"), vars);
    }

    @Test
    void formulaVaciaDevuelveSetVacio() {
        assertTrue(FormulaUtils.extractVariables("").isEmpty());
        assertTrue(FormulaUtils.extractVariables(null).isEmpty());
        assertTrue(FormulaUtils.extractVariables("   ").isEmpty());
    }

    @Test
    void duplicadosNoAparecenDosVeces() {
        Set<String> vars = FormulaUtils.extractVariables("p1 + p1 * p1");
        assertEquals(1, vars.size());
        assertTrue(vars.contains("p1"));
    }

    @Test
    void missingVariablesDetectaFaltantes() {
        Set<String> faltantes = FormulaUtils.missingVariables(
            "(p1 + p2) / p3",
            Set.of("p1", "p2"));
        assertEquals(Set.of("p3"), faltantes);
    }

    @Test
    void unusedVariablesDetectaSobrantes() {
        Set<String> sobrantes = FormulaUtils.unusedVariables(
            "p1 + p2",
            Set.of("p1", "p2", "p3"));
        assertEquals(Set.of("p3"), sobrantes);
    }

    @Test
    void coherenciaPerfectaSinFaltantesNiSobrantes() {
        Set<String> faltantes = FormulaUtils.missingVariables(
            "(p1+p2)/p3", Set.of("p1", "p2", "p3"));
        Set<String> sobrantes = FormulaUtils.unusedVariables(
            "(p1+p2)/p3", Set.of("p1", "p2", "p3"));
        assertTrue(faltantes.isEmpty());
        assertTrue(sobrantes.isEmpty());
    }
}
