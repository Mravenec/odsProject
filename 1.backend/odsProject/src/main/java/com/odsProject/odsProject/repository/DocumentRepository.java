package com.odsProject.odsProject.repository;

import com.odsProject.odsProject.repository.interfaces.IDocumentRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.odsProject.odsProject.database.jooq.ods_master.tables.ProyectoDocumentos.PROYECTO_DOCUMENTOS;

/**
 * Acceso a ods_master.proyecto_documentos vía JOOQ generado (POJOs).
 */
@Repository
public class DocumentRepository implements IDocumentRepository {

    @Autowired
    @Qualifier("dslOdsMaster")
    private DSLContext dsl;

    public com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos insertDocumento(
            Integer proyectoId, String nombre, String mime,
            Integer tamanio, byte[] contenido, Integer subidoPor,
            String descripcion) {
        var doc = new com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos();
        doc.setProyectoId(proyectoId);
        doc.setNombreArchivo(nombre);
        doc.setTipoMime(mime);
        doc.setTamanioBytes(tamanio);
        doc.setContenido(contenido);
        doc.setSubidoPor(subidoPor);
        doc.setDescripcion(descripcion);
        return dsl.insertInto(PROYECTO_DOCUMENTOS)
                .set(PROYECTO_DOCUMENTOS.PROYECTO_ID, doc.getProyectoId())
                .set(PROYECTO_DOCUMENTOS.NOMBRE_ARCHIVO, doc.getNombreArchivo())
                .set(PROYECTO_DOCUMENTOS.TIPO_MIME, doc.getTipoMime())
                .set(PROYECTO_DOCUMENTOS.TAMANIO_BYTES, doc.getTamanioBytes())
                .set(PROYECTO_DOCUMENTOS.CONTENIDO, doc.getContenido())
                .set(PROYECTO_DOCUMENTOS.SUBIDO_POR, doc.getSubidoPor())
                .set(PROYECTO_DOCUMENTOS.DESCRIPCION, doc.getDescripcion())
                .returning()
                .fetchOneInto(com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos.class);
    }

    public List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos> findByProyectoId(
            Integer proyectoId) {
        return findByProyecto(proyectoId);
    }

    public List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos> findByProyecto(
            Integer proyectoId) {
        if (proyectoId == null) return Collections.emptyList();
        return dsl.select(
                        PROYECTO_DOCUMENTOS.ID,
                        PROYECTO_DOCUMENTOS.PROYECTO_ID,
                        PROYECTO_DOCUMENTOS.NOMBRE_ARCHIVO,
                        PROYECTO_DOCUMENTOS.TIPO_MIME,
                        PROYECTO_DOCUMENTOS.TAMANIO_BYTES,
                        PROYECTO_DOCUMENTOS.SUBIDO_POR,
                        PROYECTO_DOCUMENTOS.SUBIDO_AT,
                        PROYECTO_DOCUMENTOS.DESCRIPCION)
                .from(PROYECTO_DOCUMENTOS)
                .where(PROYECTO_DOCUMENTOS.PROYECTO_ID.eq(proyectoId))
                .orderBy(PROYECTO_DOCUMENTOS.SUBIDO_AT.desc())
                .fetchInto(com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos.class);
    }

    public Optional<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos> findByIdCompleto(
            Integer id) {
        if (id == null) return Optional.empty();
        return dsl.selectFrom(PROYECTO_DOCUMENTOS)
                .where(PROYECTO_DOCUMENTOS.ID.eq(id))
                .fetchOptionalInto(com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos.class);
    }

    public boolean delete(Integer documentoId, Integer usuarioId, boolean isAdmin) {
        if (documentoId == null) return false;
        var q = dsl.deleteFrom(PROYECTO_DOCUMENTOS).where(PROYECTO_DOCUMENTOS.ID.eq(documentoId));
        if (!isAdmin) q = q.and(PROYECTO_DOCUMENTOS.SUBIDO_POR.eq(usuarioId));
        return q.execute() > 0;
    }
}
