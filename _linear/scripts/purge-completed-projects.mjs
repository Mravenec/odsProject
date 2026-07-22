/**
 * Fase 0 extra: archiva/borra proyectos Linear Completed del equipo.
 * En Linear, `projectDelete` = archivar (desaparecen de activos; "All" puede listarlos).
 *
 * Uso: node scripts/purge-completed-projects.mjs
 *        node scripts/purge-completed-projects.mjs --dry-run
 *
 * Criterio OK: 0 proyectos Completed **activos** (sin includeArchived).
 */
import { linear, getTeam } from "./linear-lib.mjs";

const dry = process.argv.includes("--dry-run");

async function fetchProjects(teamId, { includeArchived = false } = {}) {
  const all = [];
  let cursor;
  do {
    const res = await linear.projects({
      filter: { accessibleTeams: { id: { eq: teamId } } },
      first: 50,
      after: cursor,
      includeArchived,
    });
    all.push(...res.nodes);
    cursor = res.pageInfo.hasNextPage ? res.pageInfo.endCursor : undefined;
  } while (cursor);
  return all;
}

function isCompletedLike(p) {
  const state = String(p.state || "").toLowerCase();
  const progress = typeof p.progress === "number" ? p.progress : null;
  return state === "completed" || state === "canceled" || (progress !== null && progress >= 1);
}

async function deleteProjectHard(project) {
  try {
    await linear.deleteProject(project.id);
    return "deleted";
  } catch (e1) {
    const m = `mutation($id: String!) { projectDelete(id: $id) { success } }`;
    await linear.client.rawRequest(m, { id: project.id });
    return "deleted-gql";
  }
}

const team = await getTeam();
const active = await fetchProjects(team.id, { includeArchived: false });
const targets = active.filter(isCompletedLike);

console.log(`\n🧹 purge-completed-projects (${dry ? "dry-run" : "LIVE"})`);
console.log(`  team: ${team.name} · activos: ${active.length} · Completed a limpiar: ${targets.length}`);
console.log(`  Nota: Linear projectDelete archiva; "Projects/all" puede mostrar archivados.\n`);

for (const p of targets) {
  const label = `${p.name} [${p.state}]`;
  if (dry) {
    console.log(`  · would remove: ${label}`);
    continue;
  }
  try {
    const how = await deleteProjectHard(p);
    console.log(`  ✓ ${how}: ${label}`);
  } catch (e) {
    console.error(`  ✗ ${label}: ${e.message}`);
    process.exitCode = 1;
  }
}

if (!dry) {
  const leftActive = await fetchProjects(team.id, { includeArchived: false });
  const still = leftActive.filter(isCompletedLike);
  if (still.length) {
    console.error(`\n❌ Quedan ${still.length} Completed activos:`);
    for (const p of still) console.error(`  - ${p.name}`);
    process.exit(1);
  }
  const archived = await fetchProjects(team.id, { includeArchived: true });
  const archivedDone = archived.filter((p) => p.archivedAt && isCompletedLike(p));
  console.log(`\n✅ Activos limpios (${leftActive.length} proyectos activos).`);
  if (archivedDone.length) {
    console.log(
      `  ℹ ${archivedDone.length} archivado(s) pueden verse en Projects → All; filtrá Active o vaciá trash en Linear UI.`
    );
  }
  console.log("");
} else {
  console.log("");
}
