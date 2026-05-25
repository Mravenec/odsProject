#!/usr/bin/env node
/**
 * Sprint 6 — Mantenimiento de usuarios (Admin)
 * Propuesta: propuesta_users_admin.html
 * Equipo: linear_ods · JOOQ + POJOs · sin DTOs
 */
import { LinearClient } from "@linear/sdk";
import "../load-env.mjs";

const LINEAR_API_KEY = process.env.LINEAR_API_KEY;
const LINEAR_TEAM_NAME = process.env.LINEAR_TEAM_NAME || "linear_ods";
if (!LINEAR_API_KEY || LINEAR_API_KEY.includes("REEMPLAZA")) {
  console.error("LINEAR_API_KEY requerida en _linear/.env");
  process.exit(1);
}

const linear = new LinearClient({ apiKey: LINEAR_API_KEY });

async function getTeam() {
  const r = await linear.teams();
  const t = r.nodes.find(
    (x) =>
      x.name.toLowerCase() === LINEAR_TEAM_NAME.toLowerCase() ||
      x.key.toLowerCase() === LINEAR_TEAM_NAME.toLowerCase()
  );
  if (!t) throw new Error(`Equipo "${LINEAR_TEAM_NAME}" no encontrado.`);
  return t;
}

async function getOrCreateState(teamId, name, type, color) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((s) => s.name.toLowerCase() === name.toLowerCase());
  if (f) { console.log(`  ♻️  ${name}`); return f.id; }
  const res = await linear.createWorkflowState({ teamId, name, type, color });
  console.log(`  ✅ ${name}`);
  return (await res.workflowState).id;
}

async function getOrCreateLabel(teamId, name, color) {
  const r = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find((l) => l.name.toLowerCase() === name.toLowerCase());
  if (f) { console.log(`  ♻️  ${name}`); return f.id; }
  const res = await linear.createIssueLabel({ teamId, name, color });
  console.log(`  ✅ ${name}`);
  return (await res.issueLabel).id;
}

async function getOrCreateEpic(teamId, name, desc, color) {
  const r = await linear.projects({ filter: { name: { eq: name } } });
  if (r.nodes.length) { console.log(`  ♻️  Epic: ${name}`); return r.nodes[0].id; }
  const res = await linear.createProject({ teamIds: [teamId], name, description: desc, color });
  console.log(`  ✅ Epic: ${name}`);
  return (await res.project).id;
}

async function getOrCreateSprint(teamId, name, startsAt, endsAt) {
  const r = await linear.cycles({ filter: { team: { id: { eq: teamId } }, name: { eq: name } } });
  if (r.nodes.length) { console.log(`  ♻️  Sprint: ${name}`); return r.nodes[0].id; }
  const res = await linear.createCycle({ teamId, name, startsAt, endsAt });
  console.log(`  ✅ Sprint: ${name}`);
  return (await res.cycle).id;
}

async function iss(params) {
  const res = await linear.createIssue(params);
  const i = await res.issue;
  console.log(`    📌 ${i.identifier}: ${i.title}`);
  return i;
}

async function dep(child, parent) {
  await linear.createIssueRelation({
    issueId: parent.id,
    relatedIssueId: child.id,
    type: "blocks",
  });
  console.log(`     🔗 ${child.identifier} ← ${parent.identifier}`);
}

const NOW = new Date();
const WEEK = 7 * 24 * 60 * 60 * 1000;

async function main() {
  console.log("\n🔷  Sprint 6 — Admin: mantenimiento de usuarios");
  console.log("━".repeat(54));

  const team = await getTeam();
  const teamId = team.id;
  console.log(`\n✅ Equipo: ${team.name} (${team.key})`);

  console.log("\n🔄 Estados...");
  const ST = {
    backlog: await getOrCreateState(teamId, "Backlog", "backlog", "#94A3B8"),
    todo:    await getOrCreateState(teamId, "Todo", "unstarted", "#A78BFA"),
    inprog:  await getOrCreateState(teamId, "In Progress", "started", "#F59E0B"),
    done:    await getOrCreateState(teamId, "Done", "completed", "#22C55E"),
  };

  console.log("\n🏷️  Labels...");
  const L = {
    db:   await getOrCreateLabel(teamId, "role:database", "#EAB308"),
    be:   await getOrCreateLabel(teamId, "role:backend", "#22C55E"),
    fe:   await getOrCreateLabel(teamId, "role:frontend", "#0EA5E9"),
    ux:   await getOrCreateLabel(teamId, "role:ux", "#EC4899"),
    feat: await getOrCreateLabel(teamId, "type:feature", "#3B82F6"),
    test: await getOrCreateLabel(teamId, "type:test", "#8B5CF6"),
  };

  const epicId = await getOrCreateEpic(
    teamId,
    "Admin / Usuarios",
    "CRUD de usuarios por sede y rol — menú administrador. JOOQ + POJOs.",
    "#7C3AED"
  );

  const sprintId = await getOrCreateSprint(
    teamId,
    "Sprint 6 — Admin: mantenimiento de usuarios",
    new Date(NOW.getTime() + 10 * WEEK),
    new Date(NOW.getTime() + 12 * WEEK)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };

  const usr1 = await iss({
    ...base,
    title: "DB: verificar schema ods_login suficiente para CRUD usuarios",
    description: `## Contexto
El schema en \`0.database/propuesta_actual/1. login_system.sql\` ya tiene:
- \`usuarios\` (rol_id, sede_id, email, is_active, password_hash, …)
- \`roles\` (admin, gestor, consultor, evaluador)
- \`sedes\` (catálogo)
- Trigger \`trg_usuarios_update\` audita CAMBIO_SEDE, CAMBIO_PASSWORD

**v1: sin ALTER TABLE** — solo verificación.

## Verificación
\`\`\`sql
SELECT id, nombre FROM ods_login.roles ORDER BY id;
SELECT id, nombre FROM ods_login.sedes ORDER BY id;
SELECT u.id, u.username, u.email, r.nombre AS rol, s.nombre AS sede
FROM ods_login.usuarios u
JOIN ods_login.roles r ON u.rol_id = r.id
LEFT JOIN ods_login.sedes s ON u.sede_id = s.id;
\`\`\`

## Checklist
- [ ] 4 roles confirmados
- [ ] Sedes mocks presentes (6 en \`21. ods_mocks.sql\`)
- [ ] FKs usuarios → roles, sedes válidas
- [ ] Trigger CAMBIO_SEDE documentado en comentario Linear
- [ ] **No** ejecutar drop/setup salvo que se detecte drift de schema`,
    priority: 1,
    labelIds: [L.db, L.feat],
    estimate: 1,
  });

  const usr2 = await iss({
    ...base,
    title: "JOOQ: generate-sources — confirmar POJOs Usuarios, Roles, Sedes",
    description: `## Comando
\`\`\`bash
cd 1.backend/odsProject
mvn generate-sources
\`\`\`

## POJOs esperados (\`database/jooq/ods_login/tables/pojos/\`)
- \`Usuarios.java\` — rolId, sedeId, passwordHash, isActive, …
- \`Roles.java\` — id, nombre, descripcion
- \`Sedes.java\` — id, nombre, descripcion

## Checklist
- [ ] BUILD SUCCESS
- [ ] \`git diff --name-only src/main/java/**/jooq/\` vacío (sin cambio SQL en v1)
- [ ] Confirmar campos en Usuarios.java para CRUD admin`,
    priority: 1,
    labelIds: [L.be, L.test],
    estimate: 1,
  });

  const usr3 = await iss({
    ...base,
    title: "BE: LoginRepository — updateUsuario + listUsuariosAdmin (JOOQ join)",
    description: `## Archivos
- \`LoginRepository.java\`
- \`ILoginRepository.java\`

## Métodos nuevos (JOOQ, POJOs — sin DTOs)
\`\`\`java
Usuarios updateUsuario(Usuarios u);           // dsl.update(USUARIOS)...
void deactivateUsuario(Integer id);           // is_active = 0
List<...> findAllUsuariosAdmin();             // join ROLES + SEDES, sin password_hash
boolean existsEmail(String email, Integer excludeId);
boolean existsUsername(String username, Integer excludeId);
\`\`\`

## Proyección admin (inline JOOQ o record POJO)
Retornar: id, username, fullName, email, rolId, rol (nombre), sedeId, sede (nombre), isActive

## Checklist
- [ ] updateUsuario funciona (no re-usar saveUsuario INSERT)
- [ ] deactivateUsuario soft-delete
- [ ] list join roles+sedes
- [ ] unicidad email/username en create/update`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 2,
  });

  const usr4 = await iss({
    ...base,
    title: "BE: LoginService — createUser, updateUser, deactivateUser (BCrypt)",
    description: `## Archivos
- \`LoginService.java\`
- \`ILoginService.java\`

## Lógica
- **createUser:** BCryptPasswordEncoder (ya en service), rolId + sedeId obligatorios
- **updateUser:** password opcional; validar rolId ∈ roles, sedeId ∈ sedes
- **deactivateUser:** is_active=false; no desactivar único admin activo

## Checklist
- [ ] createUser hashea password
- [ ] updateUser parcial (password solo si viene)
- [ ] validación FK rol/sede
- [ ] guard admin único
- [ ] usar repository update (USR-3)`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 2,
  });

  const usr5 = await iss({
    ...base,
    title: "BE: LoginController — GET/POST/PUT/PATCH users + GET roles (POJOs)",
    description: `## Endpoints REST (sin DTOs — POJOs sanitizados)
| Método | Ruta | Respuesta |
|--------|------|-----------|
| GET | \`/api/login/users\` | List admin (sin passwordHash) |
| GET | \`/api/login/roles\` | \`List<Roles>\` |
| POST | \`/api/login/users\` | 201 create |
| PUT | \`/api/login/users/{id}\` | 200 update |
| PATCH | \`/api/login/users/{id}/deactivate\` | 200 soft-delete |

## Cablear stubs existentes
- \`GET /roles\` → actualmente retorna \`List.of()\` — conectar a service
- \`PUT /users/{id}\` → stub 400 — implementar
- Deprecar \`POST /admin/users\` accion-based

## Seguridad mínima v1
- **Nunca** serializar \`passwordHash\` en JSON

## Checklist
- [ ] 5 endpoints funcionando
- [ ] GET /roles retorna 4 roles
- [ ] POST create con body: username, email, fullName, password, rolId, sedeId`,
    priority: 1,
    labelIds: [L.be, L.feat],
    estimate: 2,
  });

  const usr6 = await iss({
    ...base,
    title: "BE: .http — CRUD usuarios + roles + sedes",
    description: `## Archivo nuevo
\`src/test/java/com/odsProject/odsProject/http/users-admin.http\`

## Casos
1. Login admin (\`admin@ods.local\`)
2. GET /login/roles → 4 roles
3. GET /login/sedes → lista sedes
4. GET /login/users → lista usuarios
5. POST /login/users → crear gestor test
6. PUT /login/users/{id} → cambiar rol/sede
7. PATCH /login/users/{id}/deactivate
8. Verificar passwordHash ausente en responses

## Checklist
- [ ] Archivo .http creado
- [ ] Todos los requests 2xx
- [ ] Documentar en comentario Linear`,
    priority: 1,
    labelIds: [L.be, L.test],
    estimate: 1,
  });

  const usr7 = await iss({
    ...base,
    title: "FE: authService — listUsers, getRoles, createUser, updateUser, deactivateUser",
    description: `## Archivo
\`src/services/authService.js\`

## Métodos (patrón catalogService — snake_case → camelCase)
\`\`\`js
listUsers()        → GET  /login/users
getRoles()         → GET  /login/roles
createUser(payload)→ POST /login/users
updateUser(id, p)  → PUT  /login/users/{id}
deactivateUser(id) → PATCH /login/users/{id}/deactivate
\`\`\`

Payload create/update (UsersAdminPage ya lo envía):
\`{ username, email, fullName, password, rolId, sedeId }\`

## Checklist
- [ ] 5 métodos implementados
- [ ] Retorno \`{ success, data | error }\` consistente
- [ ] Mapeo rol/sede/fullName para tabla admin`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 1,
  });

  const usr8 = await iss({
    ...base,
    title: "FE: cablear UsersAdminPage — ruta, permiso, dashboard link",
    description: `## Página existente
\`src/pages/Admin/Users/UsersAdminPage.jsx\` — UI completa, falta wiring.

## Cambios
1. \`App.jsx\`: ruta \`/admin/users\` + \`ProtectedRoute require="canManageUsers"\`
2. \`usePermissions.js\`: \`canManageUsers: true\` solo para \`admin\`
3. \`DashboardPage.jsx\`: card "Administración de usuarios" → \`/admin/users\`

## Checklist
- [ ] Ruta registrada
- [ ] Solo admin accede (resto → /forbidden)
- [ ] Dashboard link visible para admin
- [ ] Modal create/edit funciona con authService (USR-7)`,
    priority: 1,
    labelIds: [L.fe, L.feat],
    estimate: 2,
  });

  const usr9 = await iss({
    ...base,
    title: "FE: npm run dev — verificación CRUD admin en browser",
    description: `## Comando
\`\`\`bash
cd 2.frontend/odsProject && npm run dev
\`\`\`

## Casos de prueba
- [ ] Login \`admin@ods.local\` → dashboard → Administración de usuarios
- [ ] Listar usuarios con rol y sede
- [ ] Crear gestor en Sede Central + email
- [ ] Editar: cambiar rol a evaluador, cambiar sede
- [ ] Desactivar usuario → ya no en lista
- [ ] Network: responses sin passwordHash
- [ ] \`npm run build\` exit 0

## Pre-requisitos
Backend corriendo: \`cd 1.backend/odsProject && mvn spring-boot:run\``,
    priority: 1,
    labelIds: [L.fe, L.ux, L.test],
    estimate: 1,
  });

  console.log("\n🔗 Dependencias...");
  await dep(usr2, usr1);
  await dep(usr3, usr2);
  await dep(usr4, usr3);
  await dep(usr5, usr4);
  await dep(usr6, usr5);
  await dep(usr7, usr6);
  await dep(usr8, usr7);
  await dep(usr9, usr8);

  const all = [usr1, usr2, usr3, usr4, usr5, usr6, usr7, usr8, usr9];
  console.log("\n" + "━".repeat(54));
  console.log("🎉  Sprint 6 creado en Linear!\n");
  console.log(`  📝 Issues : ${all.length}`);
  console.log(`  📊 Puntos : 13`);
  console.log(`  🔗 Deps   : 8`);
  console.log("\n  Orden:");
  all.forEach((i, n) => console.log(`  ${n + 1}. ${i.identifier}  ${i.title.slice(0, 55)}`));
  console.log("\n  Propuesta: _linear/scripts/propuesta_users_admin.html");
  console.log("  Pipeline: DB verify → JOOQ → BE → .http → FE → npm run dev\n");
}

main().catch((e) => {
  console.error("\n❌", e.message);
  process.exit(1);
});
