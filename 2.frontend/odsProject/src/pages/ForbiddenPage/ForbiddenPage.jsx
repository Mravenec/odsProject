import React from 'react';
import { useNavigate } from 'react-router-dom';
import { usePermissions } from '../../hooks/usePermissions';
import { Lock, ArrowLeft } from 'lucide-react';

export default function ForbiddenPage() {
  const navigate = useNavigate();
  const perms = usePermissions();
  return (
    <div style={{minHeight:'70vh',display:'flex',alignItems:'center',justifyContent:'center',padding:20}}>
      <div style={{background:'#fff',borderRadius:12,padding:'40px 32px',textAlign:'center',
                   maxWidth:480,boxShadow:'0 4px 12px rgba(0,0,0,0.05)',border:'1px solid #f1f3f5'}}>
        <div style={{width:56,height:56,margin:'0 auto 20px',borderRadius:'50%',background:'#fef3c7',
                     display:'flex',alignItems:'center',justifyContent:'center'}}>
          <Lock size={26} color="#92400e" />
        </div>
        <h2 style={{margin:'0 0 8px',fontSize:22,color:'#111'}}>Sin acceso</h2>
        <p style={{margin:'0 0 6px',color:'#555',fontSize:14}}>
          Tu rol <strong>{perms.roleLabel}</strong> no tiene permisos para esta sección.
        </p>
        <p style={{margin:'0 0 22px',color:'#888',fontSize:13}}>
          Si crees que es un error, contactá a un administrador.
        </p>
        <button onClick={() => navigate('/dashboard')}
                style={{display:'inline-flex',alignItems:'center',gap:8,padding:'10px 18px',
                        background:'#3b5bdb',color:'#fff',border:'none',borderRadius:8,cursor:'pointer',fontSize:14}}>
          <ArrowLeft size={16} /> Volver al dashboard
        </button>
      </div>
    </div>
  );
}
