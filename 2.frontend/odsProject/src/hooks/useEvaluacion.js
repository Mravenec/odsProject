import { useCallback } from 'react';
import { evaluacionService } from '../services/evaluacionService';

/**
 * Hook sobre evaluacionService (preview / validación de fórmulas).
 */
export function useEvaluacion() {
  const preview = useCallback(
    (formula, parametros, metaValor) => evaluacionService.preview(formula, parametros, metaValor),
    []
  );

  const validarFormula = useCallback(
    (formula, declaradas) => evaluacionService.validarFormula(formula, declaradas),
    []
  );

  const extraerVariables = useCallback(
    (formula) => evaluacionService.extraerVariables(formula),
    []
  );

  return { preview, validarFormula, extraerVariables };
}

export default useEvaluacion;
