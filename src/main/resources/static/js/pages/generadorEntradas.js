import { EventoAPI } from '../api/EventoApi.js';
import { AsistenteAPI } from '../api/AsistentesApi.js'

document.addEventListener('DOMContentLoaded', async () => {

    const parametros = new URLSearchParams(window.location.search);
    const idEvento = parametros.get('id');
    const evento = await EventoAPI.obtenerPorId(idEvento);

    const lista = document.querySelector(".main-eventos");

    // --- BOTON NUEVA ENTRADA ---
    const btnNueva = document.createElement("button");
    btnNueva.textContent = "Nueva entrada";
    btnNueva.classList.add("btn-nueva-entrada");
    lista.appendChild(btnNueva);

    // --- FORMULARIO (oculto por defecto) ---
    const formulario = document.createElement("div");
    formulario.classList.add("tarjeta");
    formulario.style.display = "none";
    formulario.innerHTML = `
        <div class="datos-entrada">
            <p><span class="entrada-nombre">Nombre: </span><input class="input-editar" type="text" id="nuevo-nombre" placeholder="Nombre"/></p>
            <p><span class="entrada-nombre">Email: </span><input class="input-editar" type="email" id="nuevo-email" placeholder="Email"/></p>
            <p><span class="entrada-nombre">Documento: </span><input class="input-editar" type="text" id="nuevo-doc" placeholder="Documento"/></p>
            <p><span class="entrada-nombre">Edad: </span><input class="input-editar" type="number" id="nuevo-edad" placeholder="Edad"/></p>
        </div>
        <div class="boton-entrada-nueva">
            <button class="btn-crear">Crear entrada</button>
        </div>
    `;
    lista.appendChild(formulario);

    // --- MOSTRAR / OCULTAR FORMULARIO ---
    btnNueva.addEventListener("click", function () {
        if (formulario.style.display == "none") {
            formulario.style.display = "block";
            btnNueva.textContent = "Cancelar";
        } else {
            formulario.style.display = "none";
            btnNueva.textContent = "Nueva entrada";
        }
    });

    // --- CREAR ENTRADA ---
    formulario.querySelector(".btn-crear").addEventListener("click", function () {
        let vacio = true;

        const nuevoNombre = formulario.querySelector("#nuevo-nombre").value.trim();
        const nuevoEmail = formulario.querySelector("#nuevo-email").value.trim();
        const nuevoDoc = formulario.querySelector("#nuevo-doc").value.trim();
        const nuevaEdad = formulario.querySelector("#nuevo-edad").value;

        if (!nuevoNombre || !nuevoEmail || !nuevoDoc || !nuevaEdad) {
            alert("Ninguno de los campos pueden estar vacíos.");
        } else if (nuevaEdad < 18 && evento.edadMinima == 18) {
            alert("No puedes poner una edad menor de 18.");
        } else if (nuevaEdad < 16 && evento.edadMinima == 16) {
            alert("No puedes poner una edad menor de 16.");
        } else {
            vacio = false;
        }

        if (!vacio) {
            AsistenteAPI.crear({
                nombre: nuevoNombre,
                email: nuevoEmail,
                documento: nuevoDoc,
                edad: nuevaEdad,
                eventoId: idEvento
            })
                .then((nuevoAsistente) => {
                    console.log(nuevoAsistente);
                    formulario.querySelector("#nuevo-nombre").value = "";
                    formulario.querySelector("#nuevo-email").value = "";
                    formulario.querySelector("#nuevo-doc").value = "";
                    formulario.querySelector("#nuevo-edad").value = "";

                    formulario.style.display = "none";
                    btnNueva.textContent = "Nueva entrada";

                    formulario.insertAdjacentElement("afterend", crearTarjeta(nuevoAsistente));
                });
        }
    });

    // --- TARJETAS EXISTENTES ---
    for (const asistente of evento.asistentes) {
        lista.appendChild(crearTarjeta(asistente));
    }

    // --- FUNCION CREAR TARJETA ---
    function crearTarjeta(asistente) {
        const tarjeta = document.createElement("div");
        const ticketNum = String((asistente.id * 7919) % 90000 + 10000);
        tarjeta.classList.add("tarjeta");
        tarjeta.innerHTML = `
            <div class="datos-entrada">
                <p><span class="entrada-nombre">Nombre: </span><span class="valor-nombre">${asistente.nombre}</span></p>
                <p><span class="entrada-nombre">Email: </span><span class="valor-email">${asistente.email}</span></p>
                <p><span class="entrada-nombre">Documento: </span><span class="valor-doc">${asistente.documento}</span></p>
                <p><span class="entrada-nombre">Edad: </span><span class="valor-edad">${asistente.edad}</span></p>
                <p><span class="entrada-nombre">Nº Entrada: </span>${ticketNum}</p>
                <br>
                <p><span class="entrada-nombre">Evento: </span>${evento.nombre} - ${evento.lugar.nombreLugar}</p>
            </div>
            <div class="boton-entrada">
                <button class="btn-ver">Ver entrada</button>
                <button class="btn-editar">Editar</button>
                <div class="qr" id="qr-${ticketNum}"></div>
            </div>
        `;

        // --- VER QR ---
        tarjeta.querySelector(".btn-ver").addEventListener("click", function () {
            const qrDiv = tarjeta.querySelector(".qr");
            qrDiv.innerHTML = "";

            const data = `${ticketNum}\n${evento.nombre.replace(" ", "").slice(0, 4)}`;

            new QRCode(qrDiv, {
                text: data,
                width: 100,
                height: 100,
            });

            this.style.display = "none";
            qrDiv.style.display = "block";
        });

        // --- EDITAR ---
        tarjeta.querySelector(".btn-editar").addEventListener("click", function () {
            const estaEditando = tarjeta.dataset.editando == "true";

            if (!estaEditando) {
                const nombreActual = tarjeta.querySelector(".valor-nombre").textContent;
                const emailActual = tarjeta.querySelector(".valor-email").textContent;
                const documentoActual = tarjeta.querySelector(".valor-doc").textContent;
                const edadActual = tarjeta.querySelector(".valor-edad").textContent;

                tarjeta.querySelector(".valor-nombre").innerHTML =
                    `<input class="input-editar" type="text" value="${nombreActual}" />`;
                tarjeta.querySelector(".valor-email").innerHTML =
                    `<input class="input-editar" type="email" value="${emailActual}" />`;
                tarjeta.querySelector(".valor-doc").innerHTML =
                    `<input class="input-editar" type="text" value="${documentoActual}" />`;
                tarjeta.querySelector(".valor-edad").innerHTML =
                    `<input class="input-editar" type="number" value="${edadActual}"/>`;

                const btnEliminar = document.createElement("button");
                
                btnEliminar.classList.add("btn-eliminar");
                btnEliminar.textContent = "Eliminar";
                tarjeta.querySelector(".boton-entrada").appendChild(btnEliminar);

                btnEliminar.addEventListener("click", function () {
                    AsistenteAPI.eliminar(asistente.id)
                        .then(() => {
                            tarjeta.remove();
                        });
                });

                this.textContent = "Guardar";
                tarjeta.dataset.editando = "true";

            } else {
                let vacio = true;
                const nuevoNombre = tarjeta.querySelector(".valor-nombre input").value.trim();
                const nuevoEmail = tarjeta.querySelector(".valor-email input").value.trim();
                const nuevoDoc = tarjeta.querySelector(".valor-doc input").value.trim();
                const nuevaEdad = tarjeta.querySelector(".valor-edad input").value;

                if (!nuevoNombre || !nuevoEmail || !nuevoDoc) {
                    alert("Ninguno de los campos pueden estar vacíos.");
                } else if (nuevaEdad < 18 && evento.edadMinima == 18) {
                    alert("No puedes poner una edad Menor de 18.");
                } else if (nuevaEdad < 16 && evento.edadMinima == 16) {
                    alert("No puedes poner una edad Menor de 16.");
                } else {
                    vacio = false;
                }

                if (!vacio) {
                    AsistenteAPI.actualizar(asistente.id, {
                        nombre: nuevoNombre,
                        email: nuevoEmail,
                        documento: nuevoDoc,
                        edad: nuevaEdad
                    })
                        .then(() => {
                            tarjeta.querySelector(".valor-nombre").innerHTML = nuevoNombre;
                            tarjeta.querySelector(".valor-email").innerHTML = nuevoEmail;
                            tarjeta.querySelector(".valor-doc").innerHTML = nuevoDoc;
                            tarjeta.querySelector(".valor-edad").innerHTML = nuevaEdad;

                            const btnEliminar = tarjeta.querySelector(".btn-eliminar");
                            if (btnEliminar) btnEliminar.remove();

                            this.textContent = "Editar";
                            tarjeta.dataset.editando = "false";
                        });
                }
            }
        });

        return tarjeta;
    }

});