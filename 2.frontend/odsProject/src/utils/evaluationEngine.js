/**
 * evaluationEngine.js — Sprint 4
 *
 * El motor canónico de evaluación es ahora el backend (exp4j vía
 * POST /api/evaluacion/preview). El cliente NO debe calcular números que
 * luego se persistan: el server siempre recalcula al recibir una medición
 * auditada.
 *
 * Este módulo expone dos APIs:
 *   1. evaluateRemote(formula, parametros, metaValor)  → async, pega al backend
 *   2. evaluationEngine (objeto compatible con la API anterior, para no romper
 *      llamadas síncronas existentes en el código): usa un intérprete simple,
 *      seguro, con tokenizador y AST mínimo. SIN regex+eval, SIN new Function().
 *
 * El cálculo síncrono local queda como UX preview hasta que el backend responde.
 * El valor que se persiste viene SIEMPRE del backend.
 */

import { evaluacionService } from '../services/evaluacionService';

/**
 * Evaluación remota — fuente de verdad.
 */
export async function evaluateRemote(formula, parametros, metaValor) {
  const result = await evaluacionService.preview(formula, parametros, metaValor);
  if (!result.ok) {
    throw new Error(result.error || 'Error evaluando la fórmula en el servidor');
  }
  return {
    valor: parseFloat(result.valor),
    metaAlcanzada: !!result.metaAlcanzada,
    formula: result.formula,
    parametros: result.parametros
  };
}

// ─────────────────────────────────────────────────────────────────
// Fallback local: tokenizador + Shunting-Yard + evaluador de RPN
// Solo para preview UX; los valores persistidos vienen del backend.
// ─────────────────────────────────────────────────────────────────

const PRECEDENCE = { '+': 2, '-': 2, '*': 3, '/': 3, 'u-': 4 };
const RIGHT_ASSOC = new Set(['u-']);
const FUNCTIONS = {
  abs: Math.abs, sqrt: Math.sqrt, floor: Math.floor, ceil: Math.ceil,
  round: Math.round, log: Math.log, log10: Math.log10, exp: Math.exp,
  sin: Math.sin, cos: Math.cos, tan: Math.tan
};
const CONSTANTS = { pi: Math.PI, e: Math.E };

function tokenize(formula) {
  const tokens = [];
  let i = 0;
  const f = String(formula).replace(/,/g, '.');
  while (i < f.length) {
    const c = f[i];
    if (/\s/.test(c)) { i++; continue; }
    if (/[0-9.]/.test(c)) {
      let num = '';
      while (i < f.length && /[0-9.]/.test(f[i])) { num += f[i++]; }
      tokens.push({ type: 'num', value: parseFloat(num) });
      continue;
    }
    if (/[a-zA-Z_]/.test(c)) {
      let id = '';
      while (i < f.length && /[a-zA-Z0-9_]/.test(f[i])) { id += f[i++]; }
      // function vs variable
      let j = i;
      while (j < f.length && /\s/.test(f[j])) j++;
      if (f[j] === '(' && FUNCTIONS[id]) {
        tokens.push({ type: 'func', value: id });
      } else if (id in CONSTANTS) {
        tokens.push({ type: 'num', value: CONSTANTS[id] });
      } else {
        tokens.push({ type: 'var', value: id });
      }
      continue;
    }
    if (c === '(' || c === ')') { tokens.push({ type: 'paren', value: c }); i++; continue; }
    if (c === '+' || c === '-' || c === '*' || c === '/') {
      // Detectar unary minus: si es el primer token o el anterior es un operador o paren abierto
      const prev = tokens[tokens.length - 1];
      const isUnary = c === '-' && (
        !prev || prev.type === 'op' || (prev.type === 'paren' && prev.value === '(') || prev.type === 'func'
      );
      tokens.push({ type: 'op', value: isUnary ? 'u-' : c });
      i++; continue;
    }
    throw new Error(`Carácter inválido en fórmula: '${c}'`);
  }
  return tokens;
}

function toRPN(tokens) {
  const output = [];
  const stack = [];
  for (const t of tokens) {
    if (t.type === 'num' || t.type === 'var') { output.push(t); continue; }
    if (t.type === 'func') { stack.push(t); continue; }
    if (t.type === 'op') {
      while (
        stack.length &&
        (stack[stack.length - 1].type === 'op' || stack[stack.length - 1].type === 'func') &&
        (stack[stack.length - 1].type === 'func' ||
          (PRECEDENCE[stack[stack.length - 1].value] > PRECEDENCE[t.value]) ||
          (PRECEDENCE[stack[stack.length - 1].value] === PRECEDENCE[t.value] && !RIGHT_ASSOC.has(t.value)))
      ) {
        output.push(stack.pop());
      }
      stack.push(t);
      continue;
    }
    if (t.type === 'paren' && t.value === '(') { stack.push(t); continue; }
    if (t.type === 'paren' && t.value === ')') {
      while (stack.length && !(stack[stack.length - 1].type === 'paren' && stack[stack.length - 1].value === '(')) {
        output.push(stack.pop());
      }
      if (!stack.length) throw new Error('Paréntesis desbalanceados');
      stack.pop(); // descartar '('
      if (stack.length && stack[stack.length - 1].type === 'func') output.push(stack.pop());
      continue;
    }
  }
  while (stack.length) {
    const t = stack.pop();
    if (t.type === 'paren') throw new Error('Paréntesis desbalanceados');
    output.push(t);
  }
  return output;
}

function evalRPN(rpn, variables) {
  const stack = [];
  for (const t of rpn) {
    if (t.type === 'num') { stack.push(t.value); continue; }
    if (t.type === 'var') {
      const v = variables[t.value];
      stack.push(v != null && !isNaN(parseFloat(v)) ? parseFloat(v) : 0);
      continue;
    }
    if (t.type === 'func') {
      const a = stack.pop();
      stack.push(FUNCTIONS[t.value](a));
      continue;
    }
    if (t.type === 'op') {
      if (t.value === 'u-') { stack.push(-stack.pop()); continue; }
      const b = stack.pop();
      const a = stack.pop();
      switch (t.value) {
        case '+': stack.push(a + b); break;
        case '-': stack.push(a - b); break;
        case '*': stack.push(a * b); break;
        case '/': stack.push(b === 0 ? 0 : a / b); break;
        default: throw new Error(`Operador desconocido: ${t.value}`);
      }
    }
  }
  return stack.length === 1 ? stack[0] : 0;
}

/**
 * Objeto compat-API: misma firma que el viejo evaluationEngine.
 * Solo usar como preview UX. Los valores persistidos los calcula el backend.
 */
export const evaluationEngine = {
  evaluateFormula(formula, parameters) {
    if (!formula) return 0;
    try {
      const tokens = tokenize(formula);
      const rpn = toRPN(tokens);
      const result = evalRPN(rpn, parameters || {});
      return isFinite(result) ? Math.round(result * 10000) / 10000 : 0;
    } catch (e) {
      console.warn('[evaluationEngine] preview local falló:', e.message);
      return 0;
    }
  },

  calculateAchievement(value, goal) {
    if (!goal || !goal.value) return 0;
    const goalVal = parseFloat(goal.value);
    if (!goalVal) return 0;
    const achievement = (parseFloat(value) / goalVal) * 100;
    return isFinite(achievement) ? achievement : 0;
  }
};
