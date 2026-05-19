/**
 * Formateador de fechas estándar para el proyecto.
 * @param {string|Date} dateString 
 * @param {boolean} longMonth 
 * @returns {string}
 */
export const formatDate = (dateString, longMonth = true) => {
  if (!dateString) return 'N/A';
  return new Date(dateString).toLocaleDateString('es-ES', {
    day: 'numeric',
    month: longMonth ? 'long' : 'short',
    year: 'numeric'
  });
};

/**
 * Formatea nombres de indicadores de CamelCase a texto legible.
 * @param {string} indicator 
 * @returns {string}
 */
export const formatIndicatorName = (indicator) => {
  if (!indicator) return '';
  return indicator
    .replace(/([A-Z])/g, ' $1')
    .replace(/^./, str => str.toUpperCase())
    .trim();
};

/**
 * Obtiene el nombre completo de un objetivo ODS por su ID.
 * @param {number|string} objectiveId 
 * @returns {string}
 */
export const getObjectiveName = (objectiveId) => {
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
  return objectives[objectiveId] || 'Objetivo Desconocido';
};

/**
 * Determina la clase CSS de éxito basada en el porcentaje de logro.
 * @param {number} percentage 
 * @returns {string}
 */
export const getAchievementClass = (percentage) => {
  if (percentage >= 100) return 'success';
  if (percentage >= 75) return 'warning';
  return 'danger';
};

/**
 * Colores oficiales de los Objetivos de Desarrollo Sostenible.
 */
export const odsColors = {
  1: '#E5243B', // Fin de la Pobreza
  2: '#DDA63A', // Hambre Cero
  3: '#4C9F38', // Salud y Bienestar
  4: '#C5192D', // Educación de Calidad
  5: '#FF3A21', // Igualdad de Género
  6: '#26BDE2', // Agua Limpia y Saneamiento
  7: '#FCC30B', // Energía Asequible y No Contaminante
  8: '#A21942', // Trabajo Decente y Crecimiento Económico
  9: '#FD6925', // Industria, Innovación e Infraestructura
  10: '#DD1367', // Reducción de las Desigualdades
  11: '#FD9D24', // Ciudades y Comunidades Sostenibles
  12: '#BF8B2E', // Producción y Consumo Responsables
  13: '#3F7E44', // Acción por el Clima
  14: '#0A97D9', // Vida Submarina
  15: '#56C02B', // Vida de Ecosistemas Terrestres
  16: '#00689D', // Paz, Justicia e Instituciones Sólidas
  17: '#19486A'  // Alianzas para Lograr los Objetivos
};

/**
 * Obtiene el color oficial de un ODS por su ID.
 * @param {number|string} odsId 
 * @returns {string}
 */
export const getOdsColor = (odsId) => {
  return odsColors[odsId] || '#64748B';
};

// ═════════════════════════════════════════════════════════════════════
//  Sprint 18 — Helpers de estado del proyecto
// ═════════════════════════════════════════════════════════════════════

/**
 * Devuelve true si el proyecto está en un estado que prohíbe ediciones.
 * Lo usan los componentes para ocultar botones de edición / upload / delete.
 * El backend y los triggers de BD también validan, pero esto evita la
 * vuelta innecesaria al servidor.
 */
export const isProjectLocked = (project) => {
  if (!project) return false;
  const s = String(project.status || project.estado || '').toLowerCase();
  return s === 'completado' || s === 'cancelado';
};

/**
 * Devuelve true si el proyecto está en revisión (esperando al auditor).
 * En este estado, el gestor NO puede subir más documentos ni editar.
 */
export const isProjectInReview = (project) => {
  if (!project) return false;
  return String(project.status || project.estado || '').toLowerCase() === 'en_revision';
};

/**
 * Sprint 17 — Etiqueta legible del estado, lista para mostrar en pills.
 */
export const getEstadoLabel = (estado) => {
  const s = String(estado || '').toLowerCase();
  const labels = {
    planificacion: 'Planificación',
    activo:        'Activo',
    en_revision:   'En revisión',
    completado:    'Auditado',
    cancelado:     'Cancelado',
  };
  return labels[s] || estado || 'Desconocido';
};

/**
 * Sprint 17 — Devuelve la clase CSS apropiada para un pill de estado.
 * El consumidor solo concatena: `status-pill ${getEstadoClass(estado)}`.
 */
export const getEstadoClass = (estado) => {
  const s = String(estado || '').toLowerCase();
  // Las clases mapean a definiciones en index.css / DashboardPage.css
  return s.replace(/[^a-z_]/g, '') || 'unknown';
};
