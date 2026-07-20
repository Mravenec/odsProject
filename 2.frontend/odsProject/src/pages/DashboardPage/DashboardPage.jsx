import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { getOdsColor, getEstadoLabel, getEstadoClass, isProjectCompletado } from '../../utils/formatters';
import BulkProjectExportPanel from '../../components/projects/BulkProjectExportPanel';
import UtnMark from '../../components/UtnMark';
import './DashboardPage.css';

const DashboardPage = () => {
  const { user, logout, isAdmin, isGestor } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();
  const location = useLocation();
  const { 
    projects, 
    globalDashboard,
    loading: projectsLoading, 
    error: projectsError, 
    fetchUserProjects, 
    fetchAdminProjects,
    fetchGlobalDashboard 
  } = useProjects();
  
  const [loading, setLoading] = useState(true);
  const [loginAt] = useState(() => new Date());

  useEffect(() => {
    if (user) {
      loadDashboardData();
    }
  }, [user, location.pathname, perms.canViewGlobalDashboard]);

  const loadDashboardData = async () => {
    try {
      setLoading(true);
      // Sprint 10/14: admin/auditor/consultor → vista global; gestor → solo suyos
      if (perms.canViewGlobalDashboard) {
        await fetchAdminProjects();
        if (perms.canViewAdminPanel) await fetchGlobalDashboard();
      } else if (user?.id) {
        await fetchUserProjects(user.id);
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
    if (!dateString) return 'Sin fecha';
    try {
      return new Date(dateString).toLocaleDateString('es-ES', { 
        day: 'numeric', 
        month: 'short', 
        year: 'numeric' 
      });
    } catch (e) {
      return 'Fecha inválida';
    }
  };

  const displayFullName =
    user?.fullName || user?.name || user?.username || 'Usuario';

  const formatLoginDateTime = (date) => {
    try {
      return date.toLocaleString('es-CR', {
        weekday: 'long',
        day: 'numeric',
        month: 'long',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
      });
    } catch (e) {
      return date.toLocaleString();
    }
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
          <UtnMark compact subtitle="Plataforma ODS · Agenda 2030" />
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
            <h2>
              {displayFullName}, usted ha ingresado exitosamente a la plataforma ODS Agenda 2030
            </h2>
            <p className="welcome-login-at">
              Fecha y hora de ingreso: {formatLoginDateTime(loginAt)}
            </p>
          </div>
          {perms.canCreateProject && (
            <button className="btn-create-header" onClick={() => navigate('/projects/create')}>
              <span>+ Nuevo Proyecto</span>
            </button>
          )}
          {perms.canViewAuditQueue && (
            <button className="btn-create-header btn-audit"
              onClick={() => navigate('/evaluacion')}>
              <span>📋 Cola de evaluación</span>
            </button>
          )}
        </section>

        {perms.canExportBulkProjects && (
          <div className="dashboard-export-wrap">
            <BulkProjectExportPanel className="bulk-export-section--dashboard" />
          </div>
        )}

        {isAdmin() && globalDashboard && (
          <section className="stats-container">
            <div className="stat-card blue">
              <div className="stat-icon">📊</div>
              <div className="stat-info">
                <h3>Total Proyectos</h3>
                <p className="stat-value">{globalDashboard.totalProyectos || 0}</p>
              </div>
            </div>
            <div className="stat-card green">
              <div className="stat-icon">⚡</div>
              <div className="stat-info">
                <h3>Activos</h3>
                <p className="stat-value">{globalDashboard.proyectosActivos || 0}</p>
              </div>
            </div>
            <div className="stat-card purple">
              <div className="stat-icon">✅</div>
              <div className="stat-info">
                <h3>Completados</h3>
                <p className="stat-value">{globalDashboard.proyectosCompletados || 0}</p>
              </div>
            </div>
            <div className="stat-card orange">
              <div className="stat-icon">👥</div>
              <div className="stat-info">
                <h3>Usuarios</h3>
                <p className="stat-value">{globalDashboard.totalUsuarios || 0}</p>
              </div>
            </div>
          </section>
        )}

        {isAdmin() && globalDashboard && globalDashboard.progresoPromedio !== undefined && (
          <section className="ecosystem-impact-section fade-in">
             <div className="impact-card">
                <div className="impact-header">
                  <h3>Impacto Global del Ecosistema</h3>
                  <span className="impact-value">{(globalDashboard.progresoPromedio || 0).toFixed(1)}%</span>
                </div>
                <div className="progress-bar-container">
                  <div 
                    className="progress-bar-fill" 
                    style={{ width: `${Math.min(100, globalDashboard.progresoPromedio || 0)}%` }}
                  ></div>
                </div>
                <p className="impact-footer">Promedio de logro de metas a través de los 17 ODS</p>
             </div>
          </section>
        )}

        <div className="dashboard-grid">
          <section className="main-panel">
            {perms.canViewAuditQueue ? (
              <div className="admin-actions-grid">
                {/* Sprint 3: Cola de evaluación — admin/evaluador */}
                <div className="action-card action-card--audit" onClick={() => navigate('/evaluacion')}>
                  <div className="action-icon">🔍</div>
                  <h4>Cola de Evaluación</h4>
                  <p>Revisar documentos del gestor e ingresar las mediciones que el sistema calculará según la fórmula.</p>
                  <span className="action-link">Evaluar proyectos →</span>
                </div>
                {/* Solo admin: panel admin completo */}
                {perms.canViewAdminPanel && (
                  <div className="action-card" onClick={() => navigate('/admin/projects')}>
                    <div className="action-icon">📂</div>
                    <h4>Gestión de Proyectos</h4>
                    <p>Supervisar y filtrar todos los proyectos del sistema.</p>
                    <span className="action-link">Acceder →</span>
                  </div>
                )}
                {perms.canManageUsers && (
                  <div className="action-card" onClick={() => navigate('/admin/users')}>
                    <div className="action-icon">👤</div>
                    <h4>Administración de usuarios</h4>
                    <p>Crear, editar y desactivar cuentas por sede y rol.</p>
                    <span className="action-link">Gestionar usuarios →</span>
                  </div>
                )}
                {perms.canViewLoginAudit && (
                  <div className="action-card" onClick={() => navigate('/admin/bitacora')}>
                    <div className="action-icon">📋</div>
                    <h4>Bitácora de ingresos</h4>
                    <p>Auditoría de login, logout e intentos fallidos.</p>
                    <span className="action-link">Ver bitácora →</span>
                  </div>
                )}
                <div className="action-card" onClick={() => navigate('/projects')}>
                  <div className="action-icon">📊</div>
                  <h4>Ver todos los proyectos</h4>
                  <p>Listado completo con estado de meta evaluado por proyecto.</p>
                  <span className="action-link">Ver →</span>
                </div>
              </div>
            ) : (
              <div className="user-projects-section">
                <div className="section-header">
                  <h3>{perms.canCreateProject ? 'Mis Proyectos Recientes' : 'Proyectos del sistema'}</h3>
                  <button className="btn-link" onClick={() => navigate('/projects')}>Ver todos</button>
                </div>
                
                {projects.length > 0 ? (
                  <div className="projects-list">
                    {projects.map(project => {
                      // Combinar ODS primario con vinculados, eliminar duplicados y ordenar
                      const allOds = [
                        project.objective,
                        ...(project.odsVinculados || [])
                      ].filter(ods => ods != null && !isNaN(ods))
                       .map(ods => parseInt(ods))
                       .filter((value, index, self) => self.indexOf(value) === index)
                       .sort((a, b) => a - b);

                      // Sprint UTN: normalización defensiva para que el card no muestre
                      // "0/0 indicadores" ni "NaN%". Si el backend aún no respondió con
                      // el resumen, los valores caen a cero pero la maquetación se mantiene.
                      const totalInd       = Number(project.totalIndicators) || project.indicators?.length || 0;
                      const achievedInd    = Number(project.indicatorsAchieved) || 0;
                      const progressPct    = Number(project.progressPercentage) || 0;
                      const workflowStatus = project.status;
                      const statusLabel    = getEstadoLabel(workflowStatus);
                      const statusClass    = getEstadoClass(workflowStatus);
                      const isInProgress   = !isProjectCompletado(project);

                      return (
                      <div key={project.id} className="project-item-card enriched">
                        <div className="project-card-badge-ods" style={{ backgroundColor: getOdsColor(project.objective) }}>
                          {allOds.length > 1 ? `${allOds[0]}+${allOds.length - 1}` : (allOds[0] ?? '—')}
                        </div>
                        
                        <div className="project-main-info">
                          <div className="title-row">
                            <h4>{project.name || 'Proyecto sin nombre'}</h4>
                            <span className={`status-pill ${statusClass}`}>
                              {statusLabel}
                            </span>
                          </div>
                          
                          <p className="project-desc-snippet">
                            {project.description || 'Sin descripción registrada para este proyecto.'}
                          </p>
                          
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
                          <div className="meta-stats-row">
                            <span className="label">
                              {allOds.length > 1
                                ? `Metas ODS ${allOds.join(', ')}`
                                : `Meta ODS ${allOds[0] ?? '—'}`
                              }
                            </span>
                            <span className="indicators-count">
                               <strong>{achievedInd}/{totalInd}</strong> indicadores
                            </span>
                          </div>
                          
                          <div className="mini-progress-section">
                            <div className="mini-progress-bar">
                              <div 
                                className="mini-progress-fill" 
                                style={{ width: `${Math.min(100, progressPct)}%` }}
                              ></div>
                            </div>
                            <span className="progress-percent">{progressPct.toFixed(0)}%</span>
                          </div>

                          <span className="date-info">Vence: {formatDate(project.endDate)}</span>
                        </div>

                        <div className="project-button">
                            <button 
                             className="btn-action-primary"
                             onClick={() => navigate(`/projects/${project.id}/results`)}
                           >
                             {isInProgress ? 'Medir Impacto' : 'Ver Reporte'}
                           </button>
                        </div>
                      </div>
                      );
                    })}
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
