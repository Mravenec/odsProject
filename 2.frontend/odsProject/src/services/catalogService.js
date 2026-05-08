import api from './api';

export const catalogService = {
  async getOdsList() {
    try {
      const res = await api.get('/catalog/ods');
      return (res.data || []).map(o => ({
        id: o.id,
        nombre: o.nombre,
        colorHex: o.colorHex || o.color_hex || '#e5243b',
        descripcion: o.descripcion || ''
      }));
    } catch (e) {
      console.error('catalogService.getOdsList:', e);
      return [];
    }
  },

  async getIndicadoresByOds(odsId) {
    try {
      const res = await api.get(`/catalog/ods/${odsId}/indicadores`);
      return (res.data || []).map(i => ({
        id: i.id,
        codigo: i.codigo,
        nombre: i.nombre,
        formulaDefault: i.formulaDefault || i.formula_default || 'valor',
        unidad: i.unidadMedidaDefault || i.unidad_medida_default || 'Porcentaje'
      }));
    } catch (e) {
      console.error('catalogService.getIndicadoresByOds:', e);
      return [];
    }
  },

  async getParametrosMaster(indicadorId) {
    if (!indicadorId) return [];
    try {
      const res = await api.get(`/catalog/indicadores/${indicadorId}/parametros`);
      return (res.data || []).map(p => ({
        id: p.id,
        nombreParametro: p.nombreParametro || p.nombre_parametro,
        descripcionParam: p.descripcionParam || p.descripcion_param,
        tipoDato: p.tipoDato || p.tipo_dato || 'Decimal'
      }));
    } catch (e) {
      return [];
    }
  }
};
