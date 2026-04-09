import React from 'react';
import { useParams, Link } from 'react-router-dom';
import { LayoutGrid, Download, ArrowLeft, FileText, MapPin, Target, Calendar, CheckCircle2, Building } from 'lucide-react';
import { useProjects } from '../context/ProjectContext';

const ProjectDetail = () => {
  const { id } = useParams();
  const { projects } = useProjects();
  
  const project = projects.find(p => p.id === parseInt(id) || p.id === id);

  if (!project) {
    return (
      <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', minHeight: '60vh', gap: '1.5rem', textAlign: 'center' }}>
        <h2 style={{ fontSize: '1.5rem', fontWeight: '800', color: '#0F172A' }}>Proyecto no encontrado</h2>
        <Link to="/Proyectos" style={{ textDecoration: 'none', backgroundColor: '#2563EB', color: 'white', padding: '0.75rem 1.5rem', borderRadius: '0.75rem', fontWeight: '600' }}>Volver al listado</Link>
      </div>
    );
  }

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#F8FAFC' }}>
      {/* Header */}
      <header className="navbar" style={{ backgroundColor: 'white', borderBottom: '1px solid #E2E8F0', padding: '1rem 0' }}>
        <div className="container flex justify-between items-center" style={{ maxWidth: '1200px', margin: '0 auto', padding: '0 1.5rem', width: '100%' }}>
          <div className="flex items-center gap-4">
            <Link to="/Proyectos" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '40px', height: '40px', backgroundColor: 'white', border: '1px solid #E2E8F0', borderRadius: '0.75rem', color: '#64748B', transition: 'all 0.2s' }}>
              <ArrowLeft size={20} />
            </Link>
            <div className="flex items-center gap-3">
              <div style={{ backgroundColor: '#2563EB', padding: '0.625rem', borderRadius: '0.75rem', color: 'white', boxShadow: '0 4px 6px -1px rgba(37, 99, 235, 0.2)' }}>
                <Building size={20} />
              </div>
              <div>
                <h1 style={{ fontSize: '1.25rem', fontWeight: '800', color: '#0F172A', letterSpacing: '-0.025em' }}>Detalle del Proyecto</h1>
                <p style={{ fontSize: '0.75rem', color: '#64748B' }}>Reporte completo del proyecto</p>
              </div>
            </div>
          </div>
          <button style={{ height: '2.5rem', padding: '0 1.25rem', borderRadius: '0.75rem', backgroundColor: '#10B981', color: 'white', display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.8125rem', fontWeight: '600', border: 'none', boxShadow: '0 4px 6px -1px rgba(16, 185, 129, 0.2)' }}>
            <Download size={16} />
            Exportar a CSV
          </button>
        </div>
      </header>

      <main className="container" style={{ maxWidth: '1200px', margin: '0 auto', padding: '2.5rem 1.5rem' }}>
        <div style={{ display: 'grid', gridTemplateColumns: '1.5fr 1fr', gap: '1.5rem', marginBottom: '1.5rem' }}>
          {/* Card 1: Info General */}
          <div style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', padding: '2rem', boxShadow: '0 1px 3px rgba(0,0,0,0.05)' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '2rem' }}>
              <div style={{ color: '#2563EB' }}><FileText size={20} /></div>
              <h2 style={{ fontSize: '1.125rem', fontWeight: '800', color: '#0F172A' }}>Información General</h2>
            </div>
            
            <div style={{ display: 'flex', flexDirection: 'column', gap: '2rem' }}>
              <div>
                <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '0.75rem' }}>Nombre del Proyecto</label>
                <h3 style={{ fontSize: '1.5rem', fontWeight: '800', color: '#1E3A8A', lineHeight: '1.2' }}>{project.title}</h3>
              </div>

              <div>
                <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '0.75rem' }}>
                  <Target size={14} /> Objetivo General
                </label>
                <div style={{ padding: '1.25rem', backgroundColor: '#F8FAFC', borderRadius: '1rem', border: '1px solid #F1F5F9', color: '#334155', fontSize: '0.875rem', lineHeight: '1.6' }}>
                  {project.objective || project.description}
                </div>
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '2rem' }}>
                <div>
                  <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '0.75rem' }}>
                    <Calendar size={14} /> Fecha de Inicio
                  </label>
                  <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                    <div style={{ width: '8px', height: '8px', borderRadius: '50%', backgroundColor: '#10B981' }}></div>
                    <span style={{ fontSize: '1.125rem', fontWeight: '800', color: '#0F172A' }}>{project.start_date}</span>
                  </div>
                </div>
                <div>
                  <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '0.75rem' }}>
                    <Calendar size={14} /> Fecha de Finalización
                  </label>
                  <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                    <div style={{ width: '8px', height: '8px', borderRadius: '50%', backgroundColor: '#EF4444' }}></div>
                    <span style={{ fontSize: '1.125rem', fontWeight: '800', color: '#0F172A' }}>{project.end_date}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Card 2: Ubicación */}
          <div style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', padding: '2rem', boxShadow: '0 1px 3px rgba(0,0,0,0.05)' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '2rem' }}>
              <div style={{ color: '#10B981' }}><MapPin size={20} /></div>
              <h2 style={{ fontSize: '1.125rem', fontWeight: '800', color: '#0F172A' }}>Ubicación del Proyecto</h2>
            </div>

            <div style={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
              <div>
                <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '1rem' }}>Provincia</label>
                <div style={{ backgroundColor: '#EFF6FF', color: '#1E40AF', padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #DBEAFE', fontSize: '0.875rem', fontWeight: '700', textAlign: 'center' }}>
                  {project.location_province || 'No especificada'}
                </div>
              </div>
              <div>
                <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '1rem' }}>Cantón</label>
                <div style={{ backgroundColor: '#F0FDF4', color: '#166534', padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #DCFCE7', fontSize: '0.875rem', fontWeight: '700', textAlign: 'center' }}>
                  {project.location_canton || 'No especificado'}
                </div>
              </div>
              <div>
                <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', letterSpacing: '0.05em', marginBottom: '1rem' }}>Distrito / Ciudad</label>
                <div style={{ backgroundColor: '#FAF5FF', color: '#6B21A8', padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #F3E8FF', fontSize: '0.875rem', fontWeight: '700', textAlign: 'center' }}>
                  {project.location_district || project.location_city || 'No especificado'}
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Card 3: ODS Indicators */}
        <div style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', padding: '2rem', boxShadow: '0 1px 3px rgba(0,0,0,0.05)' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '2rem' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
              <div style={{ color: '#F59E0B' }}><CheckCircle2 size={20} /></div>
              <h2 style={{ fontSize: '1.125rem', fontWeight: '800', color: '#0F172A' }}>Objetivos de Desarrollo Sostenible</h2>
            </div>
            <div style={{ backgroundColor: '#FEF3C7', padding: '0.375rem 0.875rem', borderRadius: '9999px', fontSize: '0.75rem', fontWeight: '700', color: '#92400E' }}>
              {(project.ods || []).length} ODS Vincualdos
            </div>
          </div>

          <div style={{ display: 'grid', gridTemplateColumns: '1fr', gap: '1.5rem' }}>
            {(project.ods || []).map(item => {
              const odsItem = typeof item === 'object' ? item : { id: item, name: `Objetivo ${item}`, color: '#E5243B' };
              const indicators = Object.entries(project.indicatorConfigs || {}).filter(([name]) => name.startsWith(`${odsItem.id}.`));
              
              return (
                <div key={odsItem.id} style={{ border: '1px solid #F1F5F9', borderRadius: '1.25rem', padding: '1.5rem', backgroundColor: '#FFFFFF' }}>
                  <div style={{ display: 'flex', alignItems: 'center', gap: '1rem', marginBottom: '1.5rem' }}>
                    <div style={{ backgroundColor: odsItem.color, color: 'white', width: '40px', height: '40px', borderRadius: '8px', display: 'flex', alignItems: 'center', justifyContent: 'center', fontWeight: '900', fontSize: '1.125rem' }}>
                      {odsItem.id}
                    </div>
                    <span style={{ fontSize: '1.125rem', fontWeight: '800', color: '#0F172A' }}>{odsItem.name}</span>
                  </div>

                  <div style={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
                    {indicators.length > 0 ? indicators.map(([indName, config]) => (
                      <div key={indName} style={{ padding: '1rem', borderLeft: `4px solid ${odsItem.color}`, backgroundColor: '#F8FAFC', borderRadius: '0 0.75rem 0.75rem 0' }}>
                        <p style={{ fontSize: '0.75rem', fontWeight: '800', color: odsItem.color, letterSpacing: '0.05em', textTransform: 'uppercase', marginBottom: '0.5rem' }}>Indicador vinculado</p>
                        <p style={{ fontSize: '0.9375rem', fontWeight: '700', color: '#0F172A' }}>Indicador {indName}</p>
                        
                        <div style={{ marginTop: '1rem', display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem', marginTop: '0.5rem' }}>
                            <div style={{ backgroundColor: 'white', padding: '0.75rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0' }}>
                              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', marginBottom: '0.375rem' }}>Fórmula de Cálculo</p>
                              <p style={{ fontSize: '0.8125rem', fontWeight: '600', color: '#2563EB', fontFamily: 'monospace' }}>{config.formula}</p>
                            </div>
                            <div style={{ backgroundColor: 'white', padding: '0.75rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0' }}>
                              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', marginBottom: '0.375rem' }}>Meta Esperada</p>
                              <p style={{ fontSize: '0.8125rem', fontWeight: '700', color: '#16A34A' }}>{config.goal.value} {config.goal.unit === 'Percentage' ? '%' : ''} - {config.goal.name}</p>
                            </div>
                          </div>
                          <div style={{ padding: '0.75rem', backgroundColor: 'white', borderRadius: '0.75rem', border: '1px solid #E2E8F0' }}>
                             <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', marginBottom: '0.5rem' }}>Parámetros Requeridos</p>
                             <div style={{ display: 'flex', gap: '0.5rem', flexWrap: 'wrap' }}>
                               {config.parameters.map(p => (
                                 <span key={p.name} style={{ fontSize: '0.7rem', padding: '0.25rem 0.5rem', backgroundColor: '#F1F5F9', borderRadius: '4px', color: '#475569', fontWeight: '600' }}>{p.name} ({p.type})</span>
                               ))}
                             </div>
                          </div>
                        </div>
                      </div>
                    )) : (
                      <p style={{ fontSize: '0.875rem', color: '#94A3B8', fontStyle: 'italic' }}>No se han configurado indicadores específicos para este proyecto.</p>
                    )}
                  </div>
                </div>
              );
            })}
          </div>
        </div>

        <div style={{ display: 'flex', justifyContent: 'center', marginTop: '2.5rem' }}>
          <Link to="/Proyectos" style={{ textDecoration: 'none', backgroundColor: '#2563EB', color: 'white', padding: '0.875rem 2.5rem', borderRadius: '1rem', fontWeight: '700', fontSize: '0.9375rem', boxShadow: '0 10px 15px -3px rgba(37, 99, 235, 0.2)' }}>
            Volver a Proyectos
          </Link>
        </div>
      </main>
    </div>
  );
};

export default ProjectDetail;
