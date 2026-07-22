import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import AchievementBadge from '../../components/AchievementBadge';
import { formatDate, getOdsColor, getEstadoLabel, getEstadoClass, matchesProjectStatusFilter, isProjectCompletado, PROJECT_STATUS_FILTER_OPTIONS } from '../../utils/formatters';
import { useExport } from '../../hooks/useExport';
import BulkProjectExportPanel from '../../components/projects/BulkProjectExportPanel';
import { 
  ArrowLeft, 
  Search, 
  Plus, 
  Filter, 
  Trash2, 
  BarChart3,
  Calendar,
  MapPin,
  FileDown
} from 'lucide-react';
import './ProjectListPage.css';

const ProjectListPage = () => {
  const { user } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();
  const { 
    projects: allProjectsHook, 
    loading: projectsLoading, 
    fetchUserProjects,
    fetchAllProjects,
    deleteProject 
  } = useProjects();
  
  const [projects, setProjects] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [filterStatus, setFilterStatus] = useState('all');
  const { downloadProjectFullReport } = useExport();
  const [exportingId, setExportingId] = useState(null);

  // Sprint 10: gestor ve solo sus proyectos; otros roles ven todos
  useEffect(() => {
    if (!user?.id) return;
    if (perms.canViewAllProjects) fetchAllProjects();
    else fetchUserProjects(user.id);
  }, [user, perms.canViewAllProjects]);

  useEffect(() => {
    if (allProjectsHook) {
      let filtered = [...allProjectsHook];
      
      if (searchTerm) {
        filtered = filtered.filter(p => 
          p.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
          p.description?.toLowerCase().includes(searchTerm.toLowerCase())
        );
      }
      
      if (filterStatus !== 'all') {
        filtered = filtered.filter(p => matchesProjectStatusFilter(p, filterStatus));
      }
      
      setProjects(filtered);
    }
  }, [allProjectsHook, searchTerm, filterStatus]);

  const handleDelete = async (e, id) => {
    e.stopPropagation();
    if (window.confirm('¿Estás seguro de que deseas eliminar este proyecto?')) {
      await deleteProject(id);
    }
  };

  const handleExportExcel = async (e, project) => {
    e.stopPropagation();
    if (!isProjectCompletado(project)) return;
    setExportingId(project.id);
    const r = await downloadProjectFullReport(project.id);
    setExportingId(null);
    if (!r.success) {
      window.alert(r.error || 'No se pudo descargar el Excel');
    }
  };

  if (projectsLoading) {
    return (
      <div className="dashboard-loading">
        <div className="loader"></div>
        <p>Cargando tu portafolio de proyectos...</p>
      </div>
    );
  }

  return (
    <div className="project-list-page fade-in">
      <header className="list-header">
        <div className="container header-content">
          <div className="header-left">
            <button className="btn-back" onClick={() => navigate('/dashboard')}>
              <ArrowLeft size={20} />
            </button>
            <div className="title-group">
              <h1>{perms.canViewAllProjects ? 'Proyectos' : 'Mis Proyectos'}</h1>
              <p>{perms.roleLabel} · Impacto ODS Registrado</p>
            </div>
          </div>
          {perms.canCreateProject && (
            <button className="btn-primary-glow" onClick={() => navigate('/projects/create')}>
              <Plus size={18} />
              Nuevo Proyecto
            </button>
          )}
        </div>
      </header>

      <main className="container list-main">
        <section className="controls-section">
          <div className="search-box">
            <Search className="search-icon" size={18} />
            <input 
              type="text" 
              placeholder="Buscar por nombre o descripción..." 
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          <div className="filters-box">
            <Filter size={18} className="filter-icon" />
            <select value={filterStatus} onChange={(e) => setFilterStatus(e.target.value)}>
              {PROJECT_STATUS_FILTER_OPTIONS.map((opt) => (
                <option key={opt.value} value={opt.value}>{opt.label}</option>
              ))}
            </select>
          </div>
        </section>

        <BulkProjectExportPanel />

        <div className="projects-grid">
          {projects.length > 0 ? (
            projects.map(project => (
              <div 
                key={project.id} 
                className="project-card-premium"
                onClick={() => navigate(`/projects/${project.id}/results`)}
              >
                <div className="card-top" style={{ borderTop: `4px solid ${getOdsColor(project.objective)}` }}>
                  <div className="ods-badge-small" style={{ backgroundColor: getOdsColor(project.objective) }}>
                    ODS {project.objective || '?'}
                  </div>
                  {/* Sprint 8.3: si el proyecto cubre más de un ODS, mostrar el contador */}
                  {Array.isArray(project.odsVinculados) && project.odsVinculados.length > 1 && (
                    <div className="ods-badge-extra"
                         title={`Otros ODS: ${project.odsVinculados.filter(n => n !== project.objective).join(', ')}`}
                         style={{
                           fontSize: 11, padding: '2px 7px', borderRadius: 99,
                           background: 'rgba(0,0,0,0.06)', color: '#444', marginLeft: 6
                         }}>
                      +{project.odsVinculados.length - 1}
                    </div>
                  )}
                  <span className={`status-tag ${getEstadoClass(project.status)}`}>
                    {getEstadoLabel(project.status)}
                  </span>
                </div>

                <div className="card-body">
                  <h3>{project.name}</h3>
                  <p className="description-text">{project.description}</p>
                  
                  <div className="card-meta">
                    <div className="meta-item">
                      <Calendar size={14} />
                      <span>Fin: {formatDate(project.endDate)}</span>
                    </div>
                    <div className="meta-item">
                      <MapPin size={14} />
                      <span>{project.provinciaNombre || 'S.J.'}</span>
                    </div>
                  </div>

                  {/* Sprint 14: badge de logro (lo que el consultor necesita ver) */}
                  <div style={{marginTop:10}}>
                    <AchievementBadge
                      porcentaje={project.progressPercentage}
                      estado={project.progressPercentage > 0 ? null : 'SIN DATOS'}
                      size="sm" />
                  </div>

                  <div className="progress-section">
                    <div className="progress-label">
                      <span>Logro de Metas</span>
                      <span>{Math.round(project.progressPercentage || 0)}%</span>
                    </div>
                    <div className="progress-track">
                      <div 
                        className="progress-fill" 
                        style={{ 
                          width: `${project.progressPercentage || 0}%`,
                          backgroundColor: getOdsColor(project.objective)
                        }}
                      ></div>
                    </div>
                  </div>
                </div>

                <div className="card-footer">
                  <button className="btn-card-action">
                    <BarChart3 size={16} />
                    Ver Impacto
                  </button>
                  {perms.canDownloadEvidence && (
                    <button
                      type="button"
                      className={`btn-card-action btn-card-action--excel ${!isProjectCompletado(project) ? 'is-disabled' : ''}`}
                      onClick={(e) => handleExportExcel(e, project)}
                      disabled={!isProjectCompletado(project) || exportingId === project.id}
                      title={isProjectCompletado(project)
                        ? 'Descargar Excel resumen'
                        : 'Disponible al finalizar (proyecto evaluado)'}
                    >
                      <FileDown size={16} />
                      {exportingId === project.id ? '…' : 'Excel'}
                    </button>
                  )}
                  {(perms.canDeleteProject || perms.canEditProject(project)) && (
                    <button 
                      className="btn-card-delete" 
                      onClick={(e) => handleDelete(e, project.id)}
                      title="Eliminar Proyecto"
                    >
                      <Trash2 size={16} />
                    </button>
                  )}
                </div>
              </div>
            ))
          ) : (
            <div className="empty-projects">
              <div className="empty-illus">📂</div>
              <h3>No se encontraron proyectos</h3>
              {perms.canCreateProject ? (
                <>
                  <p>¿Por qué no empiezas creando uno nuevo?</p>
                  <button className="btn-primary" onClick={() => navigate('/projects/create')}>
                    Comenzar ahora
                  </button>
                </>
              ) : (
                <p>Aún no hay proyectos en el sistema.</p>
              )}
            </div>
          )}
        </div>
      </main>
    </div>
  );
};

export default ProjectListPage;
