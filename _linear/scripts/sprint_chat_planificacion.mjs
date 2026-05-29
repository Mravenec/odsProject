#!/usr/bin/env node
/**
 * Sprint Chat Planificación — gestor ⇄ admin/evaluador + transición con aprobación
 * Equipo: linear_ods · Cadena serial ODS-1→5
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

const EPIC_NAME = "Chat Planificación";
const SPRINT_NAME = "Sprint Chat Planificación";
const EPIC_DESC =
  "Chat por proyecto en planificacion + solicitud/aprobación de salida. DB→JOOQ→BE chat→BE transición→FE.";

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
  console.log("\n🔷  Sprint Chat Planificación");
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
    db: await getOrCreateLabel(teamId, "role:database", "#EAB308"),
    be: await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
    fe: await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
    feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
  };

  const epicId = await getOrCreateEpic(teamId, EPIC_NAME, EPIC_DESC, "#0EA5E9");
  const NOW = new Date();
  const WEEK = 7 * 24 * 60 * 60 * 1000;
  const sprintId = await getOrCreateSprint(
    teamId,
    SPRINT_NAME,
    NOW,
    new Date(NOW.getTime() + 2 * WEEK)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };

  console.log("\n📝 Issues...");

  const ods1 = await createIssue({
    ...base,
    title: "Schema chat + solicitud transición + mocks + pipeline BD/JOOQ",
    description: `## Archivos
- \`0.database/propuesta_actual/2. ods_master_database.sql\`
- \`0.database/propuesta_actual/21. ods_mocks.sql\` (proyecto id=7 planificacion; id=6 intacto)

## Checklist
- [ ] Crear tabla proyecto_chat_mensajes en \`2. ods_master_database.sql\`
- [ ] Crear tabla proyecto_transicion_solicitud en \`2. ods_master_database.sql\`
- [ ] Mocks: proyecto id=7 en planificacion + mensajes + solicitud pendiente
- [ ] Borrar script temporal de tarea (si se usó)
- [ ] Pipeline: drop_db → setup_db → load_mocks → mvn spring-boot:run (Testing OK)
- [ ] outputArtifacts: tablas nuevas + jooqRegenerated: true
- [ ] **Handoff:** marcar ODS-2 checklist ítem 1 — señal BD+JOOQ listo`,
    priority: 1,
    labelIds: [L.db, L.feat],
    estimate: 3,
  });

  const ods2 = await createIssue({
    ...base,
    title: "Backend Chat (IREPO→REPO→ISVC→SVC→ICTRL→CTRL) + chat_planificacion.http",
    description: `## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta ODS-1 marque este ítem (BD + JOOQ listo)
- [ ] IChatMensajeRepository + ChatMensajeRepository
- [ ] IChatMensajeService + ChatMensajeService (30 min; solo escritura en planificacion)
- [ ] IChatMensajeController + ChatMensajeController — \`/api/projects/{id}/chat/messages\`
- [ ] \`chat_planificacion.http\` — 2xx listar/enviar/editar/borrar
- [ ] Service/Controller inyectan interfaces
- [ ] **Handoff:** marcar ODS-3 checklist ítem 1 — endpoints chat verificados`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 5,
  });

  const ods3 = await createIssue({
    ...base,
    title: "Backend Transición + cerrar bypass PATCH/PUT + transicion_planificacion.http",
    description: `## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta ODS-2 marque este ítem (chat .http OK)
- [ ] ITransicionPlanificacionRepository + TransicionPlanificacionRepository
- [ ] ITransicionPlanificacionService + impl (1 pendiente/proyecto)
- [ ] \`transitionState\`: bloquear planificacion→activo|cancelado salvo aprobar()
- [ ] Bloquear planificacion→en_revision en PATCH genérico
- [ ] \`ROLES_BY_TARGET\`: evaluador puede activo→cancelado (fuerza mayor)
- [ ] \`updateProyecto\`: actorUserId/actorRole; ignorar estado en PUT; 403 admin/eval fuera planificacion
- [ ] ITransicionPlanificacionController + rutas \`/planificacion/*\`
- [ ] \`transicion_planificacion.http\` — solicitud/aprobar/rechazar/fuerza mayor
- [ ] **Handoff:** marcar ODS-4 checklist ítem 1 — GATE_HTTP completo`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 5,
  });

  const ods4 = await createIssue({
    ...base,
    title: "UI Chat — chatService + useProjectChat + ProjectChatPanel",
    description: `## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta ODS-3 marque este ítem (GATE_HTTP)
- [ ] \`chatService.js\` alineado con API chat
- [ ] \`useProjectChat.js\`
- [ ] \`ProjectChatPanel.jsx\` — burbujas, editado, solo-lectura fuera planificacion
- [ ] Montar panel en ProjectResultsPage (import mínimo)
- [ ] Testing: npm run dev + npm run build
- [ ] **Handoff:** marcar ODS-5 checklist ítem 1 — chat UI integrado`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 3,
  });

  const ods5 = await createIssue({
    ...base,
    title: "UI Transición — transicionService + PlanificacionTransicionBar",
    description: `## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta ODS-4 marque este ítem
- [ ] \`transicionService.js\`
- [ ] \`usePlanificacionTransicion.js\` + \`PlanificacionTransicionBar.jsx\`
- [ ] Gestor solicita; admin/evaluador aprueba/rechaza
- [ ] \`projectService.updateProject\` con actorUserId/actorRole
- [ ] Testing: npm run dev + npm run build`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 3,
  });

  console.log("\n🔗 Dependencias (blocks)...");
  await addBlocksRelation(ods2, ods1);
  await addBlocksRelation(ods3, ods2);
  await addBlocksRelation(ods4, ods3);
  await addBlocksRelation(ods5, ods4);

  console.log("\n✅ Epic + 5 issues creados. Siguiente: node scripts/sprint_chat_planificacion.mjs next\n");
}

async function cmdStatus() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic || !issues.length) {
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
  for (const [st, count] of Object.entries(byState).sort()) console.log(`   ${st.padEnd(14)} ${count}`);
  console.log("");
}

async function cmdList() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues.\n");
    return;
  }
  const states = await getWorkflowStates(team.id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));
  console.log(`\n📋 ${EPIC_NAME}\n`);
  for (const i of issues.sort((a, b) => a.number - b.number)) {
    const st = stateMap[i.stateId]?.name || "?";
    console.log(`  ${i.identifier}  [${st.padEnd(12)}]  ${i.title.slice(0, 55)}`);
  }
  console.log("");
}

async function cmdNext() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues.\n");
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
  console.log(`\n⏭️  Próximo: ${i.identifier}  [${st}]  ${roles.join(", ")}\n    ${i.title}\n`);
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
    await updateIssueChecklist(issue, "all");
    issue = await findIssueByIdentifier(team, identifier);
  }
  if (stateName.toLowerCase() === "done") {
    const check = await requireChecklistComplete(issue);
    if (!check.ok) {
      console.error(`\n❌ ${identifier}: checklist ${check.done}/${check.total}\n`);
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
  console.log(`\n💬 ${identifier}: comentario publicado\n`);
}

async function cmdCleanup() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic || !issues.length) {
    console.log(`\n✅ Epic "${EPIC_NAME}" vacío.\n`);
    return;
  }
  for (const i of issues) {
    await linear.deleteIssue(i.id);
    console.log(`   ✓ ${i.identifier}`);
  }
  console.log(`\n✅ ${issues.length} issues eliminados.\n`);
}

function cmdHelp() {
  console.log(`
sprint_chat_planificacion.mjs — Chat Planificación · ODS-1→5 serial

  create | status | list | next | show ODS-N
  checklist ODS-N 1,2,3 | state ODS-N "In Progress" | state ODS-N Testing | state ODS-N Done
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
  process.exit(1);
});
