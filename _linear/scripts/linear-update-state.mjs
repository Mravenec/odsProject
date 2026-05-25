#!/usr/bin/env node
/** node linear-update-state.mjs ODS-101 "In Progress" */
import { LinearClient } from "@linear/sdk";
import "../load-env.mjs";

const [identifier, stateName] = process.argv.slice(2);
if (!identifier || !stateName) {
  console.error('Uso: node linear-update-state.mjs ODS-101 "Done"');
  process.exit(1);
}

const linear = new LinearClient({ apiKey: process.env.LINEAR_API_KEY });
const num = Number(identifier.split("-")[1]);
const teams = await linear.teams();
const team = teams.nodes.find((t) => t.key === "ODS" || t.name === "linear_ods");
const states = await linear.workflowStates({ filter: { team: { id: { eq: team.id } } } });
const state = states.nodes.find((s) => s.name.toLowerCase() === stateName.toLowerCase());
if (!state) throw new Error(`Estado "${stateName}" no encontrado`);

const r = await linear.issues({
  filter: { team: { id: { eq: team.id } }, number: { eq: num } },
  first: 1,
});
const issue = r.nodes[0];
if (!issue) throw new Error(`Issue ${identifier} no encontrado`);

await linear.updateIssue(issue.id, { stateId: state.id });
console.log(`✅ ${identifier} → ${state.name}`);
