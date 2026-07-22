---
name: linear-orchestrator
description: >-
  Orquestación Linear ODS: higiene, Fase 0 cleanup (al trabajo nuevo), plan HTML,
  pedir ✅ APROBADO, create, next, cierre Fase 6 dejando resumen legible.
  Usar al iniciar trabajo de producto, cuando sprint-next está vacío, al cerrar
  un sprint, o antes de proponer un sprint nuevo.
---

# Orquestador Linear — ODS

## Cuándo aplicar

- Usuario pide feature, bug, mejora o «hazlo» sobre producto (`0.`/`1.`/`2.`).
- `sprint-next` sin issues desbloqueados.
- Sprint con todos los issues en Done.
- Usuario pide cleanup / preparar el siguiente sprint.

## Secuencia obligatoria (no saltear)

```
1. cd _linear && node scripts/validate-linear-hygiene.mjs
2. node scripts/sprint-next.mjs
3. Según resultado → tabla abajo
```

| Situación | Hacer |
|-----------|--------|
| Higiene fail (Fase 6 incompleta / multi-plan) | Completar resumen o limpiar según mensaje. Parar código. |
| Sprint cerrado con resumen legible + **nueva** instrucción de producto | **Fase 0 primero:** `cleanup` (issues **+ epic**) + borrar plan/resumen/script → luego plan nuevo |
| Issue ODS-N desbloqueado | `show` → checklist 1 ítem → implementar → `checklist N` → … → Done |
| Sin issues / sin sprint activo + pedido de feature | Fase 0 si hace falta → `plan_sprint_*.html` → `validate-plan-html.mjs` → **¿✅ APROBADO?** → `create` |
| Usuario dice Hazlo / fixea sin plan | Plan corto + link HTML + **¿✅ APROBADO?** No implementar aún |

## Frases que NO son bypass

- «Hazlo», «arranca», «rápido», «para la demo», «hotfix»
- Bypass solo: *bypass linear* | *sin linear* | *solo local* en el mensaje actual

## Cierre (Fase 6) — dejar legible

1. `resumen_sprint_<nombre>.html`
2. Epic → Completed
3. **NO** `cleanup` ni borrar artefactos
4. Decir al humano la ruta del resumen para que lo lea

## Fase 0 — al iniciar el próximo trabajo (no al cerrar)

1. `sprint_<anterior>.mjs cleanup` → borra issues **y** el epic en Linear (`projectDelete`; **no** solo archive — archive sigue en [Projects/all](https://linear.app))
2. Si quedan epics Completed/100% huérfanos: `node scripts/purge-completed-projects.mjs` (primero `--dry-run`)
3. Borrar `plan_sprint_` / `resumen_sprint_` / `sprint_*.mjs` del cerrado
4. Verificar en Linear Projects que el epic ya no aparece → recién entonces proponer el plan nuevo

## Comandos útiles

```bash
cd _linear
node scripts/validate-linear-hygiene.mjs
node scripts/sprint-next.mjs
node scripts/validate-plan-html.mjs plans/plan_sprint_<nombre>.html
node scripts/sprint_<nombre>.mjs create
node scripts/sprint_<nombre>.mjs cleanup   # solo en Fase 0 (trabajo nuevo)
```
