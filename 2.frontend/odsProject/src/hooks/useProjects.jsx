import { useState, useCallback } from 'react';
import { projectService } from '../services/projectService';
import { evaluationEngine } from '../utils/evaluationEngine';

export const useProjects = () => {
  const [projects, setProjects] = useState([]);
  const [globalDashboard, setGlobalDashboard] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const fetchUserProjects = useCallback(async (userId, odsId) => {
    setLoading(true);
    setError(null);
    try {
      const data = await projectService.getUserProjects(userId, odsId);
      setProjects(data);
      return data;
    } catch (err) {
      setError(err.message);
      return [];
    } finally {
      setLoading(false);
    }
  }, []);

  // Sprint 10 — admin/auditor/consultor ven TODOS los proyectos
  const fetchAllProjects = useCallback(async () => {
    setLoading(true); setError(null);
    try {
      const result = await projectService.getAllProjects();
      const data = result?.data || [];
      setProjects(data);
      return data;
    } catch (err) { setError(err.message); return []; }
    finally { setLoading(false); }
  }, []);

  const createProject = async (projectData) => {
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.createProject(projectData);
      if (result.success) {
        setProjects(prev => [result.data, ...prev]);
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
        setProjects(prev => [result.data, ...prev]);
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

  const fetchAdminProjects = useCallback(async (odsId) => {
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.getAdminProjects(odsId);
      if (result.success) {
        setProjects(result.data);
        return result.data;
      } else {
        setError(result.error);
        return [];
      }
    } catch (err) {
      setError(err.message);
      return [];
    } finally {
      setLoading(false);
    }
  }, []);

  const fetchStatistics = useCallback(async (odsId) => {
    try {
      const result = await projectService.getStatistics(odsId);
      return result;
    } catch (err) {
      setError(err.message);
      return { success: false, error: err.message };
    }
  }, []);

  /**
   * Carga métricas globales del Dashboard Core V3
   */
  const fetchGlobalDashboard = useCallback(async () => {
    setLoading(true);
    setError(null);
    try {
      const result = await projectService.getGlobalDashboardData();
      if (result.success) {
        setGlobalDashboard(result.data);
        return result.data;
      } else {
        setError(result.error);
        return null;
      }
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
      setProjects(prev => prev.filter(p => p.id !== projectId));
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


  // --- Evaluation Methods ---

  const calculateIndicatorAchievement = (config, paramValues) => {
    if (!config || !config.formula) return { value: 0, achievement: 0 };
    
    const value = evaluationEngine.evaluateFormula(config.formula, paramValues);
    const achievement = evaluationEngine.calculateAchievement(value, config.goal);
    
    return { value, achievement };
  };

  return {
    projects,
    globalDashboard,
    loading,
    error,
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
    // Evaluation
    calculateIndicatorAchievement
  };
};
