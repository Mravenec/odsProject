import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 15 — Service → Hook → Page */
export const useObjetivo15 = (proyectoId, options) => useObjetivoByNumber(15, proyectoId, options);
export default useObjetivo15;
