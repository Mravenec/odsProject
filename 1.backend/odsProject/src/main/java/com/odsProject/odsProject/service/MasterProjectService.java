package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoBeneficiarios;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.service.interfaces.IEvaluationService;
import com.odsProject.odsProject.service.interfaces.IMasterProjectService;
import com.odsProject.odsProject.service.interfaces.IPlanificacionEdicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio Maestro de Proyectos
 * Maneja la lógica de negocio central de gestión de proyectos multi-ods
 */
@Service
public class MasterProjectService implements IMasterProjectService {

    @Autowired
    private IMasterProjectRepository masterProjectRepository;

    @Autowired
    private IEvaluationService evaluationService;

    @Autowired
    private IPlanificacionEdicionService planificacionEdicionService;

    @Autowired
    private List<com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?>> odsServices;

    @Override
    public List<Proyectos> getAllProyectos() {
        return masterProjectRepository.findAll();
    }

    @Override
    public Optional<Proyectos> getProyectoById(Integer id) {
        return masterProjectRepository.findById(id);
    }

    @Override
    public List<Proyectos> getProyectosByUsuario(Integer usuarioId) {
        return masterProjectRepository.findByUsuario(usuarioId);
    }

    @Override
    public Proyectos createProyecto(Proyectos proyecto) {
        return masterProjectRepository.save(proyecto);
    }

    @Override
    public Proyectos updateProyecto(Proyectos proyecto) {
        if (!masterProjectRepository.exists(proyecto.getId())) {
            throw new RuntimeException("Proyecto no encontrado");
        }
        return masterProjectRepository.update(proyecto);
    }

    @Override
    public Proyectos updateProyecto(Integer proyectoId, Proyectos proyecto,
                                    Integer actorUserId, String actorRole) {
        Proyectos existing = masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
        String role = actorRole != null ? actorRole.trim().toLowerCase() : "";
        String estadoActual = String.valueOf(existing.getEstado()).toLowerCase();
        if ("admin".equals(role) || "evaluador".equals(role)) {
            if (!"planificacion".equals(estadoActual)) {
                throw new SecurityException(
                        "Admin/evaluador no puede editar el proyecto fuera de planificacion");
            }
        } else if ("gestor".equals(role)) {
            if (actorUserId == null || !actorUserId.equals(existing.getUsuarioId())) {
                throw new SecurityException("Solo el gestor dueño puede editar el proyecto");
            }
        } else {
            throw new SecurityException("Rol no autorizado para editar: " + role);
        }
        proyecto.setId(proyectoId);
        proyecto.setEstado(existing.getEstado());
        return masterProjectRepository.update(proyecto);
    }

    @Override
    public void deleteProyecto(Integer id) {
        masterProjectRepository.delete(id);
    }

    @Override
    public Map<String, Object> calculateProjectSummary(Integer proyectoId) {
        Map<String, Object> summary = new HashMap<>();
        summary.put("proyectoId", proyectoId);
        
        Optional<Proyectos> proyectoOpt = getProyectoById(proyectoId);
        if (proyectoOpt.isEmpty()) {
            summary.put("status", "error");
            summary.put("message", "Proyecto no encontrado");
            return summary;
        }

        int totalIndicators = 0;
        double totalProgress = 0.0;
        int odsCount = 0;

        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> service : odsServices) {
            try {
                // Verificamos si este ODS tiene indicadores para este proyecto
                List<?> indicators = service.findAllIndicadoresByProyecto(proyectoId);
                if (!indicators.isEmpty()) {
                    totalIndicators += indicators.size();
                    totalProgress += service.calculateProjectProgress(proyectoId);
                    odsCount++;
                }
            } catch (Exception e) {
                // Silently skip if an ODS schema isn't fully ready or doesn't have the project
            }
        }

        double averageProgress = odsCount > 0 ? totalProgress / odsCount : 0.0;

        summary.put("totalIndicators", totalIndicators);
        summary.put("odsLinkedCount", odsCount);
        summary.put("averageProgress", averageProgress);
        // Estado de workflow desde BD — no inferir por progreso de indicadores
        Proyectos proyecto = proyectoOpt.get();
        summary.put("workflowEstado", proyecto.getEstado() != null
                ? String.valueOf(proyecto.getEstado()).toLowerCase()
                : "planificacion");
        
        return summary;
    }

    @Override
    public Map<String, Object> getGlobalDashboardData() {
        Map<String, Object> dashboard = masterProjectRepository.spAdminGlobalDashboard();
        
        int totalIndicatorsAllOds = 0;
        int completedIndicatorsAllOds = 0;
        double summedProgressAllOds = 0.0;
        int odsWithIndicatorsCount = 0;

        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> service : odsServices) {
            try {
                // Obtenemos los datos del dashboard de cada ODS
                Map<String, Object> odsDashboard = service.getDashboardData();
                if (odsDashboard != null) {
                    int total = (int) odsDashboard.getOrDefault("total_indicadores", 0);
                    int completed = (int) odsDashboard.getOrDefault("indicadores_terminados", 0);
                    double progress = ((Number) odsDashboard.getOrDefault("progreso_promedio", 0.0)).doubleValue();

                    totalIndicatorsAllOds += total;
                    completedIndicatorsAllOds += completed;
                    summedProgressAllOds += progress;
                    
                    if (total > 0) {
                        odsWithIndicatorsCount++;
                    }
                }
            } catch (Exception e) {
                // Silently skip if an ODS schema isn't fully ready
            }
        }

        dashboard.put("indicadoresTotalesEcosistema", totalIndicatorsAllOds);
        dashboard.put("indicadoresCompletadosEcosistema", completedIndicatorsAllOds);
        dashboard.put("progresoPromedio", odsWithIndicatorsCount > 0 ? summedProgressAllOds / odsWithIndicatorsCount : 0.0);
        dashboard.put("odsActivosConDatos", odsWithIndicatorsCount);
        
        return dashboard;
    }

    @Override
    public Double evaluateProjectIndicator(Integer proyectoId, Integer odsId, Integer indicadorId) {
        // Buscamos el servicio correspondiente al ODS solicitado
        // El nombre del servicio suele ser ObjetivoXX...Service
        String servicePrefix = String.format("Objetivo%02d", odsId);
        
        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> service : odsServices) {
            if (service.getClass().getSimpleName().startsWith(servicePrefix)) {
                // Aquí podríamos disparar lógica específica o simplemente retornar el valor actual
                // Por ahora, el Master actúa como pasarela si se requiere orquestación global
                return service.calculateProjectProgress(proyectoId);
            }
        }
        return 0.0;
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 3 — Orquestador transaccional de creación completa.
    //
    //  Las transacciones cross-DB no son atómicas (sin XA), así que aplicamos
    //  el patrón Saga simple: registramos compensaciones para cada paso y, si
    //  algo explota, las ejecutamos en orden inverso.
    // ─────────────────────────────────────────────────────────────────────

    private static final org.slf4j.Logger log =
        org.slf4j.LoggerFactory.getLogger(MasterProjectService.class);

    @Override
    public Map<String, Object> getSodsiFichaByProyectoId(Integer proyectoId) {
        Proyectos p = masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
        List<ProyectoBeneficiarios> beneficiarios = masterProjectRepository.findBeneficiariosByProyecto(proyectoId);
        Map<String, Object> m = new java.util.LinkedHashMap<>();
        m.put("proyectoId", proyectoId);
        m.put("ejePlanesId", p.getEjePlanesId() != null ? p.getEjePlanesId().intValue() : null);
        m.put("aliadoExterno", p.getAliadoExterno());
        m.put("beneficiarios", beneficiarios);
        return m;
    }

    @Override
    public Map<String, Object> createFullProject(Map<String, Object> payload) {
        if (payload == null) throw new IllegalArgumentException("payload requerido");

        // Estructuras de respuesta
        List<Integer> odsVinculados      = new java.util.ArrayList<>();
        List<Map<String, Object>> indicadoresCreados = new java.util.ArrayList<>();
        List<Map<String, Object>> errores            = new java.util.ArrayList<>();

        // Stack LIFO de compensaciones (Runnables que deshacen lo hecho)
        java.util.Deque<Runnable> compensaciones = new java.util.ArrayDeque<>();

        Integer proyectoId = null;

        try {
            // ── PASO 1: Crear cabecera del proyecto ──────────────────────
            Object proyectoRaw = payload.get("proyecto");
            if (!(proyectoRaw instanceof Map)) {
                throw new IllegalArgumentException("El campo 'proyecto' es requerido y debe ser un objeto");
            }
            Proyectos proyecto = mapToProyectos(asStringObjectMap(proyectoRaw));
            Proyectos saved = masterProjectRepository.save(proyecto);
            if (saved == null || saved.getId() == null) {
                throw new IllegalStateException("No se obtuvo ID al guardar el proyecto");
            }
            proyectoId = saved.getId();
            final Integer pidForLambda = proyectoId;
            compensaciones.push(() -> {
                log.warn("[Compensación] Borrando proyecto {} y dependencias", pidForLambda);
                try { masterProjectRepository.delete(pidForLambda); } catch (Exception ignored) {}
            });
            log.info("[createFullProject] Proyecto {} creado", proyectoId);

            if (payload.get("fichaSodsi") instanceof Map<?, ?> fichaRaw) {
                planificacionEdicionService.saveFichaSodsi(proyectoId, asStringObjectMap(fichaRaw));
            }

            // ── PASO 2: Vincular ODS ─────────────────────────────────────
            List<Integer> odsIds       = toIntList(payload.get("odsIds"));
            Integer       primaryOdsId = toInt(payload.get("primaryOdsId"));
            // Si vienen indicadores, inferir ODS automáticamente
            List<Map<String, Object>> indicadoresRaw = asMapList(payload.get("indicadores"));
            java.util.Set<Integer> odsSetFromIndicators = new java.util.LinkedHashSet<>();
            for (Map<String, Object> ind : indicadoresRaw) {
                Integer odsId = toInt(ind.get("odsId"));
                if (odsId != null) odsSetFromIndicators.add(odsId);
            }
            // Unión: ODS explícitos + ODS implícitos por indicadores
            java.util.Set<Integer> odsToLink = new java.util.LinkedHashSet<>();
            if (odsIds != null) odsToLink.addAll(odsIds);
            odsToLink.addAll(odsSetFromIndicators);

            // ── Sprint 8.2: validación dura. Si no hay ODS para vincular,
            //    el proyecto sería un huérfano: cabecera sin cuerpo. Antes
            //    el método retornaba success=true y el usuario veía un
            //    proyecto "Objetivo Desconocido" en el listado.
            if (odsToLink.isEmpty()) {
                throw new IllegalStateException(
                    "El proyecto no puede crearse sin ODS vinculados. " +
                    "Recibí 'odsIds' vacío y ningún indicador con 'odsId'. " +
                    "Asegurate de enviar al menos un ODS o un indicador.");
            }

            for (Integer odsId : odsToLink) {
                boolean esPrimario = odsId.equals(primaryOdsId);
                masterProjectRepository.linkOds(proyectoId, odsId, esPrimario);
                odsVinculados.add(odsId);
                log.info("[createFullProject] Proyecto {} vinculado a ODS {}", proyectoId, odsId);
            }

            // ── PASO 3: Crear indicadores por ODS ────────────────────────
            for (Map<String, Object> ind : indicadoresRaw) {
                Integer odsId             = toInt(ind.get("odsId"));
                Integer indicadorMasterId = toInt(ind.get("indicadorMasterId"));
                if (odsId == null || indicadorMasterId == null) {
                    errores.add(java.util.Map.of(
                        "etapa", "indicador",
                        "odsId", String.valueOf(odsId),
                        "error", "odsId e indicadorMasterId son requeridos"));
                    continue;
                }

                com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> svc =
                    findServiceForOds(odsId);
                if (svc == null) {
                    errores.add(java.util.Map.of(
                        "etapa", "indicador",
                        "odsId", odsId,
                        "indicadorMasterId", indicadorMasterId,
                        "error", "No hay servicio backend para ODS " + odsId));
                    continue;
                }

                try {
                    Map<String, Object> result = createIndicadorViaService(
                        svc, proyectoId, ind);
                    indicadoresCreados.add(result);
                    final Integer indId = (Integer) result.get("proyectoIndicadorId");
                    if (indId != null) {
                        compensaciones.push(() -> {
                            log.warn("[Compensación] Borrando indicador {} de ODS {}", indId, odsId);
                            try { svc.deleteIndicador(indId); } catch (Exception ignored) {}
                        });
                    }
                } catch (Exception e) {
                    errores.add(java.util.Map.of(
                        "etapa", "indicador",
                        "odsId", odsId,
                        "indicadorMasterId", indicadorMasterId,
                        "error", e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()));
                }
            }

            // Si todos los indicadores fallaron, consideramos el proyecto inviable
            if (!indicadoresRaw.isEmpty() && indicadoresCreados.isEmpty()) {
                throw new IllegalStateException(
                    "Ningún indicador se pudo crear. Errores: " + errores);
            }

        } catch (Exception fatal) {
            log.error("[createFullProject] FATAL — ejecutando compensaciones", fatal);
            while (!compensaciones.isEmpty()) {
                try { compensaciones.pop().run(); } catch (Exception ignored) {}
            }
            errores.add(java.util.Map.of(
                "etapa", "fatal",
                "error", fatal.getMessage() != null ? fatal.getMessage() : fatal.getClass().getSimpleName()));
            // Reset del proyectoId para que la respuesta refleje el rollback
            proyectoId = null;
            odsVinculados.clear();
            indicadoresCreados.clear();
        }

        Map<String, Object> response = new java.util.LinkedHashMap<>();
        response.put("proyectoId", proyectoId);
        response.put("odsVinculados", odsVinculados);
        response.put("indicadoresCreados", indicadoresCreados);
        response.put("errores", errores);
        response.put("success", proyectoId != null && errores.stream()
            .noneMatch(e -> "fatal".equals(e.get("etapa"))));
        return response;
    }

    @Override
    public List<Map<String, Object>> getOdsByProyecto(Integer proyectoId) {
        return masterProjectRepository.findOdsByProyecto(proyectoId);
    }

    // ── Sprint 8.3: Listados enriquecidos ────────────────────────────────

    @Override
    public List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
            getAllProyectosWithOds() {
        return masterProjectRepository.findAllWithOds();
    }

    @Override
    public List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds>
            getProyectosWithOdsByUsuario(Integer usuarioId) {
        return masterProjectRepository.findByUsuarioWithOds(usuarioId);
    }

    // ── Helpers internos ─────────────────────────────────────────────────

    private com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?>
            findServiceForOds(Integer odsId) {
        if (odsId == null) return null;
        String prefix = String.format("Objetivo%02d", odsId);
        for (com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> s : odsServices) {
            if (s.getClass().getSimpleName().startsWith(prefix)) return s;
        }
        return null;
    }

    /**
     * Crea el ProyectoIndicadores vía reflexión-light: usamos el contrato genérico
     * IOdsBaseService.saveIndicador(E indicador) y luego saveMetaProyecto para
     * cada parámetro explícito. El POJO E varía por ODS pero todos extienden el
     * mismo shape (proyectoId, indicadorMasterId, metaValor, metaUnidad, formulaCustom).
     */
    private Map<String, Object> createIndicadorViaService(
            com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> svc,
            Integer proyectoId,
            Map<String, Object> indSpec) throws Exception {

        Integer odsId             = toInt(indSpec.get("odsId"));
        Integer indicadorMasterId = toInt(indSpec.get("indicadorMasterId"));
        java.math.BigDecimal metaValor = toBigDecimal(indSpec.get("metaValor"));
        String metaUnidad   = strOr(indSpec.get("metaUnidad"),   "unidad");
        String metaNombre   = strOr(indSpec.get("metaNombre"),   null);
        String formula      = strOr(indSpec.get("formulaCustom"), null);

        // Instanciar el POJO de ProyectoIndicadores del ODS correcto vía reflexión.
        String pkg = String.format(
            "com.odsProject.odsProject.database.jooq.ods%02d.tables.pojos.ProyectoIndicadores", odsId);
        Class<?> pojoClass = Class.forName(pkg);
        Object pojo = pojoClass.getDeclaredConstructor().newInstance();
        pojoClass.getMethod("setProyectoId", Integer.class).invoke(pojo, proyectoId);
        pojoClass.getMethod("setIndicadorMasterId", Integer.class).invoke(pojo, indicadorMasterId);
        pojoClass.getMethod("setMetaValor", java.math.BigDecimal.class).invoke(pojo, metaValor);
        pojoClass.getMethod("setMetaUnidad", String.class).invoke(pojo, metaUnidad);
        pojoClass.getMethod("setMetaNombre", String.class).invoke(pojo, metaNombre);
        pojoClass.getMethod("setFormulaCustom", String.class).invoke(pojo, formula);

        java.lang.reflect.Method saveIndicadorMethod =
                svc.getClass().getMethod("saveIndicador", pojoClass);
        Object savedIndicator = saveIndicadorMethod.invoke(svc, pojo);
        Integer proyectoIndicadorId = (Integer) pojoClass.getMethod("getId").invoke(savedIndicator);

        // Parámetros explícitos (si los hay) — refinan los auto-sembrados por Sprint 3
        int parametrosGuardados = 0;
        Object paramsObj = indSpec.get("parametros");
        if (paramsObj instanceof java.util.List<?> paramList) {
            String paramPkg = String.format(
                "com.odsProject.odsProject.database.jooq.ods%02d.tables.pojos.ProyectoIndicadorParametros", odsId);
            Class<?> paramClass = Class.forName(paramPkg);
            String tipoEnumPkg = String.format(
                "com.odsProject.odsProject.database.jooq.ods%02d.enums.ProyectoIndicadorParametrosTipoDato", odsId);
            Class<?> tipoEnum = Class.forName(tipoEnumPkg);

            for (Object p : paramList) {
                if (!(p instanceof Map)) continue;
                Map<String, Object> pm = asStringObjectMap(p);
                String nombre   = strOr(pm.get("nombreParametro"), null);
                if (nombre == null || nombre.isBlank()) continue;
                String variable = strOr(pm.get("nombreVariable"), nombre);
                String tipoStr  = strOr(pm.get("tipoDato"), "Decimal");

                Object pPojo = paramClass.getDeclaredConstructor().newInstance();
                paramClass.getMethod("setProyectoIndicadorId", Integer.class).invoke(pPojo, proyectoIndicadorId);
                paramClass.getMethod("setNombreParametro", String.class).invoke(pPojo, nombre);
                paramClass.getMethod("setNombreVariable", String.class).invoke(pPojo, variable);
                Object tipoVal = enumConstant(tipoEnum, tipoStr, "Decimal");
                paramClass.getMethod("setTipoDato", tipoEnum).invoke(pPojo, tipoVal);
                paramClass.getMethod("setValorActual", java.math.BigDecimal.class)
                          .invoke(pPojo, java.math.BigDecimal.ZERO);
                java.lang.reflect.Method saveMetaMethod =
                        svc.getClass().getMethod("saveMetaProyecto", paramClass);
                saveMetaMethod.invoke(svc, pPojo);
                parametrosGuardados++;
            }
        }

        Map<String, Object> out = new java.util.LinkedHashMap<>();
        out.put("odsId", odsId);
        out.put("indicadorMasterId", indicadorMasterId);
        out.put("proyectoIndicadorId", proyectoIndicadorId);
        out.put("parametros", parametrosGuardados);
        return out;
    }

    private static Map<String, Object> asStringObjectMap(Object raw) {
        if (!(raw instanceof Map<?, ?> source)) {
            throw new IllegalArgumentException("Se esperaba un objeto JSON/map");
        }
        Map<String, Object> out = new java.util.LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : source.entrySet()) {
            out.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return out;
    }

    private static List<Map<String, Object>> asMapList(Object raw) {
        if (raw == null) return java.util.Collections.emptyList();
        if (!(raw instanceof List<?> list)) return java.util.Collections.emptyList();
        List<Map<String, Object>> out = new java.util.ArrayList<>();
        for (Object item : list) {
            if (item instanceof Map<?, ?>) {
                out.add(asStringObjectMap(item));
            }
        }
        return out;
    }

    private static Object enumConstant(Class<?> enumClass, String name, String fallback)
            throws ReflectiveOperationException {
        java.lang.reflect.Method valueOf = enumClass.getMethod("valueOf", String.class);
        try {
            return valueOf.invoke(null, name);
        } catch (java.lang.reflect.InvocationTargetException ex) {
            return valueOf.invoke(null, fallback);
        }
    }

    private Proyectos mapToProyectos(Map<String, Object> m) {
        Proyectos p = new Proyectos();
        p.setUsuarioId(toInt(m.get("usuarioId")));
        p.setSedeId(toInt(m.get("sedeId")));
        p.setNombreProyecto(strOr(m.get("nombreProyecto"), null));
        p.setDescripcion(strOr(m.get("descripcion"), null));
        Object fi = m.get("fechaInicio");
        Object ff = m.get("fechaFin");
        if (fi != null) p.setFechaInicio(java.time.LocalDate.parse(String.valueOf(fi)));
        if (ff != null) p.setFechaFin(java.time.LocalDate.parse(String.valueOf(ff)));
        p.setMetaGeneral(strOr(m.get("metaGeneral"), null));
        p.setAliadoExterno(strOr(m.get("aliadoExterno"), null));
        p.setLocationProvince(strOr(m.get("locationProvince"), null));
        p.setLocationCanton(strOr(m.get("locationCanton"), null));
        p.setLocationDistrict(strOr(m.get("locationDistrict"), null));
        String estado = strOr(m.get("estado"), "planificacion");
        try {
            p.setEstado(com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectosEstado.valueOf(estado));
        } catch (IllegalArgumentException ex) {
            p.setEstado(com.odsProject.odsProject.database.jooq.ods_master.enums.ProyectosEstado.planificacion);
        }
        return p;
    }

    private static Integer toInt(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        try { return Integer.valueOf(String.valueOf(v)); }
        catch (NumberFormatException e) { return null; }
    }

    private static java.math.BigDecimal toBigDecimal(Object v) {
        if (v == null) return java.math.BigDecimal.ZERO;
        if (v instanceof java.math.BigDecimal bd) return bd;
        if (v instanceof Number n) return java.math.BigDecimal.valueOf(n.doubleValue());
        try { return new java.math.BigDecimal(String.valueOf(v)); }
        catch (NumberFormatException e) { return java.math.BigDecimal.ZERO; }
    }

    private static List<Integer> toIntList(Object v) {
        if (v == null) return null;
        if (v instanceof List<?> l) {
            List<Integer> result = new java.util.ArrayList<>();
            for (Object o : l) {
                Integer n = toInt(o);
                if (n != null) result.add(n);
            }
            return result;
        }
        return null;
    }

    private static String strOr(Object v, String fallback) {
        if (v == null) return fallback;
        String s = String.valueOf(v).trim();
        return s.isEmpty() ? fallback : s;
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Sprint 15 — Máquina de estados del proyecto
    //
    //  Transiciones permitidas:
    //    planificacion → activo                   (auto al primer indicador)
    //    activo        → en_revision              (Sprint 16: Gestor envía)
    //    en_revision   → completado               (Sprint 17: Auditor aprueba)
    //    en_revision   → activo                   (Sprint 17: Auditor rechaza)
    //    (cualquiera)  → cancelado                (Admin cancela)
    //
    //  El método transitionState() es defensivo: valida la transición contra
    //  esta tabla y rechaza con IllegalStateException si no es permitida.
    // ═══════════════════════════════════════════════════════════════════════

    private static final java.util.Map<String, java.util.Set<String>> ALLOWED_TRANSITIONS =
        java.util.Map.of(
            "planificacion", java.util.Set.of("activo", "en_revision", "cancelado"),
            "activo",        java.util.Set.of("en_revision", "cancelado"),
            "en_revision",   java.util.Set.of("completado", "activo", "cancelado"),
            "completado",    java.util.Set.of("cancelado"),    // mayoritariamente terminal
            "cancelado",     java.util.Set.of()                // estrictamente terminal
        );

    /** Roles que pueden disparar cada tipo de transición. */
    private static final java.util.Map<String, java.util.Set<String>> ROLES_BY_TARGET =
        java.util.Map.of(
            "activo",      java.util.Set.of("gestor", "admin", "evaluador"),
            "en_revision", java.util.Set.of("gestor"),
            "completado",  java.util.Set.of("admin", "evaluador"),
            "cancelado",   java.util.Set.of("admin", "evaluador")
        );

    @Override
    public Map<String, Object> transitionState(Integer proyectoId,
                                               String nuevoEstado,
                                               Integer actorUserId,
                                               String actorRole,
                                               String observaciones) {
        if (proyectoId == null || nuevoEstado == null)
            throw new IllegalArgumentException("proyectoId y nuevoEstado son requeridos");

        Proyectos p = masterProjectRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));

        String currentEstado = String.valueOf(p.getEstado()).toLowerCase();
        String target        = nuevoEstado.toLowerCase();

        if ("planificacion".equals(currentEstado)) {
            if ("activo".equals(target) || "cancelado".equals(target)) {
                throw new IllegalStateException(
                        "Use POST /api/projects/{id}/planificacion/solicitud/aprobar para salir de planificacion");
            }
            if ("en_revision".equals(target)) {
                throw new IllegalStateException(
                        "Use POST /api/projects/{id}/enviar-evaluacion para enviar a revisión");
            }
        }

        // 1. ¿La transición es legal por máquina de estados?
        java.util.Set<String> permitidas = ALLOWED_TRANSITIONS.getOrDefault(currentEstado, java.util.Set.of());
        if (!permitidas.contains(target)) {
            throw new IllegalStateException(
                "Transición no permitida: " + currentEstado + " → " + target);
        }

        // 2. ¿El rol puede disparar esta transición?
        String role = actorRole != null ? actorRole.toLowerCase() : "";
        java.util.Set<String> rolesAutorizados = ROLES_BY_TARGET.getOrDefault(target, java.util.Set.of());
        if (!rolesAutorizados.contains(role)) {
            throw new SecurityException(
                "Rol '" + role + "' no autorizado para transición → " + target);
        }

        // 3. Stamping condicional según destino
        boolean stampCierre  = "completado".equals(target);
        boolean stampEnvio   = "en_revision".equals(target);
        Integer auditorStamp = stampCierre ? actorUserId : null;

        int rows = masterProjectRepository.updateEstado(
            proyectoId, target, auditorStamp, observaciones, stampCierre, stampEnvio);
        if (rows == 0)
            throw new IllegalStateException("UPDATE no afectó filas (carrera de concurrencia?)");

        // 4. Devolver el proyecto actualizado en formato amigable para el frontend
        Proyectos updated = masterProjectRepository.findById(proyectoId).orElse(null);
        Map<String, Object> resp = new java.util.LinkedHashMap<>();
        resp.put("success", true);
        resp.put("proyecto", updated);
        resp.put("estadoAnterior", currentEstado);
        resp.put("estadoNuevo", target);
        return resp;
    }

    @Override
    public Map<String, Object> transitionStatePlanificacionAprobada(Integer proyectoId,
                                                                   String nuevoEstado,
                                                                   Integer actorUserId,
                                                                   String actorRole,
                                                                   String observaciones) {
        Proyectos p = masterProjectRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
        String currentEstado = String.valueOf(p.getEstado()).toLowerCase();
        String target = nuevoEstado.toLowerCase();
        if (!"planificacion".equals(currentEstado)) {
            throw new IllegalStateException("La aprobación solo aplica en planificacion");
        }
        if (!"activo".equals(target) && !"cancelado".equals(target)) {
            throw new IllegalArgumentException("Destino inválido tras aprobación: " + target);
        }
        String role = actorRole != null ? actorRole.trim().toLowerCase() : "";
        if (!java.util.Set.of("admin", "evaluador").contains(role)) {
            throw new SecurityException("Solo admin o evaluador pueden aprobar la solicitud");
        }
        return applyTransition(proyectoId, p, currentEstado, target, role, actorUserId, observaciones);
    }

    private Map<String, Object> applyTransition(Integer proyectoId,
                                                Proyectos p,
                                                String currentEstado,
                                                String target,
                                                String role,
                                                Integer actorUserId,
                                                String observaciones) {
        java.util.Set<String> permitidas = ALLOWED_TRANSITIONS.getOrDefault(currentEstado, java.util.Set.of());
        if (!permitidas.contains(target)) {
            throw new IllegalStateException(
                "Transición no permitida: " + currentEstado + " → " + target);
        }
        java.util.Set<String> rolesAutorizados = ROLES_BY_TARGET.getOrDefault(target, java.util.Set.of());
        if (!rolesAutorizados.contains(role)) {
            throw new SecurityException(
                "Rol '" + role + "' no autorizado para transición → " + target);
        }
        boolean stampCierre  = "completado".equals(target);
        boolean stampEnvio   = "en_revision".equals(target);
        Integer auditorStamp = stampCierre ? actorUserId : null;
        int rows = masterProjectRepository.updateEstado(
            proyectoId, target, auditorStamp, observaciones, stampCierre, stampEnvio);
        if (rows == 0)
            throw new IllegalStateException("UPDATE no afectó filas (carrera de concurrencia?)");
        Proyectos updated = masterProjectRepository.findById(proyectoId).orElse(null);
        Map<String, Object> resp = new java.util.LinkedHashMap<>();
        resp.put("success", true);
        resp.put("proyecto", updated);
        resp.put("estadoAnterior", currentEstado);
        resp.put("estadoNuevo", target);
        return resp;
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Sprint 16 — Gestor envía proyecto a auditoría
    // ═══════════════════════════════════════════════════════════════════════

    @Override
    public Map<String, Object> enviarEvaluacion(Integer proyectoId, Integer gestorUserId) {
        if (gestorUserId == null)
            throw new IllegalArgumentException("actorUserId requerido");

        Proyectos p = masterProjectRepository.findById(proyectoId)
            .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));

        // Sólo el dueño del proyecto puede enviarlo a revisión
        if (!gestorUserId.equals(p.getUsuarioId())) {
            throw new SecurityException("Solo el gestor dueño del proyecto puede enviarlo a evaluación");
        }

        // Precondiciones de negocio
        int indicadores = masterProjectRepository.countIndicadoresByProyecto(proyectoId);
        if (indicadores == 0) {
            throw new IllegalStateException("Debe configurar al menos un indicador antes de enviar a evaluación");
        }
        int documentos = masterProjectRepository.countDocumentosByProyecto(proyectoId);
        if (documentos == 0) {
            throw new IllegalStateException("Debe subir al menos un documento de evidencia antes de enviar a evaluación");
        }

        return transitionState(proyectoId, "en_revision", gestorUserId, "gestor", null);
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Sprint 17 — Auditor cierra (aprueba) o rechaza
    // ═══════════════════════════════════════════════════════════════════════

    @Override
    public Map<String, Object> aprobarEvaluacion(Integer proyectoId,
                                                 Integer evaluadorUserId,
                                                 String evaluadorRole,
                                                 String observaciones) {
        if (evaluadorUserId == null || evaluadorRole == null)
            throw new IllegalArgumentException("actorUserId y actorRole requeridos");
        String role = evaluadorRole.toLowerCase();
        if (!(role.equals("admin") || role.equals("evaluador"))) {
            throw new SecurityException("Solo admin o evaluador pueden aprobar evaluaciones");
        }
        // Precondición central: TODOS los indicadores deben tener al menos una medición
        if (!masterProjectRepository.allIndicadoresTienenMedicion(proyectoId)) {
            throw new IllegalStateException(
                "No se puede cerrar: faltan mediciones en uno o más indicadores");
        }
        return transitionState(proyectoId, "completado", evaluadorUserId, role, observaciones);
    }

    @Override
    public Map<String, Object> rechazarEvaluacion(Integer proyectoId,
                                                  Integer evaluadorUserId,
                                                  String evaluadorRole,
                                                  String motivoRechazo) {
        if (evaluadorUserId == null || evaluadorRole == null)
            throw new IllegalArgumentException("actorUserId y actorRole requeridos");
        if (motivoRechazo == null || motivoRechazo.trim().length() < 10) {
            throw new IllegalArgumentException("El motivo de rechazo debe tener al menos 10 caracteres");
        }
        String role = evaluadorRole.toLowerCase();
        if (!(role.equals("admin") || role.equals("evaluador"))) {
            throw new SecurityException("Solo admin o evaluador pueden rechazar evaluaciones");
        }
        // El motivo se persiste en observaciones_cierre para que el gestor lo lea como banner
        return transitionState(proyectoId, "activo", evaluadorUserId, role, motivoRechazo.trim());
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Sprint 19 — Métricas para AuditQueuePage
    // ═══════════════════════════════════════════════════════════════════════

    @Override
    public Map<String, Object> getEvaluationQueueMetrics() {
        return masterProjectRepository.auditQueueMetrics();
    }
}
