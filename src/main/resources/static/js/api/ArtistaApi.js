import { API_CONFIG } from '../config/apiConfig.js';
import { fetchApi } from '../utils/apiUtils.js';

export const ArtistaAPI = {
    obtenerTodos: () => fetchApi('GET', '/artistas'),
    
    obtenerPorId: (id) => fetchApi('GET', `/artistas/${id}`),
    
    obtenerPorCiudad: (ciudad) => fetchApi('GET', `/artistas/ciudad/${ciudad}`),

    obtenerPorNombres: (nombre) => fetchApi('GET', `/artistas/nombre?nombre=${nombre}` ),
    
    actualizar: (id, artista) => fetchApi('PUT', `/artistas/${id}`, artista),
    
    eliminar: (id) => fetchApi('DELETE', `/artista/${id}`)
};