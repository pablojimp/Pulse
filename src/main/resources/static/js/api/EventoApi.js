import { API_CONFIG } from '../config/apiConfig.js';
import { fetchApi } from '../utils/apiUtils.js';

export const EventoAPI = {
    obtenerTodos: () => fetchApi('GET', '/eventos'),
    
    obtenerPorId: (id) => fetchApi('GET', `/eventos/${id}`),
    
    obtenerPorPromotor: (promotor) => fetchApi('GET', `/eventos/promotor/${promotor}`),

    obtenerPorNumeroAleatorio: (cantidad) => fetchApi('GET', `/eventos/random?cantidad=${cantidad}`),
    
    actualizar: (id, evento) => fetchApi('PUT', `/eventos/${id}`, evento),
    
    eliminar: (id) => fetchApi('DELETE', `/eventos/${id}`)
};