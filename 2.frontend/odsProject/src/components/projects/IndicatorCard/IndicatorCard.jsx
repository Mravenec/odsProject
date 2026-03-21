import React from 'react';
import { formatIndicatorName, getAchievementClass } from '../../../utils/formatters';
import './IndicatorCard.css';

const IndicatorCard = ({ 
  indicator, 
  targetValue, 
  finalValue, 
  goalAchievement, 
  mode = 'view', // 'view' or 'input'
  inputValue = '',
  onInputChange,
  index
}) => {
  const isView = mode === 'view';
  const statusClass = isView ? getAchievementClass(goalAchievement) : '';

  return (
    <div className={`shared-indicator-card ${mode}-mode ${statusClass}`}>
      <div className="indicator-card-header">
        <div className="indicator-brand">
          <span className="indicator-index">#{index || ''}</span>
          <h4>{formatIndicatorName(indicator)}</h4>
        </div>
        {isView && (
          <div className={`achievement-badge-sm ${statusClass}`}>
            {goalAchievement.toFixed(1)}%
          </div>
        )}
      </div>

      <div className="indicator-card-body">
        {isView ? (
          <div className="indicator-stats-view">
            <div className="stat-box">
              <span className="stat-label">Meta</span>
              <span className="stat-value">{targetValue}</span>
            </div>
            <div className="stat-box">
              <span className="stat-label">Logro</span>
              <span className="stat-value">{finalValue}</span>
            </div>
            <div className="progress-mini">
              <div 
                className={`progress-mini-fill ${statusClass}`}
                style={{ width: `${Math.min(goalAchievement, 100)}%` }}
              ></div>
            </div>
          </div>
        ) : (
          <div className="indicator-input-group">
            <div className="input-meta-info">
              <span>Meta establecida: <strong>{targetValue}</strong></span>
            </div>
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
          </div>
        )}
      </div>
    </div>
  );
};

export default IndicatorCard;
