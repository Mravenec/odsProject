#!/usr/bin/env node
/** Comentario en issue Linear: node linear-comment.mjs ODS-92 "texto" */
import { LinearClient } from "@linear/sdk";
import "../load-env.mjs";

const [identifier, ...rest] = process.argv.slice(2);
const body = rest.join(" ");
if (!identifier || !body) {
  console.error("Uso: node linear-comment.mjs ODS-92 \"mensaje\"");
  process.exit(1);
}

const linear = new LinearClient({ apiKey: process.env.LINEAR_API_KEY });
const num = Number(identifier.split("-")[1]);
const teams = await linear.teams();
const team = teams.nodes.find((t) => t.key === "ODS" || t.name === "linear_ods");
const r = await linear.issues({
  filter: { team: { id: { eq: team.id } }, number: { eq: num } },
  first: 1,
});
const issue = r.nodes[0];
if (!issue) throw new Error(`No encontrado: ${identifier}`);
await linear.createComment({ issueId: issue.id, body });
console.log(`💬 ${identifier}: comentario publicado`);
