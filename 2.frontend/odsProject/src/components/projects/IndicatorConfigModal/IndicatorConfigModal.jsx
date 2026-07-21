import React, { useState, useEffect, useRef, useCallback } from 'react';
import './IndicatorConfigModal.css';
import { useEvaluacion } from '../../../hooks/useEvaluacion';

/**
 * IndicatorConfigModal — Sprint 4
 *
 * Bug histórico arreglado: la inserción de variables y operadores ahora respeta
 * la posición del cursor del textarea (useRef + selectionStart/selectionEnd).
 *
 * Si el usuario escribe "()" y deja el cursor entre los paréntesis, al hacer
 * clic en la chip "p1" el textarea queda con "(p1)" y el caret pegado después
 * de "p1". Igual para los operadores +, -, *, /.
 *
 * Además: validación en vivo contra el backend usando POST /api/evaluacion/validar-formula
 * y muestra al usuario qué variables están en la fórmula y cuáles le faltan o sobran.
 *
 * Unidades: presets fijos + "Otro" con texto libre (nm, NTU, ₡…) persistido en metaUnidad.
 */
const PRESET_UNITS = ['Percentage', 'Number', 'Decimal', 'Hectareas', 'Personas', 'USD'];

const resolveUnitUi = (unit) => {
  const raw = (unit || 'Percentage').trim();
  if (!raw || raw === 'Otro') {
    return { selectValue: 'Otro', customUnit: '' };
  }
  if (PRESET_UNITS.includes(raw)) {
    return { selectValue: raw, customUnit: '' };
  }
  return { selectValue: 'Otro', customUnit: raw };
};

const IndicatorConfigModal = ({ indicator, existingConfig, onSave, onClose }) => {
  const formulaRef = useRef(null);
  const { validarFormula } = useEvaluacion();

  const [paramCount, setParamCount] = useState(
    existingConfig?.parameters?.length || 1
  );
  const [parameters, setParameters] = useState(() =>
    existingConfig?.parameters?.map(p => ({ ...p })) || [{ name: '', type: 'Integer' }]
  );
  const [formula, setFormula] = useState(existingConfig?.formula || '');
  const [goal, setGoal] = useState(() => {
    const base = existingConfig?.goal || { name: '', value: '', unit: 'Percentage' };
    const { selectValue, customUnit } = resolveUnitUi(base.unit);
    return {
      ...base,
      unit: selectValue === 'Otro' ? (customUnit || 'Otro') : selectValue,
      _unitSelect: selectValue,
      _customUnit: customUnit,
    };
  });

  // Estado de validación live
  const [validation, setValidation] = useState({
    valida: true,
    sintaxisValida: true,
    variablesEnFormula: [],
    faltantes: [],
    sobrantes: []
  });
  const [validating, setValidating] = useState(false);
  const [fieldErrors, setFieldErrors] = useState({});

  const clearFieldError = (key) => {
    setFieldErrors((prev) => {
      if (!prev[key]) return prev;
      const next = { ...prev };
      delete next[key];
      return next;
    });
  };

  // Sincroniza el array de parámetros cuando el usuario cambia la cantidad
  useEffect(() => {
    const count = parseInt(paramCount) || 0;
    setParameters(prev => {
      if (count > prev.length) {
        const extras = Array.from(
          { length: count - prev.length },
          () => ({ name: '', type: 'Integer' })
        );
        return [...prev, ...extras];
      }
      return prev.slice(0, count);
    });
  }, [paramCount]);

  /**
   * Inserta texto en la posición exacta del cursor del textarea.
   * Reemplaza la selección si la hay. Restaura el cursor justo después
   * del texto insertado en el siguiente paint.
   */
  const insertAtCursor = useCallback((text) => {
    const ta = formulaRef.current;
    if (!ta) {
      // Fallback: concatenar al final si no hay ref aún
      setFormula(prev => prev + text);
      return;
    }
    const start = ta.selectionStart ?? formula.length;
    const end   = ta.selectionEnd   ?? formula.length;
    const before = formula.substring(0, start);
    const after  = formula.substring(end);
    const next   = before + text + after;
    setFormula(next);

    // Reposicionar el cursor DESPUÉS de que React aplique el setState
    requestAnimationFrame(() => {
      const node = formulaRef.current;
      if (node) {
        node.focus();
        const pos = start + text.length;
        node.setSelectionRange(pos, pos);
      }
    });
  }, [formula]);

  // Insertar el nombre de una variable
  const insertParam = useCallback((name) => {
    if (!name?.trim()) return;
    insertAtCursor(name);
  }, [insertAtCursor]);

  // Insertar un operador / paréntesis
  const insertOperator = useCallback((op) => {
    insertAtCursor(op);
  }, [insertAtCursor]);

  /** Rewrite formula identifiers on word boundaries (Z→G without touching ZA). */
  const rewriteFormulaIdentifier = (formulaText, oldName, newName) => {
    if (!formulaText || !oldName || !newName || oldName === newName) return formulaText;
    const escaped = oldName.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
    return formulaText.replace(new RegExp(`\\b${escaped}\\b`, 'g'), newName);
  };

  const handleParamChange = (index, field, value) => {
    if (field === 'name') {
      clearFieldError(`param_${index}`);
      clearFieldError('params');
      const newName = value.replace(/\s+/g, '_').replace(/[^a-zA-Z0-9_]/g, '');
      const oldName = parameters[index]?.name || '';
      setParameters(prev => {
        const updated = [...prev];
        updated[index] = {
          ...updated[index],
          name: newName,
          // Keep variable in sync — payload used to prefer stale `variable` over `name`.
          variable: newName,
        };
        return updated;
      });
      if (oldName && newName && oldName !== newName) {
        setFormula(prev => rewriteFormulaIdentifier(prev, oldName, newName));
      }
      return;
    }
    setParameters(prev => {
      const updated = [...prev];
      updated[index] = { ...updated[index], [field]: value };
      return updated;
    });
  };

  // Validación en vivo (debounced) cuando cambia la fórmula o las variables
  useEffect(() => {
    if (!formula.trim()) {
      setValidation({ valida: true, sintaxisValida: true, variablesEnFormula: [], faltantes: [], sobrantes: [] });
      return;
    }
    const declaradas = parameters.map(p => p.name).filter(n => n && n.trim());
    const handle = setTimeout(async () => {
      setValidating(true);
      try {
        const res = await validarFormula(formula, declaradas);
        setValidation(res);
      } catch (e) {
        // Sin backend: validación visual se omite, no bloqueamos el flujo
        setValidation(v => ({ ...v, sintaxisValida: true, valida: true }));
      } finally {
        setValidating(false);
      }
    }, 350);
    return () => clearTimeout(handle);
  }, [formula, parameters, validarFormula]);

  const handleUnitSelectChange = (selectValue) => {
    clearFieldError('customUnit');
    if (selectValue === 'Otro') {
      setGoal(prev => ({
        ...prev,
        _unitSelect: 'Otro',
        unit: (prev._customUnit || '').trim() || 'Otro',
      }));
      return;
    }
    setGoal(prev => ({
      ...prev,
      _unitSelect: selectValue,
      unit: selectValue,
    }));
  };

  const handleCustomUnitChange = (text) => {
    clearFieldError('customUnit');
    setGoal(prev => ({
      ...prev,
      _unitSelect: 'Otro',
      _customUnit: text,
      unit: text.trim() || 'Otro',
    }));
  };

  const handleSave = () => {
    const errors = {};
    if (!formula.trim()) errors.formula = true;
    if (goal.value === '' || goal.value === null || goal.value === undefined) errors.goalValue = true;
    const unitSelect = goal._unitSelect || resolveUnitUi(goal.unit).selectValue;
    const customUnit = (goal._customUnit || '').trim();
    if (unitSelect === 'Otro' && !customUnit) errors.customUnit = true;
    parameters.forEach((p, i) => {
      if (!String(p.name || '').trim()) errors[`param_${i}`] = true;
    });
    if (Object.keys(errors).some((k) => k.startsWith('param_'))) errors.params = true;
    if (validation.faltantes && validation.faltantes.length > 0) errors.faltantes = true;
    if (validation.sintaxisValida === false) errors.formula = true;

    setFieldErrors(errors);
    if (Object.keys(errors).length > 0) {
      if (errors.faltantes) {
        alert(
          `Complete las variables faltantes en la fórmula: ${validation.faltantes.join(', ')}`
        );
      } else if (errors.formula && !formula.trim()) {
        alert('Por favor, ingrese una fórmula de cálculo.');
      } else if (errors.goalValue) {
        alert('Por favor, ingrese un valor objetivo para la meta.');
      } else if (errors.customUnit) {
        alert('Indique la unidad personalizada (ej: nm, NTU, ₡).');
      } else if (errors.params) {
        alert('Asigne un nombre a todos los parámetros antes de guardar.');
      } else if (errors.formula) {
        alert('Corrija la fórmula antes de guardar.');
      }
      return;
    }

    const persistedUnit = unitSelect === 'Otro' ? customUnit : unitSelect;
    const { _unitSelect, _customUnit, ...goalRest } = goal;
    onSave({
      parameters: parameters.map(p => ({
        ...p,
        name: (p.name || '').trim(),
        variable: (p.name || '').trim(),
      })),
      formula,
      goal: { ...goalRest, unit: persistedUnit },
    });
  };

  // Nombre visible del indicador
  const indicatorLabel = typeof indicator === 'string'
    ? indicator
    : (indicator?.nombre || indicator?.name || indicator?.codigo || indicator?.code || '');

  const operatorChips = [
    { label: '(', insert: '(' },
    { label: ')', insert: ')' },
    { label: '+', insert: ' + ' },
    { label: '−', insert: ' - ' },
    { label: '×', insert: ' * ' },
    { label: '÷', insert: ' / ' },
    { label: '× 100', insert: ' * 100' }
  ];

  const namedParams = parameters.filter(p => p.name.trim());

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
                      Insertar en posición del cursor
                    </button>
                  </div>
                  <div className="param-inputs">
                    <input
                      placeholder="Nombre (ej: total_personas, ingresos_y)"
                      value={p.name}
                      onChange={e => handleParamChange(i, 'name', e.target.value)}
                      className={fieldErrors[`param_${i}`] ? 'is-invalid' : undefined}
                      aria-invalid={!!fieldErrors[`param_${i}`]}
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
              Posicione el cursor dentro del textarea y haga clic en una variable u operador para insertarlo en ese punto exacto.
            </p>
            <textarea
              ref={formulaRef}
              value={formula}
              onChange={e => {
                clearFieldError('formula');
                clearFieldError('faltantes');
                setFormula(e.target.value);
              }}
              className={`formula-textarea${fieldErrors.formula || fieldErrors.faltantes ? ' is-invalid' : ''}`}
              placeholder="Ej: (total_becados / total_estudiantes) * 100"
              spellCheck={false}
              aria-invalid={!!(fieldErrors.formula || fieldErrors.faltantes)}
            />

            {/* Chips de inserción rápida — variables */}
            {namedParams.length > 0 && (
              <div className="param-chips">
                {namedParams.map((p, i) => (
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
            )}

            {/* Chips de inserción rápida — operadores */}
            <div className="param-chips" style={{ marginTop: 8 }}>
              {operatorChips.map(op => (
                <button
                  key={`op-${op.label}`}
                  type="button"
                  onClick={() => insertOperator(op.insert)}
                  className="formula-chip"
                  style={{ fontFamily: 'ui-monospace, SFMono-Regular, monospace' }}
                >
                  {op.label}
                </button>
              ))}
            </div>

            {/* Panel de validación en vivo */}
            <div className="formula-validation" style={{
              marginTop: 12,
              padding: '10px 12px',
              borderRadius: 8,
              fontSize: 12,
              background: validation.valida ? '#f0fdf4' : '#fff7ed',
              border: `1px solid ${validation.valida ? '#bbf7d0' : '#fed7aa'}`,
              color: '#374151'
            }}>
              <div style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
                <strong>{validation.valida ? '✓' : '⚠'} Validación</strong>
                {validating && <span style={{ color: '#6b7280' }}>verificando…</span>}
              </div>
              {validation.variablesEnFormula?.length > 0 && (
                <div style={{ marginTop: 4 }}>
                  Variables en la fórmula: <code>{Array.from(validation.variablesEnFormula).join(', ')}</code>
                </div>
              )}
              {validation.faltantes?.length > 0 && (
                <div style={{ marginTop: 4, color: '#b45309' }}>
                  Faltan declarar: <code>{Array.from(validation.faltantes).join(', ')}</code>
                </div>
              )}
              {validation.sobrantes?.length > 0 && (
                <div style={{ marginTop: 4, color: '#6b7280' }}>
                  Declaradas pero no usadas: <code>{Array.from(validation.sobrantes).join(', ')}</code>
                </div>
              )}
              {!validation.sintaxisValida && (
                <div style={{ marginTop: 4, color: '#b91c1c' }}>
                  Error de sintaxis en la fórmula.
                </div>
              )}
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
                    onChange={e => {
                      clearFieldError('goalValue');
                      setGoal({ ...goal, value: e.target.value });
                    }}
                    placeholder="Ej: 80"
                    className={fieldErrors.goalValue ? 'is-invalid' : undefined}
                    aria-invalid={!!fieldErrors.goalValue}
                  />
                </div>
                <div className="form-group">
                  <label>Unidad</label>
                  <select
                    value={goal._unitSelect || resolveUnitUi(goal.unit).selectValue}
                    onChange={e => handleUnitSelectChange(e.target.value)}
                  >
                    <option value="Percentage">Porcentaje (%)</option>
                    <option value="Number">Número absoluto</option>
                    <option value="Decimal">Decimal</option>
                    <option value="Hectareas">Hectáreas (ha)</option>
                    <option value="Personas">Personas</option>
                    <option value="USD">USD</option>
                    <option value="Otro">Otro / Personalizada</option>
                  </select>
                </div>
              </div>
              {(goal._unitSelect || resolveUnitUi(goal.unit).selectValue) === 'Otro' && (
                <div className="form-group" style={{ marginTop: 12 }}>
                  <label>Unidad personalizada</label>
                  <input
                    type="text"
                    value={goal._customUnit ?? ''}
                    onChange={e => handleCustomUnitChange(e.target.value)}
                    placeholder="Ej: nm, NTU, ₡, kg/m³"
                    maxLength={64}
                    autoFocus={!goal._customUnit}
                    className={fieldErrors.customUnit ? 'is-invalid' : undefined}
                    aria-invalid={!!fieldErrors.customUnit}
                  />
                  <span className="input-hint" style={{ display: 'block', marginTop: 6 }}>
                    Se guarda como texto libre en la meta (metaUnidad).
                  </span>
                </div>
              )}
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
