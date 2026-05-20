# ODS Agenda 2030 — Sistema de Seguimiento UTN Costa Rica.

Sistema web para el registro, configuración y evaluación de proyectos alineados a los **17 Objetivos de Desarrollo Sostenible (ODS)** de la Agenda 2030. Desarrollado para la **Universidad Técnica Nacional de Costa Rica**.

---

## Estructura del repositorio

```
odsProject/
├── 0.database/          → Scripts SQL (MariaDB) — 19 schemas
├── 1.backend/           → Spring Boot + JOOQ (Java 21)
└── 2.frontend/          → React 18 + Vite
```

---

## Stack tecnológico

| Capa | Tecnología | Versión |
|------|-----------|---------|
| Base de datos | MariaDB | 10.11 |
| Backend | Spring Boot | 3.3.0 |
| ORM/Query | jOOQ | 3.19.6 |
| Motor de fórmulas | exp4j | 0.4.8 |
| Autenticación | JWT (jjwt) | 0.12.5 |
| Frontend | React | 18 |
| Bundler | Vite | 5 |
| HTTP client | Axios | — |
| Iconos | Lucide React | — |

---

## Requisitos previos

- **Java 21** (JDK)
- **Maven 3.8+** (o usar el wrapper `./mvnw`)
- **Node.js 18+** con npm
- **MariaDB 10.11** corriendo en `localhost:3306`

---

## 1. Base de datos

### Ejecutar los scripts en orden

```sql
SOURCE 0.database/propuesta_actual/1. login_system.sql;
SOURCE 0.database/propuesta_actual/2. ods_master_database.sql;
SOURCE 0.database/propuesta_actual/3. ods_common.sql;
SOURCE 0.database/propuesta_actual/4. ods01_database.sql;
-- ... repetir del 5 al 20 (ods02–ods17)
SOURCE 0.database/propuesta_actual/20. ods17_database.sql;
SOURCE 0.database/propuesta_actual/21. ods_mocks.sql;
SOURCE 0.database/propuesta_actual/22. indicador_parametros_master_seeds.sql;
```

> **Orden obligatorio:** `ods_login` → `ods_master` → `ods01–17` → mocks → seeds.

### Schemas creados

| Schema | Contenido |
|--------|-----------|
| `ods_login` | Usuarios, roles, sedes, catálogo (239 indicadores, 17 ODS) |
| `ods_master` | Proyectos — cabecera multi-ODS |
| `ods01` – `ods17` | Indicadores, parámetros libres, mediciones, auditoría por ODS |

### Borrar y recrear desde cero

```sql
DROP DATABASE IF EXISTS ods_login, ods_master,
  ods01, ods02, ods03, ods04, ods05, ods06, ods07, ods08, ods09,
  ods10, ods11, ods12, ods13, ods14, ods15, ods16, ods17;
```

---

## 2. Backend

### Configuración

`1.backend/odsProject/src/main/resources/application.properties` — valores por defecto:

```properties
spring.datasource.jdbc-url=jdbc:mariadb://localhost:3306/ods_login
spring.datasource.username=root
spring.datasource.password=123456

app.jwt.secret=odsProjectSecretKey2030Agenda_ChangeInProduction!
app.cors.allowed-origins=http://localhost:5173,http://localhost:3000
```

> ⚠️ Cambiar `app.jwt.secret` en producción.

### Ejecutar

```bash
cd 1.backend/odsProject

./mvnw compile -DskipTests    # Compilar
./mvnw spring-boot:run        # Correr en puerto 8080
```

### Regenerar POJOs JOOQ

Solo necesario si se modifica el schema SQL:

```bash
# MariaDB debe estar corriendo con los 19 schemas
./mvnw jooq-codegen:generate
```

Los POJOs ya están generados y commiteados — no es necesario regenerarlos para el desarrollo normal.

### Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| `POST` | `/api/login/auth/login` | Autenticación → JWT |
| `GET` | `/api/login/auth/validate` | Validar token |
| `GET` | `/api/login/sedes` | Sedes UTN |
| `GET` | `/api/catalog/ods` | 17 ODS con colores |
| `GET` | `/api/catalog/ods/{id}/indicadores` | Indicadores de un ODS |
| `GET` | `/api/catalog/indicadores/{id}/parametros` | Plantillas de parámetros |
| `GET/POST` | `/api/projects` | Proyectos |
| `GET` | `/api/projects/user/{userId}` | Proyectos por usuario |
| `POST` | `/api/ods/{XX}/indicadores` | Vincular indicador |
| `POST` | `/api/ods/{XX}/metas` | Guardar variable/parámetro |
| `POST` | `/api/ods/{XX}/mediciones` | Registrar medición |
| `GET` | `/api/ods/{XX}/mediciones?indicadorId=N` | Historial |

`{XX}` = `01` a `17`.

### Pruebas con HTTP Client

```
1.backend/odsProject/api-tests.http
```

Compatible con IntelliJ IDEA HTTP Client y VS Code REST Client. Cubre el flujo completo: login → proyecto → indicador → medición.

---

## 3. Frontend

```bash
cd 2.frontend/odsProject

npm install
npm run dev      # Puerto 5173
npm run build    # Genera dist/
```

### Rutas

| Ruta | Descripción | Acceso |
|------|-------------|--------|
| `/dashboard` | Panel con proyectos del usuario | Todos |
| `/projects` | Lista completa de proyectos | Todos |
| `/projects/create` | Crear proyecto (wizard 2 pasos) | Gestores |
| `/projects/:id/evaluation` | Evaluar indicadores | Todos |
| `/projects/:id/results` | Ver resultados | Todos |
| `/admin/projects` | Gestión administrativa | Admin |

---

## Flujo completo

```
Login
 └── Dashboard
       └── Nuevo Proyecto
             ├── Paso 1: nombre · sede · responsable · ubicación CR · período
             └── Paso 2: seleccionar ODS → seleccionar indicadores
                   └── Por cada indicador configurar:
                         ├── Definir variables con nombre libre
                         │     Ej: "becados_total"  Decimal
                         │         "estudiantes_total"  Integer
                         ├── Escribir fórmula usando esas variables
                         │     Ej: (becados_total / estudiantes_total) * 100
                         └── Establecer meta
                               Ej: "Alcanzar 50% de becados" · valor: 50 · %
                                         ↓
             Evaluación (/projects/:id/evaluation)
                   ├── Tab "Ingreso de datos"
                   │     └── Ingresar valores → Calcular y Evaluar → ✅ Cumplido / 🔄 En Progreso
                   └── Tab "Resumen"
                         └── Tarjetas con estado y porcentaje de logro por indicador
```

### Motor de fórmulas (exp4j)

El usuario define variables con nombres arbitrarios y la fórmula que desee:

```
Variable A:  becados_total     = 450
Variable B:  estudiantes_total = 1200

Fórmula:     (becados_total / estudiantes_total) * 100
Resultado:   37.5
Meta:        50 → Estado: 🔄 En Progreso
```

---

## Credenciales de prueba

Creadas por `21. ods_mocks.sql`:

| Rol | Email | Contraseña |
|-----|-------|-----------|
| Administrador | `admin@ods.local` | `Admin1234!` |
| Gestor ODS 01 | `ana.garcia@ods.cr` | `password123` |
| Gestor ODS 02 | `carlos.rodriguez@ods.cr` | `password123` |
| Consultor | `maria.jimenez@ods.cr` | `password123` |

---

## Datos de ejemplo

El script de mocks incluye 5 proyectos con indicadores y mediciones para:

- **ODS 01** — Fin de la Pobreza (parámetros p1, p2 con mediciones históricas)
- **ODS 02** — Hambre Cero
- **ODS 03** — Salud y Bienestar
- **ODS 04** — Educación de Calidad
- **ODS 13** — Acción por el Clima (fórmula custom de área reforestada)

---

## Arquitectura

### Backend

```
src/main/java/com/odsProject/odsProject/
├── config/
│   ├── DataSourceConfig.java     → 19 datasources HikariCP con @Qualifier
│   ├── JooqConfig.java           → 19 DSLContexts (dslLogin, dslMaster, dslOds01..17)
│   ├── SecurityConfig.java       → Spring Security + CORS con withDefaults()
│   └── WebConfig.java            → CORS origins
├── controller/
│   ├── CatalogController.java    → /api/catalog/*
│   ├── LoginController.java      → /api/login/*
│   ├── MasterProjectController.java  → /api/projects/*
│   └── Objetivo01..17Controller.java → /api/ods/01..17/*
├── service/
│   ├── EvaluationService.java    → Motor exp4j para fórmulas libres
│   ├── LoginService.java         → JWT (Jwts.builder + Claims)
│   └── Objetivo01..17Service.java → recalculateIndicator() automático
├── repository/
│   └── Objetivo01..17Repository.java
└── database/jooq/
    ├── ods_login/    → Usuarios, Sedes, IndicadorMaster, OdsCatalog
    ├── ods_master/   → Proyectos (con locationProvince/Canton/District, responsableNombre)
    └── ods01..17/    → ProyectoIndicadores (con metaNombre), ProyectoIndicadorParametros
```

### Frontend

```
src/
├── services/
│   ├── authService.js             → login/logout/validate
│   ├── catalogService.js          → GET /catalog/ods y /indicadores
│   ├── projectService.js          → createFullProject() con cascade completo
│   ├── geoService.js              → API externa CR (provincias/cantones/distritos)
│   └── objetivo01..17Service.js   → getIndicators · saveIndicator · saveParameter
│                                     getMediciones · createMedicion · getMetasProyecto
├── hooks/
│   ├── useAuth.jsx                → isAdmin() · isGestor() · getSedes()
│   ├── useCatalog.jsx             → carga y caché ODS + indicadores
│   └── useProjects.jsx            → fetchUserProjects · fetchAdminProjects
├── pages/
│   ├── ProjectCreationPage/       → wizard 2 pasos + IndicatorConfigModal
│   └── EvaluationPage/            → tabs Ingreso/Resumen + calcular y evaluar
└── components/projects/
    └── IndicatorConfigModal/      → variables libres + fórmula + meta (sin catálogo)
```

---

## Para producción

Actualizar en `application.properties`:

```properties
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña_segura>
app.jwt.secret=<secret_aleatorio_minimo_32_chars>
app.cors.allowed-origins=https://tu-dominio.com
```

En el frontend, crear `.env.production`:

```env
VITE_API_BASE_URL=https://api.tu-dominio.com/api
```

---

## Licencia

Proyecto académico — Universidad Técnica Nacional de Costa Rica.