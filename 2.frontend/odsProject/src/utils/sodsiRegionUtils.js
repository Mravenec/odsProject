/** Región Mideplan derivada de provincia (sodsi_provincias en catálogo). */
export function resolveRegionMideplan(provinciaNombre, catalogs) {
  if (!provinciaNombre?.trim()) return null;
  const provincias = catalogs?.provincias || [];
  const prov = provincias.find(
    (p) => String(p.nombre || '').toLowerCase() === String(provinciaNombre).toLowerCase(),
  );
  if (!prov) return null;
  const regionId = prov.regionMideplanId ?? prov.region_mideplan_id;
  const regiones = catalogs?.regionesMideplan || [];
  const region = regiones.find((r) => Number(r.id) === Number(regionId));
  return region?.nombre || null;
}

export default resolveRegionMideplan;
