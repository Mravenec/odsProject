import React from 'react';
import { useNavigate } from 'react-router-dom';
import { usePermissions } from '../../hooks/usePermissions';
import { Lock, ArrowLeft } from 'lucide-react';
import './ForbiddenPage.css';

export default function ForbiddenPage() {
  const navigate = useNavigate();
  const perms = usePermissions();
  return (
    <div className="forbidden-page">
      <div className="forbidden-card fade-in">
        <div className="forbidden-icon-wrap">
          <Lock size={26} />
        </div>
        <h2 className="forbidden-title">Sin acceso</h2>
        <p className="forbidden-text">
          Tu rol <strong>{perms.roleLabel}</strong> no tiene permisos para esta sección.
        </p>
        <p className="forbidden-hint">
          Si crees que es un error, contactá a un administrador.
        </p>
        <button
          type="button"
          className="forbidden-back-btn"
          onClick={() => navigate('/dashboard')}
        >
          <ArrowLeft size={16} /> Volver al dashboard
        </button>
      </div>
    </div>
  );
}
