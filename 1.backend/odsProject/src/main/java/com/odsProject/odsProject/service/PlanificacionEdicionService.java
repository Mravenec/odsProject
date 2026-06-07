package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.service.interfaces.IPlanificacionEdicionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class PlanificacionEdicionService implements IPlanificacionEdicionService {

    @Autowired
    private IMasterProjectRepository masterProjectRepository;

    @Autowired
    private List<com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?>> odsServices;

    @Override
    public void assertCanEditPlanificacion(Integer proyectoId, Integer actorUserId, String actorRole) {
        Proyectos existing = masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
        String role = actorRole != null ? actorRole.trim().toLowerCase() : "";
        String estado = String.valueOf(existing.getEstado()).toLowerCase();
        if (!"planificacion".equals(estado)) {
            throw new SecurityException("Solo se puede editar en planificacion; estado actual: " + estado);
        }
        if ("admin".equals(role) || "evaluador".equals(role)) {
            return;
        }
        if ("gestor".equals(role)) {
            if (actorUserId == null || !actorUserId.equals(existing.getUsuarioId())) {
                throw new SecurityException("Solo el gestor dueño puede editar el proyecto");
            }
            return;
        }
        throw new SecurityException("Rol no autorizado para editar en planificacion: " + role);
    }

    @Override
    public Map<String, Object> buildEditableSnapshot(Integer proyectoId, Integer actorUserId, String actorRole) {
        assertCanEditPlanificacion(proyectoId, actorUserId, actorRole);
        Proyectos p = masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));

        List<Map<String, Object>> odsLinks = masterProjectRepository.findOdsByProyecto(proyectoId);
        List<Integer> odsIds = new ArrayList<>();
        Integer primaryOdsId = null;
        for (Map<String, Object> link : odsLinks) {
            Integer odsId = toInt(link.get("ods_id"));
            if (odsId == null) odsId = toInt(link.get("odsId"));
            if (odsId != null) odsIds.add(odsId);
            Boolean prim = link.get("es_primario") instanceof Boolean b ? b
                    : Boolean.TRUE.equals(link.get("esPrimario"));
            if (Boolean.TRUE.equals(prim)) primaryOdsId = odsId;
        }

        List<Map<String, Object>> indicadores = new ArrayList<>();
        for (int ods = 1; ods <= 17; ods++) {
            var svc = findServiceForOds(ods);
            if (svc == null) continue;
            List<?> rows;
            try {
                rows = svc.findAllIndicadoresByProyecto(proyectoId);
            } catch (Exception e) {
                continue;
            }
            if (rows == null || rows.isEmpty()) continue;
            for (Object row : rows) {
                Map<String, Object> ind = mapEnrichedIndicator(row, ods, proyectoId, svc);
                if (ind != null) indicadores.add(ind);
            }
        }

        Map<String, Object> proyecto = new LinkedHashMap<>();
        proyecto.put("id", p.getId());
        proyecto.put("usuarioId", p.getUsuarioId());
        proyecto.put("sedeId", p.getSedeId());
        proyecto.put("nombreProyecto", p.getNombreProyecto());
        proyecto.put("descripcion", p.getDescripcion());
        proyecto.put("fechaInicio", p.getFechaInicio() != null ? p.getFechaInicio().toString() : null);
        proyecto.put("fechaFin", p.getFechaFin() != null ? p.getFechaFin().toString() : null);
        proyecto.put("metaGeneral", p.getMetaGeneral());
        proyecto.put("responsableNombre", p.getResponsableNombre());
        proyecto.put("locationProvince", p.getLocationProvince());
        proyecto.put("locationCanton", p.getLocationCanton());
        proyecto.put("locationDistrict", p.getLocationDistrict());
        proyecto.put("estado", String.valueOf(p.getEstado()));

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("proyectoId", proyectoId);
        out.put("proyecto", proyecto);
        out.put("odsIds", odsIds);
        out.put("primaryOdsId", primaryOdsId);
        out.put("indicadores", indicadores);
        out.put("success", true);
        return out;
    }

    @Override
    @Transactional("txManagerMaster")
    public Map<String, Object> updateFullProject(Integer proyectoId, Map<String, Object> payload,
                                                 Integer actorUserId, String actorRole) {
        if (payload == null) throw new IllegalArgumentException("payload requerido");
        assertCanEditPlanificacion(proyectoId, actorUserId, actorRole);

        List<Map<String, Object>> errores = new ArrayList<>();
        List<Map<String, Object>> indicadoresActualizados = new ArrayList<>();
        List<Integer> odsVinculados = new ArrayList<>();

        Object proyectoRaw = payload.get("proyecto");
        if (proyectoRaw instanceof Map<?, ?> pm) {
            Proyectos patch = mapToProyectos(asStringObjectMap(pm));
            Proyectos existing = masterProjectRepository.findById(proyectoId).orElseThrow();
            if (patch.getNombreProyecto() != null) existing.setNombreProyecto(patch.getNombreProyecto());
            if (patch.getDescripcion() != null) existing.setDescripcion(patch.getDescripcion());
            if (patch.getFechaInicio() != null) existing.setFechaInicio(patch.getFechaInicio());
            if (patch.getFechaFin() != null) existing.setFechaFin(patch.getFechaFin());
            if (patch.getMetaGeneral() != null) existing.setMetaGeneral(patch.getMetaGeneral());
            if (patch.getResponsableNombre() != null) existing.setResponsableNombre(patch.getResponsableNombre());
            if (patch.getLocationProvince() != null) existing.setLocationProvince(patch.getLocationProvince());
            if (patch.getLocationCanton() != null) existing.setLocationCanton(patch.getLocationCanton());
            if (patch.getLocationDistrict() != null) existing.setLocationDistrict(patch.getLocationDistrict());
            if (patch.getSedeId() != null) existing.setSedeId(patch.getSedeId());
            masterProjectRepository.update(existing);
        }

        List<Integer> odsIds = toIntList(payload.get("odsIds"));
        Integer primaryOdsId = toInt(payload.get("primaryOdsId"));
        List<Map<String, Object>> indicadoresRaw = asMapList(payload.get("indicadores"));
        Set<Integer> odsSet = new LinkedHashSet<>();
        if (odsIds != null) odsSet.addAll(odsIds);
        for (Map<String, Object> ind : indicadoresRaw) {
            Integer odsId = toInt(ind.get("odsId"));
            if (odsId != null) odsSet.add(odsId);
        }

        List<Map<String, Object>> currentOds = masterProjectRepository.findOdsByProyecto(proyectoId);
        Set<Integer> currentIds = new HashSet<>();
        for (Map<String, Object> link : currentOds) {
            Integer oid = toInt(link.get("ods_id"));
            if (oid == null) oid = toInt(link.get("odsId"));
            if (oid != null) currentIds.add(oid);
        }
        for (Integer cur : currentIds) {
            if (!odsSet.contains(cur)) {
                masterProjectRepository.unlinkOds(proyectoId, cur);
            }
        }
        for (Integer odsId : odsSet) {
            boolean esPrimario = odsId.equals(primaryOdsId);
            masterProjectRepository.linkOds(proyectoId, odsId, esPrimario);
            odsVinculados.add(odsId);
        }

        Set<String> payloadIndicadorKeys = new LinkedHashSet<>();
        for (Map<String, Object> ind : indicadoresRaw) {
            Integer odsId = toInt(ind.get("odsId"));
            Integer masterId = toInt(ind.get("indicadorMasterId"));
            if (odsId != null && masterId != null) {
                payloadIndicadorKeys.add(odsId + ":" + masterId);
            }
        }
        syncRemovedIndicadores(proyectoId, payloadIndicadorKeys, errores);

        for (Map<String, Object> ind : indicadoresRaw) {
            Integer odsId = toInt(ind.get("odsId"));
            Integer masterId = toInt(ind.get("indicadorMasterId"));
            if (odsId == null || masterId == null) {
                errores.add(Map.of("etapa", "indicador", "error", "odsId e indicadorMasterId requeridos"));
                continue;
            }
            var svc = findServiceForOds(odsId);
            if (svc == null) {
                errores.add(Map.of("etapa", "indicador", "odsId", odsId, "error", "Sin servicio ODS"));
                continue;
            }
            try {
                Integer piId = toInt(ind.get("proyectoIndicadorId"));
                if (piId == null) {
                    piId = masterProjectRepository.findProyectoIndicadorId(odsId, proyectoId, masterId)
                            .orElse(null);
                }
                Map<String, Object> result;
                if (piId != null) {
                    result = updateIndicadorViaService(svc, odsId, piId, proyectoId, ind);
                } else {
                    ind.put("proyectoId", proyectoId);
                    result = createIndicadorViaService(svc, proyectoId, ind);
                }
                indicadoresActualizados.add(result);
            } catch (Exception e) {
                errores.add(Map.of(
                        "etapa", "indicador",
                        "odsId", odsId,
                        "indicadorMasterId", masterId,
                        "error", e.getMessage() != null ? e.getMessage() : e.getClass().getSimpleName()));
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("proyectoId", proyectoId);
        response.put("odsVinculados", odsVinculados);
        response.put("indicadoresActualizados", indicadoresActualizados);
        response.put("errores", errores);
        response.put("success", errores.stream().noneMatch(e -> "fatal".equals(e.get("etapa"))));
        return response;
    }

    private void syncRemovedIndicadores(Integer proyectoId, Set<String> payloadIndicadorKeys,
                                        List<Map<String, Object>> errores) {
        for (int ods = 1; ods <= 17; ods++) {
            var svc = findServiceForOds(ods);
            if (svc == null) continue;
            List<?> rows;
            try {
                rows = svc.findAllIndicadoresByProyecto(proyectoId);
            } catch (Exception e) {
                continue;
            }
            if (rows == null) continue;
            for (Object row : rows) {
                try {
                    Class<?> c = row.getClass();
                    Integer masterId = (Integer) invokeGetter(row, c, "getIndicadorMasterId");
                    if (masterId == null) continue;
                    String key = ods + ":" + masterId;
                    if (payloadIndicadorKeys.contains(key)) continue;
                    Integer piId = masterProjectRepository.findProyectoIndicadorId(ods, proyectoId, masterId)
                            .orElse(null);
                    if (piId == null) continue;
                    Boolean deleted = svc.deleteIndicador(piId);
                    if (!Boolean.TRUE.equals(deleted)) {
                        errores.add(Map.of(
                                "etapa", "indicador",
                                "odsId", ods,
                                "indicadorMasterId", masterId,
                                "error", "No se pudo eliminar indicador desvinculado"));
                    }
                } catch (Exception e) {
                    errores.add(Map.of(
                            "etapa", "indicador",
                            "odsId", ods,
                            "error", e.getMessage() != null ? e.getMessage() : "Error al eliminar indicador"));
                }
            }
        }
    }

    private Map<String, Object> mapEnrichedIndicator(Object row, int odsId, Integer proyectoId,
            com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> svc) {
        try {
            Class<?> c = row.getClass();
            Integer masterId = (Integer) invokeGetter(row, c, "getIndicadorMasterId");
            if (masterId == null) return null;
            Integer piId = masterProjectRepository.findProyectoIndicadorId(odsId, proyectoId, masterId)
                    .orElse(null);
            if (piId == null) {
                return null;
            }
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("proyectoIndicadorId", piId);
            m.put("odsId", odsId);
            m.put("indicadorMasterId", masterId);
            m.put("codigo", invokeGetter(row, c, "getIndicadorCodigo"));
            m.put("metaValor", invokeGetter(row, c, "getMetaValor"));
            m.put("metaUnidad", invokeGetter(row, c, "getMetaUnidad"));
            m.put("metaNombre", invokeGetter(row, c, "getMetaNombre"));
            m.put("formulaCustom", invokeGetter(row, c, "getFormulaCustom"));

            List<Map<String, Object>> params = new ArrayList<>();
            if (piId != null) {
                List<?> metas = svc.findAllMetasProyecto(proyectoId);
                for (Object meta : metas) {
                    Class<?> mc = meta.getClass();
                    Integer indId = (Integer) invokeGetter(meta, mc, "getProyectoIndicadorId");
                    if (!piId.equals(indId)) continue;
                    Map<String, Object> p = new LinkedHashMap<>();
                    p.put("id", invokeGetter(meta, mc, "getId"));
                    p.put("nombreParametro", invokeGetter(meta, mc, "getNombreParametro"));
                    p.put("nombreVariable", invokeGetter(meta, mc, "getNombreVariable"));
                    Object tipo = invokeGetter(meta, mc, "getTipoDato");
                    p.put("tipoDato", tipo != null ? String.valueOf(tipo) : "Decimal");
                    params.add(p);
                }
            }
            m.put("parametros", params);
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, Object> updateIndicadorViaService(
            com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> svc,
            Integer odsId, Integer proyectoIndicadorId, Integer proyectoId,
            Map<String, Object> indSpec) throws Exception {

        String pkg = String.format(
                "com.odsProject.odsProject.database.jooq.ods%02d.tables.pojos.ProyectoIndicadores", odsId);
        Class<?> pojoClass = Class.forName(pkg);
        Object pojo = pojoClass.getDeclaredConstructor().newInstance();
        pojoClass.getMethod("setId", Integer.class).invoke(pojo, proyectoIndicadorId);
        pojoClass.getMethod("setProyectoId", Integer.class).invoke(pojo, proyectoId);
        pojoClass.getMethod("setIndicadorMasterId", Integer.class)
                .invoke(pojo, toInt(indSpec.get("indicadorMasterId")));
        pojoClass.getMethod("setMetaValor", BigDecimal.class)
                .invoke(pojo, toBigDecimal(indSpec.get("metaValor")));
        pojoClass.getMethod("setMetaUnidad", String.class)
                .invoke(pojo, strOr(indSpec.get("metaUnidad"), "unidad"));
        pojoClass.getMethod("setMetaNombre", String.class)
                .invoke(pojo, strOr(indSpec.get("metaNombre"), null));
        pojoClass.getMethod("setFormulaCustom", String.class)
                .invoke(pojo, strOr(indSpec.get("formulaCustom"), null));

        java.lang.reflect.Method updateMethod =
                svc.getClass().getMethod("updateIndicador", pojoClass);
        updateMethod.invoke(svc, pojo);

        int parametrosGuardados = syncParametros(svc, odsId, proyectoIndicadorId, indSpec.get("parametros"));

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("odsId", odsId);
        out.put("proyectoIndicadorId", proyectoIndicadorId);
        out.put("parametros", parametrosGuardados);
        out.put("updated", true);
        return out;
    }

    private Map<String, Object> createIndicadorViaService(
            com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> svc,
            Integer proyectoId, Map<String, Object> indSpec) throws Exception {

        Integer odsId = toInt(indSpec.get("odsId"));
        Integer indicadorMasterId = toInt(indSpec.get("indicadorMasterId"));
        String pkg = String.format(
                "com.odsProject.odsProject.database.jooq.ods%02d.tables.pojos.ProyectoIndicadores", odsId);
        Class<?> pojoClass = Class.forName(pkg);
        Object pojo = pojoClass.getDeclaredConstructor().newInstance();
        pojoClass.getMethod("setProyectoId", Integer.class).invoke(pojo, proyectoId);
        pojoClass.getMethod("setIndicadorMasterId", Integer.class).invoke(pojo, indicadorMasterId);
        pojoClass.getMethod("setMetaValor", BigDecimal.class)
                .invoke(pojo, toBigDecimal(indSpec.get("metaValor")));
        pojoClass.getMethod("setMetaUnidad", String.class)
                .invoke(pojo, strOr(indSpec.get("metaUnidad"), "unidad"));
        pojoClass.getMethod("setMetaNombre", String.class)
                .invoke(pojo, strOr(indSpec.get("metaNombre"), null));
        pojoClass.getMethod("setFormulaCustom", String.class)
                .invoke(pojo, strOr(indSpec.get("formulaCustom"), null));

        java.lang.reflect.Method saveMethod = svc.getClass().getMethod("saveIndicador", pojoClass);
        Object saved = saveMethod.invoke(svc, pojo);
        Integer proyectoIndicadorId = (Integer) pojoClass.getMethod("getId").invoke(saved);

        int parametrosGuardados = syncParametros(svc, odsId, proyectoIndicadorId, indSpec.get("parametros"));

        Map<String, Object> out = new LinkedHashMap<>();
        out.put("odsId", odsId);
        out.put("indicadorMasterId", indicadorMasterId);
        out.put("proyectoIndicadorId", proyectoIndicadorId);
        out.put("parametros", parametrosGuardados);
        out.put("created", true);
        return out;
    }

    private int syncParametros(
            com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?> svc,
            Integer odsId, Integer proyectoIndicadorId, Object paramsObj) throws Exception {
        if (!(paramsObj instanceof List<?> paramList)) return 0;
        String paramPkg = String.format(
                "com.odsProject.odsProject.database.jooq.ods%02d.tables.pojos.ProyectoIndicadorParametros", odsId);
        Class<?> paramClass = Class.forName(paramPkg);
        String tipoEnumPkg = String.format(
                "com.odsProject.odsProject.database.jooq.ods%02d.enums.ProyectoIndicadorParametrosTipoDato", odsId);
        Class<?> tipoEnum = Class.forName(tipoEnumPkg);
        int count = 0;
        for (Object p : paramList) {
            if (!(p instanceof Map)) continue;
            Map<String, Object> pm = asStringObjectMap(p);
            String nombre = strOr(pm.get("nombreParametro"), null);
            if (nombre == null || nombre.isBlank()) continue;
            String variable = strOr(pm.get("nombreVariable"), nombre);
            String tipoStr = strOr(pm.get("tipoDato"), "Decimal");
            Integer paramId = toInt(pm.get("id"));

            Object pPojo = paramClass.getDeclaredConstructor().newInstance();
            if (paramId != null) {
                paramClass.getMethod("setId", Integer.class).invoke(pPojo, paramId);
            }
            paramClass.getMethod("setProyectoIndicadorId", Integer.class).invoke(pPojo, proyectoIndicadorId);
            paramClass.getMethod("setNombreParametro", String.class).invoke(pPojo, nombre);
            paramClass.getMethod("setNombreVariable", String.class).invoke(pPojo, variable);
            Object tipoVal = enumConstant(tipoEnum, tipoStr, "Decimal");
            paramClass.getMethod("setTipoDato", tipoEnum).invoke(pPojo, tipoVal);
            paramClass.getMethod("setValorActual", BigDecimal.class)
                    .invoke(pPojo, BigDecimal.ZERO);

            if (paramId != null) {
                java.lang.reflect.Method upd = svc.getClass().getMethod("updateMetaProyecto", paramClass);
                upd.invoke(svc, pPojo);
            } else {
                java.lang.reflect.Method save = svc.getClass().getMethod("saveMetaProyecto", paramClass);
                save.invoke(svc, pPojo);
            }
            count++;
        }
        return count;
    }

    private com.odsProject.odsProject.service.interfaces.IOdsBaseService<?, ?, ?, ?, ?, ?>
            findServiceForOds(Integer odsId) {
        if (odsId == null) return null;
        String prefix = String.format("Objetivo%02d", odsId);
        for (var s : odsServices) {
            if (s.getClass().getSimpleName().startsWith(prefix)) return s;
        }
        return null;
    }

    private static Object invokeGetter(Object target, Class<?> c, String method) throws Exception {
        try {
            return c.getMethod(method).invoke(target);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    private static Proyectos mapToProyectos(Map<String, Object> m) {
        Proyectos p = new Proyectos();
        p.setUsuarioId(toInt(m.get("usuarioId")));
        p.setSedeId(toInt(m.get("sedeId")));
        p.setNombreProyecto(strOr(m.get("nombreProyecto"), null));
        p.setDescripcion(strOr(m.get("descripcion"), null));
        Object fi = m.get("fechaInicio");
        Object ff = m.get("fechaFin");
        if (fi != null && !String.valueOf(fi).isBlank()) {
            p.setFechaInicio(java.time.LocalDate.parse(String.valueOf(fi)));
        }
        if (ff != null && !String.valueOf(ff).isBlank()) {
            p.setFechaFin(java.time.LocalDate.parse(String.valueOf(ff)));
        }
        p.setMetaGeneral(strOr(m.get("metaGeneral"), null));
        p.setResponsableNombre(strOr(m.get("responsableNombre"), null));
        p.setLocationProvince(strOr(m.get("locationProvince"), null));
        p.setLocationCanton(strOr(m.get("locationCanton"), null));
        p.setLocationDistrict(strOr(m.get("locationDistrict"), null));
        return p;
    }

    private static Map<String, Object> asStringObjectMap(Object raw) {
        if (!(raw instanceof Map<?, ?> source)) {
            throw new IllegalArgumentException("Se esperaba un objeto JSON/map");
        }
        Map<String, Object> out = new LinkedHashMap<>();
        for (Map.Entry<?, ?> entry : source.entrySet()) {
            out.put(String.valueOf(entry.getKey()), entry.getValue());
        }
        return out;
    }

    private static List<Map<String, Object>> asMapList(Object raw) {
        if (raw == null) return Collections.emptyList();
        if (!(raw instanceof List<?> list)) return Collections.emptyList();
        List<Map<String, Object>> out = new ArrayList<>();
        for (Object item : list) {
            if (item instanceof Map<?, ?>) out.add(asStringObjectMap(item));
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

    private static Integer toInt(Object v) {
        if (v == null) return null;
        if (v instanceof Number n) return n.intValue();
        try { return Integer.valueOf(String.valueOf(v)); }
        catch (NumberFormatException e) { return null; }
    }

    private static BigDecimal toBigDecimal(Object v) {
        if (v == null) return BigDecimal.ZERO;
        if (v instanceof BigDecimal bd) return bd;
        if (v instanceof Number n) return BigDecimal.valueOf(n.doubleValue());
        try { return new BigDecimal(String.valueOf(v)); }
        catch (NumberFormatException e) { return BigDecimal.ZERO; }
    }

    private static List<Integer> toIntList(Object v) {
        if (v == null) return null;
        if (v instanceof List<?> l) {
            List<Integer> result = new ArrayList<>();
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
}
