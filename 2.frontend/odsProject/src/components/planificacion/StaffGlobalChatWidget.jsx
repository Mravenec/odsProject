import React, { useState, useEffect, useRef } from 'react';
import { createPortal } from 'react-dom';
import { useNavigate } from 'react-router-dom';
import {
  MessageCircle, ChevronDown, ChevronLeft, Send, ExternalLink,
} from 'lucide-react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useStaffChatInbox } from '../../hooks/useStaffChatInbox';
import {
  useProjectChat,
  isSameChatUser,
  isStaffSideMessage,
  chatAuthorRoleLabel,
  setChatLastReadId,
} from '../../hooks/useProjectChat';
import './ProjectChatPanel.css';
import './StaffGlobalChatWidget.css';

function ThreadConversation({ user, thread, onBackToList, onClose }) {
  const navigate = useNavigate();
  const [open] = useState(true);
  const chat = useProjectChat(thread.proyectoId, user, thread.estado || 'planificacion', open);
  const [editingId, setEditingId] = useState(null);
  const [editText, setEditText] = useState('');
  const messagesEndRef = useRef(null);

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [chat.messages, chat.loading]);

  useEffect(() => {
    const maxId = (chat.messages || []).reduce(
      (max, m) => Math.max(max, Number(m.id) || 0),
      Number(thread.lastMessageId) || 0,
    );
    if (maxId > 0) {
      setChatLastReadId(thread.proyectoId, user.id, maxId);
    }
  }, [thread.proyectoId, thread.lastMessageId, user.id, chat.messages]);

  const projectHref = `/projects/${thread.proyectoId}/results`;

  return (
    <>
      <header className="chat-widget-header">
        <button
          type="button"
          className="chat-widget-icon-btn"
          onClick={onBackToList}
          aria-label="Volver a la bandeja"
          title="Volver"
        >
          <ChevronLeft size={18} />
        </button>
        <div className="chat-widget-header-text">
          <h3>{thread.nombreProyecto}</h3>
          <p>Chat de planificación</p>
        </div>
        <div className="chat-widget-header-actions">
          <button
            type="button"
            className="chat-widget-icon-btn"
            onClick={onClose}
            aria-label="Minimizar conversación"
            title="Minimizar"
          >
            <ChevronDown size={18} />
          </button>
        </div>
      </header>

      <a
        className="staff-chat-project-link"
        href={projectHref}
        onClick={(e) => {
          e.preventDefault();
          navigate(projectHref);
        }}
      >
        <ExternalLink size={14} />
        Ir al proyecto #{thread.proyectoId}
      </a>

      {chat.error && <p className="chat-widget-error">{chat.error}</p>}

      <div className="chat-widget-messages">
        {chat.loading && chat.messages.length === 0 && (
          <p className="chat-widget-loading">Cargando mensajes…</p>
        )}
        {!chat.loading && chat.messages.length === 0 && (
          <p className="chat-widget-empty">Sin mensajes aún.</p>
        )}
        {chat.messages.map((m) => {
          const staffSide = isStaffSideMessage(m, thread.gestorUserId);
          const ownMessage = isSameChatUser(m.autorId, user.id);
          const canEdit = chat.canEditMessage(m);
          const roleLabel = chatAuthorRoleLabel(m, thread.gestorUserId);
          return (
            <div
              key={m.id}
              className={`chat-widget-bubble-wrap ${staffSide ? 'chat-widget-bubble-wrap--staff' : 'chat-widget-bubble-wrap--gestor'}`}
            >
              <span
                className={`chat-widget-author ${staffSide ? 'chat-widget-author--staff' : 'chat-widget-author--gestor'}`}
              >
                <span className="chat-widget-author-name">
                  {m.autorNombre || `Usuario ${m.autorId}`}
                </span>
                <span className="chat-widget-author-role">{roleLabel}</span>
              </span>
              {editingId === m.id ? (
                <div className="chat-widget-edit">
                  <textarea
                    value={editText}
                    onChange={(e) => setEditText(e.target.value)}
                    rows={2}
                    aria-label="Editar mensaje"
                  />
                  <div className="chat-widget-edit-actions">
                    <button
                      type="button"
                      onClick={() => {
                        chat.edit(m.id, editText);
                        setEditingId(null);
                      }}
                    >
                      Guardar
                    </button>
                    <button type="button" onClick={() => setEditingId(null)}>
                      Cancelar
                    </button>
                  </div>
                </div>
              ) : (
                <>
                  <div className="chat-widget-bubble">{m.cuerpo}</div>
                  {(m.editCount > 0 || (ownMessage && canEdit)) && (
                    <span className={`chat-widget-meta ${staffSide ? 'chat-widget-meta--staff' : ''}`}>
                      {m.editCount > 0 && 'Editado · '}
                      {ownMessage && canEdit && (
                        <button
                          type="button"
                          className="chat-widget-edit-link"
                          onClick={() => {
                            setEditingId(m.id);
                            setEditText(m.cuerpo);
                          }}
                        >
                          Editar mi mensaje
                        </button>
                      )}
                    </span>
                  )}
                </>
              )}
            </div>
          );
        })}
        <div ref={messagesEndRef} />
      </div>

      {chat.canParticipate && (
        <form
          className="chat-widget-composer"
          onSubmit={(e) => {
            e.preventDefault();
            chat.send();
          }}
        >
          <input
            type="text"
            placeholder="Escribe un mensaje…"
            value={chat.draft}
            onChange={(e) => chat.setDraft(e.target.value)}
            aria-label="Mensaje"
          />
          <button
            type="submit"
            className="chat-widget-send"
            disabled={!chat.draft.trim() || chat.sending}
            aria-label="Enviar"
          >
            <Send size={18} />
          </button>
        </form>
      )}
    </>
  );
}

/**
 * Burbuja global de chat para admin y evaluador (cualquier pantalla).
 */
export default function StaffGlobalChatWidget() {
  const { user } = useAuth();
  const inbox = useStaffChatInbox(user);
  const [open, setOpen] = useState(false);
  const [activeThread, setActiveThread] = useState(null);
  const prevUnreadRef = useRef(0);

  useEffect(() => {
    if (!inbox.enabled) return;
    const prev = prevUnreadRef.current;
    if (inbox.unreadThreadCount > prev && inbox.unreadThreadCount > 0) {
      setOpen(true);
      if (!activeThread && inbox.threads.length === 1) {
        const only = inbox.threads.find((t) => t.unread) || inbox.threads[0];
        if (only) setActiveThread(only);
      }
    }
    prevUnreadRef.current = inbox.unreadThreadCount;
  }, [inbox.enabled, inbox.unreadThreadCount, inbox.threads, activeThread]);

  if (!inbox.enabled || !user) return null;

  const badge = inbox.unreadThreadCount;

  const widget = (
    <div className="chat-widget-root staff-global-chat" role="region" aria-label="Chats de planificación">
      <button
        type="button"
        className={`chat-widget-fab ${open ? 'chat-widget-fab--hidden' : ''}${badge > 0 ? ' chat-widget-fab--pulse' : ''}`}
        onClick={() => {
          setOpen(true);
          inbox.reload({ silent: true });
        }}
        aria-label="Abrir chats de planificación"
        title="Chats de planificación"
        style={{ position: 'relative' }}
      >
        <MessageCircle size={22} strokeWidth={2} />
        {badge > 0 && (
          <span className="chat-widget-badge" aria-label={`${badge} conversaciones con mensajes nuevos`}>
            {badge > 99 ? '99+' : badge}
          </span>
        )}
      </button>

      {open && (
        <div className="chat-widget-panel">
          {activeThread ? (
            <ThreadConversation
              user={user}
              thread={activeThread}
              onBackToList={() => {
                setActiveThread(null);
                inbox.reload({ silent: true });
              }}
              onClose={() => {
                setActiveThread(null);
                inbox.reload({ silent: true });
                setOpen(false);
              }}
            />
          ) : (
            <>
              <header className="chat-widget-header">
                <div className="chat-widget-header-text">
                  <h3>Chats de planificación</h3>
                  <p>Proyectos con mensajes recientes</p>
                </div>
                <div className="chat-widget-header-actions">
                  <button
                    type="button"
                    className="chat-widget-icon-btn"
                    onClick={() => setOpen(false)}
                    aria-label="Minimizar"
                    title="Minimizar"
                  >
                    <ChevronDown size={18} />
                  </button>
                </div>
              </header>

              {inbox.error && <p className="chat-widget-error">{inbox.error}</p>}

              <div className="staff-chat-inbox-list">
                {inbox.loading && inbox.threads.length === 0 && (
                  <p className="chat-widget-loading">Cargando bandeja…</p>
                )}
                {!inbox.loading && inbox.threads.length === 0 && (
                  <p className="chat-widget-empty">
                    No hay chats activos.
                    <br />
                    <span style={{ fontSize: '0.8rem' }}>
                      Cuando un gestor escriba en un proyecto, aparecerá aquí.
                    </span>
                  </p>
                )}
                {inbox.threads.map((t) => (
                  <button
                    key={t.proyectoId}
                    type="button"
                    className={`staff-chat-inbox-item${t.unread ? ' staff-chat-inbox-item--unread' : ''}`}
                    onClick={() => setActiveThread(t)}
                  >
                    <div className="staff-chat-inbox-item-top">
                      <span className="staff-chat-inbox-title">{t.nombreProyecto}</span>
                      {t.unread && <span className="staff-chat-inbox-dot" aria-hidden />}
                    </div>
                    <span className="staff-chat-inbox-preview">
                      {(t.lastAutorNombre || 'Usuario') + ': '}
                      {(t.lastCuerpo || '').slice(0, 80)}
                      {(t.lastCuerpo || '').length > 80 ? '…' : ''}
                    </span>
                  </button>
                ))}
              </div>
            </>
          )}
        </div>
      )}
    </div>
  );

  return createPortal(widget, document.body);
}
