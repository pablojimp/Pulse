import { API_CONFIG } from '../config/apiConfig.js';
import { fetchApi } from '../utils/apiUtils.js';

export const FestivalAPI = {
    obtenerTodos: () => fetchApi('GET', '/festivales'),

    obtenerPorId: (id) => fetchApi('GET', `/festivales/${id}`),

    obtenerPorPrecio: (min, max) => fetchApi('GET', `/festivales/precios?min=${min}&max=${max}`),

    obtenerPorEdad: (edad) => fetchApi('GET', `/festivales/edad/${edad}`),

    obtenerPorDias: (min, max) => fetchApi('GET', `/festivales/dias?min=${min}&max=${max}`),

    actualizar: (id, festival) => fetchApi('PUT', `/festivales/${id}`, festival),

    eliminar: (id) => fetchApi('DELETE', `/festivales/${id}`)
};