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
    
    // Buscar sede id por nombre si no viene
    const sedeId = projectData.sedeId || 1; // Default
    
    const backendData = {
      usuarioId: projectData.userId,
      sedeId: sedeId,
      nombreProyecto: projectData.name,
      objetivoId: parseInt(odsId),
      descripcion: projectData.description,
      fechaInicio: projectData.startDate,
      fechaFin: projectData.endDate,
      metaGeneral: projectData.description?.substring(0, 100), // Fallback
      estado: 'planificacion'
    };

    try {
      const response = await api.post(`/ods/${odsId}/proyecto`, backendData);
      return {
        success: true,
        data: this._mapBackendToFrontend(response.data, odsId)
      };
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al crear el proyecto');
    }
  },

  /**
   * Orquestador para creación completa de proyecto (Encabezado + Indicadores + Parámetros)
   */
  async createFullProject(projectData, servicesMap) {
    try {
      // 1. Crear encabezado del proyecto
      const headerRes = await this.createProject(projectData);
      if (!headerRes.success) throw new Error('No se pudo crear el encabezado del proyecto');
      
      const projectId = headerRes.data.id;
      const primaryOds = projectData.objective;
      const service = servicesMap[primaryOds];

      if (!service) throw new Error(`Servicio para ODS ${primaryOds} no disponible`);

      // 2. Vincular indicadores seleccionados
      if (projectData.indicators && projectData.indicators.length > 0) {
        for (const code of projectData.indicators) {
          const config = projectData.indicatorConfigs[code] || {};
          const meta = projectData.indicatorMetadata[code] || {};

          if (!meta.masterId) {
            console.warn(`Indicador ${code} no tiene masterId. Saltando vinculación.`);
            continue;
          }

          const indRes = await service.saveIndicator({
            proyectoId: projectId,
            indicadorMasterId: meta.masterId,
            metaValor: config.targetValue || config.goalValue || 0,
            metaUnidad: meta.unit || 'unidad',
            formulaCustom: config.formula || null
          });

          // 3. Guardar parámetros/variables si existen
          if (indRes.success && config.parameters && config.parameters.length > 0) {
            const proyectoIndicadorId = indRes.data.id;
            for (const param of config.parameters) {
              await service.saveParameter({
                proyectoIndicadorId,
                nombreParametro: param.name || param,
                tipoDato: 'Decimal'
              });
            }
          }
        }
      }

      return headerRes;
    } catch (error) {
      console.error('[projectService] Error in createFullProject:', error);
      throw error;
    }
  },

  async updateProject(projectData, odsId) {
    const formattedOdsId = String(odsId).padStart(2, '0');
    try {
      const response = await api.put(`/ods/${formattedOdsId}/proyecto/${projectData.id}`, projectData);
      return {
        success: true,
        data: this._mapBackendToFrontend(response.data, formattedOdsId)
      };
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al actualizar el proyecto');
    }
  },

  async updateProjectResults(resultsData, odsId = '01') {
    const formattedOdsId = String(odsId).padStart(2, '0');
    try {
      const response = await api.put(`/ods/${formattedOdsId}/proyecto/${resultsData.projectId}`, {
        id: resultsData.projectId,
        estado: 'COMPLETADO'
      });
      return {
        success: true,
        data: this._mapBackendToFrontend(response.data, formattedOdsId)
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
      await api.delete(`/ods/${formattedOdsId}/proyecto/${projectId}`);
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
      indicators: p.indicadores || [],
      indicatorConfigs: p.configuracionIndicadores || p.indicator_configs || {}
    };
  }
};
