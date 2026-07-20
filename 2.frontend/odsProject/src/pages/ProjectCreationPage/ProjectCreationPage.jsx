import React, { useState, useEffect, useMemo } from 'react';
import { useCatalog } from '../../hooks/useCatalog';
import { useNavigate } from 'react-router-dom';
import { ArrowLeft, ChevronRight, Check } from 'lucide-react';

// Hooks y Servicios
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { useGeo } from '../../hooks/useGeo.jsx';
import { usePermissions } from '../../hooks/usePermissions';


import IndicatorConfigModal from '../../components/projects/IndicatorConfigModal/IndicatorConfigModal';
import ProjectPlanificacionWizard from '../../components/projects/ProjectPlanificacionWizard';
import { useSodsiCatalogs } from '../../hooks/useSodsiCatalogs';
import { useOdsMetadata } from '../../hooks/useOdsMetadata';
import { OBJETIVO_SERVICES_MAP } from '../../hooks/objetivoServicesMap';
import { emptyFichaSodsi } from '../../utils/sodsiFichaUtils';
import { resolveRegionMideplan } from '../../utils/sodsiRegionUtils';
import './ProjectCreationPage.css';

const ProjectCreationPage = () => {
  const { user, loading: authLoading, getSedes, getActiveUsers } = useAuth();
  const perms = usePermissions();
  const navigate = useNavigate();
  const [currentStep, setCurrentStep] = useState(1);
  
  const { 
    createProject, 
    createFullProject,
    loading: projectsLoading, 
    error: projectsError
  } = useProjects();

  const { odsList } = useCatalog();

  const {
    provincias,
    cantones,
    distritos,
    loadingGeo,
    fetchProvincias,
    fetchCantones,
    fetchDistritos
  } = useGeo();

  const [formData, setFormData] = useState({
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
    indicators: []
  });

  const [indicatorConfigs, setIndicatorConfigs] = useState({});
  const [configuringIndicator, setConfiguringIndicator] = useState(null);
  const [expandedOds, setExpandedOds] = useState(null);
  const [fichaSodsi, setFichaSodsi] = useState(emptyFichaSodsi);
  const sodsiCatalogs = useSodsiCatalogs();

  const {
    availableIndicators,
    indicatorMetadata,
    setIndicatorMetadata,
    loadingMetadata,
    hasMetadataForOds,
    loadOdsMetadata,
  } = useOdsMetadata();

  // Catálogos reales
  const [catalogSedes, setCatalogSedes] = useState([]);
  const [academicPersonnel, setAcademicPersonnel] = useState([]);
  const [loadingResources, setLoadingResources] = useState(true);

  // Carga de catálogos institucionales
  useEffect(() => {
    const loadCatalogs = async () => {
      setLoadingResources(true);
      try {
        const [sedesRes, personnelRes] = await Promise.all([
          getSedes(),
          getActiveUsers()
        ]);
        
        if (sedesRes.success) {
          setCatalogSedes(sedesRes.data);
        }
        
        if (personnelRes.success) {
          // Filtrar o mapear personal si es necesario
          setAcademicPersonnel(personnelRes.data);
        }
      } catch (error) {
        console.error('[ProjectCreation] Error cargando catálogos:', error);
      } finally {
        setLoadingResources(false);
      }
    };

    loadCatalogs();
  }, [getSedes, getActiveUsers]);

  const isGestor = perms.role === 'gestor';

  const gestorProfile = useMemo(() => {
    if (!isGestor || !user) return null;
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
  }, [isGestor, user, catalogSedes]);

  const lockGestorInstitutionalFields = Boolean(isGestor && gestorProfile);

  const regionMideplanNombre = useMemo(
    () => resolveRegionMideplan(formData.provinciaNombre, sodsiCatalogs.catalogs),
    [formData.provinciaNombre, sodsiCatalogs.catalogs],
  );

  // Gestor: área y responsable desde perfil autenticado
  useEffect(() => {
    if (!isGestor || !user?.id) return;

    const area = gestorProfile?.sedeNombre
      || catalogSedes.find((s) => Number(s.id) === Number(user.sedeId))?.nombre
      || '';

    const responsable = user.fullName || user.name || '';

    if (!area && !responsable) return;

    setFormData((prev) => {
      const nextArea = area || prev.area;
      const nextResponsable = responsable || prev.responsable;
      if (nextArea === prev.area && nextResponsable === prev.responsable) return prev;
      return { ...prev, area: nextArea, responsable: nextResponsable };
    });
  }, [isGestor, user, gestorProfile, catalogSedes]);

  // Carga inicial de geografía
  useEffect(() => {
    fetchProvincias();
  }, [fetchProvincias]);

  useEffect(() => {
    if (formData.provinciaId) fetchCantones(formData.provinciaId);
  }, [formData.provinciaId, fetchCantones]);

  useEffect(() => {
    if (formData.cantonId) fetchDistritos(formData.cantonId);
  }, [formData.cantonId, fetchDistritos]);

  useEffect(() => {
    if (expandedOds && !loadingMetadata[expandedOds] && !hasMetadataForOds(expandedOds)) {
      loadOdsMetadata(expandedOds);
    }
  }, [expandedOds, loadingMetadata, hasMetadataForOds, loadOdsMetadata]);

  // Filtrado de personal académico según el área seleccionada
  const filteredPersonnel = useMemo(() => {
    if (!formData.area) return academicPersonnel;
    return academicPersonnel.filter(p => p.sede === formData.area);
  }, [academicPersonnel, formData.area]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => {
      const newState = { ...prev, [name]: value };
      
      // Si el usuario cambia el Area manualmente, limpiamos el responsable 
      // para que el filtro surta efecto y no queden datos inconsistentes
      if (name === 'area') {
        newState.responsable = '';
      }
      
      return newState;
    });
  };

  const handleGeoChange = (e) => {
    const { name, value } = e.target;
    let nombre = '';
    if (name === 'provinciaId') {
      nombre = provincias.find(p => p.id === value)?.nombre || '';
      setFormData(prev => ({ ...prev, provinciaId: value, provinciaNombre: nombre, cantonId: '', distritoId: '' }));
    } else if (name === 'cantonId') {
      nombre = cantones.find(c => c.id === value)?.nombre || '';
      setFormData(prev => ({ ...prev, cantonId: value, cantonNombre: nombre, distritoId: '' }));
    } else if (name === 'distritoId') {
      nombre = distritos.find(d => d.id === value)?.nombre || '';
      setFormData(prev => ({ ...prev, distritoId: value, distritoNombre: nombre }));
    }
  };

  const handleResponsableChange = (e) => {
    const selectedValue = e.target.value;
    
    // Buscar los datos del personal seleccionado para auto-asignar la sede
    const staffMember = academicPersonnel.find(p => p.fullName === selectedValue);
    
    setFormData(prev => ({
      ...prev,
      responsable: selectedValue,
      // Si el personal tiene una sede asignada en la BD, se auto-selecciona el área
      area: staffMember?.sede || prev.area
    }));
  };

  const toggleOds = (odsId) => {
    const prefix = `${odsId}.`;
    const isCurrentlySelected = formData.selectedOds.includes(odsId);

    if (isCurrentlySelected) {
      const removedCodes = (formData.indicators || []).filter((code) =>
        String(code).startsWith(prefix)
      );
      if (removedCodes.length > 0) {
        setIndicatorConfigs((prev) => {
          const next = { ...prev };
          removedCodes.forEach((code) => { delete next[code]; });
          return next;
        });
        setIndicatorMetadata((prev) => {
          const next = { ...prev };
          removedCodes.forEach((code) => { delete next[code]; });
          return next;
        });
      }
    }

    setFormData((prev) => {
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
        primaryOds: newSelected.length > 0
          ? (prev.primaryOds && newSelected.includes(prev.primaryOds) ? prev.primaryOds : newSelected[0])
          : null,
      };
    });
  };

  // Guardia de Seguridad
  if (authLoading || !user || !user.id) {
    return (
      <div className="global-loader-container">
        <div className="loader"></div>
        <p>Sincronizando portal...</p>
      </div>
    );
  }

  const toggleIndicator = (indicatorCode) => {
    setFormData(prev => {
      const isSelected = prev.indicators.includes(indicatorCode);
      return {
        ...prev,
        indicators: isSelected
          ? prev.indicators.filter(i => i !== indicatorCode)
          : [...prev.indicators, indicatorCode]
      };
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (currentStep === 1) {
      if (!(fichaSodsi.beneficiarioValorIds || []).length) {
        alert('Seleccioná al menos un beneficiario en el paso 1.');
        return;
      }
      setCurrentStep(2);
      window.scrollTo(0, 0);
      return;
    }

    try {
      // ── S4: Pre-cargar metadata de ODS que aún no se cargaron ──────────────
      const odsNoLoaded = formData.selectedOds.filter(id => !hasMetadataForOds(id));
      if (odsNoLoaded.length > 0) {
        // Cargar en paralelo los ODS sin metadata (más rápido que secuencial)
        await Promise.allSettled(odsNoLoaded.map(id => loadOdsMetadata(id)));
      }

      // ── S4: Validar que todos los indicadores seleccionados tienen masterId ──
      const sinMasterId = formData.indicators.filter(code => {
        const meta = indicatorMetadata[code];
        return !meta || !meta.masterId;
      });

      if (sinMasterId.length > 0) {
        alert(
          `No se pudo cargar el catálogo para los siguientes indicadores:
${sinMasterId.join(', ')}

` +
          'Verifique la conexión con el servidor y vuelva a intentarlo.'
        );
        return;
      }

      if (!(fichaSodsi.beneficiarioValorIds || []).length) {
        alert('Seleccioná al menos un sector beneficiario en el paso 1.');
        return;
      }

      const finalData = {
        ...formData,
        objective: formData.primaryOds,
        indicatorConfigs,
        indicatorMetadata,
        userId: user.id,
        sedeId: user.sedeId,
        fichaSodsi,
      };
      
      const result = await createFullProject(finalData, OBJETIVO_SERVICES_MAP);
      
      if (result.success) {
        // ── S5: feedback granular si hay errores parciales ──
        const hasPartialErrors = result.errores && result.errores.length > 0;
        const hasSkipped       = result.skippedIndicators && result.skippedIndicators.length > 0;

        if (hasPartialErrors || hasSkipped) {
          const lineas = [];
          lineas.push(`Proyecto #${result.proyectoId} creado con ${result.savedIndicators || 0} indicador(es).`);
          if (result.odsVinculados?.length) {
            lineas.push(`ODS vinculados: ${result.odsVinculados.join(', ')}`);
          }
          if (hasSkipped) {
            lineas.push(`\nOmitidos (sin masterId): ${result.skippedIndicators.join(', ')}`);
          }
          if (hasPartialErrors) {
            lineas.push('\nErrores parciales:');
            result.errores.forEach(e => {
              const tag = e.indicadorMasterId ? `ODS ${e.odsId} indicador ${e.indicadorMasterId}` : `Etapa ${e.etapa}`;
              lineas.push(`  • ${tag}: ${e.error}`);
            });
          }
          alert(lineas.join('\n'));
        }
        
        if (perms.canEnterMeasurements) {
          navigate(`/projects/${result.proyectoId || result.data?.id}/evaluation`);
        } else {
          navigate('/dashboard');
        }
      } else {

        // success === false: rollback completo (compensaciones se ejecutaron)
        const errLines = (result.errores || []).map(e =>
          `  • [${e.etapa}] ${e.error}`).join('\n');
        alert(`No se pudo crear el proyecto.\n\n${errLines || 'Error desconocido'}`);
      }
    } catch (err) {
      console.error('[ProjectCreation] Error persistiendo proyecto:', err);
      alert(err.message || 'Error al guardar el proyecto completo. Verifique la consola.');
    }
  };

  return (
    <div className="project-creation-page fade-in">
      <header className="page-header">
        <div className="header-left">
          <button onClick={() => currentStep === 1 ? navigate('/dashboard') : setCurrentStep(1)} className="btn-back">
            <ArrowLeft size={20} />
          </button>
          <h1>{currentStep === 1 ? 'Diseño de Proyecto' : 'Configuración Técnica'}</h1>
        </div>
        
        <div className="stepper">
          <div className={`step ${currentStep >= 1 ? 'active' : ''} ${currentStep > 1 ? 'completed' : ''}`}>
            {currentStep > 1 ? <Check size={20} /> : '1'}
          </div>
          <div className={`step ${currentStep >= 2 ? 'active' : ''}`}>2</div>
        </div>
      </header>

      <main className="form-card">
        <form onSubmit={handleSubmit}>
          <ProjectPlanificacionWizard
            mode="create"
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
            beneficiarioValorIds={fichaSodsi.beneficiarioValorIds}
            onBeneficiariosChange={(ids) => setFichaSodsi((prev) => ({ ...prev, beneficiarioValorIds: ids }))}
            fichaSodsi={fichaSodsi}
            onFichaSodsiChange={(patch) => setFichaSodsi((prev) => ({ ...prev, ...patch }))}
            sodsiCatalogs={sodsiCatalogs.catalogs}
            sodsiCatalogsLoading={sodsiCatalogs.loading}
            onSodsiCatalogRefresh={sodsiCatalogs.reload}
            createBeneficiarioValor={sodsiCatalogs.createBeneficiarioValor}
            odsList={odsList}
            selectedOds={formData.selectedOds}
            onToggleOds={toggleOds}
            indicators={formData.indicators}
            onToggleIndicator={toggleIndicator}
            indicatorMetadata={indicatorMetadata}
            indicatorConfigs={indicatorConfigs}
            onConfigureIndicator={setConfiguringIndicator}
            availableIndicators={availableIndicators}
            loadingMetadata={loadingMetadata}
            expandedOds={expandedOds}
            onToggleExpandedOds={(odsId) => setExpandedOds(expandedOds === odsId ? null : odsId)}
          />

          <div className="form-actions">
            <button 
              type="button" 
              className="btn-premium btn-secondary" 
              onClick={() => (currentStep === 1 ? navigate('/dashboard') : setCurrentStep(1))}
            >
              {currentStep === 1 ? 'Cancelar' : 'Anterior'}
            </button>
            <button 
              type="submit" 
              className="btn-premium btn-primary" 
              disabled={projectsLoading || (currentStep === 2 && formData.selectedOds.length === 0)}
            >
              {currentStep === 1 ? (
                <>Siguiente <ChevronRight size={18} /></>
              ) : (
                projectsLoading ? 'Creando...' : 'Finalizar y Crear Proyecto'
              )}
            </button>
          </div>
        </form>
      </main>

      {configuringIndicator && (() => {
        const _meta = indicatorMetadata[configuringIndicator] || {};
        const _ind = {
          codigo: configuringIndicator,
          code: configuringIndicator,
          nombre: _meta.description || configuringIndicator
        };
        return (
        <IndicatorConfigModal 
          indicator={_ind}
          existingConfig={indicatorConfigs[configuringIndicator]}
          onSave={(config) => {
            setIndicatorConfigs(prev => ({ ...prev, [configuringIndicator]: config }));
            setConfiguringIndicator(null);
          }}
          onClose={() => setConfiguringIndicator(null)}
        />
        );
      })()}
    </div>
  );
};

export default ProjectCreationPage;
