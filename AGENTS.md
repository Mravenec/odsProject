# Agentes — ODS UTN Platform

Coordinación multi-agente del repo. Reglas Cursor: `linear-gate-obligatorio.mdc` + `linear-plan-html-obligatorio.mdc` + `linear-checklist-secuencial.mdc` + `linear-multiagente.mdc`. Pipeline completo: `_linear/README.md`.

## Inicio obligatorio (toda tarea de producto)

**Antes de editar código**, desde `_linear/`:

```bash
cd _linear
node scripts/sprint-next.mjs
```

- Si hay issue desbloqueado → trabajar **solo** ese ticket (checklist secuencial).
- Si no hay `sprint_<activo>.mjs` o `next` vacío → **no codear**: Fase 0–2 (limpieza → plan HTML Linear completo → `validate-plan-html.mjs` → aprobación → `create`).
- Si el issue está **bloqueado** (`blocks`) → esperar; otro agente/epic upstream debe cerrar primero.

Bypass Linear **solo** si el humano lo pide explícito en el mensaje (*sin linear*, *bypass linear*, *solo local*).

### Por qué Linear en multi-agente

| Sin Linear | Con Linear |
|------------|------------|
| Varios chats pisan el mismo archivo | Un issue activo por cadena; `blocks` fuerza orden |
| FE arranca antes del `.http` | GATE_HTTP en checklist + handoff |
| No hay testigo de quién terminó qué | `Done` + handoff desbloquea downstream |
| Commits sin trazabilidad | Issue ODS-N ↔ checklist ↔ estado |

## Roles

### Orquestador (`role:orchestrator`)

- Ejecuta Fases 0–4 y 6: limpieza, plan HTML (desde `_plantilla_ods.html`), validación, `sprint_<nombre>.mjs create`, cierre con `resumen_sprint_*.html`.

## Plan HTML — contrato Linear (Fase 2)

Todo `plan_sprint_<nombre>.html` es **casi un ticket de Linear**: no es solo el dominio del producto.

1. Copiar `_linear/plans/_plantilla_ods.html` (tiene esqueleto de secciones `Linear — …`).
2. Rellenar las 12 secciones listadas en `linear-plan-html-obligatorio.mdc`.
3. Validar antes de pedir aprobación o hacer `create`:

```bash
cd _linear
node scripts/validate-plan-html.mjs plans/plan_sprint_<nombre>.html
```

4. Humano responde **✅ APROBADO** en chat.
5. `node scripts/sprint_<nombre>.mjs create`

Referencia de calidad: `plans/plan_sprint_export_sodsi.html`. Gates N/A (sin BD) deben figurar explícitos; si hay `role:database`, incluir `drop_db` → `setup_db` → `load_mocks` en Testing.
- Bucle: `next` → asignar/reclamar issue → verificar Done + handoffs → `status`.
- No implementa código de producto salvo fixes mínimos del script `_linear/`.
- Usa `get_sprint_health` / `watchdog_check` si MCP Linear está activo.

### Database (`role:database`)

- Archivos en `0.database/propuesta_actual/`.
- **Un solo agente** corre `drop_db` → `setup_db` → `load_mocks` por sprint.
- Handoff a backend: marcar ítem 1 del issue GATE_BD/JOOQ downstream.

### Backend (`role:backend`)

- Código en `1.backend/odsProject/`.
- Orden por issue: IREPO → REPO → ISVC → SVC → ICTRL → CTRL → `.http`.
- GATE_HTTP obligatorio antes de que frontend empiece.
- Handoff: `handoff` al primer issue FE del epic cuando `.http` pasa 2xx.

### Frontend (`role:frontend`)

- Código en `2.frontend/odsProject/`.
- No iniciar si ítem 1 del issue dice «⏸ GATE_HTTP» sin `[x]`.
- Verificar `npm run build` en Testing antes de Done.

## Ciclo por issue (todos los roles)

```
next → claim_issue → get_issue_context
→ Doing: implementar + checklist ODS-N <n> (un ítem por vez)
→ Testing: pruebas locales según rol
→ handoff (si aplica) → state Done
```

## Paralelismo

| Escenario | Agentes |
|-----------|---------|
| Epic BE serial + Epic FE espera | 1 activo en BE; FE bloqueado hasta handoff |
| 2 epics sin choque de archivos | 2 chats Agent en paralelo |
| `drop_db` o mismo `.jsx` | 1 agente; el otro espera checklist cross-epic |

## Comandos (desde `_linear/`)

```bash
node scripts/sprint_<nombre>.mjs next
node scripts/sprint_<nombre>.mjs show ODS-N
node scripts/sprint_<nombre>.mjs checklist ODS-N <n>
node scripts/sprint_<nombre>.mjs handoff ODS-M 1
node scripts/sprint_<nombre>.mjs state ODS-N Testing
node scripts/sprint_<nombre>.mjs state ODS-N Done
```

MCP (si está configurado en Cursor): `claim_issue`, `ping_issue`, `get_issue_context`, `submit_for_review`, `fail_issue`.

## Prohibido

- Plan HTML sin secciones Linear (`validate-plan-html.mjs` falla).
- `sprint_*.mjs create` sin ✅ APROBADO y sin validación exitosa.
- Trabajar sin issue reclamado o sin `next` claro.
- Paralelizar issues de la misma cadena `blocks`.
- Cerrar trabajo sin Linear (`state Done` + checklist completo).
- Batch de checklist (`1,2,3` o `all`) — ver `linear-lib.mjs`.
