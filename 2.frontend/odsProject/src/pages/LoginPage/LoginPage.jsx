import React, { useState } from 'react';
import { useAuth } from '../../hooks/useAuth.jsx';
import './LoginPage.css';

const LoginPage = () => {
  const [credentials, setCredentials] = useState({
    username: '',
    password: ''
  });
  const [error, setError] = useState('');
  const { login, loading } = useAuth();

  const handleChange = (e) => {
    setCredentials({
      ...credentials,
      [e.target.name]: e.target.value
    });
    setError('');
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!credentials.username || !credentials.password) {
      setError('Por favor complete todos los campos');
      return;
    }

    const result = await login(credentials);
    
    if (!result.success) {
      setError(result.error);
    }
  };

  return (
    <div className="login-page">
      <div className="login-overlay"></div>
      <div className="login-container fade-in">
        <div className="login-card">
          <div className="login-header">
            <div className="logo-placeholder">ODS</div>
            <h1>Project ODS</h1>
            <p className="subtitle">Gestión Inteligente de Objetivos de Desarrollo Sostenible</p>
          </div>
          
          <form onSubmit={handleSubmit} className="login-form">
            <div className="form-group">
              <label htmlFor="username">Usuario</label>
              <div className="input-wrapper">
                <input
                  type="text"
                  id="username"
                  name="username"
                  value={credentials.username}
                  onChange={handleChange}
                  placeholder="Tu nombre de usuario"
                  disabled={loading}
                  autoComplete="username"
                />
              </div>
            </div>
            
            <div className="form-group">
              <label htmlFor="password">Contraseña</label>
              <div className="input-wrapper">
                <input
                  type="password"
                  id="password"
                  name="password"
                  value={credentials.password}
                  onChange={handleChange}
                  placeholder="••••••••"
                  disabled={loading}
                  autoComplete="current-password"
                />
              </div>
            </div>
            
            {error && <div className="error-message">{error}</div>}
            
            <button 
              type="submit" 
              className={`login-button ${loading ? 'loading' : ''}`}
              disabled={loading}
            >
              {loading ? (
                <span className="spinner"></span>
              ) : 'Entrar al Dashboard'}
            </button>
          </form>
          
          <div className="login-footer">
            <p>© 2026 Project ODS • Transformando el futuro</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
