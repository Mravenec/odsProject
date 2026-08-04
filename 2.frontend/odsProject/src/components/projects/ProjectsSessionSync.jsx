import { useEffect, useRef } from 'react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';

/**
 * Limpia el cache compartido de proyectos al logout o al cambiar de usuario.
 */
export default function ProjectsSessionSync() {
  const { user, isAuthenticated } = useAuth();
  const { invalidateProjects } = useProjects();
  const prevUserIdRef = useRef(undefined);

  useEffect(() => {
    const id = user?.id ?? null;
    if (!isAuthenticated || id == null) {
      invalidateProjects();
      prevUserIdRef.current = null;
      return;
    }
    if (prevUserIdRef.current != null && prevUserIdRef.current !== id) {
      invalidateProjects();
    }
    prevUserIdRef.current = id;
  }, [user?.id, isAuthenticated, invalidateProjects]);

  return null;
}
