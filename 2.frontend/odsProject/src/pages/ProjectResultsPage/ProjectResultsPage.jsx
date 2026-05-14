import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { projectService } from '../../services/projectService';
import { usePermissions } from '../../hooks/usePermissions';
import {
  FileText, MapPin, Target, Calendar, CheckCircle2,
  Download, ArrowLeft, Building, ClipboardCheck
} from 'lucide-react';
import { formatDate, getObjectiveName, getOdsColor } from '../../utils/formatters';
import EvidenceSection from '../../components/projects/EvidenceSection';
import AchievementBadge, { deriveEstado } from '../../components/AchievementBadge';
import './ProjectResultsPage.css';

/**
 * Sprint 9 + 10 + 12 + 14 — Detalle del proyecto, adaptado por rol:
 *  - todos: cabecera + ODS + indicadores + fórmulas + estado de meta
 *  - gestor (dueño): zona de subida de documentos
 *  - admin/auditor: botón "Auditar este proyecto" → /audit/:id
 *  - consultor: solo lectura, ve el estado de meta auditado
 */
const ProjectResultsPage = () => {
  const { user } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();
  const { projectId } = useParams();

  const [project, setProject] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError]     = useState('');

  useEffect(() => { fetchProjectFull(); }, [projectId]);

  const fetchProjectFull = async () => {
    setLoading(true);
    try {
      const headerRes = await projectService.getProjectById(projectId);
      if (!headerRes.success || !headerRes.data) {
        setError('Proyecto no encontrado'); return;
      }
      const currentProject = headerRes.data;

      const odsRes = await projectService.getOdsByProyecto(projectId);
      const linkedOdsRaw = odsRes.success ? odsRes.data : [];

      const linkedOds = (await Promise.all(
        linkedOdsRaw.map(async odsLink => {
          const odsId = parseInt(odsLink.ods_id ?? odsLink.odsId);
          if (!odsId || Number.isNaN(odsId)) return null;
          const padded = String(odsId).padStart(2, '0');
          let svc;
          try {
            const mod = await import(`../../services/objetivo${padded}Service.js`);
            svc = mod.default || mod[`objetivo${padded}Service`];
          } catch { return null; }
          if (!svc?.getIndicators) return null;

          const indicatorsMap = await svc.getIndicators(parseInt(projectId));
          // El service ya mapea pero NO preserva proyectoId del view. Filtramos
          // por evidencia de pertenencia: tiene fórmula, meta > 0, o currentValue.
          const indicators = Object.values(indicatorsMap)
            .filter(ind => ind && (
              (ind.formula && ind.formula.trim().length > 0) ||
              (typeof ind.targetValue === 'number' && ind.targetValue > 0) ||
              ind.currentValue != null || ind.hasData
            ))
            .map(ind => ({
              ...ind,
              // % de logro: si tenemos currentValue y targetValue
              porcentajeLogro: (ind.currentValue != null && ind.targetValue > 0)
                ? Math.min((Number(ind.currentValue) / Number(ind.targetValue)) * 100, 200)
                : null,
              estado: ind.estadoIndicador ||
                (ind.currentValue != null && ind.targetValue > 0
                  ? deriveEstado((Number(ind.currentValue) / Number(ind.targetValue)) * 100)
                  : 'SIN DATOS')
            }))
            .sort((a, b) => String(a.code).localeCompare(String(b.code)));

          let parameters = [];
          if (svc.getMetasProyecto) {
            try {
              const mp = await svc.getMetasProyecto(parseInt(projectId));
              parameters = mp?.data || mp || [];
            } catch {}
          }

          return {
            odsId,
            esPrimario: !!(odsLink.es_primario ?? odsLink.esPrimario),
            indicators, parameters
          };
        })
      )).filter(Boolean);

      linkedOds.sort((a, b) => {
        if (a.esPrimario && !b.esPrimario) return -1;
        if (!a.esPrimario && b.esPrimario) return 1;
        return a.odsId - b.odsId;
      });

      // ── Agregado de logro del proyecto: promedio de % de los indicadores con dato
      const allInds  = linkedOds.flatMap(o => o.indicators);
      const auditados = allInds.filter(i => i.porcentajeLogro != null);
      const pctProyecto = auditados.length > 0
        ? auditados.reduce((s, i) => s + i.porcentajeLogro, 0) / auditados.length
        : null;

      setProject({
        ...currentProject,
        linkedOds,
        objective: linkedOds.find(o => o.esPrimario)?.odsId ?? linkedOds[0]?.odsId ?? currentProject.objective,
        pctProyecto,
        totalIndicadores: allInds.length,
        auditados: auditados.length
      });
    } catch (err) {
      console.error('[ProjectResultsPage]', err);
      setError(err.message || 'Error cargando proyecto');
    } finally {
      setLoading(false);
    }
  };

  if (loading) return (
    <div className="global-loader-container"><div className="loader"></div>
      <div className="loader-content"><p>Cargando proyecto...</p></div></div>
  );
  if (error || !project) return (
    <div className="project-results-page premium-view">
      <div className="error-container fade-in">
        <h2>Error al cargar el proyecto</h2>
        <p>{error || 'No disponible.'}</p>
        <button className="btn-primary" onClick={() => navigate('/dashboard')}>Volver</button>
      </div>
    </div>
  );

  // Helper: parámetros agrupados por indicador (matching por variable de la fórmula)
  const matchParamsToIndicator = (indicator, allParams) => {
    if (!indicator.formula) return [];
    const RESERVED = new Set(['sqrt','sin','cos','tan','log','exp','round','floor','ceil','abs','pi','e','valor','count']);
    const vars = new Set((indicator.formula.match(/[a-zA-Z_][a-zA-Z0-9_]*/g) || [])
      .filter(v => !RESERVED.has(v.toLowerCase())));
    return (allParams || []).filter(p => vars.has(p.nombreVariable || p.nombreParametro));
  };

  return (
    <div className="project-results-page premium-view fade-in">
      <header className="premium-nav">
        <div className="container nav-content">
          <div className="nav-left">
            <button className="btn-back-square" onClick={() => navigate('/projects')}>
              <ArrowLeft size={20} />
            </button>
            <div className="nav-title-group">
              <div className="nav-icon-box blue"><Building size={20} /></div>
              <div>
                <h1>Detalle del Proyecto</h1>
                <p>{perms.roleLabel}</p>
              </div>
            </div>
          </div>
          <div className="nav-right">
            {/* Sprint 14: botón AUDITAR para admin/auditor */}
            {perms.canEnterMeasurements && (
              <button
                type="button"
                className="btn-audit"
                onClick={() => navigate(`/audit/${projectId}`)}
              >
                <ClipboardCheck size={16} /> Auditar este proyecto
              </button>
            )}
          </div>
        </div>
      </header>

      <main className="premium-main">
        <div className="report-grid">
          {/* Card 1 — Info general */}
          <section className="report-card info-card">
            <div className="card-header"><FileText size={20} className="icon-blue" /><h2>Información General</h2></div>
            <div className="card-body vertical-gap">
              <div className="data-group">
                <label>Nombre del Proyecto</label>
                <h3>{project.name}</h3>
              </div>
              <div className="data-group">
                <label className="flex-items"><Target size={14} /> Descripción</label>
                <div className="description-box">{project.description || 'Sin descripción'}</div>
              </div>
              <div className="date-grid">
                <div className="date-item">
                  <label><Calendar size={14} /> Inicio</label>
                  <div className="date-status"><span className="dot green"></span>
                    <span className="date-text">{formatDate(project.startDate)}</span></div>
                </div>
                <div className="date-item">
                  <label><Calendar size={14} /> Finalización</label>
                  <div className="date-status"><span className="dot red"></span>
                    <span className="date-text">{formatDate(project.endDate, false)}</span></div>
                </div>
              </div>
            </div>
          </section>

          {/* Card 2 — Estado de logro del proyecto (Sprint 14) */}
          <section className="report-card" style={{background:'#fff'}}>
            <div className="card-header">
              <CheckCircle2 size={20} className="icon-amber" />
              <h2>Estado de logro</h2>
            </div>
            <div className="card-body" style={{textAlign:'center',padding:'20px 14px'}}>
              {project.totalIndicadores === 0 ? (
                <div style={{color:'#888'}}>Aún no hay indicadores cargados.</div>
              ) : project.auditados === 0 ? (
                <>
                  <AchievementBadge estado="SIN DATOS" size="lg" showPct={false} />
                  <div style={{marginTop:10,color:'#888',fontSize:13}}>
                    Pendiente de auditoría<br/>
                    <span style={{fontSize:11}}>{project.totalIndicadores} indicador(es) cargado(s)</span>
                  </div>
                </>
              ) : (
                <>
                  <AchievementBadge porcentaje={project.pctProyecto} size="lg" />
                  <div style={{marginTop:10,color:'#666',fontSize:13}}>
                    {project.auditados} de {project.totalIndicadores} indicador(es) auditado(s)
                  </div>
                </>
              )}
            </div>
          </section>
        </div>

        {/* ODS vinculados con sus indicadores */}
        <div className="indicators-report-section">
          <div className="section-header-row">
            <div className="header-subtitle-group">
              <CheckCircle2 size={20} className="icon-amber" />
              <h2>Objetivos de Desarrollo Sostenible</h2>
            </div>
            <div className="ods-count-badge">
              {project.linkedOds.length > 0
                ? `${project.linkedOds.length} ODS Vinculado${project.linkedOds.length !== 1 ? 's' : ''}`
                : 'Sin ODS vinculados'}
            </div>
          </div>

          <div className="ods-impact-list">
            {project.linkedOds.length === 0 ? (
              <div style={{padding:20,textAlign:'center',color:'#888'}}>Este proyecto aún no tiene ODS vinculados.</div>
            ) : project.linkedOds.map(ods => (
              <div key={ods.odsId} className="ods-impact-item" style={{marginBottom:24}}>
                <div className="ods-summary-header">
                  <div className="ods-number-box" style={{backgroundColor:getOdsColor(ods.odsId)}}>{ods.odsId}</div>
                  <span className="ods-full-name">
                    Objetivo {ods.odsId}: {getObjectiveName(ods.odsId)}
                    {ods.esPrimario && (
                      <span style={{marginLeft:10,fontSize:11,fontWeight:600,padding:'2px 8px',
                                    borderRadius:99,background:'#fef3c7',color:'#92400e'}}>PRIMARIO</span>
                    )}
                  </span>
                </div>

                {ods.indicators.length === 0 ? (
                  <div style={{padding:14,color:'#888',fontSize:13,background:'#fafafa',
                               borderRadius:8,marginTop:10}}>Sin indicadores cargados.</div>
                ) : (
                  <div className="indicators-grid-layout" style={{display:'grid',gap:14,marginTop:14}}>
                    {ods.indicators.map((ind, idx) => {
                      const params = matchParamsToIndicator(ind, ods.parameters);
                      return (
                        <div key={`${ods.odsId}-${ind.code || idx}`}
                             style={{background:'#fff',border:'1px solid #e5e7eb',borderRadius:10,padding:16}}>
                          <div style={{display:'flex',justifyContent:'space-between',alignItems:'flex-start',gap:10}}>
                            <div style={{flex:1}}>
                              <div style={{fontFamily:'monospace',fontSize:11,color:'#888',marginBottom:4}}>{ind.code}</div>
                              <div style={{fontWeight:600,fontSize:14,color:'#111'}}>{ind.name}</div>
                            </div>
                            <AchievementBadge
                              estado={ind.estado}
                              porcentaje={ind.porcentajeLogro}
                              size="sm" />
                          </div>

                          <div style={{display:'flex',gap:8,marginTop:10,flexWrap:'wrap'}}>
                            {typeof ind.targetValue === 'number' && ind.targetValue > 0 && (
                              <div style={{padding:'4px 10px',borderRadius:6,background:'#eef2ff',
                                           color:'#012169',fontSize:12,fontWeight:600}}>
                                Meta: {ind.targetValue} {ind.unit || ''}
                              </div>
                            )}
                            {ind.currentValue != null && (
                              <div style={{padding:'4px 10px',borderRadius:6,background:'#f0fdf4',
                                           color:'#166534',fontSize:12,fontWeight:600}}>
                                Actual: {Number(ind.currentValue).toFixed(2)} {ind.unit || ''}
                              </div>
                            )}
                          </div>

                          {ind.formula && ind.formula.trim() !== '' && (
                            <div style={{marginTop:10,padding:'8px 12px',background:'#f0f4ff',
                                         borderRadius:6,fontFamily:'monospace',fontSize:13,color:'#012169'}}>
                              <span style={{fontSize:10,color:'#5577dd',textTransform:'uppercase',
                                            letterSpacing:'0.06em',marginRight:8}}>Fórmula:</span>
                              {ind.formula}
                            </div>
                          )}

                          {params.length > 0 && (
                            <div style={{marginTop:10}}>
                              <div style={{fontSize:11,color:'#666',textTransform:'uppercase',
                                           letterSpacing:'0.06em',marginBottom:6}}>
                                Variables ({params.length})
                              </div>
                              <div style={{display:'flex',gap:8,flexWrap:'wrap'}}>
                                {params.map(p => (
                                  <div key={p.id} style={{display:'inline-flex',alignItems:'center',gap:6,
                                                            padding:'4px 10px',borderRadius:6,
                                                            background:'#f3f4f6',fontSize:12}}>
                                    <code style={{fontFamily:'monospace',fontWeight:600,color:'#012169'}}>
                                      {p.nombreVariable || p.nombreParametro}
                                    </code>
                                    <span style={{color:'#888'}}>·</span>
                                    <span style={{color:'#555'}}>{p.tipoDato}</span>
                                    <span style={{color:'#888'}}>·</span>
                                    <span style={{color:'#555',fontVariantNumeric:'tabular-nums'}}>
                                      {p.valorActual ?? 0}
                                    </span>
                                  </div>
                                ))}
                              </div>
                            </div>
                          )}
                        </div>
                      );
                    })}
                  </div>
                )}
              </div>
            ))}
          </div>
        </div>

        {/* Sprint 12 — Documentos de evidencia */}
        <EvidenceSection project={project} />
      </main>
    </div>
  );
};

export default ProjectResultsPage;
