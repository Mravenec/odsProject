import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 09 — Service → Hook → Page */
export const useObjetivo09 = (proyectoId, options) => useObjetivoByNumber(9, proyectoId, options);
export default useObjetivo09;
