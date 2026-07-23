#!/usr/bin/env node
/** Epic — Fórmula ÷100, raíz, potencia + validación guardar */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Fórmula indicador — ÷100, raíz, potencia + validación guardar",
  SPRINT_NAME: "formula_ops",
  EPIC_DESC:
    "IndicatorConfigModal: chips ÷ 100, sqrt, ^; EvaluationService documenta ops; no Guardar si validar-formula inválida (panel rojo).",
  PLAN_REL: "plans/plan_sprint_formula_ops.html",
  SCRIPT_HINT: "sprint_formula_ops.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    beMotor: {
      title: "BE · Motor: documentar + HTTP √ / ^ / ÷100",
      role: "backend",
      estimate: 2,
      type: "improvement",
      description: `## Archivos
EvaluationService.java, EvaluationServiceTest.java, EvaluationController.http

## Checklist
- [ ] EvaluationService: documentar operadores de negocio + − * / ^ y función sqrt (además de paréntesis)
- [ ] Tests unitarios: evaluate/validate con p1/100, sqrt(p1), p1^2; sintaxis inválida p1/p2)*100 → false
- [ ] EvaluationController.http: casos preview + validar-formula (÷100, sqrt, ^, inválida)
- [ ] Testing: mvn test EvaluationServiceTest + .http 2xx
- [ ] Handoff: marcar FE checklist ítem 1`,
    },
    feChips: {
      title: "FE · Chips ÷100 √ ^ + panel rojo + bloquear Guardar",
      role: "frontend",
      estimate: 3,
      type: "improvement",
      description: `## Archivos
IndicatorConfigModal.jsx, IndicatorConfigModal.css

## Checklist
- [ ] ⏸ Gate: NO iniciar hasta BE GATE_HTTP / handoff ítem 1 [x]
- [ ] operatorChips: ÷ 100 ( / 100 ), √ (sqrt(), potencia (^)
- [ ] Panel formula-validation rojo si !valida; mensaje claro de sintaxis
- [ ] handleSave: no guardar si !validation.valida o validating; textarea is-invalid
- [ ] Testing: npm run build; smoke modal; matar proceso puerto 8080`,
    },
  },
  // [blocked, blocker] — FE espera a BE
  BLOCKS: [["feChips", "beMotor"]],
});
