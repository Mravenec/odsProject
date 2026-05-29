import { useState, useEffect, useCallback } from 'react';
import { transicionService } from '../services/transicionService';

export function usePlanificacionTransicion(projectId, user, projectStatus, onProjectUpdated) {
  const [solicitud, setSolicitud] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [motivo, setMotivo] = useState('');
  const [nota, setNota] = useState('');
  const [destino, setDestino] = useState('activo');

  const status = String(projectStatus || '').toLowerCase();
  const role = user?.role || '';
  const actorUserId = user?.id;

  const isGestor = role === 'gestor';
  const isReviewer = role === 'admin' || role === 'evaluador';
  const inPlanificacion = status === 'planificacion';
  const inActivo = status === 'activo';

  const load = useCallback(async () => {
    if (!projectId || !actorUserId || !role) return;
    if (!inPlanificacion && !isReviewer) return;
    setLoading(true);
    setError('');
    try {
      const r = await transicionService.getPendiente(projectId, actorUserId, role);
      setSolicitud(r.data?.solicitud ?? null);
    } catch (e) {
      setError(e.response?.data?.error || 'Error al cargar solicitud');
      setSolicitud(null);
    } finally {
      setLoading(false);
    }
  }, [projectId, actorUserId, role, inPlanificacion, isReviewer]);

  useEffect(() => { load(); }, [load]);

  const solicitar = async () => {
    if (!motivo.trim()) {
      setError('Indique el motivo de la solicitud');
      return;
    }
    setLoading(true);
    setError('');
    try {
      const r = await transicionService.crearSolicitud(
        projectId, actorUserId, destino, motivo.trim());
      if (r.data?.solicitud) setSolicitud(r.data.solicitud);
      setMotivo('');
      await load();
      onProjectUpdated?.();
    } catch (e) {
      setError(e.response?.data?.error || 'No se pudo crear la solicitud');
    } finally {
      setLoading(false);
    }
  };

  const aprobar = async () => {
    setLoading(true);
    setError('');
    try {
      await transicionService.aprobar(projectId, actorUserId, role, nota);
      setNota('');
      onProjectUpdated?.();
      window.location.reload();
    } catch (e) {
      setError(e.response?.data?.error || 'No se pudo aprobar');
    } finally {
      setLoading(false);
    }
  };

  const rechazar = async () => {
    if (nota.trim().length < 10) {
      setError('La nota de rechazo debe tener al menos 10 caracteres');
      return;
    }
    setLoading(true);
    setError('');
    try {
      await transicionService.rechazar(projectId, actorUserId, role, nota.trim());
      setNota('');
      await load();
      onProjectUpdated?.();
    } catch (e) {
      setError(e.response?.data?.error || 'No se pudo rechazar');
    } finally {
      setLoading(false);
    }
  };

  const cancelarFuerzaMayor = async () => {
    if (!motivo.trim()) {
      setError('Indique el motivo de cancelación');
      return;
    }
    setLoading(true);
    setError('');
    try {
      await transicionService.fuerzaMayor(projectId, actorUserId, role, motivo.trim());
      setMotivo('');
      onProjectUpdated?.();
      window.location.reload();
    } catch (e) {
      setError(e.response?.data?.error || 'No se pudo cancelar');
    } finally {
      setLoading(false);
    }
  };

  return {
    solicitud,
    loading,
    error,
    motivo,
    setMotivo,
    nota,
    setNota,
    destino,
    setDestino,
    isGestor,
    isReviewer,
    inPlanificacion,
    inActivo,
    solicitar,
    aprobar,
    rechazar,
    cancelarFuerzaMayor,
    reload: load,
  };
}
