import { API_CONFIG } from '../config/apiConfig.js';
import { fetchApi } from '../utils/apiUtils.js';

export const ConciertoAPI = {
    obtenerTodos: () => fetchApi('GET', '/conciertos'),

    obtenerPorId: (id) => fetchApi('GET', `/conciertos/${id}`),

    obtenerPorEdad: (edad) => fetchApi('GET', `/conciertos/edad/${edad}`),
    
    obtenerPorPrecio: (min, max) => fetchApi('GET', `/conciertos/precios?min=${min}&max=${max}`),

    actualizar: (id, concierto) => fetchApi('PUT', `/conciertos/${id}`, concierto),

    eliminar: (id) => fetchApi('DELETE', `/conciertos/${id}`)
};