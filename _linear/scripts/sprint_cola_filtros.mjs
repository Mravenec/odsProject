#!/usr/bin/env node
/** Epic — Filtros cola evaluación por estado */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Cola evaluación — filtros por estado (5 tabs)",
  SPRINT_NAME: "cola_filtros",
  EPIC_DESC:
    "Quitar Todos. Tabs: Planificación | Activos (sin doc) | En curso (con doc) | Pendientes | Evaluados.",
  PLAN_REL: "plans/plan_sprint_cola_filtros.html",
  SCRIPT_HINT: "sprint_cola_filtros.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    feFiltros: {
      title: "FE · Redefinir filterTabs y predicados cola evaluación",
      role: "frontend",
      estimate: 2,
      type: "improvement",
      description: `## Archivos
EvaluationQueuePage.jsx

## Checklist
- [ ] Quitar tab Todos; counts + filtered según 5 reglas (planificacion / activos / en_curso / pendientes / evaluados)
- [ ] filterTabs orden: Planificación | Activos | En curso (con documento) | Pendientes de evaluación | Evaluados
- [ ] npm run build; smoke: evaluados no aparecen fuera de Evaluados; activos sin doc solo en Activos`,
    },
  },
  BLOCKS: [],
});
