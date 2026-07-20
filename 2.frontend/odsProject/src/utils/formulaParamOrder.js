/** Identificadores reservados del motor de fórmulas (no son parámetros). */
const RESERVED = new Set([
  'sqrt', 'sin', 'cos', 'tan', 'log', 'exp', 'round', 'floor', 'ceil', 'abs', 'pi', 'e', 'valor', 'count',
]);

/**
 * Nombres de variables en orden de primera aparición en la fórmula.
 * Ej: "(a + c) / b" → ["a", "c", "b"]
 */
export function extractFormulaVarOrder(formula) {
  if (!formula) return [];
  const ordered = [];
  const seen = new Set();
  for (const token of String(formula).match(/[a-zA-Z_][a-zA-Z0-9_]*/g) || []) {
    const key = token.toLowerCase();
    if (RESERVED.has(key) || seen.has(token)) continue;
    seen.add(token);
    ordered.push(token);
  }
  return ordered;
}

/**
 * Ordena parámetros según la fórmula (no por id / alfabeto).
 * Parámetros ausentes de la fórmula quedan al final.
 */
export function sortParamsByFormulaOrder(formula, params) {
  const list = Array.isArray(params) ? params : [];
  if (!formula || list.length <= 1) return list;

  const order = extractFormulaVarOrder(formula);
  if (order.length === 0) return list;

  const indexOf = new Map(order.map((name, i) => [name, i]));
  const paramName = (p) => p?.nombreVariable || p?.nombre_variable || p?.nombreParametro || p?.nombre_parametro || p?.name || '';

  return [...list].sort((a, b) => {
    const na = paramName(a);
    const nb = paramName(b);
    const ia = indexOf.has(na) ? indexOf.get(na) : order.length;
    const ib = indexOf.has(nb) ? indexOf.get(nb) : order.length;
    if (ia !== ib) return ia - ib;
    return 0;
  });
}
