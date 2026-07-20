#!/usr/bin/env node
/** Epic 1 — Hotfix walkthrough (bugs demo) */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Hotfix walkthrough — bugs demo planificación/evaluación",
  SPRINT_NAME: "Sprint hotfix walkthrough",
  EPIC_DESC:
    "Bugs de la demo: ODS no se quita, rename param, LOGRADO falso, fórmulas +−*/, chat visible, copy ES. Sin reinventar flujo.",
  PLAN_REL: "plans/plan_sprint_hotfix_walkthrough.html",
  SCRIPT_HINT: "sprint_hotfix_walkthrough.mjs",
  EPIC_COLOR: "#EF4444",
  ISSUES: {
    feOds: {
      title: "FE · Planificación — deseleccionar ODS limpia indicadores",
      role: "frontend",
      estimate: 3,
      type: "bug",
      description: `## Archivos
PlanificacionEditorPage.jsx, ProjectCreationPage.jsx, planificacionEditorUtils.js

## Checklist
- [ ] Al deseleccionar ODS N filtrar indicators/configs/metadata de ese ODS
- [ ] Payload odsIds e indicadores sin ese ODS
- [ ] Smoke: 2 ODS → quitar 1 → guardar → reload gestor/evaluador limpio
- [ ] npm run build`,
    },
    beParam: {
      title: "BE · Persist rename parámetro (nombreVariable) + fórmula",
      role: "backend",
      estimate: 5,
      type: "bug",
      description: `## Archivos
PlanificacionEdicionService.syncParametros, IndicatorConfigModal, planificacionEditorUtils, repos updateMetaProyecto, .http

## Checklist
- [ ] Reproducir Z→G: payload → BD → GET planificacion/editable
- [ ] Fix sync/update o mapeo FE (nombreParametro, nombreVariable, formula_custom)
- [ ] GATE_HTTP .http edición parámetro assert nombreVariable
- [ ] Testing E2E como demo
- [ ] **Handoff cross-epic:** comentar en Epic Unidades que IndicatorConfigModal está libre (unFeModal ítem 1)`,
    },
    feLogrado: {
      title: "FE · Cola evaluación — no LOGRADO solo por completado",
      role: "frontend",
      estimate: 2,
      type: "bug",
      description: `## Archivos
EvaluationQueuePage.jsx, AchievementBadge.jsx, ProjectResultsPage.jsx

## Checklist
- [ ] Quitar estado={isEvaluated ? 'LOGRADO' : 'SIN DATOS'}
- [ ] Badge usa pct/metaAlcanzada o «Evaluado» sin LOGRADO falso
- [ ] Caso 30% vs meta 70% → No alcanzó/BAJO
- [ ] npm run build`,
    },
    beFormula: {
      title: "BE · Motor fórmulas + tests (+ − * /)",
      role: "backend",
      estimate: 5,
      type: "bug",
      description: `## Archivos
EvaluationService.java, FormulaUtils, EvaluationController, tests, .http

## Checklist
- [ ] Tests: (100+100)/1=200; (100+100)/400=0.5; A+B/Z por nombreVariable
- [ ] Alinear preview FE vs BE si diverge
- [ ] GATE_HTTP preview/validar-formula asserts
- [ ] NO potencia/raíz/logaritmo`,
    },
    feChat: {
      title: "FE · Ocultar chat fuera de planificación",
      role: "frontend",
      estimate: 2,
      type: "bug",
      description: `## Archivos
ProjectResultsPage.jsx, ProjectChatPanel.jsx, useProjectChat.js

## Checklist
- [ ] No renderizar chat si estado ≠ planificacion
- [ ] Smoke: planificacion escribe; activo sin widget
- [ ] npm run build`,
    },
    feCopy: {
      title: "FE · Copy evidencia + labels ES (Percentage)",
      role: "frontend",
      estimate: 1,
      type: "bug",
      description: `## Archivos
EvidenceSection, EvaluationPage, IndicatorCard

## Checklist
- [ ] Copy: «De forma opcional puede escribir una breve descripción para el evaluador»
- [ ] Percentage → Porcentaje en UI
- [ ] npm run build`,
    },
    orch: {
      title: "ORCH · resumen hotfix + Epic Completed",
      role: "orchestrator",
      estimate: 1,
      type: "feature",
      description: `## Checklist
- [ ] ⏸ Todos issues hotfix Done checklist 100%
- [ ] resumen_sprint_hotfix_walkthrough.html
- [ ] Epic Completed
- [ ] Señal cross-epic: Unidades puede tomar IndicatorConfigModal`,
    },
  },
  BLOCKS: [
    ["orch", "feOds"],
    ["orch", "beParam"],
    ["orch", "feLogrado"],
    ["orch", "beFormula"],
    ["orch", "feChat"],
    ["orch", "feCopy"],
  ],
});
