import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { projectService } from '../services/projectService';

const ProjectResultsPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { projectId } = useParams();
  const [project, setProject] = useState(null);
  const [formData, setFormData] = useState({});
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
  const [results, setResults] = useState(null);

  useEffect(() => {
    fetchProject();
  }, [projectId]);

  const fetchProject = async () => {
    try {
      setLoading(true);
      const userProjects = await projectService.getUserProjects(user.id);
      const currentProject = userProjects.find(p => p.id === parseInt(projectId));
      
      if (!currentProject) {
        setError('Proyecto no encontrado');
        return;
      }
      
      setProject(currentProject);
      
      if (currentProject.status === 'completed') {
        const projectResults = await projectService.getProjectResults(currentProject.id);
        setResults(projectResults);
      } else {
        const initialFormData = {};
        currentProject.indicators.forEach(indicator => {
          initialFormData[indicator] = '';
        });
        setFormData(initialFormData);
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleInputChange = (indicator, value) => {
    setFormData(prev => ({
      ...prev,
      [indicator]: value
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');

    try {
      const resultsData = {
        projectId: project.id,
        finalValues: formData
      };

      await projectService.updateProjectResults(resultsData);
      const projectResults = await projectService.getProjectResults(project.id);
      setResults(projectResults);
      setProject(prev => ({ ...prev, status: 'completed' }));
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  };

  const formatIndicatorName = (indicator) => {
    return indicator.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase());
  };

  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('es-ES');
  };

  const getGoalAchievementColor = (achieved) => {
    if (achieved >= 100) return 'text-green-600';
    if (achieved >= 80) return 'text-yellow-600';
    return 'text-red-600';
  };

  const getGoalAchievementText = (achieved) => {
    if (achieved >= 100) return 'Objetivo Cumplido ✓';
    if (achieved >= 80) return 'Cerca del Objetivo';
    return 'Objetivo No Cumplido';
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-lg text-gray-600">Cargando proyecto...</div>
      </div>
    );
  }

  if (!project) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 mb-4">Proyecto no encontrado</h2>
          <button
            onClick={() => navigate('/dashboard')}
            className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
          >
            Volver al Dashboard
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <div className="flex justify-between items-center mb-8">
            <h1 className="text-3xl font-bold text-gray-900">Resultados del Proyecto</h1>
            <button
              onClick={() => navigate('/dashboard')}
              className="px-4 py-2 bg-gray-600 text-white rounded-md hover:bg-gray-700"
            >
              Volver al Dashboard
            </button>
          </div>

          {error && (
            <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded-md">
              <p className="text-red-600">{error}</p>
            </div>
          )}

          <div className="mb-8 bg-gray-50 rounded-lg p-6">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Información del Proyecto</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <span className="text-sm font-medium text-gray-500">Nombre:</span>
                <p className="text-lg text-gray-900">{project.name}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Descripción:</span>
                <p className="text-lg text-gray-900">{project.description}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Objetivo ODS:</span>
                <p className="text-lg text-gray-900">{project.objective}. {project.objectiveName || 'Objetivo ' + project.objective}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Estado:</span>
                <p className="text-lg text-gray-900">
                  <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${
                    project.status === 'completed' ? 'bg-blue-100 text-blue-800' : 'bg-green-100 text-green-800'
                  }`}>
                    {project.status === 'completed' ? 'Completado' : 'Activo'}
                  </span>
                </p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Fecha de Inicio:</span>
                <p className="text-lg text-gray-900">{formatDate(project.startDate)}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Fecha de Fin:</span>
                <p className="text-lg text-gray-900">{formatDate(project.endDate)}</p>
              </div>
            </div>
          </div>

          {results ? (
            <div>
              <h2 className="text-xl font-semibold text-gray-900 mb-6">Resultados Finales</h2>
              <div className="space-y-4">
                {results.indicatorResults.map((result, index) => (
                  <div key={index} className="border border-gray-200 rounded-lg p-4">
                    <div className="flex justify-between items-start mb-3">
                      <h3 className="text-lg font-medium text-gray-900">
                        {formatIndicatorName(result.indicator)}
                      </h3>
                      <span className={`text-lg font-semibold ${getGoalAchievementColor(result.goalAchievement)}`}>
                        {result.goalAchievement.toFixed(1)}%
                      </span>
                    </div>
                    <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                      <div>
                        <span className="text-sm text-gray-500">Valor Objetivo:</span>
                        <p className="text-lg font-medium text-gray-900">{result.targetValue}</p>
                      </div>
                      <div>
                        <span className="text-sm text-gray-500">Valor Final:</span>
                        <p className="text-lg font-medium text-gray-900">{result.finalValue}</p>
                      </div>
                      <div>
                        <span className="text-sm text-gray-500">Estado:</span>
                        <p className={`text-lg font-medium ${getGoalAchievementColor(result.goalAchievement)}`}>
                          {getGoalAchievementText(result.goalAchievement)}
                        </p>
                      </div>
                    </div>
                    {result.notes && (
                      <div className="mt-3 p-3 bg-blue-50 rounded-md">
                        <p className="text-sm text-blue-800">{result.notes}</p>
                      </div>
                    )}
                  </div>
                ))}
              </div>
              
              <div className="mt-8 bg-gray-50 rounded-lg p-6">
                <h3 className="text-lg font-semibold text-gray-900 mb-4">Resumen del Proyecto</h3>
                <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                  <div className="text-center">
                    <div className="text-3xl font-bold text-gray-900">{results.overallScore.toFixed(1)}%</div>
                    <div className="text-sm text-gray-500">Puntuación General</div>
                  </div>
                  <div className="text-center">
                    <div className={`text-3xl font-bold ${getGoalAchievementColor(results.overallScore)}`}>
                      {results.indicatorsAchieved}/{results.totalIndicators}
                    </div>
                    <div className="text-sm text-gray-500">Indicadores Cumplidos</div>
                  </div>
                  <div className="text-center">
                    <div className={`text-3xl font-bold ${getGoalAchievementColor(results.overallScore)}`}>
                      {getGoalAchievementText(results.overallScore)}
                    </div>
                    <div className="text-sm text-gray-500">Estado General</div>
                  </div>
                </div>
              </div>
            </div>
          ) : (
            <form onSubmit={handleSubmit} className="space-y-6">
              <div>
                <h2 className="text-xl font-semibold text-gray-900 mb-6">Ingresar Resultados Finales</h2>
                <p className="text-gray-600 mb-6">
                  Por favor, ingrese los valores finales obtenidos para cada indicador medido en su proyecto.
                </p>
              </div>

              <div className="space-y-4">
                {project.indicators.map((indicator, index) => (
                  <div key={index} className="border border-gray-200 rounded-lg p-4">
                    <div className="mb-3">
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        {formatIndicatorName(indicator)}
                      </label>
                      <div className="text-sm text-gray-500 mb-2">
                        Valor objetivo: {project.targetValues[indicator]}
                      </div>
                      <input
                        type="number"
                        step="0.01"
                        value={formData[indicator] || ''}
                        onChange={(e) => handleInputChange(indicator, e.target.value)}
                        required
                        placeholder="Ingrese el valor final obtenido"
                        className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                      />
                    </div>
                  </div>
                ))}
              </div>

              <div className="flex justify-end space-x-4">
                <button
                  type="button"
                  onClick={() => navigate('/dashboard')}
                  className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50"
                >
                  Cancelar
                </button>
                <button
                  type="submit"
                  disabled={submitting}
                  className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 disabled:opacity-50"
                >
                  {submitting ? 'Guardando...' : 'Guardar Resultados'}
                </button>
              </div>
            </form>
          )}
        </div>
      </div>
    </div>
  );
};

export default ProjectResultsPage;
