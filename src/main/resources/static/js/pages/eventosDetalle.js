
import { EventoAPI } from '../api/EventoApi.js';
import { AsistenteAPI } from '../api/AsistentesApi.js';
import { FestivalAPI } from '../api/FestivalApi.js'
import { ConciertoAPI } from '../api/ConciertoApi.js'
document.addEventListener('DOMContentLoaded', async () => {

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

    const artistasAsistentes = document.querySelector(".section-head");
    // obtengo la id y obtengo que evento es y sus asistentes
    const parametros = new URLSearchParams(window.location.search);
    const idEvento = parametros.get('id');
    const totalAsistente = await AsistenteAPI.obtenerPorAsistentesId(idEvento)
    const evento = await EventoAPI.obtenerPorId(idEvento);

    // obtengo los contenedores donde pondre la informacion
    const detalle = document.querySelector(".detalle")
    const personas = document.getElementById("lista-asistentes-destacados")
    let asistentesMostrar;
    let fechaFormateada = formateoHora(evento.fechaHora)

    // acorto la lista de todos los asistentes a los 5 primeros
    if (totalAsistente.length > 5) {
        asistentesMostrar = totalAsistente.slice(0, 5);
    }

    // obtengo el evento y creo una card que muestre 
    if (evento.artista) {
        detalle.innerHTML = `
            <div class="detalle-imagen">
                <img class="card-img" src=${evento.artista.imagen}>
                </div>
                <div class="detalle-contenido">
                        <div class="detalle-info-principal">
                            <h2 class="detalle-nombre-artista">${evento.artista.nombre}</h2>
                            <span class="detalle-nombre-ciudad">en Granada</span>
                        </div>
                        <div class="detalle-info-secundaria">
                            <div class="detalle-info-lugar">${evento.lugar.nombreLugar}</div>
                            <div class="detalle-info-fecha">${fechaFormateada}</div>
                            <div class="detalle-info-precio">
                                <div class="precio">
                                    <span class="valor-precio">${evento.precio}</span>€
                                </div>
                                <button class="btn-comprar" onclick="window.location.href='entradas.html?id=${idEvento}'">GESTIONA ENTRADAS</button>
                                <button class="btn-comprar" id="btn-editar">EDITA EVENTO</button>
                            </div>
                            <div class="detalle-info-extra">
                                <h3>Información</h3>
                                <p class="detalle-info-desc">
                                    ${evento.artista.descripcion}
                                </p>
                                <ul class="detalle-lista">
                                    <li>Evento para Mayores de ${evento.edadMinima} años</li>
                                    <li>Organizado por ${evento.promotor}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
            `

        artistasAsistentes.innerHTML = `
                            <h2>Gente popular</h2>
                    <span>que asistira</span>
            `

        // bucle para rellenar las personas que asistiran a nuestro evento
        for (const persona of asistentesMostrar) {

            personas.innerHTML += `
                        <div class="card">
                        <div class="card-imagen">
                            <img class="card-img" src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png">
                        </div>
                        <div class="card-info">
                            <p class="card-nombre">${persona.nombre}</p>
                            <a class="card-artista">${persona.edad} años</a>
                        </div>
                    </div>
                    `
        }


    } else {
        detalle.innerHTML = `
            <div class="detalle-imagen">
                    <div class="grid-fotos-detalle">
                        <img src="${evento.artistas[0].imagen}" alt="">
                        <img src="${evento.artistas[1].imagen}" alt="">
                        <img src="${evento.artistas[2].imagen}" alt="">
                        <img src="${evento.artistas[3].imagen}" alt="">
                    </div>
                </div>

                <div class="detalle-contenido">
                        <div class="detalle-info-principal">
                            <h2 class="detalle-nombre-artista">${evento.nombre}</h2>
                        </div>

                        <div class="detalle-info-secundaria">
                            <div class="detalle-info-lugar">${evento.lugar.nombreLugar}</div>
                            <div class="detalle-info-fecha">${fechaFormateada}</div>
                            <div class="detalle-info-precio">
                                <div class="precio">
                                    <span class="valor-precio">${evento.precio}</span>€
                                </div>

                                <button class="btn-comprar" onclick="window.location.href='entradas.html?id=${idEvento}'">GESTIONA ENTRADAS</button>
                                <button class="btn-comprar" id="btn-editar">EDITA EVENTO</button>
                            </div>

                            <div class="detalle-info-extra">
                                <ul class="detalle-lista">
                                    <li>Evento para Mayores de ${evento.edadMinima} años</li>
                                    <li>Organizado por ${evento.promotor}</li>
                                    <li>Duracion: ${evento.numeroDias} dias</li>
                                    <li>Numero Escenarios: ${evento.numeroEscenarios} dias</li>
                                </ul>
                            </div>
                        </div>
                    </div>
            `

        artistasAsistentes.innerHTML = `
                    <h2>Artistas populares</h2>
                    <span>que asistiran</span>
            `

        // bucle para rellenar las personas que asistiran a nuestro evento
        for (const artista of evento.artistas) {
            let numero = artista.numeroOyentes.toLocaleString();
            personas.innerHTML += `
                            <div class="card" data-artista-id="${artista.id}">
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
    }



    document.getElementById("btn-editar").addEventListener("click", function () {
        const estaEditando = document.getElementById("btn-editar").dataset.editando == "true";

        if (!estaEditando) {
            const precioActual = document.querySelector(".valor-precio").textContent;

            document.querySelector(".valor-precio").innerHTML =
                `<input class="input-editar-precio" type="text" value="${precioActual}" />`;

            if (evento.artistas) {
                const cards = document.querySelectorAll("#lista-asistentes-destacados .card");
                for (const card of cards) {
                    card.style.position = "relative";
                    card.innerHTML += `<button class="btn-quitar-artista-card">✕</button>`;
                }
            }

            this.textContent = "GUARDAR";
            document.getElementById("btn-editar").dataset.editando = "true";

        } else {

            let vacio = true;
            const nuevoPrecio = document.querySelector(".valor-precio input").value.trim();

            if (!nuevoPrecio) {
                alert("El precio no puede estar vacío.");
            } else if (isNaN(nuevoPrecio) || Number(nuevoPrecio) < 0) {
                alert("El precio no es válido.");
            } else {
                vacio = false;
            }



            if (!vacio) {
                if (evento.artistas) {
                    // Leer los artistas que quedan en el DOM
                    const cards = document.querySelectorAll("#lista-asistentes-destacados .card");
                    const nuevosArtistas = [];

                    for (const card of cards) {
                        const idArtista = card.dataset.artistaId;
                        // Buscar el artista completo en el array original por su id
                        const artistaEncontrado = evento.artistas.find(artista => artista.id == idArtista);

                        if (artistaEncontrado) {
                            nuevosArtistas.push(artistaEncontrado);
                        }
                    }

                    FestivalAPI.actualizar(idEvento, {
                        nombre: evento.nombre,
                        promotor: evento.promotor,
                        lugar: evento.lugar,
                        asistentes: evento.asistentes,
                        fechaHora: evento.fechaHora,
                        precio: nuevoPrecio,
                        edadMinima: evento.edadMinima,
                        numeroEscenarios: evento.numeroEscenarios,
                        numeroDias: evento.numeroDias,
                        artistas: nuevosArtistas
                    }).then(() => {
                         location.reload();
                    });

                } else {
                    // ========== CONCIERTO ==========
                    ConciertoAPI.actualizar(idEvento, {
                        nombre: evento.nombre,
                        promotor: evento.promotor,
                        lugar: evento.lugar,
                        asistentes: evento.asistentes,
                        fechaHora: evento.fechaHora,
                        precio: nuevoPrecio,
                        edadMinima: evento.edadMinima,
                        duracion: evento.duracion,
                        artista: evento.artista
                    }).then(() => {
                        location.reload();
                    });
                }
            }

        }
    }
    )

    document.getElementById("lista-asistentes-destacados").addEventListener("click", function (artista) {
        let cards = document.querySelectorAll("#lista-asistentes-destacados .card");
        if (artista.target.classList.contains("btn-quitar-artista-card")) {
            if (cards.length > 4) {
                artista.target.parentElement.remove();
            } else {
                alert("Como minimo tienes que tener 4 Artista");
            }
        }
    });
});