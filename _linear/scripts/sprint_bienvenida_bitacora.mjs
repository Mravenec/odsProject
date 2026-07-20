#!/usr/bin/env node
/** Epic 3 — Bienvenida, logo UTN, bitácora */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Ingreso formal + marca UTN + bitácora de accesos",
  SPRINT_NAME: "Sprint bienvenida bitacora",
  EPIC_DESC:
    "Saludo formal dashboard + fecha/hora; logo UTN; UI admin sobre auditoria_login existente. Sin email SMTP. Sin telemetría proyecto tocado (backlog).",
  PLAN_REL: "plans/plan_sprint_bienvenida_bitacora.html",
  SCRIPT_HINT: "sprint_bienvenida_bitacora.mjs",
  EPIC_COLOR: "#0EA5E9",
  ISSUES: {
    feSaludo: {
      title: "FE · Dashboard saludo formal + fecha/hora",
      role: "frontend",
      estimate: 2,
      type: "feature",
      description: `## Archivos
DashboardPage.jsx

## Checklist
- [ ] Quitar «Qué bueno verte» e impacto del día (no-admin)
- [ ] «{Nombre}, usted ha ingresado exitosamente a la plataforma ODS Agenda 2030» + fecha/hora
- [ ] npm run build`,
    },
    feLogo: {
      title: "FE · Logo UTN en login",
      role: "frontend",
      estimate: 2,
      type: "feature",
      description: `## Archivos
LoginPage, assets públicos

## Checklist
- [ ] ⏸ Asset logo UTN del profe (o placeholder acordado)
- [ ] Login con logo; layout móvil OK
- [ ] npm run build`,
    },
    beBitacora: {
      title: "BE · GATE_HTTP bitácora audit-recent + logout",
      role: "backend",
      estimate: 2,
      type: "feature",
      description: `## Archivos
LoginController (/admin/audit-recent, login-history), .http

## Checklist
- [ ] .http admin: LOGIN_OK / LOGIN_FALLIDO / LOGOUT
- [ ] Respuesta: usuario, fecha, ip, evento
- [ ] NO telemetría «proyecto tocado»
- [ ] **Handoff:** marcar feBitacora checklist ítem 1`,
    },
    feBitacora: {
      title: "FE · Admin pantalla Bitácora de ingresos",
      role: "frontend",
      estimate: 5,
      type: "feature",
      description: `## Archivos
Nueva página admin + rutas + menú

## Checklist
- [ ] ⏸ GATE_HTTP: NO iniciar hasta handoff beBitacora ítem 1 [x]
- [ ] Lista filtrable fechas / usuario / evento
- [ ] Solo role admin; sin email al login
- [ ] npm run build`,
    },
    orch: {
      title: "ORCH · resumen bienvenida/bitácora + Epic Completed",
      role: "orchestrator",
      estimate: 1,
      type: "feature",
      description: `## Checklist
- [ ] ⏸ feSaludo, feLogo, feBitacora Done
- [ ] resumen_sprint_bienvenida_bitacora.html
- [ ] Epic Completed`,
    },
  },
  BLOCKS: [
    ["feBitacora", "beBitacora"],
    ["orch", "feSaludo"],
    ["orch", "feLogo"],
    ["orch", "feBitacora"],
  ],
});
