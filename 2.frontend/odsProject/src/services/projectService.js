import api from './api';

/**
 * Servicio de Proyectos — Alineado con MasterProjectController.java
 * 
 * Backend endpoints (MasterProjectController):
 *   GET    /api/projects                 → Todos los proyectos
 *   GET    /api/projects/{id}            → Proyecto por ID
 *   GET    /api/projects/user/{userId}   → Proyectos del usuario
 *   POST   /api/projects                 → Crear proyecto
 *   PUT    /api/projects/{id}            → Actualizar proyecto
 *   DELETE /api/projects/{id}            → Eliminar proyecto
 *   GET    /api/projects/dashboard       → Dashboard global
 */
export const projectService = {
  
  // ── Mapeo Backend → Frontend ────────────────────────────────────
  _mapBackendToFrontend(p) {
    return {
      id: p.id,
      name: p.nombreProyecto || p.nombre_proyecto,
      description: p.descripcion,
      userId: p.usuarioId || p.usuario_id,
      sedeId: p.sedeId || p.sede_id,
      objective: p.objetivoId || p.objetivo_id,  // ODS number
      startDate: p.fechaInicio || p.fecha_inicio,
      endDate: p.fechaFin || p.fecha_fin,
      status: p.estado,
      createdAt: p.createdAt || p.created_at
    };
  },

  // ── CRUD Proyectos ──────────────────────────────────────────────
  async getAllProjects() {
    try {
      const response = await api.get('/projects');
      return {
        success: true,
        data: (response.data || []).map(p => this._mapBackendToFrontend(p))
      };
    } catch (error) {
      console.error('Error fetching projects:', error);
      return { success: false, error: error.message, data: [] };
    }
  },

  async getAdminProjects() {
    return this.getAllProjects();
  },

  async getUserProjects(userId) {
    try {
      const response = await api.get(`/projects/user/${userId}`);
      return (response.data || []).map(p => this._mapBackendToFrontend(p));
    } catch (error) {
      console.error('Error fetching user projects:', error);
      return [];
    }
  },

  async getProjectById(projectId) {
    try {
      const response = await api.get(`/projects/${projectId}`);
      return { success: true, data: this._mapBackendToFrontend(response.data) };
    } catch (error) {
      return { success: false, error: error.message };
    }
  },

  async createProject(projectData) {
    const backendData = {
      usuarioId: projectData.userId,
      sedeId: projectData.sedeId || 1,
      nombreProyecto: projectData.name,
      descripcion: projectData.description,
      fechaInicio: projectData.startDate,
      fechaFin: projectData.endDate,
      metaGeneral: projectData.description?.substring(0, 100),
      responsableNombre: projectData.responsableNombre || null,
        locationProvince:  projectData.locationProvince  || null,
        locationCanton:    projectData.locationCanton    || null,
        locationDistrict:  projectData.locationDistrict  || null,
        estado: 'planificacion'
    };
    try {
      const response = await api.post('/projects', backendData);
      return { success: true, data: this._mapBackendToFrontend(response.data) };
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al crear el proyecto');
    }
  },

  async updateProject(projectId, projectData) {
    const backendData = {
      id: projectId,
      usuarioId: projectData.userId,
      sedeId: projectData.sedeId || 1,
      nombreProyecto: projectData.name,
      descripcion: projectData.description,
      fechaInicio: projectData.startDate,
      fechaFin: projectData.endDate,
      estado: projectData.status || 'activo'
    };
    try {
      const response = await api.put(`/projects/${projectId}`, backendData);
      return { success: true, data: this._mapBackendToFrontend(response.data) };
    } catch (error) {
      throw new Error(error.response?.data?.message || 'Error al actualizar el proyecto');
    }
  },

  async deleteProject(projectId) {
    try {
      await api.delete(`/projects/${projectId}`);
      return { success: true };
    } catch (error) {
      return { success: false, error: error.message };
    }
  },

  // ── Dashboard ────────────────────────────────────────────────────
  async getGlobalDashboard() {
    try {
      const response = await api.get('/projects/dashboard');
      return { success: true, data: response.data };
    } catch (error) {
      return { success: false, error: error.message, data: {} };
    }
  },

  // ── Resultados del Proyecto ──────────────────────────────────────
  async getProjectResults(projectId, odsNum) {
    const formattedOds = String(odsNum).padStart(2, '0');
    try {
      const response = await api.get(`/ods/${formattedOds}/indicadores/proyecto`, {
        params: { proyectoId: projectId }
      });
      return { success: true, data: response.data || [] };
    } catch (error) {
      return { success: false, error: error.message, data: [] };
    }
  },

  /**
   * Orquestador de creación completa de proyecto:
   *  1. Crear el proyecto en ods_master
   *  2. Vincular indicadores ODS al proyecto
   *  3. Guardar configuración de fórmulas y parámetros
   */
  async createFullProject(projectData, servicesMap) {
    try {
      // ─ 1. Crear encabezado del proyecto ─
      const headerRes = await this.createProject(projectData);
      if (!headerRes.success) throw new Error('No se pudo crear el encabezado del proyecto');
      const projectId = headerRes.data?.id || headerRes.data;

      const { indicators, indicatorConfigs, indicatorMetadata } = projectData;

      // ── S5: Contadores para feedback al usuario ───────────────────────────
      let savedIndicators = 0;
      let skippedIndicators = [];
      let savedParameters = 0;

      // ─ 2. Iterar flat array ["1.1.1", "3.1.1", "13.2.1"] ─
      // Cada código determina su ODS por prefijo → servicio correcto
      if (Array.isArray(indicators) && indicators.length > 0) {
        for (const code of indicators) {
          const odsNum  = parseInt(code.split('.')[0]);  // "3.1.1" → 3 → servicesMap[3]
          const service = servicesMap[odsNum];
          if (!service?.saveIndicator) {
            console.warn(`[ProjectService] Sin servicio para ODS ${odsNum} (${code})`);
            skippedIndicators.push(code);
            continue;
          }

          const config = indicatorConfigs?.[code] || {};
          const meta   = indicatorMetadata?.[code] || {};

          if (!meta.masterId) {
            console.warn(`[ProjectService] Sin masterId para ${code} — saltando`);
            skippedIndicators.push(code);
            continue;
          }

          // 2a. Guardar el indicador con fórmula y meta
          let indRes;
          try {
            indRes = await service.saveIndicator({
              proyectoId:        projectId,
              indicadorMasterId: meta.masterId,
              metaValor:         parseFloat(config.goal?.value) || 0,
              metaUnidad:        config.goal?.unit  || meta.unit || 'unidad',
              metaNombre:        config.goal?.name  || null,
              formulaCustom:     config.formula     || null
            });
            if (indRes?.success) savedIndicators++;
          } catch (e) {
            console.warn(`[ProjectService] Error guardando indicador ${code}:`, e.message);
            skippedIndicators.push(code);
            continue;
          }

          // 2b. Guardar cada variable/parámetro libre que el usuario definió
          if (indRes?.success && Array.isArray(config.parameters) && config.parameters.length > 0) {
            const proyectoIndicadorId = indRes.data?.id || indRes.data;
            for (const param of config.parameters) {
              if (!param.name?.trim()) continue;
              try {
                await service.saveParameter({
                  proyectoIndicadorId,
                  nombreParametro: param.name,
                  nombreVariable:  param.name,
                  tipoDato:        param.type || 'Decimal',
                  valorActual:     0
                });
                savedParameters++;
              } catch (e) {
                console.warn(`[ProjectService] Error guardando parámetro ${param.name}:`, e.message);
              }
            }
          }
        }
      }

      // ── S5: Retornar resultado con estadísticas del cascade ───────────────
      return {
        ...headerRes,
        savedIndicators,
        skippedIndicators,
        savedParameters
      };
    } catch (error) {
      console.error('[projectService] Error in createFullProject:', error);
      throw error;
    }
  },
};
