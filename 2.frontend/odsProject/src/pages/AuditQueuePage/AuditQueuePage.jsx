import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { projectService } from '../../services/projectService';
import { documentService } from '../../services/documentService';
import { ClipboardCheck, ArrowLeft, FileText, AlertCircle } from 'lucide-react';
import { formatDate, getOdsColor, getObjectiveName } from '../../utils/formatters';
import AchievementBadge from '../../components/AchievementBadge';

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

  const filtered = rows.filter(r => {
    if (filter === 'pendientes') return r.hasDocs && r.status !== 'completado';
    if (filter === 'con_doc')    return r.hasDocs;
    return true;
  });

  return (
    <div style={{minHeight:'100vh',background:'#f8f9fb'}}>
      <header style={{background:'#fff',borderBottom:'1px solid #e5e7eb',padding:'16px 24px'}}>
        <div style={{maxWidth:1100,margin:'0 auto',display:'flex',alignItems:'center',gap:14}}>
          <button onClick={() => navigate('/dashboard')}
            style={{background:'#f3f4f6',border:'none',borderRadius:8,padding:8,cursor:'pointer'}}>
            <ArrowLeft size={20} />
          </button>
          <div style={{display:'flex',alignItems:'center',gap:10}}>
            <div style={{width:40,height:40,borderRadius:10,background:'#16a34a',
                         display:'flex',alignItems:'center',justifyContent:'center'}}>
              <ClipboardCheck size={20} color="#fff" />
            </div>
            <div>
              <h1 style={{margin:0,fontSize:20}}>Cola de auditoría</h1>
              <p style={{margin:0,fontSize:13,color:'#888'}}>{perms.roleLabel} · {rows.length} proyectos</p>
            </div>
          </div>
        </div>
      </header>

      <main style={{maxWidth:1100,margin:'0 auto',padding:'24px'}}>
        {/* Filtros */}
        <div style={{display:'flex',gap:8,marginBottom:18}}>
          {[
            { k: 'all',         label: `Todos (${rows.length})` },
            { k: 'pendientes',  label: `Pendientes de auditar (${rows.filter(r => r.hasDocs && r.status !== 'completado').length})` },
            { k: 'con_doc',     label: `Con documento (${rows.filter(r => r.hasDocs).length})` }
          ].map(t => (
            <button key={t.k} onClick={() => setFilter(t.k)}
              style={{padding:'8px 14px',borderRadius:8,
                      background: filter === t.k ? '#3b5bdb' : '#fff',
                      color: filter === t.k ? '#fff' : '#555',
                      border:'1px solid ' + (filter === t.k ? '#3b5bdb' : '#e5e7eb'),
                      fontSize:13,fontWeight:500,cursor:'pointer'}}>
              {t.label}
            </button>
          ))}
        </div>

        {loading ? (
          <div style={{padding:40,textAlign:'center',color:'#888'}}>Cargando proyectos...</div>
        ) : filtered.length === 0 ? (
          <div style={{padding:40,textAlign:'center',color:'#888',background:'#fff',
                       borderRadius:12,border:'1px solid #e5e7eb'}}>
            <AlertCircle size={36} color="#aaa" style={{marginBottom:10}} />
            <div>No hay proyectos en esta vista.</div>
          </div>
        ) : (
          <div style={{display:'flex',flexDirection:'column',gap:12}}>
            {filtered.map(p => (
              <div key={p.id} onClick={() => navigate(`/audit/${p.id}`)}
                style={{background:'#fff',border:'1px solid #e5e7eb',borderRadius:10,
                        padding:16,cursor:'pointer',display:'flex',alignItems:'center',gap:14,
                        transition:'box-shadow 0.15s'}}
                onMouseEnter={e => e.currentTarget.style.boxShadow='0 4px 12px rgba(0,0,0,0.06)'}
                onMouseLeave={e => e.currentTarget.style.boxShadow='none'}>
                <div style={{
                  width:48,height:48,borderRadius:10,flexShrink:0,
                  background: getOdsColor(p.objective ?? p.odsPrimario),
                  display:'flex',alignItems:'center',justifyContent:'center',
                  color:'#fff',fontSize:18,fontWeight:700
                }}>
                  {p.objective ?? p.odsPrimario ?? '?'}
                </div>
                <div style={{flex:1,minWidth:0}}>
                  <div style={{fontWeight:600,fontSize:15,color:'#111'}}>{p.name}</div>
                  <div style={{fontSize:12,color:'#888',marginTop:3}}>
                    ODS {p.objective ?? '?'} · {getObjectiveName(p.objective)} · {formatDate(p.startDate)} → {formatDate(p.endDate)}
                  </div>
                  {Array.isArray(p.odsVinculados) && p.odsVinculados.length > 1 && (
                    <div style={{marginTop:4,fontSize:11,color:'#666'}}>
                      Cubre ODS: {p.odsVinculados.join(', ')}
                    </div>
                  )}
                </div>
                <div style={{display:'flex',flexDirection:'column',gap:5,alignItems:'flex-end'}}>
                  {p.hasDocs ? (
                    <div style={{display:'inline-flex',alignItems:'center',gap:5,
                                 padding:'3px 10px',borderRadius:99,background:'#dcfce7',
                                 color:'#166534',fontSize:11,fontWeight:600}}>
                      <FileText size={12} /> {p.docCount} doc{p.docCount !== 1 ? 's' : ''}
                    </div>
                  ) : (
                    <div style={{padding:'3px 10px',borderRadius:99,background:'#fef3c7',
                                 color:'#92400e',fontSize:11,fontWeight:600}}>
                      Sin documento
                    </div>
                  )}
                  <AchievementBadge estado={p.status === 'completado' ? 'LOGRADO' : 'SIN DATOS'}
                    porcentaje={null} size="sm" showPct={false} />
                </div>
              </div>
            ))}
          </div>
        )}
      </main>
    </div>
  );
};

export default AuditQueuePage;
