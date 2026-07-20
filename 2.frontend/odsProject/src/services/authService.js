import api from './api';

export const authService = {
  // Mapeo estricto basado en LoginController y login_system.sql
  _mapUser(data) {
    if (!data) return null;

    const nested = data.user;
    const source = nested || data;
    const profile = data.profile || source.profile || {};

    const id = source.userId || source.id || data.userId;
    if (!id) return null;

    return {
      id,
      username: source.username || source.email || data.email,
      name: source.fullName || source.full_name || source.nombre || source.username,
      fullName: source.fullName || source.full_name || source.nombre || source.username,
      email: source.email || data.email,
      role: (source.role || source.rol || data.role || '').toLowerCase(),
      sedeId: source.sedeId ?? source.sede_id ?? data.sedeId ?? profile.sedeId ?? null,
      sedeNombre: source.sedeNombre || data.sedeNombre || profile.sedeNombre || null,
      telefonoContacto: source.telefonoContacto || data.telefonoContacto || profile.telefonoContacto || '',
      areaId: source.areaId ?? source.area_id ?? data.areaId ?? profile.areaId ?? null,
      areaNombre: source.areaNombre || data.areaNombre || profile.areaNombre || null,
      dependenciaId: source.dependenciaId ?? source.dependencia_id ?? data.dependenciaId ?? profile.dependenciaId ?? null,
      dependenciaNombre: source.dependenciaNombre || data.dependenciaNombre || profile.dependenciaNombre || null,
      rolDependenciaId: source.rolDependenciaId ?? source.rol_dependencia_id ?? data.rolDependenciaId ?? profile.rolDependenciaId ?? null,
      rolDependenciaNombre: source.rolDependenciaNombre || data.rolDependenciaNombre || profile.rolDependenciaNombre || null,
      contacto: source.contacto || data.contacto || profile.contacto || null,
    };
  },

  // Login sincronizado con LoginController.java
  async login(credentials) {
    try {
      const response = await api.post('/login/auth/login', {
        email: credentials.email || credentials.username,
        password: credentials.password,
        ip: "127.0.0.1",
        userAgent: "React Frontend"
      });
      
      const { data } = response;
      const user = this._mapUser(data);
      
      if (!user) {
        return { success: false, error: 'Respuesta de usuario inválida' };
      }

      return {
        success: true,
        data: { 
          user, 
          token: data.token 
        }
      };
    } catch (error) {
      return {
        success: false,
        error: error.response?.data?.message || 'Credenciales incorrectas'
      };
    }
  },

  // Verificación de token real (Alineado con Backend: GET /login/auth/validate)
  async verifyToken(token) {
    try {
      // El backend espera un GET con el token en la cabecera Authorization
      const response = await api.get('/login/auth/validate', {
        headers: {
          'Authorization': token.startsWith('Bearer ') ? token : `Bearer ${token}`
        }
      });

      console.log('[AuthService] Respuesta de validación recibida:', !!response.data);

      const user = this._mapUser(response.data);
      
      if (!user || !user.id) {
        return { success: false, error: 'Sesión expirada o inválida' };
      }

      return {
        success: true,
        data: { user }
      };
    } catch (error) {
      console.error('[AuthService] Error en validación de token:', error.response?.status, error.message);
      return { success: false, error: 'Token inválido' };
    }
  },

  // Logout real
  async logout() {
    try {
      await api.post('/login/auth/logout');
      return { success: true };
    } catch (error) {
      return { success: true };
    }
  },

  // ── Catálogos y Datos Administrativos ──

  // Obtener catálogo de sedes
  async getSedes() {
    try {
      const response = await api.get('/login/sedes');
      return { success: true, data: response.data };
    } catch (error) {
      console.error('[AuthService] Error obteniendo sedes:', error);
      return { success: false, error: 'No se pudo cargar el catálogo de sedes' };
    }
  },

  // Obtener usuarios activos (para responsables técnicos/académicos)
  async getActiveUsers() {
    try {
      const response = await api.get('/login/admin/active-users');
      const data = (response.data || []).map(u => this._mapAdminUser(u));
      return { success: true, data };
    } catch (error) {
      console.error('[AuthService] Error obteniendo usuarios activos:', error);
      return { success: false, error: 'No se pudo cargar la lista de personal académico' };
    }
  },

  // ── Administración de usuarios (Sprint 6) ──

  _mapAdminUser(u) {
    if (!u) return null;
    return {
      id: u.id,
      username: u.username,
      email: u.email,
      fullName: u.fullName || u.full_name,
      rolId: u.rolId ?? u.rol_id,
      rol: u.rol || u.role,
      sedeId: u.sedeId ?? u.sede_id,
      sede: u.sede,
      areaId: u.areaId ?? u.area_id ?? null,
      dependenciaId: u.dependenciaId ?? u.dependencia_id ?? null,
      rolDependenciaId: u.rolDependenciaId ?? u.rol_dependencia_id ?? null,
      telefonoContacto: u.telefonoContacto ?? u.telefono_contacto ?? '',
      isActive: u.isActive ?? u.is_active,
      ultimoLogin: u.ultimoLogin || u.ultimo_login,
      createdAt: u.createdAt || u.created_at,
    };
  },

  _mapRole(r) {
    return {
      id: r.id,
      name: r.name || r.nombre,
      nombre: r.nombre || r.name,
      descripcion: r.descripcion || r.description || '',
    };
  },

  _apiError(error, fallback) {
    return error.userMessage || error.response?.data?.message || fallback;
  },

  async listUsers() {
    try {
      const res = await api.get('/login/users');
      const data = (res.data || []).map(u => this._mapAdminUser(u));
      return { success: true, data };
    } catch (error) {
      console.error('[AuthService] listUsers:', error);
      return { success: false, error: this._apiError(error, 'No se pudo cargar la lista de usuarios') };
    }
  },

  async getRoles() {
    try {
      const res = await api.get('/login/roles');
      const data = (res.data || []).map(r => this._mapRole(r));
      return { success: true, data };
    } catch (error) {
      console.error('[AuthService] getRoles:', error);
      return { success: false, error: this._apiError(error, 'No se pudo cargar el catálogo de roles') };
    }
  },

  async createUser(payload) {
    try {
      const res = await api.post('/login/users', payload);
      return { success: true, data: this._mapAdminUser(res.data) };
    } catch (error) {
      console.error('[AuthService] createUser:', error);
      return { success: false, error: this._apiError(error, 'No se pudo crear el usuario') };
    }
  },

  async updateUser(id, payload) {
    try {
      const body = { ...payload };
      if (!body.password) delete body.password;
      const res = await api.put(`/login/users/${id}`, body);
      return { success: true, data: this._mapAdminUser(res.data) };
    } catch (error) {
      console.error('[AuthService] updateUser:', error);
      return { success: false, error: this._apiError(error, 'No se pudo actualizar el usuario') };
    }
  },

  async deactivateUser(id) {
    try {
      const res = await api.patch(`/login/users/${id}/deactivate`);
      return { success: true, data: this._mapAdminUser(res.data) };
    } catch (error) {
      console.error('[AuthService] deactivateUser:', error);
      return { success: false, error: this._apiError(error, 'No se pudo desactivar el usuario') };
    }
  },

  // ── Bitácora de ingresos (admin) ──

  _mapAuditEntry(row) {
    if (!row) return null;
    return {
      id: row.id,
      fecha: row.fechaEvento || row.fecha_evento || row.fecha,
      evento: row.evento || '',
      usuario: row.username || row.usuario || row.fullName || row.full_name || row.emailIntento || '—',
      fullName: row.fullName || row.full_name || '',
      ip: row.ipAddress || row.ip_address || row.ip || '—',
      userAgent: row.userAgent || row.user_agent || '',
      detalle: row.detalle || '',
    };
  },

  /**
   * GET /login/admin/audit-recent — Bearer admin.
   * Campos: usuario, fecha, ip, evento (LOGIN_OK | LOGIN_FALLIDO | LOGOUT).
   */
  async getAuditRecent(dias = 30) {
    try {
      const res = await api.get('/login/admin/audit-recent', {
        params: { dias },
      });
      const data = (res.data || []).map((row) => this._mapAuditEntry(row));
      return { success: true, data };
    } catch (error) {
      console.error('[AuthService] getAuditRecent:', error);
      return { success: false, error: this._apiError(error, 'No se pudo cargar la bitácora de ingresos') };
    }
  },
};
