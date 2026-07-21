---
name: linear-orchestrator
description: >-
  Orquestación Linear ODS: higiene, Fase 0 cleanup, plan HTML, pedir ✅ APROBADO,
  create, next, cierre Fase 6. Usar al iniciar trabajo de producto, cuando
  sprint-next está vacío, al cerrar un sprint, o antes de proponer un sprint nuevo.
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
| Higiene fail / sprints Done acumulados | Ofrecer y ejecutar Fase 0 (`cleanup` + borrar plan/resumen/script). Parar código. |
| Issue ODS-N desbloqueado | `show` → checklist 1 ítem → implementar → `checklist N` → … → Done |
| Sin issues / sin sprint activo | **No codear.** Fase 0 si hace falta → escribir `plan_sprint_<nombre>.html` → `validate-plan-html.mjs` → preguntar **¿✅ APROBADO?** → esperar → `create` |
| Usuario dice Hazlo / fixea sin plan | Responder con plan corto + link al HTML + **¿✅ APROBADO?** No implementar aún |

## Frases que NO son bypass

- «Hazlo», «arranca», «rápido», «para la demo», «hotfix»
- Bypass solo: *bypass linear* | *sin linear* | *solo local* en el mensaje actual

## Cierre

1. `resumen_sprint_<nombre>.html`
2. Epic Completed
3. `sprint_<nombre>.mjs cleanup`
4. Borrar plan + resumen + script del sprint
5. Confirmar al humano: listo para el próximo plan

## Comandos útiles

```bash
cd _linear
node scripts/validate-linear-hygiene.mjs
node scripts/sprint-next.mjs
node scripts/validate-plan-html.mjs plans/plan_sprint_<nombre>.html
node scripts/sprint_<nombre>.mjs create
node scripts/sprint_<nombre>.mjs cleanup
```
