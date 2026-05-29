package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.service.FormulaUtils;
import com.odsProject.odsProject.service.interfaces.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * EvaluationController — Endpoints públicos del motor de evaluación.
 *
 * Permite al frontend:
 *   1. Hacer dry-run de una fórmula con valores hipotéticos (preview)
 *   2. Validar la sintaxis de una fórmula contra una lista de variables declaradas
 *   3. Extraer las variables que aparecen en una fórmula (helper para el modal)
 *
 * Sin DTOs: usa Map&lt;String, Object&gt; como contrato de entrada/salida; los POJOs JOOQ
 * se manejan en los servicios por-ODS, no acá. Este controller es 100% transversal.
 */
@RestController
@RequestMapping("/api/evaluacion")
public class EvaluationController {

    @Autowired
    private IEvaluationService evaluationService;

    /**
     * POST /api/evaluacion/preview
     *
     * Body: { "formula": "(p1+p2)/p3", "parametros": {"p1": 10, "p2": 20, "p3": 5}, "metaValor": 6 }
     *
     * Devuelve: { "valor": 6.0000, "metaAlcanzada": true, "formula": ..., "parametros": ... }
     */
    @PostMapping("/preview")
    public ResponseEntity<Map<String, Object>> preview(@RequestBody Map<String, Object> body) {
        String formula = String.valueOf(body.getOrDefault("formula", ""));
        Object parametrosRaw = body.get("parametros");
        Object metaRaw = body.get("metaValor");

        Map<String, BigDecimal> params = toBigDecimalMap(parametrosRaw);
        BigDecimal meta = toBigDecimal(metaRaw);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("formula", formula);
        response.put("parametros", params);

        try {
            BigDecimal valor = evaluationService.evaluateFormula(formula, params);
            response.put("valor", valor);
            response.put("metaValor", meta);
            response.put("metaAlcanzada", meta != null && evaluationService.metaAlcanzada(valor, meta));
            response.put("ok", true);
        } catch (IllegalArgumentException e) {
            response.put("ok", false);
            response.put("error", e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/evaluacion/validar-formula
     *
     * Body: { "formula": "(p1+p2)/p3", "variablesDeclaradas": ["p1","p2","p3"] }
     *
     * Devuelve: { "valida": true|false, "variablesEnFormula": [...], "faltantes": [...], "sobrantes": [...] }
     */
    @PostMapping("/validar-formula")
    public ResponseEntity<Map<String, Object>> validarFormula(@RequestBody Map<String, Object> body) {
        String formula = String.valueOf(body.getOrDefault("formula", ""));
        Object declaradasRaw = body.get("variablesDeclaradas");

        Set<String> declaradas = toStringSet(declaradasRaw);
        Set<String> enFormula  = FormulaUtils.extractVariables(formula);
        Set<String> faltantes  = FormulaUtils.missingVariables(formula, declaradas);
        Set<String> sobrantes  = FormulaUtils.unusedVariables(formula, declaradas);
        boolean syntaxOk       = evaluationService.validateFormula(formula, declaradas);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("formula", formula);
        response.put("variablesEnFormula", enFormula);
        response.put("variablesDeclaradas", declaradas);
        response.put("faltantes", faltantes);
        response.put("sobrantes", sobrantes);
        response.put("sintaxisValida", syntaxOk);
        response.put("valida", syntaxOk && faltantes.isEmpty());
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/evaluacion/variables?formula=...
     *
     * Devuelve: { "formula": "...", "variables": ["p1","p2"] }
     * Útil cuando el front quiere sembrar parámetros automáticamente desde la fórmula.
     */
    @GetMapping("/variables")
    public ResponseEntity<Map<String, Object>> variables(@RequestParam String formula) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("formula", formula);
        response.put("variables", FormulaUtils.extractVariables(formula));
        return ResponseEntity.ok(response);
    }

    // ── Helpers de conversión ──────────────────────────────────────────────

    private Map<String, BigDecimal> toBigDecimalMap(Object raw) {
        Map<String, BigDecimal> result = new HashMap<>();
        if (raw instanceof Map<?, ?> m) {
            for (Map.Entry<?, ?> e : m.entrySet()) {
                String key = String.valueOf(e.getKey());
                BigDecimal value = toBigDecimal(e.getValue());
                if (value != null) result.put(key, value);
            }
        }
        return result;
    }

    private BigDecimal toBigDecimal(Object value) {
        if (value == null) return null;
        if (value instanceof BigDecimal bd) return bd;
        if (value instanceof Number n) return BigDecimal.valueOf(n.doubleValue());
        try {
            return new BigDecimal(String.valueOf(value));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Set<String> toStringSet(Object raw) {
        Set<String> out = new java.util.LinkedHashSet<>();
        if (raw instanceof Iterable<?> it) {
            for (Object o : it) {
                if (o != null) out.add(String.valueOf(o));
            }
        }
        return out;
    }
}
