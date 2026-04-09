import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { LayoutGrid, Settings, Plus, X, User, Layers, ArrowLeft } from 'lucide-react';
import { useProjects } from '../context/ProjectContext';

const Configuracion = () => {
  const { areas, personnel, addArea, deleteArea, addPersonnel, deletePersonnel } = useProjects();
  const [newArea, setNewArea] = useState('');
  const [newName, setNewName] = useState('');
  const [newAreaId, setNewAreaId] = useState('');

  const handleAddArea = (e) => {
    e.preventDefault();
    if (newArea.trim()) {
      addArea(newArea.trim());
      setNewArea('');
    }
  };

  const handleAddPersonnel = (e) => {
    e.preventDefault();
    if (newName.trim()) {
      addPersonnel(newName.trim(), newAreaId);
      setNewName('');
      setNewAreaId('');
    }
  };

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#F8FAFC' }}>
      {/* Header */}
      <header className="navbar" style={{ backgroundColor: 'white', borderBottom: '1px solid #E2E8F0', padding: '1rem 0' }}>
        <div className="container flex justify-between items-center" style={{ maxWidth: '1200px', margin: '0 auto', padding: '0 1.5rem', width: '100%' }}>
          <div className="flex items-center gap-4">
            <Link to="/Proyectos" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '40px', height: '40px', backgroundColor: 'white', border: '1px solid #E2E8F0', borderRadius: '0.75rem', color: '#64748B' }}>
              <ArrowLeft size={20} />
            </Link>
            <div className="flex items-center gap-3">
              <div style={{ backgroundColor: '#0F172A', padding: '0.625rem', borderRadius: '0.75rem', color: 'white' }}>
                <Settings size={20} />
              </div>
              <div>
                <h1 style={{ fontSize: '1.25rem', fontWeight: '800', color: '#0F172A', letterSpacing: '-0.025em' }}>Configuración</h1>
                <p style={{ fontSize: '0.75rem', color: '#64748B' }}>Administre las listas de datos del sistema</p>
              </div>
            </div>
          </div>
        </div>
      </header>

      <main className="container" style={{ maxWidth: '1200px', margin: '0 auto', padding: '2.5rem 1.5rem', display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '2rem' }}>
        {/* Areas Card */}
        <section style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', overflow: 'hidden', boxShadow: '0 1px 3px rgba(0,0,0,0.05)', display: 'flex', flexDirection: 'column' }}>
          <div style={{ padding: '1.5rem 2rem', borderBottom: '1px solid #F1F5F9', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
              <Layers size={20} style={{ color: '#0F172A' }} />
              <h2 style={{ fontSize: '1rem', fontWeight: '800', color: '#0F172A' }}>Áreas de la UTN</h2>
            </div>
            <span style={{ fontSize: '0.75rem', fontWeight: '700', color: '#64748B', backgroundColor: '#F1F5F9', padding: '0.25rem 0.75rem', borderRadius: '9999px' }}>
              {areas.length} registros
            </span>
          </div>
          
          <div style={{ padding: '2rem' }}>
            <form onSubmit={handleAddArea} style={{ display: 'flex', gap: '0.75rem', marginBottom: '2rem' }}>
              <input 
                type="text" 
                placeholder="Agregar área..." 
                style={{ flex: 1, padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F8FAFC', fontSize: '0.875rem' }}
                value={newArea}
                onChange={(e) => setNewArea(e.target.value)}
              />
              <button type="submit" style={{ width: '40px', height: '40px', backgroundColor: '#2563EB', color: 'white', border: 'none', borderRadius: '0.75rem', display: 'flex', alignItems: 'center', justifyContent: 'center', cursor: 'pointer' }}>
                <Plus size={20} />
              </button>
            </form>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
              {areas.map(area => (
                <div key={area.id} className="group" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '1rem 1.25rem', borderRadius: '1rem', border: '1px solid #F1F5F9', backgroundColor: '#F8FAFC', transition: 'all 0.2s' }}>
                  <span style={{ fontSize: '0.875rem', fontWeight: '600', color: '#334155' }}>{area.name}</span>
                  <button 
                    onClick={() => deleteArea(area.id)}
                    style={{ color: '#EF4444', border: 'none', backgroundColor: 'transparent', cursor: 'pointer', padding: '0.25rem' }}
                  >
                    <X size={18} />
                  </button>
                </div>
              ))}
            </div>
          </div>
        </section>

        {/* Personnel Card */}
        <section style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', overflow: 'hidden', boxShadow: '0 1px 3px rgba(0,0,0,0.05)', display: 'flex', flexDirection: 'column' }}>
          <div style={{ padding: '1.5rem 2rem', borderBottom: '1px solid #F1F5F9', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
              <User size={20} style={{ color: '#0F172A' }} />
              <h2 style={{ fontSize: '1rem', fontWeight: '800', color: '#0F172A' }}>Personas Responsables</h2>
            </div>
            <span style={{ fontSize: '0.75rem', fontWeight: '700', color: '#64748B', backgroundColor: '#F1F5F9', padding: '0.25rem 0.75rem', borderRadius: '9999px' }}>
              {personnel.length} registros
            </span>
          </div>
          
          <div style={{ padding: '2rem' }}>
            <form onSubmit={handleAddPersonnel} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem', marginBottom: '2rem' }}>
              <div style={{ display: 'flex', gap: '0.75rem' }}>
                <input 
                  type="text" 
                  placeholder="Nombre del responsable..." 
                  style={{ flex: 1, padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F8FAFC', fontSize: '0.875rem' }}
                  value={newName}
                  onChange={(e) => setNewName(e.target.value)}
                />
                <button type="submit" style={{ width: '40px', height: '40px', backgroundColor: '#2563EB', color: 'white', border: 'none', borderRadius: '0.75rem', display: 'flex', alignItems: 'center', justifyContent: 'center', cursor: 'pointer' }}>
                  <Plus size={20} />
                </button>
              </div>
              <select 
                style={{ width: '100%', padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F8FAFC', fontSize: '0.875rem', appearance: 'none' }}
                value={newAreaId}
                onChange={(e) => setNewAreaId(e.target.value)}
              >
                <option value="">Asignar a un área (opcional)</option>
                {areas.map(area => (
                  <option key={area.id} value={area.id}>{area.name}</option>
                ))}
              </select>
            </form>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
              {personnel.map(person => (
                <div key={person.id} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '1rem 1.25rem', borderRadius: '1rem', border: '1px solid #F1F5F9', backgroundColor: '#F8FAFC', transition: 'all 0.2s' }}>
                  <div>
                    <p style={{ fontSize: '0.875rem', fontWeight: '700', color: '#0F172A', marginBottom: '0.125rem' }}>{person.name}</p>
                    <p style={{ fontSize: '0.75rem', color: '#64748B', fontWeight: '500' }}>
                      {areas.find(a => a.id === parseInt(person.areaId))?.name || 'Área no asignada'}
                    </p>
                  </div>
                  <button 
                    onClick={() => deletePersonnel(person.id)}
                    style={{ color: '#EF4444', border: 'none', backgroundColor: 'transparent', cursor: 'pointer', padding: '0.25rem' }}
                  >
                    <X size={18} />
                  </button>
                </div>
              ))}
            </div>
          </div>
        </section>
      </main>
    </div>
  );
};

export default Configuracion;
