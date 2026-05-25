package com.odsProject.odsProject.controller;

import com.odsProject.odsProject.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping(value = "/projects/{projectId}/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos> upload(
            @PathVariable Integer projectId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("usuarioId") Integer usuarioId,
            @RequestParam(value = "descripcion", required = false) String descripcion) throws Exception {
        return ResponseEntity.ok(
                documentService.uploadDocument(projectId, usuarioId, file, descripcion));
    }

    @GetMapping("/projects/{projectId}/documents")
    public ResponseEntity<List<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos>> list(
            @PathVariable Integer projectId) {
        return ResponseEntity.ok(documentService.listByProyecto(projectId));
    }

    @GetMapping("/documents/{docId}")
    public ResponseEntity<com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.ProyectoDocumentos> metadata(
            @PathVariable Integer docId) {
        var doc = documentService.getDocumentoCompleto(docId);
        if (doc == null) return ResponseEntity.notFound().build();
        doc.setContenido(null);
        return ResponseEntity.ok(doc);
    }

    @GetMapping("/documents/{docId}/download")
    public ResponseEntity<byte[]> download(@PathVariable Integer docId) {
        var doc = documentService.getDocumentoCompleto(docId);
        if (doc == null) return ResponseEntity.notFound().build();
        byte[] data = doc.getContenido() != null ? doc.getContenido() : new byte[0];
        String nombre = doc.getNombreArchivo() != null ? doc.getNombreArchivo() : "documento";
        String mime = doc.getTipoMime() != null ? doc.getTipoMime() : "application/octet-stream";
        String encoded = URLEncoder.encode(nombre, StandardCharsets.UTF_8).replace("+", "%20");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mime));
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encoded);
        headers.setContentLength(data.length);
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @DeleteMapping("/documents/{docId}")
    public ResponseEntity<Map<String, Object>> delete(
            @PathVariable Integer docId,
            @RequestParam Integer usuarioId,
            @RequestParam(value = "admin", defaultValue = "false") boolean admin) {
        return ResponseEntity.ok(Map.of("deleted", documentService.deleteDocumento(docId, usuarioId, admin)));
    }
}
