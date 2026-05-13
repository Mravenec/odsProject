import { useState, useCallback, useEffect } from 'react';
import { documentService } from '../services/documentService';

export function useDocuments(proyectoId) {
  const [documents, setDocuments] = useState([]);
  const [loading, setLoading]     = useState(false);
  const [error, setError]         = useState(null);

  const reload = useCallback(async () => {
    if (!proyectoId) return;
    setLoading(true); setError(null);
    const r = await documentService.listByProject(proyectoId);
    if (r.success) setDocuments(r.data); else setError(r.error);
    setLoading(false);
  }, [proyectoId]);

  useEffect(() => { reload(); }, [reload]);

  const upload = async (file, usuarioId, descripcion) => {
    setLoading(true);
    const r = await documentService.upload(proyectoId, file, usuarioId, descripcion);
    if (r.success) await reload(); else setError(r.error);
    setLoading(false);
    return r;
  };
  const download = (doc) =>
    documentService.download(doc.id ?? doc.ID, doc.nombre_archivo ?? doc.nombreArchivo ?? 'documento');
  const remove = async (doc, usuarioId, isAdmin) => {
    const r = await documentService.remove(doc.id ?? doc.ID, usuarioId, isAdmin);
    if (r.success) await reload();
    return r;
  };
  return { documents, loading, error, reload, upload, download, remove };
}
export default useDocuments;
