import React, { useRef, useState } from 'react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { useDocuments } from '../../hooks/useDocuments';
import { Upload, Download, FileText, Trash2, AlertCircle, CheckCircle2 } from 'lucide-react';
import './EvidenceSection.css';

export default function EvidenceSection({ project }) {
  const { user } = useAuth();
  const perms = usePermissions();
  const { documents, loading, error, upload, download, remove } = useDocuments(project?.id);
  const fileRef = useRef(null);
  const [descripcion, setDescripcion] = useState('');
  const [selectedName, setSelectedName] = useState('');
  const [busy, setBusy] = useState(false);
  const [uploadError, setUploadError] = useState('');
  const [uploadSuccess, setUploadSuccess] = useState('');
  const canUpload = perms.canUploadEvidenceFor(project);

  const handleFileChange = () => {
    const file = fileRef.current?.files?.[0];
    setSelectedName(file ? file.name : '');
    setUploadError('');
    setUploadSuccess('');
  };

  const handleSelectClick = () => fileRef.current?.click();

  const handleUpload = async () => {
    const file = fileRef.current?.files?.[0];
    if (!file) {
      setUploadError('Seleccione un archivo primero');
      return;
    }
    setBusy(true);
    setUploadError('');
    setUploadSuccess('');
    const r = await upload(file, user.id, descripcion);
    setBusy(false);
    if (!r.success) {
      setUploadError(r.error || 'Error al subir el documento');
      return;
    }
    setUploadSuccess('Documento subido correctamente');
    setDescripcion('');
    setSelectedName('');
    if (fileRef.current) fileRef.current.value = '';
  };

  const formatSize = (b) => !b ? '—' : b < 1024 ? `${b} B` :
    b < 1024 * 1024 ? `${(b / 1024).toFixed(1)} KB` : `${(b / 1024 / 1024).toFixed(2)} MB`;

  const introSteps = canUpload ? (
    <ol className="evidence-steps">
      <li>Seleccione el archivo (Word, Excel, PDF o texto plano (.txt)) con los resultados del proyecto.</li>
      <li>Verifique que el nombre del archivo aparezca correctamente debajo del botón.</li>
      <li>Si lo desea, escriba una descripción breve para el evaluador (opcional).</li>
      <li>Pulse «Subir documento» y espere la confirmación.</li>
      <li>Revise la lista: el evaluador descargará el archivo para ingresar las mediciones.</li>
    </ol>
  ) : perms.canEnterMeasurements ? (
    <p className="evidence-intro-text">
      Documentos cargados por el gestor. Descárguelos antes de ingresar las mediciones.
    </p>
  ) : (
    <p className="evidence-intro-text">Documentos disponibles para descargar.</p>
  );

  return (
    <section className="evidence-section">
      <header className="evidence-header">
        <FileText size={20} className="evidence-header-icon" />
        <h2 className="evidence-title">Documentos de evidencia</h2>
      </header>

      <div className="evidence-intro">{introSteps}</div>

      {canUpload && (
        <div className="evidence-uploader">
          <input
            ref={fileRef}
            type="file"
            className="evidence-file-input-hidden"
            accept=".pdf,.doc,.docx,.xls,.xlsx,.txt,.csv,.odt,.ods"
            onChange={handleFileChange}
          />
          <div className="evidence-file-row">
            <button
              type="button"
              className="evidence-select-btn"
              onClick={handleSelectClick}
              disabled={busy}
            >
              Seleccionar archivo
            </button>
            <span className={`evidence-file-name${selectedName ? '' : ' evidence-file-name--empty'}`}>
              {selectedName || 'Ningún archivo seleccionado'}
            </span>
          </div>
          <label className="evidence-desc-label" htmlFor="evidence-desc">
            Descripción (opcional)
          </label>
          <input
            id="evidence-desc"
            type="text"
            className="evidence-desc-input"
            placeholder="Nota breve para el evaluador"
            value={descripcion}
            onChange={e => setDescripcion(e.target.value)}
            maxLength={500}
            disabled={busy}
          />
          {uploadError && (
            <div className="evidence-upload-error">
              <AlertCircle size={16} /> {uploadError}
            </div>
          )}
          {uploadSuccess && (
            <div className="evidence-upload-success">
              <CheckCircle2 size={16} /> {uploadSuccess}
            </div>
          )}
          <button
            type="button"
            className="evidence-upload-btn"
            onClick={handleUpload}
            disabled={busy}
          >
            <Upload size={14} /> {busy ? 'Subiendo…' : 'Subir documento'}
          </button>
        </div>
      )}

      {loading ? (
        <div className="evidence-loading">Cargando…</div>
      ) : error ? (
        <div className="evidence-error">{error}</div>
      ) : documents.length === 0 ? (
        <div className="evidence-empty">Aún no hay documentos cargados.</div>
      ) : (
        <div className="evidence-list">
          {documents.map(doc => {
            const id = doc.id;
            const name = doc.nombreArchivo;
            const size = doc.tamanioBytes;
            const date = doc.subidoAt;
            const desc = doc.descripcion;
            const sub = doc.subidoPor;
            const isOwn = sub === user?.id;
            return (
              <div key={id} className="evidence-item">
                <FileText size={20} className="evidence-item-icon" />
                <div className="evidence-item-info">
                  <div className="evidence-item-name">{name}</div>
                  {desc && <div className="evidence-item-desc">{desc}</div>}
                  <div className="evidence-item-meta">
                    {formatSize(size)} · {date ? new Date(date).toLocaleString('es-ES') : ''}
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
