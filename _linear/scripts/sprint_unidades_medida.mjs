#!/usr/bin/env node
/** Epic 4 — Unidades custom (texto libre, sin catálogo BD) */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Unidades de medida personalizadas (texto libre)",
  SPRINT_NAME: "Sprint unidades medida",
  EPIC_DESC:
    "Permitir unidades custom (nm, NTU, ₡) vía meta_unidad texto libre. Sin catálogo BD. FE modal espera Hotfix beParam Done (mismo IndicatorConfigModal).",
  PLAN_REL: "plans/plan_sprint_unidades_medida.html",
  SCRIPT_HINT: "sprint_unidades_medida.mjs",
  EPIC_COLOR: "#EAB308",
  ISSUES: {
    beUnidad: {
      title: "BE · Aceptar/persistir meta_unidad texto libre",
      role: "backend",
      estimate: 3,
      type: "feature",
      description: `## Archivos
Planificación update indicadores, .http

## Checklist
- [ ] Persist meta_unidad texto libre (nm, NTU, colones…)
- [ ] GATE_HTTP assert unidad custom
- [ ] **Handoff:** marcar feModal checklist ítem 2`,
    },
    feModal: {
      title: "FE · IndicatorConfigModal unidad custom",
      role: "frontend",
      estimate: 5,
      type: "feature",
      description: `## Archivos
IndicatorConfigModal.jsx

## Cross-epic
Hotfix walkthrough · BE Persist rename (mismo archivo) debe estar Done

## Checklist
- [ ] ⏸ Cross-epic: NO iniciar hasta Hotfix beParam Done (IndicatorConfigModal libre)
- [ ] ⏸ Gate BE: ítem 2 [x] por handoff beUnidad
- [ ] UI agregar/editar unidad personalizada (nm, NTU, ₡…)
- [ ] Persiste y se ve en evaluación
- [ ] npm run build`,
    },
    orch: {
      title: "ORCH · resumen unidades + Epic Completed",
      role: "orchestrator",
      estimate: 1,
      type: "feature",
      description: `## Checklist
- [ ] ⏸ feModal Done
- [ ] resumen_sprint_unidades_medida.html
- [ ] Epic Completed`,
    },
  },
  BLOCKS: [
    ["feModal", "beUnidad"],
    ["orch", "feModal"],
  ],
});
