import { useState, useEffect, useCallback, useMemo } from 'react';
import { authService } from '../services/authService';

const EVENTOS = ['LOGIN_OK', 'LOGIN_FALLIDO', 'LOGOUT'];
const PAGE_SIZE = 50;

const todayIso = () => {
  const d = new Date();
  const y = d.getFullYear();
  const m = String(d.getMonth() + 1).padStart(2, '0');
  const day = String(d.getDate()).padStart(2, '0');
  return `${y}-${m}-${day}`;
};

const daysBetween = (from, to) => {
  if (!from) return 30;
  const start = new Date(from);
  const end = to ? new Date(to) : new Date();
  const ms = end.getTime() - start.getTime();
  const days = Math.ceil(ms / (1000 * 60 * 60 * 24));
  return Math.max(1, Math.min(365, days || 30));
};

/**
 * Bitácora de ingresos — Service → Hook → BitacoraAdminPage.
 * Carga audit-recent y filtra en cliente; pagina de a 50.
 */
export function useLoginAudit(initialFilters = {}) {
  const [entries, setEntries] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [page, setPage] = useState(0);
  const [filters, setFilters] = useState({
    fechaDesde: initialFilters.fechaDesde ?? todayIso(),
    fechaHasta: initialFilters.fechaHasta ?? todayIso(),
    usuario: initialFilters.usuario || '',
    evento: initialFilters.evento || '',
  });

  const load = useCallback(async () => {
    setLoading(true);
    setError('');
    const dias = daysBetween(filters.fechaDesde, filters.fechaHasta);
    const res = await authService.getAuditRecent(dias);
    if (!res.success) {
      setError(res.error);
      setEntries([]);
    } else {
      setEntries(res.data);
    }
    setPage(0);
    setLoading(false);
  }, [filters.fechaDesde, filters.fechaHasta]);

  useEffect(() => {
    load();
  }, [load]);

  const filtered = useMemo(() => {
    const userQ = filters.usuario.trim().toLowerCase();
    const eventoQ = filters.evento.trim().toUpperCase();
    const desde = filters.fechaDesde ? new Date(`${filters.fechaDesde}T00:00:00`) : null;
    const hasta = filters.fechaHasta ? new Date(`${filters.fechaHasta}T23:59:59`) : null;

    return entries.filter((row) => {
      if (eventoQ && String(row.evento || '').toUpperCase() !== eventoQ) return false;

      if (userQ) {
        const hay = `${row.usuario || ''} ${row.fullName || ''}`.toLowerCase();
        if (!hay.includes(userQ)) return false;
      }

      if (desde || hasta) {
        const fecha = row.fecha ? new Date(row.fecha) : null;
        if (!fecha || Number.isNaN(fecha.getTime())) return false;
        if (desde && fecha < desde) return false;
        if (hasta && fecha > hasta) return false;
      }

      return true;
    });
  }, [entries, filters]);

  const totalPages = Math.max(1, Math.ceil(filtered.length / PAGE_SIZE));
  const safePage = Math.min(page, totalPages - 1);
  const pageEntries = useMemo(() => {
    const start = safePage * PAGE_SIZE;
    return filtered.slice(start, start + PAGE_SIZE);
  }, [filtered, safePage]);

  const updateFilter = useCallback((name, value) => {
    setFilters((prev) => ({ ...prev, [name]: value }));
    setPage(0);
  }, []);

  const clearFilters = useCallback(() => {
    setFilters({ fechaDesde: '', fechaHasta: '', usuario: '', evento: '' });
    setPage(0);
  }, []);

  return {
    entries: pageEntries,
    totalFiltered: filtered.length,
    totalLoaded: entries.length,
    page: safePage,
    pageSize: PAGE_SIZE,
    totalPages,
    setPage,
    loading,
    error,
    setError,
    filters,
    updateFilter,
    clearFilters,
    reload: load,
    eventos: EVENTOS,
  };
}

export default useLoginAudit;
