import api from './api';

/**
 * evaluacionService — Cliente del motor de evaluación del backend.
 *
 * Centraliza las llamadas a los endpoints transversales /api/evaluacion/*:
 *   - preview(formula, parametros, metaValor) → ejecuta la fórmula en el server
 *   - validarFormula(formula, declaradas)     → valida sintaxis + variables
 *   - extraerVariables(formula)               → lista de variables presentes
 *
 * Esta es la fuente de verdad. El motor JS local de utils/evaluationEngine.js
 * queda como fallback offline; siempre que haya backend, ESTE servicio gana.
 */
export const evaluacionService = {

  /**
   * Ejecuta la fórmula en el backend con los valores indicados y devuelve
   * { ok, valor, metaAlcanzada, metaValor, formula, parametros, error? }
   */
  async preview(formula, parametros, metaValor) {
    try {
      const response = await api.post('/evaluacion/preview', {
        formula,
        parametros: parametros || {},
        metaValor: metaValor != null ? metaValor : null
      });
      return response.data;
    } catch (error) {
      return {
        ok: false,
        error: error.response?.data?.error || error.message,
        valor: 0,
        metaAlcanzada: false
      };
    }
  },

  /**
   * Valida sintaxis + coherencia de variables. Devuelve:
   *   { valida, sintaxisValida, variablesEnFormula, faltantes, sobrantes }
   *
   * - valida = sintaxisValida && faltantes.length === 0
   */
  async validarFormula(formula, variablesDeclaradas) {
    try {
      const response = await api.post('/evaluacion/validar-formula', {
        formula,
        variablesDeclaradas: variablesDeclaradas || []
      });
      return response.data;
    } catch (error) {
      return {
        valida: false,
        sintaxisValida: false,
        variablesEnFormula: [],
        faltantes: [],
        sobrantes: [],
        error: error.response?.data?.error || error.message
      };
    }
  },

  /**
   * Extrae las variables de una fórmula. Útil para sembrar parámetros
   * automáticamente desde la UI antes de guardar.
   */
  async extraerVariables(formula) {
    try {
      const response = await api.get('/evaluacion/variables', {
        params: { formula }
      });
      return response.data?.variables || [];
    } catch (error) {
      return [];
    }
  }
};
