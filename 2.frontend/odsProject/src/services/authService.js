import api from './api';

export const authService = {
  // Mapeo estricto basado en LoginController y login_system.sql
  _mapUser(data) {
    if (!data) return null;

    // El backend retorna los datos directamente en el login o anidados en 'user' en validación
    const source = data.user || data;
    
    // Mapeo exacto de campos del backend
    const id = source.userId || source.id;
    if (!id) return null;

    return {
      id: id,
      username: source.username || source.email,
      name: source.fullName || source.nombre || source.username,
      email: source.email,
      role: source.role || source.rol || (
        source.rolId === 1 ? 'admin' : 
        source.rolId === 2 ? 'gestor' : 
        source.rolId === 4 ? 'evaluador' : 'consultor'
      )
    };
  },

  // Login sincronizado con LoginController.java
  async login(credentials) {
    try {
      const response = await api.post('/login/auth/login', {
        email: credentials.username, // El backend espera 'email'
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
      return { success: true, data: response.data };
    } catch (error) {
      console.error('[AuthService] Error obteniendo usuarios activos:', error);
      return { success: false, error: 'No se pudo cargar la lista de personal académico' };
    }
  }
};
