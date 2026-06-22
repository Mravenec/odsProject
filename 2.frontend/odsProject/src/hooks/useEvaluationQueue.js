import { useState, useEffect, useCallback } from 'react';
import { projectService } from '../services/projectService';
import { documentService } from '../services/documentService';

/**
 * Cola de evaluación — Service → Hook → EvaluationQueuePage.
 */
export function useEvaluationQueue() {
  const [rows, setRows] = useState([]);
  const [metrics, setMetrics] = useState({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const load = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const [projRes, metricsRes] = await Promise.all([
        projectService.getAllProjects(),
        projectService.getEvaluationMetrics(),
      ]);

      const projects = projRes.data || [];
      const enriched = await Promise.all(projects.map(async (p) => {
        let docs = [];
        try {
          const d = await documentService.listByProject(p.id);
          docs = d.success ? d.data : [];
        } catch { /* ignore */ }
        return { ...p, docCount: docs.length, hasDocs: docs.length > 0 };
      }));
      setRows(enriched);
      setMetrics(metricsRes.data || {});
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    load();
  }, [load]);

  return { rows, metrics, loading, error, reload: load };
}

export default useEvaluationQueue;
