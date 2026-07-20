#!/usr/bin/env node
/**
 * next multi-epic — un epic o todos en paralelo.
 *
 * Uso:
 *   node scripts/sprint-next.mjs
 *       → 1 sprint: next de ese
 *       → N sprints: next de CADA epic (multi-agente)
 *   node scripts/sprint-next.mjs hotfix_walkthrough
 *       → solo ese sprint_<nombre>.mjs next
 *   node scripts/sprint-next.mjs list
 *       → lista sprint_*.mjs activos
 */
import { readdirSync } from "fs";
import { dirname, join } from "path";
import { fileURLToPath } from "url";
import { spawnSync } from "child_process";

const here = dirname(fileURLToPath(import.meta.url));
const scriptsDir = here;
const arg = process.argv[2];

const sprints = readdirSync(scriptsDir)
  .filter((f) => f.startsWith("sprint_") && f.endsWith(".mjs") && !f.includes("sprint-next"))
  .sort();

function sprintName(file) {
  return file.replace(/^sprint_/, "").replace(/\.mjs$/, "");
}

function runNext(file) {
  const script = join(scriptsDir, file);
  console.log(`\n── ${file} ──`);
  const r = spawnSync(process.execPath, [script, "next"], {
    stdio: "inherit",
    cwd: join(here, ".."),
  });
  return r.status ?? 1;
}

if (sprints.length === 0) {
  console.error("\n❌ No hay sprint activo (scripts/sprint_<nombre>.mjs).\n");
  console.error("   Orden: Fase 0 → plan_sprint_*.html → ✅ APROBADO → sprint_*.mjs create\n");
  process.exit(1);
}

if (arg === "list") {
  console.log("\n📋 Sprints activos (multi-epic):\n");
  for (const f of sprints) console.log(`  · ${sprintName(f)}  →  scripts/${f}`);
  console.log(`\n  Uso: node scripts/sprint-next.mjs <nombre>`);
  console.log(`       node scripts/sprint-next.mjs          # next en todos\n`);
  process.exit(0);
}

if (arg) {
  const match = sprints.find((f) => sprintName(f) === arg || f === `sprint_${arg}.mjs`);
  if (!match) {
    console.error(`\n❌ No existe sprint_${arg}.mjs\n`);
    console.error("   Activos:");
    sprints.forEach((f) => console.error(`   - ${sprintName(f)}`));
    console.error("");
    process.exit(1);
  }
  process.exit(runNext(match));
}

if (sprints.length === 1) {
  process.exit(runNext(sprints[0]));
}

console.log(`\n🔀 Multi-epic: ${sprints.length} sprints — next por línea de trabajo\n`);
console.log("   (Agente: reclamá solo issues de TU epic / rol)\n");
let worst = 0;
for (const f of sprints) {
  const st = runNext(f);
  if (st !== 0) worst = st;
}
console.log("\n💡 Filtrar: node scripts/sprint-next.mjs <nombre_epic>\n");
process.exit(worst);
