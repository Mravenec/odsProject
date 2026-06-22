import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 10 — Service → Hook → Page */
export const useObjetivo10 = (proyectoId, options) => useObjetivoByNumber(10, proyectoId, options);
export default useObjetivo10;
