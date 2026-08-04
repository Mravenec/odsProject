#!/usr/bin/env node
/** Epic — Notif global + auto-refresh */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Notif transición global + auto-refresh UI",
  SPRINT_NAME: "notif_refresh",
  EPIC_DESC:
    "Banner verde/rojo de transición visible en Dashboard (App-level). Poll silencioso en listas/cola/detalle para todos los roles.",
  PLAN_REL: "plans/plan_sprint_notif_refresh.html",
  SCRIPT_HINT: "sprint_notif_refresh.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    beRecientes: {
      title: "BE · GET bandeja resoluciones del gestor",
      role: "backend",
      estimate: 3,
      type: "feature",
      description: `## Archivos
TransicionPlanificacionController/Service/Repository, transicion_planificacion.http

## Checklist
- [ ] GET /api/projects/planificacion/solicitudes/recientes?actorUserId=&actorRole=gestor → resoluciones recientes (aprobada/rechazada) de proyectos del dueño
- [ ] Incluir proyectoId, nombre, estadoDestino, estadoSolicitud, notaResolucion, resueltoEn
- [ ] .http 2xx con asserts
- [ ] Testing GATE_HTTP
- [ ] Handoff FE ítem 1`,
    },
    feGlobal: {
      title: "FE · Banner global + poll silencioso listas",
      role: "frontend",
      estimate: 4,
      type: "feature",
      description: `## Archivos
App.jsx, GestorTransicionNotif, useProjects, useEvaluationQueue, usePlanificacionTransicion, useProjectResultsDetail

## Checklist
- [ ] ⏸ Gate: NO iniciar hasta BE handoff ítem 1 [x]
- [ ] Widget global GestorTransicionNotif (App.jsx): poll recientes; banner verde aprobado/activo (y rojo rechazo) dismissible localStorage — visible en Dashboard y cualquier ruta
- [ ] useSilentPoll / intervals: useProjects, useEvaluationQueue, usePlanificacionTransicion, detalle proyecto (silent, sin spinner)
- [ ] npm run build; smoke: aprobar sin F5 → gestor en Dashboard ve aviso; cola admin se actualiza sola`,
    },
  },
  BLOCKS: [["feGlobal", "beRecientes"]],
});
