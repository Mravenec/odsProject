package com.odsProject.odsProject.util;

import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.Proyectos;
import com.odsProject.odsProject.database.jooq.ods_master.tables.pojos.VistaResumenProyectosOds;

import java.util.List;
import java.util.stream.Collectors;

public final class ProjectAccessHelper {

    private ProjectAccessHelper() {}

    public static boolean canViewProject(String role, Proyectos project, Integer userId) {
        if (role == null || project == null) return true;
        String r = role.toLowerCase();
        if ("consultor".equals(r) || "planeacion".equals(r)) {
            return "completado".equals(estado(project));
        }
        if ("gestor".equals(r)) {
            return userId != null && userId.equals(project.getUsuarioId());
        }
        return true;
    }

    public static boolean canEditProject(String role, Proyectos project, Integer userId) {
        if (role == null || project == null) return false;
        String r = role.toLowerCase();
        if ("consultor".equals(r) || "planeacion".equals(r) || "auditor".equals(r) || "evaluador".equals(r)) {
            return false;
        }
        if ("gestor".equals(r)) {
            return userId != null && userId.equals(project.getUsuarioId());
        }
        return "admin".equals(r);
    }

    public static List<VistaResumenProyectosOds> filterProjectsForRole(
            String role, List<VistaResumenProyectosOds> projects) {
        if (role == null || projects == null) return projects;
        String r = role.toLowerCase();
        if ("consultor".equals(r) || "planeacion".equals(r)) {
            return projects.stream()
                    .filter(p -> p.getEstado() != null
                            && "completado".equals(String.valueOf(p.getEstado())))
                    .collect(Collectors.toList());
        }
        return projects;
    }

    private static String estado(Proyectos project) {
        return project.getEstado() == null ? "" : String.valueOf(project.getEstado()).toLowerCase();
    }
}
