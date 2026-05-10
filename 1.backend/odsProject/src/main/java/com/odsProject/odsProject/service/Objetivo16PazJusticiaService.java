package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.ProyectoIndicadorParametros;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.MedicionesHistoricas;
import com.odsProject.odsProject.database.jooq.ods16.tables.pojos.AuditoriaOds16;
import com.odsProject.odsProject.repository.Objetivo16PazJusticiaRepository;
import com.odsProject.odsProject.service.interfaces.IObjetivo16PazJusticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementación del Servicio para el Objetivo 16: Paz, Justicia e Instituciones Sólidas
 */
@Service
public class Objetivo16PazJusticiaService implements IObjetivo16PazJusticiaService {

    @Autowired
    private Objetivo16PazJusticiaRepository objetivo16PazJusticiaRepository;

    @Autowired
    private EvaluationService evaluationService;

    @Override public List<VistaAdminDetalleIndicadores> getAllIndicators(Integer proyectoId) { return objetivo16PazJusticiaRepository.findAllIndicadoresByProyectoOds16(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_3(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_1_4(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_1_4(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_2_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_2_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_2_3(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_2_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_3_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_3_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_3_3(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_3_3(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_4_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_4_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_4_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_4_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_5_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_5_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_5_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_5_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_6_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_6_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_6_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_6_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_7_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_7_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_7_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_7_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_8_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_8_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_9_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_9_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_10_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_10_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_10_2(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_10_2(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_a_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_a_1(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> getIndicador_16_b_1(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicador_16_b_1(proyectoId); }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyectoOds16(Integer proyectoId) { return objetivo16PazJusticiaRepository.findAllIndicadoresByProyectoOds16(proyectoId); }
    @Override public List<VistaAdminDetalleIndicadores> findIndicadoresByMeta(Integer proyectoId, String metaPrefix) { return objetivo16PazJusticiaRepository.findIndicadoresByMeta(proyectoId, metaPrefix); }

    @Override public List<Proyectos> getAllProjectsOds16() { return objetivo16PazJusticiaRepository.findAllProyectosOds16(); }
    @Override public Optional<Proyectos> getProjectOds16ById(Integer proyectoId) { return objetivo16PazJusticiaRepository.findProyectoOds16ById(proyectoId); }
    @Override public List<ProyectoIndicadorParametros> getAllMetasProyectoOds16(Integer proyectoId) { return objetivo16PazJusticiaRepository.findAllMetasProyectoOds16(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> getMetaProyectoOds16ById(Integer metaId) { return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId); }
    @Override public List<MedicionesHistoricas> getAllMedicionesHistoricasOds16(Integer indicadorId) { return objetivo16PazJusticiaRepository.findAllMedicionesHistoricasOds16(indicadorId); }
    @Override public Optional<MedicionesHistoricas> getMedicionHistoricaOds16ById(Integer medicionId) { return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId); }

    @Override public Double calculateProjectProgress(Integer proyectoId) {
        List<VistaAdminDetalleIndicadores> indicadores = objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId);
        if (indicadores.isEmpty()) return 0.0;
        long withData = indicadores.stream().filter(ind -> ind.getValorActual() != null).count();
        return (double) withData / indicadores.size() * 100.0;
    }

    @Override public Map<String, Object> getOds16Statistics() {
        List<Proyectos> proyectos = objetivo16PazJusticiaRepository.findAllProyectosOds16();
        List<VistaAdminDetalleIndicadores> indicadores = proyectos.stream().flatMap(p -> objetivo16PazJusticiaRepository.findIndicadoresByProyecto(p.getId()).stream()).toList();
        return Map.of("totalProyectos", proyectos.size(), "totalIndicadores", indicadores.size(), "indicadoresConDatos", indicadores.stream().filter(i -> i.getValorActual() != null).count());
    }

    // IOdsBaseService implementations
    @Override public List<Proyectos> findAllProyectos() { return objetivo16PazJusticiaRepository.findAllProyectos(); }
    @Override public Optional<Proyectos> findProyectoById(Integer proyectoId) { return objetivo16PazJusticiaRepository.findProyectoById(proyectoId); }
    @Override public Proyectos saveProyecto(Proyectos proyecto) { return objetivo16PazJusticiaRepository.saveProyecto(proyecto); }
    @Override public Proyectos updateProyecto(Proyectos proyecto) { return objetivo16PazJusticiaRepository.updateProyecto(proyecto); }
    @Override public Boolean deleteProyecto(Integer proyectoId) { try { objetivo16PazJusticiaRepository.deleteProyecto(proyectoId); return true; } catch (Exception e) { return false; } }

    @Override public List<VistaAdminDetalleIndicadores> findAllIndicadoresByProyecto(Integer proyectoId) { return objetivo16PazJusticiaRepository.findIndicadoresByProyecto(proyectoId); }
    @Override public Optional<VistaAdminDetalleIndicadores> findIndicadorById(Integer indicadorId) { return objetivo16PazJusticiaRepository.findIndicadorById(indicadorId); }
    
    @Override public ProyectoIndicadores saveIndicador(ProyectoIndicadores indicador) {
        ProyectoIndicadores saved = objetivo16PazJusticiaRepository.saveIndicador(indicador);
        if (saved != null && saved.getFormulaCustom() != null) {
            seedParametrosFromFormula(saved.getId(), saved.getFormulaCustom());
        }
        return saved;
    }
    @Override public ProyectoIndicadores updateIndicador(ProyectoIndicadores indicador) { return objetivo16PazJusticiaRepository.updateIndicador(indicador); }
    @Override public Boolean deleteIndicador(Integer indicadorId) { try { objetivo16PazJusticiaRepository.deleteIndicador(indicadorId); return true; } catch (Exception e) { return false; } }

    @Override public List<ProyectoIndicadorParametros> findAllMetasProyecto(Integer proyectoId) { return objetivo16PazJusticiaRepository.findMetasByProyecto(proyectoId); }
    @Override public Optional<ProyectoIndicadorParametros> findMetaProyectoById(Integer metaId) { return objetivo16PazJusticiaRepository.findMetaProyectoOds16ById(metaId); }
    @Override public ProyectoIndicadorParametros saveMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros saved = objetivo16PazJusticiaRepository.saveMetaProyecto(meta);
        recalculateIndicator(saved.getProyectoIndicadorId());
        return saved;
    }
    @Override public ProyectoIndicadorParametros updateMetaProyecto(ProyectoIndicadorParametros meta) { 
        ProyectoIndicadorParametros updated = objetivo16PazJusticiaRepository.updateMetaProyecto(meta);
        recalculateIndicator(updated.getProyectoIndicadorId());
        return updated;
    }
    @Override public Boolean deleteMetaProyecto(Integer metaId) { try { objetivo16PazJusticiaRepository.deleteMetaProyecto(metaId); return true; } catch (Exception e) { return false; } }

    @Override public List<MedicionesHistoricas> findAllMedicionesHistoricas(Integer indicadorId) { return objetivo16PazJusticiaRepository.findMedicionesByIndicador(indicadorId); }
    @Override public Optional<MedicionesHistoricas> findMedicionHistoricaById(Integer medicionId) { return objetivo16PazJusticiaRepository.findMedicionHistoricaOds16ById(medicionId); }
    @Override public MedicionesHistoricas saveMedicionHistorica(MedicionesHistoricas medicion) { return objetivo16PazJusticiaRepository.saveMedicion(medicion); }
    @Override public MedicionesHistoricas updateMedicionHistorica(MedicionesHistoricas medicion) { return objetivo16PazJusticiaRepository.updateMedicionHistorica(medicion); }
    @Override public Boolean deleteMedicionHistorica(Integer medicionId) { try { objetivo16PazJusticiaRepository.deleteMedicionHistorica(medicionId); return true; } catch (Exception e) { return false; } }

    @Override public Boolean validateIndicatorData(VistaAdminDetalleIndicadores indicador) { return indicador.getProyectoId() != null && indicador.getIndicadorCodigo() != null; }
    @Override public Boolean validateProjectData(Proyectos proyecto) { return proyecto != null && proyecto.getNombreProyecto() != null && !proyecto.getNombreProyecto().trim().isEmpty(); }
    @Override public Map<String, Object> getOdsStatistics() { return getOds16Statistics(); }
    @Override public Boolean existsProyecto(Integer proyectoId) { return objetivo16PazJusticiaRepository.existsProyecto(proyectoId); }
    @Override public Boolean existsIndicador(Integer indicadorId) { return objetivo16PazJusticiaRepository.existsIndicador(indicadorId); }
    @Override public Boolean existsMetaProyecto(Integer metaId) { return objetivo16PazJusticiaRepository.existsMetaProyecto(metaId); }
    @Override public Boolean existsMedicionHistorica(Integer medicionId) { return objetivo16PazJusticiaRepository.existsMedicionHistorica(medicionId); }
    @Override public Map<String, Object> getDashboardData() { return objetivo16PazJusticiaRepository.spAdminDashboard(); }

    /**
     * Recalcula el valor actual de un indicador basado en sus parámetros y fórmula
     */
    private void recalculateIndicator(Integer proyectoIndicadorId) {
        if (proyectoIndicadorId == null) return;

        Optional<ProyectoIndicadores> optIndicador = objetivo16PazJusticiaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optIndicador.isEmpty()) return;

        ProyectoIndicadores indicador = optIndicador.get();
        if (indicador.getFormulaCustom() == null || indicador.getFormulaCustom().isEmpty()) return;

        List<ProyectoIndicadorParametros> parametros = objetivo16PazJusticiaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
        
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
            objetivo16PazJusticiaRepository.updateIndicador(indicador);
        } catch (Exception e) {
            System.err.println("Error recalculando indicador ODS16 " + proyectoIndicadorId + ": " + e.getMessage());
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Sprint 2/5: Medición auditada y traza de auditoría
    //  El cálculo lo hace el backend; el cliente NUNCA puede inyectar valor.
    // ─────────────────────────────────────────────────────────────────────

    @Override
    @org.springframework.transaction.annotation.Transactional("txManagerOds16")
    @SuppressWarnings("unchecked")
    public java.util.Map<String, Object> saveMedicionAuditada(java.util.Map<String, Object> payload) {
        if (payload == null) throw new IllegalArgumentException("payload requerido");

        Integer proyectoIndicadorId = toInt(payload.get("proyectoIndicadorId"));
        if (proyectoIndicadorId == null)
            throw new IllegalArgumentException("proyectoIndicadorId es requerido");

        Optional<ProyectoIndicadores> optInd = objetivo16PazJusticiaRepository.findIndicadorByIdEntity(proyectoIndicadorId);
        if (optInd.isEmpty())
            throw new IllegalArgumentException("Indicador no encontrado: " + proyectoIndicadorId);
        ProyectoIndicadores indicador = optInd.get();

        // 1. Reconstruir Map<nombre_variable, valor> a partir de los IDs ingresados
        java.util.List<ProyectoIndicadorParametros> parametros =
            objetivo16PazJusticiaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
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

        MedicionesHistoricas saved = objetivo16PazJusticiaRepository.saveMedicion(medicion);

        for (java.util.Map.Entry<Integer, java.math.BigDecimal> e : valoresPorParametroId.entrySet()) {
            objetivo16PazJusticiaRepository.insertMedicionParametroValor(saved.getId(), e.getKey(), e.getValue());
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

        MedicionesHistoricas medicion = objetivo16PazJusticiaRepository.findMedicionByIdEntity(medicionId);
        if (medicion == null) throw new IllegalArgumentException("Medición no encontrada: " + medicionId);

        Optional<ProyectoIndicadores> optInd =
            objetivo16PazJusticiaRepository.findIndicadorByIdEntity(medicion.getProyectoIndicadorId());
        ProyectoIndicadores indicador = optInd.orElse(null);

        java.util.List<java.util.Map<String, Object>> valores =
            objetivo16PazJusticiaRepository.findMedicionParametroValoresByMedicion(medicionId);

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
            objetivo16PazJusticiaRepository.findMetasByProyectoIndicador(proyectoIndicadorId);
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
                objetivo16PazJusticiaRepository.saveMetaProyecto(nuevo);
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
