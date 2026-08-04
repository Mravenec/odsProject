import { useEffect, useRef } from 'react';

/**
 * Poll silencioso (sin spinner) — mismo patrón que useProjectChat / Staff inbox.
 * @param {() => void | Promise<void>} fn
 * @param {number} ms
 * @param {boolean} enabled
 */
export function useSilentPoll(fn, ms, enabled = true) {
  const fnRef = useRef(fn);
  fnRef.current = fn;

  useEffect(() => {
    if (!enabled || !ms || ms < 1000) return undefined;
    const id = setInterval(() => {
      try {
        const r = fnRef.current?.();
        if (r && typeof r.then === 'function') r.catch(() => {});
      } catch { /* ignore */ }
    }, ms);
    return () => clearInterval(id);
  }, [ms, enabled]);
}

export default useSilentPoll;
