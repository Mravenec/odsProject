import React, { useState, useEffect } from 'react';
import './IndicatorConfigModal.css';

const IndicatorConfigModal = ({ indicator, existingConfig, onSave, onClose }) => {
  const [paramCount, setParamCount] = useState(existingConfig?.parameters?.length || 1);
  const [parameters, setParameters] = useState(() => 
    existingConfig?.parameters?.map(p => ({ ...p })) || [{ name: '', type: 'Integer' }]
  );
  const [formula, setFormula] = useState(existingConfig?.formula || '');
  const [goal, setGoal] = useState(existingConfig?.goal || { name: '', value: '', unit: 'Percentage' });

  useEffect(() => {
    const count = parseInt(paramCount) || 0;
    setParameters(prev => {
      if (count > prev.length) {
        const additional = Array.from({ length: count - prev.length }, () => ({ name: '', type: 'Integer' }));
        return [...prev, ...additional];
      }
      return prev.slice(0, count);
    });
  }, [paramCount]);

  const insertParam = (name) => {
    if (!name) return;
    setFormula(prev => prev + (prev ? ' ' : '') + name);
  };

  const handleSave = () => {
    // Basic validation
    if (!formula.trim()) {
      alert('Por favor, ingrese una fórmula.');
      return;
    }
    if (!goal.value) {
      alert('Por favor, ingrese un valor objetivo.');
      return;
    }
    onSave({ parameters, formula, goal });
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content config-modal fade-in">
        <div className="modal-header">
          <div>
            <h3>Configurar Indicador</h3>
            <p>{indicator.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase())}</p>
          </div>
          <button className="btn-close" onClick={onClose}>&times;</button>
        </div>

        <div className="modal-body">
          <section className="config-section">
            <label className="config-label">¿Cuántos parámetros definen este indicador?</label>
            <div className="input-row">
              <input 
                type="number" 
                min="1" 
                max="10"
                value={paramCount} 
                onChange={(e) => setParamCount(e.target.value)} 
                className="input-sm"
              />
              <span className="input-hint">parámetros de medición</span>
            </div>
            
            <div className="params-list">
              {parameters.map((p, i) => (
                <div key={`param-${i}`} className="param-item">
                  <div className="param-header">
                    <span className="param-badge">Variable {String.fromCharCode(65 + i)}</span>
                    <button 
                      type="button" 
                      onClick={() => insertParam(p.name)} 
                      disabled={!p.name}
                      className="btn-text-action"
                    >
                      Insertar en fórmula
                    </button>
                  </div>
                  <div className="param-inputs">
                    <input 
                      placeholder="Nombre (ej: total_personas)" 
                      value={p.name} 
                      onChange={(e) => {
                        const newParams = [...parameters];
                        newParams[i].name = e.target.value.replace(/\s+/g, '_');
                        setParameters(newParams);
                      }} 
                    />
                    <select 
                      value={p.type} 
                      onChange={(e) => {
                        const newParams = [...parameters];
                        newParams[i].type = e.target.value;
                        setParameters(newParams);
                      }}
                    >
                      <option value="Integer">Entero</option>
                      <option value="Decimal">Decimal</option>
                    </select>
                  </div>
                </div>
              ))}
            </div>
          </section>

          <section className="config-section">
            <label className="config-label">Fórmula de Cálculo</label>
            <p className="section-hint">Construya la expresión matemática usando las variables superiores.</p>
            <textarea 
              value={formula} 
              onChange={(e) => setFormula(e.target.value)}
              className="formula-textarea"
              placeholder="Ej: (A + B) / C"
            />
            <div className="param-chips">
              {parameters.filter(p => p.name).map((p, i) => (
                <button 
                  key={`chip-${i}`} 
                  onClick={() => insertParam(p.name)}
                  className="formula-chip"
                >
                  {p.name}
                </button>
              ))}
            </div>
          </section>

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
                  onChange={(e) => setGoal({...goal, name: e.target.value})} 
                  placeholder="Ej: Alcanzar el 80% de cobertura" 
                />
              </div>
              <div className="input-grid-2">
                <div className="form-group">
                  <label>Valor objetivo</label>
                  <input 
                    type="number"
                    value={goal.value} 
                    onChange={(e) => setGoal({...goal, value: e.target.value})} 
                    placeholder="100" 
                  />
                </div>
                <div className="form-group">
                  <label>Unidad</label>
                  <select value={goal.unit} onChange={(e) => setGoal({...goal, unit: e.target.value})}>
                    <option value="Percentage">Porcentaje (%)</option>
                    <option value="Number">Número absoluto</option>
                    <option value="Decimal">Decimal</option>
                  </select>
                </div>
              </div>
            </div>
          </section>
        </div>

        <div className="modal-footer">
          <button className="btn-secondary" onClick={onClose}>Cancelar</button>
          <button className="btn-primary" onClick={handleSave}>Guardar Configuración</button>
        </div>
      </div>
    </div>
  );
};

export default IndicatorConfigModal;
