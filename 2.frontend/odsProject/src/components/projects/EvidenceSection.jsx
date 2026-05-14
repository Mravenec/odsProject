import React, { useRef, useState } from 'react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { useDocuments } from '../../hooks/useDocuments';
import { Upload, Download, FileText, Trash2, AlertCircle } from 'lucide-react';
import './EvidenceSection.css';

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
    <section className="evidence-section">
      <header className="evidence-header">
        <FileText size={20} className="evidence-header-icon" />
        <h2 className="evidence-title">Documentos de evidencia</h2>
      </header>

      <p className="evidence-intro">
        {canUpload
          ? 'Subí el documento (Word/Excel/PDF) con los resultados del proyecto. El auditor lo leerá para ingresar las mediciones.'
          : perms.canEnterMeasurements
            ? 'Documentos cargados por el gestor. Descargalos antes de ingresar las mediciones.'
            : 'Documentos disponibles para descargar.'}
      </p>

      {canUpload && (
        <div className="evidence-uploader">
          <input
            ref={fileRef}
            type="file"
            className="evidence-file-input"
            accept=".pdf,.doc,.docx,.xls,.xlsx,.txt,.csv,.odt,.ods"
          />
          <input
            type="text"
            className="evidence-desc-input"
            placeholder="Descripción (opcional)"
            value={descripcion}
            onChange={e => setDescripcion(e.target.value)}
          />
          {uploadError && (
            <div className="evidence-upload-error">
              <AlertCircle size={16} /> {uploadError}
            </div>
          )}
          <button
            type="button"
            className="evidence-upload-btn"
            onClick={handleUpload}
            disabled={busy}
          >
            <Upload size={14} /> {busy ? 'Subiendo...' : 'Subir documento'}
          </button>
        </div>
      )}

      {loading ? (
        <div className="evidence-loading">Cargando...</div>
      ) : error ? (
        <div className="evidence-error">{error}</div>
      ) : documents.length === 0 ? (
        <div className="evidence-empty">
          Aún no hay documentos cargados.
        </div>
      ) : (
        <div className="evidence-list">
          {documents.map(doc => {
            const id   = doc.id ?? doc.ID;
            const name = doc.nombre_archivo ?? doc.nombreArchivo;
            const size = doc.tamanio_bytes ?? doc.tamanioBytes;
            const date = doc.subido_at ?? doc.subidoAt;
            const desc = doc.descripcion;
            const sub  = doc.subido_por ?? doc.subidoPor;
            const isOwn = sub === user?.id;
            return (
              <div key={id} className="evidence-item">
                <FileText size={20} className="evidence-item-icon" />
                <div className="evidence-item-info">
                  <div className="evidence-item-name">{name}</div>
                  <div className="evidence-item-meta">
                    {formatSize(size)} · {date ? new Date(date).toLocaleString('es-ES') : ''}
                    {desc && <> · {desc}</>}
                  </div>
                </div>
                <button
                  type="button"
                  className="evidence-download-btn"
                  onClick={() => download(doc)}
                  title="Descargar"
                >
                  <Download size={14} /> Descargar
                </button>
                {(perms.canDeleteProject || (canUpload && isOwn)) && (
                  <button
                    type="button"
                    className="evidence-delete-btn"
                    onClick={async () => {
                      if (window.confirm('¿Eliminar este documento?'))
                        await remove(doc, user.id, perms.canDeleteProject);
                    }}
                    title="Eliminar"
                  >
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
