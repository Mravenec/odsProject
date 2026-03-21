import api from './api';

export const authService = {
  // Login real
  async login(credentials) {
    try {
      const response = await api.post('/login/auth/login', {
        email: credentials.username, // El Frontend pide username, el Backend asume que es email
        password: credentials.password,
        ip: "127.0.0.1",
        userAgent: "React Frontend"
      });
      
      const { token, userId, email, role, nombre } = response.data;
      
      return {
        success: true,
        data: {
          user: {
            id: userId || response.data.id || 1,
            username: credentials.username,
            name: nombre || credentials.username,
            email: email || credentials.username,
            role: role || 'admin'
          },
          token: token || response.data.token
        }
      };
    } catch (error) {
      return {
        success: false,
        error: error.response?.data?.message || 'Credenciales inválidas'
      };
    }
  },

  // Verificación de token real
  async verifyToken(token) {
    try {
      const response = await api.get('/login/auth/validate');
      return {
        success: true,
        data: {
          id: response.data.userId || 1,
          username: 'usuario',
          name: response.data.nombre || 'Usuario Autorizado',
          email: response.data.email || 'usuario@ods.cr',
          role: response.data.role || 'admin'
        }
      };
    } catch (error) {
      return { success: false, error: 'Token inválido' };
    }
  },

  // Logout real
  async logout() {
    try {
      await api.post('/login/auth/logout');
      return { success: true };
    } catch (error) {
      // Incluso si falla en el servidor, permitimos limpiar el frontend
      return { success: true };
    }
  }
};
