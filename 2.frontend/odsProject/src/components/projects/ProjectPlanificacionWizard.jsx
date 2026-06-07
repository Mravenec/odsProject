import React, { useState } from 'react';
import {
  Check, Settings, Target, Calendar, MapPin, Users, Info,
  Layers, LayoutGrid, FileText, ChevronRight,
} from 'lucide-react';
import { getObjectiveName, odsColors } from '../../utils/formatters';

export const OdsSelectionCard = ({ ods, selected, onToggle }) => {
  const [imgFailed, setImgFailed] = useState(false);
  const showIcon = Boolean(ods.iconoUrl) && !imgFailed;

  return (
    <div
      className={`ods-card ${showIcon ? 'ods-card--has-icon' : ''} ${selected ? 'selected' : ''}`}
      style={{
        backgroundColor: ods.colorHex,
        ...(showIcon
          ? {
              backgroundImage: `url(${ods.iconoUrl})`,
              backgroundSize: '100%',
              backgroundRepeat: 'no-repeat',
              backgroundPosition: 'center',
            }
          : {}),
      }}
      onClick={onToggle}
    >
      {ods.iconoUrl && !imgFailed && (
        <img
          src={ods.iconoUrl}
          alt=""
          aria-hidden="true"
          className="ods-icon-probe"
          onError={() => setImgFailed(true)}
        />
      )}
      {!showIcon && (
        <>
          <span className="ods-number">{ods.id}</span>
          <span className="ods-title">{ods.nombre}</span>
        </>
      )}
      {selected && (
        <div className="selection-overlay">
          <Check size={16} />
        </div>
      )}
    </div>
  );
};

/**
 * Wizard compartido create + edit (pasos 1 y 2 de planificación).
 */
export default function ProjectPlanificacionWizard({
  mode = 'create',
  currentStep,
  formData,
  onInputChange,
  onGeoChange,
  onResponsableChange,
  provincias = [],
  cantones = [],
  distritos = [],
  catalogSedes = [],
  filteredPersonnel = [],
  loadingResources = false,
  lockGestorInstitutionalFields = false,
  odsList = [],
  selectedOds = [],
  onToggleOds,
  indicators = [],
  onToggleIndicator,
  indicatorMetadata = {},
  indicatorConfigs = {},
  onConfigureIndicator,
  availableIndicators = {},
  loadingMetadata = {},
  expandedOds,
  onToggleExpandedOds,
}) {
  const getIndicatorsForOds = (odsId) => availableIndicators[odsId] || [];

  if (currentStep === 1) {
    return (
      <div className="step-content">
        <div className="section-intro">
          <Info size={18} />
          <p>
            {mode === 'edit'
              ? 'Actualice los datos básicos del proyecto en planificación.'
              : 'Complete los datos básicos para iniciar la planificación estratégica del proyecto en la UTN.'}
          </p>
        </div>

        <div className="form-grid">
          <div className="form-group full-width">
            <label><FileText size={14} /> Nombre del Proyecto</label>
            <input
              name="name"
              value={formData.name}
              onChange={onInputChange}
              required
              placeholder="Ej: Fortalecimiento de la Economía Circular en Región Huetar"
            />
          </div>

          <div className="form-group">
            <label><Layers size={14} /> Área Responsable</label>
            <select
              name="area"
              value={formData.area}
              onChange={onInputChange}
              required={mode !== 'edit'}
              disabled={(loadingResources && !formData.area) || lockGestorInstitutionalFields}
            >
              <option value="">
                {loadingResources ? 'Cargando áreas...' : 'Seleccione área institucional'}
              </option>
              {catalogSedes.map((sede) => (
                <option key={sede.id} value={sede.nombre}>{sede.nombre}</option>
              ))}
            </select>
            {lockGestorInstitutionalFields && (
              <span className="form-hint">
                {mode === 'edit'
                  ? 'Asignación institucional del proyecto (solo lectura para gestor).'
                  : 'Asignado automáticamente según su perfil.'}
              </span>
            )}
          </div>

          <div className="form-group">
            <label><Users size={14} /> Responsable Técnico</label>
            <select
              name="responsable"
              value={formData.responsable}
              onChange={onResponsableChange}
              required={mode !== 'edit'}
              disabled={(loadingResources && !formData.responsable) || lockGestorInstitutionalFields}
            >
              <option value="">
                {loadingResources
                  ? 'Cargando personal...'
                  : (formData.area ? 'Seleccione personal de esta sede' : 'Seleccione personal académico')}
              </option>
              {filteredPersonnel.map((person) => (
                <option key={person.id} value={person.fullName}>{person.fullName}</option>
              ))}
            </select>
          </div>

          <div className="form-group">
            <label><Calendar size={14} /> Inicio Estimado</label>
            <input type="date" name="startDate" value={formData.startDate} onChange={onInputChange} required />
          </div>

          <div className="form-group">
            <label><Calendar size={14} /> Finalización Impacto</label>
            <input type="date" name="endDate" value={formData.endDate} onChange={onInputChange} required />
          </div>

          <div className="form-group">
            <label><MapPin size={14} /> Provincia</label>
            <select
              name="provinciaId"
              value={formData.provinciaId || ''}
              onChange={onGeoChange}
              required={mode !== 'edit'}
            >
              <option value="">Seleccione Provincia</option>
              {provincias.map((p) => <option key={p.id} value={p.id}>{p.nombre}</option>)}
            </select>
          </div>

          <div className="form-group">
            <label><MapPin size={14} /> Cantón</label>
            <select
              name="cantonId"
              value={formData.cantonId || ''}
              onChange={onGeoChange}
              required={mode !== 'edit'}
              disabled={!formData.provinciaId}
            >
              <option value="">Seleccione Cantón</option>
              {cantones.map((c) => <option key={c.id} value={c.id}>{c.nombre}</option>)}
            </select>
          </div>

          <div className="form-group">
            <label><MapPin size={14} /> Distrito</label>
            <select
              name="distritoId"
              value={formData.distritoId || ''}
              onChange={onGeoChange}
              required={mode !== 'edit'}
              disabled={!formData.cantonId}
            >
              <option value="">Seleccione Distrito</option>
              {distritos.map((d) => <option key={d.id} value={d.id}>{d.nombre}</option>)}
            </select>
          </div>

          <div className="form-group full-width">
            <label><Target size={14} /> Justificación y Descripción</label>
            <textarea
              name="description"
              value={formData.description}
              onChange={onInputChange}
              rows="4"
              placeholder="Describa cómo este proyecto soluciona una problemática específica..."
            />
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="step-content">
      <div className="ods-grid-header">
        <LayoutGrid size={18} />
        <h3>Selección de Impacto ODS</h3>
      </div>

      <div className="ods-selection-grid">
        {odsList.map((ods) => (
          <OdsSelectionCard
            key={ods.id}
            ods={ods}
            selected={selectedOds.includes(ods.id)}
            onToggle={() => onToggleOds(ods.id)}
          />
        ))}
      </div>

      {selectedOds.length > 0 && (
        <div className="indicators-panel fade-in">
          <div className="section-header">
            <Settings size={18} />
            <h3>Configuración de Indicadores</h3>
          </div>

          {selectedOds.map((odsId) => {
            const odsIndicators = getIndicatorsForOds(odsId);
            const isLoaded = !loadingMetadata[odsId];
            return (
              <div key={odsId} className={`ods-accordion ${expandedOds === odsId ? 'open' : ''}`}>
                <div
                  className="accordion-header"
                  onClick={() => onToggleExpandedOds(odsId)}
                  style={{ borderLeft: `4px solid ${odsColors[odsId]}` }}
                >
                  <span className="ods-badge" style={{ backgroundColor: odsColors[odsId] }}>ODS {odsId}</span>
                  <span className="ods-name">{getObjectiveName(odsId)}</span>
                  <div className="header-right">
                    <span className="count-badge">
                      {odsIndicators.filter((i) => indicators.includes(i)).length} / {odsIndicators.length}
                    </span>
                    {!isLoaded && expandedOds === odsId
                      ? <div className="spinner-xs" />
                      : <ChevronRight className="chevron" size={20} />}
                  </div>
                </div>

                {expandedOds === odsId && (
                  <div className="accordion-content">
                    {!isLoaded ? (
                      <div className="metadata-loader">
                        <div className="spinner-sm" />
                        <span>Sincronizando indicadores con base de datos...</span>
                      </div>
                    ) : (
                      <div className="indicators-selection-list">
                        {odsIndicators.map((code) => {
                          const meta = indicatorMetadata[code];
                          return (
                            <div
                              key={code}
                              className={`indicator-li ${indicators.includes(code) ? 'selected' : ''}`}
                            >
                              <div className="indicator-main" onClick={() => onToggleIndicator(code)}>
                                <div className="checkbox">
                                  {indicators.includes(code) && <Check size={12} />}
                                </div>
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

                              {indicators.includes(code) && (
                                <button
                                  type="button"
                                  className={`btn-config ${indicatorConfigs[code] ? 'active' : ''}`}
                                  onClick={(e) => {
                                    e.stopPropagation();
                                    onConfigureIndicator(code);
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
  );
}
