package com.odsProject.odsProject.repository;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.DSL.table;

/**
 * Sprint 11 — Acceso a ods_master.proyecto_documentos vía raw DSL.
 * Cuando el usuario corra mvn spring-boot:run JOOQ regenerará el POJO oficial,
 * pero este código sigue funcionando.
 */
@Repository
public class DocumentRepository {

    @Autowired @Qualifier("dslOdsMaster")
    private DSLContext dsl;

    private static final org.jooq.Table<?> DOCS =
        table(name("ods_master", "proyecto_documentos"));
    private static final Field<Integer> D_ID =
        field(name("ods_master","proyecto_documentos","id"), Integer.class);
    private static final Field<Integer> D_PROYECTO_ID =
        field(name("ods_master","proyecto_documentos","proyecto_id"), Integer.class);
    private static final Field<String> D_NOMBRE =
        field(name("ods_master","proyecto_documentos","nombre_archivo"), String.class);
    private static final Field<String> D_MIME =
        field(name("ods_master","proyecto_documentos","tipo_mime"), String.class);
    private static final Field<Integer> D_TAMANIO =
        field(name("ods_master","proyecto_documentos","tamanio_bytes"), Integer.class);
    private static final Field<byte[]> D_CONTENIDO =
        field(name("ods_master","proyecto_documentos","contenido"), byte[].class);
    private static final Field<Integer> D_SUBIDO_POR =
        field(name("ods_master","proyecto_documentos","subido_por"), Integer.class);
    private static final Field<java.time.LocalDateTime> D_SUBIDO_AT =
        field(name("ods_master","proyecto_documentos","subido_at"), java.time.LocalDateTime.class);
    private static final Field<String> D_DESC =
        field(name("ods_master","proyecto_documentos","descripcion"), String.class);

    public Integer insertDocumento(Integer proyectoId, String nombre, String mime,
                                   Integer tamanio, byte[] contenido, Integer subidoPor,
                                   String descripcion) {
        Record rec = dsl.insertInto(DOCS)
           .set(D_PROYECTO_ID, proyectoId).set(D_NOMBRE, nombre).set(D_MIME, mime)
           .set(D_TAMANIO, tamanio).set(D_CONTENIDO, contenido)
           .set(D_SUBIDO_POR, subidoPor).set(D_DESC, descripcion)
           .returningResult(D_ID).fetchOne();
        return rec != null ? rec.get(D_ID) : null;
    }

    public List<Map<String, Object>> findByProyecto(Integer proyectoId) {
        if (proyectoId == null) return java.util.Collections.emptyList();
        return dsl.select(D_ID, D_PROYECTO_ID, D_NOMBRE, D_MIME, D_TAMANIO,
                          D_SUBIDO_POR, D_SUBIDO_AT, D_DESC)
                  .from(DOCS).where(D_PROYECTO_ID.eq(proyectoId))
                  .orderBy(D_SUBIDO_AT.desc()).fetchMaps();
    }

    public Map<String, Object> findByIdCompleto(Integer id) {
        if (id == null) return null;
        return dsl.select(D_ID, D_PROYECTO_ID, D_NOMBRE, D_MIME, D_TAMANIO,
                          D_CONTENIDO, D_SUBIDO_POR, D_SUBIDO_AT, D_DESC)
                  .from(DOCS).where(D_ID.eq(id)).fetchOneMap();
    }

    public boolean delete(Integer documentoId, Integer usuarioId, boolean isAdmin) {
        if (documentoId == null) return false;
        var q = dsl.deleteFrom(DOCS).where(D_ID.eq(documentoId));
        if (!isAdmin) q = q.and(D_SUBIDO_POR.eq(usuarioId));
        return q.execute() > 0;
    }
}
