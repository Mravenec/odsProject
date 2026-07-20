#!/usr/bin/env node
/** Epic 2 — Export SODSI Meta + Unidad (EPIC 0 admin users ya Done) */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Export SODSI — Meta indicador + Unidad encargada",
  SPRINT_NAME: "Sprint export SODSI meta unidad",
  EPIC_DESC:
    "Excel SODSI: columna Meta = nombre del indicador ODS (no metaNombre/fórmula); Unidad encargada desde perfil SODSI del consultor que descarga. Prerrequisito Admin usuarios SODSI Done.",
  PLAN_REL: "plans/plan_sprint_export_sodsi_meta_unidad.html",
  SCRIPT_HINT: "sprint_export_sodsi_meta_unidad.mjs",
  EPIC_COLOR: "#22C55E",
  ISSUES: {
    beMeta: {
      title: "BE · formatMetasExport = indicadorNombre",
      role: "backend",
      estimate: 3,
      type: "bug",
      description: `## Archivos
ExportService.formatMetasExport

## Checklist
- [ ] Meta = "[codigo] " + indicadorNombre (ignorar metaNombre / formula_custom)
- [ ] Varios indicadores separados por coma
- [ ] Sin columna logrado/no logrado
- [ ] Test o .http assert
- [ ] **Handoff:** marcar beUnidad checklist ítem 1`,
    },
    beUnidad: {
      title: "BE · Unidad encargada desde perfil actor",
      role: "backend",
      estimate: 3,
      type: "feature",
      description: `## Archivos
ExportService.writeSodsiMatrizRow

## Prerrequisito
Admin usuarios SODSI Done (areaId/dependenciaId en usuarios)

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] por beMeta (mismo ExportService)
- [ ] Resolver dependencia/unidad del actor que descarga
- [ ] Vacío si sin perfil (no inventar)
- [ ] GATE_HTTP export consultor 2xx
- [ ] **Handoff:** marcar feSmoke checklist ítem 1`,
    },
    feSmoke: {
      title: "FE · Smoke export consultor Meta+Unidad",
      role: "frontend",
      estimate: 2,
      type: "feature",
      description: `## Archivos
BulkProjectExportPanel (smoke)

## Checklist
- [ ] ⏸ GATE_HTTP: NO iniciar hasta handoff beUnidad ítem 1 [x]
- [ ] Excel: Meta=indicador; Unidad si perfil; usuario=quien descarga
- [ ] npm run build`,
    },
    orch: {
      title: "ORCH · resumen export SODSI + Epic Completed",
      role: "orchestrator",
      estimate: 1,
      type: "feature",
      description: `## Checklist
- [ ] ⏸ feSmoke Done
- [ ] resumen_sprint_export_sodsi_meta_unidad.html
- [ ] Epic Completed`,
    },
  },
  BLOCKS: [
    ["beUnidad", "beMeta"],
    ["feSmoke", "beUnidad"],
    ["orch", "feSmoke"],
  ],
});
