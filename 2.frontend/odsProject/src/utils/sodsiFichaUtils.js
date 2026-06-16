export const emptyFichaSodsi = () => ({
  ejePlanesId: '',
  aliadoExterno: '',
  beneficiarioValorIds: [],
});

export function normalizeFichaSodsiFromSnapshot(raw) {
  if (!raw || typeof raw !== 'object') return emptyFichaSodsi();

  const beneficiarioValorIds = Array.isArray(raw.beneficiarioValorIds)
    ? raw.beneficiarioValorIds.map((id) => Number(id)).filter((n) => !Number.isNaN(n))
    : (Array.isArray(raw.beneficiarios)
      ? raw.beneficiarios
        .map((b) => Number(b.valorId ?? b.valor_id))
        .filter((n) => !Number.isNaN(n))
      : []);

  return {
    ejePlanesId: raw.ejePlanesId != null ? String(raw.ejePlanesId) : '',
    aliadoExterno: raw.aliadoExterno || '',
    beneficiarioValorIds,
  };
}

export function fichaSodsiToPayload(ficha) {
  const f = ficha || emptyFichaSodsi();
  return {
    ejePlanesId: f.ejePlanesId ? Number(f.ejePlanesId) : null,
    aliadoExterno: f.aliadoExterno?.trim() || null,
    beneficiarioValorIds: (f.beneficiarioValorIds || [])
      .map((id) => Number(id))
      .filter((n) => !Number.isNaN(n)),
  };
}
