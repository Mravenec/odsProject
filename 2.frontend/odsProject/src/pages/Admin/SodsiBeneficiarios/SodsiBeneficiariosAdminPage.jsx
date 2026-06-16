import React, { useState, useEffect, useCallback } from 'react';
import { useNavigate } from 'react-router-dom';
import { ArrowLeft, RefreshCw } from 'lucide-react';
import { sodsiCatalogService } from '../../../services/sodsiCatalogService';
import './SodsiBeneficiariosAdminPage.css';

const categoriaLabel = (categorias, categoriaId) => {
  const match = (categorias || []).find((c) => Number(c.id) === Number(categoriaId));
  return match?.nombre || `Cat. ${categoriaId}`;
};

export default function SodsiBeneficiariosAdminPage() {
  const navigate = useNavigate();
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

  useEffect(() => { load(); }, [load]);

  const toggleActivo = async (row) => {
    const next = !row.activo;
    const msg = next
      ? `¿Reactivar «${row.nombre}» en el catálogo?`
      : `¿Desactivar «${row.nombre}»? Los proyectos que ya lo usan conservan el valor en export.`;
    if (!window.confirm(msg)) return;

    setBusyId(row.id);
    const res = await sodsiCatalogService.setBeneficiarioActivo(row.id, next);
    setBusyId(null);
    if (!res.success) {
      setError(res.error);
      return;
    }
    await load();
  };

  return (
    <div className="sodsi-ben-admin fade-in">
      <header className="sodsi-ben-admin-header">
        <button type="button" className="btn-back" onClick={() => navigate('/admin/users')}>
          <ArrowLeft size={20} />
        </button>
        <div>
          <h1>Catálogo beneficiarios SODSI</h1>
          <p>Valores del glosario OPSI. Desactivar no borra referencias en proyectos existentes.</p>
        </div>
        <button type="button" className="btn-refresh" onClick={load} disabled={loading}>
          <RefreshCw size={16} /> Actualizar
        </button>
      </header>

      {error && <div className="sodsi-ben-admin-error">{error}</div>}

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
                      className="btn-toggle"
                      disabled={busyId === v.id}
                      onClick={() => toggleActivo(v)}
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
    </div>
  );
}
