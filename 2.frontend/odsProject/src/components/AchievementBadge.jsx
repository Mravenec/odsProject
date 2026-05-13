import React from 'react';
import { CheckCircle2, Clock, TrendingDown, XCircle, HelpCircle } from 'lucide-react';

/**
 * Sprint 14 — Badge que muestra si un indicador / proyecto alcanzó la meta.
 *
 * Lee uno de:
 *   - estado: string "LOGRADO" | "CERCA META" | "PROGRESO" | "BAJO" | "SIN DATOS"
 *   - porcentaje: number (0-200)
 *
 * Si recibe solo porcentaje, deriva el estado.
 */
const ESTADO_CONFIG = {
  'LOGRADO':    { label: '✅ Logrado',        color: '#166534', bg: '#dcfce7', border: '#86efac', icon: CheckCircle2 },
  'CERCA META': { label: '🔵 Cerca de meta',  color: '#1e40af', bg: '#dbeafe', border: '#93c5fd', icon: Clock },
  'PROGRESO':   { label: '🟡 En progreso',    color: '#92400e', bg: '#fef3c7', border: '#fde68a', icon: TrendingDown },
  'BAJO':       { label: '🔴 No alcanzó',     color: '#991b1b', bg: '#fee2e2', border: '#fca5a5', icon: XCircle },
  'SIN DATOS':  { label: '⚪ Sin auditar',    color: '#6b7280', bg: '#f3f4f6', border: '#d1d5db', icon: HelpCircle },
};

export function deriveEstado(porcentaje) {
  if (porcentaje == null || isNaN(porcentaje)) return 'SIN DATOS';
  const p = Number(porcentaje);
  if (p >= 100) return 'LOGRADO';
  if (p >= 80)  return 'CERCA META';
  if (p >= 50)  return 'PROGRESO';
  return 'BAJO';
}

export default function AchievementBadge({ estado, porcentaje, size = 'md', showPct = true }) {
  const finalEstado = estado || deriveEstado(porcentaje);
  const cfg = ESTADO_CONFIG[finalEstado] || ESTADO_CONFIG['SIN DATOS'];
  const Icon = cfg.icon;
  const padding = size === 'sm' ? '3px 8px' : size === 'lg' ? '8px 16px' : '5px 12px';
  const fontSize = size === 'sm' ? 11 : size === 'lg' ? 14 : 12;
  const iconSize = size === 'sm' ? 12 : size === 'lg' ? 18 : 14;
  return (
    <div style={{
      display:'inline-flex',alignItems:'center',gap:6,padding,borderRadius:99,
      background:cfg.bg,color:cfg.color,border:`1px solid ${cfg.border}`,
      fontSize,fontWeight:600,whiteSpace:'nowrap'
    }}>
      <Icon size={iconSize} />
      <span>{cfg.label}</span>
      {showPct && porcentaje != null && (
        <span style={{opacity:0.8,marginLeft:4,fontVariantNumeric:'tabular-nums'}}>
          ({Number(porcentaje).toFixed(0)}%)
        </span>
      )}
    </div>
  );
}
