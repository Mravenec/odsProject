# Parche UTN — Identidad Gráfica + Corrección de datos en el Dashboard

Este paquete contiene **únicamente los 6 archivos modificados** del frontend.
Se entregan en su ruta relativa exacta para que puedas extraer el ZIP sobre la
raíz de tu clon de `odsProject` y los archivos sobrescriban los originales.

```
2.frontend/odsProject/src/
├── index.css                                ← reescrito (paleta UTN)
├── pages/
│   ├── LoginPage/
│   │   ├── LoginPage.jsx                    ← rebrandeado a UTN
│   │   └── LoginPage.css                    ← paleta UTN
│   └── DashboardPage/
│       ├── DashboardPage.jsx                ← FIX visualización proyectos + brand
│       └── DashboardPage.css                ← overrides UTN (al final del archivo)
└── services/
    └── projectService.js                    ← FIX enriquecimiento con /summary
```

---

## 1. Identidad Gráfica UTN aplicada

Se siguió el **Manual de Identidad Gráfica UTN** (Octubre 2014). Los puntos
del Manual que se trasladaron al sistema son:

### 1.1 Paleta corporativa (Manual pág. 22 — “Utilización del Color”)

| Jerarquía | Pantone        | HEX usado | Variable CSS         | Uso |
|-----------|----------------|-----------|----------------------|-----|
| Nº 1      | **280 C**      | `#012169` | `--utn-azul`         | Color institucional principal |
| Nº 2      | **278 C**      | `#98BADF` | `--utn-celeste`      | Complemento del azul |
| Nº 3      | **Cool Gray 3 C** | `#C8C9C7` | `--utn-gris`      | Fondos sobrios / gerenciales |
| Nº 4      | **1665 C**     | `#DC4405` | `--utn-naranja`      | **Sólo** materiales promocionales |

El antiguo `--primary: #2563eb` (azul genérico Tailwind) fue reemplazado por
`var(--utn-azul)` en `index.css`. Como el resto del proyecto ya consumía
`--primary`, `--primary-hover`, `--primary-glow`, etc., **la migración se
propagó automáticamente** a todas las pantallas (ProjectListPage,
ProjectResultsPage, EvaluationPage, AuditQueuePage, Admin/*, etc.).

### 1.2 Tipografías (Manual págs. 18-21)

| Familia oficial | Fallback web (cuando la fuente no está instalada) | Variable |
|-----------------|---------------------------------------------------|----------|
| Century Gothic  | URW Gothic L → Avant Garde → Questrial → Open Sans | `--font-display` |
| Myriad Pro      | Open Sans → Helvetica Neue → Helvetica → Arial    | `--font-body` |
| Helvetica Neue  | Helvetica → Arial                                 | `--font-sign` |

Se importan **Questrial** y **Open Sans** desde Google Fonts como fallbacks de
alta fidelidad para clientes que no tengan instaladas las fuentes corporativas.

Los **titulares** (`h1…h6`) usan `--font-display` (Century Gothic), el cuerpo
usa `--font-body` (Myriad Pro), y la marca UTN usa `--font-sign`
(Helvetica Neue), exactamente como exige el Manual.

### 1.3 Marca institucional reutilizable

Se creó el componente CSS `.utn-mark` que reproduce el logotipo UTN en
versión digital:

- recuadro azul `#012169` con las siglas **UTN** en blanco;
- los **6 puntos** celestes a la derecha (representan las 6 instituciones
  parauniversitarias que formaron la UTN, Manual pág. 14);
- firma tipográfica **“Universidad Técnica Nacional”** en Helvetica Neue.

Se usa en el header del Dashboard y, en variante `.utn-mark--stacked`, en el
LoginPage.

### 1.4 Componentes que cambiaron de color

- Botón primario, botón “Medir Impacto”, botón “Ingresar”: **azul UTN**.
- Barras de progreso: fondo celeste UTN, fill degradado azul→celeste.
- Tarjetas: borde superior azul UTN sutil.
- Pills de estado:
  - `activo` / `planificacion` → celeste + azul UTN (antes verde y morado).
  - `completado` → mismo estilo celeste + azul UTN.
- Sombras y `focus rings` ahora usan el halo del azul institucional
  (`rgba(1,33,105,0.18)`).

> El **naranja Pantone 1665 C** queda disponible como `--utn-naranja` pero
> **NO** se aplicó a UI institucional. Conforme al Manual, sólo debería
> usarse en materiales promocionales puntuales (campañas, banners de
> eventos, etc.).

---

## 2. Fix del Dashboard — “0/0 indicadores · 0%” aunque el proyecto fue auditado

### 2.1 Diagnóstico

El listado del Dashboard se alimenta de
`GET /api/projects/with-ods` y `GET /api/projects/user/{id}/with-ods`. Esos
endpoints están servidos por el view de jOOQ
`VistaResumenProyectosOds` (paquete
`com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds`).

Los campos que expone el view son **únicamente**:

```
proyectoId, nombreProyecto, gestor, sede, estado,
fechaInicio, fechaFin, odsVinculados, odsPrimario
```

Es decir, el view **NO devuelve**:

- `totalIndicators`
- `indicatorsAchieved`
- `progressPercentage`
- `descripcion`
- ubicación geográfica (`provinciaNombre`, `cantonNombre`, `distritoNombre`)

Por eso el card siempre mostraba **`0/0 indicadores`** y **`0%`** aunque
hubieras auditado los indicadores: simplemente el front no recibía esos
números.

### 2.2 Solución (sin tocar la BD ni el backend)

El backend **sí** expone esos números, pero por proyecto, en
`GET /api/projects/{id}/summary` (método `calculateProjectSummary` en
`MasterProjectService`). Devuelve:

```json
{
  "proyectoId":      123,
  "totalIndicators": 5,
  "odsLinkedCount":  2,
  "averageProgress": 73.4,
  "status":          "activo"
}
```

En `projectService.js` se agregó:

1. **`getProjectSummary(projectId)`** — wrapper sobre `/projects/{id}/summary`.
2. **`enrichWithSummaries(projects)`** — corre en paralelo (`Promise.allSettled`)
   un `/summary` por cada proyecto del listado y mergea los campos faltantes:

   ```js
   {
     totalIndicators:    s.totalIndicators,
     indicatorsAchieved: round(averageProgress / 100 * totalIndicators),
     progressPercentage: s.averageProgress,
     odsLinkedCount:     s.odsLinkedCount
   }
   ```

3. Tanto `getAllProjects()` como `getUserProjects(userId)` aplican
   `enrichWithSummaries` antes de devolver datos. Si el `/summary` de un
   proyecto falla, ese proyecto queda con `0/0 · 0%` (degradación elegante,
   nunca rompe el render).

4. Se añadió `getGlobalDashboardData()` como **alias de `getGlobalDashboard()`**
   porque `useProjects.jsx` lo llamaba con ese nombre y el método **no
   existía**, lo que dejaba el panel global de admin en cero también.

5. `_mapBackendToFrontend()` ahora inicializa **explícitamente**
   `totalIndicators`, `indicatorsAchieved`, `progressPercentage`,
   `odsLinkedCount`, y los campos geográficos a valores neutros — así el
   render entre que llega el listado y termina el enrichment no muestra
   `undefined%` ni `NaN%`.

### 2.3 Robustez del JSX

`DashboardPage.jsx` ahora **normaliza defensivamente** lo que recibe del
hook antes de renderizar cada tarjeta:

```js
const totalInd     = Number(project.totalIndicators) || project.indicators?.length || 0;
const achievedInd  = Number(project.indicatorsAchieved) || 0;
const progressPct  = Number(project.progressPercentage) || 0;
const isInProgress = ['active', 'activo', 'planificacion'].includes(project.status);
const statusLabel  = isInProgress ? 'En Curso'
                    : project.status === 'completado' ? 'Completado'
                    : (project.status || 'Sin estado');
```

Otras mejoras de visualización:

- Si el proyecto **no tiene descripción**, ya no aparece el espacio vacío:
  muestra `"Sin descripción registrada para este proyecto."`.
- Si **no hay nombre**, fallback a `"Proyecto sin nombre"`.
- Si **no hay ODS**, fallback a `—` (antes mostraba `undefined`).
- Nuevo estado `planificacion` cuenta como “En Curso” (era el motivo por
  el que muchos proyectos nuevos aparecían como “Completado” por defecto).

### 2.4 Roadmap sugerido (para cerrar el bug en backend)

Lo ideal a mediano plazo es **modificar la vista**
`vista_resumen_proyectos_ods` en `ods_master` para que ya incluya los
campos derivados. Eso ahorra las N+1 llamadas que hace el front. Pseudo-SQL:

```sql
CREATE OR REPLACE VIEW ods_master.vista_resumen_proyectos_ods AS
SELECT p.id AS proyecto_id,
       p.nombre_proyecto,
       p.descripcion,
       u.nombre   AS gestor,
       s.nombre   AS sede,
       p.estado,
       p.fecha_inicio,
       p.fecha_fin,
       GROUP_CONCAT(DISTINCT po.objetivo_id ORDER BY po.objetivo_id) AS ods_vinculados,
       p.objetivo_primario_id AS ods_primario,
       -- agregados derivados (nuevos):
       COALESCE(SUM(pi.total_indicadores),    0) AS total_indicadores,
       COALESCE(SUM(pi.indicadores_logrados), 0) AS indicadores_logrados,
       COALESCE(AVG(pi.avance_promedio),      0) AS avance_promedio
FROM   ods_master.proyectos        p
LEFT   JOIN ods_master.usuarios    u ON u.id = p.usuario_id
LEFT   JOIN ods_master.sedes       s ON s.id = p.sede_id
LEFT   JOIN ods_master.proyecto_ods po ON po.proyecto_id = p.id
LEFT   JOIN ods_master.vista_indicadores_por_proyecto pi ON pi.proyecto_id = p.id
GROUP BY p.id;
```

Cuando se haga eso, el front sigue funcionando idéntico (el mapper ya lee
ambos camelCase y snake_case), y simplemente se podría eliminar la
llamada extra `enrichWithSummaries` para ganar latencia.

---

## 3. Cómo aplicar este parche

```bash
# 1) Clonar (si no lo tenés ya)
git clone https://github.com/Mravenec/odsProject.git
cd odsProject

# 2) Extraer el ZIP sobre la raíz del repo — sobrescribe sólo los 6 archivos
unzip -o ../odsProject-utn-patch.zip

# 3) (opcional) Reinstalar deps por si tu node_modules es viejo
cd 2.frontend/odsProject
npm install

# 4) Levantar el frontend
npm run dev
```

No hay que tocar nada del backend ni de la base de datos para que el fix
del Dashboard funcione.

---

## 4. Verificación visual

- En `dashboard_preview.html` (incluido en este paquete) podés ver una
  maqueta estática con la nueva identidad UTN aplicada al dashboard,
  para confirmar la dirección visual antes de levantar el frontend.

— Generado para Universidad Técnica Nacional.
