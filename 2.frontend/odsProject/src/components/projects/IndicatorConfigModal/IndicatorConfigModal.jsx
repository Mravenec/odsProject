import React, { useState, useEffect } from 'react';
import './IndicatorConfigModal.css';

/**
 * IndicatorConfigModal
 * Permite al usuario definir libremente las variables de un indicador,
 * construir la fórmula de cálculo y establecer la meta del proyecto.
 * No depende de ningún catálogo del backend — las variables son libres.
 */
const IndicatorConfigModal = ({ indicator, existingConfig, onSave, onClose }) => {
  const [paramCount, setParamCount] = useState(
    existingConfig?.parameters?.length || 1
  );
  const [parameters, setParameters] = useState(() =>
    existingConfig?.parameters?.map(p => ({ ...p })) || [{ name: '', type: 'Decimal' }]
  );
  const [formula, setFormula] = useState(existingConfig?.formula || '');
  const [goal, setGoal] = useState(
    existingConfig?.goal || { name: '', value: '', unit: 'Percentage' }
  );

  // Sincroniza el array de parámetros cuando el usuario cambia la cantidad
  useEffect(() => {
    const count = parseInt(paramCount) || 0;
    setParameters(prev => {
      if (count > prev.length) {
        const extras = Array.from(
          { length: count - prev.length },
          () => ({ name: '', type: 'Decimal' })
        );
        return [...prev, ...extras];
      }
      return prev.slice(0, count);
    });
  }, [paramCount]);

  // Inserta el nombre de la variable en la posición del cursor del textarea
  const insertParam = (name) => {
    if (!name.trim()) return;
    setFormula(prev => prev + (prev ? ' ' : '') + name);
  };

  const handleParamChange = (index, field, value) => {
    setParameters(prev => {
      const updated = [...prev];
      // Nombres de variables: sin espacios, solo letras/números/guión_bajo
      updated[index] = {
        ...updated[index],
        [field]: field === 'name'
          ? value.replace(/\s+/g, '_').replace(/[^a-zA-Z0-9_]/g, '')
          : value
      };
      return updated;
    });
  };

  const handleSave = () => {
    if (!formula.trim()) {
      alert('Por favor, ingrese una fórmula de cálculo.');
      return;
    }
    if (!goal.value) {
      alert('Por favor, ingrese un valor objetivo para la meta.');
      return;
    }
    const unnamed = parameters.filter(p => !p.name.trim());
    if (unnamed.length > 0) {
      alert('Asigne un nombre a todos los parámetros antes de guardar.');
      return;
    }
    onSave({ parameters, formula, goal });
  };

  // Nombre visible del indicador
  const indicatorLabel = typeof indicator === 'string'
    ? indicator
    : (indicator?.nombre || indicator?.name || indicator?.codigo || indicator?.code || '');

  return (
    <div className="modal-overlay">
      <div className="modal-content config-modal fade-in">
        <div className="modal-header">
          <div>
            <h3>Configurar Indicador</h3>
            <p className="indicator-subtitle">{indicatorLabel}</p>
          </div>
          <button className="btn-close" onClick={onClose}>&times;</button>
        </div>

        <div className="modal-body">

          {/* ── Sección 1: Variables ── */}
          <section className="config-section">
            <label className="config-label">
              ¿Cuántos parámetros definen este indicador?
            </label>
            <div className="input-row">
              <input
                type="number"
                min="1"
                max="10"
                value={paramCount}
                onChange={e => setParamCount(e.target.value)}
                className="input-sm"
              />
              <span className="input-hint">parámetros de medición</span>
            </div>

            <div className="params-list">
              {parameters.map((p, i) => (
                <div key={`param-${i}`} className="param-item">
                  <div className="param-header">
                    <span className="param-badge">
                      Variable {String.fromCharCode(65 + i)}
                    </span>
                    <button
                      type="button"
                      onClick={() => insertParam(p.name)}
                      disabled={!p.name.trim()}
                      className="btn-text-action"
                    >
                      Insertar en fórmula
                    </button>
                  </div>
                  <div className="param-inputs">
                    <input
                      placeholder="Nombre (ej: total_personas, ingresos_y)"
                      value={p.name}
                      onChange={e => handleParamChange(i, 'name', e.target.value)}
                    />
                    <select
                      value={p.type}
                      onChange={e => handleParamChange(i, 'type', e.target.value)}
                    >
                      <option value="Integer">Entero (sin decimales)</option>
                      <option value="Decimal">Decimal (con decimales)</option>
                    </select>
                  </div>
                </div>
              ))}
            </div>
          </section>

          {/* ── Sección 2: Fórmula ── */}
          <section className="config-section">
            <label className="config-label">Fórmula de Cálculo</label>
            <p className="section-hint">
              Construya la expresión matemática usando los nombres de las variables definidas arriba.
            </p>
            <textarea
              value={formula}
              onChange={e => setFormula(e.target.value)}
              className="formula-textarea"
              placeholder="Ej: (total_becados / total_estudiantes) * 100"
              spellCheck={false}
            />
            {/* Chips de inserción rápida */}
            <div className="param-chips">
              {parameters.filter(p => p.name.trim()).map((p, i) => (
                <button
                  key={`chip-${i}`}
                  type="button"
                  onClick={() => insertParam(p.name)}
                  className="formula-chip"
                >
                  {p.name}
                </button>
              ))}
            </div>
          </section>

          {/* ── Sección 3: Meta ── */}
          <section className="config-section goal-section">
            <div className="section-title-row">
              <span className="icon">🎯</span>
              <label className="config-label">Meta del Proyecto</label>
            </div>
            <div className="goal-inputs">
              <div className="form-group">
                <label>Descripción de la meta</label>
                <input
                  value={goal.name}
                  onChange={e => setGoal({ ...goal, name: e.target.value })}
                  placeholder="Ej: Alcanzar el 80% de cobertura en el cantón"
                />
              </div>
              <div className="input-grid-2">
                <div className="form-group">
                  <label>Valor objetivo</label>
                  <input
                    type="number"
                    value={goal.value}
                    onChange={e => setGoal({ ...goal, value: e.target.value })}
                    placeholder="Ej: 80"
                  />
                </div>
                <div className="form-group">
                  <label>Unidad</label>
                  <select
                    value={goal.unit}
                    onChange={e => setGoal({ ...goal, unit: e.target.value })}
                  >
                    <option value="Percentage">Porcentaje (%)</option>
                    <option value="Number">Número absoluto</option>
                    <option value="Decimal">Decimal</option>
                    <option value="Hectareas">Hectáreas (ha)</option>
                    <option value="Personas">Personas</option>
                    <option value="USD">USD</option>
                    <option value="Otro">Otro</option>
                  </select>
                </div>
              </div>
            </div>
          </section>
        </div>

        <div className="modal-footer">
          <button className="btn-secondary" onClick={onClose}>Cancelar</button>
          <button className="btn-primary" onClick={handleSave}>
            Guardar Configuración
          </button>
        </div>
      </div>
    </div>
  );
};

export default IndicatorConfigModal;
