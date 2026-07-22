import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../../hooks/useAuth.jsx';
import { useProjects } from '../../../hooks/useProjects.jsx';
import { formatDate, getEstadoLabel, getEstadoClass, matchesProjectStatusFilter, PROJECT_STATUS_FILTER_OPTIONS } from '../../../utils/formatters';
import './OverviewPage.css';

const AdminProjectOverviewPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { 
    projects: allProjectsHook, 
    loading: projectsLoading, 
    fetchAdminProjects,
    deleteProject 
  } = useProjects();
  
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [filter, setFilter] = useState({
    user: '',
    objective: '',
    status: 'all'
  });

  const objectives = [
    { id: 1, name: 'Fin de la Pobreza' },
    { id: 2, name: 'Hambre Cero' },
    { id: 3, name: 'Salud y Bienestar' },
    { id: 4, name: 'Educación de Calidad' },
    { id: 5, name: 'Igualdad de Género' },
    { id: 6, name: 'Agua Limpia y Saneamiento' }
  ];

  useEffect(() => {
    loadProjects();
  }, []);

  useEffect(() => {
    if (allProjectsHook) {
      applyFilters();
    }
  }, [allProjectsHook, filter]);

  const loadProjects = async () => {
    try {
      setLoading(true);
      await fetchAdminProjects();
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const applyFilters = () => {
    let filtered = allProjectsHook;
    
    if (filter.user) {
      filtered = filtered.filter(project => 
        project.userName.toLowerCase().includes(filter.user.toLowerCase())
      );
    }
    
    if (filter.objective) {
      filtered = filtered.filter(project => 
        project.objective === parseInt(filter.objective)
      );
    }
    
    if (filter.status !== 'all') {
      filtered = filtered.filter(project => matchesProjectStatusFilter(project, filter.status));
    }
    
    setProjects(filtered);
  };

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilter(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleDeleteProject = async (projectId) => {
    if (window.confirm('¿Está seguro de que desea eliminar este proyecto? Esta acción no se puede deshacer.')) {
      try {
        const result = await deleteProject(projectId);
        if (!result.success) {
          setError(result.error);
        }
      } catch (err) {
        setError(err.message);
      }
    }
  };

  if (loading || projectsLoading) {
    return (
      <div className="dashboard-loading">
        <div className="loader"></div>
        <p>Cargando panel de administración...</p>
      </div>
    );
  }

  return (
    <div className="admin-projects-page fade-in">
      <header className="admin-header">
        <div className="admin-header-container">
          <div className="admin-title-area">
            <h1>Visión General de Proyectos</h1>
            <p className="admin-subtitle">Gestión centralizada de impacto ODS</p>
          </div>
          <button onClick={() => navigate('/dashboard')} className="btn-secondary">
            Volver al Dashboard
          </button>
        </div>
      </header>

      <main className="admin-content">
        {error && (
          <div className="error-banner">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>
            <span>{error}</span>
          </div>
        )}

        <section className="filters-bar">
          <div className="search-wrapper">
             <svg className="search-icon" xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
             <input
                type="text"
                name="user"
                value={filter.user}
                onChange={handleFilterChange}
                placeholder="Buscar por usuario..."
                className="search-input"
              />
          </div>
          
          <div className="filter-group">
            <select name="objective" value={filter.objective} onChange={handleFilterChange} className="filter-select">
              <option value="">Todos los ODS</option>
              {objectives.map(obj => <option key={obj.id} value={obj.id}>ODS {obj.id}</option>)}
            </select>
            
            <select name="status" value={filter.status} onChange={handleFilterChange} className="filter-select">
              {PROJECT_STATUS_FILTER_OPTIONS.map((opt) => (
                <option key={opt.value} value={opt.value}>{opt.label}</option>
              ))}
            </select>

            <button className="btn-icon-clear" onClick={() => setFilter({ user: '', objective: '', status: 'all' })} title="Limpiar Filtros">
              <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M3 6h18"></path><path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path><path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path></svg>
            </button>
          </div>
        </section>

        <section className="table-container shadow-sm">
          <table className="admin-table">
            <thead>
              <tr>
                <th>Proyecto</th>
                <th>Usuario</th>
                <th>ODS</th>
                <th>Periodo</th>
                <th>Estado</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              {projects.length > 0 ? (
                projects.map(project => (
                  <tr key={project.id}>
                    <td>
                      <div className="project-cell-name">
                        <span className="project-name-bold">{project.name}</span>
                        <span className="project-user-sm">{project.indicators?.length || 0} indicadores activos</span>
                      </div>
                    </td>
                    <td>{project.userName}</td>
                    <td><span className="ods-tag-table">ODS {project.objective}</span></td>
                    <td>
                      <div className="date-range-sm">
                        <span>{formatDate(project.startDate)}</span>
                        <span className="separator">→</span>
                        <span>{formatDate(project.endDate)}</span>
                      </div>
                    </td>
                    <td>
                      <span className={`status-badge-table ${getEstadoClass(project.status)}`}>
                        {getEstadoLabel(project.status)}
                      </span>
                    </td>
                    <td className="actions-cell">
                      <button 
                        className="btn-icon view" 
                        onClick={() => navigate(`/projects/${project.id}/results`)}
                        title="Ver Resultados"
                      >
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"></path><circle cx="12" cy="12" r="3"></circle></svg>
                      </button>
                      <button 
                        className="btn-icon delete" 
                        onClick={() => handleDeleteProject(project.id)}
                        title="Eliminar Proyecto"
                      >
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M3 6h18"></path><path d="M19 6v14c0 1-1 2-2 2H7c-1 0-2-1-2-2V6"></path><path d="M8 6V4c0-1 1-2 2-2h4c1 0 2 1 2 2v2"></path></svg>
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="6" className="loading-state-table">
                    No se encontraron proyectos activos.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </section>
      </main>
    </div>
  );
};

export default AdminProjectOverviewPage;
