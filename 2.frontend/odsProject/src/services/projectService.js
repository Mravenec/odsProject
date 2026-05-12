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
    // Sprint 8.3: la vista vista_resumen_proyectos_ods devuelve campos
    // distintos a la tabla proyectos. Soportamos ambos para que el
    // listado y el detalle coexistan sin romper.
    const odsPrimarioRaw = p.odsPrimario ?? p.ods_primario;
    const odsPrimario = odsPrimarioRaw != null
      ? parseInt(odsPrimarioRaw)
      : null;
    const odsVinculadosCsv = p.odsVinculados ?? p.ods_vinculados;
    const odsVinculados = typeof odsVinculadosCsv === 'string' && odsVinculadosCsv.length > 0
      ? odsVinculadosCsv.split(',').map(s => parseInt(s.trim())).filter(n => !Number.isNaN(n))
      : [];

    return {
      id: p.id ?? p.proyectoId ?? p.proyecto_id,
      name: p.nombreProyecto || p.nombre_proyecto,
      description: p.descripcion,
      userId: p.usuarioId || p.usuario_id,
      sedeId: p.sedeId || p.sede_id,
      // ODS primario: lo lee tanto del campo del view (odsPrimario)
      // como del fallback histórico (objetivoId / objetivo_id)
      objective: p.objetivoId || p.objetivo_id || odsPrimario,
      odsPrimario,
      odsVinculados,
      startDate: p.fechaInicio || p.fecha_inicio,
      endDate: p.fechaFin || p.fecha_fin,
      status: p.estado,
      createdAt: p.createdAt || p.created_at
    };
  },

  // ── CRUD Proyectos ──────────────────────────────────────────────
  // Sprint 8.3: por defecto pegamos al endpoint /with-ods que devuelve
  // el ODS primario y el CSV de vinculados. Antes la pantalla de listado
  // mostraba "Objetivo Desconocido" porque /api/projects no incluye esa info.
  async getAllProjects() {
    try {
      const response = await api.get('/projects/with-ods');
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
      const response = await api.get(`/projects/user/${userId}/with-ods`);
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
   * Orquestador de creación completa de proyecto — Sprint 5
   *
   * Antes hacía N+1 llamadas (1 proyecto + N indicadores + N×K parámetros).
   * Ahora arma el árbol completo y hace UNA SOLA llamada a /api/projects/full.
   * El backend orquesta con compensaciones y devuelve un resumen detallado
   * con errores granulares por indicador.
   */
  async createFullProject(projectData, servicesMap) {
    try {
      const { indicators, indicatorConfigs, indicatorMetadata, selectedOds } = projectData;

      // ── Armar el array de indicadores con ODS inferido del prefijo del código ──
      const indicadoresPayload = [];
      const odsSet = new Set();
      const skippedIndicators = [];

      // ── Sprint 8.1: SEMBRAR el set con los ODS que el usuario marcó en la
      //    cuadrícula, ANTES de iterar los indicadores. Si el usuario eligió
      //    ODS pero no marcó indicadores adentro, igual queremos vincularlos.
      //    Antes este bug causaba odsIds=[] y el proyecto quedaba huérfano.
      if (Array.isArray(selectedOds)) {
        for (const odsId of selectedOds) {
          const n = parseInt(odsId);
          if (!Number.isNaN(n)) odsSet.add(n);
        }
      }

      if (Array.isArray(indicators) && indicators.length > 0) {
        for (const code of indicators) {
          const odsNum = parseInt(code.split('.')[0]);
          const meta   = indicatorMetadata?.[code] || {};
          const config = indicatorConfigs?.[code] || {};

          if (!meta.masterId) {
            skippedIndicators.push(code);
            continue;
          }
          odsSet.add(odsNum);
          indicadoresPayload.push({
            odsId:             odsNum,
            codigo:            code,                         // informativo, no se persiste
            indicadorMasterId: meta.masterId,
            metaValor:         parseFloat(config.goal?.value) || 0,
            metaUnidad:        config.goal?.unit || meta.unit || 'unidad',
            metaNombre:        config.goal?.name || null,
            formulaCustom:     config.formula || null,
            parametros: (Array.isArray(config.parameters) ? config.parameters : [])
              .filter(p => p?.name?.trim())
              .map(p => ({
                nombreParametro: p.name.trim(),
                nombreVariable:  (p.variable || p.name).trim(),
                tipoDato:        p.type || 'Decimal'
              }))
          });
        }
      }

      // ── Payload completo para POST /api/projects/full ──
      const payload = {
        proyecto: {
          usuarioId:         projectData.userId,
          sedeId:            projectData.sedeId || 1,
          nombreProyecto:    projectData.name,
          descripcion:       projectData.description,
          fechaInicio:       projectData.startDate,
          fechaFin:          projectData.endDate,
          metaGeneral:       projectData.description?.substring(0, 100),
          responsableNombre: projectData.responsableNombre || null,
          locationProvince:  projectData.locationProvince  || null,
          locationCanton:    projectData.locationCanton    || null,
          locationDistrict:  projectData.locationDistrict  || null,
          estado:            'planificacion'
        },
        odsIds:       Array.from(odsSet),
        primaryOdsId: projectData.primaryOds || projectData.objective || Array.from(odsSet)[0] || null,
        indicadores:  indicadoresPayload
      };

      console.info('[createFullProject] Enviando árbol completo:', payload);
      const response = await api.post('/projects/full', payload);
      const data = response.data || {};

      return {
        success: data.success === true,
        data:    { id: data.proyectoId },
        proyectoId:         data.proyectoId,
        odsVinculados:      data.odsVinculados || [],
        indicadoresCreados: data.indicadoresCreados || [],
        savedIndicators:    (data.indicadoresCreados || []).length,
        skippedIndicators,
        errores:            data.errores || []
      };
    } catch (error) {
      // El backend manda el cuerpo estructurado vía GlobalExceptionHandler
      const errorData = error.response?.data || {};
      console.error('[projectService] Error en createFullProject:', errorData);
      throw new Error(
        errorData.message ||
        errorData.error   ||
        error.message     ||
        'Error al guardar el proyecto'
      );
    }
  },

  // ── Lectura: qué ODS cubre un proyecto ──────────────────────────────
  async getOdsByProyecto(proyectoId) {
    try {
      const response = await api.get(`/projects/${proyectoId}/ods`);
      return { success: true, data: response.data || [] };
    } catch (error) {
      return { success: false, error: error.message, data: [] };
    }
  },
};
