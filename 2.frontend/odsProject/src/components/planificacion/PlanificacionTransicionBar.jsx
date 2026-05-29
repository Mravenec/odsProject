import React from 'react';
import { usePlanificacionTransicion } from '../../hooks/usePlanificacionTransicion';

export default function PlanificacionTransicionBar({
  projectId,
  user,
  projectStatus,
  onProjectUpdated,
}) {
  const t = usePlanificacionTransicion(projectId, user, projectStatus, onProjectUpdated);

  if (!projectId || !user) return null;

  const showBar =
    (t.inPlanificacion && (t.isGestor || t.isReviewer)) ||
    (t.inActivo && t.isReviewer);

  if (!showBar) return null;

  const pendiente = t.solicitud?.estadoSolicitud === 'pendiente';

  return (
    <section
      className="planificacion-transicion-bar"
      style={{
        maxWidth: 'var(--container-max, 1200px)',
        margin: '1rem auto 1.5rem',
        padding: '1.25rem',
        background: 'rgba(249, 115, 22, 0.08)',
        border: '1px solid rgba(249, 115, 22, 0.25)',
        borderRadius: '12px',
      }}
    >
      <h3 style={{ margin: '0 0 0.75rem', fontSize: '1.05rem' }}>
        Transición de planificación
      </h3>

      {t.error && (
        <p style={{ color: '#FCA5A5', fontSize: '0.85rem', marginBottom: '0.75rem' }}>
          {t.error}
        </p>
      )}

      {t.inPlanificacion && t.isGestor && !pendiente && (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <p style={{ fontSize: '0.9rem', color: '#94A3B8', margin: 0 }}>
            Solicite pasar el proyecto a activo o cancelado. Un revisor debe aprobar.
          </p>
          <label style={{ fontSize: '0.85rem' }}>
            Destino:
            <select
              value={t.destino}
              onChange={(e) => t.setDestino(e.target.value)}
              style={{ marginLeft: '8px' }}
            >
              <option value="activo">Activo</option>
              <option value="cancelado">Cancelado</option>
            </select>
          </label>
          <textarea
            placeholder="Motivo de la solicitud…"
            value={t.motivo}
            onChange={(e) => t.setMotivo(e.target.value)}
            rows={2}
            style={{ width: '100%', padding: '0.5rem' }}
          />
          <button type="button" disabled={t.loading} onClick={t.solicitar}>
            {t.loading ? 'Enviando…' : 'Solicitar transición'}
          </button>
        </div>
      )}

      {pendiente && (
        <div style={{ marginBottom: '1rem', fontSize: '0.9rem' }}>
          <strong>Solicitud pendiente</strong> → {t.solicitud.estadoDestino}
          {t.solicitud.motivo && (
            <p style={{ margin: '4px 0 0', color: '#94A3B8' }}>{t.solicitud.motivo}</p>
          )}
        </div>
      )}

      {t.inPlanificacion && t.isReviewer && pendiente && (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <textarea
            placeholder="Nota de aprobación o rechazo (mín. 10 caracteres para rechazar)…"
            value={t.nota}
            onChange={(e) => t.setNota(e.target.value)}
            rows={2}
            style={{ width: '100%', padding: '0.5rem' }}
          />
          <div style={{ display: 'flex', gap: '8px', flexWrap: 'wrap' }}>
            <button type="button" disabled={t.loading} onClick={t.aprobar}>
              Aprobar
            </button>
            <button type="button" disabled={t.loading} onClick={t.rechazar}>
              Rechazar
            </button>
          </div>
        </div>
      )}

      {t.inActivo && t.isReviewer && (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '8px' }}>
          <p style={{ fontSize: '0.9rem', color: '#94A3B8', margin: 0 }}>
            Fuerza mayor: cancelar proyecto en estado activo.
          </p>
          <textarea
            placeholder="Motivo obligatorio…"
            value={t.motivo}
            onChange={(e) => t.setMotivo(e.target.value)}
            rows={2}
            style={{ width: '100%', padding: '0.5rem' }}
          />
          <button type="button" disabled={t.loading} onClick={t.cancelarFuerzaMayor}>
            Cancelar (fuerza mayor)
          </button>
        </div>
      )}
    </section>
  );
}
