import { useState, useEffect, useCallback } from 'react';
import { objetivo03Service } from '../services/objetivo03Service';

/**
 * Smart Hook para ODS 03: Salud y Bienestar
 * @param {string|number} proyectoId - ID del proyecto a cargar
 * @returns {Object} Variables reactivas con indicadores, estadísticas y estado de carga
 */
export const useObjetivo03 = (proyectoId) => {
  const [indicators, setIndicators] = useState({});
  const [stats, setStats] = useState({ totalProyectos: 0, totalIndicadores: 0 });
  const [dashboard, setDashboard] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async () => {
    if (!proyectoId) return;
    
    setLoading(true);
    setError(null);
    
    try {
      const [indicatorsData, statsData] = await Promise.all([
        objetivo03Service.getIndicators(proyectoId),
        objetivo03Service.getStatistics()
      ]);
      
      setIndicators(indicatorsData);
      setStats(prev => ({
        ...prev,
        ...statsData
      }));
    } catch (err) {
      console.error('[useObjetivo03] Error loading data:', err);
      setError(err.message || 'Error al cargar datos del ODS 03');
    } finally {
      setLoading(false);
    }
  }, [proyectoId]);

  const fetchDashboard = useCallback(async () => {
    try {
      const res = await objetivo03Service.getDashboard();
      if (res.success) setDashboard(res.data);
      return res;
    } catch (err) {
      console.error('[useObjetivo03] Error fetching dashboard:', err);
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
