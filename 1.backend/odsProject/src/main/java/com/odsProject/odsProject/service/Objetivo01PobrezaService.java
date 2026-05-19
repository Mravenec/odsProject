package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.AuditoriaOds01;
import com.odsProject.odsProject.repository.Objetivo01PobrezaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo01PobrezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 1: Fin de la Pobreza
 */
@Service
public class Objetivo01PobrezaService implements IObjetivo01PobrezaService {

    @Autowired
    private Objetivo01PobrezaRepository objetivo01PobrezaRepository;

    @Autowired
    private com.odsProject.odsProject.service.interfaces.IEvaluationService evaluationService;

    
    @Autowired
    private com.odsProject.odsProject.repository.MasterProjectRepository masterProjectRepository;

@Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo01PobrezaRepository.findAllIndicadoresByProyectoOds01(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_1_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_2_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_2_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_3_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_4_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_4_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_3(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_5_4(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_5_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_a_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_a_2(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_a_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_1_b_1(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicador_1_b_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds01(Integer proyectoId) { return objetivo01PobrezaRepository.findAllIndicadoresByProyectoOds01(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo01PobrezaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds01() { return objetivo01PobrezaRepository.findAllProyectosOds01(); }
    @Override public Optional<Proyectos> getProjectOds01ById(Integer proyectoId) { return objetivo01PobrezaRepository.findProyectoOds01ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds01(Integer proyectoId) { return objetivo01PobrezaRepository.findAllMetasProyectoOds01(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds01ById(Integer metaId) { return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds01(Integer indicadorId) { return objetivo01PobrezaRepository.findAllMedicionesHistoricasOds01(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds01ById(Integer medicionId) { return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds01Statistics() {
        List<Proyectos> proyectos = objetivo01PobrezaRepository.findAllProyectosOds01();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo01PobrezaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo01PobrezaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo01PobrezaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo01PobrezaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo01PobrezaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo01PobrezaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo01PobrezaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo01PobrezaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        ProyectoIndicadores saved = objetivo01PobrezaRepository.saveIndicador(indicador);
        if (saved != null && saved.getFormulaCustom() != null) {
            seedParametrosFromFormula(saved.getId(), saved.getFormulaCustom());
        }
        return saved;
    }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo01PobrezaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo01PobrezaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo01PobrezaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo01PobrezaRepository.findMetaProyectoOds01ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo01PobrezaRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo01PobrezaRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo01PobrezaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo01PobrezaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo01PobrezaRepository.findMedicionHistoricaOds01ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo01PobrezaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo01PobrezaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo01PobrezaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds01Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo01PobrezaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo01PobrezaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo01PobrezaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo01PobrezaRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo01PobrezaRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo01PobrezaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        // Obtener todos los parámetros de este indicador
        List<ProyectoIndicadorParametros> parametros = objetivo01PobrezaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
        java.util.Map<String, java.math.BigDecimal> paramsMap = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) {
            String varName = p.getNombreVariable() != null ? p.getNombreVariable() : p.getNombreParametro();
            if (varName != null) {
                paramsMap.put(varName, p.getValorActual() != null ? p.getValorActual() : java.math.BigDecimal.ZERO);
            }
        }

        try {
            java.math.BigDecimal result = evaluationService.evaluateFormula(indicador.getFormulaCustom(), paramsMap);
            indicador.setValorActual(result);
            objetivo01PobrezaRepository.updateIndicador(indicador);
        } catch (Exception e) {
            // Log error or handle gracefully
            System.err.println("Error recalculando indicador " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 2/5: Medición auditada y traza de auditoría
    //  El cálculo lo hace el backend; el cliente NUNCA puede inyectar valor.
    // ─────────────────────────────────────────────────────────────────────

    @Override
    @org.springframework.transaction.annotation.Transactional("txManagerOds01")
    @SuppressWarnings("unchecked")
    public java.util.Map<String, Object> saveMedicionAuditada(java.util.Map<String, Object> payload) {
        if (payload == null) throw new IllegalArgumentException("payload requerido");


        // Sprint 18 — Inmutabilidad post-auditoría (service-level guard)
        // El trigger de BD también bloquea, pero acá lo agarramos primero para
        // devolver un 409 limpio (IllegalStateException) en vez de propagar
        // DataAccessException con stack trace.
        Integer __pidCheck = toInt(payload.get("proyectoIndicadorId"));
        if (__pidCheck != null) {
            objetivo01PobrezaRepository.findIndicadorByIdEntity(__pidCheck).ifPresent(__ind -> {
                Integer __pid = __ind.getProyectoId();
                if (__pid != null) {
                    masterProjectRepository.findById(__pid).ifPresent(__p -> {
                        String __est = String.valueOf(__p.getEstado()).toLowerCase();
                        if ("completado".equals(__est) || "cancelado".equals(__est)) {
                            throw new IllegalStateException(
                                "Proyecto auditado o cancelado: no se permiten nuevas mediciones");
                        }
                    });
                }
            });
        }

        Integer proyectoIndicadorId = toInt(payload.get("proyectoIndicadorId"));
        if (proyectoIndicadorId == null)
            throw new IllegalArgumentException("proyectoIndicadorId es requerido");

        Optional<ProyectoIndicadores> optInd = objetivo01PobrezaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optInd.isEmpty())
            throw new IllegalArgumentException("Indicador no encontrado: " + proyectoIndicadorId);
        ProyectoIndicadores indicador = optInd.get();

        // 1. Reconstruir Map<nombre_variable, valor> a partir de los IDs ingresados
        java.util.List<ProyectoIndicadorParametros> parametros =
            objetivo01PobrezaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        java.util.Map<Integer, ProyectoIndicadorParametros> paramsById = new java.util.HashMap<>();
        for (ProyectoIndicadorParametros p : parametros) paramsById.put(p.getId(), p);

        java.util.Map<String, java.math.BigDecimal> formulaParams = new java.util.HashMap<>();
        java.util.Map<Integer, java.math.BigDecimal> valoresPorParametroId = new java.util.LinkedHashMap<>();

        Object valoresRaw = payload.get("valoresParametros");
        if (valoresRaw instanceof java.util.Map) {
            for (java.util.Map.Entry<?, ?> e : ((java.util.Map<?, ?>) valoresRaw).entrySet()) {
                Integer paramId = toInt(e.getKey());
                java.math.BigDecimal valor = toBigDecimal(e.getValue());
                if (paramId == null || valor == null) continue;
                ProyectoIndicadorParametros p = paramsById.get(paramId);
                if (p == null) continue;
                String varName = p.getNombreVariable() != null ? p.getNombreVariable() : p.getNombreParametro();
                if (varName != null) formulaParams.put(varName, valor);
                valoresPorParametroId.put(paramId, valor);
            }
        }

        // 2. Calcular server-side
        String formula = indicador.getFormulaCustom();
        java.math.BigDecimal valorCalculado;
        if (formula == null || formula.trim().isEmpty() || "valor".equalsIgnoreCase(formula.trim())) {
            // Indicador sin fórmula: usar la suma de los valores como fallback
            java.math.BigDecimal sum = java.math.BigDecimal.ZERO;
            for (java.math.BigDecimal v : formulaParams.values()) sum = sum.add(v);
            valorCalculado = sum.setScale(4, java.math.RoundingMode.HALF_UP);
        } else {
            valorCalculado = evaluationService.evaluateFormula(formula, formulaParams);
        }

        // 3. Persistir medición + valores de parámetros (misma transacción)
        MedicionesHistoricas medicion = new MedicionesHistoricas();
        medicion.setProyectoIndicadorId(proyectoIndicadorId);
        medicion.setValorCalculado(valorCalculado);
        Object fechaRaw = payload.get("fechaMedicion");
        try {
            medicion.setFechaMedicion(fechaRaw != null
                ? java.time.LocalDate.parse(String.valueOf(fechaRaw))
                : java.time.LocalDate.now());
        } catch (java.time.format.DateTimeParseException ex) {
            medicion.setFechaMedicion(java.time.LocalDate.now());
        }
        Object resp = payload.get("responsable");
        if (resp != null) medicion.setResponsable(String.valueOf(resp));
        Object metodo = payload.get("metodoMedicion");
        if (metodo != null) medicion.setMetodoMedicion(String.valueOf(metodo));
        Object obs = payload.get("observaciones");
        if (obs != null) medicion.setObservaciones(String.valueOf(obs));

        MedicionesHistoricas saved = objetivo01PobrezaRepository.saveMedicion(medicion);

        for (java.util.Map.Entry<Integer, java.math.BigDecimal> e : valoresPorParametroId.entrySet()) {
            objetivo01PobrezaRepository.insertMedicionParametroValor(saved.getId(), e.getKey(), e.getValue());
        }

        // 4. Construir respuesta auditable
        java.math.BigDecimal metaValor = indicador.getMetaValor() != null
            ? indicador.getMetaValor() : java.math.BigDecimal.ZERO;
        boolean alcanzada = evaluationService.metaAlcanzada(valorCalculado, metaValor);

        java.util.Map<String, Object> resp2 = new java.util.LinkedHashMap<>();
        resp2.put("medicion", saved);
        resp2.put("formula", formula);
        resp2.put("valor", valorCalculado);
        resp2.put("metaValor", metaValor);
        resp2.put("metaUnidad", indicador.getMetaUnidad());
        resp2.put("metaAlcanzada", alcanzada);
        resp2.put("estado", alcanzada ? "LOGRADO" : (
            metaValor.signum() > 0
                && valorCalculado.compareTo(metaValor.multiply(new java.math.BigDecimal("0.8"))) >= 0
                ? "CERCA META"
                : metaValor.signum() > 0
                    && valorCalculado.compareTo(metaValor.multiply(new java.math.BigDecimal("0.5"))) >= 0
                    ? "PROGRESO" : "BAJO"
        ));
        resp2.put("parametros", formulaParams);
        resp2.put("valoresParametros", valoresPorParametroId);
        return resp2;
    }

    @Override
    public java.util.Map<String, Object> getMedicionAuditoria(Integer medicionId) {
        if (medicionId == null) throw new IllegalArgumentException("medicionId requerido");

        MedicionesHistoricas medicion = objetivo01PobrezaRepository.findMedicionByIdEntity(medicionId);
        if (medicion == null) throw new IllegalArgumentException("Medición no encontrada: " + medicionId);

        Optional<ProyectoIndicadores> optInd =
            objetivo01PobrezaRepository.findIndicadorByIdEntity(medicion.getProyectoIndicadorId());
        ProyectoIndicadores indicador = optInd.orElse(null);

        java.util.List<java.util.Map<String, Object>> valores =
            objetivo01PobrezaRepository.findMedicionParametroValoresByMedicion(medicionId);

        java.util.Map<String, Object> out = new java.util.LinkedHashMap<>();
        out.put("medicion", medicion);
        if (indicador != null) {
            out.put("formula", indicador.getFormulaCustom());
            out.put("metaValor", indicador.getMetaValor());
            out.put("metaUnidad", indicador.getMetaUnidad());
            out.put("metaNombre", indicador.getMetaNombre());
            out.put("metaAlcanzada",
                evaluationService.metaAlcanzada(medicion.getValorCalculado(), indicador.getMetaValor()));
        }
        out.put("valoresParametros", valores);
        return out;
    }

    /**
     * Sprint 3 — Auto-siembra parámetros a partir de las variables de la fórmula.
     * Crea registros en proyecto_indicador_parametros para cada variable detectada
     * que aún no exista. Tipo por defecto: Decimal, valor inicial: 0.
     */
    private void seedParametrosFromFormula(Integer proyectoIndicadorId, String formula) {
        if (proyectoIndicadorId == null || formula == null || formula.isBlank()) return;
        java.util.Set<String> variables =
            com.odsProject.odsProject.service.FormulaUtils.extractVariables(formula);
        if (variables.isEmpty()) return;

        java.util.List<ProyectoIndicadorParametros> existentes =
            objetivo01PobrezaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        java.util.Set<String> yaSembradas = new java.util.HashSet<>();
        for (ProyectoIndicadorParametros p : existentes) {
            if (p.getNombreVariable() != null) yaSembradas.add(p.getNombreVariable());
            if (p.getNombreParametro() != null) yaSembradas.add(p.getNombreParametro());
        }

        for (String v : variables) {
            if (yaSembradas.contains(v)) continue;
            ProyectoIndicadorParametros nuevo = new ProyectoIndicadorParametros();
            nuevo.setProyectoIndicadorId(proyectoIndicadorId);
            nuevo.setNombreParametro(v);
            nuevo.setNombreVariable(v);
            nuevo.setValorActual(java.math.BigDecimal.ZERO);
            try {
                objetivo01PobrezaRepository.saveMetaProyecto(nuevo);
            } catch (Exception ex) {
                System.err.println("[seedParametrosFromFormula] Variable '" + v + "': " + ex.getMessage());
            }
        }
    }

    private static Integer toInt(Object v) {
        if (v == null) return null;
        if (v instanceof Number) return ((Number) v).intValue();
        try { return Integer.valueOf(String.valueOf(v)); }
        catch (NumberFormatException e) { return null; }
    }

    private static java.math.BigDecimal toBigDecimal(Object v) {
        if (v == null) return null;
        if (v instanceof java.math.BigDecimal) return (java.math.BigDecimal) v;
        if (v instanceof Number) return java.math.BigDecimal.valueOf(((Number) v).doubleValue());
        try { return new java.math.BigDecimal(String.valueOf(v)); }
        catch (NumberFormatException e) { return null; }
    }

}
