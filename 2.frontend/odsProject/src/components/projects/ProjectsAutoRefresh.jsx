import { useCallback } from 'react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { useSilentPoll } from '../../hooks/useSilentPoll';

/**
 * Auto-refresh de listas de proyectos (gestor / admin / evaluador) sin F5 ni spinner.
 */
export default function ProjectsAutoRefresh() {
  const { user } = useAuth();
  const { fetchUserProjects, fetchAllProjects, loadedScope } = useProjects();

  const tick = useCallback(() => {
    if (!user?.id) return;
    const role = user.role;
    if (role === 'gestor') {
      return fetchUserProjects(user.id, undefined, { force: true, silent: true });
    }
    if (role === 'admin' || role === 'evaluador' || role === 'consultor') {
      // Solo si ya cargaron scope all (lista/cola/overview)
      if (loadedScope === 'all' || loadedScope?.startsWith('user:')) {
        if (loadedScope === 'all') {
          return fetchAllProjects({ force: true, silent: true });
        }
        return fetchUserProjects(user.id, undefined, { force: true, silent: true });
      }
      return fetchAllProjects({ force: true, silent: true });
    }
    return undefined;
  }, [user, fetchUserProjects, fetchAllProjects, loadedScope]);

  useSilentPoll(tick, 10000, !!user?.id);

  return null;
}
