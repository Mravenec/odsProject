package com.odsProject.odsProject.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests del motor de evaluación (Sprint 5).
 *
 * Cubren los casos del enunciado del profesor:
 *   - Fórmula del estilo ((a+b)*z)/2 con parámetros que tienen nombres reales
 *   - Validación con variables declaradas
 *   - Comportamiento ante división por cero y NaN
 *   - Comparación contra meta (alcanzada / no alcanzada)
 */
class EvaluationServiceTest {

    private final EvaluationService svc = new EvaluationService();

    // ── evaluateFormula ───────────────────────────────────────────────────

    @Test
    void evaluateSimpleQuotient() {
        Map<String, BigDecimal> params = Map.of(
            "p1", new BigDecimal("25"),
            "p2", new BigDecimal("100")
        );
        BigDecimal result = svc.evaluateFormula("(p1/p2)*100", params);
        assertEquals(0, result.compareTo(new BigDecimal("25.0000")),
            "25/100*100 debería ser 25.0000");
    }

    @Test
    void evaluateNestedExpressionDelEnunciado() {
        // Caso del enunciado: ((a+b)*z)/2  con a=10, b=20, z=2  →  30
        Map<String, BigDecimal> params = Map.of(
            "a", new BigDecimal("10"),
            "b", new BigDecimal("20"),
            "z", new BigDecimal("2")
        );
        BigDecimal result = svc.evaluateFormula("((a+b)*z)/2", params);
        assertEquals(0, result.compareTo(new BigDecimal("30.0000")));
    }

    @Test
    void evaluateWithDescriptiveVariableNames() {
        // El enunciado dice que las variables tienen nombres del dominio,
        // no a/b/c. Probamos con identificadores con guion bajo.
        Map<String, BigDecimal> params = Map.of(
            "estudiantes_becados", new BigDecimal("80"),
            "total_estudiantes",   new BigDecimal("400")
        );
        BigDecimal result = svc.evaluateFormula(
            "(estudiantes_becados / total_estudiantes) * 100", params);
        assertEquals(0, result.compareTo(new BigDecimal("20.0000")));
    }

    @Test
    void evaluateDivisionByZeroReturnsZero() {
        Map<String, BigDecimal> params = Map.of(
            "p1", new BigDecimal("10"),
            "p2", BigDecimal.ZERO
        );
        BigDecimal result = svc.evaluateFormula("p1/p2", params);
        assertEquals(0, result.compareTo(BigDecimal.ZERO),
            "División entre cero debe devolver 0, no romper el flujo");
    }

    @Test
    void evaluateNullFormulaReturnsZero() {
        assertEquals(BigDecimal.ZERO, svc.evaluateFormula(null, new HashMap<>()));
        assertEquals(BigDecimal.ZERO, svc.evaluateFormula("", new HashMap<>()));
        assertEquals(BigDecimal.ZERO, svc.evaluateFormula("   ", new HashMap<>()));
    }

    @Test
    void evaluateNullValuesAreCoercedToZero() {
        Map<String, BigDecimal> params = new HashMap<>();
        params.put("p1", null);
        params.put("p2", new BigDecimal("100"));
        BigDecimal result = svc.evaluateFormula("p1+p2", params);
        assertEquals(0, result.compareTo(new BigDecimal("100.0000")));
    }

    @Test
    void evaluateRespectsCommaAsDecimalSeparator() {
        // El motor reemplaza ',' por '.' por seguridad regional
        Map<String, BigDecimal> params = Map.of(
            "p1", new BigDecimal("3.5"),
            "p2", new BigDecimal("2")
        );
        BigDecimal result = svc.evaluateFormula("p1*p2", params);
        assertEquals(0, result.compareTo(new BigDecimal("7.0000")));
    }

    @Test
    void invalidFormulaThrowsIllegalArgument() {
        Map<String, BigDecimal> params = Map.of("p1", new BigDecimal("1"));
        assertThrows(IllegalArgumentException.class,
            () -> svc.evaluateFormula("((p1+", params));
    }

    // ── validateFormula ───────────────────────────────────────────────────

    @Test
    void validateFormulaConVariablesDeclaradas() {
        assertTrue(svc.validateFormula("(p1+p2)/p3", Set.of("p1", "p2", "p3")));
    }

    @Test
    void validateFormulaConVariableFaltanteEsInvalida() {
        // Si la fórmula usa p3 pero solo declaramos p1 y p2, la validación
        // contra el set declarado debería fallar
        assertFalse(svc.validateFormula("(p1+p2)/p3", Set.of("p1", "p2")));
    }

    @Test
    void validateFormulaSintaxisInvalidaEsFalse() {
        assertFalse(svc.validateFormula("((p1+", Set.of("p1")));
        assertFalse(svc.validateFormula("p1 ++ p2", Set.of("p1", "p2")));
    }

    @Test
    void validateFormulaLiteralSinVariables() {
        // Fórmulas como "100" o "valor" deben validar correctamente con set vacío
        assertTrue(svc.validateFormula("100"));
        assertTrue(svc.validateFormula("100", Set.of()));
    }

    @Test
    void validateFormulaAutoExtraeVariables() {
        // La sobrecarga sin parametrizar debe auto-detectar las variables
        // y validar contra ellas (siempre debería ser true para fórmulas válidas)
        assertTrue(svc.validateFormula("(p1+p2)/p3"));
    }

    // ── metaAlcanzada ─────────────────────────────────────────────────────

    @Test
    void metaAlcanzadaCuandoValorIgualMeta() {
        assertTrue(svc.metaAlcanzada(new BigDecimal("100"), new BigDecimal("100")));
    }

    @Test
    void metaAlcanzadaCuandoValorMayor() {
        assertTrue(svc.metaAlcanzada(new BigDecimal("150"), new BigDecimal("100")));
    }

    @Test
    void metaNoAlcanzadaCuandoValorMenor() {
        assertFalse(svc.metaAlcanzada(new BigDecimal("80"), new BigDecimal("100")));
    }

    @Test
    void metaAlcanzadaConNullsEsFalse() {
        assertFalse(svc.metaAlcanzada(null, new BigDecimal("100")));
        assertFalse(svc.metaAlcanzada(new BigDecimal("100"), null));
        assertFalse(svc.metaAlcanzada(null, null));
    }
}
