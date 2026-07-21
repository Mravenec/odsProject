import { useState, useEffect, useCallback, useMemo } from 'react';
import { chatService } from '../services/chatService';
import { getChatLastReadId } from './useProjectChat';

const INBOX_POLL_MS = 5000;

function normalizeThread(raw) {
  if (!raw) return null;
  return {
    proyectoId: Number(raw.proyectoId ?? raw.proyecto_id),
    nombreProyecto: raw.nombreProyecto ?? raw.nombre_proyecto ?? 'Proyecto',
    gestorUserId: raw.gestorUserId ?? raw.gestor_user_id ?? null,
    estado: raw.estado || 'planificacion',
    lastMessageId: Number(raw.lastMessageId ?? raw.last_message_id ?? 0),
    lastAutorId: raw.lastAutorId ?? raw.last_autor_id ?? null,
    lastAutorNombre: raw.lastAutorNombre ?? raw.last_autor_nombre ?? null,
    lastAutorRol: raw.lastAutorRol ?? raw.last_autor_rol ?? null,
    lastCuerpo: raw.lastCuerpo ?? raw.last_cuerpo ?? '',
    lastAt: raw.lastAt ?? raw.last_at ?? null,
    messageCount: Number(raw.messageCount ?? raw.message_count ?? 0),
  };
}

/**
 * Bandeja de chats de planificación para admin / evaluador.
 */
export function useStaffChatInbox(user) {
  const [threads, setThreads] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const actorUserId = user?.id;
  const actorRole = user?.role || '';
  const enabled = actorRole === 'admin' || actorRole === 'evaluador';

  const load = useCallback(async ({ silent = false } = {}) => {
    if (!enabled || !actorUserId) return;
    if (!silent) setLoading(true);
    setError('');
    try {
      const r = await chatService.listInbox(actorUserId, actorRole);
      const list = (Array.isArray(r.data) ? r.data : []).map(normalizeThread).filter(Boolean);
      setThreads(list);
    } catch (e) {
      setError(e.response?.data?.error || e.message || 'No se pudo cargar la bandeja');
      if (!silent) setThreads([]);
    } finally {
      if (!silent) setLoading(false);
    }
  }, [enabled, actorUserId, actorRole]);

  useEffect(() => {
    if (!enabled) return;
    load();
  }, [enabled, load]);

  useEffect(() => {
    if (!enabled) return;
    const timer = setInterval(() => load({ silent: true }), INBOX_POLL_MS);
    return () => clearInterval(timer);
  }, [enabled, load]);

  const threadsWithUnread = useMemo(() => {
    if (!actorUserId) return [];
    return threads.map((t) => {
      const lastRead = getChatLastReadId(t.proyectoId, actorUserId);
      const unread = t.lastMessageId > lastRead
        && Number(t.lastAutorId) !== Number(actorUserId);
      return { ...t, unread: Boolean(unread) };
    });
  }, [threads, actorUserId]);

  const unreadThreadCount = useMemo(
    () => threadsWithUnread.filter((t) => t.unread).length,
    [threadsWithUnread],
  );

  return {
    enabled,
    threads: threadsWithUnread,
    unreadThreadCount,
    loading,
    error,
    reload: load,
  };
}

export default useStaffChatInbox;
