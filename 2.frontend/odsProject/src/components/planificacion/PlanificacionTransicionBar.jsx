import React, { useState, useEffect } from 'react';
import './PlanificacionTransicionBar.css';

const dismissKey = (projectId, solicitudId) =>
  `ods-transicion-aprobada-seen-${projectId}-${solicitudId}`;

export default function PlanificacionTransicionBar({ user, transicion, projectId }) {
  const t = transicion;
  const [hideAprobado, setHideAprobado] = useState(false);

  useEffect(() => {
    if (!t?.aprobacionActivoVisible || !t?.ultimaSolicitud?.id || !projectId) {
      setHideAprobado(false);
      return;
    }
    try {
      setHideAprobado(localStorage.getItem(dismissKey(projectId, t.ultimaSolicitud.id)) === '1');
    } catch {
      setHideAprobado(false);
    }
  }, [t?.aprobacionActivoVisible, t?.ultimaSolicitud?.id, projectId]);

  if (!user || !t) return null;

  const showBar =
    (t.inPlanificacion && (t.isGestor || t.isReviewer)) ||
    (t.inActivo && (t.isReviewer || t.aprobacionActivoVisible));

  if (!showBar) return null;

  const pendiente = t.solicitud?.estadoSolicitud === 'pendiente';
  const showAprobadoBanner = t.aprobacionActivoVisible && !hideAprobado;

  const dismissAprobado = () => {
    if (projectId && t.ultimaSolicitud?.id) {
      try {
        localStorage.setItem(dismissKey(projectId, t.ultimaSolicitud.id), '1');
      } catch { /* ignore */ }
    }
    setHideAprobado(true);
  };

  return (
    <section
      className="planificacion-transicion-bar"
      style={{
        maxWidth: 'var(--container-max, 1200px)',
        margin: '1rem auto 1.5rem',
        padding: '1.25rem',
        background: showAprobadoBanner
          ? 'rgba(22, 163, 74, 0.1)'
          : 'rgba(249, 115, 22, 0.08)',
        border: showAprobadoBanner
          ? '1px solid rgba(22, 163, 74, 0.35)'
          : '1px solid rgba(249, 115, 22, 0.25)',
        borderRadius: '12px',
      }}
    >
      <h3 style={{ margin: '0 0 0.75rem', fontSize: '1.05rem' }}>
        Transición de planificación
      </h3>

      {showAprobadoBanner && (
        <div
          style={{
            marginBottom: '1rem',
            padding: '0.85rem 1rem',
            borderRadius: '8px',
            background: '#ecfdf5',
            border: '1px solid #86efac',
            color: '#166534',
            display: 'flex',
            justifyContent: 'space-between',
            gap: '12px',
            alignItems: 'flex-start',
          }}
        >
          <p style={{ margin: 0, fontWeight: 600, fontSize: '0.95rem' }}>
            Su proyecto ha sido aprobado y se encuentra activo.
          </p>
          <button
            type="button"
            className="ptb-btn ptb-btn--primary"
            onClick={dismissAprobado}
            style={{ flexShrink: 0, minHeight: '2rem', padding: '0.35rem 0.9rem', fontSize: '0.82rem' }}
          >
            Entendido
          </button>
        </div>
      )}

      {t.error && (
        <p style={{ color: '#b91c1c', fontSize: '0.85rem', marginBottom: '0.75rem' }}>
          {t.error}
        </p>
      )}

      {t.rechazoVisible && (
        <div
          style={{
            marginBottom: '1rem',
            padding: '0.85rem 1rem',
            borderRadius: '8px',
            background: '#fef2f2',
            border: '1px solid #fecaca',
            color: '#7f1d1d',
          }}
        >
          <strong>Solicitud rechazada.</strong> Puede editar la planificación y volver a solicitar.
          {t.ultimaSolicitud?.notaResolucion && (
            <p style={{ margin: '6px 0 0', fontSize: '0.9rem' }}>
              Nota del revisor: {t.ultimaSolicitud.notaResolucion}
            </p>
          )}
        </div>
      )}

      {t.inPlanificacion && t.isGestor && !pendiente && (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <p style={{ fontSize: '0.9rem', color: '#64748b', margin: 0 }}>
            Solicite pasar el proyecto a activo o cancelado. Un revisor debe aprobar.
          </p>
          <label style={{ fontSize: '0.85rem' }}>
            Destino:
            <select
              className="ptb-select"
              value={t.destino}
              onChange={(e) => t.setDestino(e.target.value)}
            >
              <option value="activo">Activo</option>
              <option value="cancelado">Cancelado</option>
            </select>
          </label>
          <textarea
            className="ptb-field"
            placeholder="Motivo de la solicitud…"
            value={t.motivo}
            onChange={(e) => t.setMotivo(e.target.value)}
            rows={2}
          />
          <div className="ptb-actions">
            <button
              type="button"
              className="ptb-btn ptb-btn--accent"
              disabled={t.loading}
              onClick={t.solicitar}
            >
              {t.loading ? 'Enviando…' : 'Solicitar transición'}
            </button>
          </div>
        </div>
      )}

      {pendiente && (
        <div style={{ marginBottom: '1rem', fontSize: '0.9rem' }}>
          <strong>Solicitud pendiente</strong> → {t.solicitud.estadoDestino}
          {t.solicitud.motivo && (
            <p style={{ margin: '4px 0 0', color: '#64748b' }}>{t.solicitud.motivo}</p>
          )}
        </div>
      )}

      {t.inPlanificacion && t.isReviewer && pendiente && (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <textarea
            className="ptb-field"
            placeholder="Nota de aprobación o rechazo (mín. 10 caracteres para rechazar)…"
            value={t.nota}
            onChange={(e) => t.setNota(e.target.value)}
            rows={2}
          />
          <div className="ptb-actions">
            <button
              type="button"
              className="ptb-btn ptb-btn--primary"
              disabled={t.loading}
              onClick={t.aprobar}
            >
              Aprobar
            </button>
            <button
              type="button"
              className="ptb-btn ptb-btn--ghost"
              disabled={t.loading}
              onClick={t.rechazar}
            >
              Rechazar
            </button>
          </div>
        </div>
      )}

      {t.inActivo && t.isReviewer && (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <p style={{ fontSize: '0.9rem', color: '#64748b', margin: 0 }}>
            Fuerza mayor: cancelar proyecto en estado activo.
          </p>
          <textarea
            className="ptb-field"
            placeholder="Motivo obligatorio…"
            value={t.motivo}
            onChange={(e) => t.setMotivo(e.target.value)}
            rows={2}
          />
          <div className="ptb-actions">
            <button
              type="button"
              className="ptb-btn ptb-btn--danger"
              disabled={t.loading}
              onClick={t.cancelarFuerzaMayor}
            >
              Cancelar (fuerza mayor)
            </button>
          </div>
        </div>
      )}
    </section>
  );
}
