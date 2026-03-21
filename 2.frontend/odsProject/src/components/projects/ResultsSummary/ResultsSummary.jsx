import React from 'react';
import { getAchievementClass } from '../../../utils/formatters';
import './ResultsSummary.css';

const ResultsSummary = ({ 
  overallScore, 
  indicatorsAchieved, 
  totalIndicators,
  title = "Impacto General"
}) => {
  const statusClass = getAchievementClass(overallScore);
  const successRate = totalIndicators > 0 ? (indicatorsAchieved / totalIndicators) * 100 : 0;

  return (
    <div className={`shared-results-summary ${statusClass}`}>
      <div className="summary-circle-container">
        <svg viewBox="0 0 100 100" className="progress-ring">
          <circle className="progress-ring-track" cx="50" cy="50" r="45" />
          <circle 
            className={`progress-ring-fill ${statusClass}`} 
            cx="50" 
            cy="50" 
            r="45" 
            style={{ 
              strokeDasharray: '282.7', 
              strokeDashoffset: `${282.7 - (282.7 * Math.min(overallScore, 100)) / 100}` 
            }} 
          />
        </svg>
        <div className="circle-content">
          <span className="score-number">{overallScore.toFixed(0)}%</span>
          <span className="score-label">LOGRO</span>
        </div>
      </div>

      <div className="summary-info">
        <h3>{title}</h3>
        <p className="summary-stats-text">
          Has cumplido con <strong>{indicatorsAchieved} de {totalIndicators}</strong> metas establecidas en este proyecto.
        </p>
        <div className="success-rate-bar">
          <div className="rate-label">Tasa de cumplimiento operativa</div>
          <div className="rate-bar-bg">
            <div className="rate-bar-fill" style={{ width: `${successRate}%` }}></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ResultsSummary;
