import { useState, useCallback, useEffect } from 'react';
import { projectService } from '../services/projectService';
import { documentService } from '../services/documentService';
import { getObjetivoService } from './objetivoServicesMap';
import { deriveEstado } from '../components/AchievementBadge';
import { useSilentPoll } from './useSilentPoll';

/**
 * Detalle de resultados por proyecto — Service → Hook → ProjectResultsPage.
 */
export function useProjectResultsDetail(projectId) {
  const [project, setProject] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [hasEvidenceDocs, setHasEvidenceDocs] = useState(false);

  const fetchProjectFull = useCallback(async (opts = {}) => {
    const { silent = false } = opts;
    if (!projectId) return;
    if (!silent) setLoading(true);
    setError('');
    try {
      const headerRes = await projectService.getProjectById(projectId);
      if (!headerRes.success || !headerRes.data) {
        setError('Proyecto no encontrado');
        setProject(null);
        return;
      }
      const currentProject = headerRes.data;

      const odsRes = await projectService.getOdsByProyecto(projectId);
      const linkedOdsRaw = odsRes.success ? odsRes.data : [];

      const linkedOds = (await Promise.all(
        linkedOdsRaw.map(async (odsLink) => {
          const odsId = parseInt(odsLink.ods_id ?? odsLink.odsId, 10);
          if (!odsId || Number.isNaN(odsId)) return null;
          const svc = getObjetivoService(odsId);
          if (!svc?.getIndicators) return null;

          const indicatorsMap = await svc.getIndicators(parseInt(projectId, 10));
          const indicators = Object.values(indicatorsMap)
            .filter((ind) => ind && (
              (ind.formula && ind.formula.trim().length > 0)
              || (typeof ind.targetValue === 'number' && ind.targetValue > 0)
              || ind.currentValue != null || ind.hasData
            ))
            .map((ind) => ({
              ...ind,
              porcentajeLogro: (ind.currentValue != null && ind.targetValue > 0)
                ? Math.min((Number(ind.currentValue) / Number(ind.targetValue)) * 100, 200)
                : null,
              estado: ind.estadoIndicador
                || (ind.currentValue != null && ind.targetValue > 0
                  ? deriveEstado((Number(ind.currentValue) / Number(ind.targetValue)) * 100)
                  : 'SIN DATOS'),
            }))
            .sort((a, b) => String(a.code).localeCompare(String(b.code)));

          let parameters = [];
          if (svc.getMetasProyecto) {
            try {
              const mp = await svc.getMetasProyecto(parseInt(projectId, 10));
              parameters = mp?.data || mp || [];
            } catch { /* ignore */ }
          }

          return {
            odsId,
            esPrimario: !!(odsLink.es_primario ?? odsLink.esPrimario),
            indicators,
            parameters,
          };
        })
      )).filter(Boolean);

      linkedOds.sort((a, b) => {
        if (a.esPrimario && !b.esPrimario) return -1;
        if (!a.esPrimario && b.esPrimario) return 1;
        return a.odsId - b.odsId;
      });

      const allInds = linkedOds.flatMap((o) => o.indicators);
      const auditados = allInds.filter((i) => i.porcentajeLogro != null);
      const pctProyecto = auditados.length > 0
        ? auditados.reduce((s, i) => s + i.porcentajeLogro, 0) / auditados.length
        : null;

      const docsRes = await documentService.listByProject(projectId);
      setHasEvidenceDocs((docsRes.data || []).length > 0);

      setProject({
        ...currentProject,
        linkedOds,
        objective: linkedOds.find((o) => o.esPrimario)?.odsId ?? linkedOds[0]?.odsId ?? currentProject.objective,
        pctProyecto,
        totalIndicadores: allInds.length,
        auditados: auditados.length,
      });
    } catch (err) {
      console.error('[useProjectResultsDetail]', err);
      setError(err.message || 'Error cargando proyecto');
      setProject(null);
    } finally {
      if (!silent) setLoading(false);
    }
  }, [projectId]);

  const sendForEvaluation = useCallback(async (userId) => {
    return projectService.sendForEvaluation(projectId, userId);
  }, [projectId]);

  const refreshEvidenceStatus = useCallback(async () => {
    if (!projectId) return;
    const r = await documentService.listByProject(projectId);
    setHasEvidenceDocs((r.data || []).length > 0);
  }, [projectId]);

  useSilentPoll(() => fetchProjectFull({ silent: true }), 8000, !!projectId);

  return {
    project,
    loading,
    error,
    hasEvidenceDocs,
    fetchProjectFull,
    sendForEvaluation,
    refreshEvidenceStatus,
  };
}

export default useProjectResultsDetail;
