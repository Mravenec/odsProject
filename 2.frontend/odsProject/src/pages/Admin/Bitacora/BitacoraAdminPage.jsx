import React from 'react';
import { useNavigate } from 'react-router-dom';
import { ArrowLeft, RefreshCw, Filter } from 'lucide-react';
import { useLoginAudit } from '../../../hooks/useLoginAudit';
import './BitacoraAdminPage.css';

const EVENTO_LABEL = {
  LOGIN_OK: 'Login OK',
  LOGIN_FALLIDO: 'Login fallido',
  LOGOUT: 'Logout',
};

const eventoClass = (evento) => {
  const key = String(evento || '').toUpperCase();
  if (key === 'LOGIN_OK') return 'badge-ok';
  if (key === 'LOGIN_FALLIDO') return 'badge-fail';
  if (key === 'LOGOUT') return 'badge-logout';
  return 'badge-neutral';
};

const formatFechaHora = (value) => {
  if (!value) return '—';
  const d = new Date(value);
  if (Number.isNaN(d.getTime())) return String(value);
  return d.toLocaleString('es-CR', {
    day: '2-digit',
    month: 'short',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
  });
};

export default function BitacoraAdminPage() {
  const navigate = useNavigate();
  const {
    entries,
    totalLoaded,
    loading,
    error,
    filters,
    updateFilter,
    clearFilters,
    reload,
    eventos,
  } = useLoginAudit();

  const onFilterChange = (e) => {
    const { name, value } = e.target;
    updateFilter(name, value);
  };

  return (
    <div className="bitacora-admin fade-in">
      <header className="bitacora-admin-header">
        <button type="button" className="btn-back" onClick={() => navigate('/admin/users')}>
          <ArrowLeft size={20} />
        </button>
        <div>
          <h1>Bitácora de ingresos</h1>
          <p>Auditoría de accesos: usuario, fecha, IP y evento. Solo administradores.</p>
        </div>
        <button type="button" className="btn-refresh" onClick={reload} disabled={loading}>
          <RefreshCw size={16} /> Actualizar
        </button>
      </header>

      <section className="bitacora-filters" aria-label="Filtros de bitácora">
        <div className="bitacora-filters-title">
          <Filter size={16} />
          <span>Filtros</span>
        </div>
        <div className="bitacora-filters-grid">
          <label>
            Desde
            <input
              type="date"
              name="fechaDesde"
              value={filters.fechaDesde}
              onChange={onFilterChange}
            />
          </label>
          <label>
            Hasta
            <input
              type="date"
              name="fechaHasta"
              value={filters.fechaHasta}
              onChange={onFilterChange}
            />
          </label>
          <label>
            Usuario
            <input
              type="search"
              name="usuario"
              placeholder="Nombre o username…"
              value={filters.usuario}
              onChange={onFilterChange}
            />
          </label>
          <label>
            Evento
            <select name="evento" value={filters.evento} onChange={onFilterChange}>
              <option value="">Todos</option>
              {eventos.map((ev) => (
                <option key={ev} value={ev}>
                  {EVENTO_LABEL[ev] || ev}
                </option>
              ))}
            </select>
          </label>
          <button type="button" className="btn-clear-filters" onClick={clearFilters}>
            Limpiar
          </button>
        </div>
      </section>

      {error && <div className="bitacora-admin-error">{error}</div>}

      {loading ? (
        <div className="bitacora-admin-loader">Cargando bitácora…</div>
      ) : (
        <>
          <p className="bitacora-count">
            Mostrando {entries.length} de {totalLoaded} eventos
          </p>
          <div className="bitacora-table-wrap">
            <table className="bitacora-table">
              <thead>
                <tr>
                  <th>Usuario</th>
                  <th>Fecha</th>
                  <th>IP</th>
                  <th>Evento</th>
                </tr>
              </thead>
              <tbody>
                {entries.length === 0 ? (
                  <tr>
                    <td colSpan={4} className="empty">
                      No hay eventos con los filtros actuales.
                    </td>
                  </tr>
                ) : (
                  entries.map((row) => (
                    <tr key={row.id}>
                      <td>
                        <div className="user-cell">
                          <span className="user-main">{row.usuario}</span>
                          {row.fullName && row.fullName !== row.usuario && (
                            <span className="user-sub">{row.fullName}</span>
                          )}
                        </div>
                      </td>
                      <td>{formatFechaHora(row.fecha)}</td>
                      <td className="mono">{row.ip}</td>
                      <td>
                        <span className={`badge ${eventoClass(row.evento)}`}>
                          {EVENTO_LABEL[row.evento] || row.evento || '—'}
                        </span>
                      </td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>
        </>
      )}
    </div>
  );
}
