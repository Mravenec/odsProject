#!/usr/bin/env node
/**
 * Higiene _linear — falla si hay basura que impide trabajo nuevo o Fase 6 incompleta.
 *
 * Uso:
 *   node scripts/validate-linear-hygiene.mjs
 *   node scripts/validate-linear-hygiene.mjs --json
 *
 * Exit 0 = OK (puede haber sprint cerrado CON resumen legible — Fase 0 al próximo trabajo).
 * Exit 1 = hay que corregir (Fase 6 incompleta, multi-plan, etc.) antes de codear.
 *
 * Regla de timing:
 *   - Fase 6 deja plan + resumen + script + epic Completed para que el humano lea.
 *   - Fase 0 (cleanup issues+epic + borrar artefactos) solo al INICIAR un trabajo nuevo.
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
  const empty =
    /Sin issues desbloqueados/i.test(out) ||
    /Sin issues\. create/i.test(out) ||
    /Epic vacío/i.test(out) ||
    /Epic no encontrado/i.test(out);
  const hasWork = /\bODS-\d+\b/.test(out) && /desbloqueado/i.test(out);
  return { empty, hasWork, out, status: r.status ?? 1 };
}

const sprintScripts = listSprintScripts();
const plans = listPlans("plan_sprint_");
const resumenes = listPlans("resumen_sprint_");

const problems = [];
const staleNeedsResumen = []; // Done sin resumen → incompleto
const closedReadable = []; // Done + resumen → legible; Fase 0 en próximo trabajo
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
    if (hasResumen) {
      closedReadable.push({ name, file: f, hasPlan, hasResumen });
    } else {
      staleNeedsResumen.push({ name, file: f, hasPlan, hasResumen });
      problems.push({
        code: "FASE6_INCOMPLETA",
        message: `Sprint «${name}» sin issues abiertos y sin resumen_sprint_${name}.html — completar Fase 6 (resumen + epic Completed). No borrar aún.`,
        name,
      });
    }
  }
}

if (plans.length > 1 && active.length <= 1) {
  problems.push({
    code: "MULTI_PLAN",
    message: `Hay ${plans.length} plan_sprint_*.html (máx. 1). Al iniciar trabajo nuevo: Fase 0 del cerrado, luego un solo plan.`,
  });
}

// Varios resúmenes = acumulación (varios sprints sin Fase 0)
if (resumenes.length > 1) {
  problems.push({
    code: "MULTI_RESUMEN",
    message: `Hay ${resumenes.length} resumen_sprint_*.html. Al iniciar el próximo trabajo: Fase 0 (cleanup+epic+borrar artefactos) de los cerrados.`,
  });
}

const ok = problems.length === 0;
const report = {
  ok,
  sprintScripts,
  plans,
  resumenes,
  active,
  closedReadable,
  staleNeedsResumen,
  pendingFase0OnNextWork: closedReadable.map((c) => c.name),
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
console.log(
  `  cerrados legibles (Fase 0 al próximo trabajo): ${closedReadable.map((s) => s.name).join(", ") || "—"}`
);
console.log(
  `  Fase 6 incompleta: ${staleNeedsResumen.map((s) => s.name).join(", ") || "—"}`
);

if (ok) {
  if (closedReadable.length) {
    console.log(
      "\n✅ Higiene OK — resumen(es) legible(s). No borrar hasta la próxima instrucción de trabajo nuevo (Fase 0: cleanup issues+epic + borrar plan/resumen/script).\n"
    );
  } else {
    console.log("\n✅ Higiene OK — podés continuar con next / plan nuevo.\n");
  }
  process.exit(0);
}

console.log("\n❌ Higiene rota — NO codear producto todavía:\n");
for (const p of problems) {
  console.log(`  · [${p.code}] ${p.message}`);
}
console.log(`
Fase 6 (al terminar sprint): resumen HTML + epic Completed — DEJAR archivos para lectura.
Fase 0 (al INICIAR trabajo nuevo):
  node scripts/sprint_<nombre>.mjs cleanup   # borra issues + epic Linear
  # luego borrar: plan_ / resumen_ / sprint_*.mjs
`);
process.exit(1);
