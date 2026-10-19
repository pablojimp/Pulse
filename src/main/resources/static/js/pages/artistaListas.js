import { ArtistaAPI } from '../api/ArtistaApi.js';
document.addEventListener('DOMContentLoaded', async () => {


    const artistas = await ArtistaAPI.obtenerTodos();

    const listaArtistas = document.getElementById("lista-eventos-artistas");

    for (const artista of artistas) {

        let numero = artista.numeroOyentes.toLocaleString();

             listaArtistas.innerHTML += `
                     <div class="card">
                     <a href="ArtistaDetalle.html?id=${artista.id}">
                     <div class="card-imagen">
                         <img class="card-img" src="${artista.imagen}">
                     </div>
                     <div class="card-info">
                         <p class="card-nombre">${artista.nombre}</p>
                         </a>
                         <p>${numero} Oyentes Mensuales</p>

                         <p class="card-lugar-artista">${artista.ciudadNacimiento}</p>
                     </div>
                 </div>
                 `
        
    }



});