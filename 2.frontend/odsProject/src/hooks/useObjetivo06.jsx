import { useState, useEffect, useCallback } from 'react';
import { objetivo06Service } from '../services/objetivo06Service';

/**
 * Smart Hook para ODS 06: Agua Limpia y Saneamiento
 * @param {string|number} proyectoId - ID del proyecto a cargar
 * @returns {Object} Variables reactivas con indicadores, estadísticas y estado de carga
 */
export const useObjetivo06 = (proyectoId) => {
  const [indicators, setIndicators] = useState({});
  const [stats, setStats] = useState({
    totalProyectos: 0,
    totalUsuarios: 0,
    progresoGlobal: 0
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchData = useCallback(async () => {
    if (!proyectoId) return;
    
    setLoading(true);
    setError(null);
    
    try {
      const [indicatorsData, statsData] = await Promise.all([
        objetivo06Service.getIndicators(proyectoId),
        objetivo06Service.getStatistics()
      ]);
      
      setIndicators(indicatorsData);
      setStats(prev => ({
        ...prev,
        ...statsData
      }));
    } catch (err) {
      console.error('[useObjetivo06] Error loading data:', err);
      setError(err.message || 'Error al cargar datos del ODS 06');
    } finally {
      setLoading(false);
    }
  }, [proyectoId]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  return {
    indicators,
    stats,
    loading,
    error,
    refetch: fetchData
  };
};
