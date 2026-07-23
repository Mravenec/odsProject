package com.odsProject.odsProject.service;

import com.odsProject.odsProject.service.interfaces.IEvaluationService;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

/**
 * Servicio de Evaluación de Fórmulas Dinámicas
 * Implementa el motor de cálculo basado en exp4j para todo el ecosistema ODS.
 *
 * Este servicio es transversal y permite la evaluación de expresiones matemáticas
 * definidas dinámicamente en el catálogo de indicadores.
 *
 * Reglas de diseño:
 *   - Operadores de producto: + − * / ^ (potencia) y función sqrt (raíz), más
 *     paréntesis y variables por nombreVariable. Misma API en preview, validar-formula
 *     y evaluación de mediciones a futuro (un solo motor exp4j).
 *   - Todas las variables se declaran en exp4j ANTES de evaluar (evita errores de
 *     "Unknown variable").
 *   - El resultado se trunca a 4 decimales con HALF_UP (mismo patrón que la columna
 *     DECIMAL(15,4) en la BD).
 *   - NaN / Infinity se devuelven como BigDecimal.ZERO para evitar romper el flujo
 *     de mediciones (ej. división entre cero al inicio de un proyecto).
 */
@Service
public class EvaluationService implements IEvaluationService {

    @Override
    public BigDecimal evaluateFormula(String formula, Map<String, BigDecimal> parametros) {
        if (formula == null || formula.trim().isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            // Reemplazar comas por puntos en la fórmula (por seguridad regional)
            String sanitizedFormula = formula.replace(',', '.');

            ExpressionBuilder builder = new ExpressionBuilder(sanitizedFormula);

            // Registrar las variables que se usarán en la fórmula
            if (parametros != null && !parametros.isEmpty()) {
                builder.variables(parametros.keySet());
            }

            Expression expression = builder.build();

            // Mapear los valores de los parámetros a double (formato exp4j)
            if (parametros != null) {
                for (Map.Entry<String, BigDecimal> entry : parametros.entrySet()) {
                    BigDecimal value = entry.getValue();
                    expression.setVariable(entry.getKey(),
                        value != null ? value.doubleValue() : 0.0);
                }
            }

            double result = expression.evaluate();

            // Validar resultado (NaN o Infinity)
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                return BigDecimal.ZERO;
            }

            // Retornar resultado con escala de 4 decimales (igual a la columna DECIMAL(15,4))
            return BigDecimal.valueOf(result).setScale(4, RoundingMode.HALF_UP);

        } catch (ArithmeticException e) {
            // Manejo específico para división por cero u otros errores aritméticos
            return BigDecimal.ZERO;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                "Error evaluando la fórmula '" + formula + "': " + e.getMessage(), e);
        }
    }

    @Override
    public boolean validateFormula(String formula) {
        // Validación SIN declarar variables: solo sirve para fórmulas literales
        // como "100" o "valor". Para fórmulas con variables, usar la sobrecarga.
        if (formula == null || formula.trim().isEmpty()) return false;

        // Auto-extraer variables presentes en la fórmula y delegar a la sobrecarga
        Set<String> variables = FormulaUtils.extractVariables(formula);
        return validateFormula(formula, variables);
    }

    @Override
    public boolean validateFormula(String formula, Set<String> variables) {
        if (formula == null || formula.trim().isEmpty()) return false;
        try {
            String sanitized = formula.replace(',', '.');
            
            // Verificación extra: evitar operadores dobles que suelen ser errores de digitación
            // (exp4j a veces los acepta como operadores unarios, pero el negocio los prefiere inválidos)
            if (sanitized.contains("++") || sanitized.contains("**") || sanitized.contains("//")) {
                return false;
            }

            ExpressionBuilder builder = new ExpressionBuilder(sanitized);
            if (variables != null && !variables.isEmpty()) {
                builder.variables(variables);
            }
            Expression expression = builder.build();
            // exp4j .validate() reporta variables no asignadas; aquí solo nos importa
            // que la sintaxis y los identificadores sean coherentes con el set declarado.
            return expression.validate(false).isValid();
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean metaAlcanzada(BigDecimal valor, BigDecimal meta) {
        if (valor == null || meta == null) return false;
        return valor.compareTo(meta) >= 0;
    }
}
