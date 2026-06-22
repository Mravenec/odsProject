import { useState, useCallback } from 'react';
import { exportService } from '../services/exportService';

export function useExport() {
  const [exporting, setExporting] = useState(false);
  const [error, setError] = useState(null);

  const runExport = useCallback(async (fn) => {
    setExporting(true);
    setError(null);
    try {
      const result = await fn();
      if (!result.success) setError(result.error);
      return result;
    } catch (e) {
      const msg = e.message || 'Error al exportar';
      setError(msg);
      return { success: false, error: msg };
    } finally {
      setExporting(false);
    }
  }, []);

  const downloadProjectExport = useCallback(
    (projectId) => runExport(() => exportService.downloadProjectExport(projectId)),
    [runExport]
  );

  const downloadConsolidatedExport = useCallback(
    () => runExport(() => exportService.downloadConsolidatedExport()),
    [runExport]
  );

  const downloadProjectsExcel = useCallback(
    (params) => runExport(() => exportService.downloadProjectsExcel(params)),
    [runExport]
  );

  return {
    exporting,
    error,
    downloadProjectExport,
    downloadProjectFullReport: downloadProjectExport,
    downloadConsolidatedExport,
    downloadProjectsExcel,
  };
}

export default useExport;
