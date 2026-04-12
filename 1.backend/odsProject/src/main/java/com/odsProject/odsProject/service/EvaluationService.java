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
            if (parametros != null) {
                Set<String> variables = parametros.keySet();
                if (!variables.isEmpty()) {
                    builder.variables(variables);
                }
            }

            Expression expression = builder.build();

            // Mapear los valores de los parámetros a double (formato exp4j)
            if (parametros != null) {
                for (Map.Entry<String, BigDecimal> entry : parametros.entrySet()) {
                    if (entry.getValue() != null) {
                        expression.setVariable(entry.getKey(), entry.getValue().doubleValue());
                    } else {
                        expression.setVariable(entry.getKey(), 0.0);
                    }
                }
            }

            double result = expression.evaluate();
            
            // Validar resultado (NaN o Infinity)
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                return BigDecimal.ZERO;
            }

            // Retornar resultado con escala de 4 decimales
            return BigDecimal.valueOf(result).setScale(4, RoundingMode.HALF_UP);

        } catch (Exception e) {
            // En caso de error en la fórmula o parámetros faltantes
            throw new IllegalArgumentException("Error evaluando la fórmula '" + formula + "': " + e.getMessage(), e);
        }
    }

    @Override
    public boolean validateFormula(String formula) {
        if (formula == null || formula.trim().isEmpty()) {
            return false;
        }

        try {
            // Simplificado: intentamos construir la expresión con algunos parámetros dummy si es necesario
            // o simplemente validamos la sintaxis básica
            new ExpressionBuilder(formula.replace(',', '.')).build();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
