import React, { useState, useCallback, useContext, createContext, useRef } from 'react';
import { projectService } from '../services/projectService';
import { evaluationEngine } from '../utils/evaluationEngine';

const ProjectsContext = createContext(null);

/**
 * Estado compartido de proyectos (capa hook sobre projectService / axios).
 * Un solo provider evita refetch duplicado dashboard ↔ lista.
 */
function useProjectsState() {
  const [projects, setProjects] = useState([]);
  const [globalDashboard, setGlobalDashboard] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [loadedScope, setLoadedScope] = useState(null);
  const projectsRef = useRef([]);

  const fetchUserProjects = useCallback(async (userId, odsId, { force = false } = {}) => {
    const scope = `user:${userId}`;
    if (!force && loadedScope === scope && projectsRef.current.length > 0) {
      return projectsRef.current;
    }
    setLoading(true);
    setError(null);
    try {
      const data = await projectService.getUserProjects(userId, odsId);
      projectsRef.current = data;
      setProjects(data);
      setLoadedScope(scope);
      return data;
    } catch (err) {
      setError(err.message);
      return [];
    } finally {
      setLoading(false);
    }
  }, [loadedScope]);

  const fetchAllProjects = useCallback(async ({ force = false } = {}) => {
    const scope = 'all';
    if (!force && loadedScope === scope && projectsRef.current.length > 0) {
      return projectsRef.current;
    }
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.getAllProjects();
      const data = result?.data || [];
      projectsRef.current = data;
      setProjects(data);
      setLoadedScope(scope);
      return data;
    } catch (err) {
      setError(err.message);
      return [];
    } finally {
      setLoading(false);
    }
  }, [loadedScope]);

  const fetchAdminProjects = useCallback(async (odsId, options) => {
    return fetchAllProjects(options);
  }, [fetchAllProjects]);

  const createProject = async (projectData) => {
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.createProject(projectData);
      if (result.success) {
        setProjects(prev => {
          const next = [result.data, ...prev];
          projectsRef.current = next;
          return next;
        });
      }
      return result;
    } catch (err) {
      setError(err.message);
      return { success: false, error: err.message };
    } finally {
      setLoading(false);
    }
  };

  const createFullProject = async (projectData, servicesMap) => {
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.createFullProject(projectData, servicesMap);
      if (result.success) {
        setLoadedScope(null);
        projectsRef.current = [];
        setProjects([]);
      }
      return result;
    } catch (err) {
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const getProjectResults = async (projectId, odsId) => {
    setLoading(true);
    try {
      return await projectService.getProjectResults(projectId, odsId);
    } catch (err) {
      setError(err.message);
      throw err;
    } finally {
      setLoading(false);
    }
  };

  const fetchStatistics = useCallback(async (odsId) => {
    try {
      const result = await projectService.getStatistics(odsId);
      return result;
    } catch (err) {
      setError(err.message);
      return { success: false, error: err.message };
    }
  }, []);

  const fetchGlobalDashboard = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.getGlobalDashboardData();
      if (result.success) {
        setGlobalDashboard(result.data);
        return result.data;
      }
      setError(result.error);
      return null;
    } catch (err) {
      setError(err.message);
      return null;
    } finally {
      setLoading(false);
    }
  }, []);

  const deleteProject = async (projectId, odsId) => {
    setLoading(true);
    try {
      await projectService.deleteProject(projectId, odsId);
      setProjects(prev => {
        const next = prev.filter(p => p.id !== projectId);
        projectsRef.current = next;
        return next;
      });
      return { success: true };
    } catch (err) {
      setError(err.message);
      return { success: false, error: err.message };
    } finally {
      setLoading(false);
    }
  };

  const updateProjectResults = async (resultsData, odsId) => {
    setLoading(true);
    try {
      await projectService.updateProjectResults(resultsData, odsId);
      return { success: true };
    } catch (err) {
      setError(err.message);
      return { success: false, error: err.message };
    } finally {
      setLoading(false);
    }
  };

  const calculateIndicatorAchievement = (config, paramValues) => {
    if (!config || !config.formula) return { value: 0, achievement: 0 };
    const value = evaluationEngine.evaluateFormula(config.formula, paramValues);
    const achievement = evaluationEngine.calculateAchievement(value, config.goal);
    return { value, achievement };
  };

  const invalidateProjects = useCallback(() => {
    setLoadedScope(null);
    projectsRef.current = [];
  }, []);

  return {
    projects,
    globalDashboard,
    loading,
    error,
    loadedScope,
    fetchUserProjects,
    fetchAllProjects,
    fetchAdminProjects,
    fetchStatistics,
    fetchGlobalDashboard,
    createProject,
    createFullProject,
    getProjectResults,
    updateProjectResults,
    deleteProject,
    invalidateProjects,
    calculateIndicatorAchievement,
  };
}

export function ProjectsProvider({ children }) {
  const value = useProjectsState();
  return (
    <ProjectsContext.Provider value={value}>
      {children}
    </ProjectsContext.Provider>
  );
}

/** Hook — requiere ProjectsProvider en App. */
export const useProjects = () => {
  const ctx = useContext(ProjectsContext);
  if (!ctx) {
    throw new Error('useProjects debe usarse dentro de <ProjectsProvider>');
  }
  return ctx;
};
