import { useState, useEffect, useCallback, useRef } from 'react';
import { catalogService } from '../services/catalogService';

export const useCatalog = () => {
  const [odsList, setOdsList] = useState([]);
  const [indicadoresByOds, setIndicadoresByOds] = useState({});
  const [loading, setLoading] = useState(true);
  const cache = useRef({});

  useEffect(() => {
    catalogService.getOdsList()
      .then(data => setOdsList(data))
      .finally(() => setLoading(false));
  }, []);

  const loadIndicadores = useCallback(async (odsId) => {
    if (cache.current[odsId]) return cache.current[odsId];
    const data = await catalogService.getIndicadoresByOds(odsId);
    cache.current[odsId] = data;
    setIndicadoresByOds(prev => ({ ...prev, [odsId]: data }));
    return data;
  }, []);

  const getParametros = useCallback(
    (indicadorId) => catalogService.getParametrosMaster(indicadorId),
    []
  );

  return { odsList, indicadoresByOds, loading, loadIndicadores, getParametros };
};
