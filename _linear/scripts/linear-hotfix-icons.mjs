#!/usr/bin/env node
/** Crea issue hotfix en Linear + comenta issues afectados */
import { LinearClient } from "@linear/sdk";
import "../load-env.mjs";

const linear = new LinearClient({ apiKey: process.env.LINEAR_API_KEY });
const teams = await linear.teams();
const team = teams.nodes.find((t) => t.key === "ODS");
const teamId = team.id;

const states = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
const doneId = states.nodes.find((s) => s.name === "Done")?.id;
const backlogId = states.nodes.find((s) => s.name === "Backlog")?.id;

const labels = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
const feLabel = labels.nodes.find((l) => l.name === "role:frontend")?.id;
const fixLabel = labels.nodes.find((l) => l.name === "type:fix")?.id;

const body = `## Root cause
El sprint original descargaba \`itemNN-deco.png\` (página de detalle). El grid de ods.cr usa \`itemNN.png\` del tema Drupal:

\`https://ods.cr/themes/custom/ods10/img/item01.png\` … \`item17.png\`

CSS ods.cr: \`#obj-item1 a { background-image: url(../img/item01.png); }\`

## Fix aplicado
- \`node scripts/download-ods-icons.mjs\` — fuente corregida (17 PNGs del grid ods.cr)
- \`OdsSelectionCard\` — fallback si img falla; sin backgroundColor cuando hay ícono
- CSS — \`.ods-card--icon\` con img \`position: absolute; inset: 0\`

## Verificación
\`\`\`bash
curl -I http://localhost:5173/ods-icons/ods-01.png  # → image/png
node scripts/download-ods-icons.mjs                 # re-descargar si faltan
\`\`\`

Relacionado: ODS-92 (assets), ODS-96 (grid), ODS-97 (CSS), ODS-99 (QA)`;

const res = await linear.createIssue({
  teamId,
  title: "HOTFIX: íconos grid ods.cr — itemNN.png (no itemNN-deco.png)",
  description: body,
  stateId: doneId ?? backlogId,
  priority: 1,
  estimate: 1,
  labelIds: [feLabel, fixLabel].filter(Boolean),
});
const issue = await res.issue;
console.log(`✅ ${issue.identifier}: ${issue.title}`);
console.log(issue.url);

const comment = `🔧 **Hotfix ${issue.identifier}** — Íconos corregidos: ahora \`item01.png…item17.png\` del tema ods.cr (grid #items-objetivos), no deco. Script: \`node _linear/scripts/download-ods-icons.mjs\`. Recargar /projects/new con Ctrl+Shift+R.`;

for (const num of [92, 96, 97, 99]) {
  const r = await linear.issues({ filter: { team: { id: { eq: teamId } }, number: { eq: num } }, first: 1 });
  const i = r.nodes[0];
  if (i) {
    await linear.createComment({ issueId: i.id, body: comment });
    console.log(`💬 ODS-${num}`);
  }
}
