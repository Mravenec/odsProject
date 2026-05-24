package com.odsProject.odsProject.util;

public final class ProjectEditGuard {

    private ProjectEditGuard() {}

    public static boolean isEditable(String estado) {
        return "planificacion".equals(norm(estado));
    }

    /** Bloquea edición de estructura / gestión estándar (gestor fuera de planificación). */
    public static boolean isStructureLocked(String estado) {
        String e = norm(estado);
        return "activo".equals(e) || "en_aprobacion".equals(e)
                || "en_revision".equals(e) || "completado".equals(e) || "cancelado".equals(e);
    }

    public static boolean isFullyLocked(String estado) {
        String e = norm(estado);
        return "en_revision".equals(e) || "completado".equals(e) || "cancelado".equals(e);
    }

    /**
     * Sprint 2 — Persistencia de mediciones auditadas desde servicios objetivo:
     * se permiten sólo cuando el proyecto NO está bloqueado para mediciones intermedias,
     * excluyendo {@code en_revision} (el evaluador ingresa valores ahí).
     */
    public static boolean blocksMedicionesAuditadasExceptReview(String estado) {
        String e = norm(estado);
        return "activo".equals(e) || "en_aprobacion".equals(e)
                || "completado".equals(e) || "cancelado".equals(e);
    }

    private static String norm(String estado) {
        return estado == null ? "" : estado.toLowerCase();
    }
}
