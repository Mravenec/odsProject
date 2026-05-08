import React, { useState, useEffect, useMemo } from 'react';
import { useCatalog } from '../../hooks/useCatalog';
import { useNavigate } from 'react-router-dom';
import { 
  ArrowLeft, 
  ChevronRight, 
  ChevronLeft, 
  Check, 
  Settings, 
  Target, 
  Calendar, 
  MapPin, 
  Users, 
  Info,
  Layers,
  LayoutGrid,
  FileText
} from 'lucide-react';

// Hooks y Servicios
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import { useGeo } from '../../hooks/useGeo.jsx';
import { getObjectiveName, getOdsColor, odsColors } from '../../utils/formatters';

// Servicios de Objetivos
import { objetivo01Service } from '../../services/objetivo01Service';
import { objetivo02Service } from '../../services/objetivo02Service';
import { objetivo03Service } from '../../services/objetivo03Service';
import { objetivo04Service } from '../../services/objetivo04Service';
import { objetivo05Service } from '../../services/objetivo05Service';
import { objetivo06Service } from '../../services/objetivo06Service';
import { objetivo07Service } from '../../services/objetivo07Service';
import { objetivo08Service } from '../../services/objetivo08Service';
import { objetivo09Service } from '../../services/objetivo09Service';
import { objetivo10Service } from '../../services/objetivo10Service';
import { objetivo11Service } from '../../services/objetivo11Service';
import { objetivo12Service } from '../../services/objetivo12Service';
import { objetivo13Service } from '../../services/objetivo13Service';
import { objetivo14Service } from '../../services/objetivo14Service';
import { objetivo15Service } from '../../services/objetivo15Service';
import { objetivo16Service } from '../../services/objetivo16Service';
import { objetivo17Service } from '../../services/objetivo17Service';

import IndicatorConfigModal from '../../components/projects/IndicatorConfigModal/IndicatorConfigModal';
import './ProjectCreationPage.css';

// Mapeo dinámico de servicios
const SERVICES_MAP = {
  1: objetivo01Service, 2: objetivo02Service, 3: objetivo03Service, 4: objetivo04Service,
  5: objetivo05Service, 6: objetivo06Service, 7: objetivo07Service, 8: objetivo08Service,
  9: objetivo09Service, 10: objetivo10Service, 11: objetivo11Service, 12: objetivo12Service,
  13: objetivo13Service, 14: objetivo14Service, 15: objetivo15Service, 16: objetivo16Service,
  17: objetivo17Service
};

const SDG_INDICATORS_CATALOG = {
  // ODS 1
  "1.1.1": "Proporción de la población que vive por debajo del umbral internacional de pobreza",
  "1.2.1": "Proporción de la población que vive por debajo del umbral nacional de pobreza",
  "1.4.1": "Proporción de la población que vive en hogares con acceso a los servicios básicos",
  // ODS 2
  "2.1.1": "Prevalencia de la subalimentación",
  "2.2.1": "Prevalencia del retraso del crecimiento entre los niños menores de 5 años",
  // ODS 3
  "3.1.1": "Tasa de mortalidad materna",
  "3.2.1": "Tasa de mortalidad de niños menores de 5 años",
  "3.3.1": "Número de nuevas infecciones por el VIH por cada 1.000 personas",
  // ODS 4
  "4.1.1": "Proporción de niños y jóvenes que alcanzan un nivel mínimo de competencia en lectura y matemáticas",
  "4.3.1": "Tasa de participación de los jóvenes y adultos en la enseñanza y formación académica",
  // ODS 5
  "5.1.1": "Existencia de marcos jurídicos para promover la igualdad y la no discriminación",
  "5.5.1": "Proporción de escaños ocupados por mujeres en los parlamentos nacionales",
  // ODS 6
  "6.1.1": "Proporción de la población que dispone de servicios de agua potable",
  "6.2.1": "Proporción de la población que utiliza servicios de saneamiento gestionados de forma segura",
  // ODS 7
  "7.1.1": "Proporción de la población que tiene acceso a la electricidad",
  "7.2.1": "Cuota de la energía renovable en el consumo final total de energía",
  // ODS 8
  "8.1.1": "Tasa de crecimiento anual del PIB real por persona empleada",
  "8.5.1": "Ingreso por hora medio de empleadas y empleados",
  // ODS 9
  "9.1.1": "Proporción de la población rural que vive a menos de 2 km de una carretera transitable",
  "9.2.1": "Valor añadido de la industria manufacturera como proporción del PIB",
  // ODS 10
  "10.1.1": "Tasas de crecimiento del gasto o los ingresos de los hogares por habitante",
  "10.4.1": "Proporción del PIB que corresponde a los ingresos de los trabajadores",
  // ODS 11
  "11.1.1": "Proporción de la población urbana que vive en barrios marginales",
  "11.3.1": "Relación entre la tasa de consumo de tierras y la tasa de crecimiento de la población",
  // ODS 13
  "13.1.1": "Número de personas muertas, desaparecidas y afectadas directamente por desastres",
  // ODS 16
  "16.1.1": "Número de víctimas de homicidio doloso por cada 100.000 habitantes",
  "16.5.1": "Proporción de personas que han tenido al menos un contacto con un funcionario público y que han pagado un soborno"
};

const ProjectCreationPage = () => {
  const { user, loading: authLoading, getSedes, getActiveUsers } = useAuth();
  const navigate = useNavigate();
  const [currentStep, setCurrentStep] = useState(1);
  
  const { 
    createProject, 
    createFullProject,
    loading: projectsLoading, 
    error: projectsError
  } = useProjects();

  const { } = useCatalog();

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
  
  // Nuevo estado para metadatos reales de la BD
  const [indicatorMetadata, setIndicatorMetadata] = useState({});
  const [availableIndicators, setAvailableIndicators] = useState({}); // Mapeo odsId -> [codes]
  const [loadingMetadata, setLoadingMetadata] = useState({});

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

  // Cargar metadatos cuando se expande un ODS
  useEffect(() => {
    if (expandedOds && !loadingMetadata[expandedOds] && !hasMetadataForOds(expandedOds)) {
      loadOdsMetadata(expandedOds);
    }
  }, [expandedOds]);

  const hasMetadataForOds = (odsId) => {
    return availableIndicators[odsId] && availableIndicators[odsId].length > 0;
  };

  const loadOdsMetadata = async (odsId) => {
    const service = SERVICES_MAP[odsId];
    if (!service) return;

    setLoadingMetadata(prev => ({ ...prev, [odsId]: true }));
    
    try {
      // Usamos el nuevo método estandarizado para obtener todos los indicadores del ODS
      // Pasamos 0 o null como proyectoId para obtener la lista del Master (vía LEFT JOIN)
      const indicatorsData = await service.getIndicators(0);
      
      const newMetadata = {};
      const codes = Object.keys(indicatorsData);
      
      codes.forEach(code => {
        const ind = indicatorsData[code];
        // Merge con catálogo estático para tener descripciones de respaldo si la BD es escueta
        const fallbackDescription = SDG_INDICATORS_CATALOG[code];
        
        newMetadata[code] = {
          masterId: ind.masterId,
          description: (ind.name && ind.name.length > 5 && !ind.name.includes('Indicador')) 
            ? ind.name 
            : (fallbackDescription || `Seguimiento de metas técnicas para indicador ${code}`),
          unit: ind.unit || 'unidad'
        };
      });

      setAvailableIndicators(prev => ({ ...prev, [odsId]: codes }));
      setIndicatorMetadata(prev => ({ ...prev, ...newMetadata }));
    } catch (error) {
      console.error(`[ProjectCreation] Error loading indicators for ODS ${odsId}:`, error);
    } finally {
      setLoadingMetadata(prev => ({ ...prev, [odsId]: false }));
    }
  };

  // Filtrado de personal académico según el área seleccionada
  const filteredPersonnel = useMemo(() => {
    if (!formData.area) return academicPersonnel;
    return academicPersonnel.filter(p => p.sede === formData.area);
  }, [academicPersonnel, formData.area]);

  const getIndicatorsForOds = (odsId) => {
    return availableIndicators[odsId] || [];
  };

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
    setFormData(prev => {
      const isSelected = prev.selectedOds.includes(odsId);
      const newSelected = isSelected 
        ? prev.selectedOds.filter(id => id !== odsId)
        : [...prev.selectedOds, odsId];
      
      return {
        ...prev,
        selectedOds: newSelected,
        primaryOds: newSelected.length > 0 ? newSelected[0] : null
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

      const finalData = {
        ...formData,
        objective: formData.primaryOds,
        indicatorConfigs,
        indicatorMetadata,
        userId: user.id
      };
      
      const result = await createFullProject(finalData, SERVICES_MAP);
      
      if (result.success) {
        // ── S5: Mostrar advertencia si algunos indicadores no se pudieron guardar ──
        if (result.skippedIndicators && result.skippedIndicators.length > 0) {
          alert(
            `Proyecto creado con ${result.savedIndicators || 0} indicador(es) guardado(s).
` +
            `Advertencia: No se pudieron guardar: ${result.skippedIndicators.join(', ')}

` +
            'Puede configurarlos más tarde desde la página de evaluación.'
          );
        }
        navigate(`/projects/${result.data?.id || result.data}/evaluation`);
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
          {currentStep === 1 ? (
            <div className="step-content">
              <div className="section-intro">
                <Info size={18} />
                <p>Complete los datos básicos para iniciar la planificación estratégica del proyecto en la UTN.</p>
              </div>

              <div className="form-grid">
                <div className="form-group full-width">
                  <label><FileText size={14} /> Nombre del Proyecto</label>
                  <input name="name" value={formData.name} onChange={handleInputChange} required placeholder="Ej: Fortalecimiento de la Economía Circular en Región Huetar" />
                </div>

                <div className="form-group">
                  <label><Layers size={14} /> Área Responsable</label>
                  <select 
                    name="area" 
                    value={formData.area} 
                    onChange={handleInputChange} 
                    required 
                    disabled={loadingResources}
                  >
                    <option value="">
                      {loadingResources ? 'Cargando áreas...' : 'Seleccione área institucional'}
                    </option>
                    {catalogSedes.map(sede => (
                      <option key={sede.id} value={sede.nombre}>
                        {sede.nombre}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="form-group">
                  <label><Users size={14} /> Responsable Técnico</label>
                  <select 
                    name="responsable" 
                    value={formData.responsable} 
                    onChange={handleResponsableChange} 
                    required
                    disabled={loadingResources}
                  >
                    <option value="">
                      {loadingResources ? 'Cargando personal...' : (formData.area ? 'Seleccione personal de esta sede' : 'Seleccione personal académico')}
                    </option>
                    {filteredPersonnel.map(person => (
                      <option key={person.id} value={person.fullName}>
                        {person.fullName}
                      </option>
                    ))}
                  </select>
                </div>

                <div className="form-group">
                  <label><Calendar size={14} /> Inicio Estimado</label>
                  <input type="date" name="startDate" value={formData.startDate} onChange={handleInputChange} required />
                </div>

                <div className="form-group">
                  <label><Calendar size={14} /> Finalización Impacto</label>
                  <input type="date" name="endDate" value={formData.endDate} onChange={handleInputChange} required />
                </div>

                <div className="form-group">
                  <label><MapPin size={14} /> Provincia</label>
                  <select name="provinciaId" value={formData.provinciaId} onChange={handleGeoChange} required>
                    <option value="">Seleccione Provincia</option>
                    {provincias.map(p => <option key={p.id} value={p.id}>{p.nombre}</option>)}
                  </select>
                </div>

                <div className="form-group">
                  <label><MapPin size={14} /> Cantón</label>
                  <select name="cantonId" value={formData.cantonId} onChange={handleGeoChange} required disabled={!formData.provinciaId}>
                    <option value="">Seleccione Cantón</option>
                    {cantones.map(c => <option key={c.id} value={c.id}>{c.nombre}</option>)}
                  </select>
                </div>

                <div className="form-group">
                  <label><MapPin size={14} /> Distrito</label>
                  <select name="distritoId" value={formData.distritoId} onChange={handleGeoChange} required disabled={!formData.cantonId}>
                    <option value="">Seleccione Distrito</option>
                    {distritos.map(d => <option key={d.id} value={d.id}>{d.nombre}</option>)}
                  </select>
                </div>

                <div className="form-group full-width">
                  <label><Target size={14} /> Justificación y Descripción</label>
                  <textarea name="description" value={formData.description} onChange={handleInputChange} rows="4" placeholder="Describa cómo este proyecto soluciona una problemática específica..." />
                </div>
              </div>
            </div>
          ) : (
            <div className="step-content">
              <div className="ods-grid-header">
                <LayoutGrid size={18} />
                <h3>Selección de Impacto ODS</h3>
              </div>
              
              <div className="ods-selection-grid">
                {Object.keys(odsColors).map(odsId => (
                  <div 
                    key={odsId} 
                    className={`ods-card ${formData.selectedOds.includes(parseInt(odsId)) ? 'selected' : ''}`}
                    style={{ backgroundColor: odsColors[odsId] }}
                    onClick={() => toggleOds(parseInt(odsId))}
                  >
                    <span className="ods-number">{odsId}</span>
                    <span className="ods-title">{getObjectiveName(odsId)}</span>
                    {formData.selectedOds.includes(parseInt(odsId)) && (
                      <div className="selection-overlay">
                        <Check size={12} />
                      </div>
                    )}
                  </div>
                ))}
              </div>

              {formData.selectedOds.length > 0 && (
                <div className="indicators-panel fade-in">
                  <div className="section-header">
                    <Settings size={18} />
                    <h3>Configuración de Indicadores</h3>
                  </div>
                  
                  {formData.selectedOds.map(odsId => {
                    const indicators = getIndicatorsForOds(odsId);
                    const isLoaded = !loadingMetadata[odsId];
                    return (
                      <div key={odsId} className={`ods-accordion ${expandedOds === odsId ? 'open' : ''}`}>
                        <div className="accordion-header" onClick={() => setExpandedOds(expandedOds === odsId ? null : odsId)} style={{ borderLeft: `4px solid ${odsColors[odsId]}` }}>
                          <span className="ods-badge" style={{ backgroundColor: odsColors[odsId] }}>ODS {odsId}</span>
                          <span className="ods-name">{getObjectiveName(odsId)}</span>
                          <div className="header-right">
                            <span className="count-badge">{indicators.filter(i => formData.indicators.includes(i)).length} / {indicators.length}</span>
                            {!isLoaded && expandedOds === odsId ? <div className="spinner-xs"></div> : <ChevronRight className="chevron" size={20} />}
                          </div>
                        </div>
                        
                        {expandedOds === odsId && (
                          <div className="accordion-content">
                            {!isLoaded ? (
                              <div className="metadata-loader">
                                <div className="spinner-sm"></div>
                                <span>Sincronizando indicadores con base de datos...</span>
                              </div>
                            ) : (
                              <div className="indicators-selection-list">
                                {indicators.map(code => {
                                  const meta = indicatorMetadata[code];
                                  return (
                                    <div key={code} className={`indicator-li ${formData.indicators.includes(code) ? 'selected' : ''}`}>
                                      <div className="indicator-main" onClick={() => toggleIndicator(code)}>
                                        <div className="checkbox">{formData.indicators.includes(code) && <Check size={12} />}</div>
                                        <div className="indicator-info">
                                          <div className="indicator-top-row">
                                            <span className="code">{code}</span>
                                            {meta?.unit && <span className="unit-badge">{meta.unit}</span>}
                                          </div>
                                          <span className="label">
                                            {meta?.description || `Indicador ${code}`}
                                          </span>
                                        </div>
                                      </div>
                                      
                                      {formData.indicators.includes(code) && (
                                        <button 
                                          type="button" 
                                          className={`btn-config ${indicatorConfigs[code] ? 'active' : ''}`}
                                          onClick={(e) => {
                                            e.stopPropagation();
                                            setConfiguringIndicator(code);
                                          }}
                                        >
                                          <Settings size={14} />
                                          {indicatorConfigs[code] ? 'Configurado' : 'Configurar'}
                                        </button>
                                      )}
                                    </div>
                                  );
                                })}
                              </div>
                            )}
                          </div>
                        )}
                      </div>
                    );
                  })}
                </div>
              )}
            </div>
          )}

          <div className="form-actions">
            <button 
              type="button" 
              className="btn-premium btn-secondary" 
              onClick={() => currentStep === 1 ? navigate('/dashboard') : setCurrentStep(1)}
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
