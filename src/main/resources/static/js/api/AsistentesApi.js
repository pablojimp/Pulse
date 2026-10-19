import { API_CONFIG } from '../config/apiConfig.js';
import { fetchApi } from '../utils/apiUtils.js';

export const AsistenteAPI = {
    obtenerTodos: () => fetchApi('GET', '/asistentes'),

    obtenerPorId: (id) => fetchApi('GET', `/asistentes/${id}`),

    obtenerPorAsistentesId: (id) => fetchApi('GET', `/asistentes/evento?id=${id}`),

    obtenerPorDni: (dni) => fetchApi('GET', `/asistentes/dni?documento=${dni}`),

    obtenerPorEmail: (email) => fetchApi('GET', `/asistentes/email?email=${email}`),

    crear: (asistente) => fetchApi('POST', '/asistentes/crear', asistente),

    actualizar: (id, asistente) => fetchApi('PUT', `/asistentes/${id}`, asistente),

    eliminar: (id) => fetchApi('DELETE', `/asistentes/${id}`)
};