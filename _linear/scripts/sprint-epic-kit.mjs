/**
 * Kit compartido para sprint_<nombre>.mjs multi-epic.
 * Cada epic importa helpers + define EPIC_NAME, ISSUES, BLOCKS.
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

export function validatePlanHtml(planRelative) {
  const PLAN_FILE = join(_linearRoot, planRelative);
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

export async function getEpicIssues(EPIC_NAME) {
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
  try {
    const res = await linear.createCycle({ teamId, name, startsAt, endsAt });
    console.log(`  ✅ Sprint: ${name}`);
    return (await res.cycle).id;
  } catch (e) {
    const msg = e?.message || String(e);
    if (!/overlap/i.test(msg)) throw e;
    // Linear no permite cycles con fechas solapadas: reusar el activo (o el más reciente).
    const active = await linear.cycles({
      filter: { team: { id: { eq: teamId } }, isActive: { eq: true } },
    });
    if (active.nodes.length) {
      console.log(`  ♻️  Cycle activo (evita overlap): ${active.nodes[0].name}`);
      return active.nodes[0].id;
    }
    const all = await linear.cycles({ filter: { team: { id: { eq: teamId } } }, first: 10 });
    const sorted = [...all.nodes].sort((a, b) => new Date(b.endsAt) - new Date(a.endsAt));
    if (sorted[0]) {
      console.log(`  ♻️  Cycle reciente (evita overlap): ${sorted[0].name}`);
      return sorted[0].id;
    }
    throw e;
  }
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

/**
 * @param {object} cfg
 * @param {string} cfg.EPIC_NAME
 * @param {string} cfg.SPRINT_NAME
 * @param {string} cfg.EPIC_DESC
 * @param {string} cfg.PLAN_REL — plans/plan_sprint_….html
 * @param {string} cfg.SCRIPT_HINT — sprint_….mjs
 * @param {string} [cfg.EPIC_COLOR]
 * @param {Record<string, {title, role, estimate, description, type?: 'bug'|'feature'}>} cfg.ISSUES
 * @param {Array<[string, string]>} cfg.BLOCKS — [blockedKey, blockerKey]
 */
export function registerSprint(cfg) {
  const {
    EPIC_NAME,
    SPRINT_NAME,
    EPIC_DESC,
    PLAN_REL,
    SCRIPT_HINT,
    EPIC_COLOR = "#0EA5E9",
    ISSUES,
    BLOCKS,
  } = cfg;

  async function cmdCreate() {
    validatePlanHtml(PLAN_REL);
    console.log(`\n🔷  ${SPRINT_NAME}`);
    console.log("━".repeat(58));

    const team = await getTeam();
    const teamId = team.id;
    const ST = { backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8") };
    const L = {
      be: await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
      fe: await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
      db: await getOrCreateLabel(teamId, "role:database", "#EAB308"),
      orch: await getOrCreateLabel(teamId, "role:orchestrator", "#EF4444"),
      bug: await getOrCreateLabel(teamId, "type:bug", "#EF4444"),
      feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
    };
    const roleLabel = {
      backend: L.be,
      frontend: L.fe,
      database: L.db,
      orchestrator: L.orch,
    };

    const epicId = await getOrCreateEpic(teamId, EPIC_NAME, EPIC_DESC, EPIC_COLOR);
    const NOW = new Date();
    const sprintId = await getOrCreateSprint(
      teamId,
      SPRINT_NAME,
      NOW,
      new Date(NOW.getTime() + 21 * 24 * 60 * 60 * 1000)
    );
    const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };

    console.log(`\n📝 Issues (${Object.keys(ISSUES).length})...`);
    const created = {};
    for (const [key, spec] of Object.entries(ISSUES)) {
      const typeLabel = spec.type === "bug" ? L.bug : L.feat;
      created[key] = await createIssue({
        ...base,
        title: spec.title,
        description: spec.description,
        priority: spec.type === "bug" ? 1 : 2,
        labelIds: [roleLabel[spec.role], typeLabel],
        estimate: spec.estimate,
      });
    }

    if (BLOCKS?.length) {
      console.log("\n🔗 Blocks...");
      for (const [blocked, blocker] of BLOCKS) {
        await addBlocksRelation(created[blocked], created[blocker]);
      }
    }

    const points = Object.values(created).reduce((s, i) => s + (i.estimate || 0), 0);
    console.log("\n" + "━".repeat(58));
    console.log("🎉  Epic creado\n");
    console.log(`  Issues: ${Object.keys(created).length} · Puntos: ${points}`);
    console.log(`  next: node scripts/sprint-next.mjs ${SCRIPT_HINT.replace(/^sprint_/, "").replace(/\.mjs$/, "")}\n`);
  }

  async function cmdStatus() {
    const team = await getTeam();
    const { epic, issues } = await getEpicIssues(EPIC_NAME);
    if (!epic) {
      console.log(`\n⚠️  Epic no encontrado. create\n`);
      return;
    }
    const enriched = await resolveIssueStates(issues);
    console.log(`\n📊 ${SPRINT_NAME} — ${issues.length} issues\n`);
    for (const { issue: i, state: stObj } of enriched.sort((a, b) => a.issue.number - b.issue.number)) {
      const c = checklistSummary(i.description);
      console.log(`  ${i.identifier}  [${stObj?.name || "?"}]  chk ${c.done}/${c.total}  ${i.title.slice(0, 55)}`);
    }
    console.log("");
  }

  async function cmdNext() {
    const team = await getTeam();
    const { issues } = await getEpicIssues(EPIC_NAME);
    if (!issues.length) {
      console.log("\n⚠️  Sin issues. create\n");
      return;
    }
    const available = await getUnblockedIssues(issues);
    if (!available.length) {
      console.log("\n✅ Sin issues desbloqueados en este epic.\n");
      return;
    }
    console.log(`\n⏭️  ${EPIC_NAME}`);
    console.log(`   ${available.length} issue(s) desbloqueado(s):\n`);
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
    if (flags.includes("--check-all")) {
      console.error("\n❌ --check-all prohibido.\n");
      process.exit(1);
    }
    const team = await getTeam();
    let issue = await findIssueByIdentifier(team, identifier);
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
    const { epic, issues } = await getEpicIssues(EPIC_NAME);
    if (!epic) {
      console.log("\n✅ Epic no encontrado (ya limpio).\n");
      return;
    }
    for (const i of issues) {
      await linear.deleteIssue(i.id);
      console.log(`  ✓ issue ${i.identifier}`);
    }
    if (issues.length) {
      console.log(`\n  ${issues.length} issue(s) eliminados.`);
    } else {
      console.log("\n  (sin issues en el epic)");
    }
    // Borrar el proyecto/epic en Linear — no dejar epics Completed acumulados
    try {
      await linear.deleteProject(epic.id);
      console.log(`  ✓ epic eliminado: ${EPIC_NAME}\n`);
    } catch (e) {
      // Fallback: archivar si la API no permite delete
      try {
        await linear.archiveProject(epic.id);
        console.log(`  ✓ epic archivado (delete no disponible): ${EPIC_NAME}\n`);
      } catch (e2) {
        console.error(`\n❌ No se pudo borrar/archivar epic «${EPIC_NAME}»: ${e2.message || e2}\n`);
        process.exit(1);
      }
    }
  }

  function cmdHelp() {
    console.log(`
🔷 ${SCRIPT_HINT} — ${SPRINT_NAME}

Epic Linear: ${EPIC_NAME}
Plan: ${PLAN_REL}

Comandos: create | next | show | checklist | handoff | state | status | cleanup
Multi-epic: node scripts/sprint-next.mjs ${SCRIPT_HINT.replace(/^sprint_/, "").replace(/\.mjs$/, "")}
`);
  }

  const [cmd, ...rest] = process.argv.slice(2);
  const handlers = {
    create: cmdCreate,
    status: cmdStatus,
    list: cmdStatus,
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
  });
}
