#!/usr/bin/env node
/** Epic — LOGOUT fiable en bitácora */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "LOGOUT fiable en bitácora",
  SPRINT_NAME: "logout_audit",
  EPIC_DESC:
    "Al logout, registrar siempre LOGOUT en auditoria_login desde el JWT (no depender solo de sp_logout).",
  PLAN_REL: "plans/plan_sprint_logout_audit.html",
  SCRIPT_HINT: "sprint_logout_audit.mjs",
  EPIC_COLOR: "#B06A2C",
  ISSUES: {
    beLogout: {
      title: "BE · Logout: auditar LOGOUT desde JWT",
      role: "backend",
      estimate: 2,
      type: "bug",
      description: `## Archivos
LoginService.java, bitacora_audit.http

## Checklist
- [ ] logout: parse JWT → registerLoginAudit(LOGOUT) siempre
- [ ] Revocar sesión; no duplicar vía sp_logout
- [ ] .http / smoke GATE_HTTP`,
    },
  },
  BLOCKS: [],
});
