import { useState, useEffect, useCallback } from 'react';
import { sodsiCatalogService } from '../services/sodsiCatalogService';

const emptyCatalogs = () => ({
  unidades: [],
  provincias: [],
  regionesMideplan: [],
  ejesPlanes: [],
  aliadoTipos: [],
  beneficiarioCategorias: [],
  beneficiarioValores: [],
});

export function useSodsiCatalogs({ enabled = true } = {}) {
  const [catalogs, setCatalogs] = useState(emptyCatalogs);
  const [loading, setLoading] = useState(Boolean(enabled));
  const [error, setError] = useState(null);

  const reload = useCallback(async () => {
    if (!enabled) return;
    setLoading(true);
    setError(null);
    const r = await sodsiCatalogService.getCatalogos();
    if (!r.success) {
      setError(r.error);
      setCatalogs(emptyCatalogs());
    } else {
      setCatalogs({ ...emptyCatalogs(), ...r.data });
    }
    setLoading(false);
  }, [enabled]);

  useEffect(() => {
    reload();
  }, [reload]);

  return { catalogs, loading, error, reload };
}

export default useSodsiCatalogs;
