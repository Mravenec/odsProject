// Mock de gestión de proyectos - simula una API real
const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));

// Mock de base de datos de proyectos
let mockProjects = [
  {
    id: 1,
    userId: 2,
    userName: 'Usuario',
    objectiveId: '01',
    objectiveName: 'Fin de la Pobreza',
    indicators: ['1.1.1', '1.2.1'],
    title: 'Proyecto de Reducción de Pobreza Urbana',
    description: 'Medición de indicadores de pobreza en zona urbana',
    startDate: '2024-01-15',
    endDate: '2024-06-15',
    status: 'completed',
    targetValues: {
      '1.1.1': 5.0,
      '1.2.1': 8.0
    },
    finalValues: {
      '1.1.1': 4.5,
      '1.2.1': 7.8
    },
    results: {
      '1.1.1': { achieved: true, difference: -0.5, percentage: 110 },
      '1.2.1': { achieved: true, difference: -0.2, percentage: 102.5 }
    },
    createdAt: '2024-01-15T10:00:00Z',
    updatedAt: '2024-06-15T15:30:00Z'
  },
  {
    id: 2,
    userId: 2,
    userName: 'Usuario',
    objectiveId: '02',
    objectiveName: 'Hambre Cero',
    indicators: ['2.1.1', '2.2.1'],
    title: 'Proyecto de Seguridad Alimentaria',
    description: 'Medición de indicadores de hambre y nutrición',
    startDate: '2024-02-01',
    endDate: '2024-07-01',
    status: 'in_progress',
    targetValues: {
      '2.1.1': 3.0,
      '2.2.1': 2.5
    },
    finalValues: {},
    results: {},
    createdAt: '2024-02-01T09:00:00Z',
    updatedAt: '2024-02-01T09:00:00Z'
  }
];

let nextProjectId = 3;

export const projectService = {
  // Obtener todos los proyectos (para administrador)
  async getAllProjects() {
    await delay(800);
    return {
      success: true,
      data: [...mockProjects].sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))
    };
  },

  // Obtener proyectos del usuario actual
  async getUserProjects(userId) {
    await delay(600);
    const userProjects = mockProjects.filter(p => p.userId === userId);
    return {
      success: true,
      data: userProjects.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt))
    };
  },

  // Crear nuevo proyecto
  async createProject(projectData) {
    await delay(1000);
    
    const newProject = {
      id: nextProjectId++,
      ...projectData,
      status: 'in_progress',
      finalValues: {},
      results: {},
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    };

    mockProjects.push(newProject);
    
    return {
      success: true,
      data: newProject
    };
  },

  // Actualizar proyecto (valores finales y resultados)
  async updateProjectResults(projectId, finalValues) {
    await delay(800);
    
    const projectIndex = mockProjects.findIndex(p => p.id === projectId);
    if (projectIndex === -1) {
      return { success: false, error: 'Proyecto no encontrado' };
    }

    const project = mockProjects[projectIndex];
    const results = {};

    // Calcular resultados para cada indicador
    Object.keys(finalValues).forEach(indicator => {
      const target = project.targetValues[indicator];
      const final = finalValues[indicator];
      
      if (target !== undefined && final !== undefined) {
        const difference = final - target;
        const percentage = target !== 0 ? (final / target) * 100 : 0;
        const achieved = difference <= 0; // Se logra si el valor final es menor o igual al objetivo
        
        results[indicator] = {
          achieved,
          difference: parseFloat(difference.toFixed(2)),
          percentage: parseFloat(percentage.toFixed(1))
        };
      }
    });

    // Actualizar proyecto
    mockProjects[projectIndex] = {
      ...project,
      finalValues,
      results,
      status: 'completed',
      updatedAt: new Date().toISOString()
    };

    return {
      success: true,
      data: mockProjects[projectIndex]
    };
  },

  // Eliminar proyecto
  async deleteProject(projectId) {
    await delay(500);
    
    const projectIndex = mockProjects.findIndex(p => p.id === projectId);
    if (projectIndex === -1) {
      return { success: false, error: 'Proyecto no encontrado' };
    }

    mockProjects.splice(projectIndex, 1);
    
    return { success: true };
  },

  // Obtener estadísticas para administrador
  async getAdminStats() {
    await delay(400);
    
    const stats = {
      totalProjects: mockProjects.length,
      completedProjects: mockProjects.filter(p => p.status === 'completed').length,
      inProgressProjects: mockProjects.filter(p => p.status === 'in_progress').length,
      totalUsers: [...new Set(mockProjects.map(p => p.userId))].length,
      objectivesStats: {}
    };

    // Estadísticas por objetivo
    mockProjects.forEach(project => {
      if (!stats.objectivesStats[project.objectiveId]) {
        stats.objectivesStats[project.objectiveId] = {
          name: project.objectiveName,
          total: 0,
          completed: 0
        };
      }
      stats.objectivesStats[project.objectiveId].total++;
      if (project.status === 'completed') {
        stats.objectivesStats[project.objectiveId].completed++;
      }
    });

    return { success: true, data: stats };
  }
};
