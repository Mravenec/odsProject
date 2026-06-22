import React, { useState, useEffect, useRef } from 'react';
import { createPortal } from 'react-dom';
import { MessageCircle, ChevronDown, Send } from 'lucide-react';
import {
  useProjectChat,
  isSameChatUser,
  isStaffSideMessage,
  chatAuthorRoleLabel,
} from '../../hooks/useProjectChat';
import './ProjectChatPanel.css';

export default function ProjectChatPanel({ projectId, user, projectStatus, projectOwnerUserId }) {
  const [open, setOpen] = useState(false);
  const chat = useProjectChat(projectId, user, projectStatus, open);
  const [editingId, setEditingId] = useState(null);
  const [editText, setEditText] = useState('');
  const messagesEndRef = useRef(null);
  const listRef = useRef(null);

  useEffect(() => {
    if (!open) return;
    messagesEndRef.current?.scrollIntoView({ behavior: 'smooth' });
  }, [open, chat.messages, chat.loading]);

  if (!projectId || !user) return null;

  const unread = chat.unreadCount;
  const messageCount = chat.messages.length;
  const statusLabel = chat.isPlanificacion
    ? 'Puede escribir; solo editar sus propios mensajes (30 min)'
    : 'Solo lectura';

  const widget = (
    <div className="chat-widget-root" role="region" aria-label="Chat de planificación">
      <button
        type="button"
        className={`chat-widget-fab ${open ? 'chat-widget-fab--hidden' : ''}`}
        onClick={() => setOpen(true)}
        aria-label="Abrir chat de planificación"
        title="Chat de planificación"
        style={{ position: 'relative' }}
      >
        <MessageCircle size={22} strokeWidth={2} />
        {unread > 0 && (
          <span className="chat-widget-badge" aria-label={`${unread} mensajes no leídos`}>
            {unread > 99 ? '99+' : unread}
          </span>
        )}
      </button>

      {open && (
        <div className="chat-widget-panel">
          <header className="chat-widget-header">
            <div className="chat-widget-header-text">
              <h3>Planificación</h3>
              <p>{statusLabel}</p>
            </div>
            <div className="chat-widget-header-actions">
              <button
                type="button"
                className="chat-widget-icon-btn"
                onClick={() => setOpen(false)}
                aria-label="Minimizar chat"
                title="Minimizar"
              >
                <ChevronDown size={18} />
              </button>
            </div>
          </header>

          {!chat.isPlanificacion && (
            <p className="chat-widget-readonly">
              El proyecto ya no está en planificación. Puede leer el historial.
            </p>
          )}

          {chat.error && <p className="chat-widget-error">{chat.error}</p>}

          <div className="chat-widget-messages" ref={listRef}>
            {chat.loading && messageCount === 0 && (
              <p className="chat-widget-loading">Cargando mensajes…</p>
            )}
            {!chat.loading && messageCount === 0 && (
              <p className="chat-widget-empty">
                Sin mensajes aún.
                <br />
                <span style={{ fontSize: '0.8rem' }}>Escriba el primero abajo.</span>
              </p>
            )}
            {!chat.loading &&
              chat.messages.map((m) => {
                const staffSide = isStaffSideMessage(m, projectOwnerUserId);
                const ownMessage = isSameChatUser(m.autorId, user.id);
                const canEdit = chat.canEditMessage(m);
                const roleLabel = chatAuthorRoleLabel(m, projectOwnerUserId);
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
                            {ownMessage && canEdit && editingId !== m.id && (
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
        </div>
      )}
    </div>
  );

  return createPortal(widget, document.body);
}
