import { useState, useCallback } from 'react';
import { projectService } from '../services/projectService';
import { useProjectOdsIndicators } from './useProjectOdsIndicators';

/**
 * Hook de evaluación de proyecto: cabecera + ODS vinculados + indicadores.
 * Service → Hook → EvaluationPage.
 */
export function useProjectEvaluation(projectId) {
  const [project, setProject] = useState(null);
  const [loadingPage, setLoadingPage] = useState(true);
  const [auditClosing, setAuditClosing] = useState(false);
  const [auditError, setAuditError] = useState(null);

  const {
    allIndicators,
    setAllIndicators,
    load: loadIndicators,
    getService,
  } = useProjectOdsIndicators(projectId);

  const load = useCallback(async () => {
    if (!projectId) return;
    setLoadingPage(true);
    try {
      const projRes = await projectService.getProjectById(parseInt(projectId, 10));
      const pData = projRes.data || projRes;
      setProject(pData);

      let odsToLoad = pData.odsVinculados || [];
      if (odsToLoad.length === 0) {
        const links = await projectService.getOdsByProyecto(parseInt(projectId, 10));
        odsToLoad = (links.data || []).map((l) => l.odsId || l.ods_id);
      }
      return await loadIndicators(odsToLoad);
    } catch (e) {
      console.error('[useProjectEvaluation]', e);
    } finally {
      setLoadingPage(false);
    }
  }, [projectId, loadIndicators]);

  const approveEvaluation = useCallback(async (userId, userRole, observaciones) => {
    setAuditClosing(true);
    setAuditError(null);
    const r = await projectService.approveEvaluation(
      parseInt(projectId, 10), userId, userRole, observaciones
    );
    setAuditClosing(false);
    if (!r.success) setAuditError(r.error);
    return r;
  }, [projectId]);

  const rejectEvaluation = useCallback(async (userId, userRole, motivo) => {
    setAuditClosing(true);
    setAuditError(null);
    const r = await projectService.rejectEvaluation(
      parseInt(projectId, 10), userId, userRole, motivo
    );
    setAuditClosing(false);
    if (!r.success) setAuditError(r.error);
    return r;
  }, [projectId]);

  return {
    project,
    allIndicators,
    setAllIndicators,
    loadingPage,
    auditClosing,
    auditError,
    setAuditError,
    load,
    getService,
    approveEvaluation,
    rejectEvaluation,
  };
}

export default useProjectEvaluation;
