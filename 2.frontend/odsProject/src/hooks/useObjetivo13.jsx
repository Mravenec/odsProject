import { useState, useEffect, useCallback } from 'react';
import { objetivo13Service } from '../services/objetivo13Service';

/**
 * Smart Hook para ODS 13: Acción por el Clima
 * @param {string|number} proyectoId - ID del proyecto a cargar
 * @returns {Object} Variables reactivas con indicadores, estadísticas y estado de carga
 */
export const useObjetivo13 = (proyectoId) => {
  const [indicators, setIndicators] = useState({});
  const [stats, setStats] = useState({
    totalProyectos: 0,
    totalIndicadores: 0,
    indicadoresConDatos: 0
  });
  const [dashboard, setDashboard] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async () => {
    if (!proyectoId) return;
    
    setLoading(true);
    setError(null);
    
    try {
      const [indicatorsData, statsData] = await Promise.all([
        objetivo13Service.getIndicators(proyectoId),
        objetivo13Service.getStatistics()
      ]);
      
      setIndicators(indicatorsData);
      setStats(prev => ({
        ...prev,
        ...statsData
      }));
    } catch (err) {
      console.error('[useObjetivo13] Error loading data:', err);
      setError(err.message || 'Error al cargar datos del ODS 13');
    } finally {
      setLoading(false);
    }
  }, [proyectoId]);

  const fetchDashboard = useCallback(async () => {
    try {
      const res = await objetivo13Service.getDashboard();
      if (res.success) setDashboard(res.data);
      return res;
    } catch (err) {
      console.error('[useObjetivo13] Error fetching dashboard:', err);
      return { success: false, error: err.message };
    }
  }, []);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  return {
    indicators,
    stats,
    dashboard,
    loading,
    error,
    refetch: fetchData,
    fetchDashboard
  };
};
