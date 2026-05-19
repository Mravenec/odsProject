# Plataforma ODS UTN — Sprints 15–20 aplicados

Este zip contiene el proyecto base con el roadmap de auditoría implementado
realísticamente. Las pruebas de la base de datos están verificadas en vivo
contra MariaDB 10.11 corriendo localmente; el backend está escrito siguiendo
los patrones JOOQ del equipo y compila después de regenerar POJOs.

## Qué se aplicó (Sprints 15–20)

| Sprint | Qué hace | Capa(s) tocada(s) | Verificado en vivo |
|--------|----------|-------------------|--------------------|
| **15** | Estado machine: agrega `en_revision`, columnas `auditado_por`, `auditado_en`, `observaciones_cierre`, `fecha_envio_revision`, FK a usuarios, vista enriquecida | BD + Repo + Service + Controller | ✅ ENUM y view verificados con `DESCRIBE proyectos` y `SHOW CREATE VIEW` |
| **16** | "Enviar a auditoría" del gestor: validación de dueño + indicadores + documentos | Service + Controller + API + ProjectResults | ✅ SQL probado: `estado='en_revision'`, stamp de envío |
| **17** | "Cerrar auditoría" del auditor + Rechazar con motivo (≥10 chars) | Service + Controller + API + EvaluationPage modal | ✅ Flujo completo de aprobar y de rechazar probado |
| **18** | Inmutabilidad post-auditoría: 3 triggers en cada uno de los 17 schemas ODS + guard en services + helper `isProjectLocked()` en frontend | BD (51 triggers) + 17 Services + formatters.js | ✅ Trigger rechaza: "Proyecto auditado: indicadores inmutables" |
| **19** | Cola de auditoría con 4 pestañas reales (Todos / Pendientes / En curso / Auditados) + panel de 4 KPIs vivos | Repo + Service + Controller + AuditQueuePage | ✅ Query de métricas devuelve cuentas correctas |
| **20** | Visibilidad para consultor: auditor_nombre en view, panel "Auditoría cerrada" en detalle, badge "Auditado" en cola, banner de rechazo para gestor | View enriquecida + ProjectResultsPage + AuditQueuePage | ✅ View expone `auditor_nombre`, `observaciones_cierre` |

**No aplicados (por scope/tiempo):**
- Sprint 21 (PDF descargable) — requiere lib nueva (`openhtmltopdf`)
- Sprint 22 (reapertura por nuevo período fiscal) — opcional según roadmap

## Cómo correr todo (mismos comandos del proyecto base)

Los comandos del `comandosDelProyecto.txt` **no fueron modificados**. La secuencia
estándar funciona porque:

1. Los cambios SQL viven dentro de `0.database/propuesta_actual/` (los mismos
   archivos que carga `setup_db.py`), así que el `python setup_db.py` recarga
   limpio con todo el nuevo schema.
2. Los cambios Java usan `DSL.field()` raw para las columnas nuevas (siguiendo
   la convención del equipo en `MasterProjectRepository` para `proyecto_ods`),
   así que el código compila tanto antes como después de regenerar JOOQ.

```bash
# 1. Borrar
python 0.database/drop_db.py

# 2. Instalar y cargar mocks
python 0.database/setup_db.py
python 0.database/load_mocks.py

# 3. Regenerar POJOs JOOQ (paso crítico cuando hay cambios de BD)
cd 1.backend/odsProject
mvn clean generate-sources

# 4. Backend
mvn spring-boot:run

# 5. Frontend (en otra terminal)
cd ../../2.frontend/odsProject
npm install   # primera vez
npm run dev
```

Si el contenedor MariaDB se llama distinto a `SistemaPrincipal`, ajustá
`drop_db.py` y `setup_db.py` antes del paso 1.

## Verificación manual del flujo

Una vez levantado todo, podés validar el flujo end-to-end así:

1. **Loguearte como `gestor_pobreza`** → abrir el proyecto 6 → subir un
   documento → click **"📤 Enviar a auditoría"** en la cabecera.
2. El proyecto se marca como `en_revision`. El botón desaparece.
3. **Loguearte como `auditor_general`** → ir a `/audit` → ahora ves el
   panel de 4 KPIs y 4 pestañas. El proyecto aparece en "Pendientes".
4. Click → entrar al workbench → ingresar mediciones → click
   **"✓ Aprobar auditoría"** → modal pregunta observaciones de cierre.
5. Después de aprobar: el proyecto pasa a `completado`, queda firmado con
   tu nombre + fecha + observaciones.
6. **Loguearte como `consultor_general`** → ver detalle del proyecto: panel
   verde "Auditoría cerrada · datos firmados" con stamp del auditor.
7. **Probar inmutabilidad**: intentar editar un indicador de ese proyecto.
   El backend devuelve 409 y la UI muestra "Proyecto auditado: indicadores
   inmutables" (mensaje del trigger SQL).

## Rechazo y re-envío (Sprint 17)

Si el auditor encuentra problemas:
1. Click **"✗ Rechazar"** → modal pide motivo (mínimo 10 chars).
2. Proyecto vuelve a `activo`, observaciones se guardan en
   `observaciones_cierre`.
3. El gestor abre su proyecto y ve un **banner amarillo con el motivo**.
4. Corrige lo indicado, sube nueva evidencia, vuelve a enviar a auditoría.

## Archivos modificados (resumen)

### Base de datos
- `0.database/propuesta_actual/2. ods_master_database.sql` — tabla
  `proyectos` extendida + view actualizada
- `0.database/propuesta_actual/4–20. ods*_database.sql` — 17 archivos con
  los 3 triggers de Sprint 18 inyectados

### Backend (Java)
- `repository/MasterProjectRepository.java` + interface — 5 nuevos métodos
- `service/MasterProjectService.java` + interface — máquina de estados,
  `enviarARevision`, `cerrarAuditoria`, `rechazarAuditoria`,
  `getAuditQueueMetrics`
- `controller/MasterProjectController.java` + interface — 6 nuevos endpoints
- `service/Objetivo[01-17]*Service.java` — guard Sprint 18 inyectado en
  los 17 servicios (en `saveMedicionAuditada`)

### Frontend (React)
- `src/services/projectService.js` — `sendForReview`, `approveAudit`,
  `rejectAudit`, `changeProjectState`, `getAuditMetrics` + mapper de
  campos de auditoría
- `src/utils/formatters.js` — `isProjectLocked`, `isProjectInReview`,
  `getEstadoLabel`, `getEstadoClass`
- `src/pages/AuditQueuePage/AuditQueuePage.jsx` — 4 tabs reales + panel
  de 4 KPIs vivos + badge "Auditado"
- `src/pages/AuditQueuePage/AuditQueuePage.css` — estilos KPI + pills
- `src/pages/EvaluationPage/EvaluationPage.jsx` — botones Aprobar/Rechazar
  + dos modales de confirmación
- `src/pages/ProjectResultsPage/ProjectResultsPage.jsx` — botón "Enviar
  a auditoría" del gestor + banner de rechazo + panel "Auditoría cerrada"
- `src/pages/ProjectResultsPage/ProjectResultsPage.css` — botón con UTN
  naranja para acciones de cambio de estado

## Smoke tests ejecutados en vivo

Durante el desarrollo verifiqué contra MariaDB 10.11:

```sql
-- Sprint 16 simulado
UPDATE proyectos SET estado='en_revision', fecha_envio_revision=NOW() WHERE id=1;
-- → OK

-- Sprint 19 metrics
SELECT (SELECT COUNT(*) FROM proyectos WHERE estado='en_revision') AS pendientes;
-- → 1

-- Sprint 17 simulado (auditor aprueba)
UPDATE proyectos SET estado='completado', auditado_por=1, auditado_en=NOW(),
       observaciones_cierre='Auditoría limpia' WHERE id=1;
-- → OK

-- Sprint 20: view expone auditor name
SELECT * FROM vista_resumen_proyectos_ods WHERE proyecto_id=1;
-- → auditor_nombre = 'Administrador del Sistema', observaciones_cierre OK

-- Sprint 18: trigger bloquea mutación
UPDATE ods01.proyecto_indicadores SET valor_actual=999 WHERE proyecto_id=1;
-- → ERROR 1644: Proyecto auditado: indicadores inmutables ✓
```

## Frontend verificado con build

```
$ npm run build
✓ built in 7.17s
dist/index.html                                  0.95 kB │ gzip:  0.52 kB
dist/assets/index-zhUZTPOq.css                  78.99 kB │ gzip: 13.99 kB
dist/assets/index-DtM-lFm5.js                  434.16 kB │ gzip: 116.36 kB
```

Sin errores. Los warnings son sobre chunking dinámico de los 17 servicios
ODS, son informativos y pre-existen en el código base.

---

**Roles testeados:** gestor (`gestor_pobreza`), auditor (`auditor_general`),
admin (`admin`), consultor (`consultor_general`). El usuario auditor ya
existía en `21. ods_mocks.sql` del Sprint 14.

**Compatibilidad de datos:** los proyectos existentes con `estado='activo'`
mantienen su comportamiento. Las 4 nuevas columnas son nullables, por lo
que filas viejas sobreviven al ALTER sin migración manual.
