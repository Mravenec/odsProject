import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { formatDate, formatIndicatorName, getAchievementClass } from '../../utils/formatters';
import IndicatorCard from '../../components/projects/IndicatorCard/IndicatorCard';
import ResultsSummary from '../../components/projects/ResultsSummary/ResultsSummary';
import './ProjectResultsPage.css';

const ProjectResultsPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { projectId } = useParams();
  const { 
    loading: projectsLoading, 
    fetchUserProjects, 
    getProjectResults,
    updateProjectResults 
  } = useProjects();
  
  const [project, setProject] = useState(null);
  const [formData, setFormData] = useState({});
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
  const [results, setResults] = useState(null);

  useEffect(() => {
    fetchProject();
  }, [projectId]);

  const fetchProject = async () => {
    try {
      setLoading(true);
      const userProjects = await fetchUserProjects(user.id);
      const currentProject = userProjects.find(p => p.id === parseInt(projectId));
      
      if (!currentProject) {
        setError('Proyecto no encontrado');
        return;
      }
      
      setProject(currentProject);
      
      if (currentProject.status === 'completed' || currentProject.status === 'completado') {
        const resultsData = await getProjectResults(currentProject.id, currentProject.objective);
        setResults(resultsData);
      } else {
        const initialFormData = {};
        currentProject.indicators.forEach(indicator => {
          initialFormData[indicator] = '';
        });
        setFormData(initialFormData);
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (indicator, value) => {
    setFormData(prev => ({
      ...prev,
      [indicator]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');

    try {
      const resultsData = {
        projectId: project.id,
        finalValues: formData
      };

      const updateRes = await updateProjectResults(resultsData, project.objective);
      if (updateRes.success) {
        const projectResults = await getProjectResults(project.id, project.objective);
        setResults(projectResults);
        setProject(prev => ({ ...prev, status: 'completed' }));
      } else {
        setError(updateRes.error);
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  };

  if (loading || projectsLoading) {
    return (
      <div className="dashboard-loading">
        <div className="loader"></div>
        <p>Procesando métricas de impacto...</p>
      </div>
    );
  }

  return (
    <div className="project-results-page fade-in">
      <header className="results-header">
        <div className="results-header-container">
          <div className="header-top">
            <div className="project-badge-icon">🎯</div>
            <div className="project-title-area">
              <h1>{project.name}</h1>
              <p className="project-subtitle">Resultados y Medición de Impacto ODS</p>
            </div>
          </div>
          
          <div className="header-stats-row">
            <div className="header-stat-item">
              <span className="header-stat-label">Objetivo</span>
              <span className="header-stat-value">ODS {project.objective}</span>
            </div>
            <div className="header-stat-item">
              <span className="header-stat-label">Periodo</span>
              <span className="header-stat-value">{formatDate(project.startDate)} - {formatDate(project.endDate, false)}</span>
            </div>
            <div className="header-stat-item">
              <span className="header-stat-label">Estado</span>
              <span className={`role-tag ${project.status === 'completed' ? 'user' : 'admin'}`}>
                {project.status === 'completed' ? 'Finalizado' : 'En Medición'}
              </span>
            </div>
          </div>
        </div>
      </header>

      <main className="results-content">
        {error && (
          <div className="error-banner">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>
            <span>{error}</span>
          </div>
        )}

        <div className="results-grid">
          <section className="indicators-section">
            <h2>{results ? 'Análisis de Indicadores' : 'Ingresar Valores Finales'}</h2>
            
            {results ? (
              <div className="indicators-list-shared">
                {results.indicatorResults.map((result, index) => (
                  <IndicatorCard 
                    key={index}
                    {...result}
                    index={index + 1}
                    mode="view"
                  />
                ))}
              </div>
            ) : (
              <form onSubmit={handleSubmit} className="modern-form">
                <div className="indicators-list-shared">
                  {project.indicators.map((indicator, index) => (
                    <IndicatorCard 
                      key={index}
                      indicator={indicator}
                      targetValue={project.targetValues[indicator]}
                      mode="input"
                      inputValue={formData[indicator] || ''}
                      onInputChange={handleInputChange}
                      index={index + 1}
                    />
                  ))}
                </div>
                <div className="form-actions-results">
                  <button type="button" className="btn-secondary" onClick={() => navigate('/dashboard')}>
                    Cancelar
                  </button>
                  <button type="submit" className="btn-primary" disabled={submitting}>
                    {submitting ? <span className="spinner"></span> : 'Finalizar Medición'}
                  </button>
                </div>
              </form>
            )}
          </section>

          <aside className="sidebar-results">
            {results && (
              <ResultsSummary 
                overallScore={results.overallScore}
                indicatorsAchieved={results.indicatorsAchieved}
                totalIndicators={results.totalIndicators}
              />
            )}

            <div className="action-card-results">
              <h4>Acciones del Proyecto</h4>
              <button className="btn-outline-full" onClick={() => window.print()}>
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><polyline points="6 9 6 2 18 2 18 9"></polyline><path d="M6 18H4a2 2 0 0 1-2-2v-5a2 2 0 0 1 2-2h16a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2h-2"></path><rect x="6" y="14" width="12" height="8"></rect></svg>
                Exportar Reporte
              </button>
              <button className="btn-outline-full" onClick={() => navigate('/dashboard')}>
                <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>
                Ir al Dashboard
              </button>
            </div>
          </aside>
        </div>
      </main>
    </div>
  );
};

export default ProjectResultsPage;
