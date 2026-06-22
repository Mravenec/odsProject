import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 01 — Service → Hook → Page */
export const useObjetivo01 = (proyectoId, options) => useObjetivoByNumber(1, proyectoId, options);
export default useObjetivo01;
