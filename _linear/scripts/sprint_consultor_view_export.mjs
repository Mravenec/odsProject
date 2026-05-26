#!/usr/bin/env node
/**
 * Sprint Consultor — Vista global + Exportación Excel
 * Equipo: linear_ods · Orquestación multiagente (cadena + ramas FE paralelas)
 *
 * Comandos:
 *   create              Crea epic, sprint, labels e issues CV-1…9
 *   status              Resumen del sprint (estados, puntos, bloqueados)
 *   list                Lista issues del sprint con estado
 *   next                Issues desbloqueados (puede listar 2 en paralelo: CV-6/CV-7)
 *   show ODS-N          Detalle de un issue
 *   checklist ODS-N all|1,2,3  Marca checklist en descripción
 *   state ODS-N Done    Cambia estado (Done exige checklist completo)
 *   comment ODS-N "…"   Nota técnica opcional
 *   cleanup             Elimina issues del epic Consultor — Vista y Exportación
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

const EPIC_NAME = "Consultor — Vista y Exportación";
const SPRINT_NAME = "Sprint Consultor — Vista + Excel resumen";
const EPIC_DESC =
  "Rol consultor: gating 403 BE, Excel 3 hojas, botones descarga FE, UX read-only, mocks QA. DB → BE → .http → FE.";

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

const DONE_STATE_TYPES = new Set(["completed", "canceled"]);

/** Linear SDK expone issue.state como Promise; stateId suele venir undefined. */
async function resolveIssueStates(issues) {
  return Promise.all(issues.map(async (issue) => ({ issue, state: await issue.state })));
}

/** issue/relatedIssue en relaciones son Promises — hay que await antes de leer .id */
async function collectBlockRelations(issues) {
  const relations = [];
  for (const i of issues) {
    const rels = await i.relations();
    for (const rel of rels.nodes) {
      if (rel.type !== "blocks") continue;
      const blocker = await rel.issue;
      const blocked = await rel.relatedIssue;
      if (blocker?.id && blocked?.id) {
        relations.push({ blockerId: blocker.id, blockedId: blocked.id });
      }
    }
  }
  return relations;
}

async function getUnblockedIssues(issues) {
  const enriched = await resolveIssueStates(issues);
  const doneIds = new Set(
    enriched.filter(({ state }) => DONE_STATE_TYPES.has(state?.type)).map(({ issue }) => issue.id)
  );

  const blockedBy = new Map();
  for (const { blockerId, blockedId } of await collectBlockRelations(issues)) {
    if (!blockedBy.has(blockedId)) blockedBy.set(blockedId, []);
    blockedBy.get(blockedId).push(blockerId);
  }

  return enriched
    .filter(({ state }) => !DONE_STATE_TYPES.has(state?.type))
    .filter(({ issue }) => {
      const blockers = blockedBy.get(issue.id) || [];
      return blockers.every((bId) => doneIds.has(bId));
    })
    .map(({ issue }) => issue)
    .sort((a, b) => a.number - b.number);
}

async function cmdCreate() {
  console.log("\n🔷  Sprint Consultor — Vista + Exportación Excel");
  console.log("━".repeat(58));

  const team = await getTeam();
  const teamId = team.id;
  console.log(`\n✅ Equipo: ${team.name} (${team.key})`);

  console.log("\n🔄 Estados...");
  const ST = {
    backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8"),
    todo: await getOrCreateState(teamId, "Todo", "unstarted", "#A78BFA"),
    inprog: await getOrCreateState(teamId, "In Progress", "started", "#F59E0B"),
    testing: await getOrCreateState(teamId, "Testing", "started", "#8B5CF6"),
    review: await getOrCreateState(teamId, "In Review", "started", "#3B82F6"),
    done: await getOrCreateState(teamId, "Done", "completed", "#22C55E"),
  };

  console.log("\n🏷️  Labels...");
  const L = {
    db: await getOrCreateLabel(teamId, "role:database", "#EAB308"),
    be: await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
    fe: await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
    orch: await getOrCreateLabel(teamId, "role:orchestrator", "#EF4444"),
    feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
    test: await getOrCreateLabel(teamId, "type:test", "#8B5CF6"),
  };

  const epicId = await getOrCreateEpic(teamId, EPIC_NAME, EPIC_DESC, "#0EA5E9");

  const NOW = new Date();
  const WEEK = 7 * 24 * 60 * 60 * 1000;
  const sprintId = await getOrCreateSprint(
    teamId,
    SPRINT_NAME,
    NOW,
    new Date(NOW.getTime() + 2 * WEEK)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };

  console.log("\n📝 Issues...");

  const cv1 = await createIssue({
    ...base,
    title: "DB · Mock consultor + proyecto completado con auditoría",
    description: `## Objetivo
Append mocks para QA del rol consultor y un proyecto \`completado\` con stamping de auditoría.

## Archivo
\`0.database/propuesta_actual/21. ods_mocks.sql\`

## Checklist
- [ ] Append a 0.database/propuesta_actual/21. ods_mocks.sql
- [ ] Usuario: consultor@ods.local · password Consultor2026! · rol_id=3 · sede_id válida
- [ ] Proyecto "Proyecto QA Consultor" con usuario_id=gestor, estado='completado'
- [ ] Stamping auditoría: auditado_por (evaluador), auditado_en, observaciones_cierre
- [ ] 1+ vínculo en proyecto_ods (es_primario=TRUE) + mediciones en ods0X
- [ ] 2 filas en proyecto_documentos (1 PDF, 1 XLSX) con contenido pequeño
- [ ] Pipeline local: drop_db.py → setup_db.py → load_mocks.py sin error
- [ ] Verificar SELECT * FROM ods_master.vista_resumen_proyectos_ods WHERE estado='completado'
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE security (CV-2) — señal de inicio`,
    priority: 1,
    labelIds: [L.db, L.feat],
    estimate: 2,
  });

  const cv2 = await createIssue({
    ...base,
    title: "BE · RoleAuthorizationFilter (JWT → 403 en POST/PUT/DELETE para consultor)",
    description: `## Archivos
- \`security/RoleAuthorizationFilter.java\` (nuevo)
- \`config/SecurityConfig.java\`

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que agente CV-1 marque este ítem (mock cargado)
- [ ] Crear src/main/java/com/odsProject/odsProject/security/RoleAuthorizationFilter.java
- [ ] Filtro lee Authorization: Bearer <jwt>, decodifica con jjwt, extrae claim 'rol'
- [ ] Lista blanca: GET *, POST /api/login/**, OPTIONS — passthrough
- [ ] Si rol=consultor y método ∈ {POST, PUT, PATCH, DELETE} → 403 + JSON {error:"ROL_CONSULTOR_READONLY"}
- [ ] SecurityConfig: addFilterBefore(roleFilter, UsernamePasswordAuthenticationFilter.class)
- [ ] Mantener anyRequest().permitAll() (auth real sigue siendo el JWT custom)
- [ ] mvn compile sin warnings críticos · mvn spring-boot:run levanta OK
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE export (CV-3) — señal de inicio`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 3,
  });

  const cv3 = await createIssue({
    ...base,
    title: "BE · ExportService enriquecido — 3 hojas (General+Auditor / Indicadores / Evidencias)",
    description: `## Archivos
- \`service/ExportService.java\`
- \`repository/DocumentRepository.java\` (findByProyectoId si falta)

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que agente CV-2 marque este ítem (filtro funcionando)
- [ ] Mantener IllegalStateException 409 si estado != completado (ya existe)
- [ ] Hoja 1 "General": ID, Nombre, Descripción, Fecha inicio/fin, Responsable, Meta general, Sede, ODS vinculados (CSV), ODS primario, Estado, Auditor, Fecha auditoría, Observaciones
- [ ] Hoja 2 "Indicadores": Código, Indicador, Valor actual, Meta, Unidad, %, Estado
- [ ] Hoja 3 "Evidencias": Nombre archivo, Tipo MIME, Tamaño KB, Subido por (full_name), Fecha
- [ ] Reusar IMasterProjectRepository.findById + linkedOds; DocumentRepository.findByProyectoId
- [ ] Estilos: header bold, autoSizeColumn por hoja; freeze pane fila 1
- [ ] Test manual: GET /api/export/proyecto/{id_completado} → xlsx con 3 hojas
- [ ] **Handoff:** marcar checklist ítem 1 del issue BE .http (CV-4) — señal de inicio`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 3,
  });

  const cv4 = await createIssue({
    ...base,
    title: "BE · postman/consultor_flow.http (200/200/200/409/403/403/403)",
    description: `## Archivo
\`1.backend/odsProject/postman/consultor_flow.http\`

## Gate FE — frontend no empieza antes de este issue Done

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que agente CV-3 marque este ítem (Excel listo)
- [ ] Crear 1.backend/odsProject/postman/consultor_flow.http
- [ ] @login_consultor: POST /api/login/login {consultor@ods.local} → 200 + token
- [ ] GET /api/proyectos (consultor token) → 200 con array
- [ ] GET /api/export/proyecto/{id_completado} → 200 + content-type spreadsheetml
- [ ] GET /api/export/proyecto/{id_activo} → 409
- [ ] POST /api/proyectos (consultor token) → 403 {error:"ROL_CONSULTOR_READONLY"}
- [ ] PUT /api/proyectos/{id} (consultor token) → 403
- [ ] DELETE /api/proyectos/{id} (consultor token) → 403
- [ ] Todos los 7 escenarios verificados local
- [ ] **Handoff:** marcar checklist ítem 1 del issue FE service (CV-5) — señal de inicio`,
    priority: 1,
    labelIds: [L.be, L.test],
    estimate: 1,
  });

  const cv5 = await createIssue({
    ...base,
    title: "FE · exportService.downloadProjectFullReport + manejo 409/403",
    description: `## Archivo
\`2.frontend/odsProject/src/services/exportService.js\`

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que agente CV-4 marque este ítem (.http verde)
- [ ] Extender exportService.js con downloadProjectFullReport(id)
- [ ] Alias semántico que llama al mismo /api/export/proyecto/{id} (responseType:blob)
- [ ] Capturar 409 → return {success:false, error:'Proyecto aún no finalizado', code:409}
- [ ] Capturar 403 → return {success:false, error:'Sin permiso (rol consultor read-only)', code:403}
- [ ] Filename Content-Disposition; fallback "proyecto-{id}-resumen-final.xlsx"
- [ ] npm run dev sin errores en consola
- [ ] **Handoff doble:** marcar CV-6 ítem 1 y CV-7 ítem 1 (ramas paralelas)`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 2,
  });

  const cv6 = await createIssue({
    ...base,
    title: 'FE · Botón "Descargar Excel" en ProjectResultsPage (solo si completado)',
    description: `## Archivo
\`src/pages/ProjectResultsPage/ProjectResultsPage.jsx\`

## Paralelo con CV-7 — mismo agente FE, archivos distintos

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que agente CV-5 marque este ítem (service listo)
- [ ] Importar exportService + iconos Download de lucide-react
- [ ] Render condicional: project?.status === 'completado' && perms.canDownloadEvidence
- [ ] Botón verde en hero; label "Descargar Excel resumen"
- [ ] onClick async → downloadProjectFullReport(id); toast/alertModal según success/error
- [ ] Estado disabled mientras descarga (spinner inline)
- [ ] QA visual: proyecto completado muestra botón; activo no
- [ ] **Handoff:** marcar CV-8 checklist ítem 1 cuando termine (junto con CV-7)`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 2,
  });

  const cv7 = await createIssue({
    ...base,
    title: "FE · Botón Descargar en card de ProjectListPage (deshabilitado si no completado)",
    description: `## Archivo
\`src/pages/ProjectListPage/ProjectListPage.jsx\`

## Paralelo con CV-6 — mismo agente FE, archivos distintos

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que agente CV-5 marque este ítem (service listo)
- [ ] Importar exportService + iconos FileDown
- [ ] En .card-footer agregar btn-card-action secundario "Excel" (icono FileDown)
- [ ] disabled = project.status !== 'completado'; title="Disponible al finalizar el proyecto"
- [ ] onClick (e) => { e.stopPropagation(); downloadProjectFullReport(project.id) }
- [ ] Visibilidad: solo si perms.canDownloadEvidence
- [ ] QA visual: activos → btn opaco; completados → habilitado
- [ ] **Handoff:** marcar CV-8 checklist ítem 1 cuando termine (junto con CV-6)`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 2,
  });

  const cv8 = await createIssue({
    ...base,
    title: "FE · Read-only consultor + QA cross-page",
    description: `## Archivos
- \`src/components/projects/EvidenceSection.jsx\`
- \`src/pages/ProjectResultsPage/ProjectResultsPage.jsx\`

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que CV-6 Y CV-7 marquen este ítem (ambos botones cableados)
- [ ] EvidenceSection.jsx: wrap input upload con perms.canUploadEvidenceFor(project)
- [ ] EvidenceSection.jsx: wrap btn eliminar con perms.canUploadEvidenceFor
- [ ] ProjectResultsPage.jsx: wrap transiciones de estado con perms.canEditProject
- [ ] Verificar ProtectedRoute bloquea /projects/create, /admin/* para consultor
- [ ] npm run build exit 0 sin warnings nuevos
- [ ] QA manual login consultor@ods.local: dashboard, lista, forbidden create, Excel solo en completado, sin upload/delete/edit
- [ ] curl POST /api/proyectos con token consultor → 403
- [ ] **Handoff:** marcar CV-9 checklist ítem 1 — orchestrator puede cerrar epic`,
    priority: 1,
    labelIds: [L.fe, L.test],
    estimate: 2,
  });

  const cv9 = await createIssue({
    ...base,
    title: "Orchestrator · resumen_sprint_consultor_view_export.html + epic Completed",
    description: `## Objetivo
Cierre Fase 5 del pipeline _linear.

## Checklist
- [ ] ⏸ **Gate externo:** NO iniciar hasta que CV-8 marque este ítem (QA verde)
- [ ] Verificar node scripts/sprint_consultor_view_export.mjs status → todos Done, checklists 100%
- [ ] Copiar _plantilla_ods.html → _linear/plans/resumen_sprint_consultor_view_export.html
- [ ] Cambiar al.pending → al.ok, chips y badges a Done
- [ ] Sección "Archivos modificados" con paths reales
- [ ] Sección "Comandos usados" (drop_db, setup_db, load_mocks, mvn, npm run dev/build)
- [ ] update_project del epic "${EPIC_NAME}" → state=completed
- [ ] Comentar en el epic con ruta al resumen HTML`,
    priority: 1,
    labelIds: [L.orch, L.feat],
    estimate: 1,
  });

  console.log("\n🔗 Dependencias...");
  await addBlocksRelation(cv2, cv1);
  await addBlocksRelation(cv3, cv2);
  await addBlocksRelation(cv4, cv3);
  await addBlocksRelation(cv5, cv4);
  await addBlocksRelation(cv6, cv5);
  await addBlocksRelation(cv7, cv5);
  await addBlocksRelation(cv8, cv6);
  await addBlocksRelation(cv8, cv7);
  await addBlocksRelation(cv9, cv8);

  const all = [cv1, cv2, cv3, cv4, cv5, cv6, cv7, cv8, cv9];
  const points = all.reduce((s, i) => s + (i.estimate || 0), 0);

  console.log("\n" + "━".repeat(58));
  console.log("🎉  Sprint Consultor creado en Linear!\n");
  console.log(`  📝 Issues : ${all.length}`);
  console.log(`  📊 Puntos : ${points}`);
  console.log(`  🔗 Deps   : cadena + CV-6 ∥ CV-7 → CV-8`);
  console.log("\n  Orden:");
  all.forEach((i, n) => console.log(`  ${n + 1}. ${i.identifier}  ${i.title.slice(0, 55)}`));
  console.log("\n  Handoff cross-ticket (upstream marca ítem 1 del downstream):");
  console.log("    node scripts/linear-update-state.mjs ODS-N --checklist 1   # en ticket downstream");
  console.log("\n  Siguiente paso:");
  console.log("    node scripts/sprint_consultor_view_export.mjs next\n");
}

async function cmdStatus() {
  const team = await getTeam();
  const { epic, issues } = await getEpicIssues(team);
  if (!epic) {
    console.log(`\n⚠️  Epic "${EPIC_NAME}" no encontrado. Ejecute: create\n`);
    return;
  }

  const enriched = await resolveIssueStates(issues);

  const byState = {};
  let pointsDone = 0;
  let pointsTotal = 0;

  for (const { issue: i, state: stObj } of enriched) {
    const st = stObj?.name || "?";
    byState[st] = (byState[st] || 0) + 1;
    const est = i.estimate || 0;
    pointsTotal += est;
    if (stObj?.type === "completed") pointsDone += est;
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

  const pending = enriched.filter(({ state }) => !DONE_STATE_TYPES.has(state?.type));
  if (pending.length) {
    console.log("\n  Pendientes:");
    for (const { issue: i, state: stObj } of pending.sort(
      (a, b) => a.issue.number - b.issue.number
    )) {
      const st = stObj?.name || "?";
      const c = checklistSummary(i.description);
      console.log(`    ${i.identifier}  [${st}]  ${c.done}/${c.total} chk  ${i.title.slice(0, 45)}`);
    }
  }
  console.log("");
}

async function cmdList() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. Ejecute: create\n");
    return;
  }
  const enriched = await resolveIssueStates(issues);

  console.log(`\n📋 ${EPIC_NAME} (${issues.length} issues)\n`);
  for (const { issue: i, state: stObj } of enriched.sort(
    (a, b) => a.issue.number - b.issue.number
  )) {
    const st = stObj?.name || "?";
    const pts = i.estimate ? `${i.estimate}pt` : "—";
    const c = checklistSummary(i.description);
    console.log(
      `  ${i.identifier}  [${st.padEnd(12)}] ${pts.padStart(3)}  chk ${c.done}/${c.total}  ${i.title}`
    );
  }
  console.log("");
}

async function cmdNext() {
  const team = await getTeam();
  const { issues } = await getEpicIssues(team);
  if (!issues.length) {
    console.log("\n⚠️  Sin issues. Ejecute: create\n");
    return;
  }

  const available = await getUnblockedIssues(issues);

  if (!available.length) {
    console.log("\n✅ No hay issues desbloqueados pendientes (¿sprint completo?).\n");
    return;
  }

  const parallel = available.length > 1;
  console.log(
    `\n⏭️  ${available.length} issue(s) disponible(s)${parallel ? " — ramas paralelas permitidas" : ""}:\n`
  );

  for (const i of available) {
    const stObj = await i.state;
    const st = stObj?.name || "?";
    const labels = await i.labels();
    const roles = labels.nodes.filter((l) => l.name.startsWith("role:")).map((l) => l.name);
    const c = checklistSummary(i.description);
    console.log(`  ${i.identifier}  [${st}]  ${roles.join(", ") || "sin rol"}  chk ${c.done}/${c.total}`);
    console.log(`    ${i.title}\n`);
  }
}

async function cmdShow(identifier) {
  if (!identifier) {
    console.error("Uso: show ODS-N");
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

  printChecklistStatus(issue.identifier, issue.description);
  console.log("--- Descripción ---\n");
  console.log(issue.description || "(sin descripción)");
  console.log("");
}

async function cmdChecklist(identifier, spec) {
  if (!identifier || !spec) {
    console.error("Uso: checklist ODS-N all|1,2,3");
    process.exit(1);
  }
  const team = await getTeam();
  let issue = await findIssueByIdentifier(team, identifier);
  await updateIssueChecklist(issue, spec);
  issue = await findIssueByIdentifier(team, identifier);
  printChecklistStatus(issue.identifier, issue.description);
}

async function cmdState(identifier, stateName, flags = []) {
  if (!identifier || !stateName) {
    console.error('Uso: state ODS-N "In Progress"');
    console.error("     state ODS-N Testing");
    console.error("     state ODS-N Done [--check-all]");
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

async function cmdComment(identifier, body) {
  if (!identifier || !body) {
    console.error('Uso: comment ODS-N "mensaje"');
    process.exit(1);
  }
  const team = await getTeam();
  const issue = await findIssueByIdentifier(team, identifier);
  await addIssueComment(issue, body);
  console.log(`\n💬 ${identifier}: nota publicada\n`);
}

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

function cmdHelp() {
  console.log(`
🔷 sprint_consultor_view_export.mjs — Consultor vista + Excel

Pipeline: DB mocks → BE security → BE export → .http → FE service → (CV-6 ∥ CV-7) → QA read-only → resumen

Comandos:
  create                    Crea epic, sprint e issues (18 pts)
  status                    Resumen de progreso
  list                      Lista issues con checklist
  next                      Issues desbloqueados (CV-6 y CV-7 en paralelo tras CV-5)
  show ODS-N                Detalle + checklist
  checklist ODS-N 1,2,3     Marcar checklist en descripción
  state ODS-N "In Progress" Mover a Doing
  state ODS-N Testing       Pruebas locales (checklist completo)
  state ODS-N Done          Cerrar (exige checklist)
  comment ODS-N "texto"     Nota opcional
  cleanup                   Eliminar issues del epic
  help                      Esta ayuda

Handoff cross-ticket (agente upstream libera downstream):
  node scripts/linear-update-state.mjs ODS-N --checklist 1

Flujo por agente:
  1. next → claim / state "In Progress"
  2. checklist ODS-N 1,2,3… (Doing)
  3. state ODS-N Testing → pruebas locales
  4. handoff: --checklist 1 en ticket downstream
  5. state ODS-N Done → desbloquea blocks
`);
}

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
