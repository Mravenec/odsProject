import api from './api';

export const projectService = {
  // Nota: El backend tiene proyectos por ODS. 
  // Si no se especifica odsId, por defecto usamos '01' (Fin de la Pobreza)
  
  async getAllProjects(odsId = '01') {
    const formattedOdsId = String(odsId).padStart(2, '0');
    try {
      const response = await api.get(`/ods/${formattedOdsId}/proyectos`);
      return {
        success: true,
        data: (response.data || []).map(p => this._mapBackendToFrontend(p, formattedOdsId))
      };
    } catch (error) {
      console.error(`Error fetching projects for ODS ${formattedOdsId}:`, error);
      return { success: false, error: error.message };
    }
  },

  // Alias para compatibilidad con Dashboard
  async getAdminProjects() {
    return this.getAllProjects();
  },

  async getUserProjects(userId, odsId = '01') {
    const res = await this.getAllProjects(odsId);
    if (res.success) {
      // El backend actual parece devolver todos, así que filtramos por userId si el backend no lo hace
      // Aunque en una versión real el backend debería filtrar
      res.data = res.data.filter(p => p.userId === userId);
    }
    return res.data; // Dashboard espera el array directamente
  },

  async createProject(projectData) {
    const odsId = String(projectData.objective || '01').padStart(2, '0');
    const backendData = {
      nombre: projectData.name,
      descripcion: projectData.description,
      fechaInicio: projectData.startDate,
      fechaFin: projectData.endDate,
      presupuesto: 0, // No está en el form actual, ponemos 0
      estado: 'ACTIVO'
    };

    try {
      const response = await api.post(`/ods/${odsId}/proyectos`, backendData);
      return {
        success: true,
        data: this._mapBackendToFrontend(response.data, odsId)
      };
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al crear el proyecto');
    }
  },

  async updateProjectResults(resultsData) {
    // El frontend envía { projectId, finalValues }
    // Asumimos ODS 1 para la búsqueda del proyecto inicial
    const odsId = '01'; 
    try {
      // En un sistema real, primero buscaríamos el proyecto para saber su ODS
      // Aquí simplificamos a ODS 1
      const response = await api.put(`/ods/${odsId}/proyectos/${resultsData.projectId}`, {
        id: resultsData.projectId,
        // Aquí el backend espera el objeto Proyecto completo
        estado: 'COMPLETADO'
      });
      return {
        success: true,
        data: this._mapBackendToFrontend(response.data, odsId)
      };
    } catch (error) {
      throw new Error(error.message);
    }
  },

  async getProjectResults(projectId, odsId = '01') {
    const formattedOdsId = String(odsId).padStart(2, '0');
    try {
      const response = await api.get(`/ods/${formattedOdsId}/progreso/${projectId}`);
      // El backend devuelve un Double (progreso). 
      // El frontend espera un objeto complejo con score e indicadores.
      // Simulamos la estructura esperada por la UI con los datos reales disponibles
      return {
        overallScore: response.data || 0,
        indicatorsAchieved: (response.data >= 100) ? 1 : 0,
        totalIndicators: 1,
        indicatorResults: [
          {
            indicator: 'Progreso General',
            goalAchievement: response.data || 0,
            targetValue: 100,
            finalValue: response.data || 0
          }
        ]
      };
    } catch (error) {
      throw new Error(error.message);
    }
  },

  async deleteProject(projectId, odsId = '01') {
    const formattedOdsId = String(odsId).padStart(2, '0');
    try {
      await api.delete(`/ods/${formattedOdsId}/proyectos/${projectId}`);
      return { success: true };
    } catch (error) {
      throw new Error(error.message);
    }
  },

  async getStatistics(odsId = '01') {
    const formattedOdsId = String(odsId).padStart(2, '0');
    try {
      const response = await api.get(`/ods/${formattedOdsId}/estadisticas`);
      return {
        success: true,
        data: {
          totalProjects: response.data?.totalProyectos || 0,
          totalUsers: response.data?.totalUsuarios || 0
        }
      };
    } catch (error) {
      return { success: false, error: error.message };
    }
  },

  // Helper para mapear campos
  _mapBackendToFrontend(p, odsId) {
    return {
      id: p.id,
      userId: p.usuarioId || 1, // Fallback si no viene
      name: p.nombre,
      description: p.descripcion,
      objective: odsId,
      objectiveName: `ODS ${odsId}`,
      status: (p.estado || 'active').toLowerCase(),
      startDate: p.fechaInicio,
      endDate: p.fechaFin,
      indicators: [] // El backend no devuelve indicadores directo en la lista de proyectos
    };
  }
};
