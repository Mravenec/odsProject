import React, { useState, useEffect } from 'react';
import { createPortal } from 'react-dom';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjectResultsDetail } from '../../hooks/useProjectResultsDetail';
import { useExport } from '../../hooks/useExport';
import { usePermissions } from '../../hooks/usePermissions';
import {
  FileText, MapPin, Target, Calendar, CheckCircle2,
  Download, ArrowLeft, Building, ClipboardCheck, Pencil
} from 'lucide-react';
import { usePlanificacionTransicion } from '../../hooks/usePlanificacionTransicion';
import { formatDate, formatUnitLabel, getObjectiveName, getOdsColor, isProjectCompletado, isEvaluationRejection } from '../../utils/formatters';
import { extractFormulaVarOrder, sortParamsByFormulaOrder } from '../../utils/formulaParamOrder';
import EvidenceSection from '../../components/projects/EvidenceSection';
import AchievementBadge from '../../components/AchievementBadge';
import ProjectChatPanel from '../../components/planificacion/ProjectChatPanel';
import PlanificacionTransicionBar from '../../components/planificacion/PlanificacionTransicionBar';
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

  const {
    project,
    loading,
    error,
    hasEvidenceDocs,
    fetchProjectFull,
    sendForEvaluation,
    refreshEvidenceStatus,
  } = useProjectResultsDetail(projectId);

  const { exporting, downloadProjectFullReport } = useExport();

  const [confirmModal, setConfirmModal] = useState({ show: false, message: '', onConfirm: null });
  const [alertModal, setAlertModal] = useState({ show: false, message: '', isError: false });

  const transicion = usePlanificacionTransicion(
    Number(projectId),
    user,
    project?.status,
    (opts) => fetchProjectFull(opts)
  );

  useEffect(() => { fetchProjectFull(); }, [fetchProjectFull]);

  // Tras error / no encontrado: redirigir al dashboard a los 5 s
  useEffect(() => {
    if (loading || (!error && project)) return undefined;
    const t = setTimeout(() => navigate('/dashboard', { replace: true }), 5000);
    return () => clearTimeout(t);
  }, [loading, error, project, navigate]);

  if (loading) return (
    <div className="global-loader-container"><div className="loader"></div>
      <div className="loader-content"><p>Cargando proyecto...</p></div></div>
  );
  if (error || !project) return (
    <div className="project-results-page premium-view">
      <div className="error-container fade-in">
        <h2>Error al cargar el proyecto</h2>
        <p>{error || 'No disponible.'}</p>
        <p className="error-redirect-hint" style={{ marginTop: '0.75rem', opacity: 0.75, fontSize: '0.9rem' }}>
          Redirigiendo al dashboard en 5 segundos…
        </p>
        <button className="btn-primary" onClick={() => navigate('/dashboard')}>Volver</button>
      </div>
    </div>
  );

  // Helper: parámetros del indicador en orden de aparición en la fórmula
  const matchParamsToIndicator = (indicator, allParams) => {
    if (!indicator.formula) return [];
    const vars = new Set(extractFormulaVarOrder(indicator.formula));
    const matched = (allParams || []).filter(p => vars.has(p.nombreVariable || p.nombreParametro));
    return sortParamsByFormulaOrder(indicator.formula, matched);
  };

  const handleDownloadExcel = async () => {
    const r = await downloadProjectFullReport(projectId);
    if (!r.success) {
      setAlertModal({ show: true, message: r.error || 'No se pudo descargar', isError: true });
    }
  };

  const hasIndicators = (project?.linkedOds || []).some((o) => (o.indicators || []).length > 0);
  const canSendToReview = hasIndicators && hasEvidenceDocs;
  const sendDisabledReason = !hasIndicators
    ? 'Configure al menos un indicador antes de enviar a evaluación'
    : !hasEvidenceDocs
      ? 'Suba al menos un documento de evidencia antes de enviar a evaluación'
      : '';

  const openSendForEvaluationConfirm = () => {
    if (!canSendToReview) return;
    setConfirmModal({
      show: true,
      message: '¿Enviar este proyecto a evaluación?\n\nDespués de enviar, no podrás modificar indicadores ni subir documentos hasta que el evaluador revise el proyecto.',
      onConfirm: async () => {
        const r = await sendForEvaluation(user.id);
        if (!r.success) {
          setAlertModal({ show: true, message: 'No se pudo enviar:\n' + r.error, isError: true });
          return;
        }
        setAlertModal({ show: true, message: 'Proyecto enviado a evaluación exitosamente.', isError: false });
        await fetchProjectFull({ silent: true });
      },
    });
  };

  const showSendForReviewButton = project
    && project.userId === user?.id
    && String(project.status || '').toLowerCase() === 'activo'
    && perms.canEditOwnProject;

  const showEvaluationRejectionBanner = showSendForReviewButton && isEvaluationRejection(project);

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
            {project && perms.canEditInPlanificacion(project) && (
              <button
                type="button"
                className="btn-export-excel"
                onClick={() => navigate(`/projects/${projectId}/planificacion/edit`)}
              >
                <Pencil size={16} /> Editar planificación
              </button>
            )}

            {showSendForReviewButton && (
              <button
                type="button"
                className="btn-send-for-review"
                disabled={!canSendToReview}
                title={sendDisabledReason || undefined}
                aria-disabled={!canSendToReview}
                onClick={openSendForEvaluationConfirm}
              >
                📤 Enviar a evaluación
              </button>
            )}

            {/* Sprint 17: botón AUDITAR para admin/auditor (Sprint 14 original).
               Solo visible si el proyecto está 'en_revision' (no antes, no después). */}
            {perms.canEnterMeasurements
              && String(project?.status||'').toLowerCase() === 'en_revision' && (
              <button
                type="button"
                className="btn-audit"
                onClick={() => navigate(`/evaluacion/${projectId}`)}
              >
                <ClipboardCheck size={16} /> Evaluar este proyecto
              </button>
            )}

            {perms.canDownloadEvidence && isProjectCompletado(project) && (
              <button
                type="button"
                className="btn-export-excel"
                onClick={handleDownloadExcel}
                disabled={exporting}
              >
                <Download size={16} />
                {exporting ? 'Generando…' : 'Descargar Excel resumen'}
              </button>
            )}
          </div>
        </div>
      </header>

      {project && String(project.status || '').toLowerCase() === 'planificacion'
        && user?.role === 'gestor' && project.userId === user?.id && (
        <ProjectChatPanel
          projectId={Number(projectId)}
          user={user}
          projectStatus={project.status}
          projectOwnerUserId={project.userId}
        />
      )}

      {project && transicion.isReviewer && transicion.solicitud?.estadoSolicitud === 'pendiente'
        && String(project.status || '').toLowerCase() === 'planificacion' && (
        <div style={{
          maxWidth: 'var(--container-max, 1200px)', margin: '1rem auto 0',
          padding: '1rem 1.25rem', background: '#eff6ff',
          border: '1px solid #bfdbfe', borderLeft: '4px solid #2563eb',
          borderRadius: 8, color: '#1e3a8a',
        }}>
          <div style={{ fontWeight: 800, fontSize: '0.78rem', textTransform: 'uppercase',
            letterSpacing: '0.1em', marginBottom: 4 }}>
            Revisión pendiente — verifique los cambios antes de aprobar
          </div>
          <div style={{ fontSize: '0.92rem', lineHeight: 1.5 }}>
            Transición solicitada hacia <strong>{transicion.solicitud.estadoDestino}</strong>.
            {transicion.solicitud.motivo && (
              <span> Motivo: {transicion.solicitud.motivo}</span>
            )}
            {' '}Use <strong>Editar planificación</strong> para validar fórmulas y metas actualizadas.
          </div>
        </div>
      )}

      {project && (
        <PlanificacionTransicionBar
          user={user}
          transicion={transicion}
          projectId={Number(projectId)}
        />
      )}

      {showEvaluationRejectionBanner && (
        <div style={{
          maxWidth:'var(--container-max, 1200px)',margin:'1rem auto 0',
          padding:'1rem 1.25rem',background:'#fffbeb',
          border:'1px solid #fef3c7',borderLeft:'4px solid #E9A23B',
          borderRadius:8,color:'#78350f'
        }}>
          <div style={{fontWeight:800,fontSize:'0.78rem',textTransform:'uppercase',
                       letterSpacing:'0.1em',color:'#854d0e',marginBottom:4}}>
            ⚠ La evaluación fue rechazada
          </div>
          <div style={{fontSize:'0.92rem',lineHeight:1.5}}>
            <strong>Motivo:</strong> {project.closureObservations}
          </div>
          <div style={{fontSize:'0.85rem',marginTop:8,color:'#92400e',lineHeight:1.5}}>
            Corregí lo indicado y volvé a enviar el proyecto a evaluación.
          </div>
          <button
            type="button"
            className="btn-send-for-review"
            disabled={!canSendToReview}
            title={sendDisabledReason || 'Reenviar a evaluación'}
            onClick={openSendForEvaluationConfirm}
            style={{ marginTop: '0.75rem' }}
          >
            📤 Reenviar a evaluación
          </button>
          {!canSendToReview && sendDisabledReason && (
            <p style={{ fontSize: '0.78rem', marginTop: '0.5rem', color: '#b45309' }}>
              {sendDisabledReason}
            </p>
          )}
        </div>
      )}

      {/* Sprint 20 — Panel de auditoría para consultor / admin / auditor
         visible cuando el proyecto YA fue cerrado (estado completado). */}
      {project && String(project.status||'').toLowerCase() === 'completado' && (
        <div style={{
          maxWidth:'var(--container-max, 1200px)',margin:'1rem auto 0',
          padding:'1rem 1.25rem',background:'#ecfdf5',
          border:'1px solid #dcfce7',borderLeft:'4px solid #1F9D55',
          borderRadius:8
        }}>
          <div style={{display:'flex',alignItems:'center',gap:8,
                       fontWeight:800,fontSize:'0.78rem',textTransform:'uppercase',
                       letterSpacing:'0.1em',color:'#166534',marginBottom:6}}>
            <CheckCircle2 size={14} /> Evaluación cerrada · datos firmados
          </div>
          <div style={{fontSize:'0.92rem',color:'#14532d',lineHeight:1.55}}>
            Evaluado por <strong>{project.auditedByName || `Usuario #${project.auditedBy}`}</strong>
            {project.auditedAt && <> el <strong>{formatDate(project.auditedAt)}</strong></>}.
            {project.closureObservations && (
              <div style={{marginTop:6,padding:'0.6rem 0.8rem',background:'#fff',
                           borderRadius:6,color:'#1B2440',fontSize:'0.88rem',fontStyle:'italic'}}>
                "{project.closureObservations}"
              </div>
            )}
          </div>
        </div>
      )}

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
                    Pendiente de evaluación<br/>
                    <span style={{fontSize:11}}>{project.totalIndicadores} indicador(es) cargado(s)</span>
                  </div>
                </>
              ) : (
                <>
                  <AchievementBadge porcentaje={project.pctProyecto} size="lg" />
                  <div style={{marginTop:10,color:'#666',fontSize:13}}>
                    {project.auditados} de {project.totalIndicadores} indicador(es) evaluado(s)
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
                                Meta: {ind.targetValue} {formatUnitLabel(ind.unit)}
                              </div>
                            )}
                            {ind.currentValue != null && (
                              <div style={{padding:'4px 10px',borderRadius:6,background:'#f0fdf4',
                                           color:'#166534',fontSize:12,fontWeight:600}}>
                                Valor actual: {Number(ind.currentValue).toFixed(2)} {formatUnitLabel(ind.unit)}
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
                                Parámetros ({params.length})
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
        <EvidenceSection project={project} onDocumentsChange={refreshEvidenceStatus} />
      </main>

      {/* Modales Personalizados */}
      {confirmModal.show && createPortal(
        <div style={{ position: 'fixed', top: 0, left: 0, right: 0, bottom: 0, background: 'rgba(0,0,0,0.6)', display: 'flex', alignItems: 'center', justifyContent: 'center', zIndex: 9999 }}>
          <div style={{ background: '#fff', padding: '24px', borderRadius: '12px', maxWidth: '400px', width: '90%', boxShadow: '0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)' }}>
            <h3 style={{ margin: '0 0 16px 0', color: '#1B2440', fontSize: '1.2rem', display: 'flex', alignItems: 'center', gap: '8px' }}>
              <div style={{ background: '#f0f4ff', color: '#012169', padding: '8px', borderRadius: '50%', display: 'flex' }}><Target size={20} /></div>
              Confirmar envío
            </h3>
            <p style={{ color: '#4b5563', fontSize: '0.95rem', lineHeight: '1.5', margin: '0 0 24px 0', whiteSpace: 'pre-wrap' }}>{confirmModal.message}</p>
            <div style={{ display: 'flex', justifyContent: 'flex-end', gap: '12px' }}>
              <button onClick={() => setConfirmModal({ show: false, message: '', onConfirm: null })} style={{ padding: '8px 16px', border: '1px solid #d1d5db', background: '#fff', color: '#374151', borderRadius: '6px', fontWeight: 600, cursor: 'pointer' }}>
                Cancelar
              </button>
              <button onClick={() => { confirmModal.onConfirm(); setConfirmModal({ show: false, message: '', onConfirm: null }); }} style={{ padding: '8px 16px', border: 'none', background: '#012169', color: '#fff', borderRadius: '6px', fontWeight: 600, cursor: 'pointer' }}>
                Enviar a evaluación
              </button>
            </div>
          </div>
        </div>,
        document.body
      )}

      {alertModal.show && createPortal(
        <div style={{ position: 'fixed', top: 0, left: 0, right: 0, bottom: 0, background: 'rgba(0,0,0,0.6)', display: 'flex', alignItems: 'center', justifyContent: 'center', zIndex: 9999 }}>
          <div style={{ background: '#fff', padding: '24px', borderRadius: '12px', maxWidth: '400px', width: '90%', boxShadow: '0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px rgba(0, 0, 0, 0.04)' }}>
            <h3 style={{ margin: '0 0 16px 0', color: alertModal.isError ? '#dc2626' : '#166534', fontSize: '1.2rem', display: 'flex', alignItems: 'center', gap: '8px' }}>
              {alertModal.isError ? (
                <div style={{ background: '#fef2f2', color: '#dc2626', padding: '8px', borderRadius: '50%', display: 'flex' }}><Target size={20} /></div>
              ) : (
                <div style={{ background: '#f0fdf4', color: '#16a34a', padding: '8px', borderRadius: '50%', display: 'flex' }}><CheckCircle2 size={20} /></div>
              )}
              {alertModal.isError ? 'Error de transición' : '¡Éxito!'}
            </h3>
            <p style={{ color: '#4b5563', fontSize: '0.95rem', lineHeight: '1.5', margin: '0 0 24px 0', whiteSpace: 'pre-wrap' }}>{alertModal.message}</p>
            <div style={{ display: 'flex', justifyContent: 'flex-end' }}>
              <button onClick={() => setAlertModal({ show: false, message: '', isError: false })} style={{ padding: '8px 16px', border: 'none', background: alertModal.isError ? '#dc2626' : '#16a34a', color: '#fff', borderRadius: '6px', fontWeight: 600, cursor: 'pointer' }}>
                Entendido
              </button>
            </div>
          </div>
        </div>,
        document.body
      )}
    </div>
  );
};

export default ProjectResultsPage;
