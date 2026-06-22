import { useState, useEffect, useCallback } from 'react';
import { sodsiCatalogService } from '../services/sodsiCatalogService';

/**
 * Admin SODSI beneficiarios — Service → Hook → Page.
 */
export function useSodsiBeneficiariosAdmin() {
  const [valores, setValores] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [busyId, setBusyId] = useState(null);

  const load = useCallback(async () => {
    setLoading(true);
    setError('');
    const [catRes, valRes] = await Promise.all([
      sodsiCatalogService.getCatalogos(),
      sodsiCatalogService.listBeneficiarioValores({ adminAll: true }),
    ]);
    if (!catRes.success) {
      setError(catRes.error);
      setValores([]);
      setCategorias([]);
    } else {
      setCategorias(catRes.data.beneficiarioCategorias || []);
    }
    if (!valRes.success) {
      setError(valRes.error || 'No se pudieron cargar los valores');
      setValores([]);
    } else {
      setValores(valRes.data);
    }
    setLoading(false);
  }, []);

  useEffect(() => {
    load();
  }, [load]);

  const setBeneficiarioActivo = useCallback(async (id, activo) => {
    setBusyId(id);
    setError('');
    const res = await sodsiCatalogService.setBeneficiarioActivo(id, activo);
    setBusyId(null);
    if (res.success) await load();
    return res;
  }, [load]);

  return {
    valores,
    categorias,
    loading,
    error,
    setError,
    busyId,
    reload: load,
    setBeneficiarioActivo,
  };
}

export default useSodsiBeneficiariosAdmin;
