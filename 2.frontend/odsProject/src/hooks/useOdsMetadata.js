import { useState, useCallback } from 'react';
import { getObjetivoService } from './objetivoServicesMap';
import { SDG_INDICATORS_CATALOG } from '../utils/planificacionEditorUtils';

/**
 * Metadatos de indicadores por ODS (catálogo master vía getIndicators(0)).
 * Usado en creación y edición de planificación — Service → Hook → Page.
 */
export function useOdsMetadata() {
  const [availableIndicators, setAvailableIndicators] = useState({});
  const [indicatorMetadata, setIndicatorMetadata] = useState({});
  const [loadingMetadata, setLoadingMetadata] = useState({});

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
      const codes = Object.keys(indicatorsData || {});
      const newMetadata = {};

      codes.forEach((code) => {
        const ind = indicatorsData[code];
        const fallbackDescription = SDG_INDICATORS_CATALOG[code];
        newMetadata[code] = {
          masterId: ind.masterId,
          description: (ind.name && ind.name.length > 5 && !ind.name.includes('Indicador'))
            ? ind.name
            : (fallbackDescription || `Seguimiento de metas técnicas para indicador ${code}`),
          unit: ind.unit || 'unidad',
        };
      });

      setAvailableIndicators((prev) => ({ ...prev, [odsId]: codes }));
      setIndicatorMetadata((prev) => {
        const merged = { ...prev, ...newMetadata };
        codes.forEach((code) => {
          const linked = prev[code];
          if (linked?.proyectoIndicadorId) {
            merged[code] = { ...merged[code], proyectoIndicadorId: linked.proyectoIndicadorId };
          }
        });
        return merged;
      });
    } catch (e) {
      console.error(`[useOdsMetadata] ODS ${odsId}:`, e);
    } finally {
      setLoadingMetadata((prev) => ({ ...prev, [odsId]: false }));
    }
  }, []);

  return {
    availableIndicators,
    setAvailableIndicators,
    indicatorMetadata,
    setIndicatorMetadata,
    loadingMetadata,
    hasMetadataForOds,
    loadOdsMetadata,
  };
}

export default useOdsMetadata;
