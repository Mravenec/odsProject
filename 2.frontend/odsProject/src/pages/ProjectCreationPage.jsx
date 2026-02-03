import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { projectService } from '../services/projectService';

const ProjectCreationPage = () => {
  const { user } = useAuth();
  const navigate = useNavigate();
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
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

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
    setLoading(true);
    setError('');

    try {
      const projectData = {
        ...formData,
        userId: user.id,
        status: 'active'
      };

      await projectService.createProject(projectData);
      navigate('/dashboard');
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const formatIndicatorName = (indicator) => {
    return indicator.replace(/([A-Z])/g, ' $1').replace(/^./, str => str.toUpperCase());
  };

  return (
    <div className="min-h-screen bg-gray-50 py-8">
      <div className="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="bg-white shadow-lg rounded-lg p-6">
          <h1 className="text-3xl font-bold text-gray-900 mb-8">Crear Nuevo Proyecto ODS</h1>
          
          {error && (
            <div className="mb-4 p-4 bg-red-50 border border-red-200 rounded-md">
              <p className="text-red-600">{error}</p>
            </div>
          )}

          <form onSubmit={handleSubmit} className="space-y-6">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Nombre del Proyecto
              </label>
              <input
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Descripción
              </label>
              <textarea
                name="description"
                value={formData.description}
                onChange={handleInputChange}
                rows="3"
                required
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              />
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Objetivo ODS
              </label>
              <select
                name="objective"
                value={formData.objective}
                onChange={handleInputChange}
                required
                className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
              >
                <option value="">Seleccionar Objetivo</option>
                {objectives.map(objective => (
                  <option key={objective.id} value={objective.id}>
                    {objective.id}. {objective.name}
                  </option>
                ))}
              </select>
            </div>

            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Fecha de Inicio
                </label>
                <input
                  type="date"
                  name="startDate"
                  value={formData.startDate}
                  onChange={handleInputChange}
                  required
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">
                  Fecha de Fin
                </label>
                <input
                  type="date"
                  name="endDate"
                  value={formData.endDate}
                  onChange={handleInputChange}
                  required
                  min={formData.startDate}
                  className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
            </div>

            {availableIndicators.length > 0 && (
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-4">
                  Indicadores a Medir
                </label>
                <div className="space-y-3 max-h-64 overflow-y-auto border border-gray-200 rounded-md p-4">
                  {availableIndicators.map(indicator => (
                    <div key={indicator} className="flex items-center justify-between">
                      <div className="flex items-center">
                        <input
                          type="checkbox"
                          id={indicator}
                          checked={formData.indicators.includes(indicator)}
                          onChange={() => handleIndicatorToggle(indicator)}
                          className="mr-3 h-4 w-4 text-blue-600 focus:ring-blue-500 border-gray-300 rounded"
                        />
                        <label htmlFor={indicator} className="text-sm text-gray-700">
                          {formatIndicatorName(indicator)}
                        </label>
                      </div>
                      {formData.indicators.includes(indicator) && (
                        <input
                          type="number"
                          step="0.01"
                          placeholder="Valor objetivo"
                          value={formData.targetValues[indicator] || ''}
                          onChange={(e) => handleTargetValueChange(indicator, e.target.value)}
                          required
                          className="w-32 px-2 py-1 text-sm border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                      )}
                    </div>
                  ))}
                </div>
              </div>
            )}

            <div className="flex justify-end space-x-4">
              <button
                type="button"
                onClick={() => navigate('/dashboard')}
                className="px-4 py-2 border border-gray-300 rounded-md text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-gray-500"
              >
                Cancelar
              </button>
              <button
                type="submit"
                disabled={loading || !formData.indicators.length}
                className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500 disabled:opacity-50"
              >
                {loading ? 'Creando...' : 'Crear Proyecto'}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default ProjectCreationPage;
