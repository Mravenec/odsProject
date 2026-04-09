import api from './api';

export const authService = {
  // Función interna para unificar el mapeo del usuario (Híbrida y Ultra-Robusta)
  _mapUser(data, defaultUsername = 'usuario') {
    if (!data) return null;

    // 1. Identificar la fuente de datos (raíz o anidada)
    // El backend puede enviar datos al root o dentro de .usuario / .user
    const source = data.usuario || data.user || data;
    
    console.log('[AuthService] Mapeando usuario desde la fuente:', Object.keys(source));

    // 2. Extraer el ID (campo crítico)
    const id = source.userId || source.id || source.idUsuario || source.sub;

    if (!id) {
      console.error('[AuthService] Error Crítico: No se encontró ID en ninguna estructura conocida.', {
        rootKeys: Object.keys(data),
        sourceKeys: Object.keys(source)
      });
      return null; // Forzar fallo de guardia
    }

    // 3. Extraer el Rol
    const role = source.role || source.rol || source.perfil || 
                 (source.rolId === 1 ? 'admin' : source.rolId === 2 ? 'gestor' : 'user');

    return {
      id: id,
      username: source.email || source.username || source.login || defaultUsername,
      name: source.nombre || source.name || source.fullName || source.email || defaultUsername,
      email: source.email || 'usuario@ods.cr',
      role: role
    };
  },

  // Login real
  async login(credentials) {
    try {
      const response = await api.post('/login/auth/login', {
        email: credentials.username,
        password: credentials.password,
        ip: "127.0.0.1",
        userAgent: "React Frontend"
      });
      
      console.log('[AuthService] Respuesta de login recibida:', Object.keys(response.data));

      // Pasamos todo el response.data, _mapUser se encarga de buscar dentro
      const user = this._mapUser(response.data, credentials.username);
      const token = response.data.token || response.data.usuario?.token || response.data.user?.token;
      
      if (!user || !user.id) {
        return { success: false, error: 'La respuesta del servidor no contiene datos de usuario válidos' };
      }

      return {
        success: true,
        data: { user, token }
      };
    } catch (error) {
      console.error('[AuthService] Error en login API:', error);
      return {
        success: false,
        error: error.response?.data?.message || 'Error de comunicación con el servidor'
      };
    }
  },

  // Verificación de token real
  async verifyToken(token) {
    try {
      const response = await api.get('/login/auth/validate', {
        headers: {
          'Authorization': token
        }
      });

      console.log('[AuthService] Respuesta de validación recibida:', Object.keys(response.data));

      // El endpoint de validación también puede retornar datos híbridos
      const user = this._mapUser(response.data);
      
      if (!user || !user.id) {
        return { success: false, error: 'Sesión expirada o inválida' };
      }

      return {
        success: true,
        data: { user }
      };
    } catch (error) {
      console.error('[AuthService] Error en validación de token:', error);
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
