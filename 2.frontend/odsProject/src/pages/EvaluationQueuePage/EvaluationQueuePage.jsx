import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { useEvaluationQueue } from '../../hooks/useEvaluationQueue';
import { ClipboardCheck, ArrowLeft, FileText, AlertCircle,
         Clock, CheckCircle2, Hourglass, TrendingUp } from 'lucide-react';
import { formatDate, getOdsColor, getObjectiveName,
         getEstadoLabel, getEstadoClass } from '../../utils/formatters';
import AchievementBadge from '../../components/AchievementBadge';
import './EvaluationQueuePage.css';

/**
 * Sprint 3 — Cola de evaluación completa
 */
const EvaluationQueuePage = () => {
  const { user } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();

  const { rows, metrics, loading, reload: load } = useEvaluationQueue();
  const [filter, setFilter] = useState('pendientes');

  const counts = {
    all:        rows.length,
    pendientes: rows.filter(r => r.status === 'en_revision').length,
    en_curso:   rows.filter(r => ['activo', 'planificacion'].includes(r.status) && r.hasDocs).length,
    evaluados:  rows.filter(r => r.status === 'completado').length,
  };

  const filtered = rows.filter(r => {
    if (filter === 'pendientes') return r.status === 'en_revision';
    if (filter === 'en_curso')   return ['activo', 'planificacion'].includes(r.status) && r.hasDocs;
    if (filter === 'evaluados')  return r.status === 'completado';
    return true;
  });

  const filterTabs = [
    { k: 'all',        label: 'Todos',                       count: counts.all },
    { k: 'pendientes', label: 'Pendientes de evaluación',    count: counts.pendientes },
    { k: 'en_curso',   label: 'En curso (con documento)',    count: counts.en_curso },
    { k: 'evaluados',  label: 'Evaluados',                   count: counts.evaluados },
  ];

  const openRow = (p) => {
    if (p.status === 'completado') navigate(`/projects/${p.id}/results`);
    else navigate(`/evaluacion/${p.id}`);
  };

  const fmtPromHoras = (h) => {
    if (h == null) return '—';
    if (h < 24) return `${Math.round(h)} h`;
    return `${Math.round(h / 24)} d`;
  };

  return (
    <div className="evaluation-queue-page fade-in">
      <header className="evaluation-header">
        <div className="container evaluation-header-content">
          <button
            type="button"
            className="btn-back"
            onClick={() => navigate('/dashboard')}
            aria-label="Volver al dashboard"
          >
            <ArrowLeft size={20} />
          </button>
          <div className="evaluation-title-group">
            <div className="evaluation-title-icon">
              <ClipboardCheck size={20} />
            </div>
            <div className="evaluation-title-text">
              <h1>Cola de Evaluación</h1>
              <p>{perms.roleLabel} · {rows.length} {rows.length === 1 ? 'proyecto' : 'proyectos'}</p>
            </div>
          </div>
        </div>
      </header>

      <main className="container evaluation-main">

        <section className="evaluation-kpis" aria-label="Métricas de la cola">
          <article className="kpi-card kpi-pendientes">
            <div className="kpi-icon"><Hourglass size={18} /></div>
            <div className="kpi-body">
              <span className="kpi-label">Pendientes</span>
              <span className="kpi-value">{metrics.pendientes ?? '—'}</span>
              <span className="kpi-hint">En espera del evaluador</span>
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
          <article className="kpi-card kpi-evaluados">
            <div className="kpi-icon"><CheckCircle2 size={18} /></div>
            <div className="kpi-body">
              <span className="kpi-label">Evaluados este mes</span>
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

        <div className="evaluation-filters" role="tablist" aria-label="Filtros de la cola">
          {filterTabs.map(t => (
            <button
              key={t.k}
              type="button"
              role="tab"
              aria-selected={filter === t.k}
              className={`evaluation-filter-tab ${filter === t.k ? 'active' : ''}`}
              onClick={() => setFilter(t.k)}
            >
              <span className="evaluation-filter-label">{t.label}</span>
              <span className="evaluation-filter-count">{t.count}</span>
            </button>
          ))}
        </div>

        {loading ? (
          <div className="evaluation-loading">
            <div className="loader"></div>
            <p>Cargando proyectos...</p>
          </div>
        ) : filtered.length === 0 ? (
          <div className="evaluation-empty">
            <AlertCircle size={36} className="evaluation-empty-icon" />
            <h3>No hay proyectos en esta vista</h3>
            <p>
              {filter === 'pendientes' && 'Cuando un gestor envíe un proyecto a evaluación, lo verás aquí listo para revisar.'}
              {filter === 'en_curso'   && 'Los proyectos activos con documentos subidos por el gestor aparecerán aquí.'}
              {filter === 'evaluados'  && 'Los proyectos cerrados aparecerán aquí como referencia histórica.'}
              {filter === 'all'        && 'No hay proyectos en el sistema todavía.'}
            </p>
          </div>
        ) : (
          <div className="evaluation-rows">
            {filtered.map(p => {
              const odsNumber = p.objective ?? p.odsPrimario;
              const isEvaluated = p.status === 'completado';
              return (
                <article
                  key={p.id}
                  className={`evaluation-row ${isEvaluated ? 'evaluation-row--evaluated' : ''}`}
                  onClick={() => openRow(p)}
                  onKeyDown={(e) => {
                    if (e.key === 'Enter' || e.key === ' ') {
                      e.preventDefault();
                      openRow(p);
                    }
                  }}
                  role="button"
                  tabIndex={0}
                  aria-label={isEvaluated
                    ? `Ver detalle del proyecto evaluado ${p.name}`
                    : `Evaluar proyecto ${p.name}`}
                >
                  <div
                    className="evaluation-row-ods"
                    style={{ backgroundColor: getOdsColor(odsNumber) }}
                  >
                    {odsNumber ?? '?'}
                  </div>

                  <div className="evaluation-row-info">
                    <div className="evaluation-row-title-line">
                      <h4 className="evaluation-row-title">{p.name}</h4>
                      <span className={`evaluation-row-state pill-${getEstadoClass(p.status)}`}>
                        {getEstadoLabel(p.status)}
                      </span>
                    </div>
                    <p className="evaluation-row-meta">
                      <span>ODS {odsNumber ?? '?'}</span>
                      <span className="evaluation-row-sep">·</span>
                      <span>{getObjectiveName(odsNumber)}</span>
                      <span className="evaluation-row-sep">·</span>
                      <span>{formatDate(p.startDate)} → {formatDate(p.endDate)}</span>
                    </p>
                    {isEvaluated && p.auditedByName && (
                      <p className="evaluation-row-stamping">
                        ✓ Evaluado por <strong>{p.auditedByName}</strong>
                        {p.auditedAt && <> el <strong>{formatDate(p.auditedAt)}</strong></>}
                      </p>
                    )}
                    {Array.isArray(p.odsVinculados) && p.odsVinculados.length > 1 && (
                      <p className="evaluation-row-linked">
                        Cubre ODS: {p.odsVinculados.join(', ')}
                      </p>
                    )}
                  </div>

                  <div className="evaluation-row-status">
                    {p.hasDocs ? (
                      <span className="evaluation-doc-pill has-docs">
                        <FileText size={12} />
                        {p.docCount} doc{p.docCount !== 1 ? 's' : ''}
                      </span>
                    ) : (
                      <span className="evaluation-doc-pill no-docs">
                        Sin documento
                      </span>
                    )}
                    <AchievementBadge
                      estado={isEvaluated ? 'LOGRADO' : 'SIN DATOS'}
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

export default EvaluationQueuePage;
