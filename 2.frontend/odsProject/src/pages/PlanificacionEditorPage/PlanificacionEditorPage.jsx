import React, { useState, useEffect, useMemo, useRef } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { ArrowLeft, Check, ChevronRight } from 'lucide-react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useCatalog } from '../../hooks/useCatalog';
import { useGeo } from '../../hooks/useGeo.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import usePlanificacionEditor from '../../hooks/usePlanificacionEditor';
import useSodsiCatalogs from '../../hooks/useSodsiCatalogs';
import ProjectPlanificacionWizard from '../../components/projects/ProjectPlanificacionWizard';
import IndicatorConfigModal from '../../components/projects/IndicatorConfigModal/IndicatorConfigModal';
import {
  enrichInstitutionalFields,
  findGeoByName,
  resolveSedeIdFromArea,
  canAdvanceToIndicatorsStep,
} from '../../utils/planificacionEditorUtils';
import {
  collectStep1FieldErrors,
  collectStep2FieldErrors,
  hasFieldErrors,
  step2ValidationMessage,
} from '../../utils/planificacionValidation';
import { resolveRegionMideplan } from '../../utils/sodsiRegionUtils';
import '../ProjectCreationPage/ProjectCreationPage.css';

const PlanificacionEditorPage = () => {
  const { projectId } = useParams();
  const navigate = useNavigate();
  const { user, loading: authLoading, getSedes, getActiveUsers } = useAuth();
  const perms = usePermissions();
  const { odsList } = useCatalog();
  const editor = usePlanificacionEditor(projectId);
  const sodsiCatalogs = useSodsiCatalogs({ enabled: !editor.loading });
  const geoHydratedRef = useRef(false);

  const {
    provincias, cantones, distritos, fetchProvincias, fetchCantones, fetchDistritos,
  } = useGeo();

  const [configuringIndicator, setConfiguringIndicator] = useState(null);
  const [catalogSedes, setCatalogSedes] = useState([]);
  const [academicPersonnel, setAcademicPersonnel] = useState([]);
  const [loadingResources, setLoadingResources] = useState(true);
  const [validationErrors, setValidationErrors] = useState({});

  const clearValidationKey = (key) => {
    setValidationErrors((prev) => {
      if (!prev[key] && !(key === 'indicators' && prev.odsWithoutIndicators)) return prev;
      const next = { ...prev };
      delete next[key];
      if (key === 'indicatorConfigs') delete next.missingIndicatorCodes;
      if (key === 'indicators') delete next.odsWithoutIndicators;
      return next;
    });
  };

  useEffect(() => {
    const loadCatalogs = async () => {
      setLoadingResources(true);
      try {
        const [sedesRes, personnelRes] = await Promise.all([getSedes(), getActiveUsers()]);
        if (sedesRes.success) setCatalogSedes(sedesRes.data);
        if (personnelRes.success) setAcademicPersonnel(personnelRes.data);
      } finally {
        setLoadingResources(false);
      }
    };
    loadCatalogs();
  }, [getSedes, getActiveUsers]);

  useEffect(() => { fetchProvincias(); }, [fetchProvincias]);
  useEffect(() => {
    geoHydratedRef.current = false;
  }, [projectId]);
  useEffect(() => {
    if (editor.formData.provinciaId) fetchCantones(editor.formData.provinciaId);
  }, [editor.formData.provinciaId, fetchCantones]);
  useEffect(() => {
    if (editor.formData.cantonId) fetchDistritos(editor.formData.cantonId);
  }, [editor.formData.cantonId, fetchDistritos]);

  const isGestorOwner = perms.role === 'gestor'
    && editor.ownerUserId != null
    && Number(editor.ownerUserId) === Number(user?.id);

  // Área y responsable desde sedeId / dueño / sesión (gestor no usa active-users).
  useEffect(() => {
    if (editor.loading || !editor.formData.name) return;
    if (!catalogSedes.length && !academicPersonnel.length && !isGestorOwner) return;

    editor.setFormData((prev) => {
      const enriched = enrichInstitutionalFields(prev, {
        sedeId: editor.sedeId,
        ownerUserId: editor.ownerUserId,
        catalogSedes,
        academicPersonnel,
        currentUser: isGestorOwner ? user : null,
      });
      if (enriched.area === prev.area && enriched.responsable === prev.responsable) return prev;
      return enriched;
    });
  }, [
    editor.loading, editor.formData.name, editor.sedeId, editor.ownerUserId,
    catalogSedes, academicPersonnel, isGestorOwner, user,
  ]);

  useEffect(() => {
    if (editor.loading || loadingResources || !editor.formData.area) return;
    const sid = resolveSedeIdFromArea(editor.formData.area, catalogSedes, editor.sedeId);
    if (sid && sid !== editor.sedeId) editor.setSedeId(sid);
  }, [editor.loading, loadingResources, editor.formData.area, catalogSedes, editor.sedeId]);

  // Provincia / cantón / distrito: nombres del snapshot → IDs del catálogo geo.
  useEffect(() => {
    if (geoHydratedRef.current || editor.loading || !provincias.length) return;
    const { provinciaNombre, cantonNombre, distritoNombre, provinciaId, cantonId, distritoId } = editor.formData;
    if (!provinciaNombre && !cantonNombre && !distritoNombre) return;
    if (provinciaId && cantonId && distritoId) {
      geoHydratedRef.current = true;
      return;
    }

    let cancelled = false;
    (async () => {
      const updates = {};
      let provId = provinciaId;
      if (!provId && provinciaNombre) {
        const prov = findGeoByName(provincias, provinciaNombre);
        if (prov) {
          provId = String(prov.id);
          updates.provinciaId = provId;
          updates.provinciaNombre = prov.nombre;
        }
      }
      if (provId && cantonNombre && !cantonId) {
        const cantonesList = await fetchCantones(provId);
        if (cancelled) return;
        const cant = findGeoByName(cantonesList, cantonNombre);
        if (cant) {
          updates.cantonId = String(cant.id);
          updates.cantonNombre = cant.nombre;
          if (distritoNombre && !distritoId) {
            const distritosList = await fetchDistritos(cant.id);
            if (cancelled) return;
            const dist = findGeoByName(distritosList, distritoNombre);
            if (dist) {
              updates.distritoId = String(dist.id);
              updates.distritoNombre = dist.nombre;
            }
          }
        }
      }
      if (!cancelled && Object.keys(updates).length > 0) {
        editor.setFormData((prev) => ({ ...prev, ...updates }));
      }
      geoHydratedRef.current = true;
    })();

    return () => { cancelled = true; };
  }, [
    editor.loading,
    provincias,
    editor.formData.provinciaNombre,
    editor.formData.cantonNombre,
    editor.formData.distritoNombre,
    editor.formData.provinciaId,
    editor.formData.cantonId,
    editor.formData.distritoId,
    fetchCantones,
    fetchDistritos,
  ]);

  const gestorProfile = useMemo(() => {
    if (!isGestorOwner || !user) return null;
    const sedeNombre = user.sedeNombre
      || catalogSedes.find((s) => Number(s.id) === Number(user.sedeId))?.nombre
      || '';
    return {
      fullName: user.fullName || user.name,
      contacto: user.contacto
        || [user.fullName, user.email, user.telefonoContacto].filter(Boolean).join(' - '),
      sedeNombre,
      areaNombre: user.areaNombre || '',
      dependenciaNombre: user.dependenciaNombre || '',
      rolDependenciaNombre: user.rolDependenciaNombre || '',
    };
  }, [isGestorOwner, user, catalogSedes]);

  const lockGestorInstitutionalFields = Boolean(isGestorOwner && gestorProfile);

  const regionMideplanNombre = useMemo(
    () => resolveRegionMideplan(editor.formData.provinciaNombre, sodsiCatalogs.catalogs),
    [editor.formData.provinciaNombre, sodsiCatalogs.catalogs],
  );

  const canGoToStep2 = !editor.loading && canAdvanceToIndicatorsStep(editor.formData);

  const filteredPersonnel = useMemo(() => {
    if (!editor.formData.area) return academicPersonnel;
    const bySede = academicPersonnel.filter((p) => p.sede === editor.formData.area);
    const current = editor.formData.responsable;
    if (current && !bySede.some((p) => p.fullName === current)) {
      const match = academicPersonnel.find((p) => p.fullName === current);
      if (match) return [match, ...bySede];
    }
    return bySede;
  }, [academicPersonnel, editor.formData.area, editor.formData.responsable]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    clearValidationKey(name);
    editor.setFormData((prev) => {
      const next = { ...prev, [name]: value };
      if (name === 'area' && !lockGestorInstitutionalFields) {
        next.responsable = '';
        clearValidationKey('responsable');
      }
      return next;
    });
    if (name === 'area') {
      const sid = resolveSedeIdFromArea(value, catalogSedes, editor.sedeId);
      if (sid) editor.setSedeId(sid);
    }
  };

  const handleGeoChange = (e) => {
    const { name, value } = e.target;
    clearValidationKey(name);
    if (name === 'provinciaId') {
      clearValidationKey('cantonId');
      clearValidationKey('distritoId');
      const nombre = provincias.find((p) => p.id === value)?.nombre || '';
      editor.setFormData((prev) => ({
        ...prev, provinciaId: value, provinciaNombre: nombre, cantonId: '', distritoId: '',
      }));
    } else if (name === 'cantonId') {
      clearValidationKey('distritoId');
      const nombre = cantones.find((c) => c.id === value)?.nombre || '';
      editor.setFormData((prev) => ({
        ...prev, cantonId: value, cantonNombre: nombre, distritoId: '',
      }));
    } else if (name === 'distritoId') {
      const nombre = distritos.find((d) => d.id === value)?.nombre || '';
      editor.setFormData((prev) => ({ ...prev, distritoId: value, distritoNombre: nombre }));
    }
  };

  const handleResponsableChange = (e) => {
    clearValidationKey('responsable');
    const selectedValue = e.target.value;
    const staffMember = academicPersonnel.find((p) => p.fullName === selectedValue);
    editor.setFormData((prev) => ({
      ...prev,
      responsable: selectedValue,
      area: staffMember?.sede || prev.area,
    }));
  };

  const toggleOds = (odsId) => {
    const prefix = `${odsId}.`;
    const isCurrentlySelected = editor.formData.selectedOds.includes(odsId);

    if (isCurrentlySelected) {
      const removedCodes = (editor.formData.indicators || []).filter((code) =>
        String(code).startsWith(prefix)
      );
      if (removedCodes.length > 0) {
        editor.setIndicatorConfigs((prev) => {
          const next = { ...prev };
          removedCodes.forEach((code) => { delete next[code]; });
          return next;
        });
        editor.setIndicatorMetadata((prev) => {
          const next = { ...prev };
          removedCodes.forEach((code) => { delete next[code]; });
          return next;
        });
      }
    }

    editor.setFormData((prev) => {
      const isSelected = prev.selectedOds.includes(odsId);
      const newSelected = isSelected
        ? prev.selectedOds.filter((id) => id !== odsId)
        : [...prev.selectedOds, odsId];
      const newIndicators = isSelected
        ? (prev.indicators || []).filter((code) => !String(code).startsWith(prefix))
        : prev.indicators;
      return {
        ...prev,
        selectedOds: newSelected,
        indicators: newIndicators,
        primaryOds: newSelected.length > 0 ? (prev.primaryOds && newSelected.includes(prev.primaryOds) ? prev.primaryOds : newSelected[0]) : null,
      };
    });
  };

  const toggleIndicator = (code) => {
    editor.setFormData((prev) => {
      const isSelected = prev.indicators.includes(code);
      return {
        ...prev,
        indicators: isSelected
          ? prev.indicators.filter((i) => i !== code)
          : [...prev.indicators, code],
      };
    });
    if (editor.formData.indicators.includes(code)) {
      editor.setIndicatorConfigs((prev) => {
        const next = { ...prev };
        delete next[code];
        return next;
      });
      editor.setIndicatorMetadata((prev) => {
        if (!prev[code]?.proyectoIndicadorId) return prev;
        const next = { ...prev };
        next[code] = { ...next[code], proyectoIndicadorId: undefined };
        return next;
      });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (editor.currentStep === 1) {
      if (!canAdvanceToIndicatorsStep(editor.formData)) {
        alert('Espere a que cargue el proyecto o complete el nombre.');
        return;
      }
      const step1Errors = collectStep1FieldErrors(editor.formData, editor.fichaSodsi, {
        lockGestorInstitutionalFields,
      });
      setValidationErrors(step1Errors);
      if (hasFieldErrors(step1Errors)) {
        alert('Complete los campos marcados en rojo antes de continuar.');
        return;
      }
      editor.setCurrentStep(2);
      window.scrollTo(0, 0);
      return;
    }
    const step2Errors = collectStep2FieldErrors(editor.formData, editor.indicatorConfigs);
    setValidationErrors(step2Errors);
    if (hasFieldErrors(step2Errors)) {
      alert(step2ValidationMessage(step2Errors));
      return;
    }
    const result = await editor.save({ catalogSedes });
    if (!result.success) {
      alert(result.error || 'No se pudo guardar');
      return;
    }
    const errs = result.errores || result.data?.errores || [];
    if (errs.length > 0) {
      alert(`Guardado con advertencias:\n${errs.map((x) => `• ${x.error}`).join('\n')}`);
    }
    navigate(`/projects/${projectId}/results`);
  };

  if (authLoading || !user?.id) {
    return (
      <div className="global-loader-container">
        <div className="loader" />
        <p>Sincronizando portal...</p>
      </div>
    );
  }

  if (editor.loading) {
    return (
      <div className="global-loader-container">
        <div className="loader" />
        <p>Cargando planificación editable...</p>
      </div>
    );
  }

  if (editor.forbidden) {
    navigate('/forbidden', { replace: true });
    return null;
  }

  if (editor.error && !editor.formData.name) {
    return (
      <div className="project-creation-page fade-in">
        <div className="error-container" style={{ padding: '2rem' }}>
          <h2>No se puede editar</h2>
          <p>{editor.error}</p>
          <button type="button" className="btn-premium btn-primary" onClick={() => navigate(`/projects/${projectId}/results`)}>
            Volver al proyecto
          </button>
        </div>
      </div>
    );
  }

  const { currentStep, setCurrentStep, formData, saving } = editor;

  return (
    <div className="project-creation-page fade-in">
      <header className="page-header">
        <div className="header-left">
          <button
            type="button"
            onClick={() => {
              if (currentStep === 1) navigate(`/projects/${projectId}/results`);
              else setCurrentStep(currentStep - 1);
            }}
            className="btn-back"
          >
            <ArrowLeft size={20} />
          </button>
          <h1>
            {currentStep === 1 && 'Editar planificación'}
            {currentStep === 2 && 'Indicadores y metas'}
          </h1>
        </div>

        <div className="stepper">
          <div className={`step ${currentStep >= 1 ? 'active' : ''} ${currentStep > 1 ? 'completed' : ''}`}>
            {currentStep > 1 ? <Check size={20} /> : '1'}
          </div>
          <div className={`step ${currentStep >= 2 ? 'active' : ''}`}>2</div>
        </div>
      </header>

      <main className="form-card">
        <form onSubmit={handleSubmit} noValidate>
          <ProjectPlanificacionWizard
              mode="edit"
              currentStep={currentStep}
              formData={formData}
              onInputChange={handleInputChange}
              onGeoChange={handleGeoChange}
              onResponsableChange={handleResponsableChange}
              provincias={provincias}
              cantones={cantones}
              distritos={distritos}
              catalogSedes={catalogSedes}
              filteredPersonnel={filteredPersonnel}
              loadingResources={loadingResources}
              lockGestorInstitutionalFields={lockGestorInstitutionalFields}
              gestorProfile={gestorProfile}
              regionMideplanNombre={regionMideplanNombre}
              beneficiarioValorIds={editor.fichaSodsi.beneficiarioValorIds}
              onBeneficiariosChange={(ids) => {
                clearValidationKey('beneficiarios');
                editor.setFichaSodsi((prev) => ({ ...prev, beneficiarioValorIds: ids }));
              }}
              fichaSodsi={editor.fichaSodsi}
              onFichaSodsiChange={(patch) => editor.setFichaSodsi((prev) => ({ ...prev, ...patch }))}
              sodsiCatalogs={sodsiCatalogs.catalogs}
              sodsiCatalogsLoading={sodsiCatalogs.loading}
              onSodsiCatalogRefresh={sodsiCatalogs.reload}
              createBeneficiarioValor={sodsiCatalogs.createBeneficiarioValor}
              odsList={odsList}
              selectedOds={formData.selectedOds}
              onToggleOds={(id) => {
                clearValidationKey('ods');
                clearValidationKey('indicators');
                clearValidationKey('indicatorConfigs');
                toggleOds(id);
              }}
              indicators={formData.indicators}
              onToggleIndicator={(code) => {
                clearValidationKey('indicators');
                clearValidationKey('indicatorConfigs');
                toggleIndicator(code);
              }}
              indicatorMetadata={editor.indicatorMetadata}
              indicatorConfigs={editor.indicatorConfigs}
              onConfigureIndicator={setConfiguringIndicator}
              availableIndicators={editor.availableIndicators}
              loadingMetadata={editor.loadingMetadata}
              expandedOds={editor.expandedOds}
              onToggleExpandedOds={(odsId) => editor.setExpandedOds(editor.expandedOds === odsId ? null : odsId)}
              validationErrors={validationErrors}
            />

          <div className="form-actions">
            <button
              type="button"
              className="btn-premium btn-secondary"
              onClick={() => {
              if (currentStep === 1) navigate(`/projects/${projectId}/results`);
              else setCurrentStep(currentStep - 1);
            }}
            >
              {currentStep === 1 ? 'Cancelar' : 'Anterior'}
            </button>
            <button
              type="submit"
              className="btn-premium btn-primary"
              disabled={saving || (currentStep === 1 && !canGoToStep2)}
            >
              {currentStep === 1 ? (
                <>Siguiente <ChevronRight size={18} /></>
              ) : (
                saving ? 'Guardando...' : 'Guardar cambios'
              )}
            </button>
          </div>
        </form>
      </main>

      {configuringIndicator && (() => {
        const _meta = editor.indicatorMetadata[configuringIndicator] || {};
        const _ind = {
          codigo: configuringIndicator,
          code: configuringIndicator,
          nombre: _meta.description || configuringIndicator,
        };
        return (
          <IndicatorConfigModal
            indicator={_ind}
            existingConfig={editor.indicatorConfigs[configuringIndicator]}
            onSave={(config) => {
              clearValidationKey('indicatorConfigs');
              clearValidationKey('indicators');
              editor.setIndicatorConfigs((prev) => ({ ...prev, [configuringIndicator]: config }));
              setConfiguringIndicator(null);
            }}
            onClose={() => setConfiguringIndicator(null)}
          />
        );
      })()}
    </div>
  );
};

export default PlanificacionEditorPage;
