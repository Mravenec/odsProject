import { useState, useEffect, useCallback, useMemo } from 'react';
import { chatService } from '../services/chatService';

const EDIT_WINDOW_MS = 30 * 60 * 1000;
const POLL_INTERVAL_MS = 4000;

export function isSameChatUser(a, b) {
  if (a == null || b == null) return false;
  return Number(a) === Number(b);
}

const STAFF_ROLES = new Set(['admin', 'evaluador']);

/** Gestor del proyecto → burbuja izquierda; admin/evaluador → derecha (vista fija para todos). */
export function isGestorSideMessage(msg, projectOwnerUserId) {
  if (projectOwnerUserId != null && msg?.autorId != null) {
    return Number(msg.autorId) === Number(projectOwnerUserId);
  }
  const rol = String(msg?.autorRol || '').toLowerCase();
  if (rol === 'gestor') return true;
  if (STAFF_ROLES.has(rol)) return false;
  return true;
}

export function isStaffSideMessage(msg, projectOwnerUserId) {
  return !isGestorSideMessage(msg, projectOwnerUserId);
}

export function chatAuthorRoleLabel(msg, projectOwnerUserId) {
  if (isGestorSideMessage(msg, projectOwnerUserId)) return 'Gestor';
  const rol = String(msg?.autorRol || '').toLowerCase();
  if (rol === 'admin') return 'Administrador';
  if (rol === 'evaluador') return 'Evaluador';
  return 'UTN';
}

function normalizeMessage(raw) {
  if (!raw) return raw;
  return {
    ...raw,
    id: raw.id,
    autorId: raw.autorId ?? raw.autor_id,
    proyectoId: raw.proyectoId ?? raw.proyecto_id,
    cuerpo: raw.cuerpo,
    createdAt: raw.createdAt ?? raw.created_at ?? null,
    editedAt: raw.editedAt ?? raw.edited_at ?? null,
    editCount: Number(raw.editCount ?? raw.edit_count ?? 0),
    eliminado: Boolean(raw.eliminado),
    autorNombre: raw.autorNombre ?? raw.autor_nombre ?? null,
    autorRol: raw.autorRol ?? raw.autor_rol ?? null,
  };
}

function normalizeMessages(list) {
  return (Array.isArray(list) ? list : []).map(normalizeMessage);
}

function readStorageKey(projectId, userId) {
  return `ods-chat-read:${projectId}:${userId}`;
}

function loadLastReadId(projectId, userId) {
  if (!projectId || !userId) return 0;
  try {
    const raw = sessionStorage.getItem(readStorageKey(projectId, userId));
    const n = parseInt(raw, 10);
    return Number.isFinite(n) ? n : 0;
  } catch {
    return 0;
  }
}

function saveLastReadId(projectId, userId, messageId) {
  if (!projectId || !userId || !messageId) return;
  try {
    sessionStorage.setItem(readStorageKey(projectId, userId), String(messageId));
  } catch {
    /* ignore */
  }
}

function maxMessageId(list) {
  if (!list?.length) return 0;
  return list.reduce((max, m) => Math.max(max, Number(m.id) || 0), 0);
}

function mergeMessages(prev, incoming) {
  if (!Array.isArray(incoming) || incoming.length === 0) return incoming;
  const byId = new Map(prev.map((m) => [m.id, m]));
  for (const m of incoming) byId.set(m.id, m);
  return [...byId.values()].sort(
    (a, b) => new Date(a.createdAt || 0) - new Date(b.createdAt || 0)
  );
}

export function useProjectChat(projectId, user, projectStatus, chatOpen = false) {
  const [messages, setMessages] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [draft, setDraft] = useState('');
  const [sending, setSending] = useState(false);
  const [lastReadId, setLastReadId] = useState(0);

  const isPlanificacion = String(projectStatus || '').toLowerCase() === 'planificacion';
  const actorRole = user?.role || '';
  const actorUserId = user?.id;

  useEffect(() => {
    setLastReadId(loadLastReadId(projectId, actorUserId));
  }, [projectId, actorUserId]);

  const markAllRead = useCallback(
    (list) => {
      const maxId = maxMessageId(list);
      if (maxId <= 0) return;
      setLastReadId(maxId);
      saveLastReadId(projectId, actorUserId, maxId);
    },
    [projectId, actorUserId]
  );

  const unreadCount = useMemo(() => {
    if (!messages.length || !actorUserId) return 0;
    return messages.filter(
      (m) =>
        (Number(m.id) || 0) > lastReadId &&
        Number(m.autorId) !== Number(actorUserId)
    ).length;
  }, [messages, lastReadId, actorUserId]);

  useEffect(() => {
    if (chatOpen && messages.length > 0) {
      markAllRead(messages);
    }
  }, [chatOpen, messages, markAllRead]);

  const canParticipate =
    isPlanificacion &&
    (actorRole === 'admin' ||
      actorRole === 'evaluador' ||
      (actorRole === 'gestor' && user?.id));

  const load = useCallback(async ({ silent = false } = {}) => {
    if (!projectId || !actorUserId || !actorRole) return;
    if (!silent) setLoading(true);
    setError('');
    try {
      const r = await chatService.listMessages(projectId, actorUserId, actorRole);
      const list = normalizeMessages(r.data);
      setMessages((prev) => (silent ? mergeMessages(prev, list) : list));
    } catch (e) {
      setError(e.response?.data?.error || e.message || 'Error al cargar chat');
      if (!silent) setMessages([]);
    } finally {
      if (!silent) setLoading(false);
    }
  }, [projectId, actorUserId, actorRole]);

  useEffect(() => {
    load();
  }, [load]);

  useEffect(() => {
    if (!projectId || !actorUserId || !actorRole) return;
    const timer = setInterval(() => load({ silent: true }), POLL_INTERVAL_MS);
    return () => clearInterval(timer);
  }, [load, projectId, actorUserId, actorRole]);

  const send = async () => {
    const text = draft.trim();
    if (!text || !canParticipate || sending) return;
    setSending(true);
    setError('');
    try {
      const r = await chatService.sendMessage(projectId, actorUserId, actorRole, text);
      setDraft('');
      if (r.data?.id) {
        setMessages((prev) => mergeMessages(prev, [normalizeMessage(r.data)]));
      }
      await load({ silent: true });
    } catch (e) {
      setError(e.response?.data?.error || 'No se pudo enviar');
    } finally {
      setSending(false);
    }
  };

  const edit = async (msgId, newBody) => {
    setError('');
    try {
      const r = await chatService.editMessage(projectId, msgId, actorUserId, newBody);
      if (r.data?.id) {
        const updated = normalizeMessage(r.data);
        setMessages((prev) =>
          prev.map((m) => (m.id === updated.id ? { ...m, ...updated } : m))
        );
      }
      await load({ silent: true });
    } catch (e) {
      setError(e.response?.data?.error || 'No se pudo editar');
    }
  };

  const canEditMessage = (msg) => {
    if (!isPlanificacion || !isSameChatUser(msg.autorId, actorUserId)) return false;
    if (!msg.createdAt) return false;
    const created = new Date(msg.createdAt).getTime();
    if (!Number.isFinite(created)) return false;
    return Date.now() - created <= EDIT_WINDOW_MS;
  };

  return {
    messages,
    unreadCount,
    loading,
    error,
    draft,
    setDraft,
    send,
    sending,
    edit,
    canParticipate,
    isPlanificacion,
    canEditMessage,
    reload: load,
    markAllRead,
  };
}
