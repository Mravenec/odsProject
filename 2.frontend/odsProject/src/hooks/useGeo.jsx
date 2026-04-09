import { useState, useCallback } from 'react';
import { geoService } from '../services/geoService';

/**
 * Custom hook to manage geographic data (Provinces, Cantons, Districts)
 * from Costa Rica API.
 */
export const useGeo = () => {
  const [provincias, setProvincias] = useState([]);
  const [cantones, setCantones] = useState([]);
  const [distritos, setDistritos] = useState([]);
  const [loadingGeo, setLoadingGeo] = useState(false);
  const [errorGeo, setErrorGeo] = useState(null);

  /**
   * Fetch all provinces
   */
  const fetchProvincias = useCallback(async () => {
    setLoadingGeo(true);
    setErrorGeo(null);
    try {
      const data = await geoService.getProvincias();
      setProvincias(Array.isArray(data) ? data : []);
      return data;
    } catch (err) {
      setErrorGeo(err.message);
      return [];
    } finally {
      setLoadingGeo(false);
    }
  }, []);

  /**
   * Fetch cantons for a specific province
   */
  const fetchCantones = useCallback(async (provinciaId) => {
    if (!provinciaId) {
      setCantones([]);
      setDistritos([]);
      return [];
    }
    setLoadingGeo(true);
    setErrorGeo(null);
    try {
      const data = await geoService.getCantones(provinciaId);
      setCantones(Array.isArray(data) ? data : []);
      setDistritos([]); // Reset districts when province changes
      return data;
    } catch (err) {
      setErrorGeo(err.message);
      return [];
    } finally {
      setLoadingGeo(false);
    }
  }, []);

  /**
   * Fetch districts for a specific canton
   */
  const fetchDistritos = useCallback(async (cantonId) => {
    if (!cantonId) {
      setDistritos([]);
      return [];
    }
    setLoadingGeo(true);
    setErrorGeo(null);
    try {
      const data = await geoService.getDistritos(cantonId);
      setDistritos(Array.isArray(data) ? data : []);
      return data;
    } catch (err) {
      setErrorGeo(err.message);
      return [];
    } finally {
      setLoadingGeo(false);
    }
  }, []);

  return {
    provincias,
    cantones,
    distritos,
    loadingGeo,
    errorGeo,
    fetchProvincias,
    fetchCantones,
    fetchDistritos
  };
};
