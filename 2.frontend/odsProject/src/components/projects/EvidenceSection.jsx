import React, { useRef, useState } from 'react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { useDocuments } from '../../hooks/useDocuments';
import { Upload, Download, FileText, Trash2, AlertCircle } from 'lucide-react';

export default function EvidenceSection({ project }) {
  const { user } = useAuth();
  const perms = usePermissions();
  const { documents, loading, error, upload, download, remove } = useDocuments(project?.id);
  const fileRef = useRef(null);
  const [descripcion, setDescripcion] = useState('');
  const [busy, setBusy] = useState(false);
  const [uploadError, setUploadError] = useState('');
  const canUpload = perms.canUploadEvidenceFor(project);

  const handleUpload = async () => {
    const file = fileRef.current?.files?.[0];
    if (!file) { setUploadError('Elegí un archivo primero'); return; }
    setBusy(true); setUploadError('');
    const r = await upload(file, user.id, descripcion);
    setBusy(false);
    if (!r.success) { setUploadError(r.error || 'Falló la subida'); return; }
    setDescripcion('');
    if (fileRef.current) fileRef.current.value = '';
  };

  const formatSize = (b) => !b ? '—' : b < 1024 ? `${b} B` :
    b < 1024 * 1024 ? `${(b/1024).toFixed(1)} KB` : `${(b/1024/1024).toFixed(2)} MB`;

  return (
    <section style={{background:'#fff',border:'1px solid #e5e7eb',borderRadius:12,padding:24,marginTop:18}}>
      <div style={{display:'flex',alignItems:'center',gap:10,marginBottom:14}}>
        <FileText size={20} color="#3b5bdb" />
        <h2 style={{margin:0,fontSize:18,fontWeight:700,color:'#111'}}>Documentos de evidencia</h2>
      </div>
      <p style={{color:'#666',fontSize:13,marginTop:0,marginBottom:14}}>
        {canUpload
          ? 'Subí el documento (Word/Excel/PDF) con los resultados del proyecto. El auditor lo leerá para ingresar las mediciones.'
          : perms.canEnterMeasurements
            ? 'Documentos cargados por el gestor. Descargalos antes de ingresar las mediciones.'
            : 'Documentos disponibles para descargar.'}
      </p>

      {canUpload && (
        <div style={{padding:16,border:'2px dashed #cbd5e1',borderRadius:10,background:'#fafbfc',marginBottom:16}}>
          <div style={{display:'flex',flexDirection:'column',gap:10}}>
            <input ref={fileRef} type="file"
              accept=".pdf,.doc,.docx,.xls,.xlsx,.txt,.csv,.odt,.ods" style={{fontSize:13}} />
            <input type="text" placeholder="Descripción (opcional)"
              value={descripcion} onChange={e => setDescripcion(e.target.value)}
              style={{padding:'8px 12px',borderRadius:6,border:'1px solid #d1d5db',fontSize:13}} />
            {uploadError && (
              <div style={{display:'flex',gap:8,color:'#b91c1c',fontSize:13,padding:'6px 0'}}>
                <AlertCircle size={16} /> {uploadError}
              </div>
            )}
            <button onClick={handleUpload} disabled={busy}
              style={{padding:'8px 14px',background:'#3b5bdb',color:'#fff',border:'none',borderRadius:6,
                      cursor:busy?'wait':'pointer',fontSize:13,fontWeight:600,
                      display:'inline-flex',alignItems:'center',gap:8,width:'fit-content'}}>
              <Upload size={14} /> {busy ? 'Subiendo...' : 'Subir documento'}
            </button>
          </div>
        </div>
      )}

      {loading ? <div style={{padding:14,color:'#888',fontSize:13}}>Cargando...</div>
       : error ? <div style={{padding:14,color:'#b91c1c',fontSize:13}}>{error}</div>
       : documents.length === 0 ? (
          <div style={{padding:20,color:'#888',fontSize:13,textAlign:'center',background:'#fafafa',borderRadius:8}}>
            Aún no hay documentos cargados.
          </div>
       ) : (
          <div style={{display:'flex',flexDirection:'column',gap:10}}>
            {documents.map(doc => {
              const id   = doc.id ?? doc.ID;
              const name = doc.nombre_archivo ?? doc.nombreArchivo;
              const size = doc.tamanio_bytes ?? doc.tamanioBytes;
              const date = doc.subido_at ?? doc.subidoAt;
              const desc = doc.descripcion;
              const sub  = doc.subido_por ?? doc.subidoPor;
              const isOwn = sub === user?.id;
              return (
                <div key={id} style={{display:'flex',alignItems:'center',gap:12,padding:12,
                                       border:'1px solid #e5e7eb',borderRadius:8,background:'#fff'}}>
                  <FileText size={20} color="#3b5bdb" />
                  <div style={{flex:1,minWidth:0}}>
                    <div style={{fontWeight:600,fontSize:14,color:'#111',
                                 overflow:'hidden',textOverflow:'ellipsis',whiteSpace:'nowrap'}}>{name}</div>
                    <div style={{fontSize:12,color:'#888',marginTop:2}}>
                      {formatSize(size)} · {date ? new Date(date).toLocaleString('es-ES') : ''}
                      {desc && <> · {desc}</>}
                    </div>
                  </div>
                  <button onClick={() => download(doc)} title="Descargar"
                    style={{padding:'6px 10px',background:'#eef2ff',color:'#3b5bdb',
                            border:'none',borderRadius:6,cursor:'pointer',
                            display:'inline-flex',alignItems:'center',gap:6,fontSize:13}}>
                    <Download size={14} /> Descargar
                  </button>
                  {(perms.canDeleteProject || (canUpload && isOwn)) && (
                    <button onClick={async () => {
                        if (window.confirm('¿Eliminar este documento?'))
                          await remove(doc, user.id, perms.canDeleteProject);
                      }} title="Eliminar"
                      style={{padding:'6px 8px',background:'#fef2f2',color:'#b91c1c',
                              border:'none',borderRadius:6,cursor:'pointer'}}>
                      <Trash2 size={14} />
                    </button>
                  )}
                </div>
              );
            })}
          </div>
       )}
    </section>
  );
}
