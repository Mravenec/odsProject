#!/usr/bin/env node
/**
 * Sprint 7 — Documentos de evidencia (EvidenceSection)
 * Equipo: linear_ods · Orquestación multiagente secuencial
 *
 * Comandos:
 *   create              Crea epic, sprint, labels e issues EVD-1…8
 *   status              Resumen del sprint (estados, puntos, bloqueados)
 *   list                Lista issues del sprint con estado
 *   next                Próximo issue desbloqueado (sin dependencias pendientes)
 *   show ODS-110        Detalle de un issue
 *   checklist ODS-110 all|1,2,3  Marca checklist en descripción (obligatorio antes de Done)
 *   state ODS-110 Done [--check-all]  Cambia estado (Done exige checklist completo)
 *   comment ODS-110 "…"  Nota técnica opcional (NO reemplaza checklist)
 *   cleanup             Elimina issues del epic Evidencia / UX
 *   help                Muestra esta ayuda
 */
import {
  linear,
  getTeam,
  findIssueByIdentifier,
  getWorkflowStates,
  updateIssueChecklist,
  requireChecklistComplete,
  setIssueState,
  addIssueComment,
  printChecklistStatus,
  checklistSummary,
} from "./linear-lib.mjs";

const LINEAR_TEAM_NAME = process.env.LINEAR_TEAM_NAME || "linear_ods";

const EPIC_NAME = "Evidencia / UX";
const SPRINT_NAME = "Sprint 7 — Documentos de evidencia";
const EPIC_DESC =
  "EvidenceSection: DB fuente de verdad → JOOQ → BE → .http → FE. Español usted, descripción opcional.";

async function fetchAllIssues(filter) {
  const all = [];
  let cursor = undefined;
  do {
    const r = await linear.issues({ filter, first: 50, after: cursor });
    all.push(...r.nodes);
    cursor = r.pageInfo.hasNextPage ? r.pageInfo.endCursor : undefined;
  } while (cursor);
  return all;
}

async function getEpicIssues(team) {
  const projects = await linear.projects({ filter: { name: { eq: EPIC_NAME } } });
  const epic = projects.nodes[0];
  if (!epic) return { epic: null, issues: [] };
  const issues = await fetchAllIssues({ project: { id: { eq: epic.id } } });
  return { epic, issues };
}

// ─── Setup helpers (create) ──────────────────────────────────────────────────

async function getOrCreateState(teamId, name, type, color) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((s) => s.name.toLowerCase() === name.toLowerCase());
  if (f) {
    console.log(`  ♻️  ${name}`);
    return f.id;
  }
  const res = await linear.createWorkflowState({ teamId, name, type, color });
  console.log(`  ✅ ${name}`);
  return (await res.workflowState).id;
}

async function getOrCreateLabel(teamId, name, color) {
  const r = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((l) => l.name.toLowerCase() === name.toLowerCase());
  if (f) {
    console.log(`  ♻️  ${name}`);
    return f.id;
  }
  const res = await linear.createIssueLabel({ teamId, name, color });
  console.log(`  ✅ ${name}`);
  return (await res.issueLabel).id;
}

async function getOrCreateEpic(teamId, name, desc, color) {
  const r = await linear.projects({ filter: { name: { eq: name } } });
  if (r.nodes.length) {
    console.log(`  ♻️  Epic: ${name}`);
    return r.nodes[0].id;
  }
  const res = await linear.createProject({ teamIds: [teamId], name, description: desc, color });
  console.log(`  ✅ Epic: ${name}`);
  return (await res.project).id;
}

async function getOrCreateSprint(teamId, name, startsAt, endsAt) {
  const r = await linear.cycles({ filter: { team: { id: { eq: teamId } }, name: { eq: name } } });
  if (r.nodes.length) {
    console.log(`  ♻️  Sprint: ${name}`);
    return r.nodes[0].id;
  }
  const res = await linear.createCycle({ teamId, name, startsAt, endsAt });
  console.log(`  ✅ Sprint: ${name}`);
  return (await res.cycle).id;
}

async function createIssue(params) {
  const res = await linear.createIssue(params);
  const i = await res.issue;
  console.log(`    📌 ${i.identifier}: ${i.title}`);
  return i;
}

async function addBlocksRelation(blockedIssue, blockerIssue) {
  await linear.createIssueRelation({
    issueId: blockerIssue.id,
    relatedIssueId: blockedIssue.id,
    type: "blocks",
  });
  console.log(`     🔗 ${blockedIssue.identifier} ← ${blockerIssue.identifier}`);
}

// ─── Comando: create ─────────────────────────────────────────────────────────

async function cmdCreate() {
  console.log("\n🔷  Sprint 7 — Documentos de evidencia (EvidenceSection)");
  console.log("━".repeat(58));

  const team = await getTeam();
  const teamId = team.id;
  console.log(`\n✅ Equipo: ${team.name} (${team.key})`);

  console.log("\n🔄 Estados...");
  const ST = {
    backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8"),
    todo: await getOrCreateState(teamId, "Todo", "unstarted", "#A78BFA"),
    inprog: await getOrCreateState(teamId, "In Progress", "started", "#F59E0B"),
    review: await getOrCreateState(teamId, "In Review", "started", "#3B82F6"),
    done: await getOrCreateState(teamId, "Done", "completed", "#22C55E"),
  };

  console.log("\n🏷️  Labels...");
  const L = {
    db: await getOrCreateLabel(teamId, "role:database", "#EAB308"),
    be: await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
    fe: await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
    ux: await getOrCreateLabel(teamId, "role:ux", "#EC4899"),
    feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
    bug: await getOrCreateLabel(teamId, "type:bug", "#EF4444"),
    test: await getOrCreateLabel(teamId, "type:test", "#8B5CF6"),
  };

  const epicId = await getOrCreateEpic(teamId, EPIC_NAME, EPIC_DESC, "#EC4899");

  const NOW = new Date();
  const WEEK = 7 * 24 * 60 * 60 * 1000;
  const sprintId = await getOrCreateSprint(
    teamId,
    SPRINT_NAME,
    new Date(NOW.getTime() + 12 * WEEK),
    new Date(NOW.getTime() + 14 * WEEK)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };

  console.log("\n📝 Issues...");

  const evd1 = await createIssue({
    ...base,
    title: "DB: verificar proyecto_documentos en SQL fuente + drop/setup/load",
    description: `## Fuente de verdad
\`0.database/propuesta_actual/2. ods_master_database.sql\`

## Tabla proyecto_documentos
- descripcion VARCHAR(500) — requerida para bug reportado
- Si hay drift entre SQL y BD viva → corregir SQL primero

## Comandos obligatorios (comandosDelProyecto.txt pasos 1–2)
\`\`\`bash
python 0.database/drop_db.py
python 0.database/setup_db.py
python 0.database/load_mocks.py
\`\`\`

## Checklist
- [ ] DESCRIBE ods_master.proyecto_documentos incluye descripcion
- [ ] Mocks cargados sin error
- [ ] Comentario Linear: «sin cambio SQL» o diff aplicado`,
    priority: 1,
    labelIds: [L.db, L.feat],
    estimate: 1,
  });

  const evd2 = await createIssue({
    ...base,
    title: "JOOQ: spring-boot:run — regenerar POJOs ods_master",
    description: `## Comando (paso 3 comandosDelProyecto.txt)
\`\`\`bash
cd 1.backend/odsProject
mvn spring-boot:run
\`\`\`

Genera JOOQ + POJOs al arrancar. **No** usar generate-sources aislado.

## Checklist
- [ ] App arranca / BUILD SUCCESS
- [ ] Verificar POJO ProyectoDocumentos en jooq/ods_master (si aplica)
- [ ] Documentar en Linear: POJO generado vs raw DSL`,
    priority: 1,
    labelIds: [L.be, L.test],
    estimate: 1,
  });

  const evd3 = await createIssue({
    ...base,
    title: "BE: DocumentRepository — insert/list con descripcion (JOOQ/POJO)",
    description: `## Archivo
\`DocumentRepository.java\`

## Cambios
- INSERT incluye descripcion
- SELECT list incluye descripcion
- Migrar a POJO JOOQ si EVD-2 generó ProyectoDocumentos

## Pre-requisito
EVD-2 Done — POJOs disponibles

## Checklist
- [ ] insertDocumento persiste descripcion
- [ ] findByProyecto retorna descripcion`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 1,
  });

  const evd4 = await createIssue({
    ...base,
    title: "BE: DocumentService — normalizar camelCase + descripcion en upload",
    description: `## Archivo
\`DocumentService.java\`

## Cambios
1. uploadDocument → incluir descripcion en JSON respuesta
2. listByProyecto → camelCase: id, proyectoId, nombreArchivo, tipoMime, tamanioBytes, subidoPor, subidoAt, descripcion
3. Helper inline toDocumentRow(Map) — sin DTOs separados

## Checklist
- [ ] POST con descripcion → response la incluye
- [ ] GET list → descripcion por ítem (null si vacío)
- [ ] Keys camelCase consistentes`,
    priority: 1,
    labelIds: [L.be, L.bug],
    estimate: 2,
  });

  const evd5 = await createIssue({
    ...base,
    title: "BE: DocumentController — verificar multipart descripcion",
    description: `## Archivo
\`DocumentController.java\`

## Verificar
- @RequestParam(value = "descripcion", required = false)
- Respuestas delegadas a DocumentService (EVD-4)

## Checklist
- [ ] POST multipart acepta descripcion
- [ ] Sin regresión en download/delete`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 1,
  });

  const evd6 = await createIssue({
    ...base,
    title: "BE: .http — documents-evidence.http (upload/list/descripcion)",
    description: `## Archivo nuevo
\`src/test/java/com/odsProject/odsProject/http/documents-evidence.http\`

## Gate FE — todos 2xx antes de tocar frontend

## Casos
1. POST multipart con descripcion «Informe final»
2. GET list → descripcion presente
3. POST sin descripcion → null OK
4. Download + delete

## Checklist
- [ ] Archivo .http creado
- [ ] Resultados en comentario Linear`,
    priority: 1,
    labelIds: [L.be, L.test],
    estimate: 1,
  });

  const evd7 = await createIssue({
    ...base,
    title: "FE: documentService.js — alinear con API probada (.http)",
    description: `## Archivo
\`src/services/documentService.js\`

## Pre-requisito
EVD-6 Done — API verificada con .http

## Cambios
- Consumir camelCase del BE
- upload: enviar descripcion (trim; omitir si vacío)
- listByProject: mapear descripcion

## Checklist
- [ ] upload/list funcionan contra BE corriendo
- [ ] descripcion en response`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 1,
  });

  const evd8 = await createIssue({
    ...base,
    title: "FE: useDocuments.js — hook tras documentService",
    description: `## Archivo
\`src/hooks/useDocuments.js\`

## Cambios
- upload(file, usuarioId, descripcion) → documentService
- reload tras éxito
- Errores propagados al componente

## Checklist
- [ ] Hook no toca axios directo — usa documentService
- [ ] descripcion fluye al upload`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 1,
  });

  const evd9 = await createIssue({
    ...base,
    title: "FE: EvidenceSection — copy usted, pasos cronológicos, selector custom",
    description: `## Archivos
- \`EvidenceSection.jsx\`
- \`EvidenceSection.css\`

## Copy (español formal, usted — sin spanglish)
| Actual | Propuesto |
| Subí… | Pasos numerados 1–5 |
| Elegí un archivo | Seleccione un archivo primero |
| Choose file | Botón «Seleccionar archivo» |
| Descargalos | Descárguelos |

## UI
1. input file oculto + botón custom
2. Nombre archivo visible (paso 2)
3. Descripción opcional + display en lista
4. Mensaje éxito: «Documento subido correctamente»

## Checklist
- [ ] Pasos cronológicos <ol>
- [ ] Descripción visible en lista
- [ ] Cero inglés visible`,
    priority: 1,
    labelIds: [L.fe, L.ux],
    estimate: 2,
  });

  const evd10 = await createIssue({
    ...base,
    title: "FE: npm run dev — QA gestor + evaluador end-to-end",
    description: `## Comando (paso 4 comandosDelProyecto.txt)
\`\`\`bash
cd 2.frontend/odsProject
npm run dev
\`\`\`

## Pre-requisito
Backend corriendo: \`mvn spring-boot:run\`

## Casos
- [ ] Gestor → subir PDF con descripción → visible en lista
- [ ] Evaluador → descargar documento
- [ ] Copy solo español usted
- [ ] npm run build exit 0`,
    priority: 1,
    labelIds: [L.fe, L.test],
    estimate: 1,
  });

  console.log("\n🔗 Dependencias (cadena estricta — sin paralelismo)...");
  await addBlocksRelation(evd2, evd1);
  await addBlocksRelation(evd3, evd2);
  await addBlocksRelation(evd4, evd3);
  await addBlocksRelation(evd5, evd4);
  await addBlocksRelation(evd6, evd5);
  await addBlocksRelation(evd7, evd6);
  await addBlocksRelation(evd8, evd7);
  await addBlocksRelation(evd9, evd8);
  await addBlocksRelation(evd10, evd9);

  const all = [evd1, evd2, evd3, evd4, evd5, evd6, evd7, evd8, evd9, evd10];
  console.log("\n" + "━".repeat(58));
  console.log("🎉  Sprint 7 creado en Linear!\n");
  console.log(`  📝 Issues : ${all.length}`);
  console.log(`  📊 Puntos : 12`);
  console.log(`  🔗 Deps   : 9 (cadena estricta)`);
  console.log("\n  Orden obligatorio:");
  all.forEach((i, n) => console.log(`  ${n + 1}. ${i.identifier}  ${i.title.slice(0, 50)}`));
  console.log("\n  Pipeline: DB → drop/setup/load → spring-boot:run → repo→svc→ctrl→.http → FE");
  console.log("\n  Siguiente paso:");
  console.log("    node scripts/sprint_evidence_section.mjs next\n");
}

// ─── Comando: status ─────────────────────────────────────────────────────────

async function cmdStatus() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic) {
    console.log(`\n⚠️  Epic "${EPIC_NAME}" no encontrado. Ejecute: create\n`);
    return;
  }

  const states = await getWorkflowStates(team.id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));

  const byState = {};
  let pointsDone = 0;
  let pointsTotal = 0;

  for (const i of issues) {
    const st = stateMap[i.stateId]?.name || "?";
    byState[st] = (byState[st] || 0) + 1;
    const est = i.estimate || 0;
    pointsTotal += est;
    if (stateMap[i.stateId]?.type === "completed") pointsDone += est;
  }

  let checklistDone = 0;
  let checklistTotal = 0;
  for (const i of issues) {
    const c = checklistSummary(i.description);
    checklistDone += c.done;
    checklistTotal += c.total;
  }
  if (checklistTotal) {
    console.log(`   Checklist: ${checklistDone}/${checklistTotal} ítems marcados en descripciones`);
  }

  console.log(`\n📊 Sprint: ${SPRINT_NAME}`);
  console.log(`   Epic: ${EPIC_NAME}`);
  console.log(`   Issues: ${issues.length} · Puntos: ${pointsDone}/${pointsTotal}\n`);

  for (const [st, count] of Object.entries(byState).sort()) {
    console.log(`   ${st.padEnd(14)} ${count}`);
  }

  const doneType = new Set(["completed", "canceled"]);
  const pending = issues.filter((i) => !doneType.has(stateMap[i.stateId]?.type));
  if (pending.length) {
    console.log("\n  Pendientes:");
    for (const i of pending.sort((a, b) => a.number - b.number)) {
      const st = stateMap[i.stateId]?.name || "?";
      console.log(`    ${i.identifier}  [${st}]  ${i.title.slice(0, 50)}`);
    }
  }
  console.log("");
}

// ─── Comando: list ───────────────────────────────────────────────────────────

async function cmdList() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. Ejecute: create\n");
    return;
  }
  const states = await getWorkflowStates(team.id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));

  console.log(`\n📋 ${EPIC_NAME} (${issues.length} issues)\n`);
  for (const i of issues.sort((a, b) => a.number - b.number)) {
    const st = stateMap[i.stateId]?.name || "?";
    const pts = i.estimate ? `${i.estimate}pt` : "—";
    console.log(`  ${i.identifier}  [${st.padEnd(12)}] ${pts.padStart(3)}  ${i.title}`);
  }
  console.log("");
}

// ─── Comando: next ───────────────────────────────────────────────────────────

async function cmdNext() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. Ejecute: create\n");
    return;
  }

  const states = await getWorkflowStates(team.id);
  const stateMap = Object.fromEntries(states.map((s) => [s.id, s]));
  const doneType = new Set(["completed", "canceled"]);

  const doneIds = new Set(
    issues.filter((i) => doneType.has(stateMap[i.stateId]?.type)).map((i) => i.id)
  );

  const relations = [];
  for (const i of issues) {
    const rels = await i.relations();
    relations.push(...rels.nodes);
  }

  const blockedBy = new Map();
  for (const rel of relations) {
    if (rel.type === "blocks") {
      const blocked = rel.relatedIssue?.id || rel.relatedIssueId;
      const blocker = rel.issue?.id || rel.issueId;
      if (blocked && blocker) {
        if (!blockedBy.has(blocked)) blockedBy.set(blocked, []);
        blockedBy.get(blocked).push(blocker);
      }
    }
  }

  const available = issues
    .filter((i) => !doneType.has(stateMap[i.stateId]?.type))
    .filter((i) => {
      const blockers = blockedBy.get(i.id) || [];
      return blockers.every((bId) => doneIds.has(bId));
    })
    .sort((a, b) => a.number - b.number);

  if (!available.length) {
    console.log("\n✅ No hay issues desbloqueados pendientes (¿sprint completo?).\n");
    return;
  }

  console.log("\n⏭️  Próximo issue disponible (cadena estricta — solo uno):\n");
  const i = available[0];
  const st = stateMap[i.stateId]?.name || "?";
  const labels = await i.labels();
  const roles = labels.nodes.filter((l) => l.name.startsWith("role:")).map((l) => l.name);
  console.log(`  ${i.identifier}  [${st}]  ${roles.join(", ") || "sin rol"}`);
  console.log(`    ${i.title}\n`);
  if (available.length > 1) {
    console.log(`  (${available.length - 1} más en cola — completar ${i.identifier} primero)\n`);
  }
}

// ─── Comando: show ───────────────────────────────────────────────────────────

async function cmdShow(identifier) {
  if (!identifier) {
    console.error("Uso: show ODS-110");
    process.exit(1);
  }
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  const state = await issue.state;
  const labels = await issue.labels();
  const rels = await issue.relations();

  console.log(`\n📄 ${issue.identifier}: ${issue.title}`);
  console.log(`   Estado: ${state?.name || "?"}`);
  console.log(`   Puntos: ${issue.estimate ?? "—"}`);
  console.log(`   Labels: ${labels.nodes.map((l) => l.name).join(", ") || "—"}`);

  if (rels.nodes.length) {
    console.log("   Relaciones:");
    for (const r of rels.nodes) {
      console.log(`     ${r.type}: ${r.relatedIssue?.identifier || r.relatedIssueId}`);
    }
  }

  console.log("\n--- Descripción ---\n");
  console.log(issue.description || "(sin descripción)");
  console.log("");
}

// ─── Comando: checklist ──────────────────────────────────────────────────────

async function cmdChecklist(identifier, spec) {
  if (!identifier || !spec) {
    console.error("Uso: checklist ODS-110 all|1,2,3");
    process.exit(1);
  }
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);
  await updateIssueChecklist(issue, spec);
  issue = await findIssueByIdentifier(team, identifier);
  printChecklistStatus(issue.identifier, issue.description);
}

// ─── Comando: state ──────────────────────────────────────────────────────────

async function cmdState(identifier, stateName, flags = []) {
  if (!identifier || !stateName) {
    console.error('Uso: state ODS-110 "In Progress"');
    console.error('     state ODS-110 Done [--check-all]');
    process.exit(1);
  }
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);

  if (flags.includes("--check-all")) {
    await updateIssueChecklist(issue, "all");
    issue = await findIssueByIdentifier(team, identifier);
  }

  if (stateName.toLowerCase() === "done") {
    const check = await requireChecklistComplete(issue);
    if (!check.ok) {
      console.error(`\n❌ ${identifier}: checklist incompleto (${check.done}/${check.total})\n`);
      check.unchecked.forEach((l) => console.error(l));
      console.error(`\n  checklist ${identifier} all`);
      console.error(`  state ${identifier} Done --check-all\n`);
      process.exit(1);
    }
  }

  const state = await setIssueState(issue, stateName);
  console.log(`\n✅ ${identifier} → ${state.name}\n`);
}

// ─── Comando: comment ────────────────────────────────────────────────────────

async function cmdComment(identifier, body) {
  if (!identifier || !body) {
    console.error('Uso: comment ODS-110 "mensaje"');
    process.exit(1);
  }
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  await addIssueComment(issue, body);
  console.log(`\n💬 ${identifier}: nota publicada (checklist en descripción del issue)\n`);
}

// ─── Comando: cleanup ────────────────────────────────────────────────────────

async function cmdCleanup() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic || !issues.length) {
    console.log(`\n✅ Epic "${EPIC_NAME}" vacío o inexistente.\n`);
    return;
  }
  console.log(`\n🗑️  Eliminando ${issues.length} issues de "${EPIC_NAME}"...\n`);
  for (const i of issues) {
    await linear.deleteIssue(i.id);
    console.log(`   ✓ ${i.identifier} eliminado`);
  }
  console.log(`\n✅ ${issues.length} issues eliminados.\n`);
}

// ─── Comando: help ───────────────────────────────────────────────────────────

function cmdHelp() {
  console.log(`
🔷 sprint_evidence_section.mjs — Sprint 7 · EvidenceSection

Pipeline: DB → drop/setup/load → spring-boot:run → repo→svc→ctrl→.http → documentService → hook → EvidenceSection → npm run dev

Comandos:
  create                    Crea epic, sprint e issues EVD-1…10 (12 pts, cadena estricta)
  status                    Resumen de progreso del epic
  list                      Lista todos los issues con estado
  next                      UN solo issue desbloqueado (multiagente secuencial)
  show ODS-110              Detalle de un issue
  checklist ODS-110 all     Marcar checklist en descripción del issue
  state ODS-110 "Done"      Cerrar (exige checklist completo)
  state ODS-110 Done --check-all  Marcar todo el checklist y cerrar
  comment ODS-110 "texto"   Nota técnica opcional
  cleanup                   Eliminar todos los issues del epic
  help                      Esta ayuda

Regla multiagente: FE no arranca hasta EVD-6 (.http) Done. Sin paralelismo entre capas.

Flujo por issue:
  1. next → trabajar
  2. checklist ODS-N 1,2,3  (marcar ítems en descripción)
  3. comment ODS-N "…"      (opcional — detalle técnico)
  4. state ODS-N Done       (bloqueado si checklist incompleto)
  5. next → siguiente agente
`);
}

// ─── Main ────────────────────────────────────────────────────────────────────

const [cmd, ...rest] = process.argv.slice(2);

const handlers = {
  create: cmdCreate,
  status: cmdStatus,
  list: cmdList,
  next: cmdNext,
  show: () => cmdShow(rest[0]),
  checklist: () => cmdChecklist(rest[0], rest[1]),
  state: () => {
    const flags = rest.filter((a) => a.startsWith("--"));
    const positional = rest.filter((a) => !a.startsWith("--"));
    cmdState(positional[0], positional.slice(1).join(" "), flags);
  },
  comment: () => cmdComment(rest[0], rest.slice(1).filter((a) => !a.startsWith("--")).join(" ")),
  cleanup: cmdCleanup,
  help: cmdHelp,
};

if (!cmd || cmd === "help" || cmd === "--help" || cmd === "-h") {
  cmdHelp();
  process.exit(0);
}

const handler = handlers[cmd];
if (!handler) {
  console.error(`Comando desconocido: ${cmd}\n`);
  cmdHelp();
  process.exit(1);
}

handler().catch((e) => {
  console.error("\n❌", e.message);
  process.exit(1);
});
