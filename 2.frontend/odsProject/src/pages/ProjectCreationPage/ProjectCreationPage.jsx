import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth.jsx';
import { useProjects } from '../../hooks/useProjects.jsx';
import './ProjectCreationPage.css';

const ProjectCreationPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
  const { createProject, loading, error: projectsError } = useProjects();
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    objective: '',
    indicators: [],
    targetValues: {},
    startDate: '',
    endDate: ''
  });
  const [availableIndicators, setAvailableIndicators] = useState([]);

  const objectives = [
    { id: 1, name: 'Fin de la Pobreza', service: 'objetivo01Service' },
    { id: 2, name: 'Hambre Cero', service: 'objetivo02Service' },
    { id: 3, name: 'Salud y Bienestar', service: 'objetivo03Service' },
    { id: 4, name: 'Educación de Calidad', service: 'objetivo04Service' },
    { id: 5, name: 'Igualdad de Género', service: 'objetivo05Service' },
    { id: 6, name: 'Agua Limpia y Saneamiento', service: 'objetivo06Service' },
    { id: 7, name: 'Energía Asequible y No Contaminante', service: 'objetivo07Service' },
    { id: 8, name: 'Trabajo Decente y Crecimiento Económico', service: 'objetivo08Service' },
    { id: 9, name: 'Industria, Innovación e Infraestructura', service: 'objetivo09Service' },
    { id: 10, name: 'Reducción de las Desigualdades', service: 'objetivo10Service' },
    { id: 11, name: 'Ciudades y Comunidades Sostenibles', service: 'objetivo11Service' },
    { id: 12, name: 'Producción y Consumo Responsables', service: 'objetivo12Service' },
    { id: 13, name: 'Acción por el Clima', service: 'objetivo13Service' },
    { id: 14, name: 'Vida Submarina', service: 'objetivo14Service' },
    { id: 15, name: 'Vida de Ecosistemas Terrestres', service: 'objetivo15Service' },
    { id: 16, name: 'Paz, Justicia e Instituciones Sólidas', service: 'objetivo16Service' },
    { id: 17, name: 'Alianzas para Lograr los Objetivos', service: 'objetivo17Service' }
  ];

  const indicatorNames = {
    objetivo01Service: [
      'povertyRate', 'extremePovertyRate', 'socialProtectionCoverage',
      'governmentSocialSpending', 'povertyGapRatio', 'multidimensionalPovertyIndex',
      'vulnerableEmployment', 'informalEmployment', 'unemploymentRate', 'incomeInequality'
    ],
    objetivo02Service: [
      'prevalenceUndernourishment', 'foodInsecurityPrevalence', 'stuntingChildren',
      'wastingChildren', 'overweightChildren', 'anemiaWomen', 'agriculturalProductivity',
      'smallholderAccess', 'geneticDiversity', 'livestockDiversity'
    ],
    objetivo03Service: [
      'maternalMortalityRate', 'skilledBirthAttendance', 'neonatalMortalityRate',
      'under5MortalityRate', 'tuberculosisIncidence', 'malariaIncidence', 'hepatitisBIncidence',
      'hivIncidence', 'ncdPrematureMortality', 'suicideMortalityRate'
    ],
    objetivo04Service: [
      'literacyRate', 'primaryEducationCompletion', 'secondaryEducationCompletion',
      'earlyChildhoodEducation', 'genderParityIndex', 'teacherTrainingRatio',
      'pupilTeacherRatio', 'educationExpenditureGDP', 'schoolInfrastructure', 'digitalLiteracy'
    ],
    objetivo05Service: [
      'parliamentarySeatsWomen', 'managerialPositionsWomen', 'laborForceParticipation',
      'unpaidCareWork', 'genderPayGap', 'violenceAgainstWomen', 'earlyMarriage',
      'reproductiveHealthRights', 'economicEmpowerment', 'educationGenderParity'
    ],
    objetivo06Service: [
      'safeDrinkingWaterAccess', 'basicSanitationAccess', 'hygieneFacilitiesAccess',
      'wastewaterTreatment', 'waterQuality', 'waterUseEfficiency', 'waterStressLevel',
      'freshwaterEcosystems', 'transboundaryCooperation', 'communityWaterManagement'
    ],
    objetivo07Service: [
      'electricityAccess', 'cleanCookingSolutions', 'renewableEnergyShare',
      'energyEfficiency', 'energyIntensity', 'cleanEnergyInvestment', 'energyAccessReliability',
      'energyAffordability', 'solarEnergyCapacity', 'windEnergyCapacity'
    ],
    objetivo08Service: [
      'gdpGrowthRate', 'laborProductivity', 'unemploymentRate', 'youthUnemployment',
      'informalEmployment', 'workingPoverty', 'occupationalSafety', 'equalPayGender',
      'laborRightsProtection', 'tourismContribution'
    ],
    objetivo09Service: [
      'manufacturingValueAdded', 'industrialEmployment', 'smallIndustryAccess',
      'infrastructureCoverage', 'roadDensity', 'internetAccess', 'mobilePhoneCoverage',
      'broadbandSubscription', 'researchDevelopmentSpending', 'researchersPerMillion'
    ],
    objetivo10Service: [
      'incomeGiniCoefficient', 'palmaRatio', 'bottom40Share', 'socialProtectionCoverage',
      'laborShareGDP', 'remittanceFlows', 'migrationPolicies', 'tariffBarriers',
      'developmentAssistance', 'financialInclusion'
    ],
    objetivo11Service: [
      'urbanPopulationSlums', 'publicTransportAccess', 'urbanAirQuality',
      'municipalWasteManagement', 'greenSpacePerCapita', 'urbanDisasterDeaths',
      'housingAffordability', 'culturalHeritageProtection', 'urbanPlanningCapacity', 'roadSafety'
    ],
    objetivo12Service: [
      'materialFootprint', 'domesticMaterialConsumption', 'foodWasteReduction',
      'chemicalWasteManagement', 'recyclingRate', 'sustainableTourism', 'corporateSustainability',
      'publicProcurementSustainability', 'foodLossReduction', 'packagingWasteReduction'
    ],
    objetivo13Service: [
      'climateAdaptationPlans', 'earlyWarningSystems', 'climateEducation',
      'mitigationPlanning', 'climateFinance', 'disasterRiskReduction', 'climateResilience',
      'renewableEnergyTransition', 'carbonPricing', 'climateTechnologyTransfer'
    ],
    objetivo14Service: [
      'marinePollutionPrevention', 'marineProtectedAreas', 'oceanAcidification',
      'sustainableFisheries', 'marineBiodiversity', 'coastalEcosystemHealth', 'oceanGovernance',
      'marineResearchFunding', 'plasticPollutionReduction', 'coralReefProtection'
    ],
    objetivo15Service: [
      'forestAreaChange', 'protectedAreasCoverage', 'biodiversityIndex',
      'endangeredSpeciesProtection', 'ecosystemRestoration', 'sustainableForestManagement',
      'desertificationControl', 'mountainEcosystemProtection', 'wetlandConservation',
      'wildlifeTraffickingPrevention'
    ],
    objetivo16Service: [
      'violenceReduction', 'conflictRelatedDeaths', 'humanTraffickingPrevention',
      'birthRegistration', 'ruleOfLaw', 'corruptionPerception', 'publicTrustInstitutions',
      'accessToJustice', 'judicialIndependence', 'governmentEffectiveness'
    ],
    objetivo17Service: [
      'officialDevelopmentAssistance', 'privateInvestmentFlows', 'remittanceCosts',
      'debtSustainability', 'tradeBarriers', 'technologyTransfer', 'internetAccessDeveloping',
      'multilateralCooperation', 'policyCoherence', 'dataAvailability'
    ]
  };

  useEffect(() => {
    if (formData.objective) {
      const selectedObjective = objectives.find(obj => obj.id === parseInt(formData.objective));
      if (selectedObjective) {
        setAvailableIndicators(indicatorNames[selectedObjective.service] || []);
        setFormData(prev => ({
          ...prev,
          indicators: [],
          targetValues: {}
        }));
      }
    }
  }, [formData.objective]);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleIndicatorToggle = (indicator) => {
    setFormData(prev => {
      const newIndicators = prev.indicators.includes(indicator)
        ? prev.indicators.filter(ind => ind !== indicator)
        : [...prev.indicators, indicator];
      
      const newTargetValues = { ...prev.targetValues };
      if (!newIndicators.includes(indicator)) {
        delete newTargetValues[indicator];
      }
      
      return {
        ...prev,
        indicators: newIndicators,
        targetValues: newTargetValues
      };
    });
  };

  const handleTargetValueChange = (indicator, value) => {
    setFormData(prev => ({
      ...prev,
      targetValues: {
        ...prev.targetValues,
        [indicator]: value
      }
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const projectData = {
        ...formData,
        userId: user.id
      };

      const result = await createProject(projectData);
      if (result.success) {
        navigate('/dashboard');
      }
    } catch (err) {
      console.error('Error creating project:', err);
    }
  };

  const formatIndicatorName = (indicator) => {
    return indicator.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase());
  };

  return (
    <div className="project-creation-page fade-in">
      <div className="page-header-simple">
        <button onClick={() => navigate('/dashboard')} className="btn-back" title="Volver al Dashboard">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
        </button>
        <h1>Crear Proyecto ODS</h1>
      </div>

      <main className="form-container">
        <form onSubmit={handleSubmit} className="modern-form">
          <section className="form-section">
            <div className="section-title">
              <span className="step-number">1</span>
              <h3>Información Básica</h3>
            </div>
            
            <div className="form-grid">
              <div className="form-group">
                <label>Nombre del Proyecto</label>
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleInputChange}
                  placeholder="Ej: Reducción de brecha digital en comunidades rurales"
                  required
                />
              </div>

              <div className="form-group">
                <label>Descripción</label>
                <textarea
                  name="description"
                  value={formData.description}
                  onChange={handleInputChange}
                  placeholder="Describe brevemente los objetivos y el alcance del proyecto..."
                  rows="4"
                  required
                />
              </div>

              <div className="form-group">
                <label>Objetivo ODS</label>
                <select
                  name="objective"
                  value={formData.objective}
                  onChange={handleInputChange}
                  required
                >
                  <option value="">Seleccionar Objetivo</option>
                  {objectives.map(objective => (
                    <option key={objective.id} value={objective.id}>
                      {objective.id}. {objective.name}
                    </option>
                  ))}
                </select>
              </div>

              <div className="form-group-row">
                <div className="form-group">
                  <label>Fecha Inicio</label>
                  <input
                    type="date"
                    name="startDate"
                    value={formData.startDate}
                    onChange={handleInputChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label>Fecha Fin</label>
                  <input
                    type="date"
                    name="endDate"
                    value={formData.endDate}
                    onChange={handleInputChange}
                    required
                    min={formData.startDate}
                  />
                </div>
              </div>
            </div>
          </section>

          <section className="form-section">
            <div className="section-title">
              <span className="step-number">2</span>
              <h3>Selección de Indicadores</h3>
              <p className="section-subtitle">Selecciona los indicadores que medirás en este proyecto.</p>
            </div>

            {availableIndicators.length > 0 ? (
              <div className="indicators-grid">
                {availableIndicators.map(indicator => (
                  <div 
                    key={indicator} 
                    className={`indicator-chip ${formData.indicators.includes(indicator) ? 'active' : ''}`}
                    onClick={() => handleIndicatorToggle(indicator)}
                  >
                    <span className="chip-icon">{formData.indicators.includes(indicator) ? '✓' : '+'}</span>
                    <div className="chip-content">
                      <span className="chip-label">{formatIndicatorName(indicator)}</span>
                      {formData.indicators.includes(indicator) && (
                        <input
                          type="number"
                          step="0.01"
                          placeholder="Meta"
                          value={formData.targetValues[indicator] || ''}
                          onClick={(e) => e.stopPropagation()}
                          onChange={(e) => handleTargetValueChange(indicator, e.target.value)}
                          required
                          className="target-input-sm"
                        />
                      )}
                    </div>
                  </div>
                ))}
              </div>
            ) : (
                <p className="no-indicators-msg">Selecciona un objetivo para ver sus indicadores.</p>
            )}
          </section>

          {projectsError && (
            <div className="error-banner">
              <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="8" x2="12" y2="12"></line><line x1="12" y1="16" x2="12.01" y2="16"></line></svg>
              <span>{projectsError}</span>
            </div>
          )}

          <div className="form-actions">
            <button 
              type="button" 
              className="btn-secondary" 
              onClick={() => navigate('/dashboard')}
              disabled={loading}
            >
              Cancelar
            </button>
            <button 
              type="submit" 
              className={`btn-primary ${loading ? 'loading' : ''}`}
              disabled={loading || formData.indicators.length === 0}
            >
              {loading ? <span className="spinner"></span> : 'Crear Proyecto ODS'}
            </button>
          </div>
        </form>
      </main>
    </div>
  );
};

export default ProjectCreationPage;
