import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 13 — Service → Hook → Page */
export const useObjetivo13 = (proyectoId, options) => useObjetivoByNumber(13, proyectoId, options);
export default useObjetivo13;
