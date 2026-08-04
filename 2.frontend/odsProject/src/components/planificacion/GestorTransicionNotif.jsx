import React, { useCallback, useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { transicionService } from '../../services/transicionService';
import { useSilentPoll } from '../../hooks/useSilentPoll';

const dismissKey = (solicitudId) => `ods-transicion-global-seen-${solicitudId}`;
const AUTO_DISMISS_MS = 5000;

/**
 * Banner global para gestor: resoluciones de transición (aprobada → activo / rechazada).
 * Visible en Dashboard y cualquier ruta autenticada.
 * Si el usuario no pulsa Entendido, se oculta solo a los 5 s (y no vuelve a aparecer).
 */
export default function GestorTransicionNotif() {
  const { user } = useAuth();
  const navigate = useNavigate();
  const [items, setItems] = useState([]);
  const autoTimersRef = useRef(new Map());

  const isGestor = user?.role === 'gestor' && user?.id;

  const dismiss = useCallback((id) => {
    const tid = autoTimersRef.current.get(id);
    if (tid) {
      clearTimeout(tid);
      autoTimersRef.current.delete(id);
    }
    try { localStorage.setItem(dismissKey(id), '1'); } catch { /* ignore */ }
    setItems((prev) => prev.filter((x) => x.id !== id));
  }, []);

  const load = useCallback(async () => {
    if (!isGestor) return;
    try {
      const r = await transicionService.listRecientes(user.id, 'gestor');
      const list = r.data?.items || [];
      const visible = list.filter((it) => {
        if (!it?.id) return false;
        try {
          if (localStorage.getItem(dismissKey(it.id)) === '1') return false;
        } catch { /* ignore */ }
        const est = String(it.estadoSolicitud || '').toLowerCase();
        const dest = String(it.estadoDestino || '').toLowerCase();
        const proy = String(it.estadoProyecto || '').toLowerCase();
        if (est === 'aprobada' && dest === 'activo' && proy === 'activo') return true;
        if (est === 'rechazada') return true;
        if (est === 'aprobada' && dest === 'cancelado' && proy === 'cancelado') return true;
        return false;
      });
      setItems(visible.slice(0, 5));
    } catch {
      /* silencioso */
    }
  }, [isGestor, user?.id]);

  useEffect(() => { load(); }, [load]);
  useSilentPoll(load, 5000, !!isGestor);

  // Un timer por ítem desde la primera aparición (el poll no reinicia el conteo).
  useEffect(() => {
    const visibleIds = new Set(items.map((it) => it.id));
    for (const [id, tid] of autoTimersRef.current) {
      if (!visibleIds.has(id)) {
        clearTimeout(tid);
        autoTimersRef.current.delete(id);
      }
    }
    for (const it of items) {
      if (autoTimersRef.current.has(it.id)) continue;
      const tid = setTimeout(() => dismiss(it.id), AUTO_DISMISS_MS);
      autoTimersRef.current.set(it.id, tid);
    }
  }, [items, dismiss]);

  useEffect(() => () => {
    for (const tid of autoTimersRef.current.values()) clearTimeout(tid);
    autoTimersRef.current.clear();
  }, []);

  if (!isGestor || items.length === 0) return null;

  return (
    <div
      className="gestor-transicion-notif"
      style={{
        position: 'fixed',
        top: 12,
        left: '50%',
        transform: 'translateX(-50%)',
        zIndex: 9999,
        width: 'min(560px, calc(100vw - 24px))',
        display: 'flex',
        flexDirection: 'column',
        gap: 8,
        pointerEvents: 'none',
      }}
    >
      {items.map((it) => {
        const aprobada = String(it.estadoSolicitud).toLowerCase() === 'aprobada';
        const activo = String(it.estadoDestino).toLowerCase() === 'activo';
        const cancelado = String(it.estadoDestino).toLowerCase() === 'cancelado';
        const green = aprobada && activo;
        const nombre = it.nombreProyecto || '#' + it.proyectoId;
        const nota = String(it.notaResolucion || '');
        const motivo = String(it.motivo || '');
        const esFuerzaMayor = /fuerza\s*mayor/i.test(nota) || /fuerza\s*mayor/i.test(motivo);
        const msg = green
          ? `Su proyecto «${nombre}» ha sido aprobado y se encuentra activo.`
          : aprobada && cancelado && esFuerzaMayor
            ? `Su proyecto «${nombre}» fue cancelado por fuerza mayor.${nota ? ' ' + nota : ''}`
            : aprobada && cancelado
              ? `Su proyecto «${nombre}» fue pasado a cancelado.`
              : `Solicitud rechazada para «${nombre}».${nota ? ' Nota: ' + nota : ''} Puede editar y volver a solicitar.`;

        return (
          <div
            key={it.id}
            style={{
              pointerEvents: 'auto',
              padding: '12px 14px',
              borderRadius: 10,
              background: green ? '#ecfdf5' : '#fef2f2',
              border: `1px solid ${green ? '#86efac' : '#fecaca'}`,
              color: green ? '#166534' : '#7f1d1d',
              boxShadow: '0 8px 24px rgba(0,0,0,0.12)',
              display: 'flex',
              gap: 10,
              alignItems: 'flex-start',
            }}
          >
            <div style={{ flex: 1, fontSize: 14, fontWeight: 600, lineHeight: 1.4 }}>
              {msg}
              <div style={{ marginTop: 8, display: 'flex', gap: 8, flexWrap: 'wrap' }}>
                <button
                  type="button"
                  onClick={() => navigate(`/projects/${it.proyectoId}/results`)}
                  style={{
                    border: 'none',
                    background: green ? '#16a34a' : '#b91c1c',
                    color: '#fff',
                    borderRadius: 6,
                    padding: '4px 10px',
                    fontWeight: 600,
                    cursor: 'pointer',
                    fontSize: 12,
                  }}
                >
                  Ver proyecto
                </button>
                <button
                  type="button"
                  onClick={() => dismiss(it.id)}
                  style={{
                    border: `1px solid ${green ? '#86efac' : '#fecaca'}`,
                    background: 'transparent',
                    color: 'inherit',
                    borderRadius: 6,
                    padding: '4px 10px',
                    fontWeight: 600,
                    cursor: 'pointer',
                    fontSize: 12,
                  }}
                >
                  Entendido
                </button>
              </div>
            </div>
          </div>
        );
      })}
    </div>
  );
}
