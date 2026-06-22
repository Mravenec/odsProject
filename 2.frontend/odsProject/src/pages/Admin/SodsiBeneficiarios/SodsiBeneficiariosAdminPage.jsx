import React, { useState, useEffect, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { ArrowLeft, RefreshCw } from 'lucide-react';
import { sodsiCatalogService } from '../../../services/sodsiCatalogService';
import './SodsiBeneficiariosAdminPage.css';

const categoriaLabel = (categorias, categoriaId) => {
  const match = (categorias || []).find((c) => Number(c.id) === Number(categoriaId));
  return match?.nombre || `Cat. ${categoriaId}`;
};

const EMPTY_CONFIRM = { open: false, row: null, nextActivo: null };

export default function SodsiBeneficiariosAdminPage() {
  const navigate = useNavigate();
  const [valores, setValores] = useState([]);
  const [categorias, setCategorias] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [busyId, setBusyId] = useState(null);
  const [confirm, setConfirm] = useState(EMPTY_CONFIRM);

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

  useEffect(() => { load(); }, [load]);

  useEffect(() => {
    if (!success) return undefined;
    const t = setTimeout(() => setSuccess(''), 4000);
    return () => clearTimeout(t);
  }, [success]);

  const openToggleConfirm = (row) => {
    const nextActivo = !row.activo;
    setConfirm({ open: true, row, nextActivo });
  };

  const closeConfirm = () => {
    if (busyId) return;
    setConfirm(EMPTY_CONFIRM);
  };

  const confirmToggle = async () => {
    const { row, nextActivo } = confirm;
    if (!row) return;

    setBusyId(row.id);
    setError('');
    const res = await sodsiCatalogService.setBeneficiarioActivo(row.id, nextActivo);
    setBusyId(null);

    if (!res.success) {
      setError(res.error);
      return;
    }

    setConfirm(EMPTY_CONFIRM);
    setSuccess(
      nextActivo
        ? `«${row.nombre}» reactivado en el catálogo.`
        : `«${row.nombre}» desactivado. Los proyectos existentes conservan el valor en export.`,
    );
    await load();
  };

  const confirmTitle = confirm.nextActivo ? 'Reactivar beneficiario' : 'Desactivar beneficiario';
  const confirmBody = confirm.row
    ? (confirm.nextActivo
      ? `¿Reactivar «${confirm.row.nombre}»? Volverá a aparecer en los formularios de gestores.`
      : `¿Desactivar «${confirm.row.nombre}»? Los proyectos que ya lo usan conservan el valor en export.`)
    : '';

  return (
    <div className="sodsi-ben-admin fade-in">
      <header className="sodsi-ben-admin-header">
        <button type="button" className="btn-back" onClick={() => navigate('/admin/users')}>
          <ArrowLeft size={20} />
        </button>
        <div>
          <h1>Catálogo beneficiarios SODSI</h1>
          <p>Valores del glosario OPSI. Desactivar o reactivar sin borrar referencias en proyectos existentes.</p>
        </div>
        <button type="button" className="btn-refresh" onClick={load} disabled={loading}>
          <RefreshCw size={16} /> Actualizar
        </button>
      </header>

      {error && <div className="sodsi-ben-admin-error">{error}</div>}
      {success && <div className="sodsi-ben-admin-success">{success}</div>}

      {loading ? (
        <div className="sodsi-ben-admin-loader">Cargando catálogo…</div>
      ) : (
        <div className="sodsi-ben-admin-table-wrap">
          <table className="sodsi-ben-admin-table">
            <thead>
              <tr>
                <th>Código</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Origen</th>
                <th>Estado</th>
                <th>Acción</th>
              </tr>
            </thead>
            <tbody>
              {valores.length === 0 ? (
                <tr>
                  <td colSpan={6} className="empty">Sin valores en catálogo.</td>
                </tr>
              ) : valores.map((v) => (
                <tr key={v.id} className={!v.activo ? 'row-inactive' : ''}>
                  <td>{v.codigo}</td>
                  <td>{v.nombre}</td>
                  <td>{categoriaLabel(categorias, v.categoriaId)}</td>
                  <td>{v.esPersonalizado ? 'Gestor' : 'Seed OPSI'}</td>
                  <td>
                    <span className={`badge ${v.activo ? 'badge-active' : 'badge-inactive'}`}>
                      {v.activo ? 'Activo' : 'Inactivo'}
                    </span>
                  </td>
                  <td>
                    <button
                      type="button"
                      className={`btn-toggle ${v.activo ? 'btn-toggle--danger' : 'btn-toggle--success'}`}
                      disabled={busyId === v.id}
                      onClick={() => openToggleConfirm(v)}
                    >
                      {v.activo ? 'Desactivar' : 'Reactivar'}
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {confirm.open && (
        <div
          className="sodsi-ben-confirm-overlay"
          role="dialog"
          aria-modal="true"
          aria-labelledby="sodsi-ben-confirm-title"
          onClick={closeConfirm}
        >
          <div className="sodsi-ben-confirm-modal" onClick={(e) => e.stopPropagation()}>
            <h2 id="sodsi-ben-confirm-title">{confirmTitle}</h2>
            <p>{confirmBody}</p>
            <div className="sodsi-ben-confirm-actions">
              <button type="button" className="btn-secondary" onClick={closeConfirm} disabled={Boolean(busyId)}>
                Cancelar
              </button>
              <button
                type="button"
                className={confirm.nextActivo ? 'btn-primary' : 'btn-danger'}
                onClick={confirmToggle}
                disabled={Boolean(busyId)}
              >
                {busyId ? 'Guardando…' : confirm.nextActivo ? 'Reactivar' : 'Desactivar'}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
