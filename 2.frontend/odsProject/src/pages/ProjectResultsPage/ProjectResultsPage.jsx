import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { projectService } from '../../services/projectService';
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

      // ── Sprint 9: traer el "árbol completo" del proyecto desde la BD ─────
      // Antes la página esperaba project.indicators / project.indicatorConfigs /
      // project.objective / project.targetValues que solo existían en el
      // formulario de creación (era localStorage). Esa info ya no viaja
      // con getProjectById; ahora la armamos a partir de:
      //   1) GET /api/projects/{id}                → cabecera Proyectos
      //   2) GET /api/projects/{id}/ods            → ODS vinculados
      //   3) GET /api/ods/{XX}/indicadores         → indicadores del proyecto
      //   4) GET /api/ods/{XX}/metas               → parámetros de cada indicador
      // ─────────────────────────────────────────────────────────────────────

      const headerRes = await projectService.getProjectById(projectId);
      if (!headerRes.success || !headerRes.data) {
        setError('Proyecto no encontrado');
        return;
      }
      const currentProject = headerRes.data;

      // ODS vinculados (proyecto_ods)
      const odsRes = await projectService.getOdsByProyecto(projectId);
      const linkedOdsRaw = odsRes.success ? odsRes.data : [];

      // Por cada ODS, importar dinámicamente su servicio y pedir
      // indicadores + parámetros del proyecto. En paralelo.
      const linkedOds = (await Promise.all(
        linkedOdsRaw.map(async odsLink => {
          const odsId = parseInt(odsLink.ods_id ?? odsLink.odsId);
          if (!odsId || Number.isNaN(odsId)) return null;
          const padded = String(odsId).padStart(2, '0');

          let svc;
          try {
            const mod = await import(`../../services/objetivo${padded}Service.js`);
            svc = mod.default || mod[`objetivo${padded}Service`];
          } catch (e) {
            console.warn(`[ProjectResultsPage] sin servicio para ODS ${padded}`, e);
            return null;
          }
          if (!svc?.getIndicators) return null;

          // La vista admin_detalle_indicadores hace LEFT JOIN entre todos los
          // master indicators y los del proyecto. Los que pertenecen al
          // proyecto son los que tienen fórmula o meta cargada.
          const indicatorsMap = await svc.getIndicators(parseInt(projectId));
          const indicators = Object.values(indicatorsMap)
            .filter(ind => ind && (
              (ind.formula && ind.formula.trim().length > 0) ||
              (typeof ind.targetValue === 'number' && ind.targetValue > 0) ||
              ind.currentValue != null ||
              ind.hasData
            ))
            .sort((a, b) => String(a.code).localeCompare(String(b.code)));

          // Parámetros (proyecto_indicador_parametros)
          let parameters = [];
          if (svc.getMetasProyecto) {
            try {
              const mp = await svc.getMetasProyecto(parseInt(projectId));
              parameters = mp?.data || mp || [];
            } catch (e) {
              console.warn(`[ProjectResultsPage] error parámetros ODS ${padded}`, e);
            }
          }

          return {
            odsId,
            esPrimario: !!(odsLink.es_primario ?? odsLink.esPrimario),
            fechaVinculacion: odsLink.fecha_vinculacion ?? odsLink.fechaVinculacion,
            indicators,
            parameters
          };
        })
      )).filter(Boolean);

      // Ordenar: primario primero, luego por número de ODS
      linkedOds.sort((a, b) => {
        if (a.esPrimario && !b.esPrimario) return -1;
        if (!a.esPrimario && b.esPrimario) return 1;
        return a.odsId - b.odsId;
      });

      setProject({
        ...currentProject,
        linkedOds,
        // Mantenemos campos legacy para que el resto de la página no rompa
        objective: linkedOds.find(o => o.esPrimario)?.odsId ?? linkedOds[0]?.odsId ?? currentProject.objective,
        indicators: linkedOds.flatMap(o => o.indicators.map(i => i.code))
      });
    } catch (err) {
      console.error('[ProjectResultsPage] fetchProject error', err);
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

  if (error || !project) {
    return (
      <div className="project-results-page premium-view">
        <div className="error-container fade-in">
          <h2>Error al cargar el proyecto</h2>
          <p>{error || 'El proyecto solicitado no está disponible.'}</p>
          <button className="btn-primary" onClick={() => navigate('/dashboard')}>
            Volver al Dashboard
          </button>
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
            {project.linkedOds && project.linkedOds.length > 0
              ? `${project.linkedOds.length} ODS Vinculado${project.linkedOds.length !== 1 ? 's' : ''}`
              : 'Sin ODS vinculados'}
          </div>
        </div>

        {/* ── Sprint 9: iterar TODOS los ODS vinculados, no solo el primario ── */}
        <div className="ods-impact-list">
          {project.linkedOds && project.linkedOds.length > 0 ? (
            project.linkedOds.map(ods => {
              // ── Para cada indicador, extraer variables de su fórmula y casar
              //    los parámetros del ODS por nombre_variable. La API del view
              //    de indicadores no expone proyecto_indicador_id, así que
              //    matcheamos por las variables que aparecen en la fórmula.
              const RESERVED = new Set([
                'sqrt','sin','cos','tan','log','exp','round','floor','ceil','abs',
                'pi','e','valor','count'
              ]);
              const extractVars = (formula) => {
                if (!formula) return new Set();
                const matches = String(formula).match(/[a-zA-Z_][a-zA-Z0-9_]*/g) || [];
                return new Set(matches.filter(v => !RESERVED.has(v.toLowerCase())));
              };

              const paramsByIndicatorCode = {};
              for (const ind of ods.indicators) {
                paramsByIndicatorCode[ind.code] = [];
              }
              const assigned = new Set();
              for (const ind of ods.indicators) {
                const vars = extractVars(ind.formula);
                for (const p of (ods.parameters || [])) {
                  if (assigned.has(p.id)) continue;
                  const varName = p.nombreVariable || p.nombreParametro;
                  if (vars.has(varName)) {
                    paramsByIndicatorCode[ind.code].push(p);
                    assigned.add(p.id);
                  }
                }
              }
              // Parámetros sin match (caso raro): los pegamos al primer indicador
              const unmatched = (ods.parameters || []).filter(p => !assigned.has(p.id));
              if (unmatched.length > 0 && ods.indicators[0]) {
                paramsByIndicatorCode[ods.indicators[0].code].push(...unmatched);
              }

              return (
                <div key={ods.odsId} className="ods-impact-item" style={{ marginBottom: 24 }}>
                  <div className="ods-summary-header">
                    <div
                      className="ods-number-box"
                      style={{ backgroundColor: getOdsColor(ods.odsId) }}
                    >
                      {ods.odsId}
                    </div>
                    <span className="ods-full-name">
                      Objetivo {ods.odsId}: {getObjectiveName(ods.odsId)}
                      {ods.esPrimario && (
                        <span style={{
                          marginLeft: 10, fontSize: 11, fontWeight: 600,
                          padding: '2px 8px', borderRadius: 99,
                          background: '#fef3c7', color: '#92400e'
                        }}>
                          PRIMARIO
                        </span>
                      )}
                    </span>
                  </div>

                  {ods.indicators && ods.indicators.length > 0 ? (
                    <div className="indicators-grid-layout" style={{ display: 'grid', gap: 14, marginTop: 14 }}>
                      {ods.indicators.map((ind, idx) => {
                        const params = paramsByIndicatorCode[ind.code] || [];

                        return (
                          <div
                            key={`${ods.odsId}-${ind.code || idx}`}
                            style={{
                              background: '#fff', border: '1px solid #e5e7eb',
                              borderRadius: 10, padding: 16
                            }}
                          >
                            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start', gap: 10 }}>
                              <div style={{ flex: 1 }}>
                                <div style={{
                                  fontFamily: 'monospace', fontSize: 11,
                                  color: '#888', marginBottom: 4
                                }}>
                                  {ind.code}
                                </div>
                                <div style={{ fontWeight: 600, fontSize: 14, color: '#111' }}>
                                  {ind.name}
                                </div>
                              </div>
                              {typeof ind.targetValue === 'number' && ind.targetValue > 0 && (
                                <div style={{
                                  padding: '4px 10px', borderRadius: 99,
                                  background: '#eef2ff', color: '#3b5bdb',
                                  fontSize: 12, fontWeight: 600, whiteSpace: 'nowrap'
                                }}>
                                  Meta: {ind.targetValue} {ind.unit || ''}
                                </div>
                              )}
                            </div>

                            {ind.formula && ind.formula.trim() !== '' && (
                              <div style={{
                                marginTop: 10, padding: '8px 12px',
                                background: '#f0f4ff', borderRadius: 6,
                                fontFamily: 'monospace', fontSize: 13,
                                color: '#3b5bdb'
                              }}>
                                <span style={{
                                  fontSize: 10, color: '#5577dd',
                                  textTransform: 'uppercase', letterSpacing: '0.06em',
                                  marginRight: 8
                                }}>
                                  Fórmula:
                                </span>
                                {ind.formula}
                              </div>
                            )}

                            {/* Parámetros del indicador (variables de la fórmula) */}
                            {params.length > 0 && (
                              <div style={{ marginTop: 10 }}>
                                <div style={{
                                  fontSize: 11, color: '#666',
                                  textTransform: 'uppercase', letterSpacing: '0.06em',
                                  marginBottom: 6
                                }}>
                                  Variables ({params.length})
                                </div>
                                <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap' }}>
                                  {params.map(p => (
                                    <div
                                      key={p.id}
                                      style={{
                                        display: 'inline-flex', alignItems: 'center', gap: 6,
                                        padding: '4px 10px', borderRadius: 6,
                                        background: '#f3f4f6', fontSize: 12
                                      }}
                                    >
                                      <code style={{
                                        fontFamily: 'monospace', fontWeight: 600,
                                        color: '#3b5bdb'
                                      }}>
                                        {p.nombreVariable || p.nombreParametro}
                                      </code>
                                      <span style={{ color: '#888' }}>·</span>
                                      <span style={{ color: '#555' }}>{p.tipoDato}</span>
                                      <span style={{ color: '#888' }}>·</span>
                                      <span style={{ color: '#555', fontVariantNumeric: 'tabular-nums' }}>
                                        actual: {p.valorActual ?? 0}
                                      </span>
                                    </div>
                                  ))}
                                </div>
                              </div>
                            )}

                            {ind.currentValue != null && (
                              <div style={{ marginTop: 8, fontSize: 12, color: '#555' }}>
                                Valor calculado actual:&nbsp;
                                <strong style={{ color: '#111' }}>
                                  {ind.currentValue} {ind.unit || ''}
                                </strong>
                              </div>
                            )}
                          </div>
                        );
                      })}
                    </div>
                  ) : (
                    <div style={{
                      padding: 14, color: '#888', fontSize: 13,
                      background: '#fafafa', borderRadius: 8, marginTop: 10
                    }}>
                      Sin indicadores cargados todavía en este ODS.
                    </div>
                  )}
                </div>
              );
            })
          ) : (
            <div style={{ padding: 20, textAlign: 'center', color: '#888' }}>
              Este proyecto aún no tiene ODS vinculados.
            </div>
          )}
        </div>

        {/* Botón hacia la página de evaluación para cargar valores */}
        <div style={{ marginTop: 20, display: 'flex', justifyContent: 'flex-end' }}>
          <button
            className="btn-primary"
            onClick={() => navigate(`/projects/${projectId}/evaluation`)}
            style={{
              padding: '10px 18px', background: '#3b5bdb', color: '#fff',
              border: 'none', borderRadius: 8, cursor: 'pointer', fontSize: 14
            }}
          >
            Ir a evaluación →
          </button>
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
