import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { getOdsColor } from '../../utils/formatters';
import './DashboardPage.css';

const DashboardPage = () => {
  const { user, logout, isAdmin, isUser } = useAuth();
  const navigate = useNavigate();
  const { 
    projects, 
    loading: projectsLoading, 
    error: projectsError, 
    fetchUserProjects, 
    fetchAdminProjects,
    fetchStatistics 
  } = useProjects();
  
  const [adminStats, setAdminStats] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (user) {
      loadDashboardData();
    }
  }, [user]);

  const loadDashboardData = async () => {
    try {
      setLoading(true);
      if (isAdmin()) {
        const allProjects = await fetchAdminProjects();
        const statsRes = await fetchStatistics();
        
        if (statsRes.success) {
          setAdminStats({
            totalProjects: allProjects.length,
            activeProjects: allProjects.filter(p => p.status === 'active' || p.status === 'activo' || p.status === 'planificacion').length,
            completedProjects: allProjects.filter(p => p.status === 'completed' || p.status === 'completado').length,
            totalUsers: statsRes.data.totalUsers || 0
          });
        }
      } else if (isUser() && user?.id) {
        await fetchUserProjects(user.id);
      } else {
        console.warn('[Dashboard] User data incomplete for fetching projects');
      }
    } catch (error) {
      console.error('Error loading dashboard data:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleLogout = async () => {
    await logout();
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('es-ES', { 
      day: 'numeric', 
      month: 'short', 
      year: 'numeric' 
    });
  };

  if (loading || projectsLoading) {
    return (
      <div className="dashboard-loading">
        <div className="loader"></div>
        <p>Sincronizando con los ODS...</p>
      </div>
    );
  }

  return (
    <div className="dashboard-page fade-in">
      <header className="dashboard-header">
        <div className="header-container">
          <div className="brand">
            <div className="logo-small">ODS</div>
            <div className="brand-text">
              <h1>Project ODS</h1>
              <p>Agenda 2030 Dashboard</p>
            </div>
          </div>
          <div className="header-actions">
            <div className="user-profile">
              <div className="avatar">{user?.name?.charAt(0)}</div>
              <div className="user-meta">
                <span className="user-name">{user?.name}</span>
                <span className={`role-tag ${user?.role}`}>{user?.role}</span>
              </div>
            </div>
            <button onClick={handleLogout} className="btn-logout" title="Cerrar Sesión">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"></path><polyline points="16 17 21 12 16 7"></polyline><line x1="21" y1="12" x2="9" y2="12"></line></svg>
            </button>
          </div>
        </div>
      </header>

      <main className="dashboard-content">
        {projectsError && (
          <div className="error-banner">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>
            <span>{projectsError}</span>
          </div>
        )}

        <section className="welcome-section">
          <div className="welcome-text">
            <h2>¡Qué bueno verte de nuevo, {user?.name?.split(' ')[0] || 'Usuario'}!</h2>
            <p>Aquí tienes un resumen del impacto generado hoy.</p>
          </div>
          {isUser() && (
            <button className="btn-create-header" onClick={() => navigate('/projects/create')}>
              <span>+ Nuevo Proyecto</span>
            </button>
          )}
        </section>

        {isAdmin() && adminStats && (
          <section className="stats-container">
            <div className="stat-card blue">
              <div className="stat-icon">📊</div>
              <div className="stat-info">
                <h3>Total Proyectos</h3>
                <p className="stat-value">{adminStats.totalProjects}</p>
              </div>
            </div>
            <div className="stat-card green">
              <div className="stat-icon">⚡</div>
              <div className="stat-info">
                <h3>Activos</h3>
                <p className="stat-value">{adminStats.activeProjects}</p>
              </div>
            </div>
            <div className="stat-card purple">
              <div className="stat-icon">✅</div>
              <div className="stat-info">
                <h3>Completados</h3>
                <p className="stat-value">{adminStats.completedProjects}</p>
              </div>
            </div>
            <div className="stat-card orange">
              <div className="stat-icon">👥</div>
              <div className="stat-info">
                <h3>Usuarios</h3>
                <p className="stat-value">{adminStats.totalUsers}</p>
              </div>
            </div>
          </section>
        )}

        <div className="dashboard-grid">
          <section className="main-panel">
            {isAdmin() ? (
              <div className="admin-actions-grid">
                <div className="action-card" onClick={() => navigate('/admin/projects')}>
                  <div className="action-icon">📂</div>
                  <h4>Gestión de Proyectos</h4>
                  <p>Supervisar y filtrar todos los proyectos activos en el sistema.</p>
                  <span className="action-link">Acceder →</span>
                </div>
                <div className="action-card disabled">
                  <div className="action-icon">📈</div>
                  <h4>Reportes de Impacto</h4>
                  <p>Próximamente: Generación de informes PDF y analítica avanzada.</p>
                </div>
              </div>
            ) : (
              <div className="user-projects-section">
                <div className="section-header">
                  <h3>Mis Proyectos Recientes</h3>
                  <button className="btn-link" onClick={() => navigate('/projects/create')}>Ver todos</button>
                </div>
                
                {projects.length > 0 ? (
                  <div className="projects-list">
                    {projects.map(project => (
                      <div key={project.id} className="project-item-card enriched">
                        <div className="project-card-badge-ods" style={{ backgroundColor: getOdsColor(project.objective) }}>
                          {project.objective}
                        </div>
                        
                        <div className="project-main-info">
                          <div className="title-row">
                            <h4>{project.name}</h4>
                            <span className={`status-pill ${project.status}`}>
                              {project.status === 'active' || project.status === 'activo' || project.status === 'planificacion' ? 'En Curso' : 'Completado'}
                            </span>
                          </div>
                          
                          <p className="project-desc-snippet">{project.description}</p>
                          
                          <div className="project-location-badges">
                            {project.provinciaNombre && (
                              <span className="location-badge province">📍 {project.provinciaNombre}</span>
                            )}
                            {project.cantonNombre && (
                              <span className="location-badge canton">{project.cantonNombre}</span>
                            )}
                            {project.distritoNombre && (
                              <span className="location-badge district">{project.distritoNombre}</span>
                            )}
                          </div>
                        </div>

                        <div className="project-meta-info">
                          <span className="label">Meta ODS {project.objective}</span>
                          <span className="indicators-count">{project.indicators.length} indicadores</span>
                          <span className="date-info">Vence: {formatDate(project.endDate)}</span>
                        </div>

                        <div className="project-button">
                            <button 
                             className="btn-action-primary"
                             onClick={() => navigate(`/projects/${project.id}/results`)}
                           >
                             {project.status === 'active' || project.status === 'activo' || project.status === 'planificacion' ? 'Medir Impacto' : 'Ver Reporte'}
                           </button>
                        </div>
                      </div>
                    ))}
                  </div>
                ) : (
                  <div className="empty-state">
                    <div className="empty-icon">🌱</div>
                    <h4>No hay proyectos activos</h4>
                    <p>Comienza a medir tu impacto creando tu primer proyecto ODS.</p>
                    <button className="btn-primary-glow" onClick={() => navigate('/projects/create')}>
                      Crear Proyecto ODS
                    </button>
                  </div>
                )}
              </div>
            )}
          </section>

          <aside className="side-panel">
            <div className="side-card info">
              <h4>¿Sabías qué?</h4>
              <p>El ODS 1 busca erradicar la pobreza en todas sus formas en todo el mundo para el año 2030.</p>
              <a href="https://www.un.org/sustainabledevelopment/es/poverty/" target="_blank" rel="noreferrer" className="learn-more">Saber más</a>
            </div>
            
            <div className="side-card profile-summary">
              <h4>Estado de Cuenta</h4>
              <div className="account-item">
                <span className="dot active"></span>
                <span>Sesión Activa</span>
              </div>
              <div className="account-item">
                <span className="icon">🛡️</span>
                <span>Acceso {user?.role === 'admin' ? 'Administrador' : 'Estándar'}</span>
              </div>
            </div>
          </aside>
        </div>
      </main>
    </div>
  );
};

export default DashboardPage;
