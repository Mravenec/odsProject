import { fichaSodsiToPayload, normalizeFichaSodsiFromSnapshot } from './sodsiFichaUtils';
import { OBJETIVO_SERVICES_MAP as SERVICES_MAP } from '../hooks/objetivoServicesMap';

export { SERVICES_MAP };

export const SDG_INDICATORS_CATALOG = {
  '1.2.1': 'Proporción de la población que vive por debajo del umbral nacional de pobreza',
  '1.4.1': 'Proporción de la población que vive en hogares con acceso a los servicios básicos',
  '2.1.1': 'Prevalencia de la subalimentación',
  '2.2.1': 'Prevalencia del retraso del crecimiento entre los niños menores de 5 años',
  '3.1.1': 'Tasa de mortalidad materna',
  '3.2.1': 'Tasa de mortalidad de niños menores de 5 años',
  '3.3.1': 'Número de nuevas infecciones por el VIH por cada 1.000 personas',
  '4.1.1': 'Proporción de niños y jóvenes que alcanzan un nivel mínimo de competencia en lectura y matemáticas',
  '4.3.1': 'Tasa de participación de los jóvenes y adultos en la enseñanza y formación académica',
  '5.1.1': 'Existencia de marcos jurídicos para promover la igualdad y la no discriminación',
  '5.5.1': 'Proporción de escaños ocupados por mujeres en los parlamentos nacionales',
  '6.1.1': 'Proporción de la población que dispone de servicios de agua potable',
  '6.2.1': 'Proporción de la población que utiliza servicios de saneamiento gestionados de forma segura',
  '7.1.1': 'Proporción de la población que tiene acceso a la electricidad',
  '7.2.1': 'Cuota de la energía renovable en el consumo final total de energía',
  '8.1.1': 'Tasa de crecimiento anual del PIB real por persona empleada',
  '8.5.1': 'Ingreso por hora medio de empleadas y empleados',
  '9.1.1': 'Proporción de la población rural que vive a menos de 2 km de una carretera transitable',
  '9.2.1': 'Valor añadido de la industria manufacturera como proporción del PIB',
  '10.1.1': 'Tasas de crecimiento del gasto o los ingresos de los hogares por habitante',
  '10.4.1': 'Proporción del PIB que corresponde a los ingresos de los trabajadores',
  '11.1.1': 'Proporción de la población urbana que vive en barrios marginales',
  '11.3.1': 'Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población',
  '13.1.1': 'Número de personas muertas, desaparecidas y afectadas directamente por desastres',
  '16.1.1': 'Número de víctimas de homicidio doloso por cada 100.000 habitantes',
  '16.5.1': 'Proporción de personas que han tenido al menos un contacto con un funcionario público y que han pagado un soborno',
};

const normalizeGeoName = (s) => (s || '')
  .trim()
  .toLowerCase()
  .normalize('NFD')
  .replace(/[\u0300-\u036f]/g, '');

export function findGeoByName(list, nombre) {
  if (!nombre || !list?.length) return null;
  const target = normalizeGeoName(nombre);
  return list.find((x) => normalizeGeoName(x.nombre) === target) || null;
}

/**
 * Completa área y responsable desde sedeId / dueño / sesión cuando el snapshot no trae nombres.
 */
export function enrichInstitutionalFields(formData, {
  sedeId,
  ownerUserId,
  catalogSedes,
  academicPersonnel,
  currentUser,
}) {
  const next = { ...formData };

  if (!next.area && sedeId != null && catalogSedes?.length) {
    const sede = catalogSedes.find((s) => Number(s.id) === Number(sedeId));
    if (sede?.nombre) next.area = sede.nombre;
  }

  if (!next.responsable && ownerUserId != null && academicPersonnel?.length) {
    const owner = academicPersonnel.find((p) => Number(p.id) === Number(ownerUserId));
    if (owner?.fullName) {
      next.responsable = owner.fullName;
      if (!next.area && owner.sede) next.area = owner.sede;
    }
  }

  if (next.responsable && !next.area && academicPersonnel?.length) {
    const byName = academicPersonnel.find((p) => p.fullName === next.responsable);
    if (byName?.sede) next.area = byName.sede;
  }

  if (currentUser) {
    if (!next.responsable) {
      next.responsable = currentUser.fullName || currentUser.name || '';
    }
    if (!next.area && catalogSedes?.length) {
      const sid = sedeId ?? currentUser.sedeId;
      const sede = catalogSedes.find((s) => Number(s.id) === Number(sid));
      if (sede?.nombre) next.area = sede.nombre;
    }
  }

  return next;
}

export function isStep1Complete(formData, { isEditMode = false } = {}) {
  if (!formData.name?.trim() || !formData.startDate || !formData.endDate) return false;
  if (!formData.area?.trim() || !formData.responsable?.trim()) return false;
  const hasGeoIds = Boolean(formData.provinciaId && formData.cantonId && formData.distritoId);
  const hasGeoNames = Boolean(
    formData.provinciaNombre?.trim()
    && formData.cantonNombre?.trim()
    && formData.distritoNombre?.trim()
  );
  if (isEditMode) return hasGeoIds || hasGeoNames;
  return hasGeoIds;
}

/** Edición: basta con proyecto cargado para ir a indicadores/metas sin rellenar paso 1. */
export function canAdvanceToIndicatorsStep(formData) {
  return Boolean(
    formData.name?.trim()
    && (formData.selectedOds?.length > 0 || formData.indicators?.length > 0)
  );
}

function strOrForm(formVal, baselineVal) {
  const s = formVal != null ? String(formVal).trim() : '';
  return s || (baselineVal != null ? String(baselineVal).trim() : '') || null;
}

export function resolveSedeIdFromArea(area, catalogSedes, fallbackSedeId = 1) {
  if (!area || !catalogSedes?.length) return fallbackSedeId;
  const sede = catalogSedes.find((s) => s.nombre === area);
  return sede?.id ?? fallbackSedeId;
}

const emptyFormData = () => ({
  name: '',
  description: '',
  area: '',
  responsable: '',
  startDate: '',
  endDate: '',
  provinciaId: '',
  cantonId: '',
  distritoId: '',
  provinciaNombre: '',
  cantonNombre: '',
  distritoNombre: '',
  selectedOds: [],
  primaryOds: null,
  indicators: [],
});

/**
 * Mapea GET /planificacion/editable → shape de ProjectCreationPage.
 */
export function snapshotToEditorState(snapshot) {
  const p = snapshot?.proyecto || {};
  const indicadores = Array.isArray(snapshot?.indicadores) ? snapshot.indicadores : [];
  const odsIds = Array.isArray(snapshot?.odsIds) ? snapshot.odsIds : [];

  const formData = {
    ...emptyFormData(),
    name: p.nombreProyecto || '',
    description: p.descripcion || '',
    responsable: p.responsableNombre || '',
    startDate: p.fechaInicio ? String(p.fechaInicio).slice(0, 10) : '',
    endDate: p.fechaFin ? String(p.fechaFin).slice(0, 10) : '',
    provinciaNombre: p.locationProvince || '',
    cantonNombre: p.locationCanton || '',
    distritoNombre: p.locationDistrict || '',
    selectedOds: odsIds.map(Number).filter((n) => !Number.isNaN(n)),
    primaryOds: snapshot?.primaryOdsId != null ? Number(snapshot.primaryOdsId) : (odsIds[0] ?? null),
    indicators: [],
  };

  const indicatorConfigs = {};
  const indicatorMetadata = {};
  // Catálogo completo se carga aparte (getIndicators(0)); aquí solo vínculos reales.
  const availableIndicators = {};

  for (const ind of indicadores) {
    const code = ind.codigo || ind.code;
    if (!code) continue;
    formData.indicators.push(code);
    indicatorMetadata[code] = {
      masterId: ind.indicadorMasterId,
      proyectoIndicadorId: ind.proyectoIndicadorId,
      description: SDG_INDICATORS_CATALOG[code] || `Indicador ${code}`,
      unit: ind.metaUnidad || 'unidad',
    };
    indicatorConfigs[code] = {
      formula: ind.formulaCustom || '',
      goal: {
        value: ind.metaValor != null ? Number(ind.metaValor) : 0,
        unit: ind.metaUnidad || 'unidad',
        name: ind.metaNombre || '',
      },
      parameters: (ind.parametros || []).map((param) => ({
        id: param.id,
        name: param.nombreParametro || '',
        variable: param.nombreVariable || param.nombreParametro || '',
        type: param.tipoDato || 'Decimal',
      })),
    };
  }

  return {
    formData,
    indicatorConfigs,
    indicatorMetadata,
    availableIndicators,
    fichaSodsi: normalizeFichaSodsiFromSnapshot(snapshot?.fichaSodsi),
    proyecto: p,
    userId: p.usuarioId,
    sedeId: p.sedeId,
    status: p.estado,
  };
}

/**
 * Arma payload PUT /api/projects/{id}/full desde el estado del editor.
 */
export function editorStateToUpdatePayload({
  projectId,
  actorUserId,
  actorRole,
  formData,
  indicatorConfigs,
  indicatorMetadata,
  sedeId,
  proyectoBaseline = null,
  fichaSodsi = null,
}) {
  const b = proyectoBaseline || {};
  const odsSet = new Set();
  if (Array.isArray(formData.selectedOds)) {
    for (const odsId of formData.selectedOds) {
      const n = parseInt(odsId, 10);
      if (!Number.isNaN(n)) odsSet.add(n);
    }
  }

  const indicadoresPayload = [];
  for (const code of formData.indicators || []) {
    const meta = indicatorMetadata?.[code] || {};
    const config = indicatorConfigs?.[code] || {};
    if (!meta.masterId) continue;
    const odsNum = parseInt(String(code).split('.')[0], 10);
    odsSet.add(odsNum);
    indicadoresPayload.push({
      proyectoIndicadorId: meta.proyectoIndicadorId ?? null,
      odsId: odsNum,
      indicadorMasterId: meta.masterId,
      metaValor: parseFloat(config.goal?.value) || 0,
      metaUnidad: config.goal?.unit || meta.unit || 'unidad',
      metaNombre: config.goal?.name || null,
      formulaCustom: config.formula || null,
      parametros: (Array.isArray(config.parameters) ? config.parameters : [])
        .filter((p) => p?.name?.trim())
        .map((p) => ({
          id: p.id ?? undefined,
          nombreParametro: p.name.trim(),
          nombreVariable: (p.variable || p.name).trim(),
          tipoDato: p.type || 'Decimal',
        })),
    });
  }

  return {
    actorUserId,
    actorRole,
    proyecto: {
      nombreProyecto: strOrForm(formData.name, b.nombreProyecto),
      descripcion: strOrForm(formData.description, b.descripcion),
      fechaInicio: formData.startDate || b.fechaInicio || null,
      fechaFin: formData.endDate || b.fechaFin || null,
      metaGeneral: (formData.description?.substring(0, 100) || b.metaGeneral) ?? null,
      responsableNombre: strOrForm(formData.responsable, b.responsableNombre),
      locationProvince: strOrForm(formData.provinciaNombre, b.locationProvince),
      locationCanton: strOrForm(formData.cantonNombre, b.locationCanton),
      locationDistrict: strOrForm(formData.distritoNombre, b.locationDistrict),
      sedeId: sedeId || b.sedeId || 1,
    },
    odsIds: Array.from(odsSet),
    primaryOdsId: formData.primaryOds || formData.selectedOds?.[0] || Array.from(odsSet)[0] || null,
    indicadores: indicadoresPayload,
    fichaSodsi: fichaSodsiToPayload(fichaSodsi),
    _projectId: projectId,
  };
}

export function validateEditorBeforeSave(formData, indicatorMetadata, fichaSodsi) {
  const missing = (formData.indicators || []).filter((code) => {
    const meta = indicatorMetadata?.[code];
    return !meta?.masterId;
  });
  if (missing.length > 0) {
    return { ok: false, message: `Falta catálogo para: ${missing.join(', ')}` };
  }
  if (!formData.name?.trim()) {
    return { ok: false, message: 'El nombre del proyecto es obligatorio.' };
  }
  if (!formData.selectedOds?.length) {
    return { ok: false, message: 'Seleccione al menos un ODS.' };
  }
  const benefIds = fichaSodsi?.beneficiarioValorIds || [];
  if (!benefIds.length) {
    return { ok: false, message: 'Seleccioná al menos un sector beneficiario en el paso 1.' };
  }
  return { ok: true };
}
