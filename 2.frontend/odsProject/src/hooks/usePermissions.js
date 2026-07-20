import { useMemo } from 'react';
import { useAuth } from './useAuth.jsx';

/**
 * Sprint 10 — Matriz central de permisos por rol.
 * Roles: admin, gestor, evaluador, consultor.
 * Regla del profesor: el que propone NO mide; el que mide NO propone.
 */
const capitalize = (s) => (s ? s.charAt(0).toUpperCase() + s.slice(1) : 'Sin rol');

const ROLE_MATRIX = {
  admin: {
    canViewAllProjects: true, canViewGlobalDashboard: true,
    canViewAdminPanel:  true, canViewAuditQueue: true,
    canManageUsers:     true, canViewLoginAudit: true,
    canCreateProject:   false, canEditAnyProject: true, canEditOwnProject: false,
    canUploadEvidence:  false, canDownloadEvidence: true,
    canEnterMeasurements: true, canViewMeasurements: true,
    canApproveProject:  true, canDeleteProject: true,
  },
  evaluador: {
    canViewAllProjects: true, canViewGlobalDashboard: true,
    canViewAdminPanel:  false, canManageUsers:     false, canViewAuditQueue: true,
    canViewLoginAudit:  false,
    canCreateProject:   false, canEditAnyProject: false, canEditOwnProject: false,
    canUploadEvidence:  false, canDownloadEvidence: true,
    canEnterMeasurements: true, canViewMeasurements: true,
    canApproveProject:  true, canDeleteProject: false,
  },
  gestor: {
    canViewAllProjects: false, canViewGlobalDashboard: false,
    canViewAdminPanel:  false, canManageUsers:     false, canViewAuditQueue: false,
    canViewLoginAudit:  false,
    canCreateProject:   true, canEditAnyProject: false, canEditOwnProject: true,
    canUploadEvidence:  true, canDownloadEvidence: true,
    canEnterMeasurements: false, canViewMeasurements: true,
    canApproveProject:  false, canDeleteProject: false,
  },
  consultor: {
    canViewAllProjects: true, canViewGlobalDashboard: true,
    canViewAdminPanel:  false, canManageUsers:     false, canViewAuditQueue: false,
    canViewLoginAudit:  false,
    canCreateProject:   false, canEditAnyProject: false, canEditOwnProject: false,
    canUploadEvidence:  false, canDownloadEvidence: true,
    canEnterMeasurements: false, canViewMeasurements: true,
    canApproveProject:  false, canDeleteProject: false,
  },
};

const DENY_ALL = Object.keys(ROLE_MATRIX.admin).reduce((acc, k) => {
  acc[k] = false;
  return acc;
}, {});

export function usePermissions() {
  const { user } = useAuth();
  return useMemo(() => {
    const role = (user?.role || '').toLowerCase();
    const caps = ROLE_MATRIX[role] || DENY_ALL;
    const canEditInPlanificacion = (project) => {
      if (!project) return false;
      const estado = String(project.status ?? project.estado ?? '').toLowerCase();
      if (estado !== 'planificacion') return false;
      if (role === 'admin' || role === 'evaluador') return true;
      if (role === 'gestor') return project.userId === user?.id;
      return false;
    };

    return {
      role,
      roleLabel: capitalize(role),
      ...caps,
      isOwner: (project) => project?.userId === user?.id,
      canEditProject: (project) =>
        caps.canEditAnyProject || (caps.canEditOwnProject && project?.userId === user?.id),
      canEditInPlanificacion,
      canExportBulkProjects: ['admin', 'evaluador', 'consultor'].includes(role),
      canUploadEvidenceFor: (project) => {
        if (!project) return false;
        const estado = String(project.status ?? project.estado ?? '').toLowerCase();
        return caps.canUploadEvidence && project.userId === user?.id && estado === 'activo';
      },
    };
  }, [user]);
}

export default usePermissions;
