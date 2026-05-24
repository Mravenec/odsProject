# 🔷 _linear — MCP Server v2.0 (Multi-Agente)

Servidor MCP para Linear.app, bloqueado al proyecto **`linear_ods`**.  
Versión 2.0 implementa la arquitectura de **orquestación multi-agente** con claim atómico, heartbeat, tool gating, manejo de fallos y traspaso de contexto entre agentes.

---

## 📁 Estructura

```
_linear/
├── src/
│   └── index.ts          ← Fuente TypeScript completa
├── dist/
│   └── index.js          ← Compilado listo para usar
├── state/
│   └── agent-claims.json ← Registro local de claims (auto-generado)
├── package.json
├── tsconfig.json
└── README.md
```

---

## ⚙️ Instalación

```bash
cd _linear
npm install
npm run build
```

---

## 🔌 Configurar en Claude Desktop

**macOS:** `~/Library/Application Support/Claude/claude_desktop_config.json`  
**Windows:** `%APPDATA%\Claude\claude_desktop_config.json`

```json
{
  "mcpServers": {
    "linear-ods": {
      "command": "node",
      "args": ["/RUTA/ABSOLUTA/_linear/dist/index.js"],
      "env": {
        "LINEAR_API_KEY": "lin_api_89eOKlnd9NzYLyiW7WSI2v4UNIysuxeEuPHCZHgS",
        "LINEAR_TEAM_NAME": "linear_ods",
        "HEARTBEAT_TTL_MS": "300000"
      }
    }
  }
}
```

> Reemplaza `/RUTA/ABSOLUTA/` con la ruta real. Reinicia Claude Desktop.

---

## 🧰 Herramientas — Referencia completa

### 👤 Equipo / Meta
| Herramienta | Descripción |
|---|---|
| `get_team_info` | Info general del equipo |
| `get_workflow_states` | Lista todos los estados del tablero |
| `list_team_members` | Lista los miembros |
| `setup_workflow` | ⚡ **Ejecutar primero.** Crea los labels necesarios para multi-agente |

### 📝 Issues
| Herramienta | Descripción |
|---|---|
| `create_issue` | Crea issue con `agentRole`, dependencias (`blockedBy`, `blocks`) y todos los campos |
| `bulk_create_issues` | Crea múltiples issues de una vez — ideal para volcar el backlog completo |
| `list_issues` | Lista con filtros: estado, proyecto, ciclo, `agentRole` |
| `update_issue` | Actualiza cualquier campo |
| `delete_issue` | Elimina un issue |
| `create_issue_relation` | Crea relación `blocks`, `related` o `duplicate` entre dos issues |

### 🤖 Ciclo de vida del agente
| Herramienta | Descripción |
|---|---|
| `claim_issue` | **Atómico.** Busca, reclama y mueve a In Progress el primer issue disponible para el rol |
| `ping_issue` | Heartbeat — llamar cada 2-3 min para mantener el claim vivo |
| `release_issue` | Libera el claim sin completar — issue vuelve a Todo |
| `fail_issue` | Marca como fallido con motivo. Opción de cancelar dependientes en cascada |
| `submit_for_review` | Mueve a In Review + adjunta `outputArtifacts` para el siguiente agente |
| `approve_issue` | ✅ Aprobación humana → Done. Libera tareas dependientes |
| `reject_issue` | 🔄 Rechazo humano → vuelve a In Progress con feedback |
| `get_issue_context` | Lee descripción + artifacts de dependencias antes de empezar a trabajar |
| `list_available_issues` | Consulta issues disponibles para un rol (sin hacer claim) |

### 🔍 Watchdog / Salud
| Herramienta | Descripción |
|---|---|
| `watchdog_check` | Detecta claims con heartbeat expirado, revierte a Todo y alerta |
| `get_sprint_health` | Métricas en tiempo real: estados, agentes activos, bloqueados, velocidad |

### 🔄 Ciclos / Sprints
| Herramienta | Descripción |
|---|---|
| `create_cycle` | Crea un sprint con nombre y fechas |
| `list_cycles` | Lista todos los sprints |
| `add_issues_to_cycle` | Agrega issues a un sprint |
| `remove_issue_from_cycle` | Quita un issue del sprint |

### 🗂️ Proyectos / Epics
| Herramienta | Descripción |
|---|---|
| `create_project` | Crea un Epic |
| `list_projects` | Lista todos los Epics |
| `update_project` | Actualiza nombre, estado o fecha objetivo |

### 🏷️ Etiquetas
| Herramienta | Descripción |
|---|---|
| `create_label` | Crea etiqueta con nombre y color |
| `list_labels` | Lista todas las etiquetas |

---

## 🔐 Tool Gating

Las herramientas `ping_issue`, `release_issue`, `fail_issue` y `submit_for_review` **verifican que el agente tenga un claim activo** antes de ejecutarse. Si el agente no reclamó el issue o el heartbeat expiró, la operación es rechazada con:

```
TOOL GATE: Agent "X" has no active claim on issue Y. Use claim_issue first.
```

---

## 🏃 Flujo completo de un agente

```
1. setup_workflow          ← Solo la primera vez
2. list_available_issues   ← Ver qué hay disponible
3. claim_issue             ← Reclamar atómicamente (mueve a In Progress)
4. get_issue_context       ← Leer descripción + artifacts de dependencias
5. [trabajar... ]
   ping_issue cada 2-3 min ← Mantener claim vivo
6a. submit_for_review      ← Enviar a revisión humana
    approve_issue / reject_issue  ← Decisión humana
6b. fail_issue             ← Si algo sale mal
```

---

## 💬 Ejemplos con Claude

### Setup inicial (una sola vez)
```
Configura el workflow de Linear para multi-agente
```

### Planificación de un sprint completo
```
Crea el Sprint 1 del 2 al 16 de junio. Luego crea estas tareas con sus dependencias:
- "Crear tabla Users" (role: database)
- "API de Login" (role: backend, bloqueada por la anterior)
- "UI de Login" (role: frontend, bloqueada por la anterior)
```

### Flujo de un agente backend
```
Soy el backend-agent-1 con rol backend. 
Busca y reclama mi próxima tarea del Sprint 1.
```

### Revisión del sprint
```
Muéstrame la salud del Sprint 1: cuántas tareas hay por estado, 
cuáles están bloqueadas y si hay algún agente sin heartbeat.
```

### Watchdog (el Orquestador lo llama periódicamente)
```
Ejecuta el watchdog para revisar si hay claims expirados.
```

---

## 🏷️ Labels del sistema (creados por `setup_workflow`)

| Label | Color | Uso |
|---|---|---|
| `role:frontend` | 🔵 Azul | Issues del agente frontend |
| `role:backend` | 🟢 Verde | Issues del agente backend |
| `role:database` | 🟡 Amarillo | Issues del agente de base de datos |
| `role:devops` | 🟣 Violeta | Issues del agente devops |
| `role:orchestrator` | 🔴 Rojo | Issues del orquestador |
| `status:failed` | 🔴 Rojo oscuro | Issue marcado como fallido |
| `status:awaiting-review` | 🟠 Naranja | En espera de revisión humana |
| `status:stale-claim` | ⚫ Gris | Claim expirado detectado por watchdog |

---

## ⚠️ Variables de entorno

| Variable | Default | Descripción |
|---|---|---|
| `LINEAR_API_KEY` | `lin_api_89e...` | API key de Linear |
| `LINEAR_TEAM_NAME` | `linear_ods` | Nombre del equipo (solo opera sobre este) |
| `HEARTBEAT_TTL_MS` | `300000` (5 min) | Tiempo antes de que un claim expire |
