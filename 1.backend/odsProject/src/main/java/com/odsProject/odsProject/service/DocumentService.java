package com.odsProject.odsProject.service;

import com.odsProject.odsProject.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class DocumentService {
    @Autowired private DocumentRepository documentRepository;

    public Map<String, Object> uploadDocument(Integer proyectoId, Integer usuarioId,
                                              MultipartFile file, String descripcion) throws Exception {
        if (file == null || file.isEmpty())
            throw new IllegalArgumentException("Archivo vacío");
        if (proyectoId == null || usuarioId == null)
            throw new IllegalArgumentException("proyectoId y usuarioId son requeridos");
        if (file.getSize() > 10L * 1024 * 1024)
            throw new IllegalArgumentException("Archivo supera el límite de 10 MB");

        Integer id = documentRepository.insertDocumento(
            proyectoId, file.getOriginalFilename(),
            file.getContentType() != null ? file.getContentType() : "application/octet-stream",
            (int) file.getSize(), file.getBytes(), usuarioId, descripcion);

        Map<String, Object> out = new java.util.LinkedHashMap<>();
        out.put("id", id);
        out.put("proyectoId", proyectoId);
        out.put("nombreArchivo", file.getOriginalFilename());
        out.put("tipoMime", file.getContentType());
        out.put("tamanioBytes", file.getSize());
        return out;
    }

    public List<Map<String, Object>> listByProyecto(Integer proyectoId) {
        return documentRepository.findByProyecto(proyectoId);
    }
    public Map<String, Object> getDocumentoCompleto(Integer documentoId) {
        return documentRepository.findByIdCompleto(documentoId);
    }
    public boolean deleteDocumento(Integer documentoId, Integer usuarioId, boolean isAdmin) {
        return documentRepository.delete(documentoId, usuarioId, isAdmin);
    }
}
