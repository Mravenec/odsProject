import { useState, useEffect, useCallback } from 'react';
import { objetivo07Service } from '../services/objetivo07Service';

/**
 * Smart Hook para ODS 07: Energía Asequible y No Contaminante
 * @param {string|number} proyectoId - ID del proyecto a cargar
 * @returns {Object} Variables reactivas con indicadores, estadísticas y estado de carga
 */
export const useObjetivo07 = (proyectoId) => {
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
        objetivo07Service.getIndicators(proyectoId),
        objetivo07Service.getStatistics()
      ]);
      
      setIndicators(indicatorsData);
      setStats(prev => ({
        ...prev,
        ...statsData
      }));
    } catch (err) {
      console.error('[useObjetivo07] Error loading data:', err);
      setError(err.message || 'Error al cargar datos del ODS 07');
    } finally {
      setLoading(false);
    }
  }, [proyectoId]);

  const fetchDashboard = useCallback(async () => {
    try {
      const res = await objetivo07Service.getDashboard();
      if (res.success) setDashboard(res.data);
      return res;
    } catch (err) {
      console.error('[useObjetivo07] Error fetching dashboard:', err);
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
