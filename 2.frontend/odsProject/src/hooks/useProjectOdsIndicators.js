import { useState, useCallback, useEffect } from 'react';
import { getObjetivoService } from './objetivoServicesMap';

const RESERVED = new Set(['sqrt', 'sin', 'cos', 'tan', 'log', 'exp', 'round', 'floor', 'ceil', 'abs', 'pi', 'e', 'valor', 'count']);

const extractVars = (formula) => {
  if (!formula) return new Set();
  return new Set((String(formula).match(/[a-zA-Z_][a-zA-Z0-9_]*/g) || [])
    .filter((v) => !RESERVED.has(v.toLowerCase())));
};

const enrichIndicatorsForProject = async (odsNum, pid) => {
  const svc = getObjetivoService(odsNum);
  if (!svc) return null;

  const data = await (svc.getIndicators ? svc.getIndicators(pid) : svc.getAllIndicators?.(pid));
  if (!data || Object.keys(data).length === 0) return null;

  const metasRes = svc.getMetasProyecto ? await svc.getMetasProyecto(pid) : { data: [] };
  const metas = metasRes.data || [];
  const list = Object.values(data).filter((i) => i && i.proyectoId);

  const enriched = list.map((ind) => {
    const vars = extractVars(ind.formula || ind.formulaCustom);
    const matchingParams = metas.filter((m) => {
      const varName = m.nombreVariable || m.nombre_variable || m.nombreParametro || m.nombre_parametro;
      return vars.has(varName);
    });
    const pId = matchingParams.length > 0
      ? (matchingParams[0].proyectoIndicadorId ?? matchingParams[0].proyecto_indicador_id)
      : undefined;
    return {
      ...ind,
      id: pId,
      indicadorCodigo: ind.indicadorCodigo || ind.codigo || ind.code,
      indicadorNombre: ind.indicadorNombre || ind.nombre || ind.name,
      indicadorMasterId: ind.indicadorMasterId || ind.masterId,
      formulaCustom: ind.formulaCustom || ind.formula,
      formulaDefault: ind.formulaDefault || 'valor',
      metaValor: ind.metaValor || ind.targetValue || 0,
      metaUnidad: ind.metaUnidad || ind.unit || 'Porcentaje',
      metaNombre: ind.metaNombre || '',
      estadoIndicador: ind.estadoIndicador || 'SIN DATOS',
      porcentajeLogro: ind.porcentajeLogro || 0,
      parametros: matchingParams.map((p) => ({
        id: p.id ?? p.ID,
        nombreParametro: p.nombreParametro ?? p.nombre_parametro,
        nombreVariable: p.nombreVariable ?? p.nombre_variable,
        tipoDato: p.tipoDato ?? p.tipo_dato,
        valorActual: p.valorActual ?? p.valor_actual,
      })),
    };
  });

  return enriched.length > 0 ? enriched : null;
};

/**
 * Carga indicadores enriquecidos de varios ODS para un proyecto (evaluación / resultados).
 */
export function useProjectOdsIndicators(projectId) {
  const [allIndicators, setAllIndicators] = useState({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const loadAllIndicators = useCallback(async (pid, odsIds = []) => {
    const result = {};
    const targets = (Array.isArray(odsIds) && odsIds.length > 0)
      ? odsIds
      : Array.from({ length: 17 }, (_, i) => i + 1);

    for (const n of targets) {
      try {
        const enriched = await enrichIndicatorsForProject(n, pid);
        if (enriched) result[n] = enriched;
      } catch (e) {
        console.error('[useProjectOdsIndicators] ODS', n, e);
      }
    }
    return result;
  }, []);

  const load = useCallback(async (odsIds = []) => {
    if (!projectId) return {};
    setLoading(true);
    setError(null);
    try {
      const data = await loadAllIndicators(parseInt(projectId, 10), odsIds);
      setAllIndicators(data);
      return data;
    } catch (e) {
      setError(e.message);
      return {};
    } finally {
      setLoading(false);
    }
  }, [projectId, loadAllIndicators]);

  const getService = useCallback((odsNum) => getObjetivoService(odsNum), []);

  return {
    allIndicators,
    setAllIndicators,
    loading,
    error,
    load,
    loadAllIndicators,
    getService,
  };
}

export default useProjectOdsIndicators;
