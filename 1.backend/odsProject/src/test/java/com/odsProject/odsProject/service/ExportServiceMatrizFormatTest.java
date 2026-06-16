package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods01.tables.pojos.VistaAdminDetalleIndicadores;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds;
import org.jooq.types.ULong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ExportServiceMatrizFormatTest {

    @Test
    void formatObjetivosExport_listaTodosOdsVinculados() {
        VistaResumenProyectosOds v = new VistaResumenProyectosOds();
        v.setOdsVinculados("1,6,11");
        v.setOdsPrimario(ULong.valueOf(1));

        Map<Integer, String> names = Map.of(
                1, "Fin de la pobreza",
                6, "Agua limpia y saneamiento",
                11, "Ciudades y comunidades sostenibles");

        String result = ExportService.formatObjetivosExport(v, names);

        Assertions.assertEquals(
                "[1] Fin de la pobreza, [6] Agua limpia y saneamiento, [11] Ciudades y comunidades sostenibles",
                result);
    }

    @Test
    void formatObjetivosExport_fallbackOdsPrimarioSiCsvVacio() {
        VistaResumenProyectosOds v = new VistaResumenProyectosOds();
        v.setOdsPrimario(ULong.valueOf(3));

        String result = ExportService.formatObjetivosExport(v, Map.of(3, "Salud y bienestar"));

        Assertions.assertEquals("[3] Salud y bienestar", result);
    }

    @Test
    void formatMetasExport_listaTodosIndicadores() {
        VistaAdminDetalleIndicadores a = new VistaAdminDetalleIndicadores();
        a.setIndicadorCodigo("1.1.1");
        a.setIndicadorNombre("Indicador pobreza extrema");
        a.setMetaNombre("Reducir pobreza extrema");

        VistaAdminDetalleIndicadores b = new VistaAdminDetalleIndicadores();
        b.setIndicadorCodigo("1.2.1");
        b.setIndicadorNombre("Indicador pobreza relativa");

        String result = ExportService.formatMetasExport(List.of(a, b));

        Assertions.assertEquals(
                "[1.1.1] Reducir pobreza extrema, [1.2.1] Indicador pobreza relativa",
                result);
    }

    @Test
    void parseOdsVinculadosIds_ignoraEspaciosYTokensInvalidos() {
        Assertions.assertEquals(List.of(1, 6, 11), ExportService.parseOdsVinculadosIds("1, 6 , 11"));
        Assertions.assertEquals(List.of(5), ExportService.parseOdsVinculadosIds("5,foo"));
        Assertions.assertTrue(ExportService.parseOdsVinculadosIds("").isEmpty());
    }
}
