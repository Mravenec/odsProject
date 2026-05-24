#!/usr/bin/env node
/**
 * 🎯 Sprint UX — Íconos ODS desde ods.cr
 *
 * ─── URLS VERIFICADAS (grid ods.cr #items-objetivos) ─────────────────────
 * El grid oficial usa PNGs del tema Drupal, NO itemNN-deco.png (detalle):
 *  https://ods.cr/themes/custom/ods10/img/item01.png … item17.png
 * CSS: #obj-item1 a { background-image: url(../img/item01.png); }
 * Descarga: node _linear/scripts/download-ods-icons.mjs
 *
 * (itemNN-deco.png es decorativo en páginas /es/objetivo/objetivo-N — no usar en grid)
 *
 * ─── ANÁLISIS DEL REPO ───────────────────────────────────────────────────
 * DB  : icono_url existe en ods_catalog (schema), NUNCA poblada (17 INSERTs sin ella)
 * BE  : CatalogController ya retorna iconoUrl — sin cambios de código
 * FE  : catalogService NO mapea iconoUrl
 *       ProjectCreationPage tiene const { } = useCatalog() — vacío — usa odsColors hardcoded
 * ────────────────────────────────────────────────────────────────────────
 */

import { LinearClient } from "@linear/sdk";
import "../load-env.mjs";

const LINEAR_API_KEY   = process.env.LINEAR_API_KEY;
const LINEAR_TEAM_NAME = process.env.LINEAR_TEAM_NAME || "linear_ods";
if (!LINEAR_API_KEY || LINEAR_API_KEY.includes("REEMPLAZA")) {
  console.error("LINEAR_API_KEY requerida en _linear/.env (copia desde .env.example).");
  process.exit(1);
}
const linear = new LinearClient({ apiKey: LINEAR_API_KEY });

async function getTeam() {
  const r = await linear.teams();
  const t = r.nodes.find(t =>
    t.name.toLowerCase() === LINEAR_TEAM_NAME.toLowerCase() ||
    t.key.toLowerCase()  === LINEAR_TEAM_NAME.toLowerCase()
  );
  if (!t) throw new Error(`Equipo "${LINEAR_TEAM_NAME}" no encontrado.`);
  return t;
}
async function getOrCreateState(teamId, name, type, color) {
  const r = await linear.workflowStates({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find(s => s.name.toLowerCase() === name.toLowerCase());
  if (f) { console.log(`  ♻️  ${name}`); return f.id; }
  const res = await linear.createWorkflowState({ teamId, name, type, color });
  console.log(`  ✅ ${name}`); return (await res.workflowState).id;
}
async function getOrCreateLabel(teamId, name, color) {
  const r = await linear.issueLabels({ filter: { team: { id: { eq: teamId } } } });
  const f = r.nodes.find(l => l.name.toLowerCase() === name.toLowerCase());
  if (f) { console.log(`  ♻️  ${name}`); return f.id; }
  const res = await linear.createIssueLabel({ teamId, name, color });
  console.log(`  ✅ ${name}`); return (await res.issueLabel).id;
}
async function getOrCreateEpic(teamId, name, desc, color) {
  const r = await linear.projects({ filter: { name: { eq: name } } });
  if (r.nodes.length) { console.log(`  ♻️  Epic: ${name}`); return r.nodes[0].id; }
  const res = await linear.createProject({ teamIds: [teamId], name, description: desc, color });
  console.log(`  ✅ Epic: ${name}`); return (await res.project).id;
}
async function getOrCreateSprint(teamId, name, startsAt, endsAt) {
  const r = await linear.cycles({ filter: { team: { id: { eq: teamId } }, name: { eq: name } } });
  if (r.nodes.length) { console.log(`  ♻️  Sprint: ${name}`); return r.nodes[0].id; }
  const res = await linear.createCycle({ teamId, name, startsAt, endsAt });
  console.log(`  ✅ Sprint: ${name}`); return (await res.cycle).id;
}
async function iss(params) {
  const res = await linear.createIssue(params);
  const i   = await res.issue;
  console.log(`    📌 ${i.identifier}: ${i.title}`);
  return i;
}
async function dep(child, parent) {
  // Linear SDK: type "blocks" — parent blocks child (child blocked until parent Done)
  await linear.createIssueRelation({ issueId: parent.id, relatedIssueId: child.id, type: "blocks" });
  console.log(`     🔗 ${child.identifier} ← ${parent.identifier}`);
}

const NOW  = new Date();
const WEEK = 7 * 24 * 60 * 60 * 1000;

async function main() {
  console.log("\n🔷  Sprint UX — Íconos ODS desde ods.cr");
  console.log("━".repeat(54));

  const team   = await getTeam();
  const teamId = team.id;
  console.log(`\n✅ Equipo: ${team.name} (${team.key})`);

  console.log("\n🔄 Estados...");
  const ST = {
    backlog:  await getOrCreateState(teamId, "Backlog",          "backlog",   "#94A3B8"),
    ready:    await getOrCreateState(teamId, "Ready",            "unstarted", "#60A5FA"),
    todo:     await getOrCreateState(teamId, "Todo",             "unstarted", "#A78BFA"),
    inprog:   await getOrCreateState(teamId, "In Progress",      "started",   "#F59E0B"),
    review:   await getOrCreateState(teamId, "Code Review",      "started",   "#F97316"),
    qa:       await getOrCreateState(teamId, "Testing / QA",     "started",   "#EC4899"),
    staging:  await getOrCreateState(teamId, "Ready for Deploy", "started",   "#10B981"),
    done:     await getOrCreateState(teamId, "Done",             "completed", "#22C55E"),
  };

  console.log("\n🏷️  Labels...");
  const L = {
    db:   await getOrCreateLabel(teamId, "role:database",  "#EAB308"),
    be:   await getOrCreateLabel(teamId, "role:backend",   "#22C55E"),
    fe:   await getOrCreateLabel(teamId, "role:frontend",  "#0EA5E9"),
    ux:   await getOrCreateLabel(teamId, "role:ux",        "#EC4899"),
    feat: await getOrCreateLabel(teamId, "type:feature",   "#3B82F6"),
    fix:  await getOrCreateLabel(teamId, "type:fix",       "#EF4444"),
    test: await getOrCreateLabel(teamId, "type:test",      "#8B5CF6"),
  };

  const epicId = await getOrCreateEpic(teamId,
    "UX / UI", "Íconos ODS oficiales y mejoras de interfaz.", "#1E40AF");

  const sprintId = await getOrCreateSprint(teamId,
    "Sprint 5 — UX/UI: íconos ODS desde ods.cr",
    new Date(NOW.getTime() + 8 * WEEK),
    new Date(NOW.getTime() + 10 * WEEK)
  );

  const base = { teamId, projectId: epicId, cycleId: sprintId, stateId: ST.backlog };

  // ══════════════════════════════════════════════════════════════
  // CAPA 1 — BASE DE DATOS
  // ══════════════════════════════════════════════════════════════
  const db1 = await iss({
    ...base,
    title: "DB: descargar íconos de ods.cr + poblar icono_url en los 17 INSERTs",
    description: `## Fuente verificada: grid ods.cr (#items-objetivos)

Íconos cuadrados oficiales del tema Drupal (\`layout.css\`):
\`https://ods.cr/themes/custom/ods10/img/itemNN.png\`

**No usar** \`itemNN-deco.png\` — es decorativo en páginas de detalle.

## Paso 1 — Crear carpeta + descargar (script)
\`\`\`bash
node _linear/scripts/download-ods-icons.mjs
\`\`\`

O manualmente:
\`\`\`bash
mkdir -p 2.frontend/odsProject/public/ods-icons
curl -L -o 2.frontend/odsProject/public/ods-icons/ods-01.png "https://ods.cr/themes/custom/ods10/img/item01.png"
# … ods-02 … ods-17 con item02.png … item17.png
\`\`\`

## Paso 3 — Actualizar los 17 INSERTs en ods01-17_database.sql

La columna \`icono_url\` existe en el schema pero **nunca se pobló**.
Cambiar de 4 a 5 columnas en cada archivo (ods01 a ods17):

\`\`\`sql
-- ANTES (todos los 17 archivos):
INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion)
VALUES (@ODS_NUM, '...', '#...', '...');

-- DESPUÉS (agregar icono_url al final):
INSERT IGNORE INTO ods_login.ods_catalog (id, nombre, color_hex, descripcion, icono_url)
VALUES (@ODS_NUM, '...', '#...', '...', '/ods-icons/ods-01.png');
-- (ods-01.png para archivo 4.ods01, ods-02.png para archivo 5.ods02, etc.)
\`\`\`

## Paso 4 — Pipeline de base de datos
\`\`\`bash
python 0.database/drop_db.py
python 0.database/setup_db.py
python 0.database/load_mocks.py
\`\`\`

## Verificación
\`\`\`sql
SELECT id, nombre, icono_url FROM ods_login.ods_catalog ORDER BY id;
-- 17 filas, icono_url = '/ods-icons/ods-NN.png' en cada una
\`\`\`

## Checklist
- [ ] Crear \`public/ods-icons/\` en el frontend Vite
- [ ] Descargar los 17 PNGs con los curl exactos de arriba
- [ ] Verificar que los 17 archivos existen y tienen tamaño > 0
- [ ] Actualizar los 17 INSERTs (uno en cada \`ods0N_database.sql\`) con \`icono_url\`
- [ ] \`python 0.database/drop_db.py\`
- [ ] \`python 0.database/setup_db.py\`
- [ ] \`python 0.database/load_mocks.py\`
- [ ] SELECT confirma 17 filas con \`icono_url\` no null`,
    priority: 1, labelIds: [L.db, L.feat], estimate: 2,
  });

  // ══════════════════════════════════════════════════════════════
  // CAPA 2 — JOOQ
  // ══════════════════════════════════════════════════════════════
  const jooq1 = await iss({
    ...base,
    title: "JOOQ: mvn generate-sources — confirmar OdsCatalog.java ya tiene iconoUrl",
    description: `## Contexto
La columna \`icono_url\` ya existía en el schema antes de este sprint.
El POJO \`OdsCatalog.java\` generado por JOOQ **ya tiene**:
\`\`\`java
private String iconoUrl;
public String getIconoUrl() { ... }
public OdsCatalog setIconoUrl(String iconoUrl) { ... }
\`\`\`

El cambio fue de datos (poblar la columna), no de estructura.
JOOQ no generará cambios nuevos. El paso es de confirmación.

## Comando
\`\`\`bash
cd 1.backend/odsProject
mvn generate-sources -P jooq
\`\`\`

## Verificación
\`\`\`bash
git diff --name-only src/main/java/.../jooq/
# → Sin cambios (los POJOs ya tenían iconoUrl)
\`\`\`

## Checklist
- [ ] \`mvn generate-sources\` sin errores
- [ ] \`git diff\` no muestra cambios en archivos JOOQ
- [ ] Confirmar en \`OdsCatalog.java\`: existe \`private String iconoUrl\``,
    priority: 1, labelIds: [L.be, L.test], estimate: 1,
  });

  // ══════════════════════════════════════════════════════════════
  // CAPA 3 — BACKEND run + .http
  // ══════════════════════════════════════════════════════════════
  const be1 = await iss({
    ...base,
    title: "BE: mvn spring-boot:run + verificar GET /api/catalog/ods retorna iconoUrl",
    description: `## Contexto
\`CatalogController.java\` ya tiene el endpoint correcto y **no necesita cambios de código**:
\`\`\`java
@GetMapping("/ods")
public ResponseEntity<List<OdsCatalog>> getOdsCatalog() {
    List<OdsCatalog> result = dsl.selectFrom(ODS_CATALOG)
        .orderBy(ODS_CATALOG.ID.asc())
        .fetchInto(OdsCatalog.class);
    return ResponseEntity.ok(result);
}
\`\`\`
El POJO ya tiene \`iconoUrl\` — solo necesita que la BD tenga el dato.

## Comando
\`\`\`bash
cd 1.backend/odsProject
mvn spring-boot:run
\`\`\`

## Test .http
Agregar en \`src/test/java/.../http/api-tests.http\`:
\`\`\`http
### Catálogo ODS — verificar iconoUrl poblado
GET {{baseUrl}}/api/catalog/ods

# Respuesta esperada:
# [
#   { "id":1, "nombre":"Fin de la Pobreza", "colorHex":"#E5243B",
#     "iconoUrl":"/ods-icons/ods-01.png", "descripcion":"..." },
#   { "id":2, "nombre":"Hambre Cero", "colorHex":"#DDA63A",
#     "iconoUrl":"/ods-icons/ods-02.png", "descripcion":"..." },
#   ...
#   { "id":17, "nombre":"Alianzas para Lograr los Objetivos", "colorHex":"#19486A",
#     "iconoUrl":"/ods-icons/ods-17.png", "descripcion":"..." }
# ]
\`\`\`

## Checklist
- [ ] \`mvn spring-boot:run\` — app levanta sin errores
- [ ] \`GET /api/catalog/ods\` → 200 OK, array de 17 objetos
- [ ] Cada objeto tiene \`iconoUrl: "/ods-icons/ods-NN.png"\` (no null)
- [ ] Confirmar colores DB: ODS 9=\`#F36D25\`, 10=\`#E11484\`, 11=\`#F99D26\`, 15=\`#56DB27\``,
    priority: 1, labelIds: [L.be, L.test], estimate: 1,
  });

  // ══════════════════════════════════════════════════════════════
  // CAPA 4 — FRONTEND
  // ══════════════════════════════════════════════════════════════
  const fe1 = await iss({
    ...base,
    title: "FE: agregar iconoUrl al mapping de catalogService.getOdsList()",
    description: `## Contexto (verificado en \`src/services/catalogService.js\`)

El método mapea 4 campos pero **descarta \`iconoUrl\`** aunque llega en la respuesta:
\`\`\`js
return (res.data || []).map(o => ({
  id:          o.id,
  nombre:      o.nombre,
  colorHex:    o.colorHex || o.color_hex || '#e5243b',
  descripcion: o.descripcion || ''
  // iconoUrl no está ← problema
}));
\`\`\`

## Cambio
\`\`\`js
return (res.data || []).map(o => ({
  id:          o.id,
  nombre:      o.nombre,
  colorHex:    o.colorHex || o.color_hex || '#e5243b',
  descripcion: o.descripcion || '',
  iconoUrl:    o.iconoUrl   || o.icono_url || null,  // ← AGREGAR
}));
\`\`\`

## Checklist
- [ ] Agregar \`iconoUrl\` al mapping en \`catalogService.js\`
- [ ] Verificar en consola del browser: \`odsList[0].iconoUrl === "/ods-icons/ods-01.png"\`

**Depende de:** BE-1`,
    priority: 1, labelIds: [L.fe, L.feat], estimate: 1,
  });

  const fe2 = await iss({
    ...base,
    title: "FE: conectar odsList al grid de selección en ProjectCreationPage",
    description: `## El problema central (verificado en \`ProjectCreationPage.jsx\`)

\`useCatalog\` está importado (línea 2) pero con **destructuring vacío en línea 115**:
\`\`\`js
const { } = useCatalog();   // ← vacío — odsList ignorado
\`\`\`

El grid usa datos hardcodeados:
\`\`\`jsx
{Object.keys(odsColors).map(odsId => (
  <div style={{ backgroundColor: odsColors[odsId] }}>
    <span className="ods-number">{odsId}</span>
    <span className="ods-title">{getObjectiveName(odsId)}</span>
  </div>
))}
\`\`\`

## Cambios

**Línea 115 — extraer odsList:**
\`\`\`js
const { odsList } = useCatalog();   // ← conectar
\`\`\`

**Grid — usar odsList de la BD:**
\`\`\`jsx
{odsList.map(ods => (
  <div
    key={ods.id}
    className={\`ods-card \${formData.selectedOds.includes(ods.id) ? 'selected' : ''}\`}
    style={{ backgroundColor: ods.colorHex }}
    onClick={() => toggleOds(ods.id)}
  >
    {ods.iconoUrl
      ? <img
          src={ods.iconoUrl}
          alt={\`ODS \${ods.id} — \${ods.nombre}\`}
          className="ods-icon-img"
          onError={(e) => { e.target.style.display = 'none'; }}
        />
      : <>
          <span className="ods-number">{ods.id}</span>
          <span className="ods-title">{ods.nombre}</span>
        </>
    }
    {formData.selectedOds.includes(ods.id) && (
      <div className="selection-overlay"><Check size={16} /></div>
    )}
  </div>
))}
\`\`\`

> El fallback (\`iconoUrl\` null → número+nombre) evita que la UI se rompa
> si un ícono no carga o la BD tiene el campo vacío.

## Checklist
- [ ] Cambiar \`const { } = useCatalog()\` → \`const { odsList } = useCatalog()\`
- [ ] Reemplazar \`Object.keys(odsColors).map\` → \`odsList.map\`
- [ ] Usar \`ods.colorHex\`, \`ods.iconoUrl\`, \`ods.nombre\`, \`ods.id\`
- [ ] Agregar fallback cuando \`iconoUrl\` es null
- [ ] Verificar que el acordeón de indicadores sigue funcionando
- [ ] Verificar que \`toggleOds\` y \`formData.selectedOds\` reciben número (no string)

**Depende de:** FE-1`,
    priority: 1, labelIds: [L.fe, L.feat], estimate: 3,
  });

  const fe3 = await iss({
    ...base,
    title: "FE: CSS — imagen a pantalla completa en .ods-card",
    description: `## Cambios en \`ProjectCreationPage.css\`
\`\`\`css
/* La imagen ocupa toda la card */
.ods-card .ods-icon-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: calc(var(--radius-lg) - 2px);
  display: block;
  pointer-events: none;
  user-select: none;
}

/* Sin padding cuando hay imagen */
.ods-card { padding: 0; }   /* antes: 1rem */

/* Overlay de selección sobre la imagen */
.ods-card .selection-overlay {
  position: absolute; inset: 0;
  background: rgba(255, 255, 255, 0.22);
  display: flex; align-items: center; justify-content: center;
  border-radius: inherit;
}

/* Fallback: card sin imagen mantiene padding */
.ods-card:not(:has(.ods-icon-img)) { padding: 0.75rem; }
\`\`\`

## Checklist
- [ ] Agregar \`.ods-icon-img\` con \`object-fit: cover\`
- [ ] Cambiar padding de \`.ods-card\` a \`0\`
- [ ] Actualizar \`.selection-overlay\` para posicionarse sobre la imagen
- [ ] Verificar mobile 375px: grid 3 columnas sin distorsión`,
    priority: 1, labelIds: [L.fe, L.ux], estimate: 1,
  });

  const fe4 = await iss({
    ...base,
    title: "FE: corregir 4 colores en odsColors de formatters.js para alinear con la BD",
    description: `## Discrepancias detectadas (BD = fuente de verdad)

| ODS | DB (correcto)  | formatters.js (incorrecto) |
|-----|---------------|---------------------------|
| 9   | \`#F36D25\`   | \`#FD6925\`               |
| 10  | \`#E11484\`   | \`#DD1367\`               |
| 11  | \`#F99D26\`   | \`#FD9D24\`               |
| 15  | \`#56DB27\`   | \`#56C02B\`               |

\`odsColors\` se sigue usando en el acordeón de indicadores
(bordes y badges) — debe corregirse aunque el grid ahora use \`odsList\`.

## Cambios en \`src/utils/formatters.js\`
\`\`\`js
9:  '#F36D25',   // antes: '#FD6925'
10: '#E11484',   // antes: '#DD1367'
11: '#F99D26',   // antes: '#FD9D24'
15: '#56DB27',   // antes: '#56C02B'
\`\`\`

## Checklist
- [ ] Corregir los 4 valores
- [ ] Verificar en el acordeón de indicadores que los colores son correctos`,
    priority: 2, labelIds: [L.fe, L.fix], estimate: 1,
  });

  const fe5 = await iss({
    ...base,
    title: "FE: npm run dev — verificación completa en browser",
    description: `## Comando
\`\`\`bash
cd 2.frontend/odsProject
npm run dev
\`\`\`

## Casos de prueba

### Network tab
- [ ] \`GET /api/catalog/ods\` → 200, cada objeto tiene \`iconoUrl: "/ods-icons/ods-NN.png"\`
- [ ] \`GET /ods-icons/ods-01.png\` → 200 (Vite sirve desde \`/public/\`)
- [ ] Los 17 archivos PNG cargan correctamente

### Selección de ODS (\`/projects/new\`)
- [ ] Grid muestra los 17 íconos descargados desde ods.cr
- [ ] Colores de fondo vienen de la BD (ODS 9, 10, 11, 15 tienen colores corregidos)
- [ ] Hover, selección con overlay de check, toggle — funcionan correctamente

### Fallback
- [ ] Si se simula error de imagen (renombrar un PNG), la card muestra número+nombre
- [ ] Ningún ícono roto visible

### Acordeón de indicadores
- [ ] Bordes de color y badges muestran colores correctos tras el fix de formatters.js

**Depende de:** FE-3 y FE-4`,
    priority: 1, labelIds: [L.fe, L.ux, L.test], estimate: 1,
  });

  // Dependencias
  console.log("\n🔗 Dependencias...");
  await dep(jooq1, db1);
  await dep(be1,   jooq1);
  await dep(fe1,   be1);
  await dep(fe2,   fe1);
  await dep(fe3,   fe2);
  await dep(fe4,   fe1);
  await dep(fe5,   fe3);
  await dep(fe5,   fe4);

  const all = [db1, jooq1, be1, fe1, fe2, fe3, fe4, fe5];
  console.log("\n" + "━".repeat(54));
  console.log("🎉  Sprint creado!\n");
  console.log(`  📝 Issues    : ${all.length}`);
  console.log(`  🔗 Deps      : 8`);
  console.log("\n  Orden:");
  console.log("  1. DB-1  : curl 17 PNGs + actualizar INSERTs + drop/setup/load_mocks");
  console.log("  2. JOOQ  : mvn generate-sources (confirmar, sin cambios en POJOs)");
  console.log("  3. BE-1  : mvn spring-boot:run + .http test iconoUrl");
  console.log("  4. FE-1  : catalogService.js → mapear iconoUrl");
  console.log("  5. FE-2  : ProjectCreationPage → conectar odsList al grid");
  console.log("  6. FE-3  : CSS object-fit:cover               ┐ paralelo");
  console.log("  7. FE-4  : fix 4 colores en formatters.js     ┘");
  console.log("  8. FE-5  : npm run dev + verificación\n");
}

main().catch(e => { console.error("\n❌", e.message); process.exit(1); });
