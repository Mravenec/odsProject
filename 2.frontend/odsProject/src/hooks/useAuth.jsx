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
          const response = await authService.verifyToken(token);
          if (response.success && response.data?.user) {
            setUser(response.data.user);
            setIsAuthenticated(true);
          } else {
            localStorage.removeItem('token');
            setIsAuthenticated(false);
          }
        } catch (error) {
          localStorage.removeItem('token');
          setIsAuthenticated(false);
        }
      } else {
        setIsAuthenticated(false);
      }
      
      setLoading(false);
    };

    checkAuth();
  }, []);

  // Login — no tocar `loading` (es solo bootstrap); si no, App desmonta LoginPage y borra el form
  const login = async (credentials) => {
    try {
      const response = await authService.login(credentials);
      
      if (response.success) {
        setUser(response.data.user);
        setIsAuthenticated(true);
        localStorage.setItem('token', response.data.token);
        return { success: true };
      }
      return { success: false, error: response.error };
    } catch (error) {
      return { success: false, error: 'Error de conexión' };
    }
  };

  // Logout
  const logout = async () => {
    try {
      await authService.logout();
    } catch (error) {
      console.error('Error en logout:', error);
    } finally {
      setUser(null);
      setIsAuthenticated(false);
      localStorage.removeItem('token');
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
