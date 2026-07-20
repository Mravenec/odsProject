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
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
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

    /** UTN #00689D — cabecera matriz SODSI */
    private static final byte[] COLOR_UTN_BLUE = {0, 104, (byte) 157};
    private static final byte[] COLOR_ROW_EVEN = {(byte) 248, (byte) 250, (byte) 252};
    private static final byte[] COLOR_ROW_ODD = {(byte) 255, (byte) 255, (byte) 255};
    private static final byte[] COLOR_ODS_HIGHLIGHT = {(byte) 220, (byte) 252, (byte) 231};
    private static final byte[] COLOR_ACCION_HIGHLIGHT = {(byte) 254, (byte) 243, (byte) 199};

    private static final int COL_ACCION = 4;
    private static final int COL_OBJETIVO = 5;
    private static final int COL_META = 6;

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
                masterProjectRepository.findDetalleIndicadoresProyecto(proyectoId);
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
    public byte[] exportProyectosEvaluadosPorSedeYAnio(Integer sedeId, Integer anio, Integer actorUserId) {
        if (sedeId == null || anio == null) {
            throw new IllegalArgumentException("sedeId y anio son requeridos");
        }
        if (actorUserId == null) {
            throw new IllegalArgumentException("actorUserId es requerido");
        }
        if (anio < 2000 || anio > 2100) {
            throw new IllegalArgumentException("anio fuera de rango válido");
        }

        String actorNombre = loginRepository.findUsuarioById(actorUserId)
                .map(u -> u.getFullName())
                .orElse("");
        String actorInstitucion = resolveInstitucionForActor(actorUserId);
        String actorUnidad = resolveUnidadEncargadaForActor(actorUserId);

        List<VistaResumenProyectosOds> proyectos = resolveEvaluadosPorSedeYAnio(sedeId, anio);

        try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            writeSodsiMatrizSheet(wb, proyectos, actorNombre, actorInstitucion, actorUnidad);
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

    private void writeSodsiMatrizSheet(Workbook wb, List<VistaResumenProyectosOds> proyectos,
                                       String actorNombre, String actorInstitucion, String actorUnidad) {
        Sheet sheet = wb.createSheet(SODSI_MATRIZ_SHEET);
        SodsiMatrizTheme theme = createSodsiMatrizTheme(wb);

        Row header = sheet.createRow(0);
        for (int i = 0; i < SODSI_MATRIZ_COLUMNS.length; i++) {
            header.createCell(i).setCellValue(SODSI_MATRIZ_COLUMNS[i]);
        }
        applyMatrizRowStyles(header, theme, true, 0);

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
                    masterProjectRepository.findDetalleIndicadoresProyecto(v.getProyectoId());
            writeSodsiMatrizRow(sheet.createRow(rowIdx), v, beneficiarios, indicadores, odsNames, valorById, catById,
                    actorNombre, actorInstitucion, actorUnidad);
            applyMatrizRowStyles(sheet.getRow(rowIdx), theme, false, rowIdx);
            rowIdx++;
        }
        if (proyectos.isEmpty()) {
            writeSodsiMatrizRow(sheet.createRow(1), null, List.of(), List.of(), odsNames, valorById, catById,
                    actorNombre, actorInstitucion, actorUnidad);
            applyMatrizRowStyles(sheet.getRow(1), theme, false, 1);
        }
        int lastRow = sheet.getLastRowNum();
        sheet.createFreezePane(0, 1);
        if (lastRow >= 0) {
            sheet.setAutoFilter(new CellRangeAddress(0, lastRow, 0, SODSI_MATRIZ_COLUMNS.length - 1));
        }
        autosizeMatrizLayout(sheet);
    }

    /** Ancho de columnas + alto de cabecera y filas con texto largo (evita títulos recortados). */
    private static void autosizeMatrizLayout(Sheet sheet) {
        for (int i = 0; i < SODSI_MATRIZ_COLUMNS.length; i++) {
            sheet.autoSizeColumn(i);
            int auto = sheet.getColumnWidth(i);
            int min = minColumnWidthForHeader(SODSI_MATRIZ_COLUMNS[i]);
            sheet.setColumnWidth(i, Math.min(Math.max(auto, min), 20000));
        }
        fitMatrizHeaderRowHeight(sheet);
        fitMatrizDataRowHeights(sheet);
    }

    private static int minColumnWidthForHeader(String title) {
        int len = title != null ? title.length() : 0;
        return Math.min(Math.max(3400, len * 340 + 600), 12000);
    }

    private static void fitMatrizHeaderRowHeight(Sheet sheet) {
        Row header = sheet.getRow(0);
        if (header == null) {
            return;
        }
        float lineHeight = 16f;
        float maxLines = 1f;
        for (int i = 0; i < SODSI_MATRIZ_COLUMNS.length; i++) {
            maxLines = Math.max(maxLines, estimateWrappedLines(SODSI_MATRIZ_COLUMNS[i], sheet.getColumnWidth(i), 11));
        }
        header.setHeightInPoints(maxLines * lineHeight + 10f);
    }

    private static void fitMatrizDataRowHeights(Sheet sheet) {
        int last = sheet.getLastRowNum();
        for (int r = 1; r <= last; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            float maxLines = 1f;
            for (int c = 0; c < SODSI_MATRIZ_COLUMNS.length; c++) {
                Cell cell = row.getCell(c);
                if (cell == null) {
                    continue;
                }
                String text = matrizCellText(cell);
                if (!text.isBlank()) {
                    maxLines = Math.max(maxLines, estimateWrappedLines(text, sheet.getColumnWidth(c), 10));
                }
            }
            row.setHeightInPoints(Math.min(maxLines * 15f + 6f, 180f));
        }
    }

    private static float estimateWrappedLines(String text, int columnWidth, int fontSizePt) {
        if (text == null || text.isBlank()) {
            return 1f;
        }
        int charsPerLine = Math.max(4, columnWidth / (fontSizePt * 28));
        float lines = 0f;
        for (String segment : text.split("\n")) {
            int len = segment.length();
            lines += Math.max(1f, (float) Math.ceil(len / (double) charsPerLine));
        }
        return Math.max(1f, lines);
    }

    private static String matrizCellText(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> {
                yield switch (cell.getCachedFormulaResultType()) {
                    case STRING -> cell.getStringCellValue();
                    case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
                    case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                    default -> "";
                };
            }
            default -> "";
        };
    }

    private static final class SodsiMatrizTheme {
        final CellStyle header;
        final CellStyle dataEven;
        final CellStyle dataOdd;
        final CellStyle dataEvenOds;
        final CellStyle dataOddOds;
        final CellStyle dataEvenAccion;
        final CellStyle dataOddAccion;

        SodsiMatrizTheme(CellStyle header, CellStyle dataEven, CellStyle dataOdd,
                         CellStyle dataEvenOds, CellStyle dataOddOds,
                         CellStyle dataEvenAccion, CellStyle dataOddAccion) {
            this.header = header;
            this.dataEven = dataEven;
            this.dataOdd = dataOdd;
            this.dataEvenOds = dataEvenOds;
            this.dataOddOds = dataOddOds;
            this.dataEvenAccion = dataEvenAccion;
            this.dataOddAccion = dataOddAccion;
        }
    }

    private SodsiMatrizTheme createSodsiMatrizTheme(Workbook wb) {
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerFont.setFontHeightInPoints((short) 11);

        CellStyle header = wb.createCellStyle();
        applySolidFill(header, wb, COLOR_UTN_BLUE);
        header.setFont(headerFont);
        header.setAlignment(HorizontalAlignment.CENTER);
        header.setVerticalAlignment(VerticalAlignment.CENTER);
        header.setWrapText(true);
        applyThinBorders(header, IndexedColors.WHITE);

        CellStyle dataEven = createDataStyle(wb, COLOR_ROW_EVEN, false);
        CellStyle dataOdd = createDataStyle(wb, COLOR_ROW_ODD, false);
        CellStyle dataEvenOds = createDataStyle(wb, COLOR_ODS_HIGHLIGHT, false);
        CellStyle dataOddOds = createDataStyle(wb, COLOR_ODS_HIGHLIGHT, false);
        CellStyle dataEvenAccion = createDataStyle(wb, COLOR_ACCION_HIGHLIGHT, true);
        CellStyle dataOddAccion = createDataStyle(wb, COLOR_ACCION_HIGHLIGHT, true);

        return new SodsiMatrizTheme(header, dataEven, dataOdd, dataEvenOds, dataOddOds,
                dataEvenAccion, dataOddAccion);
    }

    private static CellStyle createDataStyle(Workbook wb, byte[] fillRgb, boolean bold) {
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 10);
        if (bold) {
            font.setBold(true);
        }
        CellStyle style = wb.createCellStyle();
        applySolidFill(style, wb, fillRgb);
        style.setFont(font);
        style.setVerticalAlignment(VerticalAlignment.TOP);
        style.setWrapText(true);
        applyThinBorders(style, IndexedColors.GREY_40_PERCENT);
        return style;
    }

    private static void applySolidFill(CellStyle style, Workbook wb, byte[] rgb) {
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        if (style instanceof XSSFCellStyle xssf && wb instanceof XSSFWorkbook) {
            xssf.setFillForegroundColor(new XSSFColor(rgb, new DefaultIndexedColorMap()));
        } else {
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        }
    }

    private static void applyThinBorders(CellStyle style, IndexedColors color) {
        short c = color.getIndex();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setTopBorderColor(c);
        style.setBottomBorderColor(c);
        style.setLeftBorderColor(c);
        style.setRightBorderColor(c);
    }

    private static void applyMatrizRowStyles(Row row, SodsiMatrizTheme theme, boolean headerRow, int rowIndex) {
        if (row == null) {
            return;
        }
        boolean even = rowIndex % 2 == 0;
        for (int col = 0; col < SODSI_MATRIZ_COLUMNS.length; col++) {
            Cell cell = row.getCell(col);
            if (cell == null) {
                cell = row.createCell(col);
            }
            if (headerRow) {
                cell.setCellStyle(theme.header);
                continue;
            }
            if (col == COL_ACCION) {
                cell.setCellStyle(even ? theme.dataEvenAccion : theme.dataOddAccion);
            } else if (col == COL_OBJETIVO || col == COL_META) {
                cell.setCellStyle(even ? theme.dataEvenOds : theme.dataOddOds);
            } else {
                cell.setCellStyle(even ? theme.dataEven : theme.dataOdd);
            }
        }
    }

    private void writeSodsiMatrizRow(Row row, VistaResumenProyectosOds v,
                                     List<ProyectoBeneficiarios> beneficiarios,
                                     List<VistaAdminDetalleIndicadores> indicadores,
                                     Map<Integer, String> odsNames,
                                     Map<Integer, SodsiBeneficiarioValor> valorById,
                                     Map<Integer, SodsiBeneficiarioCategoria> catById,
                                     String actorNombre, String actorInstitucion, String actorUnidad) {
        int c = 0;
        setAnioCell(row.createCell(c++), resolveAnioExport(v));
        row.createCell(c++).setCellValue(nullSafe(actorInstitucion));
        row.createCell(c++).setCellValue(nullSafe(actorNombre));
        row.createCell(c++).setCellValue(nullSafe(actorUnidad));
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

    private static void setAnioCell(org.apache.poi.ss.usermodel.Cell cell, int anio) {
        if (anio > 0) {
            cell.setCellValue(anio);
        } else {
            cell.setCellValue("");
        }
    }

    /**
     * Institución del consultor que descarga el reporte. La plataforma ODS actual es UTN;
     * extensible si se agrega catálogo multi-institución en {@code ods_login}.
     */
    private String resolveInstitucionForActor(Integer actorUserId) {
        return INSTITUCION_UTN;
    }

    /**
     * Unidad encargada = nombre de {@code sodsi_dependencia} del perfil SODSI del actor que descarga.
     * Sin {@code dependenciaId} o dependencia inexistente → cadena vacía (no inventar).
     */
    String resolveUnidadEncargadaForActor(Integer actorUserId) {
        if (actorUserId == null) {
            return "";
        }
        return loginRepository.findUsuarioById(actorUserId)
                .map(u -> u.getDependenciaId())
                .flatMap(sodsiCatalogRepository::findDependenciaById)
                .map(d -> d.getNombre() != null ? d.getNombre() : "")
                .orElse("");
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

    /**
     * Meta SODSI: "[codigo] " + indicadorNombre (Planificación).
     * Ignora metaNombre / formula_custom. Varios indicadores separados por coma.
     * Sin estado logrado/no logrado.
     */
    static String formatMetasExport(List<VistaAdminDetalleIndicadores> indicadores) {
        if (indicadores == null || indicadores.isEmpty()) return "";
        List<String> parts = new ArrayList<>();
        for (VistaAdminDetalleIndicadores ind : indicadores) {
            if (ind == null) continue;
            String codigo = ind.getIndicadorCodigo();
            if (codigo == null || codigo.isBlank()) continue;
            String nombre = ind.getIndicadorNombre();
            parts.add((nombre == null || nombre.isBlank()) ? "[" + codigo + "]" : "[" + codigo + "] " + nombre);
        }
        return String.join(", ", parts);
    }

    static String formatBeneficiariosExport(List<ProyectoBeneficiarios> rows,
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
            String codes = "[" + catCode + "]-[" + val.getCodigo().intValue() + "]";
            String valNombre = nullSafe(val.getNombre());
            String catNombre = cat != null ? nullSafe(cat.getNombre()) : "";
            String legible = valNombre.isBlank()
                    ? (catNombre.isBlank() ? codes : codes + " " + catNombre)
                    : codes + " " + valNombre;
            parts.add(legible);
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
