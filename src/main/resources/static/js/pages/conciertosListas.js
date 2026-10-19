
import { ArtistaAPI } from '../api/ArtistaApi.js';
import { ConciertoAPI } from '../api/ConciertoApi.js';
import { LugarEventoAPI } from '../api/LugarEventoApi.js'
document.addEventListener('DOMContentLoaded', async () => {

    // consigo todos los conciertos y todos los lugares
    const conciertosIniciales = await ConciertoAPI.obtenerTodos();
    const eventosTotal = await LugarEventoAPI.obtenerTodos();
    // obtengo el contenedor donde tienen que colocarse todas las tarjetas
    const listaEventosDestacados = document.getElementById("lista-eventos-destacados");

    relleno(conciertosIniciales, listaEventosDestacados)
    lugaresAniadir(eventosTotal)


    // funcion para formatear hora
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

        // hago un bucle de cada concierto que hay para mostrarlo 
        for (const evento of arrayRellenar) {
            // formateo la hora para que se vea correctamente
            let fechaFormateada = formateoHora(evento.fechaHora);

            // relleno mi contendor con las cards correspondientes para la pagina de conciertos
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

        }
    }

    function lugaresAniadir(lugaresLista) {
        const lugares = document.getElementById("lugar")
        for (const lugar of lugaresLista) {
            lugares.innerHTML += `<option value="${lugar.nombreLugar}">${lugar.nombreLugar}</option>`
        }
    }

    function buscarPorLugar(valor, porLugar) {
        let conciertosEncontrados = [];
        for (let evento of porLugar) {
            if (evento.lugar.nombreLugar == valor) {
                conciertosEncontrados.push(evento);
            }
        }
        return conciertosEncontrados;
    }

    document.getElementById("btn-aplicar").addEventListener("click", async () => {
        listaEventosDestacados.innerHTML = "";

        let min = document.getElementById("precio-min").value;
        let max = document.getElementById("precio-max").value;
        let edad = document.getElementById("edad").value;
        let lugarValor = document.getElementById("lugar").value;
        let conciertos = await ConciertoAPI.obtenerTodos();

        if (min < 0) {
            min = 0;
        }

        // Filtro por lugar
        if (lugarValor != "") {
            conciertos = buscarPorLugar(lugarValor, conciertos);
        }

        // Filtro por edad
        if (edad != "") {
            const porEdad = await ConciertoAPI.obtenerPorEdad(edad);
            const resultado = [];
            for (const evento of conciertos) {
                for (const eventoEdad of porEdad) {
                    if (eventoEdad.id == evento.id) {
                        resultado.push(evento);
                    }
                }
            }
            conciertos = resultado;
        }

        // Filtro por precio
        let resultadoPrecio = [];
        if (min != "" && max != "") {
            const porPrecio = await ConciertoAPI.obtenerPorPrecio(min, max);
            for (const evento of conciertos) {
                for (const eventoPrecio of porPrecio) {
                    if (eventoPrecio.id == evento.id) {
                        resultadoPrecio.push(evento);
                    }
                }
            }
            conciertos = resultadoPrecio;
        }


        else if (min == "" && max !== "") {
            const porPrecio = await ConciertoAPI.obtenerPorPrecio(0, max);
            for (const evento of conciertos) {
                for (const eventoPrecio of porPrecio) {
                    if (eventoPrecio.id == evento.id) {
                        resultadoPrecio.push(evento);
                    }
                }
            }
            conciertos = resultadoPrecio;
        }

        else if (min != "" && max == "") {
            const porPrecio = await ConciertoAPI.obtenerPorPrecio(min, 9999999);
            for (const evento of conciertos) {
                for (const eventoPrecio of porPrecio) {
                    if (eventoPrecio.id == evento.id) {
                        resultadoPrecio.push(evento);
                    }
                }
            }
            conciertos = resultadoPrecio;
        }

        if (conciertos.length == 0) {
            listaEventosDestacados.innerHTML = `<h2>NADA QUE MOSTRAR...</h2> 
      `
        } else {
            relleno(conciertos, listaEventosDestacados);
        }

    });

    document.getElementById("btn-limpiar").addEventListener("click", async () => {
        // Limpia los filtros
        document.getElementById("precio-min").value = "";
        document.getElementById("precio-max").value = "";
        document.getElementById("edad").value = "";
        document.getElementById("lugar").value = "";

        // Vuelve a mostrar todos los conciertos
        listaEventosDestacados.innerHTML = "";
        const todosLosConciertos = await ConciertoAPI.obtenerTodos();
        relleno(todosLosConciertos, listaEventosDestacados);
    });
})