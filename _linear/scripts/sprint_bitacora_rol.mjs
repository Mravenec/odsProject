#!/usr/bin/env node
/** Epic — Bitácora mostrar rol */
import { registerSprint } from "./sprint-epic-kit.mjs";

registerSprint({
  EPIC_NAME: "Bitácora — mostrar rol en columna Usuario",
  SPRINT_NAME: "bitacora_rol",
  EPIC_DESC:
    "Bitácora admin: columna Usuario muestra roles.nombre (admin/gestor/consultor/evaluador) en vez de username; fullName como subtítulo.",
  PLAN_REL: "plans/plan_sprint_bitacora_rol.html",
  SCRIPT_HINT: "sprint_bitacora_rol.mjs",
  EPIC_COLOR: "#2E8B63",
  ISSUES: {
    beRol: {
      title: "BE · audit-recent expone rol",
      role: "backend",
      estimate: 2,
      type: "improvement",
      description: `## Archivos
LoginRepository, LoginController

## Checklist
- [ ] LoginRepository.findVistaAuditoriaReciente: LEFT JOIN roles; seleccionar r.nombre
- [ ] LoginController audit-recent: map.put("rol", …); fallback null si sin usuario
- [ ] (Opcional) CREATE OR REPLACE vista_admin_auditoria_login_reciente con columna rol
- [ ] Testing: GET /admin/audit-recent → cada fila con usuario tiene rol en {admin,gestor,consultor,evaluador}
- [ ] Handoff: marcar ODS-FE checklist ítem 1`,
    },
    feRol: {
      title: "FE · Bitácora celda = rol + fullName",
      role: "frontend",
      estimate: 2,
      type: "improvement",
      description: `## Archivos
authService.js, BitacoraAdminPage.jsx

## Checklist
- [ ] ⏸ GATE_HTTP: NO iniciar hasta handoff BE ítem 1 [x]
- [ ] authService._mapAuditEntry: usuario = rol (display); conservar fullName; filtro usuario también matchea rol
- [ ] BitacoraAdminPage: user-main = rol; user-sub = fullName; th opcional «Rol»
- [ ] npm run build; smoke /admin/bitacora`,
    },
  },
  BLOCKS: [["feRol", "beRol"]],
});
