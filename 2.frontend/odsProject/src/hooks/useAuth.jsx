import React, { useState, useEffect, useContext, createContext } from 'react';
import { authService } from '../services/authService';

// Contexto de autenticación
const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [isAuthenticated, setIsAuthenticated] = useState(false);

  // Verificar autenticación al cargar
  useEffect(() => {
    const checkAuth = async () => {
      const token = localStorage.getItem('token');
      
      if (token) {
        try {
          console.log('[Auth] Verificando token persistido...');
          const response = await authService.verifyToken(token);
          if (response.success && response.data?.user) {
            console.log('[Auth] Usuario verificado correctamente:', response.data.user);
            setUser(response.data.user);
            setIsAuthenticated(true);
          } else {
            console.warn('[Auth] Token inválido o sin datos de usuario');
            localStorage.removeItem('token');
          }
        } catch (error) {
          console.error('[Auth] Error en verificación de sesión:', error);
          localStorage.removeItem('token');
        }
      }
      
      setLoading(false);
    };

    checkAuth();
  }, []);

  // Login
  const login = async (credentials) => {
    setLoading(true);
    try {
      const response = await authService.login(credentials);
      
      if (response.success) {
        setUser(response.data.user);
        setIsAuthenticated(true);
        localStorage.setItem('token', response.data.token);
        return { success: true };
      } else {
        return { success: false, error: response.error };
      }
    } catch (error) {
      return { success: false, error: 'Error de conexión' };
    } finally {
      setLoading(false);
    }
  };

  // Logout
  const logout = async () => {
    setLoading(true);
    try {
      await authService.logout();
      setUser(null);
      setIsAuthenticated(false);
      localStorage.removeItem('token');
    } catch (error) {
      console.error('Error en logout:', error);
    } finally {
      setLoading(false);
    }
  };
  
  // Catálogos
  const getSedes = React.useCallback(async () => {
    return await authService.getSedes();
  }, []);

  const getActiveUsers = React.useCallback(async () => {
    return await authService.getActiveUsers();
  }, []);

  // Verificar permisos
  const hasRole = (role) => {
    return user?.role === role;
  };

  const isAdmin = () => hasRole('admin');
  const isGestor = () => hasRole('gestor');
  const isUser = () => hasRole('user');

  const value = {
    user,
    loading,
    isAuthenticated,
    login,
    logout,
    getSedes,
    getActiveUsers,
    hasRole,
    isAdmin,
    isGestor,
    isUser
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

// Hook personalizado para usar el contexto
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth debe ser usado dentro de un AuthProvider');
  }
  return context;
};
