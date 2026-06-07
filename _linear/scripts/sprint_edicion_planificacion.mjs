#!/usr/bin/env node
/**
 * Sprint Edición Planificación — CRUD planificación (gestor + admin/evaluador)
 * Equipo: linear_ods · 2 Epics: Backend ‖ Frontend (FE espera GATE_HTTP del BE)
 *
 * Epics:
 *   - Edición Planificación · Backend  (1 issue — master PUT /full, todos los ODS vía orquestador)
 *   - Edición Planificación · Frontend (3 issues serial)
 *
 * Comandos: create | status | list | next | show | checklist | state | comment | cleanup | help
 */
import {
  linear,
  getTeam,
  findIssueByIdentifier,
  getWorkflowStates,
  updateIssueChecklist,
  requireChecklistComplete,
  setIssueState,
  addIssueComment,
  printChecklistStatus,
  checklistSummary,
} from "./linear-lib.mjs";

const EPIC_BE = "Edición Planificación · Backend";
const EPIC_FE = "Edición Planificación · Frontend";
const EPIC_NAMES = [EPIC_BE, EPIC_FE];
const SPRINT_NAME = "Sprint Edición Planificación";

const EPIC_BE_DESC =
  "PUT /api/projects/{id}/full + guard planificación. Orquestador master cubre ODS 01–17 (sin tocar 17 controllers). Sin cambio de schema.";
const EPIC_FE_DESC =
  "UI edición planificación: permisos, editor wizard, integración ProjectResultsPage. Espera GATE_HTTP del epic Backend.";

async function fetchAllIssues(filter) {
  const all = [];
  let cursor = undefined;
  do {
    const r = await linear.issues({ filter, first: 50, after: cursor });
    all.push(...r.nodes);
    cursor = r.pageInfo.hasNextPage ? r.pageInfo.endCursor : undefined;
  } while (cursor);
  return all;
}

async function getEpicByName(name) {
  const r = await linear.projects({ filter: { name: { eq: name } } });
  return r.nodes[0] || null;
}

/** Issues de todos los epics de este sprint (para next/status/list/cleanup). */
async function getSprintIssues() {
  const issues = [];
  for (const epicName of EPIC_NAMES) {
    const epic = await getEpicByName(epicName);
    if (!epic) continue;
    const epicIssues = await fetchAllIssues({ project: { id: { eq: epic.id } } });
    issues.push(...epicIssues);
  }
  return issues.sort((a, b) => a.number - b.number);
}

async function getOrCreateState(teamId, name, type, color) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((s) => s.name.toLowerCase() === name.toLowerCase());
  if (f) {
    console.log(`  ♻️  ${name}`);
    return f.id;
  }
  const res = await linear.createWorkflowState({ teamId, name, type, color });
  console.log(`  ✅ ${name}`);
  return (await res.workflowState).id;
}

async function getOrCreateLabel(teamId, name, color) {
  const r = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((l) => l.name.toLowerCase() === name.toLowerCase());
  if (f) {
    console.log(`  ♻️  ${name}`);
    return f.id;
  }
  const res = await linear.createIssueLabel({ teamId, name, color });
  console.log(`  ✅ ${name}`);
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

async function cmdCreate() {
  console.log("\n🔷  Sprint Edición Planificación (2 epics: BE + FE)");
  console.log("━".repeat(58));

  const team = await getTeam();
  const teamId = team.id;
  console.log(`\n✅ Equipo: ${team.name} (${team.key})`);

  console.log("\n🔄 Estados...");
  const ST = {
    backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8"),
    inprog: await getOrCreateState(teamId, "In Progress", "started", "#F59E0B"),
    testing: await getOrCreateState(teamId, "Testing", "started", "#3B82F6"),
    done: await getOrCreateState(teamId, "Done", "completed", "#22C55E"),
  };

  console.log("\n🏷️  Labels...");
  const L = {
    be: await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
    fe: await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
    feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
  };

  const epicBeId = await getOrCreateEpic(teamId, EPIC_BE, EPIC_BE_DESC, "#22C55E");
  const epicFeId = await getOrCreateEpic(teamId, EPIC_FE, EPIC_FE_DESC, "#0EA5E9");

  const NOW = new Date();
  const WEEK = 7 * 24 * 60 * 60 * 1000;
  const sprintId = await getOrCreateSprint(
    teamId,
    SPRINT_NAME,
    NOW,
    new Date(NOW.getTime() + 2 * WEEK)
  );

  const baseBe = { teamId, projectId: epicBeId, cycleId: sprintId, stateId: ST.backlog };
  const baseFe = { teamId, projectId: epicFeId, cycleId: sprintId, stateId: ST.backlog };

  console.log("\n📝 Issues — Epic Backend...");

  const issueBe = await createIssue({
    ...baseBe,
    title: "PUT /full + PlanificacionEdicionService + GET editable + edicion_planificacion.http",
    description: `## Alcance
Orquestador **master** — \`updateFullProject\` enruta a **todos los ODS (01–17)** vía \`findServiceForOds\` (mismo patrón que \`createFullProject\`). Sin tocar los 17 controllers ODS en v1.

## Archivos
- \`service/interfaces/IPlanificacionEdicionService.java\`
- \`service/PlanificacionEdicionService.java\`
- \`service/MasterProjectService.java\` · \`service/interfaces/IMasterProjectService.java\`
- \`controller/MasterProjectController.java\` · \`controller/interfaces/IMasterProjectController.java\`
- \`http/edicion_planificacion.http\`

## Checklist
- [ ] IPlanificacionEdicionService + PlanificacionEdicionService
- [ ] assertCanEditPlanificacion — gestor dueño | admin | evaluador; solo estado planificacion
- [ ] buildEditableSnapshot(proyectoId) — GET agregado para editor (1 round-trip)
- [ ] updateFullProject — cabecera + sync ODS + upsert indicadores/parametros (todos los ODS)
- [ ] IMasterProjectService + MasterProjectService — delegar; reutilizar helpers createFullProject
- [ ] PUT /api/projects/{id}/full + GET /api/projects/{id}/planificacion/editable
- [ ] edicion_planificacion.http — 2xx planificacion; 403 activo; 403 consultor; upsert formula/meta (proyecto id=7)
- [ ] Testing: mvn compile + spring-boot:run + .http 2xx
- [ ] **Handoff cross-epic:** marcar checklist ítem 1 del issue Frontend «FE foundation — permisos + service + hook»`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 8,
  });

  console.log("\n📝 Issues — Epic Frontend...");

  const issueFe1 = await createIssue({
    ...baseFe,
    title: "FE foundation — permisos + projectService + usePlanificacionEditor + utils",
    description: `## Archivos
- \`src/hooks/usePermissions.js\`
- \`src/services/projectService.js\`
- \`src/hooks/usePlanificacionEditor.js\`
- \`src/utils/planificacionEditorUtils.js\`

## Checklist
- [ ] ⏸ **Gate GATE_HTTP:** NO iniciar hasta Backend «PUT /full» marque este ítem [x] (edicion_planificacion.http OK)
- [ ] canEditInPlanificacion(project) — gestor dueño + admin + evaluador en planificacion
- [ ] projectService.updateFullProject + getPlanificacionEditable
- [ ] planificacionEditorUtils.js — map backend ↔ formData (shape ProjectCreationPage)
- [ ] usePlanificacionEditor.js — load, validate, save, 403 handling
- [ ] **Handoff:** marcar checklist ítem 1 del issue «FE editor — PlanificacionEditorPage + wizard»`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 3,
  });

  const issueFe2 = await createIssue({
    ...baseFe,
    title: "FE editor — PlanificacionEditorPage + ProjectPlanificacionWizard",
    description: `## Archivos
- \`src/pages/PlanificacionEditorPage/PlanificacionEditorPage.jsx\`
- \`src/components/projects/ProjectPlanificacionWizard.jsx\`
- \`src/pages/ProjectCreationPage/ProjectCreationPage.jsx\` (refactor mínimo)

## Checklist
- [ ] ⏸ **Gate:** NO iniciar hasta «FE foundation» marque este ítem [x]
- [ ] Extraer wizard compartido create + edit modes
- [ ] PlanificacionEditorPage — usePlanificacionEditor + IndicatorConfigModal
- [ ] ProjectCreationPage delega wizard (flujo crear intacto)
- [ ] Guard: !canEditInPlanificacion → /forbidden
- [ ] Testing: npm run dev — /projects/7/planificacion/edit; npm run build exit 0
- [ ] **Handoff:** marcar checklist ítem 1 del issue «FE integración — rutas + ProjectResultsPage»`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 5,
  });

  const issueFe3 = await createIssue({
    ...baseFe,
    title: "FE integración — rutas + ProjectResultsPage + banner revisor",
    description: `## Archivos
- \`src/App.jsx\`
- \`src/pages/ProjectResultsPage/ProjectResultsPage.jsx\`

## Checklist
- [ ] ⏸ **Gate:** NO iniciar hasta «FE editor» marque este ítem [x]
- [ ] Ruta /projects/:projectId/planificacion/edit (ProtectedRoute)
- [ ] Botón «Editar planificación» en ProjectResultsPage
- [ ] Banner revisor: verificar cambios antes de aprobar (solicitud pendiente)
- [ ] Coherencia con ProjectChatPanel y PlanificacionTransicionBar (sin reimplementar)
- [ ] Testing Modo A (evaluador edita) + Modo B (chat → gestor → aprueba); build OK`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 3,
  });

  console.log("\n🔗 Dependencias (blocks)...");
  await addBlocksRelation(issueFe1, issueBe);
  await addBlocksRelation(issueFe2, issueFe1);
  await addBlocksRelation(issueFe3, issueFe2);

  console.log("\n✅ Backlog completo (4 issues, 2 epics):");
  console.log(`   Backend: ${issueBe.identifier}`);
  console.log(`   Frontend: ${issueFe1.identifier} → ${issueFe2.identifier} → ${issueFe3.identifier}`);
  console.log("\n   Siguiente: node scripts/sprint_edicion_planificacion.mjs next\n");
}

async function cmdStatus() {
  const team = await getTeam();
  const issues = await getSprintIssues();
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. Ejecute: create\n");
    return;
  }
  const states = await getWorkflowStates(team.id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));
  const byState = {};
  let pointsTotal = 0;
  let pointsDone = 0;
  for (const i of issues) {
    const st = stateMap[i.stateId]?.name || "?";
    byState[st] = (byState[st] || 0) + 1;
    const est = i.estimate || 0;
    pointsTotal += est;
    if (stateMap[i.stateId]?.type === "completed") pointsDone += est;
  }
  console.log(`\n📊 ${SPRINT_NAME} · ${issues.length} issues · ${pointsDone}/${pointsTotal} pts\n`);
  for (const epicName of EPIC_NAMES) {
    const epic = await getEpicByName(epicName);
    const epicIssues = issues.filter((i) => i.projectId === epic?.id);
    if (epicIssues.length) console.log(`   📁 ${epicName}: ${epicIssues.length} issues`);
  }
  console.log("");
  for (const [st, count] of Object.entries(byState).sort()) console.log(`   ${st.padEnd(14)} ${count}`);
  console.log("");
}

async function cmdList() {
  const issues = await getSprintIssues();
  if (!issues.length) {
    console.log("\n⚠️  Sin issues.\n");
    return;
  }
  const states = await getWorkflowStates((await getTeam()).id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));
  console.log(`\n📋 ${SPRINT_NAME} (epics: ${EPIC_NAMES.join(" · ")})\n`);
  for (const i of issues) {
    const st = stateMap[i.stateId]?.name || "?";
    const epic = EPIC_NAMES.find((n) => i.project?.name === n) || "";
    console.log(`  ${i.identifier}  [${st.padEnd(12)}]  ${i.title.slice(0, 52)}`);
  }
  console.log("");
}

async function cmdNext() {
  const team = await getTeam();
  const issues = await getSprintIssues();
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. Ejecute: create\n");
    return;
  }
  const states = await getWorkflowStates(team.id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));
  const doneType = new Set(["completed", "canceled"]);
  const doneIds = new Set(
    issues.filter((i) => doneType.has(stateMap[i.stateId]?.type)).map((i) => i.id)
  );
  const relations = [];
  for (const i of issues) {
    const rels = await i.relations();
    relations.push(...rels.nodes);
  }
  const blockedBy = new Map();
  for (const rel of relations) {
    if (rel.type === "blocks") {
      const blocked = rel.relatedIssue?.id || rel.relatedIssueId;
      const blocker = rel.issue?.id || rel.issueId;
      if (blocked && blocker) {
        if (!blockedBy.has(blocked)) blockedBy.set(blocked, []);
        blockedBy.get(blocked).push(blocker);
      }
    }
  }
  const available = issues
    .filter((i) => !doneType.has(stateMap[i.stateId]?.type))
    .filter((i) => {
      const blockers = blockedBy.get(i.id) || [];
      return blockers.every((bId) => doneIds.has(bId));
    })
    .sort((a, b) => a.number - b.number);

  if (!available.length) {
    console.log("\n✅ Sprint completo o sin issues desbloqueados.\n");
    return;
  }

  const i = available[0];
  const st = stateMap[i.stateId]?.name || "?";
  const labels = await i.labels();
  const roles = labels.nodes.filter((l) => l.name.startsWith("role:")).map((l) => l.name);
  const project = await i.project;
  const epicLabel = project?.name || "?";
  console.log(`\n⏭️  Próximo: ${i.identifier}  [${st}]  ${roles.join(", ")}`);
  console.log(`    Epic: ${epicLabel}`);
  console.log(`    ${i.title}\n`);
}

async function cmdShow(identifier) {
  if (!identifier) process.exit(1);
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  console.log(`\n📄 ${issue.identifier}: ${issue.title}\n\n${issue.description || ""}\n`);
}

async function cmdChecklist(identifier, spec) {
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);
  await updateIssueChecklist(issue, spec);
  issue = await findIssueByIdentifier(team, identifier);
  printChecklistStatus(issue.identifier, issue.description);
}

async function cmdState(identifier, stateName, flags = []) {
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);
  if (flags.includes("--check-all")) {
    console.error("\n❌ --check-all eliminado. Marque checklist ítem por ítem.\n");
    process.exit(1);
  }
  if (stateName.toLowerCase() === "done") {
    const check = await requireChecklistComplete(issue);
    if (!check.ok) {
      console.error(`\n❌ ${identifier}: checklist ${check.done}/${check.total}\n`);
      if (check.unchecked) check.unchecked.forEach((l) => console.error(l));
      process.exit(1);
    }
  }
  const state = await setIssueState(issue, stateName);
  console.log(`\n✅ ${identifier} → ${state.name}\n`);
}

/** Marca ítem N del checklist de OTRO issue (handoff cross-epic). */
async function cmdHandoff(targetIdentifier, itemNum) {
  const team = await getTeam();
  const target = await findIssueByIdentifier(team, targetIdentifier);
  await updateIssueChecklist(target, String(itemNum));
  const refreshed = await findIssueByIdentifier(team, targetIdentifier);
  printChecklistStatus(refreshed.identifier, refreshed.description);
  console.log(`🔗 Handoff: ${targetIdentifier} ítem ${itemNum} marcado\n`);
}

async function cmdComment(identifier, body) {
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  await addIssueComment(issue, body);
  console.log(`\n💬 ${identifier}: comentario publicado\n`);
}

async function cmdCleanup() {
  const issues = await getSprintIssues();
  if (!issues.length) {
    console.log(`\n✅ Sprints epics vacíos (${EPIC_NAMES.join(", ")}).\n`);
    return;
  }
  for (const i of issues) {
    await linear.deleteIssue(i.id);
    console.log(`   ✓ ${i.identifier}`);
  }
  console.log(`\n✅ ${issues.length} issues eliminados (${EPIC_NAMES.length} epics).\n`);
}

function cmdHelp() {
  console.log(`
sprint_edicion_planificacion.mjs — Edición Planificación · 2 epics · BE → FE serial

Epics: ${EPIC_BE} | ${EPIC_FE}

  create | status | list | next | show ODS-N
  checklist ODS-N 3        ← un ítem; debe ser el siguiente pendiente
  handoff ODS-N 1          ← marcar ítem 1 de otro issue (cross-epic)
  state ODS-N "In Progress" | state ODS-N Testing | state ODS-N Done
  comment ODS-N "…" | cleanup | help
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
    const positional = rest.filter((a) => !a.startsWith("--"));
    return cmdState(positional[0], positional.slice(1).join(" "), flags);
  },
  comment: () => cmdComment(rest[0], rest.slice(1).join(" ")),
  cleanup: cmdCleanup,
  help: cmdHelp,
};

if (!cmd || cmd === "help") {
  cmdHelp();
  process.exit(0);
}
const handler = handlers[cmd];
if (!handler) {
  cmdHelp();
  process.exit(1);
}
handler().catch((e) => {
  console.error("\n❌", e.message);
  if (e.message?.includes("LINEAR_API_KEY") || e.message?.includes("API")) {
    console.error("\n⚠️  Detenido: configurar _linear/.env con LINEAR_API_KEY válida antes de continuar.\n");
  }
  process.exit(1);
});
