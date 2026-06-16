#!/usr/bin/env node
/**
 * Sprint Admin usuarios — listado SODSI en GET /login/users
 * Equipo: linear_ods
 *
 * Cadena: BE-1 → BE-2(GATE_HTTP) → FE-1 → ORCH
 */
import { spawnSync } from "child_process";
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
const PLAN_FILE = join(_linearRoot, "plans", "plan_sprint_admin_users_sodsi.html");

const EPIC_NAME = "Admin usuarios — listado SODSI";
const SPRINT_NAME = "Sprint admin users SODSI list";
const EPIC_DESC =
  "GET /login/users debe devolver areaId, dependenciaId, rolDependenciaId y telefonoContacto para alinear Admin Usuarios con login y creación de proyecto.";

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
  be1: {
    title: "BE · findAllUsuariosAdmin campos SODSI",
    role: "backend",
    estimate: 2,
    description: `## Archivos
LoginRepository.java

## Checklist
- [ ] findAllUsuariosAdmin: añadir areaId, dependenciaId, rolDependenciaId, telefonoContacto al SELECT
- [ ] mvn -q compile
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE-2`,
  },
  be2: {
    title: "BE · GATE_HTTP — admin_users_sodsi.http",
    role: "backend",
    estimate: 2,
    description: `## Archivos
admin_users_sodsi.http

## Checklist
- [ ] ⏸ Gate: ítem 1 [x] por BE-1 upstream (handoff)
- [ ] admin_users_sodsi.http — GET /login/users como admin; gestor_pobreza con areaId/dependenciaId
- [ ] .http 2xx con backend levantado
- [ ] **Handoff:** marcar checklist ítem 1 del issue FE-1 (GATE_HTTP)`,
  },
  fe1: {
    title: "FE · verificar UsersAdminPage sin badge falso",
    role: "frontend",
    estimate: 1,
    description: `## Archivos
UsersAdminPage.jsx (solo si ajuste necesario)

## Checklist
- [ ] ⏸ GATE_HTTP: NO iniciar hasta handoff BE-2 ítem 1 [x]
- [ ] Verificar gestor_pobreza sin badge «Perfil incompleto» y columnas SODSI pobladas
- [ ] npm run build`,
  },
  orch: {
    title: "Orquestador · resumen + Epic Completed",
    role: "orchestrator",
    estimate: 1,
    description: `## Checklist
- [ ] ⏸ Gate: FE-1 en Done
- [ ] status — todos Done checklist 100%
- [ ] resumen_sprint_admin_users_sodsi.html
- [ ] Epic Completed`,
  },
};

async function cmdCreate() {
  validatePlanHtml();

  console.log("\n🔷  Sprint admin users SODSI list");
  console.log("━".repeat(58));

  const team = await getTeam();
  const teamId = team.id;

  const ST = { backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8") };
  const L = {
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
    new Date(NOW.getTime() + 14 * 24 * 60 * 60 * 1000)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };
  const roleLabel = { backend: L.be, frontend: L.fe, orchestrator: L.orch };

  console.log("\n📝 Issues (4)...");
  const created = {};
  for (const [key, spec] of Object.entries(ISSUES)) {
    created[key] = await createIssue({
      ...base,
      title: spec.title,
      description: spec.description,
      priority: 1,
      labelIds: [roleLabel[spec.role], L.feat],
      estimate: spec.estimate,
    });
  }

  console.log("\n🔗 Blocks...");
  const chain = [
    ["be2", "be1"],
    ["fe1", "be2"],
    ["orch", "fe1"],
  ];
  for (const [blocked, blocker] of chain) {
    await addBlocksRelation(created[blocked], created[blocker]);
  }

  const points = Object.values(created).reduce((s, i) => s + (i.estimate || 0), 0);

  console.log("\n" + "━".repeat(58));
  console.log("🎉  Sprint creado\n");
  console.log(`  Issues: 4 · Puntos: ${points}`);
  console.log(
    `  Cadena: ${created.be1.identifier} → ${created.be2.identifier} → ${created.fe1.identifier} → ${created.orch.identifier}`
  );
  console.log(`\n  Siguiente: node scripts/sprint-next.mjs  →  ${created.be1.identifier}\n`);
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
  console.log(`\n⏭️  ${available.length} issue(s):\n`);
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
🔷 sprint_admin_users_sodsi.mjs — Admin listado SODSI

Pipeline: BE-1 → BE-2(.http) → FE-1 → ORCH

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
    return cmdState(pos[0], pos.slice(1).join(" "), flags);
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
