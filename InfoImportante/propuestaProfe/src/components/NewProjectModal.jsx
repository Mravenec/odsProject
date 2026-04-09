import React, { useState, useEffect } from 'react';
import { X, LayoutGrid, FileText, MapPin, Target, Calendar, CheckCircle2, ChevronDown, ChevronUp, User, Layers, Plus, CircleHelp, Building } from 'lucide-react';
import { useProjects } from '../context/ProjectContext';
import { PROVINCIAS, CANTONES, DISTRITOS } from '../data/cr_locations';
import { ODS_LIST } from '../data/ods_data';

const NewProjectModal = ({ isOpen, onClose }) => {
  const { areas, personnel, addProject } = useProjects();
  const [step, setStep] = useState(1);
  const [openODS, setOpenODS] = useState(null);
  const [openIndicatorConfig, setOpenIndicatorConfig] = useState(null); // { odsId, metaId, indicatorName }
  
  const [formData, setFormData] = useState({
    title: '',
    areaId: '',
    personId: '',
    objective: '',
    location_province: '',
    location_canton: '',
    location_district: '',
    start_date: '',
    end_date: '',
    ods: [],
    selectedMetas: [],
    indicatorConfigs: {} // metaId -> { parameters: [], formula: '', goal: {} }
  });

  if (!isOpen) return null;

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => {
      if (name === 'location_province') return { ...prev, [name]: value, location_canton: '', location_district: '' };
      if (name === 'location_canton') return { ...prev, [name]: value, location_district: '' };
      if (name === 'areaId') return { ...prev, [name]: value, personId: '' };
      return { ...prev, [name]: value };
    });
  };

  const toggleODS = (ods) => {
    setFormData(prev => {
      const exists = prev.ods.find(o => o.id === ods.id);
      if (exists) {
        const newMetas = prev.selectedMetas.filter(m => !m.startsWith(`${ods.id}.`));
        // Remove configs associated with this ODS
        const newConfigs = { ...prev.indicatorConfigs };
        Object.keys(newConfigs).forEach(key => {
          if (key.startsWith(`${ods.id}.`)) delete newConfigs[key];
        });
        return { ...prev, ods: prev.ods.filter(o => o.id !== ods.id), selectedMetas: newMetas, indicatorConfigs: newConfigs };
      } else {
        return { ...prev, ods: [...prev.ods, ods] };
      }
    });
  };

  const toggleIndicatorConfig = (odsId, metaId, indicatorName) => {
    setOpenIndicatorConfig({ odsId, metaId, indicatorName });
  };

  const saveIndicatorConfig = (indicatorName, config) => {
    setFormData(prev => ({
      ...prev,
      indicatorConfigs: {
        ...prev.indicatorConfigs,
        [indicatorName]: config
      },
      selectedMetas: prev.selectedMetas.includes(indicatorName) ? prev.selectedMetas : [...prev.selectedMetas, indicatorName]
    }));
    setOpenIndicatorConfig(null);
  };

  const toggleMeta = (metaId) => {
    setFormData(prev => {
      const exists = prev.selectedMetas.includes(metaId);
      if (exists) {
        return { ...prev, selectedMetas: prev.selectedMetas.filter(m => m !== metaId) };
      } else {
        return { ...prev, selectedMetas: [...prev.selectedMetas, metaId] };
      }
    });
  };

  const isStep1Valid = formData.title && formData.areaId && formData.personId && formData.objective && formData.location_province && formData.location_canton && formData.location_district && formData.start_date && formData.end_date;

  const handleSubmit = () => {
    const provinceName = PROVINCIAS.find(p => p.id === formData.location_province)?.name || '';
    const cantonName = CANTONES[formData.location_province]?.find(c => c.id === formData.location_canton)?.name || '';
    const districtName = DISTRITOS[formData.location_canton]?.find(d => d.id === formData.location_district)?.name || '';

    addProject({
      ...formData,
      location_province: provinceName,
      location_canton: cantonName,
      location_district: districtName,
      location_city: districtName,
    });
    
    setFormData({
      title: '', areaId: '', personId: '', objective: '',
      location_province: '', location_canton: '', location_district: '',
      start_date: '', end_date: '', ods: [], selectedMetas: [], indicatorConfigs: {}
    });
    setStep(1);
    onClose();
  };

  const selectedCantones = CANTONES[formData.location_province] || [];
  const selectedDistritos = DISTRITOS[formData.location_canton] || [];

  return (
    <div style={{ position: 'fixed', inset: 0, backgroundColor: 'rgba(15, 23, 42, 0.4)', backdropFilter: 'blur(8px)', zIndex: 1000, display: 'flex', alignItems: 'center', justifyContent: 'center', padding: '1.5rem' }}>
      <div className="animate-in fade-in zoom-in-95 duration-200" style={{ width: '100%', maxWidth: '900px', backgroundColor: 'white', borderRadius: '1.25rem', boxShadow: '0 25px 50px -12px rgba(0, 0, 0, 0.25)', maxHeight: '90vh', display: 'flex', flexDirection: 'column', overflow: 'hidden' }}>
        
        {/* Header */}
        <div style={{ padding: '1.5rem 2rem', borderBottom: '1px solid #F1F5F9', display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <div>
            <h2 style={{ fontSize: '1.25rem', fontWeight: '800', color: '#0F172A', letterSpacing: '-0.025em' }}>Registrar Nuevo Proyecto</h2>
            <p style={{ fontSize: '0.875rem', color: '#64748B' }}>Complete la información para comenzar su nuevo proyecto.</p>
          </div>
          <button onClick={onClose} style={{ color: '#94A3B8', padding: '0.5rem', borderRadius: '0.75rem', backgroundColor: '#F8FAFC' }}>
            <X size={20} />
          </button>
        </div>

        {/* Stepper */}
        <div style={{ padding: '1rem 2rem', backgroundColor: '#F8FAFC', borderBottom: '1px solid #F1F5F9', display: 'flex', alignItems: 'center', gap: '1rem' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
            <div style={{ width: '28px', height: '28px', borderRadius: '50%', backgroundColor: step === 1 ? '#2563EB' : '#10B981', color: 'white', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '0.75rem', fontWeight: '700' }}>
              {step > 1 ? <CheckCircle2 size={16} /> : '1'}
            </div>
            <span style={{ fontSize: '0.8125rem', fontWeight: '600', color: step === 1 ? '#0F172A' : '#64748B' }}>Información Básica</span>
          </div>
          <div style={{ width: '40px', height: '1px', backgroundColor: '#E2E8F0' }}></div>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
            <div style={{ width: '28px', height: '28px', borderRadius: '50%', backgroundColor: step === 2 ? '#2563EB' : '#E2E8F0', color: step === 2 ? 'white' : '#94A3B8', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '0.75rem', fontWeight: '700' }}>2</div>
            <span style={{ fontSize: '0.8125rem', fontWeight: '600', color: step === 2 ? '#0F172A' : '#94A3B8' }}>ODS</span>
          </div>
        </div>

        {/* Content */}
        <div style={{ flex: 1, overflowY: 'auto', padding: '2rem' }}>
          {step === 1 ? (
            <div style={{ display: 'flex', flexDirection: 'column', gap: '1.5rem' }}>
              <div>
                <label style={{ display: 'block', fontSize: '0.8125rem', fontWeight: '600', color: '#334155', marginBottom: '0.5rem' }}>Nombre del Proyecto</label>
                <div style={{ position: 'relative' }}>
                  <FileText size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8' }} />
                  <input 
                    name="title" 
                    value={formData.title} 
                    onChange={handleInputChange} 
                    type="text" 
                    placeholder="Ingrese el nombre del proyecto" 
                    style={{ width: '100%', padding: '0.75rem 1rem 0.75rem 3rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F8FAFC', fontSize: '0.875rem' }} 
                  />
                </div>
              </div>

              <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1.5rem' }}>
                <div>
                  <label style={{ display: 'block', fontSize: '0.8125rem', fontWeight: '600', color: '#334155', marginBottom: '0.5rem' }}>Área de la UTN</label>
                  <div style={{ position: 'relative' }}>
                    <Layers size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8' }} />
                    <select 
                      name="areaId" 
                      value={formData.areaId} 
                      onChange={handleInputChange} 
                      style={{ width: '100%', padding: '0.75rem 1rem 0.75rem 3rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F8FAFC', fontSize: '0.875rem', appearance: 'none' }}
                    >
                      <option value="">Seleccione área</option>
                      {areas.map(a => <option key={a.id} value={a.id}>{a.name}</option>)}
                    </select>
                    <ChevronDown size={16} style={{ position: 'absolute', right: '1rem', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8', pointerEvents: 'none' }} />
                  </div>
                </div>
                <div>
                  <label style={{ display: 'block', fontSize: '0.8125rem', fontWeight: '600', color: '#334155', marginBottom: '0.5rem' }}>Persona Responsable</label>
                  <div style={{ position: 'relative' }}>
                    <User size={18} style={{ position: 'absolute', left: '1rem', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8' }} />
                    <select 
                      name="personId" 
                      value={formData.personId} 
                      onChange={handleInputChange} 
                      disabled={!formData.areaId}
                      style={{ width: '100%', padding: '0.75rem 1rem 0.75rem 3rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: formData.areaId ? '#F8FAFC' : '#F1F5F9', fontSize: '0.875rem', appearance: 'none' }}
                    >
                      <option value="">Seleccione responsable</option>
                      {personnel.filter(p => !formData.areaId || p.areaId === parseInt(formData.areaId)).map(p => (
                        <option key={p.id} value={p.id}>{p.name}</option>
                      ))}
                    </select>
                    <ChevronDown size={16} style={{ position: 'absolute', right: '1rem', top: '50%', transform: 'translateY(-50%)', color: '#94A3B8', pointerEvents: 'none' }} />
                  </div>
                </div>
              </div>

              <div>
                <label style={{ display: 'block', fontSize: '0.8125rem', fontWeight: '600', color: '#334155', marginBottom: '0.5rem' }}>Objetivo General</label>
                <div style={{ position: 'relative' }}>
                  <Target size={18} style={{ position: 'absolute', left: '1rem', top: '1rem', color: '#94A3B8' }} />
                  <textarea 
                    name="objective" 
                    value={formData.objective} 
                    onChange={handleInputChange} 
                    placeholder="Describa el objetivo general del proyecto" 
                    style={{ width: '100%', padding: '0.75rem 1rem 0.75rem 3rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: '#F8FAFC', fontSize: '0.875rem', minHeight: '100px', resize: 'vertical' }}
                  ></textarea>
                </div>
              </div>

              <div style={{ padding: '1.5rem', backgroundColor: '#F8FAFC', borderRadius: '1rem', border: '1px solid #E2E8F0' }}>
                <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '1.25rem' }}>
                  <MapPin size={18} style={{ color: '#2563EB' }} />
                  <span style={{ fontSize: '0.75rem', fontWeight: '800', color: '#2563EB', letterSpacing: '0.05em', textTransform: 'uppercase' }}>Ubicación Geográfica</span>
                </div>
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr 1fr', gap: '1rem' }}>
                  <div>
                    <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem', fontWeight: '500' }}>Provincia</label>
                    <select name="location_province" value={formData.location_province} onChange={handleInputChange} style={{ width: '100%', backgroundColor: 'white', fontSize: '0.8125rem' }}>
                      <option value="">Seleccione</option>
                      {PROVINCIAS.map(p => <option key={p.id} value={p.id}>{p.name}</option>)}
                    </select>
                  </div>
                  <div>
                    <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem', fontWeight: '500' }}>Cantón</label>
                    <select name="location_canton" value={formData.location_canton} onChange={handleInputChange} disabled={!formData.location_province} style={{ width: '100%', backgroundColor: formData.location_province ? 'white' : '#F1F5F9', fontSize: '0.8125rem' }}>
                      <option value="">Seleccione</option>
                      {selectedCantones.map(c => <option key={c.id} value={c.id}>{c.name}</option>)}
                    </select>
                  </div>
                  <div>
                    <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem', fontWeight: '500' }}>Distrito</label>
                    <select name="location_district" value={formData.location_district} onChange={handleInputChange} disabled={!formData.location_canton} style={{ width: '100%', backgroundColor: formData.location_canton ? 'white' : '#F1F5F9', fontSize: '0.8125rem' }}>
                      <option value="">Seleccione</option>
                      {selectedDistritos.map(d => <option key={d.id} value={d.id}>{d.name}</option>)}
                    </select>
                  </div>
                </div>
              </div>

              <div style={{ padding: '1.5rem', backgroundColor: '#F8FAFC', borderRadius: '1rem', border: '1px solid #E2E8F0' }}>
                <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '1.25rem' }}>
                  <Calendar size={18} style={{ color: '#2563EB' }} />
                  <span style={{ fontSize: '0.75rem', fontWeight: '800', color: '#2563EB', letterSpacing: '0.05em', textTransform: 'uppercase' }}>Periodo de Realización</span>
                </div>
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1.5rem' }}>
                  <div>
                    <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem', fontWeight: '500' }}>Fecha de Inicio</label>
                    <input name="start_date" value={formData.start_date} onChange={handleInputChange} type="date" style={{ width: '100%', backgroundColor: 'white', fontSize: '0.8125rem' }} />
                  </div>
                  <div>
                    <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem', fontWeight: '500' }}>Fecha de Finalización</label>
                    <input name="end_date" value={formData.end_date} onChange={handleInputChange} type="date" style={{ width: '100%', backgroundColor: 'white', fontSize: '0.8125rem' }} />
                  </div>
                </div>
              </div>
            </div>
          ) : (
            <div>
              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '1.5rem' }}>
                <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                  <h3 style={{ fontWeight: '800', fontSize: '1.25rem', color: '#0F172A' }}>Objetivos de Desarrollo Sostenible</h3>
                  <div style={{ backgroundColor: '#DBEAFE', padding: '0.375rem 0.875rem', borderRadius: '4px', fontSize: '0.75rem', fontWeight: '700', color: '#2563EB', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                    {formData.ods.length} ODS seleccionados
                  </div>
                  <div style={{ color: '#94A3B8', cursor: 'pointer' }}><CircleHelp size={20} /></div>
                </div>
              </div>
              <p style={{ fontSize: '0.875rem', color: '#64748B', marginTop: '-1rem', marginBottom: '2rem' }}>Seleccione los ODS relacionados</p>
              <div style={{ display: 'grid', gridTemplateColumns: 'repeat(6, 1fr)', gap: '0.75rem', marginBottom: '2rem' }}>
                {ODS_LIST.map(ods => {
                  const isSelected = formData.ods.find(o => o.id === ods.id);
                  const isConfigured = formData.selectedMetas.some(m => m.startsWith(`${ods.id}.`));
                  return (
                    <div 
                      key={ods.id} 
                      onClick={() => toggleODS(ods)} 
                      style={{ 
                        aspectRatio: '1/1', 
                        backgroundColor: ods.color, 
                        borderRadius: '0.75rem', 
                        display: 'flex', 
                        flexDirection: 'column', 
                        alignItems: 'center', 
                        justifyContent: 'center', 
                        cursor: 'pointer', 
                        transition: 'all 0.2s', 
                        position: 'relative',
                        padding: '0.5rem',
                        textAlign: 'center',
                        opacity: isSelected ? 1 : 0.85,
                        transform: isSelected ? 'scale(1.05)' : 'scale(1)',
                        boxShadow: isSelected ? `0 10px 15px -3px ${ods.color}50` : 'none',
                        border: isSelected ? '3px solid white' : 'none'
                      }}
                    >
                      <span style={{ fontSize: '1.75rem', fontWeight: '900', color: 'white', lineHeight: '1', marginBottom: '0.25rem' }}>{ods.id}</span>
                      <span style={{ fontSize: '0.55rem', fontWeight: '800', color: 'white', textTransform: 'uppercase', lineHeight: '1.2', display: '-webkit-box', WebkitLineClamp: '2', WebkitBoxOrient: 'vertical', overflow: 'hidden' }}>{ods.name}</span>
                      
                      {isConfigured && (
                        <div style={{ position: 'absolute', top: '-5px', right: '-5px', backgroundColor: '#2563EB', borderRadius: '50%', padding: '2px', border: '2px solid white', zIndex: 10 }}>
                          <CheckCircle2 size={12} style={{ color: 'white' }} />
                        </div>
                      )}
                    </div>
                  );
                })}
              </div>

              {formData.ods.length > 0 && (
                <div className="animate-in fade-in slide-in-from-top-4 duration-300">
                  <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem', marginBottom: '1.5rem' }}>
                    <div style={{ color: '#2563EB' }}><Target size={20} /></div>
                    <h4 style={{ fontSize: '1rem', fontWeight: '700', color: '#0F172A' }}>Estructura de Metas e Indicadores</h4>
                  </div>
                  <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                    {formData.ods.map(ods => (
                      <div key={ods.id} style={{ border: `1px solid ${ods.color}40`, borderRadius: '1rem', overflow: 'hidden', backgroundColor: 'white' }}>
                        <div onClick={() => setOpenODS(openODS === ods.id ? null : ods.id)} style={{ padding: '1rem 1.25rem', backgroundColor: `${ods.color}10`, display: 'flex', justifyContent: 'space-between', alignItems: 'center', cursor: 'pointer' }}>
                          <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
                            <div style={{ backgroundColor: ods.color, color: 'white', width: '24px', height: '24px', borderRadius: '4px', display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: '0.75rem', fontWeight: '800' }}>{ods.id}</div>
                            <span style={{ fontSize: '0.875rem', fontWeight: '700', color: '#0F172A' }}>{ods.name}</span>
                          </div>
                          {openODS === ods.id ? <ChevronUp size={18} /> : <ChevronDown size={18} />}
                        </div>
                        {openODS === ods.id && (
                          <div style={{ padding: '1.25rem', display: 'flex', flexDirection: 'column', gap: '1.25rem' }}>
                            {ods.targets && ods.targets.length > 0 ? ods.targets.map(target => (
                              <div key={target.id} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                                <div style={{ display: 'flex', gap: '0.75rem', alignItems: 'flex-start' }}>
                                  <div style={{ marginTop: '0.25rem' }}><CheckCircle2 size={16} style={{ color: ods.color }} /></div>
                                  <div>
                                    <span style={{ fontSize: '0.875rem', fontWeight: '700', color: '#0F172A', display: 'block' }}>Meta {target.id}</span>
                                    <p style={{ fontSize: '0.8125rem', color: '#64748B', margin: 0 }}>{target.text}</p>
                                  </div>
                                </div>
                                <div style={{ paddingLeft: '1.75rem', display: 'flex', flexDirection: 'column', gap: '0.5rem' }}>
                                  {target.indicators && target.indicators.map(ind => {
                                    const indId = ind.split(' ')[0];
                                    const isConfigured = formData.indicatorConfigs[indId];
                                    return (
                                      <div key={indId} onClick={() => toggleIndicatorConfig(ods.id, target.id, indId)} style={{ padding: '0.75rem 1rem', borderRadius: '0.75rem', border: '1px solid #E2E8F0', backgroundColor: isConfigured ? '#F0F9FF' : '#F8FAFC', display: 'flex', justifyContent: 'space-between', alignItems: 'center', cursor: 'pointer', transition: 'all 0.2s' }}>
                                        <div style={{ display: 'flex', alignItems: 'center', gap: '0.75rem' }}>
                                          <div style={{ width: '8px', height: '8px', borderRadius: '50%', backgroundColor: isConfigured ? '#0369A1' : '#94A3B8' }}></div>
                                          <span style={{ fontSize: '0.8125rem', color: isConfigured ? '#0369A1' : '#475569', fontWeight: isConfigured ? '700' : '500' }}>Indicador {ind}</span>
                                        </div>
                                        {isConfigured ? (
                                          <span style={{ fontSize: '0.625rem', fontWeight: '800', backgroundColor: '#BAE6FD', color: '#0369A1', padding: '0.25rem 0.5rem', borderRadius: '4px', textTransform: 'uppercase' }}>Configurado</span>
                                        ) : (
                                          <Plus size={14} style={{ color: '#94A3B8' }} />
                                        )}
                                      </div>
                                    );
                                  })}
                                </div>
                              </div>
                            )) : (
                              <p style={{ fontSize: '0.875rem', color: '#94A3B8', fontStyle: 'italic', textAlign: 'center', padding: '1rem' }}>No hay metas específicas registradas.</p>
                            )}
                          </div>
                        )}
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          )}
        </div>

        {/* Footer */}
        <div style={{ padding: '1.5rem 2rem', borderTop: '1px solid #F1F5F9', backgroundColor: 'white', display: 'flex', justifyContent: 'flex-end', gap: '0.75rem' }}>
          <button onClick={() => step === 2 ? setStep(1) : onClose()} style={{ padding: '0.75rem 1.5rem', borderRadius: '0.75rem', backgroundColor: 'white', border: '1px solid #E2E8F0', color: '#64748B', fontSize: '0.875rem', fontWeight: '600' }}>
            {step === 2 ? 'Volver atrás' : 'Cancelar'}
          </button>
          <button onClick={() => step === 1 ? setStep(2) : handleSubmit()} disabled={step === 1 ? !isStep1Valid : formData.selectedMetas.length === 0} style={{ padding: '0.75rem 1.75rem', borderRadius: '0.75rem', backgroundColor: step === 1 ? '#2563EB' : '#16A34A', color: 'white', fontSize: '0.875rem', fontWeight: '600', border: 'none', opacity: (step === 1 ? isStep1Valid : formData.selectedMetas.length > 0) ? 1 : 0.5 }}>
            {step === 1 ? 'Continuar a ODS' : (
              <><CheckCircle2 size={18} style={{ marginRight: '0.5rem' }} />Registrar Proyecto</>
            )}
          </button>
        </div>

        {/* Indicator Config Sub-Modal */}
        {openIndicatorConfig && (
          <IndicatorConfigModal 
            indicator={openIndicatorConfig} 
            color={ODS_LIST.find(o => o.id === openIndicatorConfig.odsId)?.color}
            existingConfig={formData.indicatorConfigs[openIndicatorConfig.indicatorName]}
            onSave={(config) => saveIndicatorConfig(openIndicatorConfig.indicatorName, config)}
            onClose={() => setOpenIndicatorConfig(null)}
          />
        )}
      </div>
    </div>
  );
};

const IndicatorConfigModal = ({ indicator, color, existingConfig, onSave, onClose }) => {
  const [paramCount, setParamCount] = useState(existingConfig?.parameters?.length || 1);
  const [parameters, setParameters] = useState(() => existingConfig?.parameters?.map(p => ({...p})) || [{ name: '', type: 'Integer' }]);
  const [formula, setFormula] = useState(existingConfig?.formula || '');
  const [goal, setGoal] = useState(existingConfig?.goal || { name: '', value: '', unit: 'Percentage' });

  useEffect(() => {
    const count = parseInt(paramCount) || 0;
    setParameters(prev => {
      if (count > prev.length) {
        const additional = Array.from({ length: count - prev.length }, () => ({ name: '', type: 'Integer' }));
        return [...prev, ...additional];
      }
      return prev.slice(0, count);
    });
  }, [paramCount]);

  const insertParam = (name) => {
    setFormula(prev => prev + (prev ? ' ' : '') + name);
  };

  return (
    <div style={{ position: 'fixed', inset: 0, backgroundColor: 'rgba(0,0,0,0.5)', zIndex: 1100, display: 'flex', alignItems: 'center', justifyContent: 'center', padding: '1rem' }}>
      <div className="animate-in zoom-in-95 duration-200" style={{ backgroundColor: 'white', width: '100%', maxWidth: '600px', borderRadius: '1.25rem', padding: '2rem', maxHeight: '85vh', overflowY: 'auto' }}>
        <h3 style={{ fontSize: '1.25rem', fontWeight: '800', marginBottom: '0.5rem' }}>Configurar Indicador</h3>
        <p style={{ color: '#64748B', fontSize: '0.875rem', marginBottom: '2rem' }}>{indicator.indicatorName} - Meta {indicator.metaId}</p>

        <section style={{ marginBottom: '2rem' }}>
          <label style={{ display: 'block', fontSize: '0.875rem', fontWeight: '700', marginBottom: '1rem' }}>¿Cuántos parámetros desea utilizar para medir este indicador?</label>
          <input type="number" min="1" value={paramCount} onChange={(e) => setParamCount(e.target.value)} style={{ width: '80px', marginBottom: '1.5rem' }} />
          
          <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
            {parameters.map((p, i) => (
              <div key={`param-${i}`} style={{ border: '1px solid #E2E8F0', borderRadius: '0.75rem', padding: '1rem', backgroundColor: '#F8FAFC' }}>
                <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.75rem' }}>
                  <span style={{ fontSize: '0.75rem', fontWeight: '800', color: '#2563EB', backgroundColor: '#DBEAFE', padding: '0.25rem 0.5rem', borderRadius: '4px' }}>Parámetro {i + 1}</span>
                  <button onClick={() => insertParam(p.name)} disabled={!p.name} style={{ color: '#2563EB', fontSize: '0.75rem', fontWeight: '700', border: 'none', background: 'none' }}>Insertar en fórmula</button>
                </div>
                <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
                  <input placeholder="ej: poblacion_total" value={p.name} onChange={(e) => {
                    const newParams = parameters.map((item, idx) => idx === i ? { ...item, name: e.target.value } : item);
                    setParameters(newParams);
                  }} style={{ fontSize: '0.8125rem' }} />
                  <select value={p.type} onChange={(e) => {
                    const newParams = parameters.map((item, idx) => idx === i ? { ...item, type: e.target.value } : item);
                    setParameters(newParams);
                  }} style={{ fontSize: '0.8125rem' }}>
                    <option value="Integer">Número sin decimales</option>
                    <option value="Decimal">Número con decimales</option>
                  </select>
                </div>
              </div>
            ))}
          </div>
        </section>

        <section style={{ marginBottom: '2rem' }}>
          <label style={{ display: 'block', fontSize: '0.875rem', fontWeight: '700', marginBottom: '0.5rem' }}>Fórmula de Cálculo</label>
          <p style={{ fontSize: '0.75rem', color: '#64748B', marginBottom: '1rem' }}>Ingrese la fórmula utilizando los nombres de los parámetros</p>
          <textarea 
            value={formula} 
            onChange={(e) => setFormula(e.target.value)}
            style={{ width: '100%', fontFamily: 'monospace', borderRadius: '0.75rem', padding: '1rem', backgroundColor: '#F1F7FE', border: '1px solid #DBEAFE', minHeight: '80px' }}
          ></textarea>
          <div style={{ marginTop: '0.75rem', display: 'flex', gap: '0.5rem', flexWrap: 'wrap' }}>
            {parameters.filter(p => p.name).map((p, i) => (
              <button key={`btn-param-${i}`} onClick={() => insertParam(p.name)} style={{ padding: '0.25rem 0.5rem', backgroundColor: '#F1F5F9', border: '1px solid #E2E8F0', borderRadius: '4px', fontSize: '0.75rem', fontWeight: '600', cursor: 'pointer' }}>{p.name}</button>
            ))}
          </div>
        </section>

        <section style={{ marginBottom: '2.5rem' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '1rem' }}>
            <Target size={18} style={{ color: '#10B981' }} />
            <h4 style={{ fontSize: '0.875rem', fontWeight: '800' }}>Meta Esperada</h4>
          </div>
          <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
            <div>
              <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem' }}>Nombre de la meta</label>
              <input value={goal.name} onChange={(e) => setGoal({...goal, name: e.target.value})} placeholder="ej: Reducir la pobreza al 10%" style={{ width: '100%' }} />
            </div>
            <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: '1rem' }}>
              <div>
                <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem' }}>Valor objetivo</label>
                <input value={goal.value} onChange={(e) => setGoal({...goal, value: e.target.value})} placeholder="ej: 10" style={{ width: '100%' }} />
              </div>
              <div>
                <label style={{ display: 'block', fontSize: '0.75rem', color: '#64748B', marginBottom: '0.375rem' }}>Unidad</label>
                <select value={goal.unit} onChange={(e) => setGoal({...goal, unit: e.target.value})} style={{ width: '100%' }}>
                  <option value="Number">Número</option>
                  <option value="Percentage">Porcentaje</option>
                </select>
              </div>
            </div>
          </div>
        </section>

        <div style={{ display: 'flex', justifyContent: 'flex-end', gap: '1rem' }}>
          <button onClick={onClose} style={{ padding: '0.75rem 1.5rem', borderRadius: '0.75rem', background: 'none', border: '1px solid #E2E8F0', fontWeight: '600' }}>Cancelar</button>
          <button onClick={() => onSave({ parameters, formula, goal })} style={{ padding: '0.75rem 1.75rem', borderRadius: '0.75rem', backgroundColor: '#16A34A', color: 'white', border: 'none', fontWeight: '700', display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
            <Plus size={18} /> Guardar Configuración
          </button>
        </div>
      </div>
    </div>
  );
};

export default NewProjectModal;
