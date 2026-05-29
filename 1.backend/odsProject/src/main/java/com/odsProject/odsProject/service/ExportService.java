package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds;
import com.odsProject.odsProject.repository.LoginRepository;
import com.odsProject.odsProject.repository.interfaces.IDocumentRepository;
import com.odsProject.odsProject.repository.interfaces.IMasterProjectRepository;
import com.odsProject.odsProject.service.interfaces.IExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExportService implements IExportService {

    private final IMasterProjectRepository masterProjectRepository;
    private final IDocumentRepository documentRepository;
    private final LoginRepository loginRepository;

    public ExportService(IMasterProjectRepository masterProjectRepository,
                         IDocumentRepository documentRepository,
                         LoginRepository loginRepository) {
        this.masterProjectRepository = masterProjectRepository;
        this.documentRepository = documentRepository;
        this.loginRepository = loginRepository;
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

        try (Workbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            writeGeneralAuditoriaSheet(wb, p, resumen);
            writeIndicadoresSheet(wb, indicadores);
            writeEvidenciasSheet(wb, documentos);
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
        rowIdx = writeRow(sheet, rowIdx, "Responsable", p.getResponsableNombre());
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
}
