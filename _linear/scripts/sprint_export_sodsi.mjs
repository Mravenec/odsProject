#!/usr/bin/env node
/**
 * Sprint Export SODSI — Glosario PDF completo · Multi-agente
 * Equipo: linear_ods
 *
 * Cadena: DB×3 → BE×4 → GATE_HTTP → (FE wizard ∥ FE export) → ORCH
 *
 * Comandos: create | status | list | next | show ODS-N
 *   checklist ODS-N <n>   (un ítem, secuencial)
 *   handoff ODS-N <n>
 *   state ODS-N Testing|Done|"In Progress"
 *   cleanup | help
 */
import { spawnSync } from "child_process";
import { readFileSync } from "fs";
import { dirname, join } from "path";
import { fileURLToPath } from "url";
import {
  linear,
  getTeam,
  findIssueByIdentifier,
  updateIssueChecklist,
  requireChecklistComplete,
  setIssueState,
  addIssueComment,
  printChecklistStatus,
  checklistSummary,
} from "./linear-lib.mjs";

const __dir = dirname(fileURLToPath(import.meta.url));
const _linearRoot = join(__dir, "..");
const PLAN_FILE = join(_linearRoot, "plans", "plan_sprint_export_sodsi.html");

const EPIC_NAME = "Export SODSI — Glosario completo";
const SPRINT_NAME = "Sprint SODSI — BD + captura + Excel";
const EPIC_DESC =
  "Implementar variables del glosario SODSI (Conare/UTN): catálogos BD, ficha gestor, export multi-hoja. Multi-agente: database → backend → GATE_HTTP → frontend (paralelo) → cierre.";

function validatePlanHtml() {
  console.log("\n📋 Validando plan HTML...");
  const r = spawnSync(process.execPath, [join(__dir, "validate-plan-html.mjs"), PLAN_FILE], {
    cwd: _linearRoot,
    stdio: "inherit",
  });
  if (r.status !== 0) process.exit(r.status ?? 1);
}

async function fetchAllIssues(filter) {
  const all = [];
  let cursor = undefined;
  do {
    const res = await linear.issues({ filter, first: 50, after: cursor });
    all.push(...res.nodes);
    cursor = res.pageInfo.hasNextPage ? res.pageInfo.endCursor : undefined;
  } while (cursor);
  return all;
}

async function getEpicIssues(team) {
  const projects = await linear.projects({ filter: { name: { eq: EPIC_NAME } } });
  const epic = projects.nodes[0];
  if (!epic) return { epic: null, issues: [] };
  const issues = await fetchAllIssues({ project: { id: { eq: epic.id } } });
  return { epic, issues };
}

async function getOrCreateState(teamId, name, type, color) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((s) => s.name.toLowerCase() === name.toLowerCase());
  if (f) return f.id;
  const res = await linear.createWorkflowState({ teamId, name, type, color });
  return (await res.workflowState).id;
}

async function getOrCreateLabel(teamId, name, color) {
  const r = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((l) => l.name.toLowerCase() === name.toLowerCase());
  if (f) return f.id;
  const res = await linear.createIssueLabel({ teamId, name, color });
  return (await res.issueLabel).id;
}

async function getOrCreateEpic(teamId, name, desc, color) {
  const r = await linear.projects({ filter: { name: { eq: name } } });
  if (r.nodes.length) {
    console.log(`  ♻️  Epic: ${name}`);
    return r.nodes[0].id;
  }
  const res = await linear.createProject({ teamIds: [teamId], name, description: desc, color });
  console.log(`  ✅ Epic: ${name}`);
  return (await res.project).id;
}

async function getOrCreateSprint(teamId, name, startsAt, endsAt) {
  const r = await linear.cycles({ filter: { team: { id: { eq: teamId } }, name: { eq: name } } });
  if (r.nodes.length) {
    console.log(`  ♻️  Sprint: ${name}`);
    return r.nodes[0].id;
  }
  const res = await linear.createCycle({ teamId, name, startsAt, endsAt });
  console.log(`  ✅ Sprint: ${name}`);
  return (await res.cycle).id;
}

async function createIssue(params) {
  const res = await linear.createIssue(params);
  const i = await res.issue;
  console.log(`    📌 ${i.identifier}: ${i.title}`);
  return i;
}

async function addBlocksRelation(blockedIssue, blockerIssue) {
  await linear.createIssueRelation({
    issueId: blockerIssue.id,
    relatedIssueId: blockedIssue.id,
    type: "blocks",
  });
  console.log(`     🔗 ${blockedIssue.identifier} ← ${blockerIssue.identifier}`);
}

const DONE_STATE_TYPES = new Set(["completed", "canceled"]);

async function resolveIssueStates(issues) {
  return Promise.all(issues.map(async (issue) => ({ issue, state: await issue.state })));
}

async function collectBlockRelations(issues) {
  const relations = [];
  for (const i of issues) {
    const rels = await i.relations();
    for (const rel of rels.nodes) {
      if (rel.type !== "blocks") continue;
      const blocker = await rel.issue;
      const blocked = await rel.relatedIssue;
      if (blocker?.id && blocked?.id) {
        relations.push({ blockerId: blocker.id, blockedId: blocked.id });
      }
    }
  }
  return relations;
}

async function getUnblockedIssues(issues) {
  const enriched = await resolveIssueStates(issues);
  const doneIds = new Set(
    enriched.filter(({ state }) => DONE_STATE_TYPES.has(state?.type)).map(({ issue }) => issue.id)
  );
  const blockedBy = new Map();
  for (const { blockerId, blockedId } of await collectBlockRelations(issues)) {
    if (!blockedBy.has(blockedId)) blockedBy.set(blockedId, []);
    blockedBy.get(blockedId).push(blockerId);
  }
  return enriched
    .filter(({ state }) => !DONE_STATE_TYPES.has(state?.type))
    .filter(({ issue }) => {
      const blockers = blockedBy.get(issue.id) || [];
      return blockers.every((bId) => doneIds.has(bId));
    })
    .map(({ issue }) => issue)
    .sort((a, b) => a.number - b.number);
}

const ISSUES = {
  db1: {
    title: "DB · Catálogos SODSI (glosario PDF)",
    role: "database",
    estimate: 5,
    description: `## Objetivo
Crear catálogos alineados al glosario SODSI (UTN).

## Archivos
- \`0.database/propuesta_actual/22. sodsi_catalogos.sql\` (nuevo)
- Actualizar \`0.database/00_run_all.sql\`

## Checklist
- [ ] sodsi_unidades_programaticas (seed UTN — mín. sedes + placeholder unidades)
- [ ] sodsi_regiones_mideplan (6 regiones + N/A)
- [ ] sodsi_ejes_planes (Docencia, Investigación, Extensión, Vida Estudiantil, Gestión)
- [ ] sodsi_aliado_tipo (Nacional/Internacional × Academia, Empresa, Gobierno, Sociedad)
- [ ] sodsi_beneficiario_categoria + sodsi_beneficiario_valor (árbol PDF)
- [ ] GATE_SQL: merge en propuesta_actual (CREATE TABLE en SQL definitivo — **sin ALTER** ni scripts sueltos)
- [ ] **Handoff:** marcar checklist ítem 1 del issue DB schema (downstream)`,
  },
  db2: {
    title: "DB · Schema proyecto SODSI + relaciones",
    role: "database",
    estimate: 5,
    description: `## Objetivo
Columnas y tablas N:M en ods_master.proyectos — **editar** \`2. ods_master_database.sql\` (CREATE TABLE completo). Prohibido ALTER incremental; ver _linear/README.md GATE_SQL.

## Archivos
- \`0.database/propuesta_actual/2. ods_master_database.sql\` (definición CREATE, no migración)
- \`0.database/propuesta_actual/21. ods_mocks.sql\` (proyecto #6/#7 con ficha SODSI QA)

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] marcado por DB catálogos upstream
- [ ] Editar CREATE TABLE proyectos: contacto_telefono, contacto_correo, dependencia_id, region_mideplan_id, eje_planes_id, perspectiva_genero
- [ ] proyecto_aliados (nombre, ambito, tipo_id)
- [ ] proyecto_beneficiarios (valor_id)
- [ ] proyecto_dependencias_participantes (unidad_id, rol coordinadora|participante)
- [ ] Vista resumen ampliada o documentar merge en export
- [ ] **Handoff:** marcar checklist ítem 1 del issue GATE_BD pipeline`,
  },
  db3: {
    title: "DB · GATE_BD — drop_db + setup + mocks + JOOQ",
    role: "database",
    estimate: 3,
    description: `## Objetivo
Pipeline BD único — un solo agente.

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] por DB schema upstream
- [ ] python 0.database/drop_db.py
- [ ] python 0.database/setup_db.py
- [ ] python 0.database/load_mocks.py
- [ ] cd 1.backend/odsProject && mvn -q compile (JOOQ POJOs OK)
- [ ] outputArtifacts: schemaChanged, jooqRegenerated
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE repos`,
  },
  be1: {
    title: "BE · Repos catálogos SODSI + proyecto extendido",
    role: "backend",
    estimate: 4,
    description: `## Archivos
IREPO→REPO: ISodsiCatalogRepository, SodsiCatalogRepository; IMasterProjectRepository extendido

## Checklist
- [ ] ⏸ Gate GATE_BD: ítem 1 [x]
- [ ] ISodsiCatalogRepository + SodsiCatalogRepository (lectura catálogos)
- [ ] IMasterProjectRepository: findByIds, saveSodsiRelations
- [ ] MasterProjectRepository: JOOQ implementación
- [ ] mvn -q compile
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE service proyecto`,
  },
  be2: {
    title: "BE · Service + API proyecto con ficha SODSI",
    role: "backend",
    estimate: 5,
    description: `## Archivos
I*Service, *Service, I*Controller, *Controller — projects/full PUT planificación

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] por BE repos
- [ ] DTOs SODSI (contacto, dependencia, aliados[], beneficiarios[], participantes[])
- [ ] MasterProjectService: persistir ficha SODSI en planificación
- [ ] GET proyecto devuelve ficha SODSI
- [ ] ISodsiCatalogController GET /api/sodsi/catalogos/*
- [ ] mvn -q compile
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE export`,
  },
  be3: {
    title: "BE · ExportService Excel multi-hoja SODSI",
    role: "backend",
    estimate: 5,
    description: `## Archivos
ExportService.java — 5 hojas: Ficha, Indicadores, Aliados, Beneficiarios, Dependencias

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] por BE service proyecto
- [ ] Consolidado sede+año: hoja Ficha SODSI (todas variables escalares + Institución UTN)
- [ ] Hojas Aliados, Beneficiarios, Dependencias (aplanado)
- [ ] Export individual: General ampliado
- [ ] Sin breaking change GET /api/export/projects/excel
- [ ] mvn -q compile
- [ ] **Handoff:** marcar checklist ítem 1 del issue GATE_HTTP`,
  },
  be4: {
    title: "BE · GATE_HTTP — export_sodsi.http + catálogos",
    role: "backend",
    estimate: 3,
    description: `## Archivos
export_sodsi.http, consultor_flow.http, sodsi_catalogos.http

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] por BE export
- [ ] GET catalogos → 200
- [ ] GET consolidado sedeId=2 anio=2024 → 200 xlsx 5 hojas
- [ ] GET export proyecto #6 → 200
- [ ] consultor 200; gestor 403 consolidado
- [ ] **Handoff:** marcar ítem 1 en issue FE wizard Y en issue FE export (paralelo)`,
  },
  fe1: {
    title: "FE · Wizard ficha SODSI (gestor planificación)",
    role: "frontend",
    estimate: 6,
    description: `## Archivos
SodsiFichaStep, useSodsiCatalogs, projectService, ProjectPlanificacionWizard, usePlanificacionEditor

## Checklist
- [ ] ⏸ Gate GATE_HTTP: ítem 1 [x]
- [ ] sodsiCatalogService.js + hook catálogos
- [ ] Paso wizard: dependencia, contacto tel/email, Mideplan, PLANES, género
- [ ] Aliados dinámicos (lista)
- [ ] Beneficiarios multi-select por categoría
- [ ] Dependencias participantes (coordinadora + participantes)
- [ ] PUT /full persiste ficha; proyecto #7 QA
- [ ] npm run build exit 0`,
  },
  fe2: {
    title: "FE · Panel export SODSI + filename",
    role: "frontend",
    estimate: 2,
    description: `## Archivos
BulkProjectExportPanel.jsx, exportService.js

## Checklist
- [ ] ⏸ Gate GATE_HTTP: ítem 1 [x] (paralelo con FE wizard — otro agente)
- [ ] Copy «Reporte institucional SODSI»
- [ ] Filename ods_sodsi_{sede}_{anio}.xlsx
- [ ] npm run build exit 0
- [ ] Smoke consultor descarga dashboard`,
  },
  orch: {
    title: "Orquestador · Resumen HTML + epic Completed + cleanup",
    role: "orchestrator",
    estimate: 1,
    description: `## Checklist
- [ ] ⏸ Gate: FE wizard Y FE export en Done
- [ ] status — todos Done checklist 100%
- [ ] resumen_sprint_export_sodsi.html
- [ ] Epic Completed + comentario ruta resumen
- [ ] cleanup (opcional tras commit)`,
  },
};

async function cmdCreate() {
  validatePlanHtml();

  console.log("\n🔷  Sprint SODSI — Glosario completo · Multi-agente");
  console.log("━".repeat(58));

  const team = await getTeam();
  const teamId = team.id;

  const ST = { backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8") };
  const L = {
    db: await getOrCreateLabel(teamId, "role:database", "#EAB308"),
    be: await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
    fe: await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
    orch: await getOrCreateLabel(teamId, "role:orchestrator", "#EF4444"),
    feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
  };

  const epicId = await getOrCreateEpic(teamId, EPIC_NAME, EPIC_DESC, "#22C55E");
  const NOW = new Date();
  const sprintId = await getOrCreateSprint(
    teamId,
    SPRINT_NAME,
    NOW,
    new Date(NOW.getTime() + 21 * 24 * 60 * 60 * 1000)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };
  const roleLabel = { database: L.db, backend: L.be, frontend: L.fe, orchestrator: L.orch };

  console.log("\n📝 Issues (10)...");
  const created = {};
  for (const [key, spec] of Object.entries(ISSUES)) {
    created[key] = await createIssue({
      ...base,
      title: spec.title,
      description: spec.description,
      priority: spec.role === "database" ? 1 : spec.role === "backend" ? 1 : 2,
      labelIds: [roleLabel[spec.role], L.feat],
      estimate: spec.estimate,
    });
  }

  console.log("\n🔗 Blocks...");
  const chain = [
    ["db2", "db1"],
    ["db3", "db2"],
    ["be1", "db3"],
    ["be2", "be1"],
    ["be3", "be2"],
    ["be4", "be3"],
    ["fe1", "be4"],
    ["fe2", "be4"],
    ["orch", "fe1"],
    ["orch", "fe2"],
  ];
  for (const [blocked, blocker] of chain) {
    await addBlocksRelation(created[blocked], created[blocker]);
  }

  const points = Object.values(created).reduce((s, i) => s + (i.estimate || 0), 0);

  console.log("\n" + "━".repeat(58));
  console.log("🎉  Sprint creado\n");
  console.log(`  Issues: 10 · Puntos: ${points}`);
  console.log("\n  Asignación multi-agente:");
  console.log("    database  → " + created.db1.identifier + " … " + created.db3.identifier);
  console.log("    backend   → " + created.be1.identifier + " … " + created.be4.identifier);
  console.log("    frontend  → " + created.fe1.identifier + " ∥ " + created.fe2.identifier + " (paralelo tras HTTP)");
  console.log("    orchestrator → " + created.orch.identifier);
  console.log("\n  Paralelo FE: tras " + created.be4.identifier + " Done + handoff ítem 1 en ambos FE");
  console.log("\n  Siguiente: node scripts/sprint-next.mjs  →  " + created.db1.identifier + "\n");
}

async function cmdStatus() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic) {
    console.log(`\n⚠️  Epic no encontrado. Ejecute: create\n`);
    return;
  }
  const enriched = await resolveIssueStates(issues);
  console.log(`\n📊 ${SPRINT_NAME} — ${issues.length} issues\n`);
  for (const { issue: i, state: stObj } of enriched.sort((a, b) => a.issue.number - b.issue.number)) {
    const c = checklistSummary(i.description);
    console.log(`  ${i.identifier}  [${stObj?.name || "?"}]  chk ${c.done}/${c.total}  ${i.title.slice(0, 50)}`);
  }
  console.log("");
}

async function cmdList() {
  await cmdStatus();
}

async function cmdNext() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. create\n");
    return;
  }
  const available = await getUnblockedIssues(issues);
  if (!available.length) {
    console.log("\n✅ Sin issues desbloqueados.\n");
    return;
  }
  const parallel = available.length > 1;
  console.log(`\n⏭️  ${available.length} issue(s)${parallel ? " — paralelo permitido" : ""}:\n`);
  for (const i of available) {
    const st = await i.state;
    const labels = await i.labels();
    const roles = labels.nodes.filter((l) => l.name.startsWith("role:")).map((l) => l.name);
    const c = checklistSummary(i.description);
    console.log(`  ${i.identifier}  [${st?.name}]  ${roles.join(", ")}  chk ${c.done}/${c.total}`);
    console.log(`    ${i.title}\n`);
  }
}

async function cmdShow(identifier) {
  if (!identifier) {
    console.error("Uso: show ODS-N");
    process.exit(1);
  }
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  printChecklistStatus(issue.identifier, issue.description);
  console.log(issue.description);
}

async function cmdChecklist(identifier, spec) {
  if (!identifier || !spec) {
    console.error("Uso: checklist ODS-N <n>");
    process.exit(1);
  }
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);
  await updateIssueChecklist(issue, spec);
  issue = await findIssueByIdentifier(team, identifier);
  printChecklistStatus(issue.identifier, issue.description);
}

async function cmdHandoff(identifier, spec) {
  console.log(`\n🤝 Handoff → ${identifier} ítem ${spec}\n`);
  await cmdChecklist(identifier, spec);
}

async function cmdState(identifier, stateName, flags = []) {
  if (!identifier || !stateName) {
    console.error('Uso: state ODS-N Testing|Done|"In Progress"');
    process.exit(1);
  }
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);
  if (flags.includes("--check-all")) {
    await updateIssueChecklist(issue, "all");
    issue = await findIssueByIdentifier(team, identifier);
  }
  if (stateName.toLowerCase() === "done") {
    const check = await requireChecklistComplete(issue);
    if (!check.ok) {
      console.error(`\n❌ Checklist incompleto (${check.done}/${check.total})\n`);
      process.exit(1);
    }
  }
  const state = await setIssueState(issue, stateName);
  console.log(`\n✅ ${identifier} → ${state.name}\n`);
}

async function cmdComment(identifier, body) {
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  await addIssueComment(issue, body);
}

async function cmdCleanup() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic || !issues.length) {
    console.log("\n✅ Epic vacío.\n");
    return;
  }
  for (const i of issues) {
    await linear.deleteIssue(i.id);
    console.log(`  ✓ ${i.identifier}`);
  }
  console.log(`\n✅ ${issues.length} issues eliminados.\n`);
}

function cmdHelp() {
  console.log(`
🔷 sprint_export_sodsi.mjs — SODSI glosario completo

Pipeline multi-agente:
  DB1→DB2→DB3 → BE1→BE2→BE3→BE4(.http) → FE1∥FE2 → ORCH

LEER show → HACER un ítem → checklist ODS-N <n> → repetir

Comandos: create | next | show | checklist | handoff | state | cleanup
`);
}

const [cmd, ...rest] = process.argv.slice(2);
const handlers = {
  create: cmdCreate,
  status: cmdStatus,
  list: cmdList,
  next: cmdNext,
  show: () => cmdShow(rest[0]),
  checklist: () => cmdChecklist(rest[0], rest[1]),
  handoff: () => cmdHandoff(rest[0], rest[1]),
  state: () => {
    const flags = rest.filter((a) => a.startsWith("--"));
    const pos = rest.filter((a) => !a.startsWith("--"));
    cmdState(pos[0], pos.slice(1).join(" "), flags);
  },
  comment: () => cmdComment(rest[0], rest.slice(1).join(" ")),
  cleanup: cmdCleanup,
  help: cmdHelp,
};

if (!cmd || cmd === "help") {
  cmdHelp();
  process.exit(0);
}
handlers[cmd]?.().catch((e) => {
  console.error("\n❌", e.message);
  process.exit(1);
}) ?? (console.error("Comando desconocido"), cmdHelp(), process.exit(1));
