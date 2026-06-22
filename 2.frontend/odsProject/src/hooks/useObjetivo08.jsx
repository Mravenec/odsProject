import { useObjetivoByNumber } from './useObjetivoFactory';

/** Hook ODS 08 — Service → Hook → Page */
export const useObjetivo08 = (proyectoId, options) => useObjetivoByNumber(8, proyectoId, options);
export default useObjetivo08;
