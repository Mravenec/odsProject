package com.odsProject.odsProject.service.interfaces;

/**
 * Reglas de autorización por rol (JWT custom). Usado por filtros y, si aplica, por controllers.
 */
public interface IRoleAuthorizationService {

    /**
     * @param authorizationHeader valor de {@code Authorization} (p. ej. {@code Bearer …})
     * @return claim {@code rol} del JWT, o null si no hay token válido
     */
    String extractRoleFromAuthorizationHeader(String authorizationHeader);

    /**
     * @param authorizationHeader valor de {@code Authorization}
     * @return ID del usuario (JWT subject), o null si no hay token válido
     */
    Integer extractUserIdFromAuthorizationHeader(String authorizationHeader);

    /**
     * @return true si el rol no puede ejecutar métodos de escritura HTTP
     */
    boolean isWriteForbiddenForRole(String role);

    /** Código JSON de error cuando un consultor intenta escribir. */
    String consultorReadonlyErrorCode();

    /** admin, evaluador y consultor pueden descargar el Excel consolidado por sede/año. */
    boolean canExportBulkProjects(String role);

    /** admin, evaluador y consultor ven todos los proyectos; gestor solo los propios. */
    boolean canViewAllProjects(String role);
}
