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
