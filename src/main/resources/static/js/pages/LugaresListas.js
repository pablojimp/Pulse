import { LugarEventoAPI } from '../api/LugarEventoApi.js';
document.addEventListener('DOMContentLoaded', async () => {

    // obtengo todos los lugares
    const todosLosLugares = await LugarEventoAPI.obtenerTodos();

    // obtengo donde van todos los lugares
    const listaLugares = document.getElementById("lista-lugares-destacados");

    // hago un bucle que recora todos los lugares
    for (const lugar of todosLosLugares) {

        // añadado a la lista de lugares cada card de cada lugar
        listaLugares.innerHTML += `
                     <div class="card">
                     <a href="LugarDetalle.html?id=${lugar.id}">
                     <div class="card-imagen">
                         <img class="card-img" src="https://placehold.co/200x200">
                     </div>
                     <div class="card-info">
                         <p class="card-nombre">${lugar.nombreLugar}</p>
                         </a>
                         <p>Aforo: ${lugar.aforoLugar} personas</p>
                         <p class="card-artista">${lugar.localizacion}</p>
                     </div>
                 </div>
                 `

    }
})