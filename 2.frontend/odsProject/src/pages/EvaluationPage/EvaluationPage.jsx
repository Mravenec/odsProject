import { evaluationEngine } from '../../utils/evaluationEngine';
import React, { useState, useEffect, useCallback } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';
import { projectService } from '../../services/projectService';

// Map ODS number -> service import (dynamic require pattern)
const getService = async (odsNum) => {
  const n = String(odsNum).padStart(2, '0');
  try {
    const mod = await import(`../../services/objetivo${n}Service.js`);
    return mod.default || mod[`objetivo${n}Service`];
  } catch { return null; }
};

const estadoColors = {
  'LOGRADO':    '#2dba74', 'CERCA META': '#3b5bdb',
  'PROGRESO':   '#e8c33a', 'BAJO':       '#e05555', 'SIN DATOS':  '#aaa'
};
const estadoLabels = {
  'LOGRADO':'✅ Logrado','CERCA META':'🔵 Cerca de la meta',
  'PROGRESO':'🟡 En progreso','BAJO':'🔴 Bajo','SIN DATOS':'⚪ Sin datos'
};

const EvaluationPage = () => {
  const { projectId } = useParams();
  const navigate = useNavigate();
  const { user } = useAuth();
  const [project, setProject]           = useState(null);
  const [allIndicators, setAllIndicators] = useState({});
  const [paramInputs, setParamInputs]   = useState({});
  const [calcResults, setCalcResults]   = useState({});
  const [activeTab, setActiveTab]       = useState('ingreso');
  const [openOds, setOpenOds]           = useState({});
  const [saving, setSaving]             = useState({});
  const [loadingPage, setLoadingPage]   = useState(true);

  const loadAllIndicators = useCallback(async (pid) => {
    const result = {};
    for (let n = 1; n <= 17; n++) {
      try {
        const svc = await getService(n);
        if (!svc) continue;
        // Load indicators enriched (VistaAdminDetalleIndicadores)
        const data = await (svc.getIndicators ? svc.getIndicators(pid) : svc.getAllIndicators?.(pid));
        if (!data || Object.keys(data).length === 0) continue;
        
        // Load parametros for this ODS in this project
        const metasRes = svc.getMetasProyecto ? await svc.getMetasProyecto(pid) : { data: [] };
        const metas = metasRes.data || [];
        
        // Enrich each indicator with its parametros
        const list = Object.values(data).filter(i => i && i.proyectoId);
        const enriched = list.map(ind => ({
          ...ind,
          // Also expose these fields that VistaAdminDetalleIndicadores returns
          indicadorCodigo: ind.indicadorCodigo || ind.codigo,
          indicadorNombre: ind.indicadorNombre || ind.nombre,
          indicadorMasterId: ind.indicadorMasterId || ind.masterId,
          formulaCustom: ind.formulaCustom || ind.formula,
          formulaDefault: ind.formulaDefault || 'valor',
          metaValor: ind.metaValor || ind.targetValue || 0,
          metaUnidad: ind.metaUnidad || ind.unit || 'Porcentaje',
          metaNombre: ind.metaNombre || '',
          estadoIndicador: ind.estadoIndicador || 'SIN DATOS',
          porcentajeLogro: ind.porcentajeLogro || 0,
          // Attach parametros that belong to this indicator
          parametros: metas.filter(m =>
            m.proyectoIndicadorId === ind.id ||
            m.proyecto_indicador_id === ind.id
          )
        }));
        
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
        setProject(projRes.data || projRes);
        const indicators = await loadAllIndicators(parseInt(projectId));
        setAllIndicators(indicators);
        // Open all ODS that have data
        const opened = {};
        Object.keys(indicators).forEach(k => { opened[k] = true; });
        setOpenOds(opened);
      } catch (e) { console.error(e); }
      setLoadingPage(false);
    };
    if (projectId) load();
  }, [projectId]);

  const handleParamChange = (codigo, paramVar, value) => {
    setParamInputs(prev => ({
      ...prev,
      [codigo]: { ...(prev[codigo] || {}), [paramVar]: value }
    }));
  };

  const evaluateFormula = (formula, params) => {
    if (!formula || formula === 'valor') return null;
    try {
      const result = evaluationEngine.evaluateFormula(formula, params);
      return parseFloat(result.toFixed(4));
    } catch { return null; }
  };

  const handleCalcular = async (odsNum, indicator) => {
    const codigo = indicator.indicadorCodigo;
    const formula = indicator.formulaCustom || indicator.formulaDefault || 'valor';
    const params = paramInputs[codigo] || {};
    const result = formula === 'valor'
      ? parseFloat(Object.values(params)[0] || 0)
      : evaluateFormula(formula, params);

    if (result === null || isNaN(result)) {
      return alert('Error al calcular. Verifique los valores ingresados.');
    }

    const metaVal = parseFloat(indicator.metaValor || 0);
    const pct = metaVal > 0 ? Math.min((result / metaVal) * 100, 200) : 0;
    const status = result >= metaVal ? 'Cumplido' : 'En Progreso';

    setCalcResults(prev => ({ ...prev, [codigo]: { result, pct, status } }));

    // Save to backend
    setSaving(prev => ({ ...prev, [codigo]: true }));
    try {
      const svc = await getService(odsNum);
      if (svc?.createMedicion) {
        await svc.createMedicion({
          proyectoIndicadorId: indicator.indicadorMasterId || indicator.id,
          valorCalculado: result,
          fechaMedicion: new Date().toISOString().split('T')[0],
          responsable: user?.fullName || user?.name || 'Sistema'
        });
      }
    } catch (e) { console.error('Error guardando medición:', e); }
    setSaving(prev => ({ ...prev, [codigo]: false }));
  };

  if (loadingPage) return (
    <div style={{display:'flex',alignItems:'center',justifyContent:'center',height:'60vh',flexDirection:'column',gap:12}}>
      <div style={{width:40,height:40,border:'4px solid #e5e7eb',borderTopColor:'#3b5bdb',borderRadius:'50%',animation:'spin 0.8s linear infinite'}} />
      <span style={{color:'#888',fontSize:14}}>Cargando evaluación...</span>
      <style>{`@keyframes spin{to{transform:rotate(360deg)}}`}</style>
    </div>
  );

  const totalIndicadores = Object.values(allIndicators).flat().length;
  const logrados = Object.values(calcResults).filter(r => r.status === 'Cumplido').length;

  return (
    <div style={{maxWidth:900,margin:'0 auto',padding:'24px 20px',fontFamily:'system-ui,sans-serif'}}>
      {/* Header */}
      <div style={{background:'#fff',border:'1px solid #e5e7eb',borderRadius:12,padding:20,marginBottom:20}}>
        <div style={{display:'flex',justifyContent:'space-between',alignItems:'flex-start',flexWrap:'wrap',gap:12}}>
          <div style={{flex:1}}>
            <div style={{fontSize:11,color:'#888',fontFamily:'monospace',letterSpacing:'0.06em',marginBottom:4}}>
              EVALUACIÓN DE PROYECTO
            </div>
            <h1 style={{margin:0,fontSize:20,fontWeight:600,color:'#111'}}>{project?.nombreProyecto}</h1>
          </div>
          <button onClick={() => navigate(-1)} style={{
            padding:'8px 16px',border:'1px solid #ddd',borderRadius:8,
            background:'#fff',cursor:'pointer',fontSize:13,color:'#555'
          }}>← Volver</button>
        </div>
        <div style={{display:'grid',gridTemplateColumns:'repeat(auto-fit,minmax(180px,1fr))',gap:12,marginTop:16}}>
          {[
            ['Sede / Área', project?.sede || project?.sedeId],
            ['Responsable', project?.responsableNombre || '—'],
            ['Ubicación', project?.locationCanton ? `${project.locationCanton}, ${project.locationProvince}` : '—'],
            ['Periodo', project?.fechaInicio ? `${project.fechaInicio} → ${project.fechaFin}` : '—'],
          ].map(([label, val]) => (
            <div key={label} style={{background:'#f9fafb',borderRadius:8,padding:'10px 14px'}}>
              <div style={{fontSize:10,color:'#888',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:3}}>{label}</div>
              <div style={{fontSize:13,fontWeight:500,color:'#333'}}>{val || '—'}</div>
            </div>
          ))}
        </div>
      </div>

      {/* Progress bar */}
      {totalIndicadores > 0 && (
        <div style={{background:'#fff',border:'1px solid #e5e7eb',borderRadius:12,padding:16,marginBottom:20}}>
          <div style={{display:'flex',justifyContent:'space-between',fontSize:13,color:'#555',marginBottom:8}}>
            <span>Progreso de evaluación</span>
            <span style={{fontWeight:600,color:'#2dba74'}}>{logrados} / {totalIndicadores} evaluados</span>
          </div>
          <div style={{background:'#f0f4ff',borderRadius:99,height:8,overflow:'hidden'}}>
            <div style={{
              width: totalIndicadores > 0 ? `${(logrados/totalIndicadores)*100}%` : '0%',
              background:'#2dba74',height:'100%',borderRadius:99,transition:'width 0.4s ease'
            }} />
          </div>
        </div>
      )}

      {/* Tabs */}
      <div style={{display:'flex',gap:4,marginBottom:20}}>
        {[['ingreso','📋 Ingreso de datos'],['resumen','📊 Resumen']].map(([id,label]) => (
          <button key={id} onClick={() => setActiveTab(id)} style={{
            padding:'10px 20px',border:'1px solid',borderRadius:8,cursor:'pointer',fontSize:13,fontWeight:500,
            background: activeTab===id ? '#3b5bdb' : '#fff',
            color: activeTab===id ? '#fff' : '#555',
            borderColor: activeTab===id ? '#3b5bdb' : '#ddd'
          }}>{label}</button>
        ))}
      </div>

      {/* TAB: Ingreso de datos */}
      {activeTab === 'ingreso' && (
        <div>
          {Object.keys(allIndicators).length === 0 ? (
            <div style={{textAlign:'center',padding:60,color:'#888',background:'#fff',borderRadius:12,border:'1px solid #e5e7eb'}}>
              <div style={{fontSize:40,marginBottom:12}}>📭</div>
              <div style={{fontSize:16,fontWeight:500,marginBottom:8}}>Sin indicadores vinculados</div>
              <div style={{fontSize:13}}>Este proyecto aún no tiene indicadores configurados.</div>
            </div>
          ) : Object.entries(allIndicators).map(([odsNum, indicadores]) => (
            <div key={odsNum} style={{background:'#fff',border:'1px solid #e5e7eb',borderRadius:12,marginBottom:12,overflow:'hidden'}}>
              {/* ODS header */}
              <div
                onClick={() => setOpenOds(prev => ({ ...prev, [odsNum]: !prev[odsNum] }))}
                style={{
                  display:'flex',alignItems:'center',gap:12,padding:'14px 18px',cursor:'pointer',
                  background:'#f9fafb',borderBottom: openOds[odsNum] ? '1px solid #e5e7eb' : 'none'
                }}
              >
                <div style={{
                  width:36,height:36,borderRadius:8,background:'#3b5bdb',
                  display:'flex',alignItems:'center',justifyContent:'center',
                  fontSize:14,fontWeight:700,color:'#fff',flexShrink:0
                }}>{odsNum}</div>
                <div style={{flex:1,fontWeight:500,fontSize:14}}>ODS {odsNum} — {indicadores.length} indicador{indicadores.length!==1?'es':''}</div>
                <span style={{color:'#aaa',fontSize:12}}>{openOds[odsNum] ? '▲' : '▼'}</span>
              </div>

              {/* Indicadores */}
              {openOds[odsNum] && indicadores.map(ind => {
                const codigo = ind.indicadorCodigo;
                const calc = calcResults[codigo];
                const isSaving = saving[codigo];
                return (
                  <div key={codigo} style={{padding:18,borderBottom:'1px solid #f3f4f6'}}>
                    <div style={{display:'flex',justifyContent:'space-between',alignItems:'flex-start',marginBottom:14}}>
                      <div>
                        <div style={{fontFamily:'monospace',fontSize:11,color:'#888',marginBottom:3}}>{codigo}</div>
                        <div style={{fontWeight:600,fontSize:14,color:'#111'}}>{ind.indicadorNombre}</div>
                      </div>
                      {calc && (
                        <div style={{
                          padding:'4px 12px',borderRadius:99,fontSize:12,fontWeight:600,
                          background: calc.status==='Cumplido' ? '#e6f9f0' : '#fff3f3',
                          color: calc.status==='Cumplido' ? '#2dba74' : '#e05555',
                          border: `1px solid ${calc.status==='Cumplido' ? '#a7f0ca' : '#ffb8b8'}`
                        }}>
                          {calc.status==='Cumplido' ? '✅ Cumplido' : '🔄 En Progreso'}
                        </div>
                      )}
                    </div>

                    {/* Info row: formula + meta */}
                    <div style={{display:'grid',gridTemplateColumns:'1fr 1fr',gap:10,marginBottom:14}}>
                      <div style={{background:'#f0f4ff',borderRadius:8,padding:'10px 14px'}}>
                        <div style={{fontSize:10,color:'#5577dd',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:4}}>Fórmula</div>
                        <code style={{fontSize:12,color:'#3b5bdb'}}>{ind.formulaCustom || ind.formulaDefault || 'valor'}</code>
                      </div>
                      <div style={{background:'#f0fdf4',borderRadius:8,padding:'10px 14px'}}>
                        <div style={{fontSize:10,color:'#22c55e',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:4}}>Meta</div>
                        <div style={{fontSize:13,fontWeight:500,color:'#166534'}}>
                          {ind.metaValor} {ind.metaUnidad}
                          {ind.metaNombre && <div style={{fontSize:11,color:'#555',marginTop:2,fontWeight:400}}>{ind.metaNombre}</div>}
                        </div>
                      </div>
                    </div>

                    {/* Result display */}
                    {calc && (
                      <div style={{background: calc.status==='Cumplido'?'#e6f9f0':'#fff3f3',borderRadius:8,padding:'10px 14px',marginBottom:14,display:'flex',alignItems:'center',gap:12}}>
                        <div style={{flex:1}}>
                          <div style={{fontSize:10,color:'#666',textTransform:'uppercase',letterSpacing:'0.06em',marginBottom:2}}>Resultado calculado</div>
                          <div style={{fontSize:18,fontWeight:700,color: calc.status==='Cumplido'?'#2dba74':'#e05555'}}>
                            {calc.result} <span style={{fontSize:13,fontWeight:400}}>{ind.metaUnidad}</span>
                          </div>
                        </div>
                        <div style={{textAlign:'center'}}>
                          <div style={{fontSize:10,color:'#666',marginBottom:2}}>Progreso</div>
                          <div style={{fontSize:16,fontWeight:700}}>{calc.pct.toFixed(1)}%</div>
                        </div>
                      </div>
                    )}

                    {/* Param inputs */}
                    <div style={{display:'grid',gridTemplateColumns:'repeat(auto-fit,minmax(200px,1fr))',gap:10,marginBottom:14}}>
                      {(ind.parametros || []).map((param, idx) => (
                        <div key={idx}>
                          <label style={{fontSize:12,color:'#555',display:'block',marginBottom:4}}>
                            <code style={{
                              fontFamily:'monospace',
                              color:'#3b5bdb',
                              marginRight:6,
                              background:'#f0f4ff',
                              padding:'1px 5px',
                              borderRadius:3
                            }}>{param.nombreVariable || param.nombreParametro}</code>
                            {/* Si el nombre descriptivo es diferente al nombre de variable, mostrarlo */}
                            {param.nombreParametro && param.nombreParametro !== param.nombreVariable && (
                              <span style={{color:'#888',fontSize:11}}>{param.nombreParametro}</span>
                            )}
                          </label>
                          <input
                            type="number"
                            value={paramInputs[codigo]?.[param.nombreVariable] ?? param.valorActual ?? ''}
                            onChange={e => handleParamChange(codigo, param.nombreVariable, e.target.value)}
                            placeholder={`Valor actual: ${param.valorActual ?? 0}`}
                            style={{
                              width:'100%',border:'1px solid #ddd',borderRadius:8,
                              padding:'9px 12px',fontSize:13,boxSizing:'border-box'
                            }}
                          />
                        </div>
                      ))}
                    </div>

                    <button
                      onClick={() => handleCalcular(odsNum, ind)}
                      disabled={isSaving}
                      style={{
                        padding:'10px 22px',border:'none',borderRadius:8,
                        background: isSaving ? '#94a3b8' : '#3b5bdb',color:'#fff',
                        cursor: isSaving ? 'not-allowed' : 'pointer',fontSize:13,fontWeight:500
                      }}
                    >
                      {isSaving ? 'Guardando...' : '🔢 Calcular y Evaluar'}
                    </button>
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
                <div key={codigo} style={{
                  background:'#fff',border:'1px solid #e5e7eb',borderRadius:12,padding:16
                }}>
                  <div style={{fontFamily:'monospace',fontSize:10,color:'#888',marginBottom:4}}>{codigo}</div>
                  <div style={{fontWeight:600,fontSize:13,marginBottom:10,color:'#111',lineHeight:1.4}}>
                    {ind.indicadorNombre}
                  </div>
                  <div style={{
                    display:'inline-block',padding:'3px 10px',borderRadius:99,fontSize:11,fontWeight:600,
                    background: (estadoColors[estado]||'#aaa') + '22',
                    color: estadoColors[estado] || '#666',
                    marginBottom:10
                  }}>{estadoLabels[estado] || estado}</div>
                  <div style={{background:'#f3f4f6',borderRadius:99,height:6,overflow:'hidden'}}>
                    <div style={{
                      width: Math.min(pct,100) + '%',height:'100%',borderRadius:99,
                      background: estadoColors[estado] || '#aaa',transition:'width 0.4s'
                    }} />
                  </div>
                  <div style={{textAlign:'right',fontSize:11,color:'#888',marginTop:4}}>
                    {pct.toFixed(1)}% de {ind.metaValor} {ind.metaUnidad}
                  </div>
                </div>
              );
            })
          )}
          {Object.values(allIndicators).flat().length === 0 && (
            <div style={{gridColumn:'1/-1',textAlign:'center',padding:40,color:'#888'}}>
              Sin indicadores para mostrar en el resumen.
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default EvaluationPage;
