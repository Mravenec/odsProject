import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 06 — Service → Hook → Page */
export const useObjetivo06 = (proyectoId, options) => useObjetivoByNumber(6, proyectoId, options);
export default useObjetivo06;
