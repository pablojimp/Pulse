
import { EventoAPI } from '../api/EventoApi.js';
document.addEventListener('DOMContentLoaded', async () => {

    // Recarga la pagina al venir del botón atras/adelante del navegador
    window.onpageshow = function (event) {
        if (event.persisted) {
            document.location.reload();
        }
    };

    // Obtengo todos mis eventos
    const ListaOrdenada = await EventoAPI.obtenerTodos();

    // Cuantos eventos quiero
    const cantidad = 5;

    // Cuantos eventos quiero
    const promotor = "La Vendicion";

    // obtento todos los eventos de manera aleatoria
    const eventos = await EventoAPI.obtenerPorNumeroAleatorio(ListaOrdenada.length);

    // obtento los eventos para la seccion de eventos Destacados (5 aleatorios)
    const eventosDestacados = await EventoAPI.obtenerPorNumeroAleatorio(cantidad);

    // obtento los eventos para la seccion de promotor Destacados (La vendicion)
    const eventosLaV = (await EventoAPI.obtenerPorPromotor(promotor)).slice(0, cantidad);


    // obtento los eventos para la seccion donde van los 5 aletorios
    const listaEventosDestacados = document.getElementById("lista-eventos-destacados");

    // obtento los eventos para la seccion donde van los 5 eventos de promotor
    const listaEventosPromotor = document.getElementById("lista-eventos-promotor")

    // obtento los eventos para la seccion donde van todos los eventos de forma aleatoria
    const listaEventosTodos = document.getElementById("lista-eventos-todos")

    // vacio contenedores
    listaEventosPromotor.innerHTML = "";
    listaEventosDestacados.innerHTML = "";
    listaEventosTodos.innerHTML = "";



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

    // Relleno todos los huecos de mi pagina
    relleno(eventosLaV, listaEventosPromotor)
    relleno(eventos, listaEventosTodos)
    relleno(eventosDestacados, listaEventosDestacados)

});