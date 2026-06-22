import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 02 — Service → Hook → Page */
export const useObjetivo02 = (proyectoId, options) => useObjetivoByNumber(2, proyectoId, options);
export default useObjetivo02;
