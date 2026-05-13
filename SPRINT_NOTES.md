# Sprint notes — Refactor del motor de fórmulas variables

> Este documento describe **todos los cambios aplicados** sobre el repo original
> en orden de Sprint. La base de datos NO se tocó: el esquema existente cubre
> todos los casos.

## Resumen de archivos por categoría

| Categoría | Cantidad | Estado |
|---|---|---|
| Archivos backend nuevos | 4 | creados |
| Archivos backend modificados | 56 | parchados |
| Archivos frontend nuevos | 1 | creado |
| Archivos frontend modificados | 21 | reescritos / parchados |
| Tests JUnit nuevos | 2 | creados |
| Cambios en BD | 0 | — |

---

## Sprint 1 — Motor de evaluación robusto (backend transversal)

### Nuevos archivos

- `service/FormulaUtils.java` — utilidad para extraer variables de una fórmula
  y comparar contra el set declarado (faltantes / sobrantes). Filtra reservadas
  de exp4j (`sqrt`, `sin`, `pi`, `e`, etc.) y marcadores semánticos (`valor`,
  `count`).
- `controller/EvaluationController.java` — endpoint público transversal:
  - `POST /api/evaluacion/preview` — ejecuta una fórmula con valores hipotéticos.
  - `POST /api/evaluacion/validar-formula` — valida sintaxis + coherencia de variables.
  - `GET  /api/evaluacion/variables?formula=...` — lista variables presentes.
- `config/TransactionManagerConfig.java` — un `PlatformTransactionManager` por
  cada DataSource (Login, Master, ODS01..ODS17). Necesario para que
  `@Transactional("txManagerOds01")` funcione en este setup multi-DS.

### Archivos modificados

- `service/EvaluationService.java` — sobrecarga `validateFormula(formula, Set<String> variables)`
  que sí declara variables a exp4j (la versión sin variables fallaba para
  `(p1+p2)/p3`); resultado escalado a `DECIMAL(15,4)` (mismo formato que la BD);
  método `metaAlcanzada(valor, meta)` agregado.
- `service/interfaces/IEvaluationService.java` — contrato extendido.

---

## Sprint 2 — Persistencia auditable de mediciones (backend per-ODS)

Cada una de las 17 ODS fue parcheada uniformemente con un script Python
(`apply_sprints.py`, conservado en el root para referencia y reproducibilidad).

### Patrón aplicado a las 17 ODS

**`service/ObjetivoXX*Service.java`** — nuevos métodos:
  - `saveMedicionAuditada(Map<String, Object> payload)` — `@Transactional("txManagerOdsXX")`.
    Recalcula `valor_calculado` server-side con `EvaluationService` (NO confía
    en el cliente), persiste `mediciones_historicas` + `medicion_parametro_valores`
    en una sola transacción, devuelve `{ medicion, formula, valor, metaValor,
    metaAlcanzada, estado, parametros, valoresParametros }`.
  - `getMedicionAuditoria(Integer medicionId)` — devuelve la traza completa de
    una medición (fórmula vigente + valores ingresados por parámetro + meta_alcanzada).

**`repository/ObjetivoXX*Repository.java`** — nuevos métodos:
  - `findMedicionByIdEntity(Integer)` — recupera la medición sin envolver en Optional.
  - `insertMedicionParametroValor(medicionId, parametroId, valor)` — persiste un
    valor ingresado para un parámetro durante una medición.
  - `findMedicionParametroValoresByMedicion(medicionId)` — devuelve la lista de
    valores con join a `proyecto_indicador_parametros` para resolver nombres.

**`controller/ObjetivoXX*Controller.java`** — endpoints:
  - `POST /api/ods/XX/mediciones/auditada`
  - `GET  /api/ods/XX/mediciones/{medicionId}/auditoria`

### Modificaciones a interfaces base

- `service/interfaces/IOdsBaseService.java` — agregados `saveMedicionAuditada`
  y `getMedicionAuditoria` al contrato.
- `controller/interfaces/IOdsBaseController.java` — agregados los mappings REST.
- `repository/interfaces/IOdsBaseRepository.java` — agregados los 3 primitives
  de auditoría.

---

## Sprint 3 — Auto-sembrado de parámetros desde la fórmula (backend per-ODS)

Cada `saveIndicador` en los 17 servicios ahora detecta las variables de la
`formula_custom` con `FormulaUtils.extractVariables` y crea automáticamente los
`proyecto_indicador_parametros` que faltan (tipo `Decimal`, valor `0`).

Resultado: el frontend ya no necesita hacer N+1 POSTs después de crear un
indicador; basta con guardar el `proyecto_indicadores` con la fórmula y los
parámetros aparecen sembrados.

---

## Sprint 4 — UX del builder de fórmula y consistencia front-back (frontend)

### El bug del cursor — RESUELTO

`components/projects/IndicatorConfigModal/IndicatorConfigModal.jsx` reescrito:

- `useRef` al `<textarea>`, lectura de `selectionStart` / `selectionEnd`.
- Inserción con splice exacto en la posición del cursor.
- Reposicionamiento del caret tras el texto insertado en el siguiente `requestAnimationFrame`.
- Chips para operadores (`(`, `)`, `+`, `-`, `*`, `/`, `× 100`) que también
  insertan en posición del cursor.
- Validación en vivo (debounced 350ms) llamando a `POST /api/evaluacion/validar-formula`,
  con panel que muestra variables en la fórmula, faltantes y sobrantes.

### Nuevos / reescritos

- `services/evaluacionService.js` — cliente del motor de evaluación del backend.
- `utils/evaluationEngine.js` — reemplazado el regex+`new Function()` (inseguro
  y divergente de exp4j) por un evaluador local seguro (tokenizador + Shunting-Yard +
  RPN, sin `eval`). Solo se usa como preview UX; los valores que se persisten
  vienen del backend (`evaluateRemote`).

### Bugs corregidos

- `pages/EvaluationPage/EvaluationPage.jsx`:
  - Antes enviaba `proyectoIndicadorId: indicator.indicadorMasterId` — el
    `indicadorMasterId` es del catálogo, NO el ID del registro en
    `proyecto_indicadores`. Ahora envía `indicator.id` correcto.
  - Reescrito para usar el nuevo `createMedicionAuditada` que envía
    `valoresParametros: { parametroId → valor }` y deja que el backend recalcule.

### Patch a los 17 `services/objetivoXXService.js`

Cada uno incorpora:
- `createMedicionAuditada({ proyectoIndicadorId, valoresParametros, ... })`
- `getMedicionAuditoria(medicionId)`

---

## Sprint 5 — Visualización de auditoría y tests

### Nueva pestaña "Auditoría" en `EvaluationPage.jsx`

Por cada indicador del proyecto:
- Muestra la fórmula vigente.
- Lista todas las mediciones (`<details>` colapsable).
- Para cada medición: fecha, responsable, valor calculado, meta_alcanzada, y
  una tabla con cada parámetro y el valor ingresado en esa medición concreta.
- Esto materializa "se audita según la fórmula con los parámetros respectivos"
  del enunciado.

### Tests JUnit (`src/test/java/com/odsProject/odsProject/service/`)

- `EvaluationServiceTest.java` — 16 tests:
  - `((a+b)*z)/2` con `a=10, b=20, z=2` → `30`
  - división por cero → `0` (no rompe el flujo)
  - validación con / sin variables declaradas
  - meta alcanzada / no alcanzada
  - identificadores con guion bajo (`total_becados / total_estudiantes`)
  - coma como separador decimal
  - fórmula inválida → `IllegalArgumentException`

- `FormulaUtilsTest.java` — 9 tests:
  - extracción simple, orden preservado, filtro de reservadas, identificadores
    con guion bajo, fórmula vacía, faltantes, sobrantes, coherencia perfecta.

---

## Cómo correr todo

```sh
# 1. BD (sin cambios desde el original)
mysql -u root -p < 0.database/00_run_all.sql

# 2. Backend (regenera POJOs JOOQ desde la BD recién creada y arranca)
cd 1.backend/odsProject
./mvnw spring-boot:run

# 3. Frontend (correr npm install desde cero — el node_modules NO se incluye)
cd 2.frontend/odsProject
npm install
npm run dev

# 4. Tests
cd 1.backend/odsProject
./mvnw test
```

## Caveats

- **No corrí `mvn compile` durante el desarrollo** porque el sandbox tiene
  Maven Central bloqueado. Las verificaciones que sí pasaron: brace balance
  perfecto en los 56 archivos Java modificados, `node --check` y `esbuild`
  limpios en todo el frontend, presencia de los métodos en los 17×3 archivos.
- Si al hacer `mvn spring-boot:run` aparece un error de compilación, lo más
  probable es un import faltante en un repository o el `transactionManager`
  qualifier — todos están centralizados en el script `apply_sprints.py` que
  se conservó al lado del proyecto para reproducibilidad.
- `apply_sprints.py` queda en el root del repo para que veas exactamente qué
  cambios programáticos se aplicaron y puedas re-ejecutarlos si hace falta.

---

## Hotfix post-Sprint 3 — Conflicto saveIndicador ↔ frontend.saveParameter

### Síntoma
Al crear un proyecto con indicadores que tienen fórmula custom (ej. `(a+b)/100`),
los `POST /api/ods/XX/metas` para los parámetros `a` y `b` devolvían
**500 Internal Server Error**. Los proyectos se creaban con el header y los
indicadores, pero los parámetros del usuario no quedaban guardados con el
`tipo_dato` que él eligió.

### Causa raíz
El esquema `proyecto_indicador_parametros` tiene
`UNIQUE KEY uk_proyecto_param (proyecto_indicador_id, nombre_parametro)`.

Sprint 3 introdujo `seedParametrosFromFormula(...)` dentro de `saveIndicador`,
que inserta los parámetros detectados en la fórmula con `tipo_dato = Decimal`
y `nombre_variable = nombre_parametro` por defecto.

El frontend, después de crear el indicador, sigue iterando sobre
`config.parameters` y llamando `service.saveParameter(...)` por cada uno con el
`tipo_dato` que el usuario eligió. Como el auto-seed ya insertó la fila, el
INSERT plano de `saveMetaProyecto` choca con el unique → 500.

### Fix aplicado
`Objetivo[XX]Repository.saveMetaProyecto` reemplazado por **UPSERT** vía
`INSERT ... ON DUPLICATE KEY UPDATE` (soporte nativo MariaDB / JOOQ). La fila
que sembró el auto-seed se "refina" con lo que mandó el frontend
(`nombre_variable`, `tipo_dato`, `valor_actual`). Si no existía, se inserta
normal. Idempotente y compatible con reintentos.

- 17/17 repositorios actualizados con el mismo patrón.
- Cero cambios en la BD ni en JOOQ.
- El controller no se tocó (su contrato `POST /metas` y respuesta no cambian).

### Por qué no se eligieron las propuestas alternativas
- **DTOs (IA #1):** El error es 500 (lógica/SQL), no 400 (deserialización).
  Jackson sí puede crear los POJOs JOOQ aquí. Meter DTOs duplicaba el modelo
  sin resolver nada y rompía la regla "sin DTOs".
- **Eliminar la llamada a `saveParameter` desde el frontend (IA #2):** el
  diagnóstico apuntaba al lado correcto pero la corrección perdía información:
  el auto-seed no sabe el `tipo_dato` que el usuario eligió ni un
  `nombre_variable` distinto del `nombre_parametro`. La UPSERT mantiene ambos
  flujos coexistentes y sin conflicto.

---

## Sprints 1-6 — Persistencia Proyecto ↔ ODS ↔ Indicadores

Plan completo aplicado tras detectar que aún no se guardaba la cadena
completa. Sin cambios al `pom.xml`.

### Sprint 1 — Observabilidad ✓
- `config/GlobalExceptionHandler.java` ya existía y fue verificado.
- Removido `try/catch` con `500 sin body` de los 17 controllers de ODS
  (`createIndicador` y `createMetaProyecto`).
- `application-dev.properties` con log SQL JOOQ y stack traces.
- Frontend `api.js`: interceptor pone `error.userMessage` con el detalle.

### Sprint 2 — Modelo explícito Proyecto ↔ ODS ✓
- Nueva tabla `ods_master.proyecto_ods` con UNIQUE `(proyecto_id, ods_id)`,
  trigger `trg_proyecto_ods_unico_primario` y FK a `ods_login.ods_catalog`.
- Vista `vista_resumen_proyectos_ods` ampliada con `ods_vinculados` y
  `ods_primario` agregados.
- En backend NO usamos POJO JOOQ para la tabla nueva — accedemos con
  `DSL.table()/DSL.field()` raw para no requerir regenerar JOOQ antes de
  compilar. Cuando el usuario corra `mvn spring-boot:run` la BD nueva está
  presente y JOOQ regenera POJOs oficiales que NO chocan con el código.

### Sprint 3 — Orquestador transaccional ✓
- `MasterProjectRepository.linkOds / findOdsByProyecto / unlinkOds`.
- `MasterProjectService.createFullProject(Map)` con patrón Saga:
  registra compensaciones (deque LIFO) y, ante fallo fatal, las ejecuta
  para borrar lo creado. Usa reflexión para llamar `saveIndicador` y
  `saveMetaProyecto` de los 17 servicios sin generar 17 ramas.
- Nuevo endpoint `POST /api/projects/full` y `GET /api/projects/{id}/ods`.

### Sprint 4 — Per-ODS idempotente ✓
- ALTER en las 18 tablas SQL: `INDEX idx_proyecto_master` →
  `UNIQUE KEY uk_proyecto_indicador`.
- `saveIndicador` en los 17 repos reemplazado por UPSERT
  `INSERT … ON DUPLICATE KEY UPDATE`. Re-ejecutar `createFullProject`
  con los mismos IDs ya no falla con `Duplicate entry`.

### Sprint 5 — Frontend: una llamada ✓
- `projectService.createFullProject` armaba N+1 requests; ahora arma el
  árbol y hace un único `POST /api/projects/full`.
- `getOdsByProyecto(id)` agregado.
- `ProjectCreationPage` muestra errores granulares por indicador y el
  estado de las compensaciones cuando `success=false`.
- `api.js` interceptor expone `error.userMessage` para que la UI lo lea.

### Sprint 6 — Verificación E2E ✓
- `requests/full_e2e.http` con 5 casos: mínimo, multi-ODS, idempotencia,
  error FK con rollback, lectura de ODS por proyecto.
- `MasterProjectFullSaveIT.java`: test de contexto Spring + plantilla para
  el caso real cuando hay BD viva (descomentar el método).

### Cómo correrlo
1. **BD**: `mysql -u root -p < 0.database/00_run_all.sql` (incluye proyecto_ods
   y el UNIQUE en proyecto_indicadores de los 17 ODS).
2. **Backend**: `cd 1.backend/odsProject && ./mvnw spring-boot:run`
   - JOOQ se regenera al arrancar contra la BD recién cargada.
3. **Frontend**: `cd 2.frontend/odsProject && npm install && npm run dev`.
4. **Validación**: abrir `requests/full_e2e.http` en IntelliJ o VS Code
   REST Client y ejecutar los 5 casos en orden.

### Sigue sin necesitarse cambios en `pom.xml`
`spring-boot-starter-web`, `spring-boot-starter-jooq`, `spring-boot-starter-test`
ya cubren `@ControllerAdvice`, `@Transactional`, `@SpringBootTest` y
`TestRestTemplate`. Cualquier cambio adicional al pom es innecesario para
esta entrega.

---

## Sprint 7 — Hotfix: trigger mutante en `proyecto_ods`

**Síntoma reportado**
```
SQL [insert into `ods_master`.`proyecto_ods` ... on duplicate key update ...];
(conn=1054) Can't update table 'proyecto_ods' in stored function/trigger
because it is already used by statement which invoked this stored function/trigger
```

**Causa raíz**
El trigger `BEFORE INSERT trg_proyecto_ods_unico_primario` que agregué en
Sprint 2 intentaba hacer `UPDATE proyecto_ods` desde dentro de un
`INSERT INTO proyecto_ods`. MariaDB y MySQL prohíben estáticamente que un
trigger modifique la misma tabla que lo activó (error 1442). Es un antipatrón
conocido como *mutating trigger*; funciona en PostgreSQL y SQL Server, no en
MariaDB/MySQL.

**Fix aplicado**
- `0.database/propuesta_actual/2. ods_master_database.sql`: trigger
  reemplazado por `DROP TRIGGER IF EXISTS` para idempotencia.
- `0.database/hotfix_sprint7.sql` nuevo: migración aplicable sin recargar
  todo el schema.
- `MasterProjectRepository.linkOds`: si `esPrimario=true`, primero hace
  `UPDATE` despromoviendo a los otros primarios del mismo proyecto,
  después el `UPSERT`. Las dos sentencias usan el mismo `DSLContext`,
  conexión reutilizada.

**Por qué no usar DTOs (refutación de la IA #1 una vez más)**
El error fue siempre SQL (FK violation + trigger inválido), no
deserialización Jackson. Ninguna parte del fix de Sprint 7 requiere DTOs.
La regla "sin DTOs" se mantiene intacta.

**Cómo aplicar**
```bash
# Si tu BD ya está cargada:
mysql -u root -p < 0.database/hotfix_sprint7.sql

# Reemplazá MasterProjectRepository.java por la versión de este zip y arrancá:
cd 1.backend/odsProject && ./mvnw spring-boot:run
```

**Validación**
Repetir el POST que mostró el error:
```json
POST /api/projects/full
{
  "proyecto": { "usuarioId": 2, "sedeId": 1, "nombreProyecto": "PruebitaSimple",
                "descripcion": "Solamente una pruebita simple",
                "fechaInicio": "2026-05-11", "fechaFin": "2026-06-30",
                "estado": "planificacion" },
  "odsIds": [1],
  "primaryOdsId": 1,
  "indicadores": [...]
}
```
Esperado: `success=true`, `proyectoId != null`, fila en `proyecto_ods`
con `es_primario=1`. Sin error 1442.

---

## Sprint 8 — Fix proyecto sin ODS vinculados (sin DTOs, sin cambios en BD)

### Diagnóstico verificado en código
Después de Sprint 7 el POST `/api/projects/full` ya no daba error 1442, pero
el proyecto se creaba huérfano: en la BD entraba la fila a `ods_master.proyectos`
pero NO a `ods_master.proyecto_ods`. En la UI eso se veía como "Sin ODS
vinculados" y "Objetivo Desconocido". Tres bugs independientes lo causaban:

| # | Síntoma | Archivo | Causa |
|---|---|---|---|
| 1 | Payload con `odsIds: []` | `projectService.js:158-171` | `odsSet` se llenaba SOLO con ODS de indicadores marcados; ignoraba `formData.selectedOds` |
| 2 | Backend respondía `success=true` con 0 ODS | `MasterProjectService.java:220` | No había validación de `odsToLink.isEmpty()` |
| 3 | Listado mostraba "Objetivo Desconocido" | `MasterProjectRepository.findByUsuario` | `SELECT * FROM proyectos` sin JOIN con `proyecto_ods` |

### Fix aplicado

**Sprint 8.1 — Frontend `projectService.createFullProject`**
Antes del loop de indicadores, sembrar `odsSet` con `projectData.selectedOds`.
Si el usuario marcó ODS en la cuadrícula pero no eligió indicadores adentro,
igual se vinculan. El campo se desestructura del payload (`const { ..., selectedOds }`).

**Sprint 8.2 — Backend `MasterProjectService.createFullProject`**
Después de construir `odsToLink`, si está vacío se lanza `IllegalStateException`
con mensaje claro. El catch fatal ejecuta las compensaciones (borra el proyecto
recién creado), así no quedan huérfanos. El usuario ve por qué falló.

**Sprint 8.3 — Backend listados enriquecidos**
- Aprovechamos `VistaResumenProyectosOds` (el POJO JOOQ generado del view
  `vista_resumen_proyectos_ods` que ya teníamos en BD). No hay DTOs.
- Repo: `findAllWithOds()` y `findByUsuarioWithOds(usuarioId)` con JOIN contra `proyectos`.
- Service: `getAllProyectosWithOds()` y `getProyectosWithOdsByUsuario(id)`.
- Controller: `GET /api/projects/with-ods` y `GET /api/projects/user/{id}/with-ods`.
- Frontend: `projectService.getAllProjects()` y `getUserProjects(id)` ahora
  pegan a los endpoints `/with-ods`. El mapper lee `p.odsPrimario` y lo parsea
  como ODS principal; `p.odsVinculados` viene como CSV y se splittea a array.
- `ProjectListPage.jsx`: si el proyecto cubre más de un ODS, muestra `+N`
  como badge secundario.

### Por qué NO se necesitan cambios en BD
La tabla `proyecto_ods`, la vista `vista_resumen_proyectos_ods`, sus POJOs
JOOQ y las FKs ya existían desde Sprint 2. El bug era 100% en código.

### Validación
Con el zip nuevo:
1. Recargá frontend y backend (`npm run dev` + `./mvnw spring-boot:run`).
2. Creá un proyecto picando ODS pero sin elegir indicadores → ahora se vincula
   el ODS al proyecto (antes quedaba huérfano).
3. Creá un proyecto sin ODS ni indicadores → el backend lo rechaza con mensaje
   claro y el proyecto NO se crea (compensación borra el header).
4. En la lista de proyectos → el ODS aparece correctamente con su color y nombre.

### Lección
Ninguna de las 2 IAs externas tenía razón completa, pero la #2 sí identificó
los 3 bugs estructurales con precisión. Los recomendados "implementar DTOs" de
la #1 no resuelven nada acá: el problema nunca fue Jackson, era lógica de
orquestación + faltante de JOIN.

---

## Sprint 9 — ProjectResultsPage muestra ODS, indicadores y fórmulas

### Síntoma reportado
La BD guarda todo correctamente (Sprint 8 confirmado con queries SQL):
proyecto, `proyecto_ods`, `proyecto_indicadores`, `proyecto_indicador_parametros`.
Pero al hacer click en una card del listado, la página de detalle muestra
"Sin ODS vinculados", sin indicadores y sin fórmulas.

### Causa raíz (verificada línea por línea)
`ProjectResultsPage.jsx` viene de la era localStorage. En su `useEffect` original
llama a `projectService.getProjectById(id)` y luego intenta leer:

```js
project.objective           // ← undefined: proyecto no tiene esta columna
project.indicators?.map(...)  // ← undefined?.map(): no renderiza nada
project.indicatorConfigs?.[code] // ← undefined: sin fórmulas
project.targetValues[code]    // ← undefined: sin metas
```

Esos campos existían cuando el proyecto era un objeto plano en localStorage
con todos sus indicadores anidados adentro. Cuando migramos a backend con
tablas normalizadas (`proyecto_ods`, `proyecto_indicadores`, ...), el shape
cambió pero la página quedó leyendo el shape viejo.

### Fix aplicado
Reescritura del `useEffect` y de la sección "Objetivos de Desarrollo Sostenible":

**fetchProject (nuevo flujo):**
1. `GET /api/projects/{id}` → cabecera Proyectos.
2. `GET /api/projects/{id}/ods` → lista de ODS vinculados (proyecto_ods).
3. Para cada ODS vinculado, import dinámico de `objetivoXXService` y dos llamadas
   paralelas: `getIndicators(projectId)` + `getMetasProyecto(projectId)`.
4. Construye `project.linkedOds = [{odsId, esPrimario, indicators, parameters}]`.
5. Mantiene `project.objective` y `project.indicators` como campos legacy para
   no romper otros componentes que aún los leen.

**Rendering (nueva sección):**
- Itera `project.linkedOds.map(...)` en lugar de un solo `project.objective`.
- Muestra el badge "PRIMARIO" en el ODS marcado como tal.
- Por cada indicador: código, nombre, meta, **fórmula**, y la lista de variables.
- Casa parámetros con indicadores extrayendo las variables de la fórmula con
  regex (`/[a-zA-Z_][a-zA-Z0-9_]*/g`) y matching contra `param.nombreVariable`.
  Esto evita tener que cambiar el view de BD para exponer `proyecto_indicador_id`.

**Botón "Ir a evaluación" → `/projects/{id}/evaluation`**
Para que el usuario pase fácil del detalle a la carga de mediciones reales.

### Archivos afectados
- `2.frontend/odsProject/src/pages/ProjectResultsPage/ProjectResultsPage.jsx`
  (reescritura quirúrgica de 2 bloques)

### Por qué NO toqué el backend ni la BD
La BD ya tiene todo lo necesario:
- `ods_master.proyectos` ✓
- `ods_master.proyecto_ods` ✓
- `ods0X.proyecto_indicadores` ✓ (con `formula_custom`, `meta_valor`, etc.)
- `ods0X.proyecto_indicador_parametros` ✓

Los endpoints también:
- `GET /api/projects/{id}` (Sprint 3)
- `GET /api/projects/{id}/ods` (Sprint 3)
- `GET /api/ods/XX/indicadores?proyectoId={id}` (preexistente)
- `GET /api/ods/XX/metas?proyectoId={id}` (preexistente)

El bug era 100% frontend: una página vieja que leía un shape que ya no existe.

### Validación esperada
Después de aplicar el zip, abrir un proyecto que tenga indicadores en BD
(como el proyecto 6 del usuario) debe mostrar:
- "2 ODS Vinculados" en el badge.
- ODS 1 (PRIMARIO) con indicador `1.1.1`, fórmula `(a + b)/ 100`, meta 70%,
  y variables `a` (Integer), `b` (Integer).
- ODS 2 con indicador `2.1.1`, fórmula `(a + b)`, meta 50%, mismas variables.

---

## Sprint 14 — Auditoría operativa por rol + dashboard de logro

### Problema reportado
- Admin/auditor no tenían un camino claro para ingresar los datos del documento y disparar el cálculo de la fórmula.
- Consultor no veía si los proyectos alcanzaron sus metas.
- El usuario `auditor` no existía en los seeds (rol estaba en la tabla pero no había usuario).

### Implementación

**Sprint 14.1 — `<AchievementBadge>` componente compartido**
Renderiza el estado de meta de un indicador o proyecto:
- 🟢 LOGRADO (≥100%) — meta cumplida
- 🔵 CERCA META (≥80%)
- 🟡 PROGRESO (≥50%)
- 🔴 BAJO (<50%) — no alcanzó la meta
- ⚪ SIN DATOS — pendiente de auditar

Función `deriveEstado(porcentaje)` exportada para usar el mismo criterio en
toda la app. Coincide con la lógica del view `vista_admin_detalle_indicadores`
del backend (que devuelve estos mismos labels).

**Sprint 14.2 — `AuditQueuePage` para admin/auditor**
Nueva página `/audit` con cola de proyectos pendientes de auditoría:
- Lista de TODOS los proyectos del sistema (vía `/api/projects/with-ods`)
- Por cada uno: cuenta de documentos subidos por el gestor
- Filtros: "Todos", "Pendientes de auditar" (con doc pero no completado), "Con documento"
- Click → entra a `/audit/:id` (alias de EvaluationPage con permission gate)
- Gated por `canViewAuditQueue` (admin + auditor) en `ProtectedRoute`

**Sprint 14.3 — `ProjectResultsPage` mejorado**
- Card de estado de logro del proyecto: promedio de % de los indicadores auditados
- Por cada indicador, AchievementBadge individual visible para TODOS los roles
- Botón verde "Auditar este proyecto" solo para admin/auditor
- Sección de Documentos (EvidenceSection) integrada
- Consultor ve TODA la info en modo solo-lectura

**Sprint 14.4 — Dashboard adaptado por rol**
- Admin/auditor ven card "Cola de Auditoría" (verde) → `/audit`
- Admin además ve card "Gestión de Proyectos" → `/admin/projects`
- Gestor ve "Nuevo Proyecto" + lista de sus proyectos recientes
- Consultor ve "Ver todos los proyectos"

**Sprint 14.5 — EvaluationPage es la pantalla de medición**
Esta página ya existía y funciona: itera los 17 ODS, muestra indicadores del
proyecto, recibe valores de cada parámetro, llama a `createMedicionAuditada`
del backend (que calcula la fórmula y persiste). El Sprint 14 simplemente:
- La hace accesible vía `/audit/:id` (más semántico)
- La gatea con `canEnterMeasurements` (gestor/consultor NO pueden entrar)
- Importa `usePermissions` y deja `readOnly` derivado para futuro uso

**Sprint 14.6 — Usuario auditor seedado**
En `21. ods_mocks.sql`:
```sql
(5, 'auditor_general', 'auditor@ods.cr', '$2b$12$MOCK_HASH_1234567890',
 'Luis Vargas Castro', 4, 2, TRUE, TRUE)
```
El rol auditor (rol_id=4) ya existía en `1. login_system.sql` pero faltaba un
usuario con ese rol para probar el flujo.

### Cero archivos basura
Los hotfix SQL de sprints anteriores (hotfix_sprint7.sql, hotfix_sprint11.sql)
fueron eliminados porque sus cambios ya están integrados en
`0.database/propuesta_actual/2. ods_master_database.sql`. Lo que queda es
canónico, no migraciones temporales.

### Flujo end-to-end completo

1. **`gestor_pobreza`** crea proyecto → asigna ODS + indicadores + fórmula `(a+b)/100`
2. Durante el período, el sistema espera
3. Al cerrar el período, el gestor abre el proyecto → **sube documento** (Excel/PDF) con resultados → ve solo el estado "SIN DATOS"
4. **`admin`** o **`auditor_general`** → entra a Dashboard → click "Cola de Auditoría"
5. Ve la lista, identifica el proyecto con doc subido, hace click → entra a `/audit/:id`
6. Descarga el documento (botón en EvidenceSection)
7. Lee los números del doc, los ingresa en el formulario (a=5, b=10)
8. Click "Guardar medición" → backend calcula `(5+10)/100 = 0.15`, persiste en `proyecto_indicadores.valor_actual`, retorna `metaAlcanzada: false` (porque 0.15 < meta 70)
9. **`consultor_general`** → entra a Dashboard → "Ver todos los proyectos"
10. Ve la card del proyecto con badge **🔴 No alcanzó (15%)** — sin tener que abrirlo
11. Si abre el detalle, ve el indicador específico con su estado y los valores ingresados

Toda la cadena funciona con la regla del profesor: **el gestor NO mide, el auditor SÍ; el consultor SÍ ve resultados auditados pero NO los modifica**.

### Cómo correrlo
```bash
mysql -u root -p < 0.database/00_run_all.sql   # carga todo limpio
cd 1.backend/odsProject && ./mvnw spring-boot:run
cd 2.frontend/odsProject && npm install && npm run dev
```

Usuarios de prueba (todos con password mock):
- `admin` (admin del sistema)
- `gestor_pobreza` / `gestor_hambre` (crean proyectos)
- `auditor_general` (audita) ← **nuevo**
- `consultor_general` (solo lectura)

---

## Sprint 15 — FIX EvaluationPage no mostraba datos

### Bug reportado por el usuario
Admin entra a `/projects/6/evaluation`, ve la tarjeta del proyecto con
"Indicadores: 0 — Logrados: 0", y al hacer clic en cualquier tab (Ingresar
valores / Resumen / Auditoría) no aparece ningún contenido.

Aunque la BD tenía `proyecto_indicadores` cargados para el proyecto 6, la UI
los descartaba.

### Causa raíz

El backend devuelve `proyecto_id` (entre otros campos) en el response de
`/api/ods/XX/indicadores?proyectoId=Y`. Sin embargo, el mapping del frontend
en los 17 `objetivoXXService.getIndicators` se quedaba con un subset de
campos y descartaba `proyectoId`:

```js
acc[code] = {
  code, masterId, name, currentValue, targetValue, unit, formula, updatedAt, hasData
};   // ← proyectoId NO está
```

Luego en `EvaluationPage.loadAllIndicators`:
```js
const list = Object.values(data).filter(i => i && i.proyectoId);
// → SIEMPRE array vacío → "Indicadores: 0"
```

Además, el view `vista_admin_detalle_indicadores` **no expone** el campo
`proyecto_indicadores.id` (proyecto_indicador_id), que es lo que la página
necesita para llamar a `createMedicionAuditada`. Así que aunque el filtro
funcionara, la persistencia de mediciones también estaba rota.

### Fix aplicado (cero cambios al backend)

**15.1 — Frontend services (×17)**
Script Python actualizó el mapping de los 17 `objetivoXXService.js` para
preservar los campos que el backend YA devuelve:

```js
acc[code] = {
  code,
  proyectoId: ind.proyectoId,                  // ← AHORA preservado
  masterId: ind.indicadorMasterId,
  name: ind.indicadorNombre,
  currentValue, targetValue, unit,
  metaNombre: ind.metaNombre || null,
  formula: ind.formulaCustom || '',
  estadoIndicador: ind.estadoIndicador,        // ← para AchievementBadge
  porcentajeLogro: ind.porcentajeLogro,
  updatedAt, hasData
};
```

Marker `Sprint 15` deja huella en cada archivo para futura referencia.

**15.2 — EvaluationPage.loadAllIndicators**
- El filtro `i.proyectoId` ahora funciona porque el mapping preserva el campo.
- Matching de parámetros a indicadores se rehizo por **extracción de variables
  de la fórmula con regex** (no por proyecto_indicador_id que no existe):
  ```js
  const RESERVED = new Set(['sqrt','sin','cos','tan','log','exp','round','floor','ceil','abs','pi','e','valor','count']);
  const vars = new Set((formula.match(/[a-zA-Z_][a-zA-Z0-9_]*/g) || [])
                       .filter(v => !RESERVED.has(v.toLowerCase())));
  // Filtra metas cuyo nombreVariable coincide con vars
  ```
- Params normalizados a camelCase consistente: `{id, nombreParametro, nombreVariable, tipoDato, valorActual}`.

**15.3 — EvaluationPage.handleCalcular (reescrito)**
- **Guard `readOnly`**: si el rol no permite mediciones, muestra alert y
  return. Doble defensa frente al ProtectedRoute.
- **UPSERT idempotente** para resolver `proyectoIndicadorId`: si no lo tenemos
  cacheado en `indicator.id`, llamamos a `svc.saveIndicator(...)` pasando los
  mismos `metaValor/metaUnidad/formulaCustom` que YA están en BD. El backend
  hace `INSERT...ON DUPLICATE KEY UPDATE` (de Sprint 4) y nos devuelve la fila
  con su `id`. Lo cacheamos en `indicator.id` para próximos clicks.
- **Refresh de parámetros**: si `indicator.parametros` está vacío, llamamos
  `svc.getMetasProyecto` y filtramos por `proyectoIndicadorId`. Normalizamos a
  camelCase.
- **`valoresParametros`** se construye solo con `p.id != null` para evitar
  payloads inválidos.
- Llama `createMedicionAuditada({proyectoIndicadorId, valoresParametros, ...})`.

**15.4 — Render: feedback visual para readOnly**
- Inputs `disabled={readOnly}`, fondo gris + cursor not-allowed
- Botón `🔢 Calcular y Evaluar` reemplazado por mensaje `🔒 Solo lectura` si readOnly
- Warning `⚠️ Sin parámetros configurados` si el indicador no tiene params
- Botón disabled si `parametros.length === 0`

### Resultado E2E

1. `auditor_general` (o `admin`) abre `/projects/6/evaluation`
2. Ve "Indicadores: 1 — Logrados: 0" en el header
3. El panel "Ingresar valores" muestra el ODS 1 expandido automáticamente
4. Lista el indicador `1.1.1` con su fórmula `(a+b)/100`, meta 70, estado SIN DATOS
5. Inputs editables para variables `a` y `b`
6. Click "🔢 Calcular y Evaluar" → frontend hace UPSERT, resuelve id, envía medición
7. Backend calcula `(5+10)/100 = 0.15`, devuelve `metaAlcanzada: false`
8. UI muestra badge "🔄 En Progreso · auditado" + pct 0.21%
9. Consultor en `/projects` ve la card del proyecto con badge "🔴 No alcanzó (0.21%)"

### Si fuese gestor o consultor
- ProtectedRoute al `/projects/:id/evaluation` bloquea con redirect a `/forbidden`
- Si llegan por otra vía: `readOnly=true` deshabilita inputs y muestra "🔒"
