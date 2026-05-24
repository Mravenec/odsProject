#!/usr/bin/env node
/** Vincula dependencias ODS-92…99 (sprint íconos) si aún no existen. */
import { LinearClient } from "@linear/sdk";
import "../load-env.mjs";

const linear = new LinearClient({ apiKey: process.env.LINEAR_API_KEY });
const TEAM = process.env.LINEAR_TEAM_NAME || "linear_ods";

const EDGES = [
  ["ODS-93", "ODS-92"],
  ["ODS-94", "ODS-93"],
  ["ODS-95", "ODS-94"],
  ["ODS-96", "ODS-95"],
  ["ODS-97", "ODS-96"],
  ["ODS-98", "ODS-95"],
  ["ODS-99", "ODS-97"],
  ["ODS-99", "ODS-98"],
];

async function getTeamId() {
  const teams = await linear.teams();
  const t = teams.nodes.find(
    (x) => x.name.toLowerCase() === TEAM.toLowerCase() || x.key.toLowerCase() === TEAM.toLowerCase()
  );
  if (!t) throw new Error(`Team ${TEAM} not found`);
  return t.id;
}

async function byIdentifier(teamId, id) {
  const r = await linear.issues({
    filter: { team: { id: { eq: teamId } }, number: { eq: Number(id.split("-")[1]) } },
    first: 1,
  });
  const i = r.nodes[0];
  if (!i) throw new Error(`Issue ${id} not found`);
  return i;
}

async function main() {
  const teamId = await getTeamId();
  const cache = {};
  for (const [childId, parentId] of EDGES) {
    if (!cache[childId]) cache[childId] = await byIdentifier(teamId, childId);
    if (!cache[parentId]) cache[parentId] = await byIdentifier(teamId, parentId);
    const child = cache[childId];
    const parent = cache[parentId];
    await linear.createIssueRelation({
      issueId: parent.id,
      relatedIssueId: child.id,
      type: "blocks",
    });
    console.log(`🔗 ${child.identifier} ← ${parent.identifier}`);
  }
  console.log("✅ Dependencias listas");
}

main().catch((e) => {
  console.error("❌", e.message);
  process.exit(1);
});
