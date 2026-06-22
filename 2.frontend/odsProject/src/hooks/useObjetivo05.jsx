import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 05 — Service → Hook → Page */
export const useObjetivo05 = (proyectoId, options) => useObjetivoByNumber(5, proyectoId, options);
export default useObjetivo05;
