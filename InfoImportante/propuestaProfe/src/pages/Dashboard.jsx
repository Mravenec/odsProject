import React, { useState } from 'react'
import { Plus, Building, Layers, Eye, Edit3, Trash2, TrendingUp, MapPin, Calendar, Folder, Search, Filter, ChevronRight, Share2, Download } from 'lucide-react'
import { Link } from 'react-router-dom';
import NewProjectModal from '../components/NewProjectModal';
import { useProjects } from '../context/ProjectContext';

// Layout Component
const Layout = ({ children, onNewProject, isModalOpen, setIsModalOpen }) => {
  return (
    <div className="flex flex-col min-h-screen">
      <header className="navbar">
        <div className="container flex justify-between items-center" style={{ width: '100%', maxWidth: '1200px', margin: '0 auto' }}>
          <div className="flex items-center gap-4">
            <Link to="/Proyectos" style={{ textDecoration: 'none', display: 'flex', alignItems: 'center', gap: '1rem' }}>
              <div style={{ backgroundColor: '#2563EB', padding: '0.75rem', borderRadius: '0.75rem', color: 'white', boxShadow: '0 4px 6px -1px rgba(37, 99, 235, 0.2)' }}>
                <Building size={24} />
              </div>
              <div>
                <h1 style={{ fontSize: '1.5rem', fontWeight: '700', color: '#0F172A', letterSpacing: '-0.025em' }}>Gestión de Proyectos</h1>
                <p style={{ fontSize: '0.875rem', color: '#64748B', fontWeight: '400' }}>Universidad Técnica Nacional</p>
              </div>
            </Link>
          </div>
          <div className="flex items-center gap-3">
            <Link to="/Configuracion" className="btn-secondary" style={{ height: '2.75rem', padding: '0 1.25rem', borderRadius: '0.75rem', textDecoration: 'none', display: 'flex', alignItems: 'center', gap: '0.5rem', fontWeight: '500', fontSize: '0.875rem' }}>
              <Layers size={18} />
              Configuración
            </Link>
            <button className="btn-primary" onClick={onNewProject} style={{ height: '2.75rem', padding: '0 1.5rem', borderRadius: '0.75rem', backgroundColor: '#2563EB', color: 'white', display: 'flex', alignItems: 'center', gap: '0.5rem', fontWeight: '500', fontSize: '0.875rem', boxShadow: '0 4px 6px -1px rgba(37, 99, 235, 0.2)' }}>
              <Plus size={18} />
              Nuevo Proyecto
            </button>
          </div>
        </div>
      </header>
      <NewProjectModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} />
      <main className="container" style={{ padding: '2.5rem 1.5rem', flex: '1' }}>
        {children}
      </main>
    </div>
  )
}

// Project Card Component
const ProjectCard = ({ project, onDelete }) => {
  return (
    <div className="project-card flex flex-col gap-4" style={{ position: 'relative', padding: '1.5rem', borderRadius: '1rem', backgroundColor: 'white', border: '1px solid #E2E8F0', boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -2px rgba(0, 0, 0, 0.1)' }}>
      <div className="flex justify-between items-start">
        <div style={{ flex: '1' }}>
          <h3 style={{ fontSize: '1.25rem', fontWeight: '700', color: '#1E3A8A', marginBottom: '0.25rem' }}>{project.title}</h3>
          <p style={{ fontSize: '0.875rem', color: '#64748B', maxWidth: '800px' }}>{project.description}</p>
        </div>
        
        <div className="flex gap-2 shrink-0">
          <button 
            onClick={() => onDelete(project.id)}
            style={{ color: '#EF4444', backgroundColor: 'transparent', padding: '0.5rem', borderRadius: '0.5rem', border: '1px solid #FEE2E2', transition: 'all 0.2s' }}
            title="Eliminar"
          >
            <Trash2 size={16} />
          </button>
          <button style={{ backgroundColor: 'transparent', border: '1px solid #E2E8F0', color: '#64748B', padding: '0 0.875rem', borderRadius: '0.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.8125rem', fontWeight: '500', height: '2.25rem' }}>
            <Edit3 size={16} />
            Editar
          </button>
          <Link 
            to={`/Proyectos/Detalleproyecto/${project.id}`} 
            style={{ textDecoration: 'none', backgroundColor: '#2563EB', color: 'white', padding: '0 0.875rem', borderRadius: '0.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.8125rem', fontWeight: '500', height: '2.25rem', boxShadow: '0 1px 2px rgba(0,0,0,0.05)' }}
          >
            <Eye size={16} />
            Ver Detalle
          </Link>
          <Link 
            to={`/EvaluacionProyecto/${project.id}`} 
            style={{ textDecoration: 'none', backgroundColor: '#9333EA', color: 'white', padding: '0 0.875rem', borderRadius: '0.5rem', display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.8125rem', fontWeight: '500', height: '2.25rem', boxShadow: '0 1px 2px rgba(0,0,0,0.05)' }}
          >
            <TrendingUp size={16} />
            Evaluar
          </Link>
        </div>
      </div>
      
      <div className="flex flex-wrap gap-2 mt-2">
        <span style={{ display: 'flex', alignItems: 'center', gap: '0.375rem', backgroundColor: '#F8FAFC', color: '#475569', fontSize: '0.75rem', fontWeight: '500', padding: '0.25rem 0.625rem', borderRadius: '0.375rem', border: '1px solid #E2E8F0' }}>
          <MapPin size={12} />
          {project.location_province}, {project.location_canton}, {project.location_city}
        </span>
      </div>
      
      <div style={{ marginTop: '1rem', paddingTop: '1rem', borderTop: '1px solid #F1F5F9', display: 'flex', justifyContent: 'space-between', alignItems: 'flex-end' }}>
        <div>
          <p style={{ fontSize: '0.75rem', color: '#64748B', fontWeight: '500', marginBottom: '0.5rem' }}>ODS Relacionados:</p>
          <div className="flex gap-1.5">
            {(project.ods || []).map(num => {
              const odsNum = typeof num === 'object' ? num.id : num;
              const odsColor = num.color || (odsNum === 8 ? '#A21942' : '#FF3A21');
              return (
                <div 
                  key={odsNum} 
                  title={`ODS ${odsNum}`}
                  style={{ 
                    backgroundColor: odsColor, 
                    width: '24px', 
                    height: '24px', 
                    display: 'flex', 
                    alignItems: 'center', 
                    justifyContent: 'center', 
                    borderRadius: '4px', 
                    color: 'white', 
                    fontSize: '0.75rem', 
                    fontWeight: '700' 
                  }}
                >
                  {odsNum}
                </div>
              );
            })}
          </div>
        </div>
        
        <div className="flex items-center gap-1.5" style={{ color: '#64748B', fontSize: '0.75rem', fontWeight: '500' }}>
          <Calendar size={14} />
          <span>Fin: {project.end_date}</span>
        </div>
      </div>
    </div>
  )
}

function Dashboard() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { projects, deleteProject } = useProjects();

  return (
    <Layout 
      onNewProject={() => setIsModalOpen(true)} 
      isModalOpen={isModalOpen} 
      setIsModalOpen={setIsModalOpen}
    >
      <div className="flex justify-between items-center" style={{ marginBottom: '2.5rem' }}>
        <h2 style={{ fontSize: '1.5rem', fontWeight: '700', color: '#1E293B' }}>Proyectos Registrados</h2>
        <span className="badge badge-tag" style={{ fontSize: '0.875rem', padding: '0.375rem 1rem' }}>
          {projects.length} proyecto{projects.length !== 1 ? 's' : ''}
        </span>
      </div>

      <div className="flex flex-col gap-6">
        {projects.length > 0 ? (
          projects.map(p => (
            <ProjectCard key={p.id} project={p} onDelete={deleteProject} />
          ))
        ) : (
          <div style={{ 
            backgroundColor: 'white', 
            borderRadius: '1rem', 
            border: '2px dashed #E2E8F0', 
            padding: '4rem 2rem', 
            display: 'flex', 
            flexDirection: 'column', 
            alignItems: 'center', 
            justifyContent: 'center', 
            textAlign: 'center' 
          }}>
            <div style={{ backgroundColor: '#F8FAFC', padding: '1.25rem', borderRadius: '50%', marginBottom: '1.5rem' }}>
              <Folder size={48} style={{ color: '#94A3B8' }} />
            </div>
            <h3 style={{ fontSize: '1.25rem', fontWeight: '600', color: '#0F172A', marginBottom: '0.5rem' }}>No hay proyectos registrados</h3>
            <p style={{ fontSize: '0.875rem', color: '#64748B', marginBottom: '2rem', maxWidth: '400px' }}>Comience creando su primer proyecto para realizar el seguimiento de los objetivos de desarrollo sostenible.</p>
            <button 
              onClick={() => setIsModalOpen(true)}
              style={{ 
                backgroundColor: '#2563EB', 
                color: 'white', 
                padding: '0.75rem 1.5rem', 
                borderRadius: '0.75rem', 
                fontWeight: '600', 
                display: 'flex', 
                alignItems: 'center', 
                gap: '0.5rem',
                border: 'none',
                cursor: 'pointer',
                boxShadow: '0 4px 6px -1px rgba(37, 99, 235, 0.2)'
              }}
            >
              <Plus size={18} />
              Crear Proyecto
            </button>
          </div>
        )}
      </div>
    </Layout>
  )
}

export default Dashboard
