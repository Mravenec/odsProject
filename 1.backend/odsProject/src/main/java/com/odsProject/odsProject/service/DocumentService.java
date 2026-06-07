package com.odsProject.odsProject.service;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.repository.DocumentRepository;
import com.odsProject.odsProject.repository.MasterProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private MasterProjectRepository masterProjectRepository;

    public com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos uploadDocument(
            Integer proyectoId, Integer usuarioId,
            MultipartFile file, String descripcion) throws Exception {
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("Archivo vacío");
        if (proyectoId == null || usuarioId == null)
            throw new IllegalArgumentException("proyectoId y usuarioId son requeridos");

        Proyectos proyecto = masterProjectRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado: " + proyectoId));
        if (!"activo".equalsIgnoreCase(String.valueOf(proyecto.getEstado()))) {
            throw new IllegalStateException(
                    "Solo se pueden subir documentos cuando el proyecto está en estado activo");
        }
        if (file.getSize() > 10L * 1024 * 1024)
            throw new IllegalArgumentException("Archivo supera el límite de 10 MB");

        String desc = descripcion != null && !descripcion.isBlank() ? descripcion.trim() : null;

        var saved = documentRepository.insertDocumento(
                proyectoId,
                file.getOriginalFilename(),
                file.getContentType() != null ? file.getContentType() : "application/octet-stream",
                (int) file.getSize(),
                file.getBytes(),
                usuarioId,
                desc);

        return sanitize(saved);
    }

    public List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos> listByProyecto(
            Integer proyectoId) {
        return documentRepository.findByProyecto(proyectoId).stream()
                .map(this::sanitize)
                .collect(Collectors.toList());
    }

    public com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos getDocumentoCompleto(
            Integer documentoId) {
        return documentRepository.findByIdCompleto(documentoId).orElse(null);
    }

    public boolean deleteDocumento(Integer documentoId, Integer usuarioId, boolean isAdmin) {
        return documentRepository.delete(documentoId, usuarioId, isAdmin);
    }

    /** Respuesta pública — nunca incluir contenido binario. */
    private com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos sanitize(
            com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos doc) {
        if (doc == null) return null;
        doc.setContenido(null);
        return doc;
    }
}
