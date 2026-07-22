import { useState, useEffect, useCallback } from 'react';
import { authService } from '../services/authService';
import { sodsiCatalogService } from '../services/sodsiCatalogService';
import { useAuth } from './useAuth.jsx';

const EMPTY_SODSI_CATALOGS = {
  areas: [],
  dependencias: [],
  rolesDependencia: [],
  unidades: [],
};

/**
 * Administración de usuarios — Service → Hook → UsersAdminPage.
 */
export function useUsersAdmin() {
  const { getSedes } = useAuth();
  const [users, setUsers] = useState([]);
  const [roles, setRoles] = useState([]);
  const [sedes, setSedes] = useState([]);
  const [sodsiCatalogs, setSodsiCatalogs] = useState(EMPTY_SODSI_CATALOGS);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [saving, setSaving] = useState(false);

  const load = useCallback(async () => {
    setLoading(true);
    setError('');
    try {
      const [usersRes, rolesRes, sedesRes, sodsiRes] = await Promise.all([
        authService.listUsers(),
        authService.getRoles(),
        getSedes(),
        sodsiCatalogService.getCatalogos(),
      ]);
      if (usersRes.success) setUsers(usersRes.data || []);
      else setError(usersRes.error || 'No se pudieron cargar usuarios');
      if (rolesRes.success) setRoles(rolesRes.data || []);
      if (sedesRes.success) setSedes(sedesRes.data || []);
      if (sodsiRes.success) {
        setSodsiCatalogs({
          areas: sodsiRes.data?.areas || [],
          dependencias: sodsiRes.data?.dependencias || [],
          rolesDependencia: sodsiRes.data?.rolesDependencia || [],
          unidades: sodsiRes.data?.unidades || [],
        });
      }
    } catch (e) {
      setError(e.message);
    } finally {
      setLoading(false);
    }
  }, [getSedes]);

  useEffect(() => {
    load();
  }, [load]);

  const createUser = useCallback(async (payload) => {
    setSaving(true);
    const r = await authService.createUser(payload);
    setSaving(false);
    if (r.success) await load();
    return r;
  }, [load]);

  const updateUser = useCallback(async (userId, payload) => {
    setSaving(true);
    const r = await authService.updateUser(userId, payload);
    setSaving(false);
    if (r.success) await load();
    return r;
  }, [load]);

  const deactivateUser = useCallback(async (userId) => {
    setSaving(true);
    const r = await authService.deactivateUser(userId);
    setSaving(false);
    if (r.success) await load();
    return r;
  }, [load]);

  return {
    users,
    roles,
    sedes,
    sodsiCatalogs,
    loading,
    error,
    saving,
    setError,
    reload: load,
    createUser,
    updateUser,
    deactivateUser,
  };
}

export default useUsersAdmin;
