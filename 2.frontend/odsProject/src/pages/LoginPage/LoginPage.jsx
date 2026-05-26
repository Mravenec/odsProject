import React, { useState } from 'react';
import { Eye, EyeOff } from 'lucide-react';
import { useAuth } from '../../hooks/useAuth.jsx';
import './LoginPage.css';

const LoginPage = () => {
  const [credentials, setCredentials] = useState({
    email: '',
    password: ''
  });
  const [showPassword, setShowPassword] = useState(false);
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
    
    if (!credentials.email || !credentials.password) {
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
            <div className="utn-mark utn-mark--stacked" aria-label="Universidad Técnica Nacional">
              <span className="utn-mark__logo">UTN</span>
              <span className="utn-mark__text">
                <strong>Universidad Técnica Nacional</strong>
                <span>Costa Rica</span>
              </span>
            </div>
            <h1>Plataforma ODS</h1>
            <p className="subtitle">Sistema institucional de seguimiento a la Agenda 2030 de Desarrollo Sostenible.</p>
          </div>
          
          <form onSubmit={handleSubmit} className="login-form">
            <div className="form-group">
              <label htmlFor="email">Correo electrónico</label>
              <div className="input-wrapper">
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={credentials.email}
                  onChange={handleChange}
                  placeholder="correo@ejemplo.com"
                  disabled={loading}
                  autoComplete="email"
                />
              </div>
            </div>
            
            <div className="form-group">
              <label htmlFor="password">Contraseña</label>
              <div className="input-wrapper input-wrapper--password">
                <input
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  name="password"
                  value={credentials.password}
                  onChange={handleChange}
                  placeholder="••••••••"
                  disabled={loading}
                  autoComplete="current-password"
                />
                <button
                  type="button"
                  className="password-toggle"
                  onClick={() => setShowPassword(v => !v)}
                  disabled={loading}
                  aria-label={showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'}
                  aria-pressed={showPassword}
                  aria-controls="password"
                >
                  {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                </button>
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
              ) : 'Ingresar'}
            </button>
          </form>
          
          <div className="login-footer">
            <p>© {new Date().getFullYear()} Universidad Técnica Nacional · ¡Tu futuro es ahora!</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
