
import { ArtistaAPI } from '../api/ArtistaApi.js';
import { EventoAPI } from '../api/EventoApi.js';
document.addEventListener('DOMContentLoaded', async () => {
    // funcion para obtener los eventos en los que aparece un artista
    function obtenerEventosDeUnArtista(idArtista, eventosLista) {
        let listaEventosArtista = []
        for (const evento of eventosLista) {
            if (evento.artista) {
                if (evento.artista.id == idArtista) {
                    listaEventosArtista.unshift(evento)
                }
            }
            else {
                for (const artista of evento.artistas) {
                    if (artista.id == idArtista) {
                        listaEventosArtista.unshift(evento)

                    }
                }
            }
        }
        return listaEventosArtista;
    }
    // funcion para darle formato a la hora
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

    // obtengo el parametro id
    const parametros = new URLSearchParams(window.location.search);
    const idArtista = parametros.get('id');

    // obtengo el artista segun su id
    const artista = await ArtistaAPI.obtenerPorId(idArtista);
    // obtengo todos los eventos
    const eventos = await EventoAPI.obtenerTodos();

    //obtengo contenedores donde ira la informacion
    const detalle = document.querySelector(".detalle")
    const titulo = document.querySelector(".section-head")
    const lista = document.getElementById("lista-eventos")

    // obtengo la lista de los eventos segun el artista
    let listaEventos = obtenerEventosDeUnArtista(idArtista, eventos)

    // Modifico el titulo segun el id del artista
    titulo.innerHTML = `
    <h2 id="nombre-artista">Todos los eventos de</h2>
    <span>${artista.nombre}</span>
    `

    // relleno la pagina de detalle 
    detalle.innerHTML = `
    <div class="detalle-imagen">
    <img class="card-img" src="${artista.imagen}">
    </div>
    
    <div class="detalle-contenido">
    <div class="detalle-info-principal">
    <h2 class="detalle-nombre-artista">${artista.nombre}</h2>
    </div>
    
    <div class="detalle-info-secundaria">
    <div class="detalle-info-lugar">${artista.ciudadNacimiento}</div>
    
    <div class="detalle-info-extra">
    <h3>Información</h3>
    <p class="detalle-info-desc">
    ${artista.descripcion}
    </p>
    </div>
    </div>
    </div>
    `;
    relleno(listaEventos, lista)

});