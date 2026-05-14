import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { projectService } from '../../services/projectService';
import { documentService } from '../../services/documentService';
import { ClipboardCheck, ArrowLeft, FileText, AlertCircle } from 'lucide-react';
import { formatDate, getOdsColor, getObjectiveName } from '../../utils/formatters';
import AchievementBadge from '../../components/AchievementBadge';
import './AuditQueuePage.css';

/**
 * Sprint 14 — Cola de auditoría
 *
 * Para admin/auditor: lista de proyectos del sistema con info de documentos
 * subidos y estado de auditoría. Click → entra a /audit/:id para hacer la
 * medición real.
 */
const AuditQueuePage = () => {
  const { user } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();

  const [rows, setRows]       = useState([]);
  const [loading, setLoading] = useState(true);
  const [filter, setFilter]   = useState('all'); // all | pendientes | con_doc

  useEffect(() => { load(); }, []);

  const load = async () => {
    setLoading(true);
    try {
      const res = await projectService.getAllProjects();
      const projects = res.data || [];

      // Para cada proyecto: traer docs (count) en paralelo
      const enriched = await Promise.all(projects.map(async p => {
        let docs = [];
        try {
          const d = await documentService.listByProject(p.id);
          docs = d.success ? d.data : [];
        } catch {}
        return { ...p, docCount: docs.length, hasDocs: docs.length > 0 };
      }));
      setRows(enriched);
    } finally { setLoading(false); }
  };

  const pendientesCount = rows.filter(r => r.hasDocs && r.status !== 'completado').length;
  const conDocCount     = rows.filter(r => r.hasDocs).length;

  const filtered = rows.filter(r => {
    if (filter === 'pendientes') return r.hasDocs && r.status !== 'completado';
    if (filter === 'con_doc')    return r.hasDocs;
    return true;
  });

  const filterTabs = [
    { k: 'all',        label: 'Todos',                  count: rows.length },
    { k: 'pendientes', label: 'Pendientes de auditar',  count: pendientesCount },
    { k: 'con_doc',    label: 'Con documento',          count: conDocCount },
  ];

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
        {/* Filtros */}
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
            <p>Cuando un gestor cargue documentos, los verás aquí listos para auditar.</p>
          </div>
        ) : (
          <div className="audit-rows">
            {filtered.map(p => {
              const odsNumber = p.objective ?? p.odsPrimario;
              return (
                <article
                  key={p.id}
                  className="audit-row"
                  onClick={() => navigate(`/audit/${p.id}`)}
                  onKeyDown={(e) => {
                    if (e.key === 'Enter' || e.key === ' ') {
                      e.preventDefault();
                      navigate(`/audit/${p.id}`);
                    }
                  }}
                  role="button"
                  tabIndex={0}
                  aria-label={`Auditar proyecto ${p.name}`}
                >
                  <div
                    className="audit-row-ods"
                    style={{ backgroundColor: getOdsColor(odsNumber) }}
                  >
                    {odsNumber ?? '?'}
                  </div>

                  <div className="audit-row-info">
                    <h4 className="audit-row-title">{p.name}</h4>
                    <p className="audit-row-meta">
                      <span>ODS {odsNumber ?? '?'}</span>
                      <span className="audit-row-sep">·</span>
                      <span>{getObjectiveName(odsNumber)}</span>
                      <span className="audit-row-sep">·</span>
                      <span>{formatDate(p.startDate)} → {formatDate(p.endDate)}</span>
                    </p>
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
                      estado={p.status === 'completado' ? 'LOGRADO' : 'SIN DATOS'}
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
