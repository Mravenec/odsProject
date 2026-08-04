#!/usr/bin/env node
/** Epic — Aislamiento proyectos entre gestores */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Aislamiento proyectos entre gestores",
  SPRINT_NAME: "aislamiento_gestor",
  EPIC_DESC:
    "Gestor solo ve/abre sus proyectos (JWT). Staff (admin/evaluador/consultor) sigue viendo todos.",
  PLAN_REL: "plans/plan_sprint_aislamiento_gestor.html",
  SCRIPT_HINT: "sprint_aislamiento_gestor.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    beIso: {
      title: "BE · Filtrar listados/detalle por JWT dueño gestor",
      role: "backend",
      estimate: 3,
      type: "bug",
      description: `## Archivos
MasterProjectController.java, MasterProjectService.java, IRoleAuthorizationService (si hace falta), aislamiento_gestor.http

## Checklist
- [ ] GET with-ods / user/{id}/with-ods / {id}/with-ods: gestor solo dueño (JWT); staff ve todos
- [ ] IDOR: gestor pediendo userId ajeno → 403
- [ ] .http + Testing GATE_HTTP
- [ ] Handoff FE ítem 1`,
    },
    feIso: {
      title: "FE · Logout limpia projects + filtro defensivo",
      role: "frontend",
      estimate: 2,
      type: "bug",
      description: `## Archivos
useAuth.jsx, useProjects.jsx, ProjectListPage.jsx, DashboardPage.jsx

## Checklist
- [ ] ⏸ Gate: NO iniciar hasta BE handoff ítem 1 [x]
- [ ] Logout / cambio de usuario: invalidateProjects
- [ ] Dashboard/lista: filtro defensivo userId si rol gestor
- [ ] npm run build`,
    },
  },
  BLOCKS: [["feIso", "beIso"]],
});
