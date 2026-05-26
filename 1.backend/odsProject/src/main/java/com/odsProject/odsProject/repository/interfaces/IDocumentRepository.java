package com.odsProject.odsProject.repository.interfaces;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos;

import java.util.List;
import java.util.Optional;

/**
 * Contrato de acceso a ods_master.proyecto_documentos.
 */
public interface IDocumentRepository {

    ProyectoDocumentos insertDocumento(
            Integer proyectoId, String nombre, String mime,
            Integer tamanio, byte[] contenido, Integer subidoPor,
            String descripcion);

    List<ProyectoDocumentos> findByProyectoId(Integer proyectoId);

    List<ProyectoDocumentos> findByProyecto(Integer proyectoId);

    Optional<ProyectoDocumentos> findByIdCompleto(Integer id);

    boolean delete(Integer documentoId, Integer usuarioId, boolean isAdmin);
}
