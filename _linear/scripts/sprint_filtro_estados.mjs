#!/usr/bin/env node
/** Epic — Filtro proyectos estados ENUM */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Filtro proyectos — estados ENUM correctos",
  SPRINT_NAME: "filtro_estados",
  EPIC_DESC:
    "ProjectListPage/Overview: select de estados alineado al ENUM BD (planificacion|activo|en_revision|completado|cancelado); no mezclar planificación en Activo.",
  PLAN_REL: "plans/plan_sprint_filtro_estados.html",
  SCRIPT_HINT: "sprint_filtro_estados.mjs",
  EPIC_COLOR: "#1B5E42",
  ISSUES: {
    feFiltro: {
      title: "FE · Select + matcher estados ENUM",
      role: "frontend",
      estimate: 2,
      type: "bug",
      description: `## Archivos
formatters.js, ProjectListPage.jsx, OverviewPage.jsx

## Checklist
- [ ] matchesProjectStatusFilter: valores = planificacion|activo|en_revision|completado|cancelado (no mezclar planificacion en activo)
- [ ] ProjectListPage select: opciones con value ENUM + labels getEstadoLabel
- [ ] OverviewPage select: mismas opciones/ENUM
- [ ] npm run build; smoke filtro en /projects`,
    },
  },
  BLOCKS: [],
});
