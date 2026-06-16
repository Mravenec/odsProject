package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiAliadoTipo;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioCategoria;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiBeneficiarioValor;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.SodsiUnidadesProgramaticas;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoBeneficiarios;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds;
import com.odsProject.odsProject.repository.LoginRepository;
import com.odsProject.odsProject.repository.interfaces.IDocumentRepository;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.repository.interfaces.ISodsiCatalogRepository;
import com.odsProject.odsProject.service.interfaces.IExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExportService implements IExportService {

    /** Contrato Acciones por revisar SODSI.xlsx — orden y nombres exactos. */
    private static final String[] SODSI_MATRIZ_COLUMNS = {
            "Año", "Institución", "Usuario", "Unidad encargada", "Acción", "Objetivo", "Meta",
            "Eje de planes", "Fuente de información", "Contacto", "Sede", "Dependencia",
            "Rol de dependencia", "Aliado externo", "Sector beneficiario", "Región Mideplan",
            "Perspectiva de género", "Provincia", "Cantón", "Distrito", "Enlace"
    };

    private static final String SODSI_MATRIZ_SHEET = "Acciones";

    private static final String INSTITUCION_UTN = "Universidad Técnica Nacional (UTN)";

    private final IMasterProjectRepository masterProjectRepository;
    private final IDocumentRepository documentRepository;
    private final LoginRepository loginRepository;
    private final ISodsiCatalogRepository sodsiCatalogRepository;

    public ExportService(IMasterProjectRepository masterProjectRepository,
                         IDocumentRepository documentRepository,
                         LoginRepository loginRepository,
                         ISodsiCatalogRepository sodsiCatalogRepository) {
        this.masterProjectRepository = masterProjectRepository;
        this.documentRepository = documentRepository;
        this.loginRepository = loginRepository;
        this.sodsiCatalogRepository = sodsiCatalogRepository;
    }

    @Override
    public byte[] exportProyecto(Integer proyectoId) {
        Proyectos p = masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
        String estado = String.valueOf(p.getEstado()).toLowerCase();
        if (!"completado".equals(estado)) {
            throw new IllegalStateException("Solo proyectos completados pueden exportarse");
        }

        VistaResumenProyectosOds resumen = masterProjectRepository
                .findResumenWithOdsByProyectoId(proyectoId)
                .orElse(null);
        List<VistaAdminDetalleIndicadores> indicadores =
                loginRepository.findVistaDetalleIndicadores(proyectoId);
        List<ProyectoDocumentos> documentos = documentRepository.findByProyecto(proyectoId);

        List<ProyectoBeneficiarios> beneficiarios = masterProjectRepository.findBeneficiariosByProyecto(proyectoId);

        try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            writeGeneralAuditoriaSheet(wb, p, resumen);
            writeSodsiScalarsOnGeneral(wb, p, resumen);
            writeIndicadoresSheet(wb, indicadores);
            writeEvidenciasSheet(wb, documentos);
            writeSodsiRelationSheetsForOne(wb, proyectoId, p, beneficiarios);
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando exportación: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] exportPlanificacionConsolidado() {
        return exportPlanificacionConsolidado(null, null);
    }

    @Override
    public byte[] exportProyectosEvaluadosPorSedeYAnio(Integer sedeId, Integer anio) {
        if (sedeId == null || anio == null) {
            throw new IllegalArgumentException("sedeId y anio son requeridos");
        }
        if (anio < 2000 || anio > 2100) {
            throw new IllegalArgumentException("anio fuera de rango válido");
        }

        List<VistaResumenProyectosOds> proyectos = resolveEvaluadosPorSedeYAnio(sedeId, anio);

        try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            writeSodsiMatrizSheet(wb, proyectos);
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando exportación SODSI por sede/año: " + e.getMessage(), e);
        }
    }

    @Override
    public byte[] exportPlanificacionConsolidado(Integer sedeId, Integer userId) {
        List<VistaResumenProyectosOds> proyectos = resolveCompletedForExport(sedeId, userId);

        Map<String, List<VistaResumenProyectosOds>> bySede = proyectos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getSede() != null ? p.getSede() : "Sin sede",
                        LinkedHashMap::new,
                        Collectors.toList()));

        try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            for (Map.Entry<String, List<VistaResumenProyectosOds>> entry : bySede.entrySet()) {
                String sheetName = entry.getKey().length() > 31 ? entry.getKey().substring(0, 31) : entry.getKey();
                Sheet sheet = wb.createSheet(sheetName);
                Row header = sheet.createRow(0);
                String[] cols = {"ID", "Proyecto", "Gestor", "Estado", "Inicio", "Fin", "ODS"};
                for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);
                int rowIdx = 1;
                for (VistaResumenProyectosOds p : entry.getValue()) {
                    Row r = sheet.createRow(rowIdx++);
                    r.createCell(0).setCellValue(p.getProyectoId() != null ? p.getProyectoId() : 0);
                    r.createCell(1).setCellValue(nullSafe(p.getNombreProyecto()));
                    r.createCell(2).setCellValue(nullSafe(p.getGestor()));
                    r.createCell(3).setCellValue(p.getEstado() != null ? String.valueOf(p.getEstado()) : "");
                    r.createCell(4).setCellValue(p.getFechaInicio() != null ? p.getFechaInicio().toString() : "");
                    r.createCell(5).setCellValue(p.getFechaFin() != null ? p.getFechaFin().toString() : "");
                    r.createCell(6).setCellValue(nullSafe(p.getOdsVinculados()));
                }
                for (int i = 0; i < cols.length; i++) sheet.autoSizeColumn(i);
            }
            if (bySede.isEmpty()) {
                Sheet sheet = wb.createSheet("Sin datos");
                sheet.createRow(0).createCell(0).setCellValue("No hay proyectos completados");
            }
            wb.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando consolidado: " + e.getMessage(), e);
        }
    }

    private List<VistaResumenProyectosOds> resolveEvaluadosPorSedeYAnio(Integer sedeId, Integer anio) {
        Set<Integer> idsInSede = masterProjectRepository.findBySede(sedeId).stream()
                .map(Proyectos::getId)
                .collect(Collectors.toSet());

        return masterProjectRepository.findCompletedWithOds().stream()
                .filter(p -> p.getProyectoId() != null && idsInSede.contains(p.getProyectoId()))
                .filter(p -> p.getAuditadoEn() != null && p.getAuditadoEn().getYear() == anio)
                .toList();
    }

    private List<VistaResumenProyectosOds> resolveCompletedForExport(Integer sedeId, Integer userId) {
        List<VistaResumenProyectosOds> base;
        if (userId != null) {
            base = masterProjectRepository.findByUsuarioWithOds(userId).stream()
                    .filter(p -> p.getEstado() != null
                            && "completado".equalsIgnoreCase(String.valueOf(p.getEstado())))
                    .toList();
        } else {
            base = masterProjectRepository.findCompletedWithOds();
        }

        if (sedeId == null) {
            return base;
        }

        Set<Integer> idsInSede = masterProjectRepository.findBySede(sedeId).stream()
                .map(Proyectos::getId)
                .collect(Collectors.toSet());
        return base.stream()
                .filter(p -> p.getProyectoId() != null && idsInSede.contains(p.getProyectoId()))
                .toList();
    }

    private void writeGeneralAuditoriaSheet(Workbook wb, Proyectos p, VistaResumenProyectosOds resumen) {
        Sheet sheet = wb.createSheet("General y auditoría");
        int rowIdx = 0;
        rowIdx = writeRow(sheet, rowIdx, "ID", p.getId());
        rowIdx = writeRow(sheet, rowIdx, "Nombre", p.getNombreProyecto());
        rowIdx = writeRow(sheet, rowIdx, "Estado", String.valueOf(p.getEstado()));
        rowIdx = writeRow(sheet, rowIdx, "Sede", resumen != null ? resumen.getSede() : "");
        rowIdx = writeRow(sheet, rowIdx, "Gestor", resumen != null ? resumen.getGestor() : "");
        rowIdx = writeRow(sheet, rowIdx, "Fecha inicio", p.getFechaInicio());
        rowIdx = writeRow(sheet, rowIdx, "Fecha fin", p.getFechaFin());
        rowIdx = writeRow(sheet, rowIdx, "Meta general", p.getMetaGeneral());
        rowIdx = writeRow(sheet, rowIdx, "ODS vinculados", resumen != null ? resumen.getOdsVinculados() : "");
        rowIdx = writeRow(sheet, rowIdx, "ODS primario", resumen != null && resumen.getOdsPrimario() != null
                ? String.valueOf(resumen.getOdsPrimario()) : "");
        rowIdx = writeRow(sheet, rowIdx, "Auditor", resumen != null ? resumen.getAuditorNombre() : "");
        rowIdx = writeRow(sheet, rowIdx, "Auditado en", resumen != null ? resumen.getAuditadoEn() : p.getAuditadoEn());
        rowIdx = writeRow(sheet, rowIdx, "Observaciones cierre",
                p.getObservacionesCierre() != null ? p.getObservacionesCierre()
                        : (resumen != null ? resumen.getObservacionesCierre() : ""));
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
    }

    private void writeIndicadoresSheet(Workbook wb, List<VistaAdminDetalleIndicadores> indicadores) {
        Sheet sheet = wb.createSheet("Indicadores");
        Row header = sheet.createRow(0);
        String[] cols = {"Código", "Indicador", "Valor actual", "Meta", "Unidad", "% logro", "Estado"};
        for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);
        int rowIdx = 1;
        for (VistaAdminDetalleIndicadores ind : indicadores) {
            Row r = sheet.createRow(rowIdx++);
            r.createCell(0).setCellValue(nullSafe(ind.getIndicadorCodigo()));
            r.createCell(1).setCellValue(nullSafe(ind.getIndicadorNombre()));
            setNumeric(r, 2, ind.getValorActual());
            setNumeric(r, 3, ind.getMetaValor());
            r.createCell(4).setCellValue(nullSafe(ind.getMetaUnidad()));
            setNumeric(r, 5, ind.getPorcentajeLogro());
            r.createCell(6).setCellValue(nullSafe(ind.getEstadoIndicador()));
        }
        for (int i = 0; i < cols.length; i++) sheet.autoSizeColumn(i);
    }

    private void writeEvidenciasSheet(Workbook wb, List<ProyectoDocumentos> documentos) {
        Sheet sheet = wb.createSheet("Evidencias");
        Row header = sheet.createRow(0);
        String[] cols = {"ID", "Archivo", "Tipo MIME", "Tamaño (bytes)", "Subido", "Descripción"};
        for (int i = 0; i < cols.length; i++) header.createCell(i).setCellValue(cols[i]);
        int rowIdx = 1;
        for (ProyectoDocumentos doc : documentos) {
            Row r = sheet.createRow(rowIdx++);
            r.createCell(0).setCellValue(doc.getId() != null ? doc.getId() : 0);
            r.createCell(1).setCellValue(nullSafe(doc.getNombreArchivo()));
            r.createCell(2).setCellValue(nullSafe(doc.getTipoMime()));
            r.createCell(3).setCellValue(doc.getTamanioBytes() != null ? doc.getTamanioBytes() : 0);
            r.createCell(4).setCellValue(doc.getSubidoAt() != null ? doc.getSubidoAt().toString() : "");
            r.createCell(5).setCellValue(nullSafe(doc.getDescripcion()));
        }
        for (int i = 0; i < cols.length; i++) sheet.autoSizeColumn(i);
    }

    private static int writeRow(Sheet sheet, int rowIdx, String label, Object value) {
        Row row = sheet.createRow(rowIdx);
        row.createCell(0).setCellValue(label);
        row.createCell(1).setCellValue(value != null ? String.valueOf(value) : "");
        return rowIdx + 1;
    }

    private static void setNumeric(Row row, int col, java.math.BigDecimal val) {
        if (val != null) row.createCell(col).setCellValue(val.doubleValue());
        else row.createCell(col).setCellValue("");
    }

    private static String nullSafe(String s) {
        return s != null ? s : "";
    }

    private void writeSodsiScalarsOnGeneral(Workbook wb, Proyectos p, VistaResumenProyectosOds resumen) {
        Sheet sheet = wb.getSheet("General y auditoría");
        if (sheet == null) return;
        int rowIdx = sheet.getLastRowNum() + 1;
        rowIdx = writeRow(sheet, rowIdx, "Institución", INSTITUCION_UTN);
        rowIdx = writeRow(sheet, rowIdx, "Contacto",
                formatContacto(resumen != null ? resumen.getGestor() : null,
                        resumen != null ? resumen.getGestorEmail() : null,
                        resumen != null ? resumen.getGestorTelefono() : null));
        rowIdx = writeRow(sheet, rowIdx, "Dependencia",
                resumen != null ? resumen.getDependenciaNombre() : "");
        rowIdx = writeRow(sheet, rowIdx, "Región Mideplan", resumen != null ? resumen.getRegionMideplan() : "");
        rowIdx = writeRow(sheet, rowIdx, "Eje PLANES", resumen != null ? resumen.getEjePlanes() : "");
        rowIdx = writeRow(sheet, rowIdx, "Aliado externo", p.getAliadoExterno());
        rowIdx = writeRow(sheet, rowIdx, "Provincia", p.getLocationProvince());
        writeRow(sheet, rowIdx, "Cantón / Distrito",
                nullSafe(p.getLocationCanton()) + " / " + nullSafe(p.getLocationDistrict()));
    }

    private void writeSodsiRelationSheetsForOne(Workbook wb, Integer proyectoId, Proyectos p,
                                               List<ProyectoBeneficiarios> beneficiarios) {
        Set<Integer> ids = new LinkedHashSet<>();
        if (beneficiarios != null) {
            beneficiarios.stream()
                    .filter(b -> b.getValorId() != null)
                    .forEach(b -> ids.add(b.getValorId().intValue()));
        }
        Map<Integer, String> beneficiarioLabels = buildValorByIdMap(ids).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> nullSafe(e.getValue().getNombre()), (a, b) -> a));

        if (p.getAliadoExterno() != null && !p.getAliadoExterno().isBlank()) {
            Sheet aliados = wb.createSheet("Aliados");
            String[] ac = {"Proyecto ID", "Aliado externo"};
            Row ah = aliados.createRow(0);
            for (int i = 0; i < ac.length; i++) ah.createCell(i).setCellValue(ac[i]);
            Row row = aliados.createRow(1);
            row.createCell(0).setCellValue(proyectoId);
            row.createCell(1).setCellValue(p.getAliadoExterno());
        }

        Sheet ben = wb.createSheet("Beneficiarios");
        String[] bc = {"Proyecto ID", "Sector beneficiario"};
        Row bh = ben.createRow(0);
        for (int i = 0; i < bc.length; i++) bh.createCell(i).setCellValue(bc[i]);
        int br = 1;
        for (ProyectoBeneficiarios b : beneficiarios) {
            Row row = ben.createRow(br++);
            row.createCell(0).setCellValue(proyectoId);
            row.createCell(1).setCellValue(b.getValorId() != null
                    ? beneficiarioLabels.getOrDefault(b.getValorId().intValue(), String.valueOf(b.getValorId())) : "");
        }
    }

    private static String formatContacto(String nombre, String email, String telefono) {
        String n = nombre != null ? nombre : "";
        String e = email != null ? email : "";
        String t = telefono != null ? telefono : "";
        if (n.isBlank() && e.isBlank() && t.isBlank()) return "";
        return n + " - " + e + " - " + t;
    }

    private void writeSodsiMatrizSheet(Workbook wb, List<VistaResumenProyectosOds> proyectos) {
        Sheet sheet = wb.createSheet(SODSI_MATRIZ_SHEET);
        Row header = sheet.createRow(0);
        for (int i = 0; i < SODSI_MATRIZ_COLUMNS.length; i++) {
            header.createCell(i).setCellValue(SODSI_MATRIZ_COLUMNS[i]);
        }

        Map<Integer, String> odsNames = sodsiCatalogRepository.findOdsCatalog().stream()
                .collect(Collectors.toMap(o -> o.getId().intValue(), o -> nullSafe(o.getNombre()), (a, b) -> a));
        Set<Integer> valorIds = new LinkedHashSet<>();
        for (VistaResumenProyectosOds v : proyectos) {
            if (v.getProyectoId() == null) continue;
            masterProjectRepository.findBeneficiariosByProyecto(v.getProyectoId()).stream()
                    .filter(b -> b.getValorId() != null)
                    .forEach(b -> valorIds.add(b.getValorId().intValue()));
        }
        Map<Integer, SodsiBeneficiarioValor> valorById = buildValorByIdMap(valorIds);
        Map<Integer, SodsiBeneficiarioCategoria> catById = sodsiCatalogRepository.findBeneficiarioCategorias().stream()
                .collect(Collectors.toMap(c -> c.getId().intValue(), c -> c, (a, b) -> a));

        int rowIdx = 1;
        for (VistaResumenProyectosOds v : proyectos) {
            if (v.getProyectoId() == null) continue;
            List<ProyectoBeneficiarios> beneficiarios =
                    masterProjectRepository.findBeneficiariosByProyecto(v.getProyectoId());
            List<VistaAdminDetalleIndicadores> indicadores =
                    loginRepository.findVistaDetalleIndicadores(v.getProyectoId());
            writeSodsiMatrizRow(sheet.createRow(rowIdx++), v, beneficiarios, indicadores, odsNames, valorById, catById);
        }
        if (proyectos.isEmpty()) {
            writeSodsiMatrizRow(sheet.createRow(1), null, List.of(), List.of(), odsNames, valorById, catById);
        }
        for (int i = 0; i < SODSI_MATRIZ_COLUMNS.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void writeSodsiMatrizRow(Row row, VistaResumenProyectosOds v,
                                     List<ProyectoBeneficiarios> beneficiarios,
                                     List<VistaAdminDetalleIndicadores> indicadores,
                                     Map<Integer, String> odsNames,
                                     Map<Integer, SodsiBeneficiarioValor> valorById,
                                     Map<Integer, SodsiBeneficiarioCategoria> catById) {
        int c = 0;
        row.createCell(c++).setCellValue(resolveAnioExport(v));
        row.createCell(c++).setCellValue("");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getGestor()) : "");
        row.createCell(c++).setCellValue("");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getNombreProyecto()) : "Sin proyectos evaluados");
        row.createCell(c++).setCellValue(v != null ? formatObjetivosExport(v, odsNames) : "");
        row.createCell(c++).setCellValue(v != null ? formatMetasExport(indicadores) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getEjePlanes()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getAreaNombre()) : "");
        row.createCell(c++).setCellValue(v != null
                ? formatContacto(v.getGestor(), v.getGestorEmail(), v.getGestorTelefono()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getSedeUsuario()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getDependenciaNombre()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getRolDependenciaNombre()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getAliadoExterno()) : "");
        row.createCell(c++).setCellValue(formatBeneficiariosExport(beneficiarios, valorById, catById));
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getRegionMideplan()) : "");
        row.createCell(c++).setCellValue("");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getLocationProvince()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getLocationCanton()) : "");
        row.createCell(c++).setCellValue(v != null ? nullSafe(v.getLocationDistrict()) : "");
        row.createCell(c).setCellValue("");
    }

    private static int resolveAnioExport(VistaResumenProyectosOds v) {
        if (v == null) return 0;
        LocalDate fin = v.getFechaFin();
        if (fin != null) return fin.getYear();
        LocalDateTime auditado = v.getAuditadoEn();
        if (auditado != null) return auditado.getYear();
        return 0;
    }

    static String formatObjetivosExport(VistaResumenProyectosOds v, Map<Integer, String> odsNames) {
        if (v == null) return "";
        List<Integer> ids = parseOdsVinculadosIds(v.getOdsVinculados());
        if (ids.isEmpty() && v.getOdsPrimario() != null) {
            ids = List.of(v.getOdsPrimario().intValue());
        }
        List<String> parts = new ArrayList<>();
        for (int n : ids) {
            String nombre = odsNames.getOrDefault(n, "");
            parts.add(nombre.isBlank() ? "[" + n + "]" : "[" + n + "] " + nombre);
        }
        return String.join(", ", parts);
    }

    static List<Integer> parseOdsVinculadosIds(String csv) {
        if (csv == null || csv.isBlank()) return List.of();
        List<Integer> ids = new ArrayList<>();
        for (String part : csv.split(",")) {
            String t = part.trim();
            if (t.isEmpty()) continue;
            try {
                ids.add(Integer.parseInt(t));
            } catch (NumberFormatException ignored) {
                // omitir tokens no numéricos
            }
        }
        return ids;
    }

    static String formatMetasExport(List<VistaAdminDetalleIndicadores> indicadores) {
        if (indicadores == null || indicadores.isEmpty()) return "";
        List<String> parts = new ArrayList<>();
        for (VistaAdminDetalleIndicadores ind : indicadores) {
            if (ind == null) continue;
            String codigo = ind.getIndicadorCodigo();
            if (codigo == null || codigo.isBlank()) continue;
            String nombre = ind.getMetaNombre();
            if (nombre == null || nombre.isBlank()) {
                nombre = ind.getIndicadorNombre();
            }
            parts.add((nombre == null || nombre.isBlank()) ? "[" + codigo + "]" : "[" + codigo + "] " + nombre);
        }
        return String.join(", ", parts);
    }

    private static String formatBeneficiariosExport(List<ProyectoBeneficiarios> rows,
                                                     Map<Integer, SodsiBeneficiarioValor> valorById,
                                                     Map<Integer, SodsiBeneficiarioCategoria> catById) {
        if (rows == null || rows.isEmpty()) return "";
        List<String> parts = new ArrayList<>();
        for (ProyectoBeneficiarios b : rows) {
            if (b == null || b.getValorId() == null) continue;
            SodsiBeneficiarioValor val = valorById.get(b.getValorId().intValue());
            if (val == null || val.getCodigo() == null) continue;
            SodsiBeneficiarioCategoria cat = val.getCategoriaId() != null
                    ? catById.get(val.getCategoriaId().intValue()) : null;
            String catCode = cat != null ? nullSafe(cat.getCodigo()) : "";
            if (catCode.isBlank()) continue;
            parts.add("[" + catCode + "]-[" + val.getCodigo().intValue() + "]");
        }
        return String.join(" / ", parts);
    }

    private Map<Integer, SodsiBeneficiarioValor> buildValorByIdMap(Set<Integer> valorIds) {
        if (valorIds == null || valorIds.isEmpty()) {
            return Map.of();
        }
        return sodsiCatalogRepository.findBeneficiarioValoresByIds(valorIds).stream()
                .filter(v -> v.getId() != null)
                .collect(Collectors.toMap(v -> v.getId().intValue(), v -> v, (a, b) -> a));
    }
}
