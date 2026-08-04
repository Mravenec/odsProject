#!/usr/bin/env node
/** Epic — setup_db sin mocks */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "setup_db sin mocks",
  SPRINT_NAME: "setup_sin_mocks",
  EPIC_DESC:
    "setup_db.py solo schema; load_mocks.py carga 21/22. Quitar SOURCE mocks de 00_run_all.sql.",
  PLAN_REL: "plans/plan_sprint_setup_sin_mocks.html",
  SCRIPT_HINT: "sprint_setup_sin_mocks.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    dbSplit: {
      title: "DB · Separar setup y load_mocks",
      role: "database",
      estimate: 1,
      type: "improvement",
      description: `## Archivos
00_run_all.sql, comandosDelProyecto.txt, setup_db.py

## Checklist
- [ ] Quitar SOURCE 21. ods_mocks.sql de 00_run_all.sql; documentar que mocks van en load_mocks.py
- [ ] Actualizar comandosDelProyecto.txt (setup vs load_mocks)
- [ ] Testing: drop_db → setup_db (sin jartavia) → load_mocks (con demos)`,
    },
  },
  BLOCKS: [],
});
