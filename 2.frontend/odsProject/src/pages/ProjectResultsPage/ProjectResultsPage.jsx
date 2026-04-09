import { 
  FileText, 
  MapPin, 
  Target, 
  Calendar, 
  CheckCircle2, 
  Download, 
  ArrowLeft,
  LayoutGrid,
  Building
} from 'lucide-react';
import { 
  formatDate, 
  formatIndicatorName, 
  getAchievementClass,
  getObjectiveName,
  getOdsColor 
} from '../../utils/formatters';
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
    updateProjectResults,
    calculateIndicatorAchievement
  } = useProjects();
  
  const [project, setProject] = useState(null);
  const [formData, setFormData] = useState({});
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
  const [results, setResults] = useState(null);
  const [paramValues, setParamValues] = useState({});

  useEffect(() => {
    fetchProject();
  }, [projectId]);

  const fetchProject = async () => {
    try {
      setLoading(true);
      // Usar el ID del usuario actual para filtrar
      const userProjects = await fetchUserProjects(user?.id);
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
        const initialParamValues = {};
        
        currentProject.indicators.forEach(indicator => {
          initialFormData[indicator] = '';
          
          const config = currentProject.indicatorConfigs?.[indicator];
          if (config && config.parameters) {
            initialParamValues[indicator] = {};
            config.parameters.forEach(p => {
              initialParamValues[indicator][p.name] = '';
            });
          }
        });
        
        setFormData(initialFormData);
        setParamValues(initialParamValues);
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

  const handleParamChange = (indicator, paramName, value) => {
    setParamValues(prev => ({
      ...prev,
      [indicator]: {
        ...(prev[indicator] || {}),
        [paramName]: value
      }
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');

    try {
      const finalValues = { ...formData };
      
      if (project.indicatorConfigs) {
        Object.keys(project.indicatorConfigs).forEach(indicator => {
          const config = project.indicatorConfigs[indicator];
          if (config && config.formula && paramValues[indicator]) {
            const { value } = calculateIndicatorAchievement(config, paramValues[indicator]);
            finalValues[indicator] = value;
          }
        });
      }

      const resultsData = {
        projectId: project.id,
        finalValues: finalValues,
        paramValues: paramValues
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
      <div className="global-loader-container">
        <div className="loader"></div>
        <div className="loader-content">
          <p>Generando reporte de impacto...</p>
          <span className="loader-subtext">Analizando métricas ODS vinculadas</span>
        </div>
      </div>
    );
  }

  return (
    <div className="project-results-page premium-view fade-in">
      <header className="premium-nav">
        <div className="container nav-content">
          <div className="nav-left">
            <button className="btn-back-square" onClick={() => navigate('/dashboard')}>
              <ArrowLeft size={20} />
            </button>
            <div className="nav-title-group">
              <div className="nav-icon-box blue">
                <Building size={20} />
              </div>
              <div>
                <h1>Detalle del Reporte</h1>
                <p>Análisis exhaustivo de impacto</p>
              </div>
            </div>
          </div>
          <div className="nav-right">
            <button className="btn-export-csv" onClick={() => window.print()}>
              <Download size={16} />
              Exportar Reporte
            </button>
          </div>
        </div>
      </header>

      <main className="premium-main">
        <div className="report-grid">
          {/* Card 1: Información General */}
          <section className="report-card info-card">
            <div className="card-header">
              <FileText size={20} className="icon-blue" />
              <h2>Información General</h2>
            </div>
            
            <div className="card-body vertical-gap">
              <div className="data-group">
                <label>Nombre del Proyecto</label>
                <h3>{project.name}</h3>
              </div>

              <div className="data-group">
                <label className="flex-items">
                  <Target size={14} /> Objetivo General
                </label>
                <div className="description-box">
                  {project.description || 'Sin descripción detallada disponible.'}
                </div>
              </div>

              <div className="date-grid">
                <div className="date-item">
                  <label>
                    <Calendar size={14} /> Inicio
                  </label>
                  <div className="date-status">
                    <span className="dot green"></span>
                    <span className="date-text">{formatDate(project.startDate)}</span>
                  </div>
                </div>
                <div className="date-item">
                  <label>
                    <Calendar size={14} /> Finalización
                  </label>
                  <div className="date-status">
                    <span className="dot red"></span>
                    <span className="date-text">{formatDate(project.endDate, false)}</span>
                  </div>
                </div>
              </div>
            </div>
          </section>

          {/* Card 2: Ubicación */}
          <section className="report-card location-card">
            <div className="card-header">
              <MapPin size={20} className="icon-green" />
              <h2>Ubicación Geográfica</h2>
            </div>

            <div className="card-body location-stack">
              <div className="location-pill province">
                <label>Provincia</label>
                <span>{project.provinciaNombre || 'San José'}</span>
              </div>
              <div className="location-pill canton">
                <label>Cantón</label>
                <span>{project.cantonNombre || 'Central'}</span>
              </div>
              <div className="location-pill district">
                <label>Distrito</label>
                <span>{project.distritoNombre || 'Carmen'}</span>
              </div>
            </div>
          </section>
        </div>

      <div className="indicators-report-section">
        <div className="section-header-row">
          <div className="header-subtitle-group">
            <CheckCircle2 size={20} className="icon-amber" />
            <h2>Objetivos de Desarrollo Sostenible</h2>
          </div>
          <div className="ods-count-badge">
            {project.objective ? '1 ODS Vinculado' : 'Sin ODS vinculados'}
          </div>
        </div>

        <div className="ods-impact-list">
          {/* En este proyecto, el objetivo es el ODS principal */}
          <div className="ods-impact-item">
            <div className="ods-summary-header">
              <div 
                className="ods-number-box" 
                style={{ backgroundColor: getOdsColor(project.objective) }}
              >
                {project.objective}
              </div>
              <span className="ods-full-name">Objetivo {project.objective}: {getObjectiveName(project.objective)}</span>
            </div>

            <div className="indicators-grid-layout">
              {results ? (
                results.indicatorResults.map((result, index) => (
                  <div key={index} className="indicator-report-wrapper">
                    <IndicatorCard 
                      {...result}
                      index={index + 1}
                      mode="view"
                      config={project.indicatorConfigs?.[result.indicator]}
                    />
                  </div>
                ))
              ) : (
                <form onSubmit={handleSubmit} className="modern-form">
                  <div className="indicators-entry-list">
                    {project.indicators.map((indicator, index) => {
                      const config = project.indicatorConfigs?.[indicator];
                      const { value, achievement } = calculateIndicatorAchievement(config, paramValues[indicator] || {});
                      
                      return (
                        <IndicatorCard 
                          key={index}
                          indicator={indicator}
                          targetValue={project.targetValues[indicator]}
                          mode="input"
                          inputValue={formData[indicator] || ''}
                          onInputChange={handleInputChange}
                          index={index + 1}
                          config={config}
                          paramValues={paramValues[indicator] || {}}
                          onParamChange={handleParamChange}
                          calculatedValue={value}
                          currentAchievement={achievement}
                        />
                      );
                    })}
                  </div>
                  <div className="form-submit-footer">
                    <button type="button" className="btn-secondary-flat" onClick={() => navigate('/dashboard')}>
                      Cancelar
                    </button>
                    <button type="submit" className="btn-primary-glow" disabled={submitting}>
                      {submitting ? <span className="spinner"></span> : 'Publicar Medición de Impacto'}
                    </button>
                  </div>
                </form>
              )}
            </div>
          </div>
        </div>
      </div>

      {results && (
        <div className="report-footer">
          <button className="btn-print-full" onClick={() => window.print()}>
            <Download size={18} />
            Descargar Reporte Completo (PDF)
          </button>
        </div>
      )}
      </main>
    </div>
  );
};

export default ProjectResultsPage;
