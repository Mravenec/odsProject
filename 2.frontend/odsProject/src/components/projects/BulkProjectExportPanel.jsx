import React, { useEffect, useState } from 'react';
import { FileDown } from 'lucide-react';
import { useAuth } from '../../hooks/useAuth.jsx';
import { usePermissions } from '../../hooks/usePermissions';
import { exportService } from '../../services/exportService';
import './BulkProjectExportPanel.css';

const buildYearOptions = () => {
  const current = new Date().getFullYear();
  return Array.from({ length: 8 }, (_, i) => current - i);
};

export default function BulkProjectExportPanel({ className = '' }) {
  const { getSedes } = useAuth();
  const perms = usePermissions();
  const [sedes, setSedes] = useState([]);
  const [bulkSedeId, setBulkSedeId] = useState('');
  const [bulkAnio, setBulkAnio] = useState(String(new Date().getFullYear()));
  const [bulkExporting, setBulkExporting] = useState(false);
  const yearOptions = buildYearOptions();

  useEffect(() => {
    if (!perms.canExportBulkProjects) return;
    (async () => {
      const r = await getSedes();
      if (r.success) setSedes(r.data || []);
    })();
  }, [perms.canExportBulkProjects, getSedes]);

  if (!perms.canExportBulkProjects) return null;

  const handleBulkExport = async () => {
    if (!bulkSedeId || !bulkAnio) {
      window.alert('Seleccione sede y año');
      return;
    }
    setBulkExporting(true);
    const r = await exportService.downloadProjectsExcel({
      sedeId: bulkSedeId,
      anio: bulkAnio,
    });
    setBulkExporting(false);
    if (!r.success) window.alert(r.error || 'No se pudo descargar el Excel consolidado');
  };

  return (
    <section className={`bulk-export-section ${className}`.trim()} id="export-consolidado">
      <div className="bulk-export-header">
        <FileDown size={18} />
        <div>
          <h2>Exportar proyectos evaluados</h2>
          <p>Excel consolidado de proyectos cerrados en el año y sede seleccionados.</p>
        </div>
      </div>
      <div className="bulk-export-controls">
        <label className="bulk-export-field">
          <span>Sede</span>
          <select
            value={bulkSedeId}
            onChange={(e) => setBulkSedeId(e.target.value)}
            disabled={bulkExporting}
          >
            <option value="">Seleccione sede</option>
            {sedes.map((s) => (
              <option key={s.id} value={s.id}>{s.nombre || s.name}</option>
            ))}
          </select>
        </label>
        <label className="bulk-export-field">
          <span>Año de evaluación</span>
          <select
            value={bulkAnio}
            onChange={(e) => setBulkAnio(e.target.value)}
            disabled={bulkExporting}
          >
            {yearOptions.map((y) => (
              <option key={y} value={y}>{y}</option>
            ))}
          </select>
        </label>
        <button
          type="button"
          className="btn-bulk-export"
          onClick={handleBulkExport}
          disabled={bulkExporting || !bulkSedeId || !bulkAnio}
        >
          <FileDown size={16} />
          {bulkExporting ? 'Generando…' : 'Descargar Excel consolidado'}
        </button>
      </div>
    </section>
  );
}
