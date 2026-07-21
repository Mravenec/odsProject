import React, { useState } from 'react';
import { Users, Plus, X } from 'lucide-react';
import './BeneficiariosField.css';

/**
 * Dropdown multiselección + escribir/agregar si no existe.
 * Pensado para el paso 1 del wizard (create + edit).
 */
export default function BeneficiariosField({
  selectedIds = [],
  onChange,
  catalogs = {},
  loading = false,
  onCatalogRefresh,
  createBeneficiarioValor,
  invalid = false,
}) {
  const [draft, setDraft] = useState('');
  const [adding, setAdding] = useState(false);
  const [error, setError] = useState('');

  const valores = catalogs.beneficiarioValores || [];
  const categorias = catalogs.beneficiarioCategorias || [];
  const defaultCatId = categorias[0]?.id ?? '';

  const selectedSet = new Set((selectedIds || []).map(Number));
  const selectedItems = valores.filter((v) => selectedSet.has(Number(v.id)));
  const available = valores.filter((v) => !selectedSet.has(Number(v.id)));

  const labelFor = (id) => valores.find((v) => Number(v.id) === Number(id))?.nombre || `#${id}`;

  const addId = (id) => {
    const n = Number(id);
    if (!n || selectedSet.has(n)) return;
    onChange([...selectedSet, n]);
  };

  const removeId = (id) => {
    onChange(Array.from(selectedSet).filter((x) => x !== Number(id)));
  };

  const handleSelect = (e) => {
    const val = e.target.value;
    if (!val) return;
    addId(val);
    e.target.value = '';
  };

  const handleAdd = async () => {
    const nombre = draft.trim();
    if (!nombre) {
      setError('Escribí el nombre del beneficiario');
      return;
    }
    if (!defaultCatId) {
      setError('No hay categorías cargadas');
      return;
    }
    setAdding(true);
    setError('');
    const res = await createBeneficiarioValor({
      categoriaId: Number(defaultCatId),
      nombre,
    });
    setAdding(false);
    if (!res.success) {
      setError(res.error);
      return;
    }
    addId(res.data.id);
    setDraft('');
    if (onCatalogRefresh) await onCatalogRefresh();
  };

  const onDraftKey = (e) => {
    if (e.key === 'Enter') {
      e.preventDefault();
      handleAdd();
    }
  };

  if (loading) {
    return (
      <div className="form-group form-group-full beneficiarios-field">
        <label><Users size={14} /> Beneficiarios</label>
        <p className="beneficiarios-loading">Cargando catálogo…</p>
      </div>
    );
  }

  return (
    <div className={`form-group form-group-full beneficiarios-field${invalid ? ' is-invalid-field' : ''}`}>
      <label><Users size={14} /> Beneficiarios *</label>

      {selectedItems.length > 0 && (
        <div className="beneficiarios-chips">
          {Array.from(selectedSet).map((id) => (
            <span key={id} className="beneficiarios-chip">
              {labelFor(id)}
              <button type="button" aria-label="Quitar" onClick={() => removeId(id)}>
                <X size={12} />
              </button>
            </span>
          ))}
        </div>
      )}

      <select
        className={`beneficiarios-select${invalid ? ' is-invalid' : ''}`}
        defaultValue=""
        onChange={handleSelect}
        aria-invalid={invalid}
      >
        <option value="">Seleccionar beneficiario…</option>
        {categorias.map((cat) => {
          const opts = available.filter(
            (v) => Number(v.categoriaId ?? v.categoria_id) === Number(cat.id),
          );
          if (!opts.length) return null;
          return (
            <optgroup key={cat.id} label={cat.nombre}>
              {opts.map((v) => (
                <option key={v.id} value={v.id}>{v.nombre}</option>
              ))}
            </optgroup>
          );
        })}
      </select>

      <div className="beneficiarios-add-row">
        <input
          type="text"
          value={draft}
          onChange={(e) => setDraft(e.target.value)}
          onKeyDown={onDraftKey}
          placeholder="¿No está? Escribí el nombre y agregalo"
        />
        <button type="button" className="btn-benef-add" disabled={adding} onClick={handleAdd}>
          <Plus size={14} /> Agregar
        </button>
      </div>

      {error && <p className="beneficiarios-error">{error}</p>}
      <span className="form-hint">Elegí del listado o escribí uno nuevo (queda guardado para otros proyectos).</span>
    </div>
  );
}
