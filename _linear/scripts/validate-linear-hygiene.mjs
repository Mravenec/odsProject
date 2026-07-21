#!/usr/bin/env node
/**
 * Higiene _linear — falla si hay basura de sprints cerrados o multi-plan sin control.
 *
 * Uso:
 *   node scripts/validate-linear-hygiene.mjs
 *   node scripts/validate-linear-hygiene.mjs --json
 *
 * Exit 0 = OK para continuar (o solo plantillas).
 * Exit 1 = hay que hacer Fase 0 / no codear producto todavía.
 */
import { readdirSync, existsSync } from "fs";
import { dirname, join } from "path";
import { fileURLToPath } from "url";
import { spawnSync } from "child_process";

const here = dirname(fileURLToPath(import.meta.url));
const root = join(here, "..");
const scriptsDir = here;
const plansDir = join(root, "plans");
const jsonMode = process.argv.includes("--json");

const KEEP_SCRIPTS = new Set([
  "sprint-next.mjs",
  "sprint-epic-kit.mjs",
  "validate-plan-html.mjs",
  "validate-linear-hygiene.mjs",
  "linear-lib.mjs",
  "linear-comment.mjs",
  "linear-update-state.mjs",
]);

function listSprintScripts() {
  return readdirSync(scriptsDir)
    .filter((f) => f.startsWith("sprint_") && f.endsWith(".mjs"))
    .sort();
}

function listPlans(prefix) {
  if (!existsSync(plansDir)) return [];
  return readdirSync(plansDir)
    .filter((f) => f.startsWith(prefix) && f.endsWith(".html"))
    .sort();
}

function sprintBase(file) {
  return file.replace(/^sprint_/, "").replace(/\.mjs$/, "");
}

function epicHasOpenIssues(sprintFile) {
  const r = spawnSync(process.execPath, [join(scriptsDir, sprintFile), "next"], {
    encoding: "utf8",
    cwd: root,
  });
  const out = `${r.stdout || ""}${r.stderr || ""}`;
  // "Sin issues desbloqueados" o "Sin issues. create" = no hay trabajo activo
  const empty =
    /Sin issues desbloqueados/i.test(out) ||
    /Sin issues\. create/i.test(out) ||
    /Epic vacío/i.test(out);
  const hasWork = /\bODS-\d+\b/.test(out) && /desbloqueado/i.test(out);
  return { empty, hasWork, out, status: r.status ?? 1 };
}

const sprintScripts = listSprintScripts();
const plans = listPlans("plan_sprint_");
const resumenes = listPlans("resumen_sprint_");

const problems = [];
const stale = []; // Done / vacíos → candidatos a Fase 0
const active = [];

for (const f of sprintScripts) {
  const name = sprintBase(f);
  const { empty, hasWork } = epicHasOpenIssues(f);
  const plan = `plan_sprint_${name}.html`;
  const resumen = `resumen_sprint_${name}.html`;
  const hasPlan = plans.includes(plan);
  const hasResumen = resumenes.includes(resumen);

  if (hasWork) {
    active.push({ name, file: f, hasPlan, hasResumen });
  } else if (empty) {
    stale.push({ name, file: f, hasPlan, hasResumen });
    problems.push({
      code: "STALE_SPRINT",
      message: `Sprint «${name}» sin issues abiertos — ejecutar Fase 0: cleanup + borrar plan/resumen/script`,
      name,
    });
  }
}

if (plans.length > 1 && active.length <= 1) {
  // Varios planes con a lo sumo un activo = acumulación
  problems.push({
    code: "MULTI_PLAN",
    message: `Hay ${plans.length} plan_sprint_*.html (máx. 1 activo recomendado). Limpiar cerrados.`,
  });
}

if (resumenes.length > 0 && stale.length > 0) {
  problems.push({
    code: "RESUMEN_SIN_CLEANUP",
    message: `${resumenes.length} resumen(es) presentes con sprint(s) vacío(s): cerrar con cleanup y borrar artefactos.`,
  });
}

const ok = problems.length === 0;
const report = {
  ok,
  sprintScripts,
  plans,
  resumenes,
  active,
  stale,
  problems,
};

if (jsonMode) {
  console.log(JSON.stringify(report, null, 2));
  process.exit(ok ? 0 : 1);
}

console.log("\n🧹 validate-linear-hygiene\n");
console.log(`  sprint_*.mjs : ${sprintScripts.length || "(ninguno)"}`);
console.log(`  plan_sprint_ : ${plans.length}`);
console.log(`  resumen_     : ${resumenes.length}`);
console.log(`  activos      : ${active.map((a) => a.name).join(", ") || "—"}`);
console.log(`  candidatos Fase 0: ${stale.map((s) => s.name).join(", ") || "—"}`);

if (ok) {
  console.log("\n✅ Higiene OK — podés continuar con next / plan nuevo.\n");
  process.exit(0);
}

console.log("\n❌ Higiene rota — NO codear producto. Fase 0 primero:\n");
for (const p of problems) {
  console.log(`  · [${p.code}] ${p.message}`);
}
console.log(`
Comandos típicos (por cada sprint cerrado):
  node scripts/sprint_<nombre>.mjs cleanup
  # luego borrar:
  #   plans/plan_sprint_<nombre>.html
  #   plans/resumen_sprint_<nombre>.html
  #   scripts/sprint_<nombre>.mjs
`);
process.exit(1);
