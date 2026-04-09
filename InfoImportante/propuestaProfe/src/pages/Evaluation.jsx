import React, { useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { ArrowLeft, TrendingUp, Calendar, MapPin, Calculator, CheckCircle2, ChevronDown, ChevronUp, FileText, Building } from 'lucide-react';
import { useProjects } from '../context/ProjectContext';

const Evaluation = () => {
  const { id } = useParams();
  const { projects, areas } = useProjects();
  const [activeTab, setActiveTab] = useState('ingreso');
  const [openSelectors, setOpenSelectors] = useState({});

  const toggleSection = (odsId) => {
    setOpenSelectors(prev => ({ ...prev, [odsId]: !prev[odsId] }));
  };

  // If no ID is provided, show the project selection list
  if (!id) {
    return (
      <div style={{ minHeight: '100vh', backgroundColor: '#F8FAFC' }}>
        <header className="navbar" style={{ backgroundColor: 'white', borderBottom: '1px solid #E2E8F0', padding: '1rem 0' }}>
          <div className="container flex justify-between items-center" style={{ maxWidth: '1200px', margin: '0 auto', padding: '0 1.5rem', width: '100%' }}>
            <div className="flex items-center gap-4">
              <Link to="/Proyectos" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '40px', height: '40px', backgroundColor: 'white', border: '1px solid #E2E8F0', borderRadius: '0.75rem', color: '#64748B' }}>
                <ArrowLeft size={20} />
              </Link>
              <div className="flex items-center gap-3">
                <div style={{ backgroundColor: '#9333EA', padding: '0.625rem', borderRadius: '0.75rem', color: 'white' }}>
                  <TrendingUp size={20} />
                </div>
                <div>
                  <h1 style={{ fontSize: '1.25rem', fontWeight: '800', color: '#0F172A', letterSpacing: '-0.025em' }}>Evaluación del Proyecto</h1>
                  <p style={{ fontSize: '0.75rem', color: '#64748B' }}>Seleccione un proyecto para comenzar la evaluación</p>
                </div>
              </div>
            </div>
          </div>
        </header>

        <main className="container" style={{ maxWidth: '1200px', margin: '0 auto', padding: '2.5rem 1.5rem' }}>
          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(350px, 1fr))', gap: '1.5rem' }}>
            {projects.map(project => (
              <Link 
                key={project.id} 
                to={`/EvaluacionProyecto/${project.id}`}
                style={{ textDecoration: 'none', backgroundColor: 'white', padding: '1.5rem', borderRadius: '1.25rem', border: '1px solid #E2E8F0', display: 'flex', justifyContent: 'space-between', alignItems: 'center', transition: 'all 0.2s', boxShadow: '0 1px 2px rgba(0,0,0,0.05)' }}
                onMouseEnter={(e) => e.currentTarget.style.borderColor = '#9333EA'}
                onMouseLeave={(e) => e.currentTarget.style.borderColor = '#E2E8F0'}
              >
                <div>
                  <h3 style={{ fontSize: '1rem', fontWeight: '700', color: '#0F172A', marginBottom: '0.25rem' }}>{project.title}</h3>
                  <p style={{ fontSize: '0.75rem', color: '#64748B' }}>{project.location_canton}, {project.location_province}</p>
                </div>
                <div style={{ backgroundColor: '#F3E8FF', color: '#7E22CE', padding: '0.375rem 0.75rem', borderRadius: '9999px', fontSize: '0.75rem', fontWeight: '700' }}>
                  {project.ods?.length || 0} ODS
                </div>
              </Link>
            ))}
          </div>
        </main>
      </div>
    );
  }

  const project = projects.find(p => p.id === parseInt(id) || p.id === id);

  if (!project) {
    return (
      <div style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', gap: '1rem' }}>
        <h2 style={{ color: '#0F172A' }}>Proyecto no encontrado</h2>
        <Link to="/EvaluacionProyecto" style={{ color: '#9333EA', fontWeight: '600' }}>Volver al listado</Link>
      </div>
    );
  }

  const [evaluationData, setEvaluationData] = useState({}); // indicatorName -> { params: {}, result: null, status: null }

  const handleParamChange = (indName, paramName, value) => {
    setEvaluationData(prev => ({
      ...prev,
      [indName]: {
        ...prev[indName],
        params: { ...(prev[indName]?.params || {}), [paramName]: value }
      }
    }));
  };

  const calculateEvaluation = (indName, config) => {
    try {
      let formulaStr = config.formula;
      const params = evaluationData[indName]?.params || {};
      
      // Replace parameter names with values in the formula
      config.parameters.forEach(p => {
        const val = params[p.name] || 0;
        formulaStr = formulaStr.replace(new RegExp(`\\b${p.name}\\b`, 'g'), val);
      });

      // Simple security check / basic eval
      // In a real app, use a proper math parser
      const result = eval(formulaStr); 
      const goalValue = parseFloat(config.goal.value);
      const isSuccess = result >= goalValue;

      setEvaluationData(prev => ({
        ...prev,
        [indName]: {
          ...prev[indName],
          result: result.toFixed(2),
          status: isSuccess ? 'Cumplido' : 'En Progreso'
        }
      }));
    } catch (e) {
      alert("Error al calcular la fórmula. Verifique los valores ingresados.");
    }
  };

  const projectArea = areas.find(a => a.id === parseInt(project.areaId))?.name || 'Sede Central';

  return (
    <div style={{ minHeight: '100vh', backgroundColor: '#F8FAFC' }}>
      {/* Header */}
      <header className="navbar" style={{ backgroundColor: 'white', borderBottom: '1px solid #E2E8F0', padding: '1rem 0' }}>
        <div className="container flex justify-between items-center" style={{ maxWidth: '1200px', margin: '0 auto', padding: '0 1.5rem', width: '100%' }}>
          <div className="flex items-center gap-4">
            <Link to="/EvaluacionProyecto" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '40px', height: '40px', backgroundColor: 'white', border: '1px solid #E2E8F0', borderRadius: '0.75rem', color: '#64748B' }}>
              <ArrowLeft size={20} />
            </Link>
            <div className="flex items-center gap-3">
              <div style={{ backgroundColor: '#9333EA', padding: '0.625rem', borderRadius: '0.75rem', color: 'white' }}>
                <TrendingUp size={20} />
              </div>
              <div>
                <h1 style={{ fontSize: '1.25rem', fontWeight: '800', color: '#0F172A', letterSpacing: '-0.025em' }}>Evaluación del Proyecto</h1>
                <p style={{ fontSize: '0.75rem', color: '#64748B' }}>{project.title}</p>
              </div>
            </div>
          </div>
        </div>
      </header>

      <main className="container" style={{ maxWidth: '1200px', margin: '0 auto', padding: '2rem 1.5rem' }}>
        {/* Context Card ... */}
        <div style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', padding: '1.25rem 2rem', marginBottom: '1.5rem', display: 'flex', alignItems: 'center', gap: '3rem', boxShadow: '0 1px 3px rgba(0,0,0,0.05)' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
            <Building size={18} style={{ color: '#64748B' }} />
            <div>
              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', marginBottom: '0.125rem' }}>ÁREA DE LA UTN</p>
              <p style={{ fontSize: '0.8125rem', fontWeight: '700', color: '#334155' }}>{projectArea}</p>
            </div>
          </div>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
            <MapPin size={18} style={{ color: '#64748B' }} />
            <div>
              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', marginBottom: '0.125rem' }}>UBICACIÓN</p>
              <p style={{ fontSize: '0.8125rem', fontWeight: '700', color: '#334155' }}>{project.location_canton}, {project.location_province}</p>
            </div>
          </div>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
            <Calendar size={18} style={{ color: '#64748B' }} />
            <div>
              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#94A3B8', textTransform: 'uppercase', marginBottom: '0.125rem' }}>PERIODO</p>
              <p style={{ fontSize: '0.8125rem', fontWeight: '700', color: '#334155' }}>{project.start_date} → {project.end_date}</p>
            </div>
          </div>
        </div>

        {/* Tabs Control */}
        <div style={{ backgroundColor: '#F1F5F9', padding: '0.375rem', borderRadius: '1rem', width: 'fit-content', display: 'flex', gap: '0.375rem', marginBottom: '2rem' }}>
          <button onClick={() => setActiveTab('ingreso')} style={{ padding: '0.625rem 1.5rem', borderRadius: '0.75rem', border: 'none', cursor: 'pointer', backgroundColor: activeTab === 'ingreso' ? '#9333EA' : 'transparent', color: activeTab === 'ingreso' ? 'white' : '#64748B', fontSize: '0.875rem', fontWeight: '700', display: 'flex', alignItems: 'center', gap: '0.5rem', transition: 'all 0.2s' }}>
            <Calculator size={16} /> Ingreso de datos
          </button>
          <button onClick={() => setActiveTab('resumen')} style={{ padding: '0.625rem 1.5rem', borderRadius: '0.75rem', border: 'none', cursor: 'pointer', backgroundColor: activeTab === 'resumen' ? '#9333EA' : 'transparent', color: activeTab === 'resumen' ? 'white' : '#64748B', fontSize: '0.875rem', fontWeight: '700', display: 'flex', alignItems: 'center', gap: '0.5rem', transition: 'all 0.2s' }}>
            <TrendingUp size={16} /> Resumen
          </button>
        </div>

        {/* Content */}
        {activeTab === 'ingreso' ? (
          <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
            {(project.ods || []).map(odsItem => {
              const isOpen = openSelectors[odsItem.id];
              const indicators = Object.entries(project.indicatorConfigs || {}).filter(([name]) => name.startsWith(`${odsItem.id}.`));
              
              return (indicators.length > 0 && (
                <div key={odsItem.id} style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', overflow: 'hidden', boxShadow: '0 1px 2px rgba(0,0,0,0.05)' }}>
                  <div onClick={() => toggleSection(odsItem.id)} style={{ padding: '1.25rem 2rem', display: 'flex', justifyContent: 'space-between', alignItems: 'center', cursor: 'pointer' }}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                      <div style={{ backgroundColor: odsItem.color, color: 'white', width: '36px', height: '36px', borderRadius: '8px', display: 'flex', alignItems: 'center', justifyContent: 'center', fontWeight: '900', fontSize: '1rem' }}>
                        {odsItem.id}
                      </div>
                      <div>
                        <h3 style={{ fontSize: '1rem', fontWeight: '800', color: '#0F172A' }}>{odsItem.name}</h3>
                        <p style={{ fontSize: '0.75rem', color: '#64748B' }}>{indicators.length} indicadores configurados</p>
                      </div>
                    </div>
                    {isOpen ? <ChevronUp size={20} /> : <ChevronDown size={20} />}
                  </div>

                  {isOpen && (
                    <div style={{ padding: '2rem', borderTop: '1px solid #F1F5F9', backgroundColor: '#F8FAFC', display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
                      {indicators.map(([indName, config]) => (
                        <div key={indName} style={{ backgroundColor: 'white', borderRadius: '1rem', border: '1px solid #E2E8F0', padding: '1.5rem' }}>
                          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '1.5rem', paddingBottom: '1rem', borderBottom: '1px solid #F1F5F9' }}>
                            <FileText size={18} style={{ color: '#64748B' }} />
                            <span style={{ fontSize: '0.875rem', fontWeight: '700', color: '#0F172A' }}>Indicador {indName}</span>
                          </div>

                          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(200px, 1fr))', gap: '1.5rem', marginBottom: '2rem' }}>
                            {config.parameters.map(p => (
                              <div key={p.name}>
                                <label style={{ display: 'block', fontSize: '0.75rem', fontWeight: '700', color: '#64748B', marginBottom: '0.5rem' }}>{p.name.replace(/_/g, ' ')} <span style={{ fontWeight: '400', fontSize: '0.7rem' }}>({p.type === 'Integer' ? 'entero' : 'decimal'})</span></label>
                                <input 
                                  type="number" 
                                  placeholder="0" 
                                  value={evaluationData[indName]?.params?.[p.name] || ''}
                                  onChange={(e) => handleParamChange(indName, p.name, e.target.value)}
                                  style={{ width: '100%', padding: '0.75rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F9FAFB', fontFamily: 'monospace', fontWeight: '700' }} 
                                />
                              </div>
                            ))}
                          </div>

                          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1.5rem', marginBottom: '2rem' }}>
                            <div style={{ backgroundColor: '#F1F7FE', padding: '1rem', borderRadius: '0.75rem', border: '1px solid #DBEAFE' }}>
                              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#1E40AF', textTransform: 'uppercase', marginBottom: '0.5rem' }}>FÓRMULA APLICADA</p>
                              <p style={{ fontSize: '0.875rem', fontWeight: '600', color: '#1E3A8A', fontFamily: 'monospace' }}>{config.formula}</p>
                            </div>
                            <div style={{ backgroundColor: '#F0FDF4', padding: '1rem', borderRadius: '0.75rem', border: '1px solid #DCFCE7', position: 'relative' }}>
                              <p style={{ fontSize: '0.625rem', fontWeight: '700', color: '#166534', textTransform: 'uppercase', marginBottom: '0.5rem' }}>META ESTABLECIDA</p>
                              <p style={{ fontSize: '0.875rem', fontWeight: '800', color: '#15803D' }}>{config.goal.value} {config.goal.unit === 'Percentage' ? '%' : ''} - {config.goal.name}</p>
                              {evaluationData[indName]?.result && (
                                <div style={{ position: 'absolute', top: '10px', right: '10px', textAlign: 'right' }}>
                                   <p style={{ fontSize: '0.625rem', fontWeight: '800', color: evaluationData[indName].status === 'Cumplido' ? '#166534' : '#991B1B' }}>RESULTADO</p>
                                   <p style={{ fontSize: '1rem', fontWeight: '900', color: evaluationData[indName].status === 'Cumplido' ? '#15803D' : '#B91C1C' }}>{evaluationData[indName].result}%</p>
                                </div>
                              )}
                            </div>
                          </div>

                          <button 
                            onClick={() => calculateEvaluation(indName, config)}
                            style={{ width: '100%', padding: '1rem', borderRadius: '0.75rem', backgroundColor: '#2563EB', color: 'white', border: 'none', fontWeight: '700', fontSize: '0.875rem', display: 'flex', alignItems: 'center', justifyContent: 'center', gap: '0.75rem', cursor: 'pointer', boxShadow: '0 4px 6px -1px rgba(37, 99, 235, 0.2)' }}>
                            <Calculator size={18} /> Calcular y Evaluar
                          </button>
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              ));
            })}
          </div>
        ) : (
          <div style={{ backgroundColor: 'white', borderRadius: '1.25rem', border: '1px solid #E2E8F0', padding: '3rem', textAlign: 'center' }}>
             <TrendingUp size={48} style={{ color: '#E2E8F0', marginBottom: '1.5rem' }} />
             <h3 style={{ fontSize: '1.25rem', fontWeight: '700', color: '#0F172A', marginBottom: '0.5rem' }}>Resumen de Evaluación</h3>
             <p style={{ fontSize: '0.875rem', color: '#64748B', maxWidth: '400px', margin: '0 auto' }}>Complete el ingreso de datos en la pestaña anterior para visualizar el progreso y cumplimiento de las metas del proyecto.</p>
          </div>
        )}
      </main>
    </div>
  );
};

export default Evaluation;
