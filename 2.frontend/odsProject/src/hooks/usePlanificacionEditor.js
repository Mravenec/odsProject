import { useState, useCallback, useEffect } from 'react';
import { useAuth } from './useAuth.jsx';
import { projectService } from '../services/projectService';
import { getObjetivoService } from './objetivoServicesMap';
import {
  SDG_INDICATORS_CATALOG,
  snapshotToEditorState,
  editorStateToUpdatePayload,
  validateEditorBeforeSave,
  resolveSedeIdFromArea,
} from '../utils/planificacionEditorUtils';
import { emptyFichaSodsi } from '../utils/sodsiFichaUtils';

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
 * Carga / guarda edición de planificación vía GET editable + PUT /full.
 */
export function usePlanificacionEditor(projectId) {
  const { user } = useAuth();
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [error, setError] = useState(null);
  const [forbidden, setForbidden] = useState(false);
  const [currentStep, setCurrentStep] = useState(1);

  const [formData, setFormData] = useState(emptyFormData);
  const [indicatorConfigs, setIndicatorConfigs] = useState({});
  const [indicatorMetadata, setIndicatorMetadata] = useState({});
  const [availableIndicators, setAvailableIndicators] = useState({});
  const [loadingMetadata, setLoadingMetadata] = useState({});
  const [expandedOds, setExpandedOds] = useState(null);
  const [sedeId, setSedeId] = useState(1);
  const [ownerUserId, setOwnerUserId] = useState(null);
  const [proyectoBaseline, setProyectoBaseline] = useState(null);
  const [fichaSodsi, setFichaSodsi] = useState(emptyFichaSodsi);

  const hasMetadataForOds = useCallback(
    (odsId) => availableIndicators[odsId] && availableIndicators[odsId].length > 0,
    [availableIndicators]
  );

  const loadOdsMetadata = useCallback(async (odsId) => {
    const service = getObjetivoService(odsId);
    if (!service) return;

    setLoadingMetadata((prev) => ({ ...prev, [odsId]: true }));
    try {
      const indicatorsData = await service.getIndicators(0);
      const codes = Object.keys(indicatorsData);

      setAvailableIndicators((prev) => ({ ...prev, [odsId]: codes }));
      setIndicatorMetadata((prev) => {
        const merged = { ...prev };
        codes.forEach((code) => {
          const ind = indicatorsData[code];
          const fallbackDescription = SDG_INDICATORS_CATALOG[code];
          const linkedMeta = prev[code];
          merged[code] = {
            masterId: ind.masterId,
            description: (ind.name && ind.name.length > 5 && !ind.name.includes('Indicador'))
              ? ind.name
              : (fallbackDescription || `Seguimiento de metas técnicas para indicador ${code}`),
            unit: ind.unit || 'unidad',
            ...(linkedMeta?.proyectoIndicadorId
              ? { proyectoIndicadorId: linkedMeta.proyectoIndicadorId }
              : {}),
          };
        });
        return merged;
      });
    } catch (e) {
      console.error(`[usePlanificacionEditor] ODS ${odsId}:`, e);
    } finally {
      setLoadingMetadata((prev) => ({ ...prev, [odsId]: false }));
    }
  }, []);

  const load = useCallback(async () => {
    if (!projectId || !user?.id) return;
    setLoading(true);
    setError(null);
    setForbidden(false);
    try {
      const r = await projectService.getPlanificacionEditable(
        projectId,
        user.id,
        user.role
      );
      if (!r.success) {
        if (r.status === 403) setForbidden(true);
        setError(r.error || 'No se pudo cargar el proyecto');
        return;
      }
      const state = snapshotToEditorState(r.data);
      setFormData(state.formData);
      setIndicatorConfigs(state.indicatorConfigs);
      setIndicatorMetadata(state.indicatorMetadata);
      setAvailableIndicators(state.availableIndicators);
      if (state.sedeId) setSedeId(state.sedeId);
      setOwnerUserId(state.userId ?? null);
      setProyectoBaseline(state.proyecto ?? null);
      setFichaSodsi(state.fichaSodsi ?? emptyFichaSodsi());
    } catch (e) {
      setError(e.message || 'Error de carga');
    } finally {
      setLoading(false);
    }
  }, [projectId, user]);

  useEffect(() => {
    load();
  }, [load]);

  useEffect(() => {
    if (expandedOds && !loadingMetadata[expandedOds] && !hasMetadataForOds(expandedOds)) {
      loadOdsMetadata(expandedOds);
    }
  }, [expandedOds, loadingMetadata, hasMetadataForOds, loadOdsMetadata]);

  useEffect(() => {
    if (loading || !formData.selectedOds?.length) return;
    formData.selectedOds.forEach((odsId) => {
      if (!hasMetadataForOds(odsId) && !loadingMetadata[odsId]) {
        loadOdsMetadata(odsId);
      }
    });
  }, [loading, formData.selectedOds, hasMetadataForOds, loadingMetadata, loadOdsMetadata]);

  const save = useCallback(async (options = {}) => {
    if (!user?.id) return { success: false, error: 'Sesión inválida' };

    const { catalogSedes } = options;

    const odsNoLoaded = (formData.selectedOds || []).filter((id) => !hasMetadataForOds(id));
    if (odsNoLoaded.length > 0) {
      await Promise.allSettled(odsNoLoaded.map((id) => loadOdsMetadata(id)));
    }

    const validation = validateEditorBeforeSave(formData, indicatorMetadata, fichaSodsi, indicatorConfigs);
    if (!validation.ok) {
      return { success: false, error: validation.message };
    }

    setSaving(true);
    setError(null);
    try {
      const effectiveSedeId = resolveSedeIdFromArea(formData.area, catalogSedes, sedeId);

      const payload = editorStateToUpdatePayload({
        projectId,
        actorUserId: user.id,
        actorRole: user.role,
        formData,
        indicatorConfigs,
        indicatorMetadata,
        sedeId: effectiveSedeId,
        proyectoBaseline,
        fichaSodsi,
      });
      const result = await projectService.updateFullProject(projectId, payload);
      if (!result.success) {
        if (result.status === 403) setForbidden(true);
        setError(result.error);
        return result;
      }
      await load();
      return result;
    } catch (e) {
      const msg = e.message || 'Error al guardar';
      setError(msg);
      return { success: false, error: msg };
    } finally {
      setSaving(false);
    }
  }, [
    user, formData, indicatorConfigs, indicatorMetadata, sedeId, proyectoBaseline, fichaSodsi,
    projectId, hasMetadataForOds, loadOdsMetadata, load,
  ]);

  return {
    loading,
    saving,
    error,
    forbidden,
    currentStep,
    setCurrentStep,
    formData,
    setFormData,
    indicatorConfigs,
    setIndicatorConfigs,
    indicatorMetadata,
    setIndicatorMetadata,
    availableIndicators,
    loadingMetadata,
    expandedOds,
    setExpandedOds,
    hasMetadataForOds,
    loadOdsMetadata,
    save,
    reload: load,
    ownerUserId,
    sedeId,
    setSedeId,
    fichaSodsi,
    setFichaSodsi,
  };
}

export default usePlanificacionEditor;
