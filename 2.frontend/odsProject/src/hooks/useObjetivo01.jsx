import { useState, useEffect, useCallback } from 'react';
import { objetivo01Service } from '../services/objetivo01Service';

/**
 * Smart Hook para ODS 01: Fin de la Pobreza
 * @param {string|number} proyectoId - ID del proyecto a cargar
 * @returns {Object} Variables reactivas con indicadores, estadísticas y estado de carga
 */
export const useObjetivo01 = (proyectoId) => {
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
        objetivo01Service.getIndicators(proyectoId),
        objetivo01Service.getStatistics()
      ]);
      
      setIndicators(indicatorsData);
      setStats(prev => ({
        ...prev,
        ...statsData
      }));
    } catch (err) {
      console.error('[useObjetivo01] Error loading data:', err);
      setError(err.message || 'Error al cargar datos del ODS 01');
    } finally {
      setLoading(false);
    }
  }, [proyectoId]);

  const fetchDashboard = useCallback(async () => {
    try {
      const res = await objetivo01Service.getDashboard();
      if (res.success) setDashboard(res.data);
      return res;
    } catch (err) {
      console.error('[useObjetivo01] Error fetching dashboard:', err);
      return { success: false, error: err.message };
    }
  }, []);

  // Auto-fetch al cambiar el proyectoId
  useEffect(() => {
    fetchData();
  }, [fetchData]);

  return {
    // Variables reactivas (Data)
    indicators,
    stats,
    dashboard,
    
    // Estado (Loading/Error)
    loading,
    error,
    
    // Acciones
    refetch: fetchData,
    fetchDashboard
  };
};
