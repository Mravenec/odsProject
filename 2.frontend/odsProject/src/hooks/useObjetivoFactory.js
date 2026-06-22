import { useState, useEffect, useCallback, useMemo } from 'react';
import { getObjetivoService } from './objetivoServicesMap';

const DEFAULT_STATS = {
  totalProyectos: 0,
  totalIndicadores: 0,
  indicadoresConDatos: 0,
};

/**
 * Hook genérico: Service → Hook → Page (un ODS + proyectoId).
 * Expone estado reactivo y delega escritura al service subyacente.
 */
export function useObjetivoByNumber(odsId, proyectoId, { autoFetch = true } = {}) {
  const service = useMemo(() => getObjetivoService(odsId), [odsId]);
  const [indicators, setIndicators] = useState({});
  const [stats, setStats] = useState(DEFAULT_STATS);
  const [dashboard, setDashboard] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async () => {
    if (!service || proyectoId == null || proyectoId === '') return;
    setLoading(true);
    setError(null);
    try {
      const [indicatorsData, statsData] = await Promise.all([
        service.getIndicators(proyectoId),
        service.getStatistics?.() ?? Promise.resolve({}),
      ]);
      setIndicators(indicatorsData || {});
      setStats((prev) => ({ ...prev, ...(statsData || {}) }));
    } catch (err) {
      console.error(`[useObjetivo ${odsId}]`, err);
      setError(err.message || `Error al cargar ODS ${odsId}`);
    } finally {
      setLoading(false);
    }
  }, [service, odsId, proyectoId]);

  const fetchDashboard = useCallback(async () => {
    if (!service?.getDashboard) return { success: false, error: 'Sin dashboard' };
    try {
      const res = await service.getDashboard();
      if (res.success) setDashboard(res.data);
      return res;
    } catch (err) {
      return { success: false, error: err.message };
    }
  }, [service]);

  useEffect(() => {
    if (autoFetch) fetchData();
  }, [autoFetch, fetchData]);

  const actions = useMemo(() => {
    if (!service) return {};
    return {
      getIndicators: (pid) => service.getIndicators(pid ?? proyectoId),
      getStatistics: () => service.getStatistics?.(),
      getDashboard: () => service.getDashboard?.(),
      saveIndicator: (data) => service.saveIndicator?.(data),
      saveParameter: (data) => service.saveParameter?.(data),
      getMetasProyecto: (pid) => service.getMetasProyecto?.(pid ?? proyectoId),
      getMediciones: (proyectoIndicadorId) => service.getMediciones?.(proyectoIndicadorId),
      createMedicion: (data) => service.createMedicion?.(data),
      createMedicionAuditada: (data) => service.createMedicionAuditada?.(data),
      getMedicionAuditoria: (medicionId) => service.getMedicionAuditoria?.(medicionId),
    };
  }, [service, proyectoId]);

  return {
    odsId: Number(odsId),
    service,
    indicators,
    stats,
    dashboard,
    loading,
    error,
    refetch: fetchData,
    fetchDashboard,
    ...actions,
  };
}

export default useObjetivoByNumber;
