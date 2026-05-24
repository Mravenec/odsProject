#!/usr/bin/env node
/**
 * Sincroniza estado Linear Sprint 3 (ODS-72..82) con el trabajo ya hecho en el repo.
 */
import { linear } from "./linear-config.mjs";

const DEPS = [
  [73, 72], [74, 73], [75, 73], [76, 75], [77, 74], [77, 76],
  [78, 77], [79, 78], [80, 78], [81, 79], [81, 80], [82, 81],
];

/** number → estado Linear según avance real en repo */
const STATUS = {
  72: { state: "Done", note: "✅ SQL `evaluador` + mocks; drop/setup/load_mocks ejecutados." },
  73: { state: "Done", note: "✅ `mvn generate-sources` OK; sin cambios inesperados en POJOs." },
  74: { state: "Done", note: "✅ Controller: paths evaluación + alias deprecados." },
  75: { state: "Done", note: "✅ Service: métodos renombrados (enviar/aprobar/rechazar evaluación)." },
  76: { state: "Done", note: '✅ "auditor" → "evaluador" en guards y transiciones.' },
  77: { state: "Done", note: "✅ Spring Boot arriba; GET `/evaluacion/metrics` → 200; `.http` actualizado." },
  78: { state: "Done", note: "✅ projectService + usePermissions + authService (evaluador)." },
  79: { state: "Done", note: "✅ EvaluationQueuePage + rutas `/evaluacion`." },
  80: { state: "Done", note: "✅ ProjectResultsPage textos y paths de evaluación." },
  81: { state: "Done", note: "✅ formatters.js + App.jsx + redirects `/audit`." },
  82: { state: "Done", note: "✅ `npm run build` OK; rutas `/evaluacion`; AuditQueuePage eliminado; textos EvaluationPage." },
};

async function getTeamId() {
  const r = await linear.teams();
  const t = r.nodes.find((x) => x.key === "ODS" || x.name.toLowerCase() === "linear_ods");
  if (!t) throw new Error("Equipo ODS no encontrado");
  return t.id;
}

async function stateId(teamId, name) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  return r.nodes.find((s) => s.name.toLowerCase() === name.toLowerCase())?.id ?? null;
}

async function main() {
  console.log("\n🔷 Sync Linear — Sprint 3 Evaluación\n");

  const teamId = await getTeamId();
  const states = {};
  for (const name of ["Done", "Todo", "Ready", "Code Review", "In Progress", "Backlog"]) {
    states[name] = await stateId(teamId, name);
  }

  const issuesRes = await linear.issues({
    filter: { team: { key: { eq: "ODS" } }, number: { gte: 72, lte: 82 } },
    first: 20,
  });
  const byNum = Object.fromEntries(issuesRes.nodes.map((i) => [i.number, i]));

  console.log("🔗 Dependencias...");
  for (const [childNum, parentNum] of DEPS) {
    const child = byNum[childNum];
    const parent = byNum[parentNum];
    if (!child || !parent) continue;
    try {
      await linear.createIssueRelation({
        issueId: parent.id,
        relatedIssueId: child.id,
        type: "blocks",
      });
      console.log(`  ✅ ${child.identifier} ← ${parent.identifier}`);
    } catch (e) {
      const msg = e.message || String(e);
      if (msg.includes("already") || msg.includes("duplicate") || msg.includes("exists")) {
        console.log(`  ♻️  ${child.identifier} ← ${parent.identifier}`);
      } else {
        console.log(`  ⚠️  ${child.identifier}: ${msg.slice(0, 100)}`);
      }
    }
  }

  console.log("\n📋 Estados...");
  for (const [numStr, cfg] of Object.entries(STATUS)) {
    const num = Number(numStr);
    const issue = byNum[num];
    if (!issue) {
      console.log(`  ❌ ODS-${num} no encontrado`);
      continue;
    }
    const sid = states[cfg.state];
    if (sid) {
      await linear.updateIssue(issue.id, { stateId: sid });
    }
    await linear.createComment({
      issueId: issue.id,
      body: `🤖 **SYNC REPO** (${new Date().toISOString().slice(0, 10)})\n${cfg.note}`,
    });
    console.log(`  ${issue.identifier} → ${cfg.state}`);
  }

  console.log("\n✅ Linear actualizado.\n");
}

main().catch((e) => {
  console.error("❌", e.message);
  process.exit(1);
});
