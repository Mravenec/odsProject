import { objetivo01Service } from '../services/objetivo01Service';
import { objetivo02Service } from '../services/objetivo02Service';
import { objetivo03Service } from '../services/objetivo03Service';
import { objetivo04Service } from '../services/objetivo04Service';
import { objetivo05Service } from '../services/objetivo05Service';
import { objetivo06Service } from '../services/objetivo06Service';
import { objetivo07Service } from '../services/objetivo07Service';
import { objetivo08Service } from '../services/objetivo08Service';
import { objetivo09Service } from '../services/objetivo09Service';
import { objetivo10Service } from '../services/objetivo10Service';
import { objetivo11Service } from '../services/objetivo11Service';
import { objetivo12Service } from '../services/objetivo12Service';
import { objetivo13Service } from '../services/objetivo13Service';
import { objetivo14Service } from '../services/objetivo14Service';
import { objetivo15Service } from '../services/objetivo15Service';
import { objetivo16Service } from '../services/objetivo16Service';
import { objetivo17Service } from '../services/objetivo17Service';

/** Mapa ODS número → service Axios (capa única; hooks y utils importan desde aquí). */
export const OBJETIVO_SERVICES_MAP = {
  1: objetivo01Service,
  2: objetivo02Service,
  3: objetivo03Service,
  4: objetivo04Service,
  5: objetivo05Service,
  6: objetivo06Service,
  7: objetivo07Service,
  8: objetivo08Service,
  9: objetivo09Service,
  10: objetivo10Service,
  11: objetivo11Service,
  12: objetivo12Service,
  13: objetivo13Service,
  14: objetivo14Service,
  15: objetivo15Service,
  16: objetivo16Service,
  17: objetivo17Service,
};

export const getObjetivoService = (odsId) => {
  const n = Number(odsId);
  return OBJETIVO_SERVICES_MAP[n] || null;
};

/** @deprecated Usar OBJETIVO_SERVICES_MAP — alias para compatibilidad con utils legacy. */
export const SERVICES_MAP = OBJETIVO_SERVICES_MAP;
