import { FestivalAPI } from '../api/FestivalApi.js';
import { EventoAPI } from '../api/EventoApi.js';

document.addEventListener('DOMContentLoaded', async () => {

    const festivalesIniciales = await FestivalAPI.obtenerTodos();
    const contenedor = document.getElementById("lista-eventos-destacados");
    relleno(festivalesIniciales, contenedor);


    function formateoHora(fecha) {
        let fechaFormateada;

        const fechaFormatear = new Date(fecha);

        if (isNaN(fechaFormatear.getTime())) {
            fechaFormateada = null;
        } else {
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

        for (const evento of arrayRellenar) {

            let fechaFormateada = formateoHora(evento.fechaHora);

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


    document.getElementById("btn-aplicar").addEventListener("click", async () => {

        contenedor.innerHTML = "";

        let min = document.getElementById("precio-min").value;
        let max = document.getElementById("precio-max").value;
        let minDias = document.getElementById("dia-min").value;
        let maxDias = document.getElementById("dia-max").value;
        let edad = document.getElementById("edad").value;
        let festivales = await FestivalAPI.obtenerTodos();

        if (minDias < 0) {
            minDias = 0;
        }

        if (edad != "") {
            const porEdad = await FestivalAPI.obtenerPorEdad(edad);
            const resultado = [];
            for (const evento of festivales) {
                for (const eventoEdad of porEdad) {
                    if (eventoEdad.id == evento.id) {
                        resultado.push(evento);
                    }
                }
            }
            festivales = resultado;
        }

        // Filtro por precio
        let resultadoPrecio = [];
        if (min != "" && max != "") {
            const porPrecio = await FestivalAPI.obtenerPorPrecio(min, max);
            for (const evento of festivales) {
                for (const eventoPrecio of porPrecio) {
                    if (eventoPrecio.id == evento.id) {
                        resultadoPrecio.push(evento);
                    }
                }
            }
            festivales = resultadoPrecio;
        }

        else if (min == "" && max !== "") {
            const porPrecio = await FestivalAPI.obtenerPorPrecio(0, max);
            for (const evento of festivales) {
                for (const eventoPrecio of porPrecio) {
                    if (eventoPrecio.id == evento.id) {
                        resultadoPrecio.push(evento);
                    }
                }
            }
            festivales = resultadoPrecio;
        }

        else if (min != "" && max == "") {
            const porPrecio = await FestivalAPI.obtenerPorPrecio(min, 9999999);
            for (const evento of festivales) {
                for (const eventoPrecio of porPrecio) {
                    if (eventoPrecio.id == evento.id) {
                        resultadoPrecio.push(evento);
                    }
                }
            }
            festivales = resultadoPrecio;
        }


        let resultadoDias = [];
        // Filtrado por dias 
        if (minDias != "" && maxDias != "") {
            const porDias = await FestivalAPI.obtenerPorDias(minDias, maxDias);

            for (const evento of festivales) {

                for (const eventoDias of porDias) {

                    if (eventoDias.id == evento.id) {
                        resultadoDias.push(evento);
                    }
                }
            }
            festivales = resultadoDias;
        }

        else if (minDias == "" && maxDias !== "") {
            const porDias = await FestivalAPI.obtenerPorDias(1, maxDias);

            for (const evento of festivales) {

                for (const eventoDias of porDias) {

                    if (eventoDias.id == evento.id) {
                        resultadoDias.push(evento);
                    }
                }
            }
            festivales = resultadoDias;
        }

        else if (minDias != "" && maxDias == "") {
            const porDias = await FestivalAPI.obtenerPorDias(minDias, 999999);

            for (const evento of festivales) {

                for (const eventoDias of porDias) {

                    if (eventoDias.id == evento.id) {
                        resultadoDias.push(evento);
                    }
                }
            }
            festivales = resultadoDias;
        }




        if (festivales.length == 0) {
            contenedor.innerHTML = `<h2>NADA QUE MOSTRAR...</h2>`
        } else {
            relleno(festivales, contenedor);
        }

    });


    document.getElementById("btn-limpiar").addEventListener("click", async () => {
        // Limpia los filtros
        document.getElementById("precio-min").value = "";
        document.getElementById("precio-max").value = "";
        document.getElementById("dia-min").value = "";
        document.getElementById("dia-max").value = "";
        document.getElementById("edad").value = "";

        // Vuelve a mostrar todos los conciertos
        contenedor.innerHTML = "";
        const todosLosFestivales = await FestivalAPI.obtenerTodos();
        relleno(todosLosFestivales, contenedor);
    });
})