#!/usr/bin/env node
/** Epic — Admin seed bcrypt */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Admin seed bcrypt",
  SPRINT_NAME: "admin_seed",
  EPIC_DESC:
    "admin@utn.ac.cr / Admin1234! con bcrypt real en login_system.sql (parte del sistema, no depende de mocks).",
  PLAN_REL: "plans/plan_sprint_admin_seed.html",
  SCRIPT_HINT: "sprint_admin_seed.mjs",
  EPIC_COLOR: "#0F3D2E",
  ISSUES: {
    dbHash: {
      title: "DB · bcrypt Admin1234! en seed",
      role: "database",
      estimate: 1,
      type: "bug",
      description: `## Archivos
1. login_system.sql, 21. ods_mocks.sql

## Checklist
- [ ] login_system.sql: password_hash bcrypt real de Admin1234! (no MOCK_HASH)
- [ ] Testing: drop_db → setup_db → login admin@utn.ac.cr / Admin1234! sin load_mocks`,
    },
  },
  BLOCKS: [],
});
