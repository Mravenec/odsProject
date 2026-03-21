import { useState, useCallback } from 'react';
import { projectService } from '../services/projectService';

export const useProjects = () => {
  const [projects, setProjects] = useState([]);
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

  return {
    projects,
    loading,
    error,
    fetchUserProjects,
    fetchAdminProjects,
    fetchStatistics,
    createProject,
    getProjectResults,
    updateProjectResults,
    deleteProject
  };
};
