import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { projectService } from '../services/projectService';

const AdminProjectOverviewPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
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
    { id: 6, name: 'Agua Limpia y Saneamiento' },
    { id: 7, name: 'Energía Asequible y No Contaminante' },
    { id: 8, name: 'Trabajo Decente y Crecimiento Económico' },
    { id: 9, name: 'Industria, Innovación e Infraestructura' },
    { id: 10, name: 'Reducción de las Desigualdades' },
    { id: 11, name: 'Ciudades y Comunidades Sostenibles' },
    { id: 12, name: 'Producción y Consumo Responsables' },
    { id: 13, name: 'Acción por el Clima' },
    { id: 14, name: 'Vida Submarina' },
    { id: 15, name: 'Vida de Ecosistemas Terrestres' },
    { id: 16, name: 'Paz, Justicia e Instituciones Sólidas' },
    { id: 17, name: 'Alianzas para Lograr los Objetivos' }
  ];

  useEffect(() => {
    fetchProjects();
  }, [filter]);

  const fetchProjects = async () => {
    try {
      setLoading(true);
      const allProjects = await projectService.getAdminProjects();
      
      let filteredProjects = allProjects;
      
      if (filter.user) {
        filteredProjects = filteredProjects.filter(project => 
          project.userName.toLowerCase().includes(filter.user.toLowerCase())
        );
      }
      
      if (filter.objective) {
        filteredProjects = filteredProjects.filter(project => 
          project.objective === parseInt(filter.objective)
        );
      }
      
      if (filter.status !== 'all') {
        filteredProjects = filteredProjects.filter(project => 
          project.status === filter.status
        );
      }
      
      setProjects(filteredProjects);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilter(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const getStatusColor = (status) => {
    switch (status) {
      case 'active':
        return 'bg-green-100 text-green-800';
      case 'completed':
        return 'bg-blue-100 text-blue-800';
      case 'cancelled':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  const getStatusText = (status) => {
    switch (status) {
      case 'active':
        return 'Activo';
      case 'completed':
        return 'Completado';
      case 'cancelled':
        return 'Cancelado';
      default:
        return status;
    }
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('es-ES');
  };

  const getObjectiveName = (objectiveId) => {
    const objective = objectives.find(obj => obj.id === objectiveId);
    return objective ? objective.name : 'Desconocido';
  };

  const handleViewResults = (projectId) => {
    navigate(`/admin/results/${projectId}`);
  };

  const handleDeleteProject = async (projectId) => {
    if (window.confirm('¿Está seguro de que desea eliminar este proyecto?')) {
      try {
        await projectService.deleteProject(projectId);
        fetchProjects();
      } catch (err) {
        setError(err.message);
      }
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-lg text-gray-600">Cargando proyectos...</div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <div className="flex justify-between items-center mb-8">
            <h1 className="text-3xl font-bold text-gray-900">Visión General de Proyectos</h1>
            <button
              onClick={() => navigate('/dashboard')}
              className="px-4 py-2 bg-gray-600 text-white rounded-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-gray-500"
            >
              Volver al Dashboard
            </button>
          </div>

          {error && (
            <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded-md">
              <p className="text-red-600">{error}</p>
            </div>
          )}

          <div className="mb-6 grid grid-cols-1 md:grid-cols-4 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Filtrar por Usuario
              </label>
              <input
                type="text"
                name="user"
                value={filter.user}
                onChange={handleFilterChange}
                placeholder="Nombre de usuario"
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>
            
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Filtrar por Objetivo
              </label>
              <select
                name="objective"
                value={filter.objective}
                onChange={handleFilterChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="">Todos los objetivos</option>
                {objectives.map(objective => (
                  <option key={objective.id} value={objective.id}>
                    {objective.id}. {objective.name}
                  </option>
                ))}
              </select>
            </div>
            
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Filtrar por Estado
              </label>
              <select
                name="status"
                value={filter.status}
                onChange={handleFilterChange}
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="all">Todos los estados</option>
                <option value="active">Activo</option>
                <option value="completed">Completado</option>
                <option value="cancelled">Cancelado</option>
              </select>
            </div>
            
            <div className="flex items-end">
              <button
                onClick={() => setFilter({ user: '', objective: '', status: 'all' })}
                className="w-full px-4 py-2 bg-gray-200 text-gray-700 rounded-md hover:bg-gray-300 focus:outline-none focus:ring-2 focus:ring-gray-500"
              >
                Limpiar Filtros
              </button>
            </div>
          </div>

          <div className="overflow-x-auto">
            <table className="min-w-full divide-y divide-gray-200">
              <thead className="bg-gray-50">
                <tr>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Proyecto
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Usuario
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Objetivo ODS
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Indicadores
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Duración
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Estado
                  </th>
                  <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
                    Acciones
                  </th>
                </tr>
              </thead>
              <tbody className="bg-white divide-y divide-gray-200">
                {projects.length === 0 ? (
                  <tr>
                    <td colSpan="7" className="px-6 py-4 text-center text-gray-500">
                      No se encontraron proyectos
                    </td>
                  </tr>
                ) : (
                  projects.map((project) => (
                    <tr key={project.id}>
                      <td className="px-6 py-4 whitespace-nowrap">
                        <div>
                          <div className="text-sm font-medium text-gray-900">
                            {project.name}
                          </div>
                          <div className="text-sm text-gray-500">
                            {project.description}
                          </div>
                        </div>
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                        {project.userName}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                        {project.objective}. {getObjectiveName(project.objective)}
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                        <div className="space-y-1">
                          {project.indicators.slice(0, 2).map((indicator, index) => (
                            <div key={index} className="text-xs">
                              • {indicator.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase())}
                            </div>
                          ))}
                          {project.indicators.length > 2 && (
                            <div className="text-xs text-gray-500">
                              +{project.indicators.length - 2} más
                            </div>
                          )}
                        </div>
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                        <div>
                          <div>{formatDate(project.startDate)}</div>
                          <div className="text-gray-500">{formatDate(project.endDate)}</div>
                        </div>
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap">
                        <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${getStatusColor(project.status)}`}>
                          {getStatusText(project.status)}
                        </span>
                      </td>
                      <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
                        <div className="flex space-x-2">
                          {project.status === 'completed' && (
                            <button
                              onClick={() => handleViewResults(project.id)}
                              className="text-blue-600 hover:text-blue-900"
                            >
                              Ver Resultados
                            </button>
                          )}
                          <button
                            onClick={() => handleDeleteProject(project.id)}
                            className="text-red-600 hover:text-red-900"
                          >
                            Eliminar
                          </button>
                        </div>
                      </td>
                    </tr>
                  ))
                )}
              </tbody>
            </table>
          </div>

          <div className="mt-6 bg-gray-50 rounded-lg p-4">
            <h3 className="text-lg font-medium text-gray-900 mb-2">Resumen</h3>
            <div className="grid grid-cols-1 md:grid-cols-4 gap-4 text-sm">
              <div>
                <span className="text-gray-500">Total de Proyectos:</span>
                <span className="ml-2 font-medium">{projects.length}</span>
              </div>
              <div>
                <span className="text-gray-500">Activos:</span>
                <span className="ml-2 font-medium text-green-600">
                  {projects.filter(p => p.status === 'active').length}
                </span>
              </div>
              <div>
                <span className="text-gray-500">Completados:</span>
                <span className="ml-2 font-medium text-blue-600">
                  {projects.filter(p => p.status === 'completed').length}
                </span>
              </div>
              <div>
                <span className="text-gray-500">Cancelados:</span>
                <span className="ml-2 font-medium text-red-600">
                  {projects.filter(p => p.status === 'cancelled').length}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminProjectOverviewPage;
