import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { projectService } from '../services/projectService';

const AdminResultsReviewPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { projectId } = useParams();
  const [project, setProject] = useState(null);
  const [results, setResults] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProjectResults();
  }, [projectId]);

  const fetchProjectResults = async () => {
    try {
      setLoading(true);
      const adminProjects = await projectService.getAdminProjects();
      const currentProject = adminProjects.find(p => p.id === parseInt(projectId));
      
      if (!currentProject) {
        setError('Proyecto no encontrado');
        return;
      }
      
      setProject(currentProject);
      
      if (currentProject.status === 'completed') {
        const projectResults = await projectService.getProjectResults(currentProject.id);
        setResults(projectResults);
      } else {
        setError('Este proyecto aún no ha sido completado');
      }
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
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

  const getGoalAchievementBadge = (achieved) => {
    if (achieved >= 100) return 'bg-green-100 text-green-800';
    if (achieved >= 80) return 'bg-yellow-100 text-yellow-800';
    return 'bg-red-100 text-red-800';
  };

  const getObjectiveName = (objectiveId) => {
    const objectives = {
      1: 'Fin de la Pobreza',
      2: 'Hambre Cero',
      3: 'Salud y Bienestar',
      4: 'Educación de Calidad',
      5: 'Igualdad de Género',
      6: 'Agua Limpia y Saneamiento',
      7: 'Energía Asequible y No Contaminante',
      8: 'Trabajo Decente y Crecimiento Económico',
      9: 'Industria, Innovación e Infraestructura',
      10: 'Reducción de las Desigualdades',
      11: 'Ciudades y Comunidades Sostenibles',
      12: 'Producción y Consumo Responsables',
      13: 'Acción por el Clima',
      14: 'Vida Submarina',
      15: 'Vida de Ecosistemas Terrestres',
      16: 'Paz, Justicia e Instituciones Sólidas',
      17: 'Alianzas para Lograr los Objetivos'
    };
    return objectives[objectiveId] || 'Desconocido';
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-lg text-gray-600">Cargando resultados...</div>
      </div>
    );
  }

  if (!project || !results) {
    return (
      <div className="min-h-screen bg-gray-50 flex items-center justify-center">
        <div className="text-center">
          <h2 className="text-2xl font-bold text-gray-900 mb-4">
            {error || 'Resultados no encontrados'}
          </h2>
          <button
            onClick={() => navigate('/admin/projects')}
            className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700"
          >
            Volver a Proyectos
          </button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <div className="flex justify-between items-center mb-8">
            <h1 className="text-3xl font-bold text-gray-900">Revisión de Resultados</h1>
            <div className="flex space-x-4">
              <button
                onClick={() => navigate('/admin/projects')}
                className="px-4 py-2 bg-gray-600 text-white rounded-md hover:bg-gray-700"
              >
                Volver a Proyectos
              </button>
            </div>
          </div>

          <div className="mb-8 bg-gray-50 rounded-lg p-6">
            <h2 className="text-xl font-semibold text-gray-900 mb-4">Información del Proyecto</h2>
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <div>
                <span className="text-sm font-medium text-gray-500">Nombre del Proyecto:</span>
                <p className="text-lg text-gray-900">{project.name}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Usuario:</span>
                <p className="text-lg text-gray-900">{project.userName}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Objetivo ODS:</span>
                <p className="text-lg text-gray-900">{project.objective}. {getObjectiveName(project.objective)}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Descripción:</span>
                <p className="text-gray-900">{project.description}</p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Duración:</span>
                <p className="text-gray-900">
                  {formatDate(project.startDate)} - {formatDate(project.endDate)}
                </p>
              </div>
              <div>
                <span className="text-sm font-medium text-gray-500">Estado:</span>
                <span className="inline-flex px-2 py-1 text-xs font-semibold rounded-full bg-blue-100 text-blue-800">
                  Completado
                </span>
              </div>
            </div>
          </div>

          <div className="mb-8">
            <h2 className="text-xl font-semibold text-gray-900 mb-6">Análisis de Resultados por Indicador</h2>
            <div className="space-y-4">
              {results.indicatorResults.map((result, index) => (
                <div key={index} className="border border-gray-200 rounded-lg p-6">
                  <div className="flex justify-between items-start mb-4">
                    <div>
                      <h3 className="text-lg font-medium text-gray-900">
                        {formatIndicatorName(result.indicator)}
                      </h3>
                      <p className="text-sm text-gray-500 mt-1">
                        Indicador #{index + 1} del proyecto
                      </p>
                    </div>
                    <div className="text-right">
                      <div className={`text-2xl font-bold ${getGoalAchievementColor(result.goalAchievement)}`}>
                        {result.goalAchievement.toFixed(1)}%
                      </div>
                      <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${getGoalAchievementBadge(result.goalAchievement)}`}>
                        {getGoalAchievementText(result.goalAchievement)}
                      </span>
                    </div>
                  </div>
                  
                  <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-4">
                    <div className="bg-blue-50 rounded-md p-3">
                      <div className="text-sm text-blue-600 font-medium">Valor Objetivo</div>
                      <div className="text-xl font-bold text-blue-900">{result.targetValue}</div>
                    </div>
                    <div className="bg-green-50 rounded-md p-3">
                      <div className="text-sm text-green-600 font-medium">Valor Final</div>
                      <div className="text-xl font-bold text-green-900">{result.finalValue}</div>
                    </div>
                    <div className="bg-purple-50 rounded-md p-3">
                      <div className="text-sm text-purple-600 font-medium">Diferencia</div>
                      <div className="text-xl font-bold text-purple-900">
                        {((result.finalValue - result.targetValue) / result.targetValue * 100).toFixed(1)}%
                      </div>
                    </div>
                    <div className={`${getGoalAchievementColor(result.goalAchievement)} bg-opacity-10 rounded-md p-3`}>
                      <div className={`text-sm font-medium ${getGoalAchievementColor(result.goalAchievement)}`}>Logro</div>
                      <div className={`text-xl font-bold ${getGoalAchievementColor(result.goalAchievement)}`}>
                        {result.goalAchievement >= 100 ? '✓' : '✗'}
                      </div>
                    </div>
                  </div>

                  {result.notes && (
                    <div className="mt-4 p-4 bg-yellow-50 border-l-4 border-yellow-400">
                      <div className="flex">
                        <div className="flex-shrink-0">
                          <svg className="h-5 w-5 text-yellow-400" viewBox="0 0 20 20" fill="currentColor">
                            <path fillRule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clipRule="evenodd" />
                          </svg>
                        </div>
                        <div className="ml-3">
                          <p className="text-sm text-yellow-700">{result.notes}</p>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
              ))}
            </div>
          </div>

          <div className="bg-gradient-to-r from-blue-50 to-indigo-50 rounded-lg p-6 mb-8">
            <h3 className="text-xl font-semibold text-gray-900 mb-6">Resumen General del Proyecto</h3>
            <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
              <div className="text-center">
                <div className="text-4xl font-bold text-blue-600">{results.overallScore.toFixed(1)}%</div>
                <div className="text-sm text-gray-600 mt-2">Puntuación General</div>
                <div className="mt-2">
                  <div className="w-full bg-gray-200 rounded-full h-2">
                    <div 
                      className="bg-blue-600 h-2 rounded-full" 
                      style={{ width: `${Math.min(results.overallScore, 100)}%` }}
                    ></div>
                  </div>
                </div>
              </div>
              <div className="text-center">
                <div className={`text-4xl font-bold ${getGoalAchievementColor(results.overallScore)}`}>
                  {results.indicatorsAchieved}
                </div>
                <div className="text-sm text-gray-600 mt-2">Indicadores Cumplidos</div>
                <div className="text-lg text-gray-500">de {results.totalIndicators} totales</div>
              </div>
              <div className="text-center">
                <div className={`text-4xl font-bold ${getGoalAchievementColor(results.overallScore)}`}>
                  {((results.indicatorsAchieved / results.totalIndicators) * 100).toFixed(0)}%
                </div>
                <div className="text-sm text-gray-600 mt-2">Tasa de Éxito</div>
              </div>
              <div className="text-center">
                <div className={`text-2xl font-bold ${getGoalAchievementColor(results.overallScore)}`}>
                  {getGoalAchievementText(results.overallScore)}
                </div>
                <div className="text-sm text-gray-600 mt-2">Estado Final</div>
                <span className={`inline-flex px-3 py-1 text-sm font-semibold rounded-full ${getGoalAchievementBadge(results.overallScore)}`}>
                  {results.overallScore >= 100 ? 'Éxito' : results.overallScore >= 80 ? 'Parcial' : 'Requiere Mejora'}
                </span>
              </div>
            </div>
          </div>

          <div className="bg-gray-50 rounded-lg p-6">
            <h3 className="text-lg font-semibold text-gray-900 mb-4">Recomendaciones</h3>
            <div className="space-y-3">
              {results.overallScore >= 100 && (
                <div className="flex items-start p-3 bg-green-50 border-l-4 border-green-400">
                  <svg className="flex-shrink-0 h-5 w-5 text-green-400 mt-0.5" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clipRule="evenodd" />
                  </svg>
                  <div className="ml-3">
                    <p className="text-sm text-green-700">
                      <strong>Excelente trabajo:</strong> El proyecto ha superado todas las metas establecidas. 
                      Considere compartir este caso como éxito y replicar la metodología en futuros proyectos.
                    </p>
                  </div>
                </div>
              )}
              
              {results.overallScore >= 80 && results.overallScore < 100 && (
                <div className="flex items-start p-3 bg-yellow-50 border-l-4 border-yellow-400">
                  <svg className="flex-shrink-0 h-5 w-5 text-yellow-400 mt-0.5" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clipRule="evenodd" />
                  </svg>
                  <div className="ml-3">
                    <p className="text-sm text-yellow-700">
                      <strong>Buen desempeño:</strong> El proyecto está cerca de cumplir todos los objetivos. 
                      Revise los indicadores no cumplidos para identificar áreas de mejora.
                    </p>
                  </div>
                </div>
              )}
              
              {results.overallScore < 80 && (
                <div className="flex items-start p-3 bg-red-50 border-l-4 border-red-400">
                  <svg className="flex-shrink-0 h-5 w-5 text-red-400 mt-0.5" viewBox="0 0 20 20" fill="currentColor">
                    <path fillRule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zM8.707 7.293a1 1 0 00-1.414 1.414L8.586 10l-1.293 1.293a1 1 0 101.414 1.414L10 11.414l1.293 1.293a1 1 0 001.414-1.414L11.414 10l1.293-1.293a1 1 0 00-1.414-1.414L10 8.586 8.707 7.293z" clipRule="evenodd" />
                  </svg>
                  <div className="ml-3">
                    <p className="text-sm text-red-700">
                      <strong>Requiere atención:</strong> El proyecto no cumplió con varios objetivos. 
                      Se recomienda una revisión detallada de la estrategia y metodología utilizada.
                    </p>
                  </div>
                </div>
              )}
              
              <div className="flex items-start p-3 bg-blue-50 border-l-4 border-blue-400">
                <svg className="flex-shrink-0 h-5 w-5 text-blue-400 mt-0.5" viewBox="0 0 20 20" fill="currentColor">
                  <path fillRule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clipRule="evenodd" />
                </svg>
                <div className="ml-3">
                  <p className="text-sm text-blue-700">
                    <strong>Próximos pasos:</strong> Documente las lecciones aprendidas y 
                    considere ajustar las estrategias para futuros proyectos basándose en estos resultados.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdminResultsReviewPage;
