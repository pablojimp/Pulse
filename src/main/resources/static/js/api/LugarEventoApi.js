import { API_CONFIG } from '../config/apiConfig.js';
import { fetchApi } from '../utils/apiUtils.js';

export const LugarEventoAPI = {
    obtenerTodos: () => fetchApi('GET', '/lugares'),

    obtenerPorId: (id) => fetchApi('GET', `/lugares/${id}`),

    obtenerPorNombre: (nombre) => fetchApi('GET', `/lugares/nombre?=${nombre}`),

    obtenerPorLugar: (id) => fetchApi('GET', `/lugares/${id}/eventos`),

    obtenerAsistentesPorId: (id) => fetchApi('GET', `/lugares/asistentes/evento?id=${id}`),

    crear: (lugar) => fetchApi('POST', '/lugares', lugar),

    actualizar: (id, lugar) => fetchApi('PUT', `/lugares/${id}`, lugar),

    eliminar: (id) => fetchApi('DELETE', `/lugares/${id}`)
};