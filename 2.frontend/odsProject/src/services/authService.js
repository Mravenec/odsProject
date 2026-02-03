// Mock de autenticación - simula una API real
const mockUsers = [
  {
    id: 1,
    username: 'admin',
    password: 'admin123',
    role: 'admin',
    name: 'Administrador',
    email: 'admin@projectODS.com'
  },
  {
    id: 2,
    username: 'user',
    password: 'user123',
    role: 'user',
    name: 'Usuario',
    email: 'user@projectODS.com'
  }
];

// Simula delay de red
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

export const authService = {
  // Login mock
  async login(credentials) {
    await delay(1000); // Simula tiempo de respuesta del servidor
    
    const { username, password } = credentials;
    const user = mockUsers.find(u => u.username === username && u.password === password);
    
    if (user) {
      // Simula token JWT
      const token = btoa(JSON.stringify({ 
        id: user.id, 
        role: user.role, 
        exp: Date.now() + 3600000 // 1 hora
      }));
      
      return {
        success: true,
        data: {
          user: {
            id: user.id,
            username: user.username,
            name: user.name,
            email: user.email,
            role: user.role
          },
          token
        }
      };
    }
    
    return {
      success: false,
      error: 'Credenciales inválidas'
    };
  },

  // Verificación de token
  async verifyToken(token) {
    await delay(500);
    
    try {
      const decoded = JSON.parse(atob(token));
      
      if (decoded.exp < Date.now()) {
        return { success: false, error: 'Token expirado' };
      }
      
      const user = mockUsers.find(u => u.id === decoded.id);
      if (user) {
        return {
          success: true,
          data: {
            id: user.id,
            username: user.username,
            name: user.name,
            email: user.email,
            role: user.role
          }
        };
      }
      
      return { success: false, error: 'Usuario no encontrado' };
    } catch (error) {
      return { success: false, error: 'Token inválido' };
    }
  },

  // Logout
  async logout() {
    await delay(300);
    return { success: true };
  }
};
