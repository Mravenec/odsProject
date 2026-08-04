#!/usr/bin/env node
/** Epic — Bitácora login solo admin JWT */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Bitácora login solo admin JWT",
  SPRINT_NAME: "bitacora_auth",
  EPIC_DESC:
    "Cerrar fuga: audit-recent / failed-attempts / login-history (y active-users) exigen Bearer + rol admin.",
  PLAN_REL: "plans/plan_sprint_bitacora_auth.html",
  SCRIPT_HINT: "sprint_bitacora_auth.mjs",
  EPIC_COLOR: "#B06A2C",
  ISSUES: {
    beAuth: {
      title: "BE · Exigir JWT admin en audit/login-history/failed-attempts",
      role: "backend",
      estimate: 3,
      type: "bug",
      description: `## Archivos
LoginController.java, IRoleAuthorizationService / RoleAuthorizationService, bitacora_audit.http

## Checklist
- [ ] Helper JWT: requireAdmin (401 sin token, 403 si no admin)
- [ ] Proteger: audit-recent, failed-attempts, users/{id}/login-history (+ active-users si mismo patrón)
- [ ] .http + Testing GATE_HTTP (sin token / gestor / admin)
- [ ] Smoke: BitacoraAdminPage con admin sigue cargando`,
    },
  },
  BLOCKS: [],
});
