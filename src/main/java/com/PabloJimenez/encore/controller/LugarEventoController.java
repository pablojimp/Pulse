package com.PabloJimenez.encore.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import com.PabloJimenez.encore.entity.Evento;
import com.PabloJimenez.encore.entity.LugarEvento;
import com.PabloJimenez.encore.service.LugarEventoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lugares")
public class LugarEventoController {

    @Autowired
    LugarEventoService lugarEventoService;

    /**
     * Obtiene la lista completa de lugares de eventos registrados.
     *
     * @return {@link List} con todos los {@link LugarEvento} existentes en la base de datos.
     */
    @GetMapping
    public List<LugarEvento> getAllLugares() {
        return lugarEventoService.findAll();
    }

    /**
     * Busca un lugar de evento por su identificador único.
     *
     * @param id identificador del lugar a buscar.
     * @return {@link Optional} que contiene el {@link LugarEvento} si existe,
     *         o vacio si no se encuentra.
     */
    @GetMapping("/{id}")
    public Optional<LugarEvento> getEventoById(@PathVariable Long id) {
        return lugarEventoService.findById(id);
    }

    /**
     * Obtiene todos los eventos que se celebran en un lugar concreto.
     *
     * @param id identificador del lugar cuyos eventos se quieren recuperar.
     * @return {@link List} de {@link Evento} asociados al lugar indicado.
     */
    @GetMapping("/{id}/eventos")
    public List<Evento> getEventosLugar(@PathVariable Long id) {
        return lugarEventoService.findEventos(id);
    }

    /**
     * Busca un lugar de evento por su nombre.
     *
     * @param nombre nombre del lugar a buscar.
     * @return el {@link LugarEvento} cuyo nombre coincide con el parametro,
     *         o {@code null} si no existe.
     */
    @GetMapping("/nombre")
    public LugarEvento getLugarByNombre(@RequestParam("nombre") String nombre) {
        return lugarEventoService.findByNombre(nombre);
    }

    /**
     * Crea un nuevo lugar de evento a partir de los datos recibidos en el cuerpo de la peticion.
     *
     * @param lugar objeto {@link LugarEvento} con los datos del nuevo lugar.
     * @return el {@link LugarEvento} persistido con su identificador generado.
     */
    @PostMapping
    public LugarEvento createLugar(@RequestBody LugarEvento lugar) {
        return lugarEventoService.save(lugar);
    }

    /**
     * Actualiza los datos de un lugar de evento existente.
     * <p>
     * Solo realiza la actualizacion si el lugar con el {@code id} indicado
     * existe en la base de datos.
     * </p>
     *
     * @param id          identificador del lugar a actualizar.
     * @param lugarEvento objeto {@link LugarEvento} con los nuevos datos.
     * @return el {@link LugarEvento} actualizado, o {@code null} si no se encontro.
     */
    @PutMapping("/{id}")
    public LugarEvento updateEvento(@PathVariable Long id, @RequestBody LugarEvento lugarEvento) {
        LugarEvento result = null;
        if (lugarEventoService.findById(id).isPresent()) {
            lugarEvento.setId(id);
            result = lugarEventoService.save(lugarEvento);
        }
        return result;
    }

    /**
     * Elimina un lugar de evento de la base de datos por su identificador.
     * <p>
     * Si el lugar no existe, la operacion no tiene ningún efecto.
     * </p>
     *
     * @param id identificador del lugar a eliminar.
     */
    @DeleteMapping("/{id}")
    public void deleteEvento(@PathVariable Long id) {
        if (lugarEventoService.findById(id).isPresent()) {
            lugarEventoService.deleteById(id);
        }
    }
}