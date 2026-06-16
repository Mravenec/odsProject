package com.odsProject.odsProject.service;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;

@SpringBootTest
class SodsiMatrizExportSmokeTest {

    private static final String[] EXPECTED_HEADERS = {
            "Año", "Institución", "Usuario", "Unidad encargada", "Acción", "Objetivo", "Meta",
            "Eje de planes", "Fuente de información", "Contacto", "Sede", "Dependencia",
            "Rol de dependencia", "Aliado externo", "Sector beneficiario", "Región Mideplan",
            "Perspectiva de género", "Provincia", "Cantón", "Distrito", "Enlace"
    };

    @Autowired
    private ExportService exportService;

    @Test
    void exportMatrizTieneUnaHojaY21Columnas() throws Exception {
        byte[] data = exportService.exportProyectosEvaluadosPorSedeYAnio(2, 2024);
        try (Workbook wb = new XSSFWorkbook(new ByteArrayInputStream(data))) {
            Assertions.assertEquals(1, wb.getNumberOfSheets());
            Sheet sheet = wb.getSheet("Acciones");
            Assertions.assertNotNull(sheet);
            Row header = sheet.getRow(0);
            Assertions.assertNotNull(header);
            Assertions.assertEquals(21, EXPECTED_HEADERS.length);
            for (int i = 0; i < EXPECTED_HEADERS.length; i++) {
                Assertions.assertEquals(EXPECTED_HEADERS[i], header.getCell(i).getStringCellValue(),
                        "Columna " + (i + 1));
            }

            Row dataRow = findRowByAccion(sheet, "Proyecto QA Consultor SODSI");
            if (dataRow != null) {
                String objetivo = dataRow.getCell(5).getStringCellValue();
                String meta = dataRow.getCell(6).getStringCellValue();
                Assertions.assertFalse(objetivo.isBlank(), "Objetivo no debe estar vacío");
                Assertions.assertTrue(objetivo.startsWith("[1]"), "Objetivo debe incluir ODS vinculado [n]");
                Assertions.assertFalse(meta.isBlank(), "Meta no debe estar vacía");
                Assertions.assertTrue(meta.startsWith("[1."), "Meta debe incluir código indicador [n.n.n]");
            }
        }
    }

    private static Row findRowByAccion(Sheet sheet, String accion) {
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null || row.getCell(4) == null) continue;
            String val = row.getCell(4).getStringCellValue();
            if (accion.equals(val)) return row;
        }
        return null;
    }
}
