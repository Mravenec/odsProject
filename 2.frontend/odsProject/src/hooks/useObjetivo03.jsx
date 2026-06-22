import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 03 — Service → Hook → Page */
export const useObjetivo03 = (proyectoId, options) => useObjetivoByNumber(3, proyectoId, options);
export default useObjetivo03;
