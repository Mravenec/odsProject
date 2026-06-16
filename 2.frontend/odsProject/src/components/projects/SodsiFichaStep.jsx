import React, { useState } from 'react';
import { Building2, Handshake, Users, Plus } from 'lucide-react';
import { sodsiCatalogService } from '../../services/sodsiCatalogService';
import './SodsiFichaStep.css';

export default function SodsiFichaStep({
  fichaSodsi,
  onChange,
  catalogs,
  loading = false,
  onCatalogRefresh,
  showBeneficiarios = false,
}) {
  const [draftByCat, setDraftByCat] = useState({});
  const [addingCat, setAddingCat] = useState(null);
  const [addError, setAddError] = useState('');

  const update = (patch) => onChange({ ...fichaSodsi, ...patch });

  const toggleBeneficiario = (valorId) => {
    const id = Number(valorId);
    const current = new Set(fichaSodsi.beneficiarioValorIds || []);
    if (current.has(id)) current.delete(id);
    else current.add(id);
    update({ beneficiarioValorIds: Array.from(current) });
  };

  const valoresPorCategoria = (categoriaId) => (catalogs.beneficiarioValores || [])
    .filter((v) => Number(v.categoriaId ?? v.categoria_id) === Number(categoriaId));

  const handleAddBeneficiario = async (cat) => {
    const nombre = (draftByCat[cat.id] || '').trim();
    if (!nombre) {
      setAddError('Escribí el nombre del beneficiario');
      return;
    }
    setAddingCat(cat.id);
    setAddError('');
    const res = await sodsiCatalogService.createBeneficiarioValor({
      categoriaId: Number(cat.id),
      nombre,
    });
    setAddingCat(null);
    if (!res.success) {
      setAddError(res.error);
      return;
    }
    const newId = Number(res.data.id);
    const current = new Set(fichaSodsi.beneficiarioValorIds || []);
    current.add(newId);
    update({ beneficiarioValorIds: Array.from(current) });
    setDraftByCat((prev) => ({ ...prev, [cat.id]: '' }));
    if (onCatalogRefresh) await onCatalogRefresh();
  };

  if (loading) {
    return (
      <div className="sodsi-ficha-step">
        <div className="sodsi-ficha-loader">
          <div className="spinner-sm" />
          <span>Cargando catálogos SODSI…</span>
        </div>
      </div>
    );
  }

  return (
    <div className="sodsi-ficha-step">
      <div className="section-intro">
        <Building2 size={18} />
        <p>
          Datos SODSI del proyecto: eje PNDIP y aliado externo opcional.
          Contacto, dependencia y región se toman del perfil del gestor en administración de usuarios.
        </p>
      </div>

      <div className="form-grid">
        <div className="form-group form-group-full">
          <label>Eje de planes (PNDIP 2023–2026)</label>
          <select
            value={fichaSodsi.ejePlanesId || ''}
            onChange={(e) => update({ ejePlanesId: e.target.value })}
          >
            <option value="">Seleccione eje</option>
            {(catalogs.ejesPlanes || []).map((e) => (
              <option key={e.id} value={e.id}>{e.nombre}</option>
            ))}
          </select>
        </div>
        <div className="form-group form-group-full">
          <label><Handshake size={14} /> Aliado externo (opcional)</label>
          <input
            type="text"
            value={fichaSodsi.aliadoExterno || ''}
            onChange={(e) => update({ aliadoExterno: e.target.value })}
            placeholder="Nombre del aliado externo, si aplica"
          />
        </div>
      </div>

      {showBeneficiarios && (
      <section className="sodsi-block">
        <div className="sodsi-block-header">
          <Users size={18} />
          <h3>Sector beneficiario *</h3>
        </div>
        <p className="sodsi-hint">
          Elegí uno o más sectores. Si no está en la lista, escribilo y agregalo (quedará disponible para otros proyectos).
        </p>
        {addError && <p className="sodsi-add-error">{addError}</p>}
        {(catalogs.beneficiarioCategorias || []).map((cat) => {
          const valores = valoresPorCategoria(cat.id);
          return (
            <div key={cat.id} className="sodsi-benef-cat">
              <h4>{cat.nombre}</h4>
              <div className="sodsi-benef-grid">
                {valores.map((v) => {
                  const checked = (fichaSodsi.beneficiarioValorIds || []).includes(Number(v.id));
                  return (
                    <label key={v.id} className={`sodsi-benef-chip ${checked ? 'selected' : ''}`}>
                      <input
                        type="checkbox"
                        checked={checked}
                        onChange={() => toggleBeneficiario(v.id)}
                      />
                      <span>{v.nombre}</span>
                    </label>
                  );
                })}
              </div>
              <div className="sodsi-benef-add">
                <input
                  type="text"
                  value={draftByCat[cat.id] || ''}
                  onChange={(e) => setDraftByCat((prev) => ({ ...prev, [cat.id]: e.target.value }))}
                  placeholder="¿No está en la lista? Escribí el beneficiario…"
                />
                <button
                  type="button"
                  className="btn-sodsi-add"
                  disabled={addingCat === cat.id}
                  onClick={() => handleAddBeneficiario(cat)}
                >
                  <Plus size={14} /> Agregar
                </button>
              </div>
            </div>
          );
        })}
      </section>
      )}
    </div>
  );
}
