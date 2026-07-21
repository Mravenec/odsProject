/**
 * Validación visual (campos en rojo) + gates de avance/guardado
 * para creación y edición de planificación.
 */

function indicatorBelongsToOds(code, odsId) {
  return String(code).startsWith(`${odsId}.`);
}

function isIndicatorConfigIncomplete(cfg) {
  if (!cfg) return true;
  if (!String(cfg.formula || '').trim()) return true;
  if (cfg.goal?.value === undefined || cfg.goal?.value === null || cfg.goal?.value === '') return true;
  if (!String(cfg.goal?.unit || '').trim()) return true;
  if (!Array.isArray(cfg.parameters) || cfg.parameters.length === 0) return true;
  const unnamed = cfg.parameters.some((p) => !String(p?.name || '').trim());
  if (unnamed) return true;
  return false;
}

export function collectStep1FieldErrors(formData, fichaSodsi, { lockGestorInstitutionalFields = false } = {}) {
  const errors = {};
  // Campos que alimentan la matriz SODSI del consultor (o ficha del proyecto) — no dejar en blanco.
  if (!String(formData?.name || '').trim()) errors.name = true;
  if (!String(formData?.description || '').trim()) errors.description = true;
  if (!formData?.startDate) errors.startDate = true;
  if (!formData?.endDate) errors.endDate = true;
  if (!lockGestorInstitutionalFields) {
    if (!formData?.area) errors.area = true;
    if (!formData?.responsable) errors.responsable = true;
  }
  if (!formData?.provinciaId) errors.provinciaId = true;
  if (!formData?.cantonId) errors.cantonId = true;
  if (!formData?.distritoId) errors.distritoId = true;
  if (!(fichaSodsi?.beneficiarioValorIds || []).length) errors.beneficiarios = true;
  if (!fichaSodsi?.ejePlanesId) errors.ejePlanesId = true;
  return errors;
}

export function collectStep2FieldErrors(formData, indicatorConfigs = {}) {
  const errors = {};
  const selectedOds = formData?.selectedOds || [];
  const indicators = formData?.indicators || [];
  if (!selectedOds.length) errors.ods = true;

  const odsWithoutIndicators = selectedOds.filter(
    (odsId) => !indicators.some((code) => indicatorBelongsToOds(code, odsId)),
  );
  if (!indicators.length || odsWithoutIndicators.length) {
    errors.indicators = true;
    if (odsWithoutIndicators.length) {
      errors.odsWithoutIndicators = odsWithoutIndicators;
    }
  }

  const missingConfig = indicators.filter((code) => isIndicatorConfigIncomplete(indicatorConfigs[code]));
  if (missingConfig.length) {
    errors.indicatorConfigs = true;
    errors.missingIndicatorCodes = missingConfig;
  }
  return errors;
}

export function step2ValidationMessage(errors) {
  if (!errors) return 'Complete la selección de ODS e indicadores.';
  if (errors.ods) return 'Seleccione al menos un ODS.';
  if (errors.odsWithoutIndicators?.length) {
    return `Cada ODS seleccionado debe tener al menos un indicador. Faltan en ODS: ${errors.odsWithoutIndicators.join(', ')}.`;
  }
  if (errors.indicators) return 'Seleccione y configure al menos un indicador por cada ODS.';
  if (errors.indicatorConfigs) {
    return `Configure fórmula, parámetros, meta y unidad en: ${(errors.missingIndicatorCodes || []).join(', ')}`;
  }
  return 'Complete la selección de ODS e indicadores.';
}

export function hasFieldErrors(errors) {
  if (!errors) return false;
  return Object.keys(errors).some((k) => k !== 'missingIndicatorCodes' && k !== 'odsWithoutIndicators' && errors[k]);
}
