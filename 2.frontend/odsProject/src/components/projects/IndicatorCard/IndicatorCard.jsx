import { formatIndicatorName, formatUnitLabel, getAchievementClass } from '../../../utils/formatters';
import './IndicatorCard.css';

const unitSuffix = (unit) => {
  if (!unit) return '';
  if (unit === 'Percentage') return '%';
  return formatUnitLabel(unit);
};

const IndicatorCard = ({ 
  index,
  config = null,
  paramValues = {},
  onParamChange,
  // New props for centralized logic
  calculatedValue,
  currentAchievement,
  indicator,
  targetValue,
  finalValue,
  goalAchievement,
  mode = 'view',
  inputValue = ''
}) => {
  const isView = mode === 'view';
  
  // Use passed values or fallback to standard ones
  const displayValue = config && config.formula ? calculatedValue : finalValue;
  const displayAchievement = config && config.formula ? currentAchievement : goalAchievement;
  const displayTarget = targetValue;

  const statusClass = isView ? getAchievementClass(displayAchievement) : '';

  return (
    <div className={`shared-indicator-card ${mode}-mode ${statusClass}`}>
      <div className="indicator-card-header">
        <div className="indicator-brand">
          <span className="indicator-index">#{index || ''}</span>
          <h4>{formatIndicatorName(indicator)}</h4>
        </div>
        {isView && (
          <div className={`achievement-badge-sm ${statusClass}`}>
            {(displayAchievement || 0).toFixed(1)}%
          </div>
        )}
      </div>

      <div className="indicator-card-body">
        {isView ? (
          <div className="indicator-stats-view">
            <div className="main-stats-grid">
              <div className="stat-box">
                <span className="stat-label">Meta Establecida</span>
                <span className="stat-value secondary">{displayTarget} {unitSuffix(config?.goal?.unit)}</span>
              </div>
              <div className="stat-box">
                <span className="stat-label">Valor Alcanzado</span>
                <span className={`stat-value highlight ${statusClass}`}>
                  {displayValue} {unitSuffix(config?.goal?.unit)}
                </span>
              </div>
            </div>

            <div className="progress-bar-container">
              <div className="progress-label-row">
                <span>Progreso de Impacto</span>
                <span className={`bold ${statusClass}`}>{(displayAchievement || 0).toFixed(1)}%</span>
              </div>
              <div className="progress-mini">
                <div 
                  className={`progress-mini-fill ${statusClass}`}
                  style={{ width: `${Math.min(displayAchievement || 0, 100)}%` }}
                ></div>
              </div>
            </div>

            {config && (
              <div className="technical-details-box">
                {config.formula && (
                  <div className="tech-item highlight-blue">
                    <span className="tech-label">Fórmula de Cálculo</span>
                    <code className="mono-formula">{config.formula}</code>
                  </div>
                )}
                {config.parameters && config.parameters.length > 0 && (
                  <div className="tech-item">
                    <span className="tech-label">Parámetros Utilizados</span>
                    <div className="params-pills">
                      {config.parameters.map(p => (
                        <span key={p.name} className="param-pill">
                          {p.name}: <strong>{paramValues[p.name] || '0'}</strong>
                        </span>
                      ))}
                    </div>
                  </div>
                )}
              </div>
            )}
          </div>
        ) : (
          <div className="indicator-input-group">
            <div className="input-meta-info">
              <span>Meta establecida: <strong>{targetValue} {unitSuffix(config?.goal?.unit)}</strong></span>
              {config && <div className="formula-display">Fórmula: <code>{config.formula}</code></div>}
            </div>
            
            {config && config.parameters ? (
              <div className="dynamic-params-grid">
                {config.parameters.map((param, pIdx) => (
                  <div key={pIdx} className="param-input-control">
                    <label>{param.name}:</label>
                    <input
                      type="number"
                      value={paramValues[param.name] || ''}
                      onChange={(e) => onParamChange(indicator, param.name, e.target.value)}
                      placeholder="0.00"
                    />
                  </div>
                ))}
                <div className="calculation-preview">
                  <span className="preview-label">Resultado calculado:</span>
                  <span className="preview-value">{(displayValue || 0).toFixed(2)}</span>
                </div>
              </div>
            ) : (
              <div className="input-control">
                <label>Valor Alcanzado:</label>
                <input
                  type="number"
                  step="0.01"
                  value={inputValue}
                  onChange={(e) => onInputChange(indicator, e.target.value)}
                  required
                  placeholder="0.00"
                />
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
};

export default IndicatorCard;
