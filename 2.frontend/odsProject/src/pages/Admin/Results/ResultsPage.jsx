import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../../../hooks/useAuth.jsx';
import { useProjects } from '../../../hooks/useProjects.jsx';
import { formatDate } from '../../../utils/formatters';
import IndicatorCard from '../../../components/projects/IndicatorCard/IndicatorCard';
import ResultsSummary from '../../../components/projects/ResultsSummary/ResultsSummary';
import './ResultsPage.css';

const AdminResultsReviewPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { projectId } = useParams();
  const { 
    loading: projectsLoading, 
    fetchAdminProjects, 
    getProjectResults 
  } = useProjects();
  
  const [project, setProject] = useState(null);
  const [results, setResults] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProjectResults();
  }, [projectId]);

  const fetchProjectResults = async () => {
    try {
      setLoading(true);
      const adminProjects = await fetchAdminProjects();
      const currentProject = adminProjects.find(p => p.id === parseInt(projectId));
      
      if (!currentProject) {
        setError('Proyecto no encontrado');
        return;
      }
      
      setProject(currentProject);
      
      if (currentProject.status === 'completed' || currentProject.status === 'completado') {
        const resultsData = await getProjectResults(currentProject.id, currentProject.objective);
        setResults(resultsData);
      } else {
        setError('Este proyecto aún no ha sido completado por el usuario');
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  if (loading || projectsLoading) {
    return (
      <div className="dashboard-loading">
        <div className="loader"></div>
        <p>Generando reporte detallado...</p>
      </div>
    );
  }

  return (
    <div className="admin-review-page fade-in">
      <header className="review-header">
        <div className="review-header-container">
          <div className="review-title-area">
            <h1>Revisión de Resultados</h1>
            <p className="admin-subtitle">ID Proyecto: #{projectId}</p>
          </div>
          <button onClick={() => navigate('/admin/projects')} className="btn-secondary">
            Volver a Proyectos
          </button>
        </div>
      </header>

      <main className="review-content">
        {error && (
          <div className="error-banner">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>
            <span>{error}</span>
          </div>
        )}

        <section className="project-info-card">
          <div className="header-with-badge">
            <span className="role-tag admin">EXPEDIENTE PROYECTO</span>
            <h3>{project?.name}</h3>
          </div>
          
          <div className="info-grid-review">
            <div className="info-item">
              <h5>Responsable</h5>
              <p>{project?.userName}</p>
            </div>
            <div className="info-item">
              <h5>Objetivo ODS</h5>
              <p>ODS {project?.objective}</p>
            </div>
            <div className="info-item">
              <h5>Periodo de Ejecución</h5>
              <p>{formatDate(project?.startDate)} - {formatDate(project?.endDate)}</p>
            </div>
          </div>
        </section>

        {results && (
          <div className="results-analysis-section">
            <h2>Análisis de Resultados por Indicador</h2>
            <div className="indicators-group-shared">
              {results.indicatorResults.map((result, index) => (
                <IndicatorCard 
                  key={index}
                  {...result}
                  index={index + 1}
                  mode="view"
                />
              ))}
            </div>

            <section className="summary-section-admin">
              <ResultsSummary 
                overallScore={results.overallScore}
                indicatorsAchieved={results.indicatorsAchieved}
                totalIndicators={results.totalIndicators}
                title="Impacto Global del Proyecto"
              />
            </section>
          </div>
        )}
      </main>
    </div>
  );
};

export default AdminResultsReviewPage;
