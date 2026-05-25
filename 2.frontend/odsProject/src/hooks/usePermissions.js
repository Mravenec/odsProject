import { useMemo } from 'react';
import { useAuth } from './useAuth.jsx';

/**
 * Sprint 10 — Matriz central de permisos por rol.
 * Roles: admin, gestor, evaluador, consultor.
 * Regla del profesor: el que propone NO mide; el que mide NO propone.
 */
const ROLE_MATRIX = {
  admin: {
    canViewAllProjects: true, canViewGlobalDashboard: true,
    canViewAdminPanel:  true, canViewAuditQueue: true,
    canManageUsers:     true,
    canCreateProject:   false, canEditAnyProject: true, canEditOwnProject: false,
    canUploadEvidence:  false, canDownloadEvidence: true,
    canEnterMeasurements: true, canViewMeasurements: true,
    canApproveProject:  true, canDeleteProject: true,
    roleLabel: 'Administrador',
  },
  evaluador: {
    canViewAllProjects: true, canViewGlobalDashboard: true,
    canViewAdminPanel:  false, canManageUsers:     false, canViewAuditQueue: true,
    canCreateProject:   false, canEditAnyProject: false, canEditOwnProject: false,
    canUploadEvidence:  false, canDownloadEvidence: true,
    canEnterMeasurements: true, canViewMeasurements: true,
    canApproveProject:  true, canDeleteProject: false,
    roleLabel: 'Evaluador',
  },
  gestor: {
    canViewAllProjects: false, canViewGlobalDashboard: false,
    canViewAdminPanel:  false, canManageUsers:     false, canViewAuditQueue: false,
    canCreateProject:   true, canEditAnyProject: false, canEditOwnProject: true,
    canUploadEvidence:  true, canDownloadEvidence: true,
    canEnterMeasurements: false, canViewMeasurements: true,  // ← ve sus resultados pero NO los ingresa
    canApproveProject:  false, canDeleteProject: false,
    roleLabel: 'Gestor',
  },
  consultor: {
    canViewAllProjects: true, canViewGlobalDashboard: true,
    canViewAdminPanel:  false, canManageUsers:     false, canViewAuditQueue: false,
    canCreateProject:   false, canEditAnyProject: false, canEditOwnProject: false,
    canUploadEvidence:  false, canDownloadEvidence: true,
    canEnterMeasurements: false, canViewMeasurements: true,  // ← solo lee
    canApproveProject:  false, canDeleteProject: false,
    roleLabel: 'Consultor',
  },
};

const DENY_ALL = Object.keys(ROLE_MATRIX.admin).reduce((acc, k) => {
  acc[k] = typeof ROLE_MATRIX.admin[k] === 'boolean' ? false : 'Sin rol';
  return acc;
}, {});

export function usePermissions() {
  const { user } = useAuth();
  return useMemo(() => {
    const role = (user?.role || '').toLowerCase();
    const caps = ROLE_MATRIX[role] || DENY_ALL;
    return {
      role,
      ...caps,
      isOwner: (project) => project?.userId === user?.id,
      canEditProject: (project) =>
        caps.canEditAnyProject || (caps.canEditOwnProject && project?.userId === user?.id),
      canUploadEvidenceFor: (project) =>
        caps.canUploadEvidence && project?.userId === user?.id,
    };
  }, [user]);
}

export default usePermissions;
