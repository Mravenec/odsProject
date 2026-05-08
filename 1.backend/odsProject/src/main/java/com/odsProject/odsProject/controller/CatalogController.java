package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.OdsCatalog;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.IndicadorMaster;
import com.odsProject.odsProject.database.jooq.ods_login.tables.pojos.IndicadorParametrosMaster;
import org.jooq.DSLContext;
import org.jooq.types.UByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.odsProject.odsProject.database.jooq.ods_login.tables.OdsCatalog.ODS_CATALOG;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorMaster.INDICADOR_MASTER;
import static com.odsProject.odsProject.database.jooq.ods_login.tables.IndicadorParametrosMaster.INDICADOR_PARAMETROS_MASTER;

/**
 * CatalogController — Endpoints para el catálogo de ODS e indicadores.
 * Usados por el frontend para cargar datos dinámicamente en ProjectCreationPage
 * e IndicatorConfigModal.
 */
@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @Autowired
    @Qualifier("dslLogin")
    private DSLContext dsl;

    /**
     * GET /api/catalog/ods
     * Retorna los 17 ODS con su color y descripción.
     */
    @GetMapping("/ods")
    public ResponseEntity<List<OdsCatalog>> getOdsCatalog() {
        List<OdsCatalog> result = dsl
            .selectFrom(ODS_CATALOG)
            .orderBy(ODS_CATALOG.ID.asc())
            .fetchInto(OdsCatalog.class);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /api/catalog/ods/{odsId}/indicadores
     * Retorna los indicadores del catálogo para un ODS específico.
     */
    @GetMapping("/ods/{odsId}/indicadores")
    public ResponseEntity<List<IndicadorMaster>> getIndicadoresByOds(@PathVariable Integer odsId) {
        List<IndicadorMaster> result = dsl
            .selectFrom(INDICADOR_MASTER)
            .where(INDICADOR_MASTER.ODS_ID.eq(UByte.valueOf(odsId)))
            .orderBy(INDICADOR_MASTER.CODIGO.asc())
            .fetchInto(IndicadorMaster.class);
        return ResponseEntity.ok(result);
    }

    /**
     * GET /api/catalog/indicadores/{indicadorId}/parametros
     * Retorna los parámetros (p1, p2, etc.) de un indicador con sus descripciones.
     */
    @GetMapping("/indicadores/{indicadorId}/parametros")
    public ResponseEntity<List<IndicadorParametrosMaster>> getParametros(@PathVariable Integer indicadorId) {
        List<IndicadorParametrosMaster> result = dsl
            .selectFrom(INDICADOR_PARAMETROS_MASTER)
            .where(INDICADOR_PARAMETROS_MASTER.INDICADOR_ID.eq(indicadorId))
            .fetchInto(IndicadorParametrosMaster.class);
        return ResponseEntity.ok(result);
    }
}
