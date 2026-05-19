import axios from 'axios';

// Configuración base para axios
const api = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
  timeout: 30000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Interceptor para inyectar el token en las peticiones
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Interceptor para manejar errores — Sprint 1
// El GlobalExceptionHandler del backend devuelve { timestamp, status, error,
// message, sqlState?, hint? }. Aquí surfacamos el message al stack para que la
// UI lo muestre.
api.interceptors.response.use(
  (response) => response,
  (error) => {
    const data = error.response?.data;
    const enrichedMsg = data?.message
      || data?.hint
      || data?.error
      || error.message;
    // Adjunto un message legible al objeto error
    error.userMessage = enrichedMsg;
    console.error(`[API ${error.response?.status || '???'}]`, enrichedMsg, data);
    return Promise.reject(error);
  }
);

export default api;
