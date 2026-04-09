import axios from 'axios';

const GEO_API_BASE_URL = 'https://api-geo-cr.vercel.app';

/**
 * Service to fetch geographic data from Costa Rica
 * (Province, Canton, District)
 */
export const geoService = {
  /**
   * Fetch all provinces
   */
  async getProvincias() {
    try {
      const response = await axios.get(`${GEO_API_BASE_URL}/provincias`);
      // La API devuelve { status, statusCode, message, data: [...] }
      const rawData = response.data?.data || [];
      return rawData.map(p => ({
        id: String(p.idProvincia),
        nombre: p.descripcion
      }));
    } catch (error) {
      console.error('Error fetching provincias:', error);
      return [];
    }
  },

  /**
   * Fetch cantons for a specific province
   */
  async getCantones(provinciaId) {
    if (!provinciaId) return [];
    try {
      const response = await axios.get(`${GEO_API_BASE_URL}/provincias/${provinciaId}/cantones`);
      const rawData = response.data?.data || [];
      return rawData.map(c => ({
        id: String(c.idCanton),
        nombre: c.descripcion
      }));
    } catch (error) {
      console.error(`Error fetching cantones for province ${provinciaId}:`, error);
      return [];
    }
  },

  /**
   * Fetch districts for a specific canton
   */
  async getDistritos(cantonId) {
    if (!cantonId) return [];
    try {
      const response = await axios.get(`${GEO_API_BASE_URL}/cantones/${cantonId}/distritos`);
      const rawData = response.data?.data || [];
      return rawData.map(d => ({
        id: String(d.idDistrito),
        nombre: d.descripcion
      }));
    } catch (error) {
      console.error(`Error fetching distritos for canton ${cantonId}:`, error);
      return [];
    }
  }
};
