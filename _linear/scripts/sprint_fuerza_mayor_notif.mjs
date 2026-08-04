#!/usr/bin/env node
/** Epic — Aviso fuerza mayor al gestor */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Aviso gestor al cancelar por fuerza mayor",
  SPRINT_NAME: "fuerza_mayor_notif",
  EPIC_DESC:
    "Tras fuerzaMayor, registrar resolución en bandeja del gestor para banner global.",
  PLAN_REL: "plans/plan_sprint_fuerza_mayor_notif.html",
  SCRIPT_HINT: "sprint_fuerza_mayor_notif.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    beFm: {
      title: "BE · Registrar resolución al fuerzaMayor",
      role: "backend",
      estimate: 2,
      type: "feature",
      description: `## Archivos
TransicionPlanificacionService.java, TransicionPlanificacionRepository.java, transicion_planificacion.http

## Checklist
- [ ] Tras fuerzaMayor OK: insertar resolución (destino cancelado, aprobada, nota «Fuerza mayor: …») para el dueño
- [ ] Aparece en GET solicitudes/recientes del gestor
- [ ] .http + Testing GATE_HTTP
- [ ] Handoff FE ítem 1`,
    },
    feFm: {
      title: "FE · Texto banner fuerza mayor",
      role: "frontend",
      estimate: 1,
      type: "improvement",
      description: `## Archivos
GestorTransicionNotif.jsx

## Checklist
- [ ] ⏸ Gate: NO iniciar hasta BE handoff ítem 1 [x]
- [ ] GestorTransicionNotif: mensaje claro si nota/motivo es fuerza mayor
- [ ] npm run build`,
    },
  },
  BLOCKS: [["feFm", "beFm"]],
});
