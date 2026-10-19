
import { LugarEventoAPI } from '../api/LugarEventoApi.js';
import { EventoAPI } from '../api/EventoApi.js';
document.addEventListener('DOMContentLoaded', async () => {


    function formateoHora(fecha) {
        let fechaFormateada;

        // Convertimos el parametro a Date
        const fechaFormatear = new Date(fecha);

        // Comprobamos si la fecha es valida (getTime() devuelve NaN si la fecha no lo es)
        if (isNaN(fechaFormatear.getTime())) {
            // Si no es valida, devolvemos null
            fechaFormateada = null;
        } else {
            // Si es valida, la formateamos
            fechaFormateada = fechaFormatear.toLocaleString("es-ES", {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
                hour: "2-digit",
                minute: "2-digit"
            });
        }
        return fechaFormateada;
    }

    function relleno(arrayRellenar, sitioRellenar) {

        // Recorro cada evento de los que se me pasan como parametro
        for (const evento of arrayRellenar) {

            // combierto la hora al formato que necesito
            let fechaFormateada = formateoHora(evento.fechaHora)

            // Compruebo si el evento es de un solo artista o de varios artistas
            if (evento.artista) {

                // Creo la tarjeta como si fuera un concierto
                sitioRellenar.innerHTML += `
                <div class="card">
                <a href="eventoDetalle.html?id=${evento.id}">
                <div class="card-imagen">
                <img class="card-img" src="${evento.artista.imagen}">
                </div>
                <div class="card-info">
                
                <p class="card-nombre">${evento.nombre}</p>
                </a>
                <a class="card-lugar" href="LugarDetalle.html?id=${evento.lugar.id}">${evento.lugar.nombreLugar}</a>
                <br>
                <a class="card-artista" href="ArtistaDetalle.html?id=${evento.artista.id}">${evento.artista.nombre}</a>
                <p class="card-fecha">${fechaFormateada}</p>
                <p class="card-precio">Desde ${evento.precio} €</p>
                </div>
                </div>
                `
            } else {

                // Creo la tarjeta como si fuera un festival
                sitioRellenar.innerHTML += `
                <div class="card" >
                <a href="eventoDetalle.html?id=${evento.id}">
                <div class="grid-fotos">
                <img src="${evento.artistas[0].imagen}" alt="">
                <img src="${evento.artistas[1].imagen}" alt="">
                <img src="${evento.artistas[2].imagen}" alt="">
                <img src="${evento.artistas[3].imagen}" alt="">
                </div>
                <div class="card-info">
                <p class="card-nombre">${evento.nombre}</p>
                </a>
                <a class="card-lugar" href="LugarDetalle.html?id=${evento.lugar.id}">${evento.lugar.nombreLugar}</a>
                
                <p class="card-artista">
                <a class="card-artista" href="ArtistaDetalle.html?id=${evento.artistas[0].id}">
                ${evento.artistas[0].nombre}
                </a>
                ,
                        <a class="card-artista" href="ArtistaDetalle.html?id=${evento.artistas[1].id}">
                        ${evento.artistas[1].nombre}
                        </a>
                        <a class="card-artista" href="eventoDetalle.html?id=${evento.id}">
                        y mas...
                        </a>
                        </p>
                        <p class="card-fecha">${fechaFormateada}</p>
                        <p class="card-precio">Desde ${evento.precio}€</p>
                        </div>
                        </div>`
            }
        }
    }

    // obtengo parametros
    const parametros = new URLSearchParams(window.location.search);
    const idLugar = parametros.get('id');

    // obtengo lugares y eventos
    const lugar = await LugarEventoAPI.obtenerPorId(idLugar);
    const eventos = await LugarEventoAPI.obtenerPorLugar(idLugar);


    // obtengo la lista de lugares
    const detalle = document.querySelector(".detalle")
    const titulo = document.querySelector(".section-head")
    const lista = document.getElementById("lista-eventos")

    titulo.innerHTML = `
    <h2 id="nombre-artista">Todos los eventos en</h2>
    <span>${lugar.nombreLugar}</span>
    `

    detalle.innerHTML = `
    <div class="detalle-imagen">
        <img class="card-img" src="https://placehold.co/300x300">
    </div>

    <div class="detalle-contenido">
        <div class="detalle-info-principal">
            <h2 class="detalle-nombre-artista">${lugar.nombreLugar}</h2>
        </div>

        <div class="detalle-info-secundaria">
            <div class="detalle-info-lugar">Granada</div>

            <div class="detalle-info-extra">
                <h3>Información</h3>
                <p class="detalle-info-desc">
                Aforo Total de ${lugar.nombreLugar}: ${lugar.aforoLugar} personas
                </p>
            </div>
        </div>
    </div>
`;

    relleno(eventos, lista)

});