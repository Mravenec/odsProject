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
  const [submitting, setSubmitting] = useState(false);
  const { login } = useAuth();

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

    setSubmitting(true);
    try {
      const result = await login(credentials);
      if (!result.success) {
        setError(result.error || 'Correo o contraseña incorrectos. Verificá e intentá de nuevo.');
      }
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="login-page">
      <div className="login-overlay"></div>
      <div className="login-container fade-in">
        <div className="login-card">
          <div className="login-header">
            {/* Placeholder: replace public/assets/utn-logo.png with the official UTN logo when provided by the professor. */}
            <img
              src="/assets/utn-logo.png"
              alt="Universidad Técnica Nacional"
              className="login-logo"
              width={280}
              height={112}
              decoding="async"
            />
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
                  disabled={submitting}
                  autoComplete="email"
                  aria-invalid={!!error}
                  aria-describedby={error ? 'login-error' : undefined}
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
                  disabled={submitting}
                  autoComplete="current-password"
                  aria-invalid={!!error}
                  aria-describedby={error ? 'login-error' : undefined}
                />
                <button
                  type="button"
                  className="password-toggle"
                  onClick={() => setShowPassword(v => !v)}
                  disabled={submitting}
                  aria-label={showPassword ? 'Ocultar contraseña' : 'Mostrar contraseña'}
                  aria-pressed={showPassword}
                  aria-controls="password"
                >
                  {showPassword ? <EyeOff size={18} /> : <Eye size={18} />}
                </button>
              </div>
            </div>
            
            {error && (
              <div id="login-error" className="error-message" role="alert" aria-live="assertive">
                {error}
              </div>
            )}
            
            <button 
              type="submit" 
              className={`login-button ${submitting ? 'loading' : ''}`}
              disabled={submitting}
            >
              {submitting ? (
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
