import React, { useState, useEffect, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';
import { usePermissions } from '../../hooks/usePermissions';
import { projectService } from '../../services/projectService';
import { evaluationEngine } from '../../utils/evaluationEngine';

/**
 * EvaluationPage — Sprint 4 / 5
 *
 * Cambios vs. la versión anterior:
 *   1. Se envía proyecto_indicadores.id (ind.id) y NO el indicadorMasterId
 *      al guardar la medición.
 *   2. Usa el endpoint /mediciones/auditada que recalcula server-side y persiste
 *      medicion_parametro_valores en una sola transacción.
 *   3. Agrega tab "Auditoría" para ver la traza de mediciones por indicador.
 *   4. El cálculo local sigue mostrándose como preview (UX inmediata) pero el
 *      valor que queda guardado es el que devuelve el backend.
 */

// Resolver dinámico de servicios por ODS
const getService = async (odsNum) => {
  const n = String(odsNum).padStart(2, '0');
  try {
    const mod = await import(`../../services/objetivo${n}Service.js`);
    return mod.default || mod[`objetivo${n}Service`];
  } catch { return null; }
};

const estadoColors = {
  'LOGRADO':    '#2dba74', 'CERCA META': '#012169',
  'PROGRESO':   '#e8c33a', 'BAJO':       '#e05555', 'SIN DATOS':  '#94A0B8'
};
const estadoLabels = {
  'LOGRADO':'✅ Logrado','CERCA META':'🔵 Cerca de la meta',
  'PROGRESO':'🟡 En progreso','BAJO':'🔴 Bajo','SIN DATOS':'⚪ Sin datos'
};

const EvaluationPage = () => {
  const { projectId } = useParams();
  const navigate = useNavigate();
  const { user } = useAuth();
  const perms = usePermissions();
  const readOnly = !perms.canEnterMeasurements;
  const [project, setProject]           = useState(null);

  // ── Sprint 17 — Modales de aprobar / rechazar ─────────────────────
  const [showApproveModal, setShowApproveModal] = useState(false);
  const [showRejectModal, setShowRejectModal]   = useState(false);
  const [approveObs, setApproveObs] = useState('');
  const [rejectMotivo, setRejectMotivo] = useState('');
  const [auditClosing, setAuditClosing] = useState(false);
  const [auditError, setAuditError] = useState(null);

  const handleApprove = async () => {
    setAuditClosing(true); setAuditError(null);
    const r = await projectService.approveAudit(
      parseInt(projectId), user.id, user.role, approveObs.trim() || null
    );
    setAuditClosing(false);
    if (!r.success) { setAuditError(r.error); return; }
    setShowApproveModal(false);
    alert('✓ Auditoría cerrada exitosamente. El proyecto queda firmado y bloqueado.');
    navigate('/audit');
  };

  const handleReject = async () => {
    if (rejectMotivo.trim().length < 10) {
      setAuditError('El motivo debe tener al menos 10 caracteres'); return;
    }
    setAuditClosing(true); setAuditError(null);
    const r = await projectService.rejectAudit(
      parseInt(projectId), user.id, user.role, rejectMotivo.trim()
    );
    setAuditClosing(false);
    if (!r.success) { setAuditError(r.error); return; }
    setShowRejectModal(false);
    alert('Proyecto devuelto al gestor con el motivo del rechazo.');
    navigate('/audit');
  };
  const [allIndicators, setAllIndicators] = useState({});
  const [paramInputs, setParamInputs]   = useState({});
  const [calcResults, setCalcResults]   = useState({});
  const [activeTab, setActiveTab]       = useState('ingreso');
  const [openOds, setOpenOds]           = useState({});
  const [saving, setSaving]             = useState({});
  const [loadingPage, setLoadingPage]   = useState(true);
  const [auditTrail, setAuditTrail]     = useState({});       // codigo -> [auditoria]
  const [loadingAudit, setLoadingAudit] = useState({});

  const loadAllIndicators = useCallback(async (pid, odsIds = []) => {
    const result = {};
    const targets = (Array.isArray(odsIds) && odsIds.length > 0) 
      ? odsIds 
      : Array.from({length: 17}, (_, i) => i + 1);

    for (const n of targets) {
      try {
        const svc = await getService(n);
        if (!svc) continue;
        const data = await (svc.getIndicators ? svc.getIndicators(pid) : svc.getAllIndicators?.(pid));
        if (!data || Object.keys(data).length === 0) continue;

        const metasRes = svc.getMetasProyecto ? await svc.getMetasProyecto(pid) : { data: [] };
        const metas = metasRes.data || [];

        // Sprint 15: filtramos por proyectoId (ahora preservado en el mapping)
        const list = Object.values(data).filter(i => i && i.proyectoId);

        // Sprint 15: matchear parámetros a indicadores por nombre de variable
        // (el backend no expone proyecto_indicador_id en la vista, por eso no
        // podemos hacer match por id). Extraemos variables de cada fórmula.
        const RESERVED = new Set(['sqrt','sin','cos','tan','log','exp','round','floor','ceil','abs','pi','e','valor','count']);
        const extractVars = (formula) => {
          if (!formula) return new Set();
          return new Set((String(formula).match(/[a-zA-Z_][a-zA-Z0-9_]*/g) || [])
            .filter(v => !RESERVED.has(v.toLowerCase())));
        };

        const enriched = list.map(ind => {
          const vars = extractVars(ind.formula || ind.formulaCustom);
          const matchingParams = metas.filter(m => {
            const varName = m.nombreVariable || m.nombre_variable || m.nombreParametro || m.nombre_parametro;
            return vars.has(varName);
          });
          return {
            ...ind,
            indicadorCodigo: ind.indicadorCodigo || ind.codigo || ind.code,
            indicadorNombre: ind.indicadorNombre || ind.nombre || ind.name,
            indicadorMasterId: ind.indicadorMasterId || ind.masterId,
            formulaCustom: ind.formulaCustom || ind.formula,
            formulaDefault: ind.formulaDefault || 'valor',
            metaValor: ind.metaValor || ind.targetValue || 0,
            metaUnidad: ind.metaUnidad || ind.unit || 'Porcentaje',
            metaNombre: ind.metaNombre || '',
            estadoIndicador: ind.estadoIndicador || 'SIN DATOS',
            porcentajeLogro: ind.porcentajeLogro || 0,
            // Normalizar params: usar las claves camelCase consistentes
            parametros: matchingParams.map(p => ({
              id: p.id ?? p.ID,
              nombreParametro: p.nombreParametro ?? p.nombre_parametro,
              nombreVariable:  p.nombreVariable  ?? p.nombre_variable,
              tipoDato:        p.tipoDato        ?? p.tipo_dato,
              valorActual:     p.valorActual     ?? p.valor_actual
            }))
          };
        });

        if (enriched.length > 0) result[n] = enriched;
      } catch (e) { console.error('ODS', n, e); }
    }
    return result;
  }, []);

  useEffect(() => {
    const load = async () => {
      setLoadingPage(true);
      try {
        const projRes = await projectService.getProjectById(parseInt(projectId));
        const pData = projRes.data || projRes;
        setProject(pData);
        
        // Sprint UTN: obtener ODS vinculados explícitamente para evitar escaneo de 1-17
        let odsToLoad = pData.odsVinculados || [];
        if (odsToLoad.length === 0) {
          const links = await projectService.getOdsByProyecto(parseInt(projectId));
          odsToLoad = (links.data || []).map(l => l.odsId || l.ods_id);
        }
        
        const indicators = await loadAllIndicators(parseInt(projectId), odsToLoad);
        setAllIndicators(indicators);
        const opened = {};
        Object.keys(indicators).forEach(k => { opened[k] = true; });
        setOpenOds(opened);
      } catch (e) { console.error(e); }
      setLoadingPage(false);
    };
    if (projectId) load();
  }, [projectId, loadAllIndicators]);

  const handleParamChange = (codigo, paramVar, value) => {
    setParamInputs(prev => ({
      ...prev,
      [codigo]: { ...(prev[codigo] || {}), [paramVar]: value }
    }));
  };

  /**
   * Calcula y persiste vía /mediciones/auditada.
   * El backend recalcula valor_calculado con exp4j; el valor del cliente es solo preview UX.
   */
  const handleCalcular = async (odsNum, indicator) => {
    // Sprint 14: bloqueo dura para roles solo-lectura (consultor, gestor)
    if (readOnly) {
      alert('Tu rol no permite ingresar mediciones. Esta vista es de solo lectura.');
      return;
    }

    const codigo = indicator.indicadorCodigo;
    const formula = indicator.formulaCustom || indicator.formulaDefault || 'valor';
    const params = paramInputs[codigo] || {};

    // Preview local (rápido, no persiste)
    const localPreview = formula === 'valor'
      ? parseFloat(Object.values(params)[0] || 0)
      : evaluationEngine.evaluateFormula(formula, params);

    setSaving(prev => ({ ...prev, [codigo]: true }));
    try {
      const svc = await getService(odsNum);

      // ── Sprint 15: resolver proyecto_indicador.id ──────────────────────
      // El backend no expone pi.id en la vista. Hacemos UPSERT idempotente
      // (saveIndicator) pasando exactamente los mismos valores que ya están en
      // BD; eso devuelve la fila con su id correcto.
      let proyectoIndicadorId = indicator.id;
      if (!proyectoIndicadorId && svc.saveIndicator) {
        try {
          const upsertRes = await svc.saveIndicator({
            proyectoId: parseInt(projectId),
            indicadorMasterId: indicator.indicadorMasterId || indicator.masterId,
            metaValor: parseFloat(indicator.metaValor) || 0,
            metaUnidad: indicator.metaUnidad || 'unidad',
            metaNombre: indicator.metaNombre || null,
            formulaCustom: indicator.formulaCustom || indicator.formula || null
          });
          proyectoIndicadorId = upsertRes?.data?.id ?? upsertRes?.id;
          indicator.id = proyectoIndicadorId; // cache para próximos clicks
        } catch (e) {
          console.warn('No se pudo resolver proyectoIndicadorId vía saveIndicator:', e);
        }
      }

      if (!proyectoIndicadorId) {
        alert('No se pudo identificar el indicador en BD. Recargá la página e intentá de nuevo.');
        return;
      }

      // ── Sprint 15: resolver los param.id si no los tenemos en local ──
      let parametrosLocales = indicator.parametros || [];
      if (parametrosLocales.length === 0 || !parametrosLocales[0]?.id) {
        if (svc.getMetasProyecto) {
          try {
            const refresh = await svc.getMetasProyecto(parseInt(projectId));
            const allMetas = refresh?.data || [];
            // Filtrar los que pertenecen a este indicador
            parametrosLocales = allMetas
              .filter(m => (m.proyectoIndicadorId ?? m.proyecto_indicador_id) === proyectoIndicadorId)
              .map(p => ({
                id: p.id ?? p.ID,
                nombreParametro: p.nombreParametro ?? p.nombre_parametro,
                nombreVariable:  p.nombreVariable  ?? p.nombre_variable,
                tipoDato:        p.tipoDato        ?? p.tipo_dato,
                valorActual:     p.valorActual     ?? p.valor_actual
              }));
            indicator.parametros = parametrosLocales;
          } catch {}
        }
      }

      // Construir { parametroId → valor } para el backend
      const valoresParametros = {};
      for (const p of parametrosLocales) {
        const varName = p.nombreVariable || p.nombreParametro;
        const v = parseFloat(params[varName]);
        if (!isNaN(v) && p.id != null) {
          valoresParametros[p.id] = v;
        }
      }

      if (!svc?.createMedicionAuditada) {
        // Fallback: usar createMedicion si createMedicionAuditada no existe
        if (svc?.createMedicion) {
          await svc.createMedicion({
            proyectoIndicadorId,
            valorCalculado: localPreview,
            fechaMedicion: new Date().toISOString().split('T')[0],
            responsable: user?.fullName || user?.name || 'Sistema'
          });
        }
        const metaVal = parseFloat(indicator.metaValor || 0);
        const pct = metaVal > 0 ? Math.min((localPreview / metaVal) * 100, 200) : 0;
        const status = localPreview >= metaVal ? 'Cumplido' : 'En Progreso';
        setCalcResults(prev => ({ ...prev, [codigo]: { result: localPreview, pct, status } }));
        return;
      }

      // Endpoint auditado del backend
      const auditedRes = await svc.createMedicionAuditada({
        proyectoIndicadorId,
        fechaMedicion: new Date().toISOString().split('T')[0],
        responsable: user?.fullName || user?.name || 'Sistema',
        metodoMedicion: 'manual',
        valoresParametros
      });

      // El backend devuelve { medicion, valor, metaValor, metaAlcanzada, estado, ... }
      const valor = parseFloat(auditedRes.data?.valor || auditedRes.valor || localPreview);
      const metaVal = parseFloat(auditedRes.data?.metaValor || indicator.metaValor || 0);
      const alcanzada = (auditedRes.data || auditedRes).metaAlcanzada;
      const pct = metaVal > 0 ? Math.min((valor / metaVal) * 100, 200) : 0;
      const status = alcanzada ? 'Cumplido' : 'En Progreso';
      setCalcResults(prev => ({
        ...prev,
        [codigo]: {
          result: valor, pct, status,
          medicionId: (auditedRes.data || auditedRes).medicion?.id,
          backendCalculated: true
        }
      }));

      // Refrescar auditoría visible (si el tab está abierto)
      if (activeTab === 'auditoria') refreshAuditTrail(odsNum, indicator);
    } catch (e) {
      console.error('Error guardando medición auditada:', e);
      alert('Error al guardar la medición. Verifique la consola.');
    } finally {
      setSaving(prev => ({ ...prev, [codigo]: false }));
    }
  };

  const refreshAuditTrail = async (odsNum, indicator) => {
    const codigo = indicator.indicadorCodigo;
    setLoadingAudit(prev => ({ ...prev, [codigo]: true }));
    try {
      const svc = await getService(odsNum);
      if (!svc?.getMediciones) return;
      const res = await svc.getMediciones(indicator.id);
      const mediciones = res.data || [];

      // Para cada medición, traer la traza completa
      const trail = [];
      for (const m of mediciones) {
        if (svc.getMedicionAuditoria) {
          try {
            const aud = await svc.getMedicionAuditoria(m.id);
            trail.push(aud.data || aud);
          } catch {
            trail.push({ medicion: m });
          }
        } else {
          trail.push({ medicion: m });
        }
      }
      setAuditTrail(prev => ({ ...prev, [codigo]: trail }));
    } catch (e) {
      console.error('Error cargando auditoría:', e);
    } finally {
      setLoadingAudit(prev => ({ ...prev, [codigo]: false }));
    }
  };

  // Cargar auditoría al cambiar de tab
  useEffect(() => {
    if (activeTab !== 'auditoria') return;
    Object.entries(allIndicators).forEach(([odsNum, indicadores]) => {
      indicadores.forEach(ind => {
        if (!auditTrail[ind.indicadorCodigo]) refreshAuditTrail(parseInt(odsNum), ind);
      });
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [activeTab]);

  if (loadingPage) return (
    <div style={{display:'flex',alignItems:'center',justifyContent:'center',height:'60vh',flexDirection:'column',gap:12}}>
      <div style={{width:40,height:40,border:'4px solid #D9DEE7',borderTopColor:'#012169',borderRadius:'50%',animation:'spin 0.8s linear infinite'}} />
      <span style={{color:'#5A6478',fontSize:14}}>Cargando evaluación...</span>
      <style>{`@keyframes spin{to{transform:rotate(360deg)}}`}</style>
    </div>
  );

  const totalIndicadores = Object.values(allIndicators).flat().length;
  const logrados = Object.values(calcResults).filter(r => r.status === 'Cumplido').length;

  return (
    <div style={{maxWidth:900,margin:'0 auto',padding:'24px 20px'}}>
      {/* Header */}
      <div style={{background:'#fff',border:'1px solid #D9DEE7',borderRadius:12,padding:20,marginBottom:20}}>
        <div style={{display:'flex',justifyContent:'space-between',alignItems:'flex-start',flexWrap:'wrap',gap:12}}>
          <div style={{flex:1}}>
            <div style={{fontSize:11,color:'#5A6478',fontFamily:'monospace',letterSpacing:'0.06em',marginBottom:4}}>
              PROYECTO #{projectId}
            </div>
            <h1 style={{fontSize:20,fontWeight:700,color:'#00153f',margin:0}}>
              {project?.name || project?.nombreProyecto || 'Proyecto'}
            </h1>
          </div>
          <div style={{display:'flex',gap:8,alignItems:'center',flexWrap:'wrap'}}>
            {/* Sprint 17 — Botones de cierre del auditor.
               Solo visibles si el proyecto está 'en_revision' y el usuario es admin/auditor. */}
            {project && String(project.status||'').toLowerCase() === 'en_revision'
              && (user?.role === 'admin' || user?.role === 'auditor') && (
              <>
                <button onClick={() => setShowApproveModal(true)} style={{
                  padding:'8px 14px',border:'none',borderRadius:8,
                  background:'#1F9D55',color:'#fff',cursor:'pointer',fontSize:13,fontWeight:700,
                  boxShadow:'0 4px 10px -2px rgba(31,157,85,0.25)'
                }}>✓ Aprobar auditoría</button>
                <button onClick={() => setShowRejectModal(true)} style={{
                  padding:'8px 14px',border:'1px solid #C53030',borderRadius:8,
                  background:'#fff',color:'#C53030',cursor:'pointer',fontSize:13,fontWeight:700
                }}>✗ Rechazar</button>
              </>
            )}
            <button onClick={() => navigate(-1)} style={{
              padding:'8px 14px',border:'1px solid #D9DEE7',borderRadius:8,
              background:'#fff',cursor:'pointer',fontSize:13,color:'#1B2440'
            }}>← Volver</button>
          </div>
        </div>

        {/* Métricas resumidas */}
        <div style={{display:'flex',gap:24,marginTop:14,flexWrap:'wrap'}}>
          <div>
            <div style={{fontSize:11,color:'#5A6478',textTransform:'uppercase',letterSpacing:'0.06em'}}>Indicadores</div>
            <div style={{fontSize:22,fontWeight:700,color:'#00153f'}}>{totalIndicadores}</div>
          </div>
          <div>
            <div style={{fontSize:11,color:'#5A6478',textTransform:'uppercase',letterSpacing:'0.06em'}}>Logrados</div>
            <div style={{fontSize:22,fontWeight:700,color:'#2dba74'}}>{logrados}</div>
          </div>
        </div>
      </div>

      {/* Tabs */}
      <div style={{display:'flex',gap:6,marginBottom:18}}>
        {[
          {k:'ingreso',  label:'📥 Ingresar valores'},
          {k:'resumen',  label:'📊 Resumen'},
          {k:'auditoria',label:'🔍 Auditoría'}
        ].map(t => (
          <button key={t.k} onClick={() => setActiveTab(t.k)} style={{
            padding:'8px 16px',borderRadius:8,
            background: activeTab===t.k ? '#012169' : '#fff',
            color: activeTab===t.k ? '#fff' : '#1B2440',
            border:'1px solid ' + (activeTab===t.k ? '#012169' : '#D9DEE7'),
            fontSize:13,fontWeight:500,cursor:'pointer'
          }}>{t.label}</button>
        ))}
      </div>

      {/* TAB: Ingreso */}
      {activeTab === 'ingreso' && (
        <div style={{display:'grid',gap:14}}>
          {Object.entries(allIndicators).map(([odsNum, indicadores]) => (
            <div key={odsNum} style={{background:'#fff',border:'1px solid #D9DEE7',borderRadius:12,overflow:'hidden'}}>
              <div onClick={() => setOpenOds(p => ({...p,[odsNum]: !p[odsNum]}))} style={{
                padding:14,cursor:'pointer',display:'flex',alignItems:'center',gap:10,
                borderBottom: openOds[odsNum] ? '1px solid #F1F4FA' : 'none'
              }}>
                <div style={{
                  width:32,height:32,borderRadius:8,background:'#012169',
                  display:'flex',alignItems:'center',justifyContent:'center',
                  fontSize:14,fontWeight:700,color:'#fff',flexShrink:0
                }}>{odsNum}</div>
                <div style={{flex:1,fontWeight:500,fontSize:14}}>ODS {odsNum} — {indicadores.length} indicador{indicadores.length!==1?'es':''}</div>
                <span style={{color:'#94A0B8',fontSize:12}}>{openOds[odsNum] ? '▲' : '▼'}</span>
              </div>

              {openOds[odsNum] && indicadores.map(ind => {
                const codigo = ind.indicadorCodigo;
                const calc = calcResults[codigo];
                const isSaving = saving[codigo];
                return (
                  <div key={codigo} style={{padding:18,borderBottom:'1px solid #F1F4FA'}}>
                    <div style={{display:'flex',justifyContent:'space-between',alignItems:'flex-start',marginBottom:14}}>
                      <div>
                        <div style={{fontFamily:'monospace',fontSize:11,color:'#5A6478',marginBottom:3}}>{codigo}</div>
                        <div style={{fontWeight:600,fontSize:14,color:'#00153f'}}>{ind.indicadorNombre}</div>
                      </div>
                      {calc && (
                        <div style={{
                          padding:'4px 12px',borderRadius:99,fontSize:12,fontWeight:600,
                          background: calc.status==='Cumplido' ? '#e6f9f0' : '#fff3f3',
                          color: calc.status==='Cumplido' ? '#2dba74' : '#e05555',
                          border: `1px solid ${calc.status==='Cumplido' ? '#a7f0ca' : '#ffb8b8'}`
                        }}>
                          {calc.status==='Cumplido' ? '✅ Cumplido' : '🔄 En Progreso'}
                          {calc.backendCalculated && <span style={{marginLeft:6,fontWeight:400,opacity:0.7}}>· auditado</span>}
                        </div>
                      )}
                    </div>

                    <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:10,marginBottom:14}}>
                      <div style={{background:'#d6e4f3',borderRadius:8,padding:'10px 14px'}}>
                        <div style={{fontSize:10,color:'#012169',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:4}}>Fórmula</div>
                        <code style={{fontSize:12,color:'#012169'}}>{ind.formulaCustom || ind.formulaDefault || 'valor'}</code>
                      </div>
                      <div style={{background:'#f0fdf4',borderRadius:8,padding:'10px 14px'}}>
                        <div style={{fontSize:10,color:'#22c55e',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:4}}>Meta</div>
                        <div style={{fontSize:13,fontWeight:500,color:'#166534'}}>
                          {ind.metaValor} {ind.metaUnidad}
                          {ind.metaNombre && <div style={{fontSize:11,color:'#1B2440',marginTop:2,fontWeight:400}}>{ind.metaNombre}</div>}
                        </div>
                      </div>
                    </div>

                    {calc && (
                      <div style={{background: calc.status==='Cumplido'?'#e6f9f0':'#fff3f3',borderRadius:8,padding:'10px 14px',marginBottom:14,display:'flex',alignItems:'center',gap:12}}>
                        <div style={{flex:1}}>
                          <div style={{fontSize:10,color:'#5A6478',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:2}}>Resultado calculado</div>
                          <div style={{fontSize:18,fontWeight:700,color: calc.status==='Cumplido'?'#2dba74':'#e05555'}}>
                            {calc.result} <span style={{fontSize:13,fontWeight:400}}>{ind.metaUnidad}</span>
                          </div>
                        </div>
                        <div style={{textAlign:'center'}}>
                          <div style={{fontSize:10,color:'#5A6478',marginBottom:2}}>Progreso</div>
                          <div style={{fontSize:16,fontWeight:700}}>{calc.pct.toFixed(1)}%</div>
                        </div>
                      </div>
                    )}

                    <div style={{display:'grid',gridTemplateColumns:'repeat(auto-fit,minmax(200px,1fr))',gap:10,marginBottom:14}}>
                      {(ind.parametros || []).map((param, idx) => (
                        <div key={idx}>
                          <label style={{fontSize:12,color:'#1B2440',display:'block',marginBottom:4}}>
                            <code style={{
                              fontFamily:'monospace',color:'#012169',marginRight:6,
                              background:'#d6e4f3',padding:'1px 5px',borderRadius:3
                            }}>{param.nombreVariable || param.nombreParametro}</code>
                            {param.nombreParametro && param.nombreParametro !== param.nombreVariable && (
                              <span style={{color:'#5A6478',fontSize:11}}>{param.nombreParametro}</span>
                            )}
                          </label>
                          <input
                            type="number"
                            disabled={readOnly}
                            value={paramInputs[codigo]?.[param.nombreVariable || param.nombreParametro] ?? ''}
                            onChange={e => handleParamChange(codigo, param.nombreVariable || param.nombreParametro, e.target.value)}
                            placeholder={readOnly ? `Solo lectura · actual: ${param.valorActual ?? 0}` : `Valor actual: ${param.valorActual ?? 0}`}
                            style={{
                              width:'100%',border:'1px solid #ddd',borderRadius:8,
                              padding:'9px 12px',fontSize:13,boxSizing:'border-box',
                              background: readOnly ? '#F7F9FC' : '#fff',
                              color: readOnly ? '#5A6478' : '#00153f',
                              cursor: readOnly ? 'not-allowed' : 'text'
                            }}
                          />
                        </div>
                      ))}
                    </div>

                    {(ind.parametros || []).length === 0 && (
                      <div style={{padding:14,background:'#fef3c7',color:'#92400e',
                                   borderRadius:8,fontSize:13,marginBottom:14}}>
                        ⚠️ Este indicador no tiene parámetros configurados.
                        El gestor debe agregarlos en el formulario de creación.
                      </div>
                    )}

                    {!readOnly ? (
                      <button
                        onClick={() => handleCalcular(odsNum, ind)}
                        disabled={isSaving || (ind.parametros || []).length === 0}
                        style={{
                          padding:'10px 22px',border:'none',borderRadius:8,
                          background: isSaving ? '#94a3b8' : '#012169',color:'#fff',
                          cursor: isSaving ? 'not-allowed' : 'pointer',fontSize:13,fontWeight:500,
                          opacity: (ind.parametros || []).length === 0 ? 0.5 : 1
                        }}
                      >
                        {isSaving ? 'Guardando...' : '🔢 Calcular y Evaluar'}
                      </button>
                    ) : (
                      <div style={{fontSize:12,color:'#5A6478',padding:'8px 0',fontStyle:'italic'}}>
                        🔒 Tu rol no permite ingresar mediciones. Esta es una vista de solo lectura.
                      </div>
                    )}
                  </div>
                );
              })}
            </div>
          ))}
        </div>
      )}

      {/* TAB: Resumen */}
      {activeTab === 'resumen' && (
        <div style={{display:'grid',gridTemplateColumns:'repeat(auto-fill,minmax(260px,1fr))',gap:14}}>
          {Object.entries(allIndicators).flatMap(([odsNum, indicadores]) =>
            indicadores.map(ind => {
              const codigo = ind.indicadorCodigo;
              const calc = calcResults[codigo];
              const estado = calc
                ? (calc.status === 'Cumplido' ? 'LOGRADO' : 'PROGRESO')
                : (ind.estadoIndicador || 'SIN DATOS');
              const pct = calc ? calc.pct : (ind.porcentajeLogro || 0);
              return (
                <div key={codigo} style={{background:'#fff',border:'1px solid #D9DEE7',borderRadius:12,padding:16}}>
                  <div style={{fontFamily:'monospace',fontSize:10,color:'#5A6478',marginBottom:4}}>{codigo}</div>
                  <div style={{fontWeight:600,fontSize:13,marginBottom:10,color:'#00153f',lineHeight:1.4}}>
                    {ind.indicadorNombre}
                  </div>
                  <div style={{
                    display:'inline-block',padding:'3px 10px',borderRadius:99,fontSize:11,fontWeight:600,
                    background: (estadoColors[estado]||'#94A0B8') + '22',
                    color: estadoColors[estado] || '#5A6478',marginBottom:10
                  }}>{estadoLabels[estado] || estado}</div>
                  <div style={{background:'#F1F4FA',borderRadius:99,height:6,overflow:'hidden'}}>
                    <div style={{
                      width: Math.min(pct,100) + '%',height:'100%',borderRadius:99,
                      background: estadoColors[estado] || '#94A0B8',transition:'width 0.4s'
                    }} />
                  </div>
                  <div style={{textAlign:'right',fontSize:11,color:'#5A6478',marginTop:4}}>
                    {pct.toFixed(1)}% de {ind.metaValor} {ind.metaUnidad}
                  </div>
                </div>
              );
            })
          )}
          {Object.values(allIndicators).flat().length === 0 && (
            <div style={{gridColumn:'1/-1',textAlign:'center',padding:40,color:'#5A6478'}}>
              Sin indicadores para mostrar en el resumen.
            </div>
          )}
        </div>
      )}

      {/* TAB: Auditoría */}
      {activeTab === 'auditoria' && (
        <div style={{display:'grid',gap:16}}>
          {Object.entries(allIndicators).flatMap(([odsNum, indicadores]) =>
            indicadores.map(ind => {
              const codigo = ind.indicadorCodigo;
              const trail = auditTrail[codigo] || [];
              const isLoading = loadingAudit[codigo];
              return (
                <div key={codigo} style={{background:'#fff',border:'1px solid #D9DEE7',borderRadius:12,padding:18}}>
                  <div style={{display:'flex',justifyContent:'space-between',alignItems:'center',marginBottom:10}}>
                    <div>
                      <div style={{fontFamily:'monospace',fontSize:11,color:'#5A6478'}}>ODS {odsNum} · {codigo}</div>
                      <div style={{fontWeight:600,fontSize:14,color:'#00153f'}}>{ind.indicadorNombre}</div>
                    </div>
                    <button onClick={() => refreshAuditTrail(parseInt(odsNum), ind)} style={{
                      padding:'6px 12px',border:'1px solid #D9DEE7',borderRadius:8,
                      background:'#fff',cursor:'pointer',fontSize:12
                    }}>↻ Refrescar</button>
                  </div>
                  <div style={{background:'#d6e4f3',borderRadius:8,padding:'8px 12px',marginBottom:10,fontSize:12}}>
                    <span style={{color:'#012169',textTransform:'uppercase',letterSpacing:'0.06em',fontSize:10}}>Fórmula vigente: </span>
                    <code style={{color:'#012169'}}>{ind.formulaCustom || ind.formulaDefault || 'valor'}</code>
                  </div>

                  {isLoading ? (
                    <div style={{padding:20,textAlign:'center',color:'#5A6478',fontSize:13}}>Cargando auditoría…</div>
                  ) : trail.length === 0 ? (
                    <div style={{padding:20,textAlign:'center',color:'#5A6478',fontSize:13}}>Sin mediciones registradas todavía.</div>
                  ) : (
                    <div style={{display:'grid',gap:8}}>
                      {trail.map((entry, i) => {
                        const m = entry.medicion || {};
                        const valores = entry.valoresParametros || [];
                        const ok = entry.metaAlcanzada;
                        return (
                          <details key={i} style={{
                            border:'1px solid #F1F4FA',borderRadius:8,padding:'8px 12px',
                            background: ok ? '#f0fdf4' : '#fef9f3'
                          }}>
                            <summary style={{cursor:'pointer',display:'flex',justifyContent:'space-between',alignItems:'center',gap:10}}>
                              <div>
                                <span style={{fontWeight:600,fontSize:13}}>
                                  {m.fechaMedicion || '—'}
                                </span>
                                <span style={{color:'#5A6478',marginLeft:10,fontSize:12}}>
                                  por {m.responsable || 'sistema'}
                                </span>
                              </div>
                              <div>
                                <span style={{fontWeight:700,marginRight:10}}>
                                  {m.valorCalculado ?? '—'} {ind.metaUnidad}
                                </span>
                                {ok != null && (
                                  <span style={{
                                    padding:'2px 8px',borderRadius:99,fontSize:11,fontWeight:600,
                                    background: ok ? '#dcfce7' : '#fee2e2',
                                    color: ok ? '#166534' : '#991b1b'
                                  }}>{ok ? 'LOGRADO' : 'No alcanzada'}</span>
                                )}
                              </div>
                            </summary>
                            <div style={{marginTop:10,fontSize:12,color:'#1B2440'}}>
                              {valores.length > 0 ? (
                                <table style={{width:'100%',borderCollapse:'collapse'}}>
                                  <thead>
                                    <tr style={{borderBottom:'1px solid #D9DEE7'}}>
                                      <th style={{textAlign:'left',padding:'4px 8px',fontSize:11,color:'#5A6478'}}>Variable</th>
                                      <th style={{textAlign:'left',padding:'4px 8px',fontSize:11,color:'#5A6478'}}>Parámetro</th>
                                      <th style={{textAlign:'right',padding:'4px 8px',fontSize:11,color:'#5A6478'}}>Valor ingresado</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                    {valores.map((v, j) => (
                                      <tr key={j} style={{borderBottom:'1px solid #F1F4FA'}}>
                                        <td style={{padding:'4px 8px',fontFamily:'monospace',color:'#012169'}}>
                                          {v.nombre_variable || v.nombreVariable || '—'}
                                        </td>
                                        <td style={{padding:'4px 8px'}}>{v.nombre_parametro || v.nombreParametro || '—'}</td>
                                        <td style={{padding:'4px 8px',textAlign:'right',fontWeight:500}}>
                                          {v.valor_ingresado ?? v.valorIngresado ?? '—'}
                                        </td>
                                      </tr>
                                    ))}
                                  </tbody>
                                </table>
                              ) : (
                                <div style={{color:'#5A6478'}}>Sin valores de parámetros registrados (medición pre-Sprint 2).</div>
                              )}
                              {m.observaciones && (
                                <div style={{marginTop:8,padding:8,background:'#fff',borderRadius:6,fontSize:12,color:'#444'}}>
                                  <strong>Observaciones:</strong> {m.observaciones}
                                </div>
                              )}
                            </div>
                          </details>
                        );
                      })}
                    </div>
                  )}
                </div>
              );
            })
          )}
        </div>
      )}

      {/* ═══ Sprint 17 — Modal de aprobar auditoría ═══════════════════ */}
      {showApproveModal && (
        <div style={{
          position:'fixed',inset:0,background:'rgba(1,33,105,0.45)',
          display:'flex',alignItems:'center',justifyContent:'center',
          zIndex:1000,padding:'1rem',backdropFilter:'blur(4px)'
        }} onClick={() => !auditClosing && setShowApproveModal(false)}>
          <div style={{
            background:'#fff',borderRadius:16,maxWidth:520,width:'100%',
            padding:'1.75rem',boxShadow:'0 24px 48px -12px rgba(1,33,105,0.22)'
          }} onClick={e => e.stopPropagation()}>
            <h3 style={{margin:'0 0 0.5rem',fontSize:'1.2rem',color:'#00153f'}}>
              ✓ Cerrar auditoría
            </h3>
            <p style={{color:'#5A6478',fontSize:'0.9rem',lineHeight:1.5,marginBottom:'1rem'}}>
              Confirmás que <strong>todos los indicadores tienen medición</strong> y
              que el proyecto queda firmado como <strong>auditado</strong>. Después de
              esta acción el proyecto será <strong>inmutable</strong>: no se podrán
              modificar indicadores, mediciones ni documentos.
            </p>
            <label style={{display:'block',fontSize:'0.78rem',fontWeight:700,
                           color:'#012169',marginBottom:'0.4rem',textTransform:'uppercase',letterSpacing:'0.04em'}}>
              Observaciones de cierre (opcional)
            </label>
            <textarea
              value={approveObs}
              onChange={e => setApproveObs(e.target.value)}
              placeholder="Notas para el consultor y archivo histórico..."
              rows={4}
              style={{
                width:'100%',padding:'0.7rem 0.9rem',borderRadius:8,
                border:'1px solid #D9DEE7',fontSize:'0.92rem',fontFamily:'inherit',
                resize:'vertical',color:'#1B2440'
              }}
            />
            {auditError && (
              <div style={{
                marginTop:'0.75rem',padding:'0.6rem 0.8rem',background:'#fef2f2',
                border:'1px solid #fecaca',borderRadius:8,color:'#991b1b',fontSize:'0.85rem'
              }}>
                {auditError}
              </div>
            )}
            <div style={{display:'flex',justifyContent:'flex-end',gap:8,marginTop:'1.25rem'}}>
              <button onClick={() => setShowApproveModal(false)} disabled={auditClosing} style={{
                padding:'0.6rem 1.1rem',border:'1px solid #D9DEE7',borderRadius:8,
                background:'#fff',color:'#5A6478',cursor:'pointer',fontSize:'0.88rem',fontWeight:600
              }}>Cancelar</button>
              <button onClick={handleApprove} disabled={auditClosing} style={{
                padding:'0.6rem 1.25rem',border:'none',borderRadius:8,
                background:'#1F9D55',color:'#fff',cursor:'pointer',fontSize:'0.88rem',fontWeight:700
              }}>{auditClosing ? 'Cerrando...' : 'Cerrar auditoría'}</button>
            </div>
          </div>
        </div>
      )}

      {/* ═══ Sprint 17 — Modal de rechazar auditoría ══════════════════ */}
      {showRejectModal && (
        <div style={{
          position:'fixed',inset:0,background:'rgba(1,33,105,0.45)',
          display:'flex',alignItems:'center',justifyContent:'center',
          zIndex:1000,padding:'1rem',backdropFilter:'blur(4px)'
        }} onClick={() => !auditClosing && setShowRejectModal(false)}>
          <div style={{
            background:'#fff',borderRadius:16,maxWidth:520,width:'100%',
            padding:'1.75rem',boxShadow:'0 24px 48px -12px rgba(1,33,105,0.22)'
          }} onClick={e => e.stopPropagation()}>
            <h3 style={{margin:'0 0 0.5rem',fontSize:'1.2rem',color:'#00153f'}}>
              ✗ Rechazar y devolver al gestor
            </h3>
            <p style={{color:'#5A6478',fontSize:'0.9rem',lineHeight:1.5,marginBottom:'1rem'}}>
              El proyecto vuelve al estado <strong>activo</strong> y el gestor verá tu
              motivo como banner en su Dashboard. Tiene que tener al menos
              <strong> 10 caracteres</strong> para ser útil.
            </p>
            <label style={{display:'block',fontSize:'0.78rem',fontWeight:700,
                           color:'#C53030',marginBottom:'0.4rem',textTransform:'uppercase',letterSpacing:'0.04em'}}>
              Motivo del rechazo (obligatorio)
            </label>
            <textarea
              value={rejectMotivo}
              onChange={e => setRejectMotivo(e.target.value)}
              placeholder="Ej: Los documentos no incluyen los valores del indicador 1.2.1; falta evidencia del trimestre 3..."
              rows={5}
              style={{
                width:'100%',padding:'0.7rem 0.9rem',borderRadius:8,
                border:'1px solid #D9DEE7',fontSize:'0.92rem',fontFamily:'inherit',
                resize:'vertical',color:'#1B2440'
              }}
            />
            <div style={{fontSize:'0.75rem',color:'#94A0B8',marginTop:4,textAlign:'right'}}>
              {rejectMotivo.trim().length} / 10 mínimo
            </div>
            {auditError && (
              <div style={{
                marginTop:'0.75rem',padding:'0.6rem 0.8rem',background:'#fef2f2',
                border:'1px solid #fecaca',borderRadius:8,color:'#991b1b',fontSize:'0.85rem'
              }}>
                {auditError}
              </div>
            )}
            <div style={{display:'flex',justifyContent:'flex-end',gap:8,marginTop:'1.25rem'}}>
              <button onClick={() => setShowRejectModal(false)} disabled={auditClosing} style={{
                padding:'0.6rem 1.1rem',border:'1px solid #D9DEE7',borderRadius:8,
                background:'#fff',color:'#5A6478',cursor:'pointer',fontSize:'0.88rem',fontWeight:600
              }}>Cancelar</button>
              <button onClick={handleReject} disabled={auditClosing || rejectMotivo.trim().length < 10} style={{
                padding:'0.6rem 1.25rem',border:'none',borderRadius:8,
                background:'#C53030',color:'#fff',
                cursor: rejectMotivo.trim().length < 10 ? 'not-allowed' : 'pointer',
                opacity: rejectMotivo.trim().length < 10 ? 0.55 : 1,
                fontSize:'0.88rem',fontWeight:700
              }}>{auditClosing ? 'Enviando...' : 'Rechazar y devolver'}</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default EvaluationPage;
