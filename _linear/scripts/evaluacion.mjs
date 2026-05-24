#!/usr/bin/env node
/**
 * 🎯 Sprint 3 — Refactorizar "auditoría" → "evaluación"
 *
 * Pipeline completo con dependencias multi-agente:
 *   DB  →  JOOQ  →  BE (refactor)  →  BE (run + .http)  →  FE
 */
import { linear, LINEAR_TEAM_NAME } from "./linear-config.mjs";

async function getTeam() {
  const r = await linear.teams();
  const t = r.nodes.find(t =>
    t.name.toLowerCase() === LINEAR_TEAM_NAME.toLowerCase() ||
    t.key.toLowerCase()  === LINEAR_TEAM_NAME.toLowerCase()
  );
  if (!t) throw new Error(`Equipo "${LINEAR_TEAM_NAME}" no encontrado.`);
  return t;
}

async function state(teamId, name) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  return r.nodes.find(s => s.name.toLowerCase() === name.toLowerCase())?.id ?? null;
}

async function getOrCreateState(teamId, name, type, color) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find(s => s.name.toLowerCase() === name.toLowerCase());
  if (f) { console.log(`  ♻️  Estado: ${name}`); return f.id; }
  const res = await linear.createWorkflowState({ teamId, name, type, color });
  const s   = await res.workflowState;
  console.log(`  ✅ Estado: ${name}`);
  return s.id;
}

async function getOrCreateLabel(teamId, name, color) {
  const r = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find(l => l.name.toLowerCase() === name.toLowerCase());
  if (f) { console.log(`  ♻️  Label: ${name}`); return f.id; }
  const res = await linear.createIssueLabel({ teamId, name, color: color ?? "#6B7280" });
  const l   = await res.issueLabel;
  console.log(`  ✅ Label: ${name}`);
  return l.id;
}

async function getOrCreateEpic(teamId, name, description, color) {
  const r = await linear.projects({ filter: { name: { eq: name } } });
  if (r.nodes.length) { console.log(`  ♻️  Epic: ${name}`); return r.nodes[0].id; }
  const res = await linear.createProject({ teamIds: [teamId], name, description, color });
  const p   = await res.project;
  console.log(`  ✅ Epic: ${name}`);
  return p.id;
}

async function getOrCreateSprint(teamId, name, startsAt, endsAt) {
  const r = await linear.cycles({ filter: { team: { id: { eq: teamId } }, name: { eq: name } } });
  if (r.nodes.length) { console.log(`  ♻️  Sprint: ${name}`); return r.nodes[0].id; }
  const res = await linear.createCycle({ teamId, name, startsAt, endsAt });
  const c   = await res.cycle;
  console.log(`  ✅ Sprint: ${name}`);
  return c.id;
}

async function iss(params) {
  const res = await linear.createIssue(params);
  const i   = await res.issue;
  console.log(`    📌 ${i.identifier}: ${i.title}`);
  return i;
}

async function dep(child, parent, note) {
  // Linear: parent blocks child (child cannot start until parent is done)
  await linear.createIssueRelation({ issueId: parent.id, relatedIssueId: child.id, type: "blocks" });
  console.log(`     🔗 ${child.identifier} ← ${parent.identifier}  (${note})`);
}

const NOW  = new Date();
const WEEK = 7 * 24 * 60 * 60 * 1000;

async function main() {
  console.log("\n🔷  Sprint 3 — Refactorizar auditoría → evaluación");
  console.log("━".repeat(54));

  const team   = await getTeam();
  const teamId = team.id;
  console.log(`\n✅ Equipo: ${team.name} (${team.key})`);

  // Estados
  console.log("\n🔄 Estados...");
  const ST = {
    backlog: await getOrCreateState(teamId, "Backlog",          "backlog",   "#94A3B8"),
    ready:   await getOrCreateState(teamId, "Ready",            "unstarted", "#60A5FA"),
    todo:    await getOrCreateState(teamId, "Todo",             "unstarted", "#A78BFA"),
    inprog:  await getOrCreateState(teamId, "In Progress",      "started",   "#F59E0B"),
    review:  await getOrCreateState(teamId, "Code Review",      "started",   "#F97316"),
    qa:      await getOrCreateState(teamId, "Testing / QA",     "started",   "#EC4899"),
    staging: await getOrCreateState(teamId, "Ready for Deploy", "started",   "#10B981"),
    done:    await getOrCreateState(teamId, "Done",             "completed", "#22C55E"),
  };

  // Labels
  console.log("\n🏷️  Labels...");
  const L = {
    db:    await getOrCreateLabel(teamId, "role:database",  "#EAB308"),
    be:    await getOrCreateLabel(teamId, "role:backend",   "#22C55E"),
    fe:    await getOrCreateLabel(teamId, "role:frontend",  "#0EA5E9"),
    refac: await getOrCreateLabel(teamId, "type:refactor",  "#8B5CF6"),
    test:  await getOrCreateLabel(teamId, "type:test",      "#EC4899"),
  };

  // Epic
  console.log("\n🗂️  Epic...");
  const epicId = await getOrCreateEpic(
    teamId,
    "Evaluación",
    "Renombrar auditoría → evaluación en toda la plataforma: DB, backend, tests y frontend.",
    "#7C3AED"
  );

  // Sprint
  console.log("\n🔄 Sprint...");
  const sprintId = await getOrCreateSprint(
    teamId,
    "Sprint 3 — Evaluación (renombramiento)",
    new Date(NOW.getTime() + 4 * WEEK),
    new Date(NOW.getTime() + 6 * WEEK)
  );

  const base = {
    teamId,
    projectId: epicId,
    cycleId: sprintId,
    stateId: ST.backlog,
  };

  // ══════════════════════════════════════════════════════════════════
  //  CAPA 1 — BASE DE DATOS
  // ══════════════════════════════════════════════════════════════════
  console.log("\n\n  ┌─ CAPA 1: BASE DE DATOS ─────────────────────────────");

  const db1 = await iss({
    ...base,
    title: "DB: renombrar rol `auditor` → `evaluador` en login_system.sql",
    description: `## Contexto
La tabla \`roles\` en \`0.database/propuesta_actual/1. login_system.sql\` tiene:
\`\`\`sql
INSERT IGNORE INTO roles (nombre, descripcion) VALUES
  ('auditor', 'Acceso a registros de auditoría de todos los ODS');
\`\`\`
Hay un mock de usuario en \`0.database/propuesta_actual/21. ods_mocks.sql\`:
\`\`\`sql
-- Sprint 14: usuario auditor (rol_id=4)
(5, 'auditor_general', 'auditor@ods.cr', '...', 'Luis Vargas Castro', 4, 2, TRUE, TRUE),
\`\`\`

## Cambios en archivos SQL

**\`1. login_system.sql\`** — cambiar la fila de roles:
\`\`\`sql
-- ANTES:
('auditor', 'Acceso a registros de auditoría de todos los ODS')

-- DESPUÉS:
('evaluador', 'Evaluador académico: revisa y cierra el proceso de evaluación de proyectos ODS')
\`\`\`

**\`21. ods_mocks.sql\`** — actualizar el comentario y el username del mock:
\`\`\`sql
-- ANTES:
-- Sprint 14: usuario auditor (rol_id=4)
(5, 'auditor_general', 'auditor@ods.cr', '...', 'Luis Vargas Castro', 4, 2, TRUE, TRUE),

-- DESPUÉS:
-- Sprint 3: usuario evaluador (rol_id=4)
(5, 'evaluador_general', 'evaluador@ods.cr', '...', 'Luis Vargas Castro', 4, 2, TRUE, TRUE),
\`\`\`

## Comandos a ejecutar (en orden)
\`\`\`bash
python 0.database/drop_db.py
python 0.database/setup_db.py
python 0.database/load_mocks.py
\`\`\`

## Verificación
\`\`\`sql
SELECT nombre, descripcion FROM roles;
-- Debe mostrar: evaluador | Evaluador académico...
SELECT username, email FROM usuarios WHERE rol_id = 4;
-- Debe mostrar: evaluador_general | evaluador@ods.cr
\`\`\`

## Checklist
- [ ] Editar \`1. login_system.sql\` — cambiar \`auditor\` → \`evaluador\`
- [ ] Editar \`21. ods_mocks.sql\` — actualizar usuario mock
- [ ] \`python 0.database/drop_db.py\`
- [ ] \`python 0.database/setup_db.py\`
- [ ] \`python 0.database/load_mocks.py\`
- [ ] Verificar con consulta SQL que \`roles\` tiene \`evaluador\``,
    priority: 1,
    labelIds: [L.db, L.refac],
    estimate: 1,
  });

  // ══════════════════════════════════════════════════════════════════
  //  CAPA 2 — JOOQ (regenerar POJOs)
  // ══════════════════════════════════════════════════════════════════
  console.log("\n  ├─ CAPA 2: JOOQ ──────────────────────────────────────");

  const jooq1 = await iss({
    ...base,
    title: "JOOQ: regenerar POJOs tras cambio en DB",
    description: `## Contexto
El cambio de datos en la tabla \`roles\` (auditor → evaluador) es un cambio de *datos*, no de *esquema*, por lo que JOOQ no genera código nuevo para la tabla \`roles\` en sí.

**Sin embargo**, el proceso de regeneración es obligatorio en el pipeline para:
1. Confirmar que la DB está en buen estado tras el drop/setup
2. Refrescar cualquier vista o tabla que pueda haber cambiado
3. Mantener la coherencia del ciclo de desarrollo del equipo

## Verificación previa
Confirmar que la DB está corriendo y accesible antes de lanzar JOOQ:
\`\`\`bash
mysql -u root -p -e "SELECT nombre FROM ods_login.roles WHERE nombre='evaluador';"
# Debe retornar: evaluador
\`\`\`

## Comando
\`\`\`bash
cd 1.backend/odsProject
mvn generate-sources -P jooq
\`\`\`
*(o el perfil que use el proyecto — revisar \`pom.xml\` sección \`<profiles>\`)*

## Qué revisar en los POJOs generados
- **NO deben cambiar** los POJOs de \`roles\` (solo tiene columnas id/nombre/descripcion — son datos, no estructura)
- **NO hay referencia** al string \`"auditor"\` en ningún archivo generado bajo \`database/jooq/\`
- Si hubiera algún cambio de vista que referencie el rol, aparecerá aquí

## Checklist
- [ ] Confirmar DB levantada y con rol \`evaluador\` (paso previo de DB completado)
- [ ] \`mvn generate-sources\` sin errores
- [ ] Confirmar que no hay archivos JOOQ modificados inesperadamente (\`git diff --name-only src/main/java/.../jooq/\`)
- [ ] Si hay cambios JOOQ inesperados → investigar antes de continuar`,
    priority: 1,
    labelIds: [L.be, L.refac],
    estimate: 1,
  });

  // ══════════════════════════════════════════════════════════════════
  //  CAPA 3 — BACKEND: refactor Java
  // ══════════════════════════════════════════════════════════════════
  console.log("\n  ├─ CAPA 3: BACKEND — Refactor Java ──────────────────");

  const be1 = await iss({
    ...base,
    title: "BE: renombrar paths y métodos en MasterProjectController — auditoria → evaluacion",
    description: `## Contexto (verificado en \`MasterProjectController.java\`)
Endpoints actuales que usan el término "auditoría":

\`\`\`java
@PostMapping("/{id}/enviar-revision")       // línea 140
public ResponseEntity enviarRevision(...)

@PostMapping("/{id}/cerrar-auditoria")      // línea 159
public ResponseEntity cerrarAuditoria(...)

@PostMapping("/{id}/rechazar-auditoria")    // línea 180
public ResponseEntity rechazarAuditoria(...)

@GetMapping("/audit/metrics")              // (projectService llama a este path)
public ResponseEntity getAuditMetrics(...)
\`\`\`

## Cambios en \`MasterProjectController.java\`
\`\`\`java
// ANTES → DESPUÉS (paths y nombres de método):
"/{id}/enviar-revision"    → "/{id}/enviar-evaluacion"
"/{id}/cerrar-auditoria"   → "/{id}/aprobar-evaluacion"
"/{id}/rechazar-auditoria" → "/{id}/rechazar-evaluacion"
"/audit/metrics"           → "/evaluacion/metrics"

enviarRevision(...)    → enviarEvaluacion(...)
cerrarAuditoria(...)   → aprobarEvaluacion(...)
rechazarAuditoria(...) → rechazarEvaluacion(...)
\`\`\`

> ⚠️ Agregar alias deprecados temporales para no romper el frontend hasta que se actualice en la misma PR:
\`\`\`java
@PostMapping({"/{id}/enviar-evaluacion", "/{id}/enviar-revision"})
\`\`\`

## Cambios en \`IMasterProjectController.java\`
- Renombrar las firmas de los 4 métodos para que coincidan

## Checklist
- [ ] Renombrar \`@PostMapping\` de \`enviar-revision\` → \`enviar-evaluacion\`
- [ ] Renombrar \`@PostMapping\` de \`cerrar-auditoria\` → \`aprobar-evaluacion\`
- [ ] Renombrar \`@PostMapping\` de \`rechazar-auditoria\` → \`rechazar-evaluacion\`
- [ ] Renombrar \`@GetMapping\` de \`audit/metrics\` → \`evaluacion/metrics\`
- [ ] Agregar aliases deprecados en cada mapping
- [ ] Renombrar métodos en \`IMasterProjectController.java\``,
    priority: 1,
    labelIds: [L.be, L.refac],
    estimate: 2,
  });

  const be2 = await iss({
    ...base,
    title: "BE: renombrar métodos en MasterProjectService — auditoria → evaluacion",
    description: `## Contexto (verificado en \`MasterProjectService.java\`)
Métodos actuales:
\`\`\`java
public Map<String,Object> enviarRevision(Integer id, Map<String,Object> body)   // Sprint 16
public Map<String,Object> cerrarAuditoria(Integer id, Map<String,Object> body)  // Sprint 17
public Map<String,Object> rechazarAuditoria(Integer id, Map<String,Object> body)// Sprint 17
\`\`\`

## Cambios en \`MasterProjectService.java\`
\`\`\`java
enviarRevision(...)    → enviarEvaluacion(...)
cerrarAuditoria(...)   → aprobarEvaluacion(...)
rechazarAuditoria(...) → rechazarEvaluacion(...)
\`\`\`

## Cambios en \`IMasterProjectService.java\`
- Mismas firmas renombradas en la interfaz

## Checklist
- [ ] Renombrar \`enviarRevision\` → \`enviarEvaluacion\` en service e interfaz
- [ ] Renombrar \`cerrarAuditoria\` → \`aprobarEvaluacion\` en service e interfaz
- [ ] Renombrar \`rechazarAuditoria\` → \`rechazarEvaluacion\` en service e interfaz
- [ ] Actualizar las llamadas en \`MasterProjectController\` para usar los nuevos nombres
- [ ] Verificar que el compilador no reporta errores (\`mvn compile\`)`,
    priority: 1,
    labelIds: [L.be, L.refac],
    estimate: 1,
  });

  const be3 = await iss({
    ...base,
    title: "BE: reemplazar string `\"auditor\"` → `\"evaluador\"` en MasterProjectService",
    description: `## Contexto (verificado en \`MasterProjectService.java\`)
Hay 4 apariciones del string \`"auditor"\` en lógica de negocio:

\`\`\`java
// Línea 511 — mapa de transiciones permitidas:
"activo",     java.util.Set.of("gestor", "admin", "auditor"),

// Línea 513:
"completado", java.util.Set.of("admin", "auditor"),

// Línea 609 — guard de cerrarAuditoria:
if (!(role.equals("admin") || role.equals("auditor"))) { throw ... }

// Línea 631 — guard de rechazarAuditoria:
if (!(role.equals("admin") || role.equals("auditor"))) { throw ... }
\`\`\`

## Cambios (find & replace en \`MasterProjectService.java\`)
\`\`\`java
// Todas las ocurrencias:
"auditor"  →  "evaluador"
\`\`\`

Esto implica reemplazar en:
- El mapa de transiciones de estado permitidas (líneas 511, 513)
- El guard de \`aprobarEvaluacion()\` (antes cerrarAuditoria, línea 609)
- El guard de \`rechazarEvaluacion()\` (antes rechazarAuditoria, línea 631)

## Checklist
- [ ] Reemplazar las 4 ocurrencias de \`"auditor"\` → \`"evaluador"\`
- [ ] \`mvn compile\` sin errores
- [ ] Confirmar que el login con usuario \`evaluador_general\` (del mock) sigue funcionando`,
    priority: 1,
    labelIds: [L.be, L.refac],
    estimate: 1,
  });

  // ══════════════════════════════════════════════════════════════════
  //  CAPA 4 — BACKEND: correr + .http
  // ══════════════════════════════════════════════════════════════════
  console.log("\n  ├─ CAPA 4: BACKEND — Run + .http ─────────────────────");

  const be4 = await iss({
    ...base,
    title: "BE: levantar Spring Boot y actualizar MasterProjectController.http",
    description: `## Contexto
Con el refactor del controller/service completado, se levanta la app y se actualiza el archivo \`.http\` de pruebas.

## Comando para levantar
\`\`\`bash
cd 1.backend/odsProject
mvn spring-boot:run
\`\`\`

## Archivo a actualizar
**\`src/test/java/com/odsProject/odsProject/http/MasterProjectController.http\`**

Agregar la nueva sección de evaluación al final del archivo:
\`\`\`http
### ─── Flujo de Evaluación ───────────────────────────────

@proyectoId = 1
@actorUserId = 5
@actorRole = evaluador

### Gestor envía proyecto a evaluación
POST {{baseUrl}}/api/projects/{{proyectoId}}/enviar-evaluacion
Content-Type: application/json

{
  "actorUserId": {{actorUserId}}
}

###

### Evaluador aprueba la evaluación
POST {{baseUrl}}/api/projects/{{proyectoId}}/aprobar-evaluacion
Content-Type: application/json

{
  "actorUserId": {{actorUserId}},
  "actorRole": "{{actorRole}}",
  "observaciones": "Indicadores completos y coherentes. Proyecto evaluado satisfactoriamente."
}

###

### Evaluador rechaza la evaluación
POST {{baseUrl}}/api/projects/{{proyectoId}}/rechazar-evaluacion
Content-Type: application/json

{
  "actorUserId": {{actorUserId}},
  "actorRole": "{{actorRole}}",
  "motivoRechazo": "Faltan documentos de respaldo para el indicador 3."
}

###

### Métricas de la cola de evaluación
GET {{baseUrl}}/api/projects/evaluacion/metrics
\`\`\`

## Checklist
- [ ] \`mvn spring-boot:run\` — app levanta sin errores
- [ ] GET \`/api/projects/evaluacion/metrics\` → 200 OK
- [ ] POST \`/enviar-evaluacion\` con proyecto en \`activo\` → 200, estado pasa a \`en_revision\`
- [ ] POST \`/aprobar-evaluacion\` con rol \`evaluador\` → 200, estado pasa a \`completado\`
- [ ] POST \`/rechazar-evaluacion\` con rol \`evaluador\` → 200, estado vuelve a \`activo\`
- [ ] Intentar POST \`/aprobar-evaluacion\` con rol \`gestor\` → 403 Forbidden
- [ ] Alias viejos (\`/enviar-revision\`) siguen respondiendo 200 (compatibilidad)`,
    priority: 1,
    labelIds: [L.be, L.test],
    estimate: 2,
  });

  // ══════════════════════════════════════════════════════════════════
  //  CAPA 5 — FRONTEND
  // ══════════════════════════════════════════════════════════════════
  console.log("\n  ├─ CAPA 5: FRONTEND ──────────────────────────────────");

  const fe1 = await iss({
    ...base,
    title: "FE: actualizar projectService.js — nuevos paths y rol evaluador",
    description: `## Contexto (verificado en \`src/services/projectService.js\`)
Paths actuales que apuntan a los endpoints viejos:

\`\`\`js
// Línea ~400:
const r = await api.post(\`/projects/\${projectId}/enviar-revision\`, { actorUserId });

// Línea ~413:
const r = await api.post(\`/projects/\${projectId}/cerrar-auditoria\`, {
  actorUserId, actorRole, observaciones });

// Línea ~429:
const r = await api.post(\`/projects/\${projectId}/rechazar-auditoria\`, {
  actorUserId, actorRole, motivoRechazo });

// Línea ~454:
// Backend: GET /api/projects/audit/metrics
const r = await api.get('/projects/audit/metrics');
\`\`\`

## Cambios en \`projectService.js\`
\`\`\`js
// Renombrar los 4 métodos y sus paths:
sendForReview(...)    → sendForEvaluation(...)    // path: /enviar-evaluacion
approveAudit(...)     → approveEvaluation(...)    // path: /aprobar-evaluacion
rejectAudit(...)      → rejectEvaluation(...)     // path: /rechazar-evaluacion
getAuditMetrics(...)  → getEvaluationMetrics(...) // path: /evaluacion/metrics
\`\`\`

## Cambios en \`usePermissions.js\`
\`\`\`js
// ROLE_MATRIX — renombrar entrada:
auditor: { ... }   →   evaluador: { ... }
// Todos los permisos internos permanecen igual
\`\`\`

## Checklist
- [ ] Renombrar los 4 métodos en \`projectService.js\`
- [ ] Actualizar los 4 paths de API
- [ ] Renombrar \`auditor\` → \`evaluador\` en \`usePermissions.js\` (ROLE_MATRIX)
- [ ] Buscar cualquier otro uso de \`sendForReview\`, \`approveAudit\`, \`rejectAudit\`, \`getAuditMetrics\` en el código y actualizarlos`,
    priority: 1,
    labelIds: [L.fe, L.refac],
    estimate: 1,
  });

  const fe2 = await iss({
    ...base,
    title: "FE: renombrar textos en AuditQueuePage → EvaluationQueuePage",
    description: `## Contexto (verificado en \`src/pages/AuditQueuePage/AuditQueuePage.jsx\`)
Textos actuales con "auditoría/auditor":

| Línea | Texto actual | Texto nuevo |
|-------|-------------|-------------|
| 15 | Sprint 19 — Cola de auditoría | Sprint 3 — Cola de evaluación |
| 19 | esperando al auditor | esperando al evaluador |
| 23 | GET /api/projects/audit/metrics | GET /api/projects/evaluacion/metrics |
| 79 | Pendientes de auditar | Pendientes de evaluación |
| 81 | Auditados | Evaluados |
| 84 | filas auditadas | filas evaluadas |
| 87 | navigate(\`/audit/\${p.id}\`) | navigate(\`/evaluacion/\${p.id}\`) |
| 99 | audit-queue-page | evaluation-queue-page |
| 114 | \`<h1>Cola de auditoría\</h1>\` | \`<h1>Cola de Evaluación\</h1>\` |
| 130 | En espera del auditor | En espera del evaluador |
| 141 | kpi-auditados | kpi-evaluados |
| 144 | Auditados este mes | Evaluados este mes |
| 160 | audit-filters | evaluation-filters |

## Cambios estructurales
- [ ] Renombrar carpeta: \`AuditQueuePage/\` → \`EvaluationQueuePage/\`
- [ ] Renombrar archivo: \`AuditQueuePage.jsx\` → \`EvaluationQueuePage.jsx\`
- [ ] Renombrar archivo: \`AuditQueuePage.css\` → \`EvaluationQueuePage.css\`
- [ ] Actualizar import en \`App.jsx\`:
  \`\`\`js
  // ANTES:
  import AuditQueuePage from './pages/AuditQueuePage/AuditQueuePage.jsx';
  // DESPUÉS:
  import EvaluationQueuePage from './pages/EvaluationQueuePage/EvaluationQueuePage.jsx';
  \`\`\`

## Checklist
- [ ] Renombrar carpeta y archivos
- [ ] Reemplazar todos los textos según la tabla
- [ ] Actualizar todas las clases CSS \`.audit-*\` → \`.evaluation-*\` en .jsx y .css
- [ ] Actualizar import en App.jsx`,
    priority: 1,
    labelIds: [L.fe, L.refac],
    estimate: 2,
  });

  const fe3 = await iss({
    ...base,
    title: "FE: renombrar textos en ProjectResultsPage — auditoría → evaluación",
    description: `## Contexto (verificado en \`src/pages/ProjectResultsPage/ProjectResultsPage.jsx\`)
Textos y lógica con "auditoría/auditor":

| Línea | Qué cambiar |
|-------|------------|
| 20 | \`admin/auditor: botón "Auditar este proyecto"\` → \`admin/evaluador: botón "Evaluar este proyecto"\` |
| 21 | \`consultor: solo lectura, ve el estado de meta auditado\` → \`consultor: ... estado de meta evaluado\` |
| 169 | Comentario \`Sprint 16 — Botón "Enviar a auditoría"\` → \`"Enviar a evaluación"\` |
| 180 | \`'¿Enviar este proyecto a auditoría?...'\` → \`'¿Enviar este proyecto a evaluación?...'\` |
| 187 | \`'Proyecto enviado a auditoría exitosamente.'\` → \`'Proyecto enviado a evaluación exitosamente.'\` |
| 193 | \`📤 Enviar a auditoría\` → \`📤 Enviar a evaluación\` |
| 197 | Comentario \`Sprint 17: botón AUDITAR para admin/auditor\` → \`admin/evaluador\` |
| 206 | \`<ClipboardCheck/> Auditar este proyecto\` → \`Evaluar este proyecto\` |
| 226 | \`⚠ La auditoría fue rechazada\` → \`⚠ La evaluación fue rechazada\` |
| 232 | \`volvé a enviar el proyecto a auditoría\` → \`volvé a enviar el proyecto a evaluación\` |
| 249 | \`Auditoría cerrada · datos firmados\` → \`Evaluación cerrada · datos firmados\` |
| 252–253 | \`Auditado por...\` → \`Evaluado por...\` |

## Actualizar llamadas al service
Las llamadas que usan \`sendForReview\`, \`approveAudit\`, \`rejectAudit\` → usar nombres nuevos del issue FE-1.

## Checklist
- [ ] Reemplazar todos los textos según la tabla
- [ ] Actualizar llamadas al service
- [ ] Verificar que el botón "Evaluar este proyecto" navega a \`/evaluacion/:id\` (no \`/audit/:id\`)`,
    priority: 1,
    labelIds: [L.fe, L.refac],
    estimate: 1,
  });

  const fe4 = await iss({
    ...base,
    title: "FE: actualizar formatters.js y App.jsx — labels y rutas",
    description: `## Contexto

### \`src/utils/formatters.js\`
\`\`\`js
// Estado actual:
const labels = {
  planificacion: 'Planificación',
  activo:        'Activo',
  en_revision:   'En revisión',    // ← cambiar label
  completado:    'Auditado',       // ← cambiar label
  cancelado:     'Cancelado',
};
\`\`\`

### \`src/App.jsx\`
\`\`\`js
// Línea 12:
import AuditQueuePage from './pages/AuditQueuePage/AuditQueuePage.jsx';

// Líneas 58–65:
<Route path="/audit" element={<AuditQueuePage />} />
<Route path="/audit/:projectId" element={<EvaluationPage />} />
\`\`\`

## Cambios

**\`formatters.js\`:**
\`\`\`js
en_revision:  'En evaluación',   // antes: 'En revisión'
completado:   'Evaluado',        // antes: 'Auditado'
\`\`\`

**\`App.jsx\`:**
\`\`\`js
// Import:
import EvaluationQueuePage from './pages/EvaluationQueuePage/EvaluationQueuePage.jsx';

// Rutas:
<Route path="/evaluacion" element={<EvaluationQueuePage />} />
<Route path="/evaluacion/:projectId" element={<EvaluationPage />} />
\`\`\`

> ⚠️ Mantener las rutas viejas \`/audit\` y \`/audit/:projectId\` con \`<Navigate to="/evaluacion..."/>\` hasta que todos los links del sistema estén actualizados.

## Checklist
- [ ] Editar \`formatters.js\`: \`en_revision\` → 'En evaluación', \`completado\` → 'Evaluado'
- [ ] Editar \`App.jsx\`: import y rutas actualizadas
- [ ] Agregar \`<Navigate>\` de \`/audit\` → \`/evaluacion\` como alias temporal
- [ ] Buscar en todo el frontend links hardcodeados a \`/audit\` y actualizarlos`,
    priority: 1,
    labelIds: [L.fe, L.refac],
    estimate: 1,
  });

  const fe5 = await iss({
    ...base,
    title: "FE: levantar frontend, verificar flujo completo de evaluación en browser",
    description: `## Comando
\`\`\`bash
cd 2.frontend/odsProject
npm run dev
\`\`\`

## Casos de prueba a verificar manualmente

### Como gestor (login: gestor@ods.cr)
- [ ] Proyecto en estado \`activo\` muestra botón "📤 Enviar a evaluación" (no "auditoría")
- [ ] Al enviar, el estado muestra "En evaluación" (pill verde-morado)
- [ ] Proyecto en estado \`completado\` muestra "Evaluación cerrada · datos firmados"
- [ ] El badge de estado en el Dashboard dice "Evaluado" (no "Auditado")

### Como evaluador (login: evaluador@ods.cr — antes auditor_general)
- [ ] Login exitoso con las nuevas credenciales del mock
- [ ] Menú de navegación lleva a \`/evaluacion\` (no \`/audit\`)
- [ ] \`/evaluacion\` muestra "Cola de Evaluación" como título
- [ ] Tabs dicen "Pendientes de evaluación" y "Evaluados"
- [ ] KPI card dice "Evaluados este mes"
- [ ] Click en un proyecto en revisión va a \`/evaluacion/:id\` (EvaluationPage)
- [ ] Botones de aprobar/rechazar funcionan

### Como consultor (login: consultor@ods.cr)
- [ ] Puede ver proyectos completados con panel "Evaluación cerrada"
- [ ] Panel dice "Evaluado por [nombre]" (no "Auditado por")
- [ ] NO puede acceder a \`/evaluacion\`

### Rutas antiguas
- [ ] \`/audit\` redirige a \`/evaluacion\` (Navigate)
- [ ] \`/audit/:id\` redirige a \`/evaluacion/:id\`

## Checklist final
- [ ] \`npm run dev\` sin errores de consola
- [ ] Todos los casos de prueba anteriores pasados
- [ ] No quedan textos "auditoría" o "auditor" visibles en la UI (búsqueda en browser)`,
    priority: 1,
    labelIds: [L.fe, L.test],
    estimate: 2,
  });

  // ══════════════════════════════════════════════════════════════════
  //  DEPENDENCIAS (cadena multi-agente)
  // ══════════════════════════════════════════════════════════════════
  console.log("\n\n🔗 Dependencias...");

  await dep(jooq1, db1,  "JOOQ necesita DB en estado final");
  await dep(be1,   jooq1,"Controller se refactoriza con DB/JOOQ confirmados");
  await dep(be2,   jooq1,"Service se refactoriza con DB/JOOQ confirmados");
  await dep(be3,   be2,  "String replacement sobre el service ya renombrado");
  await dep(be4,   be1,  "Run depende del controller refactorizado");
  await dep(be4,   be3,  "Run depende de los strings correctos");
  await dep(fe1,   be4,  "FE paths dependen de que el BE corra y responda");
  await dep(fe2,   fe1,  "AuditQueuePage depende de service actualizado");
  await dep(fe3,   fe1,  "ProjectResultsPage depende de service actualizado");
  await dep(fe4,   fe2,  "App.jsx/formatters dependen de que los componentes estén listos");
  await dep(fe4,   fe3,  "App.jsx/formatters dependen de que los componentes estén listos");
  await dep(fe5,   fe4,  "Verificación final depende de todo el FE listo");

  // ══════════════════════════════════════════════════════════════════
  //  RESUMEN
  // ══════════════════════════════════════════════════════════════════
  const all = [db1, jooq1, be1, be2, be3, be4, fe1, fe2, fe3, fe4, fe5];
  console.log("\n" + "━".repeat(54));
  console.log("🎉  Sprint 3 creado!\n");
  console.log(`  📝 Issues    : ${all.length}`);
  console.log(`  🔗 Deps      : 12`);
  console.log("\n  Orden de ejecución (agente o desarrollador):");
  console.log("  1. DB (SQL + drop/setup/load_mocks)");
  console.log("  2. JOOQ (generate-sources)");
  console.log("  3a. BE — Controller (paths)");
  console.log("  3b. BE — Service    (métodos)");
  console.log("  3c. BE — Strings    (\"auditor\"→\"evaluador\")");
  console.log("  4. BE — Run + .http tests");
  console.log("  5a. FE — projectService.js + usePermissions.js");
  console.log("  5b. FE — EvaluationQueuePage");
  console.log("  5c. FE — ProjectResultsPage");
  console.log("  5d. FE — formatters.js + App.jsx");
  console.log("  6. FE — npm run dev + verificación\n");
}

main().catch(e => { console.error("\n❌", e.message); process.exit(1); });
