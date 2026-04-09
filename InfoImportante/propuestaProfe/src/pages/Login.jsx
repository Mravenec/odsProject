import React from 'react';
import { useNavigate } from 'react-router-dom';
import { LayoutGrid, Mail, Lock } from 'lucide-react';

const Login = () => {
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();
    // For now, just navigate to dashboard
    navigate('/');
  };

  return (
    <div className="flex items-center justify-center min-h-screen" style={{ backgroundColor: '#F8FAFC' }}>
      <div className="project-card" style={{ width: '100%', maxWidth: '440px', padding: '3rem 2.5rem', textAlign: 'center' }}>
        <div className="flex justify-center mb-6">
          <div style={{ backgroundColor: '#2563EB', padding: '1rem', borderRadius: '1rem', color: 'white' }}>
            <LayoutGrid size={32} />
          </div>
        </div>
        
        <h1 style={{ fontSize: '1.5rem', fontWeight: '900', color: '#0F172A', marginBottom: '0.25rem', lineHeight: '1.2' }}>Gestor de Proyectos de Extensión</h1>
        <p style={{ color: '#2563EB', fontSize: '0.875rem', fontWeight: '800', marginBottom: '2.5rem', letterSpacing: '0.05em' }}>VEC - VICERRECTORÍA DE EXTENSIÓN</p>
        
        <button 
          className="btn-secondary" 
          style={{ width: '100%', justifyContent: 'center', padding: '0.875rem', marginBottom: '1.5rem', border: '1px solid #E2E8F0', borderRadius: '0.75rem', fontWeight: '700', fontSize: '0.875rem', color: '#1E293B' }}
        >
          <img src="https://www.google.com/favicon.ico" alt="Google" style={{ width: '18px', height: '18px' }} />
          Iniciar sesión con Google
        </button>
        
        <div style={{ display: 'flex', alignItems: 'center', gap: '1rem', marginBottom: '1.5rem' }}>
          <div style={{ flex: '1', height: '1px', backgroundColor: '#E2E8F0' }}></div>
          <span style={{ fontSize: '0.625rem', color: '#94A3B8', fontWeight: '800', letterSpacing: '0.05em' }}>O ACCEDE CON TU CUENTA</span>
          <div style={{ flex: '1', height: '1px', backgroundColor: '#E2E8F0' }}></div>
        </div>
        
        <form onSubmit={handleLogin} className="flex flex-col gap-4">
          <div style={{ textAlign: 'left' }}>
            <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#64748B', marginBottom: '0.5rem', textTransform: 'uppercase' }}>Correo Electrónico</label>
            <div style={{ position: 'relative' }}>
              <Mail size={18} style={{ position: 'absolute', left: '12px', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8' }} />
              <input 
                type="email" 
                placeholder="usuario@utn.ac.cr" 
                style={{ width: '100%', paddingLeft: '2.5rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', padding: '0.75rem 0.75rem 0.75rem 2.5rem', fontSize: '0.875rem' }}
                defaultValue="kcamp@utn.ac.cr"
              />
            </div>
          </div>
          
          <div style={{ textAlign: 'left' }}>
            <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#64748B', marginBottom: '0.5rem', textTransform: 'uppercase' }}>Contraseña</label>
            <div style={{ position: 'relative' }}>
              <Lock size={18} style={{ position: 'absolute', left: '12px', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8' }} />
              <input 
                type="password" 
                placeholder="••••••••" 
                style={{ width: '100%', paddingLeft: '2.5rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', padding: '0.75rem 0.75rem 0.75rem 2.5rem', fontSize: '0.875rem' }}
              />
            </div>
          </div>
          
          <button className="btn-primary" style={{ width: '100%', justifyContent: 'center', padding: '1rem', marginTop: '1rem', backgroundColor: '#0F172A', borderRadius: '0.75rem', fontWeight: '700', fontSize: '0.875rem' }}>
            Iniciar Sesión
          </button>
        </form>
        
        <div style={{ marginTop: '2.5rem', paddingTop: '1.5rem', borderTop: '1px solid #F1F5F9', display: 'flex', flexDirection: 'column', gap: '0.75rem', fontSize: '0.75rem' }}>
          <p style={{ color: '#64748B' }}>
            ¿No tienes una cuenta? <a href="#" style={{ color: '#2563EB', textDecoration: 'none', fontWeight: '700' }}>Solicitar acceso</a>
          </p>
          <p style={{ color: '#94A3B8', fontSize: '0.625rem', fontWeight: '600', marginTop: '1rem' }}>Copyright © 2024 - Universidad Técnica Nacional</p>
        </div>
      </div>
    </div>
  );
};

export default Login;
