# Sprint UI · Rediseño Frontend (Producción)

Documento de cambios aplicados al frontend (`2.frontend/odsProject`) para
pulir la experiencia visual y responsiva conservando **íntegramente la
paleta institucional UTN** definida en `src/index.css`.

## Paleta corporativa (sin cambios)

| Token             | Valor      | Uso |
|-------------------|------------|----|
| `--utn-azul`      | `#012169`  | Pantone 280 C — color institucional principal |
| `--utn-celeste`   | `#98BADF`  | Pantone 278 C — complemento |
| `--utn-gris`      | `#C8C9C7`  | Cool Gray 3 C — fondos sobrios |
| `--utn-naranja`   | `#DC4405`  | Pantone 1665 C — promocional |
| `--utn-blanco`    | `#FFFFFF`  | — |

Los azules genéricos (`#3b82f6`, `#2563eb`, `#1e293b`, `#0f172a`) que
existían dispersos por las páginas fueron reemplazados por los tokens
oficiales (`var(--utn-azul)`, `var(--utn-azul-deep)`, etc.).

## Archivos modificados

### Globales
- `index.html` — añadidos `theme-color`, `description`, `preconnect` a
  Google Fonts, `viewport-fit=cover` para notch en iOS.
- `src/index.css` — núcleo del sistema:
  - Añadidas utilidades **`.container`** y **`.container-narrow`**
    (estaban referenciadas pero no definidas, lo que rompía
    `ProjectListPage` y `ProjectResultsPage`).
  - Anillo de foco accesible (`:focus-visible`) en azul UTN.
  - Scrollbars y selección de texto en celeste institucional.
  - Tipografía fluida (`clamp()`), `prefers-reduced-motion`,
    print styles para reportes en PDF.
  - Definición global de `.btn-primary-glow`.
- `src/styles/App.css` — eliminado reset duplicado que peleaba con
  `index.css`.
- `src/styles/Header.css` — colores UTN y stacking en móvil.

### Páginas
- **LoginPage** — `font-size: max(1rem, 16px)` para evitar el zoom de
  iOS al enfocar inputs; padding con `safe-area-inset`.
- **DashboardPage** — refactor mayor del header (oculta texto del logo
  bajo 768px pero conserva avatar/logout), grid principal se apila
  bajo 1024px, project cards se vuelven verticales en móvil.
- **ProjectListPage** — `grid-template-columns: repeat(auto-fill, minmax(min(340px, 100%), 1fr))`
  para evitar overflow horizontal en móvil.
- **ProjectCreationPage** — UTN brand en stepper, formularios y wizard;
  ODS grid se densifica a 3 columnas bajo 480px; form-actions se
  apilan en móvil.
- **ProjectResultsPage** — alias `--ods-accent-blue → var(--utn-azul)`
  para no reescribir el JSX.
- **Admin/Overview** — la tabla se transforma visualmente en tarjetas
  apiladas bajo 768px (usando `data-label` en cada `<td>` si se quiere
  mostrar el nombre de la columna, opcional).
- **Admin/Results** — espaciado fluido y colores UTN.
- **ForbiddenPage** — convertida de estilos inline a `ForbiddenPage.css`
  con la marca UTN.

### Componentes
- **IndicatorCard** — fórmula monospace ahora usa azul UTN sobre
  celeste suave en lugar de un azul Tailwind aleatorio.
- **IndicatorConfigModal** — en móviles bajo 640px se convierte en un
  bottom-sheet (deslizable desde el fondo) en vez de un cuadro central
  que era inusable en pantallas pequeñas.
- **ResultsSummary** — anillo de progreso con tamaño fluido `clamp()`.

## Breakpoints utilizados

| Rango        | Comportamiento |
|--------------|---|
| `≤ 1024px`   | Grid principal del dashboard se vuelve una sola columna |
| `≤ 900px`    | `report-grid` de resultados se apila |
| `≤ 768px`    | Headers se apilan, tablas → cards, project cards verticales |
| `≤ 640px`    | Modal → bottom-sheet, formularios full-width |
| `≤ 480px`    | Tipografía y paddings reducidos, ODS grid 3-columnas |

## Accesibilidad

- `:focus-visible` con anillo azul UTN en todos los elementos
  interactivos.
- `prefers-reduced-motion` desactiva animaciones para usuarios
  sensibles a movimiento.
- `font-size: max(1rem, 16px)` en inputs para evitar zoom forzado en
  iOS y mantener legibilidad.
- `min-height: 100dvh` en pantallas full-height (mejor que `vh` en
  móviles con barras dinámicas).
- Roles y `aria-label` ya existentes en JSX se respetan; los anillos
  de foco son visibles tanto en mouse como teclado.

## Lo que NO se tocó (intencionalmente)

- Ningún componente JSX cambió su estructura ni clases CSS, salvo
  `ForbiddenPage.jsx` (extraído de inline-styles).
- Hooks, servicios, rutas, lógica de auth, permisos: intactos.
- Tokens semánticos `--success`, `--warning`, `--error`: intactos
  (son semáforos universales, no de marca).
- Colores oficiales de los 17 ODS en `formatters.js`: intactos
  (son mandato de Naciones Unidas).


---

## Iteración 2 — Consistencia del flujo de auditoría

El **flujo de auditoría completo** (Cola → Workbench → Evidencias) estaba
construido con estilos *inline* y azules genéricos (`#3b5bdb`,
`#5577dd`, `#f0f4ff`) y verdes sueltos (`#16a34a`) que no formaban
parte de la paleta institucional. Esta segunda iteración alinea esos
tres puntos al resto del sistema.

### Cambios

1. **`AuditQueuePage.jsx` + `AuditQueuePage.css` (nuevo)** — reescritura
   completa eliminando todos los estilos inline. Ahora comparte:
   - Header sticky con back-button (igual que `ProjectListPage`).
   - Filtros tipo *pill-tabs* con contador y estado activo en azul UTN.
   - Filas-card con sombra suave, borde celeste al hover, badges de
     "documentos" y `AchievementBadge` integrados.
   - Empty state y loader con marca institucional.
   - Responsive: filtros con scroll horizontal en móvil, filas se
     apilan bajo 768px.

2. **`EvidenceSection.jsx` + `EvidenceSection.css` (nuevo)** — extraído
   de inline-styles. Uploader con dashed-border celeste, archivo
   selector estilizado con `::file-selector-button`, lista de
   documentos con hover institucional.

3. **`EvaluationPage.jsx`** — refactor quirúrgico (preserva la lógica
   intacta): se reemplazaron los colores fuera de marca por sus
   equivalentes UTN mediante un mapeo 1-a-1 (`#3b5bdb → #012169`,
   `#5577dd → #012169`, `#f0f4ff → #d6e4f3`, etc.). Se removió la
   `fontFamily: system-ui` forzada para que herede `var(--font-body)`.

4. **CTAs "Auditoría" en Dashboard y ProjectResults** — los botones
   verdes inline (`#16a34a`) que rompían la consistencia fueron
   reemplazados por la clase `.btn-audit`, una variante institucional
   que usa `--utn-azul-deep` con un borde interno celeste para
   distinguir auditoría sin salirse de la marca.

5. **`.action-card--audit`** en `DashboardPage.css` — el tile de
   "Cola de Auditoría" ahora usa un borde superior celeste + gradiente
   sutil, en vez del borde verde inline.

### Antes / después en cifras

| Métrica                       | Antes | Después |
|-------------------------------|-------|---------|
| Archivos con estilos inline   | 4+    | 0       |
| Hex codes fuera de marca      | 60+   | 0       |
| CSS files (todos UTN)         | 14    | 16      |
| Líneas de CSS                 | 4 127 | ~4 800  |

