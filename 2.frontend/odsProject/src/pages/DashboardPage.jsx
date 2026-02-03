import React from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth.jsx';
import { projectService } from '../services/projectService.js';
import '../styles/DashboardPage.css';

const DashboardPage = () => {
  const { user, logout, isAdmin, isUser } = useAuth();
  const navigate = useNavigate();
  const [userProjects, setUserProjects] = React.useState([]);
  const [adminStats, setAdminStats] = React.useState(null);
  const [loading, setLoading] = React.useState(true);

  React.useEffect(() => {
    loadDashboardData();
  }, [user]);

  const loadDashboardData = async () => {
    try {
      if (isAdmin()) {
        const projects = await projectService.getAdminProjects();
        const stats = await projectService.getStatistics();
        setAdminStats({
          totalProjects: projects.length,
          activeProjects: projects.filter(p => p.status === 'active').length,
          completedProjects: projects.filter(p => p.status === 'completed').length,
          totalUsers: stats.totalUsers || 0
        });
      } else if (isUser()) {
        const projects = await projectService.getUserProjects(user.id);
        setUserProjects(projects);
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
    return new Date(dateString).toLocaleDateString('es-ES');
  };

  if (loading) {
    return (
      <div className="dashboard-container">
        <div className="loading">Cargando dashboard...</div>
      </div>
    );
  }

  return (
    <div className="dashboard-container">
      <header className="dashboard-header">
        <div className="header-content">
          <h1>Project ODS - Dashboard</h1>
          <div className="user-info">
            <span>Bienvenido, {user?.name}</span>
            <span className={`role-badge ${user?.role}`}>
              {user?.role === 'admin' ? 'Administrador' : 'Usuario'}
            </span>
            <button onClick={handleLogout} className="logout-button">
              Cerrar Sesión
            </button>
          </div>
        </div>
      </header>

      <main className="dashboard-main">
        <div className="welcome-card">
          <h2>Bienvenido al Sistema de Seguimiento ODS</h2>
          <p>Has iniciado sesión como <strong>{user?.name}</strong></p>
          <p>Email: {user?.email}</p>
          <p>ID de Usuario: {user?.id}</p>
        </div>

        {isAdmin() && (
          <div className="admin-section">
            <h3>Panel de Administrador</h3>
            
            {adminStats && (
              <div className="stats-grid">
                <div className="stat-card">
                  <h4>Total de Proyectos</h4>
                  <div className="stat-number">{adminStats.totalProjects}</div>
                </div>
                <div className="stat-card">
                  <h4>Proyectos Activos</h4>
                  <div className="stat-number">{adminStats.activeProjects}</div>
                </div>
                <div className="stat-card">
                  <h4>Proyectos Completados</h4>
                  <div className="stat-number">{adminStats.completedProjects}</div>
                </div>
                <div className="stat-card">
                  <h4>Total de Usuarios</h4>
                  <div className="stat-number">{adminStats.totalUsers}</div>
                </div>
              </div>
            )}

            <div className="admin-options">
              <div 
                className="option-card clickable"
                onClick={() => navigate('/admin/projects')}
              >
                <h4>📊 Ver Todos los Proyectos</h4>
                <p>Visualizar y gestionar todos los proyectos del sistema</p>
              </div>
              <div className="option-card">
                <h4>📈 Reportes y Estadísticas</h4>
                <p>Ver reportes detallados y estadísticas ODS</p>
              </div>
              <div className="option-card">
                <h4>⚙️ Configuración</h4>
                <p>Configuración del sistema y parámetros</p>
              </div>
            </div>
          </div>
        )}

        {isUser() && (
          <div className="user-section">
            <h3>Mi Panel de Proyectos ODS</h3>
            
            <div className="user-options">
              <div 
                className="option-card clickable"
                onClick={() => navigate('/projects/create')}
              >
                <h4>➕ Crear Nuevo Proyecto</h4>
                <p>Iniciar un nuevo proyecto de seguimiento ODS</p>
              </div>
              <div className="option-card">
                <h4>👤 Mi Perfil</h4>
                <p>Ver y editar mi información personal</p>
              </div>
              <div className="option-card">
                <h4>❓ Ayuda y Soporte</h4>
                <p>Obtener ayuda sobre el uso del sistema</p>
              </div>
            </div>

            {userProjects.length > 0 && (
              <div className="my-projects">
                <h3>Mis Proyectos Activos</h3>
                <div className="projects-grid">
                  {userProjects.map(project => (
                    <div key={project.id} className="project-card">
                      <h4>{project.name}</h4>
                      <p className="project-description">{project.description}</p>
                      <div className="project-details">
                        <p><strong>Objetivo ODS:</strong> {project.objective}</p>
                        <p><strong>Indicadores:</strong> {project.indicators.length}</p>
                        <p><strong>Estado:</strong> 
                          <span className={`status-badge ${project.status}`}>
                            {project.status === 'active' ? 'Activo' : 
                             project.status === 'completed' ? 'Completado' : 'Cancelado'}
                          </span>
                        </p>
                        <p><strong>Período:</strong> {formatDate(project.startDate)} - {formatDate(project.endDate)}</p>
                      </div>
                      <div className="project-actions">
                        {project.status === 'active' && (
                          <button 
                            className="btn-primary"
                            onClick={() => navigate(`/projects/${project.id}/results`)}
                          >
                            Ingresar Resultados
                          </button>
                        )}
                        {project.status === 'completed' && (
                          <button 
                            className="btn-secondary"
                            onClick={() => navigate(`/projects/${project.id}/results`)}
                          >
                            Ver Resultados
                          </button>
                        )}
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            )}

            {userProjects.length === 0 && (
              <div className="no-projects">
                <h3>🎯 ¡Comienza tu primer proyecto ODS!</h3>
                <p>Aún no tienes proyectos creados. Haz clic en "Crear Nuevo Proyecto" para comenzar a medir tu impacto en los Objetivos de Desarrollo Sostenible.</p>
                <button 
                  className="btn-primary"
                  onClick={() => navigate('/projects/create')}
                >
                  Crear Mi Primer Proyecto
                </button>
              </div>
            )}
          </div>
        )}
      </main>
    </div>
  );
};

export default DashboardPage;
