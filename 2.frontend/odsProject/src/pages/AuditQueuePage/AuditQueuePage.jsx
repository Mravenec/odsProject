import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { projectService } from '../../services/projectService';
import { documentService } from '../../services/documentService';
import { ClipboardCheck, ArrowLeft, FileText, AlertCircle,
         Clock, CheckCircle2, Hourglass, TrendingUp } from 'lucide-react';
import { formatDate, getOdsColor, getObjectiveName,
         getEstadoLabel, getEstadoClass } from '../../utils/formatters';
import AchievementBadge from '../../components/AchievementBadge';
import './AuditQueuePage.css';

/**
 * Sprint 19 — Cola de auditoría completa
 *
 * 4 pestañas REALES (sin duplicados):
 *   - "Todos"        → todos los proyectos del sistema
 *   - "Pendientes"   → estado = 'en_revision'  (esperando al auditor)
 *   - "En curso"     → estado = 'activo' con al menos un documento subido
 *   - "Auditados"    → estado = 'completado'  (firmados, inmutables)
 *
 * Panel superior con 4 KPIs vivos provenientes de GET /api/projects/audit/metrics.
 * Click en una fila auditada NO entra al workbench, lleva a /projects/:id/results.
 */
const AuditQueuePage = () => {
  const { user } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();

  const [rows, setRows]         = useState([]);
  const [metrics, setMetrics]   = useState({});
  const [loading, setLoading]   = useState(true);
  const [filter, setFilter]     = useState('pendientes');  // default: lo más útil

  useEffect(() => { load(); }, []);

  const load = async () => {
    setLoading(true);
    try {
      // En paralelo: lista de proyectos + métricas de la cola
      const [projRes, metricsRes] = await Promise.all([
        projectService.getAllProjects(),
        projectService.getAuditMetrics()
      ]);

      const projects = projRes.data || [];
      // Adjuntar conteo de documentos para distinguir "en curso" (activo+doc)
      const enriched = await Promise.all(projects.map(async p => {
        let docs = [];
        try {
          const d = await documentService.listByProject(p.id);
          docs = d.success ? d.data : [];
        } catch {}
        return { ...p, docCount: docs.length, hasDocs: docs.length > 0 };
      }));
      setRows(enriched);
      setMetrics(metricsRes.data || {});
    } finally { setLoading(false); }
  };

  // Conteos en tiempo real para los chips de cada tab (sin esperar /metrics)
  const counts = {
    all:        rows.length,
    pendientes: rows.filter(r => r.status === 'en_revision').length,
    en_curso:   rows.filter(r => ['activo', 'planificacion'].includes(r.status) && r.hasDocs).length,
    auditados:  rows.filter(r => r.status === 'completado').length,
  };

  const filtered = rows.filter(r => {
    if (filter === 'pendientes') return r.status === 'en_revision';
    if (filter === 'en_curso')   return ['activo', 'planificacion'].includes(r.status) && r.hasDocs;
    if (filter === 'auditados')  return r.status === 'completado';
    return true;
  });

  const filterTabs = [
    { k: 'all',        label: 'Todos',                       count: counts.all },
    { k: 'pendientes', label: 'Pendientes de auditar',       count: counts.pendientes },
    { k: 'en_curso',   label: 'En curso (con documento)',    count: counts.en_curso },
    { k: 'auditados',  label: 'Auditados',                   count: counts.auditados },
  ];

  // Click handler: filas auditadas no van al workbench, van al detalle
  const openRow = (p) => {
    if (p.status === 'completado') navigate(`/projects/${p.id}/results`);
    else navigate(`/audit/${p.id}`);
  };

  // Formatter para tiempo promedio
  const fmtPromHoras = (h) => {
    if (h == null) return '—';
    if (h < 24) return `${Math.round(h)} h`;
    return `${Math.round(h / 24)} d`;
  };

  return (
    <div className="audit-queue-page fade-in">
      <header className="audit-header">
        <div className="container audit-header-content">
          <button
            type="button"
            className="btn-back"
            onClick={() => navigate('/dashboard')}
            aria-label="Volver al dashboard"
          >
            <ArrowLeft size={20} />
          </button>
          <div className="audit-title-group">
            <div className="audit-title-icon">
              <ClipboardCheck size={20} />
            </div>
            <div className="audit-title-text">
              <h1>Cola de auditoría</h1>
              <p>{perms.roleLabel} · {rows.length} {rows.length === 1 ? 'proyecto' : 'proyectos'}</p>
            </div>
          </div>
        </div>
      </header>

      <main className="container audit-main">

        {/* ═══ Sprint 19 · Panel de KPIs ═══════════════════════════════ */}
        <section className="audit-kpis" aria-label="Métricas de la cola">
          <article className="kpi-card kpi-pendientes">
            <div className="kpi-icon"><Hourglass size={18} /></div>
            <div className="kpi-body">
              <span className="kpi-label">Pendientes</span>
              <span className="kpi-value">{metrics.pendientes ?? '—'}</span>
              <span className="kpi-hint">En espera del auditor</span>
            </div>
          </article>
          <article className="kpi-card kpi-encurso">
            <div className="kpi-icon"><Clock size={18} /></div>
            <div className="kpi-body">
              <span className="kpi-label">En curso</span>
              <span className="kpi-value">{metrics.enCurso ?? '—'}</span>
              <span className="kpi-hint">Activos con evidencia subida</span>
            </div>
          </article>
          <article className="kpi-card kpi-auditados">
            <div className="kpi-icon"><CheckCircle2 size={18} /></div>
            <div className="kpi-body">
              <span className="kpi-label">Auditados este mes</span>
              <span className="kpi-value">{metrics.auditadosMes ?? '—'}</span>
              <span className="kpi-hint">Firmados y cerrados</span>
            </div>
          </article>
          <article className="kpi-card kpi-tiempo">
            <div className="kpi-icon"><TrendingUp size={18} /></div>
            <div className="kpi-body">
              <span className="kpi-label">Tiempo promedio</span>
              <span className="kpi-value">{fmtPromHoras(metrics.tiempoPromedioHoras)}</span>
              <span className="kpi-hint">Envío → cierre (30 días)</span>
            </div>
          </article>
        </section>

        {/* ═══ Filtros (4 tabs reales) ═════════════════════════════════ */}
        <div className="audit-filters" role="tablist" aria-label="Filtros de la cola">
          {filterTabs.map(t => (
            <button
              key={t.k}
              type="button"
              role="tab"
              aria-selected={filter === t.k}
              className={`audit-filter-tab ${filter === t.k ? 'active' : ''}`}
              onClick={() => setFilter(t.k)}
            >
              <span className="audit-filter-label">{t.label}</span>
              <span className="audit-filter-count">{t.count}</span>
            </button>
          ))}
        </div>

        {loading ? (
          <div className="audit-loading">
            <div className="loader"></div>
            <p>Cargando proyectos...</p>
          </div>
        ) : filtered.length === 0 ? (
          <div className="audit-empty">
            <AlertCircle size={36} className="audit-empty-icon" />
            <h3>No hay proyectos en esta vista</h3>
            <p>
              {filter === 'pendientes' && 'Cuando un gestor envíe un proyecto a auditoría, lo verás aquí listo para revisar.'}
              {filter === 'en_curso'   && 'Los proyectos activos con documentos subidos por el gestor aparecerán aquí.'}
              {filter === 'auditados'  && 'Los proyectos cerrados aparecerán aquí como referencia histórica.'}
              {filter === 'all'        && 'No hay proyectos en el sistema todavía.'}
            </p>
          </div>
        ) : (
          <div className="audit-rows">
            {filtered.map(p => {
              const odsNumber = p.objective ?? p.odsPrimario;
              const isAudited = p.status === 'completado';
              return (
                <article
                  key={p.id}
                  className={`audit-row ${isAudited ? 'audit-row--audited' : ''}`}
                  onClick={() => openRow(p)}
                  onKeyDown={(e) => {
                    if (e.key === 'Enter' || e.key === ' ') {
                      e.preventDefault();
                      openRow(p);
                    }
                  }}
                  role="button"
                  tabIndex={0}
                  aria-label={isAudited
                    ? `Ver detalle del proyecto auditado ${p.name}`
                    : `Auditar proyecto ${p.name}`}
                >
                  <div
                    className="audit-row-ods"
                    style={{ backgroundColor: getOdsColor(odsNumber) }}
                  >
                    {odsNumber ?? '?'}
                  </div>

                  <div className="audit-row-info">
                    <div className="audit-row-title-line">
                      <h4 className="audit-row-title">{p.name}</h4>
                      <span className={`audit-row-state pill-${getEstadoClass(p.status)}`}>
                        {getEstadoLabel(p.status)}
                      </span>
                    </div>
                    <p className="audit-row-meta">
                      <span>ODS {odsNumber ?? '?'}</span>
                      <span className="audit-row-sep">·</span>
                      <span>{getObjectiveName(odsNumber)}</span>
                      <span className="audit-row-sep">·</span>
                      <span>{formatDate(p.startDate)} → {formatDate(p.endDate)}</span>
                    </p>
                    {/* Stamping de auditoría visible en filas cerradas (Sprint 20) */}
                    {isAudited && p.auditedByName && (
                      <p className="audit-row-stamping">
                        ✓ Auditado por <strong>{p.auditedByName}</strong>
                        {p.auditedAt && <> el <strong>{formatDate(p.auditedAt)}</strong></>}
                      </p>
                    )}
                    {Array.isArray(p.odsVinculados) && p.odsVinculados.length > 1 && (
                      <p className="audit-row-linked">
                        Cubre ODS: {p.odsVinculados.join(', ')}
                      </p>
                    )}
                  </div>

                  <div className="audit-row-status">
                    {p.hasDocs ? (
                      <span className="audit-doc-pill has-docs">
                        <FileText size={12} />
                        {p.docCount} doc{p.docCount !== 1 ? 's' : ''}
                      </span>
                    ) : (
                      <span className="audit-doc-pill no-docs">
                        Sin documento
                      </span>
                    )}
                    <AchievementBadge
                      estado={isAudited ? 'LOGRADO' : 'SIN DATOS'}
                      porcentaje={null}
                      size="sm"
                      showPct={false}
                    />
                  </div>
                </article>
              );
            })}
          </div>
        )}
      </main>
    </div>
  );
};

export default AuditQueuePage;
