#!/usr/bin/env node
/** Epic — Admin email UTN */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Admin email UTN",
  SPRINT_NAME: "admin_email",
  EPIC_DESC: "Cambiar admin@ods.local → admin@utn.ac.cr (Admin1234!).",
  PLAN_REL: "plans/plan_sprint_admin_email.html",
  SCRIPT_HINT: "sprint_admin_email.mjs",
  EPIC_COLOR: "#0F3D2E",
  ISSUES: {
    dbAdmin: {
      title: "DB · admin@utn.ac.cr en seed",
      role: "database",
      estimate: 1,
      type: "improvement",
      description: `## Archivos
1. login_system.sql, 21. ods_mocks.sql

## Checklist
- [ ] login_system.sql + mocks: admin@utn.ac.cr (pass Admin1234!)
- [ ] Testing: drop_db → setup_db → load_mocks
- [ ] Handoff: marcar DOC-1 ítem 1`,
    },
    docsAdmin: {
      title: "Docs/.http · admin@utn.ac.cr",
      role: "backend",
      estimate: 1,
      type: "improvement",
      description: `## Archivos
comandosDelProyecto.txt, README, *.http

## Checklist
- [ ] ⏸ GATE_BD: NO iniciar hasta load_mocks OK
- [ ] comandosDelProyecto + README + .http sin admin@ods.local
- [ ] Testing: login 200 admin@utn.ac.cr`,
    },
  },
  BLOCKS: [["dbAdmin", "docsAdmin"]],
});
