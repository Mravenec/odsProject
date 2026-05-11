package com.odsProject.odsProject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Sprint 1 — Manejador global de excepciones.
 *
 * Convierte cualquier excepción no controlada en una respuesta JSON estructurada
 * con la causa real. Antes, los controladores devolvían {@code 500} con cuerpo
 * vacío y era imposible saber desde el frontend qué falló.
 *
 * Formato de respuesta:
 * <pre>
 * {
 *   "timestamp": "2026-05-10T15:30:45",
 *   "status": 409,
 *   "error": "Conflict",
 *   "message": "Duplicate entry '5-a' for key 'uk_proyecto_param'",
 *   "sqlState": "23000",
 *   "hint": "Ya existe un parámetro con ese nombre para este indicador",
 *   "path": "/api/ods/17/metas"
 * }
 * </pre>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Violaciones de integridad: FK, UNIQUE, NOT NULL, CHECK.
     * Status 409 Conflict — el dato enviado no respeta una restricción de la BD.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleIntegrity(DataIntegrityViolationException ex) {
        log.warn("[DataIntegrity] {}", ex.getMostSpecificCause().getMessage());
        Map<String, Object> body = baseBody(HttpStatus.CONFLICT, ex);

        String msg = ex.getMostSpecificCause().getMessage();
        if (msg != null) {
            if (msg.contains("foreign key")) {
                body.put("hint", "Una referencia (proyecto, indicador maestro o usuario) no existe en su tabla destino");
            } else if (msg.contains("Duplicate entry")) {
                body.put("hint", "Ya existe un registro con esa combinación única");
            } else if (msg.contains("cannot be null")) {
                body.put("hint", "Falta un campo obligatorio en el payload");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateKeyException ex) {
        log.warn("[Duplicate] {}", ex.getMostSpecificCause().getMessage());
        Map<String, Object> body = baseBody(HttpStatus.CONFLICT, ex);
        body.put("hint", "Conflicto de llave única: el registro ya existe");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    /**
     * Errores genéricos de acceso a datos (timeout, conexión, etc.)
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccess(DataAccessException ex) {
        log.error("[DataAccess] {}", ex.getMostSpecificCause().getMessage(), ex);
        Map<String, Object> body = baseBody(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        body.put("hint", "Error de acceso a la base de datos. Revisar logs del backend");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    /**
     * Validaciones de negocio que el código lanza explícitamente.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArg(IllegalArgumentException ex) {
        log.warn("[IllegalArg] {}", ex.getMessage());
        return ResponseEntity.badRequest().body(baseBody(HttpStatus.BAD_REQUEST, ex));
    }

    /**
     * Excepciones que el código levantó con un status específico.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatus(ResponseStatusException ex) {
        log.warn("[ResponseStatus {}] {}", ex.getStatusCode().value(), ex.getReason());
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getReason());
        return ResponseEntity.status(status).body(body);
    }

    /**
     * Cualquier otra excepción no esperada. Se loguea con stack trace completo.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        log.error("[Unhandled] {}", ex.getMessage(), ex);
        Map<String, Object> body = baseBody(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        body.put("hint", "Error no esperado. Ver logs del backend para el stack trace");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

    // ── helpers ───────────────────────────────────────────────────────────

    private Map<String, Object> baseBody(HttpStatus status, Throwable ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());

        Throwable root = ex;
        while (root.getCause() != null && root.getCause() != root) root = root.getCause();
        body.put("message", root.getMessage() != null ? root.getMessage() : ex.getClass().getSimpleName());
        body.put("exception", ex.getClass().getSimpleName());

        // SQLState si está disponible
        if (root instanceof java.sql.SQLException sqlEx) {
            body.put("sqlState", sqlEx.getSQLState());
            body.put("sqlErrorCode", sqlEx.getErrorCode());
        }
        return body;
    }
}
