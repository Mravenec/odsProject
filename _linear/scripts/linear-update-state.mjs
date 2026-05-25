#!/usr/bin/env node
/**
 * Cambiar estado de un issue en Linear.
 *
 * Uso:
 *   node linear-update-state.mjs ODS-110 "In Progress"
 *   node linear-update-state.mjs ODS-110 Done              # exige checklist completo
 *   node linear-update-state.mjs ODS-110 Done --check-all    # marca checklist y cierra
 *   node linear-update-state.mjs ODS-110 --checklist all
 *   node linear-update-state.mjs ODS-110 --checklist 1,2,3
 *   node linear-update-state.mjs ODS-110 --checklist-status
 *
 * Flujo recomendado:
 *   1. --checklist 1,2,3  (marcar ítems en la descripción)
 *   2. comment opcional     (linear-comment.mjs)
 *   3. Done                 (solo si checklist completo)
 */
import {
  getTeam,
  findIssueByIdentifier,
  updateIssueChecklist,
  requireChecklistComplete,
  setIssueState,
  printChecklistStatus,
} from "./linear-lib.mjs";

const args = process.argv.slice(2);

function usage() {
  console.error(`
Uso:
  node linear-update-state.mjs ODS-110 "In Progress"
  node linear-update-state.mjs ODS-110 Done [--check-all]
  node linear-update-state.mjs ODS-110 --checklist all|1,2,3
  node linear-update-state.mjs ODS-110 --checklist-status
`);
  process.exit(1);
}

if (!args.length) usage();

const identifier = args[0];
const checkAll = args.includes("--check-all");
const statusIdx = args.indexOf("--checklist-status");
const checklistIdx = args.indexOf("--checklist");

async function main() {
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);

  if (statusIdx !== -1) {
    printChecklistStatus(issue.identifier, issue.description);
    return;
  }

  if (checklistIdx !== -1) {
    const spec = args[checklistIdx + 1];
    if (!spec) {
      console.error("Falta spec: all o 1,2,3");
      process.exit(1);
    }
    await updateIssueChecklist(issue, spec);
    issue = await findIssueByIdentifier(team, identifier);
    printChecklistStatus(issue.identifier, issue.description);
    return;
  }

  const stateParts = args.slice(1).filter((a) => !a.startsWith("--"));
  const stateName = stateParts.join(" ");
  if (!stateName) usage();

  if (checkAll) {
    await updateIssueChecklist(issue, "all");
    issue = await findIssueByIdentifier(team, identifier);
  }

  const isDone = stateName.toLowerCase() === "done";
  if (isDone) {
    const check = await requireChecklistComplete(issue);
    if (!check.ok) {
      console.error(`\n❌ ${identifier}: checklist incompleto (${check.done}/${check.total})\n`);
      check.unchecked.forEach((l) => console.error(l));
      console.error(`\nMarque ítems: node linear-update-state.mjs ${identifier} --checklist all|1,2,3`);
      console.error(`O force cierre: node linear-update-state.mjs ${identifier} Done --check-all\n`);
      process.exit(1);
    }
  }

  const state = await setIssueState(issue, stateName);
  console.log(`\n✅ ${identifier} → ${state.name}\n`);
}

main().catch((e) => {
  console.error("\n❌", e.message);
  process.exit(1);
});
